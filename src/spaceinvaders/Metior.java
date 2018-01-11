package spaceinvaders;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Metior {
    static ArrayList<Metior> metiors = new ArrayList<Metior>();
    Image image;
    static int speed = -20;
    
    private int xpos;
    private int ypos;
    private int width;
    private int height;
    
    private int topPos;
    private int bottomPos;
    private int rightPos;
    private int leftPos;
    private int width2;
    private int height2;
    
    static double xscale = 0.2;
    static double yscale = 0.2;
    
    Metior(SpaceInvaders main){
        ypos = 0;
        xpos = (int)(Math.random() * Window.getWidth2());
        image = SpaceInvaders.MetiorImage;
        metiors.add(this);
        //System.out.println(xpos + "," + ypos);
        
        
        
        width2 =  width /2;
        height2 =  height/2;
        
        
        
        
    }
    
    
    public static void Draw(Graphics2D g,boolean inGame,SpaceInvaders main){
        for (Iterator<Metior> metiorIter = metiors.iterator(); metiorIter.hasNext(); ) {
            Metior metior = metiorIter.next();

            if(inGame)
                metior.move();
            metior.drawShipImage(g, metior.xpos, metior.ypos, 0.0, main);
            if(metior.topPos >  Window.getHeight2())
                metiorIter.remove();
        }
    }
    public void move(){
        ypos -= speed;
        updateTestPositions();
    }
        
    private void drawShipImage(Graphics2D g,int xpos,int ypos,
    double rot,SpaceInvaders main){
        g.translate(xpos,ypos);
        g.scale( xscale , yscale );
        
        int w= image.getWidth(main);
        int h = image.getHeight(main);
        
        
        g.drawImage(image,-w/2,-h/2,w,h,main);
        
//        if((int)(Math.random() * 2) == 0)
//            g.fillRect(-width/4 ,0,width2 * 5,height2 * 5);
//          g.fillRect(-width/4 ,0,width/2,height/2);
//           g.drawRect(-width/2 ,-height/2,width,height);

        width = (int)Math.round(image.getWidth(main) * xscale);
        height = (int)Math.round(image.getHeight(main) * yscale);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.translate(-xpos,-ypos);
    }
    
    private void updateTestPositions(){
        topPos = ypos;
        bottomPos = ypos + height/2;
        leftPos = xpos - width/4;
        rightPos = xpos + width/4;
        System.out.println();
        //System.out.println(topPos);
       // System.out.println(bottomPos);
        System.out.println(rightPos);
        System.out.println(leftPos);
    }
    
    public static void checkForCollision(){
        Falcon p = Game.player;
        for(Metior metior : metiors){
            if(metior.bottomPos >= p.topPos){
                if(xHitCheck(p.leftPos,metior.rightPos,p.rightPos) || 
                xHitCheck(p.leftPos,metior.xpos,p.rightPos) ||
                xHitCheck(p.leftPos,metior.leftPos,p.rightPos))
                    Game.playerHit();
            }
        }
    }
    
    private static boolean xHitCheck(int num, int num2, int num3){
        if(num < num2 && num2 < num3)
            return true;
        return false;
    }
    
    public static void clear(){
        metiors.clear();
    }
    
}
