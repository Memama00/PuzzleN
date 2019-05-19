package br.com.poli.puzzleN.frontend;

import javax.swing.JButton;
import br.com.poli.puzzleN.engine.*;
import java.awt.event.*;

public class BlocoButton extends JButton {

    private static final long serialVersionUID = 1L;
    int xButton;
    int yButton;
    BlocoButton me;

    public BlocoButton(int numero) {
        super(Integer.toString(numero));
        me = this;
    }

    public BlocoButton(Puzzle partida, int x, int y) {
        super(Integer.toString(partida.getTabuleiro().getGrid()[y][x].getValor()));
        xButton = x;
        yButton = y;
        me = this;
        this.setBounds(131 + (x * 50), 47 + (y * 50), 50, 50);
        this.addActionListener(new PressBlock(partida));
    }

    public void setNumero(int in) {
        super.setName(Integer.toString(in));
    }

    public int getXButton() {
        return this.xButton;
    }

    public int getYButton() {
        return this.yButton;
    }

    public void setXButton(int in) {
        this.xButton = in;
    }

    public void setYButton(int in) {
        this.yButton = in;
    }

    private class PressBlock implements ActionListener {
        Puzzle partida;

        public PressBlock(Puzzle partida) {
            this.partida = partida;
        }

        public void actionPerformed(ActionEvent e) {
            this.moveButton(e.getSource());
            showTab(partida.getTabuleiro());
        }

        private void moveButton(Object in) {
            if (in == me) {
                try {
                    String sentido = partida.smartMove(xButton, yButton);
                    System.out.println("Selected:");
                    System.out.println("X:" + me.getX() + "/[x]:" + xButton);
                    System.out.println("Y:" + me.getY() + "/[Y]:" + yButton);
                    System.out.println("sentido:" + sentido);
                    switch (sentido) {
                    case "cima":
                        me.setLocation(me.getX(), me.getY() - 50);
                        me.setYButton(me.getYButton() - 1);
                        break;
                    case "baixo":
                        me.setLocation(me.getX(), me.getY() + 50);
                        me.setYButton(me.getYButton() + 1);
                        break;
                    case "direita":
                        me.setLocation(me.getX() + 50, me.getY());
                        me.setXButton(me.getXButton() + 1);
                        break;
                    case "esquerda":
                        me.setLocation(me.getX() - 50, me.getY());
                        me.setXButton(me.getXButton() - 1);
                        break;
                    default:
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
                System.out.println("Moves: " + partida.getQuantidadeMovimentos());
            }
        }

        private void showTab(Tabuleiro tab) {
            for (int i = 0; i < tab.getGrid().length; i++) {
                System.out.printf("\t   ");
                for (int j = 0; j < tab.getGrid().length; j++) {
                    System.out.printf(" %02d", tab.getGrid()[i][j].getValor());
                }
                System.out.printf("\n\n");
            }
            System.out.printf("\n\n");
        }
    }
}