package desafioThread2.controller;

import javax.swing.JTextField;

class NumeroThread extends Thread {
    private JTextField textField;

    public NumeroThread(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void run() {
        for (int i = 0; i < 150; i++) {
            try {
                // Gere números aleatórios e atualize o campo de texto
                int randomNumber = (int) (Math.random() * 6 + 1);
                textField.setText(Integer.toString(randomNumber));

                // Adicione um pequeno atraso para simular a rolagem
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}