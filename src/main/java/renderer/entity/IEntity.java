package renderer.entity;

import physics.Collider;
import renderer.input.ControlType;
import renderer.point.Vector;
import renderer.shapes.Polyhedron;

import java.awt.*;
import java.util.List;

public interface IEntity {
    void render(Graphics g);

    void rotate(ControlType mode, boolean CW, double xDegrees, double yDegrees, double zDegrees, Vector lightVector);

    void setLighting(Vector lightVector);

    List<Polyhedron> getPolyhedrons();

    void translate(double x, double y, double z);

    Collider getCollider();
}
