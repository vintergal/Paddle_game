public class Ass2Game {



    public static void main(String[] args) {
        GameLevel gameLevel = GameLevel.getInstance();
        gameLevel.initialize();
        gameLevel.run();

    }
}