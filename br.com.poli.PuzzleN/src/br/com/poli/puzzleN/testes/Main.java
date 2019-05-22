package br.com.poli.puzzleN.testes;

import java.util.Scanner;

import br.com.poli.puzzleN.engine.Ranking;
import br.com.poli.puzzleN.puzzles.*;

public class Main {

	private static class Jogar {
		static private PuzzleInsano partida;
		

		public static void start(String nome) {
			System.out.flush();
			Jogar.partida = new PuzzleInsano(nome);
		Testes.showTab(partida.getTabuleiro());
		}

		public static void mover(Scanner in) {
			System.out.flush();
		Testes.showTab(partida.getTabuleiro());
			System.out.println("Mover - digite: 'x'do bloco\n 'y' do bloco \n 'sentido'\n");
			partida.getTabuleiro().executaMovimento(in.nextInt(), in.nextInt(), in.nextLine());
			in.close();
		}
	}

	public static void main(String args[]) {

		

	Testes.testeClass_Tabuleiro();
	Testes.testeClass_Bloco();
	Testes.testeClass_Jogador();

	Testes.testePuzzle(new PuzzleFacil("FACIL"));
	Testes.testePuzzle(new PuzzleMedio("MEDIO"));
	Testes.testePuzzle(new PuzzleDificil("DIFICIL"));
		System.out.println("\nleia tudo antes de por um valor invalido no insano ^^\n");
	Testes.testePuzzle(new PuzzleInsano("INSANO"));// leia tudo antes de por um valor invalido no insano ^^.
		System.out.println("Jogar Insano? r: sim/nao");
		Scanner read = new Scanner(System.in);

		switch (read.nextLine()) {

		case "sim":
			System.out.flush();
			System.out.println("A partida dura por no maximo 600 movimentos!");
			System.out.println("Digite seu nome:");
			Jogar.start(read.nextLine());

			for (int i = 0; i < 600 || !Jogar.partida.isFimDeJogo(); i++) {
				Jogar.mover(read);
			}

			if (Jogar.partida.getVenceu()) {
				
				System.out.println("Voce venceu!");
				System.out.println("Salvar? r: sim/nao");

				switch (read.nextLine()) {
				case "sim":

					System.out.flush();
					read.close();

					new Ranking();
					Ranking.save(Jogar.partida);
					System.out.println("Ranking visivel no jogo padrão!");
				
					break;
				default:
					read.close();
				}
			} else
				System.out.println("Voce perdeu!");
			read.close();
			break;

		default:
			read.close();
		}

	}
}
