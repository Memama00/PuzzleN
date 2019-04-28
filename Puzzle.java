package br.com.poli.puzzleN.engine;

import java.util.Calendar;
import java.util.Date;

public class Puzzle {
	private Jogador jogador;
	private Tabuleiro gridPuzzle;
	private int quantidadeMovimentos;
	private CalculaScore score;
	private boolean venceu;
	private Calendar tempo;
	private Dificuldade dificuldade;

	public Puzzle(String nome, Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
		jogador = new Jogador(nome);
		gridPuzzle = new Tabuleiro(dificuldade);
		quantidadeMovimentos = 0;
		venceu = false;
		tempo = Calendar.getInstance();
		tempo.setTime(new Date());
	}

	public void setDificuldade(Dificuldade set) {
		dificuldade = set;
	}


	public void setVenceu(boolean venceu) {
		this.venceu = venceu;
	}

	public void setQuantidadeMovimentos(int movimentos) {
		quantidadeMovimentos = movimentos;
	}

	public void setJogador(Jogador player) {
		jogador = player;
	}

	public Dificuldade getDificuldade() {
		return dificuldade;
	}
	

	public int getScore() {
		return score.pontos(this);
	}

	public void setScore(CalculaScore score) {
		this.score = score;
	}

		public boolean getVenceu() {
		return venceu;
	}

	public long getTempo() {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		return tempo.get(Calendar.MILLISECOND) - now.get(Calendar.MILLISECOND);
	}

	public int getQuantidadeMovimentos() {
		return quantidadeMovimentos;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public Tabuleiro getTabuleiro() {
		return gridPuzzle;
	}

	public boolean isFimDeJogo() {
		venceu = gridPuzzle.isTabuleiroOrdenado(dificuldade);
		return venceu;
	}
	
	public void iniciaPartida(Dificuldade dificuldade) {
		gridPuzzle.geraTabuleiro(dificuldade);
		quantidadeMovimentos = 0;
		score = lifeIsHard(dificuldade);
		venceu = false;
		tempo.setTime(new Date());
		this.dificuldade = dificuldade;
	}
	
	private CalculaScore lifeIsHard(Dificuldade d) {
		switch(d.getValor()) {
		case 8:
			return new CalculaFacil();
		case 15:
			return new CalculaMedio();
		case 24:
			return new CalculaDificil();
		default:
			return new CalculaInsano();
		}
	}
	public void resolveTabuleiro() {
		this.setTempo(Calendar.getInstance());
		try {
			if(this.getTempo() > 200.000)
				throw new TempoExcedido();
		}catch(TempoExcedido a) {
			
		}
		
	}

	public void setTempo(Calendar tempo) {
		this.tempo = tempo;
	}
}
