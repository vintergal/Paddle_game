import biuoop.DrawSurface;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle rect;

    public ScoreIndicator(int width,int height,Counter score){
        this.score=score;
        rect=new Rectangle(new Point(0,0),width,height);


    }

    public void drawOn(DrawSurface surface){
        int startX=this.rect.getUpperLeft().getIntX();
        int startY=this.rect.getUpperLeft().getIntY();
        int height=(int)this.rect.getHeight();
        int width=(int)this.rect.getWidth();
        surface.setColor(Color.lightGray);
        surface.fillRectangle(startX,startY,width,height);
        surface.setColor(Color.black);

        surface.drawText(100,10,String.format("Score: %d",score.getValue()),12);

        surface.drawRectangle(startX,startY,width,height);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }


}
