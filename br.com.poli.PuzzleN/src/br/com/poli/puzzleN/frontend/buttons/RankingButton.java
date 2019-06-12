package br.com.poli.puzzleN.frontend.buttons;
import br.com.poli.puzzleN.frontend.screens.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class RankingButton extends JButton implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private PuzzleFrame frame;
    public RankingButton(PuzzleFrame frame) {
        super("Ranking");
        this.frame = frame;
        this.setBounds(360, 255, 90, 23);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent a) {
        frame.updateTela(new RankingScreen(frame));

    }
}