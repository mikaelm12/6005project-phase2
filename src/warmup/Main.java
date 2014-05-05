package warmup;

import physics.Geometry;
import physics.Vect;

/**
 * TODO: put documentation for your class here
 * 
 * 
 * SOOO ITS NOT AS EASY AS WE THOUGHT. I MOVED ALL LOOPS TO THE MAIN METHOD AND THERE THE BALLS POSITION
 * AND VELOCITY IS UPDATED
 * THE BOARD IS NOT PRINTING COMPLETELY AND THE BALL DOESNT MOVE
 * DONT KNOW WHATS WRONG, WILL KEEP LOOKING AT IT ONCE I FINISH MY OTHER PSET
 * EITHER WAY, SEE YOU TOMORROW!
 */
public class Main {
    
    /**
     * TODO: describe your main function's command line arguments here
     */
    public static void main(String[] args) {
        
        /*
         * Create board with size x,y
         * Create one ball and put it in the board with an initial velocity
         * Get initial time
         * for every so many seconds, print a state of the board
         */
        System.out.println("hello world");
        long start = System.currentTimeMillis();
        int x = 20;
        int y = 20;
        int xc = 10;
        int yc = 10;
        Vect vel = new Vect(3.0, 4.0);
        Board board = new Board(x, y, "Warmup");
        Ball ball = new Ball(xc, yc, .25, vel);
        board.addBall(ball);
        System.out.println("hello");
        System.out.println(board.toString());
        long previous = start;
        while (true){
            long current = System.currentTimeMillis();
           
            previous = current;
            Wall wallToCollide = null;

            if ((current-start) % 50 == 0){
                double timeToClosestCollision = 1000;
                Wall[] walls = board.getWalls();                
                for (Wall wall: walls){
                    double timeToWall = Geometry.timeUntilWallCollision(wall.getLine(), ball.getCircle(), ball.getVelocity());
                    if (timeToClosestCollision > timeToWall){
                        timeToClosestCollision = timeToWall;
                        wallToCollide = wall;
                    }
                }
                if ((ball.getPosition()[0] + ball.getVelocity().x()*.50) >= 19 || (ball.getPosition()[1] + ball.getVelocity().y()*.50) >= 19||
                        (ball.getPosition()[0] + ball.getVelocity().x()*.50) <= 0 || (ball.getPosition()[1] + ball.getVelocity().y()*.50) <= 0){
                    //System.out.println("bouncing against " + wallToCollide.name);
                    Vect newvel = ball.update(wallToCollide, ball.getVelocity());
                    ball.setVelocity(newvel);
                    System.out.println(ball.getVelocity());
                }
                double newx = ball.getPosition()[0] + ball.getVelocity().x()*.050;
                double newy = ball.getPosition()[1] + ball.getVelocity().y()*.050;
                System.out.println("old position: ("+ball.getPosition()[0]+","+ball.getPosition()[1]+")");
                System.out.println("new position: ("+newx+","+newy+")");
                ball.setPosition(newx, newy);
                System.out.println("updated position: ("+ball.getCircle().getCenter().x()+","+ball.getPosition()[1]+")");
                System.out.println(board.toString());
            }
            
        }
    }
}