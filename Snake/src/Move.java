import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Move {
    public boolean left = true;
    public boolean right = false;
    public boolean up = false;
    public boolean down = false;
    public boolean left2 = false;
    public boolean right2 = true;
    public boolean up2 = false;
    public boolean down2 = false;

    public void move(ArrayList<Integer>x, ArrayList<Integer>y, int dots){
        int tmp = 0;
        for (int i = dots-1; i > 0; i--) {
            x.set(i, x.get(i-1));
            y.set(i, y.get(i-1));
        }

        if (left) {
            tmp = x.get(0);
            x.set(0, tmp-20);
        }
        if (right) {
            tmp = x.get(0);
            x.set(0, tmp+20);
        }
        if (up) {
            tmp = y.get(0);
            y.set(0, tmp-20);
        }
        if (down) {
            tmp = y.get(0);
            y.set(0, tmp+20);
        }
    }
    public void changeDirection(int key){
        if ((key == KeyEvent.VK_LEFT) && (!right)) {
            left = true;
            up = false;
            down = false;
        }

        if ((key == KeyEvent.VK_RIGHT) && (!left)) {
            right = true;
            up = false;
            down = false;
        }

        if ((key == KeyEvent.VK_UP) && (!down)) {
            up = true;
            right = false;
            left = false;
        }

        if ((key == KeyEvent.VK_DOWN) && (!up)) {
            down = true;
            right = false;
            left = false;
        }
    }

    public void move2(ArrayList<Integer>x2, ArrayList<Integer>y2, int dots2) {
        int tmp2 = 0;
        for (int i = dots2-1; i > 0; i--) {
            x2.set(i, x2.get(i-1));
            y2.set(i, y2.get(i-1));
        }

        if (left2) {
            tmp2 = x2.get(0);
            x2.set(0, tmp2-20);
        }
        if (right2) {
            tmp2 = x2.get(0);
            x2.set(0, tmp2+20);
        }
        if (up2) {
            tmp2 = y2.get(0);
            y2.set(0, tmp2-20);
        }
        if (down2) {
            tmp2 = y2.get(0);
            y2.set(0, tmp2+20);
        }

    }

    public void changeDirection2(int key){
        if ((key == KeyEvent.VK_A) && (!right2)) {
            left2 = true;
            up2 = false;
            down2 = false;
        }

        if ((key == KeyEvent.VK_D) && (!left2)) {
            right2 = true;
            up2 = false;
            down2 = false;
        }

        if ((key == KeyEvent.VK_W) && (!down2)) {
            up2 = true;
            right2 = false;
            left2 = false;
        }

        if ((key == KeyEvent.VK_S) && (!up2)) {
            down2 = true;
            right2 = false;
            left2 = false;
        }
    }
}
