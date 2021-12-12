package game.gamelevels;

import basicshapes.Ball;
import basicshapes.Point;
import game.GameLevel;
import game.Velocity;
import spritesandcollidables.Block;
import spritesandcollidables.Rectangle;
import spritesandcollidables.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class DirectHit implements LevelInformation {
    final int TWO = 2;
    final double CANVAS_WIDTH = 800;
    final double CANVAS_HEIGHT = 600;
    final int ZERO = 0;
    final int PADDLE_WIDTH = 100;
    final int FIVE = 5;
    final double TWENTY = 20;
    final double FIVE_EIGHTY = 580;
    final int BACK_WIDTH = 760;
    final int BACK_HEIGHT = 600;
    final int BLOCK_X = 380;
    final int BLOCK_Y = 200;
    final int BLOCK_DIM = 30;
    final int ONE = 1;
    final int BALL_SIZE = 10;
    final int ONE_FIFTY = 150;


    public int numberOfBalls() {
        return ONE;
    }
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBall =  new ArrayList<>();
        initialBall.add(new Velocity(ZERO, -FIVE));
        return initialBall;
    }
    public int paddleSpeed() {
        return ZERO;
    }
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }
    public String levelName() {
        return "Direct Hit";
    }
    public Sprite getBackground() {
        Rectangle background = new Rectangle(new Point(TWENTY,FIVE_EIGHTY), BACK_WIDTH, BACK_HEIGHT);
        background.setColor(Color.BLACK);
        Block blockBack =  new Block(background, new Velocity(ZERO,ZERO));
        return blockBack;
    }
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList();
        Rectangle rect = new Rectangle(new Point(BLOCK_X, BLOCK_Y), BLOCK_DIM, BLOCK_DIM);
        rect.setColor(Color.RED);
        Block block1 = new Block(rect, new Velocity(ZERO, ZERO));
        blocks.add(block1);
        return blocks;
    }
    public int numberOfBlocksToRemove() {
        return this.blocks().size();

    }
    public ArrayList<Ball> getBalls() {
        ArrayList<Ball> balls = new ArrayList<>();
        Ball b1 =  new Ball(new Point(CANVAS_WIDTH / TWO, CANVAS_HEIGHT - ONE_FIFTY), BALL_SIZE, Color.WHITE);
        b1.setVelocity(new Velocity(ZERO, -FIVE));
        balls.add(b1);
        return balls;
    }
}
