package br.com.poli.puzzleN.testes;

import br.com.poli.puzzleN.engine.*;
import br.com.poli.puzzleN.puzzles.*;
import br.com.poli.puzzleN.Interfaces.CalculaFacil;




public class Testes {

	private void showTab(Tabuleiro tab) {
		for (int i = 0; i < tab.getGrid().length; i++) {
			System.out.printf("\t   ");
			for (int j = 0; j < tab.getGrid().length; j++) {
				System.out.printf(" %02d", tab.getGrid()[i][j].getValor());
			}
			System.out.printf("\n\n");
		}
		System.out.printf("\n\n");
	}

	public void testePuzzle(Puzzle teste) {
		
		System.out.println("\nCheck Classe Puzzle:\n");
		testGetSet(teste);
		teste.getTabuleiro().geraTabuleiro(teste.getDificuldade().getValor());
		System.out.print("\n\tCheck isFimDeJogo, metodo.: ");
		System.out.println(teste.isFimDeJogo());

		System.out.println("\n\tCheck iniciaPartida, metodo.:");
		teste.iniciaPartida();
		System.out.println("\t   Check quantidade de movimentos: " + teste.getQuantidadeMovimentos());
		System.out.println("\t   Check dificuldade: " + teste.getDificuldade());
		System.out.println("\t   Check score: " + teste.getScore());
		System.out.println("\t   Check venceu: " + teste.getVenceu());
		System.out.println("\t   Check tempo: " + teste.getTempo() + "\n");
	}

	public void testeClass_Tabuleiro() {
		Tabuleiro teste = new Tabuleiro(Dificuldade.FACIL.getValor());

		System.out.println("\nCheck Classe Tabuleiro:");

		System.out.println("\n\tCheck isTabuleiroOrdenado, metodo:\n");
		teste.geraTabuleiro(Dificuldade.FACIL.getValor());
		showTab(teste);
		System.out.println("\t   antes: " + teste.isTabuleiroOrdenado(Dificuldade.FACIL));
		teste.getGrid()[1][1].setValor(9);
		showTab(teste);
		System.out.println("\t   depois: " + teste.isTabuleiroOrdenado(Dificuldade.FACIL));

		System.out.println("\n\tCheck executaMovimento, metodo:\n");
		teste.geraTabuleiro(Dificuldade.FACIL.getValor());
		System.out.println("\t   antes: ");
		showTab(teste);
		teste.executaMovimento(1, 2, "direita");
		System.out.println("\n\t   depois: ");
		showTab(teste);
		teste.executaMovimento(1, 1, "baixo");
		System.out.println("\n\t   depois: ");
		showTab(teste);
		
		System.out.print("\n\tCheck grid(get):\n");
		System.out.println("\t   antes: " + teste.getGrid()[0][0]);
		System.out.println("\t   depos: " + teste.getGrid()[2][2]);

	}

	public void testeClass_Bloco() {

		System.out.println("\nCheck Class Bloco:\n");
		Bloco teste = new Bloco(55);

		System.out.print("\tCheck valor(get/set):\n");
		System.out.println("\t   antes: " + teste.getValor());
		teste.setValor(15);
		System.out.println("\t   depos: " + teste.getValor());

		System.out.print("\n\tCheck valido(get/set):\n");
		System.out.println("\t   antes: " + teste.getValido());
		teste.setValido(true);
		System.out.println("\t   depos: " + teste.getValido());

	}

	public void testeClass_Jogador() {

		System.out.println("\nCheck Class Jogador:\n");
		Jogador teste = new Jogador("Ricardo");

		System.out.print("\tCheck nome(get/set):\n");
		System.out.println("\t   antes: " + teste.getNome());
		teste.setNome("euNAguentoMaisEstudar ;-;");
		System.out.println("\t   depois: " + teste.getNome());
	}
	
	private void testGetSet(Puzzle teste){

		
		System.out.println("\tCheck dificuldade:");
		System.out.println("\t  antes: " + teste.getDificuldade());
		teste.setDificuldade(Dificuldade.DIFICIL);
		System.out.println("\t  depois: " + teste.getDificuldade());

		System.out.println("\n\tCheck score(get/set):");
		System.out.println("\t  antes: " + teste.getScore());
		teste.setScore(new CalculaFacil(teste));
		System.out.println("\t  depos: " + teste.getScore());

		System.out.println("\n\tCheck venceu(get/set):");
		System.out.println("\t   antes: " + teste.getVenceu());
		teste.setVenceu(true);
		System.out.println("\t   depos: " + teste.getVenceu());

		System.out.println("\n\tCheck quantidadeMovimentos(get/set):");
		System.out.println("\t   antes: " + teste.getQuantidadeMovimentos());
		teste.setQuantidadeMovimentos(100);
		System.out.println("\t   depos: " + teste.getQuantidadeMovimentos());

		System.out.println("\n\tCheck jogador(get/set):");
		System.out.println("\t   antes: " + teste.getJogador().getNome());
		teste.setJogador(new Jogador("alberto"));
		System.out.println("\t   depos: " + teste.getJogador().getNome());
		
		if(teste instanceof PuzzleInsano) {
			System.out.println("\n\tCheck tamanho(get/set):");
			System.out.println("\t  antes: " + ((PuzzleInsano)teste).getTamanho() );
			((PuzzleInsano)teste).setTamanho(10);
			System.out.println("\t  depos: " + ((PuzzleInsano)teste).getTamanho() );
		}

	}
}

