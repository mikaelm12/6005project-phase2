package pingball.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import static org.junit.Assert.*;

/**
 * 
 * @author PeterGithaiga
 * The absorber will only shoot out stored balls one at a time when triggered.
 * 
 */
public class Absorber implements Gadget{
    
    private final int width;
    private final int height;
    private final int coR;
    private final LineSegment top;
    private final LineSegment right;
    private final LineSegment bottom;
    private final LineSegment left;
    private final String name;
    private List<Gadget> gadgetsToFire;
    private final LineSegment[] edges = new LineSegment[4];
    private List<Ball> balls;
    private final CircularBumper topLeft;
    private final CircularBumper topRight;
    private final CircularBumper bottomRight;
    private final CircularBumper bottomLeft;
    private List<CircularBumper> corners;
    
    //Rep invariant:
    //width>0, height>0, name!=null && name.length>0
    //absorber within board
    //Abstraction Function:
    //represents an absorber with width, width, and height, height
    
    public Absorber(String name,int x,int y, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        this.coR = 0;    
        this.top = new LineSegment(x,y,x+width,y);
        this.right = new LineSegment(x+width,y,x+width,y+height);
        this.bottom = new LineSegment(x,y+height,x+width,y+height);
        this.left = new LineSegment(x,y,x,y+height);
        this.gadgetsToFire = new ArrayList<Gadget>();
        this.balls = new ArrayList<Ball>();
        edges[0] = left;
        edges[1] = top;
        edges[2] = right;
        edges[3] = bottom;
        
        //create 4 corners using circularBumpers of radius 0
        topLeft = new CircularBumper("topLeft",x,y,0);
        topRight = new CircularBumper("topRight",x+width,y,0);
        bottomRight = new CircularBumper("bottomRight",x+width,y+height,0);
        bottomLeft = new CircularBumper("topLeft",x,y+height,0);
        
        //create list of 4 corners of the absorber
        this.corners = new ArrayList<CircularBumper>(Arrays.asList(topLeft,topRight,bottomRight,bottomLeft));

        checkRep();
    }
    
    /**
     * 
     * @return width of absorber
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * 
     * @return height of absorber
     */
    public int getHeight(){
        return height;
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
     * shoots out a stored ball when triggered
     */
    @Override
    public void action() {
        //remove the first ball
        if(balls.size() > 1){
            Ball that = balls.get(0);
            that.setVelocity(new Vect(0,-50));//set to ball velocity to 50L/sec straight upwards
            that.setPosition(that.getPosition()[0],top.p2().y()-0.25);//set position to top of absorber
            balls.remove(0);
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
     * compute time until collision
     * @param ball to collide with
     * @return time until ball collides with absorber
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
     * stop ball and store it unmoving in the bottom right hand corner of the absorber
     * @param ball to be reflected
     */
    @Override
    public void  reflectOffGadget(Ball ball){
        ball.setVelocity(new Vect(0,0)); //stop ball
        //set ball center position .25L away from bottom and right wall
        ball.setPosition(bottom.p2().x()-0.25, bottom.p2().y()-0.25);
        balls.add(ball);
        this.trigger();
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
     * @return String representation of the absorber
     */
    @Override 
    public String toString(){
        String string = new String();
        for (int i = 0; i < (width*height); i++) {
            string += "=";
        }
        return string;
    }
    
    /**
     * check representation
     */
    private void checkRep(){
        assertTrue(name.length() > 0);
        assertTrue(width > 0 && height > 0);
        for (LineSegment edge : edges) {
            assertTrue(edge.p1().x() >=0 && edge.p1().y() >= 0);
            assertTrue(edge.p1().x() <=20 && edge.p1().y() <=20);
        }
    }


}
