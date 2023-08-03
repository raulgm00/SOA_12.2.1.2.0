package loging;

import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

import loging.ActiveDirectory;

public class AuthenticateUser {
   private static final String contextFactory = "com.sun.jndi.ldap.LdapCtxFactory";

   private static DirContext ldapContext(String ldapUri) throws Exception {
      Hashtable<String, String> env = new Hashtable();
      return ldapContext(env, ldapUri);
   }

   private static DirContext ldapContext(Hashtable<String, String> env, String ldapUri) throws Exception {
      env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
      env.put("java.naming.provider.url", ldapUri);
      DirContext ctx = new InitialDirContext(env);
      return ctx;
   }

   private static String getUid(String user, String ldapUri) throws Exception {
      DirContext ctx = ldapContext(ldapUri);
      String filter = "(cn=" + user + ")";
      SearchControls ctrl = new SearchControls();
      ctrl.setSearchScope(2);
      NamingEnumeration answer = ctx.search("", filter, ctrl);
      String dn;
      if (answer.hasMore()) {
         SearchResult result = (SearchResult)answer.next();
         dn = result.getNameInNamespace();
      } else {
         dn = null;
      }

      answer.close();
      return dn;
   }

   public static String encode(String palabra) {
      String[] caracteres = palabra.split("a");
      String cadena = "";
      int llave = Integer.parseInt(caracteres[0]);

      for(int i = 1; i < caracteres.length; ++i) {
         int b = Integer.parseInt(caracteres[i]) - llave;
         cadena = cadena + "" + (char)b;
      }

      return cadena;
   }

   public static boolean testBind(String dn, String password, String ldapUri) throws Exception {
      Hashtable<String, String> env = new Hashtable();
      env.put("java.naming.security.authentication", "simple");
      env.put("java.naming.security.principal", dn);
      env.put("java.naming.security.credentials", password);

      try {
         ldapContext(env, ldapUri);
         return true;
      } catch (AuthenticationException var5) {
         return false;
      }
   }

   public static boolean authenticateUser(String user, String password, String ldapUri) throws Exception {
      user = encode(user);
      password = encode(password);
      String dn = getUid(user, ldapUri);
      if (dn != null) {
         if (testBind(dn, password, ldapUri)) {
            System.out.println("user '" + user + "' authentication succeeded");
            return true;
         } else {
            System.out.println("user '" + user + "' authentication failed");
            return false;
         }
      } else {
         System.out.println("user '" + user + "' not found");
         return false;
      }
   }
   
    public static boolean authenticateUser(String user, String password, String domainName, String serverName) throws Exception {
       user = encode(user);
       password = encode(password);
       boolean autentico = false;
       
        try
        {
            LdapContext ctx = null;
            if(serverName.isEmpty() || serverName == null)
            {
                ctx  = ActiveDirectory.getConnection(user, password, domainName);
            }
            else
            {
                ctx  = ActiveDirectory.getConnection(user, password, domainName, serverName);
            }
            
            ctx.close();
            autentico = true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            autentico = false;
        }
       
       return autentico;
    }

   public static String getUserName(String user) {
      user = encode(user);
      return user;
   }
}