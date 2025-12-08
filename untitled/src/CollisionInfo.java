public class CollisionInfo {
    // the point at which the collision occurs.
    private Point collision_point;

    private Collidable collision_object;

    public CollisionInfo(Point collision_point,Collidable collision_object){
        this.collision_point=collision_point;
        this.collision_object=collision_object;
    }


    public Point collisionPoint(){
        return this.collision_point;
    }

    // the collidable object involved in the collision.
    public Collidable collisionObject(){
        return this.collision_object;
    }

}