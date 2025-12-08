import biuoop.DrawSurface;

import java.awt.*;

public class Block implements Collidable,Sprite {
    private Rectangle collisionRectangle;
    private Color color;
    public Block(double startX,double startY,double width, double height){
        this(startX,startY,width,height,Color.red);
    }
    public Block(double startX, double startY, double width, double height, Color color){
        this.collisionRectangle= new Rectangle(new Point(startX,startY),width,height);
        this.color=color;

    }


    public Block(Rectangle rect){
        this.collisionRectangle=rect.copy();
    }
    public void drawOn(DrawSurface surface){
        int startX=this.collisionRectangle.getUpperLeft().getIntX();
        int startY=this.collisionRectangle.getUpperLeft().getIntY();
        int height=(int)this.collisionRectangle.getHeight();
        int width=(int)this.collisionRectangle.getWidth();
        surface.setColor(this.color);
        surface.fillRectangle(startX,startY,width,height);
        surface.setColor(Color.black);

        surface.drawRectangle(startX,startY,width,height);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

        Velocity new_velocity=currentVelocity;
        if (this.collisionRectangle.isPointOnHorizontalEdge(collisionPoint)){
            new_velocity= new_velocity.getInvertedY();

        }
        if (this.collisionRectangle.isPointOnVerticalEdge(collisionPoint)){
            new_velocity= new_velocity.getInvertedX();
        }
        return new_velocity;
    }


}
