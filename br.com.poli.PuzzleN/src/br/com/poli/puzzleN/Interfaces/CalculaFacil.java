package br.com.poli.puzzleN.Interfaces;

import java.io.Serializable;
import java.util.Calendar;

import br.com.poli.puzzleN.engine.Puzzle;

public class CalculaFacil implements CalculaScore , Serializable{
	private int pontos;

	public CalculaFacil(Puzzle partida) {
		pontos = partida.getTabuleiro().getGrid().length * 100000;
	}

	public int getPontos() {
		return pontos;
	}

	public int pontos(Puzzle partida) {
		long time = partida.getTempo(Calendar.getInstance());
		int moves = partida.getQuantidadeMovimentos();
		if (time > 2)
			pontos /= ((int) time / 10) + moves;
		else
			pontos = 0;
		return partida.getVenceu() ? pontos : 0;
	}
}
