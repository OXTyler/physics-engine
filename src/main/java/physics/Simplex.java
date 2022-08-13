package physics;

import renderer.point.Vector;

public class Simplex {
    Vector[] vertexes;
    public Simplex(Vector... vectors){
        this.vertexes = new Vector[vectors.length];
        for(int i = 0; i < vectors.length; i++){
            this.vertexes[i] = vectors[i];
        }
    }

    public void addVertex(Vector newVertex){
        Vector[] newVertexs = new Vector[vertexes.length + 1];
        for(int i = 0; i < vertexes.length; i++){
            newVertexs[i] = vertexes[i];
        }
        newVertexs[vertexes.length] = newVertex;
        vertexes = newVertexs;
    }
    public void replaceVertex(int index, Vector newVertex){
        this.vertexes[index] = newVertex;
    }

}
