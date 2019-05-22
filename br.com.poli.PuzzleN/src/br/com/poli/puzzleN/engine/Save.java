package br.com.poli.puzzleN.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Save extends ArrayList<Puzzle> implements Serializable {

    private static final long serialVersionUID = 0105L;

    public Save() {
        super();
    }

    public Save(int i) {
        super(i);
    }

    public boolean addGame(Puzzle partida) {
        Boolean sucessfull = false;
        if (super.size() > 9 && super.get(9).getScore().getPontos() < partida.getScore().getPontos())
            sucessfull = super.add(partida);
        else
            sucessfull = super.add(partida);
        Collections.sort(this);
        if (super.size() > 9)
            super.remove(10);
        return sucessfull;
    }

}