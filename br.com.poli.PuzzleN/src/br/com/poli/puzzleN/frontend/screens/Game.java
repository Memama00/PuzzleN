package br.com.poli.puzzleN.frontend.screens;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.JButton;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.frontend.buttons.BlocoButton;
import br.com.poli.puzzleN.frontend.buttons.HelpButton;
import br.com.poli.puzzleN.frontend.buttons.SolverButton;
import br.com.poli.puzzleN.frontend.buttons.SurrenderButton;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	private Puzzle partida;
	private static HashMap <Integer , JButton> tabuleiro;
	public Game(PuzzleFrame frame) {

		this.partida = frame.getPartida();

		this.partida.iniciaPartida();
		this.setLayout(null);
		int k = this.partida.getTabuleiro().getGrid().length;
		Game.tabuleiro = new HashMap<Integer, JButton>();

		JLabel nome = new JLabel("nome: " + this.partida.getJogador().getNome());
		nome.setBounds(30, 500, 10 * (6 + this.partida.getJogador().getNome().length()), 20);
		nome.setForeground(Color.WHITE);
		nome.setHorizontalTextPosition(SwingConstants.LEFT);
		this.add(nome);
		this.add(new HelpButton(frame));
		this.add(new SurrenderButton(frame));
		this.add(new SolverButton(frame));

		for (int y = 0; y < k; y++) {
			for (int x = 0; x < k; x++) {
				if (partida.getTabuleiro().getGrid()[y][x].getValor() == 0)
					continue;
				JButton bloco = new BlocoButton(frame, x, y);
				this.add(bloco);
				tabuleiro.put(Integer.decode(bloco.getText()), bloco);
			}
		}
		this.add(new BackGround());
	}

	public static HashMap<Integer, JButton> getTabuleiro() {
		return tabuleiro;
	}
}