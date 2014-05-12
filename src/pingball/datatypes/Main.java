package pingball.datatypes;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import BoardExpr.BoardFactory;
import Graphics.SwingTimer;
import physics.Geometry;
import physics.Geometry.VectPair;
import physics.Vect;
import pingball.datatypes.Ball;
import pingball.datatypes.Gadget;
import pingball.datatypes.OuterWall;

public class Main {
    
    /**
     * TODO: describe your main function's command line arguments here
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException{
        //File file = new File("/Users/mikemikael3/Dropbox/School/Semester 4/6.005/pingball-phase2/boards/board5.txt");

    	File file = new File("/Users/ahochstadt/pingball-phase2/boards/board1.txt");
        //File file = new File("/Users/mikemikael3/Dropbox/School/Semester 4/6.005/pingball-phase2/boards/board1.txt");


        String fileString = "";
        BufferedReader inputFileStream = null;
        try {
           inputFileStream = new BufferedReader(new FileReader(file));
           String l;
           while ((l = inputFileStream.readLine()) != null) {
               fileString += l + "\n";
           }
       } finally {
           if (inputFileStream != null) {
               inputFileStream.close();
           }
       }
        // Create the board
        final Board board  =  BoardFactory.parse(fileString);
        
      // final Board board = new Board("board",25,0.025,0.025);
        SquareBumper square1 = new SquareBumper("square1",4,2);
        SquareBumper square2 = new SquareBumper("square2",7,2);
        SquareBumper square3 = new SquareBumper("square3",5,2);
        SquareBumper square4 = new SquareBumper("square4",6,2);
        CircularBumper circleBumper = new CircularBumper("circleBumper",8,3);
        TriangularBumper triangleBumper = new TriangularBumper("triangleBumper",19,1,270);
        //LeftFlipper flipperL = new LeftFlipper("flipperL",6,7,0);
        //RightFlipper flipperR = new RightFlipper("flipperR",10,7,0);
        SquareBumper square34 = new SquareBumper("square2",10,14);
        SquareBumper square35= new SquareBumper("square3",11,14);
        SquareBumper square36 = new SquareBumper("square2",12,14);
        SquareBumper square37= new SquareBumper("square3",13,14);
        SquareBumper square38 = new SquareBumper("square2",14,14);
        SquareBumper square39= new SquareBumper("square3",15,14);
        SquareBumper square40 = new SquareBumper("square4",6,2);
        Absorber absorber = new Absorber("abs",0,15,20,5);
        //square.addGadgetToFire(flipperL);
        //flipperL.addGadgetToFire(flipperR);
        //flipperL.addGadgetToFire(flipperL);
        //flipperR.addGadgetToFire(flipperR);
        absorber.addGadgetToFire(absorber);
        //square2.addGadgetToFire(flipperL);
//        board.addGadgetList(Arrays.asList(square1,square2,square3,square4,circleBumper,triangleBumper,flipperL,flipperR,absorber));
//        Ball ball1 = new Ball("ball",10,10,-3.4,-2.3);
//        Ball ball2 = new Ball("ball",5,5,4,2);
//        board.addBall(ball1);
//        board.addBall(ball2);
//        Board neighbor = new Board("neir",25,0.025,0.025);
//        board.setNeighborBottom(neighbor);
//        board.setNeighborTop(neighbor);
        
       EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {                
                JFrame ex = new SwingTimer(board);
                ex.setMinimumSize(new Dimension(425, 425));
                ex.setVisible(true);                
            }
        });
       
       
        
        long start = System.currentTimeMillis();

        while(true){
            long current = System.currentTimeMillis();

            if ((current-start) % 30 == 0){
                double timestep = 0.01;
                update(board, timestep);
                System.out.println(board.toString());
            }
        }
    }

    /**
     * Updates the board after one timestep     
     * @param board the board to update
     * @param timestep timestep in seconds
     */
	private static void update(Board board, double timestep) {
		for (Ball ball: board.getBalls()){
			synchronized(ball){
				ball.updateBallVelocityBeforeTimestep(board.getGravity(), board.getMu(), board.getMu2(), timestep);
			}

			System.out.println("Position: "+ball.getNormalCircle().getCenter().x()+", "+ball.getNormalCircle().getCenter().y());
			System.out.println("Velocity: "+ball.getNormalVelocity());
		}
		double timestepLeft = timestep+0;
		while (timestepLeft > 0){
			double timeUntilFirstCollision = getTimeUntilFirstCollision(board);
			System.out.println("timeUntilFirstCollision: "+timeUntilFirstCollision);
			System.out.println("timestepLeft: "+timestepLeft);
			
			if (timeUntilFirstCollision<=timestepLeft){ //we have a collision in the timestep
				updateWithCollision(board, timeUntilFirstCollision);
				timestepLeft -= timeUntilFirstCollision;
			} else { //we have no collision in the timestep
				updateWithoutCollision(board, timestepLeft);
				timestepLeft = 0;
			}
		}		
	}

	/**
	 * updates the board one subtimestep (timeUntilFirstCollision), and then executes changes in ball velocity due to the pending collision(s)
	 * @param board to update
	 * @param timeUntilFirstCollision time until the first collision in seconds
	 */
	private static void updateWithCollision(Board board, double timeUntilFirstCollision) { //will not work correctly if a ball collides with two things at the EXACT same time--which is extremely improbable
		//updateWithoutCollision(board, timeUntilFirstCollision); //we will update flippers and balls as usual, and then collide the balls
		System.out.println("in updateWithCollision()");
		
		List<String> namesOfBallsCollided = new ArrayList<String>();
		
		for (Ball ball: board.getBalls()){
			System.out.println("ball.inAbsorber()==" +ball.inAbsorber());

			if (!ball.inAbsorber()){
				for (Ball ball2: board.getBalls()){
					if (!namesOfBallsCollided.contains(ball.getName()) && !namesOfBallsCollided.contains(ball2.getName()) && !ball.getName().equals(ball2.getName())){ //make sure to only collide balls that have not been collided yet
						if (ball.timeUntilPhysicsCollision(ball2)<=timeUntilFirstCollision){
							System.out.println("Two Balls are colliding");
							VectPair newVels = Geometry.reflectBalls(ball.getPhysicsCircle().getCenter(), 1.0, ball.getPhysicsVelocity(), ball2.getPhysicsCircle().getCenter(), 1.0, ball2.getPhysicsVelocity());
							ball.updateBallPosition(timeUntilFirstCollision); //update the positions to right before the collision
							ball2.updateBallPosition(timeUntilFirstCollision);
							ball.setPhysicsVelocity(newVels.v1); //set the velocities to their post-collision values
							ball2.setPhysicsVelocity(newVels.v2);
							namesOfBallsCollided.add(ball.getName());
							namesOfBallsCollided.add(ball2.getName());
						}
					}
					
				}
				if (!ball.inAbsorber()){
					for (OuterWall wall: board.getOuterWalls()){ 
						if (!namesOfBallsCollided.contains(ball.getName())){ //make sure to only collide balls that have not been collided yet
							if(wall.timeUntilPhysicsCollision(ball)<=timeUntilFirstCollision){ //we are colliding with the wall
								System.out.println("we are colliding with the wall");
								Vect oldV = ball.getPhysicsVelocity();
								wall.reflectOff(ball);
								ball.updateBallPositionUsingOldPhysicsVelocity(timeUntilFirstCollision, oldV);
								namesOfBallsCollided.add(ball.getName());
							}
						}
					}
				}
				for (Gadget gadget: board.getGadgets()){
					if (!namesOfBallsCollided.contains(ball.getName())){ //make sure to only collide balls that have not been collided yet
						if (gadget.timeUntilPhysicsCollision(ball)<=timeUntilFirstCollision){ //we are colliding with the gadget
							Vect oldV = ball.getPhysicsVelocity();
							gadget.reflectOff(ball);
							ball.updateBallPositionUsingOldPhysicsVelocity(timeUntilFirstCollision, oldV);
							namesOfBallsCollided.add(ball.getName());
						}
					}
				}
				if (!namesOfBallsCollided.contains(ball.getName())){
					ball.updateBallPosition(timeUntilFirstCollision);
					namesOfBallsCollided.add(ball.getName());
				}
			}
		}
	}

	/**
	 * updates the board by one timestep. The board must not have a collision at the end of the timestep.
	 * @param board the board to be updated
	 * @param timestep the timestep to execute
	 */
	private static void updateWithoutCollision(Board board, double timestep) {
		for (Ball ball: board.getBalls()){
			if (!ball.inAbsorber()){
				ball.updateBallPosition(timestep);
			}
		}
		for (LeftFlipper leftFlipper: board.getLeftFlippers()){
			leftFlipper.update(timestep);
		}
		for (RightFlipper rightFlipper: board.getRightFlippers()){
			rightFlipper.update(timestep);
		}
	}

	/**
	 * Calculates the time until the first collision happens on the board
	 * @param board the board on which the collision is happening
	 * @return the time until the board's first collision, in seconds
	 */
	private static double getTimeUntilFirstCollision(Board board) {
		double timeUntilFirstCollision = Double.POSITIVE_INFINITY;
		for (Ball ball: board.getBalls()){
			for (OuterWall wall: board.getOuterWalls()){
				timeUntilFirstCollision = Math.min(timeUntilFirstCollision, wall.timeUntilPhysicsCollision(ball));
			}
			for (Gadget gadget: board.getGadgets()){
				timeUntilFirstCollision = Math.min(timeUntilFirstCollision, gadget.timeUntilPhysicsCollision(ball));
			}
			for (Ball ball2: board.getBalls()){
				timeUntilFirstCollision = Math.min(timeUntilFirstCollision, ball.timeUntilPhysicsCollision(ball2));
			}
		}
		return timeUntilFirstCollision;
	}

}
