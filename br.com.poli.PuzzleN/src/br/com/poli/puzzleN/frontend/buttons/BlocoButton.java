package br.com.poli.puzzleN.frontend.buttons;

import br.com.poli.puzzleN.frontend.screens.*;
import br.com.poli.puzzleN.puzzles.PuzzleInsano;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.engine.Ranking;
import br.com.poli.puzzleN.exceptions.MovimentoInvalido;

public class BlocoButton extends JButton implements ActionListener {

    private static final long serialVersionUID = 1L;
    int xButton;
    int yButton;
    private PuzzleFrame frame;
    private Puzzle partida;

    public BlocoButton(int numero) {
        super(Integer.toString(numero));
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
    }

    public BlocoButton(PuzzleFrame frame, int x, int y) {

        super(Integer.toString(frame.getPartida().getTabuleiro().getGrid()[y][x].getValor()));

        this.xButton = x;
        this.yButton = y;
        this.frame = frame;
        this.partida = frame.getPartida();

        float middleX = (float) (frame.getSize().getWidth() / 2);
        float middleY = (float) (frame.getSize().getHeight() / 2);
        int blocoSize = (partida.getClass() == PuzzleInsano.class ? (int) (frame.getHeight() / 1.27) : 350)
                / partida.getTabuleiro().getGrid().length;
        int tab_size = partida.getTabuleiro().getGrid().length * blocoSize;
        float startX = middleX - (tab_size / 2);
        float startY = middleY - (tab_size / 2) - 30;
        this.setBounds((int) (startX + (blocoSize * x)), (int) (startY + (blocoSize * y)), blocoSize, blocoSize);
        this.setFont(new Font(this.getFont().getName(), Font.BOLD, (int) (blocoSize / 4.3)));
        this.addActionListener(this);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            moveButton();
            int k = partida.getTabuleiro().getGrid().length - 1;
            if (partida.getTabuleiro().getGrid()[k][k].getValor() == 0)
                if (partida.isFimDeJogo()) {
                    partida.setFinalTime();
                    // calcula e salva os pontos logo após o movimento para maior precisão
                    partida.getScore().pontos(partida);
                    if (!Ranking.save(partida))
                        JOptionPane.showMessageDialog(frame,
                                "Ops! Ocorreu um erro ao tentar finalizar a seu puzzle.\n Tente jogar novamente!");
                    else
                        frame.updateTela(new InfoGame(frame));
                }
        }

    }

    public void moveButton() {
        String sentido = "null";
        try {
            sentido = partida.smartMove(xButton, yButton);
            switch (sentido) {
            case "cima":
                this.setLocation(this.getX(), this.getY() - this.getWidth());
                this.setYButton(this.getYButton() - 1);
                break;
            case "baixo":
                this.setLocation(this.getX(), this.getY() + this.getWidth());
                this.setYButton(this.getYButton() + 1);
                break;
            case "direita":
                this.setLocation(this.getX() + this.getHeight(), this.getY());
                this.setXButton(this.getXButton() + 1);
                break;
            case "esquerda":
                this.setLocation(this.getX() - this.getHeight(), this.getY());
                this.setXButton(this.getXButton() - 1);
                break;
            default:
                throw new MovimentoInvalido();
            }
        } catch (MovimentoInvalido e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
        System.out.println("Selected:");
        System.out.println("X:" + this.getX() + "/[x]:" + xButton);
        System.out.println("Y:" + this.getY() + "/[Y]:" + yButton);
        System.out.println("Moves: " + partida.getQuantidadeMovimentos());
        System.out.println("sentido: " + sentido);
        partida.getTabuleiro().print();

        this.repaint();
        this.revalidate();
        frame.getTela().repaint();
        frame.getTela().revalidate();
        frame.repaint();
        frame.revalidate();
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

}