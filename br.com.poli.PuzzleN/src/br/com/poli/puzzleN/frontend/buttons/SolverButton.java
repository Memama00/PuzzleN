package br.com.poli.puzzleN.frontend.buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import br.com.poli.puzzleN.frontend.screens.PuzzleFrame;

public class SolverButton extends JButton {

    private static final long serialVersionUID = 1L;

    public SolverButton(PuzzleFrame frame) {
        super("Solve!");
        this.setBounds(700, 470, 90, 30);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                    frame.getPartida().resolveTabuleiro();
                    frame.setVisible(true);
            }
        });
    }
}