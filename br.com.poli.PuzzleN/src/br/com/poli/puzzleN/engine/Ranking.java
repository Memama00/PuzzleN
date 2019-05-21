package br.com.poli.puzzleN.engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Ranking {
    private static final String FILE_NAME = "save.bin";
    private Save ranking;

    public Ranking() {

        try {
            ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            ranking = new Save();
            write.writeObject(ranking);
            write.close();
            // ObjectInputStream read = new ObjectInputStream(new FileInputStream(FILE_NAME));
            // if (read.readObject() == null)
            //     ranking = new Save();
            // else
            //     ranking = (Save) read.readObject();
            //     read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Puzzle partida) {
        try {
            if (ranking == null) {
                ObjectInputStream read = new ObjectInputStream(new FileInputStream(FILE_NAME));
                if (read.readObject() == null)
                    ranking = new Save();
                else
                    ranking = (Save) read.readObject();
                read.close();
            }
            if (ranking.add(partida)) {

                ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
                write.writeObject(ranking);
                write.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Save read() {
        try {
            if (ranking == null) {
                ObjectInputStream read = new ObjectInputStream(new FileInputStream(FILE_NAME));
                if (read.readObject() == null)
                    ranking = new Save();
                else
                    ranking = (Save) read.readObject();
                read.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ranking;
    }

}