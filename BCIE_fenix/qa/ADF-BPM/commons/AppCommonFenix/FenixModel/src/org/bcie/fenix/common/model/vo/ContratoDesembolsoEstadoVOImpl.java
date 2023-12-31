package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.am.FenixGestorDesembolsosAMImpl;
import org.bcie.fenix.common.model.vo.common.ContratoDesembolsoEstadoVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jan 20 20:42:55 CST 2021
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ContratoDesembolsoEstadoVOImpl extends ViewObjectImpl implements ContratoDesembolsoEstadoVO {
    /**
     * This is the default constructor (do not remove).
     */
    public ContratoDesembolsoEstadoVOImpl() {
    }
    
    /**
     * Application logger.
     */
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(ContratoDesembolsoEstadoVOImpl.class.getName());
    
    private static final String CLASS_NAME = ContratoDesembolsoEstadoVOImpl.class.getName();
    
    public Boolean actualizarEstadoContrato(String idContrato, Integer estado) {
        
        final String METHOD_NAME = "actualizarEstadoContrato" ;
        LOGGER.entering(CLASS_NAME, METHOD_NAME);
        
        
        FenixAMImpl fenixAMImplAMImpl = (FenixAMImpl) getApplicationModule();
        
        Boolean resultado = Boolean.TRUE;
        Long contrato = null;
        if (null != idContrato) {
            contrato = Long.parseLong(idContrato);

            Row row = fenixAMImplAMImpl.getContratoDesembolsoVO().obtenerContratoPorId(contrato);
            
            if (null != row) {
                LOGGER.warning("Estado anterior " + row.getAttribute("IdTcaEstado"));
                if (null != estado) {
                    row.setAttribute("IdTcaEstado", estado);
                    try {
                        getDBTransaction().commit();
                      
                    } catch (Exception e) {
                        resultado = Boolean.FALSE;
                        LOGGER.severe(e.getMessage());
                    }
                } else {
                    resultado = Boolean.FALSE;
                }
            } else {
                LOGGER.warning("El row del contrato es nulo.");
                resultado = Boolean.FALSE;
            }

        } else {
            resultado = Boolean.FALSE;
        }
        return resultado;
    }
}

