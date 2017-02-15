package me.katas.marsover.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

    private Program program;

    @Before
    public void prepare() {
        program = new Program();
        program.load(new Plateau(5,5));
        program.load(new Rover(new Point(0,0), Orientation.NORTH, program.plateau()));
    }

    @Test
    public void shouldLoadInstructionsWithCorrectInputAndReturnTrue() {
        
        InstructionsParser parser = new InstructionsParser(program);
        boolean res = parser.ingest("LMR");
        
        List<Instruction> instructions = program.instructions();
        assertNotNull(instructions);
        assertEquals(Left.class, instructions.get(0).getClass());
        assertEquals(Move.class, instructions.get(1).getClass());
        assertEquals(Right.class, instructions.get(2).getClass());

        assertTrue(res);
    }

    @Test
    public void shouldNOTLoadPlateauWithCorrectInputAndReturnFalse() {
        
        InstructionsParser parser = new InstructionsParser(program);
        boolean res = parser.ingest("LXT");
        
        assertNull(program.instructions());
        assertFalse(res);
    }
    
}
