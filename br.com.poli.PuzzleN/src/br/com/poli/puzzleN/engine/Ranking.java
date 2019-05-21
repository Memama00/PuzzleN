package br.com.poli.puzzleN.engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Ranking {
    private static final String FILE_NAME = "save.bin";
    private Save ranking;

    public Ranking() {

        ObjectInputStream read;
        try {
            ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            write.writeObject(new Save());
            write.close();
            read = new ObjectInputStream(new FileInputStream(FILE_NAME));
            if (read.readObject() == null)
                ranking = new Save();
            else
                ranking = (Save) read.readObject();
            read.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void save(Puzzle partida) {
        if (ranking.add(partida)) {
            try {
                ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
                write.writeObject(ranking);
                write.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Puzzle[] read() {
        return ranking.getRanking();
    }

    public class Save implements Serializable {
        private Puzzle[] ranking;
        private static final long serialVersionUID = 3145839246335750366L;
        private static final char slash = '/';

        public Save() {
            ranking = new Puzzle[10];
        }

        public boolean add(Puzzle partida) {

            for (int i = 0; i < ranking.length; i++)
                if (ranking[i] == null || ranking[i].getScore().getPontos() < partida.getScore().getPontos()) {
                    ranking[i] = partida;
                    return true;
                }
            return false;
        }

        public char getSlash() {
            return slash;
        }

        public Puzzle[] getRanking() {
            return ranking;
        }

    }

}