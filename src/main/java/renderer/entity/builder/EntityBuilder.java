package renderer.entity.builder;

import renderer.STLParser;
import renderer.entity.Entity;
import renderer.entity.IEntity;
import renderer.point.Point;
import renderer.shapes.Polygon;
import renderer.shapes.Polyhedron;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EntityBuilder {
    //size is side length of cube
    public static IEntity createCube(int size, double centerX, double centerY, double centerZ){

        //creates the 8 points for the cube, then initializes them as polygons (in ccw order)

        Point center = new Point(centerX, centerY, centerZ);
        Point p1 = new Point(centerX - size/2, centerY - size/2, centerZ - size/2);
        Point p2 = new Point(centerX - size/2, centerY + size/2, centerZ - size/2);
        Point p3 = new Point(centerX - size/2, centerY + size/2, centerZ + size/2);
        Point p4 = new Point(centerX - size/2, centerY - size/2, centerZ + size/2);
        Point p5 = new Point(centerX + size/2, centerY - size/2, centerZ - size/2);
        Point p6 = new Point(centerX + size/2, centerY + size/2, centerZ - size/2);
        Point p7 = new Point(centerX + size/2, centerY + size/2, centerZ + size/2);
        Point p8 = new Point(centerX + size/2, centerY - size/2, centerZ + size/2);
        //cube
        Polyhedron cube = new Polyhedron(Color.BLUE, center,
                new Polygon(Color.RED,p1,p2,p3,p4),
                new Polygon(Color.BLUE,p6,p5,p8,p7),
                new Polygon(Color.WHITE,p1,p5,p6,p2),
                new Polygon(Color.YELLOW,p1,p4,p8,p5),
                new Polygon(Color.GREEN,p2,p6,p7,p3),
                new Polygon(Color.ORANGE,p4,p3,p7,p8)
        );
        //creates entity as 3D shape
        return new Entity(cube);
    }
    //size is length of diamond
    public static IEntity createDiamond(Color color, double size, double centerX, double centerY, double centerZ){
        List<Polyhedron> polys = new ArrayList<>();
        //number of edges around the diamond
        int edges = 10;
        //sets the factor for the top edge chamfer
        double chamfer = 0.75;
        Point center = new Point(centerX, centerY, centerZ);
        Point bottom = new Point(centerX, centerY, centerZ - size / 3);
        Point[] outerPoints = new Point[edges];
        Point[] innerPoints = new Point[edges];
        for(int i = 0; i < edges; i++){
            double theta = (2*Math.PI / edges) * i;
            //size is used to scale so pos coords are outside of range [0,1]
            double xPos = -Math.sin(theta) * size/1.5;
            double yPos = Math.cos(theta) * size/1.5;
            double zPos = size/2;
            outerPoints[i] = new Point(centerX + xPos, centerY + yPos, centerZ + zPos);
            innerPoints[i] = new Point(centerX + xPos * chamfer, centerY + yPos * chamfer, centerZ + zPos/chamfer);
        }
        Polygon polygons[] = new Polygon[2 * edges + 1];
        //lower triangles
        for(int i = 0; i < edges; i++){
            //modulus is used to loop back around
            polygons[i] = new Polygon(color,outerPoints[i], bottom, outerPoints[(i+1)%edges]);
        }
        //chamfer faces
        for(int i = 0; i < edges; i++){
            //modulus is used to loop back around
            polygons[i+edges] = new Polygon(color,outerPoints[i], outerPoints[(i+1)%edges], innerPoints[(i+1)%edges], innerPoints[i]);
        }
        //top face;
        polygons[2 * edges] = new Polygon(color, innerPoints);

        Polyhedron poly = new Polyhedron(Color.BLUE, center, polygons);
        polys.add(poly);

        return new Entity(polys);
    }
    //size is side length of plane
    public static IEntity createPlane(Color color, int size){
        Point p1 = new Point(size/2, -size/2,0);
        Point p2 = new Point(size/2, size/2, 0);
        Point p3 = new Point(-size/2, size/2, 0);
        Point p4 = new Point(-size/2, -size/2, 0);
        Polygon poly = new Polygon(color, p1,p2,p3,p4);
        Polyhedron polyhedron = new Polyhedron(Color.GREEN, new Point(0,0,0), poly);

        return new Entity(polyhedron);
    }


    //much easier way of loading objects, instead of making them vertex by vertex, just load in a file
    //stlParser was taken from GitHub
    public static IEntity loadSTL(Color color, Path filePath, double centerX, double centerY, double centerZ) throws IOException {
        List<Polyhedron> polys = new ArrayList<>();
        ArrayList<Polygon> poly = (ArrayList<Polygon>) STLParser.parseSTLFile(filePath, centerX, centerY, centerZ);
        Point center = new Point(centerX, centerY, centerZ);
        polys.add(new Polyhedron(Color.BLUE, center, poly));
        return new Entity(polys);
    }
}
