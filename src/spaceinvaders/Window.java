/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

/**
 *
 * @author 373001308
 */
public class Window {
//    private static final int YBORDER = 20;
    
    
    static final int WINDOW_WIDTH = + 800;
    static final int WINDOW_HEIGHT = 800;
    static int xsize = -1;
    static int ysize = -1;


/////////////////////////////////////////////////////////////////////////
    public static int getX(int x) {
        return (x);
    }

    public static int getY(int y) {
//        return (y + YBORDER + YTITLE );
        return (y+22);
        
    }
    
    public static double getDoubleX(double x) {
        return (x);
    }

    public static double getDoubeY(double y) {
//        return (y + YBORDER + YTITLE );
        return (y);
        
    }

    public static int getYNormal(int y) {
//          return (-y + YBORDER + YTITLE + getHeight2());
      return (-y + getHeight2());
        
    }
//regular: 526,118
//window: 526,118
//noraml: 526,655
//height: 773
//width2: 800
    public static int getWidth2() {
        return (xsize);
    }

    public static int getHeight2() {
//        return (ysize - 2 * YBORDER - WINDOW_BORDER - YTITLE);
        return (ysize);
    }    
    
}

