
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
    static int[] livesImages = new int[numLives];
    static int num = 1;
    
    static final int finalRound = 3;
    static boolean roundStarted;
    static boolean roundWon;
    static int round;
    static String roundMessage;
    
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
        Metior.clear();
        currWave = new EnemyWave(EnemyWave.wave1,1,3,5);
        numEnemyShips = currWave.getNumLives();
        round = 1;
        player.resetPos();
        roundWon = false;
        roundMessage = "Round 1";
        roundStarted = false;
    }
    private static void initRoundTwo(){
        currWave = new EnemyWave(EnemyWave.wave2,3,2,3);
        numEnemyShips = currWave.getNumLives();
        round = 2;
        player.resetPos();
        roundWon = false;
        roundMessage = "Round 2";
        roundStarted = false;
    }
    private static void initRoundThree(){
        currWave = new EnemyWave(EnemyWave.wave3,5,2,2);
        numEnemyShips = currWave.getNumLives();
        round = 3;
        player.resetPos();
        roundWon = false;
        roundMessage = "Round 3";
        roundStarted = false;
    }
    private static void initFinalRound(){
        currWave = new EnemyWave(EnemyWave.waveFinal,7,2,2);
        numEnemyShips = currWave.getNumLives();
        round = finalRound;
        player.resetPos();
        roundWon = false;
        roundMessage = "Final Round";
        roundStarted = false;
    }
    private static void moveToNextRound(){
        Laser.clearLasers();
        Metior.clear();
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
        drawLivesBox(g,main);
        player.draw(g, main);
        currWave.drawEnemyShips(g, main);
        Laser.drawAllLasers(g, inGame);
        if(Menu.isOpen())
            Menu.drawMain(g);
        
        g.setColor(Color.yellow);
        g.setFont(new Font("Arial",Font.PLAIN,35));
        if(!Menu.isOpen() && !inGame && !roundStarted){
            g.drawString(roundMessage, Window.WINDOW_WIDTH/3, Window.WINDOW_HEIGHT/3);
            g.drawString("CLICK ENTER TO BEGIN", Window.WINDOW_WIDTH/4, Window.WINDOW_HEIGHT/3 * 2);
        }
        g.setColor(Color.yellow);
        g.setFont(new Font("Arial",Font.PLAIN,35));
        if(gameWon){
            g.drawString("Game Over. YOU WIN!!", Window.WINDOW_WIDTH/4, Window.WINDOW_HEIGHT/3);
        }else if(gameOver){
            g.drawString("Game Over. You Lost.", Window.WINDOW_WIDTH/4, Window.WINDOW_HEIGHT/3);
        }
        
        Metior.Draw(g,inGame,main);
        
    }
    
    public static void Update(SpaceInvaders main){
        if(inGame && main.timeCount % (int)(main.frameRate*1) == (int)(main.frameRate*1-1))
            currWave.move();
        if(inGame)
        currWave.mustMove();
        if(inGame && main.timeCount % (int)(main.frameRate*currWave.shootFREQ) == (int)(main.frameRate*currWave.shootFREQ-1))
            currWave.haveEnemyShoot();
        
        if(inGame && main.timeCount % (int)(main.frameRate*currWave.metiorFREQ) == (int)(main.frameRate*currWave.metiorFREQ-1))
            new Metior(main);
        
        
        if(inGame){
            Laser.checkForHit(currWave);
            Metior.checkForCollision();
        }
        
        if(numEnemyShips == 0){
            moveToNextRound();
        }
    }
    
    public static void playerHit(){
        numLives--;
        if(numLives <= 0)
            gameOver();
    }
    
    public static void drawLivesBox(Graphics2D g, SpaceInvaders main){
        int width = Window.getWidth2() / 7;
        int hieght = Window.getHeight2() /20;
        g.setColor(Color.GRAY);
        g.fillRect(Window.getWidth2() - width, Window.getY(0), width, hieght);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial",Font.PLAIN,25));
        g.drawString("LIVES: " + numLives,Window.getWidth2() - width , Window.getY(0) + 30);
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
        if(!Menu.isOpen() && !inGame && !roundStarted)
            roundStarted = true;
        if(!Menu.isOpen() && !inGame)
            inGame = true;
        else if(!Menu.isOpen() && inGame)
            inGame = false;
    }
    public static void Name(){
        
    }
}
