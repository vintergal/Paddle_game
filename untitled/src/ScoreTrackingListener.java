public class ScoreTrackingListener implements HitListener {
    private Game game;
    private Counter score;

    public ScoreTrackingListener(Game game, Counter score) {
        this.game=game;
        this.score=score;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        this.score.increase(5);

    }
}