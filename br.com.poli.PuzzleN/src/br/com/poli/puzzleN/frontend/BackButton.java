package br.com.poli.puzzleN.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BackButton extends JButton implements ActionListener {
    private static final long serialVersionUID = 1L;

    private PuzzleFrame frame;
    
    public BackButton(PuzzleFrame frame) {
    super("Voltar");
    this.frame = frame;
    this.setBounds(640, 500, 100, 30);
    this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        JPanel lastTela = frame.getLastTela();
        JPanel tela = frame.getTela();

        // if(tela.getClass() == InfoGame.class && lastTela.getClass() == RankingScreen.class)
        //     tela = lastTela;
        if(tela.getClass() == lastTela.getClass() || lastTela.getClass() == Game.class || lastTela.getClass() == InfoGame.class)
            tela = new Menu(frame);
        else 
            tela = lastTela;
        frame.updateTela(tela);
    }
}