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
import pingball.datatypes.Portal;
import pingball.datatypes.RightFlipper;
import pingball.datatypes.SquareBumper;
import pingball.datatypes.TriangularBumper;
import sun.java2d.loops.DrawPolygons;

public class Canvas extends JPanel 
    implements ActionListener {

    /**
     * Note: 
     *      The total GUI width >= CANVAS_WIDTH = (currently 430)
     *      The total GUI height >= CANVAS_HEIGHT + MenuBarHeight = (currently 475)
     *                                             MenuBarHeight = 45
     */
    
    //Fixed variables for the Board/Wall/Canvas
    private final int BOARD_WIDTH = 400;
    private final int BOARD_HEIGHT = 400;
    private final int BOARD_OFFSET_X = 10;
    private final int BOARD_OFFSET_Y = 10;

    private final int WALL_LENGTH = 410; //BOARD_HEIGHT + 2*WALL_WIDTH
    private final int WALL_WIDTH = 5;

    private final int CANVAS_WIDTH = BOARD_OFFSET_X*2 + WALL_WIDTH*2 + BOARD_WIDTH;
    private final int CANVAS_HEIGHT = BOARD_OFFSET_Y*2 + WALL_WIDTH*2 + BOARD_HEIGHT;

    //Scaling factor - used for all gadgets
    private final int SCALE_FACTOR = 20;
    
    //Fixed variables for all generic gadgets
    private final int GADGET_OFFSET_X_EDGE = BOARD_OFFSET_X + WALL_WIDTH;
    private final int GADGET_OFFSET_Y_EDGE = BOARD_OFFSET_Y + WALL_WIDTH;
    private final int GADGET_OFFSET_TOCENTER = SCALE_FACTOR/2;
    
    //Fixed variable for the square bumper specifically
    private final int SQR_BUMPER_LENGTH = 18;
    private final int SQR_BUMPER_OFFSET = (SCALE_FACTOR - SQR_BUMPER_LENGTH)/2;
    
    private final int DELAY = 10;  //Milliseconds to repaint
    Color backgroundColor = Color.white;
    List<Shape> shapes = new ArrayList<Shape>();
    List<Ball> balls = new ArrayList<Ball>();
    List<Gadget> gadgets = new ArrayList<Gadget>();
    List<Gadget> spawners = new ArrayList<Gadget>();
    List<Gadget> portals = new ArrayList<Gadget>();
    private Board board; 
    private Timer timer;
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
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

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
       spawners = this.board.getSpawners();
       portals = this.board.getPortals();
       
       makeWalls(graph2);
       for (Ball ball: balls ){

               Shape temp = makeBall(ball);
             
               graph2.setColor(Color.GREEN);
               
               graph2.fill(temp);
  
       }
       for(Gadget gadget: gadgets) makeGadget(gadget, graph2);
       for(Gadget gadget: spawners) makeGadget(gadget, graph2);
       for(Gadget gadget: portals) makeGadget(gadget, graph2);

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
    
    Shape newCirc = new Ellipse2D.Float((float)ball.getNormalPosition()[0]*SCALE_FACTOR + GADGET_OFFSET_X_EDGE, 
                                        (float) ball.getNormalPosition()[1]*SCALE_FACTOR + GADGET_OFFSET_Y_EDGE,
                                        5,
                                        5);
    return newCirc;
}

public void makeWalls(Graphics2D graph2){
    Shape vertWall1 = new Rectangle2D.Float(BOARD_OFFSET_X,BOARD_OFFSET_Y, WALL_WIDTH, WALL_LENGTH);
    Shape vertWall2 = new Rectangle2D.Float(BOARD_OFFSET_X + WALL_WIDTH + BOARD_WIDTH,
                                            BOARD_OFFSET_Y, WALL_WIDTH, WALL_LENGTH);
    Shape horWall1 = new Rectangle2D.Float(BOARD_OFFSET_X, BOARD_OFFSET_Y, WALL_LENGTH, WALL_WIDTH);
    Shape horWall2 = new Rectangle2D.Float(BOARD_OFFSET_X, 
                                            BOARD_OFFSET_Y + WALL_WIDTH + BOARD_HEIGHT, 
                                            WALL_LENGTH, WALL_WIDTH);
    
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
        Shape circleBumper = new Ellipse2D.Float((float)cb.getNormalCircle().getCenter().x()*SCALE_FACTOR + GADGET_OFFSET_X_EDGE - GADGET_OFFSET_TOCENTER,
                                                (float)cb.getNormalCircle().getCenter().y()*SCALE_FACTOR +GADGET_OFFSET_Y_EDGE - GADGET_OFFSET_TOCENTER, 
                                                SCALE_FACTOR/*width*/,
                                                SCALE_FACTOR/*height*/);

        graph2.setColor(Color.ORANGE);
        
        graph2.fill(circleBumper);
    }
    else if(gadget.getGadgetType().equals("Square Bumper")){
        
        SquareBumper sb = (SquareBumper)gadget;
        Shape squareBumper = new Rectangle2D.Float((float)gadget.getPosition().x()*SCALE_FACTOR + GADGET_OFFSET_X_EDGE + SQR_BUMPER_OFFSET, 
                                                (float)gadget.getPosition().y()*SCALE_FACTOR + GADGET_OFFSET_Y_EDGE + SQR_BUMPER_OFFSET, 
                                                SQR_BUMPER_LENGTH, 
                                                SQR_BUMPER_LENGTH);

        graph2.setColor(Color.BLUE);
        
        graph2.fill(squareBumper);  
    }
    else if(gadget.getGadgetType().equals("Absorber")){
        
        Absorber abs = (Absorber)gadget;
        Shape absorber = new Rectangle2D.Float((float)abs.getPosition().x()*SCALE_FACTOR + GADGET_OFFSET_X_EDGE , (float)abs.getPosition().y()*SCALE_FACTOR + GADGET_OFFSET_Y_EDGE , abs.getWidth()*SCALE_FACTOR, abs.getHeight()*SCALE_FACTOR);

        graph2.setColor(Color.magenta);
        
        graph2.fill(absorber);  
    }
    else if(gadget.getGadgetType().equals("Triangluar Bumper")){
    	TriangularBumper tb = (TriangularBumper) gadget;
    	GeneralPath tbLineDrawer = new GeneralPath();
    	
    	//positions are relvative to the top left hand corner
    	double xPosition = tb.getPosition().x()*SCALE_FACTOR + GADGET_OFFSET_X_EDGE;
    	double yPosition = tb.getPosition().y()*SCALE_FACTOR + GADGET_OFFSET_Y_EDGE;
    	
    	if (tb.getOrientation()==90){ //top-right corner triangle
    		tbLineDrawer.moveTo(xPosition, yPosition);                            //starts top-left hand corner
    		tbLineDrawer.lineTo(xPosition+SCALE_FACTOR, yPosition);               //moves to right
    		tbLineDrawer.lineTo(xPosition+SCALE_FACTOR, yPosition+SCALE_FACTOR);  //moves downward
    	} else if(tb.getOrientation()==180){ //bottom-right corner triangle
    		tbLineDrawer.moveTo(xPosition+SCALE_FACTOR, yPosition);               //starts top-right hand corner
    		tbLineDrawer.lineTo(xPosition+SCALE_FACTOR, yPosition+SCALE_FACTOR);  //moves downward
    		tbLineDrawer.lineTo(xPosition, yPosition+SCALE_FACTOR);               //moves left
    	} else if(tb.getOrientation()==270){//bottom-left corner triangle
    		tbLineDrawer.moveTo(xPosition, yPosition);                            //starts top-left hand corner
    		tbLineDrawer.lineTo(xPosition+SCALE_FACTOR, yPosition+SCALE_FACTOR);  //moves diagonally to bottom-right corner
    		tbLineDrawer.lineTo(xPosition, yPosition+SCALE_FACTOR);               // moves left
    	} else if(tb.getOrientation()==0){//top-left corner triangle
    		tbLineDrawer.moveTo(xPosition+SCALE_FACTOR, yPosition);               //starts top-right corner
    		tbLineDrawer.lineTo(xPosition, yPosition);                            //moves left
    		tbLineDrawer.lineTo(xPosition, yPosition+SCALE_FACTOR);               //moves downward
    	}
    	
    	tbLineDrawer.closePath();
    	graph2.setColor(Color.RED);
    	graph2.fill(tbLineDrawer);
    	
        //Shape absorber = new Rectangle2D.Float((float)abs.getPosition().x()*20 + 8 , (float)abs.getPosition().y()*20 , abs.getWidth()*20 - 6 , abs.getHeight()*10 + 3);

    }else if(gadget.getGadgetType().equals("Portal")){
        Portal p = (Portal)gadget;
        Shape portal = new Ellipse2D.Float((float)p.getPosition().x()*SCALE_FACTOR + GADGET_OFFSET_X_EDGE,
                                                (float)p.getPosition().y()*SCALE_FACTOR +GADGET_OFFSET_Y_EDGE, 
                                                SCALE_FACTOR/*width*/,
                                                SCALE_FACTOR/*height*/);
        Shape portalInner = new Ellipse2D.Float((float)p.getPosition().x()*SCALE_FACTOR + GADGET_OFFSET_X_EDGE+2,
                (float)p.getPosition().y()*SCALE_FACTOR +GADGET_OFFSET_Y_EDGE+2, 
                SCALE_FACTOR-4/*width*/,
                SCALE_FACTOR-4/*height*/);
        
        graph2.setColor(Color.BLACK);
        graph2.fill(portal);
        graph2.setColor(Color.CYAN);
        graph2.fill(portalInner);
    }
    
}


//TODO Implement these methods

//public Shape makeFlipper(){
//    
//}


   /**
    * This method is called every time the timer is set off and 
    * it repaints the GUI
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();  
    }
}