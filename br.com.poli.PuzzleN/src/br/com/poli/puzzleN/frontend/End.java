package br.com.poli.puzzleN.frontend;

<<<<<<< HEAD
=======

>>>>>>> master
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
=======

>>>>>>> master
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

<<<<<<< HEAD
=======
import br.com.poli.puzzleN.engine.Puzzle;
>>>>>>> master

public class End extends JPanel {

    private static final long serialVersionUID = 1L;

<<<<<<< HEAD
    public End(PuzzleFrame frame) {
        super();
        JLabel[][] endIcons = new JLabel[2][4];
        String[][] text = new String[][] { 
            { "Nome:", "Movimentos:", "Dificuldade:", "Pontos:" },
            { frame.getPartida().getJogador().getNome()
            , Integer.toString(frame.getPartida().getQuantidadeMovimentos())
            , frame.getPartida().getDificuldade().toString()
            , Integer.toString(frame.getPartida().getScore().pontos(frame.getPartida())) } 
=======
    public End(Puzzle partida, PuzzleFrame frame) {
        super();

        JLabel[][] endIcons = new JLabel[2][4];
        String[][] text = new String[][] { 
            { "Nome:", "Movimentos:", "Dificuldade:", "Pontos:" },
            { partida.getJogador().getNome()
            , Integer.toString(partida.getQuantidadeMovimentos())
            , partida.getDificuldade().toString()
            , Integer.toString(partida.getScore().pontos(partida)) } 
>>>>>>> master
        };
        
        for (int j = 0; j < 2; j++)
            for (int i = 0; i < 4; i++) {
                endIcons[j][i] = new JLabel(text[j][i]);
                // if(i > 1){//820 x 600
                endIcons[j][i].setBounds(50 + (320 * j), 40 + (70 * i), 220, 40);
                endIcons[j][i].setHorizontalAlignment(SwingConstants.LEFT);
                endIcons[j][i].setFont(new Font("Franklin Gothic Medium", Font.ITALIC, 32));
                endIcons[j][i].setForeground(j == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                this.add(endIcons[j][i]);
            }
<<<<<<< HEAD
        JButton next = new JButton("Voltar");
        next.setBounds(640, 500, 100, 30);
=======
        JButton next = new JButton("Voltar ao menu");
        next.setBounds(600, 500, 160, 30);
>>>>>>> master
        next.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
                JPanel tela = (frame.getLastTela().getClass() == Game.class) ? new Menu(frame) : frame.getLastTela();
                frame.updateTela(tela);
=======
                frame.updateTela(new Menu(partida, frame));
>>>>>>> master
            }
        });
        this.add(next);
        this.add(new BackGround());
    }
}
