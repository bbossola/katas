package me.katas.marsrover.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RoverMovementInPlateauTest {

    @Test
    public void shouldNotMoveOutsidePlateauGoingNorth() {
        Plateau plateau = new Plateau(5,5);

        Rover rover = new Rover(new Point(3,5), Orientation.NORTH, plateau);
        rover.advance();
        
        assertEquals(new Point(3,5), rover.position());
    }

    @Test
    public void shouldNotMoveOutsidePlateauGoingEeast() {
        Plateau plateau = new Plateau(5,5);

        Rover rover = new Rover(new Point(5,3), Orientation.EAST, plateau);
        rover.advance();
        
        assertEquals(new Point(5,3), rover.position());
    }

    @Test
    public void shouldNotMoveOutsidePlateauGoingSouth() {
        Plateau plateau = new Plateau(5,5);

        Rover rover = new Rover(new Point(3,0), Orientation.SOUTH, plateau);
        rover.advance();
        
        assertEquals(new Point(3,0), rover.position());
    }

    @Test
    public void shouldNotMoveOutsidePlateauGoingWest() {
        Plateau plateau = new Plateau(5,5);

        Rover rover = new Rover(new Point(0,3), Orientation.WEST, plateau);
        rover.advance();
        
        assertEquals(new Point(0,3), rover.position());
    }
}
