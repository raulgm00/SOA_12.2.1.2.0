package www.bcie.org.service;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import www.bcie.org.javabeans.ReporteResponse;
import www.bcie.org.reportenoobjeciones.ReporteNoObjeciones;

@WebService
public class CrearReporteNoObjeciones {
    public CrearReporteNoObjeciones() {
        super();
    }

    @WebMethod
    public String generarReporteNoObjeciones(@WebParam(name = "RepNoObjeciones") ReporteNoObjeciones reporteNoObjeciones) {
            ReporteResponse reporte = new ReporteResponse();
            ArrayList<byte[]> lstFiles = new ArrayList<byte[]>();
            lstFiles = reporte.generarReporte("ReporteNoObjeciones.jrxml", null,null,null,null,reporteNoObjeciones.getReporteTemplate(),null);
            String resultado = reporte.generarRespuestaReporte(lstFiles.get(1),lstFiles.get(0));
            return resultado;
        }
}
