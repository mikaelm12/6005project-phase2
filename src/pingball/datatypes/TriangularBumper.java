package pingball.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import static org.junit.Assert.*;


public class TriangularBumper implements Gadget{
    
    private final int sideLength;
    private final double coR;
    private final int orientation;
    private final LineSegment sideA;
    private final LineSegment sideB;
    private final LineSegment hypotenuse;
    private final String name;
    private List<Gadget> gadgetsToFire;
    private final LineSegment[] edges = new LineSegment[3];
    private final Vect origin;
    private final CircularBumper cornerA;
    private final CircularBumper cornerB;
    private final CircularBumper cornerC;
    private List<CircularBumper> corners;
    
    //Rep invariant:
    //orientation == 0 || orientation == 90 || orientation == 180 || orientation == 270
    //name!=null && name.length>0
    //triangle is within board
    //Abstraction Function:
    //lineSegments represent sides of a triangle
    //circularBumpers represent corners of the triangle
    
    
    public TriangularBumper(String name, int x, int y, int orientation){
        this.name = name;
        this.sideLength = 1;
        this.coR = 1.0;
        this.orientation = orientation;
        this.gadgetsToFire = new ArrayList<Gadget>();
        this.origin = new Vect(x,y);
        
        //create triangle edges and corners
        if(orientation == 0){
            this.sideA = new LineSegment(x,y,x+sideLength,y);
            this.sideB = new LineSegment(x,y,x,y+sideLength);
            this.hypotenuse = new LineSegment(x,y+sideLength,x+sideLength,y);
            this.cornerA = new CircularBumper("cornerA",x,y,0);
            this.cornerB = new CircularBumper("cornerB",x+sideLength,y,0);
            this.cornerC = new CircularBumper("cornerC",x,y+sideLength,0);
        }
        else if(orientation == 90){
            this.sideA = new LineSegment(x,y,x,y+sideLength);
            this.sideB = new LineSegment(x,y+sideLength,x+sideLength,y+sideLength);
            this.hypotenuse = new LineSegment(x,y,x+sideLength,y+sideLength);
            this.cornerA = new CircularBumper("cornerA",x,y,0);
            this.cornerB = new CircularBumper("cornerB",x,y+sideLength,0);
            this.cornerC = new CircularBumper("cornerC",x+sideLength,y+sideLength,0);
        }
        else if(orientation == 180){
            this.sideA = new LineSegment(x+sideLength,y,x+sideLength,y+sideLength);
            this.sideB = new LineSegment(x,y+sideLength,x+sideLength,y+sideLength);
            this.hypotenuse = new LineSegment(x,y+sideLength,x+sideLength,y);
            this.cornerA = new CircularBumper("cornerA",x+sideLength,y,0);
            this.cornerB = new CircularBumper("cornerB",x+sideLength,y+sideLength,0);
            this.cornerC = new CircularBumper("cornerC",x,y+sideLength,0);
        }
        else{ //orientation = 270
            this.sideA = new LineSegment(x,y,x+sideLength,y);
            this.sideB = new LineSegment(x+1,y,x+sideLength,y+sideLength);
            this.hypotenuse = new LineSegment(x,y,x+sideLength,y+sideLength);
            this.cornerA = new CircularBumper("cornerA",x,y,0);
            this.cornerB = new CircularBumper("cornerB",x+sideLength,y,0);
            this.cornerC = new CircularBumper("cornerC",x+sideLength,y+sideLength,0);
        }
        
        this.corners = new ArrayList<CircularBumper>(Arrays.asList(cornerA,cornerB,cornerC));
        
        //add sides to list of edges
        edges[0] = sideA;
        edges[1] = sideB;
        edges[2] = hypotenuse;
        
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
     * no action i.e does not respond to any trigger
     */
    @Override
    public void action() {   
    }
    
    /**
     * @return coefficient of Reflection
     */
    @Override
    public double getCoR() {
        return new Double(coR).doubleValue();
    }
    
    /**
     * computes time until ball collides with bumper
     * @param ball to collide with
     * @return time until ball collides with bumper
     */
    @Override
    public double timeUntilCollision(Ball ball) {
        double closestTimeToCollision = Double.POSITIVE_INFINITY; //default value
        
        //check for closest time to collision among edges
        for (LineSegment edge : edges) {
            double timeToEdge = Geometry.timeUntilWallCollision(edge, ball.getCircle(), ball.getVelocity());
            if(timeToEdge < closestTimeToCollision){
                closestTimeToCollision = timeToEdge;
            }
        }
      //check for closest time to collision among corners
        for (CircularBumper corner : corners) {
            double timeToCorner = Geometry.timeUntilCircleCollision(corner.getCircle(), 
                                                                    ball.getCircle(), ball.getVelocity());
            //if time nearest corner< time to edge, update closest time
            if(timeToCorner < closestTimeToCollision){
                closestTimeToCollision = timeToCorner;
            }
        }
        
        checkRep();
        return closestTimeToCollision;
    }
    
    /**
     * reflects the ball off gadget, updates its velocity and triggers this gadget
     * @param ball to be reflected
     */
    @Override
    public void reflectOffGadget(Ball ball){
        LineSegment edgeShortestTimeToCollision = null;
        CircularBumper closestCorner = null; 
        double closestTimeToCollision = Double.POSITIVE_INFINITY; //default value since double has to be initialized
        
        //find nearest edge
        for (LineSegment edge : edges) {
            double timeToEdge = Geometry.timeUntilWallCollision(edge, ball.getCircle(), ball.getVelocity());
            if(timeToEdge < closestTimeToCollision){
                closestTimeToCollision = timeToEdge;
                edgeShortestTimeToCollision = edge;
            }
        }
        //find nearest corner
        for (CircularBumper corner : corners) {
            
            double timeToCorner = Geometry.timeUntilCircleCollision(corner.getCircle(), 
                                                                    ball.getCircle(), ball.getVelocity());
            //if corner closer than nearest edge, update
            if(timeToCorner < closestTimeToCollision){
                closestTimeToCollision = timeToCorner;
                closestCorner = corner;
            }
        }
        Vect newVelocityVector;
        //reflect using appropriate corner or edge
        if(closestCorner == null){
            newVelocityVector = Geometry.reflectWall(edgeShortestTimeToCollision, ball.getVelocity(), coR);
        }
        else{
            newVelocityVector = Geometry.reflectCircle(closestCorner.getCircle().getCenter(), ball.getCircle().getCenter(),
                                                            ball.getVelocity());
        }
        //set the velocity of the ball and trigger connected gadgets
        ball.setVelocity(newVelocityVector);
        this.trigger();
        
        checkRep();
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
     * @return a String representation of TriangularBumper in the orientation specified
     */
    @Override
    public String toString(){
        if(orientation == 0 || orientation == 180){
            return "/";
        }else{
            return "\\";
        }
    }
    
    /**
     * check representation
     */
    private void checkRep(){
        assertTrue(name.length() > 0);
        assertTrue(orientation == 0 || orientation == 90 || orientation == 180 || orientation == 270);
        for (LineSegment edge : edges) {
            assertTrue(edge.p1().x() >= 0 && edge.p1().x() <= 20);
            assertTrue(edge.p2().y() >= 0 && edge.p2().y() <= 20);  
        }
    }


    
}
