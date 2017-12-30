package spaceinvaders;

import java.io.*; 
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class SpaceInvaders extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    
    Image backgroundImage;
    Image MillenniumFalconImage;
    Image TIEFighterImage;
    Falcon player = Falcon.player;
    
    double frameRate = 100.0;
    int timeCount;
    
    boolean inGame = false;

    
    
    static SpaceInvaders frame;
    public static void main(String[] args) {
        frame = new SpaceInvaders();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public SpaceInvaders() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) { 
                    System.out.println("regular: " + e.getX() + "," + e.getY());
                    System.out.println("window: " + Window.getX(e.getX()) + "," + Window.getY(e.getY()));
                    System.out.println("noraml: " + Window.getX(e.getX()) + "," + Window.getYNormal(e.getY()));
                    System.out.println("height: " + Window.getHeight2());
                    System.out.println("width2: " + Window.getWidth2());
                    System.out.println("width2: " + Window.getY(0));
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
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_UP == e.getKeyCode()) {
                } else if (e.VK_DOWN == e.getKeyCode()) {
                } else if (e.VK_LEFT == e.getKeyCode()) {
                    player.moveXPosBy(-25);
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                    player.moveXPosBy(25);
                }else if (e.VK_SPACE == e.getKeyCode()) {
                    player.fireLaser();
                }else if (e.VK_ENTER == e.getKeyCode()) {
                    inGame = true;
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
        //Draws Lasers
        Laser.drawAllLasers(g);
        //Draws Playerd
        player.draw(g,this);
        
        g.setColor(Color.GREEN);
        g.drawRect(Window.getX(0),Window.getYNormal(0),Window.getWidth2(),Window.getHeight2());
        
        Alien.drawAllAliens(g, this);
        
        gOld.drawImage(image, 0, 0, null);
    }

////////////////////////////////////////////////////////////////////////////
    public void drawAlien(int xpos,int ypos,double rot,
    double xscale,double yscale,int value)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        int xval[] = {10,-10,-10,-5,-10,0,10,5,10};
        int yval[] = {-20,-20,-5,-5,10,0,10,-5,-5};
        g.fillPolygon(xval,yval,xval.length);             
        
        g.setColor(Color.black);
        g.setFont(new Font("Arial",Font.PLAIN,16));
        g.drawString("" + value, 0, 0);        
                
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
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
            
            backgroundImage = Toolkit.getDefaultToolkit().getImage("./images/background.jpg");
            MillenniumFalconImage = Toolkit.getDefaultToolkit().getImage("./images/MillenniumFalcon.png");
            TIEFighterImage = Toolkit.getDefaultToolkit().getImage("./images/TIE_Fighter.png");
            Falcon.initPlayer();
            player = Falcon.player; 
        }
        if (timeCount % (int)(frameRate*1) == (int)(frameRate*1-1))
            Alien.moveAliens();
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
