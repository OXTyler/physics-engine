package renderer.entity;

import physics.Collider;
import renderer.Camera;
import renderer.Display;
import renderer.input.ControlType;
import renderer.point.Point;
import renderer.point.Vector;
import renderer.shapes.Polyhedron;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Entity implements IEntity{

    private List<Polyhedron> polys;
    private Collider collider;
    private Vector center;
    int xSum = 0,ySum = 0,zSum = 0;
    public Entity(List<Polyhedron> polys) {
        this.polys = polys;
    }

    public Entity(Polyhedron poly){
        polys = new ArrayList<>();
       this.polys.add(poly);
       this.center = poly.getCenter();
       this.collider = new Collider(polys);
    }

    public void addShape(Polyhedron poly){
        this.polys.add(poly);
        xSum += poly.getCenter().x;
        ySum += poly.getCenter().y;
        zSum += poly.getCenter().z;
        this.center.x = xSum / polys.size();
        this.center.y = ySum / polys.size();
        this.center.z = zSum / polys.size();
    }

    @Override
    public void render(Graphics graphics) {
        for(Polyhedron poly: this.polys){
            poly.render(graphics);
        }
    }
    //TODO fix rotation to account for camera
    @Override
    public void rotate(ControlType mode, boolean CW, double xDegrees, double yDegrees, double zDegrees, Vector lightVector) {
        for(Polyhedron poly: this.polys){
            poly.rotate(mode, CW, xDegrees, yDegrees, zDegrees, lightVector);
        }
        if(mode == ControlType.Object)
            this.collider.rotate(CW, xDegrees, yDegrees, zDegrees, this.collider.getCenter());
        else if(mode == ControlType.Camera){
            collider.rotate(CW, xDegrees, yDegrees, zDegrees, new Vector(Display.origin,Camera.cameraCoords));
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
