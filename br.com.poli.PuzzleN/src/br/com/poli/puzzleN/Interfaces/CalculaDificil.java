package br.com.poli.puzzleN.Interfaces;

import java.io.Serializable;
import br.com.poli.puzzleN.engine.Puzzle;

public class CalculaDificil implements CalculaScore, Serializable {
	private static final long serialVersionUID = 203L;
	private int pontos;

	public CalculaDificil(Puzzle partida) {
		pontos = 500000000;
	}

	public int getPontos() {
		return pontos;
	}

	public int pontos(Puzzle partida) {

		float time = partida.getTempoDecorrido() * 60;
		int moves = partida.getQuantidadeMovimentos();

		if (partida.getVenceu())
			pontos /= (time * 10) + moves;
		else
			pontos = 0;
		return pontos;
	}
}
