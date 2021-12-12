package game.gamelevels;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class AnimationRunner {
    private final int SECOND = 1000;
    static final int SIXTY = 60;

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = SIXTY;
        this.sleeper = new Sleeper();
    }
    public void run(Animation animation) {
        int millisecondsPerFrame = SECOND / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        sleeper.sleepFor(SECOND);

    }
}
