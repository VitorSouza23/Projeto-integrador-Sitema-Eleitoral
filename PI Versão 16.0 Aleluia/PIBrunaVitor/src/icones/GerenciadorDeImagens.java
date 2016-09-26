/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icones;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Aluno
 */
public class GerenciadorDeImagens {

    static BufferedImage img = null;
    static BufferedWriter writer = null;
    static File arqImg = null;
    static File arqSaida = null;

    public static byte[] InseririImagem() throws Exception {

        final JFileChooser fc = new JFileChooser();
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagem PNG", "png");
        fc.setFileFilter(filter);
        fc.setDialogTitle("Abrir arquivo de imagem do Candidato");
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            arqImg = fc.getSelectedFile();

        } else {
            throw new Exception("É necessário especificar uma imagem de entrada!");
        }
        if (!arqImg.exists()) {
            throw new Exception("O arquivo selecionado não existe");
        }

        return definirTamanho3X4(arqImg);

    }

    private static byte[] definirTamanho3X4(File arqImg) throws IOException {
        if (arqImg != null) {
            BufferedImage redimencionar = new BufferedImage(110, 150, BufferedImage.TYPE_INT_RGB);
            img = ImageIO.read(arqImg);

            redimencionar.getGraphics().drawImage(img, 0, 0, 110, 150, 0, 0, img.getWidth(), img.getHeight(), null);
            return converterImagemEmArrayDeBytes(redimencionar);
        } else {
            return null;
        }

    }

    private static byte[] converterImagemEmArrayDeBytes(BufferedImage imagem) throws FileNotFoundException, IOException {
//        byte[] b = ((DataBufferByte) (imagem).getRaster().getDataBuffer()).getData();
//        return b;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ImageIO.write(imagem, "png", buffer);
        return buffer.toByteArray();
       
    }

     private static  BufferedImage getBufferedImage (ImageIcon image) {
        int  width = image.getIconWidth();
        int  height = image.getIconHeight();
        BufferedImage bi = new  BufferedImage (width, height, BufferedImage.TYPE_INT_RGB);
        return bi;
        
    }
}
