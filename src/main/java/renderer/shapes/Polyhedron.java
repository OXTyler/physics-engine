package renderer.shapes;

import renderer.Camera;
import renderer.Display;
import renderer.point.Point;
import renderer.point.Vector;

import java.awt.*;
import java.util.ArrayList;

public class Polyhedron {
    private Polygon[] polygons;
    private Color color;
    private double[] center;

    public Polyhedron(Color color, double[] center, Polygon... polygons){
        this.color = color;
        this.center = center;
        this.polygons = new Polygon[polygons.length];
        for(int i = 0; i < polygons.length; i++){
            this.polygons[i] = new Polygon(polygons[i]);
        }
        this.sortPolygons();
    }

    public  Polyhedron(double[] center, ArrayList<Polygon> polys){
        this.color = Color.BLUE;
        this.center = center;
        System.out.println(polys.size());
        this.polygons = new Polygon[polys.size()];
        for(int i = 0; i < polygons.length; i++){
            this.polygons[i] = new Polygon(Color.BLUE, polys.get(i).getPoints());
        }
        this.sortPolygons();
    }
    //renders out all polygons for the given Polyhedron
    public void render(Graphics graphics){

        for(Polygon poly: this.polygons){
            Vector viewDir = new Vector(Display.origin, new Point(poly.getMiddle()[0],poly.getMiddle()[1], poly.getMiddle()[2]));
            if(Vector.normalize(poly.getNormal()).x < 0){
                poly.render(graphics);
            }
        }
    }
    //Used to rotate object
    public void rotate(boolean CW, double xDeg, double yDeg, double zDeg, Vector lightVector){
        for(Polygon poly : polygons){
            poly.rotate(CW, xDeg, yDeg, zDeg, lightVector, center);
        }
        this.sortPolygons();
    }

    public void translate(double x, double y, double z) {
        center[0] += x;
        center[1] += y;
        center[2] += z;
        for(Polygon poly : polygons){
            poly.translate(x,y,z);
        }
    }

    //sorts the faces so the object renders with the right faces in the front TODO how faces are rendered first needs to be improved
    private void sortPolygons(){
        Polygon.sortPolygons(this.polygons);
    }

    private void setPolygonColor(){
        for(Polygon poly : this.polygons){
            poly.setColor(this.color);
        }
    }

    public void setLighting(Vector lightVector) {
        for(Polygon poly : polygons){
            poly.setLighting(lightVector);
        }
    }

}
