 package warmup;

import physics.LineSegment;

public class Wall implements WallInterface {

    private final LineSegment wall;
    private double CoR;
    private final boolean solid;
    public final String name;
    
    public Wall(double x0 , double y0, double x1, double y1, double CoR, boolean solid, String name){
        this.wall = new LineSegment(x0, y0, x1, y1);
        this.CoR = CoR;
        this.solid = solid;
        this.name = name;
    }
    
    
    @Override
    public void setCoR(double coeff) {
        this.CoR = coeff;

    }

    @Override
    public double getCoR() {
        return this.CoR;
    }

    @Override
    public boolean isSolid() {
        return this.solid;
    }

    @Override
    public double[] getCoordinates() {
        double[] coords = {this.wall.p1().x(), this.wall.p1().y(), this.wall.p2().x(), this.wall.p2().y()};
        return coords;
    }


    @Override
    public LineSegment getLine() {
        return this.wall;
    }

}
