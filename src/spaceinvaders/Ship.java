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
public class Ship {
    Image image;
    Type type;
    
    int xpos;
    int ypos;
    
    
    
    
    enum Type{
        Falcon,TIE,Tank,Mother,
    }
    
    Ship(int _xpos,int _ypos,Type _type){
        xpos = _xpos;
        ypos = _ypos;
        type = _type;
    }
    Ship(Type _type){
        type = _type;
    }
    
    public void fireLaser(){
        new Laser(xpos,(int)ypos,type);
    }
    ///////////////// Draw Code ////////////////////////////////////////////////////
    public  void draw(Graphics2D g,SpaceInvaders main){
        
        drawShipImage(g,Window.getX(xpos),Window.getY(ypos),
        0,.1,.1,main);
        
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
