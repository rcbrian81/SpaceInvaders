package spaceinvaders;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

public class Explosion {
    static ArrayList<Explosion> explosions = new ArrayList<Explosion>();
    static double lifeTime = 0.1;
    int xpos;
    int ypos;
    boolean erase;
    double scaleX;
    double scaleY;
    boolean active;
    double timeOn;
    
    Image image;
    
    Explosion(int _x, int _y){
        xpos = _x;
        ypos = _y;
        erase = false;
        scaleX = 1;
        scaleY = 1;
        explosions.add(this);
        active = true;
    }
    public void setErase(boolean e){
        erase = e;
    }
    public boolean getErase(){
        return erase;
    }
    public static void update(){
//        for (Iterator<Laser> laserIter = lasers.iterator(); laserIter.hasNext(); ) {
//                Laser laser = laserIter.next();
        for(Iterator<Explosion> iterator = explosions.iterator(); iterator.hasNext();){
                Explosion ex = iterator.next();
                
                if(ex.timeOn == SpaceInvaders.frameRate * lifeTime)
                    iterator.remove();
                ex.timeOn++;
        }
    }
    public static void Draw(Graphics2D g, SpaceInvaders main){
        for(Explosion ex : explosions){
            ex.drawExplosion(g,Window.getX(ex.xpos),Window.getY(ex.ypos), 0,0.2,0.2,main);
        }
    }
    private void drawExplosion(Graphics2D g,int xpos,int ypos,
    double rot,double xscale,double yscale,SpaceInvaders main)
    {
        g.translate(xpos,ypos);
        g.scale( xscale , yscale );
         
         int width = main.ExplosionImage.getWidth(main);
        int height = main.ExplosionImage.getHeight(main);
        
        g.drawImage(main.ExplosionImage,-width/2,-height/2,width,height,main);        
     
        g.scale( 1.0/xscale,1.0/yscale );
        g.translate(-xpos,-ypos);
    }
}

