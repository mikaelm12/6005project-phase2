package pingball.datatypes;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import static org.junit.Assert.*;

public class LeftFlipper implements Gadget{
    
    private final int boxLength;
    private final double coR;
    private int orientation;
    private LineSegment flipper;
    private final Angle rotationAngle;
    private final Angle reverseRotationAngle;
    private final String name;
    private List<Gadget> gadgetsToFire;
    private String state = "initial"; //not triggered yet
    private double angularVelocity;
    private final Vect origin;
    private CircularBumper top;
    private CircularBumper bottom;
    
    //Rep invariant:
    //box within board
    //if orientation == 0, then lineSegment is at left of bounding box initially
    //if orientation == 90, then lineSegment is at bottom of bounding box initially
    //if orientation == 180, then lineSegment is at right of bounding box initially
    //if orientation == 270, then lineSegment is at top of bounding box initially
    //Abstraction Function
    //lineSegment represents flipper that rotates
    //circularBumpers represent corners of the flipper
    
    
    public LeftFlipper(String name, int x, int y, int orientation){
        this.name = name;
        this.boxLength = 2;
        this.coR = 0.95;
        this.orientation = orientation;
        this.gadgetsToFire = new ArrayList<Gadget>();
        this.angularVelocity = (1080.0/180)*Math.PI;
        this.rotationAngle = Angle.DEG_90;
        this.reverseRotationAngle = Angle.DEG_270;
        
        
        this.origin = new Vect(x,y);
        
        
        if(orientation == 0){
            this.flipper = new LineSegment(x,y,x,y+boxLength);
            this.top = new CircularBumper("top",x,y,0);
            this.bottom = new CircularBumper("bottom",x,y+boxLength,0);
            
        }
        else if(orientation == 90){
            this.flipper = new LineSegment(x+boxLength,y,x,y);
            this.top = new CircularBumper("top",x+boxLength,y,0);
            this.bottom = new CircularBumper("bottom",x,y,0);
            
        }
        else if(orientation == 180){
            this.flipper = new LineSegment(x+boxLength,y+boxLength,x+boxLength,y);
            this.top = new CircularBumper("top",x+boxLength,y+boxLength,0);
            this.bottom = new CircularBumper("bottom",x+boxLength,y,0);
            
        }
        else{ //orientation == 270
            this.flipper = new LineSegment(x,y+boxLength,x+boxLength,y+2);
            this.top = new CircularBumper("top",x,y+boxLength,0);
            this.bottom = new CircularBumper("bottom",x+boxLength,y+boxLength,0);
           
        }
        
        checkRep();
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
     * rotates 90degrees when triggered
     */
    @Override
    public void  action() {
        if(state.equals("initial")){
            //change to final state
            this.flipper = Geometry.rotateAround(flipper, flipper.p1(), reverseRotationAngle);
            this.bottom = new CircularBumper("bottom",(int) flipper.p2().x(),(int) flipper.p2().y(),0);
            state = "final";
        }
        else{
            //change to initial state
            this.flipper = Geometry.rotateAround(flipper, flipper.p1(), rotationAngle);
            this.bottom = new CircularBumper("bottom",(int) flipper.p2().x(),(int) flipper.p2().y(),0);
            state = "initial";
        }
        checkRep();
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
    public double timeUntilCollision(Ball ball) {
        //define +angularVelocity as being clockwise rotation
        double timeToLine;
        if(state.equals("initial")){
            //if flipper is constantly rotating use angular velocity, else set velocity to 0
            if(gadgetsToFire.contains(this)){
                timeToLine = Geometry.timeUntilRotatingWallCollision(flipper, flipper.p1(), -angularVelocity, 
                        ball.getCircle(), ball.getVelocity());
            }else{
                timeToLine = Geometry.timeUntilRotatingWallCollision(flipper, flipper.p1(), 0, 
                        ball.getCircle(), ball.getVelocity());
            }
            
        }else{
            timeToLine = Geometry.timeUntilRotatingWallCollision(flipper, flipper.p1(), angularVelocity, 
                    ball.getCircle(), ball.getVelocity());
        }
        double time;
        double timeToTop = Geometry.timeUntilCircleCollision(top.getCircle(), 
                                                            ball.getCircle(), ball.getVelocity());
        double timeToBottom = Geometry.timeUntilCircleCollision(bottom.getCircle(), 
                                                                ball.getCircle(), ball.getVelocity());
        
        time = timeToBottom;
        if(timeToTop < timeToBottom && timeToTop < timeToLine){
            time = timeToTop;
        }
        if(timeToLine < timeToTop && timeToLine < timeToBottom){
            time = timeToLine;
        }
        
        return time;
    }
    
    /**
     * reflects the ball off gadget, updates the ball's velocity and triggers this gadget
     * @param ball to be reflected
     */
    @Override
    public void reflectOffGadget(Ball ball){
        Vect newVelocityVector;
        //collision will cause instantaneous rotation of flipper
        //define +angularVelocity as being clockwise rotation
        if(state.equals("initial")){
          //if flipper is constantly rotating use angular velocity, else set velocity to 0
            if(gadgetsToFire.contains(this)){
                newVelocityVector = Geometry.reflectRotatingWall(flipper, flipper.p1(), -angularVelocity, 
                        ball.getCircle(), ball.getVelocity(), coR);
            }else{
                newVelocityVector = Geometry.reflectRotatingWall(flipper, flipper.p1(), 0, 
                        ball.getCircle(), ball.getVelocity(), coR);
            }
            
        }else{
          //if flipper is constantly rotating use angular velocity, else set velocity to 0
            if(gadgetsToFire.contains(this)){
                newVelocityVector = Geometry.reflectRotatingWall(flipper, flipper.p1(), angularVelocity, 
                        ball.getCircle(), ball.getVelocity(), coR);
            }else{
                newVelocityVector = Geometry.reflectRotatingWall(flipper, flipper.p1(), 0, 
                                                            ball.getCircle(), ball.getVelocity(), coR);
            }
        }
        double timeTillTopCollision = top.timeUntilCollision(ball);
        double timeTillBottomCollision = bottom.timeUntilCollision(ball);
        CircularBumper bumper = null;
        double time;
        if(timeTillTopCollision < timeTillBottomCollision){
            time = timeTillTopCollision;
            bumper = top;
        }else{
            time = timeTillBottomCollision;
            bumper = bottom;
        }
        double timeTillLineCollision = Geometry.timeUntilWallCollision(flipper, ball.getCircle(), ball.getVelocity());
        if(time <= timeTillLineCollision){
            newVelocityVector = Geometry.reflectCircle(bumper.getCircle().getCenter(), ball.getCircle().getCenter(), ball.getVelocity(), coR);
        }
        ball.setVelocity(newVelocityVector);
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
        assertTrue(state.equals("initial") || state.equals("final"));
    }



}
