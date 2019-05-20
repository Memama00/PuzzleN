package br.com.poli.puzzleN.engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

public class FileManeger {
	private FileInputStream fileIn;
	private FileOutputStream fileOut;
	private ObjectOutputStream objOut;
	private ObjectInputStream objIn;

	public FileManeger(Puzzle save) {
		try {
			fileOut = new FileOutputStream("serialF.dat");
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(save);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
