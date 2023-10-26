import processing.core.PApplet;

public class Car {
    Point2 position;
    Point2 velocity;
    //Point2 acceleration = new Point2(0.5,0.5);
    double acceleration = 0.1;
    double angleFromHorizontal = PApplet.PI/2;
    double angularVelRad = PApplet.PI/60;
    Point2 maxVelocity = new Point2(2,2);

    int height = 30;
    int width= 15;
    //Point2 dragForce = new Point2(0.9, 0.9);


    private double clamp(double a, double b){
        return Math.min(a, b);
    }
    

    private double frictionForce(double input){
        return clamp((0.5 * -input) + 0.1, 0.1);
    }
    public Car(Point2 position){
        this.position = position;
        velocity = new Point2(0,0);
    }

    public void draw(Game game){


        game.fill(255,0,0);
        game.pushMatrix();
        game.translate((float)position.x , (float)position.y);
        game.rotate((float)angleFromHorizontal-(PApplet.PI/2));
        game.rect((int)0,(int)0,width,height);
        game.popMatrix();

        int yMod = velocity.y > 0 ? -1 : 1;
        int xMod = velocity.x > 0 ? -1 : 1;

//        if(yMod * velocity.y < 0) {
//            double thing = (yMod * frictionForce(yMod * velocity.y));
//
//            velocity.y += thing;
//        }
//
//        if(xMod * velocity.x < 0) {
//            velocity.x += (xMod * frictionForce(xMod * velocity.x));
//        }





        if((game.key == 'w' || game.key == 'W') && game.keyPressed){
            double t = Math.cos(angleFromHorizontal) * acceleration;
            System.out.println(t + " x thing");
           velocity.x += t;
           double nt =  Math.sin(angleFromHorizontal) * acceleration;
            System.out.println(nt +" y thing");
           velocity.y -= nt;

        }
        else if((game.key == 's' || game.key == 'S') && game.keyPressed){
            velocity.x -= Math.cos(angleFromHorizontal) * acceleration;
            velocity.y += Math.sin(angleFromHorizontal) * acceleration;

        }
        if((game.key == 'd' || game.key == 'D') && game.keyPressed){
//            if(!(velocity.x >= maxVelocity.x))
//                velocity.x += acceleration.x;
            angleFromHorizontal += angularVelRad;
        }
        else if((game.key == 'a' || game.key == 'A') && game.keyPressed) {

            angleFromHorizontal -= angularVelRad;


        }



        position.x += velocity.x;
        position.y += velocity.y;

        //position.x = Math.max(0- position.x,clamp(position.x, game.width-(15-position.x)));
        //position.y = Math.max(0 - position.y,clamp(position.y, game.height-(15-position.y)));





    }
}



