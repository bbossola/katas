package me.katas.marsrover.core;

public class Plateau {

    private Point min;
    private Point max;

    public Plateau(int x, int y) {
        this.min = new Point(0,0);
        this.max = new Point(x,y);
    }

    public Point place(Point where) {
        return where.ceil(this.max).floor(this.min);
    }

}
