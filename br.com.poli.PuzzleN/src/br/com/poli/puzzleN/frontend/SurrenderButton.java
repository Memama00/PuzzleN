package br.com.poli.puzzleN.frontend;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.poli.puzzleN.engine.Puzzle;

public class SurrenderButton extends JButton {

	private static final long serialVersionUID = 1L;

	public SurrenderButton(Puzzle partida, PuzzleFrame frame) {
		super("Desistir");
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setTela(new Menu(partida, frame));
				frame.setContentPane(new JPanel());
				frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
				frame.getContentPane().add(frame.getTela());
				frame.getTela().setBackground(new Color(175, 238, 238));
				frame.getTela().setLayout(null);
				frame.setVisible(true);
			}
		});
	}
}