package Graphics;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;





import pingball.client.PingballClient;


import BoardExpr.GrammarFactory;

import pingball.datatypes.Board;




public class SwingTimer extends JFrame {
    private Board newBoard;
    public File boardFile;


    public SwingTimer(final Board board) {

        setCanvas(board);

        setTitle(board.getName());
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
        
        JMenuItem pause = new JMenuItem("Pause/Unpause");
        options.add(pause);
        
        
        pause.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                board.pauseUnpauseGame();
                
            }
        });
        
        JMenuItem connect = new JMenuItem("Connect to");
        options.add(connect);
        
        connect.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String hostValue = JOptionPane.showInputDialog("Please specify a host");
                String portValue = JOptionPane.showInputDialog("Please specify a port");
            }
        });
        
        
        JMenuItem restart = new JMenuItem("Restart");
        options.add(restart);
        
        restart.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                board.setRestart(true);
                
            }
        });
        
        JMenuItem quit = new JMenuItem("Quit");
        options.add(quit);
       quit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dispose();
                
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
                        //PingballClient.runSingleMachine(file);
                        //setVisible(false);
                        System.out.println(testing);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                
            }
        });
        
        KeyListener listener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                    board.checkKeyListener(e, true);
                }

            public void keyReleased(KeyEvent e) {
                    board.checkKeyListener(e, false);
                }
        };

        KeyListener magical = new MagicKeyListener(listener);

        addKeyListener(magical);
        
    }
    public Board getNewBoard() {
        return newBoard;
    }

    public void setNewBoard(Board newBoard) {
        this.newBoard = newBoard;
    }
    
    
    /**
     * This no board constructor is for starting a pingball game with out a file.
     * A file is chosen and a new gui is created.
     */
    public SwingTimer() {

        
        setTitle("Pingball!");
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
        
        JMenuItem pause = new JMenuItem("Pause/Unpause");
        options.add(pause);
        
        JMenuItem connect = new JMenuItem("Connect to");
        options.add(connect);
        
        connect.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int port = 0;
                String host = JOptionPane.showInputDialog("Please specify a host");
                
                String portValue = JOptionPane.showInputDialog("Please specify a port");
                
                try{
                 port = Integer.parseInt(portValue);
                }
                catch(NumberFormatException e2){
                    return;
                }
                
                final JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(null);
                int returnVal = fc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    final File testFile = fc.getSelectedFile();
                    
                try {
                    
                    PingballClient.runPingBallServerClient(host, port, testFile );
                    dispose();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                }
            }
        });
        
        JMenuItem disconnect = new JMenuItem("Disconnect");
        options.add(disconnect);
        
        JMenuItem restart = new JMenuItem("Restart");
        options.add(restart);
        
        JMenuItem quit = new JMenuItem("Quit");
        options.add(quit);
        
        quit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                
            }
        });
        

        
        file.addActionListener(new ActionListener() {
            
           
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(null);
                int returnVal = fc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    final File file = fc.getSelectedFile();
                    try {
                       //final Board board =  GrammarFactory.parse(file);
                        //setCanvas(board);
                        
                        System.out.println("PingBall Client");
                        
                        Thread one = new Thread() {
                            public void run() {
                                
                                try {
                                    PingballClient.runSingleMachine(file);
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                               
                            }  
                        };

                        one.start();
                         
                      
                        dispose();
                       
                        System.out.println("HERE NOW");
                    } catch (Exception e2) {
                        System.err.println(e2);
                    }
                  
                }
                
            }
        });
    
        //PingballClient.simulateGame(board);
    //this.pack();
    }
    
    public void setCanvas(Board board){
        this.add(new Canvas(board));
    }



}