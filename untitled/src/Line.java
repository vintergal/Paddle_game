import java.util.ArrayList;
import java.util.List;

public class Line {
    private final Point start;
    private  final Point end;
    private final double m;
    private final double n;
    private final boolean is_vertical;
    private final boolean is_horizontal;
    // constructors
    public Line(Point start, Point end) {


        this.start = start;
        this.end = end;


        if (start.getX()==end.getX()){
            this.is_vertical=true;
            this.is_horizontal=false;
            m=0; //initialization
            n=0;
        }
        else {
            this.is_vertical=false;
            this.m=(this.end.getY()-this.start.getY())/(this.end.getX()-this.start.getX());
            this.n=this.end.getY()-this.m*this.end.getX();

            this.is_horizontal=this.m==0;

        }

    }
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1,y1),new Point(x2,y2));

    }
    private double getMinY(){
        return Math.min(this.start.getY(),this.end.getY());
    }

    private double getMaxY(){
        return Math.max(this.start.getY(),this.end.getY());
    }

    private double getMinX(){
        return Math.min(this.start.getX(),this.end.getX());
    }

    private double getMaxX(){
        return Math.max(this.start.getX(),this.end.getX());
    }
    // Return the length of the line
    public double length() {
        return this.start().distance(this.end());
    }

    // Returns the middle point of the line
    public Point middle() {
        double x,y;
        x=(this.start().getX()+this.end().getX())/2;
        y=(this.start().getY()+this.end().getY())/2;

        return new Point(x,y);
    }

    // Returns the start point of the line
    public Point start() {
        return this.start;
    }

    // Returns the end point of the line
    public Point end() {
        return this.end;
    }

    private boolean isXOnLineBounds(double x){
        if (x>Math.max(this.start.getX(),this.end.getX())){
            return false;
        }

        if (x<Math.min(this.start.getX(),this.end.getX())){
            return false;
        }
        return true;
    }

    public Line getTransposed(){
        return new Line(this.start.getTransposed(),this.end.getTransposed());
    }


        // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other)!=null;
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        Point point;
        Line verticalLine=null;
        Line horizontal_line=null;
        if (this.is_vertical || other.is_vertical){
            if (this.is_horizontal || other.is_horizontal) {
                if (this.is_horizontal){
                    verticalLine=other;
                    horizontal_line=this;
                }
                else {
                    verticalLine=this;
                    horizontal_line=other;
                }
                point=new Point(verticalLine.start.getX(), horizontal_line.start.getY());
                if (!(this.isPointOnLine(point) && other.isPointOnLine(point))){
                    return null;
                }
            }else {
                point = (this.getIntersectionOfNonVerticalsInfinities(this.getTransposed(),other.getTransposed()));
                if (point!=null){
                    point=point.getTransposed();
                }
            }
        }else{
            point=this.getIntersectionOfNonVerticalsInfinities(this,other);
        }
        if (point!=null && this.isPointOnLine(point) && other.isPointOnLine(point)){
            return point;
        }else {
            return null;
        }
    }

    public Point getIntersectionOfNonVerticalsInfinities(Line one,Line other){
        double x;
        Point point;
        if (one.m==other.m){
            if (one.n==other.n){
                x=Math.max(one.getMinX(),other.getMinX());
            }
            else{
            return null;
}
        }else {
            x = (other.n - one.n) / (one.m - other.m);
        }
        point=new Point(x,one.m*x+one.n);

        if (one.isPointOnLine(point) && other.isPointOnLine(point)){
            return point;
        }else {
            return null;
        }

    }
    public boolean isPointOnLine(Point point){
        double epsilon=0.00001;
        boolean X=(this.getMaxX()+epsilon)>= point.getX() && (this.getMinX()-epsilon)<= point.getX();
        boolean Y=(this.getMaxY()+epsilon)>= point.getY() && (this.getMinY()-epsilon)<= point.getY();
        return X&&Y;
    }
    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        return this.start().equals(other.start()) && this.end().equals(other.end());
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect){
        List<Point> collisions= rect.intersectionPoints(this);
        return this.start.getClosestCollisionPoint(collisions);
    }
}

