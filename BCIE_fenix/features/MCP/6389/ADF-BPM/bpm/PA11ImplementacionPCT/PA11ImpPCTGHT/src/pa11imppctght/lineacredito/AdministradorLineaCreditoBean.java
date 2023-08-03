package pa11imppctght.lineacredito;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class AdministradorLineaCreditoBean implements Serializable {
    @SuppressWarnings("compatibility:6873984035427734661")
    private static final long serialVersionUID = -7670580458641381136L;

    private static ADFLogger logger = null;

    // Lista de Vencimientos eliminados
    private List<Long> idsVencimientosEliminados = new ArrayList<Long>();

    // Lista de Comisiones eliminadas
    private List<Long> idsComisionesEliminadas = new ArrayList<Long>();

    public AdministradorLineaCreditoBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
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
}
