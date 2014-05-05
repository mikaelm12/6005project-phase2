package warmup;

public interface BoardInterface {
    
    //TODO 
    //include methods for gadgets on the board
    //methods below are targeted toward warmup exercise where there is only one ball
    //some methods are not fully defined because corresponding classes have not been defined
    
    /**
     * Gets the gravity value of this board
     * @return the gravity of the board
     */
    public double getBoardGravity();
    
    /**
     * Gets the value of friction (mu) of this board
     * @return friction1 of the board
     */
    public double getMu();
    
    /**
     * Gets the value of friction (mu2) of this board
     * @return friction2 of the board
     */
    public double getMu2();
    
    //TODO: method should take in a ball instance since board could contain >1 ball
    /**
     * Gets the position of the ball within the board
     * @return position of the ball within the board
     */
    public double[] getBallPosition();
    
    //TODO: method should take in a ball instance since board could contain >1 ball
    /**
     * Sets the position of the ball
     * @param x new x-coordinate of the ball
     * @param y new y-coordinate of the ball
     */
    public void setBallPosition(double x, double y);
    
    //TODO: replace with method that returns array of walls that 
    //enclose the board 
    /**
     * Gets the size of the board in the form of an array [x,y]
     * @return array containing coordinates of walls that enclose the board
     */
    public double[] getBoardSize();
    
    /**
     * Sets a specific wall as one of the boundaries of the board
     * @param position where the wall is to be set
     * @param wall to be set
     */
    public void setWall(String position, Wall wall);
    
    
    /**
     * Get the wall object at the specified position
     * @param position of wall wanted
     * @return
     */
    public Wall getWall(String position);

}
