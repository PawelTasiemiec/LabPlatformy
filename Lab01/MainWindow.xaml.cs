using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Drawing;
using System.IO;
using System.Linq;
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
                        people.Add(new Person { Age = int.Parse(ageTextBox.Text), Name = nameTextBox.Text, Surname = surnameTextBox.Text, ImageRelativePath = image.Source });
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
    }
}