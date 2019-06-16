package br.com.poli.puzzleN.puzzles;

import java.awt.Point;
import java.util.LinkedList;

import br.com.poli.puzzleN.Interfaces.CalculaMedio;
import br.com.poli.puzzleN.engine.Dificuldade;
import br.com.poli.puzzleN.engine.PseudoTab;
import br.com.poli.puzzleN.engine.Puzzle;

public class PuzzleMedio extends Puzzle {

	private static final long serialVersionUID = 1L;

	public PuzzleMedio(String nome) {
		super(nome, Dificuldade.MEDIO);
		super.setScore(new CalculaMedio(this));
	}

	@Override
	public void resolveTabuleiro() {
		PseudoTab way = new PseudoTab(this.getTabuleiro().gerarPseudoTabuleiro());
		LinkedList<PseudoTab> solution;
		if (way.isEtapaCompleat(0) && way.isEtapaCompleat(1))
			while (3 != (this.getZero().x & this.getZero().y)) {
				autoZeroMove(new Point(2, 2));
				autoZeroMove(new Point(3, 3));
			}
		else if (way.isEtapaCompleat(0)) {
			way.setEtapa(way.getEtapa() + 1);
			System.out.println(way.getEtapa());
			solution = way.aStarSolve();
			solution.poll();
			for (PseudoTab p : solution)
				this.executarMovimentoAuto(p.move);
		} else {
			if (way.lineDistance(0) != 0)
				fillLine(new int[] { 1, 2, 3, 4 }, null, 0);
			way = this.getTabuleiro().getPseudoTabuleiro();
			if (way.lineDistance(0) == 0)
				fillLine(new int[] { 13, 9, 5 }, new Point[] { new Point(0, 1), new Point(1, 1), new Point(2, 1) }, 1);
		}

	}

	private void fillLine(int[] line, Point[] places, int y) {

		PseudoTab way = this.getTabuleiro().getPseudoTabuleiro();
		int max = line.length - 1;
		if (way.position(0).distanceTo(places == null ? PseudoTab.SOLVED.position(line[0]) : places[0]) != 0)
			autoZeroMove(way.position(line[0]));
		for (int i = 0; i < line.length; i++)
			if (i < line.length - 2) {
				autoMoveTo(line[i], places == null ? PseudoTab.SOLVED.position(line[i]) : places[i]);
			} else if (i < line.length)
				autoMoveTo(line[i], new Point(max, (i - (line.length - 2) + 1) + y));

		way = this.getTabuleiro().getPseudoTabuleiro();
		if (way.isInPositons(line, places, y) && places == null) {
			autoZeroMove(new Point(max - 1, 1 + y));
			autoZeroMove(new Point(max - 1, 0 + y));

			autoZeroMove(new Point(max, 0 + y));
			autoZeroMove(new Point(max, 1 + y));
			autoZeroMove(new Point(max, 2 + y));

			autoZeroMove(new Point(max - 1, 2 + y));
			autoZeroMove(new Point(max - 1, 1 + y));
			autoZeroMove(new Point(max - 1, 0 + y));

			autoZeroMove(new Point(max, 0 + y));
			autoZeroMove(new Point(max, 1 + y));
		}
	}

	private void autoMoveTo(int quem, Point to) {
		PseudoTab way = getTabuleiro().getPseudoTabuleiro();
		LinkedList<PseudoTab> solution;
		if (way.position(quem).distanceTo(to) == 0)
			return;
		solution = way.escorelSolver(quem, to);
		solution.poll();
		for (PseudoTab p : solution)
			this.executarMovimentoAuto(p.move);
	}

	private void autoZeroMove(Point to) {
		autoMoveTo(0, to);
	}
}
