/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorqrcode.business;

import geradorqrcode.model.EntidadeNegocio;
import geradorqrcode.model.QrCode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 *
 * @author Noriaki
 */
public class ImprimeCodigo implements IStrategy, Printable {

    private static ImageIcon image;

    @Override
    public String execute(EntidadeNegocio entidade) {
        QrCode qrcode = (QrCode) entidade;
        image = new ImageIcon(qrcode.getCaminho());
        try {
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn);                                       // L&F
        } catch (Exception cnf) {
        }
        PrinterJob job = PrinterJob.getPrinterJob();
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PageFormat pf = job.pageDialog(aset);
        job.setPrintable(new ImprimeCodigo(), pf);
        boolean ok = job.printDialog(aset);
        if (ok) {
            try {
                job.print(aset);
                return "Imprimindo...";
            } catch (PrinterException ex) {
                // job nao completado
            }
        }
        return null;
    }

    public int print(Graphics g, PageFormat pf, int page) throws
            PrinterException {

        if (page > 0) { /* Apenas uma unica pagina, page eh zero */

            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        double pageWidth = pf.getImageableWidth();
        double pageHeight = pf.getImageableHeight();
        double imageWidth = image.getIconWidth();
        double imageHeight = image.getIconHeight();
        double scaleX = pageWidth / imageWidth;
        double scaleY = pageHeight / imageHeight;
        double scaleFactor = Math.min(scaleX, scaleY);
        g2d.scale(scaleFactor, scaleFactor);
        g.drawImage(image.getImage(), 0, 0, null);

        //g.drawString("Test the print dialog!", 100, 100);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
}
