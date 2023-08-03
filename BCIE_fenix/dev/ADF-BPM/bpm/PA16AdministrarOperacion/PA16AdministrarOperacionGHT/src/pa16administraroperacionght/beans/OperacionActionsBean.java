package pa16administraroperacionght.beans;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.RichQuery;

import oracle.adf.view.rich.model.QueryDescriptor;
import oracle.adf.view.rich.model.QueryModel;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.operacionmo.ActualizarOperacionResponseType;

public class OperacionActionsBean extends FenixActionBean
{
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(OperacionActionsBean.class);
    public static final String BUNDLE ="pa16administraroperacionght.PA16AdministrarOperacionGHTBundle";
    
    private RichPopup popupFinalizarTarea;
    private RichPopup popupMasInfo;
    private RichPopup popupRechazar;
    private RichPopup popupCancelar;
    private RichPopup popupBuscarCliente;
    private RichQuery queryBuscarCliente;
    
    public OperacionActionsBean() {
        super();
    }
    
    public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea)
    {
      this.popupFinalizarTarea = popupFinalizarTarea;
    }

    public RichPopup getPopupFinalizarTarea()
    {
      return popupFinalizarTarea;
    }

    public void setPopupMasInfo(RichPopup popupMasInfo)
    {
      this.popupMasInfo = popupMasInfo;
    }

    public RichPopup getPopupMasInfo()
    {
      return popupMasInfo;
    }
    
    public void setPopupRechazar(RichPopup popupRechazar)
    {
      this.popupRechazar = popupRechazar;
    }

    public RichPopup getPopupRechazar()
    {
      return popupRechazar;
    }

    public void setPopupCancelar(RichPopup popupCancelar)
    {
      this.popupCancelar = popupCancelar;
    }

    public RichPopup getPopupCancelar()
    {
      return popupCancelar;
    }
    
    public void setPopupBuscarCliente(RichPopup popupBuscarCliente) {
        this.popupBuscarCliente = popupBuscarCliente;
    }

    public RichPopup getPopupBuscarCliente() {
        return popupBuscarCliente;
    }
    
    public void setQueryBuscarCliente(RichQuery queryBuscarCliente) {
        this.queryBuscarCliente = queryBuscarCliente;
    }

    public RichQuery getQueryBuscarCliente() {
        return queryBuscarCliente;
    }
    
    private Integer getCodigoTarea()
    {
        OperacionBean managedBean = (OperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.operacionBean}");

        if (null != managedBean.getCodigoTarea() && managedBean.getCodigoTarea().trim().length() > 0)
        {
            return Integer.parseInt(managedBean.getCodigoTarea());
        }

        return 0;
    }
    
    private Long getIdOperacion()
    {
        OperacionBean managedBean = (OperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.operacionBean}");
        
        if (null != managedBean.getIdOperacion() && managedBean.getIdOperacion().trim().length() > 0)
        {
            return Long.parseLong(managedBean.getIdOperacion());
        }

        return 0L;
    }
    
    public void finalizarTareaPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupFinalizarTarea.show(hints);
    }
    
    public String cancelarFinalizarTarea()
    {
        popupFinalizarTarea.hide();
        return null;
    }
    
    public void masInformacionPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupMasInfo.show(hints);
    }
    
    public String cancelarMasInformacion()
    {
        popupMasInfo.hide();
        return null;
    }
    
    public void rechazarPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupRechazar.show(hints);
    }
    
    public String cancelarRechazar()
    {
        popupRechazar.hide();
        return null;
    }
    
    public void cancelarPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupCancelar.show(hints);
    }
    
    public String cancelarCancelar()
    {
        popupCancelar.hide();
        return null;
    }
    
    
    // ValueChangeListener  para combos select one choice
    public void productoValueChangeListener(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inside productoValueChangeListener");
        
        LOGGER.warning("getOldValue " +valueChangeEvent.getOldValue());
        LOGGER.warning("getNewValue " + valueChangeEvent.getNewValue());
        
        if(valueChangeEvent.getNewValue() != null)
        {
            Integer idProducto = (Integer)valueChangeEvent.getNewValue();
            
            actualizarPayloadElement("idProducto", idProducto);
            
            OperacionBean operacionBean =
                (OperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.operacionBean}");
            operacionBean.obtenerConfiguracionByIdProducto(idProducto);
            
            agregarMontoTotalOperacion();
        }
    }
    
    public void sectorMercadoValueChangeListener(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inside sectorMercadoValueChangeListener");
        
        LOGGER.warning("getOldValue " +valueChangeEvent.getOldValue());
        LOGGER.warning("getNewValue " + valueChangeEvent.getNewValue());
        
        
        actualizarPayloadElement("IdSectorMercado", valueChangeEvent.getNewValue());
    }
    
    public void ejercicioPOAValueChangeListener(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inside ejercicioPOAValueChangeListener");
        
        LOGGER.warning("getOldValue " +valueChangeEvent.getOldValue());
        LOGGER.warning("getNewValue " + valueChangeEvent.getNewValue());
        
        
        actualizarPayloadElement("IdEjercicioPOA", valueChangeEvent.getNewValue());
    }
    
    public void tipoGarantiaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inside tipoGarantiaValueChangeListener");
        
        LOGGER.warning("getOldValue " +valueChangeEvent.getOldValue());
        LOGGER.warning("getNewValue " + valueChangeEvent.getNewValue());
        
        
        actualizarPayloadElement("IdTipoGarantia", valueChangeEvent.getNewValue());
    }
    
    //Agregar IdCatPais 2021-08-13
    public void idCatPaisValueChangeListener(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inside idCatPaisValueChangeListener");
        
        LOGGER.warning("getOldValue " +valueChangeEvent.getOldValue());
        LOGGER.warning("getNewValue " + valueChangeEvent.getNewValue());
        
        
        actualizarPayloadElement("idCatPais", valueChangeEvent.getNewValue());
    }
    
    //Metodos para Cliente
    public void buscarClienteActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE, "Inside buscarClienteActionListener: " + actionEvent.getComponent().getId());

        // Mostramos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupBuscarCliente().show(popupHints);
    }
        
    public void seleccionarClienteActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE, "Inside seleccionarClienteActionListener: " + actionEvent.getComponent().getId());
        oracle.jbo.domain.Number idCliente = null;
        String nombreCliente = null;
        String sector = null;
        OperacionBean operacionBean = null;

        // Obtenemos id de cliente seleccionado
        // (este IdCliente es de BuscarClienteVOIterator, diferente al IdCliente de CrearOperacionVOIterator
        try {
            idCliente = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{bindings.IdCliente1.inputValue}"));
        } catch (SQLException e) {
            LOGGER.log(ADFLogger.ERROR, e.getMessage());
        }
        
        //Obtenemos el sector de cliente seleccionado
        try {
            sector = JSFUtils.resolveExpression("#{bindings.Sector1.inputValue}").toString();


            operacionBean =
                (OperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.operacionBean}");
            //Si sector es publico -- ID_CAT_SECTOR_INSTITUCIONAL del cliente es = 1 habilitar
            //UnidadEjecutora y TipoDeGarantia
            if (sector == "Sector Público" || sector.equals("Sector Público")) {

                operacionBean.setEsUnidadEjecutoraSecPublico(Boolean.TRUE);
                operacionBean.setEsTipoDeGarantiaSecPublico(Boolean.TRUE);
            } else {
                operacionBean.setEsUnidadEjecutoraSecPublico(Boolean.FALSE);
                operacionBean.setEsTipoDeGarantiaSecPublico(Boolean.FALSE);
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, e.getMessage());
        }

        // Asignamos el Id cliente seleccionado al Payload de Operacion
        actualizarPayloadElement("idCliente", idCliente);

        // Asignamos el cliente seleccionado al Payload de Operacion
        nombreCliente = (String) JSFUtils.resolveExpression("#{bindings.RazonSocial1.inputValue}");
        actualizarPayloadElement("razonSocial", nombreCliente);


        // Limpiamos tabla y panel de búsqueda dentro del popup
        resetPopupBuscarCliente();

        // Cerramos el popup de Buscar cliente
        getPopupBuscarCliente().hide();
    }
    
    private void resetPopupBuscarCliente() {
        LOGGER.log(ADFLogger.TRACE, "Inside resetPopupBuscarCliente.");
        DCIteratorBinding buscarClienteIterator = null;
        ViewObject buscarClienteVO = null;

        // Hacemos un reset en panel de búsqueda antes de cerrar el popup
        QueryModel queryModel = queryBuscarCliente.getModel();
        QueryDescriptor queryDescriptor = queryBuscarCliente.getValue();
        queryModel.reset(queryDescriptor);
        queryBuscarCliente.refresh(FacesContext.getCurrentInstance());

        // Limpiamos tabla
        buscarClienteIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("BuscarClienteVOIterator");
        buscarClienteVO = buscarClienteIterator.getViewObject();

        // Nota by FCP: ejecutamos clearCache() en lugar de executeEmptyRowSet() puesto que este último no funciona cuando se invoca
        // desde un link/botón dentro de la tabla. Funciona bien si sacamos el botón/link de la tabla.
        buscarClienteVO.clearCache(); // Con clearcache garantizamos que NO se queden filtrados los resultados (aunque muestra todos)
    }
    
    //Metodos para agregar un nuevo monto operacion del tipo2 cuando no exista
    private void agregarMontoTotalOperacion()
    {
        LOGGER.warning("Inside agregarMontoTotalOperacion");
        
        boolean tieneMontoTotal = false;
        DCIteratorBinding montoOperacionIteratorDC = null;
        RowSetIterator iterator = null;
        OperacionBean operacionBean =
            (OperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.operacionBean}");
        
        //Obtener el iterador de montoOperacion
        montoOperacionIteratorDC = ADFUtils.getDCBindingContainer().findIteratorBinding("montoOperacionIterator1");
        
        iterator = montoOperacionIteratorDC.getRowSetIterator();
        iterator.reset();
        
        //Recorrer el iterador para validar si existe ya un monto con id tca monto igual a 1
        for(int i =0; i < iterator.getRowCount(); i++)
        {
            Row row = iterator.getRowAtRangeIndex(i);
            String idTipoMonto = (String)row.getAttribute("IdTcaTipoMonto");
            
            // Verificamos que posea el montoTotal
            if(idTipoMonto.equals("1"))
            {
                tieneMontoTotal = true;
            }
        }
        iterator.closeRowSetIterator();  
        
        
        LOGGER.warning("tieneMontoTotal: " + tieneMontoTotal);
        LOGGER.warning("operacionBean.getEsMontoTotal: " + operacionBean.getEsMontoTotal());
        //Si no existe un monto del tipo monto total y getEsMontoTotal es true se hace un createInsert del nuevo monto
        if(!tieneMontoTotal && operacionBean.getEsMontoTotal())
        {
            LOGGER.warning("montoOperacionIteratorDC.getEstimatedRowCount(): " + montoOperacionIteratorDC.getEstimatedRowCount());
            LOGGER.warning("Agregando nuevo monto operacion vacio");
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operBinding = bindings.getOperationBinding("CreateInsertMontoOperacion");
            operBinding.execute(); 
            LOGGER.warning("montoOperacionIteratorDC.getEstimatedRowCount(): " + montoOperacionIteratorDC.getEstimatedRowCount());
            
            //Se obtiene nuevamente el iterador de montoOperacion con ya la nueva fila seleccionada
            montoOperacionIteratorDC = ADFUtils.getDCBindingContainer().findIteratorBinding("montoOperacionIterator1");
            
            Long indexL = montoOperacionIteratorDC.getEstimatedRowCount()-1L;
            int index = 0;//indexL.intValue();
            LOGGER.warning("Descripcion Fila Seleccionada: " + montoOperacionIteratorDC.getRowAtRangeIndex(index).getAttribute("Descripcion"));
            montoOperacionIteratorDC.getRowAtRangeIndex(index).setAttribute("Descripcion", "TOTAL");
            montoOperacionIteratorDC.getRowAtRangeIndex(index).setAttribute("monto", 0);
            montoOperacionIteratorDC.getRowAtRangeIndex(index).setAttribute("IdTcaTipoMonto", 1);
            montoOperacionIteratorDC.getRowAtRangeIndex(index).setAttribute("idOperacion", getIdOperacion());
            montoOperacionIteratorDC.getRowAtRangeIndex(index).setAttribute("id", -1);
            
            
            operBinding = bindings.getOperationBinding("Commit");
            operBinding.execute(); 
                        
        }
        
    }
    
    
    //Metodo para actualizar la operacion en la base de datos
    private Boolean actualizarDatosOperacionDB()
    {
        LOGGER.warning("Inside actualizarDatosOperacionDB");
        
        Boolean estado = false;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding montoOperacionIteratorDC = null;
        DCIteratorBinding detalleOperacionEditableIteratorDC = null;
        RowSetIterator iterator = null;
        HashMap<String, ActualizarOperacionResponseType> respuestaServicio = null;
        ActualizarOperacionResponseType response = new ActualizarOperacionResponseType();
        
        LOGGER.warning("Obteniendo datos del payload");
        //Se obtienen los datos
        String nombreOperacion = (null != ADFUtils.getBoundAttributeValue("nombre")) 
                ? (String)ADFUtils.getBoundAttributeValue("nombre"): null;
        String idCliente = (null != ADFUtils.getBoundAttributeValue("idCliente")) 
                ? (String)ADFUtils.getBoundAttributeValue("idCliente"): null;
        Integer idProducto = (null != ADFUtils.getBoundAttributeValue("idProducto")) 
                ? (Integer)ADFUtils.getBoundAttributeValue("idProducto"): null;
        String descripcion = (null != ADFUtils.getBoundAttributeValue("descripcion")) 
                ? (String)ADFUtils.getBoundAttributeValue("descripcion"): null;
        Integer idMoneda = (null != ADFUtils.getBoundAttributeValue("IdTipoMoneda")) 
                ? (Integer)ADFUtils.getBoundAttributeValue("IdTipoMoneda"): null;
        //Integer IdCatPais = (null != ADFUtils.getBoundAttributeValue("IdCatPais")) 
        //        ? (Integer)ADFUtils.getBoundAttributeValue("IdCatPais"): null;
        Integer IdCatPais = (null != ADFUtils.getBoundAttributeValue("idCatPais")) 
                ? (Integer)ADFUtils.getBoundAttributeValue("idCatPais"): null;
        LOGGER.warning("IdCatPais: " + IdCatPais);
        if(IdCatPais == null){
            IdCatPais = 7;
            LOGGER.warning("IdCatPais quemado: " + IdCatPais); 
        }
        
        BigDecimal montoTotal = null;
        BigDecimal montoSolicitado = null;
        Integer idSectorMercado = (null != ADFUtils.getBoundAttributeValue("IdSectorMercado")) 
                ? (Integer)ADFUtils.getBoundAttributeValue("IdSectorMercado"): null;
        Boolean progamadoPOA = (null != ADFUtils.getBoundAttributeValue("programadoPOA")) 
                ? (Boolean)ADFUtils.getBoundAttributeValue("programadoPOA"): null;
        Integer idEjercicioPOA = (null != ADFUtils.getBoundAttributeValue("IdEjercicioPOA")) 
                ? (Integer)ADFUtils.getBoundAttributeValue("IdEjercicioPOA"): null;
        String unidadEjecutora = (null != ADFUtils.getBoundAttributeValue("unidadEjecutora")) 
                ? (String)ADFUtils.getBoundAttributeValue("unidadEjecutora"): null;
        Integer idTipoGarantia = (null != ADFUtils.getBoundAttributeValue("IdTipoGarantia")) 
                ? (Integer)ADFUtils.getBoundAttributeValue("IdTipoGarantia"): null;
        Boolean componenteConcesionalidad = (null != ADFUtils.getBoundAttributeValue("componenteConcesionalidad")) 
                ? (Boolean)ADFUtils.getBoundAttributeValue("componenteConcesionalidad"): null;
        
        //Se llenan los montos total y solicitado de iterador
        //Obtener el iterador de montoOperacion
        LOGGER.warning("Obteniendo iterator de monto");
        montoOperacionIteratorDC = ADFUtils.getDCBindingContainer().findIteratorBinding("montoOperacionIterator1");
        
        iterator = montoOperacionIteratorDC.getRowSetIterator();
        iterator.reset();
        
        LOGGER.warning("Obteniendo montos");
        LOGGER.warning("Cantidad de Montos DC: " + montoOperacionIteratorDC.getEstimatedRowCount());        
        LOGGER.warning("Cantidad de Montos IT: " + iterator.getRowCount());        
        //Recorrer el iterador para validar si existe ya un monto con id tca monto igual a 1
        for(int i =0; i < iterator.getRowCount(); i++)
        {
            Row row = iterator.getRowAtRangeIndex(i);
            
            String idTipoMonto = (String)row.getAttribute("IdTcaTipoMonto");
            LOGGER.warning("index Monto " + i);
            LOGGER.warning("idTipoMonto " + idTipoMonto);
            
            LOGGER.warning("Iterator Monto " + row.getAttribute("monto"));
            String rawMonto = (null != row.getAttribute("monto")) 
                ? row.getAttribute("monto").toString(): null;
            
            LOGGER.warning("Raw Monto " + rawMonto);
            BigDecimal monto = rawMonto != null ? BigDecimal.valueOf(Long.parseLong(rawMonto)) : null;
            LOGGER.warning("Monto " + monto);
            
            // Obtenemos cada monto a cada variable
            if(idTipoMonto.equals("1"))
            {
                montoTotal = monto;
            }
            if(idTipoMonto.equals("2"))
            {
                montoSolicitado = monto;
            }   
        }
        
        iterator.closeRowSetIterator();  
        
        
        LOGGER.warning("Set de codigo de operacion");
        //Se filtra el Vo DetalleOperacionEditable por el codigo de operacion
        OperationBinding operationBinding = bindings.getOperationBinding("setcodigoOperacion");
        operationBinding.getParamsMap().put("value", getIdOperacion());
        operationBinding.execute();
        
        LOGGER.warning("Obteniendo Iterator de detalleOperacionEditableVO");
        //Se asignan los nuevos datos a los campos correspondientes
        detalleOperacionEditableIteratorDC = ADFUtils.getDCBindingContainer().findIteratorBinding("DetalleOperacionEditableVOIterator");
        
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("Nombre", nombreOperacion);
        LOGGER.warning("detalleOperacionEditableIteratorDC Nombre: " + detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).getAttribute("Nombre")); 
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("IdCliente", idCliente);
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("IdProducto", idProducto);
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("Descripcion", descripcion);
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("IdMoneda", idMoneda);
        LOGGER.warning("detalleOperacionEditableIteratorDC IdMoneda: " + detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).getAttribute("IdMoneda")); 
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("IdCatPais", IdCatPais);
        LOGGER.warning("detalleOperacionEditableIteratorDC IdCatPais: " + detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).getAttribute("IdCatPais")); 
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("Montototal", montoTotal);
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("Montosolicitado", montoSolicitado);
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("SectorMercado", idSectorMercado);
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("ProgramadoPoa", progamadoPOA ? 1 : 0);
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("EjercicioPoa", idEjercicioPOA);
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("UnidadEjecutora", unidadEjecutora);
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("TipoGarantia", idTipoGarantia);
        detalleOperacionEditableIteratorDC.getRowAtRangeIndex(0).setAttribute("ComponenteConcecionalidad", componenteConcesionalidad ? 1 : 0);
                
        
        LOGGER.warning("Actualizando datos de operacion");
        //Se llama el metodo de actualizarDetalleoperacion
        operationBinding = bindings.getOperationBinding("actualizarDetalleOperacion");
        operationBinding.execute(); 
        
        if (operationBinding.getErrors().size() != 0) {
            // Manejo de errores
        } else if (operationBinding.getResult() != null) {
            respuestaServicio = (HashMap<String, ActualizarOperacionResponseType>) operationBinding.getResult();
            response = respuestaServicio.get("response");
            if (response.getResultado() != null) {
                
                LOGGER.warning("response.getResultado().getMessage().toString(); " + response.getResultado().getMessage().toString());
                LOGGER.warning("response.getResultado().getResult().toString(); " + response.getResultado().getResult().toString());
                
                estado = response.getResultado().getResult().toString().equals("OK");
                
                if(!estado)
                {
                    JSFUtils.addFacesErrorMessage(response.getResultado().getMessage().toString());
                }
            }
        }
        
        return estado;
    }
    
    //Metodos de las acciones en pantalla
    public void actualizarPayloadElement(String psElementName, Object poValue) {
      AttributeBinding attributeBinding = null;
    
      attributeBinding = ADFUtils.findControlBinding(psElementName);
      
      if(attributeBinding!=null)
          attributeBinding.setInputValue(poValue);
      
    }
    
    /**
     * Ejecuta un operator bindings
     * @param params contiene mapa de objetos de la operacion
     * @param operBindName contiene el nombre de la operacion
     * @return devuelve objeto Operator Bindings
     */
    @SuppressWarnings("unchecked")
    private OperationBinding executeOperBinding(Map params,
                                                String operBindName){
        BindingContainer bindings = getBindings();
        OperationBinding oper = null;
        oper = bindings.getOperationBinding(operBindName);
        if (null != oper) {
            
            if(params != null){
                oper.getParamsMap().putAll(params);    
            }
            try {
                LOGGER.warning("EJECUTA METODO ".concat(operBindName));
                oper.execute();
            } catch (Exception e) {
                LOGGER.severe("Error al ejecutar Operator Bindings: " + operBindName, e);                
            }

            if (!oper.getErrors().isEmpty()) {
                LOGGER.severe("Operator Bindings devuelve errores: " + operBindName);
            }else{
                LOGGER.warning("Operator Bindings se ejecuto correctamente");
            }
        }else{
            LOGGER.severe("Error el operator Binding " + operBindName + 
                          " no fue encontrado");
        }

        return oper;
    }
    
    public String invokeFinalizarTarea()
    {
        
        String bundleKeyError = "";
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean  messageAdded = Boolean.FALSE;
        
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea)
        {
            case FenixConstants.PA16_ACTUALIZAR_DATOS_OPERACION:
                LOGGER.warning("PA16_ACTUALIZAR_DATOS_OPERACION.");
                
                isValidFinalizarTarea = true;
            
            break;
        
            case FenixConstants.PA16_APROBAR_CAMBIOS_OPERACION:
                LOGGER.warning("PA16_APROBAR_CAMBIOS_OPERACION");
                isValidFinalizarTarea = true;
                
            break;
        
            default:
                LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
                
        if (!isValidFinalizarTarea)
        {
            if(bundleKeyError != null)
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKeyError, BUNDLE));
        } else
        {
            finalizarTareaPopup();
        }
    
        return null;
    }
    
    public String invokeMasInformacion()
    {
        int codigoTarea = getCodigoTarea();
        Boolean isValidMasInformacion = false;
        
        switch (codigoTarea)
        {
            case FenixConstants.PA16_APROBAR_CAMBIOS_OPERACION:
                LOGGER.warning("PA16_APROBAR_CAMBIOS_OPERACION");
            isValidMasInformacion =  validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                
            break;
        
            default:
                LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
                
        if (!isValidMasInformacion)
        {
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ERROR_AGREGAR_COMENTARIO", BUNDLE));
        } else
        {
            masInformacionPopup();
        }
        
        return null;
    }
    
    public String invokeRechazar()
    {
        int codigoTarea = getCodigoTarea();
        
        Boolean isValidRechazar = false;
        
        switch (codigoTarea)
        {
            case FenixConstants.PA16_APROBAR_CAMBIOS_OPERACION:
                LOGGER.warning("PA16_APROBAR_CAMBIOS_OPERACION");
            isValidRechazar =  validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                
            break;
        
            default:
                LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
                
        if (!isValidRechazar)
        {
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ERROR_AGREGAR_COMENTARIO", BUNDLE));
        } else
        {
            rechazarPopup();
        }
        
        return null;
    }
    
    public String invokeOperacionCancelada()
    {

        cancelarPopup();
        return null;
    }
    
    public String aceptarFinalizarTarea()
    {
      popupFinalizarTarea.hide();
      
      Boolean esValidoFinalizar = Boolean.FALSE;
          
      int codigoTarea = getCodigoTarea();
      
      switch (codigoTarea)
      {
          case FenixConstants.PA16_APROBAR_CAMBIOS_OPERACION: 
              LOGGER.fine("PA16_APROBAR_CAMBIOS_OPERACION");
              esValidoFinalizar = Boolean.FALSE;

              OperationBinding oper = null;
          
              esValidoFinalizar = actualizarDatosOperacionDB();
          
          break;
      
          case FenixConstants.PA16_ACTUALIZAR_DATOS_OPERACION: 
            LOGGER.fine("PA16_ACTUALIZAR_DATOS_OPERACION");
                        
            esValidoFinalizar = Boolean.TRUE;
          break;
      
          default:
              LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
          break;
      }
      
      if(esValidoFinalizar)
      {
          InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
          return invokeActionBean.invokeOperation();
      }
      else
      {
          LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): " + esValidoFinalizar);
          return null;
      }
    }
    
    public String aceptarMasInformacion()
    {
        popupMasInfo.hide();
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
      
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    public String aceptarCancelar()
    {
        popupCancelar.hide();
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
      
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    public String aceptarRechazar()
    {
        popupRechazar.hide();
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
      
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
}
