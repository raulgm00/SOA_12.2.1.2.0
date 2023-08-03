package org.bcie.fenix.view.gestordesembolsos.consolidatransferencias;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import org.bcie.fenix.common.utils.JSFUtils;

public class ConsolidarTransferenciasBean implements Serializable {
    @SuppressWarnings("compatibility:1992461849527787240")
    private static final long serialVersionUID = 1L;
    private Boolean revertir;
    private Boolean consolidar;
    private Integer contador;
    private Long contrato;
    private Integer modalidad;
    private static ADFLogger logger = null;
    private String usuario;
    private String instanciaDProceso;
    
    public void cargaConsolidaciones() {
        logger.warning("Inicia metodo cargaConsolidaciones");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio


        consolidar = Boolean.FALSE;
        contador = 0;
        try {
            modalidad = (Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pModalidad}").toString()));
            logger.warning("modalidad " + modalidad);
        } catch (Exception ex) {
            logger.warning("estado no obtenido");
        }
        try {
            contrato = (Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.pIdContrato}").toString()));
            logger.warning("claveContrato " + contrato);
        } catch (Exception ex) {
            logger.warning("claveContrato no obtenido");
        }

        try {
            instanciaDProceso = (JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}").toString());
            logger.warning("instanciaDProceso " + instanciaDProceso);
        } catch (Exception ex) {
            logger.warning("instanciaDProceso no obtenido");
        }

        //recuperamos la sesión de usuario
        if (null != JSFUtils.resolveExpression("#{securityContext.userName}")) {
            setUsuario((String) JSFUtils.resolveExpression("#{securityContext.userName}"));
        }
        logger.warning("usuario: " + usuario);

        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio) / 1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo cargaConsolidaciones con una duracion de: " + tiempo + " segundos");
    }

    public ConsolidarTransferenciasBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public Boolean getRevertir() {
        return revertir;
    }

    public void setRevertir(Boolean revertir) {
        this.revertir = revertir;
    }

    public Boolean getConsolidar() {
        return consolidar;
    }

    public void setConsolidar(Boolean consolidar) {
        this.consolidar = consolidar;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public Long getContrato() {
        return contrato;
    }

    public void setContrato(Long contrato) {
        this.contrato = contrato;
    }

    public Integer getModalidad() {
        return modalidad;
    }

    public void setModalidad(Integer modalidad) {
        this.modalidad = modalidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getInstanciaDProceso() {
        return instanciaDProceso;
    }

    public void setInstanciaDProceso(String instanciaDProceso) {
        this.instanciaDProceso = instanciaDProceso;
    }
}
