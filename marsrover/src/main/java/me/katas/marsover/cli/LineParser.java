package me.katas.marsover.cli;

public interface LineParser {

    public static final LineParser PLATEAU = new PlateauParser();
    public static final LineParser ROVER = new RoverParser();
    public static final LineParser INSTRUCTIONS = new InstructionsParser();

    public void ingest(Mission mission, String line);

    public LineParser next();

}