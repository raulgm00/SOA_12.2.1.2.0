package view;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.CallableStatement;
//------------------------
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

@Path("intentos")
@Consumes(value = { "application/json", "application/xml" })
@Produces(value = { "application/json", "application/xml" })


public class GestionarIntento {
    public GestionarIntento() {
        super();
    }
 
    @javax.ws.rs.core.Context private HttpServletRequest hsr;
    private javax.ws.rs.core.Response Respuesta;

    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/x-www-form-urlencoded")
    @Path("/gestionarIntento")
    public Response gestionarIntentoLogueo(@FormParam("usuario") String usuario, @FormParam("usrAgent") String usrAgent,
                                           @FormParam("app") String app, @FormParam("exito") String exito){//ipClient){
        usuario=usuario.toUpperCase();
        System.out.println("usuario: "+usuario);
        System.out.println("usrAgent: "+usrAgent);
        System.out.println("app: "+app);
        System.out.println("exito: "+exito);
        
        Context env = null;
        DataSource pool = null;
        Hashtable ht = new Hashtable( );
        boolean ban = true;
        String remoteHost = hsr.getRemoteHost();
                                           
        ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");        
        Connection conn = null;
        CallableStatement statement = null;
        String sql = "{call MIDDLE.SEG_K_UTL.GESTIONAR_INTENTO_LOGUEO(?,?,?,?,?,?)}";
        try{
            env = new InitialContext(ht);
            //Lookup this DataSouce at the top level of the WebLogic JNDI tree
            pool = (DataSource) env.lookup ("jdbc/LoginAppConnDS"); 
            
            ResultSet resultSet = null;
            ResultSetMetaData resultSetMetaData = null;       
            conn = pool.getConnection( );
                            
            // CONSULTA OIDParams
            statement = conn.prepareCall(sql);
                
            statement.setString("pcCodigoEmpleado", usuario);
            statement.setString("pcNavegador", usrAgent);
            statement.setString("pcCodigoAplicacion", app);
            statement.setString("pcExito", exito);
            statement.setString("pvUbicacion_Referencia", remoteHost);            
            statement.registerOutParameter("pcMensajeError", Types.VARCHAR);
            
            statement.executeQuery();
            String message="";
            if(statement.getString("pcMensajeError")==null){
                message = "{\"mensaje\": \"OK\"}"; 
            }else {
                message = "{\"mensaje\": \""+statement.getString("pcMensajeError")+"\"}";
            }
            conn.close();
            Response res = Response.status(Response.Status.OK)
            .status(Response.Status.OK).header("Access-Control-Allow-Origin","*")
            .header("Access-Control-Allow-Headers","origin, x-requested-with, accept")
            //.header("Access-Control-Max-Age","3628800")
            .header("Access-Control-Allow-Methods","POST")
            //.header("Access-Control-Allow-Credentials ","true") 
            .entity(message)
            .type(MediaType.APPLICATION_JSON)
            .build();
            return  res;
            
        } catch (Exception e) {
            e.printStackTrace();
            String message = "{\"mensaje\": \"error\"}";
            Response res = Response.status(Response.Status.OK)
                                  .status(Response.Status.OK)
                                  .header("Access-Control-Allow-Origin","*")
                                  .header("Access-Control-Allow-Headers","origin, x-requested-with, accept")
                                  //.header("Access-Control-Max-Age","3628800")
                                  .header("Access-Control-Allow-Methods","POST")
                                  //.header("Access-Control-Allow-Credentials ","true") 
                                  .entity(message)
                                  .type(MediaType.APPLICATION_JSON)
                                  .build();
            return  res;
        }
    }
    @OPTIONS
    @Path("/gestionarIntento")
    public Response options() {
        return Response.ok("")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept")
                .header("Access-Control-Allow-Methods", "POST, OPTIONS")
                //.header("Access-Control-Max-Age", "1209600")
                .build();
    }
    
    
}
