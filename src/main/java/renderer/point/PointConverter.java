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

    public static void rotateAxisX(Point point, Boolean CW, double degrees, double[] center){
        double radius = Math.sqrt((point.y - center[1]) * (point.y - center[1]) +  (point.z - center[2]) * (point.z -center[2]));
        double theta = Math.atan2(point.z-center[2], point.y-center[1]);
        theta += 2*Math.PI/360*degrees*(CW ? -1: 1);
        point.z = radius * Math.sin(theta) + center[2];
        point.y = radius * Math.cos(theta) + center[1];
    }
    public static void rotateAxisY(Point point, Boolean CW, double degrees, double[] center){
        double radius = Math.sqrt((point.x-center[0]) * (point.x-center[0]) +  (point.z - center[2]) * (point.z - center[2]));
        double theta = Math.atan2(point.x - center[0], point.z - center[2]);
        theta += 2*Math.PI/360*degrees*(CW ? -1: 1);
        point.x = radius * Math.sin(theta) + center[0];
        point.z = radius * Math.cos(theta) + center[2];
    }
    public static void rotateAxisZ(Point point, Boolean CW, double degrees, double[] center){
        double radius = Math.sqrt((point.y - center[1]) * (point.y - center[1]) +  (point.x - center[0]) * (point.x - center[0]));
        double theta = Math.atan2(point.y -center[1], point.x - center[0]);
        theta += 2*Math.PI/360*degrees*(CW ? -1: 1);
        point.y = radius * Math.sin(theta) + center[1];
        point.x = radius * Math.cos(theta) + center[0];
    }
}
