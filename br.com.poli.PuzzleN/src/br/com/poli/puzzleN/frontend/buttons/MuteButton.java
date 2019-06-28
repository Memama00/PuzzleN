package br.com.poli.puzzleN.frontend.buttons;

import br.com.poli.puzzleN.frontend.screens.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        addKeyListener(new KeyListener(){
        
            @Override
            public void keyTyped(KeyEvent arg0) {
                if(arg0.getKeyChar() == '\n')
                    doClick(1);
            }
            public void keyReleased(KeyEvent arg0) {}
            public void keyPressed(KeyEvent arg0) {}
        });
    }

    public void actionPerformed(ActionEvent arg0) {
        SondTrack.altern();
    }
}