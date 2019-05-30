package br.com.poli.puzzleN.frontend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
public class SurrenderButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private PuzzleFrame frame;
	public SurrenderButton(PuzzleFrame frame) {
		super("Desistir");
		this.frame = frame;
		this.setBounds(700, 500, 90, 30);
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() != BlocoButton.class) {
			frame.getPartida().setVenceu(false);
			frame.getPartida().setFinalTime();
			frame.getPartida().getScore().pontos(frame.getPartida());
			frame.updateTela(new InfoGame(frame));

		}
	}

}