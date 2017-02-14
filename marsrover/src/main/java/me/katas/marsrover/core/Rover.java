package me.katas.marsrover.core;

import me.katas.marsrover.core.instructions.Instruction;

public class Rover {

    public void process(Instruction move) {
        move.execute(this);
    }

    public void advance() {
    }

    public void rotateLeft() {
    }

    public void rotateRight() {
    }

}
