package org.bcie.fenix.view.lineacredito;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class AdministradorLineaCreditoBean implements Serializable {
    @SuppressWarnings("compatibility:3647651539495510121")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    Boolean esEditable = Boolean.TRUE;    

    // Lista de Vencimientos eliminados
    private List<Long> idsVencimientosEliminados = new ArrayList<Long>();
    
    // Lista de Comisiones eliminadas
    private List<Long> idsComisionesEliminadas = new ArrayList<Long>();
    
    public AdministradorLineaCreditoBean() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }


    public List<Long> getIdsVencimientosEliminados() {
        return idsVencimientosEliminados;
    }

    public void setIdsVencimientosEliminados(List<Long> idsVencimientosEliminados) {
        this.idsVencimientosEliminados = idsVencimientosEliminados;
    }

    public List<Long> getIdsComisionesEliminadas() {
        return idsComisionesEliminadas;
    }

    public void setIdsComisionesEliminadas(List<Long> idsComisionesEliminadas) {
        this.idsComisionesEliminadas = idsComisionesEliminadas;
    }
    
    public void setEsEditable(Boolean esEditable) {
        this.esEditable = esEditable;
    }    
    
    private Boolean deshabilitarMontoLinea = Boolean.TRUE;    


    public void esProductoOperacionIFI(){
      logger.warning("inicia metodo esProductoOperacionIFI");
       Boolean esIFI = Boolean.FALSE;
       
        try{
        
            Long idOperacion =  Long.parseLong((String)JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
            
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("esProductoOperacionIFI");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.execute();
                    
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding devuelve errores");
                JSFUtils.addFacesErrorMessage("Error, al consultar el producto de la operacion");
            }else{
                 esIFI =(Boolean)operationBinding.getResult();
                 deshabilitarMontoLinea = (esIFI) ? Boolean.FALSE : Boolean.TRUE;
            }                    
            
            
        } catch (Exception e) {
            logger.warning("Erroral consultar el producto de la operacion ", e);
        }
        
      logger.warning("termina metodo esProductoOperacionIFI deshabilitarMontoLinea: "+deshabilitarMontoLinea);
    }
    
    public void setDeshabilitarMontoLinea(Boolean deshabilitarMontoLinea) {
        this.deshabilitarMontoLinea = deshabilitarMontoLinea;
    }

    public Boolean getDeshabilitarMontoLinea() {
        return deshabilitarMontoLinea;
    }

    public Boolean getEsEditable() {
        return esEditable;
    }
    
    public String getLineasValidasGuardadas()
    {
        return "N";
    }
   
    
}
