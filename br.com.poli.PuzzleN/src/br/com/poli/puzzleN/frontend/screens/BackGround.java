package br.com.poli.puzzleN.frontend.screens;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BackGround extends JLabel {
	private static final long serialVersionUID = 1L;

	public BackGround() {
		super();
		super.setIcon(new ImageIcon("images/giphy.gif"));
		super.setBounds(0, 0, 820, 600);
		super.setHorizontalAlignment(SwingConstants.CENTER);
	}
}