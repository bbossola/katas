package me.katas.marsrover.core.instructions;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import me.katas.marsrover.core.Orientation;
import me.katas.marsrover.core.Point;
import me.katas.marsrover.core.Rover;

public class InstructionsTest {

    @Test
    public void shouldMoveInstructionAdvanceTheRobot() {
        Rover robot = spy(newRover());
        robot.process(new Move());
        verify(robot).advance();
    }

    @Test
    public void shouldLeftInstructionRotateLeftTheRobot() {
        Rover robot = spy(newRover());
        robot.process(new Left());
        verify(robot).rotateLeft();
    }

    @Test
    public void shouldRightInstructionRotateRightTheRobot() {
        Rover robot = spy(newRover());
        robot.process(new Right());
        verify(robot).rotateRight();
        
    }

    private Rover newRover() {
        return new Rover(new Point(0,0), Orientation.NORTH);
    }
}
