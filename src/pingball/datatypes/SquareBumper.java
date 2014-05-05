package pingball.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import static org.junit.Assert.*;


public class SquareBumper implements Gadget{
    
    private final double coR;
    private final int edgeLength;
    private final LineSegment top;
    private final LineSegment right;
    private final LineSegment bottom;
    private final LineSegment left;
    private final LineSegment[] edges = new LineSegment[4];
    private final String name;
    private List<Gadget> gadgetsToFire; 
    private final CircularBumper topLeft;
    private final CircularBumper topRight;
    private final CircularBumper bottomRight;
    private final CircularBumper bottomLeft;
    private List<CircularBumper> corners;
    
    //Rep invariant:
    //name!=null && name.length>0
    //left != null && top != null && right != null && bottom != null
    //coR = 1.0, edgeLength = 1.0
    //each edge is within the board
    //Abstraction Function:
    //the four lineSegments represent the four sides of a square
    //the four circularBumpers represent the corners of the square
    
    public SquareBumper(String name, int x, int y){
        this.name = name;
        this.edgeLength = 1;
        this.coR = 1.0;
        this.top = new LineSegment(x,y,x+edgeLength,y);
        this.right = new LineSegment(x+edgeLength,y,x+edgeLength,y+edgeLength);
        this.bottom = new LineSegment(x,y+edgeLength,x+edgeLength,y+edgeLength);
        this.left = new LineSegment(x,y,x,y+edgeLength);
        
        //create 4 corners using circularBumpers of radius 0
        topLeft = new CircularBumper("topLeft",x,y,0);
        topRight = new CircularBumper("topRight",x+edgeLength,y,0);
        bottomRight = new CircularBumper("bottomRight",x+edgeLength,y+edgeLength,0);
        bottomLeft = new CircularBumper("topLeft",x,y+edgeLength,0);
        
        //create list of 4 corners of the bumper
        this.corners = new ArrayList<CircularBumper>(Arrays.asList(topLeft,topRight,bottomRight,bottomLeft));
        
        //create list of gadgets connected to this bumper
        this.gadgetsToFire = new ArrayList<Gadget>();
        
        //add the sides to list of edges
        edges[0] = left;
        edges[1] = left;
        edges[2] = right;
        edges[3] = bottom;
        
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
    public void action(){
        
    }
    
    /**
     * @return the coefficient of Reflection
     */
    @Override
    public double getCoR(){
        return new Double(this.coR).doubleValue();
    }
    
    
    /**
     * @param ball to collide
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
     * reflects the ball off gadget and updates its velocity and triggers this gadget
     * @param ball to be reflected
     * @throws Exception 
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
        return new Vect(top.p1().x(),top.p1().y());
    }
    
    /**
     * @return string representation of the SquareBumper
     */
    @Override
    public String toString(){
        return "#";
    }
    
    /**
     * checks rep invariant
     */
    private void checkRep(){
        assertTrue(name.length() > 0);
        for (LineSegment edge : edges) {
            assertTrue(edge.p1().x() >=0 && edge.p1().y() >= 0);
            assertTrue(edge.p1().x() <=20 && edge.p1().y() <=20);
        }
        
        assertTrue(coR == 1.0 && edgeLength == 1.0);
        
    }


}
