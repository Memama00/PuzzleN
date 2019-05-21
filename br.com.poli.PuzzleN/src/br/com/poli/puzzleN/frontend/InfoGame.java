package br.com.poli.puzzleN.frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InfoGame extends JPanel {

    private static final long serialVersionUID = 1L;

    public InfoGame(PuzzleFrame frame) {
        super();
        JLabel[][] infoIcons = new JLabel[2][4];
        String[][] text = new String[][] { 
            { "Nome:", "Movimentos:", "Dificuldade:", "Pontos:" },
            { frame.getPartida().getJogador().getNome()
            , Integer.toString(frame.getPartida().getQuantidadeMovimentos())
            , frame.getPartida().getDificuldade().toString()
            , Integer.toString(frame.getPartida().getScore().pontos(frame.getPartida())) }
        };
        for (int j = 0; j < 2; j++)
            for (int i = 0; i < 4; i++) {
                infoIcons[j][i] = new JLabel(text[j][i]);
                // if(i > 1){//820 x 600
                infoIcons[j][i].setBounds(50 + (320 * j), 40 + (70 * i), 220, 40);
                infoIcons[j][i].setHorizontalAlignment(SwingConstants.LEFT);
                infoIcons[j][i].setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 32));
                infoIcons[j][i].setForeground(j == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                this.add(infoIcons[j][i]);
            }
        JButton next = new JButton("Voltar");
        next.setBounds(640, 500, 100, 30);

        next.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel tela = (frame.getLastTela().getClass() == Game.class) ? new Menu(frame) : frame.getLastTela();
                frame.updateTela(tela);

            }
        });
        this.add(next);
        this.add(new BackGround());
    }
}
