﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab01
{
    public class PersonView
    {
        public string Name { get; set; }
        public string Surname { get; set; }
        public int Age { get; set; }
        public System.Windows.Media.ImageSource ImageRelativePath { get; set; }
        static public int count = 0;
        public PersonView()
        {
            count++;
        }
    }
}