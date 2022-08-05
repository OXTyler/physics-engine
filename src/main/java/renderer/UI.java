package renderer;

import java.awt.*;

public class UI {
    public UI(){
        //initialization does nothings
    }
    public void render(Graphics graphics){
        int xOff = 10;
        int yOff = 10;
        graphics.setColor(new Color(10,10,10,50));
        graphics.fillRect(xOff,yOff,100,50);
        graphics.setColor(Color.WHITE);
        String test = "3D Renderer";
        graphics.drawChars(test.toCharArray(), 0, test.length(), xOff,yOff + 15);

    }
}
