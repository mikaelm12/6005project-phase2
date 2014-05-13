package pingball.datatypes;

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
	private List<Gadget> gadgetsToFire;
	private List<Ball> sentBallQueue;
	private List<Ball> receivedBallQueue;
	

	public Portal(String name, int x, int y, String otherBoard, String otherPortal){
		this.circle = new Circle(x+.5, y+.5, .5);
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
		if (ballComingFromPortal(ball)){ 
			return Double.POSITIVE_INFINITY;
		}
		return Geometry.timeUntilCircleCollision(this.circle, ball.getPhysicsCircle(), ball.getPhysicsVelocity());
	}

	private boolean ballComingFromPortal(Ball ball) {
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
		return (distanceAfterDelta>distanceBeforeDelta); //we are traveling away from (or out of) the portal
	}

	@Override
	public void reflectOff(Ball ball) {
		this.trigger();
		this.sendBall(ball);
	}
	
	/**
	 * Adds a ball to the portal's sentBallQueue, from which the ball will be retrieved and taken away from the portal to its destination (assuming the destination is valid).
	 * @param ball ball to be sent "into" the portal
	 */
	public void sendBall(Ball ball) {
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
		double ballX = this.x+.25;
		double ballY = this.y+.25;
		ball.setNormalPosition(ballX, ballY);
		
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
		return this.otherPortal;
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

}
