package pingball.server;

import pingball.datatypes.Ball;
import pingball.datatypes.Board;
import pingball.datatypes.OuterWall;


/**
 * World that represents a set of boards connected through the network
 * @author AlexR
 */
public interface WorldInterface {

    /**
     * Adds a client's board to the world
     * @param board to be added
     */
    public void addBoard(Board board);
    
    /**
     * Removes a client's board from the world
     * @param board to be removed
     */
    public void removeBoard(Board board);
    
    /**
     * Joins two boards in the world vertically
     * @param boardTop Board to be on top
     * @param boardBottom  Board to be on bottom
     */
    public void joinVertical(String boardTop, String boardBottom);
    
    /**
     * Joins two boards in the world horizontally
     * @param boardLeft  Board to be on the left
     * @param boardRight   Board to be on the right
     */
    public void joinHorizontal(String boardLeft, String boardRight);


    /**
     * Transfers a ball from one board to its neighboring board
     * Ball keeps original velocity vector
     * @param from board where the ball came from
     * @param ball to be transfered from board to board
     * @param wall through which the ball is exiting
     */
    public void transferBall(Board from, Ball ball, OuterWall wall);
    
    /**
     * Checks if the world contains a given board
     * @param board to be checked
     * @return boolean indicating the existence of the board
     */
    public boolean containsBoard(String board);
}