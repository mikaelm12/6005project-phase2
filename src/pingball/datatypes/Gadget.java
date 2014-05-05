package pingball.datatypes;

import java.util.List;

import physics.Vect;


public interface Gadget {
    
    /**
     * response that the gadget makes to a trigger happening somewhere on the board
     */
    public void action();
    
    /**
     * 
     * @return coefficient of reflection of the gadget
     */
    public double getCoR();
    
    /**
     * computes the time until the ball collides with the gadget
     * @param ball the ball 
     * @return time until ball collides with gadget
     */
    public double timeUntilCollision(Ball ball);
    
    /**
     * reflects the ball off gadget and updates its velocity. Calls on trigger to trigger 
     * gadgets connected to this gadget
     * @param ball to be reflected
     */
    public void reflectOffGadget(Ball ball);
    
    /**
     * 
     * @return list of gadgets that are fired when this gadget is triggered
     */
    public List<Gadget> getGadgetsToFire();
    
    /**
     * adds gadget to gadgets to be fired when this gadget is triggered
     * @param gadget gadget to be added to the list of gadgets that are fired when this
     *          gadget is triggered
     */
    public void addGadgetToFire(Gadget gadget);

    /**
     * 
     * @return name of the gadget
     */
    public String getName();
    
    /**
     * 
     * @return position of the gadget
     */
    public Vect getPosition();
    
    /**
     * 
     * @return string representation of the gadget
     */
    @Override
    public String toString();
    
    
}
