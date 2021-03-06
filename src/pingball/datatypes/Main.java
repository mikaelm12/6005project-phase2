package pingball.datatypes;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import BoardExpr.GrammarFactory;
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


    	//File file = new File("/Users/ahochstadt/pingball-phase2/boards/board2.txt");

    	File file = new File("src/../boards/portalWorldLeft.txt");



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
        
       EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() { 
                JFrame ex = new SwingTimer(board);
                ex.setMinimumSize(new Dimension(430, 475));
                ex.setVisible(true);                
            }
        });
       
        long start = System.currentTimeMillis();
        for (Portal portal: board.getPortals()){
        	if(getTargetPortal(board, portal)==null){//there is no valid destination portal
        		portal.setHasDestinationPortal(false);
        	} else {//there is a valid destination portal
        		portal.setHasDestinationPortal(true);
        	}
        }
        
        while(true){
            long current = System.currentTimeMillis();

            if ((current-start) % 20 == 0){
                double timestep = 90.0/1080.0/100.0; //it will take exactly 1000 timesteps for the flipper to rotate

                update(board, timestep);
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
		}
		double timestepLeft = timestep+0;
		while (timestepLeft > 0){
			double timeUntilFirstCollision = getTimeUntilFirstCollision(board);
			
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
		List<String> namesOfBallsCollided = new ArrayList<String>();
		
		for (Ball ball: board.getBalls()){
			if (!ball.inAbsorber()){
				for (Ball ball2: board.getBalls()){
					if (!namesOfBallsCollided.contains(ball.getName()) && !namesOfBallsCollided.contains(ball2.getName()) && !ball.getName().equals(ball2.getName())){ //make sure to only collide balls that have not been collided yet
						if (ball.timeUntilPhysicsCollision(ball2)<=timeUntilFirstCollision){
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
				if (!namesOfBallsCollided.contains(ball.getName())){ //make sure to only collide balls that have not been collided yet
					for (Portal portal: board.getPortals()){
						if (portal.timeUntilPhysicsCollision(ball)<=timeUntilFirstCollision){ //we are colliding with the portal
							Vect oldV = ball.getPhysicsVelocity();
							portal.reflectOff(ball);
							ball.updateBallPositionUsingOldPhysicsVelocity(timeUntilFirstCollision, oldV);
							namesOfBallsCollided.add(ball.getName());
							
						}
					}
				}
				
				if (!ball.inAbsorber()){
					for (OuterWall wall: board.getOuterWalls()){ 
						if (!namesOfBallsCollided.contains(ball.getName())){ //make sure to only collide balls that have not been collided yet
							if(wall.timeUntilPhysicsCollision(ball)<=timeUntilFirstCollision){ //we are colliding with the wall
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
				synchronized (portal.getOutQueue()) {
					for(Ball sentBall: portal.getOutQueue()){
						Portal targetPortal = getTargetPortal(board, portal);
						targetPortal.receiveBall(sentBall);
					}
					portal.getOutQueue().clear();
				}
			}
		}
		
		//update the flipper positions
		for (LeftFlipper leftFlipper: board.getLeftFlippers()){
			leftFlipper.update(timeUntilFirstCollision);
		}
		for (RightFlipper rightFlipper: board.getRightFlippers()){
			rightFlipper.update(timeUntilFirstCollision);
		}
		for (BallSpawner spawner: board.getSpawners()){
			synchronized(spawner.getCreatedBallQueue()){
				for(Ball createdBall: spawner.getCreatedBallQueue()){
					board.addBall(createdBall);
				}
				spawner.getCreatedBallQueue().clear();
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
		for (BallSpawner spawner: board.getSpawners()){
			synchronized(spawner.getCreatedBallQueue()){
				for(Ball createdBall: spawner.getCreatedBallQueue()){
					board.addBall(createdBall);
				}
				spawner.getCreatedBallQueue().clear();
			}
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
		
			for (Portal portal: board.getPortals()){
				timeUntilFirstCollision = Math.min(timeUntilFirstCollision, portal.timeUntilPhysicsCollision(ball));
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
