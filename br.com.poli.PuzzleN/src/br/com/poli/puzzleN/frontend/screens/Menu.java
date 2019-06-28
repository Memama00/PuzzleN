package br.com.poli.puzzleN.frontend.screens;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.poli.puzzleN.frontend.buttons.MuteButton;
import br.com.poli.puzzleN.frontend.buttons.RankingButton;
import br.com.poli.puzzleN.frontend.buttons.StartButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboBox<String> dificuldade;

	public Menu(PuzzleFrame frame) {
		super();
		if (SondTrack.getTrackName() != "MainTrack2" && SondTrack.isPlaying())
			SondTrack.changeTrack("MainTrack2");
		if(frame.getHeight() > 600)
			frame.setSize(820, 600);
		JLabel lblPuzzleN = new JLabel("PUZZLE-N");
		lblPuzzleN.setFont(new Font("Umbuntu", Font.BOLD + Font.ITALIC, 63));
		lblPuzzleN.setBounds(200, 59, 400, 56);
		lblPuzzleN.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuzzleN.setForeground(Color.WHITE);
		this.add(lblPuzzleN);

		JLabel lblNOME = new JLabel("NOME");
		lblNOME.setBounds(382, 130, 46, 14);
		lblNOME.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblNOME);

		textField = new JTextField("Digite seu nome...");
		textField.setBounds(330, 150, 150, 20);
		this.add(textField);
		textField.setColumns(20);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.black);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                textField.setText("");
            }
        });

		JLabel lblNivel = new JLabel("NIVEL");
		lblNivel.setBounds(381, 186, 48, 14);
		lblNivel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblNivel);

		this.dificuldade = new JComboBox<>();
		dificuldade.setModel(
				new DefaultComboBoxModel<>(new String[] { "selecione", "facil", "medio ", "dificil", "Insano" }));
		dificuldade.setBounds(335, 195 + 10, 140, 20);
		dificuldade.setAlignmentX(SwingConstants.CENTER);
		dificuldade.setForeground(Color.WHITE);
		dificuldade.setBackground(Color.BLACK);

		JLabel icon = new JLabel(new ImageIcon("images/Metro-Sound-32.png"));
		icon.setAlignmentY(icon.getAlignmentY() - 16);
		icon.setBounds(24, 475, 64, 42);
		icon.setVisible(true);
		this.add(icon);

		this.add(dificuldade);

		this.add(new RankingButton(frame));

		this.add(new StartButton(frame));

		this.add(new MuteButton());

		this.add(new BackGround());

	}

	public JTextField getTextField() {
		return textField;
	}

	public JComboBox<String> getDificuldade() {
		return dificuldade;
	}
}