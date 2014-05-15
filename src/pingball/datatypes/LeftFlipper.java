package pingball.datatypes;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import static org.junit.Assert.*;

public class LeftFlipper implements Gadget{
    
    private final int boxLength;
    private final double coR;
    private int orientation;
    private LineSegment flipper;
    private final String name;
    private List<Gadget> gadgetsToFire;
    private String state = "initial"; //not triggered yet
    private double angularVelocity;
    private final Vect origin;
    private Circle pivot;
    private Circle endPoint;
	private double currentAngle;
    
    //Rep invariant:
    //box within board
    //if orientation == 0, then lineSegment is at left of bounding box initially
    //if orientation == 90, then lineSegment is at bottom of bounding box initially
    //if orientation == 180, then lineSegment is at right of bounding box initially
    //if orientation == 270, then lineSegment is at top of bounding box initially
    //Abstraction Function
    //lineSegment represents flipper that rotates
    //circularBumpers represent corners of the flipper
    
    
    public double getCurrentAngle() {
        return currentAngle;
    }

    public void setCurrentAngle(double currentAngle) {
        this.currentAngle = currentAngle;
    }

    public LeftFlipper(String name, int x, int y, int orientation){
        this.name = name;
        this.boxLength = 2;
        this.coR = 0.95;
        this.orientation = orientation;
        this.gadgetsToFire = new ArrayList<Gadget>();
        this.angularVelocity = (1080.0/180)*Math.PI;
        this.currentAngle = 0;
        
        
        this.origin = new Vect(x,y);
        
        
        if(orientation == 0){
            this.flipper = new LineSegment(x,20-y,x,20-(y+boxLength));
            this.pivot = new Circle(x,20-y,0);
            this.endPoint = new Circle(x,20-(y+boxLength),0);
            
        }
        else if(orientation == 90){
            this.flipper = new LineSegment(x+boxLength,20-y,x,20-y);
            this.pivot = new Circle(x+boxLength,20-y,0);
            this.endPoint = new Circle(x,20-y,0);
            
        }
        else if(orientation == 180){
            this.flipper = new LineSegment(x+boxLength,20-(y+boxLength),x+boxLength,20-y);
            this.pivot = new Circle(x+boxLength,20-(y+boxLength),0);
            this.endPoint = new Circle(x+boxLength,20-y,0);
            
        }
        else{ //orientation == 270
            this.flipper = new LineSegment(x,20-(y+boxLength),x+boxLength,20-(y+boxLength));
            this.pivot = new Circle(x,20-(y+boxLength),0);
            this.endPoint = new Circle(x+boxLength,20-(y+boxLength),0);
           
        }
        
        //checkRep();
    }
    
    /**
     * fires the actions of gadgets in gadgetsToFire
     */
    private void trigger(){
        for (Gadget gadget : gadgetsToFire) {
            gadget.action();
        }
    }
    
    /**
     * 
     * @return the angular velocity
     */
    public double getAngularVelocity(){
        return angularVelocity;
    }
    
    /**
     * changes state, which affects update()
     */
    @Override
    public void action() {
        if(state.equals("initial")){
        	state = "flipping";
        }
        else if (state.equals("flipped")){
        	state = "deflipping";
        } else if (state.equals("flipping")){
        	state = "deflipping";
        } else {//state = deflipping
        	state = "flipping";
        }
        
        //checkRep();
    }

	public void update(double timestep) {
		if (this.state.equals("flipping")){
			double deltaAngle = timestep*this.angularVelocity;
			double angleToRotate = 0.0;
			if (this.currentAngle + deltaAngle >= Math.PI/2){//this would be an over-rotation
				angleToRotate = Math.PI/2 - this.currentAngle;
				this.state = "flipped";
				this.currentAngle = Math.PI/2;
			} else {
				angleToRotate = deltaAngle;
				this.currentAngle += angleToRotate;
			}
			Geometry.rotateAround(this.endPoint, this.pivot.getCenter(), new Angle(angleToRotate));
			Geometry.rotateAround(flipper, this.pivot.getCenter(), new Angle(angleToRotate));
		} else if (this.state.equals("deflipping")){
			double deltaAngle = (-1)*timestep*this.angularVelocity;
			double angleToRotate = 0.0;
			if (this.currentAngle + deltaAngle <= 0.0){//this would be an over-rotation
				angleToRotate = 0 - this.currentAngle;
				this.state = "initial";
				this.currentAngle = 0.0;
			} else {
				angleToRotate = deltaAngle;
				this.currentAngle += angleToRotate;
			}
			Geometry.rotateAround(this.endPoint, this.pivot.getCenter(), new Angle(angleToRotate));
			Geometry.rotateAround(flipper, this.pivot.getCenter(), new Angle(angleToRotate));
		}
		
	}
    
    /**
     * @return the coefficient of Reflection
     */
    @Override
    public double getCoR() {
        return new Double(coR).doubleValue();
    }
    
    /**
     * computes time until ball collides with flipper
     * @param ball to collide with
     * @return time until ball collides with flipper
     */
    @Override
    public double timeUntilPhysicsCollision(Ball ball) {
        //define +angularVelocity as being clockwise rotation because it's a left flipper
        double timeToLine = Double.POSITIVE_INFINITY;
        double timeToEndPoint = Double.POSITIVE_INFINITY;
        double timeToPivot = Geometry.timeUntilCircleCollision(pivot, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        
        if (state.equals("initial") || state.equals("flipped")){ //we are dealing with a stationary flipper
        	timeToLine = Geometry.timeUntilWallCollision(flipper, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        	timeToEndPoint = Geometry.timeUntilCircleCollision(endPoint, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        } else { //we are dealing with a moving flipper
        	double potentialTimeToLine = Double.POSITIVE_INFINITY;
        	double potentialTimeToEndPoint = Double.POSITIVE_INFINITY;
        	double timeUntilStationary = Double.POSITIVE_INFINITY;
        	if (state.equals("flipping")){
        		timeUntilStationary = (Math.PI/2 - this.currentAngle)/this.angularVelocity;
        		potentialTimeToLine = Geometry.timeUntilRotatingWallCollision(flipper, pivot.getCenter(), this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        		potentialTimeToEndPoint = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        	} else { //state should equal "deflipping"
        		timeUntilStationary = (this.currentAngle)/this.angularVelocity;
        		potentialTimeToLine = Geometry.timeUntilRotatingWallCollision(flipper, pivot.getCenter(), -this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        		potentialTimeToEndPoint = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), -this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        	}
        	
        	if (timeUntilStationary<=potentialTimeToLine && timeUntilStationary<=potentialTimeToEndPoint){ //the flipper becomes stationary before we hit. This is the most complicated scenario
    			LineSegment cloneFlipper = new LineSegment(flipper.p1().x(), flipper.p1().y(), flipper.p2().x(), flipper.p2().y());
    			Circle cloneEndpoint = new Circle(endPoint.getCenter().x(), endPoint.getCenter().y(), 0.0);
    			if (state.equals("flipping")){
    				Geometry.rotateAround(cloneEndpoint, pivot.getCenter(), new Angle(Math.PI/2 - this.currentAngle));
        			Geometry.rotateAround(cloneFlipper, pivot.getCenter(), new Angle(Math.PI/2 - this.currentAngle));
    			} else { //state == "deflipping"
    				Geometry.rotateAround(cloneEndpoint, pivot.getCenter(), new Angle(0.0 - this.currentAngle));
        			Geometry.rotateAround(cloneFlipper, pivot.getCenter(), new Angle(0.0 - this.currentAngle));
    			}
    			//we have now rotated our clone flipper, and can now reflect the ball off of the stationary flipper
    			timeToLine = Geometry.timeUntilWallCollision(cloneFlipper, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    			timeToEndPoint = Geometry.timeUntilCircleCollision(cloneEndpoint, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        	} else { //the flipper is moving while we hit it
        		timeToLine = potentialTimeToLine;
        		timeToEndPoint = potentialTimeToEndPoint;
        	}
        }
    	double minMovingTime = Math.min(timeToEndPoint, timeToLine);
    	return Math.min(minMovingTime, timeToPivot);
    }
    
    /**
     * reflects the ball off gadget, updates the ball's velocity and triggers this gadget
     * @param ball to be reflected
     */
    @Override
    public void reflectOff(Ball ball){
        Vect newVelocityVector;
        double timeToLine = Double.POSITIVE_INFINITY;
        double timeToEndPoint = Double.POSITIVE_INFINITY;
        double timeToPivot = Geometry.timeUntilCircleCollision(pivot, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        
        if (state.equals("initial") || state.equals("flipped")){ //flipper is stationary
        	timeToLine = Geometry.timeUntilWallCollision(flipper, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        	timeToEndPoint = Geometry.timeUntilCircleCollision(endPoint, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        	if (timeToEndPoint<=timeToPivot && timeToEndPoint<=timeToLine){ //we hit the endPoint
        		newVelocityVector = Geometry.reflectCircle(endPoint.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
        	} else if (timeToPivot <= timeToLine) { //we hit the pivot
        		newVelocityVector = Geometry.reflectCircle(pivot.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
        	} else { //we hit the flipper
        		newVelocityVector = Geometry.reflectWall(flipper, ball.getPhysicsVelocity(), this.coR);
        	}
        	
        } else if (state.equals("flipping")){
    		double timeUntilStationary = (Math.PI/2 - this.currentAngle)/this.angularVelocity;
    		double potentialTimeToLine = Geometry.timeUntilRotatingWallCollision(flipper, pivot.getCenter(), this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    		double potentialTimeToEndPoint = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    		if (timeUntilStationary<=potentialTimeToLine && timeUntilStationary<=potentialTimeToEndPoint){ //the flipper becomes stationary before we hit. This is the most complicated scenario
    			LineSegment cloneFlipper = new LineSegment(flipper.p1().x(), flipper.p1().y(), flipper.p2().x(), flipper.p2().y());
    			Circle cloneEndpoint = new Circle(endPoint.getCenter().x(), endPoint.getCenter().y(), 0.0);
    			Geometry.rotateAround(cloneEndpoint, pivot.getCenter(), new Angle(Math.PI/2 - this.currentAngle));
    			Geometry.rotateAround(cloneFlipper, pivot.getCenter(), new Angle(Math.PI/2 - this.currentAngle));
    			//we have now rotated our clone flipper into the flipped position, and can now reflect the ball off of the stationary flipped flipper
    			timeToLine = Geometry.timeUntilWallCollision(cloneFlipper, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    			timeToEndPoint = Geometry.timeUntilCircleCollision(cloneEndpoint, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    			if (timeToEndPoint<=timeToPivot && timeToEndPoint<=timeToLine){ //we hit the clone endPoint
            		newVelocityVector = Geometry.reflectCircle(cloneEndpoint.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else if (timeToPivot <= timeToLine) { //we hit the pivot
            		newVelocityVector = Geometry.reflectCircle(pivot.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else { //we hit the clone flipper
            		newVelocityVector = Geometry.reflectWall(cloneFlipper, ball.getPhysicsVelocity(), this.coR);
            	}
    		} else { //we hit the flipper while it's moving
    			timeToLine = potentialTimeToLine;
    			timeToEndPoint = potentialTimeToEndPoint;
    			if (timeToEndPoint<=timeToPivot && timeToEndPoint<=timeToLine){ //we hit the endPoint
    				newVelocityVector = Geometry.reflectRotatingCircle(endPoint, pivot.getCenter(), this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity(), this.coR);
            	} else if (timeToPivot <= timeToLine) { //we hit the pivot
            		newVelocityVector = Geometry.reflectCircle(pivot.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else { //we hit the flipper LineSegment
            		newVelocityVector = Geometry.reflectRotatingWall(flipper, pivot.getCenter(), this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity(), this.coR);
            	}
    		}
        } else { //state should equal "deflipping"
        	double timeUntilStationary = (this.currentAngle)/this.angularVelocity;
    		double potentialTimeToLine = Geometry.timeUntilRotatingWallCollision(flipper, pivot.getCenter(), -this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    		double potentialTimeToEndPoint = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), -this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    		if (timeUntilStationary<=potentialTimeToLine && timeUntilStationary<=potentialTimeToEndPoint){ //the flipper becomes stationary before we hit. This is the most complicated scenario
    			LineSegment cloneFlipper = new LineSegment(flipper.p1().x(), flipper.p1().y(), flipper.p2().x(), flipper.p2().y());
    			Circle cloneEndpoint = new Circle(endPoint.getCenter().x(), endPoint.getCenter().y(), 0.0);
    			Geometry.rotateAround(cloneEndpoint, pivot.getCenter(), new Angle(0.0 - this.currentAngle));
    			Geometry.rotateAround(cloneFlipper, pivot.getCenter(), new Angle(0.0 - this.currentAngle));
    			//we have now rotated our clone flipper into the flipped position, and can now reflect the ball off of the stationary flipped flipper
    			timeToLine = Geometry.timeUntilWallCollision(cloneFlipper, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    			timeToEndPoint = Geometry.timeUntilCircleCollision(cloneEndpoint, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    			if (timeToEndPoint<=timeToPivot && timeToEndPoint<=timeToLine){ //we hit the clone endPoint
            		newVelocityVector = Geometry.reflectCircle(cloneEndpoint.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else if (timeToPivot <= timeToLine) { //we hit the pivot
            		newVelocityVector = Geometry.reflectCircle(pivot.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else { //we hit the clone flipper
            		newVelocityVector = Geometry.reflectWall(cloneFlipper, ball.getPhysicsVelocity(), this.coR);
            	}
    		} else { //we hit the flipper while it's moving
    			timeToLine = potentialTimeToLine;
    			timeToEndPoint = potentialTimeToEndPoint;
    			if (timeToEndPoint<=timeToPivot && timeToEndPoint<=timeToLine){ //we hit the endPoint
    				newVelocityVector = Geometry.reflectRotatingCircle(endPoint, pivot.getCenter(), -this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity(), this.coR);
            	} else if (timeToPivot <= timeToLine) { //we hit the pivot
            		newVelocityVector = Geometry.reflectCircle(pivot.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else { //we hit the flipper LineSegment
            		newVelocityVector = Geometry.reflectRotatingWall(flipper, pivot.getCenter(), -this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity(), this.coR);
            	}
    		}
        }
        ball.setPhysicsVelocity(newVelocityVector);
        this.trigger();
    }
    
    /**
     * 
     * @return orientation of the flipper
     */
    public int getOrientation(){
       return orientation; 
    }
    

    
    /**
     * @return list of gadgets that are fired when this gadget is triggered
     */
    public List<Gadget> getGadgetsToFire(){
        return new ArrayList<Gadget>(gadgetsToFire);
    }
    
    /**
     * adds gadget to gadgets to be fired when this gadget is triggered
     * @param gadget gadget to be added to the list of gadgets that are fired when this
     *          gadget is triggered
     */
    public void addGadgetToFire(Gadget gadget){
        gadgetsToFire.add(gadget);
    }
    
    /**
     * 
     * @return current state of flipper
     */
    public String getState(){
        return new String(state);
    }
    
    /**
     * @return name of this gadget
     */
    public String getName(){
        return new String(name);
    }
    
    /**
     * @return position of the gadget
     */
    @Override
    public Vect getPosition(){
        return new Vect(origin.x(),origin.y());
    }
    
    /**
     * 
     * @return the pivot circle in the normal xy coordinate system
     */
    public Circle getNormalPivot(){
    	double pivotX = this.pivot.getCenter().x();
    	double pivotY = 20-this.pivot.getCenter().y();
    	return new Circle(pivotX, pivotY, 0);
    }
    
    /**
     * 
     * @return the endPoint circle in the normal xy coordinate system
     */
    public Circle getNormalEndpt(){
    	double endPointX = this.endPoint.getCenter().x();
    	double endPointY = 20-this.endPoint.getCenter().y();
    	return new Circle(endPointX, endPointY, 0);
    }
    
    /**
     * @return String representation of the flipper
     */
    @Override
    public String toString(){
        if(state.equals("initial")){
            if(orientation == 0){
                return "||  ";
            }
            else if(orientation == 90){
                return "- - ";
            }
            else if(orientation == 180){
                return "  ||";
            }
            else{
                return " - -";
            }
            
        }
        else{
            if(orientation == 0){
                return "- - ";
            }
            else if(orientation == 90){
                return "  ||";
            }
            else if(orientation == 180){
                return " - -";
            }
            else{
                return "||  ";
            }
        }
    }
    
    /**
     * check representation
     */
    private void checkRep(){
        assertTrue(name.length() > 0);
        assertTrue(state.equals("initial") || state.equals("flipping") || state.equals("flipped") || state.equals("deflipping"));
    }

    @Override
    public String getGadgetType() {
       
        return "Left Flipper";
    }
    public String getLeftFlipperGraphicsInfo(){
        String name = this.name;
        String x = String.valueOf(this.origin.x());
        String y = String.valueOf(this.origin.y());
        String orientation = String.valueOf(this.orientation);
        String currentAngle = String.valueOf(this.currentAngle);
        
        return "Left" + " " + name + " " + x + " " + y + " " + orientation + " " + currentAngle; 
    }
    
}
