package br.com.poli.puzzleN.engine;

public class Testes {

	private void littleTab(Tabuleiro tab) {
		for (byte i = 0; i < 3; i++) { // gera um pequeno tabuleiro 3X3 ja� ordenado.
			for (byte j = 0; j < 3; j++) {
				//tab.getGrid()[i][j] = new Bloco((i * 3) + j + 1);
			}
		}
		tab.getGrid()[2][2] = new Bloco(0);
	}

	private void showTab(Tabuleiro tab) {
		for (int i = 0; i < 3; i++) {
			System.out.printf("\t   ");
			for (int j = 0; j < 3; j++) {
				System.out.printf(" %d", tab.getGrid()[i][j].getValor());
			}
			System.out.printf("\n");
		}
	}

	public void testeClass_Puzzle() {

		Puzzle teste = new Puzzle("nome", Dificuldade.MEDIO);

		System.out.println("\nCheck Classe Puzzle:\n");
		System.out.println("\tCheck dificuldade:");
		System.out.println("\t  antes: " + teste.getDificuldade());
		teste.setDificuldade(Dificuldade.FACIL);
		System.out.println("\t  depois: " + teste.getDificuldade());

		System.out.println("\n\tCheck score(get/set):");
//		System.out.println("\t  antes: " + teste.getScore());
//		teste.setScore(10000);
//		System.out.println("\t  depos: " + teste.getScore());

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

		teste.getTabuleiro().geraTabuleiro(teste.getDificuldade());
		System.out.print("\n\tCheck isFimDeJogo, metodo.: ");
		System.out.println(teste.isFimDeJogo());

		System.out.println("\n\tCheck iniciaPartida, metodo.:");
		//teste.iniciaPartida(Dificuldade.DIFICIL);
		System.out.println("\t   Check quantidade de movimentos: " + teste.getQuantidadeMovimentos());
		System.out.println("\t   Check dificuldade: " + teste.getDificuldade());
//		System.out.println("\t   Check score: " + teste.getScore());
		System.out.println("\t   Check venceu: " + teste.getVenceu());
		System.out.println("\t   Check tempo: " + teste.getTempo() + "\n");

	}

	public void testeClass_Tabuleiro() {
		Tabuleiro teste = new Tabuleiro(Dificuldade.FACIL);

		System.out.println("\nCheck Classe Tabuleiro:");

		System.out.println("\n\tCheck isTabuleiroOrdenado, metodo:\n");
		teste.geraTabuleiro(Dificuldade.FACIL);
		//littleTab(teste);
		showTab(teste);
		System.out.println("\t   antes: " + teste.isTabuleiroOrdenado(Dificuldade.FACIL));
		teste.getGrid()[1][1].setValor(9);
		showTab(teste);
		System.out.println("\t   depois: " + teste.isTabuleiroOrdenado(Dificuldade.FACIL));

		System.out.println("\n\tCheck executaMovimento, metodo:\n");
		littleTab(teste);
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
		teste.setNome("Leticia");
		System.out.println("\t   depois: " + teste.getNome());
	}
}
