package me.katas.marsrover.core;

import me.katas.marsrover.core.instructions.Instruction;

public class Rover {

    private Point position;
    private Orientation orientation;
    private Plateau plateau;

    public Rover(Point point, Orientation orientation, Plateau plateau) {
        this.position = point;
        this.orientation = orientation;
        this.plateau = plateau;
    }

    public void process(Instruction move) {
        move.execute(this);
    }

    public void advance() {
        position = plateau.place(orientation.step(position));
    }

    public void rotateLeft() {
        orientation = orientation.toLeft();
    };

    public void rotateRight() {
        orientation = orientation.toRight();
    }

    public Orientation orientation() {
        return this.orientation;
    }

    public Point position() {
        return this.position;
    }

    public Plateau plateau() {
        return this.plateau;
    }

    @Override
    public boolean equals(Object o) {
        try {
            Rover other = (Rover)o;
            return other.orientation == this.orientation &&
                   other.position.equals(this.position) &&
                   other.plateau.equals(this.plateau);
        }
        catch (Exception any) {
            return false;
        }
    }
}
