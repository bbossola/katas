package me.katas.marsover.cli;

import me.katas.marsrover.core.Plateau;

public class PlateauParser implements LineParser {

    @Override
    public void ingest(Mission mission, String line) {
        try {
            String[] tokens = line.split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            mission.load(new Plateau(x,y));
        } catch (Exception ex) {
            System.err.println("Invalid plateau input: '"+line+"'");
        }
    }

    @Override
    public LineParser next() {
        return LineParser.ROVER;
    }
}
