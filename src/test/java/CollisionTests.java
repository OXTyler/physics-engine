import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import physics.CollisionManager;
import renderer.entity.EntityManager;
import renderer.entity.builder.EntityBuilder;
import renderer.point.Vector;

import java.io.IOException;

public class CollisionTests {
    //tests the collision of 2 cubes, the most simple shape to calculate
    @Test
    void cubeCollision() throws IOException {
        EntityManager em = new EntityManager();
        CollisionManager collisionManager = new CollisionManager();
        em.addEntity(EntityBuilder.createCube(100,0,0,30));
        em.addEntity(EntityBuilder.createCube(100,0,0,0));
        Assertions.assertEquals(true, collisionManager.isCollide(em.getEntitiy(0).getCollider(), em.getEntitiy(1).getCollider()));
    }

    @Test
    void findFurthest(){
        EntityManager em = new EntityManager();
        em.addEntity(EntityBuilder.createCube(100,0,0,0));
        Vector fur = em.getEntitiy(0).getCollider().getFurthest(new Vector(1,1,1));
        Vector ans = new Vector(50,50,50);
        Assertions.assertEquals(ans.x, fur.x);
        Assertions.assertEquals(ans.y,fur.y);
        Assertions.assertEquals(ans.z,fur.z);
    }
}
