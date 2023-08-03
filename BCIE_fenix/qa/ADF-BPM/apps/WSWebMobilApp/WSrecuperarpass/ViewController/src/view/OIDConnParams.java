package view;

/**
 * Clase que contiene las variables que definen los parametros de conexion para OID
 */
public class OIDConnParams {
    //reinos de funcionarios y jubilados

    
    private String OC_REALM_BCIE = null; 
    private String OC_REALM_JUBILADOS = null; 
    //contrasenya de app
    private String appContrasenya = null; 
    //contextos de realms
    //Host de OID
    private String dirHostName = null;
    //Puerto de OID
    private String dirPort = null; 
    //entrada de usuario
    public static final String RDN = "cn";
    // Identity Management Realm
    //contexto de aplicacion
    private String appContext = null;
    // Group context under Identity Realm, this is where all group entries are present
    public static final String groupContext = "cn=Groups,";
    // User context under Identity Realm, this is where all user entries are present
    public static final String userContext = "cn=Users,";
    //GRUPOS

    public OIDConnParams(String realmBCIE, String realmJubilados, String appContrasenya, String dirHostName, String dirPort, String appContext) {
        this.setRealmBCIE(realmBCIE);
        this.setRealmJuliados(realmJubilados);
        this.setAppContrasenya(appContrasenya);
        this.setDirHostName(dirHostName);
        this.setDirPort(dirPort);
        this.setAppContext(appContext);
    }
    
    public String getRootContextBCIE(){
      return "dc=" + this.getRealmBCIE() + ",dc=org";
    }
      
    public String getRootContextJubilados(){
      return "dc=" + this.getRealmJuliados() + ",dc=org";
    }
    
  public String getRealmBCIE(){
    return this.OC_REALM_BCIE;
  }

  public void setRealmBCIE(String valor){
      this.OC_REALM_BCIE = valor;
  }

  public String getRealmJuliados(){
    return this.OC_REALM_JUBILADOS;
  }

  public void setRealmJuliados(String valor){
      this.OC_REALM_JUBILADOS = valor;
  }

  public String getAppContrasenya(){
    return this.appContrasenya;
  }

  public void setAppContrasenya(String valor){
      this.appContrasenya = valor;
  }

  public String getDirHostName(){
    return this.dirHostName;
  }

  public void setDirHostName(String valor){
      this.dirHostName = valor;
  }

  public String getDirPort(){
    return this.dirPort;
  }

  public void setDirPort(String valor){
      this.dirPort = valor;
  }

  public String getAppContext(){
    return this.appContext;
  }

  public void setAppContext(String valor){
      this.appContext = valor;
  }
    
}