package br.com.poli.puzzleN.puzzles;

import java.util.LinkedList;

import br.com.poli.puzzleN.Interfaces.CalculaDificil;
import br.com.poli.puzzleN.engine.Dificuldade;
import br.com.poli.puzzleN.engine.PseudoTab;
import br.com.poli.puzzleN.engine.Puzzle;

public class PuzzleDificil extends Puzzle {

	private static final long serialVersionUID = 1L;

	public PuzzleDificil(String nome) {
		super(nome, Dificuldade.DIFICIL);
		super.setScore(new CalculaDificil(this));
	}

	@Override
	public void resolveTabuleiro() {

		PseudoTab way = new PseudoTab(this.getTabuleiro().gerarPseudoTabuleiro());
		int k = this.getTabuleiro().getGrid().length;

		if (k - way.getEtapa() <= 3) {
			LinkedList<PseudoTab> solution;
			System.out.println(way.getEtapa());
			solution = way.aStarSolve();
			solution.poll();
			for (PseudoTab p : solution)
				this.executarMovimentoAuto(p.move);
		} else {
			if (way.lineDistance(way.getEtapa()) != 0)
				fillLine(new int[] {1, 2, 3, 4, 5}, null);
				fillLine(new int[] {6, 7, 8, 9, 10}, null);
			way = getTabuleiro().getPseudoTabuleiro();
			if (way.collDistance(way.getEtapa()) != 0)
				fillColl(new int[] { 21, 16, 11});
				fillColl(new int[] { 22, 17, 12});
			resolveTabuleiro();
		}
	}

}
