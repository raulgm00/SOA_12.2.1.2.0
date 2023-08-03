package www.bcie.org.Simulaciones;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;

import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import org.xml.sax.InputSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class AvisoCobroComisionDo {

    private static String Plantillas = "C:\\JdevBPM\\mywork\\mywork\\DESARROLLO\\FENIX\\BCIE_REPORTS\\";
    private static String Plantilla = "AvisoCobroComision";

    public AvisoCobroComisionDo() {
        super();
    }

    public static void main(String[] args) throws IOException {
        
        System.out.println(AvisoCobroComisionContent.Document);

        //Se arma la ruta del documento
        File directoryToZip = new File(Plantillas + Plantilla);

        //Se convierte el string del XML recibido a un documento XML
        Document doc = convertStringToXMLDocument(AvisoCobroComisionContent.Document);

        //Se pasa el XML a un byte array output para posterior procesamiento logico
        ByteArrayOutputStream outDocument = writeXmlDocumentToByteArray(doc);
        ByteArrayOutputStream outZipFileStream;
        ByteArrayOutputStream outPDFFileStream = new ByteArrayOutputStream();
        
        //se escribe temporalmente el documento en la ruta
        //writeXmlDocumentToXmlFile(doc, document);

        List<File> fileList = new ArrayList<File>();
        System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
        getAllFiles(directoryToZip, fileList);
        
        //se crea el archivo zip fisicamente
        //System.out.println("---Creating zip file In Directory");
        //writeZipFile(directoryToZip, fileList);
        
        //se crea el archivo zip en streams
        System.out.println("---Creating zip file In Stream");
        outZipFileStream = makeZipFile(directoryToZip,new ByteArrayInputStream(outDocument.toByteArray()), fileList);
        
        System.out.println("---Tamaño del zip: " + outZipFileStream.size());
        System.out.println("---Done");
        
        //se convierte el output stream a base 64
       // System.out.println("---Word");
       // System.out.println(new Base64().encodeBase64String(outZipFileStream.toByteArray()));
        
        
        //Convertir el docx a pdf
        
        // 1) Se pasa el docx a POI XWPFDocument
        XWPFDocument wordXWPF = new XWPFDocument(new ByteArrayInputStream(outZipFileStream.toByteArray()));

        // 2) Convert POI XWPFDocument 2 PDF with iText
        PdfOptions options = PdfOptions.create().fontEncoding("utf-8");
        
        //El pdf options se deja en null, al utilizar utf-8 no devolvia las tildes
        PdfConverter.getInstance().convert(wordXWPF, outPDFFileStream, null);
        
        System.out.println("---Pdf");
        System.out.println(new Base64().encodeBase64String(outPDFFileStream.toByteArray()));
            
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
    
    public static ByteArrayOutputStream makeZipFile(File directoryToZip, ByteArrayInputStream document, List<File> fileList) {
        ByteArrayOutputStream baos;
        try {
            baos =  new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);

            for (File file : fileList) {
                if (!file.isDirectory()) { // we only zip files, not directories
                    addToZip(directoryToZip, file, zos);
                }
            }
            
            //posterior a que se han agregado al zip los archivos de la carpeta de la plantilla, se agrega el documento xml
            addStreamToZip(document, "word"+File.separator +"document.xml", zos);

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

    public static void writeZipFile(File directoryToZip,  List<File> fileList) {

        try {
            FileOutputStream fos = new FileOutputStream("C:\\JdevBPM\\mywork\\mywork\\DESARROLLO\\FENIX\\BCIE_REPORTS\\AvisoCobroComision\\" + directoryToZip.getName() + ".docx");
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
    
    public static void addStreamToZip(ByteArrayInputStream file,String filename , ZipOutputStream zos) throws FileNotFoundException,
                                                                                            IOException {


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
