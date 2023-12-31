package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.TcaCategoriaCondicionVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jan 14 19:25:11 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TcaCategoriaCondicionVOImpl extends ViewObjectImpl implements TcaCategoriaCondicionVO {
    
    /**
     * Log de la aplicacion
     */
    private static ADFLogger logger = ADFLogger.createADFLogger(TcaCategoriaCondicionVOImpl.class);
    
    /**
     * Define nombre de view criteria para buscar registros de categoria condicion activos
     */
    public static final String CAT_CONDICION_ACTIVOS_VC = "CategoriaCondicionActivos";
    
    /**
     * This is the default constructor (do not remove).
     */
    public TcaCategoriaCondicionVOImpl() {
    }
    
    /**
     * Realiza la busqueda de registros activos de Categoria Condicion
     */
    public void buscarCategoriaCondicionActivos(){
        
        ViewCriteria vc = null;
        try{
            vc = getViewCriteria(CAT_CONDICION_ACTIVOS_VC);
            applyViewCriteria(vc);
            executeQuery();
            
            setCurrentRow(first());
        }catch(Exception e){
            logger.severe("Error al ejecutar view criteria: " + CAT_CONDICION_ACTIVOS_VC);
        }
    }
}

