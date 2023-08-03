package www.bcie.org.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;
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

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


/**
 * @params
 * Los parámetros @WebService y @BindingType servirán de referencia para poder crear el Webservice
 */
@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class CrearReporteTCC {
    
    private static Connection conn = null;
    /*
     * Query de consulta a la tabla CONFIGURACION para extraer el 
     * campo donde se encuentra alojado la ruta de los reporte
     * dentro del servidor
     */
    private static final String query = "SELECT VALOR FROM CONFIGURACION WHERE LLAVE LIKE '%REPOSITORIO%'";
    private static String valor = "";
    
    private static byte[] Resultado;
    
    private static DateFormat formato;
    private static Date fecha;
    
    private static PreparedStatement ps = null;
    
    private static ResultSet rs = null;

    /**
     *
     * @param idOperacion
     * @return
     */
    @WebMethod
    public byte[] crearReporteTCC(@WebParam(name = "idOperacion") long idOperacion){  
        
        byte[] docWord = null;
        
        Map parametros = new HashMap();
        JasperPrint imprimir = null;
        
        try{
            
            conn = getConnection();            
            
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                valor = rs.getString(1);
            }
            
            parametros.put("ID_OPERACION", idOperacion);
            parametros.put("VALOR", valor);     
            
            System.out.println(idOperacion);
            System.out.println(valor);
            
            JasperReport reporte = JasperCompileManager.compileReport(valor + "ReporteTCC.jrxml");
            imprimir = JasperFillManager.fillReport(reporte, parametros, conn);
            JasperExportManager.exportReportToPdfFile(imprimir, valor + "ReporteTCC" + ".pdf");
            
            Resultado = JasperExportManager.exportReportToPdf(imprimir);
            
        }catch(Exception exc){
            exc.printStackTrace();
        }finally{
            try{
                if(conn != null){                
                    conn.close();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        
        return Resultado;
    }
    
    /*
     * Conexión a la base de datos configurada dentro del weblogic
     */
    private Connection getConnection(){
        
        try{
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("jdbc/OperacionesDB");
            conn = ds.getConnection();
        }catch(NamingException se){
            se.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        
        return conn;
    }
    
}
