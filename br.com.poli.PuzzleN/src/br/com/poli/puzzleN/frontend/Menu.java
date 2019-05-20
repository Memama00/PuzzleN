package br.com.poli.puzzleN.frontend;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.puzzles.PuzzleDificil;
import br.com.poli.puzzleN.puzzles.PuzzleFacil;
import br.com.poli.puzzleN.puzzles.PuzzleMedio;

import javax.swing.JButton;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private Puzzle partida;
	private JButton startButton;

	public Menu(Puzzle partida, PuzzleFrame frame) {

		super();
		this.partida = partida;
		JLabel lblPuzzleN = new JLabel("PUZZLE N");
		lblPuzzleN.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 56));
		lblPuzzleN.setBounds(171, 59, 278, 56);
		lblPuzzleN.setHorizontalTextPosition(SwingConstants.CENTER);
		this.add(lblPuzzleN);

		JLabel lblNOME = new JLabel("NOME");
		lblNOME.setBounds(287, 130, 46, 14);
		lblNOME.setHorizontalTextPosition(SwingConstants.CENTER);
		this.add(lblNOME);

		textField = new JTextField("Digite seu nome...");
		textField.setBounds(235, 150, 150, 20);
		this.add(textField);
		textField.setColumns(20);

		JLabel lblNivel = new JLabel("NIVEL");
		lblNivel.setBounds(286, 186, 48, 14);
		lblNivel.setHorizontalTextPosition(SwingConstants.CENTER);
		this.add(lblNivel);

		final JComboBox dificuldade = new JComboBox();
		dificuldade.setModel(new DefaultComboBoxModel(new String[] { "selecione", "facil", "medio ", "dificil" }));
		dificuldade.setBounds(240, 195 + 10, 140, 20);
		dificuldade.addActionListener(new SelectedDificult());
		this.add(dificuldade);

		JButton btnRanking = new JButton("ranking");
		btnRanking.setBounds(265, 255, 89, 23);
		this.add(btnRanking);

		startButton = new JButton("iniciar");
		startButton.setBounds(265, 303, 89, 23);
		startButton.addActionListener(new StartGame(frame));
		this.add(startButton);

	}

	private class SelectedDificult implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			switch (((JComboBox) e.getSource()).getSelectedIndex()) {
			case 1:
				partida = new PuzzleFacil(textField.getText());
				break;
			case 2:
				partida = new PuzzleMedio(textField.getText());
				break;
			case 3:
				partida = new PuzzleDificil(textField.getText());
				break;
			default:
			}
		}
	}

	private class StartGame implements ActionListener {
		PuzzleFrame frame;

		public StartGame(PuzzleFrame frame) {
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent a) {

			if (a.getSource() == startButton) {
				//partida = new PuzzleFacil(textField.getText());
				frame.setTela(new Game(partida, frame));
				frame.setContentPane(new JPanel());
				frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
				frame.getContentPane().add(frame.getTela());
				frame.getTela().setBackground(new Color(175, 238, 238));
				frame.getTela().setLayout(null);
				frame.setVisible(true);
			}
		}
	}
}