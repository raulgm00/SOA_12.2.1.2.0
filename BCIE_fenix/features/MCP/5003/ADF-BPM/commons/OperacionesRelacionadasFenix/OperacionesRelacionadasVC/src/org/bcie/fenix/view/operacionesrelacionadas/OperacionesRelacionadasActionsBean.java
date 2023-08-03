package org.bcie.fenix.view.operacionesrelacionadas;

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

public class OperacionesRelacionadasActionsBean {
    
    private static ADFLogger logger = null;
    
    public OperacionesRelacionadasActionsBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public String getEnlacePagina(){
        String ruta="/faces/pages/gestorOperaciones.jspx";
        return ruta;
    }
    
    public void abrirDetalleOperacion(ActionEvent actionEvent){
        logger.log(ADFLogger.TRACE, "Inside abrirDetalleOperacion.");
        StringBuilder script = new StringBuilder();
        String idOperacion = null;
        
        // Asignación de variables
        idOperacion = JSFUtils.resolveExpression("#{bindings.IdOperacion.inputValue}") == null ? "" : 
            JSFUtils.resolveExpression("#{bindings.IdOperacion.inputValue}").toString();
        
        // Abrimos popup con aplicación del Gestor de Operaciones
        ExtendedRenderKitService erks = Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                                                    ExtendedRenderKitService.class);
        script.append("window.open(\"").append(this.getUrlGestorOperaciones());
        script.append("?pIdOperacion=").append(idOperacion);
        script.append("\", \"DetalleOperacion\"");
        script.append(",\"width=1024px,height=1024px,resizable=1,location=0,scrollbars=1,fullscreen=1\")");
        erks.addScript(FacesContext.getCurrentInstance(), script.toString());
    }
    
    public String getUrlGestorOperaciones() {
        DCIteratorBinding configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = configuracionVOIterator.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[]{"URL_GESTOR_OPERACIONES"}));
        String url = row == null ? null : (String) row.getAttribute("Valor");
        return url;
    }
}
