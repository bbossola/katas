package me.katas.marsrover.core.instructions;

import me.katas.marsrover.core.Rover;

public class Right implements Instruction {

    @Override
    public void execute(Rover rover) {
        rover.rotateRight();
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == this.getClass();
    }
}
