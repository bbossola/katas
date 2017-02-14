package me.katas.marsrover.core;

public enum Orientation {
    
    NORTH(0,1), EAST(1,0), SOUTH(0,-1), WEST(-1,0);
    
    private static final Orientation[] all = {NORTH, EAST, SOUTH, WEST};

    private int deltaX;
    private int deltaY;

    private Orientation(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public Orientation toRight() {
        return all[(1+ordinal())%all.length];
    }

    public Orientation toLeft() {
        return all[(all.length+ordinal()-1)%all.length];
    }

    public Point step(Point position) {
        return new Point(position.x()+deltaX, position.y()+deltaY);
    }
}

