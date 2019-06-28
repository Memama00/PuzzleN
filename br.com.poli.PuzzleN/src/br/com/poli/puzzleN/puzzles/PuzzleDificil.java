package br.com.poli.puzzleN.puzzles;

import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import br.com.poli.puzzleN.Interfaces.CalculaDificil;
import br.com.poli.puzzleN.engine.Dificuldade;
import br.com.poli.puzzleN.engine.P;
import br.com.poli.puzzleN.engine.PseudoTab;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.exceptions.TempoExcedido;
import br.com.poli.puzzleN.frontend.screens.Loading;
import br.com.poli.puzzleN.testes.Main;

public class PuzzleDificil extends Puzzle {

	private static final long serialVersionUID = 1L;

	public PuzzleDificil(String nome) {
		super(nome, Dificuldade.DIFICIL);
		super.setScore(new CalculaDificil(this));
	}

	@Override
	public void resolveTabuleiro() throws TempoExcedido {
		PseudoTab way = this.getTabuleiro().gerarPseudoTabuleiro();
		int k = this.getTabuleiro().getGrid().length;
		int max = k - 1;
		final LinkedList<P> moves = new LinkedList<P>();
		way.updateEtapa();

		for (int i = 1; i <= 5; i++)
			if (!PseudoTab.SOLVED.position(i).equals(way.position(i)))
				moves.addAll(way.pointWay(i, way.position(i).x, 1));

		for (int i = 1; i <= 5; i++)
			if (!PseudoTab.SOLVED.position(i).equals(way.position(i)))
				moves.addAll(executarMovimentoAuto(i, false));

		if (!way.isLineCompleat(0))
			moves.addAll(way.ordernLine(0));

		moves.addAll(way.pointWay(6, way.position(6).x, 1));
		moves.addAll(way.pointWay(6, max, 1));

		moves.addAll(way.pointWay(11, way.position(11).x, 1));
		moves.addAll(way.pointWay(11, max - 1, 1));

		moves.addAll(way.pointWay(21, way.position(21).x, max));
		moves.addAll(way.pointWay(21, PseudoTab.SOLVED.position(21)));

		moves.addAll(way.pointWay(16, way.position(16).x, 1));
		moves.addAll(way.pointWay(16, PseudoTab.SOLVED.position(16)));

		moves.addAll(way.pointWay(0, max - 3, PseudoTab.SOLVED.position(6).y));
		moves.addAll(way.pointWay(0, 6));
		moves.addAll(way.pointWay(0, way.zero.x, 2));
		moves.addAll(way.pointWay(0, 0, 2));
		moves.addAll(way.pointWay(0, 0, 1));
		moves.addAll(way.pointWay(0, 6));
		moves.addAll(way.pointWay(0, way.zero.x, 2));
		moves.addAll(way.pointWay(0, 0, 2));
		moves.addAll(way.pointWay(0, 11));
		moves.addAll(way.pointWay(0, 6));

		if (!way.isCollCompleat(0))
			moves.addAll(way.ordernColl(0));

		moves.addAll(executarMovimentoAuto(7, false));
		moves.addAll(executarMovimentoAuto(8, false));
		moves.addAll(executarMovimentoAuto(9, false));
		moves.addAll(executarMovimentoAuto(10, false));
		if (!way.isLineCompleat(1))
			moves.addAll(way.ordernLine(1));
		moves.addAll(way.pointWay(12, max, way.position(12).y));
		moves.addAll(way.pointWay(12, max, PseudoTab.SOLVED.position(12).y));
		moves.addAll(way.pointWay(17, 1, way.position(17).y));
		moves.addAll(way.pointWay(17, 1, PseudoTab.SOLVED.position(12).y));
		moves.addAll(way.pointWay(17, max - 1, PseudoTab.SOLVED.position(12).y));
		moves.addAll(executarMovimentoAuto(22, false));
		moves.addAll(way.pointWay(0, max - 2, PseudoTab.SOLVED.position(12).y));
		moves.addAll(way.pointWay(0, 12));
		moves.addAll(way.pointWay(0, way.zero.x, 3));
		moves.addAll(way.pointWay(0, 0, 3));
		moves.addAll(way.pointWay(0, 0, 2));
		moves.addAll(way.pointWay(0, 12));
		moves.addAll(way.pointWay(0, way.zero.x, 3));
		moves.addAll(way.pointWay(0, 0, 2));
		moves.addAll(way.pointWay(0, 17));
		moves.addAll(way.pointWay(0, 12));
		if (max - way.getEtapa() <= 3) {
			moves.addAll(way.aStarSolve());
			Loading.stop();
			if (!moves.isEmpty() && JOptionPane.showConfirmDialog(Main.janela,
					"Resolvidoem "
							+ Math.abs(
									Main.compareTime.getTime().getTime() - Calendar.getInstance().getTime().getTime())
							+ " milesegundos e " + moves.size() + " movimentos.\nMostrar solução?",
					"Mostrar?", JOptionPane.YES_NO_OPTION) == 0)
				super.autoPress(moves);
		}
	}

}
