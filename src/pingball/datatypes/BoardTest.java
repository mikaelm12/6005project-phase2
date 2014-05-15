package pingball.datatypes;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test Strategy: 
 * Testing adding and removing components to the board
 * note: the testing of gadget interactions are in the PingBallDatatypesTest file
 * 
 * (A) Balls
 *      (A1) Adding 1 ball
 *      (A2) Adding multiple balls
 *      (A3) Removing 1 ball
 *      (A4) Removing multiple balls
 * (B) Gadgets (Absorber, BallSpawner, Circular/Square/Triangular Bumper, Left/Right Flipper, Portal)
 *      (B1) Add one gadget
 *      (B2) Add multiple gadgets to a board
 *      (B3) Can add each type of gadget (and all possible orientations)
 * (C) Additional tests
 *      (C1) Empty Board
 */


public class BoardTest {
    static Ball ball;
    static Ball ball2;
    static Ball ball3;
    static Ball ball4;
    static Ball ball5;
    
    static Board BoardBalls;

    static Absorber absorber;
    static BallSpawner ballSpawner;
    static CircularBumper circularBumper;
    static SquareBumper squareBumper;
    static TriangularBumper triangularBumper;
    static TriangularBumper triangularBumper2;
    static TriangularBumper triangularBumper3;
    static TriangularBumper triangularBumper4;
    static LeftFlipper leftFlipper;
    static LeftFlipper leftFlipper2;
    static RightFlipper rightFlipper;
    static RightFlipper rightFlipper2;
    static Portal portal;
    static Portal portal2;

    static Board GadgetBoard;
    
    
    @BeforeClass
    public static void setUpBeforeClass() {
        ball = new Ball("ball",2.2,1.2,1,2);
        ball2 = new Ball("ball2",4.2,1.2,1,9);
        ball3 = new Ball("ball3",5.2,2,2,4);
        ball4 = new Ball("ball4",4.2,7,1,2);
        ball5 = new Ball("ball5",8,1,1,4);
        
        BoardBalls = new Board("IGotBalls", 20.0, 0.025, 0.025);
        BoardBalls.addBall(ball);
        BoardBalls.addBall(ball2);
        BoardBalls.addBall(ball3);
        BoardBalls.addBall(ball4);
        BoardBalls.addBall(ball5);
        
        
        GadgetBoard = new Board("IGotGadgets", 20.0, 0.025, 0.025);

        //TODO: FIX ABSORBER doest work for (width,height):
        // (3,2) (5,1), (4,1)
        // Seems like when the width is greater than the height
        absorber = new Absorber("Sponge", 6, 4, 1, 4);
        ballSpawner = new BallSpawner("Maker", 19, 19);
        circularBumper = new CircularBumper("circ", 15, 15);
        squareBumper = new SquareBumper("sqr", 12, 12);
        triangularBumper = new TriangularBumper("triB", 18, 19, 0);
        triangularBumper2 = new TriangularBumper("triB2", 17, 19, 90);
        triangularBumper3 = new TriangularBumper("triB3", 16, 19, 180);
        triangularBumper4 = new TriangularBumper("triB4", 15, 19, 270);
        leftFlipper = new LeftFlipper("FLIPOFFFFFF", 16, 16, 0);
        leftFlipper2 = new LeftFlipper("FLIPOFFFFFFNOWWWWWWW", 17, 16, 90);
        rightFlipper = new RightFlipper("GAHHHHH", 13, 17, 0);
        rightFlipper2 = new RightFlipper("GAHHHHHTestsSuck", 12, 16, 90);
        portal = new Portal("p1", 19, 18, "", "p2");
        portal2 = new Portal("p2", 18, 18, "", "p1");

        GadgetBoard.addGadget(absorber);
        GadgetBoard.addGadget(ballSpawner);
        GadgetBoard.addGadget(circularBumper);
        GadgetBoard.addGadget(squareBumper);
        GadgetBoard.addGadget(triangularBumper);
        GadgetBoard.addGadget(triangularBumper2);
        GadgetBoard.addGadget(triangularBumper3);
        GadgetBoard.addGadget(triangularBumper4);
        GadgetBoard.addGadget(leftFlipper);
        GadgetBoard.addGadget(leftFlipper2);
        GadgetBoard.addGadget(rightFlipper);
        GadgetBoard.addGadget(rightFlipper2);
        GadgetBoard.addGadget(portal);
        GadgetBoard.addGadget(portal2);
    }
   
    // Tests A1
    @Test public void addOneBall(){
        Board OnBallBoard = new Board("GotABall", 20.0, 0.025, 0.025);
        OnBallBoard.addBall(ball);
        assertEquals(1,OnBallBoard.getBalls().size());
        String expectedOutput = "......................\n"
                + ".                    .\n"
                + ".  *                 .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + "......................";

        assertEquals(expectedOutput,OnBallBoard.toString());

    }    
        
    //Tests A2
    @Test public void addMultipleBalls(){
        assertEquals(5,BoardBalls.getBalls().size());
        
        String expectedOutput = "......................\n"
                + ".                    .\n"
                + ".  * *   *           .\n"
                + ".     *              .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".    *               .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + "......................";
        assertEquals(expectedOutput,BoardBalls.toString());
    }
    
  //Tests A3
    @Test public void removeABall(){
        Board BoardRemoveBalls = BoardBalls;
        BoardRemoveBalls.removeBall(ball);

        assertEquals(4,BoardRemoveBalls.getBalls().size());
        
        String expectedOutput = "......................\n"
                + ".                    .\n"
                + ".    *   *           .\n"
                + ".     *              .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".    *               .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + "......................";

        assertEquals(expectedOutput,BoardRemoveBalls.toString());
        
    }
    
    //Tests A4
    @Test public void removeAllBallz(){
        Board BoardRemoveBalls = BoardBalls;
        BoardRemoveBalls.removeBall(ball);
        BoardRemoveBalls.removeBall(ball2);
        BoardRemoveBalls.removeBall(ball3);
        BoardRemoveBalls.removeBall(ball4);
        BoardRemoveBalls.removeBall(ball5);

        assertEquals(0,BoardRemoveBalls.getBalls().size());
        
        String expectedOutput = "......................\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + "......................";

        assertEquals(expectedOutput,BoardRemoveBalls.toString());
        
    }
    
    //Tests B1
    @Test public void addGadget(){
        Board testBoard = new Board("testBoard1", 20.0, 0.025, 0.025);
        SquareBumper sqb = new SquareBumper("square", 3, 5);
        testBoard.addGadget(sqb);
        assertEquals(1,testBoard.getGadgets().size());
//        System.out.println(testBoard.toString());
        
        String expectedOutput = "......................\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".   #                .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + "......................";
        assertEquals(expectedOutput,testBoard.toString());
        
    }
    
    //Test B2
    @Test public void addMultipleGadgets(){
        Board testBoard = new Board("testBoard1", 20.0, 0.025, 0.025);
        SquareBumper sqb = new SquareBumper("square", 3, 5);
        LeftFlipper lf = new LeftFlipper("leftFlip", 10, 10,90);
        CircularBumper circ = new CircularBumper("CBump", 13, 17);
        testBoard.addGadget(sqb);
        testBoard.addGadget(lf);
        testBoard.addGadget(circ);
        assertEquals(3,testBoard.getGadgets().size());
        
        String expectedOutput = "......................\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".   #                .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".          --        .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".             O      .\n"
                + ".                    .\n"
                + ".                    .\n"
                + "......................";
        assertEquals(expectedOutput,testBoard.toString());

    }
    
    //Test B3
    @Test public void addAllTypesGadgets(){
        assertEquals(14,GadgetBoard.getGadgets().size());
        String expectedOutput = "......................\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".      =             .\n"
                + ".      =             .\n"
                + ".      =             .\n"
                + ".      =             .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".            #       .\n"
                + ".                    .\n"
                + ".                    .\n"
                + ".               O    .\n"
                + ".                |-- .\n"
                + ".            --| |   .\n"
                + ".              |   PP.\n"
                + ".               \\/\\/S.\n"
                + "......................";
        assertEquals(expectedOutput,GadgetBoard.toString());

    }
    
    //Tests C1
    @Test public void EmptyBoard(){
        Board board3 = new Board("testBoard1", 20.0, 0.025, 0.025);
        String board = board3.toString();
        String compare = "......................\n"+
        ".                    .\n"+ 
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        "......................";
        assertEquals(board, compare);
        
        
    }
    
    //B1
    @Test public void OneSquareBumperTest(){
        Board board3 = new Board("testBoard1", 20.0, 0.025, 0.025);
        SquareBumper sqb = new SquareBumper("square", 3, 5);
        board3.addGadget(sqb);
        String board = board3.toString();
        String compare = "......................\n"+
        ".                    .\n"+ 
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".   #                .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        "......................";
        assertEquals(board, compare);
        
        
    }
    
    //B1
    @Test public void OneCircleGadgetTest(){
        Board board3 = new Board("testBoard1", 20.0, 0.025, 0.025);
        CircularBumper circ = new CircularBumper("ciclre", 3, 5);
        board3.addGadget(circ);
        String board = board3.toString();
        String compare = "......................\n"+
        ".                    .\n"+ 
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".   O                .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        "......................";
        assertEquals(board, compare);
        
        
    }
    
    //B1
    @Test public void OneTriangularGadgetTest(){
        Board board3 = new Board("testBoard1", 20.0, 0.025, 0.025);
        TriangularBumper circ = new TriangularBumper("triangular", 3, 5, 270);
        board3.addGadget(circ);
        String board = board3.toString();
        String compare = "......................\n"+
        ".                    .\n"+ 
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".   \\                .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        "......................";
        assertEquals(board, compare);
        
        
    }
    
    //B1
    @Test public void OneTriangular180GadgetTest(){
        Board board3 = new Board("testBoard1", 20.0, 0.025, 0.025);
        TriangularBumper circ = new TriangularBumper("triangular", 3, 5, 180);
        board3.addGadget(circ);
        String board = board3.toString();
        String compare = "......................\n"+
        ".                    .\n"+ 
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".   /                .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        "......................";
        assertEquals(board, compare);
        
        
    }
    
    //B1
    @Test public void OneAbsorber(){
        Board board3 = new Board("testBoard1", 20.0, 0.025, 0.025);
        Absorber circ = new Absorber("abs", 3, 5, 2,2);
        board3.addGadget(circ);
        String board = board3.toString();
        String compare = "......................\n"+
        ".                    .\n"+ 
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".   ==               .\n"+
        ".   ==               .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        ".                    .\n"+
        "......................";
        assertEquals(board, compare);
        
        
    }
    
    
}
