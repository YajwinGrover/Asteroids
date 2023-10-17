import processing.core.PImage;

public class Map {

    Point2 location;
    PImage image;

    public void draw(Game game, Point2 negVelocityOfCar){
        location.x += negVelocityOfCar.x;
    }
}
