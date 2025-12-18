import biuoop.DrawSurface;

import java.util.ArrayList;

public class SpriteCollection {
    private ArrayList<Sprite> sprites;
    public SpriteCollection(){
        sprites=new ArrayList<>();
    }
    public void addSprite(Sprite s){
        sprites.add(s);
    }
    public void removeSprite(Sprite s){
        sprites.remove(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed(){
        ArrayList<Sprite> spritesCopy=new ArrayList<>(sprites);
        for (Sprite sprite: spritesCopy)
        {
            sprite.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){
        for (Sprite sprite: sprites)
        {
            sprite.drawOn(d);
        }
    }
}