public class Game {

    private Window window;
    public int width, height;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;

        window = new Window(title, width, height);
    }
}
