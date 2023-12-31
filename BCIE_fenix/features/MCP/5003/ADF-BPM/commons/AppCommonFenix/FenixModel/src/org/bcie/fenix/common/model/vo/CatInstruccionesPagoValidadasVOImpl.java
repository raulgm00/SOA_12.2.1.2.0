package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.CatInstruccionesPagoValidadasVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Aug 17 12:50:40 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CatInstruccionesPagoValidadasVOImpl extends ViewObjectImpl implements CatInstruccionesPagoValidadasVO {
    
    private static ADFLogger logger = null;
    
    public CatInstruccionesPagoValidadasVOImpl() {        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }        
    }
    
    
    public void cargarTabla(Long idOperacion){
      logger.warning("*Inf, Inicia el metodo cargarTabla para el catalogo de instrucciones de pago");
      logger.warning("*Inf, idOperacion: "+idOperacion); 
      
          if(idOperacion != null){
             setpIdOperacion(idOperacion);
             executeQuery();        
             logger.warning("*Inf, numero de instrucciones de pago encontradas: "+getEstimatedRowCount());        
          }else{
              logger.warning("*Error idOperacion null, no se cargara el catalogo de instrucciones pago"); 
          }
             
      logger.warning("*Inf, Termina el metodo cargarTabla para el catalogo de instrucciones de pago");
    }
    
    
    public Row recuperarInstruccionPago(String noCuenta){
      logger.warning("*Inf, Inicia el metodo recuperarInstruccionPago noCuenta:"+noCuenta);
        Row fila = null;
        ViewCriteria criteria = null;

        try {
            criteria = getViewCriteriaManager().getViewCriteria("recuperarInstruccionPagoByNoCuentaVC");
            criteria.ensureVariableManager().setVariableValue("numeroCuenta", noCuenta);
            applyViewCriteria(criteria);
            executeQuery();

            if (getEstimatedRowCount() > 0) {                
                fila = first();
                logger.warning("Row recuperado para la transferencia ->" + fila.getAttribute("IdTransferencia"));
            } else {
                logger.warning("no hay coincidencias en la busqueda para la instruccion de pago");
            }

        } catch (Exception e) {
            logger.warning("*** Error al buscar instruccion pago ->",e);
        } finally {
            getViewCriteriaManager().removeApplyViewCriteriaName("recuperarInstruccionPagoByNoCuentaVC");    
            executeQuery();
        }

        logger.warning("*Inf, Termina el metodo recuperarInstruccionPago") ;  
        return fila;     
    }


    /**
     * Returns the variable value for numeroCuenta.
     * @return variable value for numeroCuenta
     */
    public String getnumeroCuenta() {
        return (String) ensureVariableManager().getVariableValue("numeroCuenta");
    }

    /**
     * Sets <code>value</code> for variable numeroCuenta.
     * @param value value to bind as numeroCuenta
     */
    public void setnumeroCuenta(String value) {
        ensureVariableManager().setVariableValue("numeroCuenta", value);
    }

    /**
     * Returns the bind variable value for pIdOperacion.
     * @return bind variable value for pIdOperacion
     */
    public Long getpIdOperacion() {
        return (Long) getNamedWhereClauseParam("pIdOperacion");
    }

    /**
     * Sets <code>value</code> for bind variable pIdOperacion.
     * @param value value to bind as pIdOperacion
     */
    public void setpIdOperacion(Long value) {
        setNamedWhereClauseParam("pIdOperacion", value);
    }
}

