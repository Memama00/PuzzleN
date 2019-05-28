package br.com.poli.puzzleN.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MuteButton extends JButton implements ActionListener {
    private static final long serialVersionUID = 1L;

    public MuteButton() {
        super();
        this.setBounds(40, 480, 32, 32);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.addActionListener(this);

    }

    public void actionPerformed(ActionEvent arg0) {
        SondTrack.altern();
    }
}