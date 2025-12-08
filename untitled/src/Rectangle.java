import java.util.ArrayList;

public class Rectangle {
    private Point upperLeft;
    private Point bottomRight;
    ArrayList<Line> edges;


    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height){
        this.upperLeft=upperLeft;
        this.bottomRight=new Point(upperLeft.getX()+width, upperLeft.getY()+height);
        this.edges=new ArrayList<>();

        //top edge
        edges.add(new Line(upperLeft.getX(),upperLeft.getY(),bottomRight.getX(),upperLeft.getY()));

        //bottom edge
        edges.add(new Line(upperLeft.getX(),bottomRight.getY(),bottomRight.getX(),bottomRight.getY()));

        //left edge
        edges.add(new Line(upperLeft.getX(),upperLeft.getY(),upperLeft.getX(),bottomRight.getY()));

        //right edge
        edges.add(new Line(bottomRight.getX(),upperLeft.getY(),bottomRight.getX(),bottomRight.getY()));
    }

    public Rectangle copy(){
        return new Rectangle(this.upperLeft,this.getWidth(),this.getHeight());
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line){
        ArrayList<Point> collisions=new ArrayList<>();


        for (Line edge : this.edges){
            Point collision=line.intersectionWith(edge);
            if (collision!=null){
                collisions.add(collision);
            }
        }
        return collisions;
    }

    // Return the width and height of the rectangle
    public double getWidth(){

        return bottomRight.getX()-upperLeft.getX();
    }
    public double getHeight(){
        return bottomRight.getY()-upperLeft.getY();

    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft(){
        return this.upperLeft;
    }
    public Point getBottomRight(){
        return this.bottomRight;
    }
    public boolean isPointOnHorizontalEdge(Point point){

        return (Math.round(point.getY())==Math.round(upperLeft.getY()) || Math.round(point.getY())==Math.round(bottomRight.getIntY()));
    }
    public boolean isPointOnVerticalEdge(Point point){
        return (Math.round(point.getX())==Math.round(upperLeft.getX()) || Math.round(point.getX())==Math.round(bottomRight.getX()));
    }
}