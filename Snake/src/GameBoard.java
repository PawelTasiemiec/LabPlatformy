import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.util.ArrayList;

class GameBoard extends JPanel implements ActionListener {

    protected Image head;
    protected Image dot;
    protected Image appleImage;
    protected Image mouseImage;
    protected Food mouse;
    protected Food apple;
    protected final int HEIGHT = 750;
    protected final int WIDTH = 800;
    protected ArrayList<Integer> x,y;
    static int dots;
    static Timer timer;
    static boolean status = true;
    static int wynik = 0;
    private int speed_mouse = 7;
    protected Move move;

    public GameBoard() {

        addKeyListener(new KAdapter());

        move = new Move();
        apple = new Food(0,0);
        mouse = new Food(0,0);
        setBackground(Color.black);

        ImageIcon ih = new ImageIcon("src/head.jpg");
        head = ih.getImage();

        ImageIcon ib = new ImageIcon("src/body.png");
        dot = ib.getImage();

        ImageIcon ia = new ImageIcon("src/apple.jpg");
        appleImage = ia.getImage();

        ImageIcon ic = new ImageIcon("src/mouse.jpg");
        mouseImage = ic.getImage();

        setFocusable(true);
        initGame();
    }

    public void initGame() {
        x = new ArrayList<Integer>();
        y = new ArrayList<Integer>();

        dots = 3;

        for (int i = 0; i < dots; i++) {
            x.add(700 + i*20);
            y.add(180);
        }

        locateApple();
        locateMouse();

        timer = new Timer(Snake.speed, this);
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
        }
    }

    public void checkCollision() {

        for(int i=1; i<dots; i++){
            if((x.get(i).equals(x.get(0))) && (y.get(i).equals(y.get(0)))) {
                status = false;
                break;
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
    }

    public void locateApple() {
        boolean bl = true;
        while (bl) {
            int r = 1;
            while(r%2!=0)
                r = (int) (Math.random()*76+1);
            apple.x = r*10;
            while(r%2!=0)
                r = (int) (Math.random()*71+1);
            apple.y = r*10;

            if ((x.contains(apple.x)) && (y.contains(apple.y)))
                continue;
            else
                bl = false;
        }
    }

    public void move_mouse()
    {
        mouse.x += speed_mouse;
        if(mouse.x>=780)
            speed_mouse *= -1;
        else if(mouse.x<=0)
            speed_mouse *= -1;
        for(int i=2; i<dots; i++){
            if((x.get(i).equals(mouse.x)) && (y.get(i).equals(mouse.y))) {
                status = false;
                break;
            }
        }
    }

    public void locateMouse(){
        boolean bl = true;
        while (bl) {
            int r = 1;
            while(r%2!=0)
                r = (int) (Math.random()*76+1);
            mouse.x = r*10;
            while(r%2!=0)
                r = (int) (Math.random()*71+1);
            mouse.y = r*10;

            if ((x.contains(mouse.x)) && (y.contains(mouse.y)))
                continue;
            else
                bl = false;
        }
    }

    public void checkApple() {
        if ((x.get(0) == apple.x) && (y.get(0) == apple.y)) {
            ++dots;
            wynik += 10;
            Snake.score.setText(String.valueOf(wynik));

            x.add(x.get(1));
            y.add(y.get(1));

            locateApple();
        }
    }

    public void checkMouse()
    {
        if ((x.get(0) >= mouse.x-10 && x.get(0) <= mouse.x + 10) && (y.get(0) == mouse.y)) {
            ++dots;
            wynik += 20;
            Snake.score.setText(String.valueOf(wynik));

            x.add(x.get(1));
            y.add(y.get(1));

            locateMouse();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (status) {
            checkApple();
            checkMouse();
            checkCollision();
            move.move(x,y,dots);
            move_mouse();
            repaint();
        }
        else {
            timer.stop();
            x.clear();
            y.clear();
            dots = 3;

            for (int i = 0; i < dots; i++) {
                x.add(700 + i*20);
                y.add(180);
            }

            status = true;
            move.left = true;
            move.right = false;
            move.up = false;
            move.down = false;

            Snake.start.setEnabled(true);
            Snake.scores.setEnabled(true);
            Snake.changeSpeed.setEnabled(true);
            Snake.multiplayer.setEnabled(true);
            Snake.singleplayer.setEnabled(true);
            repaint();

            GameOver koniec = new GameOver();
        }
    }

    private class KAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            move.changeDirection(key);
        }
    }
}
