//Ben Ganon 318731007

package game;

import basicshapes.Ball;
import basicshapes.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.gamelevels.Animation;
import game.gamelevels.AnimationRunner;
import game.gamelevels.CountdownAnimation;
import game.gamelevels.LevelInformation;
import game.listeners.BallRemover;
import game.listeners.BlockRemover;
import game.listeners.ScoreTrackingListener;
import spritesandcollidables.Block;
import spritesandcollidables.Collidable;
import spritesandcollidables.Paddle;
import spritesandcollidables.Rectangle;
import spritesandcollidables.Sprite;
import spritesandcollidables.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * the Game.Game class executes the behaviour of the game screen and it's objects.
 *
 * @version 1.0
 * @author Ben Ganon 318731007
 * @since 2021-04-23
 */
public class GameLevel implements Animation {
    final int ONE = 1;
    final int THREE = 3;
    final int SIXTY = 60;
    final int SECOND = 1000;
    final int ZERO = 0;
    final double CANVAS_WIDTH = 800;
    final double CANVAS_HEIGHT = 600;
    final int ORIGIN = 0;
    final int EDGE_WIDTH = 20;
    final int BALL_SPEED = 5;
    final int PADDLE_SPEED = 12;
    final int PADDLE_X = 340;
    final int PADDLE_Y = 550;
    final int PADDLE_HEIGHT = 20;
    final int PADDLE_WIDTH = 125;
    final int TWO = 2;
    final int SCORE_BOX_HEIGHT = 30;
    final int TEXT_SIZE = 20;
    final int TEXT_MARGIN = 10;
    final int COUNTDOWN = 3;
    final int LEVEL_ORIGIN = 540;
    final int LIVES_ORIGIN = 50;

    final double SCORE_ORIGIN = CANVAS_WIDTH / TWO -  TWO * SCORE_BOX_HEIGHT;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockNum;
    private Counter ballNum;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    /**
     * builds a new instance of a game according to pre-defined canvas dimensions.
     * sprites houses all instances of draw-able objects in the game.
     * environment houses all collidable objects in the game.
     */
    public GameLevel(LevelInformation levelInfo, GUI gui, Counter score) {
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(new LinkedList<Collidable>());
        this.blockNum = new Counter(ZERO);
        this.ballNum = new Counter(ZERO);
        this.score = score;
        this.runner = new AnimationRunner(this.gui);
        this.running = false;
        this.keyboard = gui.getKeyboardSensor();
        this.levelInfo = levelInfo;
    }

    /**
     * @return - returns the object array environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * @return - returns the gui if this game.
     */
    public GUI getGUI() {
        return gui;
    }

    /**
     * @return -returns the number of remaining blocks in the game.
     */
    public Counter getBlockNum() {
        return this.blockNum;
    }
    /**
     * adds a new collidable object to the game environment.
     * @param c - the object to insert to the environment.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adds a new sprite to the spite array.
     * @param s - the sprite to insert.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * initialize makes new gui, sleeper, balls, blocks, to start a new game with the relevant objects.
     */
    public void initialize() {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        KeyboardSensor keyboard = this.keyboard;
        this.sprites.addSprite(levelInfo.getBackground());

        ArrayList<Ball> balls = new ArrayList<>();
        balls = levelInfo.getBalls();
        for(Ball currBall : balls) {
            ((Sprite)currBall).addToGame(this);
        }
        this.ballNum.increase(levelInfo.numberOfBalls());
        ArrayList<Block> blocks = new ArrayList<>();
        blocks.addAll(levelInfo.blocks());
        BlockRemover blockRemover = new BlockRemover(this, this.blockNum);
        //adding a new score listener.
        ScoreTrackingListener listenerScore = new ScoreTrackingListener(score);
        for(int i = ZERO; i < levelInfo.numberOfBlocksToRemove(); i++) {
            blocks.get(i).addToGame(this);
            blocks.get(i).addHitListener(blockRemover);
            this.blockNum.increase(ONE);
            blocks.get(i).addHitListener(listenerScore);
        }

        this.addEdges();
        //creating the paddle.
        Rectangle paddleRect = new Rectangle(new Point(CANVAS_WIDTH / TWO  - levelInfo.paddleWidth() / TWO,
                                                                PADDLE_Y), levelInfo.paddleWidth(), PADDLE_HEIGHT);
        paddleRect.setColor(Color.CYAN);
        Paddle paddle = new Paddle(paddleRect, keyboard);
        paddle.addToGame(this);
        //adding the score counter.
        addScoreCounter();
    }

    /**
     * run tells all the objects in the game to draw themselves on the screen and move themselves according to their
     * velocity.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(TWO, COUNTDOWN, this.sprites));
        this.runner.run(this);
    }

    /**
     * adds the edges of the screen to the game.
     */
    public void addEdges() {
        //defining variables for shorter lines and readability.
        Point topOrigin = new Point(ORIGIN, EDGE_WIDTH + SCORE_BOX_HEIGHT);
        Point leftOrigin = new Point(ORIGIN, CANVAS_HEIGHT);
        Point botOrigin = new Point(ORIGIN, CANVAS_HEIGHT);
        Point rightOrigin = new Point(CANVAS_WIDTH - EDGE_WIDTH, CANVAS_HEIGHT);

        Rectangle edgeTop = new Rectangle(topOrigin, CANVAS_WIDTH, EDGE_WIDTH);
        Rectangle edgeLeft = new Rectangle(leftOrigin, EDGE_WIDTH, CANVAS_HEIGHT);
        Rectangle edgeBot = new Rectangle(botOrigin, CANVAS_WIDTH, EDGE_WIDTH);
        Rectangle edgeRight = new Rectangle(rightOrigin, EDGE_WIDTH, CANVAS_HEIGHT);

        edgeTop.setColor(Color.GRAY);
        edgeLeft.setColor(Color.GRAY);
        edgeBot.setColor(Color.GRAY);
        edgeRight.setColor(Color.GRAY);

        Sprite edgeTopBlock = new Block(edgeTop, new Velocity(ZERO, ZERO));
        Sprite edgeLeftBlock = new Block(edgeLeft, new Velocity(ZERO, ZERO));
        Sprite edgeBotBlock = new Block(edgeBot, new Velocity(ZERO, ZERO));
        Sprite edgeRightBlock = new Block(edgeRight, new Velocity(ZERO, ZERO));
        //adding the newly created blocks to the game
        edgeTopBlock.addToGame(this);
        edgeLeftBlock.addToGame(this);
        edgeBotBlock.addToGame(this);
        edgeRightBlock.addToGame(this);

        //adding a new hitListener to detect when a ball falls off screen
        BallRemover deathZone = new BallRemover(this, this.ballNum);
        ((Block) edgeBotBlock).addHitListener(deathZone);


    }

    /**
     * @return - returns the block that comprises the background.
     */
    public Block getBackground() {
        Rectangle canvas = new Rectangle(new Point(ORIGIN, CANVAS_HEIGHT),
                CANVAS_WIDTH, CANVAS_HEIGHT);
        Block canvasBlock = new Block(canvas, new Velocity(ZERO, ZERO));
        canvas.setColor(Color.LIGHT_GRAY);
        return canvasBlock;
    }

    /**
     * removes a collidable from the game environment if possible.
     * @param c - desired collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        if (this.environment.getGameObjects().contains(c)) {
            this.environment.removeCollidable(c);
        }
    }
    /**
     * removes the sprite s from the game if possible.
     * @param s - the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        if (this.sprites.getCollection().contains(s)) {
            this.sprites.removeSprite(s);
        }
    }

    /**
     * adds a new score Counter to the game at the top of the screen.
     */
    public void addScoreCounter() {
        Point topOrigin = new Point(ORIGIN, SCORE_BOX_HEIGHT);
        Rectangle scoreBox = new Rectangle(topOrigin, CANVAS_WIDTH, SCORE_BOX_HEIGHT);
        scoreBox.setColor(Color.cyan);
        Sprite scoreBoxSprite = new Block(scoreBox, new Velocity(ZERO, ZERO));
        scoreBoxSprite.addToGame(this);
    }
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = SIXTY;
        //defining the mliseconds in a second for later use.
        int millisecondsPerFrame = SECOND / framesPerSecond;
        //running the game until the games is closed.
        long startTime = System.currentTimeMillis(); // timing
        //drawing the background to the screen.
        Block background = (Block) levelInfo.getBackground();
        //background.drawOn(d);
        //drawing all the sprites to the screen.
        this.sprites.drawAllOn(d);
        d.drawText((int) SCORE_ORIGIN, SCORE_BOX_HEIGHT / TWO + TEXT_MARGIN,
                "Score: " + this.score.toString(), TEXT_SIZE);
        d.drawText(LEVEL_ORIGIN, SCORE_BOX_HEIGHT / TWO + TEXT_MARGIN,
                "Level Name: " + levelInfo.levelName(), TEXT_SIZE);
        d.drawText(LIVES_ORIGIN, SCORE_BOX_HEIGHT / TWO + TEXT_MARGIN,
                "Lives: " + 7, TEXT_SIZE);
        //telling all the game objects to move accoring to their timePassed method.
        this.sprites.notifyAllTimePassed();
        //the time that has lapsed until this point.
        long usedTime = System.currentTimeMillis() - startTime;
        //defining how long  to sleep accoring to how long we were calculating the drawing and moving.
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > ZERO) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
            if (this.blockNum.getValue() == ZERO || this.ballNum.getValue() == ZERO) {
                this.score.increase(100);
                this.running = false;
                this.gui.close();

            }
    }
    public boolean shouldStop() {
        return !this.running;
    }
}
