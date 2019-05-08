import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.Random;
import static java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;
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
            @Override

            public void actionPerformed(ActionEvent e) {

                int new_width;
                int random_width = rand.nextInt(screenWidth-75);
                int random_height = rand.nextInt(screenHeight-75);

                int new_height ;


                    new_width = random_width - button1.getLocation().x;


                    new_height = random_height - button1.getLocation().y;
                System.out.println(button1.getLocation().x);
                System.out.println(button1.getLocation().y);
                System.out.println(new_width);
                System.out.println(new_height);
                int stala1=new_width/100;
                int stala2=new_height/100;
                int stala3=button1.getLocation().x;
                int stala4=button1.getLocation().y;
                class MultithreadingDemo extends Thread
                {

                    public void run()
                    {
                        for(int i=0;i<100;i++) {

                        button1.setLocation(stala3 + (i * stala1), stala4 + (i * stala2));
                        System.out.println(button1.getLocation().x);
                        System.out.println(button1.getLocation().y);

                                try {
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }



                    }
                    }
                }
                MultithreadingDemo object = new MultithreadingDemo();
                object.start();

            }
        }
        );

    }


}
