package br.com.poli.puzzleN.frontend.screens;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.poli.puzzleN.testes.Main;

public class BackGround extends JLabel {
	private static final long serialVersionUID = 1L;

	public BackGround() {
		super();
		super.setIcon(new ImageIcon("images/giphy.gif"));
		if (Main.janela == null)
			super.setBounds(0, 0, 820, 600);
		else{
			super.setBounds(Main.janela.getBounds());
			super.setLocation(0, 0);
		}
		super.setHorizontalAlignment(SwingConstants.CENTER);
	}
}