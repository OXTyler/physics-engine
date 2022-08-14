package renderer.point;

public class Vector {
    public double x, y, z;
    public static Vector zero = new Vector(0,0,0);
    public Vector(){
        this.x = this.y = this.z = 0;
    }
    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector(Point p1, Point p2){
        this.x = p2.x - p1.x;
        this.y = p2.y - p1.y;
        this.z = p2.z - p1.z;
    }

    public static Vector normalize(Vector v) {
        double mag = Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
        return new Vector(v.x/mag, v.y/mag, v.z/mag);
    }
    public static Vector add(Vector v1, Vector v2){
        return new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }
    public static Vector sub(Vector v1, Vector v2){
        return new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }
    public static double dot(Vector v1, Vector v2){
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }
    public static Vector cross(Vector v1, Vector v2){
        return new Vector(v1.y * v2.z - v1.z * v2.y,
                          v1.z * v2.x - v1.x * v2.z,
                          v1.x * v2.y - v1.y * v2.x);
    }

    public static Vector inverse(Vector v) {
        v.x *= -1;
        v.y *= -1;
        v.z *= -1;
        return v;
    }
}
