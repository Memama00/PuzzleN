package br.com.poli.puzzleN.engine;

import java.awt.Point;

public class P extends Point {
    private static final long serialVersionUID = 1L;
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

    public int distanceTo(Point to) {
        int dx = Math.abs(this.x - to.x);
        int dy = Math.abs(this.y - to.y);
        return (dx + dy);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof P) {
            return (((P) o).x == this.x && ((P) o).y == this.y);
        } else
            return false;
    }

}
