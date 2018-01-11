/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

/**
 *
 * @author megam
 */
public class DeathStar extends Ship{
    
    DeathStar(int xpos,int ypos){
        super(xpos,ypos,Ship.Type.Death,15);
    }
    
}
