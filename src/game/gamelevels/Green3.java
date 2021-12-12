package game.gamelevels;

import basicshapes.Ball;
import basicshapes.Point;
import game.GenerateRandomColor;
import game.Velocity;
import spritesandcollidables.Block;
import spritesandcollidables.Rectangle;
import spritesandcollidables.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Green3 implements LevelInformation{
    final int TWO = 2;
    final int ONE = 1;
    final int ZERO = 0;
    final int ONE_TEN = 110;
    final int SEVENTY = 70;
    final int ROW_NUM = 5;
    final int BLOCK_NUM = 10;
    final int BLOCK_WIDTH = 63;
    final double TWENTY = 20;
    final double FIVE_EIGHTY = 580;
    final int BACK_WIDTH = 760;
    final int BACK_HEIGHT = 600;
    final double CANVAS_WIDTH = 800;
    final double CANVAS_HEIGHT = 600;
    final int BALL_RADIUS = 8;
    final int START_LINE = 20;
    final int START_ROW = 150;
    final int BLOCK_HEIGHT = 30;
    final int PADDLE_WIDTH = 120;
    final int ONE_HUNDRED = 100;
    final double BALL_ONE_X = CANVAS_WIDTH / TWO + ONE_HUNDRED;
    final double BALL_TWO_X = CANVAS_WIDTH / TWO - ONE_HUNDRED;
    final double BALL_Y = 4 * CANVAS_HEIGHT / 5;
    final double FIVE = 5;
    final int BACK_RED = 34;
    final int BACK_GREEN = 139;
    final int BACK_BLUE = 44;

   public int numberOfBalls() {
       return TWO;
   }
   public List<Velocity> initialBallVelocities() {
       return new ArrayList<>();
   }
   public int paddleSpeed() {
       return ZERO;
   }
   public int paddleWidth() {
       return PADDLE_WIDTH;
   }
   public String levelName() {
       return "Green 3";
   }
   public Sprite getBackground() {
       Rectangle background = new Rectangle(new Point(TWENTY,FIVE_EIGHTY), BACK_WIDTH, BACK_HEIGHT);


       Color backColor = new Color(BACK_RED, BACK_GREEN, BACK_BLUE);


       background.setColor(backColor);
       Block blockBack =  new Block(background, new Velocity(ZERO,ZERO));
       return blockBack;
   }
   public List<Block> blocks() {
       ArrayList<Block> blocks= new ArrayList<>();
       for (int i = ZERO; i < ROW_NUM; i++) {
           //casting a random color for each row of squares.
           Color currColor = new Color(
                   BACK_RED * (i + ONE) + SEVENTY, BACK_GREEN - ONE_TEN, BACK_BLUE * i + SEVENTY);
           //inner for loop for each block in a row.
           for (int j = ZERO; j < BLOCK_NUM - i; j++) {
               //initializing the blocks adjacent to each other, starting from a different position each row.
               double x = CANVAS_WIDTH - (START_LINE + j * (BLOCK_WIDTH));
               double y = START_ROW + i * BLOCK_HEIGHT;
               Point startRec = new Point(x, y);
               //building a new block and inserting it into the game.
               Rectangle currRec = new Rectangle(startRec, BLOCK_WIDTH, BLOCK_HEIGHT);
               currRec.setColor(currColor);
               Block currBlock = new Block(currRec, new Velocity(ZERO, ZERO));
               blocks.add(currBlock);
           }
       }
       return blocks;

   }
   public int numberOfBlocksToRemove() {
       return this.blocks().size();

   }
   public ArrayList<Ball> getBalls() {
       ArrayList<Ball> balls = new ArrayList<>();
       Ball b1 = new Ball(new Point(BALL_ONE_X, BALL_Y), BALL_RADIUS, Color.white);
       b1.setVelocity(FIVE, -FIVE);
       Ball b2 =  new Ball(new Point(BALL_TWO_X, BALL_Y), BALL_RADIUS, Color.white);
       b2.setVelocity(-FIVE, -FIVE);
       balls.add(b1);
       balls.add(b2);
       return balls;
   }
}
