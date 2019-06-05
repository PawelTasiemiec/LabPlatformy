import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class Multiplayer extends GameBoard{
    private ArrayList<Integer> x2,y2;
    private int dots2;
    static int wynik2 = 0;
    private Image head2;
    private Image dot2;
    public Multiplayer()
    {
        super();
        addKeyListener(new KAdapter2());
        ImageIcon ih2 = new ImageIcon("src/head2.jpg");
        head2 = ih2.getImage();

        ImageIcon ib2 = new ImageIcon("src/body2.jpg");
        dot2 = ib2.getImage();
        init_multiplayer();
    }

    public void init_multiplayer()
    {
        x2 = new ArrayList<Integer>();
        y2 = new ArrayList<Integer>();

        dots2 = 3;

        for (int i = 0; i < dots2; i++) {
            x2.add(100 - i*20);
            y2.add(180);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (status) {
            g.drawImage(appleImage, apple.x, apple.y, this);
            g.drawImage(mouseImage,mouse.x,mouse.y,this);

            for (int i = 0; i < dots; i++) {
                if (i == 0)
                    g.drawImage(head, x.get(i), y.get(i), this);
                else
                    g.drawImage(dot, x.get(i), y.get(i), this);
            }
            for (int i = 0; i < dots2; i++) {
                if (i == 0)
                    g.drawImage(head2, x2.get(i), y2.get(i), this);
                else
                    g.drawImage(dot2, x2.get(i), y2.get(i), this);
            }
        }
    }

    public void checkCollision2() {

        for(int i=1; i<dots; i++){
            if((x.get(i).equals(x.get(0))) && (y.get(i).equals(y.get(0)))) {
                status = false;
                break;
            }
        }

        for(int i=1; i<dots2; i++)
        {
            if((x2.get(i).equals(x2.get(0))) && (y2.get(i).equals(y2.get(0)))) {
                status = false;
                break;
            }
        }

        for(int i=0; i<dots2; i++)
        {
            for(int j=0;j<dots;j++){
                if(x2.get(i).equals(x.get(j)) && y2.get(i).equals(y.get(j))){
                    status = false;
                    break;
                }
            }
        }

        if (x.get(0) > WIDTH-20)
            status = false;

        if (x.get(0) < 20)
            status = false;

        if (y.get(0) > HEIGHT-20)
            status = false;

        if (y.get(0) < 20)
            status = false;

        if (x2.get(0) > WIDTH-20)
            status = false;

        if (x2.get(0) < 20)
            status = false;

        if (y2.get(0) > HEIGHT-20)
            status = false;

        if (y2.get(0) < 20)
            status = false;
    }

    public void checkApple2() {
        if ((x.get(0) == apple.x) && (y.get(0) == apple.y)) {
            ++dots;
            wynik += 10;
            Snake.score.setText(String.valueOf(wynik));

            x.add(x.get(1));
            y.add(y.get(1));

            locateApple();
        }

        if ((x2.get(0) == apple.x) && (y2.get(0) == apple.y)) {
            ++dots2;
            wynik2 += 10;
            Snake.score2.setText(String.valueOf(wynik2));

            x2.add(x2.get(1));
            y2.add(y2.get(1));

            locateApple();
        }
    }

    public void checkMouse2()
    {
        if ((x.get(0) >= mouse.x-5 && x.get(0) <= mouse.x + 5) && (y.get(0) == mouse.y)) {
            ++dots;
            wynik += 20;
            Snake.score.setText(String.valueOf(wynik));

            x.add(x.get(1));
            y.add(y.get(1));

            locateMouse();
        }

        if ((x2.get(0) >= mouse.x-5 && x2.get(0) <= mouse.x + 5) && (y2.get(0) == mouse.y)) {
            ++dots2;
            wynik2 += 20;
            Snake.score2.setText(String.valueOf(wynik2));

            x2.add(x2.get(1));
            y2.add(y2.get(1));

            locateMouse();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (status) {
            checkApple2();
            checkMouse2();
            checkCollision2();
            move.move(x,y,dots);
            move.move2(x2,y2,dots2);
            move_mouse();
            repaint();
        }
        else {
            timer.stop();
            x.clear();
            y.clear();
            dots = 3;
            x2.clear();
            y2.clear();
            dots2 = 3;

            for (int i = 0; i < dots; i++) {
                x.add(700 + i*20);
                y.add(180);
            }

            for (int i = 0; i < dots2; i++) {
                x2.add(100 - i*20);
                y2.add(180);
            }

            status = true;
            move.left = true;
            move.right = false;
            move.up = false;
            move.down = false;

            move.left2 = false;
            move.right2 = true;
            move.up2 = false;
            move.down2 = false;

            Snake.start.setEnabled(true);
            Snake.scores.setEnabled(true);
            Snake.changeSpeed.setEnabled(true);
            Snake.multiplayer.setEnabled(true);
            Snake.singleplayer.setEnabled(true);
            repaint();

            GameOver koniec = new GameOver();
        }
    }

    private class KAdapter2 extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            move.changeDirection(key);
            move.changeDirection2(key);
        }
    }
}
