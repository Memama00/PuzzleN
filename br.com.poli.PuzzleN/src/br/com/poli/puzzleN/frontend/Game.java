package br.com.poli.puzzleN.frontend;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import br.com.poli.puzzleN.puzzles.*;
import br.com.poli.puzzleN.engine.*;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;
	private Puzzle partida;
	private JPanel contentPane;
	private JButton[] tabuleiro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() {
		this.partida = new PuzzleFacil("ronaldo");
		int k = this.partida.getTabuleiro().getGrid().length;
		this.partida.iniciaPartida();
		this.tabuleiro = new JButton[k * k];

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(175, 238, 238));

		JLabel lblNome = new JLabel("nome: " + partida.getJogador().getNome());
		lblNome.setBounds(31, 303, 8 * (6 + partida.getJogador().getNome().length()), 20);
		panel.add(lblNome);

		JButton btnDesistir = new JButton("desistir");
		btnDesistir.setBounds(465, 299, 89, 23);
		panel.add(btnDesistir);

		for (int y = 0; y < k; y++) {
			for (int x = 0; x < k; x++) {
				if (partida.getTabuleiro().getGrid()[y][x].getValor() == 0)
					continue;
				this.tabuleiro[(y * k) + x] = new BlocoButton(partida, x, y);
				panel.add(this.tabuleiro[(y * k) + x]);
			}
		}
	}

}