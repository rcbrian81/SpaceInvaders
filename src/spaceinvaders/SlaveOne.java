package spaceinvaders;
import java.awt.*;

public class SlaveOne extends Ship{
    static Color laserColor = Color.red;
    static int laserHeight = 10;
    static int laserWidth = 2;
    static final int laserSpeed = 7;
    static int lives = 5;
    
    SlaveOne(int _xpos,int _ypos){
        super(_xpos,_ypos,Ship.Type.Slave, lives);
        image = SpaceInvaders.SlaveOneImage;
    }
}
