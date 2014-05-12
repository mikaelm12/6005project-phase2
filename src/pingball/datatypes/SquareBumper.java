package pingball.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import static org.junit.Assert.*;


public class SquareBumper implements Gadget{
    
    private final double coR;
    private final int edgeLength;
    private final String name;
    private List<Gadget> gadgetsToFire; 
    
    private final LineSegment physicsTop;
    private final LineSegment physicsRight;
    private final LineSegment physicsBottom;
    private final LineSegment physicsLeft;
    private List<LineSegment> edges; 
    
    private final Circle physicsTopLeft;
    private final Circle physicsTopRight;
    private final Circle physicsBottomRight;
    private final Circle physicsBottomLeft;
    private List<Circle> corners;
    private final double x;
    private final double y;
	
    
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
        this.x = x;
        this.y = y;
        //create 4 edges of the bumper
        this.physicsTop = new LineSegment(x,20-y,x+edgeLength,20-y);
        this.physicsRight = new LineSegment(x+edgeLength,20-y,x+edgeLength,20-(y+edgeLength));
        this.physicsBottom = new LineSegment(x,20-(y+edgeLength),x+edgeLength,20-(y+edgeLength));
        this.physicsLeft = new LineSegment(x,20-y,x,20-(y+edgeLength));
        this.edges = new ArrayList<LineSegment>(Arrays.asList(physicsTop, physicsRight, physicsBottom, physicsLeft));
        
        //create 4 corners of radius 0
        physicsTopLeft = new Circle(x,20-y,0);
        physicsTopRight = new Circle(x+edgeLength,20-y,0);
        physicsBottomRight = new Circle(x+edgeLength,20-(y+edgeLength),0);
        physicsBottomLeft = new Circle(x,20-(y+edgeLength),0);
        this.corners = new ArrayList<Circle>(Arrays.asList(physicsTopLeft,physicsTopRight,physicsBottomRight,physicsBottomLeft));
        
        //create list of gadgets connected to this bumper
        this.gadgetsToFire = new ArrayList<Gadget>();
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
    public double timeUntilPhysicsCollision(Ball ball) {
        double closestTimeToCollision = Double.POSITIVE_INFINITY; //default value
        
        //check for closest time to collision among edges
        for (LineSegment edge : edges) {
            double timeToEdge = Geometry.timeUntilWallCollision(edge, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
            if(timeToEdge < closestTimeToCollision){
                closestTimeToCollision = timeToEdge;
            }
        }
      //check for closest time to collision among corners
        for (Circle corner : corners) {
            double timeToCorner = Geometry.timeUntilCircleCollision(corner, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
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
     */
    @Override
    public void reflectOff(Ball ball){
//    	System.out.println("Reflecting Off of "+this.getName());
        LineSegment edgeShortestTimeToCollision = null;
        Circle closestCorner = null; 
        double closestTimeToCollision = Double.POSITIVE_INFINITY; //default value since double has to be initialized
        
        //find nearest edge
        for (LineSegment edge : edges) {
        	double timeToEdge = Geometry.timeUntilWallCollision(edge, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        	//System.out.println("Edge: timeUntilWallCollision: " + timeToEdge);
            if(timeToEdge <= closestTimeToCollision){
                closestTimeToCollision = timeToEdge;
                edgeShortestTimeToCollision = edge;
            }
        }
        //find nearest corner
        for (Circle corner : corners) {
            double timeToCorner = Geometry.timeUntilCircleCollision(corner, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
            //System.out.println("Corner: timeUntilCornerCollision: " + timeToCorner);
            //if corner closer than nearest edge, update
            if(timeToCorner <= closestTimeToCollision){
                closestTimeToCollision = timeToCorner;
                closestCorner = corner;
            }
        }
//        System.out.println("Position: "+ ball.getNormalCircle().getCenter().x() + ", " + ball.getNormalCircle().getCenter().y());
        Vect newVelocityVector;
        //reflect using appropriate corner or edge
        if(closestCorner == null){ //we've hit an edge
//        	System.out.println(edgeShortestTimeToCollision);
            newVelocityVector = Geometry.reflectWall(edgeShortestTimeToCollision, ball.getPhysicsVelocity(), coR);
        }
        else{//we've hit a corner
            newVelocityVector = Geometry.reflectCircle(closestCorner.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity());
        }
        //set the velocity of the ball and trigger connected gadgets
        ball.setPhysicsVelocity(newVelocityVector);
        
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
        return new Vect(this.x,this.y);
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

    @Override
    public String getGadgetType() {
        return "Square Bumper";
    }


}
