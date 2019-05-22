package br.com.poli.puzzleN.Interfaces;

import java.io.Serializable;
import br.com.poli.puzzleN.engine.Puzzle;

public class CalculaInsano implements CalculaScore, Serializable {
	private static final long serialVersionUID = 204L;
	private int pontos;

	public CalculaInsano(Puzzle partida) {
		pontos = partida.getTabuleiro().getGrid().length * 1000000;
	}

	public int getPontos() {
		return pontos;
	}

	public int pontos(Puzzle partida) {

		int time = (int) partida.getTempoDecorrido() * 60;
		int moves = partida.getQuantidadeMovimentos();

		if (partida.getVenceu())
			pontos /= (time * 10) + moves;
		else
			pontos = 0;
		return pontos;
	}
}
