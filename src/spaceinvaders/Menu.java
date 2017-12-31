/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author briansanchez
 */
public class Menu {
    private static int xpos;
    private static int ypos;
    private static int height;
    private static int width;
    private static boolean inMenu;
    
    private static int buttonXPos;
    private static int buttonYPos;
    private static int buttonWidth;
    private static int buttonHeight;
    static int num;
    
    public static void drawMain(Graphics2D g){
        
        xpos = Window.getWidth2() / 4;
        ypos = Window.getHeight2() / 4;
        height = Window.getHeight2() / 2;
        width = Window.getWidth2() /2;
        
        g.setColor(Color.BLACK);
        g.fillRect(xpos, ypos, width, height);
        g.setColor(Color.GREEN);
        g.drawRect(xpos, ypos, width, height);
        
        buttonXPos = getX(height/2);
        buttonYPos = getY(width / 5 * 4);
        buttonWidth = width / 5 * 2 ;
        buttonHeight = height / 10;
        
        createButton("Start Button",buttonXPos,buttonYPos,buttonWidth,buttonHeight,Color.ORANGE,g);
        
        
    }
//// Check for button pressed //////////////////////////////////////////////////
    public static void checkForPressedButton(int x, int y){
        x = x;
        y = y;
        
        if(x > buttonXPos - (buttonWidth / 2) && x < buttonXPos + (buttonWidth / 2) &&
           y > buttonYPos - (buttonHeight / 2) && y < buttonYPos + (buttonHeight / 2)){
            performButtonAcion();
        }
    }
//// Perform Button Action /////////////////////////////////////////////////////
    private static void performButtonAcion(){
        System.out.println("Button Pressed");
        System.out.println(num);
        num++;
        inMenu = false;
    }
//// Creating Buttons //////////////////////////////////////////////////////////
    private static void createButton(String name,int xpos,int ypos,int width,int height,Color color,Graphics2D g){
        g.translate(xpos,ypos);
        g.setColor(color);
        g.drawRect(-width/2, -height/2, width, height);
        g.fillRect(-width/2, -height/2, width, height);
        
        g.translate(-xpos,-ypos);
    }
////////////////////////////////////////////////////////////////////////////////
    public static void init(){
        inMenu = true;
    }
////////////////////////////////////////////////////////////////////////////////
    public static boolean isOpen(){
        return inMenu;
    }
    public static int getY(int y){
        return y + ypos ;
    }
    public static int getX(int x){
        return x + xpos;
    }
    
}
