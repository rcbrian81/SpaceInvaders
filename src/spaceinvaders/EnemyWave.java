/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author briansanchez
 */
public class EnemyWave {
    static int moveDistance = 15;
    int xdelta;
    int ydelta;
    int maxlength;
    int numShooters = 0;
    static Ship.Type TIE = Ship.Type.TIE;
    ArrayList<Ship> enemyAL = new ArrayList<Ship>();
    
    public static Ship.Type wave1[][] = {
        {TIE,TIE,TIE},
        {TIE,null,TIE},
        {TIE,TIE,TIE}
    };
    public static Ship.Type wave2[][] = {
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE}
    };
    public static Ship.Type wave3[][] = {
        {TIE,TIE,null,TIE,TIE,null,TIE,TIE},
        {TIE,TIE,null,TIE,TIE,null,TIE,TIE},
        {TIE,TIE,null,TIE,TIE,null,TIE,TIE},
        {TIE,TIE,null,TIE,TIE,null,TIE,TIE},
        {TIE,TIE,null,TIE,TIE,null,TIE,TIE},
        {TIE,TIE,null,TIE,TIE,null,TIE,TIE},
        {TIE,TIE,null,TIE,TIE,null,TIE,TIE}
    };
    public static Ship.Type wave4[][] = {
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,null,TIE,TIE,TIE,TIE,null,TIE},
        {TIE,TIE,TIE,null,null,TIE,TIE,TIE},
        {TIE,TIE,TIE,null,null,TIE,TIE,TIE},
        {TIE,null,TIE,TIE,TIE,TIE,null,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE}
    };
    public static Ship.Type wave5[][] = {
        {TIE,TIE,TIE,null,null,TIE,TIE,TIE},
        {TIE,null,TIE,null,null,TIE,null,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,null,TIE,null,null,TIE,null,TIE},
        {TIE,TIE,TIE,null,null,TIE,TIE,TIE}
            
    };
    public static Ship.Type wave6[][] = {
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,null,TIE,null,null,TIE,null,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {null,null,null,TIE,TIE,null,null,null},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,null,TIE,null,null,TIE,null,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE}
    };
// 1. Delay every laser shot by one second/ Implement a one second reload time for player
// 2. Creat a Menu class
// 3. Create a Game class that the menu will use to start a new game
//  a  Create a Round class to be used ny game Class
// 4. Implement Lives
// 5.Implement a point system
// **Text Jackson and Noah to design maps and possibley do the collide code
// or just make the collide code yourSelf and hace them implement the metor
// and other power ups like lives are floating power ups to to shoot or crash into
    
//    {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
//        {TIE,TIE,TIE,TIE,TIE,TIE,},
//        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
//        {TIE,TIE,TIE,TIE},
//        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
//        {TIE,TIE,TIE,TIE,TIE,TIE},
//        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE}
    EnemyWave(Ship.Type wave[][]){
        ydelta = (Window.getHeight2() / 2) / (wave.length * 2);
        for(int y=0;y<wave.length;y++){
            for(int x=0;x<wave[y].length;x++){
                xdelta = Window.getWidth2()/ (wave[y].length * 2);
                if(wave[y][x] != null){
                    switch(wave[y][x]){
                        case TIE : 
                            int ypos = (y  * 2 + 1) * ydelta;
                            int xpos = (x * 2 + 1) * xdelta;
                            
                            enemyAL.add(new TIE_Fighter(xpos, ypos));
                    }
                }
            }
        }
    }
    // Window.getX(0)+column*Window.getWidth2()/Board.numColumns
    public void drawEnemyShips(Graphics2D g, SpaceInvaders main){
        for(Ship enemy : enemyAL){
            enemy.draw(g, main);
        }
    }
    
    public void haveEnemyShoot(){
        if(enemyAL.size() < numShooters){
            numShooters = enemyAL.size();
        }
        
        int numToPick = numShooters;
        ArrayList<Integer> shooters = new ArrayList<Integer>();
        
        while(numToPick > 0){
            int num = (int)(Math.random() * enemyAL.size());
            boolean numGood = true;
            for(int x : shooters){
                if(num == x)
                    numGood = false;
            }
            
            if(numGood){
                shooters.add(num);
                numToPick--;
            }
        }
        
        if(numToPick <= 0){
            for(int shooter : shooters){
                enemyAL.get(shooter).fireLaser();
            }
        }
        
        numShooters++;
    }
    
    public ArrayList getEnemyAL(){
        return enemyAL;
    }
    public void move(){
        int move = (int)(Math.random() * 4);
        for(Ship enemy : enemyAL){
            
            if(move > 2)
                enemy.ypos -= moveDistance;
            else
                enemy.ypos += moveDistance;
        }
    }
}
