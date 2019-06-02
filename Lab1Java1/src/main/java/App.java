import java.awt.EventQueue;
import java.awt.Color;
public class App{

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                MainWindow frame = new MainWindow();

                frame.setVisible(true);


            }
        });
    }
}