import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    public AnimationRunner(){
        framesPerSecond=60;
        gui=new GUI("nice game",800,600);
        sleeper=new Sleeper();
    }

    public biuoop.KeyboardSensor getKeyboard(){
        return this.gui.getKeyboardSensor();
    }
    // ...
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;

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
    }
}