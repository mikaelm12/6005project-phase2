package pingball.datatypes;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * 
 * @author PeterGithaiga
 * Testing strategy for Gadgets:
 * 1. Test Action
 * flipper:
 *  -test that flipper rotate forward when fired when in the initial state
 *  -test flipper rotate backward after being fired while in the final state
 *  -test flipper rotate properly in different orientation
 *  
 * absorber:
 *  -test absorber stores 1 ball in board if no gadget is triggering
 *  -test absorber stores >1 ball in board if no gadget triggering
 *  -test absorber shoots stored balls when fired
 *  -test absorber shoots ball correctly with height >1
 *  
 * 2. Test timeUntilRe
 * 
 *
 */

public class PingBallDatatypesTest {
    
    @Test public void testLeftFlippersAction(){
        LeftFlipper leftFlipper = new LeftFlipper("leftFlipper",2,2,0);

        SquareBumper square = new SquareBumper("square",3,1);
        square.addGadgetToFire(leftFlipper);
        
        Ball ball1 = new Ball("ball1",3.2,1.2,-1.0,0);
        square.reflectOffGadget(ball1);
        
        assertTrue(leftFlipper.getState().equals("final"));
        
        
        square.reflectOffGadget(ball1);
        
        assertTrue(leftFlipper.getState().equals("initial"));
    }
    
    @Test public void testRightFlipperAction(){
        RightFlipper rightFlipper = new RightFlipper("rightFlipper",5,5,0);
        SquareBumper square = new SquareBumper("square",3,1);
        square.addGadgetToFire(rightFlipper);
        Ball ball1 = new Ball("ball1",3.2,1.2,-1.0,0);
        
        square.reflectOffGadget(ball1);
        assertTrue(rightFlipper.getState().equals("final"));
        
        square.reflectOffGadget(ball1);
        assertTrue(rightFlipper.getState().equals("initial"));
    }
    
    @Test public void testLeftFlippersActionDifferentOrientation(){
        LeftFlipper leftFlipper = new LeftFlipper("leftFlipper",2,2,90);
        
        SquareBumper square = new SquareBumper("square",3,1);
        square.addGadgetToFire(leftFlipper);
        
        
        assertTrue(leftFlipper.toString().equals("- - "));
        
        
        Ball ball1 = new Ball("ball1",3.2,1.2,-5.0,0);
        square.reflectOffGadget(ball1);
        
        assertTrue(leftFlipper.getState().equals("final"));
        
        
        assertTrue(leftFlipper.toString().equals("  ||"));
        
        
        square.reflectOffGadget(ball1);
        
        assertTrue(leftFlipper.getState().equals("initial"));
        
        
        assertTrue(leftFlipper.toString().equals("- - "));
        
    }
    
    @Test public void testRightFlipperActionDifferentOrientation(){
        RightFlipper rightFlipper = new RightFlipper("rightFlipper",5,5,90);
        SquareBumper square = new SquareBumper("square",3,1);
        square.addGadgetToFire(rightFlipper);
        assertTrue(rightFlipper.toString().equals(" - -"));
        
        Ball ball1 = new Ball("ball1",3.2,1.2,-5.0,0);
        square.reflectOffGadget(ball1);
        
        assertTrue(rightFlipper.getState().equals("final"));
        assertTrue(rightFlipper.toString().equals("  ||"));
        
        square.reflectOffGadget(ball1);
        
        assertTrue(rightFlipper.getState().equals("initial"));
        assertTrue(rightFlipper.toString().equals(" - -"));
        
    }
    
    @Test public void testTimeUntilCollision(){
        Ball ball = new Ball("ball",2.2,1.2,1,0); //moving in x+ direction
        TriangularBumper triangle = new TriangularBumper("triangle",1,1,0);
        SquareBumper square = new SquareBumper("square",3,1);
//        LeftFlipper leftFlipper = new LeftFlipper("leftFlipper",1,1,0);
//        RightFlipper rightFlipper = new RightFlipper("rightFlipper",1,1,90);
        assertTrue(square.timeUntilCollision(ball) < triangle.timeUntilCollision(ball));   
    }
    
    @Test public void testReflectOffGadgetAndAbsorberAction(){
        SquareBumper square = new SquareBumper("square",3,3);
        Ball ball1 = new Ball("ball1",4.2,3.3,-1.0,0); //moving in -x direction
        square.reflectOffGadget(ball1);
        assertTrue(ball1.getVelocity().x() == 1.0);
        assertTrue(ball1.getVelocity().y() == 0);
        
        TriangularBumper triangle = new TriangularBumper("triangle",5,5,180);
        Ball ball2 = new Ball("ball2",6.2,5.2,-1,0); //moving in -x direction
        triangle.reflectOffGadget(ball2);
        assertTrue(ball2.getVelocity().x() == 1);
        assertTrue(ball2.getVelocity().y() == 0);
        
        CircularBumper circular = new CircularBumper("circular",2,2);
        Ball ball3 = new Ball("ball3",3.2,2.5,-1,0); //moving in -x direction
        circular.reflectOffGadget(ball3);
        assertTrue(ball3.getVelocity().x() == 1.0);
        assertTrue(ball3.getVelocity().y() == 0);
        
        LeftFlipper leftFlipper = new LeftFlipper("leftFlipper",3,3,0);
        Ball ball4 = new Ball("ball4",3.25,4,-1,0); //moving in -x direction
        assertTrue(leftFlipper.toString().equals("||  "));
        leftFlipper.addGadgetToFire(leftFlipper);
        leftFlipper.reflectOffGadget(ball4);
        assertTrue(leftFlipper.toString().equals("- - "));

        RightFlipper rightFlipper = new RightFlipper("rightFlipper",3,3,0);
        Ball ball5 = new Ball("ball5",4.75,4,1,0); //moving in +x direction
        assertTrue(rightFlipper.toString().equals("  ||"));
        rightFlipper.addGadgetToFire(rightFlipper);
        rightFlipper.reflectOffGadget(ball5);
        assertTrue(rightFlipper.toString().equals("- - "));
        assertTrue(ball5.getVelocity().x() == -ball4.getVelocity().x());
        assertTrue(ball5.getVelocity().y() == -ball4.getVelocity().y());
        
        Absorber absorber = new Absorber("abs",0,19,20,1);
        Ball ball7 = new Ball("ball7",5,18,0,1); //moving in +y direction
        absorber.reflectOffGadget(ball7);
        assertTrue(ball7.getVelocity().x() == 0);
        assertTrue(ball7.getVelocity().y() == 0);
        assertTrue(ball7.getPosition()[0] == 19.75);
        assertTrue(ball7.getPosition()[1] == 19.75);
        
        absorber.addGadgetToFire(absorber);
        
        Ball ball8 = new Ball("ball8",5,18,0,1); //moving in +y direction
        absorber.reflectOffGadget(ball8);
        assertTrue(ball7.getVelocity().x() == 0);
        assertTrue(ball7.getVelocity().y() == -50.0);
        assertTrue(ball8.getPosition()[0] == 19.75);
        assertTrue(ball8.getPosition()[1] == 19.75);
        
        OuterWall wallLeft = new OuterWall("left", 0,0,0,20,true);
        Ball ball9 = new Ball("ball9",1,1,-1,0); //moving in -x direction
        wallLeft.reflectOffGadget(ball9);
        assertTrue(ball9.getVelocity().x() == 1.0);
        assertTrue(ball9.getVelocity().y() == 0);
        RightFlipper testR = new RightFlipper("testR",2,1,0);
        CircularBumper test = new CircularBumper("test",4,3,0);
        Ball tester = new Ball("tester",4,4,0,-1);
        Ball tester2 = new Ball("tester",4,4,0,-1);
        System.out.println(test.timeUntilCollision(tester));
        test.reflectOffGadget(tester);
        testR.reflectOffGadget(tester2);

        
        
    }
    

}
