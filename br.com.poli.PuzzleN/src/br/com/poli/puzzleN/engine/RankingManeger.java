package br.com.poli.puzzleN.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.FileNotFoundException;

public class RankingManeger {
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;
	private Ranking ranking;
	private File saveFile;

	public RankingManeger() {
		try {
			saveFile = new File(
					"/home/gustavo/Área de Trabalho/PuzzleN/br.com.poli.PuzzleN/src/br/com/poli/puzzleN/engine/saves/save.dat");

			if (objOut == null)
				objOut = new ObjectOutputStream(new FileOutputStream(saveFile));
			if (objIn == null)
				objIn = new ObjectInputStream(new FileInputStream(saveFile));

			ranking = (Ranking) objIn.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void saveGame(Puzzle save) {

		try {
			if (ranking.addGame(save))
				if (objOut != null)
					objOut.writeObject(ranking);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Ranking readRanking() {
		try {

			ranking = (Ranking) objIn.readObject();

		} catch (FileNotFoundException e) {
			System.out.println("Save not Found!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ranking;
	}
}
