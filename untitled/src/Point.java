import java.util.ArrayList;
import java.util.List;

public class Point {
    private final double x;
    private final double y;
    // constructor
    public Point(double x, double y) {
        this.x=x;
        this.y=y;
    }
    public Point(Point p) {
        this.x=p.getX();
        this.y=p.getY();
    }

    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.getX()-other.getX(),2)+Math.pow(this.getY()-other.getY(),2));
    }
    public Point getTransposed(){
        return new Point(y,x);
    }
    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {

        return this.getX()==other.getX() && this.getY()==other.getY();
    }

    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }
    public int getIntX() {
        return (int)(this.x);
    }
    public double getY() {
        return this.y;
    }

    public int getIntY() {
        return (int)(this.y);
    }

    @Override
    public String toString() {
        return String.format("(%f,%f)",this.x,this.y);
    }

    public CollisionInfo getClosestCollision(List<CollisionInfo> collisions){
        double min_distance_form_start=-1;
        CollisionInfo chosen=null;
        for (CollisionInfo collision: collisions){
            double distance=Math.floor(collision.collisionPoint().distance(this));
            if (min_distance_form_start==-1 || distance<min_distance_form_start){
                min_distance_form_start=distance;
                chosen=collision;
            }
        }
        return chosen;
    }
    public Point getClosestCollisionPoint(List<Point> collisions){
        ArrayList<CollisionInfo> collisionInfos= new ArrayList<>();
        for (Point collision : collisions){
            collisionInfos.add(new CollisionInfo(collision,null));
        }
        CollisionInfo ci=this.getClosestCollision(collisionInfos);
        if (ci==null){
            return null;
        }else {
            return ci.collisionPoint();
        }
    }
}