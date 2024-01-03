import processing.core.PApplet;

public class Asteroid {

    private float x, y;
    private float speed;
    private int size;
    private float angle;
    private boolean active;

    public Asteroid(Spaceship player){
        double num = Math.random();
        active = true;
        if(num < 0.5){
            double newNum = Math.random();
            if(newNum < 0.5){
                x = 1;
            }
            else{
                x=799;
            }
            y = (float)(Math.random()*600);
        }else{
            double newNum = Math.random();
            if(newNum < 0.5){
                y = 1;
            }
            else{
                y=599;
            }
            x = (float)(Math.random()*800);
        }

        size = (int)(75 * (Math.random() + 0.5));

        angle = (float)PApplet.atan2((player.getY()-y),(player.getX()-x));


        speed = (int)(2 * (Math.random()+0.4));
        if(speed == 0){
            speed = 1;
        }
// spawn asteroid on the edge of map with random size and speed
    }


    public void draw(PApplet game){
        //game.fill(255);
        game.stroke(0,255,0,255);
        game.noFill();
        game.ellipse(x,y,size,size);
        game.stroke(255);
    }

    public void update(){
        x += PApplet.cos(angle) * speed;
        y += PApplet.sin(angle) * speed;
        if(x < 0 || x > 800 || y < 0 || y > 600){
            active = false;
        }

        // move it
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public float size(){
        return size;
    }

    public boolean isInactive(){
        return !active;
    }

}
