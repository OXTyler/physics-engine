package physics;

import renderer.Display;
import renderer.point.Point;
import renderer.point.Vector;
import renderer.shapes.Polygon;
import renderer.shapes.Polyhedron;

import java.util.ArrayList;
import java.util.List;

public class Collider {
    ArrayList<Vector> vertices;
    public Collider(List<Polyhedron> polys){
        vertices = new ArrayList<>();
        for(Polyhedron poly : polys){
            for(Polygon polygon: poly.getPolygons()){
                for(Point point : polygon.getPoints()){
                    vertices.add(new Vector(Display.origin, point));
                }
            }
        }
    }

    public Vector getFurthest(Vector dir) {
        double magnitude;
        Vector furthest;
        furthest = vertices.get(0);
        magnitude = Vector.dot(furthest, dir);
        for(Vector vector : vertices){
            if(Vector.dot(vector, dir) > magnitude){
                magnitude = Vector.dot(vector, dir);
                furthest = vector;
            }
        }
        return furthest;
    }

    public void translate(double x, double y, double z) {
        for(Vector vertex : vertices){
            vertex.x += x;
            vertex.y += y;
            vertex.z += z;
        }
    }

    public Vector getCenter(){
        int xSum = 0;
        int ySum = 0;
        int zSum = 0;
        Point center = new Point();
        for(int i = 0; i < vertices.size(); i++){
            xSum += vertices.get(i).x;
            ySum += vertices.get(i).y;
            zSum += vertices.get(i).z;
        }
        center.x = xSum / vertices.size();
        center.y = ySum / vertices.size();
        center.z = zSum / vertices.size();
        return new Vector(center.x, center.y, center.z);
    }
}
