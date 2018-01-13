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
    int height;
    int width;
    
    int rightPos;
    int leftPos;
    int topPos;
    int bottomPos;
    
    int strength;
    int xMoveDis = 5;
    
    boolean hit = false;
    static EnemyWave currentEnemyWave;
    
    
   
    
    enum Type{
        Falcon,
        TIE,
        Slave,
        Death,
    }
    
    Ship(int _xpos,int _ypos,Type _type,int _strength){
        xpos = _xpos;
        ypos = _ypos;
        type = _type;
        strength = _strength;
        updateTestPositions();
        if(xpos < Window.getWidth2()/2)
            xMoveDis = -xMoveDis;
        else
            xMoveDis = xMoveDis;
    }
    Ship(Type _type){
        type = _type;
        updateTestPositions();
    }
    
    public void fireLaser(){
        new Laser(xpos,(int)ypos,type);
        SpaceInvaders.shootSound = new sound("Gunshot.wav");
    }
    ///////////////// Draw Code ////////////////////////////////////////////////////
    public void draw(double xscale, double yscale,Graphics2D g,SpaceInvaders main){
        
        drawShipImage(g,Window.getX(xpos),Window.getY(ypos),
        0,xscale,yscale,main);
    }
    private void drawShipImage(Graphics2D g,int xpos,int ypos,
    double rot,double xscale,double yscale,SpaceInvaders main){
        g.translate(xpos,ypos);
        g.scale( xscale , yscale );
        
        int w = image.getWidth(main);
        int h = image.getHeight(main);
        
        
        g.drawImage(image,-w/2,-h/2,w,h,main);
        
        width = roundDouble(w * xscale);
        height = roundDouble(h * yscale);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.translate(-xpos,-ypos);
    }
    public void updateTestPositions(){
        int width2 = width / 2;
        int height2 = height / 2;
        
        topPos = Window.getY(ypos) - height2;
        bottomPos = Window.getY(ypos) + height2;
        rightPos = Window.getX(xpos) + width2;
        leftPos = Window.getX(xpos) - width2;
        
    }
    
    public static int roundDouble(Double doubleNum){
     double ogNum = doubleNum;   
     boolean jobDone = false;
     int num = 0;
     while(!jobDone){
         if(ogNum <= 1.0){
             if(ogNum < 0.5)
                 num--;
            jobDone = true;
         }
         ogNum -= 1.0;
         num++;
     }
         
     return num;
    } 
    public void hit(){
        strength--;
        if(strength <= 0)
            delete();
    }
    private void delete(){
        Game.currWave.getEnemyAL().remove(this);
    }
    
    
    
}
