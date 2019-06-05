import javax.swing.JDialog;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Point;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;

class ScoreTable extends JDialog {

    private JTextArea pole;
    private String tekst = "";
    private JButton ok;

    public ScoreTable() {
        setTitle("Wyniki");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(280, 260);
        setResizable(false);
        setModal(true);
        setLocation(new Point(310, 200));

        pole = new JTextArea();
        pole.setBounds(70, 15, 160, 170);
        pole.setEditable(false);
        pole.setBackground(new Color(238,238,238));
        pole.setFont(new Font("Verdana", Font.BOLD, 12));

        ok = new JButton("OK");
        ok.setBounds(115, 190, 50, 30);
        ok.setFocusable(false);
        ok.setMargin(new Insets(1,1,1,1));
        ok.addActionListener(new Close());

        wyswietlWyniki();

        add(pole);
        add(ok);

        setVisible(true);
    }

    public void wyswietlWyniki() {
        try {

            FileReader fr = new FileReader("src/plik.txt");
            BufferedReader br = new BufferedReader(fr);

            String tmp = "";
            int i = 1;
            while ((tmp = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(tmp);
                tekst += String.valueOf(i) + ". ";

                while (st.hasMoreTokens()) {
                    String temp = st.nextToken();
                    if (temp.equals(","))
                        tekst += " -  " + st.nextToken() + "\n";
                    else
                        tekst += temp + " ";
                }
                ++i;
            }

            br.close();
        }
        catch(FileNotFoundException ex) {
            pole.setText("Nie znaleziono pliku !!!");
        }
        catch (IOException ex) {
            pole.setText("Wystapil blad odczytu pliku");
        }

        pole.setText(tekst);
    }

    private class Close implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}