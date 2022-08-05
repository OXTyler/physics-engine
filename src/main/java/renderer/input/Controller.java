package renderer.input;

import renderer.Camera;
import renderer.entity.EntityManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private EntityManager entityManager;
    private double speed = 5;

    public Controller(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void setSpeed(double s){
        speed = s;
    }

    public double getSpeed(){
        return speed;
    }

    int mode = 0;
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
                break;
        }
        if(e.isControlDown()) entityManager.translate(0,0,-speed);
    }



    @Override
    public void keyReleased(KeyEvent e) {

    }
}