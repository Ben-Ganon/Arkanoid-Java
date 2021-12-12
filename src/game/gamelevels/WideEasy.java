package game.gamelevels;
import basicshapes.Ball;
import basicshapes.Point;
import game.GameLevel;
import game.GenerateRandomColor;
import game.Velocity;
import spritesandcollidables.Block;
import spritesandcollidables.Rectangle;
import spritesandcollidables.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class WideEasy implements LevelInformation{
    final int ZERO = 0;
    final int TWO = 2;
    final int PADDLE_WIDTH = 500;
    final int FIVE = 5;
    final double TWENTY = 20;
    final double FORTY = 40;
    final double FIVE_EIGHTY = 580;
    final int BACK_WIDTH = 760;
    final int BACK_HEIGHT = 600;
    final int INITIAL_BLOCK_X = 20;
    final int BLOCK_Y = 200;
    final int BLOCK_WIDTH = 51;
    final int BLOCK_HEIGHT = 30;
    final int BALL_NUM = 10;
    final int BLOCK_NUM = 15;
    final int INITIAL_BALL_X = 220;
    final int INITIAL_BALL_Y = 400;
    final int FINAL_BALL_Y = 220;
    final int BALL_RADIUS = 8;


    public int numberOfBalls() {
        return BALL_NUM;
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
        return "Wide Easy";
    }
    public Sprite getBackground() {
        Rectangle background = new Rectangle(new Point(TWENTY,FIVE_EIGHTY), BACK_WIDTH, BACK_HEIGHT);
        background.setColor(Color.WHITE);
        Block blockBack =  new Block(background, new Velocity(ZERO,ZERO));
        return blockBack;
    }
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList();
        for (int i = ZERO; i < BLOCK_NUM; i++) {
            Rectangle rect = new Rectangle(new Point(
                        INITIAL_BLOCK_X + i * BLOCK_WIDTH, BLOCK_Y), BLOCK_WIDTH, BLOCK_HEIGHT);
            rect.setColor(GenerateRandomColor.get());
            Block block1 = new Block(rect, new Velocity(ZERO, ZERO));
            blocks.add(block1);
        }
        return blocks;
    }
    public int numberOfBlocksToRemove() {
        return this.blocks().size();

    }
    public ArrayList<Ball> getBalls() {
        ArrayList<Ball> balls = new ArrayList<>();
        for (int i = ZERO; i < (BALL_NUM / TWO); i++) {
            Ball currBall = new Ball(new Point(
                    INITIAL_BALL_X + i * FORTY, INITIAL_BALL_Y - i * TWENTY), BALL_RADIUS, Color.white);
            currBall.setVelocity(-5 + i, -3);
            balls.add(currBall);
        }
        for (int i = (BALL_NUM / TWO); i < BALL_NUM; i++) {
            Ball currBall = new Ball(new Point(
                    INITIAL_BALL_X + i * FORTY + FORTY, FINAL_BALL_Y + i * TWENTY), BALL_RADIUS, Color.white);
            currBall.setVelocity(-5 + i, -3);
            balls.add(currBall);
        }
        return balls;
    }

}
