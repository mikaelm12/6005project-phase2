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
	private List<Ball> sentBallQueue = new ArrayList<Ball>();
	private List<Ball> receivedBallQueue = new ArrayList<Ball>();
	private Portal destinationPortal;
	private boolean hasDestinationPortal = false;
	

	public Portal(String name, int x, int y, String otherBoard, String otherPortal){
		this.circle = new Circle(x+.5, 20-(y+.5), .5);
		this.name = name.substring(5); //for some reason, "name=[name]" is being passed in to the constructor
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
		if (this.destinationPortal==null){ 
			return Double.POSITIVE_INFINITY;
		}
		return Geometry.timeUntilCircleCollision(this.circle, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
	}

	public boolean ballComingFromPortal(Ball ball) {
		return false;
		/**
		double ballX = ball.getNormalCircle().getCenter().x();
		double ballY = ball.getNormalCircle().getCenter().y();
		double ballVelX = ball.getNormalVelocity().x();
		double ballVelY = ball.getNormalVelocity().y();
		double ballDeltaX = ballX*ballVelX*.01; //*.01 because we do not want this to be too large. If this is too large, we run the risk of traveling past the center of the portal--causing a false negative
		double ballDeltaY = ballY*ballVelY*.01;
		double portalX = this.x+.5;
		double portalY = this.y+.5;
		double distanceBeforeDelta = Math.sqrt(Math.pow(ballX-portalX, 2)+Math.pow(ballY-portalY, 2));
		double distanceAfterDelta = Math.sqrt(Math.pow(ballX+ballDeltaX-portalX, 2)+Math.pow(ballY+ballDeltaY-portalY, 2));		
		System.out.println("ballComingFromPortal: ");
		System.out.println(distanceAfterDelta>distanceBeforeDelta);
		return (distanceAfterDelta>distanceBeforeDelta); //we are traveling away from (or out of) the portal
	**/
	}
	
	public void setDestinationPortal(Portal destinationPortal){
		this.destinationPortal = destinationPortal;
	}
	
	public Portal getDestinationPortal(){
		return this.destinationPortal;
	}

	@Override
	public void reflectOff(Ball ball) {
		if (this.destinationPortal!=null){
			this.destinationPortal.teleportBall(ball);
			this.trigger();
		}
	}
	
	private void teleportBall(Ball ball) {
		ball.setNormalPosition(this.x, this.y);
		double speed = Math.sqrt(ball.getNormalVelocity().dot(ball.getNormalVelocity()));
		double timeTilEdgeOfPortal = .8/speed; //to place the ball safely outside the target portal (Portal has .5 radius and ball has .25 radius)0
		ball.updateBallPosition(timeTilEdgeOfPortal);
		
	}

	/**
	 * Adds a ball to the portal's sentBallQueue, from which the ball will be retrieved and taken away from the portal to its destination (assuming the destination is valid).
	 * @param ball ball to be sent "into" the portal
	 */
	public void sendBall(Ball ball) {
		System.out.println("in sendBall()");
		synchronized(sentBallQueue){
			sentBallQueue.add(ball);
		}
	}
	
	/**
	 * Receives a ball from some other portal (of which this is the target portal).
	 * Places the ball on the board, in the center of the portal.
	 * @param ball ball to "come out of" the portal
	 */
	public synchronized void receiveBall(Ball ball){
		System.out.println(this.name + " in receiveBall()");
		double velX = ball.getNormalVelocity().x();
		double velY = ball.getNormalVelocity().y();
		double propX = velX/Math.abs(velX+velY);
		double propY = velY/Math.abs(velX+velY);
		
		
		double ballX = this.x+.25+propX;
		double ballY = this.y+.25+propY;
		ball.setNormalPosition(ballX, ballY);
		receivedBallQueue.add(ball);
		
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
	
	public List<Ball> getSentBallQueue(){
		return this.sentBallQueue;
	}
	
	public List<Ball> getReceivedBallQueue(){
		return this.receivedBallQueue;
	}

	public void setTargetPortalBoardName(String name) {
		this.otherBoard = name;
	}

	public boolean getHasDestinationPortal() {
		return hasDestinationPortal;
	}

	public void setHasDestinationPortal(boolean hasDestinationPortal) {
		this.hasDestinationPortal = hasDestinationPortal;
	}

}
