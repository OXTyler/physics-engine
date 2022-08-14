package renderer.entity;

import physics.Collider;
import renderer.input.ControlType;
import renderer.point.Vector;
import renderer.shapes.Polyhedron;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Entity implements IEntity{

    private List<Polyhedron> polys;
    private Collider collider;

    public Entity(List<Polyhedron> polys) {
        this.polys = polys;
    }

    public Entity(Polyhedron poly){
        polys = new ArrayList<>();
       this.polys.add(poly);
       this.collider = new Collider(polys);
    }

    public void addShape(Polyhedron poly){
        this.polys.add(poly);
    }

    @Override
    public void render(Graphics graphics) {
        for(Polyhedron poly: this.polys){
            poly.render(graphics);
        }
    }

    @Override
    public void rotate(ControlType mode, boolean CW, double xDegrees, double yDegrees, double zDegrees, Vector lightVector) {
        for(Polyhedron poly: this.polys){
            poly.rotate(mode, CW, xDegrees, yDegrees, zDegrees, lightVector);
        }
    }

    @Override
    public void translate(double x, double y, double z) {
        for(Polyhedron poly : polys){
            poly.translate(x,y,z);
        }
        collider.translate(x,y,z);
    }

    @Override
    public void setLighting(Vector lightVector) {
        for(Polyhedron poly : polys){
            poly.setLighting(lightVector);
        }
    }
    @Override
    public Collider getCollider(){
        return collider;
    }

    @Override
    public List<Polyhedron> getPolyhedrons(){
        return this.polys;
    }

}
