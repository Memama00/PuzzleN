package br.com.poli.puzzleN.exceptions;

public class TempoExcedido extends Error{
	
	private static final long serialVersionUID = 1L;
		
	public TempoExcedido() {
		super("N alto demais! Muito processamento necessário para estas interações ☹");
	}
	
}
	