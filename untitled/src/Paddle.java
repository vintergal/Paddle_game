import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.ArrayList;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private int[] parts_changes;
    private int part_width;
    public Paddle(int startX,int startY,int width, int height,GUI gui){
        this(new Point(startX,startY),width,height,gui);
    }

    public Paddle(Point start, int width, int height, GUI gui){
        this.keyboard=gui.getKeyboardSensor();
        this.rectangle= new Rectangle(start,width,height);
        this.parts_changes= new int[]{210 , 240 , -1, 300 , 330 };
        this.part_width=width/parts_changes.length;
    }

    public void moveLeft(){
        Velocity velocity=new Velocity(-5,0);

        Point newStart=velocity.applyToPoint(this.rectangle.getUpperLeft());

        if (newStart.getX()>=Game.BOUNDS_THICK){
            this.rectangle=new Rectangle(newStart,rectangle.getWidth(),rectangle.getHeight());
        }

    }
    public void moveRight(){
        Velocity velocity=new Velocity(5,0);

        Point newStart=velocity.applyToPoint(this.rectangle.getUpperLeft());

        if (newStart.getX()+this.rectangle.getWidth()<=(Game.WIDTH- Game.BOUNDS_THICK)){
            this.rectangle=new Rectangle(newStart,rectangle.getWidth(),rectangle.getHeight());
        }}

    // Sprite
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    public void drawOn(DrawSurface d) {
        int startX=this.rectangle.getUpperLeft().getIntX();
        int startY=this.rectangle.getUpperLeft().getIntY();
        int height=(int)this.rectangle.getHeight();
        int width=(int)this.rectangle.getWidth();
        d.setColor(Color.black);
        d.fillRectangle(startX,startY,width,height);
        d.setColor(Color.red);
        for (int i = 0; i < parts_changes.length; i++) {
            d.drawLine((startX+i*(this.part_width-1)),startY,(startX+i*(this.part_width-1)),startY+height);
        }

    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

        int angle=this.parts_changes[getCollisedRegionIndex(collisionPoint)];


        if (angle==-1){
            return currentVelocity.getInvertedY();
        }else{
            Velocity new_velocity=currentVelocity.getWithAngle(angle);
            /*if (currentVelocity.isMovedUp()) {
                new_velocity.invertY();
            }*/
            return new_velocity;
        }
    }
    private int getCollisedRegionIndex(Point collisionPoint){

        double result =(collisionPoint.getX()-rectangle.getUpperLeft().getX())/this.part_width;
        return (result<5?(int)Math.floor(result):4);

    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);

    }
}