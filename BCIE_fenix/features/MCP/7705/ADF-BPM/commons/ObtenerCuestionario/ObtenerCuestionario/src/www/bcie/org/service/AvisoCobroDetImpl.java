package www.bcie.org.service;

import com.itextpdf.text.pdf.codec.Base64;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import www.bcie.org.avisodetallado.Aviso;
import www.bcie.org.javabeans.ReporteResponse;


@WebService
public class AvisoCobroDetImpl {
    public AvisoCobroDetImpl() {
        super();
    }

    @WebMethod
    public String generarAvisoDet(@WebParam(name = "avisoDet") Aviso avisoDetallado) {
            ReporteResponse reporte = new ReporteResponse();
            ArrayList<byte[]> lstFiles = new ArrayList<byte[]>();
            lstFiles = reporte.generarReporte("AvisoCobroDetallado.jrxml",null,avisoDetallado.getResumen(),null,null,null,null);
            String resultado = reporte.generarRespuestaReporte(lstFiles.get(1),lstFiles.get(0));
            return resultado;
        }
}
