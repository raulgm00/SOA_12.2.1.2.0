package www.bcie.org.javabeans;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.export.Exporter;

import www.bcie.org.avisodetallado.Resumen;
import www.bcie.org.avisovencimiento.Consolidado;
import www.bcie.org.cumplimientocondiciones.Condicion;
import www.bcie.org.cumplimientocondiciones.LstCondicion;
import www.bcie.org.prepago.PrePagoReporte;
import www.bcie.org.recibopago.Recibo;
import www.bcie.org.reporte.Archivo;
import www.bcie.org.reporte.Reporte;
import www.bcie.org.reportenoobjeciones.ReporteTemplate;

public class ReporteResponse extends Reporte {
    private static Connection conn = null;
    private static String Resources = "SELECT VALOR FROM CONFIGURACION WHERE LLAVE LIKE '%REPOSITORIO%'";
    private static String valor = "";
    public ReporteResponse() {
        super();
    }
    
    public String GeneraRespuesta(){
        String ouputXML = "";
        
        try {
                
                JAXBContext jaxbContext = JAXBContext.newInstance(Reporte.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                       
                marshaller.setProperty("jaxb.formatted.output", true);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                marshaller.marshal(this, stream);
                
                ouputXML = stream.toString();
                stream.close();
                
        } catch (JAXBException e) {
                //logger.error(e.getMessage(),e);
        } catch (IOException e) {
               // logger.error(e.getMessage(),e);
        }
        
        return ouputXML;
    }
    
    public String generarRespuestaReporte(byte[] pdfReport, byte[] docxReport) {
        ReporteResponse reportResponse = new ReporteResponse();
        //Reporte rep = new Reporte();
        Archivo archivoDoc = new Archivo();
        archivoDoc.setTipo("docx");
        archivoDoc.setContent(docxReport);
        reportResponse.getArchivo().add(archivoDoc);
        Archivo archivoPDF = new Archivo();
        archivoPDF.setTipo("pdf");
        archivoPDF.setContent(pdfReport);
        reportResponse.getArchivo().add(archivoPDF);
        reportResponse.setResultado("OK");
        reportResponse.setMensaje("Reporte Generado Exitosamente");
        return reportResponse.GeneraRespuesta();
    }
    
    public void setArchivoReporte(byte[] reportFile, String reportFormat) 
    {
        Archivo archivo= new Archivo();
        archivo.setTipo(reportFormat);
        archivo.setContent(reportFile);
        List<Archivo> archivos = this.getArchivo();
        archivos.add(archivo);
        this.setArchivo(archivos);
    }

    public String generarRespuestaReporte(ArrayList<byte[]> lstFilesInterno, ArrayList<byte[]> lstFilesExterno) {
        ReporteResponse reportResponse = new ReporteResponse();
        //Reporte rep = new Reporte();
        Archivo archivoInterno = new Archivo();
        archivoInterno.setTipo("pdf");
        archivoInterno.setContent(lstFilesInterno.get(1));
        reportResponse.getArchivo().add(archivoInterno);
        Archivo archivoCliente = new Archivo();
        archivoCliente.setTipo("pdf");
        archivoCliente.setContent(lstFilesExterno.get(1));
        reportResponse.getArchivo().add(archivoCliente);
        reportResponse.setResultado("OK");
        reportResponse.setMensaje("Reporte Generado Exitosamente");
        return reportResponse.GeneraRespuesta();
    }

    public ArrayList<byte[]> generarReporte(String nombreReporte, List<Consolidado> consolidado,  List<Resumen> resumen,List<Recibo> recibo,List<PrePagoReporte> prePagoReporte,List<ReporteTemplate> reporteTemplate,List<LstCondicion> lstCondicion) {
        BufferedReader br = null;
        HashMap<String, Object> hm = new HashMap<String, Object>();
        ArrayList<byte[]> lstFiles = new ArrayList<byte[]>();
        byte[] pdfReport = null;
        byte[] docxReport = null;
        JasperReport jasperReport;
        JasperPrint jasperPrint = null;
        String resultado = "";
        try {
            conn = getConnection();
            
            PreparedStatement ps = conn.prepareStatement(Resources);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                valor = rs.getString(1);
            }
            Map parameters = new HashMap();
            parameters.put("VALOR", valor);
            jasperReport = JasperCompileManager.compileReport(valor + nombreReporte);
            if(consolidado!=null){
               jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(consolidado));
            }else if(resumen!=null){
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(resumen));
            }else if(recibo!= null){
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(recibo));
            }else if(prePagoReporte != null){
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(prePagoReporte));
            }else if(reporteTemplate != null){
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(reporteTemplate));
            }else if(lstCondicion != null){
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(lstCondicion));
            }
            JRDocxExporter docExporter = new JRDocxExporter();
            ByteArrayOutputStream byteDocExport = new ByteArrayOutputStream();
            docExporter.setParameter(JRDocxExporterParameter.JASPER_PRINT, jasperPrint);
            docExporter.setParameter(JRDocxExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
            docExporter.setParameter(JRDocxExporterParameter.FRAMES_AS_NESTED_TABLES, Boolean.FALSE);
            docExporter.setParameter(JRDocxExporterParameter.FLEXIBLE_ROW_HEIGHT, Boolean.TRUE);
            docExporter.setParameter(JRDocxExporterParameter.CHARACTER_ENCODING, "UTF-8");
            docExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, byteDocExport);
            docExporter.exportReport();
            docxReport = byteDocExport.toByteArray();
            pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
            lstFiles.add(0,docxReport);
            lstFiles.add(1,pdfReport);
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
        }finally{
            
            try{
                if(conn != null){
                    //conn.rollback();                    
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return lstFiles;
    }
    private Connection getConnection(){
        try{
            InitialContext context = new InitialContext();
            DataSource datasource = (DataSource) context.lookup("jdbc/OperacionesDB");
            conn = datasource.getConnection();
        }catch(NamingException nex){
            nex.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
    return conn;
    }

}
