package www.bcie.org.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;

import www.bcie.org.recibopago.Detalles;
import www.bcie.org.recibopago.Recibo;
import www.bcie.org.recibopago.TablaDetalle;

public class ReciboPagoDo {
    
    private static Connection conn = null;
    private static String Resources = "SELECT VALOR FROM CONFIGURACION WHERE LLAVE LIKE '%REPOSITORIO%'";
    private static String valor = "";
    
    public ReciboPagoDo() {
        super();
    }
    
    public static void main (String[] args) {
        
        JasperReport jasperReport;
        JasperPrint jasperPrint = null;
        
        try {
            
            List<Recibo> reciboList = generarReciboPago();
            
            Map parameters = new HashMap();
            parameters.put("VALOR", "C:/Users/urbinaj/Downloads/");
            
            jasperReport = JasperCompileManager.compileReport("C:/Users/urbinaj/Downloads/ReciboPago.jrxml");
            System.out.println("Compiled.");
            
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(reciboList));
            System.out.println("Filled.");
            
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("C:/Users/urbinaj/Downloads/ReportePagoTest" + ".pdf")); // your output goes here
            
            exporter.exportReport();
            System.out.println("Finished.");
            
            /* 
            JRDocxExporter docExporter = new JRDocxExporter();
            ByteArrayOutputStream byteDocExport = new ByteArrayOutputStream();
            docExporter.setParameter(JRDocxExporterParameter.JASPER_PRINT, jasperPrint);
            docExporter.setParameter(JRDocxExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
            docExporter.setParameter(JRDocxExporterParameter.FRAMES_AS_NESTED_TABLES, Boolean.FALSE);
            docExporter.setParameter(JRDocxExporterParameter.FLEXIBLE_ROW_HEIGHT, Boolean.TRUE);
            docExporter.setParameter(JRDocxExporterParameter.CHARACTER_ENCODING, "UTF-8");
            docExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, byteDocExport);
            docExporter.exportReport();
            //docxReport = byteDocExport.toByteArray();
            pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
            //lstFiles.add(0,docxReport);
            //lstFiles.add(1,pdfReport); */
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
        }
    }
    
    public static List<Recibo> generarReciboPago() {
        List<Recibo> reciboList = new ArrayList<Recibo>();
        Recibo recibo = new Recibo();
        
        llenarRecibo(recibo);
        
        reciboList.add(recibo);
        
        return reciboList;
    }
    
    public static Recibo llenarRecibo(Recibo recibo) {
        
        recibo.setCliente("Cliente Testing.");
        recibo.setFechaEfectiva("11/10/2017");
        recibo.setFechaProceso("13/10/2017");
        recibo.setNumRecibo("1000");
        
        llenarTablaDetalle(recibo);
        
        llenarTablaDetalle2(recibo);
        
        return recibo;   
    }
    
    public static Recibo llenarTablaDetalle(Recibo recibo) {
        
        TablaDetalle tablaDetalle = new TablaDetalle();
        
        generarDetalle(tablaDetalle);
        
        recibo.getTablaDetalle().add(tablaDetalle);
        
        return recibo;   
    }
    
    public static Recibo llenarTablaDetalle2(Recibo recibo) {
        
        TablaDetalle tablaDetalle = new TablaDetalle();
        
        generarDetalle2(tablaDetalle);
        
        recibo.getTablaDetalle().add(tablaDetalle);
        
        return recibo;   
    }
    
    public static TablaDetalle generarDetalle(TablaDetalle tablaDetalle) {
            
        Detalles detalles = new Detalles();
        
        tablaDetalle.setTotalPagada("30000");
        
        llenarDetalle(detalles);
        
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
            
        return tablaDetalle;
    }
    
    public static TablaDetalle generarDetalle2(TablaDetalle tablaDetalle) {
            
        Detalles detalles = new Detalles();
        Detalles detalles2 = new Detalles();
        
        tablaDetalle.setTotalPagada("5000000");
        
        llenarDetalle2(detalles);
        //llenarDetalle3(detalles2);
        
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
        tablaDetalle.getDetalles().add(detalles);
            
        return tablaDetalle;
    }
    
    public static Detalles llenarDetalle(Detalles detalles) {
        
        detalles.setCantidadAdeudada("800,000");
        detalles.setCantidadPagada("800,000");
        detalles.setDesembolso("350/15");
        detalles.setDetalle("80000");
        detalles.setMonedaAdeudo("200,000");
        detalles.setMonedaPagada("USD");
        detalles.setPrestamo("BBBBBBBBBBB");
        
        return detalles;
    }
    
    public static Detalles llenarDetalle2(Detalles detalles) {
        
        detalles.setCantidadAdeudada("800,000");
        detalles.setCantidadPagada("800,000");
        detalles.setDesembolso("350/15");
        detalles.setDetalle("80000");
        detalles.setMonedaAdeudo("200,000");
        detalles.setMonedaPagada("HNL");
        detalles.setPrestamo("BBBBBBBBBBB");
        
        return detalles;
    }
    
    public static Detalles llenarDetalle3(Detalles detalles) {
        
        detalles.setCantidadAdeudada("800,000");
        detalles.setCantidadPagada("800,000");
        detalles.setDesembolso("350/15");
        detalles.setDetalle("80000");
        detalles.setMonedaAdeudo("200,000");
        detalles.setMonedaPagada("ANF");
        detalles.setPrestamo("BBBBBBBBBBB");
        
        return detalles;
    }
}