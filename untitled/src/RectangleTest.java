import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void Test_intersectionPoints() { //looks ok
        Rectangle rectangle = new Rectangle(new Point(50,50),100,50);
        Line vertical_no=new Line(0,0,0,10);
        Line vertical_not_long=new Line(75,0,75,25);
        Line vertical_OnePoint=new Line(75,0,75,75);
        Line vertical_twoPoints=new Line(75,0,75,110);
        Line vertical_on_edge=new Line(50,25,50,75);
        Line get_in_corner=new Line(25,25,75,75);

        List<Point> result;
        result=rectangle.intersectionPoints(vertical_no);
        assertEquals(0, result.size());

        result=rectangle.intersectionPoints(vertical_not_long);
        assertEquals(0, result.size());

        result=rectangle.intersectionPoints(vertical_OnePoint);
        assertEquals(1, result.size());

        result=rectangle.intersectionPoints(vertical_twoPoints);
        assertEquals(2, result.size());


        //return 2 of the same point
        result=rectangle.intersectionPoints(vertical_on_edge);

        //return 2 of the same point
        result=rectangle.intersectionPoints(get_in_corner);

        int a=0;

    }

    @Test
    public void Test_getClosestCollisionPoint(){
        Point start=new Point(50,50);

        ArrayList<Point> points;

        points=new ArrayList<>();
        assertEquals(null, start.getClosestCollisionPoint(points));

        points.add(new Point(10,10));
        assertEquals(points.getFirst(), start.getClosestCollisionPoint(points));

        points.add(new Point(100,100));
        assertEquals(points.getFirst(), start.getClosestCollisionPoint(points));

        points.add(new Point(50,50));
        assertEquals(points.getLast(), start.getClosestCollisionPoint(points));


    }

    @Test
    public void test_closestIntersectionToStartOfLine(){
        Line line=new Line(0,0,60,75);

        Rectangle rect = new Rectangle(new Point(30,30),10,10);
        Point Result=line.closestIntersectionToStartOfLine(rect);
        int a=0;
    }

    @Test
    public void test_checkIFCollide(){
        Line line1= new Line(0,0,10.1,0);
        Line line2= new Line(10.9,0,12,0);

        assertEquals(null,line1.intersectionWith(line2));

        line1= new Line(0,50,10,50);
        line2= new Line(5,0,5,50);
        Point result=line1.intersectionWith(line2);
        assertEquals("(5.000000,50.000000)",result.toString());

        line1= new Line(0,50,10,50);
        line2= new Line(5,0,5,50.1);
        result=line1.intersectionWith(line2);
        assertEquals(null,result);

    }

    @Test
    public void test_dealWithCollisions(){
        //movement is line

        Velocity start_velocity=new Velocity(1,1);
        Ball ball=new Ball(50,50,5, Color.blue);

        ArrayList<Block> blocks=new ArrayList<>();

        blocks.add(new Block(0,0,10,10));


    }

    }

