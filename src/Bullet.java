import processing.core.PApplet;

import java.util.ArrayList;

public class Bullet {
    float x, y;
    float angle;
    float speed;
    boolean active;

    Bullet(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = 8;
        this.active = true;
    }

    void update(ArrayList<Asteroid> droids, Spaceship player) {
        if(isActive()){
            x += speed * PApplet.cos(angle);
            y += speed * PApplet.sin(angle);

            for(int i = 0; i < droids.size();i++){
                if(isCollidingWith(droids.get(i))){
                    player.addToScore((int)droids.get(i).size());
                    droids.remove(i);
                    setInactive();

                    break;
                }

                // move bullet and check for collisions
            }
        }

    }

    private boolean isCollidingWith(Asteroid asteroid) {
        double distX = asteroid.getX() - (x);
        double distY = asteroid.getY() - (y);
        double dist = Math.sqrt(distX * distX + distY * distY);
        return dist < (asteroid.size()-30);
    }

    void display(PApplet game) {
        if(isActive()){
            game.fill(255);
            game.ellipse(x, y, 5, 10);
        }

    }

    boolean isActive() {
        return active;
    }

    void setInactive() {
        active = false;
    }

    float getY() {
        return y;
    }
}