package br.com.poli.puzzleN.frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.engine.Tabuleiro;
public class BlocoButton extends JButton {

    private static final long serialVersionUID = 1L;
    int xButton;
    int yButton;

    public BlocoButton(int numero) {
        super(Integer.toString(numero));
        this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
    }

    public BlocoButton(Puzzle partida, PuzzleFrame frame, int x, int y) {
        super(Integer.toString(partida.getTabuleiro().getGrid()[y][x].getValor()));
        xButton = x;
        yButton = y;
        float middleX = (float) (frame.getSize().getWidth()/ 2);
        float middleY = (float) (frame.getSize().getHeight()/ 2);
        int blocoSize = 300 /partida.getTabuleiro().getGrid().length;
        int tab_size = partida.getTabuleiro().getGrid().length * blocoSize;
        float startX = middleX - (tab_size / 2);
        float startY = middleY - (tab_size / 2) - 30;
        this.setBounds((int) (startX + (blocoSize * x)), (int) (startY + (blocoSize * y)), blocoSize, blocoSize);
        this.setFont(new Font(this.getFont().getName(),Font.BOLD, blocoSize/5));
        this.addActionListener(new PressBlock(partida, frame));
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
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
        PuzzleFrame frame;
        public PressBlock(Puzzle partida, PuzzleFrame frame) {
            this.partida = partida;
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            this.moveButton(e.getSource());
            showTab(partida.getTabuleiro());
            int k = partida.getTabuleiro().getGrid().length - 1;
            if (partida.getTabuleiro().getGrid()[k][k].getValor() == 0)
                if (partida.isFimDeJogo())
                    frame.updateTela(new End(frame));
        }

        private void moveButton(Object in) {
            if (in == BlocoButton.this) {
                try {
                    String sentido = partida.smartMove(xButton, yButton);
                    System.out.println("Selected:");
                    System.out.println("X:" + BlocoButton.this.getX() + "/[x]:" + xButton);
                    System.out.println("Y:" + BlocoButton.this.getY() + "/[Y]:" + yButton);
                    System.out.println("sentido:" + sentido);
                    switch (sentido) {
                    case "cima":
                        BlocoButton.this.setLocation(BlocoButton.this.getX(), BlocoButton.this.getY() - BlocoButton.this.getWidth());
                        BlocoButton.this.setYButton(BlocoButton.this.getYButton() - 1);
                        break;
                    case "baixo":
                        BlocoButton.this.setLocation(BlocoButton.this.getX(), BlocoButton.this.getY() + BlocoButton.this.getWidth());
                        BlocoButton.this.setYButton(BlocoButton.this.getYButton() + 1);
                        break;
                    case "direita":
                        BlocoButton.this.setLocation(BlocoButton.this.getX() + BlocoButton.this.getHeight(), BlocoButton.this.getY());
                        BlocoButton.this.setXButton(BlocoButton.this.getXButton() + 1);
                        break;
                    case "esquerda":
                        BlocoButton.this.setLocation(BlocoButton.this.getX() - BlocoButton.this.getHeight(), BlocoButton.this.getY());
                        BlocoButton.this.setXButton(BlocoButton.this.getXButton() - 1);
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