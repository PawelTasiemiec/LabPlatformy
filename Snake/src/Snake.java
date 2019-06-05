import javax.swing.JFrame;
import java.awt.Point;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.*;
public class Snake extends JFrame {

    static JButton start;
    static JButton scores;
    static JButton multiplayer;
    static JButton singleplayer;
    private JButton close;
    static JButton changeSpeed;
    private JLabel scoreText;
    private JLabel scoreText2;
    static JLabel score;
    static JLabel score2;
    static int speed = 100;
    private GameBoard gameBoard;
    private Multiplayer mult;
    static boolean single_play = true;

    public Snake(boolean single) {
        super("Snake");
        single_play = single;
        setSize(800, 865);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocation(new Point(240, 120));

        Insets insets = new Insets(1,1,1,1);

        start = new JButton("Start");
        start.setMargin(insets);
        start.setFocusable(false);
        start.setBounds(15, 780, 120, 30);
        start.addActionListener(new Start());

        singleplayer = new JButton("Singleplayer");
        singleplayer.setMargin(insets);
        singleplayer.setFocusable(false);
        singleplayer.setBounds(145, 780, 120, 30);
        singleplayer.addActionListener(new Single());

        multiplayer = new JButton("Multiplayer");
        multiplayer.setMargin(insets);
        multiplayer.setFocusable(false);
        multiplayer.setBounds(275, 780, 120, 30);
        multiplayer.addActionListener(new Multi());

        scores = new JButton("Wyniki");
        scores.setMargin(insets);
        scores.setFocusable(false);
        scores.setBounds(405, 780, 120, 30);
        scores.addActionListener(new Scores());

        close = new JButton("Zakończ");
        close.setMargin(insets);
        close.setFocusable(false);
        close.setBounds(665, 780, 120, 30);
        close.addActionListener(new Close());

        changeSpeed = new JButton("Zmień prędkość");
        changeSpeed.setMargin(insets);
        changeSpeed.setFocusable(false);
        changeSpeed.setBounds(535, 780, 120, 30);
        changeSpeed.addActionListener(new ChangeSpeed());

        if(single){
            scoreText = new JLabel("Wynik:");
            scoreText.setBounds(15, 750, 100, 30);

            score = new JLabel("0");
            score.setBounds(55, 750, 42, 30);

            add(score);
            add(scoreText);
        }
        else{
            scoreText = new JLabel("Wynik gracz 1:");
            scoreText.setBounds(15, 750, 100, 30);

            score = new JLabel("0");
            score.setBounds(100, 750, 42, 30);

            scoreText2 = new JLabel(("Wynik gracz 2:"));
            scoreText2.setBounds(685,750,100,30);

            score2 = new JLabel("0");
            score2.setBounds(770,750,42,30);

            add(score);
            add(scoreText);
            add(score2);
            add(scoreText2);
        }

        if(single){
            gameBoard = new GameBoard();
            gameBoard.setBounds(0, 0, 800, 750); //rozmiar planszy do gry
            add(gameBoard);
        }
        else{
            mult = new Multiplayer();
            mult.setBounds(0, 0, 800, 750);
            add(mult);
        }


        add(scores);
        add(start);
        add(close);
        add(changeSpeed);
        add(multiplayer);
        add(singleplayer);

        setVisible(true);
    }

    private class Close implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GameBoard.timer.stop();
            dispose();
        }
    }

    private class ChangeSpeed implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            getSpeed();
        }
    }

    private class Multi implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            single_play = false;
            new Snake(false);
            dispose();
        }
    }

    private class Single implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            single_play = true;
            new Snake(true);
            dispose();
        }
    }

    private class Start implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            start.setEnabled(false);
            scores.setEnabled(false);
            changeSpeed.setEnabled(false);
            multiplayer.setEnabled(false);
            singleplayer.setEnabled(false);
            if(single_play){
                GameBoard.timer = new Timer(speed,gameBoard);
                GameBoard.timer.start();
            }
            else{
                Multiplayer.timer = new Timer(speed,mult);
                Multiplayer.timer.start();
            }
        }
    }

    private class Scores implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ScoreTable ow = new ScoreTable();
        }
    }

    public void getSpeed() {
        final String[] options = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
        final int level = JOptionPane.showOptionDialog(null, "Choose a level from 1-10:", "Choose a Level",
                JOptionPane.CLOSED_OPTION, JOptionPane.CLOSED_OPTION, null, options, options);
        switch (level) {
            case 0:
                speed = 400;
                break;
            case 1:
                speed = 300;
                break;
            case 2:
                speed = 200;
                break;
            case 3:
                speed = 170;
                break;
            case 4:
                speed = 150;
                break;
            case 5:
                speed = 120;
                break;
            case 6:
                speed = 100;
                break;
            case 7:
                speed = 90;
                break;
            case 8:
                speed = 50;
                break;
            case 9:
                speed = 30;
                break;
            default:
                speed = 400;
                break;
        }
    }

    public static void main(String[] args) {
        new Snake(true);
    }
}