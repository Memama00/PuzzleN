package br.com.poli.puzzleN.frontend;
import java.awt.EventQueue;
import br.com.poli.puzzleN.engine.Puzzle;

public class Main{
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PuzzleFrame frame;
					frame = new PuzzleFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}