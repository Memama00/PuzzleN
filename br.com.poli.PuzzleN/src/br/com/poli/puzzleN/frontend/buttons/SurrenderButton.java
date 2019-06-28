package br.com.poli.puzzleN.frontend.buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import br.com.poli.puzzleN.frontend.screens.*;

public class SurrenderButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private PuzzleFrame frame;

	public SurrenderButton(PuzzleFrame frame) {
		super("Desistir");
		this.frame = frame;
		this.setBounds(frame.getWidth() - 110, frame.getHeight() - 80, 90, 30);
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() != BlocoButton.class)
			surrender();
	}

	private void surrender() {
		frame.getPartida().setVenceu(false);
		frame.getPartida().setFinalTime();
		frame.getPartida().getScore().pontos(frame.getPartida());
		frame.updateTela(new InfoGame(frame));
		if (HelpButton.getMover() != null)
			HelpButton.getMover().stop();
		if (SolverButton.getMover() != null)
			SolverButton.getMover().stop();
	}
}