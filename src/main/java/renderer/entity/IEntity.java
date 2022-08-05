package renderer.entity;

import renderer.point.Vector;

import java.awt.*;

public interface IEntity {
    void render(Graphics g);

    void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees, Vector lightVector);

    void setLighting(Vector lightVector);

    void translate(double x, double y, double z);
}
