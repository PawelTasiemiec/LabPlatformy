   M S B A M L   `   `   `  ����@  <Lab01, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullclr-namespace:Lab01Lab01  5   	       Lab01.MainWindow   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5   	   R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   %localclr-namespace:Lab01     5   	     � Title$  
MainWindow��5   	   $	��800��6   $	��800��6)   .��5	      �  � ColumnDefinitions 5
   
   �� 5      �� 5      .+�5   
   �� -   5         ��Name$ listBox��5      $��20q�6#   ��6[   �� *Items+��5      �� 5      .R�5      ��  �� Orientation$ 
Horizontal=�6!   .+�5      �� $	��100��6?   ��6$   ��  �� Path$
 Name�6?   �� 5      $	��100��6B   ��6$   �� $ Surname�6B   �� 5      $	��100��6>   ��6$   �� $	 Age�6>   �� 5      $��50��6J   ��6    �� $ ImageRelativePath�6J   5      5      5      �� 5   
   $��1��6   $��20q�6%   $ Vertical=�61   .+�5      �  5      �� 5       �� 5!      .+�5$      �� .+�5%      �� -   5         ��Name$ image��5%      $��194.333��6+   �� 5&      -   5         ��Name$ photo��5&      $��Add picture��6,    �� IsCancel . $��22.627��6f   5'      �� 5)      $��1��6   .+�5*      �� $	��100��6   ��6*   ��  �� ElementName$ listBox��$$ SelectedItem.ImageRelativePath��� 5+      $
��Wrap=�6    $
��Name��64   �� 5,      	 �� 
IsReadOnly	 . ���#eee$��23��6C   ��6O   �� $ listBox��$ SelectedItem.Name��� 5-      $
��Wrap=�6    $��Surname��64   �� 5.      	 . ���#eee$��23��6C   ��6O   �� $ listBox��$ SelectedItem.Surname��� 5/      $
��Wrap=�6    $	��Age��64   �� 50      	 . ���#eee$��23��6C   ��6P   �� $ listBox��$ SelectedItem.Age�51      52      �� 53      $
��Wrap=�6   $
��Name��6,   �� 54      -   5        $��nameTextBox��54      $��23��6*   
 �� TextWrapping$

 Wrap=�66   $��	Jarosław��6J   $��0,0,0,10q�6Z   �� 55      $
��Wrap=�6   $��Surname��6,   �� 56      -   5        $��surnameTextBox��56      $��23��6-   $

 Wrap=�69   $��	Jarosław��6M   $��0,0,0,10q�6]   �� 57      $
��Wrap=�6   $	��Age��6,   �� 58      -   5        $��
ageTextBox��58      $��23��6(   $

 Wrap=�64   $��99��6H   $��0,0,0,10q�6S   �� 59      -   5        $��addNewPersonButton��59      $��Add new person��61   6J   �� 5:      -   5        $ dodaj��5:      $!��Perform stupid calculations��6$   6J   �� 5;      $��Final number��6   �� 5<      -	   5         ��Name$ finalNumberTextBox��5<      $
��Left=�62   $��23��6M   $

 Wrap=�6Y   $	��Top=�6m   $	��120��6�   �� 5=      -
   5         ��Name$ progressTextBlock��5=      $
��Wrap=�64   �� 5>      -   5        $ progressTextBlock2��5>      $
��Wrap=�64   �� 5?      -   5        $ weatherDataButton��5?      $4��.Load the weather data from the known locations��60   6i   � 5@      -   5         �Name$ weatherDataProgressBar��5@      $��20��6:   �� 5A      -   5        $ weatherDataTextBlock��5A      �� 5B      $��
City name:��6   �� 5C      -   5        $ CityNameTextBox��5C      $
��Left=�6/   $��23��6J   $

 Wrap=�6V   $	��Top=�6j   $	��120��6�   �� 5D      -   5        $��CANCEL��5D      6&   5G      5K      5L      