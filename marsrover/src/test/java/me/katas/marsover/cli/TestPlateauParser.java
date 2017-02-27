package me.katas.marsover.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import me.katas.marsrover.core.Plateau;

public class TestPlateauParser {

    @Test
    public void shouldLoadPlateauWithCorrectInputAndReturnTrue() {
        Mission mission = new Mission();
        
        LineParser parser = new PlateauParser();
        parser.ingest(mission, "5 5");
        
        assertEquals(new Plateau(5,5), mission.plateau());
    }

    @Test
    public void shouldNOTLoadPlateauWithCorrectInputAndReturnFalse() {
        Mission mission = new Mission();
        
        LineParser parser = new PlateauParser();
        parser.ingest(mission, "5 yadda yadda");
        
        assertNull(mission.plateau());
    }
}
