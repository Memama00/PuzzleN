package br.com.poli.puzzleN.frontend.screens;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.engine.Ranking;

public class PuzzleFrame extends JFrame implements Runnable {
    private static final long serialVersionUID = 1L;

    private JPanel tela;
    private JPanel lastTela;
    private Puzzle partida;
    private Ranking ranking;

    public PuzzleFrame() {
        super("Puzzle-N *-*");
        new SondTrack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 820, 600);
        setLocationRelativeTo(null);
        ranking = new Ranking();
        this.tela = new Menu(this);
        this.setContentPane(new JPanel());
        this.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
        this.getContentPane().add(this.getTela());
        tela.setLayout(null);
    }

    public void run() {

        SondTrack.play();
        this.setVisible(true);
    
    }

    public void updateTela(JPanel tela) {
        lastTela = this.tela;
        if (tela.getClass() == Menu.class)
            partida = null;
        this.setTela(tela);
        this.setContentPane(new JPanel());
        this.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
        this.getContentPane().add(this.getTela());
        this.getTela().setLayout(null);
        this.setVisible(true);
    }

    public Ranking getRanking() {
        return ranking;
    }

    public JPanel getTela() {
        return tela;
    }

    public void setTela(JPanel novaTela) {
        tela = novaTela;
    }

    public JPanel getLastTela() {
        return lastTela;
    }

    public void setPartida(Puzzle partida) {
        this.partida = partida;
    }

    public Puzzle getPartida() {
        return partida;
    }

}