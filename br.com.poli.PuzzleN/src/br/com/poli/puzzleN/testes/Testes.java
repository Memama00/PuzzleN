package br.com.poli.puzzleN.testes;

import br.com.poli.puzzleN.engine.*;
import br.com.poli.puzzleN.puzzles.*;
import java.util.Calendar;
import br.com.poli.puzzleN.Interfaces.CalculaFacil;
import br.com.poli.puzzleN.Interfaces.CalculaDificil;



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
		
		System.out.print("\nCheck Classe Puzzle" + teste.getDificuldade() + ":\n\n");
		System.out.println("\n\tCheck iniciaPartida, metodo.:");
		teste.iniciaPartida();
		System.out.println("\t   Check quantidade de movimentos: " + teste.getQuantidadeMovimentos());
		System.out.println("\t   Check dificuldade: " + teste.getDificuldade());
		if(teste instanceof PuzzleInsano)
			System.out.println("\t   Check tamanho: " + ((PuzzleInsano)teste).getTamanho());
		System.out.println("\t   Check score: " + teste.getScore().pontos(teste));
		System.out.println("\t   Check venceu: " + teste.getVenceu());
		System.out.println("\t   Check tempo: " + teste.getTempo(Calendar.getInstance()) + "\n\n");
		
		System.out.println("\t  Tabuleiro:\n");
		showTab(teste.getTabuleiro());
		
		System.out.println("\tCheck dificuldade:");
		System.out.println("\t  antes: " + teste.getDificuldade());
		teste.setDificuldade((teste instanceof PuzzleDificil)? Dificuldade.FACIL : Dificuldade.DIFICIL);
		System.out.println("\t  depois: " + teste.getDificuldade());

		System.out.println("\n\tCheck score(get/set):");
		System.out.println("\t  antes: " + teste.getScore());
		teste.setScore((teste instanceof PuzzleDificil)? new CalculaFacil(teste) : new CalculaDificil(teste));
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
			System.out.println("\t  antes: " + ((PuzzleInsano)teste).getTamanho());
			((PuzzleInsano)teste).setTamanho(10);
			System.out.println("\t  depos: " + ((PuzzleInsano)teste).getTamanho());
		}
		
		teste.getTabuleiro().geraTabuleiro(teste.getDificuldade().getValor());
		System.out.print("\n\tCheck isFimDeJogo, metodo.: ");
		System.out.println(teste.isFimDeJogo());
	
	}

	public void testeClass_Tabuleiro() {
		Tabuleiro teste = new Tabuleiro(Dificuldade.FACIL.getValor());

		System.out.println("\nCheck Classe Tabuleiro:");

		System.out.println("\n\tCheck isTabuleiroOrdenado, metodo:\n");
		teste.geraTabuleiro((int)Math.sqrt(Dificuldade.FACIL.getValor()+1));
		showTab(teste);
		System.out.println("\t   antes: " + teste.isTabuleiroOrdenado(Dificuldade.FACIL));
		teste.getGrid()[1][1].setValor(9);
		showTab(teste);
		System.out.println("\t   depois: " + teste.isTabuleiroOrdenado(Dificuldade.FACIL));

		System.out.println("\n\tCheck executaMovimento, metodo:\n");
		teste.geraTabuleiro((int)Math.sqrt(Dificuldade.FACIL.getValor()+1));
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
		teste.setNome("eu não aguento mais estudar ;-;");
		System.out.println("\t   depois: " + teste.getNome());
	}
	
}
