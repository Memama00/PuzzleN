package br.com.poli.puzzleN.frontend.screens;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.poli.puzzleN.testes.Main;

/**
 * Loading
 */
public class Loading {

    private static JLabel load;

    public static JLabel peparar() {
        load = new JLabel(new ImageIcon("images/loading.gif"));
        load.setBounds(0, 0, 200, 200);
        load.setForeground(Color.WHITE);
        load.setLocation((Main.janela.getWidth() / 2) - 100, (Main.janela.getHeight() / 2) - 130);
        load.setHorizontalAlignment(SwingConstants.CENTER);
        load.setVerticalAlignment(SwingConstants.CENTER);
        load.setVisible(false);
        return load;
    }

    public static void start() {
        if (load != null)
            load.setVisible(true);
    }

    public static void stop() {
        if (load != null)
            load.setVisible(false);
    }
}