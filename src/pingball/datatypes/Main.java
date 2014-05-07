package pingball.datatypes;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;

import BoardExpr.BoardFactory;
import Graphics.SwingTimerExample;
import physics.Geometry;
import pingball.datatypes.Ball;
import pingball.datatypes.Gadget;
import pingball.datatypes.OuterWall;

public class Main {
    
    /**
     * TODO: describe your main function's command line arguments here
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException{
        
        
        File file = new File("/Users/mikemikael3/Dropbox/School/Semester 4/6.005/pingball-phase2/boards/board5.txt");
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
        LeftFlipper flipperL = new LeftFlipper("flipperL",6,7,0);
        RightFlipper flipperR = new RightFlipper("flipperR",10,7,0);
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
        flipperL.addGadgetToFire(flipperL);
        flipperR.addGadgetToFire(flipperR);
        absorber.addGadgetToFire(absorber);
        square2.addGadgetToFire(flipperL);
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
                JFrame ex = new SwingTimerExample(board);
                ex.setMinimumSize(new Dimension(425, 425));
                ex.setVisible(true);                
            }
        });
        
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
                    if(ball.ballOutOfBounds(0.05)){ //if the ball hits an outer wall, find which wall and the time until that collision
                        
                        
                        for(OuterWall wall: board.getOuterWalls()){
                            double timeUntilWallCollision = wall.timeUntilCollision(ball);
                            if(timeUntilWallCollision < timeToClosestWallCollision){
                                timeToClosestWallCollision = timeUntilWallCollision;
                                wallToCollide = wall;
                            } 
                        }
                      
                    }
                    double timeToClosestGadgetCollision = Double.POSITIVE_INFINITY;
                    Gadget gadgetToReflect = null;
                    
                    for (Gadget gadget : board.getGadgets()) { //find the time until the closest gadget collision--and the gadget
                        double timeUntilGadgetCollision = gadget.timeUntilCollision(ball);
                        if(timeUntilGadgetCollision < timeToClosestGadgetCollision){
                            timeToClosestGadgetCollision = timeUntilGadgetCollision;
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
                    
                    if(timeToClosestWallCollision < timeToClosestGadgetCollision){ //we are not colliding with a gadget
                        if(timeToClosestWallCollision < timeToBallCollide){//we are colliding with a wall
                            if(wallToCollide != null && timeToClosestWallCollision < 0.08){
                                System.out.println("reflecting off wall");
                                wallToCollide.reflectOffGadget(ball);   
                            }
                        } 
                    }
                    
                    else if(timeToClosestGadgetCollision < timeToBallCollide && 
                            gadgetToReflect != null && timeToClosestGadgetCollision < 0.08){ //we are colliding with a gadget
                        gadgetToReflect.reflectOffGadget(ball);
                    }
                    else{
                        
                        if(ballToCollide != null && timeToBallCollide < 0.11){// 2 timesteps ahead
                            Geometry.VectPair ballVelocities = Geometry.reflectBalls(ball.getCircle().getCenter(),
                                    1, ball.getVelocity(), ballToCollide.getCircle().getCenter(), 
                                    1, ballToCollide.getVelocity());
                            ball.setVelocity(ballVelocities.v1);
                            ballToCollide.setVelocity(ballVelocities.v2);
                        }
                    }
                    ball.updateBallPosition(0.05);
                    ball.updateBallVelocityAfterTimestep(board.getGravity(), board.getMu(), board.getMu2(), 0.08);
                    counter++;
                    
                }
                
                System.out.println(board.toString());
            }
            
        }
        
        
        
    }

}
