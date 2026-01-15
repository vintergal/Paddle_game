public class ScoreTrackingListener implements HitListener {
    private GameLevel gameLevel;
    private Counter score;

    public ScoreTrackingListener(GameLevel gameLevel, Counter score) {
        this.gameLevel = gameLevel;
        this.score=score;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        this.score.increase(5);

    }
}