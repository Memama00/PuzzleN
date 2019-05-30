package br.com.poli.puzzleN.engine;

import java.awt.Point;

public class Movimento extends Point {
    private static final long serialVersionUID = 1L;
    private String sentido;

    public Movimento(String sentido , Point ponto) {
        super(ponto);
        this.sentido = sentido;
    }

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public int X() {
        return this.x;
    }

    public int Y() {
        return this.y;
    }
}