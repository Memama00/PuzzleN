package br.com.poli.puzzleN.engine;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

public class Save extends LinkedList<Puzzle> implements Serializable {

    private static final long serialVersionUID = 105L;

    public Save() {
        super();
    }

    public boolean addGame(Puzzle partida) {
        Boolean sucessfull = false;
        sucessfull = super.add(partida);
        Collections.sort(this);
        if (super.indexOf(super.getLast()) >= 10)
            return sucessfull && super.contains(super.removeLast());
        return sucessfull;
    }

}