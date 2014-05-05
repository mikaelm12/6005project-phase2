package pingball.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pingball.datatypes.Ball;
import pingball.datatypes.Board;
import pingball.datatypes.OuterWall;


/**
 * Represents a world of boards connected through a network
 * 
 * @author AlexR
 *
 */
public class World implements WorldInterface {
    /*
     * Rep Invariants
     *   boards != null
     * Abstraction Function
     *    Represents an abstract world of Pingball boards
     *    that may or may not be connected to each other
     * Thread Safety Argument
     *   boards is a private and final variable
     *   All accesses to boards happen within World methods,
     *   which are all synchronized and guarded by this World's lock
     *   allowing only one joining of boards to happen at the same time
     */
    
    private final Map<String, Board> boards;
    
    public World(){
        boards = new HashMap<String, Board>();
    }

    @Override
    public synchronized void addBoard(Board board) {
        boards.put(board.getName(), board);
    }

    @Override
    public synchronized void removeBoard(Board board) {
        String name = board.getName();
        //if this board was someone's neighbor, remove that connection
        if (boards.get(name).getNeighborLeft() != null){
            boards.get(name).getNeighborLeft().unNeighbor(board);
        }
        if (boards.get(name).getNeighborRight() != null){
            boards.get(name).getNeighborRight().unNeighbor(board);
        }
        if (boards.get(name).getNeighborTop() != null){
            boards.get(name).getNeighborTop().unNeighbor(board);
        }
        if (boards.get(name).getNeighborBottom() != null){
            boards.get(name).getNeighborBottom().unNeighbor(board);
        }
        boards.remove(boards.get(name));
        
    }

    @Override
    public synchronized void joinVertical(String boardTop, String boardBottom) {
        boards.get(boardTop).setNeighborBottom(boards.get(boardBottom));
        boards.get(boardBottom).setNeighborTop(boards.get(boardTop));

    }

    @Override
    public synchronized void joinHorizontal(String boardLeft, String boardRight) {
        boards.get(boardLeft).setNeighborRight(boards.get(boardRight));
        boards.get(boardRight).setNeighborLeft(boards.get(boardLeft));

    }

    
    @Override
    public synchronized void transferBall(Board from, Ball ball, OuterWall wall){
        if (wall.getName().equals("left")){
            Board neighbor = from.getNeighborLeft();
            Ball newBall = new Ball(ball.getName(),19.8, ball.getPosition()[1], ball.getVelocity().x(),ball.getVelocity().y());
            neighbor.addIncomingBall(newBall);
        } else if (wall.getName().equals("top")){
            Board neighbor = from.getNeighborTop();
            Ball newBall = new Ball(ball.getName(), ball.getPosition()[0], 19.8, ball.getVelocity().x(),ball.getVelocity().y());
            neighbor.addIncomingBall(newBall);
        } else if (wall.getName().equals("right")){
            Board neighbor = from.getNeighborRight();
            Ball newBall = new Ball(ball.getName(), 0, ball.getPosition()[1], ball.getVelocity().x(),ball.getVelocity().y());
            neighbor.addIncomingBall(newBall);
        } else {
            Board neighbor = from.getNeighborBottom();
            Ball newBall = new Ball(ball.getName(), ball.getPosition()[0], 0, ball.getVelocity().x(),ball.getVelocity().y());
            neighbor.addIncomingBall(newBall);
        }
    }
    
    @Override
    public String toString(){
        return boards.entrySet().toString();
    }

    @Override
    public boolean containsBoard(String board) {
        return boards.containsKey(board);
    }

}
