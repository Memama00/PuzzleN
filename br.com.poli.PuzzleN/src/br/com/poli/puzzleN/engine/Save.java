package br.com.poli.puzzleN.engine;

import java.io.Serializable;
import java.util.ArrayList;

public class Save extends ArrayList<Puzzle> implements Serializable {

    public Save() {
        super();
    }

    public Save(int i) {
        super(i);
    }

    public boolean add(Puzzle partida) {

        for (int i = 0; i < super.size(); i++)
            if (super.get(i).getScore().getPontos() < partida.getScore().getPontos()) {
                super.add(i, partida);
                return true;
            }
        return false;
    }

}