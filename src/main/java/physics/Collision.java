package physics;

import renderer.entity.EntityManager;
import renderer.point.Point;
import renderer.point.Vector;
import renderer.shapes.Polygon;
import renderer.shapes.Polyhedron;

import java.awt.*;
import java.util.ArrayList;

//TODO fix to work with simplex class and Collider
public class Collision {
    //will use GJK algo in order to determine if a collision occurs
    public static boolean isCollide(Polyhedron p1, Polyhedron p2){
        Vector dir = Vector.normalize(Vector.sub(p1.getCenter(), p2.getCenter()));
        //used to create a point to start the simplex
        Vector a = support(dir, p1, p2);
        Point start = new Point(a.x,a.y,a.z);
        ArrayList<Polygon> polys = new ArrayList<Polygon>();
        polys.add(new Polygon(Color.BLUE, EntityManager.ORIGIN, start));
        Polyhedron simplex = new Polyhedron(Color.BLUE, EntityManager.ORIGIN, polys);
        return true;
    }

    //finds the point in the minkowski difference for a given direction
    public static Vector support(Vector dir, Polyhedron p1, Polyhedron p2){
        return Vector.sub(p1.getCollider().getFurthest(dir), p2.getCollider().getFurthest(Vector.inverse(dir)));
    }
}
