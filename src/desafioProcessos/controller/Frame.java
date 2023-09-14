package desafioProcessos.controller;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JFileChooser;

public class Frame extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField textField;
    private JButton ButtonCancelar;
    private JButton ButtonProcurar;
    private JButton ButtonOk;
    private String caminhoArquivoSelecionado = "";

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
        setBounds(100, 100, 450, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        textField = new JTextField();
        textField.setBounds(30, 40, 300, 20);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        ButtonCancelar = new JButton("Cancelar");
        ButtonCancelar.addActionListener(this);
        ButtonCancelar.setBounds(335, 77, 89, 23);
        contentPane.add(ButtonCancelar);

        ButtonProcurar = new JButton("Procurar");
        ButtonProcurar.addActionListener(this);
        ButtonProcurar.setBounds(173, 77, 89, 23);
        contentPane.add(ButtonProcurar);

        ButtonOk = new JButton("Ok");
        ButtonOk.addActionListener(this);
        ButtonOk.setBounds(10, 77, 89, 23);
        contentPane.add(ButtonOk);

        JLabel lblNewLabel = new JLabel("Digite o caminho do executável ou clique em procurar");
        lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        lblNewLabel.setBounds(26, 11, 379, 23);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(93, 43, 278, 23);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Abrir:");
        lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(10, 47, 73, 19);
        contentPane.add(lblNewLabel_1);
    }

    public void actionPerformed(ActionEvent e) {
        Metodos metodos = new Metodos();

        if (e.getSource() == ButtonProcurar) {
            caminhoArquivoSelecionado = metodos.ProcuraArquivo();
            textField.setText(caminhoArquivoSelecionado);
        }

        if (e.getSource() == ButtonOk) {
            String caminhoArquivo = textField.getText().trim(); // Obtém o texto do campo e remove espaços em branco.
            if (!caminhoArquivo.isEmpty()) {
                metodos.executaOk(caminhoArquivo);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Digite o caminho do arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == ButtonCancelar) {
            this.dispose();
        }
    }
}
