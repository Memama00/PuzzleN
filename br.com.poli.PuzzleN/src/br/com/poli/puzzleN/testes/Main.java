package br.com.poli.puzzleN.testes;

import br.com.poli.puzzleN.engine.Tabuleiro;
import br.com.poli.puzzleN.puzzles.PuzzleInsano;
import br.com.poli.puzzleN.engine.Puzzle;

public class Main {
	private static void showTab(Tabuleiro tab) {
		for (int i = 0; i < tab.getGrid().length; i++) {
			System.out.printf("\t   ");
			for (int j = 0; j < tab.getGrid().length; j++) {
				System.out.printf(" %02d", tab.getGrid()[i][j].getValor());
			}
			System.out.printf("\n\n");
		}
		System.out.printf("\n\n");
	}
	public static void main(String args[]) {
//		Testes t = new Testes();
//		t.testeClass_Puzzle();
//		t.testeClass_Tabuleiro();
//		t.testeClass_Bloco();
//		t.testeClass_Jogador();
		Puzzle teste = new PuzzleInsano("eu");
		teste.iniciaPartida();
		showTab(teste.getTabuleiro());
	}
}
