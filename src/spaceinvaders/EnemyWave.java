package spaceinvaders;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class EnemyWave {
    static int moveDistance = 15;
    int xdelta;
    int ydelta;
    int minShooters;
    int maxShooters;
    int numShooters;
    int shootFREQ;
    int metiorFREQ;
    static Ship.Type TIE1 = Ship.Type.TIE;
    static Ship.Type Slav = Ship.Type.Slave;
    static Ship.Type Deth = Ship.Type.Death;
    ArrayList<Ship> enemyAL = new ArrayList<Ship>();
    
    public static Ship.Type wave1[][] = {
        {null,TIE1,null,TIE1,null,TIE1,null,null},
        {null,null,TIE1,null,TIE1,null,null,null},
        {null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null}
    };
    public static Ship.Type wave2[][] = {
        {TIE1,Slav,null,TIE1,TIE1,null,Slav,TIE1},
        {TIE1,TIE1,TIE1,null,null,TIE1,TIE1,TIE1},
        {TIE1,null,TIE1,null,null,TIE1,null,TIE1},
        {null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null}
            
    };
    public static Ship.Type wave3[][] = {
        {TIE1,TIE1,TIE1,null,null,TIE1,TIE1,TIE1},
        {TIE1,null,TIE1,null,null,TIE1,null,TIE1},
        {TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1},
        {TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1},
        {TIE1,null,TIE1,null,null,TIE1,null,TIE1},
        {TIE1,TIE1,TIE1,null,null,TIE1,TIE1,TIE1}
            
    };
    
    public static Ship.Type waveFinal[][] = {
        {TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1},
        {TIE1,null,TIE1,null,null,TIE1,null,TIE1},
        {TIE1,null,TIE1,TIE1,TIE1,TIE1,null,TIE1},
        {null,Slav,null,TIE1,TIE1,null,Slav,null},
        {TIE1,null,TIE1,TIE1,TIE1,TIE1,null,TIE1},
        {TIE1,null,TIE1,null,null,TIE1,null,TIE1},
        {TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1}
    };
    public static Ship.Type waveFull[][] = {
        {TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1},
        {TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1},
        {TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1},
        {TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1},
        {TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1},
        {TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1},
        {TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1,TIE1}
    };
    
    
    EnemyWave(Ship.Type wave[][], int _maxShooters, int _shootFREQ, int _metiorFREQ){
        ydelta = (Window.getHeight2() / 2) / (wave.length * 2);
        for(int y=0;y<wave.length;y++){
            for(int x=0;x<wave[y].length;x++){
                xdelta = Window.getWidth2()/ (wave[y].length * 2);
                if(wave[y][x] != null){
                    int ypos = (y  * 2 + 1) * ydelta;
                    int xpos = (x * 2 + 1) * xdelta;
                    
                    switch(wave[y][x]){
                        case TIE : 
                            enemyAL.add(new TIE_Fighter(xpos, ypos));
                            break;
                        case Slave:
                            enemyAL.add(new SlaveOne(xpos, ypos));
                            break;
                    }
                }
            }
        }
        
        maxShooters = _maxShooters;
        shootFREQ = _shootFREQ;
        metiorFREQ = _metiorFREQ;
    }
    public void drawEnemyShips(Graphics2D g, SpaceInvaders main){
        for (Ship enemy : enemyAL) {
            switch(enemy.type){
                case TIE:
                    enemy.draw(0.1,0.1,g, main);
                    break;
                case Slave:
                    enemy.draw(0.03,0.03,g, main);
                    break;
                default :
                    
            }
            
        }
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
            
            enemy.updateTestPositions();
            
            if(enemy.bottomPos > Window.getHeight2())
                Game.gameOver = true;
        }
    }
    public void mustMove(){
        for(Ship enemy : enemyAL){
            if(enemy.type == Ship.Type.Slave){
                enemy.xpos += enemy.xMoveDis;
                if(enemy.xpos < 0 || enemy.xpos > Window.getWidth2())
                    enemy.xMoveDis = -enemy.xMoveDis;
            }
            enemy.updateTestPositions();
        }
    }
    
    public void haveEnemyShoot(){
        if(ranNum(0,3) == 2){
            minShooters++;
        }
        if(minShooters > enemyAL.size()){
            maxShooters = enemyAL.size();
            minShooters = maxShooters;
        }
        if(minShooters > maxShooters){
            
        }minShooters = maxShooters;
        
        for(int i =0;i<minShooters;i++){
            int shooter = (int)(Math.random() * enemyAL.size());
            enemyAL.get(shooter).fireLaser();
        }
        
    }
    private  int ranNum(int minNum, int maxNum){
        int possNums = maxNum - minNum;
        return (int)(Math.random() * possNums + minNum);
    }
    
    public int getNumLives(){
        int totalLives = 0;
        for(Ship ship : enemyAL){
            totalLives += ship.strength;
        }
        return totalLives;
    }
}
