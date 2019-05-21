package br.com.poli.puzzleN.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import br.com.poli.puzzleN.engine.Puzzle;

public class SurrenderButton extends JButton {

	private static final long serialVersionUID = 1L;

	public SurrenderButton(Puzzle partida, PuzzleFrame frame) {
		super("Desistir");
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == SurrenderButton.this) {
					frame.updateTela(new End(partida, frame));
				}
			}
		});
	}
}