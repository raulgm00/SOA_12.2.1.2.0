package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.ConsultarProductoByOperacionVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Apr 11 12:12:04 CDT 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConsultarProductoByOperacionVOImpl extends ViewObjectImpl implements ConsultarProductoByOperacionVO {
    
    private static ADFLogger logger = null;
     
    public ConsultarProductoByOperacionVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }



    public void consultarProductoOperacion(Long idOperacion){
        logger.warning("*Inf, inicia metodo consultarProductoOperacion");
        Row row = null;
        Integer requiereTir = null;
        
        if(idOperacion != null){                                
            setpIdOperacion(idOperacion);
            executeQuery();
            
            if(getEstimatedRowCount() > 0){
                row = first();
                setCurrentRow(row);
                requiereTir = (Integer)row.getAttribute("RequiereTir");
                logger.warning("*Inf, requiereTir: "+requiereTir+ " en producto: "+row.getAttribute("Descripcion"));
            }else{
                logger.warning("*Inf, No se pudo recuperar el producto de la operacion");
                throw new JboException("No se pudo recuperar el producto de la operacion");            
            }    
        
        }else{
            logger.warning("*Error al consultar el producto de la operacion idOperacion es requerido");
            throw new JboException("Error al consultar el producto de la operacion idOperacion es requerido");  
        }
        logger.warning("*Inf, termina metodo consultarProductoOperacion");  
    }


    public Integer requiereTir(Long idOperacion){
      logger.warning("*Inf, inicia metodo requiereTir");
        Integer requiereTir = null;
        Row row = null;
        
        if(idOperacion != null){
        
                              
            setpIdOperacion(idOperacion);
            executeQuery();
            
            if(getEstimatedRowCount() > 0){
                row = first();
                setCurrentRow(row);
                requiereTir = (Integer)row.getAttribute("RequiereTir");
                logger.warning("*Inf, requiereTir: "+requiereTir+ " en producto: "+row.getAttribute("Descripcion"));
            }else{
                logger.warning("*Inf, No se pudo recuperar el producto de la operacion");
                throw new JboException("No se pudo recuperar el producto de la operacion");            
            }    
        
        }else{
            logger.warning("*Error al consultar el producto de la operacion idOperacion es requerido");
            throw new JboException("Error al consultar el producto de la operacion idOperacion es requerido");  
        }
        logger.warning("*Inf, termina metodo requiereTir");  
        return requiereTir;      
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

