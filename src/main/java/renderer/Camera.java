package renderer;

import renderer.entity.EntityManager;
import renderer.input.ClickType;
import renderer.input.Controller;
import renderer.input.Mouse;
import renderer.point.Point;
import renderer.point.PointConverter;
import renderer.point.Vector;

import java.awt.event.MouseWheelEvent;

public class Camera {
    private static int x,y,initialX,initialY,xDif,yDif;
    private static double sensitivity = 2.5;
    private static final int MIN_ZOOM = -1500;
    public static Vector viewDirection = new Vector(-1,0,0);
    //TODO bad implementation right now, x is the same as Dist used in point converter, will fix eventually
    public static Point cameraCoords = new Point(1000,0,0);
    public static void update(EntityManager entityManager, Mouse mouse){
        x = mouse.getX();
        y = mouse.getY();

        if(mouse.getButton() == ClickType.LeftClick){
            rotateObjectXY(entityManager);
        }
        else if(mouse.getButton() == ClickType.RightClick) {
            rotateObjectZ(entityManager);
        }
        initialX = x;
        initialY = y;
    }

    public static void zoom(MouseWheelEvent Event){
        PointConverter.Dist += Event.getWheelRotation()* 250;
        PointConverter.Dist = Math.max(MIN_ZOOM, PointConverter.Dist);
    }

    private static void rotateObjectXY(EntityManager entityManager){
        xDif = x - initialX;
        yDif = y - initialY;

       // entityManager.rotate(Controller.getMode(), true, 0, yDif / sensitivity, -xDif / sensitivity, entityManager.lightVector);
    }
    private static void rotateObjectZ(EntityManager entityManager){
        xDif = x - initialX;
        //entityManager.rotate(Controller.getMode(),true, -xDif / sensitivity, 0 ,0, entityManager.lightVector);
    }
}
