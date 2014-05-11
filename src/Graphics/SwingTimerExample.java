package Graphics;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import pingball.datatypes.Board;



public class SwingTimerExample extends JFrame {

    public SwingTimerExample(final Board board) {

        add(new Canvas(board));

        setTitle("Test");
        pack();
        setResizable(true);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        requestFocusInWindow();
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                board.checkKeyListener(e, true);
            }

            public void keyReleased(KeyEvent e) {
                board.checkKeyListener(e, false);
            }
        });
        
    }


}