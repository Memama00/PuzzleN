package br.com.poli.puzzleN.puzzles;

import java.util.LinkedList;

import br.com.poli.puzzleN.Interfaces.CalculaMedio;
import br.com.poli.puzzleN.engine.*;

public class PuzzleMedio extends Puzzle {

	private static final long serialVersionUID = 1L;

	public PuzzleMedio(String nome) {
		super(nome, Dificuldade.MEDIO);
		super.setScore(new CalculaMedio(this));
	}

	@Override
	public void resolveTabuleiro() {

		PseudoTab way = new PseudoTab(this.getTabuleiro().gerarPseudoTabuleiro());
		int k = this.getTabuleiro().getGrid().length;
		int selecteds[][] = new int[2][k];
		for (int i = 0; i < k; i++)
			selecteds[0][i] = (way.getEtapa() * k) + i + 1;

		for (int i = 0; i < k; i++)
			selecteds[1][i] = (i * k) + way.getEtapa() + 1;

		if (k - way.getEtapa() <= 3) {
			LinkedList<PseudoTab> solution;
			System.out.println(way.getEtapa());
			solution = way.aStarSolve();
			solution.poll();
			for (PseudoTab p : solution)
				this.executarMovimentoAuto(p.move);
		} else {
			if (way.lineDistance(way.getEtapa()) != 0)
				fillLine(selecteds[0], null);
			way = getTabuleiro().getPseudoTabuleiro();
			if (way.collDistance(way.getEtapa()) != 0)
				fillColl(selecteds[1]);
			resolveTabuleiro();
		}
	}
}
