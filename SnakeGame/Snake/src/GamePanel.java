
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener {
   
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile

    public final int maxScreenCol = 16; //16 tiles across
    public final int maxScreenRow = 12; //12 tiles down\
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels
    public final int gameUnits = (screenWidth*screenHeight)/(tileSize*tileSize);

    public int[] snakeXLength = new int[gameUnits];
    public int[] snakeYLength = new int[gameUnits];

    //Snake initial direction
    public boolean right = true;
    public boolean left = false;
    public boolean up = false;
    public boolean down = false;

    //snake image
    public ImageIcon snakeUp;
    public ImageIcon snakeDown;
    public ImageIcon snakeLeft;
    public ImageIcon snakeRight;
    public ImageIcon snakeImage;
    
    //people settings
    Random random;
    public ImageIcon peopleIcon;
    public int xPeople;
    public int yPeople;

    public Timer timer;
    public int delay = 175;
    public int snakeLength = 4;
    public boolean running = false;

    public Image backgroundImage;
    public ImageIcon titleImage;
    KeyHandler keyH = new KeyHandler(this);
    public CheckCollision checkCollision = new CheckCollision(this);

    public GamePanel(){
        backgroundImage = new ImageIcon("SnakeGame\\Resources\\battle-background-sunny-hillsx1.png").getImage();
        peopleIcon = new ImageIcon("SnakeGame\\Resources\\sprites\\body.png");
        random = new Random();
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(true);

        xPeople = random.nextInt(maxScreenCol) * tileSize;
        yPeople = random.nextInt(maxScreenRow) * tileSize;

        timer = new Timer(delay, this);
        timer.start();
        newPeople();
        running = true;
    }

    public void newPeople(){
         boolean onSnake;
    do {
        onSnake = false;
        xPeople = random.nextInt(maxScreenCol) * tileSize;
        yPeople = random.nextInt(maxScreenRow) * tileSize;
        
        // Check if new food is on the snake
        for (int i = 0; i < snakeLength; i++) {
            if (snakeXLength[i] == xPeople && snakeYLength[i] == yPeople) {
                onSnake = true;
                break;
            }
        }
    } while (onSnake);
    }

    public void gameWin(Graphics g){
        if(snakeLength >= 10){
            g.setColor(Color.cyan);
            g.setFont(new Font("Courier New", Font.BOLD, 50));
            FontMetrics metrics2 = getFontMetrics(g.getFont());
            g.drawString("You won the quest!", (screenWidth - metrics2.stringWidth("You won the quest!"))/2, screenHeight/2);
            running = false;
            timer.stop();
        }
    }

    public void move(){

        for (int i = snakeLength - 1; i > 0; i--) {
            snakeXLength[i] = snakeXLength[i - 1];
            snakeYLength[i] = snakeYLength[i - 1];
        }

        if (right) {
        snakeXLength[0] += tileSize;
        } else if (left) {
            snakeXLength[0] -= tileSize;
        } else if (up) {
            snakeYLength[0] -= tileSize;
        } else if (down) {
            snakeYLength[0] += tileSize;
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
         if (backgroundImage != null) {
        g.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, this);
    }
        if(running){
        /* 
        //Border of Title Image
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);

        titleImage = new ImageIcon("SnakeGame\\Resources\\HUD.png");
        titleImage.paintIcon(this, g, 25, 11);
        //border of Game Panel
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);
        */
        
        //snake images

        //snake initial direction on frame
        for(int i = 0; i < snakeLength; i++){
          if(i == 0 && right){
            snakeRight = new ImageIcon("SnakeGame\\Resources\\sprites\\head_right.png");
            Image rightImage = snakeRight.getImage();
            g.drawImage(rightImage, snakeXLength[i], snakeYLength[i], tileSize, tileSize, this);
          }  
          if(i == 0 && left){
            snakeLeft = new ImageIcon("SnakeGame\\Resources\\sprites\\head_left.png");
            Image leftImage = snakeLeft.getImage();
            g.drawImage(leftImage, snakeXLength[i], snakeYLength[i], tileSize, tileSize, this);
          }  
          if(i == 0 && up){
            snakeUp = new ImageIcon("SnakeGame\\Resources\\sprites\\head_up.png");
            Image upImage = snakeUp.getImage();
            g.drawImage(upImage, snakeXLength[i], snakeYLength[i], tileSize, tileSize, this);
          }  
          if(i == 0 && down){
            snakeDown = new ImageIcon("SnakeGame\\Resources\\sprites\\head_down.png");
            Image downImage = snakeDown.getImage();
            g.drawImage(downImage, snakeXLength[i], snakeYLength[i], tileSize, tileSize, this);
          }  

          if(i != 0){
            snakeImage = new ImageIcon("SnakeGame\\Resources\\sprites\\body.png");
            Image snakeImageF = snakeImage.getImage();
            g.drawImage(snakeImageF, snakeXLength[i], snakeYLength[i], tileSize, tileSize, this);
          }
        }

        Image peopleImage = peopleIcon.getImage();
            g.drawImage(peopleImage, xPeople, yPeople, tileSize, tileSize, this);


        if((xPeople == snakeXLength[0]) && (yPeople == snakeYLength[0])){
            snakeLength++;
            newPeople();
        }
        gameWin(g);
        }else{
            gameOver(g);
        }
    }

    public void gameOver(Graphics g){
       g.setColor(Color.red);
       g.setFont(new Font("Courier New", Font.BOLD, 50));
       FontMetrics metrics2 = getFontMetrics(g.getFont());
       g.drawString("Game Over", (screenWidth - metrics2.stringWidth("Game Over"))/2, screenHeight/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkCollision.checkBorder();

            if (snakeLength >= 10) {
            running = false;
            timer.stop();
        }
        }
        repaint();

    }
}


