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
            new Person { Name = "P1", Age = 1, Surname ="zbys" },
            new Person { Name = "P2", Age = 2, Surname ="zbys" }
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

             if ((ageTextBox.Text).GetType()==typeof(string))
             if(nameTextBox.Text.GetType()==typeof(string))
            people.Add(new Person { Age = int.Parse(ageTextBox.Text), Name = nameTextBox.Text, Surname = surnameTextBox.Text }); 
                 
        }

        private void Photo_Click(object sender, RoutedEventArgs e)
        {
            {
                OpenFileDialog op = new OpenFileDialog();
                op.Title = "Select a picture";
                op.Filter = "All supported graphics|*.jpg;*.jpeg;*.png|" +
                  "JPEG (*.jpg;*.jpeg)|*.jpg;*.jpeg|" +
                  "Portable Network Graphic (*.png)|*.png";
                if (op.ShowDialog() == true)
                {
                    image.Source = new BitmapImage(new Uri(op.FileName));
                }

            }

        }

        
        

        
    }
}