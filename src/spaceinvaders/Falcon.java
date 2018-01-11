/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author briansanchez
 */
public class Falcon extends Ship{
    
    static Color laserColor  =Color.green;
    static final int laserWidth = 2;
    static final int laserHeight = 15; 
    static final int laserSpeed = -10;
    
    
    Falcon(){
        super(Ship.Type.Falcon);
        xpos = Window.getWidth2()/2;
        image = SpaceInvaders.MillenniumFalconImage;
    }
    
    
    public void moveXPosBy(int d){
        xpos += d;
        updateTestPositions();
    }
    public void updateXPos(int _xpos){
        xpos = _xpos;
    }
    public void resetPos(){
        xpos = Window.getWidth2()/2;
    }
    
    
    
    
///////////////// Draw Code ////////////////////////////////////////////////////
    public void draw(Graphics2D g,SpaceInvaders main){
        
        ypos = Window.getY(Window.getHeight2() / 100 * 100);
        
        drawShipImage(g,Window.getX(xpos),Window.getDoubeY(ypos),
        0,.17,.17,main);
    
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial",Font.PLAIN,15));
        g.drawString("" + topPos, xpos-10, ypos-45);
        g.drawString("" + leftPos, xpos-55, ypos+10);
        g.drawString("" + rightPos, xpos+55, ypos+10);
        
         g.drawString("" + xpos + "," + ypos, 700, 700);
    }
    private void drawShipImage(Graphics2D g,int xpos,double ypos2,
    double rot,double xscale,double yscale,SpaceInvaders main){
        g.translate(xpos,ypos2);
        g.scale( xscale , yscale );
        
        int w = main.MillenniumFalconImage.getWidth(main);
        int h = main.MillenniumFalconImage.getHeight(main);
        
        
        g.drawImage(main.MillenniumFalconImage,-w/2,-h/2,w,h,main);
        g.setColor(Color.PINK);
        g.drawRect(-w/2,-h/2,w,h);
        
        width = roundDouble(w * xscale);
        height = roundDouble(h * yscale);
                
        g.scale( 1.0/xscale,1.0/yscale );
        g.translate(-xpos,-ypos2);
    }
    
///////////////// Accessors ////////////////////////////////////////////////////
    public int getXPos(){
        return xpos;
    }
    public int getYPos(){
        return (int)ypos;
    }
}
