package me.katas.marsover.cli;

import static me.katas.marsrover.utils.Checks.notNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.katas.marsrover.core.instructions.Instruction;
import me.katas.marsrover.core.instructions.Left;
import me.katas.marsrover.core.instructions.Move;
import me.katas.marsrover.core.instructions.Right;

public class InstructionsParser {

    private static final Map<String, Class<?>> INSTRUCTIONS = new HashMap<>();
    static {
        INSTRUCTIONS.put("M", Move.class);
        INSTRUCTIONS.put("L", Left.class);
        INSTRUCTIONS.put("R", Right.class);
    }
    
    private Program program;

    public InstructionsParser(Program program) {
        this.program = program;
    }

    public boolean ingest(String line) {
        try {
            line = line.trim();

            List<Instruction> instructions = new LinkedList<Instruction>();
            for (int i=0; i<line.length(); i++) {
                String key = Character.toString(line.charAt(i));
                Instruction what = (Instruction)notNull(INSTRUCTIONS.get(key)).newInstance();
                instructions.add(what);
            }
            program.load(instructions);
            return true;
        } catch (Exception ex) {
            System.err.println("Invalid instructions input: '"+line+"'");
            return false;
        }
    }


}
