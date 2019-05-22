package br.com.poli.puzzleN.frontend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import br.com.poli.puzzleN.engine.Ranking;

public class CleanRankingButton extends JButton implements ActionListener {
    private static final long serialVersionUID = 1L;
    private PuzzleFrame frame;

    public CleanRankingButton(PuzzleFrame frame) {
        super("Limpar");
        this.frame = frame;
        this.setBounds(60, 500, 100, 30);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.RED);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent a) {
        Ranking.getRank().clear();
        Ranking.save();
        frame.updateTela(new RankingScreen(frame));

    }

}