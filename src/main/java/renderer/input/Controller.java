package renderer.input;

import renderer.Camera;
import renderer.entity.EntityManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private EntityManager entityManager;
    private double speed = 5;
    public static int mode = 1;
    public Controller(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void setSpeed(double s){
        speed = s;
    }

    public double getSpeed(){
        return speed;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //used for camera controller key binds, probably a better way to do this
        switch (e.getKeyChar()){
            case 'w':
                entityManager.translate(speed, 0,0);
                break;
            case 'a':
                entityManager.translate(0,speed,0);
                break;
            case 's':
                entityManager.translate(-speed, 0,0);
                break;
            case 'd':
                entityManager.translate(0, -speed, 0);
                break;
            case ' ':
                entityManager.translate(0,0, speed);
                break;
            //changes mode between object and camera mode
            case 't':
                speed = -speed;
                mode = mode == 0 ? 1 : 0;
                break;
        }
        if(e.isControlDown()) entityManager.getEntitiy(1).translate(0,-10,0);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static ControlType getMode(){
        switch (mode){
            case 0:
                return ControlType.Camera;
            case 1:
                return ControlType.Object;
        }
        return ControlType.Unknown;
    }
}
