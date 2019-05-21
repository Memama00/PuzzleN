package br.com.poli.puzzleN.frontend;

<<<<<<< HEAD
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

>>>>>>> master
import br.com.poli.puzzleN.engine.Puzzle;

public class SurrenderButton extends JButton {

	private static final long serialVersionUID = 1L;

	public SurrenderButton(Puzzle partida, PuzzleFrame frame) {
		super("Desistir");
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
				if (e.getSource() != BlocoButton.class) {
					frame.updateTela(new End( frame));
=======
				if (e.getSource() == SurrenderButton.this) {
					frame.updateTela(new End(partida, frame));
>>>>>>> master
				}
			}
		});
	}
}