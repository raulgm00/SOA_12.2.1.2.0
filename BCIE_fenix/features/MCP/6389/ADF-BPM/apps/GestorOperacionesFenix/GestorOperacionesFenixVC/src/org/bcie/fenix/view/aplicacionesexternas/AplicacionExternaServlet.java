package org.bcie.fenix.view.aplicacionesexternas;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.Configuration;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.am.FenixGestorOperacionesAMImpl;

public class AplicacionExternaServlet extends HttpServlet {
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    private static ADFLogger logger = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);

        int idTcaAppExterna;
        Long idOperacion = null;
        String idCliente = null;

        // Validacion de parametros de entrada
        if (null == request.getParameter(FenixConstants.SERVLET_APP_EXTERNA_ID) ||
            null == request.getParameter(FenixConstants.SERVLET_APP_EXTERNA_CODIGO_OPERACION) ||
            null == request.getParameter(FenixConstants.SERVLET_APP_EXTERNA_CODIGO_CLIENTE)) {
            mostrarError(response);
            return;
        }

        // Conversion y validacion del tipo de dato de parametros de entrada
        try {
            idTcaAppExterna = Integer.valueOf(request.getParameter(FenixConstants.SERVLET_APP_EXTERNA_ID));
            idOperacion = Long.valueOf(request.getParameter(FenixConstants.SERVLET_APP_EXTERNA_CODIGO_OPERACION));
            idCliente = request.getParameter(FenixConstants.SERVLET_APP_EXTERNA_CODIGO_CLIENTE);
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al recuperar atributos.", e);
            mostrarError(response);
            return;
        }

        response.sendRedirect(getUrlParametrizada(idTcaAppExterna, idOperacion, idCliente));

        // Para mostrar en pantalla informacion de desarrollo
        // requiere comentar la linea anterior
        /*PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>AplicaciónExterna</title></head>");
        out.println("<body>");
        out.println("<p>");
        out.println("Código Aplicación: " + idTcaAppExterna + "<br>");
        out.println("Código Operación: " + idOperacion + "<br>");
        out.println("Código Cliente: " + idCliente + "<br>");

        out.println("URL Parámetrizada: " +
                    getUrlParametrizada(idTcaAppExterna, idOperacion, idCliente) + "<br>");
        out.println("</p>");
        out.println("</body></html>");
        out.close();*/
    }

    @SuppressWarnings("unchecked")
    private String getUrlParametrizada(int idTcaAppExterna, Long idOperacion, String idCliente) {
        logger.log(ADFLogger.WARNING, "Entrando en getUrlParametrizada de Servlet IrAppExterna.");
        logger.log(ADFLogger.WARNING, "idTcaAppExterna: " + idTcaAppExterna);
        logger.log(ADFLogger.WARNING, "idOperacion: " + idOperacion);
        logger.log(ADFLogger.WARNING, "idCliente: " + idCliente);

        ApplicationModule applicationModule =
            Configuration.createRootApplicationModule("org.bcie.fenix.common.model.am.FenixGestorOperacionesAM",
                                                      "FenixGestorOperacionesAMLocal");
        FenixGestorOperacionesAMImpl fenixGestorOperacionesAMImpl = (FenixGestorOperacionesAMImpl) applicationModule;

        return fenixGestorOperacionesAMImpl.obtenerUrlParametrizada(idTcaAppExterna, idOperacion, idCliente);
    }

    private void mostrarError(HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Error</title></head>");
        out.println("<body>");
        out.println("<p>");
        out.println("El tipo o el valor de los parámetros requeridos no válidos.");
        out.println("</p>");
        out.println("</body></html>");
        out.close();
    }
}
