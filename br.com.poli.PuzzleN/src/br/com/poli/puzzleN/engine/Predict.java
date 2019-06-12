package br.com.poli.puzzleN.engine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class Predict implements Comparable<Predict> {
    private ArrayList<P> tabuleiro;
    private Point move;
    private int k;
    private int etapa;
    private int peso;

    public Predict(int[][] tab) {
        tabuleiro = new ArrayList<P>(tab.length * tab.length);
        for (int i = 0; i < (tab.length * tab.length); i++)
            tabuleiro.add(null);
        for (int y = 0; y < tab.length; y++)
            for (int x = 0; x < tab.length; x++)
                tabuleiro.set(tab[y][x], new P(x, y));
        k = tab.length;
        peso = totalDistance();
        etapa = 0;
        print();
    }

    public Predict(Predict pai) {
        tabuleiro = new ArrayList<P>();
        for (Point filho : pai.tabuleiro) {
            this.tabuleiro.add(new P(filho));
        }
        this.k = pai.k;
        peso = pai.peso + 1;
        this.etapa = pai.etapa;
    }

    private ArrayList<P> valideMoves() {
        ArrayList<P> valideMoves = new ArrayList<P>();
        for (P p : tabuleiro) {
            if (isValidMove(p))
                valideMoves.add(p);
        }
        return valideMoves;
    }

    public ArrayList<Predict> nextMoves() {
        ArrayList<Predict> moves = new ArrayList<Predict>();
        for (P p : valideMoves()) {
            Predict novo = new Predict(this);
            if (novo.move(p))
                moves.add(novo);
        }
        return moves;
    }

    public int totalDistance() {
        int dt = 0;
        for (int y = 0; y < k; y++)
            for (int x = 0; x < k; x++)
                if ((y * k) + x != 0) {
                    int dx = Math.abs(x - tabuleiro.get((y * k) + x).x);
                    int dy = Math.abs(y - tabuleiro.get((y * k) + x).y);
                    dt = +(dx + dy);
                }
        return dt;
    }

    public int distanceTo(int num, Point to) {
        return tabuleiro.get(num).distanceTo(to);
    }

    public int distance(Point num) {
        int dx = Math.abs(num.x - (tabuleiro.indexOf(num) % k));
        int dy = Math.abs(num.y - (tabuleiro.indexOf(num) / k));
        return (dx + dy);
    }

    public boolean isValidMove(Point p) {
        if ((p.x < 0) || (p.x >= k)) {
            return false;
        }
        if ((p.y < 0) || (p.y >= k)) {
            return false;
        }
        int dx = tabuleiro.get(0).x - p.x;
        int dy = tabuleiro.get(0).y - p.y;
        if ((Math.abs(dx) + Math.abs(dy) != 1) || (dx * dy != 0)) {
            return false;
        }
        return true;
    }

    private boolean move(P p) {
        if (isValidMove(p))
            for (P d : tabuleiro)
                if (d.equals(p)) {
                    d.trade(tabuleiro.get(0));
                    move = new Point(p.x, p.y);
                    return true;
                }
        return false;
    }

    private boolean move(String direcion) {
        Point zero = tabuleiro.get(0);
        P num;
        switch (direcion) {
        case "cima":
            num = new P(zero.x, zero.y - 1);
            break;
        case "baixo":
            num = new P(zero.x, zero.y + 1);
            break;
        case "esquerda":
            num = new P(zero.x - 1, zero.y);
            break;
        case "direita":
            num = new P(zero.x + 1, zero.y);
            break;
        default:
            return false;
        }
        if (!blockList().contains(num))
            return move(num);
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Predict) {
            for (int i = 0; i < this.tabuleiro.size(); i++)
                if (!this.tabuleiro.get(i).equals(((Predict) o).tabuleiro.get(i)))
                    return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int out = 0;
        for (int i = 0; i < tabuleiro.size(); i++)
            out = (out * tabuleiro.size()) + i;
        return out;
    }

    private boolean moveZeroTo(Point to) {
        String dy = (tabuleiro.get(0).y - to.y) > 0 ? "baixo" : "cima";
        String dx = (tabuleiro.get(0).x - to.x) > 0 ? "direita" : "esquerda";
        for (int i = 0; tabuleiro.get(0).y != to.y && i <= k; i++) {
            if (!move(dy))
                if (!move(dx))
                    if (!move((tabuleiro.get(0).x - to.x) < 0 ? "direita" : "esquerda"))
                        break;
            print();
        }
        for (int i = 0; tabuleiro.get(0).x != to.x && i <= k; i++) {
            if (!move(dx))
                if (!move(dy))
                    if (!move((tabuleiro.get(0).y - to.y) < 0 ? "baixo" : "cima"))
                        break;
            print();
        }
        return tabuleiro.get(0).equals(to);
    }

    private ArrayList<Point> blockList() {
        ArrayList<Point> blockedToMove = new ArrayList<Point>();
        for (Point p : tabuleiro) {
            if (tabuleiro.indexOf(p) <= 0)
                continue;
            if (distance(p) == 0 && (p.x | p.y) == etapa)
                blockedToMove.add(p);
        }
        return blockedToMove;
    }

    private int estimateError() {
        return tabuleiro.size() - blockList().size() - 1;
    }

    public LinkedList<Point> wayTo(int num, Point to) {
        PriorityQueue<Predict> q = new PriorityQueue<>();
        Map<Predict, Integer> dist = new HashMap<>();
        dist.put(this, 0);
        q.add(this);
        Predict atual = this;
        if (moveZeroTo(atual.tabuleiro.get(num)))
            while (!q.isEmpty()) {

                atual = q.poll();

                if (atual.distanceTo(num, to) == 0)
                    break;

                for (Predict child : atual.nextMoves()) {

                    if (dist.getOrDefault(child, Integer.MAX_VALUE) > child.peso) {
                        dist.put(child, child.tabuleiro.get(num).distanceTo(to));
                        q.add(child);
                    }

                }
            }

        return null;
    }

    private boolean isSolve() {
        for (int y = 0; y < tabuleiro.size(); y++)
            for (int x = 0; x < tabuleiro.size(); x++)
                if ((y & x) != 0)
                    if (tabuleiro.get((y * k) + x).distanceTo(new Point()) != 0)
                        return false;

        return true;
    }

    public LinkedList<Point> getSteps() {

        HashMap<Predict, Predict> anterior = new HashMap<Predict, Predict>();
        HashMap<Predict, Integer> depth = new HashMap<Predict, Integer>();
        final HashMap<Predict, Integer> score = new HashMap<Predict, Integer>();

        Comparator<Predict> comparator = new Comparator<Predict>() {
            @Override
            public int compare(Predict a, Predict b) {
                return score.get(a) - score.get(b);
            }
        };
        PriorityQueue<Predict> proximo = new PriorityQueue<Predict>(100000, comparator);

        anterior.put(this, null);
        depth.put(this, 0);
        score.put(this, this.estimateError());
        proximo.add(this);
        int cnt = 0;
        while (proximo.size() > 0) {
            Predict candidate = proximo.remove();
            cnt++;
            if (cnt % 20000 == 0) {
                System.out.printf("Considered %,d positions. Queue = %,d\n", cnt, proximo.size());
            }
            if (candidate.isSolve()) {
                System.out.printf("Solution considered %d boards\n", cnt);
                LinkedList<Point> solution = new LinkedList<Point>();
                Predict backtrace = candidate;
                while (backtrace != null) {
                    solution.addFirst(backtrace.move);
                    backtrace = anterior.get(backtrace);
                }
                return solution;
            }
            for (Predict fp : candidate.nextMoves()) {
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

    private void print() {

        for (int y = 0; y < k; y++) {
            System.out.print("\t");
            for (int x = 0; x < k; x++) {
                int valor = tabuleiro.indexOf(new Point(x, y));
                if (valor == 0)
                    System.out.print("   ");
                else
                    System.out.printf(" %02d", valor);
            }
            System.out.print("\n");
        }
        System.out.println("\n");
    }

    @Override
    public int compareTo(Predict o) {
        return this.peso - o.peso;
    }
}