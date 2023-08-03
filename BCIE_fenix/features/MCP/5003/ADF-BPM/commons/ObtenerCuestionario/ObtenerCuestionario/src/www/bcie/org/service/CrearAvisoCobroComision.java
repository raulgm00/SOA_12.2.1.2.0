package www.bcie.org.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;

/**
 * @params
 * Los parámetros @WebService y @BindingType servirán de referencia para poder crear el Webservice
 */
@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class CrearAvisoCobroComision {
    
    //Generación de constantes finales para la creación del archivo
    
    private static Connection conn = null;
    private final static String REPORT_NAME = "Aviso_de_Cobro"; 
    
    /*
     * Query de consulta a la tabla CONFIGURACION para extraer el 
     * campo donde se encuentra alojado la ruta de los reporte
     * dentro del servidor
     */
    private static String Resources = "SELECT VALOR FROM CONFIGURACION WHERE LLAVE LIKE '%REPOSITORIO%'";
    private static String valor = "";
    
    
    /**
     *
     * @param idOperacion
     * @param idComision
     * @return
     * @throws JRException
     * @throws IOException
     * @throws SQLException
     */
    @WebMethod
    public byte[] CrearAvisoCobro(@WebParam(name = "idOperacion") long idOperacion,
                           @WebParam(name = "idComision") long idComision)throws JRException, IOException,
                                                                                 SQLException{
        /*
         * El reporte se generará en formato Word
         */
        byte[] docWord = null;
        
                
        try{
            
            conn = getConnection();
            
            PreparedStatement ps = conn.prepareStatement(Resources);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                valor = rs.getString(1);
            }
            
            Map parametros = new HashMap();
            parametros.put("ID_OPERACION", idOperacion);
            parametros.put("ID_COMISION", idComision);
            parametros.put("VALOR", valor);
                        
            final JRDocxExporter docExporter = new JRDocxExporter();
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            
            JasperReport reporte = JasperCompileManager.compileReport(valor + "CrearReporteComision.jrxml");
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, conn);            
            docExporter.setParameter(JRDocxExporterParameter.JASPER_PRINT, imprimir);
            docExporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, bos);
            
            docExporter.exportReport();
            docWord = bos.toByteArray();
            
        }catch(JRException e){
            e.printStackTrace();
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
        
        //System.out.println(docWord.toString());
        return docWord;
        
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
