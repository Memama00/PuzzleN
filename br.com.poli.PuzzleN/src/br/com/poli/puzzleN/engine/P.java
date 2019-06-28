package br.com.poli.puzzleN.engine;

import java.awt.Point;
import java.awt.geom.Point2D;

public class P extends Point {// P de Ponto
    private static final long serialVersionUID = 1L;

    public P() {
        super(0, 0);
    }

    public P(Point p) {
        super(p.x, p.y);
    }

    public P(int x, int y) {
        super(x, y);
    }

    public void trade(Point o) {
        int tx = o.x;
        int ty = o.y;
        o.x = this.x;
        o.y = this.y;
        this.x = tx;
        this.y = ty;
    }

    public int distanceTo(Point2D to) {
        int dx = Math.abs(this.x - (int) to.getX());
        int dy = Math.abs(this.y - (int) to.getY());
        return (dx + dy);
    }

    public int distanceTo(int x, int y) {
        int dx = Math.abs(this.x - x);
        int dy = Math.abs(this.y - y);
        return (dx + dy);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof P)
            return (((Point) o).x == this.x && ((Point) o).y == this.y);
        else
            return false;
    }

    public boolean equals(int x, int y) {
        return (x == this.x && y == this.y);
    }

    @Override
    public P clone(){
        return new P(x, y);
    }
}
