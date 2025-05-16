
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel panel;

    public KeyHandler(GamePanel panel){
            this.panel = panel;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();

        if ((code == KeyEvent.VK_D )||(code == KeyEvent.VK_RIGHT)) { 
            panel.right = true;
            panel.up = false;
            panel.down = false;
            panel.left = false;
        }
        if ((code == KeyEvent.VK_A )||(code == KeyEvent.VK_LEFT)) {
            panel.left = true;
            panel.right = false;
            panel.up = false;
            panel.down = false;
        }
        if ((code == KeyEvent.VK_W )||(code == KeyEvent.VK_UP)) {
            panel.up = true;
            panel.down = false;
            panel.right = false;
            panel.left = false;
        }
        if ((code == KeyEvent.VK_S )||(code == KeyEvent.VK_DOWN)) {
            panel.down = true;
            panel.up = false;
            panel.right = false;
            panel.left = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

    
}
