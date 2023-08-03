package mx.agarcia.ea.rib.broker.test;

import java.sql.SQLException;

import java.util.Date;

import oracle.jdbc.pool.OracleDataSource;

public class ConnectionUtil {
    

    public static final String queueOwner = "RIB_AQ";

    public static final String queueName = "ETVENDORFROMRMS";

 
    public static String serverIp= "172.16.20.33";
    public static String sid = "RIBUAT";
    public static String userName = "rib_aq";
    
    public static OracleDataSource getOracleDataSource() throws SQLException {

        String serverIp= "172.16.20.33";
        String sid = "RIBUAT";
        String userName = "rib_aq";
        System.out.println( new Date() + "[García::RIBBroker] Conexión a base de datos " + userName + "@" + sid +"//" +  serverIp);
        
        OracleDataSource ds = new OracleDataSource();
        ds.setDriverType("thin");
        ds.setServerName(serverIp);
        ds.setPortNumber(1521);
        ds.setDatabaseName(sid); // sid
        ds.setUser("rib_aq");
        ds.setPassword("Welcome1");

        return ds;
    }
}
