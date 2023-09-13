package renderer.entity;

import physics.CollisionManager;
import renderer.entity.builder.EntityBuilder;
import renderer.input.ControlType;
import renderer.point.Point;
import renderer.point.Vector;
import renderer.shapes.Polyhedron;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//manages everything inside the scene, what to spawn and when, and how to render all objects
public class EntityManager {
    public static final Point ORIGIN = new Point(0,0,0);
    private List<IEntity> entities;
    private CollisionManager collisionManager;
    private int initialX, initialY, x, y, xDif, yDif;
    public Vector lightVector = Vector.normalize(new Vector(1,1,1));
    public EntityManager(List<IEntity> entities){
        this.entities = entities;
    }

    public  EntityManager(){
        this.entities = new ArrayList<IEntity>();
        this.collisionManager = new CollisionManager();
    }
    //TODO make a way to spawn objects inside the render
    public void init() throws IOException {
        //this.entities.add(EntityBuilder.createDiamond(Color.CYAN,100, 0 ,0 ,100));
        //this.entities.add(EntityBuilder.loadSTL(Color.BLUE, Paths.get("C:\\Users\\lackt\\Documents\\Projects\\Physics Engine\\src\\main\\java\\renderer\\entity\\shapeFiles\\astronaut.stl"), 0, 0, 0));
        //this.entities.add(EntityBuilder.createPlane(Color.RED, 500));
        this.entities.add(EntityBuilder.createCube(100,0,0,0));
        this.entities.add(EntityBuilder.createCube(100,0,100,10));
        this.setLighting();
    }
    public void addEntity(IEntity e){
        this.entities.add(e);
    }

    //TODO create better lighting system
    private void setLighting() {
        for(IEntity entity : this.entities){
            entity.setLighting(this.lightVector);
        }
    }
    //TODO add a way to hide objects
    public void render(Graphics graphics){
        for(IEntity entity : this.entities){
            entity.render(graphics);
            entity.getCollider().render(graphics);
            graphics.setColor(Color.WHITE);
            if(collisionManager.simplex != null)
                collisionManager.simplex[0].render(graphics);
            graphics.setColor(Color.RED);
        }
    }

    public void rotate(ControlType mode, boolean CW, double xDegrees, double yDegrees, double zDegrees, Vector lightVector){
        for(IEntity entity : this.entities){
            entity.rotate(mode, CW, xDegrees, yDegrees, zDegrees, lightVector);
        }
    }

    public void translate(double x, double y, double z){
        for(IEntity entity : this.entities){
            entity.translate(x,y,z);
        }
    }

    public IEntity getEntitiy(int index){
        return this.entities.get(index);
    }

    public void update(){
        if(entities.size() > 1){
            if(collisionManager.isCollide(entities.get(0).getCollider(), entities.get(1).getCollider())){
                for(Polyhedron poly : entities.get(0).getPolyhedrons()){
                    poly.setColor(Color.GREEN);
                }
                for(Polyhedron poly : entities.get(1).getPolyhedrons()){
                    poly.setColor(Color.GREEN);
                }
            } else {

                for(Polyhedron poly : entities.get(0).getPolyhedrons()){
                    poly.setColor(Color.RED);
                }
                for(Polyhedron poly : entities.get(1).getPolyhedrons()){
                    poly.setColor(Color.RED);
                }
            }
        }

    }
}
