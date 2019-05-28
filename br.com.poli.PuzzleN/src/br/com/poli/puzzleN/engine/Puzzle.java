package br.com.poli.puzzleN.engine;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import br.com.poli.puzzleN.Interfaces.CalculaScore;
import br.com.poli.puzzleN.exceptions.*;

public class Puzzle implements Serializable, Comparable<Puzzle> {

	private static final long serialVersionUID = 0104L;
	private Jogador jogador;
	private Tabuleiro gridPuzzle;
	private int quantidadeMovimentos;
	private CalculaScore score;
	private boolean venceu;
	private Calendar tempo;
	private Calendar finalTime;
	private Dificuldade dificuldade;

	public Puzzle(String nome, Dificuldade dificuldade) {
		jogador = new Jogador(nome);
		this.dificuldade = dificuldade;
		quantidadeMovimentos = 0;
		venceu = false;
		tempo = Calendar.getInstance();
		tempo.setTime(new Date());
		gridPuzzle = new Tabuleiro((int) Math.sqrt(dificuldade.getValor() + 1));
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Tabuleiro getTabuleiro() {
		return gridPuzzle;
	}

	public int getQuantidadeMovimentos() {
		return quantidadeMovimentos;
	}

	public void setQuantidadeMovimentos(int quantidadeMovimentos) {
		this.quantidadeMovimentos = quantidadeMovimentos;
	}

	public CalculaScore getScore() {
		return score;
	}

	public void setScore(CalculaScore score) {
		this.score = score;
	}

	public boolean getVenceu() {
		return venceu;
	}

	public void setVenceu(boolean venceu) {
		this.venceu = venceu;
	}
	
	public Calendar getTempo() {
		return tempo;
	}

	public void setTempo(Calendar tempo) {
		this.tempo = tempo;
	}

	public long getTempo(Calendar now) {
		now.setTime(new Date());
		return now.get(Calendar.MILLISECOND) - tempo.get(Calendar.MILLISECOND) ;
	}
	public Calendar getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Calendar finalTime) {
		this.finalTime = finalTime;
	}
	public void setFinalTime() {
		this.finalTime = Calendar.getInstance();
		finalTime.setTime(new Date());
	}
	public float getTempoDecorrido() {
		
		int start = tempo.get(Calendar.SECOND);
		int end = finalTime.get(Calendar.SECOND);
		return (float) ((end - start)/60);
	}

	public boolean isFimDeJogo() {
		venceu = gridPuzzle.isTabuleiroOrdenado(dificuldade);
		return venceu;
	}

	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}

	public Dificuldade getDificuldade() {
		return dificuldade;
	}

	public void iniciaPartida() {
		quantidadeMovimentos = 0;
		venceu = false;
		tempo.setTime(new Date());
		gridPuzzle.geraTabuleiro((int) Math.sqrt(dificuldade.getValor() + 1));
	}

	public void resolveTabuleiro() throws Error {
		this.setTempo(Calendar.getInstance());
		if (this.getTempo(Calendar.getInstance()) > 200.000)
			throw new TempoExcedido();
		// int k = gridPuzzle.getGrid().length;
		// for (int i = 0; i < k; i++)
		// for (int j = 0; j < k; j++) {
		// gridPuzzle.getGrid()[i][j].setValor(((i * k) + j) + 1);
		// }
		// gridPuzzle.getGrid()[k - 1][k - 1].setValor(0);
	}

	private boolean inRange(int in, int min, int max) {
		return (in >= min && in < max);
	}

	public String smartMove(int x, int y) throws MovimentoInvalido {

		String sentido = "null";
		int i, j;
		for (i = -1; i <= 1; i += 2) {
			if (inRange((y + i), 0, gridPuzzle.getGrid().length))
				if (gridPuzzle.getGrid()[y + i][x].getValor() == 0) {
					if (i == -1)
						sentido = "cima";
					else
						sentido = "baixo";
					if (!gridPuzzle.executaMovimento(x, y, sentido))
						throw new MovimentoInvalido();
					quantidadeMovimentos++;
					return sentido;
				}
		}
		for (j = -1; j <= 1; j += 2) {
			if (inRange((x + j), 0, gridPuzzle.getGrid().length))
				if (gridPuzzle.getGrid()[y][x + j].getValor() == 0) {
					if (j == -1)
						sentido = "esquerda";
					else
						sentido = "direita";
					if (!gridPuzzle.executaMovimento(x, y, sentido))
						throw new MovimentoInvalido();
					quantidadeMovimentos++;
					return sentido;
				}

		}
		return sentido;
	}
	/*
	 * *********Vai ser usado para gera o tabuleiro "possive" na etapa 4 ***********
	 * 
	 * private boolean bubbleZeroMove(int move) { int x = 0, y = 0; boolean check =
	 * true; for (y = 0; y < gridPuzzle.getGrid().length &&
	 * gridPuzzle.getGrid()[y][x].getValor() != 0; y++) for (x = 0; x <
	 * gridPuzzle.getGrid().length && gridPuzzle.getGrid()[y][x].getValor() != 0;
	 * x++) try { switch (move) { case 1:// direita gridPuzzle.executaMovimento(x +
	 * 1, y, "esquerda"); break;
	 * 
	 * case 2:// baixo gridPuzzle.executaMovimento(x, y - 1, "cima"); break;
	 * 
	 * case 3:// esquerda gridPuzzle.executaMovimento(x - 1, y, "direita"); break;
	 * 
	 * case 4:// cima gridPuzzle.executaMovimento(x, y + 1, "baixo"); break;
	 * default: check = false; } } catch (Exception e) { check = false;
	 * System.out.println(e.getLocalizedMessage()); }
	 * 
	 * return check;
	 * 
	 * }
	 */

	public int compareTo(Puzzle puzzle) {
		return (this.getScore().getPontos() > puzzle.getScore().getPontos()) ? -1 : 1;
	}

}
