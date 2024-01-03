import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Spaceship {
    private float x, y;
    private float angle;
    private float speed;
    //private boolean dead;
    private ArrayList<Bullet> bullets;
    private PVector velocity;
    private int bulletCooldown = 0;
    private int score = 0;



    Spaceship(float x, float y) {
        this.x = x;
        this.y = y;
        this.angle = 0;
        this.speed = 3;
        //this.dead = false;
        this.bullets = new ArrayList<>();
        this.velocity = new PVector(0,0);
    }

    void update(PApplet game, boolean[] keys, ArrayList<Asteroid> droids) {
        //angle = game.atan2(game.mouseY - y, game.mouseX - x);
        if(keys[1]) angle -= 0.1f;
        if(keys[2]) angle += 0.1f;
        if(keys[0]){
            velocity.set(PApplet.cos(angle) * speed, PApplet.sin(angle) * speed);
        }
        else{
            velocity.set(velocity.x * 0.999f, velocity.y * 0.999f);
        }
        if(keys[3] && canShoot()){
            shoot();
        }
        x += velocity.x;
        y += velocity.y;
        if(x < 0){
            x = game.width;
            y = game.height - y;
        }else if(x > game.width){
            x = 0;
            y = game.height - y;
        }

        if(y < 0){
            y = game.height;
            x = game.width -x;
        }
        else if(y > game.height){
            y = 0;
            x = game.width - x;
        }

        bulletCooldown--;
        for (Bullet bullet : bullets) {
            if (bullet != null) {
                bullet.update(droids,this);
                bullet.display(game);
            }
        }
    }

    void display(PApplet game) {
        game.pushMatrix();
        game.translate(x, y);
        game.rotate(angle + game.HALF_PI); // Adjusted rotation
        game.fill(255);
        game.triangle(0, -20, -10, 10, 10, 10);
        game.noFill();
        game.ellipse(0,0,40,40);
        game.popMatrix();
    }

    void shoot() {
        bulletCooldown = 40;
        bullets.add(new Bullet(x,y,angle));
    }

    void handleBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i) != null && bullets.get(i).isActive()) {
                if (bullets.get(i).getY() < 0 ||bullets.get(i).getY() > 600) {
                    bullets.get(i).setInactive();
                }
            }
        }
    }

    public boolean canShoot(){
        return bulletCooldown <= 0;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public boolean isCollided(Asteroid droid){
        double distX = droid.getX() - (x);
        double distY = droid.getY() - (y);
        double dist = Math.sqrt(distX * distX + distY * distY);
        return dist < (droid.size());
    }

    public void addToScore(int num){
        score+= num;
    }

    public int getScore(){
        return score;
    }

    public void resetScore(){
        score = 0;
    }
}
