package Graphics;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import pingball.datatypes.Absorber;
import pingball.datatypes.Ball;
import pingball.datatypes.Board;
import pingball.datatypes.CircularBumper;
import pingball.datatypes.Gadget;
import pingball.datatypes.LeftFlipper;
import pingball.datatypes.RightFlipper;
import pingball.datatypes.SquareBumper;
import pingball.datatypes.TriangularBumper;
import sun.java2d.loops.DrawPolygons;

public class Canvas extends JPanel 
    implements ActionListener {

    private final int BOARD_WIDTH = 400;
    private final int BOARD_HEIGHT = 400;
    private final int INITIAL_X = 0;
    private final int INITIAL_Y = 0;
    private final int DELAY = 10;  //Miliseconds to repaint
    Color backgroundColor = Color.white;
    List<Shape> shapes = new ArrayList<Shape>();
    List<Ball> balls = new ArrayList<Ball>();
    List<Gadget> gadgets = new ArrayList<Gadget>();
    private Board board; 
    private Image star;
    private Timer timer;
    private int x, y;
    public boolean forward = true;

    /**
     * This method sets the board to be displayed with 
     * A GUI. It also called the method to initialize
     * all the different aspects of the GUI.
     * 
     * @param board -The pingball board to be displayed
     */
    public Canvas(Board board) {
                
     
        initCanvas(); 
        this.board = board;
    }
    

    
    /**
     * This method initializes the starting conditions of our
     * canvas. It also defines the time interval in which the 
     * GUI repaints
     */
    private void initCanvas() {
        
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        setDoubleBuffered(true);

       
        // This determines how often the actionPerformed
        // method is called
        timer = new Timer(DELAY, this);
        timer.start();
        

    }

    /**
     * Calls drawBoard method which paints all the gadgets
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBoard(g);
    }
    
    /**
     * This method takes the board object for the GUI and 
     * draws all the gadgets and ball in their correct places
     * on the board
     * @param g - the main Graphics2D object
     */
    private void drawBoard(Graphics g) {
        
        Graphics2D graph2 = (Graphics2D) g;
        
        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        
        
        
        List<Shape> shapes = new ArrayList<Shape>();
        
       balls = this.board.getBalls();
       gadgets = this.board.getGadgets();
       makeWalls(graph2);
       for (Ball ball: balls ){

               Shape temp = makeBall(ball);
             
               graph2.setColor(Color.GREEN);
               
               graph2.fill(temp);
  
       }
       for(Gadget gadget: gadgets){
           
          
               
               makeGadget(gadget, graph2);

       }
       

        Graphics2D g2d = (Graphics2D) g;
       
        Toolkit.getDefaultToolkit().sync();
        g.dispose();        
    }

/**
 * This method takes in a ball object and creates its corresponding Graphics 2D object    
 * @param ball
 * @return
 */
public Shape makeBall(Ball ball){
    

//<<<<<<< HEAD
//    Shape newCirc = new Ellipse2D.Float((float)ball.getPosition()[0]*20 + 50 , (float) ball.getPosition()[1]*20, 10, 10);
//=======
    Shape newCirc = new Ellipse2D.Float((float)ball.getNormalPosition()[0]*20 , (float) ball.getNormalPosition()[1]*20 , 5, 5);
//>>>>>>> b996b95b9bd1d19656ccb4d977ea9332daa36d6c
    return newCirc;
}

public void makeWalls(Graphics2D graph2){
    Shape vertWall1 = new Rectangle2D.Float(8,0, 5, 400);
    Shape vertWall2 = new Rectangle2D.Float(400,0, 5, 400);
    Shape horWall1 = new Rectangle2D.Float(8, 0, 400, 5);
    Shape horWall2 = new Rectangle2D.Float(8, 400, 400, 5);
    
    
    graph2.setColor(Color.BLACK);
    
    graph2.fill(vertWall1);
    graph2.fill(vertWall2);
    graph2.fill(horWall1);
    graph2.fill(horWall2);
    
}


/**
 * This method takes a gadget and returns its
 * corresponding Graphics2D object
 * @param gadget
 * @param graph2
 */
public void makeGadget(Gadget gadget, Graphics2D graph2){
    if(gadget.getGadgetType().equals("Circular Bumper")){
        
        CircularBumper cb = (CircularBumper)gadget;
        Shape circleBumper = new Ellipse2D.Float((float)cb.getNormalCircle().getCenter().x()*20 + 8 ,(float)cb.getNormalCircle().getCenter().y()*20, 20,20);

        graph2.setColor(Color.ORANGE);
        
        graph2.fill(circleBumper);
    }
    else if(gadget.getGadgetType().equals("Square Bumper")){
        SquareBumper sb = (SquareBumper)gadget;
        Shape squareBumper = new Rectangle2D.Float((float)gadget.getPosition().x()*20 + 8 , (float)gadget.getPosition().y()*20, 12, 5);

        graph2.setColor(Color.BLUE);
        
        graph2.fill(squareBumper);  
        
    }
    else if(gadget.getGadgetType().equals("Absorber")){
        
        Absorber abs = (Absorber)gadget;
        Shape absorber = new Rectangle2D.Float((float)abs.getPosition().x()*20 + 8 , (float)abs.getPosition().y()*20 , abs.getWidth()*20 - 6 , abs.getHeight()*10 + 3);

        graph2.setColor(Color.magenta);
        
        graph2.fill(absorber);  
    }
    else if(gadget.getGadgetType().equals("Triangluar Bumper")){
    	TriangularBumper tb = (TriangularBumper) gadget;
    	GeneralPath tbLineDrawer = new GeneralPath();
    	if (tb.getOrientation()==0){
    		tbLineDrawer.moveTo(20*tb.getPosition().x()+0, 20*tb.getPosition().y()+0);
    		tbLineDrawer.lineTo(20*tb.getPosition().x()+20, 20*tb.getPosition().y()+0);
    		tbLineDrawer.lineTo(20*tb.getPosition().x()+0, 20*tb.getPosition().y()+20);
    	} else if(tb.getOrientation()==90){
    		tbLineDrawer.moveTo(20*tb.getPosition().x()+20, 20*tb.getPosition().y()+0);
    		tbLineDrawer.lineTo(20*tb.getPosition().x()+20, 20*tb.getPosition().y()+20);
    		tbLineDrawer.lineTo(20*tb.getPosition().x()+0, 20*tb.getPosition().y()+0);
    	} else if(tb.getOrientation()==180){
    		tbLineDrawer.moveTo(20*tb.getPosition().x()+20, 20*tb.getPosition().y()+20);
    		tbLineDrawer.lineTo(20*tb.getPosition().x()+0, 20*tb.getPosition().y()+20);
    		tbLineDrawer.lineTo(20*tb.getPosition().x()+20, 20*tb.getPosition().y()+0);
    	} else {
    		tbLineDrawer.moveTo(20*tb.getPosition().x()+0, 20*tb.getPosition().y()+20);
    		tbLineDrawer.lineTo(20*tb.getPosition().x()+0, 20*tb.getPosition().y()+0);
    		tbLineDrawer.lineTo(20*tb.getPosition().x()+20, 20*tb.getPosition().y()+20);
    	}
    	
    	tbLineDrawer.closePath();
    	graph2.setColor(Color.RED);
    	graph2.fill(tbLineDrawer);
    	
        //Shape absorber = new Rectangle2D.Float((float)abs.getPosition().x()*20 + 8 , (float)abs.getPosition().y()*20 , abs.getWidth()*20 - 6 , abs.getHeight()*10 + 3);

    }
    
    
}

//TODO Implement these methods

//public Shape makeFlipper(){
//    
//}

// public Shape make

   /**
    * This method is called every time the timer is set off and 
    * it repaints the GUI
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();  
    }
}