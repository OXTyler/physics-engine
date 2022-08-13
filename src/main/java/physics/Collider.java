package physics;

import renderer.Display;
import renderer.point.Point;
import renderer.point.Vector;
import renderer.shapes.Polygon;

import java.util.ArrayList;

public class Collider {
    ArrayList<Vector> vertexes;
    Collider(Polygon... polys){
        for(Polygon poly : polys){
            for(Point point : poly.getPoints()){
                vertexes.add(new Vector(Display.origin, point));
            }
        }
    }

    public Vector getFurthest(Vector dir) {
        double magnitude;
        Vector furthest;
        furthest = vertexes.get(0);
        magnitude = Vector.dot(furthest, dir);
        for(Vector vector : vertexes){
            if(Vector.dot(vector, dir) > magnitude){
                magnitude = Vector.dot(vector, dir);
                furthest = vector;
            }
        }
        return furthest;
    }
}
