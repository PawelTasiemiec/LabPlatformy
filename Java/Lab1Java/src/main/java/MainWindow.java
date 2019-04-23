import javax.swing.*;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.Random;
import static java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.Dimension;
import java.awt.Toolkit;
class MainWindow extends JFrame {


    private JPanel panelMain;
    private JButton button1;



    MainWindow() {
        setUndecorated(true);

        setBackground(new Color(0,0,0,0));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        Random rand = new Random();
        this.setSize(screenWidth, screenHeight);

button1.setVisible(true);


        this.setTitle("Platformy programistyczne .Net i Java, labolatorium, Java, lab01");
        this.setContentPane(panelMain);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int widt = rand.nextInt(screenWidth-50);
                int heigt = rand.nextInt(screenHeight-50);
                button1.setLocation(widt,heigt);
            }
        });

    }


}
