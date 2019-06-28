package br.com.poli.puzzleN.puzzles;

import java.awt.Point;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import br.com.poli.puzzleN.Interfaces.CalculaInsano;
import br.com.poli.puzzleN.engine.Dificuldade;
import br.com.poli.puzzleN.engine.P;
import br.com.poli.puzzleN.engine.PseudoTab;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.exceptions.K_Invalido;
import br.com.poli.puzzleN.exceptions.TempoExcedido;
import br.com.poli.puzzleN.frontend.screens.Loading;
import br.com.poli.puzzleN.frontend.screens.PuzzleFrame;
import br.com.poli.puzzleN.testes.Main;

public class PuzzleInsano extends Puzzle {

	private static final long serialVersionUID = 1L;
	private int tamanho;

	public PuzzleInsano(String nome) {
		super(nome, Dificuldade.INSANO);
		super.setScore(new CalculaInsano(this));
		tamanho = defineK(Main.janela);
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int in) {
		tamanho = in;
	}

	public void iniciaPartida() {
		super.setQuantidadeMovimentos(0);
		super.setVenceu(false);
		getTempo().setTime(new Date());
		getTabuleiro().geraTabuleiro(tamanho);
		super.setZero(new Point(tamanho - 1, tamanho - 1));

		for (int j = 0; j < 2; j++) {
			jhonnyBravo();

			for (int i = 0; i < tamanho; i++)
				if (!bubbleMoveZero(1))
					break;
			for (int i = 0; i < tamanho; i++)
				if (!bubbleMoveZero(4))
					break;
			jhonnyBravo();

			for (int i = 0; i < tamanho; i++)
				if (!bubbleMoveZero(4))
					break;
			for (int i = 0; i < tamanho; i++)
				if (!bubbleMoveZero(2))
					break;
			jhonnyBravo();

			for (int i = 0; i < tamanho; i++)
				if (!bubbleMoveZero(3))
					break;
			for (int i = 0; i < tamanho; i++)
				if (!bubbleMoveZero(1))
					break;
			jhonnyBravo();

			if (j == 0) {
				for (int i = 0; i < tamanho; i++)
					if (!bubbleMoveZero(1))
						break;
				for (int i = 0; i < tamanho; i++)
					if (!bubbleMoveZero(3))
						break;
				for (int i = 0; i < tamanho / 2; i++)
					if (!bubbleMoveZero(2))
						break;
				for (int i = 0; i < tamanho / 2; i++)
					if (!bubbleMoveZero(4))
						break;
			}
		}
	}

	private int defineK(PuzzleFrame frame) {
		int in = 1;
		try {
			in = Integer.valueOf(JOptionPane.showInputDialog(frame, "Digite um numero de 6 a 10"));
			if (in < 6 || in > 10)
				throw new K_Invalido();
		} catch (K_Invalido | NumberFormatException e) {
			String show = e.getClass() == NumberFormatException.class ? "Character invalido!" : e.getMessage();
			JOptionPane.showMessageDialog(null, show + " O valor foi automaticamente definido como maximo!");
			in = 10;
		}
		return in;
	}

	@Override
	public void resolveTabuleiro() throws TempoExcedido {
		PseudoTab way = getTabuleiro().gerarPseudoTabuleiro();
		LinkedList<P> moves = new LinkedList<P>();
		for (int i = 0; i < tamanho - 3; i++) {

			for (int j = 0; j < tamanho; j++) {
				int bloco = PseudoTab.SOLVED.getTab()[i][j];
				if (way.getTab()[i][j] != bloco)
					moves.addAll(way.pointWay(bloco, way.position(bloco).x, i + 1));
			}
			for (int j = 0; j < tamanho; j++) {
				int bloco = PseudoTab.SOLVED.getTab()[i][j];
				moves.addAll(executarMovimentoAuto(bloco, false));
			}
			// moves.addAll(way.ordernLine(i));
		}
		for (int x = 0; x < tamanho - 3; x++) {
			for (int y = tamanho - 3; y < tamanho; y++) {
				int bloco = PseudoTab.SOLVED.getTab()[y][x];

				if (way.getTab()[y][x] != bloco)
					moves.addAll(way.pointWay(bloco, x + 1, way.position(bloco).y));

				moves.addAll(executarMovimentoAuto(bloco, false));
			}
			moves.addAll(way.ordernColl(x));
			Loading.stop();
			autoPress(moves);
		}
	}

}
