package br.com.poli.puzzleN.frontend.buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import br.com.poli.puzzleN.exceptions.TempoExcedido;
import br.com.poli.puzzleN.frontend.screens.Loading;
import br.com.poli.puzzleN.frontend.screens.PuzzleFrame;
import br.com.poli.puzzleN.testes.Main;

public class SolverButton extends JButton {

    private static final long serialVersionUID = 1L;
    private static Thread mover;
    public SolverButton(PuzzleFrame frame) {
        super("Solve!");
        this.setBounds(frame.getWidth() - 110, frame.getHeight() - 110, 90, 30);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Loading.start();
                mover = new Thread(new Runnable() {
                    public void run() {
                        try {
                            Main.compareTime = Calendar.getInstance();
                            Main.compareTime.setTime(new Date());
                            frame.getPartida().resolveTabuleiro();
                        } catch (TempoExcedido i) {
                            Loading.stop();
                            JOptionPane.showMessageDialog(frame, i.getMessage());
                        }
                    }
                }, "moving");
                mover.start();
            }
        });
    }
    public synchronized static Thread getMover() {
        for (int i = 0; i < 20; i = +2)
            if (Thread.currentThread().getStackTrace()[i].getMethodName().equals("surrender"))
                return mover;
        return null;
    }
}