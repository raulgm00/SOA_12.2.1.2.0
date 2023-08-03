package org.bcie.fenix.view.gestordesembolsos;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.share.logging.ADFLogger;

public class Login {
    
    public static ADFLogger logger = ADFLogger.createADFLogger(Login.class);
    private String _username;
    private String _password;

    public void setUsername(String _username) {
        this._username = _username;
    }

    public String getUsername() {
        return _username;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public String getPassword() {
        return _password;
    }
    
    public String doLogin() {
       FacesContext ctx = FacesContext.getCurrentInstance();

       if (_username == null || _password == null) {
           showError("Credenciales inv�lidas","Usuario o contrase�a incorrectos.", null);
       } else {
           ExternalContext ectx = ctx.getExternalContext();
           HttpServletRequest request = (HttpServletRequest)
                                        ctx.getExternalContext().getRequest();
          try { 
              request.login(_username, _password); // Servlet 3.0 login
              _username = null;
              _password = null;

              String loginUrl = ectx.getRequestContextPath() +
                                "/adfAuthentication?success_url=/faces/pages/inicio.jspx"; 
              redirect(loginUrl);
          } catch (ServletException fle) {
              showError("ServletException", "Fallo de autenticaci�n. Por favor verifique el usuario y contrase�a e intente nuevamente.", null);
          }
      }
      return null;
     }
    
    private void redirect(String forwardUrl) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = ctx.getExternalContext();
        try {
          ectx.redirect(forwardUrl);
        } catch (IOException ie) {
            showError("IOException", "Ocurri� un error al redireccionar la p�gina de inicio.  Por favor consulte el log para m�s informaci�n.", ie);
        }
     }
    
    private void showError(String errType, String message, Exception e){
      FacesMessage msg =
            new FacesMessage(FacesMessage.SEVERITY_ERROR, errType, message);
      FacesContext.getCurrentInstance().addMessage("d2:it35", msg);
      if (e != null) {
          logger.warning("ERROR: ", e);
      }
    }


    public String logoff() {
      FacesContext ctx = FacesContext.getCurrentInstance();
      ExternalContext ectx = ctx.getExternalContext();
      HttpServletRequest httpRequest = (HttpServletRequest) ectx.getRequest();
      try {
          httpRequest.logout(); // Servlet 3.0 logout
          HttpSession session = httpRequest.getSession(false);
          if (session != null) {
              session.invalidate();
          }
          String logoutUrl = ectx.getRequestContextPath() + "/faces/pages/inicio.jspx" +
                                ctx.getViewRoot().getViewId();
          redirect(logoutUrl);
      } catch (ServletException e) {
          showError("ServletException", "Ocurri� un error al cerrar la sesi�n. Por favor consulte el log para m�s informaci�n.", e);
      }
      return null;
    }
}
