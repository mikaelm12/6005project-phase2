package pingball.datatypes;

import java.util.ArrayList;
import java.util.List;

import physics.Vect;


public class Board {

    private final String name;
    private OuterWall wallLeft;
    private OuterWall wallRight;
    private OuterWall wallTop;
    private OuterWall wallBottom;
    private Board neighborLeft = null;
    private Board neighborRight = null;
    private Board neighborTop = null;
    private Board neighborBottom = null;
    private final int width = 20;
    private final int height = 20;
    private final double gravity;
    private final double mu;
    private final double mu2;
    private List<Ball> balls;
    private List<Gadget> gadgets;
    private String[][] boardString;
    private String neighborLeftString;
    private String neighborTopString;
    private String neighborBottomString;
    private String neighborRightString;
    private List<Ball> incomingBalls = new ArrayList<Ball>();
    
    
    //Rep invariant:
    
    //Abstraction Function:

    /**
     * 
     * @param x width of board
     * @param y height of board
     */
    public Board(String name,double gravity, double mu, double mu2){ 
        //set walls to be solid by default
        this.wallLeft = new OuterWall("left", 0,0,0,height,true);
        this.wallRight = new OuterWall( "right",width,0,width,height,true);
        this.wallTop = new OuterWall("top",0,0,width,0,true);
        this.wallBottom = new OuterWall("bottom",0,height,width,height,true);

        this.name = name;
        this.gravity = gravity;
        this.mu = mu;
        this.mu2 = mu2;
        balls = new ArrayList<Ball>();
        gadgets = new ArrayList<Gadget>();
                
        this.neighborLeftString = wallLeft.toString();
        this.neighborTopString = wallTop.toString();
        this.neighborRightString = wallRight.toString();
        this.neighborBottomString = wallBottom.toString();

        checkRep();
        
    }
    
    /**
     * Get this board's name
     * @return the name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * 
     * @return board gravity
     */
    public double getGravity(){
        return gravity;
    }
    
    /**
     * 
     * @return friction1
     */
    public double getMu(){
        return mu;
    }
    
    /**
     * 
     * @return friction2
     */
    public double getMu2(){
        return mu2;
    }
    
    /**
     * 
     * @return array containing all walls
     */
    public OuterWall[] getOuterWalls(){
        OuterWall[] walls = {wallLeft, wallTop, wallRight, wallBottom};
        return walls;
    }
    
    /**
     * adds ball to list of balls contained in board
     * @param ball to be added to the board
     */
    public void addBall(Ball ball){
        balls.add(ball);
    }
    
    public void addIncomingBall(Ball ball){
        incomingBalls.add(ball);
    }
    
    public List<Ball> getIncomingBalls(){
        return incomingBalls;
    }
    
    /**
     * updates positions and velocity of the balls on the board
     * @param timeStep difference in time
     */
    public void updateBallPositionsAndVelocity(double timeStep){

        for (Ball ball : balls) {
            ball.updateBallPosition(timeStep);
            ball.updateBallVelocityAfterTimestep(gravity, mu, mu2, timeStep);
        }
    }
    
    /**
     * removes ball from the list of balls contained in board
     * @param ball to be removed to the board
     */
    public void removeBall(Ball ball){
        balls.remove(ball);
    }
    
    /**
     * adds a gadget to the board
     * @param gadget to be added to the board
     */
    public void addGadget(Gadget gadget){
        gadgets.add(gadget);
    }
    
    /**
     * adds the gadgets in the list of gadgets to gadgets in the board
     * @param gadgets to be added to the board
     */
    public void addGadgetList(List<Gadget> gadgets){
        for(Gadget gadget: gadgets){
            this.gadgets.add(gadget);
        }
    }
    
    /**
     * Gets this board's neighbor that shares the left wall
     * @return neighbor to the left
     */
    public Board getNeighborLeft(){
        return neighborLeft;
    }
    
    /**
     * Gets this board's neighbor that shares the right wall
     * @return neighbor to the right
     */
    public Board getNeighborRight(){
        return neighborRight;
    }
    
    /**
     * Gets this board's neighbor that shares the top wall
     * @return neighbor to the top
     */
    public Board getNeighborTop(){
        return neighborTop;
    }
    
    /**
     * Gets this board's neighbor that shares the bottom wall
     * @return neighbor to the bottom
     */
    public Board getNeighborBottom(){
        return neighborBottom;
    }
    
   /**
    * Sets this board's left neighbor to be the given board
    * @param board new neighbor
    */
    public void setNeighborLeft(Board board){
        neighborLeft = board;
        wallLeft.setWallSolidity(false);
        String neighborName = neighborLeft.getName();
        if(neighborName.length() >= 20){
            neighborLeftString = neighborName.substring(0, 20); 
        }else{
            int counter = 0;
            for (int i = 10-neighborName.length()/2; i < 10+Math.round(neighborName.length()/2.0); i++) {
                neighborLeftString = neighborLeftString.substring(0, i) + 
                                        neighborName.charAt(counter) + neighborLeftString.substring(i+1);
                counter++;
            }
        }
        
    }
    
    /**
     * Sets this board's right neighbor to be the given board
     * @param board new neighbor
     */
    public void setNeighborRight(Board board){
        neighborRight = board;
        wallRight.setWallSolidity(false);
        String neighborName = neighborRight.getName();
        if(neighborName.length() >= 20){
            neighborRightString = neighborName.substring(0, 20);
        }else{
            int counter = 0;
            for (int i = 10-neighborName.length()/2; i < 10+Math.round(neighborName.length()/2.0); i++) {
                neighborRightString = neighborRightString.substring(0, i) + 
                                        neighborName.charAt(counter) + neighborRightString.substring(i+1);
                counter++;
            }
        }
    }

    /**
     * Sets this board's top free day neighbor to be the given board
     * @param board new neighbor
     */
    public void setNeighborTop(Board board){
        neighborTop = board;
        wallTop.setWallSolidity(false);
        String neighborName = neighborTop.getName();
        if(neighborName.length() >= 20){
            neighborTopString = neighborName.substring(0, 20);
        }else{
            int counter = 0;
            for (int i = 10-neighborName.length()/2; i < 10+Math.round(neighborName.length()/2.0); i++) {
                neighborTopString = neighborTopString.substring(0, i) + 
                                        neighborName.charAt(counter) + neighborTopString.substring(i+1);
                counter++;
            }
        }
    }
    
    /**
     * Sets this board's bottom neighbor to be the given board
     * @param board new neighbor in the bottom
     */
    public void setNeighborBottom(Board board){
        neighborBottom = board;
        wallBottom.setWallSolidity(false);
        String neighborName = neighborBottom.getName();
        if(neighborName.length() >= 20){
            neighborBottomString = neighborName.substring(0, 20);
        }else{
            int counter = 0;
            for (int i = 10-neighborName.length()/2; i < 10+Math.round(neighborName.length()/2.0); i++) {
                neighborBottomString = neighborBottomString.substring(0, i) + 
                                        neighborName.charAt(counter) + neighborBottomString.substring(i+1);
                counter++;
            }
        }
    }
    
    /**
     * Removes a given board from this board's neighbors
     * @param board to be removed from neighbors
     */
    public void unNeighbor (Board board){
        //check null pointer exceptions
        if (neighborBottom != null && neighborBottom.getName().equals(board.getName())){
            neighborBottom = null;
            neighborBottomString = wallBottom.toString();
            wallBottom.setWallSolidity(true);}
        else if (neighborTop != null && neighborTop.getName().equals(board.getName())){
            neighborTop = null;
            neighborTopString = wallTop.toString();
            wallTop.setWallSolidity(true);}
        else if (neighborLeft != null && neighborLeft.getName().equals(board.getName())){
            neighborLeft = null;
            neighborLeftString = wallLeft.toString();
            wallLeft.setWallSolidity(true);}
        else if (neighborRight != null && neighborRight.getName().equals(board.getName())){
            neighborRight = null;
            neighborRightString = wallRight.toString();
            wallRight.setWallSolidity(true);}
    }
    
    /**
     * 
     * @return list of balls in this board
     */
    public List<Ball> getBalls(){
        return balls;
    }
    
    /**
     * 
     * @return list of gadgets in this board
     */
    public List<Gadget> getGadgets(){
        return gadgets;
    }
    
    /**
     * @return string representation of the board
     */
    @Override
    public String toString(){
        boardString = new String[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                boardString[i][j] = " ";
            }
        }
        
        String string = new String();
        string += neighborTopString + "\n";
        for (Gadget gadget : gadgets) {
                Vect pos = gadget.getPosition();
                int xPos = (int) pos.x();
                int yPos = (int) pos.y();
                String gadgetString = gadget.toString();
                if(gadgetString.length() == 1){
                    boardString[yPos][xPos] = gadgetString;
                }
                else if(gadgetString.length() > 1 && gadgetString.charAt(0) != '='){
                    boardString[yPos][xPos] = Character.toString(gadgetString.charAt(0));
                    boardString[yPos+1][xPos] = Character.toString(gadgetString.charAt(1));
                    boardString[yPos][xPos+1] = Character.toString(gadgetString.charAt(2));
                    boardString[yPos+1][xPos+1] = Character.toString(gadgetString.charAt(3));
                }
                else{
                    Absorber abs = (Absorber) gadget;
                    if(gadgetString.length() < 20){
                        for (int i = 0; i < gadgetString.length()/abs.getHeight(); i++) {
                            for (int j = 0; j < gadgetString.length()/abs.getWidth(); j++) {
                                boardString[yPos+i][xPos+j] = Character.toString(gadgetString.charAt((abs.getWidth()*i)+j));
                            }
                            
                        }
                    }else{
                        
                        for (int i = 0; i < (gadgetString.length()/20); i++) {
                            for (int j = 0; j <abs.getWidth() ; j++) {
                                boardString[yPos+i][xPos+j] = Character.toString(gadgetString.charAt((20*i)+j));
                            }
                        }
                    }
                }     
        }
        for (Ball ball : balls) {
            int xPos = (int) Math.floor(ball.getPosition()[0]);
            int yPos = (int) Math.floor(ball.getPosition()[1]);
            if (yPos >= 0 && yPos <= 19 && xPos>= 0 && xPos <= 19){
                boardString[yPos][xPos] = ball.toString();
            }
        }
        for (int i = 0; i < height; i++) {
            string += Character.toString(neighborLeftString.charAt(i));
            for (int j = 0; j < width; j++) {
                string += boardString[i][j];
            }
            string += Character.toString(neighborRightString.toString().charAt(i))+ "\n";
        }
        string += neighborBottomString;
        return string;
    }
    
    /**
     * check representation
     */
    private void checkRep(){
    }
    
}



