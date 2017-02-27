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

public class InstructionsParser implements LineParser {

    private static final Map<String, Class<?>> INSTRUCTIONS = new HashMap<>();
    static {
        INSTRUCTIONS.put("M", Move.class);
        INSTRUCTIONS.put("L", Left.class);
        INSTRUCTIONS.put("R", Right.class);
    }
    
    @Override
    public void ingest(Mission mission, String line) {
        try {
            line = line.trim();

            List<Instruction> instructions = new LinkedList<Instruction>();
            for (int i=0; i<line.length(); i++) {
                String key = Character.toString(line.charAt(i));
                Instruction what = (Instruction)notNull(INSTRUCTIONS.get(key)).newInstance();
                instructions.add(what);
            }
            mission.load(instructions);
            mission.execute();
        } catch (Exception ex) {
            System.err.println("Invalid instructions input: '"+line+"'");
        }
    }

    @Override
    public LineParser next() {
        return LineParser.ROVER;
    }
}
