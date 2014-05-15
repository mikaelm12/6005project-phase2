package pingball.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.Circle;
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
    
    private final Circle cornerA;
    private final Circle cornerB;
    private final Circle cornerC;
    private List<Circle> corners;
    private final double x;
    private final double y;
    
    public TriangularBumper(String name, int x, int y, int orientation){
        this.name = name;
        this.x = x;
        this.y = y;
        this.sideLength = 1;
        this.coR = 1.0;
        this.orientation = orientation;
        this.gadgetsToFire = new ArrayList<Gadget>();
        
        //create triangle edges and corners
        if(orientation == 0){
            this.sideA = new LineSegment(x,20-y,x+sideLength,20-y);
            this.sideB = new LineSegment(x,20-y,x,20-(y+sideLength));
            this.hypotenuse = new LineSegment(x,20-(y+sideLength),x+sideLength,20-y);
            
            this.cornerA = new Circle(x,20-y,0);
            this.cornerB = new Circle(x+sideLength,20-y,0);
            this.cornerC = new Circle(x,20-(y+sideLength),0);
        }
        else if(orientation == 90){
            this.sideA = new LineSegment(x,20-y,x+sideLength,20-y);
            this.sideB = new LineSegment(x+sideLength,20-(y+sideLength),x+sideLength,20-y);
            this.hypotenuse = new LineSegment(x,20-y,x+sideLength,20-(y+sideLength));
            
            this.cornerA = new Circle(x,20-y,0);
            this.cornerB = new Circle(x+sideLength,20-y,0);
            this.cornerC = new Circle(x+sideLength,20-(y+sideLength),0);
        }
        else if(orientation == 180){
            this.sideA = new LineSegment(x+sideLength,20-y,x+sideLength,20-(y+sideLength));
            this.sideB = new LineSegment(x,20-(y+sideLength),x+sideLength,20-(y+sideLength));
            this.hypotenuse = new LineSegment(x,20-(y+sideLength),x+sideLength,20-y);
            this.cornerA = new Circle(x+sideLength,20-y,0);
            this.cornerB = new Circle(x+sideLength,20-(y+sideLength),0);
            this.cornerC = new Circle(x,20-(y+sideLength),0);
        }
        else{ //orientation = 270
            this.sideA = new LineSegment(x,20-y,x+sideLength,20-y);
            this.sideB = new LineSegment(x+1,20-y,x+sideLength,20-(y+sideLength));
            this.hypotenuse = new LineSegment(x,20-y,x+sideLength,20-(y+sideLength));
            this.cornerA = new Circle(x,20-y,0);
            this.cornerB = new Circle(x+sideLength,20-y,0);
            this.cornerC = new Circle(x+sideLength,20-(y+sideLength),0);
        }
        
        this.corners = new ArrayList<Circle>(Arrays.asList(cornerA,cornerB,cornerC));
        
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
     * reflects the ball off gadget, updates its velocity and triggers this gadget
     * @param ball to be reflected
     */
    @Override
    public void reflectOff(Ball ball){
        LineSegment edgeShortestTimeToCollision = null;
        Circle closestCorner = null; 
        double closestTimeToCollision = Double.POSITIVE_INFINITY; //default value since double has to be initialized
        
        //find nearest edge
        for (LineSegment edge : edges) {
            double timeToEdge = Geometry.timeUntilWallCollision(edge, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
            System.out.println("Edge: timeUntilWallCollision: " + timeToEdge);
            if(timeToEdge < closestTimeToCollision){
                closestTimeToCollision = timeToEdge;
                edgeShortestTimeToCollision = edge;
            }
        }
        //find nearest corner
        for (Circle corner : corners) {
            double timeToCorner = Geometry.timeUntilCircleCollision(corner, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
            System.out.println("Corner: timeUntilCornerCollision: " + timeToCorner);
            //if corner closer than nearest edge, update
            if(timeToCorner < closestTimeToCollision){
                closestTimeToCollision = timeToCorner;
                closestCorner = corner;
            }
        }
        System.out.println("Position: "+ ball.getNormalCircle().getCenter().x() + ", " + ball.getNormalCircle().getCenter().y());
        Vect newVelocityVector;
        //reflect using appropriate corner or edge
        if(closestCorner == null){//we've hit an edge;
            newVelocityVector = Geometry.reflectWall(edgeShortestTimeToCollision, ball.getPhysicsVelocity(), coR);
        }else{//we've hit a corner
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
    
    public int getOrientation(){
    	return this.orientation;
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

    @Override
    public String getGadgetType() {
        return "Triangluar Bumper";
    }
    
//    public List<CircularBumper> getCorners(){
//        return this.corners;
//    }


    
}
