package br.com.poli.puzzleN.puzzles;

import java.io.Serializable;

import br.com.poli.puzzleN.Interfaces.CalculaDificil;
import br.com.poli.puzzleN.engine.Dificuldade;
import br.com.poli.puzzleN.engine.Puzzle;

public class PuzzleDificil extends Puzzle implements Serializable{

	private static final long serialVersionUID = 1L;

	public PuzzleDificil(String nome) {
		super(nome, Dificuldade.DIFICIL);
		super.setScore(new CalculaDificil(this));
	}

}
