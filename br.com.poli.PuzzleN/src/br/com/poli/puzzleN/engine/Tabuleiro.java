package br.com.poli.puzzleN.engine;

import java.io.Serializable;
import java.util.Random;
import br.com.poli.puzzleN.exceptions.MovimentoInvalido;

public class Tabuleiro implements Serializable{
	private static final long serialVersionUID = 0102L;
	private Bloco[][] grid;

	public Tabuleiro(int k) {
		geraTabuleiro(k);
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

	private boolean isMovimentoValido(Bloco a) throws Exception {
		boolean check = (a != null && a.getValido());
		if (!check)
			throw new MovimentoInvalido();
		return check;
	}

	public boolean executaMovimento(int x, int y, String movimento){
		try {
			if (x < 0 || y < 0)
				throw new MovimentoInvalido();

			switch (movimento) {

			case "direita":
				if (isMovimentoValido(grid[y][x + 1])) {
					Trade(grid[y][x], grid[y][x + 1]);
					grid[y][x + 1].setValido(false);
				}
				break;

			case "baixo":

				if (isMovimentoValido(grid[y + 1][x])) {
					Trade(grid[y][x], grid[y + 1][x]);
					grid[y + 1][x].setValido(false);
				}
				break;

			case "esquerda":

				if (isMovimentoValido(grid[y][x - 1])) {
					Trade(grid[y][x], grid[y][x - 1]);
					grid[y][x - 1].setValido(false);
				}
				break;

			case "cima":
				if (isMovimentoValido(grid[y - 1][x])) {
					Trade(grid[y][x], grid[y - 1][x]);
					grid[y - 1][x].setValido(false);
				}
				break;

			default:
				throw new MovimentoInvalido();
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage() + "\n tente um movimento valido");
			return false;
		}

	}

	public boolean isTabuleiroOrdenado(Dificuldade dificuldade) {
		boolean check = false;
		int i, j = 0;
		for (i = 0; i < grid.length; i++) {
			for (j = 0; i != grid.length - 1 ? j < grid.length : j < grid.length - 1; j++) {
				check = (grid[i][j].getValor() != ((i * grid.length) + j + 1));
				if (check)
					break;

			}
			if (check)
				break;
		}
			return !check;
	}

	private boolean fillGrid(int y, int x, int i) {

		boolean check = (grid[y][x] == null);

		if (check)
			grid[y][x] = new Bloco(i);

		return check;
	}

	public void geraTabuleiro(int k) {
		Random r = new Random();
		this.grid = new Bloco[k][k];
		int i = 0;
		while (i <= ((k * k) - 1)) {
			if (fillGrid(r.nextInt(grid.length), r.nextInt(grid.length), i))
				i++;
		}
	}
}
