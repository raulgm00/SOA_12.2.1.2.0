package org.bcie.fenix.view.lineacredito;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import java.text.NumberFormat;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;

import oracle.jbo.RowSetIterator;

import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

import pc05formalizaciongenerichumantask.constants.FormalizacionConstants;

import pc05formalizaciongenerichumantask.util.NumbersUtil;
import oracle.adf.model.BindingContext;



import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlListBinding;
import oracle.jbo.uicli.binding.JUCtrlValueBindingRef;

public class AdministradorLineaCreditoActionsBean {
    private static ADFLogger logger = null;
    private static final String BUNDLE = "pc05formalizaciongenerichumantask.PC05FormalizacionGenericHumanTaskBundle";
    private static String pIdLineaCreditoFilter=null;
    //variables para los popup´s
    private RichPopup popupEliminar;
    private RichPopup popupAgregarVencimiento;
    private RichPopup popupAgregarComision;
    
    // Inicializacion de componentes visuales en pantalla
    private RichOutputText otInitForm;
    private RichPopup popupAgregarLineaCredito;
    private RichPopup popupModificarLineaCredito;
    private RichPopup popupEliminarLineaCredito;
    private RichInputDate fechaInicioUIC;
    private RichInputText comisionFrecuenciaUIC;
    private RichPanelFormLayout formAgregarEditarLineaUIC;
    private RichTable tablaDatosLineaCredUIC;
    private RichPanelGroupLayout formDatosLineaCreditoUIC;


    public AdministradorLineaCreditoActionsBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void lineaCreditoSelectionListener(SelectionEvent selectionEvent) {
        logger.log(ADFLogger.WARNING, "Inside lineaCreditoSelectionListener");

        // Actualizamos row seleccionado en tabla
        JSFUtils.resolveMethodExpression("#{bindings.DatosLineaCreditoVO.collectionModel.makeCurrent}", 
                                         Object.class, new Class[] { SelectionEvent.class }, 
                                         new Object[] { selectionEvent });
        
        // Llenamos datos de la línea de crédito seleccionada en la tabla DatosLineaCreditoVO
        consultarLineaCredito();
    }
    
    private Boolean consultarLineaCredito() {
        logger.log(ADFLogger.WARNING, "Inside consultarLineaCredito");
        Boolean esConsultarExitoso = Boolean.TRUE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String idOperacion = null;
        String instanciaProceso = null;
        Integer idProducto = null;
        Long idLineaCredito = null;
        String idTarea = null;
        Boolean solicitudContratacion = Boolean.FALSE;
        
        //Crear instancia de AdministradorLineaCreditoBean
        AdministradorLineaCreditoBean administradorLineaCreditoBean = (AdministradorLineaCreditoBean)
            JSFUtils.resolveExpression("#{pageFlowScope.AdministradorLineaCreditoMB}");
        
        //Limpiamos las listas de eliminados(vencimientos y comisiones)
        //Limpia lista de vencimientos
        administradorLineaCreditoBean.getIdsVencimientosEliminados().clear();
        
        //Limpia lista de comisiones
        administradorLineaCreditoBean.getIdsComisionesEliminadas().clear();
        
        // Se inicializan variables que se envian como parametros para el método consultarLineaCreditoByIdLineaCredito
        //Recupera el "ID" de la linea de credito seleccionada en la tabla DatosLineaCreditoVO
        if(null != JSFUtils.resolveExpression("#{bindings.Id.inputValue}")){
            idLineaCredito=(Long)JSFUtils.resolveExpression("#{bindings.Id.inputValue}");
        }
        //Recupera el idOperacion de la entrada del taskflow
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")){
            idOperacion=(String)JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");
        }
        //Recupera la InstanciaProceso de la entrada del taskflow
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}")){
            instanciaProceso=(String)JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}");
            //pIdLineaCreditoFilter = idLineaCredito.toString(); 
            //logger.warning("Se le asigna en recuperarIdFlexByLinea a la variable pIdLineaCreditoFilter el valor->"+ pIdLineaCreditoFilter.toString());
        }
        //Recupera el idProducto de la entrada del taskflow
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}")){
            idProducto =(Integer)JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}");
        }
        //Recuperar el idTarea de la entrada del taskflow  por incidencia FNXII-3411
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}")){
            idTarea =(String)JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}");
        }
        //Recuperar el solicitudContratacion de la entrada del taskflow 
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.solicitudContratacion}")){
            solicitudContratacion =(Boolean)JSFUtils.resolveExpression("#{pageFlowScope.solicitudContratacion}");
        }
        
        //Se invoca el metodo consultarLineaCreditoByIdLineaCredito
        operationBinding = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
        
        //Datos de prueba
//        instanciaProceso = "580001";
        logger.warning("instanciaProcesoR1: " + instanciaProceso);
        //Se envian los parametros del metodo consultarLineaCreditoByIdLineaCredito
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso); //InstanciaProceso(BPM)
        operationBinding.getParamsMap().put("idLineaCredito", idLineaCredito);
        operationBinding.getParamsMap().put("idProducto", idProducto);
        //se agrega parametro idTarea por incidencia FNXII-3411
        operationBinding.getParamsMap().put("idTarea", idTarea);
        operationBinding.getParamsMap().put("solicitudContratacion", solicitudContratacion);

        // Invocamos método
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            esConsultarExitoso = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        if(getFormDatosLineaCreditoUIC() != null){
            logger.warning("Refresca panel de datos de la linea de credito");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getFormDatosLineaCreditoUIC());
        }
        
        if(idTarea.equals("62"))
             recuperarIdFlexByLinea();
        
        return esConsultarExitoso;
    }
    
    String idFlexLinea = ""; 
    public void recuperarIdFlexByLinea(){
        
        logger.warning("Inicia metodo recuperarIdFlexByLinea");
        
        Long idLineaCredito = null; 
        
        if(null != JSFUtils.resolveExpression("#{bindings.Id.inputValue}")){
            idLineaCredito=(Long)JSFUtils.resolveExpression("#{bindings.Id.inputValue}");
        }
        
        AdministradorLineaCreditoBean administradorLineaCreditoBean = (AdministradorLineaCreditoBean)
            JSFUtils.resolveExpression("#{pageFlowScope.AdministradorLineaCreditoMB}");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
       
        try{
            operationBinding = bindings.getOperationBinding("getIdFlexcubeByIdLinea");        
            operationBinding.getParamsMap().put("idLinea", idLineaCredito);
            operationBinding.execute();
            
            if(operationBinding.getErrors().isEmpty()){
              idFlexLinea = (String)operationBinding.getResult();
            }else{
                logger.warning("Error al ejecutar operBinding getIdFlexcubeByIdLinea ->"+ operationBinding.getErrors().toString());
                JSFUtils.addFacesErrorMessage("Ha cocurrido un error al ejecutar el metodo getIdFlexcubeByIdLinea");
            }
            
        }catch(Exception e){
            logger.warning("Error al ejecutar operBinding getIdFlexcubeByIdLinea ->", e);
            JSFUtils.addFacesErrorMessage("Ha cocurrido un error al ejecutar el metodo getIdFlexcubeByIdLinea");
        }
        
        if(idFlexLinea != null && !idFlexLinea.isEmpty()){
            administradorLineaCreditoBean.setEsEditable(Boolean.FALSE);
        }else{
            administradorLineaCreditoBean.setEsEditable(Boolean.TRUE);
        }
        
        logger.warning("Datos Linea Editale: "+administradorLineaCreditoBean.getEsEditable());
        
    }
    
    
    
    public void setPopupEliminar(RichPopup popupEliminar) {
        this.popupEliminar = popupEliminar;
    }

    public RichPopup getPopupEliminar() {
        return popupEliminar;
    }

    public void esRevolventeValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "Inside esRevolventeValueChangeListener");
        //Se recupera el contenedor de los bindings
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Integer idProducto = null;
        //actualiza el valor seleccionado
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        //Validar que el nuevo valor sea diferente a null
        if(valueChangeEvent.getNewValue()!=null ){
        //Recupera el idProducto de la entrada del taskflow
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}")){
            idProducto =(Integer)JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}");
        }
            OperationBinding operationBinding = bindings.getOperationBinding("filtrarProducto");
            //Se envian los parametros del metodo consultarLineaCreditoByIdLineaCredito
            //IdOperacion
            operationBinding.getParamsMap().put("idProducto", idProducto);

            if(valueChangeEvent.getNewValue().toString().equals("1") || valueChangeEvent.getNewValue().toString()=="1"){
                operationBinding.getParamsMap().put("isRevolvente","Y");
            }else{
                operationBinding.getParamsMap().put("isRevolvente","N");
            }
            operationBinding.execute();
        }//cierra if != null
        
        this.setLineasValidasGuardadas("N");
        
        }
    
    public void tiposComisionValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING,
                   "Inside tiposComisionValueChangeListener: " + valueChangeEvent.getComponent().getId());
        String tipoComision = null;
        String codigoComision = null;
        DCIteratorBinding voAgregarComision = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        tipoComision = (String) JSFUtils.resolveExpression("#{bindings.Descripcion.inputValue}");
        codigoComision = (String) JSFUtils.resolveExpression("#{bindings.Codigo.inputValue}");

        // Asignamos en current row de AgregarComisionVO los valores de TipoComision y CodigoComision
        voAgregarComision = ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarComisionVOIterator");
        voAgregarComision.getRowAtRangeIndex(0).setAttribute("TipoComision", tipoComision);
        voAgregarComision.getRowAtRangeIndex(0).setAttribute("CodigoComision", codigoComision);
    }
    
    public void agregarModificarVencimientoPlazoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside agregarModificarVencimientoPlazoActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        DCIteratorBinding voAgregarVencimientoPlazo = null;
        DCIteratorBinding voVencimientoPlazo = null;
        Row rowAgregarVencimiento = null;
        Row rowVencimiento = null;
        String opAdjuntarModificar = null;
        RichPopup.PopupHints popupHints = null;
        
        // Asignación de variables
        opAdjuntarModificar = (String)JSFUtils.resolveExpression("#{viewScope.opAdjuntarModificar}");
        
        // Creamos row de captura
        operationBinding = bindings.getOperationBinding("crearRowVencimientoPlazo");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } 
        
        // Si es modificar, asignamos datos de current row de tabla a row de captura
        if((opAdjuntarModificar != null) && (opAdjuntarModificar.equalsIgnoreCase("modificar"))) {
            
            // Asignación de variables para modificar row
            voAgregarVencimientoPlazo = ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarVencimientoPlazoVOIterator");
            voVencimientoPlazo = ADFUtils.getDCBindingContainer().findIteratorBinding("VencimientoPlazoVOIterator");
            rowAgregarVencimiento = voAgregarVencimientoPlazo.getRowAtRangeIndex(0);
            rowVencimiento = voVencimientoPlazo.getCurrentRow();
            
            rowAgregarVencimiento.setAttribute("FechaInicio", rowVencimiento.getAttribute("FechaInicio"));
            rowAgregarVencimiento.setAttribute("FechaVencimiento", rowVencimiento.getAttribute("FechaVencimiento"));
            rowAgregarVencimiento.setAttribute("IdPlazo", rowVencimiento.getAttribute("IdPlazo"));
            rowAgregarVencimiento.setAttribute("IdTipoDeFecha", rowVencimiento.getAttribute("IdTipoDeFecha"));
            rowAgregarVencimiento.setAttribute("IdTipoDeVencimiento", rowVencimiento.getAttribute("IdTipoDeVencimiento"));
            rowAgregarVencimiento.setAttribute("TipoPlazo", rowVencimiento.getAttribute("TipoPlazo"));
            rowAgregarVencimiento.setAttribute("Plazo", rowVencimiento.getAttribute("Plazo"));
            rowAgregarVencimiento.setAttribute("TipoDeFecha", rowVencimiento.getAttribute("TipoDeFecha"));
            rowAgregarVencimiento.setAttribute("TipoDeVencimiento", rowVencimiento.getAttribute("TipoDeVencimiento"));
        }
        
        // Abrimos popup
        popupHints = new RichPopup.PopupHints();
        getPopupAgregarVencimiento().show(popupHints);
    }
    
    public void agregarModificarComisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside agregarModificarComisionActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        DCIteratorBinding voTiposComision = null;
        DCIteratorBinding voAgregarComision = null;
        DCIteratorBinding voComision = null;
        Row[] rowTiposComision = null;
        Row rowAgregarComision = null;
        Row rowComision = null;
        String opAdjuntarModificar = null;
        String codProducto = null;
        RichPopup.PopupHints popupHints = null;
        
        // Asignación de variables
        opAdjuntarModificar = (String)JSFUtils.resolveExpression("#{viewScope.opAdjuntarModificar}");
        codProducto = (String)JSFUtils.resolveExpression("#{bindings.Id1.inputValue}");

        // Creamos row de captura
        operationBinding = bindings.getOperationBinding("crearRowComision");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } 
        
        // Asignación de variables para row de captura
        voAgregarComision = ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarComisionVOIterator");
        rowAgregarComision = voAgregarComision.getRowAtRangeIndex(0);
        
        // Filtramos el combo con los Tipos de Comisión
        operationBinding = bindings.getOperationBinding("setcodProducto");
        operationBinding.getParamsMap().put("value", codProducto);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } 
        
        // Ejecutamos query en combo Tipos de Comisión (debido a que se filtró)
        voTiposComision = ADFUtils.getDCBindingContainer().findIteratorBinding("TiposComisionLOVIterator");
        voTiposComision.executeQuery();
        
        // Inicializamos el VO AgregarComisionVO con el valor seleccionado por defecto en Tipos de Comisión
        if(voTiposComision.getCurrentRow() != null) {
            rowAgregarComision.setAttribute("CodigoComision", voTiposComision.getCurrentRow().getAttribute("Codigo"));
            rowAgregarComision.setAttribute("TipoComision", voTiposComision.getCurrentRow().getAttribute("Descripcion"));
        }
        
        // Si es modificar, asignamos datos de current row de tabla a row de captura
        if((opAdjuntarModificar != null) && (opAdjuntarModificar.equalsIgnoreCase("modificar"))) {
            
            // Asignación de variables para modificar row
            voComision = ADFUtils.getDCBindingContainer().findIteratorBinding("ComisionVOIterator");
            rowComision = voComision.getCurrentRow();
            
            rowAgregarComision.setAttribute("BaseCalculo", rowComision.getAttribute("BaseCalculo"));
            rowAgregarComision.setAttribute("TipoFrecuencia", rowComision.getAttribute("TipoFrecuencia"));
            rowAgregarComision.setAttribute("Frecuencia", rowComision.getAttribute("Frecuencia"));
            rowAgregarComision.setAttribute("CodigoComision", rowComision.getAttribute("CodigoComision"));
            rowAgregarComision.setAttribute("IdBaseCalculo", rowComision.getAttribute("IdBaseCalculo"));
            rowAgregarComision.setAttribute("IdFrecuencia", rowComision.getAttribute("IdFrecuencia"));
            rowAgregarComision.setAttribute("IdTipoComision", rowComision.getAttribute("IdTipoComision"));
            rowAgregarComision.setAttribute("Porcentaje", rowComision.getAttribute("Porcentaje"));
            rowAgregarComision.setAttribute("TipoComision", rowComision.getAttribute("TipoComision"));
            
            // Asignamos valor en combo Tipos de Comisión
            rowTiposComision = voTiposComision.getViewObject().getFilteredRows("Codigo", rowComision.getAttribute("CodigoComision"));
            if(rowTiposComision.length > 0)
                voTiposComision.getViewObject().setCurrentRow(rowTiposComision[0]);
        }
        
        // Abrimos popup
        popupHints = new RichPopup.PopupHints();
        getPopupAgregarComision().show(popupHints);
    }
    
    public void aceptarAgregarModificarVencimientoPlazoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside aceptarAgregarModificarVencimientoPlazoActionListenerX: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String opAdjuntarModificar = null;
        DCIteratorBinding voAgregarVencimientoPlazo = null;
        DCIteratorBinding voVencimientoPlazo = null;
        Row rowAgregarVencimiento = null;
        
        // Asignación de variables
        opAdjuntarModificar = (String)JSFUtils.resolveExpression("#{viewScope.opAdjuntarModificar}");
        voAgregarVencimientoPlazo = ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarVencimientoPlazoVOIterator");
        voVencimientoPlazo = ADFUtils.getDCBindingContainer().findIteratorBinding("VencimientoPlazoVOIterator");
        rowAgregarVencimiento = voAgregarVencimientoPlazo.getRowAtRangeIndex(0);
         
        // Insertamos/modificamos en VO el Vencimiento
        if(opAdjuntarModificar.equalsIgnoreCase("agregar")) {
                
                //--------------------------------------------------------------------------------------------------------------
                try 
                {
                
                String vIdTipoDeVencimiento = rowAgregarVencimiento.getAttribute("IdTipoDeVencimiento").toString();
                int vExisteIdTipoDeVencimiento = 0;
                logger.log(ADFLogger.WARNING, " Inicia validacion de IdTipoDeVencimiento = " + vIdTipoDeVencimiento); 
                
                ViewObject vo = voVencimientoPlazo.getViewObject();
                RowSetIterator iter = vo.createRowSetIterator(null);
                
                Boolean hasNext = iter.hasNext();
                int i = 0;
                while(hasNext)
                {
                    i++;
                    logger.log(ADFLogger.WARNING, "Linea : "+i);  
                    Row r = iter.next();
                    
                    if(r == null)
                    {
                        hasNext = false;
                        logger.log(ADFLogger.WARNING, "Registros nulos");  
                    }
                    else
                    {  
                        if(r.getAttribute("IdTipoDeVencimiento") != null)
                        { 
                            String idTipoVencimiento =  r.getAttribute("IdTipoDeVencimiento").toString(); 
                            logger.log(ADFLogger.WARNING, " idTipoVencimiento = " + idTipoVencimiento); 
                            
                            if (idTipoVencimiento.equals(vIdTipoDeVencimiento))
                            {
                                vExisteIdTipoDeVencimiento++;
                                logger.log(ADFLogger.WARNING, "true idTipoVencimiento: " + idTipoVencimiento + "  == vIdTipoDeVencimiento: " + vIdTipoDeVencimiento );  
                            }
                            else
                            {
                                logger.log(ADFLogger.WARNING, "false idTipoVencimiento: " + idTipoVencimiento + "  == vIdTipoDeVencimiento: " + vIdTipoDeVencimiento );  
                            }
                            
                            if(vExisteIdTipoDeVencimiento >= 1)
                            {
                                logger.log(ADFLogger.WARNING, "Duplicado, Existen " + vExisteIdTipoDeVencimiento + " para el idTipoVencimiento " + vIdTipoDeVencimiento);  
                                hasNext = false; 
                                //String vDescTipoDeVencimiento = rowAgregarVencimiento.getAttribute("IdTipoDeVencimiento").toString();
                                JSFUtils.addFacesErrorMessage("El Tipo De Vencimiento seleccionado ya existe."); 
                                return;
                            }
                            else
                            {
                                logger.log(ADFLogger.WARNING, " Existen " + vExisteIdTipoDeVencimiento + " para el idTipoVencimiento " + vIdTipoDeVencimiento);  
                            }
                        }
                        else
                        { 
                            logger.log(ADFLogger.WARNING, " IdTipoDeVencimiento es nulo  "); 
                        }
                    }
                }
                
                logger.log(ADFLogger.WARNING, " Termino validacion de IdTipoDeVencimiento = " + vIdTipoDeVencimiento); 
                    
                    
                    Object vFechaInicio =  rowAgregarVencimiento.getAttribute("FechaInicio");            
                    Object vFechaVencimiento =  rowAgregarVencimiento.getAttribute("FechaVencimiento");
                    
                    if (vFechaInicio == null){        
                            logger.log(ADFLogger.WARNING, "La fecha de inicio no puede estar vacia."); 
                            JSFUtils.addFacesErrorMessage("La fecha de inicio no puede estar vacia."); 
                            return ;
                        }
                    
                    if (vFechaVencimiento == null){        
                            logger.log(ADFLogger.WARNING, "La fecha de vencimiento no puede estar vacia."); 
                            JSFUtils.addFacesErrorMessage("La fecha de vencimiento no puede estar vacia."); 
                            return ;
                        }
                    
                }
                catch(Exception exp)
                {
                    logger.log(ADFLogger.WARNING, " Error al verificar si existia el tipo de vencimiento: " + exp.getMessage()); 
                }
                //--------------------------------------------------------------------------------------------------------------
                
            
            operationBinding = bindings.getOperationBinding("agregarVencimiento");
            }
        else{
            
            //--------------------------------------------------------------------------------------------------------------
            try 
            { 
                Object vFechaInicio =  rowAgregarVencimiento.getAttribute("FechaInicio");            
                Object vFechaVencimiento =  rowAgregarVencimiento.getAttribute("FechaVencimiento");
                
                if (vFechaInicio == null){        
                        logger.log(ADFLogger.WARNING, "La fecha de inicio no puede estar vacia."); 
                        JSFUtils.addFacesErrorMessage("La fecha de inicio no puede estar vacia."); 
                        return ;
                    }
                
                if (vFechaVencimiento == null){        
                        logger.log(ADFLogger.WARNING, "La fecha de vencimiento no puede estar vacia."); 
                        JSFUtils.addFacesErrorMessage("La fecha de vencimiento no puede estar vacia."); 
                        return ;
                    }
                
            }
            catch(Exception exp)
            {
                logger.log(ADFLogger.WARNING, " Error al verificar si existia el tipo de vencimiento: " + exp.getMessage()); 
            }
            //--------------------------------------------------------------------------------------------------------------
            
            operationBinding = bindings.getOperationBinding("modificarVencimiento");
            operationBinding.getParamsMap().put("id", voVencimientoPlazo.getCurrentRow().getAttribute("Id"));
            operationBinding.getParamsMap().put("idVencimiento", voVencimientoPlazo.getCurrentRow().getAttribute("IdVencimiento"));
        }
        
        // Mapeo de datos de popup    
        operationBinding.getParamsMap().put("idTipoDeVencimiento", rowAgregarVencimiento.getAttribute("IdTipoDeVencimiento"));
        operationBinding.getParamsMap().put("idTipoDeFecha", rowAgregarVencimiento.getAttribute("IdTipoDeFecha"));
        operationBinding.getParamsMap().put("idPlazo", rowAgregarVencimiento.getAttribute("IdPlazo"));
        operationBinding.getParamsMap().put("tipoDeVencimiento",rowAgregarVencimiento.getAttribute("TipoDeVencimiento"));
        operationBinding.getParamsMap().put("tipoDeFecha", rowAgregarVencimiento.getAttribute("TipoDeFecha"));
        operationBinding.getParamsMap().put("tipoPlazo", rowAgregarVencimiento.getAttribute("TipoPlazo"));
        operationBinding.getParamsMap().put("plazo", rowAgregarVencimiento.getAttribute("Plazo"));
        operationBinding.getParamsMap().put("fechaInicio", rowAgregarVencimiento.getAttribute("FechaInicio"));
        operationBinding.getParamsMap().put("fechaVencimiento", rowAgregarVencimiento.getAttribute("FechaVencimiento"));
        
        // Invocamos método
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        setLineasValidasGuardadas("N");
        
        // Cerramos popup
        getPopupAgregarVencimiento().hide();
    }
    
    public void aceptarAgregarModificarComisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside aceptarAgregarModificarComisionActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String opAdjuntarModificar = null;
        DCIteratorBinding voAgregarComision = null;
        DCIteratorBinding voComision = null;
        DCIteratorBinding voTiposComision = null;
        Row rowAgregarComision = null;
        
        // Asignación de variables
        opAdjuntarModificar = (String)JSFUtils.resolveExpression("#{viewScope.opAdjuntarModificar}");
        voAgregarComision = ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarComisionVOIterator");
        voComision = ADFUtils.getDCBindingContainer().findIteratorBinding("ComisionVOIterator");
        rowAgregarComision = voAgregarComision.getRowAtRangeIndex(0);
        
        voTiposComision = ADFUtils.getDCBindingContainer().findIteratorBinding("TiposComisionLOVIterator");
 
        // Insertamos/modificamos en VO la Comisión
        if(opAdjuntarModificar.equalsIgnoreCase("agregar")) {
            
            //--------------------------------------------------------------------------------------------------------------
            try 
            {
            String vIdTipoComision = "";
            int vExisteIdTipoComision = 0;
            logger.log(ADFLogger.WARNING, " Inicia validacion de IdTipoComision " ); 
            vIdTipoComision = "COMP";// rowAgregarComision.getAttribute("IdTipoComision").toString();
            logger.log(ADFLogger.WARNING, "  IdTipoComision = " + vIdTipoComision); 
            ViewObject vo = voComision.getViewObject();
            RowSetIterator iter = vo.createRowSetIterator(null);
            
            Boolean hasNext = iter.hasNext();
            int i = 0;
            while(hasNext)
            {
                i++;
                logger.log(ADFLogger.WARNING, "Linea : "+i);  
                Row r = iter.next();
                
                if(r == null)
                {
                    hasNext = false;
                    logger.log(ADFLogger.WARNING, "Registros nulos");  
                }
                else
                {  
                    if(r.getAttribute("TipoComision") != null)
                    { 
                        String idTipoComision = r.getAttribute("TipoComision").toString().toUpperCase(); 
                        logger.log(ADFLogger.WARNING, " idTipoComision = " + idTipoComision); 
                        
                        if (idTipoComision.contains(vIdTipoComision))//.equals(vIdTipoComision))
                        {
                            vExisteIdTipoComision++;
                            logger.log(ADFLogger.WARNING, "true idTipoComision: " + idTipoComision + "  contains vIdTipoComision: " + vIdTipoComision );  
                        }
                        else
                        {
                            logger.log(ADFLogger.WARNING, "false idTipoComision: " + idTipoComision + "  contains vIdTipoComision: " + vIdTipoComision );  
                        }
                        
                        if(vExisteIdTipoComision >= 1)
                        {
                            logger.log(ADFLogger.WARNING, "Duplicado, Existen " + vExisteIdTipoComision + " para el idTipoComision " + vIdTipoComision);  
                            hasNext = false; 
                            //JSFUtils.addFacesErrorMessage("El Tipo de Comision " + idTipoComision + " ya existe."); 
                            JSFUtils.addFacesErrorMessage("El Tipo de Comision seleccionado ya existe."); 
                            return;
                        }
                        else
                        {
                            logger.log(ADFLogger.WARNING, " Existen " + vExisteIdTipoComision + " para el idTipoVencimiento " + vIdTipoComision);  
                        }
                    }
                    else
                    { 
                        logger.log(ADFLogger.WARNING, " IdTipoComision es nulo  "); 
                    }
                }
            }
            
            logger.log(ADFLogger.WARNING, " Termino validacion de IdTipoComision = " + vIdTipoComision); 
                
            }
            catch(Exception exp)
            {
                logger.log(ADFLogger.WARNING, " Error al verificar si existia el tipo comision: " + exp.getMessage()); 
            }
            //--------------------------------------------------------------------------------------------------------------
            
            
            operationBinding = bindings.getOperationBinding("agregarComision");
        }
        else{
            operationBinding = bindings.getOperationBinding("modificarComision");
            operationBinding.getParamsMap().put("id", voComision.getCurrentRow().getAttribute("Id"));
            operationBinding.getParamsMap().put("idComision", voComision.getCurrentRow().getAttribute("IdComision"));
        }
        
        // Mapeo de datos de popup    
        operationBinding.getParamsMap().put("idBaseCalculo", rowAgregarComision.getAttribute("IdBaseCalculo"));
        operationBinding.getParamsMap().put("idFrecuencia", rowAgregarComision.getAttribute("IdFrecuencia"));
        operationBinding.getParamsMap().put("baseCalculo", rowAgregarComision.getAttribute("BaseCalculo"));
        operationBinding.getParamsMap().put("tipoFrecuencia", rowAgregarComision.getAttribute("TipoFrecuencia"));
        operationBinding.getParamsMap().put("frecuencia", rowAgregarComision.getAttribute("Frecuencia"));
        operationBinding.getParamsMap().put("porcentaje", rowAgregarComision.getAttribute("Porcentaje"));
        
        if(voTiposComision.getCurrentRow() != null) {
          operationBinding.getParamsMap().put("idTipoComision", voTiposComision.getCurrentRow().getAttribute("Id"));
          operationBinding.getParamsMap().put("tipoComision", voTiposComision.getCurrentRow().getAttribute("Descripcion"));
          operationBinding.getParamsMap().put("codigoComision", voTiposComision.getCurrentRow().getAttribute("Codigo"));
        }else
        {
          operationBinding.getParamsMap().put("idTipoComision", rowAgregarComision.getAttribute("IdTipoComision"));
          operationBinding.getParamsMap().put("codigoComision", rowAgregarComision.getAttribute("CodigoComision"));
          operationBinding.getParamsMap().put("tipoComision", rowAgregarComision.getAttribute("TipoComision"));
        }
        
        // Invocamos método
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        // Cerramos popup
        getPopupAgregarComision().hide();
    }
    
    public void cancelarAgregarVencimientoPlazoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside cancelarAgregarVencimientoPlazoActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupAgregarVencimiento().hide();
    }
    
    public void cancelarAgregarComisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside cancelarAgregarVencimientoPlazoActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupAgregarComision().hide();
    }
    
    public void eliminarVencimientoComisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside eliminarVencimientoComisionActionListener: " + actionEvent.getComponent().getId());
        RichPopup.PopupHints popupHints = null;
        
        // Abrimos popup
        popupHints = new RichPopup.PopupHints();
        getPopupEliminar().show(popupHints);
    }
    
    public void aceptarEliminarVencimientoComisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside aceptarEliminarVencimientoComisionActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voVencimientoPlazo = null;
        DCIteratorBinding voComision = null;
        String opEliminarOrigen = null;
        OperationBinding operationBinding = null;
        Row rowVencimiento = null;
        Row rowComision = null;
        Long idVencimiento = null;
        Long idComision = null;
        
        //Crear instancia de AdministradorLineaCreditoBean
        AdministradorLineaCreditoBean administradorLineaCreditoBean = (AdministradorLineaCreditoBean)
            JSFUtils.resolveExpression("#{pageFlowScope.AdministradorLineaCreditoMB}");
        
        // Asignación de variables
        opEliminarOrigen = (String)JSFUtils.resolveExpression("#{viewScope.opEliminarOrigen}");
        
        if((opEliminarOrigen != null) && (opEliminarOrigen.equalsIgnoreCase("vencimiento"))) {
            
            voVencimientoPlazo = ADFUtils.getDCBindingContainer().findIteratorBinding("VencimientoPlazoVOIterator"); 
            rowVencimiento = voVencimientoPlazo.getCurrentRow();
            idVencimiento = (Long)rowVencimiento.getAttribute("IdVencimiento");
            
            // Si el Id Vencimiento no es null, agregamos Id a lista de eliminados
            if(idVencimiento != null)
                administradorLineaCreditoBean.getIdsVencimientosEliminados().add(idVencimiento);
            
            // Eliminamos Vencimiento seleccionado de tabla
            operationBinding = bindings.getOperationBinding("eliminarVencimiento");
            operationBinding.getParamsMap().put("id", rowVencimiento.getAttribute("Id"));
        }
        else{
            voComision = ADFUtils.getDCBindingContainer().findIteratorBinding("ComisionVOIterator"); 
            rowComision = voComision.getCurrentRow();
            idComision = (Long)rowComision.getAttribute("IdComision");
                
            // Si el Id Comisión no es null, agregamos Id a lista de eliminados
            if(idComision != null)
                administradorLineaCreditoBean.getIdsComisionesEliminadas().add(idComision);
            
            // Eliminamos Comisión seleccionada de tabla
            operationBinding = bindings.getOperationBinding("eliminarComision");
            operationBinding.getParamsMap().put("id", rowComision.getAttribute("Id"));
        }
        
        // Invocamos método
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        setLineasValidasGuardadas("N");
        
        // Cerramos popup
        getPopupEliminar().hide();
    }
    
    public void cancelarEliminarVencimientoComisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside cancelarEliminarVencimientoComisionActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupEliminar().hide();
    }
    
    public void guardarDatosLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside guardarDatosLineaCreditoActionListener");
        Boolean esGuardarExitoso = Boolean.TRUE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        AdministradorLineaCreditoBean administradorLineaCreditoBean = null;
        OperationBinding operationBinding = null;
        DCIteratorBinding voDatosLineaCreditoVO = null; 
        
        DCIteratorBinding voDatosLineaCredito = null;
        voDatosLineaCredito = ADFUtils.getDCBindingContainer().findIteratorBinding("DatosLineaCreditoVOIterator");
        int actualIndex = voDatosLineaCredito.getCurrentRowIndexInRange();
        
        if(validarDatosLineaCredito(false)) {
            //Crear instancia de AdministradorLineaCreditoBean
            administradorLineaCreditoBean = (AdministradorLineaCreditoBean)
                JSFUtils.resolveExpression("#{pageFlowScope.AdministradorLineaCreditoMB}");
            
            // Insertamos al VO los Vencimientos eliminados
            if(administradorLineaCreditoBean.getIdsVencimientosEliminados().size() > 0) {
                
                operationBinding = bindings.getOperationBinding("agregarVencimientosEliminados");
                operationBinding.getParamsMap().put("idsVencimientosEliminados",
                                                            administradorLineaCreditoBean.getIdsVencimientosEliminados());
                operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    // Manejo de errores
                    esGuardarExitoso = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                }
            }
                    
            // Insertamos al VO las Comisiones eliminadas
            if(administradorLineaCreditoBean.getIdsComisionesEliminadas().size() >0) {
                
                operationBinding = bindings.getOperationBinding("agregarComisionesEliminadas");
                operationBinding.getParamsMap().put("idsComisionesEliminadas",
                                                            administradorLineaCreditoBean.getIdsComisionesEliminadas());
                
                operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    // Manejo de errores
                    esGuardarExitoso = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                }
            }
                
            // Ejecuta el metodo actualizarLineaCredito
            operationBinding = bindings.getOperationBinding("actualizarLineaCredito");
            operationBinding.getParamsMap().put("idOperacion", 
                                                JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
            operationBinding.execute();
            
            // Actualización exitosa de la Línea de crédito
            if(operationBinding.getErrors().size() < 1) {
                // Limpiamos las listas de vencimientos y comisiones eliminados
                administradorLineaCreditoBean.getIdsVencimientosEliminados().clear(); //Limpia lista de vencimientos
                administradorLineaCreditoBean.getIdsComisionesEliminadas().clear(); //Limpia lista de comisiones
                
                // Limpiamos RegistrarDatosLineaCreditoVO 
                operationBinding = bindings.getOperationBinding("inicializarRegistrarDatosLineaCredito");
                operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    // Manejo de errores
                    esGuardarExitoso = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                }
                
                // Consultamos y llenamos RegistrarDatosLineaCreditoVO
                logger.log(ADFLogger.WARNING, "execute consultarLineaCredito()");
                consultarLineaCredito();
                
                // Limpiamos tabla de Líneas de crédito
                voDatosLineaCreditoVO = ADFUtils.getDCBindingContainer().findIteratorBinding("DatosLineaCreditoVOIterator");
                voDatosLineaCreditoVO.executeQuery();
                
                Boolean solicitudContratacion = Boolean.FALSE;
                
                //Recuperar el solicitudContratacion de la entrada del taskflow 
                if(null != JSFUtils.resolveExpression("#{pageFlowScope.solicitudContratacion}")){
                    solicitudContratacion =(Boolean)JSFUtils.resolveExpression("#{pageFlowScope.solicitudContratacion}");
                }
                
                // Actualizamos datos del encabezado y Líneas de crédito (se cargan todos los datos de nuevo)
                operationBinding = bindings.getOperationBinding("consultarLineaCredito");
                operationBinding.getParamsMap().put("idOperacion", 
                                                    JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
                operationBinding.getParamsMap().put("instanciaProceso", 
                                                    JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}"));
                operationBinding.getParamsMap().put("idProducto", 
                                                    JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}"));
                operationBinding.getParamsMap().put("solicitudContratacion", solicitudContratacion);
                operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    // Manejo de errores
                    esGuardarExitoso = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                }
                
                // Inicializamos pantalla con datos de la línea de crédito seleccionada en la tabla DatosLineaCreditoVO
                if(esGuardarExitoso)
                {
                    //se mueve el mensaje, en este caso si se guardan los valores de la linea actual, pero igual luego se validan los demas valores.
                    JSFUtils.addFacesInformationMessage("Actualizó correctamente.");
                    
                    //se envia el indice actual ya que esta linea fue previamente verificada, asi se evita duplicidad de mensajes
                     
                    boolean vLineas = validarDatosLineasCredito(actualIndex);
                    //JSFUtils.addFacesErrorMessage(" validarDatosLineasCredito: "+vLineas );
                    if(vLineas)
                    { 
                        //Si todas las lineas poseen valores validos se asigna el valor a las lineasValidasGuardadas para permitir finalizar la tarea
                        setLineasValidasGuardadas("S");
                    }
                    else
                    {
                        setLineasValidasGuardadas("N");
                    }
                }
            }
        }
    }
    
    private void setLineasValidasGuardadas(String valor)
    {
        ADFContext adfCtx = ADFContext.getCurrent();
        Map sessionScope = adfCtx.getSessionScope();
        Object val = sessionScope.put("lineasValidasGuardadas", valor);
    }
    
    public Boolean validarDatosLineasCredito(Integer indiceLineaActual) 
    {
        Boolean esDatosCorrectos = Boolean.TRUE;
        
        //JURBINA BCIE@20/03/2019
        //Por problemas con las lineas, se validara de la siguiente forma: 
        //              Se obtiene el iterador y view object de la tabla principal de lineas de credito
        //              Se selecciona el primero del VO y se ejecuta el procedimiento consultarLineaCredito para obtener los datos faltantes
        //              Se itera posteriormente, por cada linea y se seleccionan nuevamente los datos, de esta forma todo esta en los bindings
        //              Al estar todo en Bindings se realizan las validaciones de forma "normal" y se van acumulando los mensajes
        
        DCIteratorBinding voDatosLineaCredito = null;
        voDatosLineaCredito = ADFUtils.getDCBindingContainer().findIteratorBinding("DatosLineaCreditoVOIterator");
        
        Long dlcRowCount = voDatosLineaCredito.getEstimatedRowCount();
        boolean vLineas = true;
        if(dlcRowCount == 0)
        {
            Boolean lineaValida = this.validarDatosLineaCredito(true);
            esDatosCorrectos = !lineaValida ? lineaValida : esDatosCorrectos;
        }
        else
        {
            //Se toma el indice actual y al final del ciclo se deja seleccionado nuevamente.
            int actualIndex = indiceLineaActual != null ? indiceLineaActual :  voDatosLineaCredito.getCurrentRowIndexInRange();
                        
            for(int i = 0; i < dlcRowCount; i++)
            {
                //solamente se validan las lineas que sean diferentes a la linea actual ya que fue previamente validada
                //se quito la validacion ya que permitia dejar guardar la linea actual con errores
                //if(i != actualIndex) 
                //{
                    voDatosLineaCredito.setCurrentRowIndexInRange(i);
                    this.consultarLineaCredito();
                    Boolean lineaValida = this.validarDatosLineaCredito(true);    
                    esDatosCorrectos = !lineaValida ? lineaValida : esDatosCorrectos;
                      
                    if (lineaValida==false && vLineas==true){
                        vLineas = false;
                    } 
                //}
            } 
            
            //se selecciona nuevamente la linea para que los bindings queden correctos
            voDatosLineaCredito.setCurrentRowIndexInRange(actualIndex);
            this.consultarLineaCredito();
            
        }  
        esDatosCorrectos = vLineas; 
        return esDatosCorrectos;
    }
    
    //Validación de linea seleccionada individualmente        
    private Boolean validarDatosLineaCredito(Boolean actualizaFlagLineasValidas) {
        Boolean esDatosCorrectos = Boolean.TRUE;
        Integer diasDePago = null;
        Integer plazoFinanciamiento = null;
        //validacion de cantidad decimales
        if(JSFUtils.resolveExpression("#{bindings.CantidadDecimales.inputValue}") != null) {
            String candidadDecimalesStr=JSFUtils.resolveExpression("#{bindings.CantidadDecimales.inputValue}").toString();
            Integer cantidadDeciInt=Integer.valueOf(candidadDecimalesStr);
            if(cantidadDeciInt!=0&&cantidadDeciInt!=2&&cantidadDeciInt!=3&&cantidadDeciInt!=4){
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("CANTIDAD_DECIMALES_MENSAJE", BUNDLE));    
            }
        }
        if(JSFUtils.resolveExpression("#{bindings.SpreadTasa.inputValue}") != null) {
            String candidadDecimalesStr=JSFUtils.resolveExpression("#{bindings.SpreadTasa.inputValue}").toString();
            Integer cantidadDeciInt=Integer.valueOf(candidadDecimalesStr);
            if(!(cantidadDeciInt>=2&&cantidadDeciInt<=5||cantidadDeciInt==0)){
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("CANTIDAD_DECIMALES_SPREAD", BUNDLE));    
            }
        }
        
        
        // Número de línea es obligatorio
        Object numeroLineaCredito = JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito.inputValue}");
        String textoLinea = numeroLineaCredito != null ? " Línea: " + numeroLineaCredito : "";
        if(( numeroLineaCredito == null) 
            || (numeroLineaCredito.toString().trim().length() < 1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Número de línea es requerido.");
        }
        
        // Fondo contable es obligatorio
        if((JSFUtils.resolveExpression("#{bindings.CatFondoContableVO.attributeValue}") == null) 
            || (JSFUtils.resolveExpression("#{bindings.CatFondoContableVO.attributeValue}").toString().trim().length() < 1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Fondo contable es requerido." + textoLinea);
        }
        
        // Es revolvente es obligatorio
        if((JSFUtils.resolveExpression("#{bindings.EsRevolvente.attributeValue}") == null) 
            || (JSFUtils.resolveExpression("#{bindings.EsRevolvente.attributeValue}").toString().trim().length() < 1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Es revolvente es requerido." + textoLinea);
        }
        
        // Aplican recursos ordinarios es obligatorio
        if((JSFUtils.resolveExpression("#{bindings.EsRecursosExternos.attributeValue}") == null) 
            || (JSFUtils.resolveExpression("#{bindings.EsRecursosExternos.attributeValue}").toString().trim().length() < 1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Aplican recursos ordinarios es requerido." + textoLinea);
        }
        
        // Producto es obligatorio
        if((JSFUtils.resolveExpression("#{bindings.CatProductoFlexcubeLOV.attributeValue}") == null) 
            || (JSFUtils.resolveExpression("#{bindings.CatProductoFlexcubeLOV.attributeValue}").toString().trim().length() < 1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Producto es requerido." + textoLinea);
        }
         
        // Fecha de apertura es obligatorio
        if((JSFUtils.resolveExpression("#{bindings.FechaValor.inputValue}") == null) 
            || (JSFUtils.resolveExpression("#{bindings.FechaValor.inputValue}").toString().trim().length() < 1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Fecha de apertura es requerido." + textoLinea);
        }

        // Plazo de financiamiento es obligatorio
        if((JSFUtils.resolveExpression("#{bindings.PlazoFinanciamiento.inputValue}") == null) 
            || (JSFUtils.resolveExpression("#{bindings.PlazoFinanciamiento.inputValue}").toString().trim().length() < 1)
            || (JSFUtils.resolveExpression("#{bindings.CatPlazoFinanciamientoLOV.attributeValue}") == null) 
            || (JSFUtils.resolveExpression("#{bindings.CatPlazoFinanciamientoLOV.attributeValue}").toString().trim().length() < 1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Plazo de financiamiento es requerido." + textoLinea);
        }
        else{
            // Plazo de financiamiento debe ser mayor a cero
            plazoFinanciamiento = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.PlazoFinanciamiento.inputValue}").toString());
            
            if(plazoFinanciamiento.intValue() < 1) {
                
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("El Plazo de financiamiento debe ser mayor a cero." + textoLinea);
            }
        }
        
        // Monto de la línea es obligatorio
        if((JSFUtils.resolveExpression("#{bindings.MontoLinea.inputValue}") == null) 
            || (JSFUtils.resolveExpression("#{bindings.MontoLinea.inputValue}").toString().trim().length() < 1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Monto de la línea es requerido." + textoLinea);
        }
        recuperarIdFlexByLinea();
        logger.log(ADFLogger.WARNING, "Valor idFlexLinea" + idFlexLinea);
        boolean vLineas = Boolean.TRUE;
        if(idFlexLinea == null){
            logger.log(ADFLogger.WARNING, "true validarDatosLineaCredito Valor idFlexLinea" + idFlexLinea);
            
        //Jurbina BCIE@20/03/2019
        //Los vencimientos de plazos siempre deben cotener Desembolso de la Totalidad de los Recursos y Inicio de Desembolso
        DCIteratorBinding voVencimientoPlazo = null;
        voVencimientoPlazo = ADFUtils.getDCBindingContainer().findIteratorBinding("VencimientoPlazoVOIterator");
        ViewObject vo = voVencimientoPlazo.getViewObject();
        RowSetIterator iter = vo.createRowSetIterator(null);

        Boolean poseeDesembolsoTotalidadRecursos = false;
        Boolean poseeInicioDesembolso = false;
        Boolean poseeDesembolsoTotalidadRecursosVencimiento = false;
        Boolean poseeInicioDesembolsoVencimiento = false;
        
        // Variables para validaciones de user stories 1684 y 1958. — FCP, 9/mar/2020.
        java.sql.Timestamp fechaVencimientoLineaCredito = (java.sql.Timestamp) JSFUtils.resolveExpression("#{bindings.FechaVencimiento.inputValue}");
        java.sql.Timestamp fechaVencimientoInicioDesembolsos = null;
        java.sql.Timestamp fechaVencimientoTotalidadRecursos = null;
        
        Boolean hasNext = iter.hasNext();
        
        while(hasNext)
        {
            Row r = iter.next();
            
            if(r == null)
            {
                hasNext = false;
            }
            else
            {
                if(r.getAttribute("IdTipoDeVencimiento") != null)
                {
                    Integer idTipoVencimiento = Integer.parseInt(r.getAttribute("IdTipoDeVencimiento").toString());
                    Object fechaVencimiento = r.getAttribute("FechaVencimiento");
                    
                    switch(idTipoVencimiento)
                    {
                        case 2: //Validar si posee el tipo de vencimiento 2, PoseeInicioDesembolso
                            poseeInicioDesembolso = true;
                            poseeInicioDesembolsoVencimiento = fechaVencimiento != null;
                            fechaVencimientoInicioDesembolsos = (java.sql.Timestamp) fechaVencimiento; // User story 1958.
                            logger.warning("fechaVencimientoInicioDesembolsos: " + fechaVencimientoInicioDesembolsos);
                            break;
                        case 3: //Validar si posee el tipo de vencimiento 3, DesembolsoTotalidadRecursos
                            poseeDesembolsoTotalidadRecursos = true;
                            poseeDesembolsoTotalidadRecursosVencimiento = fechaVencimiento != null;
                            fechaVencimientoTotalidadRecursos = (java.sql.Timestamp) fechaVencimiento; // User story 1684.
                            logger.warning("fechaVencimientoTotalidadRecursos: " + fechaVencimientoTotalidadRecursos);
                            break;
                        default:
                            break;
                    }
                    
                    if(poseeDesembolsoTotalidadRecursos && poseeInicioDesembolso)
                    {
                        hasNext = false;
                    }                  
                }
            }
        }
        boolean vVerfiicar = true;
        //De esta sección hacia abajo se envian como es datos correctos dependiendo si es validarLineaCredito el llamadoDirecto o validarLineasCredito
        if(!poseeDesembolsoTotalidadRecursos)
        {
            esDatosCorrectos = !actualizaFlagLineasValidas;
            JSFUtils.addFacesErrorMessage("Debe agregar un vencimiento de plazo del tipo \"Desembolso de la totalidad de los recursos\"." + textoLinea);
            vVerfiicar = false;
        }
        else
        {
            if(!poseeDesembolsoTotalidadRecursosVencimiento)
            {
                esDatosCorrectos = !actualizaFlagLineasValidas;
                JSFUtils.addFacesErrorMessage("Debe agregar la fecha de vencimiento para el plazo del tipo \"Desembolso de la totalidad de los recursos\"." + textoLinea);
                vVerfiicar = false;
            }
        }
        
        if(!poseeInicioDesembolso)
        {
            esDatosCorrectos = !actualizaFlagLineasValidas;
            JSFUtils.addFacesErrorMessage("Debe agregar un vencimiento de plazo del tipo \"Inicio de Desembolso\"." + textoLinea);
            vVerfiicar = false;
        }
        else
        {
            if(!poseeInicioDesembolsoVencimiento)
            {
                esDatosCorrectos = !actualizaFlagLineasValidas;
                JSFUtils.addFacesErrorMessage("Debe agregar la fecha de vencimiento para el plazo del tipo \"Inicio de Desembolso\"." + textoLinea);
                vVerfiicar = false;
            }
        }
        
        // Realizar sólo cuando se estén validando todas las líneas y no una sola. — FCP, 9/mar/2020.
        if(actualizaFlagLineasValidas) {
            // Validación de user story 1684: "No permitir finalizar la tarea si la Fecha de Vencimiento en Datos de la Línea 
            // es menor a la Fecha máxima para Desembolsar la Totalidad de los recursos".
            if((fechaVencimientoLineaCredito != null) && (fechaVencimientoTotalidadRecursos != null) && 
               (fechaVencimientoLineaCredito.compareTo(fechaVencimientoTotalidadRecursos) < 0) ) {
               
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("La Fecha de vencimiento en datos de la Línea de crédito es menor a la " + 
                                              "Fecha máxima para desembolsar la totalidad de los recursos.");
            }

            // Validación de user story 1958: "Validar que la Fecha para Iniciar Desembolso sea menor o igual que 
            // la Fecha Máxima para Totalidad de Recursos".
            if( (fechaVencimientoInicioDesembolsos != null) && (fechaVencimientoTotalidadRecursos != null) && 
                (fechaVencimientoInicioDesembolsos.compareTo(fechaVencimientoTotalidadRecursos) > 0) ) {
                
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("La Fecha vencimiento de Inicio de Desembolsos debe ser menor o igual a la " +
                                              "Fecha máxima para desembolsar la totalidad de los recursos.");
            }
        }
        
        /*
        if(!vVerfiicar)
        {
            esDatosCorrectos = false;
            logger.log(ADFLogger.WARNING, "esDatosCorrectos : " + esDatosCorrectos.toString());
        }
        */
        }
        else
        {
                logger.log(ADFLogger.WARNING, "false validarDatosLineaCredito Valor idFlexLinea" + idFlexLinea);
        }       
        return esDatosCorrectos;
    }
    
    public void setPopupAgregarVencimiento(RichPopup popupAgregarVencimiento) {
        this.popupAgregarVencimiento = popupAgregarVencimiento;
    }

    public RichPopup getPopupAgregarVencimiento() {
        return popupAgregarVencimiento;
    }

    public void setPopupAgregarComision(RichPopup popupAgregarComision) {
        this.popupAgregarComision = popupAgregarComision;
    }

    public RichPopup getPopupAgregarComision() {
        return popupAgregarComision;
    }
    
    public void setOtInitForm(RichOutputText otInitForm) {
        this.otInitForm = otInitForm;
    }

    public RichOutputText getOtInitForm() {
        
        logger.log(ADFLogger.WARNING, "Inside getOtInitForm()");
        // Inicializamos datos de la línea de crédito seleccionada en la tabla DatosLineaCreditoVO
        consultarLineaCredito();
        
        return otInitForm;
    }
    
    /* SCRUM_FenixII_BCIEFNXII-3145
     * Post-PROD-Formalización-KMBZ-3794-Campos vacíos en Registrar Línea de Crédito para Ayuda de Emergencia
     * Gabriel Niño Rosales
     * 18/03/2016
     */

    public void agregarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside agregarLineaCreditoActionListener");
        RichPopup.PopupHints popupHints = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        // Ejecutar metodo crear row
        operationBinding = bindings.getOperationBinding("crearUnicoRowAgregarLineaCredito");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        // Abrimos popupAgregarLineaCredito
        popupHints = new RichPopup.PopupHints();
        getPopupAgregarLineaCredito().show(popupHints);
    }

    public void cancelarAgregarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside cancelarAgregarLineaCreditoActionListener");
        // Cerramos popupAgregarLineaCredito
        getPopupAgregarLineaCredito().hide();
        
        //Elimina el registro temporal de la VO que sirvio para trabajar el popup
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("DeleteAgregarLineaCredito");
        if(operationBinding != null){
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                logger.severe("Error al eliminar el Row temporal de Linea de Credito");    
            }
        }
        
        //Refresca tabla de Lineas de credito.
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaDatosLineaCredUIC());
    }

    public void aceptarAgregarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.warning("Inside aceptarAgregarLineaCreditoActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String numeroLineaCredito = null;
        String descripcionLinea = null;
        BigDecimal montoLinea = null;
        Long idContrato = null;
        
        if(null != JSFUtils.resolveExpression("#{bindings.IdContrato1.inputValue}")){
            Integer idContratoLong = (Integer)JSFUtils.resolveExpression("#{bindings.IdContrato1.inputValue}");
            idContrato =idContratoLong.longValue();
        } else {
            logger.warning("IdContrato1 se recupero nulo.");
        }
        //Recuperar los valores de AgregarLineaCreditoVOIterator
        if(null != JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito1.inputValue}")){
            numeroLineaCredito=(String)JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito1.inputValue}");
        }
        if(null != JSFUtils.resolveExpression("#{bindings.DescripcionLinea.inputValue}")){
            descripcionLinea=(String)JSFUtils.resolveExpression("#{bindings.DescripcionLinea.inputValue}");
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Id Tipo moneda
        String sIdTipoMonedaMontoLinea = null;
        
        try {
            sIdTipoMonedaMontoLinea = (String)JSFUtils.resolveExpression("#{bindings.MonedaLinea11.inputValue}");
        } catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error no se puede castear el valor MontoLinea1");
        }
        
        Integer bcIdTipoMonedaMontoLinea = null;
        
        try {
            if (null == sIdTipoMonedaMontoLinea) {
                bcIdTipoMonedaMontoLinea = (Integer)JSFUtils.resolveExpression("#{bindings.MonedaLinea11.inputValue}");
                bcIdTipoMonedaMontoLinea = bcIdTipoMonedaMontoLinea + 1;
                //sIdTipoMonedaMontoLinea = bcIdTipoMonedaMontoLinea.toString();
            }
        } catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error no se puede castear el valor id Tipo Moneda MontoLinea1");
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Monto Linea
        String sMontoLinea = null;
        
        try {
            sMontoLinea = (String)JSFUtils.resolveExpression("#{bindings.MontoLinea1.inputValue}");
        } catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error no se puede castear el valor MontoLinea1");
        }
        
        try {
            if (null == sMontoLinea) {
                BigDecimal bcMontoLinea = (BigDecimal)JSFUtils.resolveExpression("#{bindings.MontoLinea1.inputValue}");
                sMontoLinea = bcMontoLinea.toString();
            }
        } catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error no se puede castear el valor MontoLinea1");
        }
        
        if(null != sMontoLinea){
            try {
                logger.info("+++++++++++++ sMontoLineaCredito: " + sMontoLinea);
                montoLinea = NumbersUtil.stringToBigDecimal(FormalizacionConstants.FORMATO_MONTO_LINEA, sMontoLinea);
            } catch (ParseException e) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage("No es posible convertir el monto de l\u00EDnea".concat(operationBinding.getErrors().toString()));
            }
        }
        
        String idOperacion = null;
        Long lnIdOperacion = null;
        try{
            idOperacion = (String)JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");
            if(idOperacion != null){
                lnIdOperacion = Long.valueOf(idOperacion);
            }else{
                logger.severe("El Id de Operacion es NULL");
            }
        }catch(Exception e){
            logger.severe("Error al obtener el Id de Operacion", e);
        }
        
        //Modificacion que atiende incidencia FNXII-4537
        //operationBinding = bindings.getOperationBinding("crearLineaCreditoSP");
        operationBinding = bindings.getOperationBinding("crearLineaCreditoWS");
        
        //Se envian los parametros del metodo crearTramoFormalizar
        operationBinding.getParamsMap().put("numeroLineaCredito", numeroLineaCredito);
        operationBinding.getParamsMap().put("descripcionLinea", descripcionLinea);
        operationBinding.getParamsMap().put("montoLinea", montoLinea);
        operationBinding.getParamsMap().put("idContrato", idContrato);
        operationBinding.getParamsMap().put("idOperacion", lnIdOperacion);
        
		operationBinding.getParamsMap().put("idTipoMonedaMontoLinea", bcIdTipoMonedaMontoLinea);
		
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            //JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            logger.severe("Muestra mensaje de error al crear la linea de credito");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_CREAR_LINEA_CREDITO"));
        }else{
            Boolean result = (Boolean)operationBinding.getResult();
            if(result != null &&
               result){
                // Muestra mensaje de exito
                logger.warning("Muestra mensaje de exito al crear la linea de credito");
                JSFUtils.addFacesInformationMessage(getStringFromBundle("EXITO_CREAR_LINEA_CREDITO"));
            }else{
                logger.severe("Muestra mensaje de error al crear la linea de credito");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_CREAR_LINEA_CREDITO"));
            }
            // Actualizar los valores de linea de credito
            this.actualizarLineaCredito();
            // Cierra el popupAgregarTramo
            getTBILineaCreditosEstado(null,numeroLineaCredito,"I");
            getPopupAgregarLineaCredito().hide();
        }
        
        //Elimina el registro temporal de la VO que sirvio para trabajar el popup
        operationBinding = null;
        operationBinding = bindings.getOperationBinding("DeleteAgregarLineaCredito");
        if(operationBinding != null){
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                logger.severe("Error al eliminar el Row temporal de Linea de Credito");    
            }
        }
        
        //Refresca tabla de Lineas de credito.
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaDatosLineaCredUIC());
        
        
        //Cambia el estado del flag de guardado valido a false
        setLineasValidasGuardadas("N");
        
        logger.warning("Finaliza aceptarAgregarLineaCreditoActionListener");
    }

    @SuppressWarnings("unchecked")
    public void modificarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside modificarLineaCreditoActionListener");
        RichPopup.PopupHints popupHints = null;
        
        // Establecemos los valores de la linea de credito seleccionada
        mappingModificarLineaCredito();
        
        /*
        // Ejecutar metodo crear row
        operationBinding = bindings.getOperationBinding("crearRowAgregarLineaCredito");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        //Recuperar valores de DatosLineaCreditoVO currentRow
        //Recuperar Id
        if(JSFUtils.resolveExpression("#{bindings.Id3.inputValue}") != null)
        {   
            Long idLineaCredito=(Long)JSFUtils.resolveExpression("#{bindings.Id3.inputValue}");
            //Asignar el valor al inputValue del dialog
            JSFUtils.setExpressionValue("#{bindings.Id2.inputValue}",idLineaCredito);
        }
        //Recuperar NumeroLineaCredito2
        if(JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito2.inputValue}") != null)
        {   
            String numeroLineaCredito=(String)JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito2.inputValue}");
            //Asignar el valor al inputValue del dialog
            JSFUtils.setExpressionValue("#{bindings.NumeroLineaCredito1.inputValue}",numeroLineaCredito);
        }
        //Recuperar MontoLinea2
        String sMontoLineaCredito = (String)JSFUtils.resolveExpression("#{bindings.MontoLinea2.inputValue}");
        if(sMontoLineaCredito != null) {
            try {
                logger.info("+++++++++++++ sMontoLineaCredito: " + sMontoLineaCredito);
                BigDecimal montoLineaCredito = null;
                String strNumber = ADFUtils.getBoundAttributeValue("MontoLinea2").toString();
                montoLineaCredito = BigDecimal.valueOf(NumberFormat.getInstance().parse(strNumber).doubleValue());
                //Asignar el valor al inputValue del dialog
                JSFUtils.setExpressionValue("#{bindings.MontoLinea1.inputValue}",montoLineaCredito);
            } catch (ParseException e) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage("No es posible convertir el monto de l\u00EDnea");
            }
        }
        //Recuperar Descripcion1
        if(JSFUtils.resolveExpression("#{bindings.Descripcion1.inputValue}") != null)
        {   
            String descripcionLineaCredito=(String)JSFUtils.resolveExpression("#{bindings.Descripcion1.inputValue}");
            //Asignar el valor al inputValue del dialog
            JSFUtils.setExpressionValue("#{bindings.DescripcionLinea.inputValue}",descripcionLineaCredito);
        }
        */
        
        // Abrimos popupAgregarLineaCredito
        popupHints = new RichPopup.PopupHints();
        getPopupModificarLineaCredito().show(popupHints);
        
        if(getFormAgregarEditarLineaUIC() != null){
            logger.warning("Refresca Formulario del Popup");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getFormAgregarEditarLineaUIC());    
        }
        
        if(getPopupModificarLineaCredito() != null){
            logger.warning("Refresca Popup");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupModificarLineaCredito());    
        }
    }
    
    public void cancelarModificarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside cancelarModificarLineaCreditoActionListener");
        // Cerramos PopupModificarLineaCredito
        //getPopupModificarLineaCredito().hide();
        
        //Elimina el registro temporal de la VO que sirvio para trabajar el popup
//        BindingContainer bindings = ADFUtils.getBindingContainer();
//        OperationBinding operationBinding = null;
//        operationBinding = bindings.getOperationBinding("DeleteAgregarLineaCredito");
//        if(operationBinding != null){
//            operationBinding.execute();
//            if(!operationBinding.getErrors().isEmpty()){
//                logger.severe("Error al eliminar el Row temporal de Linea de Credito");    
//            }
//        }
        
        //Regresamos VO a su estado anterior
        mappingModificarLineaCredito();
        
        //Refresca tabla de Lineas de credito.
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaDatosLineaCredUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupModificarLineaCredito());
        
        // Cerramos PopupModificarLineaCredito
        getPopupModificarLineaCredito().cancel();
    }
    
    public void mappingModificarLineaCredito() {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        operationBinding = bindings.getOperationBinding("CreateInsertAgregarLineaCredito");
        if(operationBinding != null){
            
            Long idLinea = null;
            if(ADFUtils.getBoundAttributeValue("Id3") != null){
                try{
                    idLinea = Long.valueOf(ADFUtils.getBoundAttributeValue("Id3").toString());    
                }catch(Exception e){
                    logger.severe("Error al obtener el Id de la Linea de Credito");
                }
            }
            
            String strNumLinea = null;
            if(ADFUtils.getBoundAttributeValue("NumeroLineaCredito2") != null){
                strNumLinea = ADFUtils.getBoundAttributeValue("NumeroLineaCredito2").toString();
            }
            
            String strDesc = null;
            if(ADFUtils.getBoundAttributeValue("Descripcion1") != null){
                strDesc = ADFUtils.getBoundAttributeValue("Descripcion1").toString();
            }
            
            BigDecimal bigMonto = null;
            if(ADFUtils.getBoundAttributeValue("MontoLinea2") != null){
                String strNumber = ADFUtils.getBoundAttributeValue("MontoLinea2").toString();
                logger.warning("Monto obtenido de la linea: " + strNumber);
                try{
                    //bigMonto = (BigDecimal)ADFUtils.getBoundAttributeValue("MontoLinea2");
                   bigMonto = BigDecimal.valueOf(NumberFormat.getInstance().parse(strNumber).doubleValue());   
                   logger.warning("Monto obtenido de la conversion: " + bigMonto);
                }catch(Exception e){
                    logger.severe("Error al obtener y convertir monto de la linea de credito");
                }
            }
            
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }else{
                
                ADFUtils.setBoundAttributeValue("NumeroLineaCredito1", strNumLinea);
                ADFUtils.setBoundAttributeValue("DescripcionLinea", strDesc);
                ADFUtils.setBoundAttributeValue("MontoLinea1", bigMonto);
                ADFUtils.setBoundAttributeValue("Id2", idLinea);
            
                logger.warning("Creacion y asignacion del Row para Editar la linea concluida con exito");
            }
        }
    }

    public void aceptarModificarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside aceptarModificarLineaCreditoActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idLineaCredito = null;
        String numeroLineaCredito = null;
        String descripcionLineaCredito = null;
        BigDecimal montoLineaCredito = null; 
        //idLineaCredito, recuperar el valor dentro del popup
        if(null != JSFUtils.resolveExpression("#{bindings.Id2.inputValue}")){
            idLineaCredito=(Long)JSFUtils.resolveExpression("#{bindings.Id2.inputValue}");
        }
        //numeroLineaCredito, recuperar el valor dentro del popup
        if(null != JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito1.inputValue}")){
            numeroLineaCredito=(String)JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito1.inputValue}");
        }
        //descripcionLineaCredito, recupera el valor dentro del popup
        if(null != JSFUtils.resolveExpression("#{bindings.DescripcionLinea.inputValue}")){
            descripcionLineaCredito=(String)JSFUtils.resolveExpression("#{bindings.DescripcionLinea.inputValue}");
        }
        //montoLineaCredito, recuperar el valor dentro del popup
        if(null != JSFUtils.resolveExpression("#{bindings.MontoLinea1.inputValue}")){
            //montoLineaCredito=(BigDecimal)JSFUtils.resolveExpression("#{bindings.MontoLinea1.inputValue}");
            
            double dbNumber = 0;
            String strNumber = null;
            strNumber = ADFUtils.getBoundAttributeValue("MontoLinea1").toString();
            
            logger.warning("Valor de Monto Linea: " + strNumber);
            
            try {
                dbNumber = NumberFormat.getInstance().parse(strNumber).doubleValue();
                montoLineaCredito = BigDecimal.valueOf(dbNumber);
            } catch (ParseException e) {
                logger.severe("Error al convertir Monto Linea en Double number");
            }
        }
        
        Long idContrato = null;
        if(ADFUtils.getBoundAttributeValue("IdContrato1") != null){
            try{
                idContrato = new Long(ADFUtils.getBoundAttributeValue("IdContrato1").toString());
            }catch(Exception e){
                logger.severe("Error al leer el atributo de Id Contrato");
            }
        }
        
        Boolean lineaGlobalIfi = null;
        if(ADFUtils.getBoundAttributeValue("LineaGlobalIfi") != null){
            try{
                lineaGlobalIfi = (Boolean)ADFUtils.getBoundAttributeValue("LineaGlobalIfi");
            }catch(Exception e){
                logger.severe("Error al leer el atributo de LineaGlobalIfi");
            }
        }
        
        if (null != lineaGlobalIfi && lineaGlobalIfi.equals(Boolean.FALSE)) {
            operationBinding = bindings.getOperationBinding("modificarLineaCreditoPorContrato");
            //Se envian los parametros del metodo modificarLineaCredito
            operationBinding.getParamsMap().put("idContrato", idContrato); 
            operationBinding.getParamsMap().put("idLineaCredito", idLineaCredito); 
            operationBinding.getParamsMap().put("numeroLineaCredito", numeroLineaCredito);
            operationBinding.getParamsMap().put("descripcionLineaCredito", descripcionLineaCredito);
            operationBinding.getParamsMap().put("montoLineaCredito", montoLineaCredito);
            operationBinding.execute();
        } else {
            operationBinding = bindings.getOperationBinding("modificarLineaCreditoPorIdLineaCredito");
            //Se envian los parametros del metodo modificarLineaCredito
            operationBinding.getParamsMap().put("idLineaCredito", idLineaCredito); 
            operationBinding.getParamsMap().put("numeroLineaCredito", numeroLineaCredito);
            operationBinding.getParamsMap().put("descripcionLineaCredito", descripcionLineaCredito);
            operationBinding.getParamsMap().put("montoLineaCredito", montoLineaCredito);
            operationBinding.execute();
        }
        
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else{
            
            //Actualizar linea de credito
            this.actualizarLineaCredito();
            
            // Cerramos PopupModificarLineaCredito
            getPopupModificarLineaCredito().hide();
            
            getTBILineaCreditosEstado(null,numeroLineaCredito,"U");
            //Mensaje de exito
            JSFUtils.addFacesInformationMessage("Se modifico con \u00E9xito.");
        }
        
        //Elimina el registro temporal de la VO que sirvio para trabajar el popup
        operationBinding = null;
        operationBinding = bindings.getOperationBinding("DeleteAgregarLineaCredito");
        if(operationBinding != null){
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                logger.severe("Error al eliminar el Row temporal de Linea de Credito");    
            }
        }
        
        //Refresca tabla de Lineas de credito.
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaDatosLineaCredUIC());
    }

    public void eliminarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside eliminarLineaCreditoActionListener");
        logger.warning("Entrando en eliminarLineaCreditoActionListener");
        RichPopup.PopupHints popupHints = null;
        // Abrimos popupAgregarLineaCredito
        popupHints = new RichPopup.PopupHints();
        getPopupEliminarLineaCredito().show(popupHints);
    }
    
    public void cancelarEliminarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside cancelarEliminarLineaCreditoActionListener");
        logger.warning("Entrando en cancelarEliminarLineaCreditoActionListener");
        // Cerramos PopupEliminarLineaCredito
        getPopupEliminarLineaCredito().hide();
    }

    public void aceptarEliminarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside aceptarEliminarLineaCreditoActionListener");
        logger.warning("Entrando en aceptarEliminarLineaCreditoActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idLineaCredito = null;
        //Recuperar el id seleccionado en la tabla Id3 que se va eliminar
        if(null != JSFUtils.resolveExpression("#{bindings.Id3.inputValue}")){
            idLineaCredito=(Long)JSFUtils.resolveExpression("#{bindings.Id3.inputValue}");
        } else {
            logger.warning("idLineaCredito es nulo.");
        }

        operationBinding = bindings.getOperationBinding("eliminarLineaCredito");
        //Se envian los parametros del metodo modificarLineaCredito
        operationBinding.getParamsMap().put("idLineaCredito", idLineaCredito);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else{
            // Cerramos PopupEliminarLineaCredito
            getPopupEliminarLineaCredito().hide();
            //Actualizar linea de credito
            this.actualizarLineaCredito();
            
            getTBILineaCreditosEstado(idLineaCredito, null,"D");
            //Mensaje de exito
            JSFUtils.addFacesInformationMessage("Se elimino con éxito.");
        }
        
        setLineasValidasGuardadas("N");
        
    }
    
    public void actualizarLineaCredito(){
        logger.log(ADFLogger.WARNING, "Inside actualizarLineaCredito");
        
        Boolean solicitudContratacion = Boolean.FALSE;
        
        //Recuperar el solicitudContratacion de la entrada del taskflow 
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.solicitudContratacion}")){
            solicitudContratacion =(Boolean)JSFUtils.resolveExpression("#{pageFlowScope.solicitudContratacion}");
        }
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        // Limpiar la tabla DatosLineaCreditoVO
        operationBinding = bindings.getOperationBinding("limpiarDatosLineaCredito");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        // Actualizamos datos del encabezado y Líneas de crédito (se cargan todos los datos de nuevo)
        operationBinding = bindings.getOperationBinding("consultarLineaCredito");
        operationBinding.getParamsMap().put("idOperacion", 
                                            JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
        operationBinding.getParamsMap().put("instanciaProceso", 
                                            JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}"));
        operationBinding.getParamsMap().put("idProducto", 
                                            JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}"));
        operationBinding.getParamsMap().put("solicitudContratacion", solicitudContratacion);
        logger.warning("instanciaProcesoR2: " + JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}"));
        //operationBinding.getParamsMap().put("instanciaProceso", "580001");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        this.consultarLineaCredito();
    }
    
    public void setPopupAgregarLineaCredito(RichPopup popupAgregarLineaCredito) {
        this.popupAgregarLineaCredito = popupAgregarLineaCredito;
    }

    public RichPopup getPopupAgregarLineaCredito() {
        return popupAgregarLineaCredito;
    }

    public void setPopupModificarLineaCredito(RichPopup popupModificarLineaCredito) {
        this.popupModificarLineaCredito = popupModificarLineaCredito;
    }

    public RichPopup getPopupModificarLineaCredito() {
        return popupModificarLineaCredito;
    }

    public void setPopupEliminarLineaCredito(RichPopup popupEliminarLineaCredito) {
        this.popupEliminarLineaCredito = popupEliminarLineaCredito;
    }

    public RichPopup getPopupEliminarLineaCredito() {
        return popupEliminarLineaCredito;
    }

    @SuppressWarnings("unchecked")
    public void obtenerFechaInicio(ValueChangeEvent valueChangeEvent) {
        
        if(null != valueChangeEvent){
            
            logger.warning("valor de tipo fecha de inicio: " + valueChangeEvent.getNewValue());  
            
            Integer tipofechaInicio = null;
            Long idOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            
            //Obliga actualizar el modelo
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            
            if(null != valueChangeEvent.getNewValue()){
                
                //Codigo Noobie deshabilitado
                /*
                switch(valueChangeEvent.getNewValue().toString()){
                case "Aprobación":
                    tipofechaInicio = 8;
                    break;
                case "Escrituración":
                    tipofechaInicio = 9;
                    break;
                case "Primer desembolso":
                    tipofechaInicio = 10;
                    break;
                case "Notificación al Cliente de consecución de recursos":
                    tipofechaInicio = 12;
                    break;
                case "Fecha de vigencia":
                    tipofechaInicio = 13;
                    break;
                default: break;
                }
                */
                
                Long lngIdTipoFechaInicio = null;
                try{
                    lngIdTipoFechaInicio = (Long) ADFUtils.getBoundAttributeValue("IdTipoDeFecha");
                    if(lngIdTipoFechaInicio != null){
                        tipofechaInicio = new Integer(lngIdTipoFechaInicio.intValue());
                    }else{
                        logger.severe("El Id de Fecha Inicio es NULL");
                    }
                }catch(Exception e){
                    logger.severe("No se pudo obtener el Id de Fecha Inicio de Vencimiento Plazo");
                }
                
                if(null != tipofechaInicio && null != idOperacion){
                    BindingContainer bindings = ADFUtils.getBindingContainer();
                    OperationBinding operationBinding = bindings.getOperationBinding("obtenerTipoFechaInicio");
                    operationBinding.getParamsMap().put("idFechaInicio", tipofechaInicio);
                    operationBinding.getParamsMap().put("idOperacion", idOperacion);
                    operationBinding.execute();
                    
                    if(!operationBinding.getErrors().isEmpty()){
                        logger.warning("Error al obtener la fecha de inicio");
                    } else {
                        
                        AttributeBinding fechaInicioBinding;
                        fechaInicioBinding = ADFUtils.findControlBinding("FechaInicio");
                        
                        if(null != operationBinding.getResult()){
                            Timestamp fechaInicio = Timestamp.valueOf(operationBinding.getResult().toString());
                            logger.warning("Fecha obtenida del modelo: " + fechaInicio);
                            fechaInicioBinding.setInputValue(fechaInicio);
                        }else{
                            fechaInicioBinding.setInputValue(null);
                        }
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaInicioUIC());
                    }
                }
            }
        }
    }

    public void setFechaInicioUIC(RichInputDate fechaInicioUIC) {
        this.fechaInicioUIC = fechaInicioUIC;
    }

    public RichInputDate getFechaInicioUIC() {
        return fechaInicioUIC;
    }

    public void setComisionFrecuenciaUIC(RichInputText comisionFrecuenciaUIC) {
        this.comisionFrecuenciaUIC = comisionFrecuenciaUIC;
    }

    public RichInputText getComisionFrecuenciaUIC() {
        return comisionFrecuenciaUIC;
    }

    public void tipoFrecuenciaChangeListener(ValueChangeEvent valueChangeEvent) {
        
        if(valueChangeEvent == null){
            return;
        }
        
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        String strAlVenciValue = "Al Vencimiento";
        String value = null;
        try{
            value = (String) ADFUtils.getBoundAttributeValue("TipoFrecuencia");
        }catch(Exception e){
            logger.warning("Error al obtener el valor de Tipo Frecuencia");
        }
        
        String strFrecuencia = null;
        try{
            strFrecuencia = (String) ADFUtils.getBoundAttributeValue("Frecuencia");    
        }catch(Exception e){
            logger.warning("Error al obtener el valor de Frecuencia");
        }
        
        if(value != null &&
           strAlVenciValue.equals(value) &&
           !"".equals(strFrecuencia)){
            ADFUtils.setBoundAttributeValue("Frecuencia", "1");
            AdfFacesContext.getCurrentInstance().addPartialTarget(comisionFrecuenciaUIC);
        }
    }

    public void setFormAgregarEditarLineaUIC(RichPanelFormLayout formAgregarEditarLineaUIC) {
        this.formAgregarEditarLineaUIC = formAgregarEditarLineaUIC;
    }

    public RichPanelFormLayout getFormAgregarEditarLineaUIC() {
        return formAgregarEditarLineaUIC;
    }

    public void setTablaDatosLineaCredUIC(RichTable tablaDatosLineaCredUIC) {
        this.tablaDatosLineaCredUIC = tablaDatosLineaCredUIC;
    }

    public RichTable getTablaDatosLineaCredUIC() {
        return tablaDatosLineaCredUIC;
    }

    public void setFormDatosLineaCreditoUIC(RichPanelGroupLayout formDatosLineaCreditoUIC) {
        this.formDatosLineaCreditoUIC = formDatosLineaCreditoUIC;
    }

    public RichPanelGroupLayout getFormDatosLineaCreditoUIC() {
        return formDatosLineaCreditoUIC;
    }
    
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }

    public void isGuardadoChangeListener(ValueChangeEvent valueChangeEvent) {
        // Si hay un cambio en los valores se invalida el estado de las lineas guardadas
        setLineasValidasGuardadas("N");
    }

    public BindingContainer getBindings() {
     return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
	
	public void montoLineavalueChangeListener(ValueChangeEvent valueChangeEvent){
        logger.warning("Inside montoLineavalueChangeListener: " + valueChangeEvent.getComponent().getId());
        
    }
    
    public void montoLinea1valueChangeListener(ValueChangeEvent valueChangeEvent){
        logger.warning("Inside montoLinea1valueChangeListener: " + valueChangeEvent.getComponent().getId());
	}
	
    public void seleccionaOpcionMora(ValueChangeEvent valueChangeEvent) {
            System.out.println("presiona opcion mora");
            String opcion1 = "Interés Ordinario + Spread de mora";
            String opcion2 = "Interés Ordinario * Factor";
            String opcion3 = "Tasa fija";
            String opcion4 = "Tasa variable";
            String opcionMora = null;
             BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            opcionMora = JSFUtils.resolveExpression("#{bindings.TcaTipoMoraVO1.inputValue}").toString();
            System.out.println("opcion seleccionada es- " + opcionMora);
            if (opcionMora != null) {
                if (opcionMora.equals(opcion1)||opcionMora.equals(opcion2)) {
                    AttributeBinding ValorTasa = (AttributeBinding)bindings.getControlBinding("ValorTasa");
                    ValorTasa.setInputValue("");
                    AttributeBinding CodigoTasaReferencia = (AttributeBinding)bindings.getControlBinding("CodigoTasaReferencia");
                    CodigoTasaReferencia.setInputValue("");
                    AttributeBinding Frecuencia1 = (AttributeBinding)bindings.getControlBinding("Frecuencia1");
                    Frecuencia1.setInputValue("");
                    AttributeBinding SpreadTasa = (AttributeBinding)bindings.getControlBinding("SpreadTasa");
                    SpreadTasa.setInputValue("");
                } else if (opcionMora.equals(opcion3)) {
                    AttributeBinding CodigoTasaReferencia = (AttributeBinding)bindings.getControlBinding("CodigoTasaReferencia");
                    CodigoTasaReferencia.setInputValue("");
                    AttributeBinding Frecuencia1 = (AttributeBinding)bindings.getControlBinding("Frecuencia1");
                    Frecuencia1.setInputValue("");
                    AttributeBinding SpreadTasa = (AttributeBinding)bindings.getControlBinding("SpreadTasa");
                    SpreadTasa.setInputValue("");
                    AttributeBinding ValorTasa = (AttributeBinding)bindings.getControlBinding("ValorTasa");
                    ValorTasa.setInputValue("");
                } else if (opcionMora.equals(opcion4)) {
                    AttributeBinding ValorTasa = (AttributeBinding)bindings.getControlBinding("ValorTasa");
                    ValorTasa.setInputValue("");
                    AttributeBinding CodigoTasaReferencia = (AttributeBinding)bindings.getControlBinding("CodigoTasaReferencia");
                    CodigoTasaReferencia.setInputValue("");
                    AttributeBinding Frecuencia1 = (AttributeBinding)bindings.getControlBinding("Frecuencia1");
                    Frecuencia1.setInputValue("");
                    AttributeBinding SpreadTasa = (AttributeBinding)bindings.getControlBinding("SpreadTasa");
                    SpreadTasa.setInputValue("");
                }
            }
        }
    
    public List onSuggest(String string) {
        System.out.println("entrando a sugerencias "+string);
        ArrayList<SelectItem> selectItems = new ArrayList<SelectItem>();
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        System.out.println("asignando valor "+string +" al set");
        OperationBinding setVariable = (OperationBinding) bindings.get("setbuscadescripcionTasa");
        setVariable.getParamsMap().put("value", string);
        setVariable.execute();  
        System.out.println("valor asignado");
        JUCtrlHierBinding hierBinding =(JUCtrlHierBinding) bindings.get("CodigoTasaReferencia1"); 
        hierBinding.executeQuery();
        System.out.println("llego a binding");
        List<JUCtrlValueBindingRef> displayDataList = hierBinding.getRangeSet();
        System.out.println("Entrando a las coincidencias ---");
        for (JUCtrlValueBindingRef displayData : displayDataList){
        Row rw = displayData.getRow();
        SelectItem a = new SelectItem("","Escribe...","",true);
            selectItems.add(a);
        SelectItem si = new SelectItem();
        String valor=(String)rw.getAttribute("DescripcionTasa");
        System.out.println("valor: "+valor!=null?valor:"nada");
            si.setValue(valor);
            si.setLabel(valor);
            si.setDescription(valor);
        selectItems.add(si);
        }
        System.out.println("total de conincidencias "+selectItems.size());
        return selectItems;
    }
    public void vaciaCantidadDecimales(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "OPCION DE REDONDEO: ");
            String opcion1 = "No Aplica";
            String rounding=null;
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            rounding = JSFUtils.resolveExpression("#{bindings.TcaTipoRedondeoVO1.inputValue}").toString();
            logger.log(ADFLogger.WARNING, "ROUNDING --: "+rounding);
            if(rounding.equals(opcion1)){
            logger.log(ADFLogger.WARNING, "Dentro de if: ");
            BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
            AttributeBinding cantidadDecimales = (AttributeBinding)bindings.getControlBinding("CantidadDecimales");
            cantidadDecimales.setInputValue("");
        }
    }

    private String getTBILineaCreditosEstado( 
            Long pIdLineaCredito,
            String pNumeroLineaCredito,
            String pEstadoLineaCredito
    ){ 
        try{
            
        
        logger.log(ADFLogger.WARNING, "Inicio getTBILineaCreditosEstado");
        Integer idContrato = JSFUtils.resolveExpression("#{bindings.IdContrato1.inputValue}") != null ? (Integer)JSFUtils.resolveExpression("#{bindings.IdContrato1.inputValue}") : 0;
        logger.log(ADFLogger.WARNING, "idContrato: " + idContrato);
        Long idContratoLong = Long.valueOf(idContrato);
        logger.log(ADFLogger.WARNING, "idContratoLong: " + idContratoLong);
        Long idLineaCredito = pIdLineaCredito;
        logger.log(ADFLogger.WARNING, "idLineaCredito: " + idLineaCredito);
        String idTareaBpm = JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}") != null ? (String)JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}") : "0";
        logger.log(ADFLogger.WARNING, "idTareaBpm: " + idTareaBpm);
        Long idTareaBpmLong = Long.valueOf(idTareaBpm);
        logger.log(ADFLogger.WARNING, "idTareaBpmLong: " + idTareaBpmLong);
        String instanciaProceso = JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}") != null ? (String)JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}") : "";
        logger.log(ADFLogger.WARNING, "idTareaBpmLong: " + idTareaBpmLong);
        String estadoLineaCredito = pEstadoLineaCredito;
        logger.log(ADFLogger.WARNING, "estadoLineaCredito: " + estadoLineaCredito);
        String usuario = "usuario";
        logger.log(ADFLogger.WARNING, "usuario: " + usuario);
        String numeroLineaCredito = pNumeroLineaCredito;
        logger.log(ADFLogger.WARNING, "numeroLineaCredito: " + numeroLineaCredito);

        logger.log(ADFLogger.WARNING, "Parametros de MergeTBILineaCredito");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("MergeTBILineaCredito");
        operationBinding.getParamsMap().put("pIdContrato", idContratoLong);
        operationBinding.getParamsMap().put("pIdLineaCredito", pIdLineaCredito);
        operationBinding.getParamsMap().put("pIdTareaBpm", idTareaBpm);
        operationBinding.getParamsMap().put("pInstanciaProceso", instanciaProceso);
        operationBinding.getParamsMap().put("pEstadoLineaCredito", estadoLineaCredito);
        operationBinding.getParamsMap().put("pUsuario", usuario);
        operationBinding.getParamsMap().put("pBanEstatus", 1);
        operationBinding.getParamsMap().put("pNumeroLineaCredito", pNumeroLineaCredito);

        logger.log(ADFLogger.WARNING, "Ejecicion");
        operationBinding.execute();
            
}
        catch(Exception exp){
                logger.log(ADFLogger.WARNING, "Error getTBILineaCreditosEstado> "+exp);
            }
        logger.log(ADFLogger.WARNING, "Fin getTBILineaCreditosEstado");
        return "true";
    }

}
