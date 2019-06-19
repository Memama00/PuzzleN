package br.com.poli.puzzleN.frontend.buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import br.com.poli.puzzleN.engine.P;
import br.com.poli.puzzleN.engine.PseudoTab;
import br.com.poli.puzzleN.frontend.screens.Game;
import br.com.poli.puzzleN.frontend.screens.PuzzleFrame;

public class HelpButton extends JButton {

    private static final long serialVersionUID = 1L;
    private int i;

    public HelpButton(PuzzleFrame frame) {
        super("Help!");
        this.setBounds(700, 440, 90, 30);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        i = 1;
        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                PseudoTab way = frame.getPartida().getTabuleiro().getPseudoTabuleiro();
                if (Game.getTabuleiro().getOrDefault(i, null) == null)
                    i = 1;
                P objetivo = PseudoTab.SOLVED.position(i);

                for (; way.position(i).equals(objetivo); i++)
                    objetivo = PseudoTab.SOLVED.position(i);

                if (!way.position(i).equals(objetivo))
                    frame.getPartida().autoZeroMove(way.position(i));
                frame.getPartida().executarMovimentoAuto(i, objetivo);

            }
        });
    }
}