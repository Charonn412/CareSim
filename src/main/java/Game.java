import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game implements Runnable {

    private Window window;
    public int width, height;
    public String title;

    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage testImage;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;

        window = new Window(title, width, height);
    }

//    private void init()  {
//        window = new Window(title, width, height);
//        testImage = ImageLoader.loadImage("C:\\Users\\charo\\IdeaProjects\\CareSim2\\res\\textures\\perry.jpg");
//    }

    private void update() {

    }

    private void render() {
        bs = window.getCanvas().getBufferStrategy();
        if(bs == null){
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear screen
        g.clearRect(0, 0, width,height);
        //Draw here!

        g.drawImage(testImage, 20, 20, null);

        //End drawing!
        bs.show();
        g.dispose();
    }

    public void run() {
        init();
        while(running) {
            //while it is running, keep updating the game
            update();
            render();
        }

        try {
            stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void start() {
        if(running) {
            return;
        }
        //this will run if running is false
        running = true;
        thread = new Thread(this);
        thread.start(); //this will start the run method
    }

    public synchronized void stop() throws InterruptedException {
        if(!running) {
            return;
        }
        //runs if running is true
        running = false;
        thread.join();
    }

}
