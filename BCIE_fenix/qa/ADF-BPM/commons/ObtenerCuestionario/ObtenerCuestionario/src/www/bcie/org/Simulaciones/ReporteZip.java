package www.bcie.org.Simulaciones;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import org.docx4j.wml.RFonts;

import org.w3c.dom.Document;

import org.xml.sax.InputSource;

public class ReporteZip {
    public ReporteZip() {
        super();
    }

    public static void main(String[] args) throws IOException,Exception {
        ReporteZip main = new ReporteZip();
        String word = "";
        String output = "";
        String template = "";


        if (args.length < 4 || args.length > 4) {
            System.err.println("Argumentos incorrectos, se esperaba una de las siguientes estructura: ");
            System.err.println("    -w Ruta_Word -o Ruta_Salida");
            System.err.println("    -t Ruta_Plantilla_Con_Document_XML -o Ruta_Salida");
            return;
        }

        switch (args[0].toLowerCase()) {
        case "-w":
            word = args[1];
            break;
        case "-t":
            template = args[1];
            break;
        case "-o":
            output = args[1];
            break;
        }

        switch (args[2].toLowerCase()) {
        case "-w":
            word = args[3];
            break;
        case "-t":
            template = args[3];
            break;
        case "-o":
            output = args[3];
            break;
        }

        if (output.isEmpty()) {
            System.err.println("Debe indicar la ruta y nombre del documento de salida.");
        }

        XWPFDocument wordXWPF = null;

        if (!word.isEmpty()) {
            //Se arma la ruta del documento
            File wordDocument = new File(word);

            FileInputStream input = new FileInputStream(wordDocument);
            //Convertir el docx a pdf

            // 1) Se pasa el docx a POI XWPFDocument
            wordXWPF = new XWPFDocument(input);
        }
        else if(!template.isEmpty())
        {
            File directoryToZip = new File(template);
            ByteArrayOutputStream outZipFileStream;
            List<File> fileList = new ArrayList<File>();
            System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
            getAllFiles(directoryToZip, fileList);
            
            outZipFileStream = makeZipFile(directoryToZip,null, fileList);
            
            
            System.out.println("---Tamaño del zip: " + outZipFileStream.size());
            System.out.println("---Done");
            
            
            wordXWPF = new XWPFDocument(new ByteArrayInputStream(outZipFileStream.toByteArray()));
        }
        
        File wordDocument = new File(word);
        FileInputStream input = new FileInputStream(wordDocument);
        File outputFile = new File(output);
        OutputStream out = new FileOutputStream(outputFile);
                
        //controllar exception
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(wordDocument);

        //Mapeo de la fuente Cambria
        Mapper fontMapper = new IdentityPlusMapper();
        wordMLPackage.setFontMapper(fontMapper);

        //Añadir Fuente Calibri
        String fontFamily = "Calibri";        
        URL calibriUrl = main.getFileFromResources("CALIBRI.TTF");
        
        PhysicalFonts.addPhysicalFonts(fontFamily, calibriUrl);
        PhysicalFont calibriFont = PhysicalFonts.get(fontFamily);
        fontMapper.put(fontFamily, calibriFont);
        
        RFonts rfonts = Context.getWmlObjectFactory().createRFonts();
        rfonts.setAscii(fontFamily);
        rfonts.setAsciiTheme(null);
        wordMLPackage.getMainDocumentPart().getPropertyResolver()
                     .getDocumentDefaultRPr().setRFonts(rfonts);
        
        //Añadir Fuente Calibri Bold
        String fontFamilyBold = "Calibri-Bold";        
        URL calibriBoldUrl = main.getFileFromResources("CALIBRIB.TTF");
        
        PhysicalFonts.addPhysicalFonts(fontFamilyBold, calibriBoldUrl);
        PhysicalFont calibriBoldFont = PhysicalFonts.get(fontFamily);
        fontMapper.put(fontFamilyBold, calibriBoldFont);
        
        RFonts rfonts2 = Context.getWmlObjectFactory().createRFonts();
        rfonts2.setAscii(fontFamilyBold);
        rfonts2.setAsciiTheme(null);
        wordMLPackage.getMainDocumentPart().getPropertyResolver()
                     .getDocumentDefaultRPr().setRFonts(rfonts2);


        FOSettings foSettings = Docx4J.createFOSettings();
        foSettings.setWmlPackage(wordMLPackage);
        
        Docx4J.toFO(foSettings, out,Docx4J.FLAG_EXPORT_PREFER_XSL);
        out.close();

    }
    
    private URL getFileFromResources(String fileName) {

            ClassLoader classLoader = getClass().getClassLoader();

            URL resource = classLoader.getResource(fileName);
            
            return resource;

        }


    private static Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeXmlDocumentToXmlFile(Document xmlDocument, String fileName) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();

            //Uncomment if you do not require XML declaration
            //transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            //Write XML to file
            FileOutputStream outStream = new FileOutputStream(new File(fileName));

            transformer.transform(new DOMSource(xmlDocument), new StreamResult(outStream));
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ByteArrayOutputStream writeXmlDocumentToByteArray(Document xmlDocument) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        ByteArrayOutputStream outputDocument;
        try {
            transformer = tf.newTransformer();

            outputDocument = new ByteArrayOutputStream();

            transformer.transform(new DOMSource(xmlDocument), new StreamResult(outputDocument));
        } catch (TransformerException e) {
            e.printStackTrace();
            outputDocument = null;
        } catch (Exception e) {
            e.printStackTrace();
            outputDocument = null;
        }
        return outputDocument;
    }


    public static void getAllFiles(File dir, List<File> fileList) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                fileList.add(file);
                if (file.isDirectory()) {
                    System.out.println("directory:" + file.getCanonicalPath());
                    getAllFiles(file, fileList);
                } else {
                    System.out.println("     file:" + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ByteArrayOutputStream makeZipFile(File directoryToZip, ByteArrayInputStream document,
                                                    List<File> fileList) {
        ByteArrayOutputStream baos;
        try {
            baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);

            for (File file : fileList) {
                if (!file.isDirectory()) { // we only zip files, not directories
                    addToZip(directoryToZip, file, zos);
                }
            }
            
            if(document != null)
            {
                 //posterior a que se han agregado al zip los archivos de la carpeta de la plantilla, se agrega el documento xml
                addStreamToZip(document, "word" + File.separator + "document.xml", zos);
            }

            zos.close();
            baos.close();
        } catch (FileNotFoundException e) {
            baos = null;
            e.printStackTrace();
        } catch (IOException e) {
            baos = null;
            e.printStackTrace();
        }

        return baos;
    }

    public static void writeZipFile(File directoryToZip, List<File> fileList) {

        try {
            FileOutputStream fos =
                new FileOutputStream("C:\\JdevBPM\\mywork\\mywork\\DESARROLLO\\FENIX\\BCIE_REPORTS\\AvisoCobroComision\\" +
                                     directoryToZip.getName() + ".docx");
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (File file : fileList) {
                if (!file.isDirectory()) { // we only zip files, not directories
                    addToZip(directoryToZip, file, zos);
                }
            }

            zos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
                                                                                            IOException {

        FileInputStream fis = new FileInputStream(file);

        // we want the zipEntry's path to be a relative path that is relative
        // to the directory being zipped, so chop off the rest of the path
        String zipFilePath =
            file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
                                              file.getCanonicalPath().length());
        System.out.println("Writing '" + zipFilePath + "' to zip file");
        ZipEntry zipEntry = new ZipEntry(zipFilePath);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }

    public static void addStreamToZip(ByteArrayInputStream file, String filename,
                                      ZipOutputStream zos) throws FileNotFoundException, IOException {


        System.out.println("Writing '" + filename + "' to zip file");
        ZipEntry zipEntry = new ZipEntry(filename);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = file.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        file.close();
        zos.closeEntry();
    }

}
