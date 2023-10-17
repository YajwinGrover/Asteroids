import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {
    // TODO: declare game variables
    Car c;
    private String URL = "./track.png";
    private PImage backgroundImg;

    public void settings() {
        size(800, 800);   // set the window size

    }

    public void setup() {
        // TODO: initialize game variables
        c = new Car(new Point2(0,0));
        //size(200, 200);
        backgroundImg = loadImage(URL, "png");
    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        background(255);    // paint screen white
        backgroundImg.resize(800, 500);
        image(backgroundImg, 0, 0);
        fill(0,255,0);          // load green paint color
        c.draw(this);

    }



    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
