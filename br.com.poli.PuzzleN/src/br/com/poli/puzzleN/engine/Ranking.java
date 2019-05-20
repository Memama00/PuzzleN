package br.com.poli.puzzleN.engine;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.poli.puzzleN.Interfaces.CalculaFacil;

public class Ranking {

    private ArrayList<Puzzle> ranking;
    // private ObjectOutputStream objOut;
    // private ObjectInputStream objIn;
    // private File saveFile;

    public Ranking() {
        ranking = new ArrayList<Puzzle>();
        
        // try {
        //     saveFile = new File(
        //             "/home/gustavo/Área de Trabalho/PuzzleN/br.com.poli.PuzzleN/bin/br/com/poli/puzzleN/engine/ranking/save.dat");
        //     if (objOut == null)
        //         objOut = new ObjectOutputStream(new FileOutputStream(saveFile));
        //     if (objIn == null)
        //         objIn = new ObjectInputStream(new FileInputStream(saveFile));

        //     if (objIn.readObject() != null)
        //         ranking = (ArrayList< Puzzle >) objIn.readObject();
        //     else {
        //         objOut.writeObject(new ArrayList<Puzzle>());
        //         ranking = (ArrayList<Puzzle>) objIn.readObject();
        //     }
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // } catch (ClassNotFoundException e) {
        //     e.printStackTrace();
        // }

    }

    public void saveGame(Puzzle save) {

        // try {
        //     if (addGame(save))
        //         if (objOut != null)
        //             objOut.writeObject(ranking);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    public void readRanking() {
        // try {

        //     return (ArrayList<Puzzle>) objIn.readObject();

        // } catch (FileNotFoundException e) {
        //     System.out.println("Save not Found!");
        //     return null;
        // } catch (IOException e) {
        //     e.printStackTrace();
        //     return null;
        // } catch (ClassNotFoundException e) {
        //     e.printStackTrace();
        //     return null;
        // }
    }

    public boolean addGame(Puzzle save) {

        if (ranking.size() < 10) {
            return ranking.add(save);
        } else
            for (int i = 1; i < ranking.size(); i++)
                if (((CalculaFacil) ranking.get(i).getScore()).getPontos() < ((CalculaFacil) save.getScore())
                        .getPontos()) {
                    ranking.add(i, save);
                    return true;
                }
        ;

        return false;
    }

    public ArrayList<Puzzle> getRanking() {
        return ranking;
    }
}