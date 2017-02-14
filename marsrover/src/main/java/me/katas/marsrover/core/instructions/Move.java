package me.katas.marsrover.core.instructions;

import me.katas.marsrover.core.Rover;

public class Move implements Instruction {

    @Override
    public void execute(Rover rover) {
        rover.advance();
    }

}
