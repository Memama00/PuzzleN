package br.com.poli.puzzleN.puzzles;

import br.com.poli.puzzleN.Interfaces.CalculaMedio;
import br.com.poli.puzzleN.engine.Dificuldade;
import br.com.poli.puzzleN.engine.Puzzle;

public class PuzzleMedio extends Puzzle {

	public PuzzleMedio(String nome) {
		super(nome, Dificuldade.MEDIO);
		super.setScore(new CalculaMedio(this));
	}

}
