package org.bcie.fenix.view.cuestionarios;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.operacionmo.ActualizarOperacionResponseType;

public class OperacionBean {
    
    private static ADFLogger logger = null;
    
    public OperacionBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void actividadEconomicaPadreValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE, "Inside actividadEconomicaPadreValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idActividadEconomica = null;
        
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idActividadEconomica = Integer.valueOf
            (JSFUtils.resolveExpression("#{bindings.Id.inputValue}").toString());
        
        // Filtramos el combo de Iniciativa estratégica por Id
        filtrarIniciativaEstrategica(new oracle.jbo.domain.Number(idActividadEconomica.intValue()));
        
        // Limpiamos combos dependientes: Cantidad de Países beneficiados, Actividad económica, Área de focalización y Eje estratégico
        filtrarCantidadPaises(null);
        filtrarActividadEconomica(null);
        filtrarAreaFocalizacion(null, null);
        filtrarEjeEstrategico(null, null);
    }
    
    public void iniciativaEstrategicaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE, "Inside iniciativaEstrategicaValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idIniciativaEstrategica = null;
        
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idIniciativaEstrategica = Integer.valueOf
            (JSFUtils.resolveExpression("#{bindings.Id1.inputValue}").toString());
                
        // Filtramos el combo Cantidad de Países beneficiados por el idIniciativaEstrategica
        filtrarCantidadPaises(new oracle.jbo.domain.Number(idIniciativaEstrategica.intValue()));
        
        // Limpiamos combos dependientes: Área de focalización y Eje estratégico
        filtrarAreaFocalizacion(null, null);
        filtrarEjeEstrategico(null, null);
    }
    
    public void cantidadPaisesBeneficiadosValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE, "Inside cantidadPaisesBeneficiadosValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idActividad = null;
        Integer idIniciativaEstrategica = null;
        Integer idRangoPaises = null;
    
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idActividad = Integer.valueOf
            (JSFUtils.resolveExpression("#{bindings.IdActividad.inputValue}").toString());
        idIniciativaEstrategica = Integer.valueOf
            (JSFUtils.resolveExpression("#{bindings.Id1.inputValue}").toString());
        idRangoPaises = Integer.valueOf
            (JSFUtils.resolveExpression("#{bindings.IdRangoPaises.inputValue}").toString());
                
        // Filtramos los combos Actividad económica, Área de focalización y Eje estratégico 
        // por el idActividad, idIniciativaEstrategica e idRangoPaises
        filtrarActividadEconomica(new oracle.jbo.domain.Number(idActividad.intValue()));
        filtrarAreaFocalizacion(new oracle.jbo.domain.Number(idIniciativaEstrategica.intValue()), 
                                new oracle.jbo.domain.Number(idRangoPaises.intValue()));
        filtrarEjeEstrategico(new oracle.jbo.domain.Number(idIniciativaEstrategica.intValue()), 
                              new oracle.jbo.domain.Number(idRangoPaises.intValue()));
    }
                              
    private void filtrarIniciativaEstrategica(oracle.jbo.domain.Number idPadre){
        
        // Filtramos el combo de Iniciativa estratégica por Id de la Actividad económica
        DCIteratorBinding voTiposIniciativaEstrategica = null;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opFiltrarIniciativaEstrategica = bindings.getOperationBinding("setidPadre");
        opFiltrarIniciativaEstrategica.getParamsMap().put("value", idPadre);
        opFiltrarIniciativaEstrategica.execute();
        if(!opFiltrarIniciativaEstrategica.getErrors().isEmpty()){
            // Manejo de errores
        }
        
        // Ejecutamos query en iterador (puesto que cambió el parámetro del Where))
        voTiposIniciativaEstrategica = 
            ADFUtils.getDCBindingContainer().findIteratorBinding("TiposIniciativaEstrategicaLOVIterator");
        voTiposIniciativaEstrategica.executeQuery();
    }
    
    private void filtrarCantidadPaises(oracle.jbo.domain.Number idIniciativaEstrategica){
        
        // Filtramos el combo de Cantidad de Países beneficiados por Id de la Iniciativa Estrategica
        DCIteratorBinding voTiposCantidadPaisesBeneficiados = null;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opFiltrarCantidadPaises = bindings.getOperationBinding("setidIniciativaEstrategica");
        opFiltrarCantidadPaises.getParamsMap().put("value", idIniciativaEstrategica);
        opFiltrarCantidadPaises.execute();
        if(!opFiltrarCantidadPaises.getErrors().isEmpty()){
            // Manejo de errores
        }
        
        // Ejecutamos query en iterador (puesto que cambió el parámetro del Where))
        voTiposCantidadPaisesBeneficiados = 
            ADFUtils.getDCBindingContainer().findIteratorBinding("TiposCantidadPaisesBeneficiadosLOVIterator");
        voTiposCantidadPaisesBeneficiados.executeQuery();
    }
    
    private void filtrarActividadEconomica(oracle.jbo.domain.Number idActividad){
        
        // Filtramos el combo de Actividad económica por Id de la Actividad económica 
        // (obtenida en combo Iniciativa estratégica)
        DCIteratorBinding voTiposActividadEconomica = null;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opFiltrarActividadEconomica = bindings.getOperationBinding("setidActividad");
        opFiltrarActividadEconomica.getParamsMap().put("value", idActividad);
        opFiltrarActividadEconomica.execute();
        if(!opFiltrarActividadEconomica.getErrors().isEmpty()){
            // Manejo de errores
        }
        
        // Ejecutamos query en iterador (puesto que cambió el parámetro del Where))
        voTiposActividadEconomica = 
            ADFUtils.getDCBindingContainer().findIteratorBinding("TiposActividadEconomicaLOVIterator");
        voTiposActividadEconomica.executeQuery();
    }
    
    private void filtrarAreaFocalizacion(oracle.jbo.domain.Number idIniciativaEstrategica, 
                                         oracle.jbo.domain.Number idRangoPaises){
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voTiposAreaFocalizacion = null;
        OperationBinding opFiltrarAreaFocalizacion = null;
        
        // Filtramos el combo de Área de focalización por Id de la Iniciativa Estrategica e Id Rango Paises
        opFiltrarAreaFocalizacion = bindings.getOperationBinding("setidIniciativaEstrategica1");
        opFiltrarAreaFocalizacion.getParamsMap().put("value", idIniciativaEstrategica);
        opFiltrarAreaFocalizacion.execute();
        if(!opFiltrarAreaFocalizacion.getErrors().isEmpty()){
            // Manejo de errores
        }
        
        opFiltrarAreaFocalizacion = bindings.getOperationBinding("setidRangoPaises");
        opFiltrarAreaFocalizacion.getParamsMap().put("value", idRangoPaises);
        opFiltrarAreaFocalizacion.execute();
        if(!opFiltrarAreaFocalizacion.getErrors().isEmpty()){
            // Manejo de errores
        }
        
        // Ejecutamos query en iterador (puesto que cambiaron parámetros del Where))
        voTiposAreaFocalizacion = 
            ADFUtils.getDCBindingContainer().findIteratorBinding("TiposAreaFocalizacionLOVIterator");
        voTiposAreaFocalizacion.executeQuery();
    }
    
    private void filtrarEjeEstrategico(oracle.jbo.domain.Number idIniciativaEstrategica,
                                       oracle.jbo.domain.Number idRangoPaises){
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voTiposEjeEstrategico = null;
        OperationBinding opFiltrarEjeEstrategico = null;
        
        // Filtramos el combo de Eje estratégico por Id de la Iniciativa Estrategica e Id Rango Paises
        opFiltrarEjeEstrategico = bindings.getOperationBinding("setidIniciativaEstrategica2");
        opFiltrarEjeEstrategico.getParamsMap().put("value", idIniciativaEstrategica);
        opFiltrarEjeEstrategico.execute();
        if(!opFiltrarEjeEstrategico.getErrors().isEmpty()){
            // Manejo de errores
        }
        
        opFiltrarEjeEstrategico = bindings.getOperationBinding("setidRangoPaises1");
        opFiltrarEjeEstrategico.getParamsMap().put("value", idRangoPaises);
        opFiltrarEjeEstrategico.execute();
        if(!opFiltrarEjeEstrategico.getErrors().isEmpty()){
            // Manejo de errores
        }
        
        // Ejecutamos query en iterador (puesto que cambiaron parámetros del Where))
        voTiposEjeEstrategico = 
            ADFUtils.getDCBindingContainer().findIteratorBinding("TiposEjeEstrategicoLOVIterator");
        voTiposEjeEstrategico.executeQuery();
    }

    public void guardarOperacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside guardarOperacionActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = null;
        OperationBinding opActualizar = null;
        Map<String,Object> parametrosMap = null;
        Long idActividadEconomica = null;
        Long idActividadEconomicaAsoc = null;
        String codigoExterno = null;
        String codigoExternoAsoc = null;
        Long idIniciativaEstrategica = null;
        Long idRangoPaises = null;
        Long idAreaFocalizacion = null;
        Long idEjeEstrategico = null;
        Long idOperacion = null;
        String loginUsuario = null;
        HashMap<String,ActualizarOperacionResponseType> respuestaServicio = null;
        ActualizarOperacionResponseType response= new ActualizarOperacionResponseType();
        
        if(validarGuardarOperacion()){ // Validación de datos
            
            try{
                // Asignación de variables desde bindings
                idActividadEconomica = Long.valueOf
                    (JSFUtils.resolveExpression("#{bindings.Id.inputValue}").toString());
                codigoExterno = (String) JSFUtils.resolveExpression("#{bindings.CodExterno.inputValue}");
                idIniciativaEstrategica = Long.valueOf
                    (JSFUtils.resolveExpression("#{bindings.Id1.inputValue}").toString());
                idRangoPaises = Long.valueOf
                    (JSFUtils.resolveExpression("#{bindings.IdRangoPaises.inputValue}").toString());
                idActividadEconomicaAsoc = Long.valueOf
                    (JSFUtils.resolveExpression("#{bindings.Id3.inputValue}").toString());
                codigoExternoAsoc = (String) JSFUtils.resolveExpression("#{bindings.CodExterno1.inputValue}");
                idAreaFocalizacion = Long.valueOf
                    (JSFUtils.resolveExpression("#{bindings.Id4.inputValue}").toString());
                idEjeEstrategico = Long.valueOf
                    (JSFUtils.resolveExpression("#{bindings.Id5.inputValue}").toString());
                idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
                loginUsuario = JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}").toString();
                
                // Llenado de mapa
                parametrosMap = new HashMap<>();
                parametrosMap.put("idActividadEconomica", idActividadEconomica);
                parametrosMap.put("idActividadAsociada", idActividadEconomicaAsoc);
                parametrosMap.put("idAreaFocalizacion", idAreaFocalizacion);
                parametrosMap.put("idEjeEstrategico", idEjeEstrategico);
                parametrosMap.put("idIniciativaEstrategica", idIniciativaEstrategica);
                parametrosMap.put("idCantidadPaises", idRangoPaises);
                parametrosMap.put("codExternoActividadEconomica", codigoExterno);
                parametrosMap.put("codExternoActividadAsociada", codigoExternoAsoc);
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "Error en asignación de variables: " + e.getMessage());
                parametrosMap = null;
            }
            
            if(parametrosMap != null){
                // Invocamos método en AM que guarda los datos modificados
                bindings = ADFUtils.getBindingContainer();
                opActualizar = bindings.getOperationBinding("actualizarOperacionEvaluacion");
                opActualizar.getParamsMap().put("idOperacion", idOperacion);
                opActualizar.getParamsMap().put("loginUsuario", loginUsuario);
                opActualizar.getParamsMap().put("parametrosMap", parametrosMap);
                
                opActualizar.execute();
                if(!opActualizar.getErrors().isEmpty()){
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(opActualizar.getErrors().toString());
                }
                else if(opActualizar.getResult() != null){
                    respuestaServicio = (HashMap<String,ActualizarOperacionResponseType>)opActualizar.getResult();
                    response= respuestaServicio.get("response");
                    JSFUtils.addFacesInformationMessage(response.getResultado().getResult()+" "+response.getResultado().getMessage());
                    //JSFUtils.addFacesInformationMessage("Operación actualizada exitosamente.");
                    
                }
            }
            else{
                JSFUtils.addFacesErrorMessage("Ocurrió un error inesperado, favor de intentar más tarde.");
            }
        }
    }
    
    private Boolean validarGuardarOperacion(){
        
        Boolean esDatosCorrectos = Boolean.TRUE;

        if((JSFUtils.resolveExpression("#{bindings.Id.inputValue}") == null) 
           || (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id.inputValue}").toString()) == 0)){
            
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Actividad económica primaria es requerido.");
        }
        
        if((JSFUtils.resolveExpression("#{bindings.Id1.inputValue}") == null) 
           || (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id1.inputValue}").toString()) == 0)){
            
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Iniciativa estratégica es requerido.");
        }
        
        if((JSFUtils.resolveExpression("#{bindings.Id2.inputValue}") == null) 
           || (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id2.inputValue}").toString()) == 0)){
            
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Cantidad de Países beneficiados es requerido.");
        }
        
        if((JSFUtils.resolveExpression("#{bindings.Id3.inputValue}") == null) 
           || (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id3.inputValue}").toString()) == 0)){
            
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("No existe una Actividad económica para la combinación seleccionada.");
        }
        
        if((JSFUtils.resolveExpression("#{bindings.Id4.inputValue}") == null) 
           || (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id4.inputValue}").toString()) == 0)){
            
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("No existe un Área de focalización para la combinación seleccionada.");
        }
        
        if((JSFUtils.resolveExpression("#{bindings.Id5.inputValue}") == null) 
           || (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id5.inputValue}").toString()) == 0)){
            
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("No existe un Eje estratégico para la combinación seleccionada.");
        }
        
        return esDatosCorrectos;
    }
}
