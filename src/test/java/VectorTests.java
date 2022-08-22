import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import renderer.point.Vector;

public class VectorTests {
    @Test
    void CrossProduct() {
        Vector v1 = new Vector(1,1,1);
        Vector v2 = new Vector(0,1,1);
        Vector ans = Vector.cross(v1,v2);
        Vector right = new Vector(0,-1,1);
        Assertions.assertEquals(ans.x,right.x);
        Assertions.assertEquals(ans.y,right.y);
        Assertions.assertEquals(ans.z,right.z);
    }

    @Test
    void DotProduct() {
        Vector v1 = new Vector(1,1,1);
        Vector v2 = new Vector(0,1,1);
        double ans = Vector.dot(v1,v2);
        Assertions.assertEquals(ans, 2);

    }

    @Test
    void Sub() {
        Vector v1 = new Vector(1,1,1);
        Vector v2 = new Vector(0,1,1);
        Vector ans = Vector.sub(v1,v2);
        Vector right = new Vector(1,0,0);
        Assertions.assertEquals(ans.x,right.x);
        Assertions.assertEquals(ans.y,right.y);
        Assertions.assertEquals(ans.z,right.z);
    }

    @Test
    void Add() {
        Vector v1 = new Vector(1,1,1);
        Vector v2 = new Vector(0,1,1);
        Vector ans = Vector.add(v1,v2);
        Vector right = new Vector(1,2,2);
        Assertions.assertEquals(ans.x,right.x);
        Assertions.assertEquals(ans.y,right.y);
        Assertions.assertEquals(ans.z,right.z);
    }

    @Test
    void Inverse(){
        Vector a = new Vector(1,1,1);
        Vector ans = new Vector(-1,-1,-1);
        a = Vector.inverse(a);
        Assertions.assertEquals(a.x,ans.x);
        Assertions.assertEquals(a.y,ans.y);
        Assertions.assertEquals(a.z,ans.z);

    }
}
