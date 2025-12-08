import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.ArrayList;

public class Ass2Game {



    public static void main(String[] args) {
        Game game=Game.getInstance();
        game.initialize();
        game.run();

    }
}