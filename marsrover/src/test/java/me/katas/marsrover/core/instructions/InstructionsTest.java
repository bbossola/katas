package me.katas.marsrover.core.instructions;

import org.junit.Test;
import static org.mockito.Mockito.*;

import me.katas.marsrover.core.Rover;
import me.katas.marsrover.core.instructions.Move;

public class InstructionsTest {

    @Test
    public void shouldMoveInstructionAdvanceTheRobot() {
        Rover robot = spy(new Rover());
        robot.process(new Move());
        verify(robot).advance();
    }

    @Test
    public void shouldLeftInstructionRotateLeftTheRobot() {
        Rover robot = spy(new Rover());
        robot.process(new Left());
        verify(robot).rotateLeft();
    }

    @Test
    public void shouldRightInstructionRotateRightTheRobot() {
        Rover robot = spy(new Rover());
        robot.process(new Right());
        verify(robot).rotateRight();
        
    }
}
