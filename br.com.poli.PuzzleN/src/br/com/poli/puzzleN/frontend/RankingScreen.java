package br.com.poli.puzzleN.frontend;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
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

        for (int i = 0; i < frame.getRanking().size(); i++) {

            Puzzle partida = frame.getRanking().get(i);
            JLabel nome = new JLabel( 1 + i + " Nome:" + partida.getJogador().getNome());
            JLabel pontos = new JLabel("Pontos:" + Integer.toString(partida.getScore().getPontos()));
            jogos.add(new GameInfo(nome, pontos, i, partida));
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

        public GameInfo(Component text1, Component text2, int index, Puzzle partida) {
            super();
            this.setBounds(100, 50 + (30 * index), 300, 30);

            text1.setBounds(0, 0, 150, 30);
            text2.setBounds(150, 0, 150, 30);

            text1.setForeground(Color.WHITE);
            text2.setForeground(Color.WHITE);
            text1.setBackground(Color.BLACK);
            text2.setBackground(Color.BLACK);
            this.setBackground(Color.BLACK);
            this.add(text1);
            this.add(text2);

            this.setVisible(true);
            this.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent a) {
                    RankingScreen.this.frame.updateTela(new End( RankingScreen.this.frame));
                }
            });
        }
    }
}