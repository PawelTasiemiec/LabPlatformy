import com.sun.org.apache.xpath.internal.operations.Mult;

import javax.swing.JDialog;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class GameOver extends JDialog {

    private JLabel imieTekst;
    private JLabel imieTekst2;
    private JTextField imie;
    private JTextField imie2;
    private JLabel twojWynik;
    private JLabel twojWynik2;
    private JButton ok;

    public GameOver() {
        setTitle("Koniec Gry");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(270, 160);
        setModal(true);
        setLocation(new Point(310, 230));
        setResizable(false);

        imieTekst = new JLabel("Podaj imię:");
        imieTekst.setBounds(35, 20, 65, 20);
        imieTekst.setBackground(Color.white);

        imie = new JTextField("");
        imie.setBounds(105, 20, 100, 20);
        if(Snake.single_play)
        {
            twojWynik = new JLabel("Wynik: " + GameBoard.wynik);
            twojWynik.setBounds(105, 50, 110, 20);
            twojWynik.setBackground(Color.white);

            ok = new JButton("OK");
            ok.setBounds(115, 85, 50, 30);
        }
        else
        {
            setSize(455,160);
            twojWynik = new JLabel("Gracz 1: " + Multiplayer.wynik);
            twojWynik.setBounds(105, 50, 110, 20);
            twojWynik.setBackground(Color.white);

            imieTekst2 = new JLabel("Podaj imię:");
            imieTekst2.setBounds(250, 20, 65, 20);
            imieTekst2.setBackground(Color.white);

            imie2 = new JTextField("");
            imie2.setBounds(320, 20, 100, 20);

            twojWynik2 = new JLabel("Gracz 2: " + Multiplayer.wynik2);
            twojWynik2.setBounds(320, 50, 110, 20);
            twojWynik2.setBackground(Color.white);

            ok = new JButton("OK");
            ok.setBounds(200, 85, 50, 30);

            add(imieTekst2);
            add(imie2);
            add(twojWynik2);
        }


        ok.setFocusable(false);
        ok.setMargin(new Insets(1,1,1,1));
        ok.addActionListener(new Koniec());

        add(ok);
        add(imieTekst);
        add(imie);
        add(twojWynik);

        setVisible(true);
    }

    public void zapiszWynik() {
        ArrayList<String> gracz = new ArrayList<String>();
        ArrayList<String> punkty = new ArrayList<String>();
        boolean b = true;

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/plik.txt"));
            String tmp = "";

            while ((tmp = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(tmp);

                while (st.hasMoreTokens()) {
                    String temp = st.nextToken();
                    if (temp.equals(",")) {
                        punkty.add(st.nextToken());
                    }
                    else if (b) {
                        gracz.add(temp);
                        b = false;
                    }
                }
                b = true;
            }
            br.close();
        }
        catch (IOException ex) {
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/plik.txt"));
            boolean bool = false;
            int i =0;
            if(Snake.single_play) {
                for (int j = 0; j < 10; j++) {
                    if ((!bool) && (GameBoard.wynik > Integer.parseInt(punkty.get(i)))) {
                        bw.write(imie.getText() + " , " + GameBoard.wynik + '\n');
                        bool = true;
                    } else {
                        bw.write(gracz.get(i) + " , " + punkty.get(i));
                        ++i;
                    }
                    bw.newLine();
                }
            }
            else{
                for (int j = 0; j < 10; j++) {
                    if ((!bool) && (Multiplayer.wynik > Integer.parseInt(punkty.get(i)))) {
                        bw.write(imie.getText() + " , " + Multiplayer.wynik);
                        bool = true;
                    }
                    else if((!bool) && (Multiplayer.wynik2 > Integer.parseInt(punkty.get(i)))){
                        bw.write(imie2.getText() + " , " + Multiplayer.wynik2);
                        bool = true;
                    }
                    else {
                        bw.write(gracz.get(i) + " , " + punkty.get(i));
                        ++i;
                    }
                    bw.newLine();
                }
            }
            bw.close();
        }
        catch (IOException ex) {
        }
    }

    private class Koniec implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!(imie.getText().equals(""))) {
                if (Character.isLetterOrDigit((imie.getText()).charAt(0))) {
                    zapiszWynik();
                    ScoreTable okno = new ScoreTable();
                    GameBoard.wynik = 0;
                    Multiplayer.wynik = 0;
                    Multiplayer.wynik2 = 0;
                    Snake.score.setText("0");
                    if(!(Snake.single_play))
                    {Snake.score2.setText("0");}
                    dispose();
                }
                else {
                    imie.setText("Nobody");
                }
            }
        }
    }
}