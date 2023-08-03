package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;

import java.util.List;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class DetalleDesembolsosBean implements Serializable {
    
    @SuppressWarnings("compatibility:1574332716712512648")
    private static final long serialVersionUID = 1L;
    
    private static ADFLogger logger = null;

    private boolean disableDetalle;
    private boolean disableOperaciones;
    private boolean disableCrearSolicitudDesembolso;
    private String responsableOperacion;
    private Integer idTcaEstadoOperacion;

    public DetalleDesembolsosBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    @SuppressWarnings("unchecked")
    public String inicializarPermisosUsuario() {
        logger.warning("inside: inicializarPermisosUsuarioDesembolsos");

        Object resultado = null;
        setDisableDetalle(true);
        setDisableOperaciones(true);
        setDisableCrearSolicitudDesembolso(true);

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("getGruposUsuario");
        String usuario = ADFContext.getCurrent().getSecurityContext().getUserName();
        operationBinding.getParamsMap().put("pUsuario", usuario);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {

        } else {
            resultado = operationBinding.getResult();
        }

        if (resultado != null) {
            List<String> grupo = (List<String>) resultado;
            for (String gs : grupo) {
                logger.warning("grupo del servicio:" + gs);
                if (gs.startsWith("FENIX")) {
                    setDisableDetalle(false);
                }
                //Se atiende incidencia FNXII-3996
                //if (gs.startsWith("FENIX_EJECUTIVO")) {
                if (gs.startsWith("FENIX_REPRESENTANTE")) {
                    setDisableOperaciones(false);
                    setDisableCrearSolicitudDesembolso(false);
                }
                responsableOperacion = usuario.toLowerCase();                
            }
        }
        logger.warning("responsableOperacion: "+responsableOperacion);
        JSFUtils.setExpressionValue("#{sessionScope.btn_ver_detalle}", Boolean.TRUE);
        return "irGestorDesembolsos";
    }

    public void setDisableDetalle(boolean disableDetalle) {
        this.disableDetalle = disableDetalle;
    }

    public boolean isDisableDetalle() {
        return disableDetalle;
    }

    public void setDisableOperaciones(boolean disableOperaciones) {
        this.disableOperaciones = disableOperaciones;
    }

    public boolean isDisableOperaciones() {
        return disableOperaciones;
    }

    public void setDisableCrearSolicitudDesembolso(boolean disableCrearSolicitudDesembolso) {
        this.disableCrearSolicitudDesembolso = disableCrearSolicitudDesembolso;
    }

    public boolean isDisableCrearSolicitudDesembolso() {
        return disableCrearSolicitudDesembolso;
    }
    
    public String getResponsableOperacion() {
        return this.responsableOperacion;
    }
    
    public void setResponsableOperacion(String responsableOperacion) {
        this.responsableOperacion = responsableOperacion;
    }

    public Integer getIdTcaEstadoOperacion() {
        return idTcaEstadoOperacion;
    }

    public void setIdTcaEstadoOperacion(Integer idTcaEstadoOperacion) {
        this.idTcaEstadoOperacion = idTcaEstadoOperacion;
    }
}
