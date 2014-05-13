package pingball.datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import static org.junit.Assert.*;

/**
 * The absorber will only shoot out stored balls one at a time when triggered.
 * 
 */
public class Absorber implements Gadget{
    
    private final int width;
    private final int height;
    private final int coR;
    private final LineSegment physicsTop;
    private final LineSegment physicsRight;
    private final LineSegment physicsBottom;
    private final LineSegment physicsLeft;
    private final String name;
    private List<Gadget> gadgetsToFire;
    private final List<LineSegment> edges;
    private List<Ball> balls;
    private final Circle physicsTopLeft;
    private final Circle physicsTopRight;
    private final Circle physicsBottomRight;
    private final Circle physicsBottomLeft;
    private final List<Circle> corners;
    private final double x;
    private final double y;
    
    //Rep invariant:
    //width>0, height>0, name!=null && name.length>0
    //absorber within board
    //Abstraction Function:
    //represents an absorber with width, width, and height, height
    
    public Absorber(String name,int x,int y, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        this.y = (double) y;
        this.x = (double) x;
        this.coR = 0;    
        this.gadgetsToFire = new ArrayList<Gadget>();
        this.balls = new ArrayList<Ball>();
        //create edges
        this.physicsTop = new LineSegment(x,20-y,x+width,20-y);
        this.physicsRight = new LineSegment(x+width,20-y,x+width,20-(y+height));
        this.physicsBottom = new LineSegment(x,20-(y+height),x+width,20-(y+height));
        this.physicsLeft = new LineSegment(x,20-y,x,20-(y+height));
        this.edges = new ArrayList<LineSegment>(Arrays.asList(physicsLeft,physicsTop,physicsRight,physicsBottom));
        
        //create corners (though for an absorber it probably doesn't matter)
        this.physicsTopLeft = new Circle(x, 20-y, 0);
        this.physicsTopRight = new Circle(x+width, 20-y, 0);
        this.physicsBottomLeft = new Circle(x+width, 20-(y+height), 0);
        this.physicsBottomRight = new Circle(x, 20-(y+height), 0);
        this.corners = new ArrayList<Circle>(Arrays.asList(physicsTopLeft,physicsTopRight,physicsBottomLeft,physicsBottomRight));

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
//    	System.out.println("in action()");
        //remove the first ball
        if(balls.size() >= 1){ //changed from >1 
        	Ball ball = balls.get(0);
//        	System.out.println("ball.inAbsorber() == " + ball.inAbsorber());
        	
            
            ball.setNormalVelocity(new Vect(0,-50));//set to ball velocity to 50L/sec straight upwards
            double ballX = ball.getNormalPosition()[0];
            double ballY = this.y-.251; //set position to top of absorber
            ball.setNormalPosition(ballX, ballY);
            
            ball.setInAbsorber(false);
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
     * stop ball and store it unmoving in the bottom right hand corner of the absorber
     * @param ball to be reflected
     */
    @Override
    public void  reflectOff(Ball ball){ //here, we absorb the ball
//    	System.out.println("Reflect off "+this.name);
    	ball.setInAbsorber(true);
        ball.setNormalVelocity(new Vect(0,0)); //stop ball
        //set ball center position .25L away from bottom and right wall
        ball.setNormalPosition(this.x+this.width-.25, this.y+this.height-.25);
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
        return new Vect(this.x,this.y);
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

    @Override
    public String getGadgetType() {
        
        return "Absorber";
    }
}
