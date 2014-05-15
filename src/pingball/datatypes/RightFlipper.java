package pingball.datatypes;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class RightFlipper implements Gadget{
    
    private final int boxLength;
    private final double coR;
    private int orientation;
    private final String name;
    private LineSegment flipper;
    private List<Gadget> gadgetsToFire;
    private String state = "initial";//not triggered yet
    private double angularVelocity;
    private final Vect origin;
    private Circle pivot;
    private Circle endPoint;
	private double currentAngle;
	private Circle initialSpot;
	private LineSegment initialFlipper;
	private Circle flippedSpot;
	private LineSegment flippedFlipper;
	private int x;
	private int y;
    
    //Rep invariant
    //if orientation == 0, then lineSegment is at right of bounding box initially
    //if orientation == 90, then lineSegment is at bottom of bounding box initially
    //if orientation == 180, then lineSegment is at left of bounding box initially
    //if orientation == 270, then lineSegment is at top of bounding box initially
    //Abstraction Function
    //lineSegment represents flipper that rotates
    //circularBumpers represent corners of the flipper
    
    public RightFlipper(String name, int x, int y,int orientation){
        this.name = name;
        this.x = x;
        this.y = y;
        this.boxLength = 2;
        this.coR = 0.95;
        this.orientation = orientation;
        this.angularVelocity = (-1)*Math.toRadians(1080.0); //*(-1) because a right flipper flips clockwise
        this.origin = new Vect(x,y);
        this.gadgetsToFire = new ArrayList<Gadget>();
        
        if(orientation == 0){
            this.flipper = new LineSegment(x+boxLength,20-y,x+boxLength,20-(y+boxLength));
            this.pivot = new Circle(x+boxLength,20-y,0);
            this.endPoint = new Circle(x+boxLength,20-(y+boxLength),0);
        }
        else if(orientation == 90){
            this.flipper = new LineSegment(x+boxLength,20-(y+boxLength),x,20-(y+boxLength));
            this.pivot = new Circle(x+boxLength,20-(y+boxLength),0);
            this.endPoint = new Circle(x,20-(y+boxLength),0);
        }
        else if(orientation == 180){
            this.flipper = new LineSegment(x,20-(y+boxLength),x,y);
            this.pivot = new Circle(x,20-(y+boxLength),0);
            this.endPoint = new Circle(x,20-y,0);
        }
        else{ //orientation == 270
            this.flipper = new LineSegment(x,20-y,x+boxLength,20-y);
            this.pivot = new Circle(x,20-y,0);
            this.endPoint = new Circle(x+boxLength,20-y,0);
        }
        double endX = this.endPoint.getCenter().x();
        double endY = 20- this.endPoint.getCenter().y();
        
        this.initialSpot = new Circle(endX, 20-endY, 0.001);
        this.initialFlipper = new LineSegment(pivot.getCenter().x(), pivot.getCenter().y(), initialSpot.getCenter().x(), initialSpot.getCenter().y());
        //create this.flippedSpot below
        double endXInBoundingBox = endX-this.x;
        double endYInBoundingBox = endY-this.y;
        double oppEndXInBoundingBox = boxLength-endXInBoundingBox; //here we find the opposite corner of the bounding box (where the flipped state will be)
        double oppEndYInBoundingBox = boxLength-endYInBoundingBox;
        double flippedX = this.x+oppEndXInBoundingBox;
        double flippedY = this.y+oppEndYInBoundingBox;
        
        this.flippedSpot = new Circle(flippedX, 20-flippedY, 0.001);
        this.flippedFlipper = new LineSegment(pivot.getCenter().x(), pivot.getCenter().y(), flippedSpot.getCenter().x(), flippedSpot.getCenter().y());
        
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
     * changes state, which affects update()
     */
    @Override
    public void action() {
        if(state.equals("initial")){
        	state = "flipping";
        } else if (state.equals("flipped")){
        	state = "deflipping";
        } else if (state.equals("flipping")){
        	state = "deflipping";
        } else {//state = deflipping
        	state = "flipping";
        }
       // checkRep();
    }

	public void update(double timestep) {
		if (this.state.equals("flipping")){
			if(endPoint.getCenter().x()==flippedSpot.getCenter().x() && endPoint.getCenter().y()==flippedSpot.getCenter().y()){ //we are already in the flipped position
				state = "flipped";
			} else {
				double deltaAngle = timestep*angularVelocity;
				double timeUntilStationary = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), angularVelocity, flippedSpot, new Vect(0,0));
				if (timestep>=timeUntilStationary){ //we will stop rotating during the timestep
					endPoint = new Circle(flippedSpot.getCenter().x(), flippedSpot.getCenter().y(), 0.0);
					flipper = new LineSegment(flippedFlipper.p1().x(), flippedFlipper.p1().y(), flippedFlipper.p2().x(), flippedFlipper.p2().y());
					state = "flipped";
				} else {
					endPoint = Geometry.rotateAround(endPoint, pivot.getCenter(), new Angle(deltaAngle));
					flipper = Geometry.rotateAround(flipper, pivot.getCenter(), new Angle(deltaAngle));
				}
			}
		} else if (this.state.equals("deflipping")){
			if(endPoint.getCenter().x()==initialSpot.getCenter().x() && endPoint.getCenter().y()==initialSpot.getCenter().y()){ //we are already in the initial position
				state = "initial";
			} else {
				double deltaAngle = timestep*(-1)*angularVelocity;
				double timeUntilStationary = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), -angularVelocity, initialSpot, new Vect(0,0));
				if (timestep>=timeUntilStationary){ //we will stop rotating during the timestep
					endPoint = new Circle(initialSpot.getCenter().x(), initialSpot.getCenter().y(), 0.0);
					flipper = new LineSegment(initialFlipper.p1().x(), initialFlipper.p1().y(), initialFlipper.p2().x(), initialFlipper.p2().y());
					state="initial";
				} else {
					endPoint = Geometry.rotateAround(endPoint, pivot.getCenter(), new Angle(deltaAngle));
					flipper = Geometry.rotateAround(flipper, pivot.getCenter(), new Angle(deltaAngle));
				}
			}
		}
		double pivotX = pivot.getCenter().x();
		double pivotY = pivot.getCenter().y();
		double initX = initialSpot.getCenter().x();
		double initY = initialSpot.getCenter().y();
		double endX = endPoint.getCenter().x();
		double endY = endPoint.getCenter().y();
		Vect initVect = new Vect(initX-pivotX, initY-pivotY);
		Vect endVect = new Vect(endX-pivotX, endY-pivotY);
		Angle initAngle = initVect.angle();
		Angle endAngle = endVect.angle();
		double currentAngle = Math.abs((endAngle.minus(initAngle)).radians());
		if (currentAngle>Math.PI/2){
			currentAngle = Math.PI*2-currentAngle;
		}
		this.currentAngle = (-1)*currentAngle;
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
        		timeUntilStationary = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), angularVelocity, flippedSpot, new Vect(0,0));
        		potentialTimeToLine = Geometry.timeUntilRotatingWallCollision(flipper, pivot.getCenter(), angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        		potentialTimeToEndPoint = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        	} else { //state should equal "deflipping"
        		timeUntilStationary = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), (-1)*angularVelocity, initialSpot, new Vect(0,0));
        		potentialTimeToLine = Geometry.timeUntilRotatingWallCollision(flipper, pivot.getCenter(), (-1)*angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        		potentialTimeToEndPoint = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), (-1)*this.angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        	}
        	
        	if (timeUntilStationary<=potentialTimeToLine && timeUntilStationary<=potentialTimeToEndPoint){ //the flipper becomes stationary before we hit. This is the most complicated scenario
    			if (state.equals("flipping")){
        			timeToLine = Geometry.timeUntilWallCollision(flippedFlipper, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        			timeToEndPoint = Geometry.timeUntilCircleCollision(flippedSpot, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    			} else { //state == "deflipping"
    				timeToLine = Geometry.timeUntilWallCollision(initialFlipper, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        			timeToEndPoint = Geometry.timeUntilCircleCollision(initialSpot, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    			}
        	} else { //the flipper is moving while we hit it
        		timeToLine = potentialTimeToLine;
        		timeToEndPoint = potentialTimeToEndPoint;
        	}
        }
    	double minMovingTime = Math.min(timeToEndPoint, timeToLine);
    	return Math.min(minMovingTime, timeToPivot);
    }
    
    /**
     * reflects the ball off gadget, updates ball's velocity and triggers this gadget
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
    		double timeUntilStationary = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), angularVelocity, flippedSpot, new Vect(0,0));
    		double potentialTimeToLine = Geometry.timeUntilRotatingWallCollision(flipper, pivot.getCenter(), angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    		double potentialTimeToEndPoint = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    		if (timeUntilStationary<=potentialTimeToLine && timeUntilStationary<=potentialTimeToEndPoint){ //the flipper becomes stationary before we hit. This is the most complicated scenario
    			timeToLine = Geometry.timeUntilWallCollision(flippedFlipper, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    			timeToEndPoint = Geometry.timeUntilCircleCollision(flippedSpot, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    			if (timeToEndPoint<=timeToPivot && timeToEndPoint<=timeToLine){ //we hit the endPoint
            		newVelocityVector = Geometry.reflectCircle(flippedSpot.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else if (timeToPivot <= timeToLine) { //we hit the pivot
            		newVelocityVector = Geometry.reflectCircle(pivot.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else { //we hit the flipper
            		newVelocityVector = Geometry.reflectWall(flippedFlipper, ball.getPhysicsVelocity(), this.coR);
            	}
    		} else { //we hit the flipper while it's moving
    			timeToLine = potentialTimeToLine;
    			timeToEndPoint = potentialTimeToEndPoint;
    			if (timeToEndPoint<=timeToPivot && timeToEndPoint<=timeToLine){ //we hit the endPoint
    				newVelocityVector = Geometry.reflectRotatingCircle(endPoint, pivot.getCenter(), angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity(), this.coR);
            	} else if (timeToPivot <= timeToLine) { //we hit the pivot
            		newVelocityVector = Geometry.reflectCircle(pivot.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else { //we hit the flipper LineSegment
            		newVelocityVector = Geometry.reflectRotatingWall(flipper, pivot.getCenter(), angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity(), this.coR);
            	}
    		}
        } else { //state should equal "deflipping"
    		double timeUntilStationary = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), -angularVelocity, initialSpot, new Vect(0,0));
    		double potentialTimeToLine = Geometry.timeUntilRotatingWallCollision(flipper, pivot.getCenter(), -angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    		double potentialTimeToEndPoint = Geometry.timeUntilRotatingCircleCollision(endPoint, pivot.getCenter(), -angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    		if (timeUntilStationary<=potentialTimeToLine && timeUntilStationary<=potentialTimeToEndPoint){ //the flipper becomes stationary before we hit. This is the most complicated scenario
    			timeToLine = Geometry.timeUntilWallCollision(initialFlipper, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    			timeToEndPoint = Geometry.timeUntilCircleCollision(initialSpot, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
    			if (timeToEndPoint<=timeToPivot && timeToEndPoint<=timeToLine){ //we hit the  endPoint
            		newVelocityVector = Geometry.reflectCircle(initialSpot.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else if (timeToPivot <= timeToLine) { //we hit the pivot
            		newVelocityVector = Geometry.reflectCircle(pivot.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else { //we hit the clone flipper
            		newVelocityVector = Geometry.reflectWall(initialFlipper, ball.getPhysicsVelocity(), this.coR);
            	}
    		} else { //we hit the flipper while it's moving
    			timeToLine = potentialTimeToLine;
    			timeToEndPoint = potentialTimeToEndPoint;
    			if (timeToEndPoint<=timeToPivot && timeToEndPoint<=timeToLine){ //we hit the endPoint
    				newVelocityVector = Geometry.reflectRotatingCircle(endPoint, pivot.getCenter(), -angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity(), this.coR);
            	} else if (timeToPivot <= timeToLine) { //we hit the pivot
            		newVelocityVector = Geometry.reflectCircle(pivot.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity(), this.coR);
            	} else { //we hit the flipper LineSegment
            		newVelocityVector = Geometry.reflectRotatingWall(flipper, pivot.getCenter(), -angularVelocity, ball.getPhysicsCircle(), ball.getPhysicsVelocity(), this.coR);
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
    	if (!gadgetsToFire.contains(gadget)){
    		gadgetsToFire.add(gadget);
    	}
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
     * @return current state of flipper
     */
    public String getState(){
        return new String(state);
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
                return "  ||";
            }
            else if(orientation == 90){
                return " - -";
            }
            else if(orientation == 180){
                return "||  ";
            }
            else{
                return "- - ";
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
        return "Right Flipper";
    }
    public double getCurrentAngle() {
        return currentAngle;
    }
    public String getRightFlipperGraphicsInfo(){
        String name = this.name;
        String x = String.valueOf(this.origin.x());
        String y = String.valueOf(this.origin.y());
        String orientation = String.valueOf(this.orientation);
        String currentAngle = String.valueOf(this.currentAngle);
        
        return "Right"+ " " + name + " " + x + " " + y + " " + orientation + " " + currentAngle; 
    }
}
