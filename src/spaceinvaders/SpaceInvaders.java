package spaceinvaders;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.*;
import java.io.*; 
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class SpaceInvaders extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    
    static Image backgroundImage;
    static Image MillenniumFalconImage;
    static Image TIEFighterImage;
    static Image SlaveOneImage;
    static Image MetiorImage;
    static Image ExplosionImage;
    Falcon player;
    
    sound shootSound = null;
    sound themeSound = null;

    
    double frameRate = 40.0;
    int timeCount;

    
    
    static SpaceInvaders frame;
    public static void main(String[] args) {
        frame = new SpaceInvaders();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public SpaceInvaders() {
        if(timeCount == 12000)
           themeSound = new sound("theme.wav");

        themeSound = new sound("theme.wav");
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) { 
                    if(Menu.isOpen())
                        Menu.checkForPressedButton(e.getX(),e.getY());
                    else
                        System.out.println("l: " + e.getX() + "," + e.getY());
                }
                if (e.BUTTON3 == e.getButton()) {
                    
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
         // Game.player.updateXPos(e.getX());
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_UP == e.getKeyCode()) {
                } else if (e.VK_ESCAPE == e.getKeyCode()) {
                    reset();
                }else if (e.VK_DOWN == e.getKeyCode()) {
                } else if (e.VK_LEFT == e.getKeyCode()) {
                    Game.leftButtonPressed();
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                    Game.rightButtonPressed();
                }else if (e.VK_SPACE == e.getKeyCode()) {
                    shootSound = new sound("Gunshot.wav");
                    Game.spaceButtonpressed();
                }else if (e.VK_ENTER == e.getKeyCode()) {
                    Game.enterButtonPressed();

                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }



////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width ||Window. ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
 
        g.setColor(Color.yellow);
        g.fillPolygon(x, y, 4);

        g.setColor(Color.GREEN);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }
        //Draws Background
        g.drawImage(backgroundImage, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), this);
        
        
        Game.Draw(g, this);
        
        gOld.drawImage(image, 0, 0, null);
    }
////////////////////////////////////////////////////////////////////////////

    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 1.0 / frameRate;    
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {

        
            backgroundImage = Toolkit.getDefaultToolkit().getImage("./images/background.jpg");
            MillenniumFalconImage = Toolkit.getDefaultToolkit().getImage("./images/MillenniumFalcon.png");
            TIEFighterImage = Toolkit.getDefaultToolkit().getImage("./images/TIE_Fighter.png");
            SlaveOneImage = Toolkit.getDefaultToolkit().getImage("./images/SlaveOne.png");
            MetiorImage = Toolkit.getDefaultToolkit().getImage("./images/three.png");
            ExplosionImage = Toolkit.getDefaultToolkit().getImage("./images/three.png");
            Game.initNewGame();
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
         
            reset();
            
        }
        
        Game.Update(this);
        
        timeCount++;
        
        
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
}
