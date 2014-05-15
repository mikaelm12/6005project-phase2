package pingball.server;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import BoardExpr.BoardFactory;
import BoardExpr2.GrammarFactory;
import Graphics.SwingTimer;
import physics.Geometry;
import physics.Vect;
import physics.Geometry.VectPair;
import pingball.datatypes.Ball;
import pingball.datatypes.Board;
import pingball.datatypes.Gadget;
import pingball.datatypes.LeftFlipper;
import pingball.datatypes.OuterWall;
import pingball.datatypes.RightFlipper;

/**
 * Thread to handle a single client's game
 * @author AlexR
 *
 */
public class PingballClientThread extends Thread {
    private final Socket socket;
    private final Board board;
    private final World world;
    
    /**
     * Initializes a user
     * @param socket to be used by this thread
     * @param world where this client's game is stored
     * @throws Exception 
     */
    public PingballClientThread(Socket socket, World world) throws Exception {
        super("PingballClientThread");
        String kill = "END OF FILE!!";
        this.socket = socket;
        this.world = world;
        //Receive and recreate the file of this user to create the corresponding board
        //PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        String fileBoard = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String line;
        while ((line = in.readLine()) != null){
            if (line.equals(kill)){
                break;
            }
            fileBoard+= line + "\n";
        }
        board = GrammarFactory.parse(fileBoard);
        world.addBoard(board);
    }

    /**
     * Run this specific thread
     */
    public void run() {
        try {
            handleConnection(socket, world);
        } catch (IOException e) {
            e.printStackTrace(); // but don't terminate serve()
        } finally {
            try {
                world.removeBoard(board);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Handle a single client connection. Returns when client disconnects.
     * 
     * @param socket socket where the client is connected
     * @throws IOException if connection has an error or terminates unexpectedly
     */
    public void handleConnection (Socket socket, World world) throws IOException{
        //Game is played here. Output to the client is the string representation of the board
        //sent every so often. 
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        //PLAY!!
        //18.206.1.78
//       EventQueue.invokeLater(new Runnable() {
//            
//            @Override
//            public void run() {                
//                SwingTimer gui = new SwingTimer(board);
//                gui.setCanvas(board);
//                gui.setMinimumSize(new Dimension(430, 475));
//                gui.setVisible(true);   
//                
//               
//    
//            }
//        });
        System.out.println("Client Thread Loop Before");
        long start = System.currentTimeMillis();
        while(true){
            long current = System.currentTimeMillis();
            if ((current-start) % 300 == 0 && !board.isPaused()){
                double timestep = 0.01;
                update(board, world, timestep);
                String output = board.ballGraphicsInfo() + board.flipperGraphicsInfo();
                out.println(output);
                }
        
        }
    }
    
    private static void update(Board board, World world, double timestep) {
		for (Ball ball: board.getBalls()){
			synchronized(ball){
				ball.updateBallVelocityBeforeTimestep(board.getGravity(), board.getMu(), board.getMu2(), timestep);
			}

			//System.out.println("Position: "+ball.getNormalCircle().getCenter().x()+", "+ball.getNormalCircle().getCenter().y());
			//System.out.println("Velocity: "+ball.getNormalVelocity());
		}
		double timestepLeft = timestep+0;
		while (timestepLeft > 0){
			double timeUntilFirstCollision = getTimeUntilFirstCollision(board);
			//System.out.println("timeUntilFirstCollision: "+timeUntilFirstCollision);
			if (timeUntilFirstCollision<=timestepLeft){ //we have a collision in the timestep
				updateWithCollision(board, world, timeUntilFirstCollision);
				timestepLeft -= timeUntilFirstCollision;
			} else { //we have no collision in the timestep
				updateWithoutCollision(board, timestep);
				timestepLeft = 0;
			}
		}		
	}

    
    
    private static void updateWithCollision(Board board, World world, double timeUntilFirstCollision) { //will not work correctly if a ball collides with two things at the EXACT same time--which is extremely improbable
		//updateWithoutCollision(board, timeUntilFirstCollision); //we will update flippers and balls as usual, and then collide the balls
		List<Ball> ballsToTransfer = new ArrayList<Ball>();
		List<String> namesOfBallsCollided = new ArrayList<String>();
		
		for (Ball ball: board.getBalls()){
			for (Ball ball2: board.getBalls()){
				if (!namesOfBallsCollided.contains(ball.getName()) && !namesOfBallsCollided.contains(ball2.getName()) && !ball.getName().equals(ball2.getName())){ //make sure to only collide balls that have not been collided yet
					if (ball.timeUntilPhysicsCollision(ball2)<=timeUntilFirstCollision){
						//System.out.println("Two Balls are colliding");
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
			for (OuterWall wall: board.getOuterWalls()){ 
				if (!namesOfBallsCollided.contains(ball.getName())){ //make sure to only collide balls that have not been collided yet
					if(wall.timeUntilPhysicsCollision(ball)<=timeUntilFirstCollision){ //we are colliding with the wall
						if(wall.isSolid()){ //we are colliding with the wall
							//System.out.println("we are colliding with the wall");
							Vect oldV = ball.getPhysicsVelocity();
							wall.reflectOff(ball);
							ball.updateBallPositionUsingOldPhysicsVelocity(timeUntilFirstCollision, oldV);
							namesOfBallsCollided.add(ball.getName());
						} else { //we are going to transfer the ball
							ball.updateBallPosition(timeUntilFirstCollision);
							world.transferBall(board, ball, wall);
							ballsToTransfer.add(ball);
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
		// To avoid modifying the list of balls in the middle of an iteration
        // wait until the iteration is over to add incoming balls and delete outgoing balls
        // if any of those exist
        for (Ball ball: ballsToTransfer){
        	board.removeBall(ball);
        }
        
        for (Ball ball : board.getIncomingBalls()){
            board.addBall(ball);            
        }
	}

	private static void updateWithoutCollision(Board board, double timestep) {
		for (Ball ball: board.getBalls()){
			ball.updateBallPosition(timestep);
		}
		for (LeftFlipper leftFlipper: board.getLeftFlippers()){
			leftFlipper.update(timestep);
		}
		for (RightFlipper rightFlipper: board.getRightFlippers()){
			rightFlipper.update(timestep);
		}
	}

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
            
            
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
         /**
                // To avoid modifying the list of balls in the middle of an iteration
                // wait until the iteration is over to add incoming balls and delete outgoing balls
                // if any of those exist
                if (transferball){ board.removeBall(ballToTransfer);}
                if (board.getIncomingBalls().size() != 0) {
                    for (Ball ball : board.getIncomingBalls()){
                        board.addBall(ball);
                    }
                    board.getIncomingBalls().clear();
                }
                out.println(board.toString());
            
            
       

**/














/**
for (Ball ball : board.getBalls()) {
    double timeToClosestWallCollision = Double.POSITIVE_INFINITY;
    OuterWall wallToCollide = null;
    double timeToBallCollide = Double.POSITIVE_INFINITY;
    Ball ballToCollide = null;
    if(ball.ballOutOfBounds(0.05)){
        
        
        for(OuterWall wall: board.getOuterWalls()){
            double timeUntilWallCollision = wall.timeUntilCollision(ball);
            if(timeUntilWallCollision < timeToClosestWallCollision){
                timeToClosestWallCollision = timeUntilWallCollision;
                wallToCollide = wall;
            } 
        }
    }
    double timeToClosestCollision = Double.POSITIVE_INFINITY;
    Gadget gadgetToReflect = null;
    
    for (Gadget gadget : board.getGadgets()) {
        double timeUntilGadgetCollision = gadget.timeUntilCollision(ball);
        if(timeUntilGadgetCollision < timeToClosestCollision){
            timeToClosestCollision = timeUntilGadgetCollision;
            gadgetToReflect = gadget;
        }
    }
    for (int i = counter; i < board.getBalls().size()-1; i++) {
        Ball that = board.getBalls().get(i);
        double timeToThatCollide = Geometry.timeUntilBallBallCollision(ball.getCircle(), 
                                                                ball.getVelocity(), that.getCircle(), 
                                                                that.getVelocity());
        if(timeToThatCollide < timeToBallCollide){
            timeToBallCollide = timeToThatCollide;
            ballToCollide = that;
        }
    } 
    if(timeToClosestWallCollision <= timeToClosestCollision){
        if(timeToClosestWallCollision <= timeToBallCollide){
            if(wallToCollide != null && timeToClosestWallCollision < 0.11){
                if (wallToCollide.isSolid()){
                wallToCollide.reflectOffGadget(ball);   
                } else { //world not solid if connected to a neighbor
                        world.transferBall(board, ball, wallToCollide);
                        ballToTransfer = ball;
                        transferball = true;
                    }
                }
            }
        } 
    
    else if(timeToClosestCollision <= timeToBallCollide && 
            gadgetToReflect != null && timeToClosestCollision < 0.11){
        gadgetToReflect.reflectOffGadget(ball);
    }
    else{
        if(ballToCollide != null && timeToBallCollide < 0.11){
            Geometry.VectPair ballVelocities = Geometry.reflectBalls(ball.getCircle().getCenter(),
                    1, ball.getVelocity(), ballToCollide.getCircle().getCenter(), 
                    1, ballToCollide.getVelocity());
            ball.setVelocity(ballVelocities.v1);
            ballToCollide.setVelocity(ballVelocities.v2);
        }
    }
    ball.updateBallPosition(0.05);
    ball.updateBallVelocityAfterTimestep(board.getGravity(), board.getMu(), board.getMu2(), 0.05);
    counter++;
}

**/
