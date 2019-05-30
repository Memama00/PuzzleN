package br.com.poli.puzzleN.engine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import br.com.poli.puzzleN.puzzles.*;
import br.com.poli.puzzleN.testes.Testes;

public class PseudoTab {

    private int[][] tiles;
    private int display_width;
    private Point blank;
    public static PseudoTab SOLVED;

    private PseudoTab(int k) {
        tiles = new int[k][k];
        int cnt = 1;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                tiles[i][j] = cnt;
                cnt++;
            }
        }
        display_width = Integer.toString(cnt).length();

        // init blank
        blank = new Point(tiles.length - 1, tiles.length - 1);
        tiles[blank.y][blank.x] = 0;
    }

    public PseudoTab(int[][] tab) {
        tiles = tab;
        int cnt = 1;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] == 0)
                    blank = new Point(j, i);
                cnt++;
            }
        }
        display_width = Integer.toString(cnt).length();
        tiles[blank.y][blank.x] = 0;
        SOLVED = new PseudoTab(tiles.length);
    }

    public PseudoTab(PseudoTab toClone) {
        this(toClone.cloneTab()); // chain to basic init
        for (Point p : allTilePos()) {
            tiles[p.y][p.x] = toClone.tile(p);
        }
        blank = toClone.getBlank();
    }

    public int[][] cloneTab() {
        int[][] clone = new int[tiles.length][tiles.length];
        for (int y = 0; y < tiles.length; y++)
            for (int x = 0; x < tiles.length; x++)
                clone[y][x] = tiles[y][x];
        return clone;
    }

    public List<Point> allTilePos() {
        ArrayList<Point> out = new ArrayList<Point>();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                out.add(new Point(i, j));
            }
        }
        return out;
    }

    public int tile(Point p) {
        return tiles[p.y][p.x];
    }

    public Point getBlank() {
        return blank;
    }

    public Point whereIs(int x) {
        for (Point p : allTilePos()) {
            if (tile(p) == x) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PseudoTab) {
            for (Point p : allTilePos())
                if (this.tile(p) != ((PseudoTab) o).tile(p))
                    return false;

            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int out = 0;
        for (Point p : allTilePos()) {
            out = (out * tiles.length * tiles.length) + this.tile(p);
        }
        return out;
    }

    public void show() {
        System.out.println("-----------------");
        for (int i = 0; i < tiles.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < tiles.length; j++) {
                int n = tiles[i][j];
                String s;
                if (n > 0) {
                    s = Integer.toString(n);
                } else {
                    s = "";
                }
                while (s.length() < display_width) {
                    s += " ";
                }
                System.out.print(s + "| ");
            }
            System.out.print("\n");
        }
        System.out.print("-----------------\n\n");
    }

    public List<Point> allValidMoves() {
        ArrayList<Point> out = new ArrayList<Point>();
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                Point tp = new Point(blank.x + dx, blank.y + dy);
                if (isValidMove(tp)) {
                    out.add(tp);
                }
            }
        }
        return out;
    }

    public boolean isValidMove(Point p) {
        if ((p.x < 0) || (p.x >= tiles.length)) {
            return false;
        }
        if ((p.y < 0) || (p.y >= tiles.length)) {
            return false;
        }
        int dx = blank.x - p.x;
        int dy = blank.y - p.y;
        if ((Math.abs(dx) + Math.abs(dy) != 1) || (dx * dy != 0)) {
            return false;
        }
        return true;
    }

    public void move(Point p) {
        if (!isValidMove(p)) {
            throw new RuntimeException("Invalid move");
        }
        assert tiles[blank.y][blank.x] == 0;
        tiles[blank.y][blank.x] = tiles[p.y][p.x];
        tiles[p.y][p.x] = 0;
        blank = p;
    }

    public PseudoTab moveClone(Point p) {
        PseudoTab out = new PseudoTab(this);
        out.move(p);
        return out;
    }

    public void shuffle(int howmany) {
        for (int i = 0; i < howmany; i++) {
            List<Point> possible = allValidMoves();
            int which = (int) (Math.random() * possible.size());
            Point move = possible.get(which);
            this.move(move);
        }
    }

    public void shuffle() {
        int mutplex = 1;
        for (int i = 0; i <= (tiles.length * tiles.length); i++)
            mutplex *= tiles.length;
        shuffle(mutplex);
    }

    public int numberMisplacedTiles() {
        int wrong = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if ((tiles[i][j] > 0) && (tiles[i][j] != (i * tiles.length) + (j + 1))) {
                    wrong++;
                }
            }
        }
        return wrong;
    }

    public boolean isSolved() {
        return numberMisplacedTiles() == 0;
    }

    public int manhattanDistance() {
        int sum = 0;
        for (Point p : allTilePos()) {
            int val = tile(p);
            if (val > 0) {
                Point correct = SOLVED.whereIs(val);
                sum += Math.abs(correct.x = p.x);
                sum += Math.abs(correct.y = p.y);
            }
        }
        return sum;
    }

    public int estimateError() {
        return this.numberMisplacedTiles();
    }

    public List<PseudoTab> allAdjacentPuzzles() {
        ArrayList<PseudoTab> out = new ArrayList<PseudoTab>();
        for (Point move : allValidMoves()) {
            out.add(moveClone(move));
        }
        return out;
    }

    public List<PseudoTab> aStarSolve() {

        HashMap<PseudoTab, PseudoTab> anterior = new HashMap<PseudoTab, PseudoTab>();
        HashMap<PseudoTab, Integer> depth = new HashMap<PseudoTab, Integer>();
        final HashMap<PseudoTab, Integer> score = new HashMap<PseudoTab, Integer>();

        Comparator<PseudoTab> comparator = new Comparator<PseudoTab>() {
            @Override
            public int compare(PseudoTab a, PseudoTab b) {
                return score.get(a) - score.get(b);
            }
        };
        PriorityQueue<PseudoTab> proximo = new PriorityQueue<PseudoTab>(100000, comparator);

        anterior.put(this, null);
        depth.put(this, 0);
        score.put(this, this.estimateError());
        proximo.add(this);
        int cnt = 0;
        while (proximo.size() > 0) {
            PseudoTab candidate = proximo.remove();
            cnt++;
            if (cnt % 20000 == 0) {
                System.out.printf("Considered %,d positions. Queue = %,d\n", cnt, proximo.size());
            }
            if (candidate.isSolved()) {
                System.out.printf("Solution considered %d boards\n", cnt);
                LinkedList<PseudoTab> solution = new LinkedList<PseudoTab>();
                PseudoTab backtrace = candidate;
                while (backtrace != null) {
                    solution.addFirst(backtrace);
                    backtrace = anterior.get(backtrace);
                }
                return solution;
            }
            for (PseudoTab fp : candidate.allAdjacentPuzzles()) {
                if (!anterior.containsKey(fp)) {
                    anterior.put(fp, candidate);
                    depth.put(fp, depth.get(candidate) + 1);
                    int estimate = fp.estimateError();
                    score.put(fp, depth.get(candidate) + 1 + estimate);
                    // dont' add to p-queue until the metadata is in place that the comparator needs
                    proximo.add(fp);
                }
            }
        }
        return null;
    }

    private static void showSolution(List<PseudoTab> solution) {
        if (solution != null) {
            System.out.printf("Success!  Solution with %d moves:\n", solution.size());
            for (PseudoTab sp : solution) {
                sp.show();
            }
        } else {
            System.out.println("Did not solve. :(");
        }
    }

    public static void main(String[] args) {
        Puzzle partida = new PuzzleMedio("nome");
        partida.iniciaPartida();
        Testes.showTab(partida.getTabuleiro());
        PseudoTab p = new PseudoTab(partida.getTabuleiro().gerarPseudoTabuleiro());
        System.out.println("Shuffled board:");
        p.show();

        List<PseudoTab> solution;

        System.out.println("Solving with A*");
        solution = p.aStarSolve();
        showSolution(solution);

    }

}