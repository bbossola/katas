package me.katas.marsover.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import me.katas.marsrover.core.Orientation;
import me.katas.marsrover.core.Plateau;
import me.katas.marsrover.core.Point;
import me.katas.marsrover.core.Rover;
import me.katas.marsrover.core.instructions.Instruction;
import me.katas.marsrover.core.instructions.Left;
import me.katas.marsrover.core.instructions.Move;
import me.katas.marsrover.core.instructions.Right;

public class TestIInstructionsParser {

    private Mission mission;

    @Before
    public void prepare() {
        mission = new Mission();
        mission.load(new Plateau(5,5));
        mission.load(new Rover(new Point(0,0), Orientation.NORTH, mission.plateau()));
    }

    @Test
    public void shouldLoadInstructionsWithCorrectInput() {
        
        InstructionsParser parser = new InstructionsParser();
        parser.ingest(mission, "LMR");
        
        List<Instruction> instructions = mission.instructions();
        assertNotNull(instructions);
        assertEquals(Left.class, instructions.get(0).getClass());
        assertEquals(Move.class, instructions.get(1).getClass());
        assertEquals(Right.class, instructions.get(2).getClass());
    }

    @Test
    public void shouldNOTLoadPlateauWithCorrectInputAndReturnFalse() {
        
        InstructionsParser parser = new InstructionsParser();
        parser.ingest(mission, "LXT");
        
        assertNull(mission.instructions());
    }
    
}
