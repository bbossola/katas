package me.katas.marsrover.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RoverMovementTest {

    @Test
    public void shouldConstructTheRobotConsistently() {
        Rover rover = newRover(new Point(1,2), Orientation.SOUTH);
        assertEquals(Orientation.SOUTH, rover.orientation());
        assertEquals(new Point(1,2), rover.position());
    }

    @Test
    public void shouldRotateRightWorkAsExpected() {
        Rover rover = newRover(new Point(0,0), Orientation.NORTH);

        rover.rotateRight();
        assertEquals(Orientation.EAST, rover.orientation());

        rover.rotateRight();
        assertEquals(Orientation.SOUTH, rover.orientation());

        rover.rotateRight();
        assertEquals(Orientation.WEST, rover.orientation());

        rover.rotateRight();
        assertEquals(Orientation.NORTH, rover.orientation());
    }

    @Test
    public void shouldRotateLeftWorkAsExpected() {
        Rover rover = newRover(new Point(0,0), Orientation.NORTH);

        rover.rotateLeft();
        assertEquals(Orientation.WEST, rover.orientation());

        rover.rotateLeft();
        assertEquals(Orientation.SOUTH, rover.orientation());

        rover.rotateLeft();
        assertEquals(Orientation.EAST, rover.orientation());

        rover.rotateLeft();
        assertEquals(Orientation.NORTH, rover.orientation());
    }

    @Test
    public void shouldAdvanceFacingNorth() {
        Rover rover = newRover(new Point(2,2), Orientation.NORTH);

        rover.advance();

        assertEquals(new Point(2,3), rover.position());
    }

    @Test
    public void shouldAdvanceFacingSouth() {
        Rover rover = newRover(new Point(2,2), Orientation.SOUTH);

        rover.advance();

        assertEquals(new Point(2,1), rover.position());
    }

    @Test
    public void shouldAdvanceFacingWest() {
        Rover rover = newRover(new Point(2,2), Orientation.WEST);

        rover.advance();

        assertEquals(new Point(1,2), rover.position());
    }

    @Test
    public void shouldAdvanceFacingEast() {
        Rover rover = newRover(new Point(2,2), Orientation.EAST);

        rover.advance();

        assertEquals(new Point(3,2), rover.position());
    }
    
    private Rover newRover(Point position, Orientation orientation) {
        return new Rover(position, orientation, new Plateau(100,100));
    }
}
