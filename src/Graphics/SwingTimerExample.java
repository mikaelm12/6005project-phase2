package Graphics;



import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import pingball.datatypes.Board;



public class SwingTimerExample extends JFrame {

    public SwingTimerExample(Board board) {

        add(new Canvas(board));

        setTitle("Test");
        pack();
        setResizable(true);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }


}