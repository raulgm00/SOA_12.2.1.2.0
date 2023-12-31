package org.bcie.fenix.common.model.vo;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.ValidarOperacionGestorTipoDocumentoVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jan 29 16:31:53 CST 2021
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ValidarOperacionGestorTipoDocumentoVOImpl extends ViewObjectImpl implements ValidarOperacionGestorTipoDocumentoVO {
    /**
     * This is the default constructor (do not remove).
     */
    public ValidarOperacionGestorTipoDocumentoVOImpl() {
    }

    /**
     * Returns the bind variable value for descripcionCorta.
     * @return bind variable value for descripcionCorta
     */
    public String getdescripcionCorta() {
        return (String) getNamedWhereClauseParam("descripcionCorta");
    }

    /**
     * Sets <code>value</code> for bind variable descripcionCorta.
     * @param value value to bind as descripcionCorta
     */
    public void setdescripcionCorta(String value) {
        setNamedWhereClauseParam("descripcionCorta", value);
    }

    /**
     * Returns the bind variable value for idOperacion.
     * @return bind variable value for idOperacion
     */
    public Integer getidOperacion() {
        return (Integer) getNamedWhereClauseParam("idOperacion");
    }

    /**
     * Sets <code>value</code> for bind variable idOperacion.
     * @param value value to bind as idOperacion
     */
    public void setidOperacion(Integer value) {
        setNamedWhereClauseParam("idOperacion", value);
    }

    /**
     * Returns the bind variable value for idTarea.
     * @return bind variable value for idTarea
     */
    public Integer getidTarea() {
        return (Integer) getNamedWhereClauseParam("idTarea");
    }

    /**
     * Sets <code>value</code> for bind variable idTarea.
     * @param value value to bind as idTarea
     */
    public void setidTarea(Integer value) {
        setNamedWhereClauseParam("idTarea", value);
    }
    
    public static ADFLogger logger = ADFLogger.createADFLogger(ValidarOperacionGestorTipoDocumentoVOImpl.class);
    
    public List<String> validarDocumentosGestor(Integer idTarea, Integer idOperacion, String descripcionCorta)
    {
        //Variables
        List<String> mensajes = new ArrayList<String>();
        
        //Se asignan los valores a los parametros
        this.setidTarea(idTarea);
        this.setidOperacion(idOperacion);
        this.setdescripcionCorta(descripcionCorta);
        
        this.executeQuery();
        
        logger.warning("validarDocumentosGestor count: "+ this.getEstimatedRowCount());
        
        if(this.getEstimatedRowCount() > 0)
        {
            RowSetIterator rs = this.createRowSetIterator(null);
            rs.reset();

            while (rs.hasNext()) {
                
                logger.warning("adentro del while ");
                ValidarOperacionGestorTipoDocumentoVORowImpl r = (ValidarOperacionGestorTipoDocumentoVORowImpl) rs.next();
                mensajes.add(r.getMensaje());
            }

            rs.closeRowSetIterator();
        }
        
        return mensajes;
    }
    
}

