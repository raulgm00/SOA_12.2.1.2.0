package view;

import java.sql.CallableStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;


public class UsuarioOID {
    
   private OIDManager OIDM = null;
   private OIDConnParams oidConnParams;
  // Códigos de Error manejados por el aplicativos.
  private String SEG_USUARIO_EXISTE = "SEG_USUARIO_EXISTE";                       // Posible Mensaje EL usuario ya existe en el directorio LDAP
  private String SEG_USUARIO_EXISTE_EN_GRUPO = "SEG_USUARIO_EXISTE_EN_GRUPO";     // El usuario ya existe en el grupo LDAP
  private String SEG_GRUPO_NO_EXISTE = "SEG_GRUPO_NO_EXISTE";                     //  Grupo no existe en el directorio LDAP
  private String SEG_USUARIO_NO_EXISTE = "SEG_USUARIO_NO_EXISTE";                 // El usuario no existe en el grupo LDAP
  private String SEG_NO_PERMISO_OID = "SEG_NO_PERMISO_OID";                       // No hay permiso en el LDAP
  private String SEG_ESTADO_USUARIO_NO_VALIDO="SEG_ESTADO_USUARIO_NO_VALIDO";

  // Códigos de Error manejados por el OID.
  private String GSL_PWDMINLENGTH_EXCP = "GSL_PWDMINLENGTH_EXCP";                 // No cumple con la política de tamaño mínimo en el directorio LDAP
  private String GSL_PWDNUMERIC_EXCP = "GSL_PWDNUMERIC_EXCP";                     // Password no cumple con la política numérica

    
   public UsuarioOID(OIDConnParams oidConnParams) {
        super();
        this.OIDM = new OIDManager(oidConnParams);
        this.oidConnParams = oidConnParams; 
   }
    
  public OIDConnParams getOIDConnParam(){
    return this.oidConnParams;
  }

  public void setOIDCOnnParam(OIDConnParams valor){
      this.oidConnParams = valor;
  }
  
  public String obtieneOIDErrorCode(String errorCode)
  {
      if (errorCode!= null){
          if (errorCode.toUpperCase().contains("LDAP: ERROR CODE 19")){
            if (errorCode.toUpperCase().contains(this.GSL_PWDMINLENGTH_EXCP))
              return this.GSL_PWDMINLENGTH_EXCP;  
            if (errorCode.toUpperCase().contains(this.GSL_PWDNUMERIC_EXCP))
              return this.GSL_PWDNUMERIC_EXCP;
          }
      }
      return errorCode;
  }
    
    
  /**
   * Función para crear un usuario en el OID.
   * @param userName : Codigo de Usuario a crear
   * @param clave : Clave de OID
   * @param compannia : Compañia para determinar el REALM
   * @param numeroEmpleado: número de empleado.
   * @param correoElectronico Correo electrónico.
   * @param nombre:  Nombre del usuario
   * @param nombreCompleto:  Nombre Completo (Nombre y Apellido)
   * @param apellido:  Apellido del usuario
   * @param fechaInicio:  Fecha de inicio del usuario
   * @param fechaVencimiento:  Fecha de vencimiento del usuario
   * @return Error Code o Null si se crea exitosamente
   * */
  public String crearUsuarioOID(String userName, String clave, String compannia, 
                              String numeroEmpleado, String correoElectronico, 
                              String nombre,
                              String nombreCompleto,
                              String apellido,
                              String fechaInicio,
                              String fechaVencimiento){
      
      //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
      //Si en los parametros de configuracion de usuari y contraseña en OID para la expiracion de una contraseña
      //el parametro pwdMaxAge es menor a 16 dias entonces no es posible
      //utilizar las funciones que consultan el estado del usuario en OID
      
      //Ahora se comentan las lineas siguientes, se limitara el parametro par no
      //colocar dias inferiores a dentro de las politicas de seguridad
      //String pwdMaxAge=obtenerParametrosPassword("pwdMaxAge");
      //setParametrosPassword("pwdMaxAge","0");
      //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
      

    // Object classes that the employee must use
    Map attrs = new HashMap();
    List objclass = new ArrayList();

    objclass.add("top");
    objclass.add("person");
    objclass.add("inetOrgPerson");
    objclass.add("organizationalperson");
    objclass.add("orclUser");
    objclass.add("orclUserV2");

    // create other attributes and their values   
    attrs.put("uid", userName);
    attrs.put("cn", userName);
    attrs.put("sn", apellido);
    attrs.put("mail", correoElectronico);
    attrs.put("userpassword", clave);
    attrs.put("employeeNumber", numeroEmpleado);
    attrs.put("givenName", nombre);
    attrs.put("displayName", nombreCompleto);
    if(fechaInicio!=null){
        attrs.put("orclactivestartdate", fechaInicio);
    }
    if(fechaVencimiento!=null){
        attrs.put("orclactiveenddate", fechaVencimiento);
    }
    

    try {
       String rootContext = "";
       if ((compannia != null) && (compannia.equals("001"))) 
          rootContext = this.getOIDConnParam().getRootContextBCIE();
       else
          rootContext = this.getOIDConnParam().getRootContextJubilados();

       String dn = oidConnParams.RDN + "=" + userName + "," + oidConnParams.userContext + rootContext;
       OIDM.addDirectoryEntry(dn, objclass, attrs, this.getOIDConnParam().getAppContext(), this.getOIDConnParam().getAppContrasenya());

      } 
      catch (NameAlreadyBoundException namingEx) { // for Directory errors
            return this.SEG_USUARIO_EXISTE;
      }
      catch (NamingException namingEx) { // for Directory errors
        return obtieneOIDErrorCode(namingEx.getMessage());
        }finally{
            //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
            //setParametrosPassword("pwdMaxAge",pwdMaxAge);
            //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
            
        }
    return null;
  }

  /**
   * Función que valdia si el userName enviado ya existe en el OID.
   * @param userName : Codigo de Usuario a validar
   * @param compannia : Compañia para determinar el REALM
   * */
  public String existeUsuarioEnOID(String userName, String compannia) throws Exception {
    String rootContext = "";
      
      //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
      //Si en los parametros de configuracion de usuari y contraseña en OID para la expiracion de una contraseña
      //el parametro pwdMaxAge es menor a 16 dias entonces no es posible
      //utilizar las funciones que consultan el estado del usuario en OID
      
      //Ahora se comentan las lineas siguientes, se limitara el parametro par no
      //colocar dias inferiores a dentro de las politicas de seguridad
      
      //String pwdMaxAge=obtenerParametrosPassword("pwdMaxAge");
      //setParametrosPassword("pwdMaxAge","0");
    
      //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
    

    if ((compannia != null) && (compannia.equals("001"))) 
       rootContext = oidConnParams.getRootContextBCIE();
    else
       rootContext = oidConnParams.getRootContextJubilados();

    try {
        String dn = OIDM.getUserDN(userName, rootContext);
        if (dn != null)
            return dn;
        else
            return null;
    }
    catch (NamingException namingEx) {
        throw new Exception("Error al validar si existe el usuario en el OID: " + namingEx.getMessage());
        }finally{
            //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
            //setParametrosPassword("pwdMaxAge",pwdMaxAge);
            //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM


        }
  }
  
    /**
     * Función para agregar un usuario a un grupo.
     * @param userName : Codigo de Usuario a crear
     * @param compannia: Compannia a la que pertenece el usuario.
     * @param nombreGrupo : Grupo al que se va a agregar el usuario.
     * @return ErrorCode o null si no hubo error.
     * */
    public String agregarUsuarioAGrupOID(String userName, String compannia, String nombreGrupo) {
        //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
        //Si en los parametros de configuracion de usuari y contraseña en OID para la expiracion de una contraseña
        //el parametro pwdMaxAge es menor a 16 dias entonces no es posible
        //utilizar las funciones que consultan el estado del usuario en OID
        
        //Ahora se comentan las lineas siguientes, se limitara el parametro par no
        //colocar dias inferiores a dentro de las politicas de seguridad
        
        //String pwdMaxAge=obtenerParametrosPassword("pwdMaxAge");
        //setParametrosPassword("pwdMaxAge","0");
        //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
        

      try {
         String rootContext = "";
         if ((compannia != null) && (compannia.equals("001"))) 
            rootContext = this.getOIDConnParam().getRootContextBCIE();
         else
            rootContext = this.getOIDConnParam().getRootContextJubilados();

         String dn = oidConnParams.RDN + "=" + userName + "," + oidConnParams.userContext + rootContext;
        String grupo = oidConnParams.RDN + "=" + nombreGrupo +",cn=Groups," + rootContext;
            //OIDM.addToGroup(dn, grupo, oidConnParams.getAppContext() + rootContext, oidConnParams.getAppContrasenya());
            OIDM.addToGroup(dn, grupo, oidConnParams.getAppContext(), oidConnParams.getAppContrasenya());
        } 
        catch (NamingException namingEx) { // for Directory errors
            String errorCode = namingEx.getMessage();
            if (errorCode != null)
                if (errorCode.toUpperCase().contains("LDAP: ERROR CODE 20"))
                    return this.SEG_USUARIO_EXISTE_EN_GRUPO;
               else if (errorCode.toUpperCase().contains("LDAP: ERROR CODE 32"))
                      return this.SEG_GRUPO_NO_EXISTE;
                    else
                      return errorCode;
            }finally{
              //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
              //setParametrosPassword("pwdMaxAge",pwdMaxAge);
              //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
              
          }

      return null;
    }

  /**
   * Función para remover un usuario de un grupo.
   * @param userName : Codigo de Usuario a crear
   * @param compannia: Compannia a la que pertenece el usuario.
   * @param nombreGrupo : Grupo al que se va a agregar el usuario.
   * @return ErrorCode o null si no hubo error.
   * */
  public String removerUsuarioDeGrupOID(String userName, String compannia, String nombreGrupo) {
      //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
      //Si en los parametros de configuracion de usuari y contraseña en OID para la expiracion de una contraseña
      //el parametro pwdMaxAge es menor a 16 dias entonces no es posible
      //utilizar las funciones que consultan el estado del usuario en OID
      
      //Ahora se comentan las lineas siguientes, se limitara el parametro par no
      //colocar dias inferiores a dentro de las politicas de seguridad
      //String pwdMaxAge=obtenerParametrosPassword("pwdMaxAge");
      //setParametrosPassword("pwdMaxAge","0");
      //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
      

    try {
       String rootContext = "";
       if ((compannia != null) && (compannia.equals("001"))) 
          rootContext = this.getOIDConnParam().getRootContextBCIE();
       else
          rootContext = this.getOIDConnParam().getRootContextJubilados();

       String dn = oidConnParams.RDN + "=" + userName + "," + oidConnParams.userContext + rootContext;
      String grupo = oidConnParams.RDN + "=" + nombreGrupo +",cn=Groups," + rootContext;
       //OIDM.removeToGroup(dn, grupo, oidConnParams.getAppContext() + rootContext, oidConnParams.getAppContrasenya());
          OIDM.removeToGroup(dn, grupo, oidConnParams.getAppContext(), oidConnParams.getAppContrasenya());
      } 
      catch (NamingException namingEx) { // for Directory errors
          String errorCode = namingEx.getMessage();
          if (errorCode != null)
              if (errorCode.toUpperCase().contains("LDAP: ERROR CODE 20"))
                  return this.SEG_USUARIO_EXISTE_EN_GRUPO;
             else if (errorCode.toUpperCase().contains("LDAP: ERROR CODE 32"))
                    return this.SEG_GRUPO_NO_EXISTE;
                  else
                    return errorCode;
          }finally{
            //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
            //setParametrosPassword("pwdMaxAge",pwdMaxAge);
            //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
        }

    return null;
  }


    
  /**
   * Función que cambia el estado al usuario indicado
   * @param userName : Codigo de Usuario a cambiar estado
   * @param compannia: Compannia del usuario
   * @param estado : Nuevo estado a cambiar. Posibles valores: ENABLED,DISABLED
   * @return codigo de error
   * */
    public String cambiarEstadoUsuarioOID(String userName, String compannia, String estado, String fechaVencimiento){
        //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
        //Si en los parametros de configuracion de usuari y contraseña en OID para la expiracion de una contraseña
        //el parametro pwdMaxAge es menor a 16 dias entonces no es posible
        //utilizar las funciones que consultan el estado del usuario en OID
        
        //Ahora se comentan las lineas siguientes, se limitara el parametro par no
        //colocar dias inferiores a dentro de las politicas de seguridad
        //String pwdMaxAge=obtenerParametrosPassword("pwdMaxAge");
        //setParametrosPassword("pwdMaxAge","0");
        //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
        

      try {
         String rootContext = "";
         if ((compannia != null) && (compannia.equals("001"))) 
            rootContext = this.getOIDConnParam().getRootContextBCIE();
         else
            rootContext = this.getOIDConnParam().getRootContextJubilados();
          String dn = OIDM.getUserDN(userName, rootContext);
          if (dn == null)
              return this.SEG_USUARIO_NO_EXISTE;
            Attributes attrs = new BasicAttributes(true);
            //The DN of the user  has to be added to the uniqueMember attribute of the group 
            // to become a member
            if (estado != null && (estado.equals("DISABLED")||estado.equals("ENABLED"))){
                attrs.put(new BasicAttribute("orclisenabled", estado));
                attrs.put(new BasicAttribute("orclactiveenddate", fechaVencimiento));
            }else{
                return this.SEG_ESTADO_USUARIO_NO_VALIDO;
            }
              
            

            OIDM.updateAttributesToEntry(dn, attrs, oidConnParams.getAppContext(), oidConnParams.getAppContrasenya());
          //OIDM.updateAttributeToEntry(dn, "orclisenabled",estado,oidConnParams.getAppContext() + rootContext, oidConnParams.getAppContrasenya());
          
          
        } 
        catch (javax.naming.NoPermissionException nopermis)
        {
            return this.SEG_NO_PERMISO_OID;
        }
        catch (NamingException namingEx) { // for Directory errors
              return namingEx.getMessage();
        }finally{
            //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
          //setParametrosPassword("pwdMaxAge",pwdMaxAge);
            //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
            
        }

      return null;
    }

  /**
   * Función que cambia el estado al usuario indicado
   * Si envia algún parámetro null no será considerado.
   * @param userName : Codigo de Usuario a crear
   * @param clave : Clave de OID
   * @param compannia : Compañia para determinar el REALM
   * @param numeroEmpleado: número de empleado.
   * @param correoElectronico Correo electrónico.
   * @param nombre:  Nombre del usuario
   * @param nombreCompleto:  Nombre Completo (Nombre y Apellido)
   * @param apellido:  Apellido del usuario
   * @param fechaInicio:  Fecha de inicio del usuario
   * @param fechaVencimiento:  Fecha de vencimiento del usuario


   * @return codigo de Error.
   * */
    public String actualizarUsuarioOID(String userName, String clave, String compannia, 
                                     String numeroEmpleado, String correoElectronico, 
                                     String nombre,
                                    String nombreCompleto,
                                    String apellido,
                                    String fechaInicio,
                                    String fechaVencimiento){
        //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
        //Si en los parametros de configuracion de usuari y contraseña en OID para la expiracion de una contraseña
        //el parametro pwdMaxAge es menor a 16 dias entonces no es posible
        //utilizar las funciones que consultan el estado del usuario en OID
        
        //Ahora se comentan las lineas siguientes, se limitara el parametro par no
        //colocar dias inferiores a dentro de las politicas de seguridad
    //String pwdMaxAge=obtenerParametrosPassword("pwdMaxAge");
    //setParametrosPassword("pwdMaxAge","0");
    //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
    

      try {
         String rootContext = "";
         if ((compannia != null) && (compannia.equals("001"))) 
            rootContext = this.getOIDConnParam().getRootContextBCIE();
         else
            rootContext = this.getOIDConnParam().getRootContextJubilados();
          String dn = OIDM.getUserDN(userName, rootContext);

          Attributes attrs = new BasicAttributes(true);
          //The DN of the user  has to be added to the uniqueMember attribute of the group 
          // to become a member
          if (clave != null)
            attrs.put(new BasicAttribute("userpassword", clave));
          if (numeroEmpleado != null)
            attrs.put(new BasicAttribute("employeeNumber", numeroEmpleado));
          if (correoElectronico != null)
            attrs.put(new BasicAttribute("mail", correoElectronico));
          if (nombreCompleto != null)
            attrs.put(new BasicAttribute("displayName", nombreCompleto));
          if (nombre != null)
            attrs.put(new BasicAttribute("givenName", nombre));
          if (apellido != null)
            attrs.put(new BasicAttribute("sn", apellido));
          if (fechaInicio != null)
            attrs.put(new BasicAttribute("orclactivestartdate", fechaInicio));
          if (fechaVencimiento != null)
            attrs.put(new BasicAttribute("orclactiveenddate", fechaVencimiento));
          
          //OIDM.updateAttributesToEntry(dn, attrs, oidConnParams.getAppContext() + rootContext, oidConnParams.getAppContrasenya());
          OIDM.updateAttributesToEntry(dn, attrs, oidConnParams.getAppContext(), oidConnParams.getAppContrasenya());
          
        } 
        catch (javax.naming.NoPermissionException nopermis)
        {
            return this.SEG_NO_PERMISO_OID; 
        }
        catch (NamingException namingEx) { // for Directory errors
            return obtieneOIDErrorCode(namingEx.getMessage()); 
        }finally{
            //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
            
          //setParametrosPassword("pwdMaxAge",pwdMaxAge);
            //Finailza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
            
        }

      return null;
    }

  /**
   * Función que obtiene el estado de un userName enviado
   * @param userName : Codigo de Grupo a validar
   * @param compannia : Compañia para determinar el REALM
   * @return String con el DN del grupo o NULL si no existe
   * */
  public boolean esUsuarioHabilitadoOID(String userName, String compannia) throws Exception {
    
      //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
      //Si en los parametros de configuracion de usuari y contraseña en OID para la expiracion de una contraseña
      //el parametro pwdMaxAge es menor a 16 dias entonces no es posible
      //utilizar las funciones que consultan el estado del usuario en OID
      
      //Ahora se comentan las lineas siguientes, se limitara el parametro par no
      //colocar dias inferiores a dentro de las politicas de seguridad
    //String pwdMaxAge=obtenerParametrosPassword("pwdMaxAge");
    //setParametrosPassword("pwdMaxAge","0");
      //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
      
    

    String rootContext = "";
    
    if ((compannia != null) && (compannia.equals("001"))) 
       rootContext = oidConnParams.getRootContextBCIE();
    else
       rootContext = oidConnParams.getRootContextJubilados();

    try {
        String estado = OIDM.getAttribute(userName,OIDConnParams.userContext,rootContext,"orclisenabled");
        
        // TODO: Esta implementación es muy riesgosa dado que si hay un userName q es subconjutno del otro falla.
        // tampco podemos comparar con la coma dado que puede q solo tenga u unico miembro.
        // Sugerencia: tokenizar o listar y luego buscar.
        if ((estado == null) || (estado.equals("ENABLED")))
            return true;
        else
            return false;
    }
    catch (NamingException namingEx) {
        throw new Exception("Error al obtener el estado del usuario " + namingEx.getMessage());
        }finally{
            //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
            //setParametrosPassword("pwdMaxAge",pwdMaxAge);
            //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
            
        }
  }
    /*
    public String getParametrosPassword(String atributo){
        
        CallableStatement oid = Util.getAppModuleImpl().getDBTransaction().createCallableStatement(Util.PL_PARAMETROS_OID,1);
        ResultSet rs=null;
      try {
          
            String SecurityQuery =
                "SELECT CODIGO_PARAMETRO CODIGO, VALOR FROM BCIE.UT_PARAMETRO_APLICACION_VAL WHERE CODIGO_APLICACION='SEG' AND CODIGO_PARAMETRO='PASS_OID_BCIE'" ;
           
            
            rs = Util.getAppModuleImpl().getDBTransaction().createStatement(0).getConnection().createStatement().executeQuery(SecurityQuery);

            String codigo=null;
            String atributo_valor=null;
            String dn=null;


            
            while(rs.next()){
            
                codigo=rs.getString("CODIGO");
                atributo_valor=rs.getString("VALOR");
                
                    if(codigo.equals("PASS_OID_BCIE")){
                        dn=atributo_valor;  
                    }
            }
            
          
          
          



          if (dn == null)
              return "DN incorrecto";
            
            String valor = OIDM.getGeneralAttribute(dn,this.getOIDConnParam().getAppContext(), this.getOIDConnParam().getAppContrasenya(),atributo);



            return valor;
        } 
        catch (javax.naming.NoPermissionException nopermis)
        {
            return this.SEG_NO_PERMISO_OID;
        }
        catch (NamingException namingEx) { // for Directory errors
              return namingEx.getMessage();
              }catch(SQLException ex)
      {

          return ex.getMessage();
          }
      finally{
          try{
              oid.close();
              rs.close();
          }catch(Exception ex){}
     }


    }
    
    public String setParametrosPassword(String atributo, String valor){
             ResultSet rs=null; 
           
           try {
               
               
                 String SecurityQuery =
                     "SELECT CODIGO_PARAMETRO CODIGO, VALOR FROM BCIE.UT_PARAMETRO_APLICACION_VAL WHERE CODIGO_APLICACION='SEG' AND CODIGO_PARAMETRO='PASS_OID_BCIE' OR CODIGO_PARAMETRO='PASS_OID_DEFAULT' OR CODIGO_PARAMETRO='PASS_OID_BCIEFPS'" ;
                
                 
                 rs = Util.getAppModuleImpl().getDBTransaction().createStatement(0).getConnection().createStatement().executeQuery(SecurityQuery);

                 String codigo=null;
                 String atributo_valor=null;
                 String dn=null;
                 String dn1=null;
                 String dn2=null;
                 
                 while(rs.next()){
                    
                      
                     codigo=rs.getString("CODIGO");
                     atributo_valor=rs.getString("VALOR");
                     
                         if(codigo.equals("PASS_OID_DEFAULT")){
                             dn2=atributo_valor;  
                           }
                     
                    if(codigo.equals("PASS_OID_BCIE")){
                        dn=atributo_valor;  
                      }
                     
                    if(codigo.equals("PASS_OID_BCIEFPS")){
                        dn1=atributo_valor;  
                      }
                }

               //String dn = "cn=default,cn=pwdPolicies,cn=Common,cn=Products,cn=OracleContext,dc=bcie,dc=org";
               //String dn1 = "cn=default,cn=pwdPolicies,cn=Common,cn=Products,cn=OracleContext,dc=bciefps,dc=org";
               //String dn2 = "cn=default,cn=pwdPolicies,cn=Common,cn=Products,cn=OracleContext";
               
               if (dn == null)
                   return "DN invalido";

        
               
               OIDM.updateAttributeToEntry(dn, atributo,valor,this.getOIDConnParam().getAppContext(), oidConnParams.getAppContrasenya());
               OIDM.updateAttributeToEntry(dn1, atributo,valor,this.getOIDConnParam().getAppContext(), oidConnParams.getAppContrasenya());
               OIDM.updateAttributeToEntry(dn2, atributo,valor,this.getOIDConnParam().getAppContext(), oidConnParams.getAppContrasenya());
               
             }
             catch (javax.naming.NoPermissionException nopermis)
             {
                 return this.SEG_NO_PERMISO_OID;
             }
             catch (NamingException namingEx) { // for Directory errors
                   return namingEx.getMessage();
            }catch(SQLException ex){
                    return ex.getMessage();
               }
           finally{
                   try{
                       
                       rs.close();
                   }catch(Exception ex){}
               }

           return null;
         }
    
    public String obtenerParametrosPassword(String atributo){
      ResultSet rs=null;
      try {
          
            String SecurityQuery =
                "SELECT CODIGO_PARAMETRO CODIGO, VALOR FROM BCIE.UT_PARAMETRO_APLICACION_VAL WHERE CODIGO_APLICACION='SEG' AND CODIGO_PARAMETRO='PASS_OID_DEFAULT'" ;
            
            
            rs = Util.getAppModuleImpl().getDBTransaction().createStatement(0).getConnection().createStatement().executeQuery(SecurityQuery);

            String codigo=null;
            String atributo_valor=null;
            String dn=null;

            
            while(rs.next()){
        
                codigo=rs.getString("CODIGO");
                atributo_valor=rs.getString("VALOR");
                
                    if(codigo.equals("PASS_OID_DEFAULT")){
                        dn=atributo_valor;  
                    }
            }

            
          if (dn == null)
              return this.SEG_USUARIO_NO_EXISTE;
            
            String valor = OIDM.getGeneralAttribute(dn,this.getOIDConnParam().getAppContext(), this.getOIDConnParam().getAppContrasenya(),atributo);
            return valor;
        } 
        catch (javax.naming.NoPermissionException nopermis)
        {
            return this.SEG_NO_PERMISO_OID;
        }
        catch (NamingException namingEx) { // for Directory errors
              return namingEx.getMessage();
        }
        catch(SQLException ex){
            return ex.getMessage();
            }
        finally{
                try{
                    
                    rs.close();
                }catch(Exception ex){}
            }
      
    }
    public String desbloquearUsuario(String usuario,String compannia){
              //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
              //Si en los parametros de configuracion de usuari y contraseña en OID para la expiracion de una contraseña
              //el parametro pwdMaxAge es menor a 16 dias entonces no es posible
              //utilizar las funciones que consultan el estado del usuario en OID
              
              //Ahora se comentan las lineas siguientes, se limitara el parametro par no
              //colocar dias inferiores a dentro de las politicas de seguridad
              //String pwdMaxAge=obtenerParametrosPassword("pwdMaxAge");
              //setParametrosPassword("pwdMaxAge","0"); 
              //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
              
            
            try {
                
                  String rootContext = "";
                  if ((compannia != null) && (compannia.equals("001"))) 
                     rootContext = this.getOIDConnParam().getRootContextBCIE();
                  else
                     rootContext = this.getOIDConnParam().getRootContextJubilados();
                   String dn = OIDM.getUserDN(usuario, rootContext);
                
                OIDM.desbloquearUsuario(dn, this.getOIDConnParam().getAppContext(), this.getOIDConnParam().getAppContrasenya());

              } 
              catch (javax.naming.NoPermissionException nopermis)
              {
                  return nopermis.getMessage();
              }
              catch (NamingException namingEx) { // for Directory errors
                  return namingEx.getMessage();
              }finally{
                  //Inicia mejora solicitada por Jose Chaves 2014/12/16 09:03AM
                //setParametrosPassword("pwdMaxAge",pwdMaxAge); 
                  //Finaliza mejora solicitada por Jose Chaves 2014/12/16 09:03AM
                
              }
            
            return null;
          }
    public String esUsuarioBloqueadoOID(String userName, String compannia) throws Exception {
      
      String rootContext = "";
      String estado=null;
      
      if ((compannia != null) && (compannia.equals("001"))) 
         rootContext = oidConnParams.getRootContextBCIE();
      else
         rootContext = oidConnParams.getRootContextJubilados();

      try {
          estado = OIDM.getAttribute(userName,OIDConnParams.userContext,rootContext,"orclpwdaccountunlock");
          
      }
      catch (NamingException namingEx) {
          throw new Exception("Error al obtener el estado del usuario " + namingEx.getMessage());
      }
      
      return estado;
    }
    */
}
