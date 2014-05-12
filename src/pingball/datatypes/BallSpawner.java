package pingball.datatypes;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import physics.Vect;

public class BallSpawner implements Gadget {
	private final String name;
	private final int x;
	private final int y;
	
	public BallSpawner(String name, int x, int y){
		this.name = name;
		this.x = x;
		this.y = y;
	}

	/**
	 * Spawns a ball with a random velocity in the center of the spawner
	 */
	@Override
	public void action() {
		double xVel = (Math.random()-.5)*40; //a random double between -20 and 20
		double yVel = (Math.random()-.5)*40; //a random double between -20 and 20
		String randomString = randomString(15); 
		String ballName = "SpawnedBall"+randomString;
		//TODO construct the ball
	}

	/**
	 * Creates a random string of alphanumeric characters
	 * @param len the length of the output string
	 * @return a random string of alphanumeric characters of length len
	 */
	private String randomString(int len) {
		String charString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
	    for(int i=0; i<len; i++) {
	    	sb.append(charString.charAt( rnd.nextInt(charString.length()) ) );
	    }
	    return sb.toString();
	}

	@Override
	public double getCoR() {
		return 0; //not applicable, this should never be called
	}

	/**
	 * You can't collide with a spawner, so timeUntilPhysicsCollision returns positive infinity.
	 */
	@Override
	public double timeUntilPhysicsCollision(Ball ball) {
		return Double.POSITIVE_INFINITY;
	}

	
	/**
	 * You can't collide with a spawner, so reflectOff does nothing.
	 */
	@Override
	public void reflectOff(Ball ball) {
	}

	
	/**
	 * A spawner cannot trigger other gadgets, so this method does nothing.
	 */
	@Override
	public List<Gadget> getGadgetsToFire() {
		return null;
	}

	/**
	 * A spawner cannot trigger other gadgets, so this method does nothing.
	 */
	@Override
	public void addGadgetToFire(Gadget gadget) {
		// TODO Auto-generated method stub
		
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
		return "Ball Spawner";
	}

}
