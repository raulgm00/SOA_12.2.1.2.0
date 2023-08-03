package org.bcie.fenix.view.gestoroperaciones;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class BusquedaOperacionBean {
    
    private static ADFLogger logger = null;
    
    private Long idOperacion = null;
    private static final String IR_DETALLE_OPERACION = "irDetalleOperacion";
    private String accionVerDetalle;
    
    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }
    
    public BusquedaOperacionBean() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void obtenerNoObjecionActionListener(ActionEvent actionEvent) {

        logger.warning("Inicia obtenerNoObjecionActionListener");

        boolean servicioDeclaracionJuradaDisponible = Boolean.FALSE;
        setAccionVerDetalle(IR_DETALLE_OPERACION);
        try {
            GestorOperacionesBean gestorOperacionBean =
                (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
            gestorOperacionBean.setPanelDetalleOperacionActivated(false);

            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            String idOperacion = JSFUtils.resolveExpression("#{bindings.IdOperacion.inputValue}").toString();

            operationBinding = bindings.getOperationBinding("consultarDeclaracionJurada");
            operationBinding.getParamsMap().put("idOperacion", idOperacion.toString());
            operationBinding.execute();
            
            if (operationBinding.getErrors().isEmpty()) {
                servicioDeclaracionJuradaDisponible = Boolean.TRUE;
            } else {
                setAccionVerDetalle("");
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, e.getClass() + " : " + e.getMessage());
        }
        //Inicio Jira Fix FNXII-1544 y FNXII-1544

        try {
            if (servicioDeclaracionJuradaDisponible) {
                String idOperacion = JSFUtils.resolveExpression("#{bindings.IdOperacion.inputValue}").toString();
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding;

                operationBinding = bindings.getOperationBinding("setoperacionId");
                operationBinding.getParamsMap().put("value", Long.parseLong(idOperacion));
                operationBinding.execute();


                operationBinding = bindings.getOperationBinding("setvarIdOperacion");
                operationBinding.getParamsMap().put("value", Long.parseLong(idOperacion));
                operationBinding.execute();
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, e.getClass() + " : " + e.getMessage());
        }

        //Fin Jira Fix FNXII-1544 y FNXII-1544
        logger.warning("Finaliza obtenerNoObjecionActionListener");
    }
    
    public String inicializarCrearOperacion() {
        GestorOperacionesBean gestorOperacionesBean = null;
        BindingContainer bindings = getBindings();
        
        //Inicializar crearRowOperacion y campos Actividad Economica Primaria, Iniciativa Estrategica y Cantidad de paises beneficiados
        OperationBinding operationBinding = bindings.getOperationBinding("inicializarCrearOperacion");
        operationBinding.execute();
        
        //setear razonSocial en null
        gestorOperacionesBean = (GestorOperacionesBean)
                    JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        gestorOperacionesBean.setNombreCliente(null);
        
        //setear campos dependientes en false
        gestorOperacionesBean.setEsMontoTotal(false);
        gestorOperacionesBean.setEsTipoDeGarantia(false);
        gestorOperacionesBean.setEsComponenteDeConsecionalidad(false);
        gestorOperacionesBean.setEsEstructurador(false);
        gestorOperacionesBean.setEsPrograma(false);
        gestorOperacionesBean.setEsBeneficiario(false);
        gestorOperacionesBean.setEsOperacionAsociada(false);
        gestorOperacionesBean.setEsTipoDeGarantiaSecPublico(false);
        gestorOperacionesBean.setEsUnidadEjecutoraSecPublico(false);
        //agrega Ejercicio POA
        gestorOperacionesBean.setEsEjercicioPoa(false);
        return "irCrearOperacion";
    }

    public String getAccionVerDetalle() {
        return accionVerDetalle;
    }

    public void setAccionVerDetalle(String accionVerDetalle) {
        this.accionVerDetalle = accionVerDetalle;
    }
}
