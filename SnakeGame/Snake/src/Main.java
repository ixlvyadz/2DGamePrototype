
import javax.swing.JFrame;


public class Main extends JFrame{
    public static void main(String[] args){

        JFrame frame = new JFrame();
        
        frame.setTitle("Rex The Great");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
