/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;
import java.awt.*;
/**
 *
 * @author briansanchez
 */
public class TIE_Fighter extends Ship {
    static Color laserColor = Color.red;
    static int laserHeight = 10;
    static int laserWidth = 2;
    static int laserSpeed = 20;
    TIE_Fighter(int _xpos,int _ypos){
        super(_xpos,_ypos,Ship.Type.TIE);
    }
}
