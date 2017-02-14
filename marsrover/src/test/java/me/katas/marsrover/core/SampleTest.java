package me.katas.marsrover.core;

import static org.junit.Assert.*;

import org.junit.Test;

import me.katas.marsrover.core.instructions.Left;
import me.katas.marsrover.core.instructions.Move;
import me.katas.marsrover.core.instructions.Right;

public class SampleTest {

    @Test
    public void sampleRoverOne() {
        Rover rover = new Rover(new Point(1,2), Orientation.NORTH, new Plateau(5,5));
        
        rover.process(new Left());
        rover.process(new Move());
        rover.process(new Left());
        rover.process(new Move());
        rover.process(new Left());
        rover.process(new Move());
        rover.process(new Left());
        rover.process(new Move());
        rover.process(new Move());
        
        assertEquals(new Point(1,3), rover.position());
        assertEquals(Orientation.NORTH, rover.orientation());
    }

    @Test
    public void sampleRoverTwo() {
        Rover rover = new Rover(new Point(3,3), Orientation.EAST, new Plateau(5,5));
        
        rover.process(new Move());
        rover.process(new Move());
        rover.process(new Right());
        rover.process(new Move());
        rover.process(new Move());
        rover.process(new Right());
        rover.process(new Move());
        rover.process(new Right());
        rover.process(new Right());
        rover.process(new Move());
        
        assertEquals(new Point(5,1), rover.position());
        assertEquals(Orientation.EAST, rover.orientation());
    }
}
