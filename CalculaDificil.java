package br.com.poli.puzzleN.engine;

public class CalculaDificil implements CalculaScore{
	
	public int pontos(Puzzle partida) {
		long time = partida.getTempo();
		int moves = partida.getQuantidadeMovimentos();
		return (int) 1000000000/(int)time - moves;// 1 Bi
	}
}
