import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {

    private static GameEnvironment instance=null;
    private List<Collidable> collidables = new ArrayList<Collidable>();
    public final Color background_color=Color.white;
    public final Color bounds_color=Color.black;

    public final int borderWidth=5;


    public static GameEnvironment getInstance() {
        if (GameEnvironment.instance==null){
            GameEnvironment.instance=new GameEnvironment();
        }
        return GameEnvironment.instance;
    }

    private GameEnvironment(){
        this.collidables=new ArrayList<>();
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c){
        this.collidables.add(c);
    }


    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory)
    {
        ArrayList<CollisionInfo> collisions=new ArrayList<>();


        for (Collidable collidable: this.collidables){
            Point collision=trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collision!=null){
                collisions.add(new CollisionInfo(collision,collidable));
            }
        }
        return trajectory.start().getClosestCollision(collisions);
    }


}