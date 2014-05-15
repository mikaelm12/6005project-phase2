package pingball.datatypes;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
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
    private boolean restart = false;


    
    //Rep invariant:
    
    //Abstraction Function:

    public boolean isRestart() {
        return restart;
    }

    public void setRestart(boolean restart) {
        this.restart = restart;
    }

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
        checkRep();
    }
    
    public synchronized void addIncomingBall(Ball ball){
        incomingBalls.add(ball);
        checkRep();
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
        checkRep();
    }
    
    /**
     * adds a gadget to the board
     * @param gadget to be added to the board
     */
    public void addGadget(Gadget gadget){
        if(gadgets.contains(gadget.getName())){
            fail("Gadget is being added: board already contains a gadget with the same name.");
        }else{
            gadgets.add(gadget);
            checkRep();
        }
    }
    
    /**
     * adds the gadgets in the list of gadgets to gadgets in the board
     * @param gadgets to be added to the board
     */
    public void addGadgetList(List<Gadget> gadgets){
        for(Gadget gadget: gadgets) this.addGadget(gadget);
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
        checkRep();
    }
    
    /**
     * adds a spawner to the board
     * @param spawner to be added to the board
     */
    public void addSpawner(BallSpawner spawner){
        spawners.add(spawner);
        checkRep();
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
        return new ArrayList<Gadget>(gadgets);
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
    public void setGamePauseStatus(boolean paused){
        this.paused = paused;
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
                                //TODO: FOUND BUG: Doesn't work with all widths and heights
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
        System.out.println("pressed: " + key +", " + gadgetKeyUpListeners.containsKey(key) + ", " + gadgetKeyDownListeners.containsKey(key));
        if(gadgetKeyUpListeners.containsKey(key)){ //Keyup
            Gadget gadget = null;

            if(pressed){//Key is pressed
                //Find the associated gadget
                String gadgetStr = gadgetKeyUpListeners.get(key);
                for(Gadget curGadget: gadgets){
                    if(curGadget.getName().equals(gadgetStr)){
                        gadget = curGadget;
                        System.out.println("gadget found");
                        gadget.action();
                    }
                }
                
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
                        gadget.action();
                    }
                }
            }
        }
        
    }
    
    public List<Portal> getPortals(){
    	return new ArrayList<Portal>(portals);
    }
    public List<BallSpawner> getSpawners(){
    	return new ArrayList<BallSpawner>(spawners);
    }

    /**
     * Remove the current flippers from the board and replaces them with the current flippers
     * sent from the server
     * @param flippers - new flipper obejcts that represent the current state of the flipper
     */
    public synchronized void refreshFlippers(List<Gadget> flippers){
         List<Gadget> flippersToDelete = new ArrayList<Gadget>();

         for(int index = 0; index < this.gadgets.size(); index++){
             if(gadgets.get(index).getGadgetType().equals("Left Flipper")||gadgets.get(index).getGadgetType().equals("Right Flipper")){
                 flippersToDelete.add(gadgets.get(index));
             }
         }
         
         for (Gadget g: flippersToDelete) this.gadgets.remove(g);
            
         for (Gadget gadget: flippers) this.gadgets.add(gadget);
         
     }
    
    
   /**
    * This is for use in the runPingballServerClient, it is a string representation of the updates occuring for the balls
    * on the board.
    * @return -A string to be split 
    */
    public String ballGraphicsInfo(){
        StringBuilder sb = new StringBuilder();
        List<LeftFlipper> leftFlippers = this.getLeftFlippers();
        List<RightFlipper> rightFlippers = this.getRightFlippers();
        
        
        for (Ball ball: balls){
            //Ball info comes in the form 
            // name x y xVel yVel
            sb.append(ball.getBallGraphicsInfo() + "_##_");
        }

        return sb.toString();

    }
    
  
   
    /**
     * This is for use in the runPingballServerClient, it is a string representation of the updates occuring for the flippers
     * on the board.
     * @return -A string to be split 
     */
  public String flipperGraphicsInfo(){
        
    StringBuilder sb = new StringBuilder();
    List<LeftFlipper> leftFlippers = this.getLeftFlippers();
    List<RightFlipper> rightFlippers = this.getRightFlippers();
    
    for(LeftFlipper lf : leftFlippers){
        //Flipper info comes in the form   name x y orientation current angl
        
        sb.append(lf.getLeftFlipperGraphicsInfo() + "_##_");
    }
    for(RightFlipper rf: rightFlippers){
        sb.append(rf.getRightFlipperGraphicsInfo() + "_##_");
    }
        return sb.toString();
  }
    
  /**
   * Takes the String for the updated balls from the 
   * @param info
   * @return
   */
  public void updateBalls(String info){
      List<Ball> updatedBalls = new ArrayList<Ball>();
      String[] ballList = info.split("_##_");
      
      for(String ball: ballList){
          
          if(ball.length() > 1){
              String[] ballInfo = ball.split(" ");
              if(ballInfo[0].equals("Ball")){
                  String name = ballInfo[1];
                  double x = Double.parseDouble(ballInfo[2]);
                  double y = Double.parseDouble(ballInfo[3]);
                  double xVel = Double.parseDouble(ballInfo[4]);
                  double yVel = Double.parseDouble(ballInfo[5]);
                  Ball newBall  = new  Ball(name, x, y, xVel, yVel);
                  updatedBalls.add(newBall);
              }
          
          }
          
      }
      this.balls =  updatedBalls;
      checkRep();
      
  }
  public void updateFlippers(String info){
      List<Gadget> updatedFlippers = new ArrayList<Gadget>();
      String[] flipperList = info.split("_##_");
      
      for(String flipper: flipperList){
          if(flipper.length() > 1){
              
              String[] flipperInfo = flipper.split(" ");
              if(flipperInfo[0].equals("Left")||flipperInfo[0].equals("Right")){
                  String type = flipperInfo[0];
                  String name = flipperInfo[1];
                  int x = (int)Math.round(Double.parseDouble(flipperInfo[2]));
                  int y = (int)Math.round(Double.parseDouble(flipperInfo[3]));
                  int orientation = Integer.parseInt(flipperInfo[4]);
                  double currentAngle = Double.parseDouble(flipperInfo[5]);
                  
                  if(type.equals("Left")){
                      LeftFlipper flip = new LeftFlipper(name, x, y, orientation);
                      
                      updatedFlippers.add(flip);
                  }
                  else{
                      RightFlipper flip = new RightFlipper(name, x, y, orientation);
                      updatedFlippers.add(flip);
                  }
              }
          
          }
          
      }
      
     refreshFlippers(updatedFlippers); 
  }
  public void intialBallPositions(List<Ball> balls){
      this.balls = balls;
  }

  
  /**
   * check representation
   */
  public void checkRep(){
      //Check if all balls are within the bounds of the baord
      double[] position;
      double xLoc;
      double yLoc;
      for(Ball ball: balls){
          position = ball.getNormalPosition();
          xLoc = position[0];
          yLoc = position[1];
          if(xLoc >= 20 || xLoc < 0) fail("ball out of bounds (x): " + xLoc);
          if(yLoc >= 20 || yLoc < 0) fail("ball out of bounds (y): " + yLoc);

          position = ball.getPhysicsPosition();
          xLoc = position[0];
          yLoc = position[1];
          if(xLoc >= 20 || xLoc < 0) fail("ball out of bounds (x): " + xLoc);
          if(yLoc >= 20 || yLoc < 0) fail("ball out of bounds (y): " + yLoc);
      }
      
      //Check if all gadgets (not including portals/spawners) are within the bounds of the baord
      Vect gadgetPosition;
      for(Gadget gadget: gadgets){
          gadgetPosition = gadget.getPosition();
          xLoc = gadgetPosition.x();
          yLoc = gadgetPosition.y();

          if(xLoc > 19 || xLoc < 0) fail(gadget.getGadgetType() + ": " + gadget.getName() + " out of bounds (x): " + xLoc);
          if(yLoc > 19 || yLoc < 0) fail(gadget.getGadgetType() + ": " + gadget.getName() + " out of bounds (y): " + yLoc);
      }
      
      //Check if all portals are within the bounds of the baord
      for(Portal portal: portals){
          gadgetPosition = portal.getPosition();
          xLoc = gadgetPosition.x();
          yLoc = gadgetPosition.y();
          
          if(xLoc > 19 || xLoc < 0) fail(portal.getGadgetType() + ": " + portal.getName() + " out of bounds (x): " + xLoc);
          if(yLoc > 19 || yLoc < 0) fail(portal.getGadgetType() + ": " + portal.getName() + " out of bounds (y): " + yLoc);
      }
      //Check if all spawners are within the bounds of the baord

      for(BallSpawner spawner: spawners){
          gadgetPosition = spawner.getPosition();
          xLoc = gadgetPosition.x();
          yLoc = gadgetPosition.y();
          
          if(xLoc > 19 || xLoc < 0) fail(spawner.getGadgetType() + ": " + spawner.getName() + " out of bounds (x): " + xLoc);
          if(yLoc > 19 || yLoc < 0) fail(spawner.getGadgetType() + ": " + spawner.getName() + " out of bounds (y): " + yLoc);
      }
      
      
      //Set up for checking overlapping objects
      int[][] gadgetsLoc = new int[20][20]; //just for checking overlapping gadgets
      for (int i = 0; i < 20; i++) {
          for (int j = 0; j < 20; j++) {
              gadgetsLoc[i][j] = 0;
          }
      }
      
      //check for overlapping GADGETS only
      //note: not including balls here because balls can overlap with portals and spawners
      //      balls can also APPEAR to overlap with other balls and flippers due to rounding
      
      //this will iterate through all the gadgets in the board and keep track of
      //how many objects are located at each position
      //assertion will be thrown if there are more than 1 objects after the iteration is complete
      
//      System.out.println("Gadget num: " + gadgets.size());
      for(Gadget gadget:gadgets){
          Vect pos = gadget.getPosition();
          int xPos = (int) pos.x();
          int yPos = (int) pos.y();
          gadgetsLoc[xPos][yPos] += 1;
//          System.out.println(gadget.getName() + ": " + xPos + ", " + yPos);
      }
//      System.out.println("Portal num: " + portals.size());
      for (Portal portal: portals){
          Vect pos = portal.getPosition();
          int xPos = (int) pos.x();
          int yPos = (int) pos.y();
          gadgetsLoc[xPos][yPos] += 1;
//          System.out.println(portal.getName() + ": " + xPos + ", " + yPos);
      }
//      System.out.println("Spawner num: "+ spawners.size());
      for (BallSpawner spawner: spawners){
          Vect pos = spawner.getPosition();
          int xPos = (int) pos.x();
          int yPos = (int) pos.y();
          gadgetsLoc[xPos][yPos] +=1;
          System.out.println(spawner.getName() + ": " + xPos + ", " + yPos);
      }
      
      for (int i = 0; i < 20; i++) {
          for (int j = 0; j < 20; j++) {
              if(gadgetsLoc[i][j] > 1){
//                  System.out.println("gadgets: " + gadgets.size());
                  fail(gadgetsLoc[i][j]+ " gadgets are overlapping at (" +i+", " +j+")");
              }
          }
      }
      
      //Check that balls have unique names
      HashSet<String> ballNames = new HashSet<String>();
      
      for(Ball ball:balls){
          String ballName = ball.getName();
          if(ballNames.contains(ballName)){
              fail("There are multiple balls with the same name: "+ ballName);
          }else{
              ballNames.add(ballName);
          }
      }
      
  }
  
}



