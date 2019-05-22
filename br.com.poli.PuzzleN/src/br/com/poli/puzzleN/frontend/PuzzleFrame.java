package br.com.poli.puzzleN.frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.engine.Ranking;

public class PuzzleFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JPanel fundo;
    private JPanel tela;
    private JPanel lastTela;
    private Puzzle partida;
    private Ranking ranking;

    public void setFundo(JPanel fundo) {
        this.fundo = fundo;
    }

    public JPanel getFundo() {
        return this.fundo;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public PuzzleFrame() {

        super("Puzzle-N :o");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 820, 600);
        setLocationRelativeTo(null);
        ranking = new Ranking();
        //this.partida = new PuzzleFacil("nome");
        this.tela = new Menu(this);
        fundo = new JPanel();
        setContentPane(fundo);
        fundo.setLayout(new GridLayout(1, 0, 0, 0));
        fundo.add(tela);
        tela.setLayout(null);

    }

    public void updateTela(JPanel tela) {
        lastTela = this.tela;
        tela.disable();
        this.setTela(tela);
        this.setContentPane(new JPanel());
        this.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
        this.getContentPane().add(this.getTela());
        this.getTela().setLayout(null);
        this.setVisible(true);
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