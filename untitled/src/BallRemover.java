public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls=remainingBalls;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        this.remainingBalls.decrease(1);

    }
}