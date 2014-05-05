package warmup;

import physics.Circle;
import physics.Vect;

public class Ball implements BallInterface{
    
    Circle ball;
    Vect velocityVector;
    double radius;
    

    public Ball(double cx, double cy, double r, Vect vel){
        this.ball = new Circle(cx, cy, r);
        this.velocityVector = vel;
        this.radius = r;
    }
    
    @Override
    public void setVelocity(Vect veloVector) {
        this.velocityVector = veloVector;
    }

    @Override
    public Vect getVelocity() {
        return this.velocityVector;
    }

    @Override
    public double getRadius() {
        return this.radius;
    }
    
    @Override
    public void setPosition(double xLoc, double yLoc) {
        this.ball = new Circle(xLoc, yLoc, this.radius);
    }
    
    @Override
    public double[] getPosition() {
        double[] posArray = new double[2];
        posArray[0] = this.ball.getCenter().x();
        posArray[1] = this.ball.getCenter().y();
       return posArray;
    }
    
    public Circle getCircle(){
        return this.ball;
    }

    @Override
    public Vect update(Wall wall, Vect veloVector) {
       return physics.Geometry.reflectWall(wall.getLine(), this.velocityVector, wall.getCoR());  
        //this.velocityVector = physics.Geometry.reflectWall(wall.getLine(), this.velocityVector, wall.getCoR());
    }



}
