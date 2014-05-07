package Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;



public class gadgets extends JFrame {
    
    
    
   
   
    
    public static void main(String[] args){
        
        
        
        
        new gadgets();
        }
    
    public gadgets(){
        this.setSize(500,500);
        this.setTitle("Gadgets");
        
        this.add(new DrawGadget(), BorderLayout.CENTER);
        this.setVisible(true);
    }
   public void addGadgets(){
        
    }
    
    private class DrawGadget extends JComponent{


//        
//        @Override
//        public void paint(Graphics g) {
//            Graphics2D graph2 = (Graphics2D) g;
//            
//            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            
//            
//            Shape drawRect = new Rectangle2D.Float(300,300, 150,100);
//            
//            
//            List<shape> shapes = new ArrayList<shape>();
//            shape rect = new shape(10,10,"test", "Rectangle");
//            shape secondRect = new shape(100,100, "test2", "Rectange");
//           shapes.add(rect);
//           shapes.add(secondRect);
//           for (shape s: shapes){
//               
//               
//                   Shape temp = makeBall(s);
//                   System.out.println(temp);
//                   graph2.setColor(Color.GREEN);
//                   graph2.fill(temp);
//                   
//               
//           }
//            
//        }
//        
//        public Shape makeBall(shape s){
//            
//
//            Shape newRect = new Ellipse2D.Float(s.x, s.y, 20, 20);
//            return newRect;
//        }
//        
//        
//    }
    }
}
