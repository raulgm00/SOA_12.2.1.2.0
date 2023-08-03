package org.bcie.fenix.view.lineacredito.beans;

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

    public Boolean getEsEditable() {
        return esEditable;
    }
    
    public String getLineasValidasGuardadas()
    {
        return "N";
    }
    
    /**
     * Ejecuta el metodo que asegura la existencia de un contrato para la operacion en el proceso actual.
     */
    public void asegurarExistenciaContratoProceso(){
        Long idOperacion = Long.parseLong((String)JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
        String instanciaProceso = JSFUtils.resolveExpressionAsString("#{pageFlowScope.pInstanciaProceso}");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("asegurarExistenciaContratoProceso");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
        operationBinding.execute();
                
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("asegurarExistenciaContratoProceso devuelve errores");
            JSFUtils.addFacesErrorMessage("Error al consultar contrato de la operacion.");
        }
    }
}
