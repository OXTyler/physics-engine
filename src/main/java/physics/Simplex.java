package physics;

import renderer.point.Vector;

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

    public void removeVertex(int index){
        if(vertices.length == 0 || index >= vertices.length) return;
        Vector[] newVertices = new Vector[this.vertices.length - 1];
        for(int i = 0; i < index; i++){
            newVertices[i] = this.vertices[i];
        }
        for(int i = index + 1; i < this.vertices.length; i++){
            newVertices[i-1] = this.vertices[i];
        }
        this.vertices = newVertices;
        size --;
    }

}
