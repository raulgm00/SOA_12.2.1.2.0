package www.bcie.org.service;

import java.util.ArrayList;

import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.jws.soap.SOAPBinding;

import javax.xml.bind.annotation.XmlSeeAlso;
import www.bcie.org.avisovencimiento.Aviso;
import www.bcie.org.avisovencimiento.ObjectFactory;
import www.bcie.org.javabeans.ReporteResponse;

@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ ObjectFactory.class })
@Stateless
@WebService(name = "avisoCobro", serviceName = "generAvisoDetalladoService",
            targetNamespace = "www.bcie.org/ofismatica", portName = "generAvisoDetalladoPort",
            wsdlLocation = "/WEB-INF/GenerarReporte.wsdl")
public class AvisoCobroImpl {
    public AvisoCobroImpl() {
    }
    @WebMethod
    public String generarAvisoDetallado(@WebParam(name = "aviso", partName = "parameters",
                                                  targetNamespace = "http://www.bcie.org/herramientaOfismatica")
                                        Aviso parameters) {
        ReporteResponse reporte = new ReporteResponse();
        ArrayList<byte[]> lstFiles = new ArrayList<byte[]>();
        lstFiles = reporte.generarReporte("AvisoCobroConsolidado.jrxml", parameters.getConsolidado(),null,null,null,null,null);
        String resultado = reporte.generarRespuestaReporte(lstFiles.get(1),lstFiles.get(0));
        return resultado;
    }
}
