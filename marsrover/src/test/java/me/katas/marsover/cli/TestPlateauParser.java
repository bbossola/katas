package me.katas.marsover.cli;

import static org.junit.Assert.*;

import org.junit.Test;

import me.katas.marsrover.core.Plateau;

public class TestPlateauParser {

    @Test
    public void shouldLoadPlateauWithCorrectInputAndReturnTrue() {
        Program program = new Program();
        
        PlateauParser parser = new PlateauParser(program);
        boolean res = parser.ingest("5 5");
        
        assertEquals(new Plateau(5,5), program.plateau());
        assertTrue(res);
    }

    @Test
    public void shouldNOTLoadPlateauWithCorrectInputAndReturnFalse() {
        Program program = new Program();
        
        PlateauParser parser = new PlateauParser(program);
        boolean res = parser.ingest("5 yadda yadda");
        
        assertNull(program.plateau());
        assertFalse(res);
    }
}
