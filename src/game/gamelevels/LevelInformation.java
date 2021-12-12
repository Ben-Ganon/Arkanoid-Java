package game.gamelevels;

import basicshapes.Ball;
import game.Velocity;
import spritesandcollidables.Block;
import spritesandcollidables.Sprite;

import java.util.ArrayList;
import java.util.List;

public interface LevelInformation {
    int numberOfBalls();
    List<Velocity> initialBallVelocities();
    int paddleSpeed();
    int paddleWidth();
    String levelName();
    Sprite getBackground();
    List<Block> blocks();
    int numberOfBlocksToRemove();
    ArrayList<Ball> getBalls();
}
