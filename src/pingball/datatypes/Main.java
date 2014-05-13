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
import BoardExpr2.GrammarFactory;
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
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception{
        //File file = new File("/Users/mikemikael3/Dropbox/School/Semester 4/6.005/pingball-phase2/boards/board5.txt");

    	File file = new File("src/../boards/board1P.txt");
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
        final Board board  =  GrammarFactory.parse(fileString);
        System.out.println(board);
        
       EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {                
                JFrame ex = new SwingTimer(board);
                ex.setMinimumSize(new Dimension(430, 475));
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

//			System.out.println("Position: "+ball.getNormalCircle().getCenter().x()+", "+ball.getNormalCircle().getCenter().y());
//			System.out.println("Velocity: "+ball.getNormalVelocity());
			
		}
		double timestepLeft = timestep+0;
		while (timestepLeft > 0){
			double timeUntilFirstCollision = getTimeUntilFirstCollision(board);
//			System.out.println("timeUntilFirstCollision: "+timeUntilFirstCollision);
//			System.out.println("timestepLeft: "+timestepLeft);
			
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
//		System.out.println("in updateWithCollision()");
		
		List<String> namesOfBallsCollided = new ArrayList<String>();
		
		for (Ball ball: board.getBalls()){
//			System.out.println("ball.inAbsorber()==" +ball.inAbsorber());

			if (!ball.inAbsorber()){
				for (Ball ball2: board.getBalls()){
					if (!namesOfBallsCollided.contains(ball.getName()) && !namesOfBallsCollided.contains(ball2.getName()) && !ball.getName().equals(ball2.getName())){ //make sure to only collide balls that have not been collided yet
						if (ball.timeUntilPhysicsCollision(ball2)<=timeUntilFirstCollision){
//							System.out.println("Two Balls are colliding");
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
//								System.out.println("we are colliding with the wall");
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
		
		//send all the balls in the portals
				for (Portal portal: board.getPortals()){
					if(getTargetPortal(board, portal)!=null){
						for(Ball sentBall: portal.getSentBallQueue()){
							Portal targetPortal = getTargetPortal(board, portal);
							targetPortal.receiveBall(sentBall);
							board.removeBall(sentBall);
						}
					}
				}
				//recieve all the balls in the portals
				for (Portal portal: board.getPortals()){
					for(Ball receivedBall: portal.getReceivedBallQueue()){
						board.addBall(receivedBall);
					}
				}
		
		for (LeftFlipper leftFlipper: board.getLeftFlippers()){
			leftFlipper.update(timeUntilFirstCollision);
		}
		for (RightFlipper rightFlipper: board.getRightFlippers()){
			rightFlipper.update(timeUntilFirstCollision);
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
		//send all the balls in the portals
		for (Portal portal: board.getPortals()){
			if(getTargetPortal(board, portal)!=null){
				for(Ball sentBall: portal.getSentBallQueue()){
					Portal targetPortal = getTargetPortal(board, portal);
					targetPortal.receiveBall(sentBall);
					board.removeBall(sentBall);
				}
			}
		}
		//recieve all the balls in the portals
		for (Portal portal: board.getPortals()){
			
			for(Ball receivedBall: portal.getReceivedBallQueue()){
				System.out.println("receivedBall == null: " + receivedBall == null);
				board.addBall(receivedBall);
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
	
	/**
	 * Gets the target portal of a source portal if it can be found.
	 * @param targetBoard the board housing the target portal
	 * @param sourcePortal the source portal
	 * @return target portal of a source portal if it can be found, else null.
	 */
	private static Portal getTargetPortal(Board targetBoard, Portal sourcePortal){
		List<Portal> portalList = targetBoard.getPortals();
		for (Portal portal: portalList){
			if (sourcePortal.getTargetPortalBoardName().equals(targetBoard.getName())){//makes sure that the target board is correct. Since this is in main, this should be true unless the sourcePortal points to a different board.
				if (portal.getName().equals(sourcePortal.getTargetPortalName())){
					return portal;
				}
			}
		}
		return null;
	}

}
