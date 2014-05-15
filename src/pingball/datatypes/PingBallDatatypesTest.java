package pingball.datatypes;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * 
 * New Testing Partition Space for all the board datatypes 
 * (not implemented due to time)
 * 
 * (A) General testing for EACH gadget
 *      (A1) Get expected Height/Width (if applicable)
 *      (A2) Get expected position
 *      (A3) Get expected GadgetType
 *      (A4) Get expected toString output
 * (B) Absorbers
 *      (B1) Constructing various sizes (assertEquals: toString, getHeight, getWidth)
 *          (B1a) Square: 1x1, 3x3, 6x6
 *          (B1b) Rectangular (height>width): 3x1, 6x3
 *          (B1c) Rectangular (width>height): 1x3, 3x6
 *      (B2) Absorbing/Releasing Balls
 *          (B2a) Absorbs when only one ball hits it
 *          (B2b) Releases a stored ball when triggered
 *          (B2c) Consecutive balls hit, as releases other ball
 * (C) SquareBumpers/CircleBumpers
 *      (C1) Reflecting balls
 *          (C1a) Reflects one ball
 *          (C1b) Reflects two balls at once
 *          (C1c) Reflects multiple balls at once
 *          (C1d) Reflects multiple consecutive balls
 * (D) TriangleBumpers
 *      (D1) Reflecting balls 
 *          (D1a) Reflects one ball
 *          (D1b) Reflects two balls at once
 *          (D1c) Reflects multiple balls at once
 *          (D1d) Reflects multiple consecutive balls
 *      (D2) Constructing various orientations (0, 90, 180, 270)
 * (E) Left/Right Flippers
 *      (E1) Reflecting balls
 *          (E1a) When stationary, reflects 1 and multiple balls
 *          (E1b) When rotation, reflects 1 and multiple balls
 *          (E1c) When triggered and hits a nearby ball
 *          (E1d) When the flipper is reflected off on both sides
 *          (E1e) When the ball reflects at the pivot/end of the flipper
 *      (E2) Constructing various orientations (0, 90, 180, 270)
 * (F) Portal
 *      (F1) Constructing
 *          (F1a) Pair of portals on one board
 *          (F1B) Pair of portals across two bards
 * (G) BallSpawner
 *      (G1) Triggered by....
 *          (G1a) a keystroke
 *          (G1b) another object
 * (H) Ball
 *      (H1) Get the  velocity, location
 *      (H2) Get the updated ball velocity, location
 *      (H3) Collisions
 *          (H3a) Simultaneous collisions between two balls
 *          (H3b) Simultaneous collisions between 3+ balls
 *          (H3c) Consecutive collisions betwee 3+ balls
 *          (H3d) Collision with non-connected wall
 *          (H3e) Collision with a connected wall to another board
 *      (H4) Interactions with gravity and friction
 * (I) Wall
 *      (I1) Solid/Non-solid walls (i.e. connected to another wall or not)
 *          (I1a) No walls connected
 *          (I1b) All combinations of number of walls being connected (i.e. 1 - 4)
 *          (I1c) Walls of the same board connected (just one pairs and all walls)
 *          (I1d) Combination of walls connected from other boards and with itself
 *      (I2) Collisions with balls
 *          (I2a) One or multiple balls
 *          (I2b) Collisions simultaneous and Consecutive
 *          (I2c) Collisions at a corner
 *          (I2d) Simultaneous Collisions with connected walls at both walls
 *          (I2e) A ball falling exactly parallel against a vertical wall
 * -----------------------------------
 * Old Partition Space (partially implemented)
 * 
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
 * 
 * 
 *
 */

public class PingBallDatatypesTest {
    
    @Test public void testLeftFlippersAction(){
        LeftFlipper leftFlipper = new LeftFlipper("leftFlipper",2,2,0);

        SquareBumper square = new SquareBumper("square",3,1);
        square.addGadgetToFire(leftFlipper);
        
        Ball ball1 = new Ball("ball1",3.2,1.2,-1.0,0);
        square.reflectOff(ball1);
        
        assertTrue(leftFlipper.getState().equals("flipping"));
        
        
        square.reflectOff(ball1);
        
        assertTrue(leftFlipper.getState().equals("deflipping"));
    }
    
    @Test public void testRightFlipperAction(){
        RightFlipper rightFlipper = new RightFlipper("rightFlipper",5,5,0);
        SquareBumper square = new SquareBumper("square",3,1);
        square.addGadgetToFire(rightFlipper);
        Ball ball1 = new Ball("ball1",3.2,1.2,-1.0,0);
        
        square.reflectOff(ball1);
        assertTrue(rightFlipper.getState().equals("flipping"));
        
        square.reflectOff(ball1);
        assertTrue(rightFlipper.getState().equals("deflipping"));
    }
    
    @Test public void testLeftFlippersActionDifferentOrientation(){
        LeftFlipper leftFlipper = new LeftFlipper("leftFlipper",2,2,90);
        
        SquareBumper square = new SquareBumper("square",3,1);
        square.addGadgetToFire(leftFlipper);
        
        
        assertTrue(leftFlipper.toString().equals("- - "));
        
        
        Ball ball1 = new Ball("ball1",3.2,1.2,-5.0,0);
        square.reflectOff(ball1);
        
        assertTrue(leftFlipper.getState().equals("flipping"));
        
        
        assertTrue(leftFlipper.toString().equals("  ||"));
        
        
        square.reflectOff(ball1);
        
        assertTrue(leftFlipper.getState().equals("deflipping"));
        
        
        assertTrue(leftFlipper.toString().equals("  ||"));
        
    }
    
    @Test public void testRightFlipperActionDifferentOrientation(){
        RightFlipper rightFlipper = new RightFlipper("rightFlipper",5,5,90);
        SquareBumper square = new SquareBumper("square",3,1);
        square.addGadgetToFire(rightFlipper);
        assertTrue(rightFlipper.toString().equals(" - -"));
        
        Ball ball1 = new Ball("ball1",3.2,1.2,-5.0,0);
        square.reflectOff(ball1);
        
        assertTrue(rightFlipper.getState().equals("flipping"));
        assertTrue(rightFlipper.toString().equals("  ||"));
        
        square.reflectOff(ball1);
        
        assertTrue(rightFlipper.getState().equals("deflipping"));
        assertTrue(rightFlipper.toString().equals("  ||"));
        
    }
    @Test public void testTimeUntilCollision(){
        Ball ball = new Ball("ball",2.2,1.2,1,0); //moving in x+ direction
        TriangularBumper triangle = new TriangularBumper("triangle",1,1,0);
        SquareBumper square = new SquareBumper("square",3,1);
//        LeftFlipper leftFlipper = new LeftFlipper("leftFlipper",1,1,0);
//        RightFlipper rightFlipper = new RightFlipper("rightFlipper",1,1,90);
        assertTrue(square.timeUntilPhysicsCollision(ball) < triangle.timeUntilPhysicsCollision(ball));   
    }
    /**
    @Test public void testReflectOffGadgetAndAbsorberAction(){
        SquareBumper square = new SquareBumper("square",3,3);
        Ball ball1 = new Ball("ball1",4.2,3.3,-1.0,0); //moving in -x direction
        square.reflectOff(ball1);
        assertTrue(ball1.getNormalVelocity().x() == 1.0);
        assertTrue(ball1.getNormalVelocity().y() == 0);
        
        TriangularBumper triangle = new TriangularBumper("triangle",5,5,180);
        Ball ball2 = new Ball("ball2",6.2,5.2,-1,0); //moving in -x direction
        triangle.reflectOff(ball2);
        assertTrue(ball2.getNormalVelocity().x() == 1);
        assertTrue(ball2.getNormalVelocity().y() == 0);
        
        CircularBumper circular = new CircularBumper("circular",2,2);
        Ball ball3 = new Ball("ball3",3.2,2.5,-1,0); //moving in -x direction
        circular.reflectOff(ball3);
        assertTrue(ball3.getNormalVelocity().x() == 1.0);
        assertTrue(ball3.getNormalVelocity().y() == 0);
        
        LeftFlipper leftFlipper = new LeftFlipper("leftFlipper",3,3,0);
        Ball ball4 = new Ball("ball4",3.25,4,-1,0); //moving in -x direction
        assertTrue(leftFlipper.toString().equals("||  "));
        leftFlipper.addGadgetToFire(leftFlipper);
        leftFlipper.reflectOff(ball4);
        assertTrue(leftFlipper.toString().equals("- - "));

        RightFlipper rightFlipper = new RightFlipper("rightFlipper",3,3,0);
        Ball ball5 = new Ball("ball5",4.75,4,1,0); //moving in +x direction
        assertTrue(rightFlipper.toString().equals("  ||"));
        rightFlipper.addGadgetToFire(rightFlipper);
        rightFlipper.reflectOff(ball5);
        assertTrue(rightFlipper.toString().equals("- - "));
        assertTrue(ball5.getNormalVelocity().x() == -ball4.getNormalVelocity().x());
        assertTrue(ball5.getNormalVelocity().y() == -ball4.getNormalVelocity().y());
        
        Absorber absorber = new Absorber("abs",0,19,20,1);
        Ball ball7 = new Ball("ball7",5,18,0,1); //moving in +y direction
        absorber.reflectOff(ball7);
        assertTrue(ball7.getNormalVelocity().x() == 0);
        assertTrue(ball7.getNormalVelocity().y() == 0);
        assertTrue(ball7.getNormalPosition()[0] == 19.75);
        assertTrue(ball7.getNormalPosition()[1] == 19.75);
        
        absorber.addGadgetToFire(absorber);
        
        Ball ball8 = new Ball("ball8",5,18,0,1); //moving in +y direction
        absorber.reflectOff(ball8);
        assertTrue(ball7.getNormalVelocity().x() == 0);
        assertTrue(ball7.getNormalVelocity().y() == -50.0);
        assertTrue(ball8.getNormalPosition()[0] == 19.75);
        assertTrue(ball8.getNormalPosition()[1] == 19.75);
        
        OuterWall wallLeft = new OuterWall("left", 0,0,0,20,true);
        Ball ball9 = new Ball("ball9",1,1,-1,0); //moving in -x direction
        wallLeft.reflectOff(ball9);
        assertTrue(ball9.getNormalVelocity().x() == 1.0);
        assertTrue(ball9.getNormalVelocity().y() == 0);
        RightFlipper testR = new RightFlipper("testR",2,1,0);
        CircularBumper test = new CircularBumper("test",4,3,0);
        Ball tester = new Ball("tester",4,4,0,-1);
        Ball tester2 = new Ball("tester",4,4,0,-1);
        System.out.println(test.timeUntilPhysicsCollision(tester));
        test.reflectOff(tester);
        testR.reflectOff(tester2);

        
        
    }
    
    @Test public void testNonExistentTargetPortal(){
    	Portal portal1 = new Portal("portal1", 5, 5, "", "nonExistentPortal");
    	Ball ball1 = new Ball("ball1", 5, 5, 0, -1.0); //heading straight down right over the portal
       
        portal1.reflectOff(ball1); //since the portal does not have a valid target portal, nothing should happen to the ball


        assertTrue(ball1.getNormalCircle().getCenter().x() == 5.0);
        assertTrue(ball1.getNormalCircle().getCenter().y() == 5.0);

        assertTrue(ball1.getNormalVelocity().x() == 0.0);
        assertTrue(ball1.getNormalVelocity().x() == -1.0); 
    }
    
    @Test public void testExistentTargetPortal(){
    	Portal portal1 = new Portal("portal1", 5, 5, "", "portal2");
    	Portal portal2 = new Portal("portal2", 5, 12, "", "nonExistentPortal");
    	Ball ball1 = new Ball("ball1", 5, 5, 0, -1.0); //heading straight down right over portal1
       
        portal1.reflectOff(ball1); //since the portal has a valid target portal, the ball should be transported to portal2

        assertFalse(ball1.getNormalCircle().getCenter().x() == 5.0);
        assertFalse(ball1.getNormalCircle().getCenter().y() == 5.0);

        assertTrue(ball1.getNormalVelocity().x() == 0.0);
        assertTrue(ball1.getNormalVelocity().x() == -1.0); 
    }
**/
}
