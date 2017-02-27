package me.katas.marsover.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import me.katas.marsrover.core.Orientation;
import me.katas.marsrover.core.Plateau;
import me.katas.marsrover.core.Point;

public class TestRoverParser {

    private Mission program;

    @Before
    public void prepare() {
        program = new Mission();
        program.load(new Plateau(5,5));
    }

    @Test
    public void shouldLoadRoverWithCorrectInputAndReturnTrue() {
        
        RoverParser parser = new RoverParser();
        parser.ingest(program, "1 2 N");
        
        assertNotNull(program.rover());
        assertEquals(new Point(1,2), program.rover().position());
        assertEquals(Orientation.NORTH, program.rover().orientation());
        assertEquals(new Plateau(5,5), program.rover().plateau());
    }

    @Test
    public void shouldNOTLoadPlateauWithCorrectInputAndReturnFalse() {
        
        RoverParser parser = new RoverParser();
        parser.ingest(program, "5 2 3");
        
        assertNull(program.rover());
    }
    
    @Test
    public void shouldLoadRoverAtSouth() {
        new RoverParser().ingest(program, "1 2 S");
        assertEquals(Orientation.SOUTH, program.rover().orientation());
    }
    
    @Test
    public void shouldLoadRoverAtWest() {
        new RoverParser().ingest(program, "1 2 W");
        assertEquals(Orientation.WEST, program.rover().orientation());
    }
    
    @Test
    public void shouldLoadRoverAtEast() {
        new RoverParser().ingest(program, "1 2 E");
        assertEquals(Orientation.EAST, program.rover().orientation());
    }

}
