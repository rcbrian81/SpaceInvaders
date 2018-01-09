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
    }
    
    
    
    
///////////////// Draw Code ////////////////////////////////////////////////////
    public void draw(Graphics2D g,SpaceInvaders main){
        ypos = Window.getY(Window.getHeight2() / 100 * 100);
        
        drawShipImage(g,Window.getX(xpos),Window.getDoubeY(ypos),
        0,.17,.17,main);
        
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial",Font.PLAIN,15));
        g.drawString("" + topPos, xpos-10, (int)ypos+30);
    }
    private void drawShipImage(Graphics2D g,int xpos,double ypos2,
    double rot,double xscale,double yscale,SpaceInvaders main){
        g.translate(xpos,ypos2);
        g.scale( xscale , yscale );
        
        int width = main.MillenniumFalconImage.getWidth(main);
        int height = main.MillenniumFalconImage.getHeight(main);
        
        
        g.drawImage(main.MillenniumFalconImage,-width/2,-height/2,width,height,main);
        
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
