package view;


import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.Attribute;
import javax.naming.directory.SearchResult;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;

import javax.naming.NamingException;
import javax.naming.NamingEnumeration;
import javax.naming.AuthenticationException;
import javax.naming.NameAlreadyBoundException;

import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Hashtable;


public class OIDManager {
    private OIDConnParams oidConnParams;

    public OIDManager(OIDConnParams oidConnParams) {
       this.oidConnParams = oidConnParams; 
    }

    public OIDConnParams getOIDConnParam(){
      return this.oidConnParams;
    }
  
    public void setOIDCOnnParam(OIDConnParams valor){
        this.oidConnParams = valor;
    }
    

    /**
     *  Inicializa a Directory Context with the specified credentials and return it.
     *  If the password is blank(null), it binds as anonymous user and returns the
     *  context.
     *  
     * @param username Directory user name
     * @param password Directory user password
     * @retur\n  valid directory context, if credentials are valid
     * @exception AuthenticationException  if credentails are invalid
     * @exception NamingException if directory operation fails
     */
    public DirContext getDirectoryContext(String username, String password) throws AuthenticationException, NamingException {
        DirContext dCtx = null;

        //Build the LDAP url 
        String ldapurl = "ldap://" + this.getOIDConnParam().getDirHostName() + ":" + this.getOIDConnParam().getDirPort();

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapurl);

        // if password is specified, set the credentials
        if (password != null) {
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, username);
            env.put(Context.SECURITY_CREDENTIALS, password);
        }

        // Bind and initialize the Directory context
        dCtx = new InitialDirContext(env);
        

        return dCtx;
    }

    public String getAttribute(String uname, String userGrupoContext, String rootContext, String atributo) throws NamingException {
        String valor;
        DirContext dCtx = null;

        dCtx = this.getDirectoryContext(this.getOIDConnParam().getAppContext(), this.getOIDConnParam().getAppContrasenya());

        SearchResult searchResult = null;
        NamingEnumeration results = null;
        String userDN = null;
        String filter = "(" + OIDConnParams.RDN + "=" + uname + ")";
        String finalContext = userGrupoContext + rootContext;//OIDConnParams.userContext + rootContext;

        // To set search controls to search with subtree scope
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        // Search the directory based on the search string from the specified context
        results = dCtx.search(finalContext, filter, searchControls);

        // If matching record found
        if (results.hasMore()) {
            searchResult = (SearchResult) results.next();
            // Build the User DN
            Attribute attr = searchResult.getAttributes().get(atributo);
            if (attr == null) {
                valor = null;
            } else {
                valor = attr.toString();
                int indice = valor.indexOf(":");

                if (indice >= 0) {
                    valor = valor.substring(indice + 1).trim();
                }

            }
        } else {// User not found
            valor = null;
        }

        return valor;
    }

    /**
     * Retrieves the Distinguished name of them of the specified RDN.
     * 
     * @param uname  Relative Distinguished name.
     * @return  Distinguished name of the user
     * @exception NamingException if directory operation fails
     */
    public String getUserDN(String uname, String rootContext) throws NamingException {
        DirContext dCtx = null;
        
        dCtx = this.getDirectoryContext(this.getOIDConnParam().getAppContext(), this.getOIDConnParam().getAppContrasenya());

        SearchResult searchResult = null;
        NamingEnumeration results = null;
        String userDN = null;
        String filter = "(" + OIDConnParams.RDN + "=" + uname + ")";
        String userContext = OIDConnParams.userContext + rootContext;

        // To set search controls to search with subtree scope
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        // Search the directory based on the search string from the specified context
        results = dCtx.search(userContext, filter, searchControls);

        // If matching record found
        if (results.hasMore()) {
            searchResult = (SearchResult) results.next();
            // Build the User DN
            userDN = searchResult.getName() + "," + userContext;
        } else {// User not found
            return null;
        }

        return userDN;
    }
    
  /**
   * PRUEBA JOSE CHAVES.Retrieves the Distinguished name of them of the specified RDN.
   * 
   * @param gname  Relative Distinguished name.
   * @return  Distinguished name of the user
   * @exception NamingException if directory operation fails
   */
  public String getGroupDN(String gname, String rootContext) throws NamingException {
      DirContext dCtx = null;

      dCtx = this.getDirectoryContext(this.getOIDConnParam().getAppContext(), this.getOIDConnParam().getAppContrasenya());

      SearchResult searchResult = null;
      NamingEnumeration results = null;
      String grupoDN = null;
      String filter = "(" + OIDConnParams.RDN + "=" + gname + ")";
      String userContext = OIDConnParams.groupContext + rootContext;

      // To set search controls to search with subtree scope
      SearchControls searchControls = new SearchControls();
      searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

      // Search the directory based on the search string from the specified context
      results = dCtx.search(userContext, filter, searchControls);

      // If matching record found
      if (results.hasMore()) {
          searchResult = (SearchResult) results.next();
          // Build the User DN
          grupoDN = searchResult.getName() + "," + userContext;
      } else {// User not found
          return null;
      }

      return grupoDN;
  }
    

    /**
     *  Authenticates the user credentials with Directory.
     *  
     * @param username  User Name of the user
     * @param passwd Password of the user
     * @return  string - if the credentials are valid
     * 
     * @exception AuthenticationException If credentials are invalid
     * @exception NamingException if any directory operation fails
     */
    public String authenticateUser(String username, String passwd, String rootContext) throws AuthenticationException, NamingException {
        String dn;
        boolean authorized = false;

        //leer nombre para FPS
        dn = this.getUserDN(username, rootContext);

        if (dn == null) {
            return dn;
        }

        try {
            // Authenticate with Directory
            this.getDirectoryContext(dn, passwd);
        } catch (AuthenticationException authEx) {
            throw new AuthenticationException("Contraseña Inválida.");
        }

        return dn;
    }

    /**
     * Creates an entry in Directory with the specified attributes and objectclass,  
     * with the specified Distingushed Name.
     * 
     * @param dn Distinguished name of the entry to be created
     * @param objCls Object classes that the entry must use
     * @param map Attribute,value mappings of the entry
     * @exception NamingException if adding entry fails
     * @exception NameAlreadyBoundException if user already exists
     */
    public void addDirectoryEntry(String dn, List objCls, Map map, String dnCrea, String dnContrasenyaCrea) throws NamingException, NameAlreadyBoundException {
        DirContext dCtx = null;

        // Create attribute list, ignore case of attribute names
        Attributes attrs = new BasicAttributes(true);

        if (!objCls.isEmpty()) {
            Attribute objclass = new BasicAttribute("objectclass");
            // Iterate thriough the collection and add the object classes to the attribute
            Iterator objclsIter = objCls.iterator();
            while (objclsIter.hasNext()) {
                // Add the object classes        
                objclass.add(objclsIter.next());
            }
            // Add the object class attribute to list
            attrs.put(objclass);
        }

        // Iterate through other attributes and add to attributes list
        Iterator attrsIter = map.entrySet().iterator();

        while (attrsIter.hasNext()) {
            Map.Entry attr = (Map.Entry) attrsIter.next();
            attrs.put(new BasicAttribute((String) attr.getKey(), attr.getValue()));
        }

        // add the directory entry to the directory with the attributes
        dCtx = this.getDirectoryContext(dnCrea, dnContrasenyaCrea);
        dCtx.createSubcontext(dn, attrs);

    }

  /**
   * Creates an entry in Directory with the specified attributes and objectclass,  
   * with the specified Distingushed Name.
   * 
   * @param dn Distinguished name of the entry to be created
   * @exception NamingException if adding entry fails
   */
  public void destroyDirectoryEntry(String dn, String dnCrea, String dnContrasenyaCrea) throws NamingException, NameAlreadyBoundException {
      DirContext dCtx = null;

      // add the directory entry to the directory with the attributes
      dCtx = this.getDirectoryContext(dnCrea, dnContrasenyaCrea);
      dCtx.destroySubcontext(dn);

  }

    /**
     *  Adds the specified user to the group.
     *  
     * @param uid Relative distinguished name of the entry 
     * @param groupdn Group to which the user has to be added
     * @exception NamingException if adding to group fails, or user is already a member
     */
    public void addToGroup(String dn, String groupdn, String dnCrea, String dnContrasenyaCrea) throws NamingException {
        // Build the distinguished name of the entry
        //String userdn = this.getUserDN(uid);

        DirContext dCtx = null;
        Attributes attrs = new BasicAttributes(true);

        //The DN of the user  has to be added to the uniqueMember attribute of the group 
        // to become a member
        attrs.put(new BasicAttribute("uniqueMember", dn));

        // Add the user as member
        dCtx = this.getDirectoryContext(dnCrea, dnContrasenyaCrea);
        dCtx.modifyAttributes(groupdn, DirContext.ADD_ATTRIBUTE, attrs);
    }

  /**
   *  Removes the specified user to the group.
   *  
   * @param dn Relative distinguished name of the entry 
   * @param groupdn Group to which the user has to be added
   * @param dnCrea  User que crea
   * @param dnContrasenyaCrea Password del usuario que crea
   * @exception NamingException if adding to group fails, or user is already a member
   */
  public void removeToGroup(String dn, String groupdn, String dnCrea, String dnContrasenyaCrea) throws NamingException {
      // Build the distinguished name of the entry
      //String userdn = this.getUserDN(uid);

      DirContext dCtx = null;
      Attributes attrs = new BasicAttributes(true);

      //The DN of the user  has to be added to the uniqueMember attribute of the group 
      // to become a member
      attrs.put(new BasicAttribute("uniqueMember", dn));

      // Add the user as member
      dCtx = this.getDirectoryContext(dnCrea, dnContrasenyaCrea);
      dCtx.modifyAttributes(groupdn, DirContext.REMOVE_ATTRIBUTE, attrs);
  }


    public String getTMPUserDN(String codigo, String rootContext) throws NamingException {
        DirContext dCtx = null;

        dCtx = this.getDirectoryContext(this.getOIDConnParam().getAppContext(), this.getOIDConnParam().getAppContrasenya());

        SearchResult searchResult = null;
        NamingEnumeration results = null;
        String userDN = null;
        String filter = "(employeenumber=" + codigo + ")";
        String userContext = OIDConnParams.userContext + rootContext;

        // To set search controls to search with subtree scope
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        // Search the directory based on the search string from the specified context
        results = dCtx.search(userContext, filter, searchControls);

        // If matching record found
        if (results.hasMore()) {
            searchResult = (SearchResult) results.next();
            // Build the User DN
            userDN = searchResult.getName() + "," + userContext;
        } else {// User not found
            return null;
        }

        return userDN;
    }
    
  /**
   * by chavesj: Actualiza un atributo único de un usuario enviado en el parámetro.
   *  
   * @param dnUserName distinguished name of the entry 
   * @param attributeName Nombre del atributo a actualizar.
   * @param attributeValue Valor del atributo a actualizar.
   * @param dnCrea Dn del usuario a autenticarse.
   * @param dnContrasenyaCrea Contraseña del usuario a autenticar/
   * @exception NamingException if adding to group fails, or user is already a member
   */
  public void updateAttributeToEntry(String dnEntry, String attributeName, String attributeValue,String dnCrea, String dnContrasenyaCrea) throws NamingException {
      // Build the distinguished name of the entry
      //String userdn = this.getUserDN(uid);

      DirContext dCtx = null;
      Attributes attrs = new BasicAttributes(true);
      //The DN of the user  has to be added to the uniqueMember attribute of the group 
      // to become a member
      attrs.put(new BasicAttribute(attributeName, attributeValue));

      // Add the user as member
      dCtx = this.getDirectoryContext(dnCrea, dnContrasenyaCrea);
      dCtx.modifyAttributes(dnEntry, DirContext.REPLACE_ATTRIBUTE, attrs);
      
  }    


  /**
   * by chavesj: Actualiza un conjunto de atributos de un usuario.
   *  
   * @param dnEntry distinguished name of the entry 
   * @param attrs Conjunto de atributos a actualizar
   * @param dnCrea Dn del usuario a autenticarse.
   * @param dnContrasenyaCrea Contraseña del usuario a autenticar
   * @exception NamingException if adding to group fails, or user is already a member
   */
  public void updateAttributesToEntry(String dnEntry, Attributes attrs,String dnCrea, String dnContrasenyaCrea) throws NamingException {
      // Build the distinguished name of the entry
      //String userdn = this.getUserDN(uid);

      DirContext dCtx = null;

      // Add the user as member
      dCtx = this.getDirectoryContext(dnCrea, dnContrasenyaCrea);
      dCtx.modifyAttributes(dnEntry, DirContext.REPLACE_ATTRIBUTE, attrs);
  }
  
    public String getAttribute(String userGrupoContext, String rootContext, String atributo) throws NamingException {
        String valor;
        DirContext dCtx = null;

        dCtx = this.getDirectoryContext(this.getOIDConnParam().getAppContext(), this.getOIDConnParam().getAppContrasenya());

        SearchResult searchResult = null;
        NamingEnumeration results = null;
        String userDN = null;
        String filter = "";
        String finalContext = userGrupoContext + rootContext;//OIDConnParams.userContext + rootContext;

        // To set search controls to search with subtree scope
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        // Search the directory based on the search string from the specified context
        results = dCtx.search(finalContext, filter, searchControls);

        // If matching record found
        if (results.hasMore()) {
            searchResult = (SearchResult) results.next();
            // Build the User DN
            Attribute attr = searchResult.getAttributes().get(atributo);
            if (attr == null) {
                valor = null;
            } else {
                valor = attr.toString();
                int indice = valor.indexOf(":");

                if (indice >= 0) {
                    valor = valor.substring(indice + 1).trim();
                }

            }
        } else {// User not found
            valor = null;
        }

        return valor;
    }
    
    public String getGeneralAttribute(String dn, String dnCrea, String dnContrasenyaCrea, String atributo) throws NamingException {
        String valor;
        DirContext dCtx = null;

        dCtx = this.getDirectoryContext(dnCrea, dnContrasenyaCrea);
                                                                       

        SearchResult searchResult = null;
        NamingEnumeration results = null;
        String userDN = null;
        String filter = "(" + OIDConnParams.RDN + "=default)";
        String finalContext = "cn=pwdPolicies,cn=Common,cn=Products,cn=OracleContext,dc=bcie,dc=org";

        // To set search controls to search with subtree scope
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        // Search the directory based on the search string from the specified context
        results = dCtx.search(finalContext, filter, searchControls);

        // If matching record found
        if (results.hasMore()) {
            searchResult = (SearchResult) results.next();
            // Build the User DN
            Attribute attr = searchResult.getAttributes().get(atributo);
            if (attr == null) {
                valor = null;
            } else {
                valor = attr.toString();
                int indice = valor.indexOf(":");

                if (indice >= 0) {
                    valor = valor.substring(indice + 1).trim();
                }

            }
        } else {// User not found
            valor = null;
        }

        return valor;
    }
    
    public String desbloquearUsuario(String dnUser, String dnCrea, String dnContrasenyaCrea) throws NamingException 
     {
         String valor;
         DirContext dCtx = null;
          Attributes attrs = new BasicAttributes(true);
         //The DN of the user  has to be added to the uniqueMember attribute of the group 
         // to become a member
         attrs.put(new BasicAttribute("orclpwdaccountunlock","1"));
     
         // Add the user as member
         dCtx = this.getDirectoryContext(dnCrea, dnContrasenyaCrea);
        
        dCtx.modifyAttributes(dnUser, javax.naming.directory.DirContext.ADD_ATTRIBUTE, attrs); 
         return "";

     }


    public OIDManager() {
    }
}
