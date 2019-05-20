package br.com.poli.puzzleN.frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import br.com.poli.puzzleN.engine.Puzzle;


public class PuzzleFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel fundo;
    private JPanel tela;
    private Puzzle partida;
    public void setFundo(JPanel fundo) {
        this.fundo = fundo;
    }
    public JPanel getFundo(){
        return this.fundo;
    }
    public PuzzleFrame() {
        
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 620, 450);
        setLocationRelativeTo(null);
        
        this.tela = new Menu( partida, this);
        fundo = new JPanel();
        setContentPane(fundo);
        fundo.setLayout(new GridLayout(1, 0, 0, 0));
        fundo.add(tela);
        tela.setBackground(new Color(175, 238, 238));
        tela.setLayout(null);
        
    }
        
	public JPanel getTela(){
        return tela;
    }

    public void setTela(JPanel novaTela) {
        tela = novaTela;
    }

    public void setPartida(Puzzle partida){
        this.partida = partida;
    }
}