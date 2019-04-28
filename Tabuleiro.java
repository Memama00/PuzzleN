package br.com.poli.puzzleN.engine;

import java.util.Random;

public class Tabuleiro {
	private Bloco[][] grid;

	public Tabuleiro(Dificuldade dificuldade) {
		this.geraTabuleiro(dificuldade);
	}

	public Bloco[][] getGrid() {
		return grid;
	}

	private void Trade(Bloco a, Bloco b) {
		int trade;
		trade = a.getValor();
		a.setValor(b.getValor());
		b.setValor(trade);
	}

	private boolean isMovimentoValido(Bloco a) {
		return (a != null && a.getValido());
	}

	public void executaMovimento(int x, int y, String movimento) {
		try {
			if(x < 0 || y < 0)
				throw new MovimentoInvalido();
			switch (movimento) {
			
			case "direita":
				if (isMovimentoValido(grid[y][x + 1])) {
					Trade(grid[y][x], grid[y][x + 1]);
					grid[y][x + 1].setValido(false);
				} else
					throw new MovimentoInvalido();
				break;

			case "baixo":

				if (isMovimentoValido(grid[y + 1][x])) {
					Trade(grid[y][x], grid[y + 1][x]);
					grid[y + 1][x].setValido(false);
				} else
					throw new MovimentoInvalido();
				break;

			case "esquerda":

				if (isMovimentoValido(grid[y][x - 1])) {
					Trade(grid[y][x], grid[y][x - 1]);
					grid[y][x - 1].setValido(false);
				} else
					throw new MovimentoInvalido();
				break;

			case "cima":
				if (isMovimentoValido(grid[y - 1][x])) {
					Trade(grid[y][x], grid[y - 1][x]);
					grid[y - 1][x].setValido(false);
				} else
					throw new MovimentoInvalido();

				break;

			default:
				return;
			}
		} catch (MovimentoInvalido e) {
			System.out.println(e.getLocalizedMessage() + "\n tente um movimento valido");
		}

	}

	public boolean isTabuleiroOrdenado(Dificuldade dificuldade) {
		boolean check = false;
		int i, j = 0;
		for (i = 0; i < grid.length; i++) {
			for (j = 0; j < grid.length; j++) {
				check = (grid[i][j].getValor() != ((i * grid.length) + j + 1));
				if (check)
					break;

			}
			if (check)
				break;
		}
		return (((i * grid.length) + j) == dificuldade.getValor());
	}

	private boolean check_r(int val_r, int x, int y) {
		boolean check = false;
		/*
		 * 
		 * y é a linha em que os numeros estão sendo escritos e x as colunas, logo, para
		 * evitar null exception à leitura é feita somente até a coluna anterior a que
		 * esta sendo escrita no momento, se a linha for a mesma a ser escria no
		 * momento. Caso contrario será lido até a extensão máxima da linha.
		 *
		 */
		for (byte i = 0; i <= y; i++) {
			for (byte j = 0; (i == y) ? (j < x) : (j < grid.length); j++) {
				check = (val_r == grid[i][j].getValor());
				if (check)
					break;
			}
			if (check)
				break;
		}
		return check;
	}

	public void geraTabuleiro(Dificuldade dificuldade) {
		Random r = new Random();
		byte lateral = (byte) Math.sqrt((double) (dificuldade.getValor() + 1));
		this.grid = new Bloco[lateral][lateral];
		int val_r;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (i == grid.length - 1 && j == grid.length - 1)
					val_r = 0;
				else
					do {
						val_r = r.nextInt(dificuldade.getValor()) + 1;
					} while (check_r(val_r, j, i));
				System.out.println();
				grid[i][j] = new Bloco(val_r);
			}
		}

	}
}
