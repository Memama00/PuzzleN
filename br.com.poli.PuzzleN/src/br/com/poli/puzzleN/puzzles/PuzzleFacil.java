package br.com.poli.puzzleN.puzzles;

import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import br.com.poli.puzzleN.Interfaces.CalculaFacil;
import br.com.poli.puzzleN.engine.Dificuldade;
import br.com.poli.puzzleN.engine.P;
import br.com.poli.puzzleN.engine.PseudoTab;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.frontend.screens.Loading;
import br.com.poli.puzzleN.testes.Main;

public class PuzzleFacil extends Puzzle {

	private static final long serialVersionUID = 1L;

	public PuzzleFacil(String nome) {
		super(nome, Dificuldade.FACIL);
		super.setScore(new CalculaFacil(this));
	}

	@Override
	public void resolveTabuleiro() {
		try {
			PseudoTab way = this.getTabuleiro().gerarPseudoTabuleiro();
			LinkedList<P> moves = way.aStarSolve();
			Loading.stop();
			if (!moves.isEmpty() && JOptionPane.showConfirmDialog(null,
					"Resolvido em "
							+ Math.abs(
									Main.compareTime.getTime().getTime() - Calendar.getInstance().getTime().getTime())
							+ " milesegundos e " + moves.size() + " movimentos.\nMostrar solução?",
					"Mostrar?", JOptionPane.YES_NO_OPTION) == 0)
				super.autoPress(moves);
		} catch (Error e) {
			JOptionPane.showMessageDialog(Main.janela, e.getMessage());
		}
	}

}
