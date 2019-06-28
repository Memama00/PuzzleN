package br.com.poli.puzzleN.puzzles;

import java.util.LinkedList;

import br.com.poli.puzzleN.Interfaces.CalculaDificil;
import br.com.poli.puzzleN.engine.Dificuldade;
import br.com.poli.puzzleN.engine.P;
import br.com.poli.puzzleN.engine.PseudoTab;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.exceptions.TempoExcedido;

public class PuzzleDificil extends Puzzle {

	private static final long serialVersionUID = 1L;

	public PuzzleDificil(String nome) {
		super(nome, Dificuldade.DIFICIL);
		super.setScore(new CalculaDificil(this));
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
