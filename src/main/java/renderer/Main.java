package renderer;

import javax.swing.*;

//purpose of project is to create a basic physics engine,
//the 3D rendering of this project was made by folling a tutorial from
//MeanRollerCoding's youtube, I've found hes made one of the best tutorials for it (albeit a bit sloppy)

public class Main {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        String title = "3D Render";
        final int WIDTH = 800;
        final int HEIGHT = 600;

        Display display = new Display(WIDTH, HEIGHT, title);
        display.frame.setTitle(title);
        display.frame.add(display);
        display.frame.pack();
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setLocationRelativeTo(null);
        display.frame.setResizable(false);
        display.frame.setVisible(true);

        display.start();
    }
}
