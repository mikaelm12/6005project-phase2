package Graphics;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import BoardExpr2.BoardCreatorListener;
import BoardExpr2.GrammarFactory;
import pingball.datatypes.Board;



public class SwingTimer extends JFrame {
    private Board newBoard;



    public SwingTimer(final Board board) {

        add(new Canvas(board));

        setTitle("Test");
        pack();
        setResizable(true);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menu = new JMenuBar();
        
        menu.setBackground(Color.DARK_GRAY);
        this.setJMenuBar(menu);
        
        JMenu options = new JMenu("Options");
        options.setBackground(Color.DARK_GRAY);
        menu.add(options);
        
        JMenuItem file = new JMenuItem("File");
        options.add(file);
        
        JMenuItem pause = new JMenuItem("Pause");
        options.add(pause);
        
        JMenuItem quit = new JMenuItem("Quit");
        options.add(quit);
        
        pause.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                board.pauseUnpauseGame();
                
            }
        });
        
        file.addActionListener(new ActionListener() {
            
           
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(null);
                int returnVal = fc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    try {
                        Board testing = GrammarFactory.parse(file);
                        setNewBoard(testing);
                        System.out.println(testing);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                
            }
        });
        
    }
    public Board getNewBoard() {
        return newBoard;
    }

    public void setNewBoard(Board newBoard) {
        this.newBoard = newBoard;
    }


}