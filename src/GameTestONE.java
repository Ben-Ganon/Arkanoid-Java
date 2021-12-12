import biuoop.GUI;
import game.GameLevel;
import game.gamelevels.*;

public class GameTestONE {
    static final int CANVAS_WIDTH = 800;
    static final int CANVAS_HEIGHT = 600;
    public static void main(String[] args) {
        GUI gui1 = new GUI("game", (int) CANVAS_WIDTH, (int) CANVAS_HEIGHT);
        LevelInformation level1 = new FinalFour();
        GameLevel gameLevel = new GameLevel(level1);
        gameLevel.initialize();
        gameLevel.run();
    }
}
