package org.bcie.fenix.common.model.vo;

import java.util.ArrayList;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.SolicitudExcepcionVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Nov 07 17:17:57 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SolicitudExcepcionVOImpl extends ViewObjectImpl implements SolicitudExcepcionVO {
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public SolicitudExcepcionVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
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

    /**
     * Returns the bind variable value for pIdContratoDesembolso.
     * @return bind variable value for pIdContratoDesembolso
     */
    public Long getpIdContratoDesembolso() {
        return (Long) getNamedWhereClauseParam("pIdContratoDesembolso");
    }

    /**
     * Sets <code>value</code> for bind variable pIdContratoDesembolso.
     * @param value value to bind as pIdContratoDesembolso
     */
    public void setpIdContratoDesembolso(Long value) {
        setNamedWhereClauseParam("pIdContratoDesembolso", value);
    }
    
    public void setpIdOperacionContratoSolExc(Long idOp, Long idCont) {
        logger.warning(" setpIdOperacion como where clause ->"+idOp);
        setNamedWhereClauseParam("pIdOperacion", idOp);
        logger.warning(" setpIdContratoDesembolso como where clause ->"+idCont);
        setNamedWhereClauseParam("pIdContratoDesembolso", idCont);
        executeQuery();
    }
    
    public ArrayList<Row> recuperarReglasActivasDesembolso(Long idOp, Long idCont){
        logger.warning("*Inf, Inicia el metodorecuperarReglasActivasDesembolso");                
        logger.warning("*Inf, idOperacion: "+idOp+ " idDesembolso: "+idCont);
        ArrayList<Row> reglasActivas = new ArrayList<Row>();
        
        if(idOp == null ||idCont == null){
           logger.warning("***Error, Parametros requeridos resueltos a null");
            JboException ex = new JboException("Error no se pudo recuperar idOperacion, idDesembolso");
            throw ex;   
        }
        
        setNamedWhereClauseParam("pIdOperacion", idOp);
        setNamedWhereClauseParam("pIdContratoDesembolso", idCont);
        executeQuery();
        
        logger.warning("*Inf, numero de reglas activas recuperadas: "+getEstimatedRowCount());        
        
        if(getEstimatedRowCount() > 0){            
            setRangeSize(-1);
            
            for(Row regla : getAllRowsInRange()){
                logger.warning("*Inf, agregando regla Activa: "+regla.getAttribute("IdTcaEstado"));
                reglasActivas.add(regla);
            }
        }else{            
            logger.warning("*Inf, Importante! no se recuperaron reglas activas para el contrato : "+idCont);
            reglasActivas = null;
        }  
        
        logger.warning("*Inf, Termina el metodorecuperarReglasActivasDesembolso");
        return reglasActivas;
    }
    
}

