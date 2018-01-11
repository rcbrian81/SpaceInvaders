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
        
        xpos = Window.getWidth2() / 2;
        ypos = Window.getHeight2() / 2;
        height = Window.getHeight2() / 2;
        width = Window.getWidth2() /2;
        
        drawBoardBack(xpos,ypos,width,height,g);
        
        buttonXPos = width/2;
        buttonYPos = height / 5 * 4;
        buttonWidth = width / 5 * 2 ;
        buttonHeight = height / 10;
        
        createButton("Start",getX(buttonXPos),getY(buttonYPos),buttonWidth,buttonHeight,Color.ORANGE,g);
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,40));
        g.drawString("Welcome", getX(width/10 * 3), getY(height/3));
        
        
    }
//// Check for button pressed //////////////////////////////////////////////////
    public static void checkForPressedButton(int x, int y){
        x = x;
        y = y;
        
        if(x > getX(buttonXPos) - (buttonWidth / 2) && x < getX(buttonXPos) + (buttonWidth / 2) &&
           y > getY(buttonYPos) - (buttonHeight / 2) && y < getY(buttonYPos) + (buttonHeight / 2)){
            performButtonAcion();
        }
    }
//// Perform Button Action /////////////////////////////////////////////////////
    private static void performButtonAcion(){
        num++;
        inMenu = false;
    }
//// Creating Buttons //////////////////////////////////////////////////////////
    private static void drawBoardBack(int xpos,int ypos,int width,int height,Graphics2D g){
        
        g.translate(xpos,ypos);
        
        g.setColor(Color.black);
        g.fillRect(-width/2, -height/2, width, height);
        g.setColor(Color.green);
        g.drawRect(-width/2, -height/2, width, height);
        
        
        g.translate(-xpos,-ypos);
    }
    private static void createButton(String string,int xpos,int ypos,int width,int height,Color color,Graphics2D g){
        g.translate(xpos,ypos);
        g.setColor(color);
        g.drawRect(-width/2, -height/2, width, height);
        g.fillRect(-width/2, -height/2, width, height);
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,35));
        g.drawString(string, -width/10 * 2, height/2);
        
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
        return y + (ypos - (height/2)) ;
        
    }
    public static int getX(int x){
        return x + (xpos - (width/2));
    }
    
}
