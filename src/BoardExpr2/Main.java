package BoardExpr2;

import java.io.File;

import pingball.datatypes.Board;



public class Main {
    
    public static void main(String[] args) throws Exception {
        Board board1 = GrammarFactory.parse(new File("src/../boards/board1.txt"));
        System.out.println(board1);
    }

}
