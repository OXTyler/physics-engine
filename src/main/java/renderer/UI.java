package renderer;

import renderer.input.ControlType;
import renderer.input.Controller;

import java.awt.*;

public class UI {
    //tells what mode user is in
    private String mode;
    private final Controller controller;
    public UI(Controller controller){
        this.controller = controller;
    }
    //updates the text info
    public void update(){
        if(controller.getMode() == ControlType.Camera){
            mode = "Camera";
        } else if(controller.getMode() == ControlType.Object){
            mode = "Object";
        }

    }
    public void render(Graphics graphics){
        int xOff = 10;
        int yOff = 10;
        graphics.setColor(new Color(10,10,10,50));
        graphics.fillRect(xOff,yOff,100,50);
        graphics.setColor(Color.WHITE);
        String test = "Mode: " + mode;
        graphics.drawChars(test.toCharArray(), 0, test.length(), xOff,yOff + 15);
    }
}
