package org.bcie.fenix.view.consultarinformacionprepago;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.JboException;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.JSFUtils;

import org.bcie.fenix.common.view.beans.FenixActionBean;

import pa12prepagoght.bean.PrepagoBean;

public class ConsultarInformacionPrepagoBean extends FenixActionBean implements Serializable{
    @SuppressWarnings("compatibility:2256318803965206621")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    private List<String> listaLineaCredito = new ArrayList<>();
    private List<String> listaContratoDesembolso = new ArrayList<>();
    
    Boolean isVisible = Boolean.TRUE;

    public ConsultarInformacionPrepagoBean() {
        super();
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    /**
     * Se crea metodo para mapear a Coberturas y Fuentes externas las listas
     * Linea de credito y Contrato desembolso.
     * @param
     * @since 07/09/2016
     */
    public void incializarTablasInformacionPrepago() {
        logger.log(ADFLogger.WARNING, "INTO consultarDatosSolicitud");
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> listaContratoDesembolso = new ArrayList<String>();

        if (null != JSFUtils.resolveExpression("#{pageFlowScope.pContratoDesembolso}")) {
            listaContratoDesembolso = (List<String>) JSFUtils.resolveExpression("#{pageFlowScope.pContratoDesembolso}");
            map.put("lineaContrato", listaContratoDesembolso);
        } else {
            logger.log(ADFLogger.WARNING, "El valor de la lista de Contrato de desmbolso es nulo.");
        }
        Integer pIdTcaTareaBpm = null;
        logger.log(ADFLogger.WARNING, "Tamaño de la lista contrato de desmbolso." + listaContratoDesembolso.size());

        BindingContainer bindings = getBindings();

        if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdTcaTareaBpm}")) {
            try {
                pIdTcaTareaBpm = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTcaTareaBpm}").toString());
            } catch(Exception e) {
                logger.log(ADFLogger.WARNING, "No es posible castear el Id de Tarea.", e);
            }
            
            logger.log(ADFLogger.WARNING, "Valor de la Tarea BPM :" + pIdTcaTareaBpm);
            if (pIdTcaTareaBpm == FenixConstants.PA12_VALIDAR_PENALIDAD_PREPAGO_COFI) {
                isVisible = Boolean.FALSE;
            }
        } else {
            logger.log(ADFLogger.WARNING, "Valor de la tarea BPM es nula.");
        }

        try {
            OperationBinding operationBindingCoberturas = bindings.getOperationBinding("consultarCoberturas");
            operationBindingCoberturas.getParamsMap().put("map", map);
            operationBindingCoberturas.execute();
            
            if (!operationBindingCoberturas.getErrors().isEmpty()) {
                logger.warning("Error al ejecutar binding validaPreinversion: " + operationBindingCoberturas.getErrors());
                JSFUtils.addFacesErrorMessage("Error al Consultar tabla Coberturas. Por favor intente m\u00E1s tarde.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al ejecutar el metodo consultarCoberturas", e);
            JSFUtils.addFacesErrorMessage("Error al Consultar tabla Coberturas. Por favor intente más tarde.");
        }
        
        try {
            OperationBinding operationBindingFuentesExternas = bindings.getOperationBinding("consultarFuentesExternas");
            operationBindingFuentesExternas.getParamsMap().put("map", map);
            operationBindingFuentesExternas.execute();
            
            if (!operationBindingFuentesExternas.getErrors().isEmpty()) {
                logger.warning("Error al ejecutar binding validaPreinversion: " + operationBindingFuentesExternas.getErrors());
                JSFUtils.addFacesErrorMessage("Error al Consultar la tabla Fuentes Externas. Por favor intente m\u00E1s tarde.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al ejecutar consultarFuentesExternas ", e);
            JSFUtils.addFacesErrorMessage("Error al Consultar la tabla Fuentes Externas. Por favor intente más tarde.");

        }
        
        try {
            OperationBinding operationBindingVentaCartera =
                bindings.getOperationBinding("consultarVentaCarteraPrepago");
            operationBindingVentaCartera.getParamsMap().put("map", map);
            operationBindingVentaCartera.execute();
            
            if (!operationBindingVentaCartera.getErrors().isEmpty()) {
                logger.warning("Error al ejecutar binding validaPreinversion: " + operationBindingVentaCartera.getErrors());
                JSFUtils.addFacesErrorMessage("Error al Consultar la tabla Venta de Cartera. Por favor intente m\u00E1s tarde.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al ejecutar consultarVentaCarteraPrepago ", e);
            JSFUtils.addFacesErrorMessage("Error al Consultar la tabla Venta de Cartera. Por favor intente más tarde.");
            
        }
    }
    
    public void setListaLineaCredito(List<String> listaLineaCredito) {
        this.listaLineaCredito = listaLineaCredito;
    }

    public List<String> getListaLineaCredito() {
        return listaLineaCredito;
    }

    public void setListaContratoDesembolso(List<String> listaContratoDesembolso) {
        this.listaContratoDesembolso = listaContratoDesembolso;
    }

    public List<String> getListaContratoDesembolso() {
        return listaContratoDesembolso;
    }
    
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }
}
