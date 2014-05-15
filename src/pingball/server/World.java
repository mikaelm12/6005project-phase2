package pingball.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pingball.datatypes.Ball;
import pingball.datatypes.Board;
import pingball.datatypes.OuterWall;
import pingball.datatypes.Portal;


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
        reevaluatePortalValidity();
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
        reevaluatePortalValidity();
        
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
    public synchronized  void transferBall(Board from, Ball ball, OuterWall wall){
        System.out.println("Moving Boards");
        if (wall.getName().equals("left")){
            Board neighbor = from.getNeighborLeft();
            if(!neighbor.isPaused()){
                Ball newBall = new Ball(ball.getName(),19.0, ball.getNormalPosition()[1], ball.getNormalVelocity().x(),ball.getNormalVelocity().y());
                neighbor.addIncomingBall(newBall);
            }
        } else if (wall.getName().equals("top")){
            Board neighbor = from.getNeighborTop();
            if(!neighbor.isPaused()){
                Ball newBall = new Ball(ball.getName(), ball.getNormalPosition()[0], 19.0, ball.getNormalVelocity().x(),ball.getNormalVelocity().y());
                neighbor.addIncomingBall(newBall);
            }
        } else if (wall.getName().equals("right")){
            Board neighbor = from.getNeighborRight();
           if(!neighbor.isPaused()){
                Ball newBall = new Ball(ball.getName(), 1, ball.getNormalPosition()[1], ball.getNormalVelocity().x(),ball.getNormalVelocity().y());
                neighbor.addIncomingBall(newBall);
            }
        } else {
            Board neighbor = from.getNeighborBottom();
            if(!neighbor.isPaused()){
                Ball newBall = new Ball(ball.getName(), ball.getNormalPosition()[0], 1, ball.getNormalVelocity().x(),ball.getNormalVelocity().y());
                neighbor.addIncomingBall(newBall);
            }
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
    
    private synchronized Portal getTargetPortal(Board targetBoard, Portal sourcePortal){
		List<Portal> portalList = targetBoard.getPortals();
		for (Portal portal: portalList){
			System.out.println("sourcePortal.getTargetPortalBoardName() = "+sourcePortal.getTargetPortalBoardName());
			System.out.println("targetBoard.getName() = "+targetBoard.getName());
			
			if (sourcePortal.getTargetPortalBoardName().equals(targetBoard.getName())){//makes sure that the target board is correct. Since this is in main, this should be true unless the sourcePortal points to a different board.
				System.out.println("sourcePortal.getTargetPortalBoardName().equals(targetBoard.getName())");
				if (portal.getName().equals(sourcePortal.getTargetPortalName())){
					System.out.println("we should return a portal");
					return portal;
				}
			}
		}
		return null;
	}
    private synchronized void reevaluatePortalValidity(){  
    	System.out.println("in reevaluatePortalValidity()");
    	for (Board board: boards.values()){
			for (Portal portal: board.getPortals()){
				if (boards.containsKey(portal.getTargetPortalBoardName())){ //we found the targetBoard
					Board otherBoard = boards.get(portal.getTargetPortalBoardName());
					if(getTargetPortal(otherBoard, portal)==null){//there is no valid destination portal
						System.out.println("getTargetPortal(board, portal)==null");
						portal.setHasDestinationPortal(false);
					} else {//there is a valid destination portal
						System.out.println("We've got a portal!");
						portal.setHasDestinationPortal(true);
					}
				} else {
					portal.setHasDestinationPortal(false);
				}
			}
    	}
    }

	public synchronized void sendBall(Ball sentBall, Portal sourcePortal, Board sourceBoard) {
		System.out.println("in World.sendBall();");
		sourceBoard.removeBall(sentBall);
		synchronized(boards){
			if(boards.containsKey(sourcePortal.getTargetPortalBoardName())){//we found the targetBoard
				Board targetBoard = boards.get(sourcePortal.getTargetPortalBoardName());
				if (getTargetPortal(targetBoard, sourcePortal)!=null){//we found the targetPortal
					Portal targetPortal = getTargetPortal(targetBoard, sourcePortal);
					synchronized(targetPortal){
						targetPortal.receiveBall(sentBall);
					}
					synchronized(targetBoard){
						targetBoard.addBall(sentBall);
					}
				}
			}
		}
		
	}

}
