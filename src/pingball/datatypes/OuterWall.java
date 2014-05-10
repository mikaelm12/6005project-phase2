package pingball.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import static org.junit.Assert.*;

public class OuterWall implements Gadget{
    
    private final LineSegment wall;
    private final double coR;
    private boolean solid;
    private final String name;
    private List<Gadget> gadgetsToFire;
    private Circle top;
    private Circle bottom;
    private List<Circle> corners;
    
    //Rep invariant:
    //name.length>0
    //wall located on boundaries of board
    //gadgetsToFire.size == 0
    //Abstraction function:
    //represents wall of board. CircularBumpers top and bottom represents corners of the board
    
    public OuterWall(String name, int x0 , int y0, int x1, int y1, boolean solid){
        this.wall = new LineSegment(x0, 20-y0, x1, 20-y1);
        this.coR = 1.0;
        this.solid = solid;
        this.name = name;
        this.gadgetsToFire = new ArrayList<Gadget>();
        
        this.top = new Circle(x0,20-y0,0);
        this.bottom = new Circle(x1,20-y1,0);
        
        this.corners = new ArrayList<Circle>(Arrays.asList(top,bottom));
        
        checkRep();
    }
    
    
    /**
     * no action i.e does not respond to any trigger
     */
    @Override
    public void action() {
        
    }
    
    /**
     * @return the coefficient of Reflection
     */
    @Override
    public double getCoR() {
        return new Double(coR).doubleValue();
    }
    
    /**
     * computes time until ball collides with wall
     * @param ball to collide with
     * @return time until ball collides with wall
     */
    @Override
    public double timeUntilPhysicsCollision(Ball ball) {
        double closestTimeToCollision = Geometry.timeUntilWallCollision(wall, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
        for (Circle corner : corners) {
            double timeToCorner = Geometry.timeUntilCircleCollision(corner, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
            //if time nearest corner< time to wall, update closest time
            if(timeToCorner < closestTimeToCollision){
                closestTimeToCollision = timeToCorner;
            }
        }
        return closestTimeToCollision;
    }
    
    /**
     * reflects the ball off wall,updates ball's velocity and triggers this gadget
     * @param ball to be reflected
     */
    @Override
    public void reflectOff(Ball ball){
        //reflect only if solid
        if(solid){
            double closestTimeToCollision = Geometry.timeUntilWallCollision(wall, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
            Circle closestCorner = null;
            for (Circle corner : corners) {
                
                double timeToCorner = Geometry.timeUntilCircleCollision(corner, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
                //if corner closer than nearest edge, update
                if(timeToCorner < closestTimeToCollision){
                    closestTimeToCollision = timeToCorner;
                    closestCorner = corner;
                }
            }
            Vect newVelocityVector;
            //reflect against corner if corner!=null, else reflect against line
            if(closestCorner != null){ //we are reflecting off a corner
                newVelocityVector = Geometry.reflectCircle(closestCorner.getCenter(), ball.getPhysicsCircle().getCenter(), ball.getPhysicsVelocity());
            }else{ //we are reflecting off the LineSegment
                newVelocityVector = Geometry.reflectWall(wall, ball.getPhysicsVelocity(), coR);
            }
            ball.setPhysicsVelocity(newVelocityVector);
            
            checkRep();
        }  
        
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
        checkRep();
    }
    
    /**
     * sets the solidity of the wall
     * @param isSolid
     */
    public void setWallSolidity(boolean isSolid){
        solid = isSolid;
    }
    
    /**
     * Determines if the wall is solid or not (if not, then it has neighbor)
     * @return boolean for solidity
     */
    public boolean isSolid(){
        return solid;
    }
    
    /**
     * @return name of this gadget
     */
    public String getName(){
        return new String(name);
    }
    
    public boolean isVertical(){
        return (wall.p1().x() == wall.p2().x());
    }
    
    /**
     * @return position of the gadget
     */
    @Override
    public Vect getPosition(){
        return new Vect(wall.p1().x(), 20-wall.p1().y());
    }
    
    /**
     * @return String representation of the outer walls
     */
    @Override
    public String toString(){
        String string = new String();
        for (int i = 0; i < 22; i++) {
            string += ".";
        }
        return string;
    }
    
    /**
     * check representation
     */
    private void checkRep(){
        assertTrue(name.length() > 0);
        assertTrue(wall.p1().x() == 0 || wall.p1().x() == 20);
        assertTrue(wall.p1().y() == 0 || wall.p1().y() == 20);
        assertTrue(wall.p2().x() == 0 || wall.p2().x() == 20);
        assertTrue(wall.p2().y() == 0 || wall.p2().y() == 20);
        assertTrue(gadgetsToFire.size() == 0);
    }


    /**
     * This method returns the type of this gadget, Wall
     */
    @Override
    public String getGadgetType() {
        return "Wall";
    }


    
    

}
