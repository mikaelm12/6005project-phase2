package warmup;

import java.util.List;

import physics.LineSegment;

/**
 * Represents the outer walls of a pingball board
 * @author AlexR
 *
 *
 */
public interface WallInterface {

    /**
     * Set the Coefficient of reflection of the wall
     * @param coeff to be assigned
     */
    public void setCoR(double coeff);
    
    /**
     * Get the Coefficient of reflection of the wall
     * @return 
     */
    public double getCoR();
    
    /**
     * Defines if the wall is solid and visible or invisible (shared with other boards)
     * @return true if the wall is solid and visible, false otherwise
     */
    public boolean isSolid();
    
    /**
     * Get the coordinates of the line in the form of an array [x1, y1, x2, y2]
     * @return coordinates
     */
    public double[] getCoordinates();
    
    /**
     * Gets the line segment that represents this wall
     */
    public LineSegment getLine();
}
