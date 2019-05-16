package br.com.poli.puzzleN.frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPuzzleN = new JLabel("PUZZLE N");
		lblPuzzleN.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 14));
		lblPuzzleN.setBounds(255, 59, 67, 14);
		panel.add(lblPuzzleN);
		
		JLabel lblNivel = new JLabel("NIVEL");
		lblNivel.setBounds(255, 156, 46, 14);
		panel.add(lblNivel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"selecionar nivel..", "facil", "medio ", "dificil"}));
		comboBox.setBounds(200, 185, 182, 20);
		panel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("NOME");
		lblNewLabel.setBounds(255, 100, 46, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(213, 125, 146, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnRanking = new JButton("ranking");
		btnRanking.setBounds(249, 245, 89, 23);
		panel.add(btnRanking);
		
		JButton btnIniciar = new JButton("iniciar");
		btnIniciar.setBounds(249, 303, 89, 23);
		panel.add(btnIniciar);
	}
}
