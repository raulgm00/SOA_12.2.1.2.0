package mx.agarcia.ea.jda.core;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Singleton;

@Singleton(name = "JDAConnectionManager", mappedName = "JDAConnectionManager")
public class JDAConnectionManagerImpl implements JDAConnectionManager, JDAConnectionManagerLocal {
    @Resource
    SessionContext sessionContext;

    String user = "GSISSOLIT2";
    String password = "GSISSOLIT2";
    String url = "//192.1.103.50/B10b829t";
    String db2Driver = "com.ibm.as400.access.AS400JDBCDriver";
    String urlPrefix = "jdbc:as400:";
    
    public JDAConnectionManagerImpl() {

        try {
            System.out.println( "[JDAConnectionManager] cargando clase: " + db2Driver );
            Class.forName( db2Driver );
                        
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection(){
        
        try {
            System.out.println( "[JDAConnectionManager] iniciando conexión a: " + urlPrefix + url + "@user" );
            
            Connection con = DriverManager.getConnection(urlPrefix + url, user, password);
            con.setAutoCommit(false);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
