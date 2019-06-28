package br.com.poli.puzzleN.frontend.buttons;

import br.com.poli.puzzleN.frontend.screens.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
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

    public void actionPerformed(ActionEvent a) {
        frame.updateTela(new RankingScreen(frame));

    }
}