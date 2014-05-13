package pingball.datatypes;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
    private List<Portal> portals;
    private List<BallSpawner> spawners;
    private String[][] boardString;
    private String neighborLeftString;
    private String neighborTopString;
    private String neighborBottomString;
    private String neighborRightString;
    private List<Ball> incomingBalls = Collections.synchronizedList(new ArrayList<Ball>());
    private boolean paused = false;
    private HashMap<String, String> gadgetKeyUpListeners;
    private HashMap<String, String> gadgetKeyDownListeners;


    
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
        balls = Collections.synchronizedList(new ArrayList<Ball>());
        gadgets = Collections.synchronizedList(new ArrayList<Gadget>());
        spawners = Collections.synchronizedList(new ArrayList<BallSpawner>());    
        portals = Collections.synchronizedList(new ArrayList<Portal>());
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
    
    public synchronized void addIncomingBall(Ball ball){
        incomingBalls.add(ball);
    }
    
    public List<Ball> getIncomingBalls(){
        return incomingBalls;
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
     * adds a portal to the board
     * @param portal to be added to the board
     */
    public void addPortal(Portal portal){
    	if(portal.getTargetPortalBoardName().equals("")){
    		portal.setTargetPortalBoardName(this.getName());
    	}
        portals.add(portal);
    }
    
    /**
     * adds a spawner to the board
     * @param spawner to be added to the board
     */
    public void addSpawner(BallSpawner spawner){
        spawners.add(spawner);
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
     * 
     * @return list of Left Flippers in this board
     */
    public List<LeftFlipper> getLeftFlippers(){
    	List<LeftFlipper> returnList = new ArrayList<LeftFlipper>();
    	for (Gadget gadget: this.getGadgets()){
    		if (gadget.getGadgetType().equals("Left Flipper")){
    			returnList.add((LeftFlipper) gadget);
    		}
    	}
        return returnList;
    }
    
    /**
     * 
     * @return list of gadgets in this board
     */
    public List<RightFlipper> getRightFlippers(){
    	List<RightFlipper> returnList = new ArrayList<RightFlipper>();
    	for (Gadget gadget: this.getGadgets()){
    		if (gadget.getGadgetType().equals("Right Flipper")){
    			returnList.add((RightFlipper) gadget);
    		}
    	}
        return returnList;
    }
    public void pauseUnpauseGame(){
        this.paused = !this.paused;
    }
    
    public boolean isPaused(){
        return this.paused;
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
        for (Portal portal: portals){
        	int xPos = (int) portal.getPosition().x();
        	int yPos = (int) portal.getPosition().y();
        	boardString[yPos][xPos] = "P";
        }
        for (BallSpawner spawner: spawners){
        	int xPos = (int) spawner.getPosition().x();
        	int yPos = (int) spawner.getPosition().y();
        	boardString[yPos][xPos] = "S";
        }
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
            int xPos = (int) Math.floor(ball.getNormalPosition()[0]);
            int yPos = (int) Math.floor(ball.getNormalPosition()[1]);
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
    
    /**
     * This method is called when during the process when the board file is being parsed.
     * This adds a hashmap of the listeners -> {String keyBoardChar: String gadget}
     * These listeners are specifically for when the associated key is being released
     * @param listeners the hashmap of listeners
     */
    public void addKeyUpListener(HashMap<String, String> listeners){
        gadgetKeyUpListeners = listeners;
    }
    
    /**
     * This method is called when during the process when the board file is being parsed.
     * This adds a hashmap of the listeners -> {String keyBoardChar: String gadget}
     * These listeners are specifically for when the associated key is being pressed
     * @param listeners the hashmap of listeners
     */
    public void addKeyDownListener(HashMap<String, String> listeners){
        gadgetKeyDownListeners = listeners;
    }
    
    /**
     * This method is called when a key is pressed when the GUI is active.
     * If the key that was pressed is associated with a gadget, the gadget is then activated.
     * If the key that was released is associated with a gadget, the gadget is then deactivated. 
     */
    public void checkKeyListener(KeyEvent e, boolean pressed){
        String key = String.valueOf(e.getKeyChar());
        if(key.equals(" ")) key = "space";
        if(gadgetKeyUpListeners.containsKey(key)){ //Keyup
            Gadget gadget = null;

            if(pressed){//Key is pressed
                //Find the associated gadget
                String gadgetStr = gadgetKeyDownListeners.get(key);
                for(Gadget curGadget: gadgets){
                    if(curGadget.getName().equals(gadgetStr)){
                        gadget = curGadget;
                    }
                }
                gadget.action();
            }
        }
        
        if(gadgetKeyDownListeners.containsKey(key)){ //Keydown
            Gadget gadget = null;
            if(!pressed){//Key is released
                //Find associated gadget
                String gadgetStr = gadgetKeyDownListeners.get(key);
                for(Gadget curGadget: gadgets){
                    if(curGadget.getName().equals(gadgetStr)){
                        gadget = curGadget;
                    }
                }
                gadget.action();
            }
        }
        
    }
    
    public List<Portal> getPortals(){
    	return this.portals;
    }
    public List<BallSpawner> getSpawners(){
    	return this.spawners;
    }
    
}



