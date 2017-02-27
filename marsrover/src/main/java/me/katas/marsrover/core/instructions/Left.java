package me.katas.marsrover.core.instructions;

import me.katas.marsrover.core.Rover;

public class Left implements Instruction {

    @Override
    public void execute(Rover rover) {
        rover.rotateLeft();
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
