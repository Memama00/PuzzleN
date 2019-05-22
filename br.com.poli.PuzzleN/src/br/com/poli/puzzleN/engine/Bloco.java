package br.com.poli.puzzleN.engine;

import java.io.Serializable;

public class Bloco implements Serializable{

	private static final long serialVersionUID = 0101L;
	private int valor;
	private boolean valido;


	public Bloco(int numero) {
		this.valor = numero;
		this.valido = (numero == 0) ? true : false;
	}

	public int getValor() {
		return valor;
	}

	public boolean getValido() { 
		return valido;
	}

	public void setValor(int numero) {
		this.valido = (numero == 0) ? true : false;
		this.valor = numero;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}
}
