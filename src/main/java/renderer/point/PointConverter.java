package renderer.point;

import renderer.Display;

public class PointConverter {
    //TODO create a function to scale objects
    private static double scale = 1;
    public static int Dist = 10;

    //converts a 3D point to a 2D point
    public static java.awt.Point convertPoint(Point point3D){
        //not entirely sure what the constant does, pretty sure its distance of focal point behind screen, smaller val
        //corresponds to more dramatic depth difference
        int FOCAL = 850;
        //using similar triangles, it finds the 2d points compared to 3d space, then centers them using WIDTH and HEIGHT
        int y2d = (int) ((FOCAL * point3D.z /(Dist - (point3D.x - FOCAL))) + Display.HEIGHT/2);
        int x2d = (int) ((FOCAL * point3D.y/(Dist - (point3D.x - FOCAL))) + Display.WIDTH/2);

        return  new java.awt.Point(x2d, y2d);
    }

    public static void rotateAxisX(Point point, Boolean CW, double degrees, Point center){
        double radius = Math.sqrt((point.y - center.y) * (point.y - center.y) +  (point.z - center.z) * (point.z -center.z));
        double theta = Math.atan2(point.z-center.z, point.y-center.y);
        theta += 2*Math.PI/360*degrees*(CW ? -1: 1);
        point.z = radius * Math.sin(theta) + center.z;
        point.y = radius * Math.cos(theta) + center.y;
    }
    public static void rotateAxisY(Point point, Boolean CW, double degrees, Point center){
        double radius = Math.sqrt((point.x-center.x) * (point.x-center.x) +  (point.z - center.z) * (point.z - center.z));
        double theta = Math.atan2(point.x - center.x, point.z - center.z);
        theta += 2*Math.PI/360*degrees*(CW ? -1: 1);
        point.x = radius * Math.sin(theta) + center.x;
        point.z = radius * Math.cos(theta) + center.z;
    }
    public static void rotateAxisZ(Point point, Boolean CW, double degrees, Point center){
        double radius = Math.sqrt((point.y - center.y) * (point.y - center.y) +  (point.x - center.x) * (point.x - center.x));
        double theta = Math.atan2(point.y -center.y, point.x - center.x);
        theta += 2*Math.PI/360*degrees*(CW ? -1: 1);
        point.y = radius * Math.sin(theta) + center.y;
        point.x = radius * Math.cos(theta) + center.x;
    }
}
