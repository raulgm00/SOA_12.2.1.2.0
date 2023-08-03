package www.bcie.org.service;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import javax.jws.WebService;
import www.bcie.org.javabeans.ReporteResponse;
import www.bcie.org.recibopago.ReciboPago;

@WebService
public class CrearReciboPago {
    public CrearReciboPago() {
        super();
    }

    @WebMethod
    public String generarReciboPago(@WebParam(name = "ReciboPago") ReciboPago reciboPago) {
        ReporteResponse reporte = new ReporteResponse();
        ArrayList<byte[]> lstFiles = new ArrayList<byte[]>();
        lstFiles = reporte.generarReporte("ReciboPago.jrxml", null,null,reciboPago.getRecibo(),null,null,null);
        String resultado = reporte.generarRespuestaReporte(lstFiles.get(1),lstFiles.get(0));
        return resultado;
    }
}
