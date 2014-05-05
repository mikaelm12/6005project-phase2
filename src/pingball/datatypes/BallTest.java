package pingball.datatypes;

import static org.junit.Assert.*;

import org.junit.Test;

public class BallTest {
    
    @Test public void testUpdateBallPosition(){
        double timeStep = 0.05;
        Ball ball = new Ball("ball",5,4,3,6);
        double newX = 5 + 3*timeStep;
        double newY = 4 + 6*timeStep;
        ball.updateBallPosition(timeStep);
        assertTrue(ball.getPosition()[0] == newX);
        assertTrue(ball.getPosition()[1] == newY);
        
        Ball ball2 = new Ball("ball2",5,4,-3,-6);
        double newX2 = 5 + (-3*timeStep);
        double newY2 = 4 + (-6*timeStep);
        ball2.updateBallPosition(timeStep);
        assertTrue(ball2.getPosition()[0] == newX2);
        assertTrue(ball2.getPosition()[1] == newY2);
    }
    
    @Test public void testUpdateBallVelocityAfterTimestep(){
        double timeStep = 0.05;
        double gravity = 25;
        double mu = 0.025;
        double mu2 = 0.025;
        Ball ball = new Ball("ball",5,4,3,6);
        double newY1 = 6 + gravity*timeStep;
        double Vmag = Math.sqrt(Math.pow(3, 2) + 
                                Math.pow(newY1, 2));
        double newX2 = 3*(1-mu*timeStep-mu2*Vmag*timeStep);
        double newY2 = newY1*(1-mu*timeStep-mu2*Vmag*timeStep);
        ball.updateBallVelocityAfterTimestep(gravity, mu, mu2, timeStep);
        assertTrue(newX2 == ball.getVelocity().x());
        assertTrue(newY2 == ball.getVelocity().y());
        
        Ball ball2 = new Ball("ball2",5,4,-3,-6);
        double newY = -6 + gravity*timeStep;
        double Vmag2 = Math.sqrt(Math.pow(-3, 2) + 
                                Math.pow(newY, 2));
        double newX3 = -3*(1-mu*timeStep-mu2*Vmag2*timeStep);
        double newY3 = newY*(1-mu*timeStep-mu2*Vmag2*timeStep);
        ball2.updateBallVelocityAfterTimestep(gravity, mu, mu2, timeStep);
        assertTrue(newX3 == ball2.getVelocity().x());
        assertTrue(newY3 == ball2.getVelocity().y());
        
    }

}
