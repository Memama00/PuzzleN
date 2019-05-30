package br.com.poli.puzzleN.frontend;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.engine.Ranking;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	private Puzzle partida;
	private JButton[] tabuleiro;
	public Game(PuzzleFrame frame) {

		this.partida = frame.getPartida();

		this.partida.iniciaPartida();
		this.setLayout(null);
		int k = this.partida.getTabuleiro().getGrid().length;
		this.tabuleiro = new JButton[k * k];

		JLabel nome = new JLabel("nome: " + this.partida.getJogador().getNome());
		nome.setBounds(30, 500, 10 * (6 + this.partida.getJogador().getNome().length()), 20);
		nome.setForeground(Color.WHITE);
		nome.setHorizontalTextPosition(SwingConstants.LEFT);
		this.add(nome);

		this.add(new SurrenderButton(frame));
		/*
		 * O jbutton "save", salva a partida independente de das condições de jogo e
		 * encerra a partida. Tem o objetivo de testar tanto o ranking como a tela de
		 * fim de partida.
		 */
		
		JButton save = new JButton("salvar");
		save.setBounds(700, 470, 90, 30);
		save.setForeground(Color.WHITE);
		save.setBackground(Color.BLACK);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPartida().setFinalTime();
				frame.getPartida().getScore().pontos(partida);//calcula e salva os pontos imediatamente para maior precisão
				Ranking.save(frame.getPartida());
				frame.updateTela(new InfoGame(frame));
			}
		});
		this.add(save);

		for (int y = 0; y < k; y++) {
			for (int x = 0; x < k; x++) {
				if (partida.getTabuleiro().getGrid()[y][x].getValor() == 0)
					continue;
				this.tabuleiro[(y * k) + x] = new BlocoButton(frame, x, y);
				this.add(this.tabuleiro[(y * k) + x]);
			}
		}
		this.add(new BackGround());
	}
}