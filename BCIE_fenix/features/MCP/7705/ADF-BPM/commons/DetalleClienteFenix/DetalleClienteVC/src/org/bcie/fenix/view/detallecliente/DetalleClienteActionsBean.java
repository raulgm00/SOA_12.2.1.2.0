package org.bcie.fenix.view.detallecliente;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class DetalleClienteActionsBean {
    
    private static ADFLogger logger = null;
    
    public DetalleClienteActionsBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public String getEnlacePagina(){
        String ruta="/faces/pages/gestorClientes.jspx";
        return ruta;
    }

    public void abrirGestorCliente(ActionEvent actionEvent){
        logger.log(ADFLogger.TRACE, "Inside abrirGestorCliente.");
        StringBuilder script = new StringBuilder();
        String codigoCliente = null;
        
        // Asignación de variables
        codigoCliente = JSFUtils.resolveExpression("#{bindings.Id.inputValue}") == null ? "" : 
            JSFUtils.resolveExpression("#{bindings.Id.inputValue}").toString();
        
        // Abrimos popup con aplicación del Gestor de Clientes
        ExtendedRenderKitService erks = Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                                                    ExtendedRenderKitService.class);
        script.append("window.open(\"").append(this.getUrlGestorCliente());
        script.append("?pIdCliente=").append(codigoCliente);
        script.append("\", \"DetalleCliente\"");
        script.append(",\"width=1024px,height=1024px,resizable=1,location=0,scrollbars=1,fullscreen=1\")");
        erks.addScript(FacesContext.getCurrentInstance(), script.toString());
    }
    
    //Se obtiene la URL_GESTOR_CLIENTES
    public String getUrlGestorCliente() {
        DCIteratorBinding configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = configuracionVOIterator.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[]{"URL_GESTOR_CLIENTES"}));
        String url = row == null ? null : (String) row.getAttribute("Valor");
        return url;
    }
}
