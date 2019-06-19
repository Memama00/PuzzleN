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
	public void resolveTabuleiro() throws TempoExcedido{
			PseudoTab way = new PseudoTab(this.getTabuleiro().gerarPseudoTabuleiro());
			int k = this.getTabuleiro().getGrid().length;
			if (k - way.getEtapa() <= 3) {
				LinkedList<PseudoTab> solution;
				System.out.println(way.getEtapa());
				solution = way.aStarSolve();
				solution.poll();
				for (PseudoTab p : solution)
					this.autoPress(p.move);
			} else {
				if (way.lineDistance(way.getEtapa()) != 0)
					fillLine(new int[]{1, 2, 3, 4}, null);
				way = getTabuleiro().getPseudoTabuleiro();
				if (way.collDistance(way.getEtapa()) != 0)
					fillColl(new int[]{13, 9, 5});
				resolveTabuleiro();
			}
	}
}
