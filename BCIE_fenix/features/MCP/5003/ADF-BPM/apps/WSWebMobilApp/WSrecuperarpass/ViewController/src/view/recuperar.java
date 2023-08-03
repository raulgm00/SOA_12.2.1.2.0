package view;




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

import java.sql.SQLException;
import java.sql.Types;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;


@Path("view")
@Consumes(value = { "application/json", "application/xml" })
@Produces(value = { "application/json", "application/xml" })
public class recuperar {
    public recuperar() {
        super();
    }
    private String ambiente = "qna";
    
    @javax.ws.rs.core.Context private javax.servlet.http.HttpServletRequest hsr;
    private javax.ws.rs.core.Response Respuesta;

    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/x-www-form-urlencoded")
    @Path("recuperar")
    public Response recuperarpass (@FormParam("userName") String userName, @FormParam("userNameAlt") String userNameAlt){
            
            propiedades prop = new propiedades();
            String host=prop.getHost();
            String port =prop.getPort();
            String message="";
            System.out.println("userName: "+userName);
            System.out.println("userNameAlt: "+userNameAlt);
            if (userName==null){
                    message = "{\"mensaje\": \"El identificador de usuario no existe.\"}";
                    Response res = Response.status(Response.Status.OK)
                                          .status(Response.Status.OK).header("Access-Control-Allow-Origin","*") 
                                          .entity(message)
                                          .type(MediaType.APPLICATION_JSON)
                                          .build();
                    return  res;   
                }
            String userNameUper = userName.toUpperCase();
            String userNameAltUper= userNameAlt.toUpperCase();
            Context env = null;
            DataSource pool = null;
            Hashtable ht = new Hashtable( );
            boolean ban = true;
            String remoteHost = hsr.getRemoteHost();
            //HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 
            //System.out.println("Client IP = " + req.getRemoteAddr()); 
            
            ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");        
            Connection conn = null;
            CallableStatement statementValidaUSR = null;
            CallableStatement statementValidaNE = null;
            CallableStatement statementOIDParams = null;
            CallableStatement statementCompania = null;
            CallableStatement statementSolCambio = null;
            String sqlIdUSR = "{call MIDDLE.SEG_K_UTL.RESOLVER_IDENTIFICADOR_USUARIO(?,?)}";
            String sqlOIDParams = "{call MIDDLE.SEG_K_UTL.PARAMETROS_OID(?,?,?,?,?,?)}";
            String sqlCompania = "{call MIDDLE.SEG_K_UTL.OBTENER_COMPANIA_EMPLEADO(?,?)}";
            String sqlSolCambio = "{call MIDDLE.SEG_K_UTL.SOLICITAR_CAMBIO_CONTRASEÑA(?,?,?,?)}";
             
                try{
                    env = new InitialContext(ht);
                    //Lookup this DataSouce at the top level of the WebLogic JNDI tree
                    pool = (DataSource) env.lookup ("jdbc/LoginAppConnDS"); 
                    
                    ResultSet resultSet = null;
                    ResultSetMetaData resultSetMetaData = null;       
                    conn = pool.getConnection( );
                    //consultar usuario
                    statementValidaUSR = conn.prepareCall(sqlIdUSR);//create a java.sql.Statement                    
                    statementValidaUSR.setString("USUARIO", userNameUper);
                    statementValidaUSR.registerOutParameter("RESPUESTA", Types.VARCHAR);
                    statementValidaUSR.executeQuery();
                    userNameUper=statementValidaUSR.getString("RESPUESTA");
                    System.out.println("validar userName= "+statementValidaUSR.getString("RESPUESTA")+"");
                    //consultar numero de empleado 
                    statementValidaNE = conn.prepareCall(sqlIdUSR);//create a java.sql.Statement                    
                    statementValidaNE.setString("USUARIO", userNameAltUper);
                    statementValidaNE.registerOutParameter("RESPUESTA", Types.VARCHAR);
                    statementValidaNE.executeQuery();
                    userNameAltUper=statementValidaNE.getString("RESPUESTA");
                    System.out.println("Validar NE= "+statementValidaNE.getString("RESPUESTA")+"");
                    
                    if(!userNameUper.equalsIgnoreCase(userNameAltUper) || userNameUper==null || userNameAltUper==null){
                        conn.close();
                        //message = "userNameUper: "+userNameUper+"  userNameAltUper: "+userNameAltUper+"";
                        message = "{\"mensaje\": \"Los identificadores no coinciden para un mismo usuario.\"}";
                        Response res = Response.status(Response.Status.OK)
                        .status(Response.Status.OK).header("Access-Control-Allow-Origin","*")
                        .header("Access-Control-Allow-Headers","origin, x-requested-with, accept")
                        .header("Access-Control-Max-Age","3628800")
                        .header("Access-Control-Allow-Methods","GET, PUT, POST, DELETE")
                        .header("Access-Control-Allow-Credentials ","true") 
                        .entity(message)
                        .type(MediaType.APPLICATION_JSON)
                        .build();
                        return  res;
                    }
                    
                    // CONSULTA OIDParams
                    statementOIDParams = conn.prepareCall(sqlOIDParams);//create a java.sql.Statement
                    //set parametros consulta oid params 
                    statementOIDParams.registerOutParameter("APP_CONTEXT", Types.VARCHAR);
                    statementOIDParams.registerOutParameter("CONTRASENA", Types.VARCHAR);
                    statementOIDParams.registerOutParameter("HOST_PORT", Types.VARCHAR);
                    statementOIDParams.registerOutParameter("HOST_URL", Types.VARCHAR);
                    statementOIDParams.registerOutParameter("REAL_BCIE", Types.VARCHAR);
                    statementOIDParams.registerOutParameter("REAL_JUBILADOS", Types.VARCHAR);
                    statementOIDParams.executeQuery();
                    //Parametros OID
                    String contexto = statementOIDParams.getString("APP_CONTEXT");
                    String contraseña = statementOIDParams.getString("CONTRASENA");
                    String puerto = statementOIDParams.getString("HOST_PORT");
                    String url = statementOIDParams.getString("HOST_URL");
                    String realmBCIE = statementOIDParams.getString("REAL_BCIE");
                    String realmJubilados = statementOIDParams.getString("REAL_JUBILADOS");
                    
                    OIDConnParams oidConnParams = new OIDConnParams(realmBCIE,realmJubilados,contraseña,url,puerto,contexto);
                    UsuarioOID usuarioOID = new UsuarioOID(oidConnParams);
                    //obtener compania del usuario 
                    //---------------------------------------------------------------------------
                    statementCompania = conn.prepareCall(sqlCompania);//create a java.sql.Statement 
                    
                    statementCompania.setString("pcUserName",userName.toUpperCase());
                    statementCompania.registerOutParameter("pcCompania", Types.VARCHAR);
                    statementCompania.executeQuery();
                    
                    String compania = statementCompania.getString("pcCompania"); 
                    String resultado = usuarioOID.existeUsuarioEnOID(userName, compania);
                    
                    if (resultado != null) {
                        statementSolCambio = conn.prepareCall(sqlSolCambio);
                        statementSolCambio.setString("SERVER", host+":"+port+"/LoginApp"+"/redirect_confirmacion.jsp");
                        statementSolCambio.setString("USUARIO", userNameUper);
                        statementSolCambio.setString("pvUbicacion_Referencia",remoteHost);
                        statementSolCambio.registerOutParameter("pcMensajeError",Types.VARCHAR);
                        statementSolCambio.executeQuery();
                        
                        if(statementSolCambio.getString("pcMensajeError")==null){
                                message = "{\"mensaje\": \"Se ha enviado a su correo electrónico el instructivo para realizar el cambio de contraseña.\"}";
                            }
                        else{
                                message = "{\"mensaje\": \""+statementSolCambio.getString("pcMensajeError")+"\"}";
                            }   
                    }else{
                        System.out.println("no existe en oid");
                        message = "{\"mensaje\": \"El usuario no puede ser encontrado en OID.\"}";
                    }
                    
                    conn.close();
                    
                    Response res = Response.status(Response.Status.OK)
                    .status(Response.Status.OK).header("Access-Control-Allow-Origin","*")
                    .header("Access-Control-Allow-Headers","origin, x-requested-with, accept")
                    .header("Access-Control-Max-Age","3628800")
                    .header("Access-Control-Allow-Methods","GET, PUT, POST, DELETE")
                    .header("Access-Control-Allow-Credentials ","true")  
                      .entity(message)
                      .type(MediaType.APPLICATION_JSON)
                      .build();
                    return res;
            } catch (Exception e) {
            try {
                conn.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            e.printStackTrace();
                message = "{\"mensaje\": \"error\"}";
                Response res = Response.status(Response.Status.OK)
                .status(Response.Status.OK).header("Access-Control-Allow-Origin","*")
                .header("Access-Control-Allow-Headers","origin, x-requested-with, accept")
                .header("Access-Control-Max-Age","3628800")
                .header("Access-Control-Allow-Methods","GET, PUT, POST, DELETE")
                .header("Access-Control-Allow-Credentials ","true") 
                .entity(message)
                .type(MediaType.APPLICATION_JSON)
                .build();
                return  res;
            }
        } 
}
