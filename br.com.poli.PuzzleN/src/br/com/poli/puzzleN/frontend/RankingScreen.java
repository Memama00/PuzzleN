package br.com.poli.puzzleN.frontend;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.poli.puzzleN.engine.Puzzle;

public class RankingScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private ArrayList<JButton> jogos;
    private PuzzleFrame frame;

    public RankingScreen(PuzzleFrame frame, String save) {
        
        this.frame = frame;
        jogos = new ArrayList<JButton>();

        for (int i = 0; i < frame.getRanking().read().length; i++) {

            Puzzle partida = frame.getRanking().read()[i];
            String nome = " Nome:" + partida.getJogador().getNome();
            String pontos = "Pontos:" + Integer.toString(partida.getScore().getPontos());
            jogos.add(new GameInfo(nome + "\t" + pontos, partida, i));
            this.add(jogos.get(i));
        }
        JButton next = new JButton("Voltar");
        next.setBounds(640, 500, 100, 30);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.updateTela(new Menu(frame));
            }
        });
        this.add(next);
        this.add(new BackGround());
    }

    private class GameInfo extends JButton {
        private static final long serialVersionUID = 1L;

        public GameInfo(String text, Puzzle partida, int index) {
            super(index + 1 + "º-" + text);
            this.setBounds(200, 50 + (31 * index), 400, 30);
            this.setBackground(Color.BLACK);
            this.setForeground(Color.WHITE);
            //this.setBorderPainted(false);
            this.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent a) {
                    RankingScreen.this.frame.updateTela(new InfoGame(RankingScreen.this.frame));
                }
            });
        }
    }
}