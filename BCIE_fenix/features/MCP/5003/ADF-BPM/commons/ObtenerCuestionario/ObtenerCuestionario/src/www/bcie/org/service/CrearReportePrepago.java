package www.bcie.org.service;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import www.bcie.org.javabeans.ReporteResponse;
import www.bcie.org.prepago.PrePago;

@WebService
public class CrearReportePrepago {
    public CrearReportePrepago() {
        super();
    }

    @WebMethod
    public String generarReportePrepago(@WebParam(name = "prepago") PrePago prepago) {
        ReporteResponse reporte = new ReporteResponse();
        ArrayList<byte[]> lstFilesInterno = new ArrayList<byte[]>();
        ArrayList<byte[]> lstFilesExterno = new ArrayList<byte[]>();
        prepago.getConsolidado().get(0).setTipoReporte("INTERNO");
        prepago.getConsolidado().get(0).getDetalleCalculo().get(0).setTipoReporte("INTERNO");
        lstFilesInterno = reporte.generarReporte("ReportePrePago.jrxml", null,null,null,prepago.getConsolidado(),null,null);
        prepago.getConsolidado().get(0).setTipoReporte("CLIENTE");
        prepago.getConsolidado().get(0).getDetalleCalculo().get(0).setTipoReporte("CLIENTE");
        lstFilesExterno = reporte.generarReporte("ReportePrePago.jrxml", null,null,null,prepago.getConsolidado(),null,null);
        String resultado = reporte.generarRespuestaReporte(lstFilesInterno,lstFilesExterno);
        return resultado;
    }
}
