package mx.agarcia.ea.rib.broker;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mx.agarcia.ea.rib.broker.test.ConnectionUtil;

@WebServlet(name = "MonitorServlet", urlPatterns = { "/monitor" })
public class MonitorServlet extends HttpServlet {
    @SuppressWarnings("compatibility:-8095922157852931977")
        
    private static final long serialVersionUID = 1L;

    
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";



    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        operate(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        operate(request, response);
    }


    public void operate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>RIB Broker MiniConsole</title></head>");


        out.println("<body>");

        String option = request.getParameter("option");
        String topic = request.getParameter("topic");
        out.println("<h3>AG RIB MiniBroker</h3>");

        try {
            if ("start".equals(option)) {
                
                if( topic == null ){
                    topic = ConnectionUtil.queueName;
                }
                
                if (!RIBBroker.getInstance().isRunning()) {
                    RIBBroker.getInstance().subscribe(topic);
                    out.println("<p>Broker iniciado para <b>"+topic+"</b>...</p>");
                } else {
                    out.println("<p>Broker ya esta iniciado...debe detenerlo</p>");
                }

            } else if ("stop".equals(option)) {
                    RIBBroker.getInstance().stop();
                    out.println("<p>Broker detenido...</p>");

            } else if ("print".equals(option)) {
                    
                    out.println("<p>Mensajes recibidos:</p>" + RIBBroker.getInstance().htmlMessages());

            } else if ("status".equals(option)) {
                if (!RIBBroker.getInstance().isRunning()) {
                    out.println("<p>Broker esta arriba...  apunta a --><b>"+topic+"</b>...</p>");
                } else {
                    out.println("<p>Broker esta abajo</p>" );
                }

            } 
                else{
                    out.println("<p>No se solicitó acción sobre el Broker ...</p>");
                }
        } catch (Exception e) {
            out.println("<b>Error:</b> " + e.getMessage());

        }

        out.println("</body></html>");
        out.close();
    }
}
