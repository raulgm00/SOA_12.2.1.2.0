package org.bcie.fenix.view.gestoroperaciones;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.operacionbo.Operacion;
import org.bcie.operacionmo.ActualizarOperacionResponseType;
import org.bcie.operacionmo.ConsultarOperacionResponseType;

public class ActualizarOperacionBean implements Serializable  {
    @SuppressWarnings("compatibility:-5680072206433240916")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;
    private transient RichPopup popupActualizarOperacion;

    public ActualizarOperacionBean() {
        super();
        if (logger == null) {
                   logger = ADFLogger.createADFLogger(this.getClass());
               }
    }

    public void actualizarOperacionListener(ActionEvent actionEvent) {
        // Mostramos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupActualizarOperacion().show(popupHints);
    }
    private Boolean validarActualizarOperacion() {

        Boolean esDatosCorrectos = Boolean.TRUE;
        Boolean banderaMontoTotal = Boolean.FALSE;
        //Cambiaron los tipos de variable ya que podian ser valores con decimales
        double montoSolicitado = 0;
        double montoTotal = 0;
        Long idCliente = null;

        // ***** Validación de sección Datos generales START
        idCliente = (Long) JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}");
        if ((idCliente == null) || (idCliente == 0)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Cliente es requerido.");
        }

        if (JSFUtils.resolveExpression("#{bindings.MontoSolicitado.inputValue}") != null)
            montoSolicitado =
                Double.parseDouble(JSFUtils.resolveExpression("#{bindings.MontoSolicitado.inputValue}").toString());

        if (JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean.esMontoTotal}") != null)
            banderaMontoTotal =
                Boolean.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean.esMontoTotal}").toString());

        if (banderaMontoTotal.equals(Boolean.TRUE)) {

            if (JSFUtils.resolveExpression("#{bindings.MontoTotal.inputValue}") != null)
                montoTotal = Double.parseDouble(JSFUtils.resolveExpression("#{bindings.MontoTotal.inputValue}").toString());
            else
                JSFUtils.addFacesErrorMessage("Monto total es requerido.");
        }

        if (banderaMontoTotal.equals(Boolean.TRUE) && (montoSolicitado > montoTotal)) {
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("Monto solicitado no puede ser mayor al monto total ingresado.");
        }
        // ***** Validación de sección Datos generales END

        // ***** Validación de sección Clasificación estratégica START
        //S&P | 07/07/2019 | Se anexan validaciones para el caso multisectorial
        //Determinar si es multisectorial
        int multisectorial = (Integer) JSFUtils.resolveExpression("#{bindings.v_multisectorial.inputValue}");
        if (multisectorial == 1) { //Es multisectorial
            //Se invoca metodo que recupera los mensajes resultado de la validacion multisectorial
            ComponentesMultisectorialBean componentesMBean =
                              (ComponentesMultisectorialBean) JSFUtils.resolveExpression("#{viewScope.componenteMultisectorialBean}");
            ArrayList<String> resultadoValidacion = componentesMBean.validarComponentesMultisectorial();
            if (resultadoValidacion.size() > 0) {
                esDatosCorrectos = Boolean.FALSE;
                for (String str : resultadoValidacion) {
                    JSFUtils.addFacesErrorMessage(str);
                }
            }


        } else {

        if ((JSFUtils.resolveExpression("#{bindings.Id.inputValue}") == null) ||
            (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id.inputValue}").toString()) == 0)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Actividad económica primaria es requerido.");
        }

        if ((JSFUtils.resolveExpression("#{bindings.Id1.inputValue}") == null) ||
            (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id1.inputValue}").toString()) == 0)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Iniciativa estratégica es requerido.");
        }

        if ((JSFUtils.resolveExpression("#{bindings.Id2.inputValue}") == null) ||
            (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id2.inputValue}").toString()) == 0)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Cantidad de Países beneficiados es requerido.");
        }

        if ((JSFUtils.resolveExpression("#{bindings.Id3.inputValue}") == null) ||
            (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id3.inputValue}").toString()) == 0)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("No existe una Actividad económica para la combinación seleccionada.");
        }

        if ((JSFUtils.resolveExpression("#{bindings.Id4.inputValue}") == null) ||
            (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id4.inputValue}").toString()) == 0)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("No existe un Área de focalización para la combinación seleccionada.");
        }

        if ((JSFUtils.resolveExpression("#{bindings.Id5.inputValue}") == null) ||
            (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id5.inputValue}").toString()) == 0)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("No existe un Eje estratégico para la combinación seleccionada.");
        }
   }
        // ***** Validación de sección Clasificación estratégica END

        return esDatosCorrectos;
    }

    public void aceptarActualizarOperacionActionListener(ActionEvent actionEvent) {
        // Invoke method actualizarOperacionReformular
        OperationBinding operationBinding;
        Operacion operacion = new Operacion();
        Integer idOficina=null;
        String codExterno="";
        BindingContainer bindings = getBindings();
        HashMap<String,ActualizarOperacionResponseType> respuestaServicio = null;
        ActualizarOperacionResponseType response= new ActualizarOperacionResponseType();
        String errorCode = null;
        GestorOperacionesBean gestorOperacionesBean=null;
        //valida Herramienta estrategica y campos obligatorios.
        //regresa true o false
        if (validarActualizarOperacion()) { 
        //recuperar Id oficina por Id cliente
        operationBinding = bindings.getOperationBinding("getIdOficinaByIdCliente");
        operationBinding.getParamsMap().put("idCliente",
                                            JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}"));
        operationBinding.execute();
        idOficina=(Integer)operationBinding.getResult();
        //recuperar codExterno por Id oficina
        operationBinding = bindings.getOperationBinding("getCodExternoByIdOficina");
        operationBinding.getParamsMap().put("idOficina",idOficina);
        operationBinding.execute();
        codExterno=(String)operationBinding.getResult();
        //crea una instancia del gestorOperacionesBean
        gestorOperacionesBean =(GestorOperacionesBean) 
                    JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        //Invocar ActualizarOperacion 
        operationBinding = bindings.getOperationBinding("actualizarOperacionReformular");
        operationBinding.getParamsMap().put("codExterno", codExterno); //enviar parametro codExterno
        operationBinding.getParamsMap().put("idOficina", idOficina.longValue()); //enviar parametro idOficina
        //Se agregaron los siguiente parametros por problemas para limpiar sus valores cuando no son visibles
        operationBinding.getParamsMap().put("esEjercicioPoa",
                        gestorOperacionesBean.getEsEjercicioPoa()); // Indicador de visibilidad para ejercicioPoa
        operationBinding.getParamsMap().put("esUnidadEjecutoraSecPublico",
                        gestorOperacionesBean.getEsUnidadEjecutoraSecPublico()); // Indicador de visibilidad para 
                                                                                 // UnidadEjecutora
        operationBinding.getParamsMap().put("esTipoDeGarantiaSecPublico",
                        gestorOperacionesBean.getEsTipoDeGarantiaSecPublico()); // Indicador de visibilidad para 
                                                                                // TipoDeGarantia
        operationBinding.execute();
        if(operationBinding.getErrors().size() != 0){
            // Manejo de errores    
        }
        else if(operationBinding.getResult() != null){
            respuestaServicio = (HashMap<String,ActualizarOperacionResponseType>)operationBinding.getResult();
            response = respuestaServicio.get("response");
            //el getResult del servicio viene vacio por eso no se esta validando contra eso
            //Correcion de los SOPA
            if(response.getResultado()!= null){
                JSFUtils.addFacesInformationMessage(response.getResultado().getMessage());
                getPopupActualizarOperacion().hide();
            }else{
                JSFUtils.addFacesErrorMessage(response.getResultado().getMessage().toString());
                getPopupActualizarOperacion().hide();
                }
            }
        
        }
        
    }

    public void cancelarActualizarOperacionActionListener(ActionEvent actionEvent) {
        // Add event code here...
        getPopupActualizarOperacion().hide();
    }
    
    public RichPopup getPopupActualizarOperacion() {
        return popupActualizarOperacion;
    }

    public void setPopupActualizarOperacion(RichPopup popupActualizarOperacion) {
        this.popupActualizarOperacion = popupActualizarOperacion;
    }
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
}
