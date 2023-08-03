package org.bcie.fenix.view.operacion;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.operacionbo.ClasificacionAmbiental;
import org.bcie.operacionmo.ActualizarOperacionRequestType;
import org.bcie.operacionmo.ActualizarOperacionResponseType;
import org.bcie.operacionmo.CrearOperacionResponseType;

public class DetalleOperacionBean {
    public DetalleOperacionBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    private String idOperacion;
    private static ADFLogger logger = null;
    private transient RichPopup popupActualizarOperacion;

    public void actividadEconomicaPadreValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside actividadEconomicaPadreValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idActividadEconomica = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idActividadEconomica = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id.inputValue}").toString());

        // Filtramos el combo de Iniciativa estratégica por Id
        filtrarIniciativaEstrategica(new oracle.jbo.domain.Number(idActividadEconomica.intValue()));

        // Limpiamos combos dependientes: Cantidad de Países beneficiados, Actividad económica, Área de focalización y Eje estratégico
        filtrarCantidadPaises(null);
        filtrarActividadEconomica(null);
        filtrarAreaFocalizacion(null, null);
        filtrarEjeEstrategico(null, null);
    }

    public void iniciativaEstrategicaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside iniciativaEstrategicaValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idIniciativaEstrategica = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idIniciativaEstrategica = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id1.inputValue}").toString());

        // Filtramos el combo Cantidad de Países beneficiados por el idIniciativaEstrategica
        filtrarCantidadPaises(new oracle.jbo.domain.Number(idIniciativaEstrategica.intValue()));
        // SPS | 201/11/2019 | FIX T-14161 : LIMPIAR COMBOS AL RESET DE INICATIVA ESTRATEGICA 
        filtrarActividadEconomica(null);
        // Limpiamos combos dependientes: Área de focalización y Eje estratégico
        filtrarAreaFocalizacion(null, null);
        filtrarEjeEstrategico(null, null);
    }


    public void cantidadPaisesBeneficiadosValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside cantidadPaisesBeneficiadosValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idActividad = null;
        Integer idIniciativaEstrategica = null;
        Integer idRangoPaises = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idActividad = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdActividad.inputValue}").toString());
        idIniciativaEstrategica = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id1.inputValue}").toString());
        idRangoPaises = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdRangoPaises.inputValue}").toString());

        // Filtramos los combos Actividad económica, Área de focalización y Eje estratégico
        // SPS | 201/11/2019 | FIX T-14161 : LIMPIAR COMBOS AL RESET DE INICATIVA ESTRATEGICA 
        if(idRangoPaises>0){
        // por el idActividad, idIniciativaEstrategica e idRangoPaises
        filtrarActividadEconomica(new oracle.jbo.domain.Number(idActividad.intValue()));
        filtrarAreaFocalizacion(new oracle.jbo.domain.Number(idIniciativaEstrategica.intValue()),
                                new oracle.jbo.domain.Number(idRangoPaises.intValue()));
        filtrarEjeEstrategico(new oracle.jbo.domain.Number(idIniciativaEstrategica.intValue()),
                              new oracle.jbo.domain.Number(idRangoPaises.intValue()));
        
        }else{
            filtrarActividadEconomica(null);
            filtrarAreaFocalizacion(null, null);
            filtrarEjeEstrategico(null, null);
        }
    }


    private void filtrarIniciativaEstrategica(oracle.jbo.domain.Number idPadre) {

        // Filtramos el combo de Iniciativa estratégica por Id de la Actividad económica
        DCIteratorBinding voTiposIniciativaEstrategica = null;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opFiltrarIniciativaEstrategica = bindings.getOperationBinding("setidPadre");
        opFiltrarIniciativaEstrategica.getParamsMap().put("value", idPadre);
        opFiltrarIniciativaEstrategica.execute();
        if (!opFiltrarIniciativaEstrategica.getErrors().isEmpty()) {
            // Manejo de errores
        }

        // Ejecutamos query en iterador (puesto que cambió el parámetro del Where))
        voTiposIniciativaEstrategica =
            ADFUtils.getDCBindingContainer().findIteratorBinding("TiposIniciativaEstrategicaLOVIterator");
        voTiposIniciativaEstrategica.executeQuery();
    }

    private void filtrarCantidadPaises(oracle.jbo.domain.Number idIniciativaEstrategica) {

        // Filtramos el combo de Cantidad de Países beneficiados por Id de la Iniciativa Estrategica
        DCIteratorBinding voTiposCantidadPaisesBeneficiados = null;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opFiltrarCantidadPaises = bindings.getOperationBinding("setidIniciativaEstrategica");
        opFiltrarCantidadPaises.getParamsMap().put("value", idIniciativaEstrategica);
        opFiltrarCantidadPaises.execute();
        if (!opFiltrarCantidadPaises.getErrors().isEmpty()) {
            // Manejo de errores
        }

        // Ejecutamos query en iterador (puesto que cambió el parámetro del Where))
        voTiposCantidadPaisesBeneficiados =
            ADFUtils.getDCBindingContainer().findIteratorBinding("TiposCantidadPaisesBeneficiadosLOVIterator");
        voTiposCantidadPaisesBeneficiados.executeQuery();
    }

    private void filtrarActividadEconomica(oracle.jbo.domain.Number idActividad) {

        // Filtramos el combo de Actividad económica por Id de la Actividad económica
        // (obtenida en combo Iniciativa estratégica)
        DCIteratorBinding voTiposActividadEconomica = null;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opFiltrarActividadEconomica = bindings.getOperationBinding("setidActividad");
        opFiltrarActividadEconomica.getParamsMap().put("value", idActividad);
        opFiltrarActividadEconomica.execute();
        if (!opFiltrarActividadEconomica.getErrors().isEmpty()) {
            // Manejo de errores
        }

        // Ejecutamos query en iterador (puesto que cambió el parámetro del Where))
        voTiposActividadEconomica =
            ADFUtils.getDCBindingContainer().findIteratorBinding("TiposActividadEconomicaLOVIterator");
        voTiposActividadEconomica.executeQuery();
    }

    private void filtrarAreaFocalizacion(oracle.jbo.domain.Number idIniciativaEstrategica,
                                         oracle.jbo.domain.Number idRangoPaises) {

        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voTiposAreaFocalizacion = null;
        OperationBinding opFiltrarAreaFocalizacion = null;

        // Filtramos el combo de Área de focalización por Id de la Iniciativa Estrategica e Id Rango Paises
        opFiltrarAreaFocalizacion = bindings.getOperationBinding("setidIniciativaEstrategica1");
        opFiltrarAreaFocalizacion.getParamsMap().put("value", idIniciativaEstrategica);
        opFiltrarAreaFocalizacion.execute();
        if (!opFiltrarAreaFocalizacion.getErrors().isEmpty()) {
            // Manejo de errores
        }

        opFiltrarAreaFocalizacion = bindings.getOperationBinding("setidRangoPaises");
        opFiltrarAreaFocalizacion.getParamsMap().put("value", idRangoPaises);
        opFiltrarAreaFocalizacion.execute();
        if (!opFiltrarAreaFocalizacion.getErrors().isEmpty()) {
            // Manejo de errores
        }

        // Ejecutamos query en iterador (puesto que cambiaron parámetros del Where))
        voTiposAreaFocalizacion =
            ADFUtils.getDCBindingContainer().findIteratorBinding("TiposAreaFocalizacionLOVIterator");
        voTiposAreaFocalizacion.executeQuery();
    }

    private void filtrarEjeEstrategico(oracle.jbo.domain.Number idIniciativaEstrategica,
                                       oracle.jbo.domain.Number idRangoPaises) {

        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voTiposEjeEstrategico = null;
        OperationBinding opFiltrarEjeEstrategico = null;

        // Filtramos el combo de Eje estratégico por Id de la Iniciativa Estrategica e Id Rango Paises
        opFiltrarEjeEstrategico = bindings.getOperationBinding("setidIniciativaEstrategica2");
        opFiltrarEjeEstrategico.getParamsMap().put("value", idIniciativaEstrategica);
        opFiltrarEjeEstrategico.execute();
        if (!opFiltrarEjeEstrategico.getErrors().isEmpty()) {
            // Manejo de errores
        }

        opFiltrarEjeEstrategico = bindings.getOperationBinding("setidRangoPaises1");
        opFiltrarEjeEstrategico.getParamsMap().put("value", idRangoPaises);
        opFiltrarEjeEstrategico.execute();
        if (!opFiltrarEjeEstrategico.getErrors().isEmpty()) {
            // Manejo de errores
        }

        // Ejecutamos query en iterador (puesto que cambiaron parámetros del Where))
        voTiposEjeEstrategico = ADFUtils.getDCBindingContainer().findIteratorBinding("TiposEjeEstrategicoLOVIterator");
        voTiposEjeEstrategico.executeQuery();
    }


    public void inicializarFlujo() {
        idOperacion = (String) JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opAsignarIdOperacion = bindings.getOperationBinding("setidPadre");
        opAsignarIdOperacion.getParamsMap().put("value", idOperacion);
        opAsignarIdOperacion.execute();
        if (!opAsignarIdOperacion.getErrors().isEmpty()) {
            // Manejo de errores
        }

    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void actualizarOperacionActionListener(ActionEvent actionEvent) {
        // Mostramos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupActualizarOperacion().show(popupHints);
    }
    
    
    // SPS | 26/11/2019 | Se modifca metodo para incluir caso multisectorial y se anexan validaciones
    public void aceptarActualizarOperacionActionListener(ActionEvent actionEvent) {
        if (validarActualizarOperacion()) {
            
           /* Integer multisectorial = (Integer) JSFUtils.resolveExpression("#{bindings.v_multisectorial.inputValue}");
            
            if(multisectorial==1){//Caso multisectorial
              //Pasar valores del primer componente a bindings
              pasarValoresBindigs(); 
            }*/
            String respuesta = actualizarOperacionNoMulti();
            boolean bandera = respuesta != null ? (respuesta.contains(">ERROR<") ? false : true) : false;
            if (bandera) {
                //Invocar actualizar CE
                respuesta = actualizarClasificacionEstrategica();
                bandera = respuesta != null ? (respuesta.contains(">ERROR<") ? false : true) : false;
                if (bandera) {
                      JSFUtils.addFacesInformationMessage(respuesta);
                      getPopupActualizarOperacion().hide();
                } else {
                    JSFUtils.addFacesErrorMessage("Error al actualizar la operacion");
                }

            } else {
                JSFUtils.addFacesErrorMessage("Error al actualizar la operacion");
            }
        }

    }

    /**
     * Método que actualzia el detalle de la operacion
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 25/11/2019
     */
    public String actualizarOperacionNoMulti() {
        logger.warning("====== Inside actualizarOperacionNoMulti");
        String respuesta = null;
        OperationBinding operationBinding;
        BindingContainer bindings = getBindings();
        HashMap<String, ActualizarOperacionResponseType> respuestaServicio = null;
        ActualizarOperacionResponseType response = new ActualizarOperacionResponseType();
        String errorCode = null;
        operationBinding = bindings.getOperationBinding("actualizarDetalleOperacion");
        operationBinding.execute();
        if (operationBinding.getErrors().size() != 0) {
            // Manejo de errores

        } else if (operationBinding.getResult() != null) {
            respuestaServicio = (HashMap<String, ActualizarOperacionResponseType>) operationBinding.getResult();
            response = respuestaServicio.get("response");
            if (response.getResultado() != null) {
                respuesta = response.getResultado().getMessage().toString();
            }
        }
        return respuesta;
    }


    /**
     * Método que actualiza los valores de clasificacion estrategica.
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 26/11/2019
     */
    public String actualizarClasificacionEstrategica() {
        String respuesta = null;
        //Mapear valores
        logger.warning("\n== Inicia metodo actualizarClasificacionEstrategica ==");
        ActualizarOperacionRequestType request = null;
        ActualizarOperacionResponseType response = new ActualizarOperacionResponseType();
        HashMap<String, ActualizarOperacionResponseType> respuestaServicio = null;
        request = obtenerValores();
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opbActualizar = bindings.getOperationBinding("actualizarClasificacionEstrategica");
        opbActualizar.getParamsMap().put("request", request);
        opbActualizar.execute();
        if (!opbActualizar.getErrors().isEmpty()) {
            // Manejo de errores
        } else if (opbActualizar.getResult() != null) {
            respuestaServicio = (HashMap<String, ActualizarOperacionResponseType>) opbActualizar.getResult();
            response = respuestaServicio.get("response");
            if (response.getResultado() != null) {
                respuesta = response.getResultado().getMessage().toString();
            }
        }
        return respuesta;
    }
    
    
    /**
     * Método auxiliar para mapear obtener el request para actualizar Clasificacion Estrategica.
     * @author : S&P Solutions
     * @return : 
     * @version: v1.0
     * @Fecha  : 26/11/2019
     */
    public ActualizarOperacionRequestType obtenerValores() {
        ActualizarOperacionRequestType request = new ActualizarOperacionRequestType();
        //Se invoca metodo que recupera los mensajes resultado de la validacion multisectorial
        EditarClasificacionEstrategicaBean componentesMBean =
                          (EditarClasificacionEstrategicaBean) JSFUtils.resolveExpression("#{viewScope.clasificacionEstrategicaBean}");
        request = componentesMBean.obtenerValores();
        return request;
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

    /**
     * Método de validaciones
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 25/11/2019
     */
    private Boolean validarActualizarOperacion() {
        Boolean esDatosCorrectos = Boolean.TRUE;
        if ((JSFUtils.resolveExpression("#{bindings.Nombre.inputValue}") == null) ||
            (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Nombre.inputValue}").toString().length()) == 0)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Operación es requerido.");
        }

        if ((JSFUtils.resolveExpression("#{bindings.Descripcion.inputValue}") == null) ||
            (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Descripcion.inputValue}").toString().length()) ==
             0)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Descripción de la operación es requerido.");
        }

        //Determinar si es multisectorial
        int multisectorial = (Integer) JSFUtils.resolveExpression("#{bindings.v_multisectorial.inputValue}");
        if (multisectorial ==
            1) { //Es multisectorial
            //Se invoca metodo que recupera los mensajes resultado de la validacion multisectorial
            EditarClasificacionEstrategicaBean componentesMBean =
                              (EditarClasificacionEstrategicaBean) JSFUtils.resolveExpression("#{viewScope.clasificacionEstrategicaBean}");
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

            if ((JSFUtils.resolveExpression("#{bindings.IdActEconomica.inputValue}") == null) ||
                (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdActEconomica.inputValue}").toString()) ==
                 0)) {

                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("No existe una Actividad económica para la combinación seleccionada.");
            }

            if ((JSFUtils.resolveExpression("#{bindings.IdAreaFocalizacion.inputValue}") == null) ||
                (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdAreaFocalizacion.inputValue}").toString()) ==
                 0)) {

                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("No existe un Área de focalización para la combinación seleccionada.");
            }

            if ((JSFUtils.resolveExpression("#{bindings.IdEjeEstrategico.inputValue}") == null) ||
                (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdEjeEstrategico.inputValue}").toString()) ==
                 0)) {

                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("No existe un Eje estratégico para la combinación seleccionada.");
            }


        }


        return esDatosCorrectos;
    }

    public void idCatPaisValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("====== Inside idCatPaisValueChangeListener" + valueChangeEvent.getComponent().getId());// — Kruger Innova, funcionalidad de idCatPais. — 24/ago/2021.
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        
        BigDecimal idCatPais = null;   
        idCatPais = new BigDecimal(JSFUtils.resolveExpression("#{bindings.Id3.inputValue}").toString());
        logger.warning("====== Nuevo IdCatPais: " + idCatPais);
    }    
}
