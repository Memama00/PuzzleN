package br.com.poli.puzzleN.testes;

import java.util.Scanner;

import br.com.poli.puzzleN.engine.Ranking;
import br.com.poli.puzzleN.puzzles.*;

public class Main {
	public static void main(String args[]) {
	(new PuzzleFacil("nome")).iniciaPartida();
		//	try {
			// Testes.testeClass_Tabuleiro();
			// Testes.testeClass_Bloco();
			// Testes.testeClass_Jogador();
			// Testes.testePuzzle(new PuzzleFacil("FACIL"));
	// 		Testes.testePuzzle(new PuzzleMedio("MEDIO"));
	// 		Testes.testePuzzle(new PuzzleDificil("DIFICIL"));
			
	// 		System.out.println("Jogar Insano? r: sim/nao");
	// 		Scanner read = new Scanner(System.in);
			
	// 		switch (read.nextLine()) {

	// 		case "sim":
	// 			System.out.flush();
	// 			System.out.println("A partida dura por no maximo 600 movimentos!");
	// 			System.out.println("Digite seu nome:");
	// 			Testes.start(read);

	// 			for (int i = 0; i < 600 && !Testes.partida.getVenceu(); i++) {
	// 				Testes.mover(read);
	// 			}

	// 			if (Testes.partida.getVenceu()) {
	// 				System.out.println("Voce venceu!");
	// 				System.out.println("Salvar? r: sim/nao");
	// 				switch (read.nextLine()) {
	// 				case "sim":
	// 					// System.out.flush();
	// 					new Ranking();
	// 					Ranking.save(Testes.partida);
	// 					System.out.println("Ranking visivel no jogo padrão!");
	// 					read.close();
	// 					break;
	// 				default:
	// 					read.close();
	// 				}
	// 			} else
	// 				System.out.println("Voce perdeu!");
	// 			System.out
	// 					.println("tmepo de partida:" + Float.toString(Testes.partida.getTempoDecorrido()) + " min(s)");
	// 			read.close();
	// 			break;

	// 		default:
	// 			read.close();
	// 		}

	// 	} catch (Exception e) {
	// 		System.out.println(e.getLocalizedMessage());
	// 	}
	 }
}
