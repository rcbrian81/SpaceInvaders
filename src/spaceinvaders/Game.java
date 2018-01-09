
package spaceinvaders;
import java.awt.*;


public class Game {
    static boolean inGame;
    static boolean gameOver;
    static EnemyWave currWave;
    static Falcon player;
    static int numEnemyShips;
    static final int gameStartNumLives = 3;
    
    static boolean gameWon;
    static int numLives;
    
    static final int finalRound = 3;
    static boolean roundWon;
    static int round;
    
///////////////////////////init Game Code///////////////////////////////////////    
    public static void initNewGame(){
        Menu.init();
        Laser.clearLasers();
        gameOver = false;
        inGame = false;
        gameWon = false;
        player = new Falcon();
        numLives = gameStartNumLives;
        initRoundOne();
    }
    
    private static void initRoundOne(){
        currWave = new EnemyWave(EnemyWave.wave1);
        numEnemyShips = currWave.getEnemyAL().size();
        round = 1;
        player.resetPos();
        roundWon = false;
        
    }
    private static void initRoundTwo(){
        currWave = new EnemyWave(EnemyWave.wave2);
        numEnemyShips = currWave.getEnemyAL().size();
        round = 2;
        player.resetPos();
        roundWon = false;
    }
    private static void initRoundThree(){
        currWave = new EnemyWave(EnemyWave.wave3);
        numEnemyShips = currWave.getEnemyAL().size();
        round = 3;
        player.resetPos();
        roundWon = false;
    }
    private static void initFinalRound(){
        currWave = new EnemyWave(EnemyWave.waveFinal);
        numEnemyShips = currWave.getEnemyAL().size();
        round = finalRound;
        player.resetPos();
        roundWon = false;
    }
    private static void moveToNextRound(){
        Laser.clearLasers();
        inGame = false;
        
        switch(round){
            case 1:
                initRoundTwo();
                break;
            case 2:
                initFinalRound();
                break;
            case finalRound:
                gameWon = true;
                break;
        }
    }
    private static void gameOver(){
        gameOver = true;
        inGame = false;
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    
    public static void Draw(Graphics2D g, SpaceInvaders main){
        player.draw(g, main);
        currWave.drawEnemyShips(g, main);
        Laser.drawAllLasers(g, inGame);
        if(Menu.isOpen())
            Menu.drawMain(g);
        
        
        g.setColor(Color.yellow);
        g.setFont(new Font("Arial",Font.PLAIN,35));
        if(gameWon){
            g.drawString("Game Over. YOU WIN!!", Window.WINDOW_WIDTH/4, Window.WINDOW_HEIGHT/3);
        }else if(gameOver){
            g.drawString("Game Over. You Lost.", Window.WINDOW_WIDTH/4, Window.WINDOW_HEIGHT/3);
        }
        
        g.drawString("" + numLives, 500, 500);
        
    }
    
    public static void Update(SpaceInvaders main){
        if(inGame && main.timeCount % (int)(main.frameRate*1) == (int)(main.frameRate*1-1)){
            currWave.move();
            
        }
        if(inGame && main.timeCount % (int)(main.frameRate*1) == (int)(main.frameRate*1-1))
            currWave.haveEnemyShoot();
        
        if(inGame)
        Laser.checkForHit(currWave);
        
        if(numEnemyShips == 0){
            moveToNextRound();
        }
    }
    
    public static void playerHit(){
        numLives--;
        if(numLives <= 0)
            gameOver();
    }
    
    
// Button Pressed Methods    
    public static void rightButtonPressed(){
        if(inGame)
            player.moveXPosBy(25);
    }
    public static void leftButtonPressed(){
        if(inGame)
            player.moveXPosBy(-25);
        
        
    }
    public static void spaceButtonpressed(){
        if(inGame)
            player.fireLaser();
    }
    public static void enterButtonPressed(){
        if(!Menu.isOpen() && !inGame)
            inGame = true;
        else if(!Menu.isOpen() && inGame)
            inGame = false;
    }
    public static void Name(){
        
    }
}
