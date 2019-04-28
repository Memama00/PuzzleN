package br.com.poli.puzzleN.engine;

public enum Dificuldade {

	FACIL(8), MEDIO(15), DIFICIL(24), INSANO(0);

	private int valor;

	private Dificuldade(int val) {
		this.valor = val;
	}

	public int getValor() {
		return this.valor;
	}
}
