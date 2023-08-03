package www.bcie.org.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;


import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.sql.DataSource;

import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.*;

import weblogic.utils.classloaders.GenericClassLoader;

@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class CrearReporteNotaDeConcepto {
   
    /*
     * Asignación de variables 
     */
    
    private static Connection conn = null;
    private static byte[] bytePDFReporte;    
        
    
    private static final String NOMBRE_REPORTE = "NotaDeConcepto_";    
    
    
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
        * @param NoObjecion
        * @param idDeclaracion
        * @param ResponsableOperacion
        * @param TipoGeneracion
        * @return
        * @throws IOException
        */
       
       @WebMethod
       public byte[] Reporte(@WebParam(name = "idOperacion") long idOperacion,
                           @WebParam(name = "NoObjecion") String NoObjecion,
                           @WebParam(name = "idDeclaracion") Long idDeclaracion,
                           @WebParam(name = "ResponsableOperacion") String responsableOperacion,
                           @WebParam(name = "TipoGeneracion") String tipoGeneracion) throws IOException{
           
           GenericClassLoader jre = (GenericClassLoader) JREvaluator.class.getClassLoader();
           //looger.info(String.format("jre: %s, s.", jre, jre.getClassPath()));
           
           try{            
               conn = getConnection(); 
                     
               PreparedStatement stmnt = conn.prepareStatement(sql);
               ResultSet rs = stmnt.executeQuery();
               
               while(rs.next()){
                   valor = rs.getString(1);
               }
               
               Map parametros = new HashMap();
               parametros.put("ID_OPERACION", idOperacion);
               parametros.put("NO_OBJECION", NoObjecion);
               parametros.put("VALOR", valor);
               if(idDeclaracion == 0){
                   idDeclaracion = null;
                   parametros.put("ID_DECLARACION", idDeclaracion);
               }else{
                   parametros.put("ID_DECLARACION", idDeclaracion);
               }
               
               parametros.put("RESPONSABLE", responsableOperacion);
               parametros.put("TIPO_GENERACION", tipoGeneracion);
               
               JasperReport reporte = JasperCompileManager.compileReport(valor  + "ReporteNotaConcepto.jrxml");
               System.out.println(reporte.toString());
               JasperPrint imprimir = JasperFillManager.fillReport(reporte, parametros, conn);
               JasperExportManager.exportReportToPdfFile(imprimir, valor + NOMBRE_REPORTE + idOperacion + ".pdf");
               //JasperViewer.viewReport(imprimir, false);
               //Reporte(imprimir);
               bytePDFReporte = JasperExportManager.exportReportToPdf(imprimir);            
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
