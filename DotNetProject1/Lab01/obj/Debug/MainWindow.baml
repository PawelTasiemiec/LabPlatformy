   M S B A M L   `   `   `  ����@  <Lab01, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullclr-namespace:Lab01Lab01  5   	       Lab01.MainWindow   5   	   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   %localclr-namespace:Lab01     5   	   -   5          � Title$  
MainWindow��5   	   $��673.333��6   $	��800��6-   .��5	      � $��-11,0,11.333,10q�6    � ColumnDefinitions 5
   
   �� 5      $��0.75* �6   �� 5      $��0.75* �6   �� 5      .+�5   
   �� $��People��6   $��Center=�6!   �� 5   
   -   5         ��Name$ listBox��5      $��12,20,6,20.333q�6#   ��6g   �� *Items+��5      �� 5      .R�5      ��  �� Orientation$ 
Horizontal=�6!   .+�5      �� $��75��6?   ��6$   ��  �� Path$
 Name�6?   �� 5      $��75��6B   ��6$   �� $ Surname�6B   �� 5      $��15��6>   ��6$   �� $	 Age�6>   �� 5      $��50��6J   ��6    �� $ ImageRelativePath�6J   5      5      5      �� 5   
   $��Cities��6   $��1��6!   $��Center=�61   �� 5   
   -   5        $ listBoxCity��5      $��6,20,12,20.333q�6'   $��1��6�   ��6k   �� *Cities+��5      �� 5       .R�5!      �� $ 
Horizontal=�6!   .+�5"      �� $��65��6?   ��6$   �� $
 Name�6?   �� 5#      $��65��6C   ��6$   �� $ Pressure�6C   �� 5$      $��65��6F   ��6$   �� $ Temperature�6F   5%      5&      5(      �� 5)   
   $��2��6   $��
0,12,12,12q�6%   $ Vertical=�69   .+�5*      �  5+      �� 5,      �� 5-      .+�50      �� .+�51      �� -   5         ��Name$ image��51      $��194.333��6+   �� 52      -   5         ��Name$ photo��52      $��Add picture��6,    �� IsCancel . $��22.627��6f   53      �� 55      $��1��6   .+�56      �� $	��100��6   ��6*   ��  �� ElementName$ listBox��$$ SelectedItem.ImageRelativePath��� 57      $
��Wrap=�6    $
��Name��64   �� 58      	 �� 
IsReadOnly	 . ���#eee$��23��6C   ��6O   �� $ listBox��$ SelectedItem.Name��� 59      $
��Wrap=�6    $��Surname��64   �� 5:      	 . ���#eee$��23��6C   ��6O   �� $ listBox��$ SelectedItem.Surname��� 5;      $
��Wrap=�6    $	��Age��64   �� 5<      	 . ���#eee$��23��6C   ��6P   �� $ listBox��$ SelectedItem.Age�5=      5>      �� 5?      $
��Wrap=�6   $
��Name��6,   �� 5@      -   5        $��nameTextBox��5@      $��23��6*   
 �� TextWrapping$

 Wrap=�66   $��	Jarosław��6J   $��0,0,0,10q�6Z   �� 5A      $
��Wrap=�6   $��Surname��6,   �� 5B      -   5        $��surnameTextBox��5B      $��23��6-   $

 Wrap=�69   $��	Jarosław��6M   $��0,0,0,10q�6]   �� 5C      $
��Wrap=�6   $	��Age��6,   �� 5D      -   5        $��
ageTextBox��5D      $��23��6(   $

 Wrap=�64   $��99��6H   $��0,0,0,10q�6S   �� 5E      -	   5        $��addNewPersonButton��5E      $��Add new person��61   6J   �� 5F      -
   5        $ ��deleteSelectedPersonButton��5F      $��Delete selected person��67   6X   �� 5G      -   5        $��LoadDataFromDb��5G      $��Load data from database��6+   $��0,0,0,10q�6`   �� 5H      -   5        $ weatherDataButton��5H      $4��.Load the weather data from the known locations��60   6i   �� 5I      -   5        $��Cancel��5I      6&   � 5J      -   5         �Name$ weatherDataProgressBar��5J      $��20��6:   �� 5K      -   5         ��Name$ weatherDataTextBlock��5K      �� 5L      $��
City name:��6   �� 5M      -   5         ��Name$ CityNameTextBox��5M      $
��Left=�6/   $��23��6J   $

 Wrap=�6V   $	��Top=�6j   $	��120��6�   �� 5N      -   5        $ loadCity��5N      $��	Load city��6'   6;   �� 5O      -   5        $��DeleteSelectedCityButton��5O      $��Delete selected city��65   6T   5P      5T      5U      