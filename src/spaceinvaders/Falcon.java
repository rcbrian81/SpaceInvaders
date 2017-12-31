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
public class Falcon {
    Image shipImage;
    private int xpos;
    private double ypos;
    
    static Falcon player;
    
    static Color laserColor  =Color.green;
    static final int laserWidth = 2;
    static final int laserHieght = 15; 
    static final int laserSpeed = +20;
    
    public static void initPlayer(){
        player = new Falcon();
    }
    Falcon(){
        xpos = Window.getWidth2()/2;
        System.out.println(xpos+ "," + ypos);
    }
    
    public void fireLaser(){
        new Laser(xpos,(int)ypos,Laser.Type.falcon);
    }
    public void moveXPosBy(int d){
        xpos += d;
    }
    
    
    
    
///////////////// Draw Code ////////////////////////////////////////////////////
    public void draw(Graphics2D g,SpaceInvaders main){
        ypos = Window.getY(Window.getHeight2() / 100 * 100);
        
        drawShipImage(g,Window.getX(xpos),Window.getDoubeY(ypos),
        0,.17,.17,main);
    }
    private void drawShipImage(Graphics2D g,int xpos,double ypos,
    double rot,double xscale,double yscale,SpaceInvaders main){
        g.translate(xpos,ypos);
        g.scale( xscale , yscale );
        
        int width = main.MillenniumFalconImage.getWidth(main);
        int height = main.MillenniumFalconImage.getHeight(main);
        
        
        g.drawImage(main.MillenniumFalconImage,-width/2,-height/2,width,height,main);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.translate(-xpos,-ypos);
    }
    
///////////////// Accessors ////////////////////////////////////////////////////
    public int getXPos(){
        return xpos;
    }
    public int getYPos(){
        return (int)ypos;
    }
}
