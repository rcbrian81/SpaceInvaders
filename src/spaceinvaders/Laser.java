/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Color;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author briansanchez
 */
public class Laser {
    Ship.Type type;
    Color color;
    final int xpos;
    int ypos;
    int width;
    int height;
    int direction;
    int velocity;
    
    private static ArrayList<Laser> allLasers = new ArrayList<Laser>();
    
    Laser(int _xpos, int _ypos,Ship.Type _type){
        type = _type;
        xpos = _xpos;
        ypos = _ypos;
        type = _type;
        
        switch(type){
            case Falcon:
                width = Falcon.laserWidth;
                height = Falcon.laserHeight;
                velocity = Falcon.laserSpeed;
                color = Falcon.laserColor;
                break;
            case TIE:
                width = TIE_Fighter.laserWidth;
                height = TIE_Fighter.laserHeight;
                velocity = TIE_Fighter.laserSpeed;
                color = TIE_Fighter.laserColor;
                
        }
        
        allLasers.add(this);
    }
    
    public static void drawAllLasers(Graphics2D g){
        int num = 0;
        for(Laser laser : allLasers){
            laser.drawLaser(g, laser.xpos, laser.ypos+=laser.velocity, 0.0, 1.0,1.0);
            
          //  System.out.println("ypos: " + laser.ypos);
            
            System.out.println(num);
            num++;
        }
        
        for (Iterator<Laser> iterator = allLasers.iterator(); iterator.hasNext(); ) {
        Laser laser = iterator.next();
        if (laser.ypos > Window.getY(Window.getHeight2()) || laser.ypos < Window.getY(0)) {
            iterator.remove();
        }
}
        
    }
    
    public void delete(){
        allLasers.remove(this);
    }
    
    public void drawLaser(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale){
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        g.setColor(color);
        g.fillRect(-width/2,-height/2,width,height);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    
    public void checkForHit(){
        
    }
    
}
