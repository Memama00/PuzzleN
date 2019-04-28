package br.com.poli.puzzleN.engine;

public class CalculaMedio implements CalculaScore{
	
	public int pontos(Puzzle partida) {
		long time = partida.getTempo();
		int moves = partida.getQuantidadeMovimentos();
		return (int) 100000000/(((int)time) - moves);//100Mi
	}
}
