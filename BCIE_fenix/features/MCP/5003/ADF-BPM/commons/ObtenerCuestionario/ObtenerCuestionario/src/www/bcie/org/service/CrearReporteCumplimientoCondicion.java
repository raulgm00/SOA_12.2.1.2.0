package www.bcie.org.service;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import www.bcie.org.cumplimientocondiciones.Cumplimiento;
import www.bcie.org.javabeans.ReporteResponse;

@WebService
public class CrearReporteCumplimientoCondicion {
    public CrearReporteCumplimientoCondicion() {
        super();
    }

    @WebMethod
    public String generarCumplimientoCondicion(@WebParam(name = "cumplimiento") Cumplimiento cumplimientoCondicion) {
            ReporteResponse reporte = new ReporteResponse();
            ArrayList<byte[]> lstFiles = new ArrayList<byte[]>();
            lstFiles = reporte.generarReporte("CumplimientoCondicion.jrxml", null,null,null,null,null,cumplimientoCondicion.getLstCondicion());
            String resultado = reporte.generarRespuestaReporte(lstFiles.get(1),lstFiles.get(0));
            return resultado;
        }
}
