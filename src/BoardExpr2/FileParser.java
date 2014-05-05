package BoardExpr2;

import physics.Circle;
import physics.Vect;
//import pingball.Bumper.Shape;
//import pingball.Flipper.Side;

public class FileParser {
    
    /**
     * This method creates a new Board object, after calling helper methods to parse the file
     */
    public static Board CreateBoard(String name, double gravity, double friction1, double friction2){
        return new Board(name, gravity, friction1, friction2);
    }
    
    /**
     * This method creates a new Board object, after calling helper methods to parse the file
     */
    public static Board CreateBoard(String name, double gravity){
        return new Board(name, gravity);
    }
    
    /**
     * This method creates a new Ball object given a line of text that supplies the information
     * for the Ball (String:name, int:x, int:y, double:xVelocity, double:yVelocity)
     * @param text from File
     * @return new Ball object
     */
    public static Ball createBall(String name, double xLoc, double yLoc, double xVel, double yVel){
        double r = .25;
        Circle circleIn = new Circle(xLoc, yLoc,r);
        Vect vectIn = new Vect(xVel,yVel);
        return new Ball(circleIn, vectIn, name);
    }
    
    /**
     * This method creates a new Bumper object given a line of text that supplies the information
     * for the Bumper
     * @param text from File
     * @return new Bumper object
     */
    public static Bumper createBumper(Shape shape, String name, int xLoc, int yLoc, int orientation){
//        new Bumper(name,x,y,Type.SQUARE);
        
        //add switch case for each constructor
        switch(shape){
        case TRIANGLE:
            return new Bumper(shape, name, xLoc, yLoc, orientation);
        default:
            return new Bumper(shape, name, xLoc, yLoc);
        }
    }
    
    /**
     * This method creates a new Flipper object given a line of text that supplies the information
     * for the Flipper
     * @param text from File
     * @return new Flipper object
     */
    public static Flipper createFlipper(Side side, String name, int xLoc, int yLoc, int orientation){
//        Side side = Side.LEFT;
        return new Flipper(name, xLoc, yLoc, orientation, side);
    }
   
    
    /**
     * This method creates a new Absorber object given a line of text that supplies the information
     * for the Absorber
     * @param text from File
     * @return new Absorber object
     */
    public static Absorber createAbsorber(String name, int xLoc, int yLoc, int height, int width){
        return new Absorber(name, xLoc, yLoc, height, width);
    }
    
}