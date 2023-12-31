package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.TramoFormalizarVO;

import static org.bcie.fenix.common.model.FenixModelConstants.BANESTATUS_TRUE;
import static org.bcie.fenix.common.model.FenixModelConstants.BANESTATUS_FALSE;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jan 08 11:33:24 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TramoFormalizarVOImpl extends ViewObjectImpl implements TramoFormalizarVO {
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public TramoFormalizarVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    /**
     * Returns the bind variable value for idContratoTramo.
     * @return bind variable value for idContratoTramo
     */
    public Integer getIdContratoTramo() {
        return (Integer) getNamedWhereClauseParam("IdContratoTramo");
    }

    /**
     * Sets <code>value</code> for bind variable idContratoTramo.
     * @param value value to bind as idContratoTramo
     */
    public void setIdContratoTramo(Integer value) {
        logger.log(ADFLogger.WARNING, "Into setIdContratoTramo.");
        logger.log(ADFLogger.WARNING, "value:"+value);
        setNamedWhereClauseParam("IdContratoTramo", value);
        this.executeQuery();
        logger.log(ADFLogger.WARNING, "Cantidad de rows obtenidos:" +this.getRowCount());
    }
    
    public void crearTramoFormalizar(Long idContrato,String nombreTramo,BigDecimal montoTramo,
                                                BigDecimal porcentaje) {
        logger.log(ADFLogger.TRACE, "Into crearTramoFormalizar.");
        logger.log(ADFLogger.WARNING, "Value monto:"+montoTramo);
        Row tramoFormalizarRow = null;
        oracle.jbo.domain.Number idTramoFormalizar = null;
        NameValuePairs nvpTramoFormalizar = null;
        SequenceImpl seqTramoFormalizar = null;

        seqTramoFormalizar = new SequenceImpl("TRAMO_FORMALIZAR_SEQ", getDBTransaction());
        idTramoFormalizar = seqTramoFormalizar.getSequenceNumber();
        
        nvpTramoFormalizar = new NameValuePairs();
        nvpTramoFormalizar.setAttribute("Id", idTramoFormalizar);
        nvpTramoFormalizar.setAttribute("IdContrato", idContrato);
        nvpTramoFormalizar.setAttribute("NombreTramo", nombreTramo);
        nvpTramoFormalizar.setAttribute("Monto", montoTramo);
        nvpTramoFormalizar.setAttribute("Porcentaje", porcentaje);
        nvpTramoFormalizar.setAttribute("BanEstatus", BANESTATUS_TRUE);//default value 1
        nvpTramoFormalizar.setAttribute("FechaRegistro",
                                        new java.sql.Timestamp(System.currentTimeMillis()));
        
        tramoFormalizarRow = this.createAndInitRow(nvpTramoFormalizar);
                
        getDBTransaction().commit();
        
        //ejecutar query
        this.executeQuery();

    }
    
    public void eliminarTramoFormalizar(Long idTramoFormalizar) {
        logger.log(ADFLogger.TRACE, "Into eliminarTramoFormalizar.");
        Row tramoFormalizarRow = null;
        tramoFormalizarRow = this.getRow(new Key(new Object[] {idTramoFormalizar}));
        if(tramoFormalizarRow != null) {
            tramoFormalizarRow.setAttribute("BanEstatus", BANESTATUS_FALSE);
            getDBTransaction().commit();
        }
        //ejecutar query
        this.executeQuery();
    }
    
    public void actualizarTramoFormalizar(Long idTramoFormalizar,String nombreTramo,BigDecimal monto,
                                                    BigDecimal porcentaje) {
        logger.log(ADFLogger.TRACE, "Into actualizarTramoFormalizar.");
        Row tramoFormalizarRow = null;
        tramoFormalizarRow = this.getRow(new Key(new Object[] {idTramoFormalizar}));
        if(tramoFormalizarRow != null) {
            tramoFormalizarRow.setAttribute("NombreTramo",nombreTramo);
            tramoFormalizarRow.setAttribute("Monto",monto);
            tramoFormalizarRow.setAttribute("Porcentaje",porcentaje);
            getDBTransaction().commit();
        }
        //ejecutar query
        this.executeQuery();
    }
}

