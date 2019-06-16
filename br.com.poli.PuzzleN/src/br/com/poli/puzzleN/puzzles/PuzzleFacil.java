package br.com.poli.puzzleN.puzzles;

import java.util.LinkedList;

import br.com.poli.puzzleN.Interfaces.CalculaFacil;
import br.com.poli.puzzleN.engine.Dificuldade;
import br.com.poli.puzzleN.engine.PseudoTab;
import br.com.poli.puzzleN.engine.Puzzle;

public class PuzzleFacil extends Puzzle {

	private static final long serialVersionUID = 1L;

	public PuzzleFacil(String nome) {
		super(nome, Dificuldade.FACIL);
		super.setScore(new CalculaFacil(this));
	}

	@Override
	public void resolveTabuleiro() {
		PseudoTab way = new PseudoTab(this.getTabuleiro().gerarPseudoTabuleiro());
		LinkedList<PseudoTab> solution = way.aStarSolve();
		solution.pollFirst();
		for (PseudoTab p : solution)
			this.executarMovimentoAuto(p.move);
	}

}
