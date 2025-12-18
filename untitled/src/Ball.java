import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class Ball implements Sprite {
    // constructor
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    private double r;
    private Point center;

    private Velocity velocity;
    private GameEnvironment game_environment;
    public Ball(double x, double y, double r, java.awt.Color color)
    {
        this.center=new Point(x,y);
        this.r=r;
        this.color=color;
        this.setVelocity(0,0);
        this.game_environment=GameEnvironment.getInstance();
        this.hitListeners=new ArrayList<>();
    }
    public Ball(Point center, int r, java.awt.Color color,GameEnvironment game_environment)
    {
        this(center.getX(),center.getY(),r,color);
    }

    // accessors
    public int getIntX(){
        return this.center.getIntX();
    }
    public int getIntY(){
        return this.center.getIntY();}
    public int getSize(){
        return (int)(this.r*this.r*Math.PI);
    }
    public java.awt.Color getColor(){
        return this.color;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface){
        surface.setColor(color);
        surface.drawCircle(this.getIntX(),this.getIntY(),(int)r);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    public void setVelocity(Velocity v){
        this.velocity=v;
    }
    public void setVelocity(double dx, double dy){
        this.velocity=new Velocity(dx,dy);
    }
    public Velocity getVelocity(){
        return this.velocity;
    }

    public void moveOneStep() {

        Point new_center = this.getVelocity().applyToPoint(this.center);
        Line trajectory=new Line(this.center,new_center);

        CollisionInfo collision_info=this.game_environment.getClosestCollision(trajectory);
        if  (collision_info!=null) {
            Point collisionPoint=collision_info.collisionPoint();
            Velocity returnVelocity=collision_info.collisionObject().hit(this,collisionPoint,this.velocity);
            this.setVelocity(returnVelocity);
        }else {
            this.center = this.velocity.applyToPoint(this.center);
        }
    }

    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }


}