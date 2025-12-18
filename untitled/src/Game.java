import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;

public class Game {
    private static Game instance=null;
    boolean TEST=false;
    public static final int WIDTH=800;
    public static final int HEIGHT=600;
    public static final int BOUNDS_THICK=30;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private BlockRemover block_remover;
    private Counter remaining_blocks;
    private BallRemover balls_remover;
    private Counter remaining_balls;
    private ScoreTrackingListener scoreTrackingListener;
    private Counter score;

    public static Game getInstance() {
        if (Game.instance==null){
            Game.instance=new Game();
        }
        return Game.instance;
    }

    private Game(){

    }


    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.sprites=new SpriteCollection();
        this.environment=GameEnvironment.getInstance();
        this.gui=new GUI("project2", WIDTH, HEIGHT);
        this.sleeper=new Sleeper();

        this.remaining_blocks=new Counter(0);
        this.block_remover=new BlockRemover(this,remaining_blocks);
        this.remaining_balls=new Counter(0);
        this.balls_remover=new BallRemover(this,remaining_balls);
        this.score=new Counter(0);
        this.scoreTrackingListener=new ScoreTrackingListener(this,this.score);

        Ball ball1 = new Ball(WIDTH-400,HEIGHT-100,5,Color.black);
        ball1.setVelocity(0,1);
        Ball ball2 = new Ball(WIDTH-350,HEIGHT-100,5,Color.black);
        ball2.setVelocity(0,1);


        Block boundLeft=new Block(0,0,BOUNDS_THICK,HEIGHT,Color.DARK_GRAY);
        Block boundRight=new Block(WIDTH-BOUNDS_THICK,0,BOUNDS_THICK,HEIGHT,Color.DARK_GRAY);
        Block boundTop=new Block(BOUNDS_THICK,0,WIDTH-2*BOUNDS_THICK,BOUNDS_THICK,Color.DARK_GRAY);
        Block boundBottom=new Block(BOUNDS_THICK,HEIGHT-BOUNDS_THICK,WIDTH-2*BOUNDS_THICK,BOUNDS_THICK,Color.DARK_GRAY);
        Paddle paddle=new Paddle(new Point(400,HEIGHT-20-BOUNDS_THICK),200,20,gui);

        boundLeft.addToGame(this);
        boundRight.addToGame(this);
        boundTop.addToGame(this);
        boundBottom.addToGame(this);
        boundBottom.addHitListener(this.balls_remover);
        ScoreIndicator si=new ScoreIndicator(WIDTH,10,score);
        si.addToGame(this);




        this.addBlocksOfPattern();

        paddle.addToGame(this);

        ball1.addToGame(this);
        ball2.addToGame(this);
        this.remaining_balls.increase(2);


    }

    public void addBlocksOfPattern(){
        int startFromRight=WIDTH-100;
        int startFromAbove=100;
        int block_width=50;
        int block_height=20;
        int blocks_in_first_row=12;
        int num_of_rows=6;
        int spaceX=0;
        int spacey=0;

        for (int row=0;row<num_of_rows;row++){
            for (int block_index=0;block_index<blocks_in_first_row-row;block_index++){
                int startX=startFromRight-(block_index+1)*(block_width+spaceX);
                int startY=startFromAbove+row*(block_height+spacey);
                Block block = new Block(startX,startY,block_width,block_height,Color.blue);
                block.addToGame(this);
                block.addHitListener(block_remover);
                block.addHitListener(scoreTrackingListener);

                this.remaining_blocks.increase(1);
            }
        }

    }


        // Run the game -- start the animation loop.
    public void run() {
        int framesPerSecond = 600;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (remaining_blocks.getValue()==0){
                //gui.close();
                score.increase(100);
                return;
            }
            if (remaining_balls.getValue()==0){
                //gui.close();
                return;
            }
        }
    }

    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    public void removeSprite(Sprite s){
        this.sprites.removeSprite(s);
    }
}