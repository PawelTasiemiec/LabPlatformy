using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;

namespace Lab01
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        BackgroundWorker worker = new BackgroundWorker();
        DotNetProjectEntities4 db;

        

        public static string getBetween(string strSource, string strStart, string strEnd)//wyszukiwanie zawartosci w srodku stringa(1 argument zrodlo, 2 poczatek stringa, 3 koniec stringa)
        {
            int Start, End;
            if (strSource.Contains(strStart) && strSource.Contains(strEnd))
            {
                Start = strSource.IndexOf(strStart, 0) + strStart.Length;
                End = strSource.IndexOf(strEnd, Start);
                return strSource.Substring(Start, End - Start);
            }
            else
            {
                return "";
            }
        }
        async Task<string> AccessTheWebAsync() //dostep do sieci async
        {
            
            using (HttpClient client = new HttpClient())
            {

                  Task<string> getStringTask = client.GetStringAsync("https://uni.wroc.pl/en/");
             
            

                string urlContents = await getStringTask;
                
                string between = getBetween(urlContents, "img src=\"", "\" alt" ); //znalezienie obrazka
                
                  var fullFilePath = @between; //sciezka do obrazka

                  BitmapImage bitmap = new BitmapImage(); //przerobienie na obraz bitowy
                  bitmap.BeginInit();
                  bitmap.UriSource = new Uri(fullFilePath, UriKind.Absolute);
                  bitmap.EndInit();

                  image.Source = bitmap;
                  
                
                return between;
            }
        }
        ObservableCollection<PersonView> people = new ObservableCollection<PersonView>
        {
            new PersonView { Name = "Przemo", Age = 1, Surname ="kowal" },
            new PersonView { Name = "Radzio", Age = 2, Surname ="kowal" }
        };

        public ObservableCollection<PersonView> Items
        {
            get => people;
        }

        ObservableCollection<CityView> cities = new ObservableCollection<CityView>
        {
            new CityView {Name = "Warsaw", Pressure = "1023 hPa", Temperature = 290}
        };

        public ObservableCollection<CityView> Cities
        {
            get => cities;
        }

        public MainWindow()
        {
            InitializeComponent();
            DataContext = this;

            worker.WorkerReportsProgress = true;
            worker.WorkerSupportsCancellation = true;
            worker.DoWork += Worker_DoWork;
            worker.ProgressChanged += Worker_ProgressChanged;
        }
        
        private void AddNewPersonButton_Click(object sender, RoutedEventArgs e)
        {
                if (ageTextBox.Text.All(char.IsDigit))
                {
                    if (!(nameTextBox.Text.Any(char.IsDigit) || surnameTextBox.Text.Any(char.IsDigit)))
                    {

                        people.Add(new PersonView { Age = int.Parse(ageTextBox.Text), Name = nameTextBox.Text, Surname = surnameTextBox.Text, ImageRelativePath = image.Source });
                        image.Source = null;
                        try
                        {
                            List<Person> allPeople = db.People.ToList();
                            int id;
                            if (allPeople.Count > 0)
                                id = allPeople[allPeople.Count - 1].Id;
                            else
                                id = 0;
                            db.People.Add(new Person { Name = nameTextBox.Text, Surname = surnameTextBox.Text, Age = int.Parse(ageTextBox.Text), Id = id + 1 });
                            db.SaveChanges();
                        }
                        catch
                        {
                        MessageBox.Show("Failed to add a new record to database");
                        }
                    }
                    else
                        MessageBox.Show("Name and Surname can't include numbers");
                }
                else
                    MessageBox.Show("Age must be a number");
        }

        public void AddCity(CityView city)
        {
            Application.Current.Dispatcher.Invoke(() => { Cities.Add(city); });
        }


        private async void LoadCity(object sender, RoutedEventArgs e)
        {
            string CitiesText = CityNameTextBox.Text;
            CitiesText.Replace(" ", string.Empty);
            string[] citiesArr = CitiesText.Split(',');

            for (int i = 0; i < citiesArr.Length; i++)
            {
                string responseXML = await WeatherConnection.LoadDataAsync(citiesArr[i]);
                WeatherDataEntry result;

                using (MemoryStream stream = new MemoryStream(Encoding.UTF8.GetBytes(responseXML)))
                {
                    result = ParseWeather_XmlReader.Parse(stream);
                    Cities.Add(new CityView()
                    {
                        Name = result.City,
                        Pressure = result.Pressure.ToString() + " hPa",
                        Temperature = (int)Math.Round(result.Temperature)
                    });
                    try
                    {
                        List<City> allCities = db.Cities.ToList();
                        int id;
                        if (allCities.Count > 0)
                            id = allCities[allCities.Count - 1].Id;
                        else
                            id = 0;
                        db.Cities.Add(new City { Name = result.City, Pressure = result.Pressure.ToString() + " hPa", Temperature = (int)Math.Round(result.Temperature), Id = id + 1 });
                        db.SaveChanges();
                    }
                    catch
                    {
                        MessageBox.Show("Failed to add a new record to database");
                    }
                }
            }
        }

        private async void LoadWeatherData(object sender, RoutedEventArgs e)
        {
            if (worker.IsBusy != true)
                worker.RunWorkerAsync();
        }

        private void Worker_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {
            weatherDataProgressBar.Value = e.ProgressPercentage;
            weatherDataTextBlock.Text = e.UserState as string;
        }

        
        private void Worker_DoWork(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            List<string> cities = new List<string>{
                "London", "Warsaw", "Paris" };
            for (int i = 1; i <= cities.Count; i++)
            {
                string city = cities[i - 1];

                if (worker.CancellationPending == true)
                {
                    worker.ReportProgress(0, "Cancelled");
                    e.Cancel = true;
                    return;
                }
                else
                {
                    worker.ReportProgress(
                        (int)Math.Round((float)i * 100.0 / (float)cities.Count),
                        "Loading " + city + "...");
                    string responseXML = WeatherConnection.LoadDataAsync(city).Result;
                    WeatherDataEntry result;

                    using (MemoryStream stream = new MemoryStream(Encoding.UTF8.GetBytes(responseXML)))
                    {
                        result = ParseWeather_XmlReader.Parse(stream);
                        AddCity(
                            new CityView()
                            {
                                Name = result.City,
                                Pressure = result.Pressure.ToString() + " hPa",
                                Temperature = (int)Math.Round(result.Temperature)
                            });
                        try
                        {
                            List<City> allCities = db.Cities.ToList();
                            int id;
                            if (allCities.Count > 0)
                                id = allCities[allCities.Count - 1].Id;
                            else
                                id = 0;
                            db.Cities.Add(new City { Name = result.City, Pressure = result.Pressure.ToString() + " hPa", Temperature = (int)Math.Round(result.Temperature), Id = id + 1 });
                            db.SaveChanges();
                        }
                        catch
                        {
                            MessageBox.Show("Failed to add a new record to database");
                        }
                    }
                    Thread.Sleep(2000);
                }
            }
            worker.ReportProgress(100, "Done");
        }
        

        private void Photo_Click(object sender, RoutedEventArgs e)
        {
                OpenFileDialog op = new OpenFileDialog();
                
                if (op.ShowDialog() == true)
                {
                    image.Source = new BitmapImage(new Uri(op.FileName));
                }
        }
        
        protected void UpdateProgressBlock(string text, TextBlock textBlock)
        {
            try
            {
                Application.Current.Dispatcher.Invoke(() =>
                {
                    textBlock.Text = text;
                });
            }
            catch { }
        }
        
        private void Cancel_Click(object sender, RoutedEventArgs e)
        {
            if (worker.WorkerSupportsCancellation == true)
            {
                weatherDataTextBlock.Text = "Cancelling...";
                worker.CancelAsync();
            }
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            db = new DotNetProjectEntities4();
            DispatcherTimer dt = new DispatcherTimer();
            dt.Interval = TimeSpan.FromSeconds(5);
            dt.Tick += Dt_Tick;
            dt.Start();
        }

        private async void Dt_Tick(object sender, EventArgs e)
        {
            string responseXML = await WeatherConnection.LoadDataAsync("Warsaw");
            WeatherDataEntry result;

            using (MemoryStream stream = new MemoryStream(Encoding.UTF8.GetBytes(responseXML)))
            {
                result = ParseWeather_XmlReader.Parse(stream);
                Cities.Add(new CityView()
                {
                    Name = result.City,
                    Pressure = result.Pressure.ToString() + " hPa",
                    Temperature = (int)Math.Round(result.Temperature)
                });
                try
                {
                    List<City> allCities = db.Cities.ToList();
                    int id;
                    if (allCities.Count > 0)
                        id = allCities[allCities.Count - 1].Id;
                    else
                        id = 0;
                    db.Cities.Add(new City { Name = result.City, Pressure = result.Pressure.ToString() + " hPa", Temperature = (int)Math.Round(result.Temperature), Id = id + 1 });
                    db.SaveChanges();
                }
                catch
                {
                    MessageBox.Show("Failed to add a new record to database");
                }
            }
        }

        private void LoadFromDb(object sender, RoutedEventArgs e)
        {
            try
            {
                List<Person> allPeople = db.People.ToList();
                List<City> allCities = db.Cities.ToList();
                people.Clear();
                cities.Clear();
                foreach (Person person in allPeople)
                {
                    people.Add(new PersonView { Name = person.Name, Surname = person.Surname, Age = person.Age, ImageRelativePath = null });
                }
                foreach (City city in allCities)
                {
                    cities.Add(new CityView { Name = city.Name, Pressure = city.Pressure, Temperature = city.Temperature });
                }
            }
            catch
            {
                MessageBox.Show("Failed to load data from database");
            }
        }

        private void DeleteSelectedPersonButton_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                PersonView selectedPerson = (PersonView)listBox.SelectedItem;
                var search = db.People.FirstOrDefault(x => x.Name == selectedPerson.Name && x.Surname == selectedPerson.Surname && x.Age == selectedPerson.Age);
                db.People.Remove(search);
                db.SaveChanges();
                people.Remove((PersonView)listBox.SelectedItem);
            }
            catch
            {
                MessageBox.Show("Pick a person you want to remove from the list");
            }
        }

        private void DeleteSelectedCityButton_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                CityView selectedCity = (CityView)listBoxCity.SelectedItem;
                var search = db.Cities.FirstOrDefault(x => x.Name == selectedCity.Name && x.Pressure == selectedCity.Pressure && x.Temperature == selectedCity.Temperature);
                db.Cities.Remove(search);
                db.SaveChanges();
                cities.Remove((CityView)listBoxCity.SelectedItem);
            }
            catch
            {
                MessageBox.Show("Pick a city you want to remove from the list");
            }
        }
    }
}