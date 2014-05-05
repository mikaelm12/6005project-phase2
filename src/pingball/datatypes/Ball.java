package pingball.datatypes;

import static org.junit.Assert.*;
import physics.Circle;
import physics.Vect;

public class Ball {
    
    private Circle circle;
    private Vect velocityVector;
    private final double radius = 0.25;
    private final String name;
    
    //Rep invariant:
    // circle != null
    //velocityVector != null
    //Abstraction Function:
    //represents a ball on a board

    public Ball(String name, double cx, double cy, double xVel, double yVel){
        this.name = name;
        this.circle = new Circle(cx, cy, radius);
        this.velocityVector = new Vect(xVel,yVel);
        
        checkRep();
    }
    
    /**
     * 
     * @return the velocity vector of the ball
     */
    public Vect getVelocity() {
        return this.velocityVector;
    }
    
    /**
     * set the velocity of the ball
     * @param vect new velocity vector of the ball
     */
    public void setVelocity(Vect vect){
        this.velocityVector = vect;
    }
    
    /**
     * sets the position of the ball
     * @param xLoc the new x-position of the ball
     * @param yLoc the new y-position of the ball
     */
    public void setPosition(double xLoc, double yLoc) {
        this.circle = new Circle(xLoc, yLoc, this.radius);
    }
    
    /**
     * 
     * @return the current position of the ball
     */
    public double[] getPosition() {
        double[] posArray = new double[2];
        posArray[0] = this.circle.getCenter().x();
        posArray[1] = this.circle.getCenter().y();
       return posArray;
    }
    
    /**
     * 
     * @return Circle object that the ball represents
     */
    public Circle getCircle(){
        return new Circle(circle.getCenter(),radius);
    }
    
    /**
     * 
     * @return name of ball
     */
    public String getName(){
        return new String(name);
    }
    
    /**
     * 
     * @param mu friction1 of board 
     * @param mu2 friction2 of board
     * @param delta_t timestep
     */
    public void updateBallVelocityAfterTimestep(double gravity, double mu,double mu2,double delta_t){
        //v1 = v0 + gt
        double yVel = velocityVector.y() + gravity*delta_t; //gravity only affects y-direction
        //assumed formula given should be applied to both x and y
        double VoldMagnitude = Math.sqrt(Math.pow(velocityVector.x(),2) + Math.pow(yVel, 2));
        Vect newVelocityVector = new Vect(velocityVector.x()*(1-mu*delta_t-mu2*VoldMagnitude*delta_t),
                                            yVel*(1-mu*delta_t-mu2*VoldMagnitude*delta_t));
        this.setVelocity(newVelocityVector);
    }
    
    /**
     * updates the position of the ball after a timestep
     * @param timeStep period of time 
     */
    public void updateBallPosition(double timeStep){
        //calculate the position of the ball and update it
        if(!ballOutOfBounds(timeStep)){
            double xPos = circle.getCenter().x() + velocityVector.x()*timeStep;
            double yPos = circle.getCenter().y()+ velocityVector.y()*timeStep;

            this.setPosition(xPos, yPos);
        }
        checkRep();
    }
    
    /**
     * 
     * @param timeStep time difference
     * @return true if ball will be out of bounds after timeStep, false otherwise
     */
    public boolean ballOutOfBounds(double timeStep){
        //calculate position of the ball and return if the ball will be out of bounds
        double xPos = circle.getCenter().x() + velocityVector.x()*timeStep;
        double yPos = circle.getCenter().y()+ velocityVector.y()*timeStep;
        
        return (xPos < 0 || xPos > 20 || yPos < 0 || yPos > 20);
            
    }
    
    /**
     * @return string representation of the ball
     */
    @Override
    public String toString(){
        return "*";
    }
    
    private void checkRep(){
        assertTrue(circle != null);
        assertTrue(velocityVector != null);
    }
    


}
