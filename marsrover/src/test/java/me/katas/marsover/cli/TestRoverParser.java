package me.katas.marsover.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import me.katas.marsrover.core.Orientation;
import me.katas.marsrover.core.Plateau;
import me.katas.marsrover.core.Point;

public class TestRoverParser {

    private Program program;

    @Before
    public void prepare() {
        program = new Program();
        program.load(new Plateau(5,5));
    }

    @Test
    public void shouldLoadRoverWithCorrectInputAndReturnTrue() {
        
        RoverParser parser = new RoverParser(program);
        boolean res = parser.ingest("1 2 N");
        
        assertNotNull(program.rover());
        assertEquals(new Point(1,2), program.rover().position());
        assertEquals(Orientation.NORTH, program.rover().orientation());
        assertEquals(new Plateau(5,5), program.rover().plateau());

        assertTrue(res);
    }

    @Test
    public void shouldNOTLoadPlateauWithCorrectInputAndReturnFalse() {
        
        RoverParser parser = new RoverParser(program);
        boolean res = parser.ingest("5 2 3");
        
        assertNull(program.rover());
        assertFalse(res);
    }
    
    @Test
    public void shouldLoadRoverAtSouth() {
        new RoverParser(program).ingest("1 2 S");
        assertEquals(Orientation.SOUTH, program.rover().orientation());
    }
    
    @Test
    public void shouldLoadRoverAtWest() {
        new RoverParser(program).ingest("1 2 W");
        assertEquals(Orientation.WEST, program.rover().orientation());
    }
    
    @Test
    public void shouldLoadRoverAtEast() {
        new RoverParser(program).ingest("1 2 E");
        assertEquals(Orientation.EAST, program.rover().orientation());
    }

}
