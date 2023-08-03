package www.bcie.org.service;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import javax.jws.WebService;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.JREvaluator;

import weblogic.utils.classloaders.GenericClassLoader;

/**
 * @params
 * Los parámetros @WebService y @BindingType servirán de referencia para poder crear el Webservice
 */
@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class CrearReporteCondicion {
    
    private static Connection conn = null;
    private static PreparedStatement ps1 = null;
    private static PreparedStatement ps2 = null;
    
    private static ResultSet rs1 = null;
    private static ResultSet rs2 = null;
    
    private static byte[] bytePDFReporte;   
    private static int idOperacion;
    
    private static final String NOMBRE_REPORTE = "Reporte de Cumplimiento de Condiciones de Formalización";
    
    /*
     * Query de consulta a la tabla CONFIGURACION para extraer el 
     * campo donde se encuentra alojado la ruta de los reporte
     * dentro del servidor
     */
   
    private static String sql = "SELECT VALOR FROM CONFIGURACION WHERE LLAVE LIKE '%REPOSITORIO%'";
    private static String valor = "";
    
    /**
     *
     * @param idOperacion
     * @return
     * @throws IOException
     */
    @WebMethod
    public byte[] ReporteCondicion(@WebParam(name = "idOperacion") long idOperacion, @WebParam(name = "idAgrupador") long idAgrupador) throws IOException{
        
        GenericClassLoader jre = (GenericClassLoader) JREvaluator.class.getClassLoader();
        //looger.info(String.format("jre: %s, s.", jre, jre.getClassPath()));
        
        try{
            
            conn = getConnection();
            
            ps2 = conn.prepareStatement(sql);
            
            rs2 = ps2.executeQuery();
            
            while (rs2.next()){
                valor = rs2.getString(1);
            }
            
            Locale locale = new Locale("es", "HN");
            Map parametros = new HashMap();
            
            parametros.put("ID_OPERACION", idOperacion);
            parametros.put("ID_AGRUPADOR", idAgrupador);
            parametros.put("VALOR" ,valor);
            parametros.put(JRParameter.REPORT_LOCALE, locale);
            
            if (idAgrupador > 0) {
                JasperReport reporte = JasperCompileManager.compileReport(valor + "ReporteCondicion.jrxml");
                System.out.println(reporte.toString());
                JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, conn);
                JasperExportManager.exportReportToPdfFile(imprimir, valor + NOMBRE_REPORTE + idOperacion + ".pdf");
                //JasperViewer.viewReport(imprimir, false);
                //Reporte(imprimir);
                bytePDFReporte = JasperExportManager.exportReportToPdf(imprimir);
            } else {
                JasperReport reporte = JasperCompileManager.compileReport(valor + "ReporteCondicionGroup.jrxml");
                System.out.println(reporte.toString());
                JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, conn);
                JasperExportManager.exportReportToPdfFile(imprimir, valor + NOMBRE_REPORTE + idOperacion + ".pdf");
                //JasperViewer.viewReport(imprimir, false);
                //Reporte(imprimir);
                bytePDFReporte = JasperExportManager.exportReportToPdf(imprimir);
            }            
        }catch(Exception exception){                                                                                                                        
                exception.printStackTrace();
        } finally {
                
             try {
                    if (conn != null) {
                      //conn.rollback();
                      conn.close();
                    }
                  }
                  catch (Exception e) {
                    e.printStackTrace();
                  }
        }
     
        return bytePDFReporte;
    }
    
    /*
     * Conexión a la base de datos configurada dentro del weblogic
     */
    
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
