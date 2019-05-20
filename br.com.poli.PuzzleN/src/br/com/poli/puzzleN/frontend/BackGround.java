package br.com.poli.puzzleN.frontend;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackGround extends JPanel {
    private static final long serialVersionUID = 1L;
    private BufferedImage img = null;
    private int x = 0;
    private int y = 0;

    public void FundoBg(String urlImg) throws IOException {
        this.img = ImageIO.read(new File(urlImg));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics gr = (Graphics2D) g.create();
        gr.drawImage(img, x, y, this.getWidth(), this.getHeight(), this);
        gr.dispose();
    }
}