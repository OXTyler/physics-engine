package renderer.point;

public class Vector {
    public double x, y, z;
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
    public Vector add(Vector vector2){
        return new Vector(this.x + vector2.x, this.y + vector2.y, this.z + vector2.z);
    }
    public Vector sub(Vector vector2){
        return new Vector(this.x - vector2.x, this.y - vector2.y, this.z - vector2.z);
    }
    public static double dot(Vector v1, Vector v2){
        return v1.x * v2.x + v1.y * v1.y + v1.z * v2.z;
    }
    public static Vector cross(Vector v1, Vector v2){
        return new Vector(v1.y * v2.z - v1.z * v2.y,
                          v1.z * v2.x - v1.x * v2.z,
                          v1.x * v2.y - v1.y * v2.x);
    }
}
