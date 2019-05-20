package br.com.poli.puzzleN.frontend;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.puzzles.*;
import javax.swing.JButton;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private Puzzle partida;
	private JButton startButton;

	public Menu(PuzzleFrame frame) {

		super();
		this.partida = frame.getPartida();
		JLabel lblPuzzleN = new JLabel("PUZZLE-N");
		lblPuzzleN.setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 63));
		lblPuzzleN.setBounds(245, 59, 330, 56);
		lblPuzzleN.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuzzleN.setForeground(Color.WHITE);
		this.add(lblPuzzleN);

		JLabel lblNOME = new JLabel("NOME");
		lblNOME.setBounds(382, 130, 46, 14);
		lblNOME.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblNOME);

		textField = new JTextField("Digite seu nome...");
		textField.setBounds(330, 150, 150, 20);
		this.add(textField);
		textField.setColumns(20);
		textField.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNivel = new JLabel("NIVEL");
		lblNivel.setBounds(381, 186, 48, 14);
		lblNivel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblNivel);
		final JComboBox dificuldade = new JComboBox();
		dificuldade.setModel(new DefaultComboBoxModel(new String[] { "selecione", "facil", "medio ", "dificil", "INSANO"}));
		dificuldade.setBounds(335, 195 + 10, 140, 20);
		dificuldade.addActionListener(new SelectedDificult(frame));
		dificuldade.setAlignmentX(SwingConstants.CENTER);
		this.add(dificuldade);

		JButton ranking = new JButton("ranking");
		ranking.setBounds(360, 255, 89, 23);
		ranking.setHorizontalAlignment(SwingConstants.CENTER);
		ranking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				if (a.getSource() == ranking)
					frame.updateTela(new RankingScreen(frame, "save1.dat"));

			}
		});
		this.add(ranking);

		startButton = new JButton("iniciar");
		startButton.setBounds(360, 303, 89, 23);
		startButton.addActionListener(new StartGame(frame));
		startButton.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(startButton);

		JLabel backGround = new BackGround();
		this.add(backGround);

	}

	private class SelectedDificult implements ActionListener {
		private PuzzleFrame frame;

		public SelectedDificult(PuzzleFrame in) {
			frame = in;
		}

		public void actionPerformed(ActionEvent e) {

			switch (((JComboBox) e.getSource()).getSelectedIndex()) {
			case 1:
				frame.setPartida(new PuzzleFacil(textField.getText()));
				break;
			case 2:
				frame.setPartida(new PuzzleMedio(textField.getText()));
				break;
			case 3:
				frame.setPartida(new PuzzleDificil(textField.getText()));
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
				frame.updateTela(new Game(frame));
				partida.setTempo(Calendar.getInstance());
			}
		}
	}
}