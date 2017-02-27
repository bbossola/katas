package me.katas.marsover.cli;

import java.util.List;

import me.katas.marsrover.core.Plateau;
import me.katas.marsrover.core.Rover;
import me.katas.marsrover.core.instructions.Instruction;

public class Mission {

    private StringBuilder output;
    private LineParser parser;

    private Plateau plateau;
    private Rover rover;
    private List<Instruction> instructions;

    public Mission() {
        this.parser = LineParser.PLATEAU;
        this.output = new StringBuilder();
    }

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

    public void process(String line) {
        parser.ingest(this, line);
        parser = parser.next();
    }

    public String output() {
        return output.toString();
    }

    public void execute() {
        for (Instruction instruction : instructions) {
            rover.process(instruction);
        }
        
        output.append(rover.position().x());
        output.append(' ');
        output.append(rover.position().y());
        output.append(' ');
        output.append(rover.orientation().toChar());
        output.append('\n');
    }
}
