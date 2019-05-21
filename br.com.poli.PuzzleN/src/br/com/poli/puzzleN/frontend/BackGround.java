package br.com.poli.puzzleN.frontend;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BackGround extends JLabel {
	private static final long serialVersionUID = 1L;

	public BackGround() {
		super();
		super.setIcon(new ImageIcon("/home/gustavo/Área de Trabalho/PuzzleN/br.com.poli.PuzzleN/giphy.gif"));
		super.setBounds(0, 0, 820, 600);
		super.setHorizontalAlignment(SwingConstants.CENTER);

	}
	/*
	private static final long serialVersionUID = 1L;
	private BufferedImage img = null;
	private int x = 0;
	private int y = 0;

	public void FundoBg() throws IOException {
		this.img = ImageIO.read(new File("C:\\Users\\vinic\\OneDrive\\\u00C1rea de Trabalho\\PuzzleN-master\\giphy.gif"));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics gr = (Graphics2D) g.create();
		gr.drawImage(img, x, y, this.getWidth(), this.getHeight(), this);
		gr.dispose();
	}
	/*
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 800, 600);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JLabel label = new JLabel("");
	label.setIcon(new ImageIcon("C:\\Users\\vinic\\OneDrive\\\u00C1rea de Trabalho\\PuzzleN-master\\giphy.gif"));
	label.setBounds(0, 0, 800, 600);
	label.setHorizontalAlignment(SwingConstants.CENTER);
	contentPane.add(label);
	*/
}