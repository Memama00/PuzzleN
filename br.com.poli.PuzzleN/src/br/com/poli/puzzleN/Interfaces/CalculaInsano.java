package br.com.poli.puzzleN.Interfaces;

import java.util.Calendar;

import br.com.poli.puzzleN.engine.Puzzle;

public class CalculaInsano implements CalculaScore{
	
	public int pontos(Puzzle partida) {
		long time = partida.getTempo(Calendar.getInstance());
		int moves = partida.getQuantidadeMovimentos();
		return (int) (partida.getTabuleiro().getGrid().length * 100000) / ((int)time / 10) - moves;
	}
}
