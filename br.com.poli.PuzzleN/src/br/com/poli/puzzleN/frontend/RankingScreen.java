package br.com.poli.puzzleN.frontend;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.poli.puzzleN.engine.Puzzle;
import br.com.poli.puzzleN.engine.Ranking;

public class RankingScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private ArrayList<JButton> jogos;
    private PuzzleFrame frame;

    public RankingScreen(PuzzleFrame frame) {
        
        this.frame = frame;
        jogos = new ArrayList<JButton>();

        for (int i = 0; i < Ranking.size(); i++) {

            Puzzle partida = Ranking.get(i);
            String nome = " Nome: " + partida.getJogador().getNome();
            String pontos = "Pontos:" + Integer.toString(partida.getScore().getPontos());
            jogos.add(new GameInfoButton(nome + "          " + pontos, partida, i));
            this.add(jogos.get(i));
        }
        this.add(new BackButton(frame));
        this.add(new CleanRankingButton(frame));
        this.add(new BackGround());
    }

    private class GameInfoButton extends JButton {
        private static final long serialVersionUID = 1L;

        public GameInfoButton(String text, Puzzle partida, int index) {
            super(index + 1 + "º-" + text);
            this.setBounds(200, 50 + (31 * index), 400, 30);
            this.setBackground(Color.BLACK);
            this.setForeground(Color.WHITE);
            //this.setBorderPainted(false);
            this.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent a) {
                    frame.setPartida(Ranking.get(index));
                    RankingScreen.this.frame.updateTela(new InfoGame(RankingScreen.this.frame));
                }
            });
        }
    }
}