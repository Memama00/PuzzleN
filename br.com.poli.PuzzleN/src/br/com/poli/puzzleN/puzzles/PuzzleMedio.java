package br.com.poli.puzzleN.puzzles;

import java.util.LinkedList;

import br.com.poli.puzzleN.Interfaces.CalculaMedio;
import br.com.poli.puzzleN.engine.*;
import br.com.poli.puzzleN.exceptions.TempoExcedido;

public class PuzzleMedio extends Puzzle {

	private static final long serialVersionUID = 1L;

	public PuzzleMedio(String nome) {
		super(nome, Dificuldade.MEDIO);
		super.setScore(new CalculaMedio(this));
	}

	@Override
	public void resolveTabuleiro() throws TempoExcedido {
		PseudoTab way = this.getTabuleiro().getPseudoTabuleiro();
		int k = this.getTabuleiro().getGrid().length;
		if (k - way.getEtapa() <= 3) {
			LinkedList<P> solution;
			System.out.println(way.getEtapa());
			solution = way.aStarSolve();
			solution.poll();
			for (P move : solution)
				this.autoPress(move);
		} else {
			
		}
	}
}
