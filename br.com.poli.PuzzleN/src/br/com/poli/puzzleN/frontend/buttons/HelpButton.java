package br.com.poli.puzzleN.frontend.buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import br.com.poli.puzzleN.engine.PseudoTab;
import br.com.poli.puzzleN.frontend.screens.Loading;
import br.com.poli.puzzleN.frontend.screens.PuzzleFrame;
import br.com.poli.puzzleN.testes.Main;

public class HelpButton extends JButton {

    private static final long serialVersionUID = 1L;
    private boolean permission;
    private Integer bloco;
    private static Thread mover;

    public HelpButton(PuzzleFrame frame) {
        super("Help!");
        this.setBounds(frame.getWidth() - 110, frame.getHeight() - 140, 90, 30);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        permission = true;
        bloco = new Integer(1);

        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.getPartida().getTabuleiro().gerarPseudoTabuleiro();
                if (permission)
                    permission = false;
                else
                    return;
                Main.compareTime = Calendar.getInstance();
                Loading.start();
                mover = new Thread(new Runnable() {
                    public void run() {
                        try {
                            frame.getPartida().executarMovimentoAuto(bloco, true);
                            if (frame.getPartida().getTabuleiro().gerarPseudoTabuleiro().position(bloco - 1)
                                    .equals(PseudoTab.SOLVED.position(bloco - 1)))
                                bloco++;
                        } catch (Error e) {
                            Loading.stop();
                            JOptionPane.showMessageDialog(frame, e.getMessage());
                        }
                        permission = true;
                    }
                }, "moving");
                mover.start();
            }
        });

    }

    public synchronized static Thread getMover() {
        for (int i = 2; i < 22; i = +2)
            if (Thread.currentThread().getStackTrace()[i].getMethodName().equals("surrender"))
                return mover;
        return null;
    }

}