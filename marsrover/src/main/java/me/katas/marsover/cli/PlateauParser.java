package me.katas.marsover.cli;

import me.katas.marsrover.core.Plateau;

public class PlateauParser {

    private Program program;

    public PlateauParser(Program program) {
        this.program = program;
    }

    public boolean ingest(String line) {
        try {
            String[] tokens = line.split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            program.load(new Plateau(x,y));
            return true;
        } catch (Exception ex) {
            System.err.println("Invalid plateau input: '"+line+"'");
            return false;
        }
    }

}
