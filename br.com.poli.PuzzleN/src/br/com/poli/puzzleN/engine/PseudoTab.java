package br.com.poli.puzzleN.engine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class PseudoTab {

    private int[][] tab;
    private int espaco;
    private Point zero;
    private int etapa;
    public Point move;
    public static PseudoTab SOLVED;

    private PseudoTab(int k) {
        tab = new int[k][k];
        int i = 1;
        for (int y = 0; y < k; y++) {
            for (int x = 0; x < k; x++) {
                tab[y][x] = i;
                i++;
            }
        }
        espaco = Integer.toString(i).length();
        zero = new Point(tab.length - 1, tab.length - 1);
        tab[zero.y][zero.x] = 0;
        etapa = tab.length - 3;
    }

    public PseudoTab(int[][] tab) {
        this.tab = tab;
        int i = 1;
        for (int y = 0; y < tab.length; y++) {
            for (int x = 0; x < tab.length; x++) {
                if (tab[y][x] == 0)
                    zero = new Point(x, y);
                i++;
            }
        }
        espaco = Integer.toString(i).length();
        tab[zero.y][zero.x] = 0;
        SOLVED = new PseudoTab(tab.length);
        etapa = 0;
        updateEtapa();
    }

    public PseudoTab(PseudoTab toClone) {
        this(toClone.cloneTab());
        for (Point p : allPositions()) {
            tab[p.y][p.x] = toClone.bloco(p);
        }
        zero = toClone.getZero();
        etapa = toClone.etapa;
        updateEtapa();
    }

    public int[][] cloneTab() {
        int[][] clone = new int[tab.length][tab.length];
        for (int y = 0; y < tab.length; y++)
            for (int x = 0; x < tab.length; x++)
                clone[y][x] = tab[y][x];
        return clone;
    }

    public List<P> allPositions() {
        ArrayList<P> out = new ArrayList<P>();
        for (int i = 0; i < tab.length; i++)
            for (int j = 0; j < tab.length; j++)
                out.add(new P(i, j));
        return out;
    }

    public int bloco(Point p) {
        return tab[p.y][p.x];
    }

    public Point getZero() {
        return zero;
    }

    public P position(int x) {
        for (P p : allPositions()) {
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

    public void updateEtapa() {
        if (tab.length - etapa <= 3)
            return;
        if (isEtapaCompleat()) {
            etapa++;
            updateEtapa();
        }
    }

    public boolean isEtapaCompleat(int etapa) {
        for (int y = 0; y < tab.length; y++)
            if (tab[y][etapa] != SOLVED.tab[y][etapa])
                return false;
        for (int x = 0; x < tab.length; x++)
            if (tab[etapa][x] != SOLVED.tab[etapa][x])
                return false;
        return true;
    }
    public boolean isEtapaCompleat() {
        return isEtapaCompleat(this.etapa);
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

    public int totalDistance() {
        int sum = 0;
        for (Point p : allPositions())
            if (bloco(p) > 0)
                sum = +blocoDistance(p);
        return sum;
    }

    public int lineDistance(int y) {
        int sum = 0;
        for (Point p : allPositions())
            if (bloco(p) > 0 && SOLVED.position(bloco(p)).y == y)
                sum = +blocoDistance(p);
        return sum;
    }

    public int colDistance(int x) {
        int sum = 0;
        for (Point p : allPositions())
            if (bloco(p) > 0 && SOLVED.position(bloco(p)).x == x)
                sum = +blocoDistance(p);
        return sum;
    }

    public int blocoDistance(Point bloco) {
        return Math.abs(bloco.x - SOLVED.position(bloco(bloco)).x)
                + Math.abs(bloco.y - SOLVED.position(bloco(bloco)).y);
    }

    public int etapaDistance() {
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

    public LinkedList<PseudoTab> escorelSolver(int bloco, Point to) {
        HashMap<PseudoTab, PseudoTab> anterior = new HashMap<PseudoTab, PseudoTab>();
        HashMap<PseudoTab, Integer> depth = new HashMap<PseudoTab, Integer>();
        final HashMap<PseudoTab, Integer> score = new HashMap<PseudoTab, Integer>();

        Comparator<PseudoTab> comparator = new Comparator<PseudoTab>() {
            @Override
            public int compare(PseudoTab a, PseudoTab b) {
                return (score.get(a) - score.get(b));
            }
        };
        PriorityQueue<PseudoTab> proximo = new PriorityQueue<PseudoTab>(10000, comparator);

        anterior.put(this, null);
        depth.put(this, 0);
        if (bloco != 0)
            score.put(this, etapaDistance() + (position(bloco).distanceTo(to) * tab.length));
        proximo.add(this);
        int i = 0;
        while (proximo.size() > 0) {
            PseudoTab candidate = proximo.remove();
            i++;
            currentStatus(i, proximo.size(), candidate);
            if (candidate.isEtapaCompleat() || candidate.position(bloco).distanceTo(to) == 0) {
                System.out.printf("Previsão de movimento completa considerando %d tabuleiros\n", i);
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
                    int estimate = fp.etapaDistance() + (position(bloco).distanceTo(to) * tab.length);
                    score.put(fp, depth.get(candidate) + estimate);
                    // dont' add to p-queue until the metadata is in place that the comparator needs
                    proximo.add(fp);
                }
            }
        }
        return null;
    }

    public LinkedList<PseudoTab> aStarSolve() {

        HashMap<PseudoTab, PseudoTab> anterior = new HashMap<PseudoTab, PseudoTab>();
        HashMap<PseudoTab, Integer> depth = new HashMap<PseudoTab, Integer>();
        final HashMap<PseudoTab, Integer> score = new HashMap<PseudoTab, Integer>();

        Comparator<PseudoTab> comparator = new Comparator<PseudoTab>() {
            @Override
            public int compare(PseudoTab a, PseudoTab b) {
                return (score.get(a) - score.get(b));
            }
        };
        PriorityQueue<PseudoTab> proximos = new PriorityQueue<PseudoTab>(10000, comparator);

        anterior.put(this, null);
        depth.put(this, 0);
        score.put(this, totalDistance());
        proximos.add(this);
        int i = 0;
        while (proximos.size() > 0) {
            PseudoTab candidate = proximos.remove();
            i++;
            currentStatus(i, proximos.size(), candidate);
            if (candidate.isSolved()) {
                System.out.printf("Resolvido considerando %d tabuleiros\n", i);
                candidate.show();
                return backTrak(candidate, anterior);
            }
            for (PseudoTab fp : candidate.sucessores()) {
                if (!anterior.containsKey(fp)) {
                    anterior.put(fp, candidate);
                    depth.put(fp, depth.get(candidate) + 1);
                    int estimate = fp.totalDistance();
                    score.put(fp, depth.get(candidate) + 1 + estimate);
                    proximos.add(fp);
                }
            }
        }
        return null;
    }

    private void currentStatus(int status, int fila, PseudoTab candidate) {
        if (status % 10000 == 0)
            if (status % 30000 == 0) {
                candidate.show();
                System.out.printf("Calculando... %,d tabuleiros. Fila = %,d\n", status, fila);
            } else
                System.out.printf("Calculando... %,d tabuleiros. Fila = %,d\n", status, fila);
    }

    private LinkedList<PseudoTab> backTrak(PseudoTab last, HashMap<PseudoTab, PseudoTab> anteriores) {
        LinkedList<PseudoTab> solution = new LinkedList<PseudoTab>();
        PseudoTab backtrace = last;
        while (backtrace != null) {
            solution.addFirst(backtrace);
            backtrace = anteriores.get(backtrace);
        }
        return solution;
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

    public boolean isInPositons(int[] blocos, Point[] positions, int y) {

        for (int i = 0; i < blocos.length; i++) {
            if (i < blocos.length - 2 && this.position(blocos[i])
                    .distanceTo(positions == null ? PseudoTab.SOLVED.position(blocos[i]) : positions[i]) != 0)
                return false;
            else if (i > blocos.length - 2 && i < blocos.length - 1 && this.position(blocos[i])
                    .distanceTo(positions == null ? new Point(blocos.length - 1, y + 1) : positions[i]) != 0)
                return false;
            else if (i > blocos.length - 2 && i < blocos.length && this.position(blocos[i])
                    .distanceTo(positions == null ? new Point(blocos.length - 1, y + 2) : positions[i]) != 0)
                return false;
        }
        return true;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

}