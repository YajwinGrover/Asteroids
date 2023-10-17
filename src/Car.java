import processing.core.PApplet;

public class Car {
    Point2 position;
    Point2 velocity;
    Point2 acceleration = new Point2(0.5,0.5);
    double acc = 3;
    double angleFromHorizontal = 0;

    double angularVelRad = PApplet.PI/60;
    Point2 maxVelocity = new Point2(6,6);
    //Point2 dragForce = new Point2(0.9, 0.9);


    private double clamp(double a, double b){
        return Math.min(a, b);
    }

    private double frictionForce(double input){
        return clamp((0.5 * -input) + 0.1, 0.3);
    }
    public Car(Point2 position){
        this.position = position;
        velocity = new Point2(0,0);
    }

    public void draw(Game game){


        game.fill(255,0,0);
        game.pushMatrix();
        game.translate((float)position.x, (float)position.y);
        game.rotate((float)angleFromHorizontal);
        game.rect((int)position.x,(int)position.y,15,30);
        game.popMatrix();

        int yMod = velocity.y > 0 ? -1 : 1;
        int xMod = velocity.x > 0 ? -1 : 1;

        if(yMod * velocity.y < 0) {
            double thing = (yMod * frictionForce(yMod * velocity.y));
            System.out.println(thing);
            velocity.y += thing;
        }

        if(xMod * velocity.x < 0) {
            velocity.x += (xMod * frictionForce(xMod * velocity.x));
        }





        //System.out.println(Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y));

        if((game.key == 'w' || game.key == 'W') && game.keyPressed){
            if(!(velocity.y <= -maxVelocity.y))
                velocity.y -= acceleration.y;

        }
        else if((game.key == 's' || game.key == 'S') && game.keyPressed){
            if(!(velocity.y >= maxVelocity.y))
                velocity.y += acceleration.y;

        }
        if((game.key == 'd' || game.key == 'D') && game.keyPressed){
//            if(!(velocity.x >= maxVelocity.x))
//                velocity.x += acceleration.x;
            angleFromHorizontal -= angularVelRad;
        }
        else if((game.key == 'a' || game.key == 'A') && game.keyPressed) {
//            if (!(velocity.x <= -maxVelocity.x))
//                velocity.x -= acceleration.x;
            angleFromHorizontal += angularVelRad;


        }



        position.x += velocity.x;
        position.y += velocity.y;

        //position.x = Math.max(0,clamp(position.x, game.width-15));
        //position.y = Math.max(0,clamp(position.y, game.height-15));



    }
}



