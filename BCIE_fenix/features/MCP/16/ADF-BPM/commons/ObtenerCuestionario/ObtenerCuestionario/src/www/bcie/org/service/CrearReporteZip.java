package www.bcie.org.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.StringReader;

import java.net.URL;

import java.sql.SQLException;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import net.sf.jasperreports.engine.JRException;

import oracle.adf.share.logging.ADFLogger;

import org.apache.commons.codec.binary.Base64;

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

import www.bcie.org.Simulaciones.AvisoCobroComisionContent;
import www.bcie.org.avisodetallado.Aviso;
import www.bcie.org.javabeans.ReporteResponse;


/**
 * @params
 * Los parámetros @WebService y @BindingType servirán de referencia para poder crear el Webservice
 */
@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class CrearReporteZip {
    private static ADFLogger logger = null;

    public CrearReporteZip() {
        super();

        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }


    /**
     *
     * @param rutaTemplate
     * @param contenidoXML
     * @param formatosSalida
     * @return
     * @throws JRException
     * @throws IOException
     */
    @WebMethod
    public String generarReporte(@WebParam(name = "rutaPlantilla") String RutaPlantilla,
                                 @WebParam(name = "contenidoXML") String ContenidoXML,
                                 @WebParam(name = "formatosSalida")
                                 ArrayList<String> FormatosSalida) throws JRException, IOException {

        logger.info("Inicia el proceso de crear el reporte a travez del ZIP");
        logger.info("   ParametrosRecibidos: ");
        logger.info("       RutaPlantilla: " + RutaPlantilla);
        logger.info("       ContenidoXML: " + ContenidoXML);
        logger.info("       FormatosSalida: " + FormatosSalida.toString());

        ReporteResponse reporte = new ReporteResponse();
        String resultado;
        String mensajeError = null;

        //Se arma la ruta del documento
        File directoryToZip = new File(RutaPlantilla);

        //validar si la ruta de la plantilla existe
        if (!directoryToZip.exists()) 
        {
            mensajeError = "El directorio de la plantilla indicado no existe.";
        } else if (!directoryToZip.canRead()) 
        {
            mensajeError = "El directorio de la plantilla indicado existe pero no puede ser leido.";
        } else if (!directoryToZip.isDirectory()) 
        {
            mensajeError =
                "La ruta indicada no pertenece a un directorio, debe indicar la dirección del directorio de la plantilla a utilizar.";
        }

        //validar el input del contenidoXML
        if (ContenidoXML == null) 
        {
            mensajeError = "El contenido en XML recibido es null, debe enviar un xml correcto para generar el archivo.";
        } else if (ContenidoXML.isEmpty() || ContenidoXML.trim().isEmpty()) 
        {
            mensajeError =
                "El contenido en XML recibido esta vacio, debe enviar un xml correcto para generar el archivo.";
        }

        //Se convierte el string del XML recibido a un documento XML
        Document doc = convertStringToXMLDocument(ContenidoXML);

        //validar el doc generado, si es null ocurrio un error
        if (doc == null) 
        {
            mensajeError = "Ocurrio un error al parsear el documento XML recibido en String a un XML Valido.";
        }

        //Si el mensaje de error es nulo se realiza el proceso principal para la generación del reporte
        if (mensajeError == null) 
        {
            //ByteArraysDeSalida
            ByteArrayOutputStream outDocument = writeXmlDocumentToByteArray(doc);
            ByteArrayOutputStream outZipFileStream;
            ByteArrayOutputStream outPDFFileStream = new ByteArrayOutputStream();

            //Se leen todos los archivos y carpetas existentes
            List<File> fileList = new ArrayList<File>();
            getAllFiles(directoryToZip, fileList);

            //se crea el archivo zip en streams
            outZipFileStream =
                makeZipFile(directoryToZip, new ByteArrayInputStream(outDocument.toByteArray()), fileList);
            logger.info("Tamaño del Zip: " + outZipFileStream.size());

            //si esta vacio se agrega por defecto DOCX
            if (FormatosSalida.isEmpty()) {
                FormatosSalida.add("docx");
            }

            if (FormatosSalida.toString().toLowerCase().contains("docx")) {
                logger.info("Se agrega el reporte en docx");
                reporte.setArchivoReporte(outZipFileStream.toByteArray(), "docx");
            }

            if (FormatosSalida.toString().toLowerCase().contains("pdf")) {
                try
                {
                logger.info("Se genera el PDF");
                outPDFFileStream = generarPDF(outZipFileStream.toByteArray());
                logger.info("Se agrega el PDF a los resultados");
                reporte.setArchivoReporte(outPDFFileStream.toByteArray(), "pdf");
                }
                catch(Exception e)
                {
                    logger.info("Ha ocurrido un error al generar el PDF. Error: " + e.getMessage());
                }
            }
            logger.info("Cantidad Archivos Agregados: " + reporte.getArchivo().size());
            reporte.setResultado("OK");
            reporte.setMensaje("Reporte Generado Exitosamente");
        } else //Se asigna el mensaje de error
        {
            logger.warning("Mensaje de error de respuesta: " + mensajeError);
            reporte.setResultado("ERROR");
            reporte.setMensaje(mensajeError);
        }

        //se genera el string de respuesta
        logger.info("Generar el string de respuesta");
        resultado = reporte.GeneraRespuesta();
        
        logger.info("Resultado: " + resultado);
        logger.info("Finaliza el proceso de crear el reporte a travez del ZIP");

        return resultado;
    }

    private static String encodeByteArray(byte[] input) 
    {
        return new Base64().encodeBase64String(input);
    }

    private ByteArrayOutputStream generarPDF(byte[] docx) throws Exception 
    {
        ByteArrayOutputStream outPDFFileStream = new ByteArrayOutputStream();

        //Convertir el docx a pdf
       
        //1) Se crea el paquete ML
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new ByteArrayInputStream(docx));
        
        
        //Mapeo de la fuente Cambria
        Mapper fontMapper = new IdentityPlusMapper();
        wordMLPackage.setFontMapper(fontMapper);
        String fontFamily = "Calibri";
        URL calibriUrl = getFileFromResources("CALIBRI.TTF");
        
        PhysicalFonts.addPhysicalFonts(fontFamily, calibriUrl);
        PhysicalFont calibriFont = PhysicalFonts.get(fontFamily);
        fontMapper.put(fontFamily, calibriFont);
        
        //Añadir Fuente Calibri Bold
        String fontFamilyBold = "Calibri-Bold";        
        URL calibriBoldUrl = getFileFromResources("CALIBRIB.TTF");
        
        PhysicalFonts.addPhysicalFonts(fontFamilyBold, calibriBoldUrl);
        PhysicalFont calibriBoldFont = PhysicalFonts.get(fontFamily);
        fontMapper.put(fontFamilyBold, calibriBoldFont);
        
        RFonts rfonts = Context.getWmlObjectFactory().createRFonts();
        rfonts.setAscii(fontFamily);
        rfonts.setAsciiTheme(null);
        wordMLPackage.getMainDocumentPart().getPropertyResolver()
                     .getDocumentDefaultRPr().setRFonts(rfonts);
                
        //2) Se generan los settings de FO
        FOSettings foSettings = Docx4J.createFOSettings();
        
        //3) Se asigna el paquete ML
        foSettings.setWmlPackage(wordMLPackage);
        
        //4) Se realiza la conversión y guarda en el outputstream
        Docx4J.toFO(foSettings, outPDFFileStream,Docx4J.FLAG_EXPORT_PREFER_NONXSL);
        
        outPDFFileStream.close();

        return outPDFFileStream;
    }
    
    private URL getFileFromResources(String fileName) {

            ClassLoader classLoader = getClass().getClassLoader();

            URL resource = classLoader.getResource(fileName);
            
            return resource;

        }

    private ByteArrayOutputStream generarPDFOld(byte[] docx) throws IOException 
    {
        ByteArrayOutputStream outPDFFileStream = new ByteArrayOutputStream();

        //Convertir el docx a pdf

        // 1) Se pasa el docx a POI XWPFDocument
        XWPFDocument wordXWPF = new XWPFDocument(new ByteArrayInputStream(docx));

        // 2) Convert POI XWPFDocument 2 PDF with iText
        PdfOptions options = PdfOptions.create().fontEncoding("utf-8");

        //El pdf options se deja en null, al utilizar utf-8 no devolvia las tildes
        PdfConverter.getInstance().convert(wordXWPF, outPDFFileStream, null);

        outPDFFileStream.close();

        return outPDFFileStream;
    }

    private static Document convertStringToXMLDocument(String xmlString) 
    {
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
            logger.warning(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static ByteArrayOutputStream writeXmlDocumentToByteArray(Document xmlDocument) 
    {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        ByteArrayOutputStream outputDocument;
        try {
            transformer = tf.newTransformer();

            outputDocument = new ByteArrayOutputStream();

            transformer.transform(new DOMSource(xmlDocument), new StreamResult(outputDocument));
        } catch (TransformerException e) {
            e.printStackTrace();
            logger.warning(e.getMessage());
            outputDocument = null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning(e.getMessage());
            outputDocument = null;
        }
        return outputDocument;
    }

    public static void getAllFiles(File dir, List<File> fileList) 
    {
        File[] files = dir.listFiles();
        for (File file : files) {
            fileList.add(file);
            if (file.isDirectory()) {
                //System.out.println("directory:" + file.getCanonicalPath());
                getAllFiles(file, fileList);
            } else {
                //System.out.println("     file:" + file.getCanonicalPath());
            }
        }
    }

    public static ByteArrayOutputStream makeZipFile(File directoryToZip, ByteArrayInputStream document,
                                                    List<File> fileList) 
    {
        ByteArrayOutputStream baos;
        try {
            baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);

            for (File file : fileList) {
                if (!file.isDirectory()) { // we only zip files, not directories
                    addToZip(directoryToZip, file, zos);
                }
            }

            //posterior a que se han agregado al zip los archivos de la carpeta de la plantilla, se agrega el documento xml
            addStreamToZip(document, "word" + File.separator + "document.xml", zos);

            zos.close();
            baos.close();
        } catch (FileNotFoundException e) {
            baos = null;
            logger.warning(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            baos = null;
            logger.warning(e.getMessage());
            e.printStackTrace();
        }

        return baos;
    }

    public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
                                                                                            IOException 
    {

        FileInputStream fis = new FileInputStream(file);

        // we want the zipEntry's path to be a relative path that is relative
        // to the directory being zipped, so chop off the rest of the path
        String zipFilePath =
            file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
                                              file.getCanonicalPath().length());
        //System.out.println("Writing '" + zipFilePath + "' to zip file");
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
                                      ZipOutputStream zos) throws FileNotFoundException, IOException 
    {


        //System.out.println("Writing '" + filename + "' to zip file");
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
