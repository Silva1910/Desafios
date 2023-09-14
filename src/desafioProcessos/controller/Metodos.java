package desafioProcessos.controller;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Metodos {

    public String ProcuraArquivo() {
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Executable file (.exe)", "exe");

        String diretorioBase = System.getProperty("user.home") + "/Desktop";
        File dir = new File(diretorioBase);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(dir);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filtro);

        String caminhoArquivo = "";
        int retorno = fileChooser.showOpenDialog(null);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            caminhoArquivo = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado", "Run File - Windows", JOptionPane.ERROR_MESSAGE);
        }

        return caminhoArquivo;
    }

    public void executaOk(String caminhoArquivo) {
        if (caminhoArquivo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Entrada inválida", "Run File - Windows", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Runtime.getRuntime().exec(caminhoArquivo); // Executa o arquivo diretamente
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar o arquivo", "Run File - Windows", JOptionPane.ERROR_MESSAGE);
        }
    }
}