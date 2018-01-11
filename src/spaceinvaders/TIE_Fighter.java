package spaceinvaders;
import java.awt.*;

public class TIE_Fighter extends Ship {
    static Color laserColor = Color.red;
    static int laserHeight = 10;
    static int laserWidth = 2;
    static final int laserSpeed = 2;
    
    TIE_Fighter(int _xpos,int _ypos){
        super(_xpos,_ypos,Ship.Type.TIE);
        image = SpaceInvaders.TIEFighterImage;
    }
}
