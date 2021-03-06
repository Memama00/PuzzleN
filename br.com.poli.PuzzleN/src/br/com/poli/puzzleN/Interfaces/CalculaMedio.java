package br.com.poli.puzzleN.Interfaces;

import java.io.Serializable;
import br.com.poli.puzzleN.engine.Puzzle;

public class CalculaMedio implements CalculaScore, Serializable {
	private static final long serialVersionUID = 202L;
	private int pontos;

	public CalculaMedio(Puzzle partida) {
		pontos = 4000000;
	}

	public int getPontos() {
		return pontos;
	}

	public int pontos(Puzzle partida) {

		int time = (int) partida.getTempoDecorrido() * 60;
		int moves = partida.getQuantidadeMovimentos();

		if (partida.getVenceu())
			pontos /= (time + moves);
		else
			pontos = 0;
		return pontos;
	}
}
