package me.katas.marsover.cli;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import me.katas.marsrover.core.Orientation;
import me.katas.marsrover.core.Plateau;
import me.katas.marsrover.core.Point;
import me.katas.marsrover.core.Rover;
import me.katas.marsrover.core.instructions.Instruction;
import me.katas.marsrover.core.instructions.Left;
import me.katas.marsrover.core.instructions.Move;
import me.katas.marsrover.core.instructions.Right;

public class TestMission {

    @Test
    public void shouldLoadPlateauAsFirstLine() {
        Mission mission = new Mission();

        mission.process("5 5");

        assertEquals(new Plateau(5, 5), mission.plateau());
    }

    @Test
    public void shouldLoadRobotAsSecondLine() {
        Mission mission = new Mission();

        mission.process("5 5");
        mission.process("1 2 N");

        assertEquals(new Rover(new Point(1, 2), Orientation.NORTH, mission.plateau()), mission.rover());
    }

    @Test
    public void shouldLoadInstructionsAsThirdLine() {
        Mission mission = new Mission();

        mission.process("5 5");
        mission.process("1 2 N");
        mission.process("LMR");

        List<Instruction> expected = Arrays.asList(new Left(), new Move(), new Right());
        assertEquals(expected, mission.instructions());
    }

    @Test
    public void shouldProduceOutputAfterThirdLine() {
        Mission mission = new Mission();

        mission.process("5 5");
        mission.process("1 2 N");
        mission.process("LMR");

        assertEquals("0 2 N\n", mission.output());
    }

    @Test
    public void shouldProduceOutputWithMultipleRobots() {
        Mission mission = new Mission();

        mission.process("5 5");
        mission.process("1 2 N");
        mission.process("LMR");
        mission.process("3 3 S");
        mission.process("RRM");

        assertEquals("0 2 N\n3 4 N\n", mission.output());
    }

    @Test
    public void shouldProduceOutputWithOriginalProblemStatementInput() {
        Mission mission = new Mission();

        mission.process("5 5");
        mission.process("1 2 N");
        mission.process("LMLMLMLMM");
        mission.process("3 3 E");
        mission.process("MMRMMRMRRM");

        assertEquals("1 3 N\n5 1 E\n", mission.output());
    }
    
    //    @Test
//    public void shouldLoadRobotAsFourthLine() {
//        Mission mission = new Mission();
//
//        mission.process("5 5");
//        mission.process("1 2 N");
//        mission.process("LMR");
//        mission.process("3 3 N");
//
//        assertEquals(new Rover(new Point(1, 2), Orientation.NORTH, mission.plateau()), mission.rover());
//    }

}
