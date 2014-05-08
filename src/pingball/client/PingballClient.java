package pingball.client;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import javax.swing.JFrame;

import BoardExpr.BoardFactory;
import BoardExpr2.GrammarFactory;
import Graphics.SwingTimerExample;
import physics.Geometry;
import pingball.datatypes.Ball;
import pingball.datatypes.Board;
import pingball.datatypes.Gadget;
import pingball.datatypes.OuterWall;


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

       File file = new File("/Users/mikemikael3/Dropbox/School/Semester 4/6.005/pingball-phase2/boards/board1.txt");

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
                JFrame ex = new SwingTimerExample(board);
                ex.setMinimumSize(new Dimension(425, 425));
                ex.setVisible(true);                
            }
        });
        System.out.println(board.toString());
        
        
        //PLAY!
        
        System.out.println("hello world");
        
        long start = System.currentTimeMillis();
        while(true){
            long current = System.currentTimeMillis();
            
            if ((current-start) % 150 == 0){
                int counter = 1;
                
                for (Ball ball : board.getBalls()) {
                    double timeToClosestWallCollision = Double.POSITIVE_INFINITY;
                    OuterWall wallToCollide = null;
                    double timeToBallCollide = Double.POSITIVE_INFINITY;
                    Ball ballToCollide = null;
                    
                    double timeToClosestCollision = Double.POSITIVE_INFINITY;
                    Gadget gadgetToReflect = null;
                    
                    for (Gadget gadget : board.getGadgets()) {//find the time until the closest gadget collision--and the gadget
                        double timeUntilGadgetCollision = gadget.timeUntilCollision(ball);
                        if(timeUntilGadgetCollision < timeToClosestCollision){
                            timeToClosestCollision = timeUntilGadgetCollision;
                            gadgetToReflect = gadget;
                        }
                    }
                    
                    for (int i = counter; i < board.getBalls().size(); i++) {//find the time until the closest ball collision--and the corresponding ball
                        if (!(board.getBalls().get(i).getName().equals(ball.getName()))){ //make sure that the ball we are looking at in this loop is not the same ball as the outer loop
                            Ball that = board.getBalls().get(i);
                            double timeToThatCollide = Geometry.timeUntilBallBallCollision(ball.getCircle(), 
                                                                                    ball.getVelocity(), that.getCircle(), 
                                                                                    that.getVelocity());
                            if(timeToThatCollide < timeToBallCollide){
                                timeToBallCollide = timeToThatCollide;
                                ballToCollide = that;
                            }
                        }
                    }
                    
                    
                    if(ball.ballOutOfBounds(0.08)){
                        
                        
                        for(OuterWall wall: board.getOuterWalls()){//if the ball hits an outer wall, find which wall and the time until that collision
                            double timeUntilWallCollision = wall.timeUntilCollision(ball);
                            if(timeUntilWallCollision < timeToClosestWallCollision){
                                timeToClosestWallCollision = timeUntilWallCollision;
                                wallToCollide = wall;
                            } 
                        }                        

                    }
                  
                    
                   
                    
                     if(timeToClosestCollision <= timeToBallCollide && 
                            gadgetToReflect != null && timeToClosestCollision < 0.10){//if we are colliding with a gadget first
                        gadgetToReflect.reflectOffGadget(ball);
                    }
                     
                     else if(timeToClosestWallCollision <= timeToClosestCollision){ //if we are colliding with an outer wall first
                         if(timeToClosestWallCollision <= timeToBallCollide){
                             if(wallToCollide != null && timeToClosestWallCollision < 0.10){
                                 wallToCollide.reflectOffGadget(ball);   
                             }
                         } 
                     }
                    else{//two balls are colliding
                        if(ballToCollide != null && timeToBallCollide < 0.10){
                            Geometry.VectPair ballVelocities = Geometry.reflectBalls(ball.getCircle().getCenter(),
                                    1, ball.getVelocity(), ballToCollide.getCircle().getCenter(), 
                                    1, ballToCollide.getVelocity());
                            ball.setVelocity(ballVelocities.v1);
                            ballToCollide.setVelocity(ballVelocities.v2);
                        }
                    }
                    ball.updateBallPosition(0.10);
                    ball.updateBallVelocityAfterTimestep(board.getGravity(), board.getMu(), board.getMu2(), 0.05);
                    counter++;
                    
                }
                System.out.println(board.toString());
            }
            
        }
    }
}
