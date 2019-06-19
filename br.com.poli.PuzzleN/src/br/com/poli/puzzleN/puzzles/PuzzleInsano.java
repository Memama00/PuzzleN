package br.com.poli.puzzleN.puzzles;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import br.com.poli.puzzleN.Interfaces.CalculaInsano;
import br.com.poli.puzzleN.engine.Dificuldade;
import br.com.poli.puzzleN.engine.PseudoTab;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.exceptions.K_Invalido;
import br.com.poli.puzzleN.exceptions.TempoExcedido;

public class PuzzleInsano extends Puzzle {

	private static final long serialVersionUID = 1L;
	private int tamanho;

	public PuzzleInsano(String nome, Scanner k) {
		super(nome, Dificuldade.INSANO);
		super.setScore(new CalculaInsano(this));
		tamanho = defineK(k);
	}

	public int getTamanho() {
		return tamanho;
	}

	/*
	 * public void setTamanho() { tamanho = defineK(); }
	 */
	public void setTamanho(int in) {
		tamanho = in;
	}

	public void iniciaPartida() {
		super.setQuantidadeMovimentos(0);
		super.setVenceu(false);
		getTempo().setTime(new Date());
		getTabuleiro().geraTabuleiro(tamanho);
	}

	private int defineK(Scanner read) {
		int in = 1;
		if (read == null)
			read = new Scanner(System.in);
		try {
			System.out.println("Digite um numero de 6 a 10");
			in = read.nextInt();
			if (in < 5 || in > 10)// Sem numeros negativos dessa vez <3
				throw new K_Invalido();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " O valor foi automaticamente definido como maximo!");
			in = 200;
		}
		return in;
	}

	@Override
	public void resolveTabuleiro() throws TempoExcedido {

		PseudoTab way = new PseudoTab(this.getTabuleiro().gerarPseudoTabuleiro());

		if (tamanho - way.getEtapa() <= 3) {
			LinkedList<PseudoTab> solution;
			System.out.println(way.getEtapa());
			solution = way.aStarSolve();
			solution.poll();
			for (PseudoTab p : solution)
				this.autoPress(p.move);
		} else {
			for (int i = 0; i < tamanho - 3; i++) {
				if (way.lineDistance(i) != 0)
					fillLine(PseudoTab.SOLVED.getTab()[i], null);
				way = getTabuleiro().getPseudoTabuleiro();
			}
			for (int i = 0; i < tamanho - 3; i++) {
				int[] col = new int[3];
				for (int j = 0; j < 3; j++) {
					col[j] = PseudoTab.SOLVED.getTab()[(tamanho - 1) - j][i];
				}
				if (way.collDistance(i) != 0)
					fillColl(col);
			}
		}
	}
}
