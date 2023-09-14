package desafioThread.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;
import desafioThread.controller.Frame;
public class CarroThread extends Thread {
    private JLabel labelCarro;
    private Color cor;
    public static boolean venceu = false;

    public CarroThread(JLabel labelCarro, Color cor) {
        this.labelCarro = labelCarro;
        this.cor = cor;
    }

    @Override
    public void run() {
        moveCarro();
    }

    public void moveCarro() {
        Rectangle posicao = labelCarro.getBounds();
        double tela = 350;
        double fim = tela;
        int velocidade = 20; // Ajuste a velocidade conforme necessário

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (posicao.getX() >= fim) {
                    apresenta();
                    ((Timer) e.getSource()).stop();
                } else {
                    double distancia = Math.random() * velocidade;

                    int novaX = (int) (posicao.getX() + distancia);
                    int novaY = (int) posicao.getY();
                    Point novaPosicao = new Point(novaX, novaY);
                    posicao.setLocation(novaPosicao);
                    labelCarro.setBounds(posicao);
                }
            }
            public synchronized void apresenta() {
                if (posicao.getX() >= fim) {
                    String corText = getColorText();
                    if (Frame.VencedorTextField.getText().isEmpty()) {
                        Frame.VencedorTextField.setText(labelCarro.getText() + " "  + corText);
                    } else {
                        Frame.PerdedorTextField.setText(labelCarro.getText() + " " + corText);
                    }
                }
            }

            private String getColorText() {
                if (cor.equals(Color.RED)) {
                    return "Vermelho";
                } else if (cor.equals(Color.BLUE)) {
                    return "Azul";
                } else {
                    return "Outra cor"; // Você pode adicionar outras cores conforme necessário
                }
            }});

        timer.start();
    
    
    }
    
        
   

	public Color getCor() {
        return cor;
    }
}