package physics;

import renderer.Display;
import renderer.point.Point;
import renderer.point.PointConverter;
import renderer.point.Vector;
import renderer.shapes.Polygon;
import renderer.shapes.Polyhedron;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Collider {
    ArrayList<Vector> vertices;
    public Collider(List<Polyhedron> polys){
        vertices = new ArrayList<>();
        Set<Vector> vertSet = new HashSet<Vector>();
        for(Polyhedron poly : polys){
            for(Polygon polygon: poly.getPolygons()){
                for(Point point : polygon.getPoints()){
                    Vector v = new Vector(Display.origin, point);
                    vertSet.add(v);
                }
            }
        }
        for (Vector v : vertSet){
        }
        vertices = new ArrayList<Vector>(vertSet);
        System.out.println(vertices);
    }

    public Vector getFurthest(Vector dir) {
        double magnitude;
        Vector furthest = this.vertices.get(0);
        magnitude = -Double.MAX_VALUE;
        for(Vector vertex : this.vertices){
            if(Vector.dot(vertex, dir) > magnitude){
                magnitude = Vector.dot(vertex, dir);
                furthest = vertex;
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

    public void rotate(boolean CW, double xDeg, double yDeg, double zDeg, Vector center){
        for(Vector vertex : this.vertices){
            Vector.rotateAxisX(vertex, CW, xDeg, center);
            Vector.rotateAxisY(vertex, CW, yDeg, center);
            Vector.rotateAxisZ(vertex, CW, zDeg, center);
        }
    }

    public Vector getCenter(){
        double xSum = 0;
        double ySum = 0;
        double zSum = 0;
        Vector center = new Vector();
        for(int i = 0; i < vertices.size(); i++){
            xSum += vertices.get(i).x;
            ySum += vertices.get(i).y;
            zSum += vertices.get(i).z;
        }
        center.x = xSum / vertices.size();
        center.y = ySum / vertices.size();
        center.z = zSum / vertices.size();
        return center;
    }
    //for Debugging
    public void render(Graphics g){
        int FOCAL = 1800;
        for(Vector v : vertices){
            int y2d = (int) ((FOCAL * v.z /(PointConverter.Dist - (v.x - FOCAL))) + Display.HEIGHT/2);
            int x2d = (int) ((FOCAL * v.y/(PointConverter.Dist - (v.x - FOCAL))) + Display.WIDTH/2);
            g.drawOval(x2d,y2d, 10,10);
        }

    }
}
