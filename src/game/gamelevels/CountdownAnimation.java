package game.gamelevels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import spritesandcollidables.SpriteCollection;

import java.awt.Color;

public class CountdownAnimation implements Animation{
    static final int SIXTY = 60;
    static final int SECOND = 1000;
    static final int TWO = 2;
    static final int TEXT_SIZE = 100;
    static final int BUFFER = 80;
    static final int ZERO = 0;
    static final int GO_OVAL_W = 300;
    static final int GO_OVAL_H = 250;
    static final int THREE = 3;
    static final int TEN = 10;
    static final int FIFTY = 50;


    private boolean stop;
    private int countFrom;
    private int currentNum;
    private double numOfSeconds;
    private SpriteCollection gameScreen;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.stop = false;
        this.currentNum = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;



    }
    public void doOneFrame(DrawSurface d){
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = SIXTY;
        //defining the mliseconds in a second for later use.
        int millisecondsPerFrame = SECOND / framesPerSecond;
        //running the game until the games is closed.
        long startTime = System.currentTimeMillis();// timing
        sleeper.sleepFor((long) this.numOfSeconds * SECOND / this.countFrom);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.BLACK);
        //d.fillOval(d.getWidth() / TWO - GO_OVAL_W / TWO, d.getHeight() / TWO - GO_OVAL_H / TWO, GO_OVAL_W, GO_OVAL_H);
        d.setColor(Color.RED);
        if (this.currentNum == 0) {

            d.drawText(d.getWidth() / TWO - BUFFER, d.getHeight() / TWO, "GO!", TEXT_SIZE);

            this.stop = true;
        }else {
            d.drawText(d.getWidth() / TWO - BUFFER / THREE, d.getHeight() / TWO, Integer.toString(this.currentNum), TEXT_SIZE);
            this.currentNum-- ;
        }



    }
    public boolean shouldStop(){
        return this.stop;
    }
}
