using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
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

namespace Lab01
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
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
        ObservableCollection<Person> people = new ObservableCollection<Person>
        {
            new Person { Name = "Przemo", Age = 1, Surname ="kowal" },
            new Person { Name = "Radzio", Age = 2, Surname ="kowal" }
        };

        public ObservableCollection<Person> Items
        {
            get => people;
        }

        public MainWindow()
        {
            InitializeComponent();
            DataContext = this;
        }

        private void AddNewPersonButton_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                if (ageTextBox.Text.All(char.IsDigit))
                {
                    if (!(nameTextBox.Text.Any(char.IsDigit) || surnameTextBox.Text.Any(char.IsDigit)))
                    {
                        people.Add(new Person { Age = int.Parse(ageTextBox.Text), Name = nameTextBox.Text, Surname = surnameTextBox.Text, ImageRelativePath = image.Source });
                        image.Source = null;
                    }
                    else
                        MessageBox.Show("Imie i nazwisko nie mogą zawierać liczb");
                }
                else
                    MessageBox.Show("Wiek musi być liczbą");
            } 
            catch(Exception)
            {
                MessageBox.Show("podaj poprawne dane");
            }
        }

        private void Photo_Click(object sender, RoutedEventArgs e)
        {
                OpenFileDialog op = new OpenFileDialog();
                
                if (op.ShowDialog() == true)
                {
                    image.Source = new BitmapImage(new Uri(op.FileName));
                }
        }

        private void ListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            
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

        class WaitingAnimation
        {
            private int maxNumberOfDots;
            private int currentDots;
            private MainWindow sender;


            public WaitingAnimation(int maxNumberOfDots, MainWindow sender)
            {
                this.maxNumberOfDots = maxNumberOfDots;
                this.sender = sender;
                currentDots = 0;
            }

            public void CheckStatus(Object stateInfo)
            {
                sender.UpdateProgressBlock(
                    "Processing" +
                    new Func<string>(() => {
                        StringBuilder strBuilder = new StringBuilder(string.Empty);
                        for (int i = 0; i < currentDots; i++)
                            strBuilder.Append(".");
                        return strBuilder.ToString();
                    })(), sender.progressTextBlock
                );
                if (currentDots == maxNumberOfDots)
                    currentDots = 0;
                else
                    currentDots++;
            }
            
        }

        private async void Dodaj_Click(object sender, RoutedEventArgs e)
        {
            
            
                try
                {
                    int finalNumber = int.Parse(this.finalNumberTextBox.Text);
                var getResultTask = AccessTheWebAsync();
                    var waitingAnimationTask =
                        new System.Threading.Timer(
                            new WaitingAnimation(10, this).CheckStatus,
                            null,
                            TimeSpan.FromMilliseconds(0),
                            TimeSpan.FromMilliseconds(500)
                        );
                    var waitingAnimationTask2 = new System.Timers.Timer(100);
                    waitingAnimationTask2.Elapsed +=
                        (innerSender, innerE) => {
                            this.UpdateProgressBlock(
                                innerE.SignalTime.ToLongTimeString(),
                                this.progressTextBlock2);
                        };
                    waitingAnimationTask2.Disposed +=
                        (innerSender, innerE) => {
                            this.progressTextBlock2.Text = "Koniec świata";
                        };
                    waitingAnimationTask2.Start();
                    string result = await getResultTask; 
                    waitingAnimationTask.Dispose();
                    waitingAnimationTask2.Dispose();

                this.progressTextBlock.Text = "Obtained result: " + result;
                }
                catch (Exception ex)
                {
                    this.progressTextBlock.Text = "Error! " + ex.Message;
                }

            }
        
    }
}