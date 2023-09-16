import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private Window window;
    public int width, height;
    public String title;

    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;

        window = new Window(title, width, height);
    }

    private void init() {
        window = new Window(title, width, height);
    }

    private void update() {

    }

    private void render() {
        bs = window.getCanvas().getBufferStrategy();
        if(bs == null){
            window.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Draw here!

        g.fillRect(0, 0, width, height);

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
