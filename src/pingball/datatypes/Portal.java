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
	private final String otherBoard; 
	private final String otherPortal;
	private List<Gadget> gadgetsToFire;

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
	public double timeUntilCollision(Ball ball) {
        return Geometry.timeUntilCircleCollision(this.circle, ball.getCircle(), ball.getVelocity());
	}

	@Override
	public void reflectOffGadget(Ball ball) {
		this.sendBall(ball);
		this.trigger();
	}

	private void sendBall(Ball ball) {
		// TODO Auto-generated method stub
	}
	
	public void receiveBall(Ball ball){
		// TODO Auto-generated method stub
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

}
