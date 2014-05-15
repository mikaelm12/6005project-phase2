package pingball.datatypes;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Vect;


public class Portal implements Gadget {
	
	private final Circle circle;
	private String name;
	private final int x;
	private final int y;
	private String otherBoard; 
	private final String otherPortal;
	private List<Gadget> gadgetsToFire = new ArrayList<Gadget>();
	private boolean hasDestinationPortal; 
	private List<Ball> outQueue = new ArrayList<Ball>();
	

	public Portal(String name, int x, int y, String otherBoard, String otherPortal){
		this.hasDestinationPortal = false;
		this.circle = new Circle(x+.5, 20-(y+.5), .5);
		this.name = name;
		this.x = x;
		this.y = y;
		this.otherBoard = otherBoard;
		this.otherPortal = otherPortal;
		
	}
	
	@Override
	public void action() {
		// no action for portals
	}

	@Override
	public double getCoR() {
		return 0; // No CoR, really. This should never be called.
	}

	@Override
	public double timeUntilPhysicsCollision(Ball ball) {
		if (!this.hasDestinationPortal){ 
			return Double.POSITIVE_INFINITY;
		}
		return Geometry.timeUntilCircleCollision(this.circle, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
	}

	@Override
	public void reflectOff(Ball ball) {
		if (this.hasDestinationPortal){
			synchronized(this.outQueue){
				this.outQueue.add(ball);
			}
			this.trigger();
		}
	}
	
	/**
	 * Receives a ball from some other portal (of which this is the target portal).
	 * Places the ball in the center of the portal, then updates it's position so that it has just traveled outside the portal
	 * @param ball ball to "come out of" the portal
	 */
	public synchronized void receiveBall(Ball ball) {
		ball.setNormalPosition(this.x, this.y);
		double speed = Math.sqrt(ball.getNormalVelocity().dot(ball.getNormalVelocity()));
		double timeTilEdgeOfPortal = .8/speed; //to place the ball safely outside the target portal (Portal has .5 radius and ball has .25 radius so must go at least .75 L away)
		ball.updateBallPosition(timeTilEdgeOfPortal);
	}
	
	/**
     * fires the actions of gadgets in gadgetsToFire
     */
    private void trigger(){
        for (Gadget gadget : gadgetsToFire) {
            gadget.action();
        }
    } 

	@Override
	public List<Gadget> getGadgetsToFire() {
		return this.gadgetsToFire;
	}

	@Override
	public void addGadgetToFire(Gadget gadget) {
		this.gadgetsToFire.add(gadget);
		
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Vect getPosition() {
		return new Vect(this.x, this.y);
	}

    @Override
    public String getGadgetType() {
        return "Portal";
    }
    
    @Override
    public String toString(){
    	return "P";
    }

	public String getTargetPortalName() {
		return this.otherPortal;
	}
	
	public String getTargetPortalBoardName(){
		return this.otherBoard;
	}
	
	public List<Ball> getOutQueue(){
		return this.outQueue;
	}

	public void setTargetPortalBoardName(String name) {
		this.otherBoard = name;
	}

	public boolean hasDestinationPortal() {
		return hasDestinationPortal;
	}

	public void setHasDestinationPortal(boolean newHasDestinationPortal) {
		this.hasDestinationPortal = newHasDestinationPortal;
	}

}
