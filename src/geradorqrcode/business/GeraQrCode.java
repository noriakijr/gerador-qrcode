/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorqrcode.business;


import geradorqrcode.model.EntidadeNegocio;
import geradorqrcode.model.QrCode;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author noriaki.odan
 */
public class GeraQrCode implements IStrategy {

    @Override
    public String execute(EntidadeNegocio entidade) {

        QrCode qrCode = (QrCode) entidade;
        // Colocando as informacoes no byteArray
        ByteArrayOutputStream out = QRCode.from(qrCode.getFrase())
                .to(ImageType.PNG).withSize(qrCode.getComprimento(), qrCode.getAltura()).stream();
        
        try {
            // Criar a imagem QRcode no caminho: W:
//            String caminho = getClass().getResource("/geradorqrcode/resources").getPath().replaceFirst("/", "");
            String caminho = System.getProperty("user.dir");
            System.out.println(caminho);
            caminho = new StringBuilder(caminho).append("\\qrcode").append(".PNG").toString();
            FileOutputStream fout = new FileOutputStream(new File(caminho));
            System.out.println(caminho);
            fout.write(out.toByteArray());					// Colocando o byteArray dentro do arquivo
            fout.flush();
            fout.close();
            
            return caminho;                                           // Criado com sucesso
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;                                                           // Erro ao criar QRcode
    }

}
