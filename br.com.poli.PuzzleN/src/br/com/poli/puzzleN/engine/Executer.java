package br.com.poli.puzzleN.engine;

import java.awt.Point;

import br.com.poli.puzzleN.exceptions.MovimentoInvalido;
import br.com.poli.puzzleN.puzzles.*;
import br.com.poli.puzzleN.testes.Testes;

public class Executer {
    private Puzzle partida;

    public Executer(Puzzle p) {
        partida = p;
    }

    private void moveTo(int numero, P ponto) {
        Predict way = new Predict(partida.getTabuleiro().gerarPseudoTabuleiro());
        for (Point p : way.wayTo(numero, ponto))
            try {
                partida.smartMove(p);
            } catch (MovimentoInvalido e) {
                e.printStackTrace();
            }
        Testes.showTab(partida.getTabuleiro());
    }

    public static void main(String[] args) {
        Executer p = new Executer(new PuzzleFacil("nome"));
        p.moveTo(1, new P(0, 0));
    }
}