package renderer.shapes;

import renderer.point.Point;
import renderer.point.PointConverter;
import renderer.point.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Polygon {
    private static final double AMBIENT_LIGHTING = 0.1;


    private Point[] points;
    private Color baseColor, lightingColor;
    public Polygon(Color color, Point... points){
        this.points = new Point[points.length];
        this.baseColor = this.lightingColor = color;
        for(int i = 0; i < points.length; i++){
            this.points[i] = new Point(points[i].x, points[i].y, points[i].z);
        }
    }

    public Polygon(Polygon oldPoly){
        this.points = new Point[oldPoly.getPoints().length];
        this.baseColor = this.lightingColor = oldPoly.getColor();
        for(int i = 0; i < points.length; i++){
            this.points[i] = new Point(oldPoly.getPoints()[i].x, oldPoly.getPoints()[i].y, oldPoly.getPoints()[i].z);
        }
    }
    //renders out the actual face of the polygon
    java.awt.Polygon poly;
    java.awt.Point temp;
    public void render(Graphics graphics) {
        poly = new java.awt.Polygon();
        for(int i = 0; i < this.points.length; i++){
            temp = PointConverter.convertPoint(points[i]);
            poly.addPoint(temp.x, temp.y);
        }

        graphics.setColor(this.lightingColor);
        graphics.fillPolygon(poly);
    }
    //gets the normal vector of the plane
    public Vector getNormal(){
        Vector v1 = new Vector(this.points[0],this.points[1]);
        Vector v2 = new Vector(this.points[0], this.points[points.length - 1]);
        return Vector.cross(Vector.normalize(v1), Vector.normalize(v2));
    }

    public void rotate(boolean CW, double xDeg, double yDeg, double zDeg, Vector light, Point center){
        for(Point point : this.points){
            PointConverter.rotateAxisX(point, CW, xDeg, center);
            PointConverter.rotateAxisY(point, CW, yDeg, center);
            PointConverter.rotateAxisZ(point, CW, zDeg, center);
        }
        this.setLighting(light);
    }

    public void translate(double x, double y, double z) {
        for(Point point : points){
            point.x += x;
            point.y += y;
            point.z += z;
        }
    }

    //this is only used for finding simplex, so order of points doesnt matter when used, it will add to end
    public void addPoint(Point newPoint){
        Point[] newPoints = new Point[points.length + 1];
        for(int i = 0; i < points.length; i++){
            newPoints[i] = points[i];
        }
        newPoints[points.length] = newPoint;
        points = newPoints;
    }
    //function used for modifying simplex, it will remove a point at a given index to modify shape
    public void removePoint(int index){
        if(index > points.length || index < 0) return;
        Point[] newPoints = new Point[points.length - 1];
        for(int i = 0; i < index; i++){
            newPoints[i] = points[i];
        }
        for(int i = index + 1; i < points.length; i++){
            newPoints[i-1] = points[i];
        }
        points = newPoints;
    }

    private void updateLightingColor(double lightRatio) {
        int red = (int) (this.baseColor.getRed() * lightRatio);
        int green = (int) (this.baseColor.getGreen() * lightRatio);
        int blue = (int) (this.baseColor.getBlue() * lightRatio);
        this.lightingColor = new Color(red,green,blue);
    }


    public Point getMiddle(){
        double xSum = 0;
        double ySum = 0;
        double zSum = 0;
        for(Point point : this.points){
            xSum += point.x;
            ySum += point.y;
            zSum += point.z;
        }
        return new Point(xSum/this.points.length, ySum/this.points.length, zSum/this.points.length);
    }

    public Point[] getPoints() {
        return this.points;
    }
    public Color getColor() {
        return this.baseColor;
    }
    public void setColor(Color color){
        this.baseColor = color;
    }
    public void setLighting(Vector lightVector){
        if(this.points.length < 3){
            return;
        }
        Vector v1 = new Vector(this.points[0], this.points[1]);
        Vector v2 = new Vector(this.points[0], this.points[2]);
        Vector normal = Vector.normalize(Vector.cross(v2, v1));
        double dot = Vector.dot(normal, lightVector);
        double sign = dot < 0 ? -1 : 1;
        dot = sign* (dot*dot);
        dot = (dot + 1) / 2 * 0.8;

        double lightRatio = Math.min(Math.max(AMBIENT_LIGHTING + dot, 0), 1);
        this.updateLightingColor(lightRatio);
    }

}