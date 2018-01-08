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
    private static ArrayList<Laser> lasers = new ArrayList<Laser>();
    
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
        
        lasers.add(this);
    }
    
    public static void drawAllLasers(Graphics2D g,boolean inGame){
        for(Laser laser : lasers){
            if(inGame)
                laser.ypos += laser.velocity;
            laser.drawLaser(g, laser.xpos, laser.ypos, 0.0, 1.0,1.0);
        }
        
        for (Iterator<Laser> iterator = lasers.iterator(); iterator.hasNext(); ) {
            Laser laser = iterator.next();
            if (laser.ypos > Window.getY(Window.getHeight2()) || laser.ypos < Window.getY(0)) {
                iterator.remove();
            }
        }
        
    }
    
    public void drawLaser(Graphics2D g,int xpos,int ypos,double rot,double xscale,double yscale){
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        g.setColor(color);
        g.fillRect(-width/2,-height/2,width,height);
        g.setColor(Color.yellow);
        g.drawRect(0,0,width/2,height/2);
        
        
        
        g.setColor(Color.yellow);
        g.setFont(new Font("Arial",Font.PLAIN,15));
        g.drawString(" " + xpos + "," + ypos, width, height);
        
        
        

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    
    public static void checkForHit(EnemyWave currWave){
            ArrayList<Ship> ships = currWave.getEnemyAL();
            
            
            for (Iterator<Laser> laserIter = lasers.iterator(); laserIter.hasNext(); ) {
                Laser laser = laserIter.next();
                
                if(laser.type == Ship.Type.Falcon){
                    for(Iterator<Ship> shipIter = ships.iterator(); shipIter.hasNext();){
                        Ship ship = shipIter.next();

                        boolean shipHit = compareLaserToShip(laser,ship);

                        if(shipHit){
                            Game.numEnemyShips--;
                            shipIter.remove();
                            laserIter.remove();
                        }

                    }
                }
                else if(laser.type != Falcon.Type.Falcon){
                    boolean shipHit = compareLaserToShip(laser,Game.player);
                    
                    if(shipHit)
                        Game.playerHit();
                }
            }
        
    }
    
    private static boolean compareLaserToShip(Laser laser,Ship ship){
        int shipTop = ship.topPos;
        int shipBottom = ship.bottomPos;
        int shipRight = ship.rightPos;
        int shipLeft = ship.leftPos;
        int xpos = laser.xpos;
        int ypos = laser.ypos;
        
        if(shipLeft < xpos && xpos < shipRight)
            if(shipTop < ypos && ypos< shipBottom)
            return true;
        
        return false;
    }
    public static void clearLasers(){
        lasers.clear();
    }
    
      
    
}
