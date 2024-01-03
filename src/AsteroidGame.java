// AsteroidsGame.java
import processing.core.PApplet;

import java.util.ArrayList;

public class AsteroidGame extends PApplet {
    private Spaceship spaceship;
    private int spawnTimer = 0;
    private boolean[] keys = new boolean[4];
    private ArrayList<Asteroid> droids = new ArrayList<>();
    private int lives = 5;
    private boolean gameOver = false;

    public static void main(String[] args) {

        PApplet.main("AsteroidGame");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        spaceship = new Spaceship(width / 2, height / 2);
    }

    public void draw() {
        background(0);
        text("Lives: " + lives, 0,0,400, 500);
        text("Score: " +spaceship.getScore(), 0,50,400,500);
        if(gameOver){
            text("PRESS 'r' TO RESTART", 350,300,400,400);
        }
        if(gameOver && keyPressed && key=='r'){
            gameOver = false;
            spaceship.resetScore();
            lives = 5;
        }
        if(!gameOver)
            spaceship.update(this, keys,droids);
        spaceship.display(this);
        spaceship.handleBullets();

        spawnTimer--;
        if(canSpawn() && !gameOver){
            spawn();
        }
        if(!gameOver){
            for (int i = 0; i < droids.size(); i++) {
                if(i < 0) break;
                if(droids.get(i).isInactive()){
                    droids.remove(i);
                    i--;
                }
                if(i < 0){
                    break;
                }
                droids.get(i).draw(this);
                droids.get(i).update();
                if(spaceship.isCollided(droids.get(i))){
                    droids.remove(i);
                    i--;
                    lives--;
                    if(lives == 0){
                        gameOver = true;
                        droids.clear();

                        break;
                    }
                }
            }
        }




    }

    public void keyPressed(){
        if(key == 'w') keys[0] = true;
        if(key == 'a') keys[1] = true;
        if(key == 'd') keys[2] = true;
        if(key == ' ') keys[3] = true;

    }

    public void keyReleased(){
        if(key == 'w') keys[0] = false;
        if(key == 'a') keys[1] = false;
        if(key == 'd') keys[2] = false;
        if(key == ' ') keys[3] = false;
    }

    public boolean canSpawn(){
        return spawnTimer <= 0;
    }

    public void spawn(){
        spawnTimer = 80;
        droids.add(new Asteroid(spaceship));
    }




}
