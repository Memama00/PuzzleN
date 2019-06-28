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
		jhonnyBravo();
		//autoZeroMove(new P(0, 0));
		jhonnyBravo();
		//autoZeroMove(new P(tamanho - 1, 0));
		jhonnyBravo();
		//autoZeroMove(new P(0, tamanho - 1));
		jhonnyBravo();
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

		PseudoTab way = this.getTabuleiro().gerarPseudoTabuleiro();

		if (tamanho - way.getEtapa() <= 3) {
			LinkedList<P> moves;
			System.out.println(way.getEtapa());
			moves = way.aStarSolve();
			for (P move : moves)
				this.autoPress(move);
		} else {
		
		}
	}

}
