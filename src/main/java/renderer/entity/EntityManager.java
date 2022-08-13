package renderer.entity;

import renderer.entity.builder.BasicEntityBuilder;
import renderer.input.ControlType;
import renderer.point.Point;
import renderer.point.Vector;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//manages everything inside the scene, what to spawn and when, and how to render all objects
public class EntityManager {
    public static final Point ORIGIN = new Point(0,0,0);
    private List<IEntity> entities;
    private int initialX, initialY, x, y, xDif, yDif;
    public Vector lightVector = Vector.normalize(new Vector(1,1,1));
    public EntityManager(List<IEntity> entities){
        this.entities = entities;
    }

    public  EntityManager(){
        this.entities = new ArrayList<IEntity>();
    }
    //TODO make a way to spawn objects inside the render
    public void init() throws IOException {
        //this.entities.add(BasicEntityBuilder.createDiamond(Color.CYAN,100, 0 ,0 ,100));
        this.entities.add(BasicEntityBuilder.loadSTL(Color.BLUE, Paths.get("C:\\Users\\lackt\\Documents\\Projects\\Physics Engine\\src\\main\\java\\renderer\\entity\\shapeFiles\\astronaut.stl"), 0, 0, 0));
        //this.entities.add(BasicEntityBuilder.createPlane(Color.RED, 500));
        //this.entities.add(BasicEntityBuilder.createCube(100,0,0,0));
        this.setLighting();
    }

    public void rotate(ControlType mode, boolean CW, double xDegrees, double yDegrees, double zDegrees, Vector lightVector){
        for(IEntity entity : this.entities){
            entity.rotate(mode, CW, xDegrees, yDegrees, zDegrees, lightVector);
        }
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
        }
    }

    public void translate(double x, double y, double z){
        for(IEntity entity : this.entities){
            entity.translate(x,y,z);
        }
    }

    public void update(){

    }
}
