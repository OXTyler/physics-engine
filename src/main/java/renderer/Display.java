package renderer;

import renderer.entity.EntityManager;
import renderer.input.ClickType;
import renderer.input.Controller;
import renderer.input.Mouse;
import renderer.point.Point;
import renderer.shapes.Polyhedron;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.swing.JFrame;

public class Display  extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;

    String title;
    public static int WIDTH = 0;
    public static int HEIGHT = 0;
    private EntityManager entityManager;
    private Controller controller;
    private UI ui;
    public static Point origin = new Point(0,0,0);
    private Mouse mouse;


    private Thread thread;
    public JFrame frame;
    private static boolean running = false;


    public Display(int WIDTH, int HEIGHT, String title){
        this.frame = new JFrame();
        this.title = title;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        Dimension size = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(size);

        this.entityManager = new EntityManager();
        this.controller = new Controller(entityManager);
        ui = new UI(controller);
        this.addKeyListener(controller);
        this.mouse = new Mouse();

        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        this.addMouseWheelListener(this.mouse);
    }


    public synchronized void start() {
        running = true;
        this.thread = new Thread(this, "Display");
        this.thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        //nanoseconds between updates
        final double ns = 1000000000.0 / 60;
        double delta = 0;
        int frames = 0;
        try {
            this.entityManager.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                update();
                delta--;
                render();
                frames++;
            }

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                this.frame.setTitle(title + " | " + frames + " fps");
                frames = 0;
            }
        }
        stop();
    }



    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bs.getDrawGraphics();
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0,0,WIDTH,HEIGHT);
        this.entityManager.render(graphics);
        Graphics uiGraphics = bs.getDrawGraphics();
        ui.render(uiGraphics);
        uiGraphics.dispose();
        graphics.dispose();
        bs.show();
    }


    private void update(){
        Camera.update(entityManager, mouse);
        ui.update();
        entityManager.update();
    }
}
