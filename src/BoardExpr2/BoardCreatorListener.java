package BoardExpr2;

import java.util.ArrayList;
import java.util.HashMap;

import pingball.datatypes.Absorber;
import pingball.datatypes.Ball;
import pingball.datatypes.BallSpawner;
import pingball.datatypes.Board;
import pingball.datatypes.CircularBumper;
import pingball.datatypes.Gadget;
import pingball.datatypes.LeftFlipper;
import pingball.datatypes.Portal;
import pingball.datatypes.RightFlipper;
import pingball.datatypes.SquareBumper;
import pingball.datatypes.TriangularBumper;

public class BoardCreatorListener extends BoardExpr2.GrammarBaseListener{
   /**
    * gadgets, balls
    * 
    * This objects store all the corresponding gadgets, balls, and fire commands that are being read
    * from the file.  After all the objects are read from antlr, they are used to create the a new board.
    */
   private ArrayList<Gadget> gadgets = new ArrayList<Gadget>();
   private ArrayList<Ball> balls = new ArrayList<Ball>();
   private ArrayList<Gadget> portals = new ArrayList<Gadget>();
   private ArrayList<Gadget> spawners = new ArrayList<Gadget>();
   private HashMap<String, String> gadgetKeyUpListeners = new HashMap<String, String>();
   private HashMap<String, String> gadgetKeyDownListeners = new HashMap<String, String>();
   private Board board;
   
   /**
    * This method is called after all of the objects on the board have been extracted from the file.
    * This method creates a new board.
    * 
    * @return board containing all the objects indicated in the file
    * @throws Exception 
    */
   public Board getBoard() throws Exception{
       for(Gadget gadget: gadgets) board.addGadget(gadget);
       for(Ball ball: balls) board.addBall(ball);
       for(Gadget spawner: spawners) board.addSpawner(spawner);
       for(Gadget portal:portals) board.addPortal(portal);
       
       board.addKeyUpListener(gadgetKeyUpListeners);
       board.addKeyDownListener(gadgetKeyDownListeners);

       resetBoardObjects();
       return board;
   }
    
   /**
    * This method is called after all the objects in the board are actually put into the board.
    * This prevents objects from one file being combined with object from another file if two file are
    * read in, one after the other.
    */
   private void resetBoardObjects(){
       gadgets = new ArrayList<Gadget>();
       balls = new ArrayList<Ball>();
   }

   /**
    * This method extends ANTLR's GrammarBaseListener.
    * 
    * When exiting out of the Board Object in the tree, this method extracts the necessary information
    * in the file to create a new board.
    */
   @Override
    public void exitBoard(GrammarParser.BoardContext ctx) {
       //The default values of friction and gravity in case the given board doesn't specify values
        double friction1 = 0.025;
        double friction2 = 0.025;
        double gravity = 25.0;
        
        String ObjectName = ctx.objectName().getChild(2).getText();
        //ctx.objectName().
        
        for(int x = ctx.getChildCount()-1; x > 0; x--){
            String childName = ctx.getChild(x).getChild(0).getText();
            String doubleValue = ctx.getChild(x).getChild(2).getText();
            if(childName.equals("friction1")){
                friction1 = Double.parseDouble(doubleValue);
            }else if(childName.equals("friction2")){
                friction2 = Double.parseDouble(doubleValue);
            }if(childName.equals("gravity")){
                gravity = Double.parseDouble(doubleValue);
            }
        }
        
        board = new Board(ObjectName, gravity, friction1, friction2);
        
    }
    
    
    /**
     * This method extends ANTLR's GrammarBaseListener.
     * 
     * When exiting out of an Object in the tree (i.e. a ball, bumper, flipper, or absorber),
     * this method extracts the necessary information in the file to create a new corresponding ball or gadget.
     */
    @Override
    public void exitObject(GrammarParser.ObjectContext ctx){
        ArrayList<Double> doubleContent = new ArrayList<Double>(); //this will store xLoc, yLoc, xVel, yVel
        String ObjectType = ctx.objectType().getChild(0).toString();
        String ObjectName = ctx.objectName().getChild(2).toString();
        for(int x = 2; x < ctx.getChildCount(); x++){
            int lastIndex = ctx.getChild(x).getChildCount() - 1;
            String content = ctx.getChild(x).getChild(lastIndex).toString();
            double value = Double.parseDouble(content);
            doubleContent.add(value);
        }
        
        if (ObjectType.equals("ball")){        
            if(doubleContent.size() != 4) System.err.println("error creating ball: file was parsed incorrectly or did not contain the correct amount of information");
            balls.add(new Ball(ObjectName, doubleContent.get(0), doubleContent.get(1), doubleContent.get(2), doubleContent.get(3)));
        } else if (ObjectType.equals("squareBumper")){
            if(doubleContent.size() != 2) System.err.println("error creating squareBumper: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(new SquareBumper(ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue()));
        } else if (ObjectType.equals("circleBumper")){
            if(doubleContent.size() != 2) System.err.println("error creating circleBumper: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(new CircularBumper(ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue()));
        } else if (ObjectType.equals("triangleBumper")){
            if(doubleContent.size() != 3) System.err.println("error creating triangleBumper: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(new TriangularBumper(ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue(), doubleContent.get(2).intValue()));
        } else if (ObjectType.equals("leftFlipper")){
            if(doubleContent.size() != 3) System.err.println("error creating leftFlipper: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(new LeftFlipper(ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue(), doubleContent.get(2).intValue()));
        } else if (ObjectType.equals("rightFlipper")){
            if(doubleContent.size() != 3) System.err.println("error creating rightFlipper: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(new RightFlipper(ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue(), doubleContent.get(2).intValue()));
        } else if (ObjectType.equals("absorber")){
            if(doubleContent.size() != 4) System.err.println("error creating absorber: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(new Absorber(ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue(), doubleContent.get(2).intValue(), doubleContent.get(3).intValue()));            
        }
        
    }

    /**
     * This method extends ANTLR's GrammarBaseListener.
     * 
     * When exiting out of the Fire Object in the tree, this method extracts the necessary information
     * in the file to store the fire information.  This method stores the fire commands as a list of
     * Strings -> [String trigger, String action] and adds it to the global object fireCmds.
     */
    @Override
    public void exitFire(GrammarParser.FireContext ctx) { 
        
        String trigger = ctx.trigger().getChild(2).toString();
        String action = ctx.action().getChild(2).toString();
        
        Gadget matchingGadget = null;
        for(Gadget gadget: gadgets){
            if (gadget.getName().equals(trigger)){
                for(Gadget actionGadget: gadgets){
                    if (actionGadget.getName().equals(action)){
                        matchingGadget = actionGadget;
                    }
                    if (matchingGadget != null){
                        gadget.addGadgetToFire(matchingGadget);
                    }
                }
            }
        }
    }
    
    /**
     * This method extends ANTLR's GrammarBaseListener.
     * 
     * When exiting out of the Keys Object in the tree, this method extracts the necessary information
     * in the file to store the key listener information.  This method stores the key commands in a global
     * hashmap that stores the key board character with the associated action.
     */
    @Override
    public void exitKeys(GrammarParser.KeysContext ctx){
        System.out.println("KEYS: "+ ctx.keyCmd().getText() + ", " + ctx.key().ID().toString() + ", " + ctx.action().ID().toString() );
        String keyBoardChar = ctx.key().ID().toString();
        String gadget = ctx.action().ID().toString();
        
        if(ctx.keyCmd().getText().toLowerCase().equals("keyup")){
            gadgetKeyUpListeners.put(keyBoardChar, gadget);
        }else if(ctx.keyCmd().getText().toLowerCase().equals("keydown")){
            gadgetKeyDownListeners.put(keyBoardChar, gadget);
        }
    }
    
    
    /**
     * This method extends ANTLR's GrammarBaseListener.
     * 
     * When exiting out of the Spawner Object in the tree, this method extracts the necessary information
     * in the file to store the spawner information.  This method adds the spawner to a global list containing
     * all of the board's spawners.
     */
    @Override
    public void exitSpawner(GrammarParser.SpawnerContext ctx){
        String name = ctx.objectName().getText();
        String xLoc = ctx.xLoc().getChild(2).getText();
        String yLoc = ctx.xLoc().getChild(2).getText();
        
        spawners.add(new BallSpawner(name, Integer.parseInt(xLoc), Integer.parseInt(yLoc)));
    }
    
    /**
     * This method extends ANTLR's GrammarBaseListener.
     * 
     * When exiting out of the Portal Object in the tree, this method extracts the necessary information
     * in the file to store the Portal information.  This method adds the portal to a global list containing
     * all of the board's portals.
     */
    @Override
    public void exitPortal(GrammarParser.PortalContext ctx){
        String name = ctx.objectName().getText();
        String xLoc = ctx.xLoc().getChild(2).getText();
        String yLoc = ctx.xLoc().getChild(2).getText();
        String otherPortal = ctx.otherPortal().getChild(2).getText();
        String otherBoard = "";
        if(ctx.getChildCount() == 5){
            otherBoard = ctx.getChild(3).getText();
        }
        
        portals.add(new Portal(name, Integer.parseInt(xLoc), Integer.parseInt(yLoc), otherBoard, otherPortal));
    }
    
}

    