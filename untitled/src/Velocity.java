// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private static final double epsilon=1.0001;
    private double dx;
    private double dy;
    // constructor
    public Velocity(double dx, double dy){
        this.dx=dx;
        this.dy=dy;
    }

    public Velocity copy(){
        Velocity velocity=new Velocity(dx, dy);
        return velocity;
    }
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));

        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p){
        return new Point(p.getX()+this.dx,p.getY()+this.dy);
    }
    public Point applyToPointWithEpsilon(Point p){
        return new Point(p.getX()+this.dx,p.getY()+this.dy);
    }

    public void invertX(){
        this.dx=-this.dx;
    }
    public void invertY(){
        this.dy=-this.dy;
    }

    public Velocity getInvertedX(){
        Velocity new_velocity=this.copy();
        new_velocity.invertX();
        return new_velocity;
    }
    public Velocity getInvertedY(){
        Velocity new_velocity=this.copy();
        new_velocity.invertY();
        return new_velocity;
    }
    private double getTotalSpeed(){
        return Math.sqrt(this.dx*this.dx+this.dy*this.dy);
    }
    public Velocity getWithAngle(double angle){
        Velocity new_velocity=Velocity.fromAngleAndSpeed(angle,getTotalSpeed());
        return new_velocity;
    }

    @Override
    public String toString() {
        return String.format("(%f,%f)",this.dx,this.dy);
    }
}