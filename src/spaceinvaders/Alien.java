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
public abstract class Alien {
    static ArrayList<Alien> aliens = new ArrayList<Alien>();
    Image image;
    int xpos;
    int ypos;
    int width;
    int height;
    
    static int moveDistance = 15;
    
    Color color;
    int laseHeight;
    int laserWidth;
    int laserSpeed;
    
    enum Type{
        TIE,Tank,Mother,
    }
    
    Alien(int _xpos,int _ypos){
        xpos = _xpos;
        ypos = _ypos;
        aliens.add(this);
    }
    
    public static void moveAliens(){
        //int move = (int)(Math.random() * 4);
        for(Alien alien : aliens){
            int move = (int)(Math.random() * 4);
            
            if(move > 2)
                alien.ypos -= moveDistance;
            else
                alien.ypos += moveDistance;
        }
    }
    ///////////////// Draw Code ////////////////////////////////////////////////////
    public static void drawAllAliens(Graphics2D g,SpaceInvaders main){
        
        for(Alien alien : aliens){
        
        drawShipImage(g,Window.getX(alien.xpos),Window.getY(alien.ypos),
        0,.1,.1,main);
        }
        
    }
    private static void drawShipImage(Graphics2D g,int xpos,int ypos,
    double rot,double xscale,double yscale,SpaceInvaders main){
        g.translate(xpos,ypos);
        g.scale( xscale , yscale );
        
        int width = main.TIEFighterImage.getWidth(main);
        int height = main.TIEFighterImage.getHeight(main);
        
       // g.fillRect(-width/2,-height/2,width,height);
        g.drawImage(main.TIEFighterImage,-width/2,-height/2,width,height,main);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.translate(-xpos,-ypos);
    }
    
    
}
