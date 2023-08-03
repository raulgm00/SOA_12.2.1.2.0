package www.bcie.org.service;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class ConvertirDocumentoBean {
    @Resource
    SessionContext sessionContext;

    public ConvertirDocumentoBean() {
    }

    @WebMethod
    public byte[] generarAvisoDetallado(@WebParam(name = "tipoOrigen")String tipoOrigen,@WebParam(name = "tipoDestino")String tipoDestino,  @WebParam(name = "base64") byte[] base64) {
        String ret = "";
        byte[] response = base64;
        return response;
    }
}
