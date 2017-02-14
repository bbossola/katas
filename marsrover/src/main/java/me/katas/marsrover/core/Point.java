package me.katas.marsrover.core;

public class Point {

    private int x;
    private int y;

    public Point(int xpos, int  ypos) {
        this.x = xpos;
        this.y = ypos;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
    
    @Override
    public boolean equals(Object other) {
        try {
            Point p = (Point)other;
            return p.x == x && p.y == y;
        }
        catch (Exception any) {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return x+","+y;
    }
}
