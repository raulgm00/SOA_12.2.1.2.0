package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.vo.common.TreeCategoriaAccionGesVO;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Sep 08 12:56:02 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TreeCategoriaAccionGesVOImpl extends ViewObjectImpl implements TreeCategoriaAccionGesVO {
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public TreeCategoriaAccionGesVOImpl() {
        if (logger == null) {
        logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    /**
     * Returns the bind variable value for varGestorProceso.
     * @return bind variable value for varGestorProceso
     */
    public Integer getvarGestorProceso() {
        return (Integer) getNamedWhereClauseParam("varGestorProceso");
    }

    /**
     * Sets <code>value</code> for bind variable varGestorProceso.
     * @param value value to bind as varGestorProceso
     */
    public void setvarGestorProceso(Integer value) {
        if(null!=value){
                setNamedWhereClauseParam("varGestorProceso", value);
            }
    }

    public void cargaCategorias(Integer proceso) {
        logger.warning("Proceso:" + proceso);
        if (null != proceso) {
            switch (proceso) {

            case FenixModelConstants.ID_GESTOR_CLIENTES:
                break;
            case FenixModelConstants.ID_GESTOR_OPERACIONES:
                break;
            default:
                break;
            }
        }
    }

    public void mostrarlista() {
        RowSetIterator rowsCategoriaVo = createRowSetIterator(null);

        while (rowsCategoriaVo.hasNext()) {
            TreeCategoriaAccionGesVORowImpl rowL = (TreeCategoriaAccionGesVORowImpl) rowsCategoriaVo.next();
            logger.warning("Categoria: " + rowL.getAttribute("Descripcion"));
        }
        rowsCategoriaVo.closeRowSetIterator();
    }
}

