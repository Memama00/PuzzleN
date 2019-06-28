package br.com.poli.puzzleN.puzzles;

import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import br.com.poli.puzzleN.Interfaces.CalculaMedio;
import br.com.poli.puzzleN.engine.*;
import br.com.poli.puzzleN.exceptions.TempoExcedido;
import br.com.poli.puzzleN.frontend.screens.Loading;
import br.com.poli.puzzleN.testes.Main;

public class PuzzleMedio extends Puzzle {

	private static final long serialVersionUID = 1L;

	public PuzzleMedio(String nome) {
		super(nome, Dificuldade.MEDIO);
		super.setScore(new CalculaMedio(this));
	}

	@Override
	public void resolveTabuleiro() throws TempoExcedido {
		PseudoTab way = this.getTabuleiro().gerarPseudoTabuleiro();
		int k = this.getTabuleiro().getGrid().length;
		int max = k - 1;
		final LinkedList<P> moves = new LinkedList<P>();
		way.updateEtapa();
		// try {
		moves.addAll(executarMovimentoAuto(1, false));
		moves.addAll(executarMovimentoAuto(2, false));
		moves.addAll(executarMovimentoAuto(3, false));
		moves.addAll(executarMovimentoAuto(4, false));
		if (!way.isLineCompleat(0))
			moves.addAll(way.ordernLine(0));
		moves.addAll(way.pointWay(5, max, way.position(5).y));
		moves.addAll(way.pointWay(5, max, PseudoTab.SOLVED.position(5).y));
		moves.addAll(way.pointWay(9, 1, way.position(9).y));
		moves.addAll(way.pointWay(9, 1, PseudoTab.SOLVED.position(5).y));
		moves.addAll(way.pointWay(9, max - 1, PseudoTab.SOLVED.position(5).y));
		moves.addAll(executarMovimentoAuto(13, false));
		moves.addAll(way.pointWay(0, max - 2, PseudoTab.SOLVED.position(5).y));
		moves.addAll(way.pointWay(0, 5));
		moves.addAll(way.pointWay(0, way.zero.x, 2));
		moves.addAll(way.pointWay(0, 0, 2));
		moves.addAll(way.pointWay(0, 0, 1));
		moves.addAll(way.pointWay(0, 5));
		moves.addAll(way.pointWay(0, way.zero.x, 2));
		moves.addAll(way.pointWay(0, 0, 2));
		moves.addAll(way.pointWay(0, 9));
		moves.addAll(way.pointWay(0, 5));
		if (max - way.getEtapa() <= 3) {
			moves.addAll(way.aStarSolve());
			Loading.stop();
			if (!moves.isEmpty() && JOptionPane.showConfirmDialog(Main.janela,
					"Resolvido em "
							+ Math.abs(
									Main.compareTime.getTime().getTime() - Calendar.getInstance().getTime().getTime())
							+ " milesegundos e " + moves.size() + " movimentos.\nMostrar solução?",
					"Mostrar?", JOptionPane.YES_NO_OPTION) == 0)
				super.autoPress(moves);
		}
		// } catch (Error e) {
		// Loading.stop();
		// JOptionPane.showMessageDialog(Main.janela, e.getMessage());
		// }
	}
}
