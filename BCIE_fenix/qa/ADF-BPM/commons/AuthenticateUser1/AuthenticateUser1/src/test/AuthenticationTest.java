package test;

import javax.naming.ldap.LdapContext;

import  loging.ActiveDirectory;
import  loging.AuthenticateUser;

public class AuthenticationTest {
    public AuthenticationTest() {
        super();
    }

    public static void main(String[] args) {
        AuthenticationTest authenticationTest = new AuthenticationTest();
        
        String user = "51a170a159a148a151a146a168a166a165";
        String password = "22a141a123a130a121a133a131a123a71a55";
        String uri = "ldap://hn-dc-qa-01.qa.bcie.org:389";
        
        try
        {
            //Boolean autentico = AuthenticateUser.authenticateUser(user, password, uri);
            //System.out.println("autentico " + autentico);
            
            LdapContext ctx  = ActiveDirectory.getConnection("aalfaro", "testfenix2015!", "qa.bcie.org", "hn-dc-qa-01"); //, "qa.bcie.org", "ldap://hn-dc-qa-01.qa.bcie.org:389/CN=Users,DC=qa,DC=BCIE,DC=ORG"
            ctx.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
    }
}
