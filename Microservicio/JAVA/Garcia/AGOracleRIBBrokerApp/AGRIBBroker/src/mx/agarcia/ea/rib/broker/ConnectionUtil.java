package mx.agarcia.ea.rib.broker;


import java.sql.SQLException;

import java.util.Date;

import oracle.jdbc.pool.OracleDataSource;

public class ConnectionUtil {
    

    public static final String queueOwner = "RIB_AQ";

    public static final String queueName = "ETASNOUTAT";

 /*
    public static String serverIp= "172.16.20.33";
    public static String sid = "RIBUAT";
    public static String userName = "rib_aq";
   */ 
    public static OracleDataSource getOracleDataSource() throws SQLException {

        String serverIp= BrokerManager.resourceBundle.getString( "rib.db.serverName" ); 
        String sid = BrokerManager.resourceBundle.getString( "rib.db.databaseName" ); 
        String userName = BrokerManager.resourceBundle.getString( "rib.db.user" );
        
        System.out.println( new Date() + "[García::RIBBroker] Conexión a base de datos " + userName + "@" + sid +"//" +  serverIp);
        
        OracleDataSource ds = new OracleDataSource();
        ds.setDriverType("thin");
        ds.setServerName(serverIp);
        ds.setPortNumber( Integer.parseInt( BrokerManager.resourceBundle.getString( "rib.db.portNumber" ) ) );
        ds.setDatabaseName(sid); // sid
        ds.setUser( userName );
        ds.setPassword( BrokerManager.resourceBundle.getString( "rib.db.password" ) );

        return ds;
    }
}
