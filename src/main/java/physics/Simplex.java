package physics;

import renderer.Display;
import renderer.point.PointConverter;
import renderer.point.Vector;

import java.awt.*;

//this design is taken from a blog called Winter's Blog, in sources
public class Simplex {
    //Simplex of a 3d shape only have 4 points
    Vector[] vertices = new Vector[]{Vector.zero,Vector.zero,Vector.zero,Vector.zero};
    int size = 0;
    public Simplex(Vector start){
        this.vertices[0] = start;
        size++;
    }

    //pushes off the oldest vertex
    public void addVertex(Vector newVertex){
        vertices = new Vector[]{newVertex, vertices[0], vertices[1], vertices[2]};
        if(size < 4) size++;
    }

    public void render(Graphics g){
        int FOCAL = 1800;
        for(Vector v : vertices){
            int y2d = (int) ((FOCAL * v.z /(PointConverter.Dist - (v.x - FOCAL))) + Display.HEIGHT/2);
            int x2d = (int) ((FOCAL * v.y/(PointConverter.Dist - (v.x - FOCAL))) + Display.WIDTH/2);
            g.drawOval(x2d,y2d, 10,10);
        }

    }
}
