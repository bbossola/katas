package me.katas.marsover.cli;

import java.util.List;

import me.katas.marsrover.core.Plateau;
import me.katas.marsrover.core.Rover;
import me.katas.marsrover.core.instructions.Instruction;

public class Program {

    private Plateau plateau;
    private Rover rover;
    private List<Instruction> instructions;

    public void load(Plateau plateau) {
        this.plateau = plateau;
    }

    public void load(Rover rover) {
        this.rover = rover;
    }

    public void load(List<Instruction> instructions) {
        this.instructions = instructions;
    }
    
    public Plateau plateau() {
        return plateau;
    }

    public Rover rover() {
        return rover;
    }

    public List<Instruction> instructions() {
        return instructions;
    }


}
