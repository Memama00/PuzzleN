package br.com.poli.puzzleN.Interfaces;

import java.io.Serializable;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.puzzles.PuzzleInsano;

public class CalculaInsano implements CalculaScore, Serializable {
	private static final long serialVersionUID = 204L;
	private int pontos;

	public CalculaInsano(Puzzle partida) {
		pontos = ((PuzzleInsano)partida).getTamanho();
		for (int i = 0; i < ((PuzzleInsano)partida).getTamanho(); i++)
			pontos *= 10;
	}

	public int getPontos() {
		return pontos;
	}

	public int pontos(Puzzle partida) {

		int time = (int) partida.getTempoDecorrido() * 10;
		int moves = partida.getQuantidadeMovimentos();

		if (partida.getVenceu())
			pontos = (pontos - time) / moves;
		else
			pontos = 0;
		return pontos;
	}
}
