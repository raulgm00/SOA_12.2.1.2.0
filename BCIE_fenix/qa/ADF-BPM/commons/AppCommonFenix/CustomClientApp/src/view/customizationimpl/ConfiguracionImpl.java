package view.customizationimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import oracle.adf.share.logging.ADFLogger;

/**
 * Define funcionalidades para obtener informacion de la tabla CONFIGURACION del schema FENIX
 */
public class ConfiguracionImpl {
    
    /**
     * Log de la aplicacion
     */
    private static ADFLogger logger = null;
    
    /**
     * Define JNDI de la BD Fenix
     */
    private static final String JNDI_FENIX_DS = "jdbc/fenixDBDS";
    
    /**
     * Contiene el SQL Query para obtener el valor de una llave en la tabla de CONFIGURACION
     */
    private static final String QUERY_URL_APP = "SELECT VALOR\n" + 
                                                "FROM CONFIGURACION\n" + 
                                                "WHERE 1 = 1\n" + 
                                                "  AND LLAVE = '%s'\n" +
                                                "  AND BAN_ESTATUS = 1 ";
    
    /**
     * Contiene definicion de columna VALOR de la tabla CONFIGURACION
     */
    private static final String VALUE_COLUMN = "VALOR";
    
    /**
     * Tipo ENUM que define las llave de las aplicaciones externas registradas en la tabla CONFIGURACION
     */
    public static enum AppKey{
        GESTOR_OPERACIONES("URL_GESTOR_OPERACIONES"),
        GESTOR_CLIENTES("URL_GESTOR_CLIENTES"),
        GESTOR_DESEMBOLSO("URL_GESTOR_DESEMBOLSOS");
        
        private String value;
        
        AppKey(String valueArg){
            value = valueArg;
        }
        
        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    
    /**
     * Constructor por defecto
     */
    public ConfiguracionImpl() {
        super();
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    /**
     * Obtiene objeto de Data Source para la conexion a la BD Fenix
     * @return devuelve objeto
     */
    private DataSource getDataSource(){
        
        logger.warning("Inicia getDataSource");
        
        Context ctx = null;
        DataSource ds = null;    
        
        try {
            ctx = new InitialContext();
        } catch (NamingException e) {
            logger.severe("Error al obtener contexto de aplicacion. Detalle: " + e.getMessage());
        }
        
        if(ctx != null){
            try {
                ds = (DataSource) ctx.lookup(JNDI_FENIX_DS);
            } catch (NamingException e) {
                logger.severe("Error al obtener el Data Source de Fenix. " + JNDI_FENIX_DS +
                              ". Detalle: " + e.getMessage());
            }
        }
        
        logger.warning("Finaliza getDataSource");
        return ds;
    }
    
    /**
     * Cierra los objetos de consulta y conexion
     * @param conn contiene objeto de conexion
     * @param stmt contiene objeto de consulta
     * @param rs contiene objeto de resultado de consulta
     */
    private void close(Connection conn, 
                       Statement stmt,
                       ResultSet rs){
        
        logger.warning("Inicia metodo close");
        
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                logger.severe("Error al cerrar objeto ResultSet");
            }
        }
        
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.severe("Error al cerrar objeto Statement");
            }
        }
        
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                logger.severe("Error al cerrar objeto Connection");
            }
        }
        
        logger.warning("Finaliza metodo close");
    }
    
    /**
     * Ejecuta la consulta de la tabla CONFIGURACION para obtener una cadena valor correspondiente a la llave indicada
     * @param key contiene cadena que indica la llave
     * @return devuelve cadena que contiene el valor de la llave
     */
    private String getValue(String key){
        
        logger.warning("Inicia metodo getValue");
        
        String strValue = null;
        
        DataSource ds = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        ds = getDataSource();
        
        if(ds != null){
            
            try {
                conn = ds.getConnection();
            } catch (SQLException e) {
                logger.severe("Error al obtener la conexion del Data Source. Detalle: " + e.getMessage());
            }
        }
        
        if(conn != null){

            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                logger.severe("Error al crear obejto Statement. Detalle: " + e.getMessage());
            }
            
            if(stmt != null){
                
                String query = QUERY_URL_APP;
                query = String.format(query, key);

                try {
                    rs = stmt.executeQuery(query);
                } catch (SQLException e) {
                    logger.severe("Error al ejecutar consulta SQL. Detalle: " + e.getMessage());
                }
                
                if(rs != null){
                    try {
                        while (rs.next()) {
                            strValue = rs.getString(VALUE_COLUMN);
                            if(strValue != null){
                                break;    
                            }
                        }
                    } catch (SQLException e) {
                        logger.severe("Error al iterar el objeto ResultSet. Detalle: " + e.getMessage());
                    }
                }
            }
        }
        
        if(strValue != null){
            logger.warning("Valor encontrado: " + strValue);
        }else{
            logger.warning("Valor no encontrado: " + strValue);
        }
        
        //Ejecuta el cierre de los objetos
        close(conn, stmt, rs);
        
        logger.warning("Finaliza metodo getValue");        
        return strValue;
    }
    
    /**
     * Obtiene el valor de la URL de una aplicacion Externa
     * @param app define la Llave del valor a consultar de las aplicaciones externas
     * @return devuelve cadena con valor de la URL
     */
    public String getUrlApp(AppKey app){
        
        logger.warning("Inicia metodo getUrlApp");
        
        String strUrl = null;
        
        logger.warning("Ejecuta obtener valor de la URL para la aplicacion con llave: " + app.getValue());
        strUrl = getValue(app.getValue());
        
        logger.warning("Finaliza metodo getUrlApp");
        return strUrl;
    }
}
