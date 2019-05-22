package br.com.poli.puzzleN.engine;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Ranking {
    private static String FILE_NAME = "save.bin";
    private static Save ranking;

    public Ranking() {
        // FILE_NAME = save;
        // if ((new File(FILE_NAME)).canRead() == false)
        try {
            if ((new File(FILE_NAME)).canRead() == true) {
                ObjectInputStream read = new ObjectInputStream(new FileInputStream(FILE_NAME));
                ranking = (Save) read.readObject();
                read.close();
                System.out.println(ranking.size());
            }
            else 
                throw new EOFException();
        } catch (ClassNotFoundException | EOFException e1) {
            System.out.print("File vazio ou inexistente, instanciando nova lista...");
            ranking = new Save();
            try {
                ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
                write.writeObject(ranking);
                write.close();
                System.out.println("Sucesso!");
            } catch (IOException e2) {
                System.out.println("Falha!");
                e1.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static int size() {
        return ranking.size();
    }

    public static Puzzle get(int index) {
        return ranking.get(index);
    }

    public static void save(Puzzle partida) {
        try {
            if ((new File(FILE_NAME)).delete() && ranking.addGame(partida)) {
                System.out.print("Salvando...");
                ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
                write.writeObject(ranking);
                write.close();
                System.out.println("Sucesso!");
            }
        } catch (IOException e) {
            System.out.println("Falha!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            if ((new File(FILE_NAME)).delete()) {
                System.out.println("Salvando...");
                ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
                write.writeObject(ranking);
                write.close();
                System.out.println("Sucesso!");
            }
        } catch (IOException e) {
            System.out.println("Falha!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static Save getRank() {
        return ranking;
    }

}