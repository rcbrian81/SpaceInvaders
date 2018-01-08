package spaceinvaders;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class EnemyWave {
    static int moveDistance = 15;
    int xdelta;
    int ydelta;
    int minShooters;
    int maxShooters = 5;
    int numShooters;
    static Ship.Type TIE = Ship.Type.TIE;
    ArrayList<Ship> enemyAL = new ArrayList<Ship>();
    
    public static Ship.Type wave1[][] = {
        {null,TIE,null,TIE,null,TIE,null,null},
        {null,null,TIE,null,TIE,null,null,null},
        {null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null},
        {null,null,null,null,null,null,null,null}
    };
    public static Ship.Type wave2[][] = {
        {TIE,TIE,TIE,null,null,TIE,TIE,TIE},
        {TIE,null,TIE,null,null,TIE,null,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,null,TIE,null,null,TIE,null,TIE},
        {TIE,TIE,TIE,null,null,TIE,TIE,TIE}
            
    };
    public static Ship.Type wave3[][] = {
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,null,TIE,null,null,TIE,null,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {null,null,null,TIE,TIE,null,null,null},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,null,TIE,null,null,TIE,null,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE}
    };
    public static Ship.Type waveFinal[][] = {
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE,TIE}
    };
    
    
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
    public void drawEnemyShips(Graphics2D g, SpaceInvaders main){
        for(Ship enemy : enemyAL){
            enemy.draw(g, main);
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
        
        for(int i =0;i<minShooters;i++){
            int shooter = (int)(Math.random() * enemyAL.size());
            enemyAL.get(shooter).fireLaser();
        }
        
    }
    private  int ranNum(int minNum, int maxNum){
        int possNums = maxNum - minNum;
        return (int)(Math.random() * possNums + minNum);
    }
}
