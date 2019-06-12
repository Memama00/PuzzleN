package br.com.poli.puzzleN.engine;

import java.awt.Point;
import br.com.poli.puzzleN.Interfaces.Pergunta;
import br.com.poli.puzzleN.testes.Testes;

public class NaTora {
    private Puzzle p;

    public NaTora(Puzzle p) {
        this.p = p;
    }

    public void show() {
        Testes.showTab(p.getTabuleiro());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
    }

    public void cicle(Point ponta, float times) {
        final Point zero = p.getZero();
        int largura = Math.abs(ponta.x - zero.x);
        int altura = Math.abs(ponta.y - zero.y);
        for (int i = 0; i < times; i++)
            try {
                int y = 0;
                do {
                    p.bubbleMoveZero(2);
                    show();
                    y++;
                } while (y < (altura * times));

                int x = 0;
                do {
                    p.bubbleMoveZero(4);
                    show();
                    x++;
                } while (x < (largura * times));

                y = 0;
                do {
                    p.bubbleMoveZero(1);
                    show();
                    y++;
                } while (y < (altura * times));

                x = 0;
                do {
                    p.bubbleMoveZero(3);
                    show();
                    x++;
                } while (x < (largura * times));

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    public void cicleWhile(Point ponta, Pergunta ateQ) {
        while (ateQ.condicao()) {
            final Point zero = p.getZero();
            int largura = Math.abs(ponta.x - zero.x);
            int altura = Math.abs(ponta.y - zero.y);
            try {
                int y = 0;
                do {
                    if (!p.bubbleMoveZero(1))
                        p.bubbleMoveZero(2);
                    show();
                    y++;
                } while (y <= altura && ateQ.condicao());

                int x = 0;
                do {
                    if (!p.bubbleMoveZero(3))
                        p.bubbleMoveZero(4);
                    show();
                    x++;
                } while (x <= largura && ateQ.condicao());

                y = 0;
                do {
                    if (!p.bubbleMoveZero(2))
                        p.bubbleMoveZero(1);
                    show();
                    y++;
                } while (y <= altura && ateQ.condicao());

                x = 0;
                do {
                    if (!p.bubbleMoveZero(4))
                        p.bubbleMoveZero(3);
                    show();
                    x++;
                } while (x <= largura && ateQ.condicao());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void cicleZeroToPoint(Point p) {
        Point zero = this.p.getZero();
        Pergunta missao = new Pergunta() {

            public boolean condicao() {
                return (zero.x != p.x || zero.y != p.y);
            }
        };
        cicleWhile(p, missao);
    }

    public void cicleToPoint(int ho, Point p) {
        Pergunta missao = new Pergunta() {
            Point quem = getPoisiton(ho);

            public boolean condicao() {
                quem = getPoisiton(ho);
                return quem.x != p.x || quem.y != p.y;
            }
        };
        // moveZeroTo(new Point(g.x - 1, zero.y));
        // moveZeroTo(new Point(g.x, g.y - 1));
        cicleWhile(p, missao);
    }

    private Point getPoisiton(int num) {
        Bloco[][] tab = p.getTabuleiro().getGrid();
        for (int y = 0; y < tab.length; y++) {
            for (int x = 0; x < tab.length; x++) {
                if (tab[y][x].getValor() == num)
                    return new Point(x, y);
            }
        }
        return null;
    }

    private boolean moveZeroTo(Point to) {
        boolean check = true;
        final Point zero = p.getZero();
        while (zero.y != to.y) {
            if (!p.bubbleMoveZero((zero.y - to.y) < 0 ? 2 : 1)) {
                check = false;
                break;
            }
            show();
        }
        while (zero.x != to.x) {
            if (!p.bubbleMoveZero((zero.x - to.x) > 0 ? 4 : 3)) {
                check = false;
                break;
            }
            show();
        }
        return check;
    }

    private void alline(int ho, int to) {
        Point blc = getPoisiton(ho);
        int dx = (blc.x - to) < 0 ? 1 : -1;
        while (blc.x != to) {
            moveZeroTo(new Point(blc.x + dx, blc.y + 1));
            moveZeroTo(new Point(blc.x, blc.y));
            blc = getPoisiton(ho);
        }

    }

    public boolean fillLine(int y) {
        int k = p.getTabuleiro().getGrid().length;
        Point alvo;
        for (int x = 1; x <= k - 2; x++) {
            alvo = getPoisiton((y * k) + x);
            if ((alvo.x != x || alvo.y != y)) {
                alline((y * k) + x, x - 1);
                alvo = getPoisiton((y * k) + x);
                cicleToPoint((y * k) + x, new Point(x - 1, y));
                moveZeroTo(new Point(x, y + 1));
            }
        }
        return false;
    }

    

}