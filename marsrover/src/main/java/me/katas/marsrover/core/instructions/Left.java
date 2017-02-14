package me.katas.marsrover.core.instructions;

import me.katas.marsrover.core.Rover;

public class Left implements Instruction {

    @Override
    public void execute(Rover rover) {
        rover.rotateLeft();
    }

}
