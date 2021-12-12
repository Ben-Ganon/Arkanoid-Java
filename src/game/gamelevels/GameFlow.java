package game.gamelevels;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.Counter;
import game.GameLevel;

import java.util.List;

public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter score;
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.gui = gui;
        this.ar = ar;
        this.ks = ks;
    }
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation lvlInfo : levels) {
            GameLevel level = new GameLevel(lvlInfo, this.gui, this.score);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
        }
    }
}
