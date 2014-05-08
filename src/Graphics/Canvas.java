package Graphics;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import pingball.datatypes.Ball;
import pingball.datatypes.Board;
import pingball.datatypes.CircularBumper;
import pingball.datatypes.Gadget;
import pingball.datatypes.SquareBumper;

public class Canvas extends JPanel 
    implements ActionListener {

    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 500;
    private final int INITIAL_X = 0;
    private final int INITIAL_Y = 0;
    private final int DELAY = 5;  //Miliseconds to repaint
    Color backgroundColor = Color.white;
    List<Shape> shapes = new ArrayList<Shape>();
    List<Ball> balls = new ArrayList<Ball>();
    List<Gadget> gadgets = new ArrayList<Gadget>();
    private Board board; 
    private Image star;
    private Timer timer;
    private int x, y;
    public boolean forward = true;

    public Canvas(Board board) {
                
     
        initCanvas(); 
        this.board = board;
    }
    

    
    private void initCanvas() {
        
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        setDoubleBuffered(true);

       
        
        timer = new Timer(DELAY, this);
        timer.start();
        

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBoard(g);
    }
    
    private void drawBoard(Graphics g) {
        
        Graphics2D graph2 = (Graphics2D) g;
        
        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        Shape drawRect = new Rectangle2D.Float(300,300, 150,100);
        
        
        List<Shape> shapes = new ArrayList<Shape>();
        
       balls = this.board.getBalls();
       gadgets = this.board.getGadgets();
       for (Ball ball: balls ){

               Shape temp = makeBall(ball);
             
               graph2.setColor(Color.GREEN);
               
               graph2.fill(temp);
  
       }
       for(Gadget gadget: gadgets){
           
           if(gadget.getGadgetType().equals("Circular Bumper")||gadget.getGadgetType().equals("Square Bumper")){
               
               makeGadget(gadget, graph2);
               
               
           }
           
           
       }
       
       
       
    
      
      
     
        
        Graphics2D g2d = (Graphics2D) g;
       
        Toolkit.getDefaultToolkit().sync();
        g.dispose();        
    }
    private void changeColor(final Color color) {
        backgroundColor = color;
        this.repaint(); // IMPORTANT!  this is what triggers Swing to call paintComponent() below
    }
public Shape makeBall(Ball ball){
    

    Shape newCirc = new Ellipse2D.Float((float)ball.getPosition()[0]*20 , (float) ball.getPosition()[1]*20, 10, 10);
    return newCirc;
}

public void makeGadget(Gadget gadget, Graphics2D graph2){
    if(gadget.getGadgetType().equals("Circular Bumper")){
        
        CircularBumper cb = (CircularBumper)gadget;
        Shape circleBumper = new Ellipse2D.Float((float)cb.getCircle().getCenter().x()*20 ,(float)cb.getCircle().getCenter().y()*20, 20,20);

        graph2.setColor(Color.ORANGE);
        
        graph2.fill(circleBumper);
    }
    else if(gadget.getGadgetType().equals("Square Bumper")){
        SquareBumper sb = (SquareBumper)gadget;
        Shape squareBumper = new Rectangle2D.Float((float)gadget.getPosition().x()*20, (float)gadget.getPosition().y()*20, 15, 10);

        graph2.setColor(Color.BLUE);
        
        graph2.fill(squareBumper);        
    }
    
    
}

//TODO Implement these methods

//public Shape makeFlipper(){
//    
//}

// public Shape make

    @Override
    public void actionPerformed(ActionEvent e) {
        
       
        
        
        repaint();  
    }
}