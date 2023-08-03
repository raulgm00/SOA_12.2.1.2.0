package org.bcie.fenix.view.matriztcc;

import java.io.Serializable;

import java.math.BigDecimal;

import oracle.adf.share.logging.ADFLogger;

import org.apache.myfaces.trinidad.model.RowKeySet;

import org.bcie.fenix.common.utils.JSFUtils;

public class MatrizTccBean implements Serializable{
    
    @SuppressWarnings("compatibility:772959969672888616")
    private static final long serialVersionUID = 1L;
    
    private static ADFLogger logger = null;

    private Long idOperacion;
    
    private Integer idModalidad;
    
    private Boolean esModoEscritura;
    
    private Integer idTareaBpm;
    
    private BigDecimal montoSolicitado;
    
    private oracle.jbo.domain.Number idTccGridSeleccionado;
    
    private Integer idTcaTccSeleccionado;
    
    private Boolean esNombreTerminoVacio;
    
    // Variables para incidencia FNXII-3485.
    private RowKeySet disclosedRowKeysTcc; // Guarda el elemento seleccionado en el tree
    private Boolean esSelectionEventQueue = Boolean.FALSE; // Evita que se llame repetidamente al Selectionlistener para el mismo evento
    
    private Long idEnmienda;
    
    private Boolean esEstadoCompletado;

    public MatrizTccBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void evaluarParametroPStateBpm(){
        logger.warning("Dentro de evaluarParametroPStateBpm");
        String state = null;
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pStateBpm}") != null) {
                state = (String) JSFUtils.resolveExpression("#{pageFlowScope.pStateBpm}");
                logger.warning("state :" + state);
                setEsEstadoCompletado(state.equals("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
            }else{
                logger.warning("pState es nulo");
                setEsEstadoCompletado(Boolean.FALSE);
            }
        } catch (Exception ex) {
            logger.severe("Error al recuperar #{pageFlowScope.pStateBpm} :",ex);
            logger.warning("pState no obtenido");
        }

        logger.warning("esEstadoCompletado : " + getEsEstadoCompletado());
        logger.warning("Fuera de evaluarParametroPStateBpm");
    }
    
    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }

    public void setIdModalidad(Integer idModalidad) {
        this.idModalidad = idModalidad;
    }

    public Integer getIdModalidad() {
        return idModalidad;
    }

    public void setEsModoEscritura(Boolean esModoEscritura) {
        this.esModoEscritura = esModoEscritura;
    }

    public Boolean getEsModoEscritura() {
        return esModoEscritura;
    }

    public void setIdTareaBpm(Integer idTareaBpm) {
        this.idTareaBpm = idTareaBpm;
    }

    public Integer getIdTareaBpm() {
        return idTareaBpm;
    }
    
    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setIdTccGridSeleccionado(oracle.jbo.domain.Number idTccGridSeleccionado) {
        this.idTccGridSeleccionado = idTccGridSeleccionado;
    }

    public oracle.jbo.domain.Number getIdTccGridSeleccionado() {
        return idTccGridSeleccionado;
    }

    public void setIdTcaTccSeleccionado(Integer idTcaTccSeleccionado) {
        this.idTcaTccSeleccionado = idTcaTccSeleccionado;
    }

    public Integer getIdTcaTccSeleccionado() {
        return idTcaTccSeleccionado;
    }

    public void setEsNombreTerminoVacio(Boolean esNombreTerminoVacio) {
        this.esNombreTerminoVacio = esNombreTerminoVacio;
    }

    public Boolean getEsNombreTerminoVacio() {
        return esNombreTerminoVacio;
    }
    
    public void setDisclosedRowKeysTcc(RowKeySet disclosedRowKeysTcc) {
        this.disclosedRowKeysTcc = disclosedRowKeysTcc;
    }

    public RowKeySet getDisclosedRowKeysTcc() {
        return disclosedRowKeysTcc;
    }
    
    public void setEsSelectionEventQueue(Boolean esSelectionEventQueue) {
        this.esSelectionEventQueue = esSelectionEventQueue;
    }

    public Boolean getEsSelectionEventQueue() {
        return esSelectionEventQueue;
    }
    
    public void setIdEnmienda(Long idEnmienda) {
        this.idEnmienda = idEnmienda;
    }

    public Long getIdEnmienda() {
        return idEnmienda;
    }


    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
}
