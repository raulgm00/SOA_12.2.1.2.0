package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.ObtenerDatosPrepagoByContratoFlexcube;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Dec 06 21:30:29 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ObtenerDatosPrepagoByContratoFlexcubeImpl extends ViewObjectImpl implements ObtenerDatosPrepagoByContratoFlexcube {
  
    private static ADFLogger logger = null;
  
    public ObtenerDatosPrepagoByContratoFlexcubeImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    
    public Long recuperarIdPrepagoByContratoFlexcube(String contratoFlexcube){
       logger.warning("Inicia el metodo recuperarIdPrepago ");
        Long idPrepago = null;
        
            setpContratoFlexcube(contratoFlexcube);
            executeQuery();
            logger.warning("Numero de registro recuperados: "+getEstimatedRowCount());
            
            if(getEstimatedRowCount() > 0){
               
                setCurrentRow(first());
                Row row = getCurrentRow();
                
                 idPrepago = (Long)row.getAttribute("IdPrepago");                
                
            }else{
                 logger.warning("Important, No se encontro ningun registro con el contratoFlexcube : "+contratoFlexcube);
                }       
         logger.warning("Termina el metodo recuperarIdPrepago idPrepago recuperado : "+idPrepago);
        return idPrepago;
        }


    /**
     * Returns the bind variable value for pContratoFlexcube.
     * @return bind variable value for pContratoFlexcube
     */
    public String getpContratoFlexcube() {
        return (String) getNamedWhereClauseParam("pContratoFlexcube");
    }

    /**
     * Sets <code>value</code> for bind variable pContratoFlexcube.
     * @param value value to bind as pContratoFlexcube
     */
    public void setpContratoFlexcube(String value) {
        setNamedWhereClauseParam("pContratoFlexcube", value);
    }
}

