package desafioThread.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {

    private JPanel contentPane;
    static JTextField VencedorTextField;
    static JTextField PerdedorTextField;
    private JLabel LabelCarro1;
    private JLabel LabelCarro2;
    private JButton ButtonCorrer;
    private JLabel LabelPerdedor;
    private JLabel LabelVencedor;
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame frame = new Frame();
                    frame.setResizable(false);
                    frame.setVisible(true);
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
        contentPane.setBackground(SystemColor.menu);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        LabelCarro1 = new JLabel("CARRO 1");
        LabelCarro1.setForeground(Color.RED);
        LabelCarro1.setFont(new Font("Arial Black", Font.PLAIN, 16));
        LabelCarro1.setBounds(10, 39, 91, 14);
        contentPane.add(LabelCarro1);

        LabelCarro2 = new JLabel("CARRO 2");
        LabelCarro2.setForeground(Color.BLUE);
        LabelCarro2.setFont(new Font("Arial Black", Font.PLAIN, 16));
        LabelCarro2.setBounds(10, 125, 128, 14);
        contentPane.add(LabelCarro2);

        ButtonCorrer = new JButton("CORRER");
        ButtonCorrer.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ButtonCorrer.setBounds(12, 183, 89, 23);
        contentPane.add(ButtonCorrer);

        VencedorTextField = new JTextField();
        VencedorTextField.setBounds(299, 160, 125, 20);
        contentPane.add(VencedorTextField);
        VencedorTextField.setColumns(10);

        PerdedorTextField = new JTextField();
        PerdedorTextField.setBounds(299, 200, 125, 20);
        contentPane.add(PerdedorTextField);
        PerdedorTextField.setColumns(10);

        LabelVencedor = new JLabel("VENCEDOR");
        LabelVencedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        LabelVencedor.setBounds(200, 163, 75, 14);
        contentPane.add(LabelVencedor);

        LabelPerdedor = new JLabel("PERDEDOR");
        LabelPerdedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        LabelPerdedor.setBounds(200, 201, 75, 14);
        contentPane.add(LabelPerdedor);

        JLabel lblNewLabel = new JLabel("==================================================");
        lblNewLabel.setBounds(10, 79, 414, 14);
        contentPane.add(lblNewLabel);

        ActionListener correr = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarroThread threadCarroVermelho = new CarroThread(LabelCarro1, Color.RED);
                CarroThread threadCarroAzul = new CarroThread(LabelCarro2, Color.BLUE);

                threadCarroVermelho.start();
                threadCarroAzul.start();

                Frame.VencedorTextField.setText(""); // Exemplo de uso
                Frame.PerdedorTextField.setText("");  // Exemplo de uso
            }
        };
        ButtonCorrer.addActionListener(correr);
    }
}