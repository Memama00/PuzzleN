package br.com.poli.puzzleN.engine;

import br.com.poli.puzzleN.puzzles.PuzzleMedio;

public class TesteSpeedPuzzle extends PuzzleMedio {

    private static final long serialVersionUID = 531L;

    public TesteSpeedPuzzle() {
        super("Teste");
    }

    @Override
    public void iniciaPartida() {
        super.iniciaPartida();
        this.getTabuleiro().getGrid()[0][0] = new Bloco(0);
        this.getTabuleiro().getGrid()[0][1] = new Bloco(15);
        this.getTabuleiro().getGrid()[0][2] = new Bloco(14);
        this.getTabuleiro().getGrid()[0][3] = new Bloco(13);
        this.getTabuleiro().getGrid()[1][0] = new Bloco(12);
        this.getTabuleiro().getGrid()[1][1] = new Bloco(11);
        this.getTabuleiro().getGrid()[1][2] = new Bloco(10);
        this.getTabuleiro().getGrid()[1][3] = new Bloco(9);
        this.getTabuleiro().getGrid()[2][0] = new Bloco(8);
        this.getTabuleiro().getGrid()[2][1] = new Bloco(7);
        this.getTabuleiro().getGrid()[2][2] = new Bloco(6);
        this.getTabuleiro().getGrid()[2][3] = new Bloco(5);
        this.getTabuleiro().getGrid()[3][0] = new Bloco(4);
        this.getTabuleiro().getGrid()[3][1] = new Bloco(3);
        this.getTabuleiro().getGrid()[3][2] = new Bloco(2);
        this.getTabuleiro().getGrid()[3][3] = new Bloco(1);
    }
}