package warmup;

import physics.Circle;
import physics.Vect;

public interface BallInterface {
    
   /**
    * Sets the velocity of the ball object

    * @param magnitude of the velocity
    * @param direction of the velocity
    */
    public void setVelocity(Vect veloVector);

    
    
    /**
    * Gets the current velocity of the ball object
    * @return the vector velocity of the ball
    */
    public Vect getVelocity();
    
    
    
    /**
     * Returns the radius of the ball
     * @return A double representing the radius of the ball
     */
    public double getRadius();
    
    
   /**
    * Sets the new position of the ball
    * @param xLoc
    * @param yLoc
    */
    public void setPosition(double xLoc, double yLoc);
    
    /**
     * Returns the current position of the ball in the board
     * that it's in.
     * @return An array of length 2 representing where the ball is in
     * in it's current board
     */
    public double[] getPosition();
    
    
    /**
     * Gets the circle object representing the ball
     * @return a circle object
     */
    public Circle getCircle();
    
    
    /**
     * Updates the velocity vector of the ball
     * @param wall
     * @param veloVector
     * @return 
     */
    public Vect update(Wall wall, Vect veloVector);
    
}
