package pingball.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
 * Client of a Pingball Game
 * May connect to PingballServer or play in single mode
 * @author AlexR
 *
 */
public class PingballClient {

    /**
     * Start a PingballClient using the given arguments.
     * 
     * Usage: PingballClient [--host HOST] [--port PORT] FILE
     * 
     * HOST is an optional hostname or IP address of the server to connect to. 
     * If no HOST is provided, then the client starts in single-machine play mode, as described above.
     * 
     * PORT is an optional integer in the range 0 to 65535 inclusive, specifying the port the
     * server should be listening on for incoming connections. E.g. "MinesweeperServer --port 1234"
     * starts the server listening on port 1234.
     * 
     * FILE is an argument specifying a file pathname where a board has been stored. The stored bored
     * is initialized as this client's board. If connected to a server, file will be sent to the server to create
     * the board and add it to the world. Otherwise, board created and run as single player mode.
     * @throws Exception 
     * 
     */
    public static void main(String[] args) throws Exception{
        int port = 10987; //default port
        String hostName = null;
//        File file = null;
//        File file = new File ("/Users/PeterGithaiga/Documents/6.005/projectPhase1/pingball-phase1/sampleBoard1"); 
   //     File file = new File ("/Users/AlexR/Desktop/6.005/pingball-phase1/alex-peter-mikael-testBoard3");
//        File file = new File ("/Users/AlexR/Desktop/6.005/pingball-phase1/alex-peter-mikael-testBoard2");
       // File file = new File ("/Users/AlexR/Desktop/6.005/pingball-phase1/sampleBoard1");

       File file = new File("src/../boards/boardG.txt");

        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while ( ! arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > 65535) {
                            throw new IllegalArgumentException("port " + port + " out of range");
                        }
                    } else if (flag.equals("--host")) {
                       hostName = arguments.remove();
                    } else if (flag.equals("--file")) {  //File is not an argument but a must
                        file = new File(arguments.remove());
                        if ( ! file.isFile()) {
                            throw new IllegalArgumentException("file not found: \"" + file + "\"");
                        }
                    } else {
                        throw new IllegalArgumentException("unknown option: \"" + flag + "\"");
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + flag);
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballClient [--host HOST] [--port PORT]  FILE");
            return;
        }
        // if HOST was provided then run the server and connect
        if (hostName != null){
        try {
            runPingBallServerClient(hostName, port, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        } else { //Play single machine mode
           runSingleMachine(file);
        }
    }
    

    /**
     * Runs a multiplayer pingball... creates board and connects to server
     * @param host where the game is being held
     * @param port to establish communication to the server
     * @param file containing board of this client
     * @throws IOException
     */
    public static void runPingBallServerClient(String host, int port, File file) throws IOException{
        String kill = "END OF FILE!!";
        String hostName = host;
        int portNumber = port;
        //Establish communication with the server
        Socket toServerSocket = new Socket(hostName, portNumber);
        PrintWriter toServe = new PrintWriter(toServerSocket.getOutputStream(), true);
        
        //Send file to the server
        BufferedReader inputFileStream = null;
        try {
           inputFileStream = new BufferedReader(new FileReader(file));
           String l;
           while ((l = inputFileStream.readLine()) != null) {
               toServe.println(l);
           }
        } finally {
            toServe.println(kill);
            if (inputFileStream != null) {
                inputFileStream.close();
            }
        }
       
        BufferedReader fromServe = new BufferedReader(new InputStreamReader(toServerSocket.getInputStream()));
        String fromServer;
        while ((fromServer = fromServe.readLine()) != null) {
            System.out.println(fromServer);
        }
       
        
    }
    
    /**
     * Runs the Pingball Client in single machine mode
     * @param file of the board to be created and run
     * @throws Exception 
     */
    public static void runSingleMachine (File file) throws Exception{
        //Turn file into a string
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
                SwingTimer gui = new SwingTimer(board);
                gui.setMinimumSize(new Dimension(410, 410));
                gui.setVisible(true);   
               
    
            }
        });
        System.out.println(board.toString());
        
        
        //PLAY!
        
        System.out.println("hello world");
        
        long start = System.currentTimeMillis();
        while(true){
            long current = System.currentTimeMillis();
//<<<<<<< HEAD
//            
//            if ((current-start) % 100 == 0){
//                int counter = 1;
//                
//                for (Ball ball : board.getBalls()) {
//                    double timeToClosestWallCollision = Double.POSITIVE_INFINITY;
//                    OuterWall wallToCollide = null;
//                    double timeToBallCollide = Double.POSITIVE_INFINITY;
//                    Ball ballToCollide = null;
//                    
//                    double timeToClosestCollision = Double.POSITIVE_INFINITY;
//                    Gadget gadgetToReflect = null;
//                    
//                    for (Gadget gadget : board.getGadgets()) {//find the time until the closest gadget collision--and the gadget
//                        double timeUntilGadgetCollision = gadget.timeUntilCollision(ball);
//                        if(timeUntilGadgetCollision < timeToClosestCollision){
//                            timeToClosestCollision = timeUntilGadgetCollision;
//                            gadgetToReflect = gadget;
//                        }
//                    }
//                    
//                    for (int i = counter; i < board.getBalls().size(); i++) {//find the time until the closest ball collision--and the corresponding ball
//                        if (!(board.getBalls().get(i).getName().equals(ball.getName()))){ //make sure that the ball we are looking at in this loop is not the same ball as the outer loop
//                            Ball that = board.getBalls().get(i);
//                            double timeToThatCollide = Geometry.timeUntilBallBallCollision(ball.getCircle(), 
//                                                                                    ball.getVelocity(), that.getCircle(), 
//                                                                                    that.getVelocity());
//                            if(timeToThatCollide < timeToBallCollide){
//                                timeToBallCollide = timeToThatCollide;
//                                ballToCollide = that;
//                            }
//                        }
//                    }
//                    
//                    
//                    if(ball.ballOutOfBounds(0.08)){
//                        
//                        
//                        for(OuterWall wall: board.getOuterWalls()){//if the ball hits an outer wall, find which wall and the time until that collision
//                            double timeUntilWallCollision = wall.timeUntilCollision(ball);
//                            if(timeUntilWallCollision < timeToClosestWallCollision){
//                                timeToClosestWallCollision = timeUntilWallCollision;
//                                wallToCollide = wall;
//                            } 
//                        }                        
//=======
//>>>>>>> b996b95b9bd1d19656ccb4d977ea9332daa36d6c

            if ((current-start) % 30 == 0 && !board.isPaused()){
                int counter = 1;
                double timestep = 0.05;
                update(board, timestep);
                counter++;
                System.out.println(board.toString());
            }
            
        }
        
        
        
    }

	private static void update(Board board, double timestep) {
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
				updateWithCollision(board, timeUntilFirstCollision);
				timestepLeft -= timeUntilFirstCollision;
			} else { //we have no collision in the timestep
				updateWithoutCollision(board, timestep);
				timestepLeft = 0;
			}
		}		
	}

	private static void updateWithCollision(Board board, double timeUntilFirstCollision) { //will not work correctly if a ball collides with two things at the EXACT same time--which is extremely improbable
		//updateWithoutCollision(board, timeUntilFirstCollision); //we will update flippers and balls as usual, and then collide the balls
		
		List<String> namesOfBallsCollided = new ArrayList<String>();
		
		for (Ball ball: board.getBalls()){
			for (Ball ball2: board.getBalls()){
				if (!namesOfBallsCollided.contains(ball.getName()) && !namesOfBallsCollided.contains(ball2.getName()) && !ball.getName().equals(ball2.getName())){ //make sure to only collide balls that have not been collided yet
					if (ball.timeUntilPhysicsCollision(ball2)<=timeUntilFirstCollision){
//						System.out.println("Two Balls are colliding");
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
//						System.out.println("we are colliding with the wall");
						Vect oldV = ball.getPhysicsVelocity();
						wall.reflectOff(ball);
						ball.updateBallPositionUsingOldPhysicsVelocity(timeUntilFirstCollision, oldV);
						namesOfBallsCollided.add(ball.getName());
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

