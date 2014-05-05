package pingball.datatypes;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Vect;


public class CircularBumper implements Gadget{
    
    private final double radius;
    private final double coR;
    private final Circle circle;
    private final String name;
    private List<Gadget> gadgetsToFire;
    private final Vect origin;
    
    //Rep invariant:
    //name!=null && name.length>0
    //circle != null
    //Abstraction Function:
    //circle represents circularBumper
    
    public CircularBumper(String name,int x, int y){
        this.name = name;
        this.origin = new Vect(x,y);
        this.radius = 0.5;
        this.coR = 1.0;
        this.circle = new Circle(x+radius,y+radius,radius);
        this.gadgetsToFire = new ArrayList<Gadget>();
        
        checkRep();
    }
    
    //create constructor that allows user to set radius
    public CircularBumper(String name,int x, int y, double radius){
        this.name = name;
        this.origin = new Vect(x,y);
        this.radius = radius;
        this.coR = 1.0;
        this.circle = new Circle(x,y,radius);
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
     * computes time until the ball collides with the circular Bumper
     * @param ball ball to collide with
     * @return time until ball collides with circularBumper
     */
    @Override
    public double timeUntilCollision(Ball ball) {
        return Geometry.timeUntilCircleCollision(this.circle, ball.getCircle(), ball.getVelocity());
    }
    
    /**
     * reflects the ball off gadget, updates the ball's velocity and triggers this gadget
     * @param ball to be reflected
     */
    @Override
    public void reflectOffGadget(Ball ball){
        Vect newVelocityVector = Geometry.reflectCircle(circle.getCenter(), ball.getCircle().getCenter(), 
                                                        ball.getVelocity(), coR);
        //set ball velocity and trigger connected gadgets
        ball.setVelocity(newVelocityVector);
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
        return new Vect(origin.x(),origin.y());
    }
    
    /**
     * 
     * @return circle that represents circularbumper
     */
    public Circle getCircle(){
        return circle;
    }
    
    /**
     * @return a String representation of the CircularBumper
     */
    @Override
    public String toString(){
        return "O";
    }
    
    /**
     * check rep of the datatype
     */
    private void checkRep(){
        assertTrue(name.length() > 0);
        assertTrue(circle != null);
    }



}
