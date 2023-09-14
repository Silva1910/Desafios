package desafioThread2.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldNumero3;
    private JTextField textFieldNumero2;
    private JTextField textFieldNumero1;
    private JLabel resultadoLabel;
    private JButton ButtonJogar;
    private Timer resultadoTimer;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame frame = new Frame();
                    frame.setVisible(true);
                    frame.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textFieldNumero1 = new JTextField();
        textFieldNumero1.setBounds(36, 96, 86, 68);
        contentPane.add(textFieldNumero1);
        textFieldNumero1.setColumns(10);

        textFieldNumero2 = new JTextField();
        textFieldNumero2.setBounds(163, 96, 86, 68);
        contentPane.add(textFieldNumero2);
        textFieldNumero2.setColumns(10);

        textFieldNumero3 = new JTextField();
        textFieldNumero3.setBounds(285, 96, 86, 68);
        contentPane.add(textFieldNumero3);
        textFieldNumero3.setColumns(10);

        resultadoLabel = new JLabel("");
        resultadoLabel.setBounds(150, 180, 150, 30);
        contentPane.add(resultadoLabel);

        ButtonJogar = new JButton("JOGAR");
        ButtonJogar.setBounds(313, 208, 89, 23);
        contentPane.add(ButtonJogar);

        resultadoTimer = new Timer(5000, new ActionListener() { // 5000 milissegundos (5 segundos)
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoLabel.setText(""); // Limpar o texto do resultado após o tempo definido
            }
        });
        resultadoTimer.setRepeats(false);

        ButtonJogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Desativar o botão para evitar cliques repetidos
                ButtonJogar.setEnabled(false);

                // Usar SwingWorker para realizar operações assíncronas
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        // Crie threads independentes para atualizar os TextField
                        Thread thread1 = new NumeroThread(textFieldNumero1);
                        Thread thread2 = new NumeroThread(textFieldNumero2);
                        Thread thread3 = new NumeroThread(textFieldNumero3);

                        // Inicie as threads
                        thread1.start();
                        thread2.start();
                        thread3.start();

                        // Aguarde até que as threads terminem
                        thread1.join();
                        thread2.join();
                        thread3.join();

                        // Verifique se todos os números são iguais
                        boolean resultado = (textFieldNumero1.getText().equals(textFieldNumero2.getText())
                                && textFieldNumero2.getText().equals(textFieldNumero3.getText()));

                        // Atualize a mensagem de resultado com base no resultado
                        if (resultado) {
                            resultadoLabel.setText("VENCEDOR!");
                        } else {
                            resultadoLabel.setText("PERDEU!");
                        }

                        // Inicie o temporizador para limpar o resultado após 5 segundos
                        resultadoTimer.restart();

                        return null;
                    }

                    @Override
                    protected void done() {
                        // Reative o botão após as threads e a verificação
                        ButtonJogar.setEnabled(true);
                    }
                };

                // Execute o SwingWorker
                worker.execute();
            }
        });
    }
}


