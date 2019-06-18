package br.com.poli.puzzleN.engine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import br.com.poli.puzzleN.Interfaces.Solver;

public class PseudoTab {

    private int[][] tab;
    private int espaco;
    private P zero;
    private int etapa;
    public P move;
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
        zero = new P(tab.length - 1, tab.length - 1);
        tab[zero.y][zero.x] = 0;
        etapa = tab.length - 3;
    }

    public PseudoTab(int[][] tab) {
        this.tab = tab;
        int i = 1;
        for (int y = 0; y < tab.length; y++) {
            for (int x = 0; x < tab.length; x++) {
                if (tab[y][x] == 0)
                    zero = new P(x, y);
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
        for (P p : allPositions()) {
            tab[p.y][p.x] = toClone.bloco(p);
        }
        zero = toClone.getZero();
        etapa = toClone.etapa;
        updateEtapa();
    }

    private int[][] cloneTab() {
        int[][] clone = new int[tab.length][tab.length];
        for (int y = 0; y < tab.length; y++)
            for (int x = 0; x < tab.length; x++)
                clone[y][x] = tab[y][x];
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PseudoTab) {
            for (P p : allPositions())
                if (this.bloco(p) != ((PseudoTab) o).bloco(p))
                    return false;

            return true;
        }
        return false;
    }

    public boolean isValidMove(P p) {
        if ((p.x < etapa) || (p.x >= tab.length) || (p.x == etapa) && isCollCompleat(p.x)) {
            return false;
        }
        if ((p.y < etapa) || (p.y >= tab.length) || (p.y == etapa) && isLineCompleat(p.y)) {
            return false;
        }
        int dx = zero.x - p.x;
        int dy = zero.y - p.y;
        if ((Math.abs(dx) + Math.abs(dy) != 1) || (dx * dy != 0)) {
            return false;
        }
        return true;
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
    public boolean isLineCompleat(int y){
        for (int i = 0; i < tab.length; i++)
            if(tab[y][i] != SOLVED.tab[y][i])
                return false;
        return true;
    }
    public boolean isCollCompleat(int x){
        for (int i = 0; i < tab.length; i++)
            if(tab[i][x] != SOLVED.tab[i][x])
                return false;
        return true;
    }   
    public boolean isEtapaCompleat() {
        return isEtapaCompleat(this.etapa);
    }

    public boolean isSolved() {
        return foraDoLugar() == 0;
    }

    public boolean isInPositons(int[] blocos, P[] positions) {
        return isInPositons(blocos, positions, 0);
    }

    public boolean isInPositons(int[] blocos, P[] positions, int index) {

        for (int i = index; i < blocos.length; i++) {
            if (i < blocos.length - 2 && this.position(blocos[i])
                    .equals(positions == null ? PseudoTab.SOLVED.position(blocos[i]) : positions[i]))
                return false;
            else if (i > blocos.length - 2 && i < blocos.length - 1
                    && this.position(blocos[i])
                            .equals(positions == null
                                    ? new P(blocos.length - 1, PseudoTab.SOLVED.position(blocos[i]).y + 1)
                                    : positions[i]))
                return false;
            else if (i > blocos.length - 2 && i < blocos.length
                    && this.position(blocos[i])
                            .equals(positions == null
                                    ? new P(blocos.length - 1, PseudoTab.SOLVED.position(blocos[i]).y + 2)
                                    : positions[i]))
                return false;
        }
        return true;
    }

    public List<P> allPositions() {
        ArrayList<P> out = new ArrayList<P>();
        for (int i = 0; i < tab.length; i++)
            for (int j = 0; j < tab.length; j++)
                out.add(new P(i, j));
        return out;
    }

    public int bloco(P p) {
        return tab[p.y][p.x];
    }

    public P getZero() {
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

    public List<P> validMoves() {
        ArrayList<P> out = new ArrayList<P>();
        for (P tp : allPositions())
            if (isValidMove(tp))
                out.add(tp);
        return out;
    }

    public void move(P p) {
        if (!isValidMove(p)) {
            throw new RuntimeException("Invalid move");
        }
        assert tab[zero.y][zero.x] == 0;
        tab[zero.y][zero.x] = tab[p.y][p.x];
        tab[p.y][p.x] = 0;
        zero = p;
        this.move = p;
    }

    public PseudoTab moveClone(P p) {
        PseudoTab out = new PseudoTab(this);
        out.move(p);
        return out;
    }

    @Override
    public int hashCode() {
        int out = 0;
        for (P p : allPositions()) {
            out = (out * tab.length * tab.length) + this.bloco(p);
        }
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

    public int totalDistance(PseudoTab progenitor) {
        int sum = 0;
        for (P p : allPositions())
            if (bloco(p) > 0)
                sum = +p.distanceTo(progenitor.position(bloco(p)));
        return sum;
    }

    public int totalDistance() {
        return totalDistance(PseudoTab.SOLVED);
    }

    public int blocoDistance(P bloco) {
        return bloco.distanceTo(SOLVED.position(bloco(bloco)));
    }

    public int lineDistance(int y) {
        int sum = 0;
        for (P p : allPositions())
            if (bloco(p) > 0 && SOLVED.position(bloco(p)).y == y)
                sum = +blocoDistance(p);
        return sum;
    }

    public int collDistance(int x) {
        int sum = 0;
        for (P p : allPositions())
            if (bloco(p) > 0 && SOLVED.position(bloco(p)).x == x)
                sum = +blocoDistance(p);
        return sum;
    }

    public int etapaDistance() {
        return lineDistance(etapa) + collDistance(etapa);
    }

    public int estimateError() {
        return this.foraDoLugar();
    }

    public List<PseudoTab> sucessores() {
        ArrayList<PseudoTab> out = new ArrayList<PseudoTab>();
        for (P move : validMoves()) {
            out.add(moveClone(move));
        }
        return out;
    }

    public void updateEtapa() {
        if (tab.length - etapa <= 3)
            return;
        if (isEtapaCompleat()) {
            etapa++;
            updateEtapa();
        }
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

    public LinkedList<PseudoTab> solver(Solver predict) {
        HashMap<PseudoTab, PseudoTab> anterior = new HashMap<PseudoTab, PseudoTab>();
        HashMap<PseudoTab, Integer> depth = new HashMap<PseudoTab, Integer>();
        final HashMap<PseudoTab, Integer> score = new HashMap<PseudoTab, Integer>();

        Comparator<PseudoTab> comparator = new Comparator<PseudoTab>() {
            @Override
            public int compare(PseudoTab a, PseudoTab b) {
                return (score.get(a) - score.get(b));
            }
        };
        PriorityQueue<PseudoTab> proximos = new PriorityQueue<PseudoTab>(1000000, comparator);

        anterior.put(this, null);
        depth.put(this, 0);
        score.put(this, predict.distance(this));
        proximos.add(this);
        int i = 0;
        while (proximos.size() > 0) {
            PseudoTab candidate = proximos.remove();
            i++;
            currentStatus(i, proximos.size(), candidate);
            if (predict.isEnd(candidate)) {
                System.out.printf("Resolvido considerando %d tabuleiros\n", i);
                candidate.show();
                return backTrak(candidate, anterior);
            }
            for (PseudoTab fp : candidate.sucessores()) {
                if (!anterior.containsKey(fp)) {
                    anterior.put(fp, candidate);
                    depth.put(fp, depth.get(candidate) + 1);
                    int estimate = predict.distance(fp);
                    score.put(fp, depth.get(candidate) + (tab.length * tab.length) + estimate);
                    proximos.add(fp);
                }
            }
        }
        return null;
    }

    public LinkedList<PseudoTab> aStarSolve() {
        return solver(new Solver() {

            @Override
            public boolean isEnd(Object o) {
                return ((PseudoTab) o).isSolved();
            }

            @Override
            public int distance(Object o) {
                return ((PseudoTab) o).totalDistance() + ((PseudoTab) o).estimateError();
            }
        });
    }

    public LinkedList<PseudoTab> pointWay(int bloco, P to) {
        return solver(new Solver() {

            @Override
            public boolean isEnd(Object o) {
                return ((PseudoTab) o).isEtapaCompleat() || ((PseudoTab) o).isSolved()
                        || ((PseudoTab) o).position(bloco).equals(to);
            }

            @Override
            public int distance(Object o) {
                return (((PseudoTab) o).position(bloco).distanceTo(to) * (tab.length * tab.length))
                        + (((PseudoTab) o).etapaDistance() * (tab.length - 1))
                        + (((PseudoTab) o).totalDistance(PseudoTab.this));
            }
        });
    }

    public LinkedList<PseudoTab> ordernLine(int y) {
        return solver(new Solver() {

            public boolean isEnd(Object o) {
                return ((PseudoTab) o).isEtapaCompleat() || ((PseudoTab) o).isSolved()
                        || ((PseudoTab) o).isLineCompleat(y);
            }

            @Override
            public int distance(Object o) {
                return (((PseudoTab) o).lineDistance(y) * tab.length * 2)
                        + (((PseudoTab) o).etapaDistance() * (tab.length - 1));
            }
        });
    }
    public LinkedList<PseudoTab> ordernColl(int x) {
        return solver(new Solver() {

            public boolean isEnd(Object o) {
                return ((PseudoTab) o).isEtapaCompleat() || ((PseudoTab) o).isSolved()
                        || ((PseudoTab) o).isCollCompleat(x);
            }

            @Override
            public int distance(Object o) {
                return (((PseudoTab) o).collDistance(x) * tab.length * 2)
                        + (((PseudoTab) o).etapaDistance() * (tab.length - 1));
            }
        });
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

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

}