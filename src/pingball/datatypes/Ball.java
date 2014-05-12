package pingball.datatypes;

import static org.junit.Assert.*;
import physics.Circle;
import physics.Geometry;
import physics.Vect;

public class Ball {
    
    private Circle circle;
    private Vect velocityVector;
    private final double radius = 0.25;
    private final String name;
    private boolean inAbsorber = false;
    
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
    public Vect getNormalVelocity() {
        return this.velocityVector;
    }
    /**
     * 
     * @return the velocity vector of the ball, accounting for the physics package's y-discrepancy
     */
    public Vect getPhysicsVelocity() {
    	double newX = this.velocityVector.x();
    	double newY = (-1)*this.velocityVector.y();
        return new Vect(newX, newY);
    }
    
    
    
    /**
     * set the velocity of the ball
     * @param vect new velocity vector of the ball
     */
    public void setNormalVelocity(Vect vect){
        this.velocityVector = adjustSpeed(vect);
    }
    private Vect adjustSpeed(Vect vect) {
    	double maxSpeed = 40.0;
    	double vectSpeed = Math.sqrt(Math.pow(vect.x(),2)+Math.pow(vect.y(),2));
    	double scalingFactor = vectSpeed/maxSpeed;
    	if (scalingFactor>1){
    		double newXVel = vect.x()/scalingFactor;
    		double newYVel = vect.y()/scalingFactor;
    		vect = new Vect(newXVel, newYVel);
    	}
    	return vect;
	}


	/**
     * set the velocity of the ball, accounting for the physics package's y-discrepancy
     * @param vect new velocity vector of the ball, accounting for the physics package's y-discrepancy
     */
    public void setPhysicsVelocity(Vect vect){
    	double newX = vect.x();
    	double newY = (-1)*vect.y();
    	Vect newVect = new Vect(newX, newY);
        this.velocityVector = adjustSpeed(newVect);
    }
    
    
    
    /**
     * sets the position of the ball
     * @param xLoc the new x-position of the ball
     * @param yLoc the new y-position of the ball
     */
    public void setNormalPosition(double xLoc, double yLoc) {
        this.circle = new Circle(xLoc, yLoc, this.radius);
    }
    /**
     * sets the position of the ball, accounting for the physics package's y-discrepancy
     * @param xLoc the new x-position of the ball
     * @param yLoc the new y-position of the ball
     */
    public void setPhysicsPosition(double xLoc, double yLoc) {
        this.circle = new Circle(xLoc, 20-yLoc, this.radius);
    }
    
    
    
    /**
     * @return the current position of the ball
     */
    public double[] getNormalPosition() {
        double[] posArray = new double[2];
        posArray[0] = this.circle.getCenter().x();
        posArray[1] = this.circle.getCenter().y();
       return posArray;
    }
    /**
     * @return the current position of the ball, accounting for the physics package's y-discrepancy
     */
    public double[] getPhysicsPosition() {
        double[] posArray = new double[2];
        posArray[0] = this.circle.getCenter().x();
        posArray[1] = 20-this.circle.getCenter().y();
       return posArray;
    }
    
    
    
    /**
     * @return Circle object that the ball represents
     */
    public Circle getNormalCircle(){
        return new Circle(circle.getCenter(),radius);
    }
    /**
     * @return Circle object that the ball represents
     */
    public Circle getPhysicsCircle(){
    	double circleX = circle.getCenter().x();
    	double circleY = 20-circle.getCenter().y();
        return new Circle(circleX, circleY,radius);
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
    public void updateBallVelocityBeforeTimestep(double gravity, double mu,double mu2,double delta_t){
        //v1 = v0 + gt
        double yVel = velocityVector.y() + gravity*delta_t; //gravity only affects y-direction
        //assumed formula given should be applied to both x and y
        double VoldMagnitude = Math.sqrt(Math.pow(velocityVector.x(),2) + Math.pow(yVel, 2));
        Vect newVelocityVector = new Vect(velocityVector.x()*(1-mu*delta_t-mu2*VoldMagnitude*delta_t),
                                            yVel*(1-mu*delta_t-mu2*VoldMagnitude*delta_t));
        this.setNormalVelocity(newVelocityVector);
        
    }
    
    /**
     * updates the position of the ball after a timestep
     * @param timeStep period of time 
     */
    public void updateBallPosition(double timeStep){
        //calculate the position of the ball and update it
    	
        double xPos = this.getNormalCircle().getCenter().x() + velocityVector.x()*timeStep;
        double yPos = this.getNormalCircle().getCenter().y()+ velocityVector.y()*timeStep;

        this.setNormalPosition(xPos, yPos);
        checkRep();
    }
    /**
     * updates the position of the ball after a timestep using an old velocity
     * @param timeStep period of time 
     */
    public void updateBallPositionUsingOldPhysicsVelocity(double timeStep, Vect oldPhysicsVelocity){
        //calculate the position of the ball and update it
        double xPos = this.getPhysicsCircle().getCenter().x() + oldPhysicsVelocity.x()*timeStep;
        double yPos = this.getPhysicsCircle().getCenter().y()+ oldPhysicsVelocity.y()*timeStep;

        this.setPhysicsPosition(xPos, yPos);
        checkRep();
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
    
    public boolean inAbsorber(){
    	return this.inAbsorber;
    }
    
    public void setInAbsorber(boolean bool){
    	this.inAbsorber = bool;
    }



	public double timeUntilPhysicsCollision(Ball ball2) {
		return Geometry.timeUntilBallBallCollision(this.getPhysicsCircle(), this.getPhysicsVelocity(), ball2.getPhysicsCircle(), ball2.getPhysicsVelocity());
	}
    


}
