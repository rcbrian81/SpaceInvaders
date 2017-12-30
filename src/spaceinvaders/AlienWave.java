/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

/**
 *
 * @author briansanchez
 */
public class AlienWave {
    int xdelta;
    int ydelta;
    int maxlength;
    static Alien.Type TIE = Alien.Type.TIE;
    public static Alien.Type wave1[][] = {
        {TIE,TIE,TIE},
        {TIE,null,TIE},
        {TIE,TIE,TIE}
    };
    public static Alien.Type wave2[][] = {
        {TIE,null,TIE},
        {null,null,null},
        {TIE,null,TIE}
    };
    public static Alien.Type wave3[][] = {
        {TIE,TIE,TIE},
        {TIE,TIE,TIE},
        {TIE,TIE,TIE}
    };
    public static Alien.Type wave4[][] = {
        {TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE}
    };
    public static Alien.Type wave5[][] = {
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE},
        {TIE,TIE,TIE,TIE,TIE,TIE,TIE}
    };
    
    
    AlienWave(Alien.Type wave[][]){
        System.out.println(wave.length);
        System.out.println(wave[0].length);
        
        ydelta = (Window.getHeight2() / 2) / (wave.length * 2);
        for(int y=0;y<wave.length;y++){
            for(int x=0;x<wave[y].length;x++){
                xdelta = Window.getWidth2()/ (wave[y].length * 2);
                if(wave[y][x] != null){
                    switch(wave[y][x]){
                        case TIE : 
                            System.out.println(y + " , "+ x);
                            int ypos = (y  * 2 + 1) * ydelta;
                            int xpos = (x * 2 + 1) * xdelta;
                            
                            new TIE_Fighter(xpos, ypos);
                    }
                }
            }
        }
    }
    // Window.getX(0)+column*Window.getWidth2()/Board.numColumns
    public static void createAlien(){
        
    }
}
