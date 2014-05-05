package warmup;

import java.util.ArrayList;
import java.util.List;

public class Board implements BoardInterface {
    
    private final String name;
    private Wall wallLeft;
    private Wall wallRight;
    private Wall wallTop;
    private Wall wallBottom;
    private final double x;
    private final double y;
    private final double gravity;
    private final double mu;
    private final double mu2;
    public List<Ball> balls;
    private String[][] board;
    private double[] previous;

    /**
     * 
     * @param x width of board
     * @param y height of board
     */
    public Board(int x,int y, String name){
        wallLeft = new Wall(0,0,0,y-1,1,true, "left");
        wallRight = new Wall(x-1,0,x-1,y-1,1,true, "right");
        wallTop = new Wall(0,0,x-1,0,1,true, "top");
        wallBottom = new Wall(0,y-1,x-1,y-1,1,true, "bottom");
        this.name = name;
        this.x = x;
        this.y = y;
        this.gravity = 0;
        this.mu = 0;
        this.mu2 = 0;
        this.board = new String[y+1][x+1];
        balls = new ArrayList<Ball>();
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                board[i][j] =  " ";
            }
            board[0][i] = ".";
            board[19][i] = ".";
            board[i][0] = ".";
            board[i][19] = ".";
        }
        board[10][10] = "*";
        previous = new double[2];
        previous[0] = 10;
        previous[1] = 10;
    }
    
    @Override
    public double getBoardGravity() {
        return this.gravity;
    }

    @Override
    public double getMu() {
        return this.mu;
    }

    @Override
    public double getMu2() {
        return this.mu2;
    }

    @Override
    public double[] getBallPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBallPosition(double x, double y) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public double[] getBoardSize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString(){
        board[(int)previous[1]][(int)previous[0]] = " ";
        previous = balls.get(0).getPosition();
        board[(int)previous[1]][(int)previous[0]] = "*";
        String string = "";
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                string += board[j][i];
            }
            string += ".\n";
        }
        return string;
    }
    
    public Wall[] getWalls(){
        Wall[] walls = {wallLeft, wallTop, wallRight, wallBottom};
        return walls;
    }
    
    public void addBall(Ball ball){
        balls.add(ball);
    }
    // TODO get walls method, so ball can iterate and collide

    @Override
    public void setWall(String position, Wall wall) {
        if (position == "Top"){ this.wallTop = wall;}
        else if (position == "Right"){ this.wallRight = wall;}
        else if (position == "Left"){ this.wallLeft = wall;}
        else {this.wallBottom = wall;}
    }

    @Override
    public Wall getWall(String position) {
        if (position == "Top"){ return this.wallTop;}
        else if (position == "Right"){ return this.wallRight;}
        else if (position == "Left"){ return this.wallLeft;}
        else {return this.wallBottom;}
    }
}
