/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www.bcie.org.Utils;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

import java.awt.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author salvador-fajardo
 */
public class Utils {

    private BaseFont bfBold;
    //public static final String PROPERTIES_RESOURCES="/home/oracle/templates_fenix/JasperResources/properties/rutas.properties";
    //public static final String PROPERTIES_RESOURCES = "C:\\BCIE\\properties\\rutas.properties";
    public static final String PROPERTIES_RESOURCES = "oracle/middleware/templates_fenix/JasperResources/properties/rutas.properties";

    private Properties propiedades = new Properties();

    public boolean escribeArchivo(byte[] arrayArchivo, String path) {

        boolean bandera = false;
        try {

            File archivo = new File(path);
            FileOutputStream fis = new FileOutputStream(archivo);
            fis.write(arrayArchivo);
            fis.close();
            bandera = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null,
                                                        ex);
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null,
                                                        ex);
        }
        return bandera;
    }

    public void generaCabecera(Document doc, PdfContentByte cb) {

        try {

            cb.setLineWidth(1f);
            propiedades.load(new FileInputStream(PROPERTIES_RESOURCES));

            String rutaImage = propiedades.getProperty("rutaImage");
            //se añade logotipo de BCIE
            Image companyLogo = Image.getInstance(rutaImage + "ee.jpg");
            companyLogo.setAbsolutePosition(25, 700);
            companyLogo.scalePercent(50);
            doc.add(companyLogo);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void generaPiePagina(Document doc, PdfContentByte cb) {

        try {

            cb.setLineWidth(1f);
            propiedades.load(new FileInputStream(PROPERTIES_RESOURCES));

            String rutaImage = propiedades.getProperty("rutaImage");

            //se añade logotipo de BCIE
            Image companyLogo = Image.getInstance(rutaImage + "bx.jpg");
            companyLogo.setAbsolutePosition(25, 20);
            companyLogo.scalePercent(60);
            doc.add(companyLogo);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void creaCampos(PdfContentByte cb, float x, float y, String text) {

        initializeFonts();
        cb.beginText();
        cb.setFontAndSize(bfBold, 10);
        Color color = new Color(0x1B83EC);
        cb.setColorFill(color);
        cb.setTextMatrix(x, y);
        cb.showText(text.trim());
        cb.endText();

    }

    public void creaDescripcionCampos(PdfContentByte cb, float x, float y,
                                      String text) {

        if (text != null) {
            initializeFonts();
            cb.beginText();
            cb.setFontAndSize(bfBold, 10);
            //Color color= new Color(0x1B83EC);
            cb.setColorFill(Color.BLACK);
            cb.setTextMatrix(x, y);
            cb.showText(text.trim());
            cb.endText();
        }
    }

    private void initializeFonts() {


        try {
            bfBold =
                    BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

            // bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static String getPROPERTIES_RESOURCES() {
        return PROPERTIES_RESOURCES;
    }

    public void setPropiedades(Properties propiedades) {
        this.propiedades = propiedades;
    }

    public Properties getPropiedades() {
        return propiedades;
    }
}
