package physics;

import renderer.Display;
import renderer.point.Point;
import renderer.point.Vector;

//will use GJK algo in order to determine if a collision occurs
//does so through 3 cases that work on each other, line, triangle, tetrahedron
//code adapted from winter.dev code in sources
public class CollisionManager {
    public Simplex[] simplex;
    public CollisionManager(){

    };
    //this is what is actually used, will use 2 helper functions, triangle then line
    public boolean isCollide(Collider c1, Collider c2){
        Vector[] dir = new Vector[]{new Vector(1,0,0)};
        //used to create a point to start the simplex
        Vector sup = support(dir[0], c1, c2);
        this.simplex = new Simplex[]{new Simplex(sup)};
        dir[0] = Vector.inverse(sup);
        while (true){
            sup = support(dir[0], c1, c2);
            if(Vector.dot(sup,dir[0]) <= 0)
                return false;
            simplex[0].addVertex(sup);
            if(nextSimplex(simplex, dir)){
                return true;
            }
        }
    }

    private static boolean nextSimplex(Simplex[] simplex, Vector... direction){
        switch (simplex[0].size){
            case 2: return line(simplex, direction);
            case 3: return triangle(simplex, direction);
            case 4: return tetrahedron(simplex, direction);
        }

        return false;
    }

    //these 3 cases work by checking the region origin can be in, if it can be outside the
    //simplex, return false

    //vector array used to pass by reference, will only ever be size 1
    private static boolean line(Simplex[] simplex, Vector... direction){
        Vector a = simplex[0].vertices[0];
        Vector b = simplex[0].vertices[1];

        Vector ab = Vector.sub(b,a); //vector from a -> b
        Vector ao = Vector.inverse(a); //vector from a -> origin

        if(Vector.dot(ab, ao) > 0) {
            direction[0] = Vector.cross(Vector.cross(ab,ao), ab);
        }
        else {
            //try again
            simplex[0].vertices = new Vector[]{a, Vector.zero,Vector.zero,Vector.zero};
            simplex[0].size = 1;
            direction[0] = ao;
        }

        return false;
    }

    private static boolean triangle(Simplex[] simplex, Vector... direction){
        Vector a = simplex[0].vertices[0];
        Vector b = simplex[0].vertices[1];
        Vector c = simplex[0].vertices[2];

        Vector ab = Vector.sub(b,a);
        Vector ac = Vector.sub(c,a);
        Vector ao = Vector.inverse(a);

        Vector abc = Vector.cross(ab,ac);

        if(Vector.dot(Vector.cross(abc,ac),ao) > 0){
            if(Vector.dot(ac,ao) > 0){
                simplex[0].vertices = new Vector[]{a,c,Vector.zero, Vector.zero};
                simplex[0].size = 2;
                direction[0] = Vector.cross(Vector.cross(ac,ao),ac);
            }
            else {
                simplex[0].vertices = new Vector[]{a,b,Vector.zero,Vector.zero};
                simplex[0].size = 2;
                return line(simplex,direction);
            }
        }
        else {
            if(Vector.dot(Vector.cross(ab,abc),ao) > 0){
                simplex[0].vertices = new Vector[]{a,b,Vector.zero,Vector.zero};
                simplex[0].size = 2;
                return line(simplex, direction);
            }
            else {
                if (Vector.dot(abc,ao) > 0){
                    direction[0] = abc;
                }
                else {
                    simplex[0].vertices = new Vector[] {a,c,b, Vector.zero};
                    simplex[0].size = 3;
                    direction[0] = Vector.inverse(abc);
                }
            }
        }
        return false;
    }

    private static boolean tetrahedron(Simplex[] simplex, Vector... direction){
        Vector a = simplex[0].vertices[0];
        Vector b = simplex[0].vertices[1];
        Vector c = simplex[0].vertices[2];
        Vector d = simplex[0].vertices[3];

        Vector ab = Vector.sub(b,a);
        Vector ac = Vector.sub(c,a);
        Vector ad = Vector.sub(d,a);
        Vector ao = Vector.inverse(a);

        Vector abc = Vector.cross(ab,ac);
        Vector acd = Vector.cross(ac,ad);
        Vector adb = Vector.cross(ad,ab);

        if(Vector.dot(abc,ao) > 0){
            simplex[0].vertices = new Vector[]{a,b,c,Vector.zero};
            simplex[0].size = 3;
            return triangle(simplex, direction);
        }
        if(Vector.dot(acd,ao) > 0){
            simplex[0].vertices = new Vector[]{a,c,d,Vector.zero};
            simplex[0].size = 3;
            return triangle(simplex,direction);
        }
        if(Vector.dot(adb,ao) > 0){
            simplex[0].vertices = new Vector[]{a,d,b,Vector.zero};
            simplex[0].size = 3;
            return triangle(simplex,direction);
        }

        return true;
    }

    //finds the point in the minkowski difference for a given direction
    private static Vector support(Vector dir, Collider c1, Collider c2){
        return Vector.sub(c1.getFurthest(dir), c2.getFurthest(Vector.inverse(dir)));
    }
}
