package br.com.poli.puzzleN.puzzles;

import java.util.Date;
import java.util.Scanner;

import br.com.poli.puzzleN.Interfaces.CalculaInsano;
import br.com.poli.puzzleN.engine.Dificuldade;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.exceptions.K_Invalido;

public class PuzzleInsano extends Puzzle {

	private int tamanho;

	public PuzzleInsano(String nome) {
		super(nome, Dificuldade.INSANO);
		super.setScore(new CalculaInsano());
		tamanho = defineK();
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho() {
		tamanho = defineK();
	}
	
	public void iniciaPartida() {
		super.setQuantidadeMovimentos(0);
		super.setVenceu(false);
		getTempo().setTime(new Date());
		getTabuleiro().geraTabuleiro(tamanho);
	}
	
	private int defineK() {
		int in = 1;
		Scanner read = new Scanner(System.in);
		try {		
			System.out.println("Digite um numero de 2 a 2000");
			in =  read.nextInt();
			if (in < 2 || in > 2000)// Sem numeros negativos dessa vez <3
				throw new K_Invalido();
			read.close();
		} catch (K_Invalido e) {
			System.out.println(e.getMessage() + " O valor foi automaticamente definido como maximo!");
			in = 2000;
		} finally {
			read.close();
		}

		return in;
	}
}
