package br.com.poli.puzzleN.engine;

public class Bloco {

	private int valor;
	private boolean valido;

	public Bloco(int numero) {
		this.valor = numero;
		valido = (numero == 0) ? true : false;
	}

	public int getValor() {
		return valor;
	}

	public boolean getValido() { 
		return valido;
	}

	public void setValor(int numero) {
		valido = (numero == 0) ? true : false;
		valor = numero;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}
}
