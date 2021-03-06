package br.com.poli.puzzleN.frontend.screens;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.poli.puzzleN.frontend.buttons.BackButton;

public class InfoGame extends JPanel {

    private static final long serialVersionUID = 1L;

    public InfoGame(PuzzleFrame frame) {
        super();
        if (frame.getLastTela().getClass() != RankingScreen.class && !frame.getPartida().getVenceu()
                && SondTrack.isPlaying())
            SondTrack.changeTrack("LoseTrack");
        JLabel[][] infoIcons = new JLabel[2][5];
        String[][] text = new String[][] { { "Nome:", "Movimentos:", "Dificuldade:", "Pontos:", "Tempo:" },
                { frame.getPartida().getJogador().getNome(),
                        Integer.toString(frame.getPartida().getQuantidadeMovimentos()),
                        frame.getPartida().getDificuldade().toString(),
                        Integer.toString(frame.getPartida().getScore().getPontos()),
                        Integer.toString((int) frame.getPartida().getTempoDecorrido() / 1) + "m"
                                + Float.toString(
                                        (float) ((int) ((frame.getPartida().getTempoDecorrido() % 1) * 6000)) / 100)
                                + "s" } };
        for (int j = 0; j < 2; j++)
            for (int i = 0; i < 5; i++) {
                infoIcons[j][i] = new JLabel(text[j][i]);
                infoIcons[j][i].setBounds(50 + (300 * j), 40 + (70 * i), 26 * text[j][i].length(), 40);
                infoIcons[j][i].setHorizontalAlignment(SwingConstants.LEFT);
                infoIcons[j][i].setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 32));
                infoIcons[j][i].setForeground(j == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                this.add(infoIcons[j][i]);
            }
        JLabel vitoria = new JLabel(frame.getPartida().getVenceu() ? "Completo!" : "Desistencia");
        vitoria.setBounds(50, 450, 42 * vitoria.getText().length(), 50);
        vitoria.setFont(new Font("Impact", Font.BOLD + Font.ITALIC, 55));
        vitoria.setForeground(Color.WHITE);
        this.add(vitoria);
        this.add(new BackButton(frame));
        this.add(new BackGround());
        // frame.setPartida(null);
    }
}
