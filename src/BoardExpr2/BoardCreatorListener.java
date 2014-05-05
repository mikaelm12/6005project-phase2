package BoardExpr2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pingball.datatypes.Ball;
import pingball.datatypes.Board;
import pingball.datatypes.Gadget;

//import pingball.Bumper.Shape;
//import pingball.Flipper.Side;
//import pingball.GrammarBaseListener;

public class BoardCreatorListener extends pingball.GrammarBaseListener{
   /**
    * gadgets, balls, and fireCmds
    * 
    * This objects store all the corresponding gadgets, balls, and fire commands that are being read
    * from the file.  After all the objects are read from antlr, they are used to create the a new board.
    */
   private static ArrayList<Gadget> gadgets = new ArrayList<Gadget>();
   private static ArrayList<Ball> balls = new ArrayList<Ball>();
   private static ArrayList<ArrayList<String>> fireCmds = new ArrayList<ArrayList<String>>();
   
   private static Board board;
   
   /**
    * This method is called after all of the objects on the board have been extracted from the file.
    * This method creates a new board.
    * 
    * @return board containing all the objects indicated in the file
 * @throws Exception 
    */
   public static Board getBoard() throws Exception{
       
       for(Gadget gadget: gadgets) board.addGadget(gadget);
       for(Ball ball: balls) board.addBall(ball);

       board.addGizmos(createTriggerActions());

       resetBoardObjects();
       return board;
   }
    
   /**
    * This method is called after all the objects in the board are actually put into the board.
    * This prevents objects from one file being combined with object from another file if two file are
    * read in, one after the other.
    */
   private static void resetBoardObjects(){
       gadgets = new ArrayList<Gadget>();
       balls = new ArrayList<Ball>();
       fireCmds = new ArrayList<ArrayList<String>>();
   }

   /**
    * This method is called after all the the objects have been read in from the file.
    * This method creates a hash map of STRINGS indicating which trigger object is mapped to which
    * actions objects.
    * 
    * @return a HashMap of strings indicating the connections between triggers and corresponding actions
    *          note: in this project, we refer to this HashMap as "gizmos"
    */
   private static HashMap<String, List<String>> createTriggerActions(){
       HashMap<String, List<String>> gizmos = new HashMap<String, List<String>>();
       for(int x = 0; x < fireCmds.size(); x ++){
           String trigger = fireCmds.get(x).get(0);
           String action = fireCmds.get(x).get(1);
           if(gizmos.containsKey(trigger)){
               List<String> curList = gizmos.get(trigger);
               curList.add(action);
               gizmos.put(trigger, curList);
           }else{
               List<String> curList = new ArrayList<String>();
               curList.add(action);
               gizmos.put(trigger, curList);
           }
       }
       
       return gizmos;
   }
   
   /**
    * This method extends ANTLR's GrammarBaseListener.
    * 
    * When exiting out of the Board Object in the tree, this method extracts the necessary information
    * in the file to create a new board.
    */
    public void exitBoard(GrammarParser.BoardContext ctx) {
//        String ObjectType = ctx.getChild(0).toString();
        String ObjectName = ctx.getChild(1).getChild(2).toString();
        String gravity = ctx.getChild(2).getChild(2).toString();
        if(ctx.getChildCount() == 4){ //if has 4 children, then friction was given
            String friction1 = ctx.getChild(3).getChild(2).toString();
            String friction2 = ctx.getChild(4).getChild(2).toString();
            board = FileParser.CreateBoard(ObjectName, Double.parseDouble(gravity), Double.parseDouble(friction1), Double.parseDouble(friction2));
        }else{
            board = FileParser.CreateBoard(ObjectName, Double.parseDouble(gravity));

        }
    }
    
    /**
     * This method extends ANTLR's GrammarBaseListener.
     * 
     * When exiting out of an Object in the tree (i.e. a ball, bumper, flipper, or absorber),
     * this method extracts the necessary information in the file to create a new corresponding ball or gadget.
     */
    public void exitObject(GrammarParser.ObjectContext ctx){
        ArrayList<Double> doubleContent = new ArrayList<Double>();
        String ObjectType = ctx.getChild(0).getChild(0).toString();
        String ObjectName = ctx.getChild(1).getChild(2).toString();

        for(int x = 2; x < ctx.getChildCount(); x++){
            int lastIndex = ctx.getChild(x).getChildCount() - 1;
            String content = ctx.getChild(x).getChild(lastIndex).toString();
            double value = Double.parseDouble(content);
            doubleContent.add(value);
        }
        
        if (ObjectType.equals("ball")){        
            if(doubleContent.size() != 4) System.err.println("error creating ball: file was parsed incorrectly or did not contain the correct amount of information");
            balls.add(FileParser.createBall(ObjectName, doubleContent.get(0), doubleContent.get(1), doubleContent.get(2), doubleContent.get(3)));
        } else if (ObjectType.equals("squareBumper")){
            if(doubleContent.size() != 2) System.err.println("error creating squareBumper: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(FileParser.createBumper(Shape.SQUARE, ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue(), 0));
        } else if (ObjectType.equals("circleBumper")){
            if(doubleContent.size() != 2) System.err.println("error creating circleBumper: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(FileParser.createBumper(Shape.CIRCLE, ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue(), 0));
        } else if (ObjectType.equals("triangleBumper")){
            if(doubleContent.size() != 3) System.err.println("error creating triangleBumper: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(FileParser.createBumper(Shape.TRIANGLE, ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue(), doubleContent.get(2).intValue()));
        } else if (ObjectType.equals("leftFlipper")){
            if(doubleContent.size() != 3) System.err.println("error creating leftFlipper: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(FileParser.createFlipper(Side.LEFT, ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue(), doubleContent.get(2).intValue()));
        } else if (ObjectType.equals("rightFlipper")){
            if(doubleContent.size() != 3) System.err.println("error creating rightFlipper: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(FileParser.createFlipper(Side.RIGHT, ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue(), doubleContent.get(2).intValue()));
        } else if (ObjectType.equals("absorber")){
            if(doubleContent.size() != 4) System.err.println("error creating absorber: file was parsed incorrectly or did not contain the correct amount of information");
            gadgets.add(FileParser.createAbsorber(ObjectName, doubleContent.get(0).intValue(), doubleContent.get(1).intValue(), doubleContent.get(2).intValue(), doubleContent.get(3).intValue()));            
        }
        
    }

    /**
     * This method extends ANTLR's GrammarBaseListener.
     * 
     * When exiting out of the a Fire Object in the tree, this method extracts the necessary information
     * in the file to store the fire information.  This method stores the fire commands as a list of
     * Strings -> [String trigger, String action] and adds it to the global object fireCmds.
     */
    public void exitFire(GrammarParser.FireContext ctx) { 
        
        String trigger = ctx.getChild(3).getChild(0).toString();
        String action = ctx.getChild(6).getChild(0).toString();
        
        ArrayList<String> gizmo = new ArrayList<String>();
        gizmo.add(trigger);
        gizmo.add(action);
        
        fireCmds.add(gizmo);
    }
    
}

    