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

    private int[][] tab;
    private int espaco;
    private Point zero;
    private int etapa;
    public Point move;
    public static PseudoTab SOLVED;

    private PseudoTab(int k) {
        tab = new int[k][k];
        int cnt = 1;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                tab[i][j] = cnt;
                cnt++;
            }
        }
        espaco = Integer.toString(cnt).length();
        zero = new Point(tab.length - 1, tab.length - 1);
        tab[zero.y][zero.x] = 0;
        etapa = tab.length - 3;
    }

    public PseudoTab(int[][] tab) {
        this.tab = tab;
        int cnt = 1;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] == 0)
                    zero = new Point(j, i);
                cnt++;
            }
        }
        espaco = Integer.toString(cnt).length();
        tab[zero.y][zero.x] = 0;
        SOLVED = new PseudoTab(tab.length);
        etapa = 0;
    }

    public PseudoTab(PseudoTab toClone) {
        this(toClone.cloneTab());
        for (Point p : allPositions()) {
            tab[p.y][p.x] = toClone.bloco(p);
        }
        zero = toClone.getZero();
        etapa = toClone.etapa;
    }

    public int[][] cloneTab() {
        int[][] clone = new int[tab.length][tab.length];
        for (int y = 0; y < tab.length; y++)
            for (int x = 0; x < tab.length; x++)
                clone[y][x] = tab[y][x];
        return clone;
    }

    public List<Point> allPositions() {
        ArrayList<Point> out = new ArrayList<Point>();
        for (int i = 0; i < tab.length; i++)
            for (int j = 0; j < tab.length; j++)
                out.add(new Point(i, j));
        return out;
    }

    public int bloco(Point p) {
        return tab[p.y][p.x];
    }

    public Point getZero() {
        return zero;
    }

    public Point posicao(int x) {
        for (Point p : allPositions()) {
            if (bloco(p) == x) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PseudoTab) {
            for (Point p : allPositions())
                if (this.bloco(p) != ((PseudoTab) o).bloco(p))
                    return false;

            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int out = 0;
        for (Point p : allPositions()) {
            out = (out * tab.length * tab.length) + this.bloco(p);
        }
        return out;
    }

    public void show() {
        System.out.println("-----------------");
        for (int i = 0; i < tab.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < tab.length; j++) {
                int n = tab[i][j];
                String s;
                if (n > 0) {
                    s = Integer.toString(n);
                } else {
                    s = "";
                }
                while (s.length() < espaco) {
                    s += " ";
                }
                System.out.print(s + "| ");
            }
            System.out.print("\n");
        }
        System.out.print("-----------------\n\n");
    }

    public List<Point> validMoves() {
        ArrayList<Point> out = new ArrayList<Point>();
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                Point tp = new Point(zero.x + dx, zero.y + dy);
                if (isValidMove(tp)) {
                    out.add(tp);
                }
            }
        }
        return out;
    }

    public boolean isValidMove(Point p) {
        if ((p.x < etapa) || (p.x >= tab.length)) {
            return false;
        }
        if ((p.y < etapa) || (p.y >= tab.length)) {
            return false;
        }
        int dx = zero.x - p.x;
        int dy = zero.y - p.y;
        if ((Math.abs(dx) + Math.abs(dy) != 1) || (dx * dy != 0)) {
            return false;
        }
        return true;
    }

    private void updateEtapa() {
        if (tab.length - etapa <= 3)
            return;
        if (isEtapaCompleat()) {
            etapa++;
            updateEtapa();
        }
    }

    private boolean isEtapaCompleat() {
        for (int y = 0; y < tab.length; y++)
            for (int x = 0; x < tab.length; x++)
                if (etapa == (x & y) && (tab[y][x] != (y * tab.length) + (x + 1)))
                    return false;
                else if(x > etapa)
                    break;
        return true;
    }

    public void move(Point p) {
        if (!isValidMove(p)) {
            throw new RuntimeException("Invalid move");
        }
        assert tab[zero.y][zero.x] == 0;
        tab[zero.y][zero.x] = tab[p.y][p.x];
        tab[p.y][p.x] = 0;
        zero = p;
        this.move = p;
        updateEtapa();
    }

    public void moveTo(Point p) {
        if (zero.y == etapa)
            move(new Point(zero.x, zero.y + 1));
        if (zero.x == etapa)
            move(new Point(zero.x + 1, zero.y));
        Point nextP = new Point(zero.x + (zero.x - p.x) < 0 ? 1 : -1, zero.y);
        while (zero.x != p.x && isValidMove(nextP)) {
            nextP = new Point(zero.x + (zero.x - p.x) < 0 ? 1 : -1, zero.y);
            if (blocoDistance(nextP) != 0)
                move(nextP);
            else {
                nextP = new Point(zero.x, zero.y + 1);
                if (blocoDistance(nextP) != 0 && isValidMove(nextP))
                    move(nextP);
                else
                    move(new Point(zero.x, zero.y - 1));
            }
        }
        while (zero.y != p.y && isValidMove(nextP)) {
            nextP = new Point(zero.x, zero.y + (zero.y - p.y) < 0 ? 1 : -1);
            if (blocoDistance(nextP) != 0 && isValidMove(nextP))
                move(nextP);
            else {
                nextP = new Point(zero.x + 1, zero.y);
                if (blocoDistance(nextP) != 0 && isValidMove(nextP))
                    move(nextP);
                else
                    move(new Point(zero.x - 1, zero.y));
            }
        }
    }

    public PseudoTab moveClone(Point p) {
        PseudoTab out = new PseudoTab(this);
        out.move(p);
        return out;
    }

    public int foraDoLugar() {
        int wrong = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if ((tab[i][j] > 0) && (tab[i][j] != (i * tab.length) + (j + 1))) {
                    wrong++;
                }
            }
        }
        return wrong;
    }

    public boolean isSolved() {
        return foraDoLugar() == 0;
    }

    public float totalDistance() {
        float sum = 0;
        for (Point p : allPositions())
            if (bloco(p) > 0)
                sum = +blocoDistance(p);
        return sum;
    }

    public float lineDistance(int y) {
        float sum = 0;
        for (Point p : allPositions())
            if (bloco(p) > 0 && SOLVED.posicao(bloco(p)).y == y)
                sum = +blocoDistance(p);
        return sum;
    }

    public float colDistance(int x) {
        float sum = 0;
        for (Point p : allPositions())
            if (bloco(p) > 0 && SOLVED.posicao(bloco(p)).x == x)
                sum = +blocoDistance(p);
        return sum;
    }

    public float blocoDistance(Point bloco) {
        return (float) bloco.distance(SOLVED.posicao(bloco(bloco)));
    }

    public float etapaDistance() {
        return lineDistance(etapa) + colDistance(etapa);
    }

    public int estimateError() {
        return this.foraDoLugar();
    }

    public List<PseudoTab> sucessores() {
        ArrayList<PseudoTab> out = new ArrayList<PseudoTab>();
        for (Point move : validMoves()) {
            out.add(moveClone(move));
        }
        return out;
    }

    public LinkedList<PseudoTab> aStarSolve() {

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
                System.out.printf("Calculando... %,d tabuleiros. Fila = %,d\n", cnt, proximo.size());
            }
            if (candidate.isSolved()) {
                System.out.printf("Resolvido considerando %d tabuleiros\n", cnt);
                candidate.show();
                LinkedList<PseudoTab> solution = new LinkedList<PseudoTab>();
                PseudoTab backtrace = candidate;
                while (backtrace != null) {
                    solution.addFirst(backtrace);
                    backtrace = anterior.get(backtrace);
                }
                return solution;
            }
            for (PseudoTab fp : candidate.sucessores()) {
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

    public static void showSolution(List<PseudoTab> solution) {
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
        Puzzle partida = new PuzzleFacil("nome");
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