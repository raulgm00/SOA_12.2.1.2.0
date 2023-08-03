package org.bcie.fenix.view.gestordesembolsos;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import java.util.ResourceBundle;

import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.MethodExpression;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.CollectionModel;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class AsignacionRecursosActionBean extends FenixActionBean {

    private static ADFLogger logger = null;
    private RichOutputText montoTotalUIC;
    private RichOutputText cargarRegistroTransferenciaRecursosUIC;
    private RichPanelGroupLayout pglTransferenciaRecursosUIC;
    private RichPanelGroupLayout pglFuenteExternaUIC;
    private RichInputText cuentaNostroUIC;
    private RichInputText cuentaClienteUIC;
    
    public static final String BUNDLE = "view.GestorDesembolsosFenixVCBundle";
    public static final String COD_EXT_MONEDA_USD = "USD";
    public static final Integer ID_TIPO_MONEDA_USD = 1;

    public AsignacionRecursosActionBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void calcularTotalMontoDesembolar(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo calcularTotalMontoDesembolar");
        Boolean esValidacionMontos = null;
        BigDecimal montoDesembolsar = null;
        
        logger.warning("Asignando bandera de control de cambio en Asignacion de recursos.");
        ADFUtils.setBoundAttributeValue("ExisteCambioPendiente", 1);
        
        if(null != valueChangeEvent && null != valueChangeEvent.getNewValue()){
            try{
                montoDesembolsar = new BigDecimal(valueChangeEvent.getNewValue().toString());
                logger.warning("MontoDesembolsar recuperado: " + montoDesembolsar);
                
                esValidacionMontos = validarMontoDesembolsarVsMontoDIsponible(montoDesembolsar);
            }catch(Exception e){
                logger.warning("ERROR al recuperar nuevo valor del valueChangeEvent.", e);
            }
        }else{
            esValidacionMontos = Boolean.TRUE;
        }
        
        if(null != esValidacionMontos){
            if(esValidacionMontos){        
                AsignacionRecursosBean asignacionRecursosBean = (AsignacionRecursosBean) JSFUtils.resolveExpression("#{pageFlowScope.AsignacionRecursosManagedBean}");
                
                //logger.warning("Total de montos: " + totalMonto);
                if(null != asignacionRecursosBean){
                    //asignacionRecursosBean.setMontoTotal(totalMonto);
                    asignacionRecursosBean.getMontoTotal();
                }else{
                    logger.warning("Instancia de Bean NULL");
                }
                AdfFacesContext.getCurrentInstance().addPartialTarget(getMontoTotalUIC()); 
            }else{
                logger.warning("Los montos a desembolsar es mayor que el monto disponible de la linea pasiva.");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_VALIDACION_MONTO_LINEA_PASIVA"));
            }
        }else{
            logger.warning("esValidacionMontos es NULL.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_VALIDAR_MONTOS_PASIVOS"));
        }
        
        logger.warning("Termina metodo calcularTotalMontoDesembolar");
    }
    
    public Boolean validarMontoDesembolsarVsMontoDIsponible(BigDecimal montoDesembolsar){
        logger.warning("Inicia metodo validarMontoDesembolsarVsMontoDIsponible");
        Boolean resultado = null;
        RowSetIterator iter = null;
        BigDecimal montoDisponible = null;
        Integer idFuente = null;
        BigDecimal montoDesembolsarConvertido = null;
        Integer idTipoMoneda = null;
        
        try{
            idFuente = (Integer) ADFUtils.getBoundAttributeValue("Id");
            logger.warning("Id Fuente externa: " + idFuente);
        }catch(Exception e){
            logger.warning("ERROR al obtener el Id de fuente externa.", e);
        }
        
        try{
            iter =
                ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesExternasContratoDesembolsoVOIterator").getRowSetIterator();
        }catch(Exception e){
            logger.warning("ERROR al recuperar el iterator de FuentesExternasContratoDesembolsoVOIterator");
        }
        
        try{
            idTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMonedaContratoActivo");
            logger.warning("IdTipoMonedaContratoActivo: " + idTipoMoneda);
        }catch(Exception e){
            logger.warning("ERROR al recuperar moneda del contrato.", e);
        }
        
        if(null != idFuente){
            if(null != iter){
                for(Row row : iter.getAllRowsInRange()){
                    logger.warning("Iterando las fuentes externas.");
                    if(null != row){
                        Integer idFuenteRow = null;
                        try{
                            idFuenteRow = (Integer) row.getAttribute("Id");
                            logger.warning("IdFuenteRow: " + idFuenteRow);
                        }catch(Exception e){
                            logger.warning("ERROR al obtener el idFuente de iterador.", e);
                        }
                        if(null != idFuenteRow){
                            if(idFuente.compareTo(idFuenteRow)==0){
                                try{
                                    montoDisponible = (BigDecimal) row.getAttribute("MontoDisponible");
                                    logger.warning("MontoDisponible: " + montoDisponible);
                                }catch(Exception e){
                                    logger.warning("ERROR al obtener MontoDisponible.", e);
                                }
                                
                                if(null != montoDisponible){
                                    if(null != idTipoMoneda){
                                        if(idTipoMoneda.compareTo(ID_TIPO_MONEDA_USD)==0){
                                            montoDesembolsarConvertido = montoDesembolsar;
                                        }else{
                                            montoDesembolsarConvertido = convertirMonedaMonto(montoDesembolsar, idTipoMoneda, COD_EXT_MONEDA_USD);
                                        }
                                        
                                        if(montoDesembolsarConvertido.compareTo(montoDisponible)==1){
                                            resultado = Boolean.FALSE;
                                        }else{
                                            resultado = Boolean.TRUE;
                                        }
                                    }else{
                                        logger.warning("IdTipoMoneda del contrato activo es NULL.");
                                    }
                                }else{
                                    logger.warning("Monto disponible son NULL.");
                                }
                                break;
                            }else{
                                logger.warning("IdFuente y IdFuenteRow son diferentes. IdFuente: " + idFuente + ", IdFuenteRow: " + idFuenteRow);
                            }
                        }else{
                            logger.warning("IdFuenteRow es NULL.");
                        }
                    }else{
                        logger.warning("EL currentRow es NULL.");
                    }
                }
            }else{
                logger.warning("El iterador es NULL.");
            }
        }else{
            logger.warning("El idFuente es NULL.");
        }
        
        logger.warning("Resultado: " + resultado);
        logger.warning("Termina metodo validarMontoDesembolsarVsMontoDIsponible");
        return resultado;
    }
    
    public BigDecimal convertirMonedaMonto(BigDecimal montoDesembolsarContrato, Integer idTipoMoneda, String codigoMonedaConvertir){
        logger.warning("Inicia metodo convertirMoneda.");
        BigDecimal montoConvertido = null;
        Map<String,Object> params = new HashMap<String,Object>();
        
        params.put("claveTipo", idTipoMoneda);
        params.put("codigoA", codigoMonedaConvertir);
        params.put("monto", montoDesembolsarContrato);
        try{
            montoConvertido = execute(params, "convertirMonedas");
            logger.warning("Monto convertido: " + montoConvertido);
        }catch(Exception e){
            logger.warning("ERROR al convertir el monto a otro tipo de moneda.", e);
        }
        logger.warning("Termina metodo convertirMoneda.");
        return montoConvertido;
    }

    public void setMontoTotalUIC(RichOutputText montoTotalUIC) {
        this.montoTotalUIC = montoTotalUIC;
    }

    public RichOutputText getMontoTotalUIC() {
        return montoTotalUIC;
    }

    public void prepararFormaTransferencia(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo prepararFormaTransferencia");
        Integer idFormatransferencia = null;
        Integer idTipoMoneda = null;
        
        logger.warning("Limpiando campos de nombre cuenta y banco.");
        ADFUtils.setBoundAttributeValue("IdBancoTransferencia", null);
        ADFUtils.setBoundAttributeValue("NumeroCuentaNostro1", null);
        ADFUtils.setBoundAttributeValue("NumeroCuentaClientePasivo", null);
        ADFUtils.setBoundAttributeValue("NumeroCuenta", null);
        ADFUtils.setBoundAttributeValue("NombreCuenta", null);
        ADFUtils.setBoundAttributeValue("NombreBancoTransferencia", null);
        ADFUtils.setBoundAttributeValue("IdBancoTransferencia", null);
        
        limpiarCuentaNostroLOV();
        limpiarCuentaClienteLOV();
        
        try{
            idFormatransferencia = (Integer) valueChangeEvent.getNewValue();
        }catch(Exception e){
            logger.warning("Error al obtener valor de binding TcaFormaTransferencia");
        }
        
        try{
            idTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
        }catch(Exception e){
            logger.warning("Error al obtener valor de binding IdTcaTipoMoneda");
        }
        
        logger.warning("IdFormaTransferencia: " + idFormatransferencia);
        logger.warning("IdTipoMoneda: " + idTipoMoneda);
        
        if(null != idFormatransferencia){
            switch(idFormatransferencia){
            case 1:
                logger.warning("Preparando forma de transferencia directa a cliente");
                break;
            case 2:
                logger.warning("Preparando forma de transferencia a cuenta BCIE");
                break;
            }           
        }else{
            logger.warning("idFormatransferencia es NULL");
        }
                
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPglFuenteExternaUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCuentaNostroUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCuentaClienteUIC());
        
        logger.warning("Termina metodo prepararFormaTransferencia");
    }
    
    private void limpiarCuentaNostroLOV() {
        logger.warning("* Entrando en limpiarCuentaNostroLOV");
        try {
            getCuentaNostroUIC().resetValue();
        }catch(Exception e){
            logger.warning("ERROR al ejecutar operationBinding limpiarCuentraNostroLOV", e);
        }
    }
    
    private void limpiarCuentaClienteLOV() {
        logger.warning("* Entrando en limpiarCuentaClienteLOV");
        try {
            getCuentaClienteUIC().resetValue();
        }catch(Exception e){
            logger.warning("ERROR al ejecutar operationBinding limpiarCuentaClienteLOV", e);
        }
    }
    
    public List onSuggest(String string) {
        
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();;
        SelectItem element = null;
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("CuentaListAttr"); 

        Row[] values = list.getAllRowsInRange();        
        if(null != values){
            for(Row obj : values){
                String value = (String) obj.getAttribute("Cuenta");
                if(null != string &&
                   null != value){
                    if(value.toUpperCase().startsWith(string.toUpperCase())){
                        element = new SelectItem(value);
                        resultItems.add(element);
                    }
                }
            }
        }else{
            element = new SelectItem("");
            resultItems.add(element);
        }
        
        return resultItems;
    }
    
    public List onSuggestNostro(String string) {
        logger.warning("Inicia metodo onSuggestNostro");
        Integer idFormaTransferencia = null;
        logger.warning("Valor a comparar: " + string);
        
        try{
            idFormaTransferencia = (Integer) ADFUtils.getBoundAttributeValue("TcaFormaTransferencia");
            logger.warning("idFormaTransferencia: " + idFormaTransferencia);
        }catch(Exception e){
            logger.warning("ERROR al obtener la forma de transferencia", e);
        }
        
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();;
        SelectItem element = null;

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        
        if(null != idFormaTransferencia){
            if(idFormaTransferencia.compareTo(2)==0){
                logger.warning("Entra a suggest de cuentas Nostro");
                
                JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("CuentaNostroListAttr"); 
        
                Row[] values = list.getAllRowsInRange();        
                if(null != values){
                    logger.warning("Iterando coincidencias");
                    for(Row obj : values){
                        String value = (String) obj.getAttribute("CuentaNostro");
                        logger.warning("Valor obtenido: " + value);
                        if(null != string &&
                           null != value){
                            if(value.toUpperCase().startsWith(string.toUpperCase())){
                                logger.warning("Seteando coincidencias");
                                element = new SelectItem(value);
                                resultItems.add(element);
                            }
                        }
                    }
                }else{
                    logger.warning("No se encontraron coincidencias");
                    element = new SelectItem("");
                    resultItems.add(element);
                }
            }else{
                logger.warning("Entra a suggest de cuentas Directo a cliente");
                JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("CuentaListAttr"); 

                Row[] values = list.getAllRowsInRange();        
                if(null != values){
                    logger.warning("Iterando coincidencias");
                    for(Row obj : values){
                        String value = (String) obj.getAttribute("Cuenta");
                        logger.warning("Valor obtenido: " + value);
                        if(null != string &&
                           null != value){
                            if(value.toUpperCase().startsWith(string.toUpperCase())){
                                logger.warning("Seteando coincidencias");
                                element = new SelectItem(value);
                                resultItems.add(element);
                            }
                        }
                    }
                }else{
                    logger.warning("No se encontraron coincidencias");
                    element = new SelectItem("");
                    resultItems.add(element);
                }
            }
        }
        logger.warning("Termina metodo onSuggestNostro");
        return resultItems;
    }

    public void asociarBancoCuentaNostro(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo asociarBancoCuentaNostro");
        RowSetIterator iter = null;
        String cuentaNostro = null;
        String cliente = null;
        String nombrecuenta = null;
        String nombreBancoTransferencia = null;
        AttributeBinding idBanco;
        idBanco = ADFUtils.findControlBinding("IdBancoTransferencia");
        AttributeBinding nombreCuentaBinding;
        nombreCuentaBinding = ADFUtils.findControlBinding("NombreCuenta");
        AttributeBinding nombreBanco;
        nombreBanco = ADFUtils.findControlBinding("NombreBancoTransferencia");        
        
        try{
            iter = 
                ADFUtils.getDCBindingContainer().findIteratorBinding("VcaCuentasNostroBcieVOIterator").getRowSetIterator();
        }catch(Exception e){
            logger.warning("ERROR al obtener VcaCuentasNostroBcieVOIterator", e);
        }
        
        
        if(null != valueChangeEvent){
            try{
                cuentaNostro = (String) valueChangeEvent.getNewValue();
            }catch(Exception e){
                logger.warning("ERROR al obtener BoundedAttribute de NumeroCuentaNostro", e);
            }
            
            ADFUtils.setBoundAttributeValue("NumeroCuenta", cuentaNostro);
            
            logger.warning("Numero de cuenta asignado: " + ADFUtils.getBoundAttributeValue("NumeroCuenta"));
        }else{
            logger.warning("valueChangeEvent es NULL");
        }
        
        if(null != iter && null != cuentaNostro){
            logger.warning("Iterando VcaCuentasNostroBcieVOIterator");
            for(Row row : iter.getAllRowsInRange()){
                if(null != row.getAttribute("CuentaNostro")){
                    String rowCuentaNostro = null;
                    try{
                        rowCuentaNostro = (String) row.getAttribute("CuentaNostro");
                    }catch(Exception e){
                        logger.warning("ERROR al castear CuentaNostro del row");
                    }
                    logger.warning("Cuenta nostro: " + cuentaNostro);
                    if(null != cuentaNostro){
                        if(cuentaNostro.equals("")){
                            logger.warning("CuentaNostro es vacío");
                            if(null != nombreCuentaBinding){
                                nombreCuentaBinding.setInputValue(null);
                            }
                            
                            logger.warning("Atributo idBanco: " + idBanco);
                            if(null != idBanco){
                                idBanco.setInputValue(null);
                            }
                            
                            logger.warning("Atributo nombreBanco: " + nombreBanco);
                            if(null != nombreBanco){
                                nombreBanco.setInputValue(null);
                            }
                        }else{
                            if(cuentaNostro.equalsIgnoreCase(rowCuentaNostro)){
                                try{
                                    cliente = (String) row.getAttribute("Cliente");
                                }catch(Exception e){
                                    logger.warning("ERROR al castear atributo Cliente", e);
                                }
                                
                                try{
                                    nombrecuenta = (String) row.getAttribute("NombreCuenta");
                                }catch(Exception e){
                                    logger.warning("ERROR al castear atributo NombreCuenta", e);
                                }
                                
                                try{
                                    nombreBancoTransferencia = (String) row.getAttribute("Banco");
                                }catch(Exception e){
                                    logger.warning("ERROR al castear atributo nombreBancoTransferencia", e);
                                }
                                logger.warning("Banco: " + cliente);
                                logger.warning("Nombre de la cuenta: " + nombrecuenta);
                                logger.warning("NombreBancoTransferencia: " + nombreBancoTransferencia);
                                
                                if(null != nombreCuentaBinding){
                                    nombreCuentaBinding.setInputValue(nombrecuenta);
                                }
                                
                                logger.warning("Atributo idBanco: " + cliente);
                                if(null != cliente){
                                    idBanco.setInputValue(cliente);
                                }
                                
                                logger.warning("Atributo nombreBancoTransferencia: " + nombreBanco);
                                if(null != nombreBanco){
                                    nombreBanco.setInputValue(nombreBancoTransferencia);
                                } 
                                                                
                            }else{
                                logger.warning("Sin coincidencias de CuentaNostro");
                            }
                        }
                    }else{
                        logger.warning("CuentaNostro es NULL");
                    }
                }else{
                    logger.warning("Atributo CuentaNostro es NULL");
                }
            }
        }else{
            logger.warning("Iterador de VcaCuentasNostroVO o CuentaNostro es NULL");
        }
        logger.warning("Termina metodo asociarBancoCuentaNostro");
    }
    
    public void seleccionarLineaPasiva(SelectionEvent event){
        logger.warning("Inicia metodo seleccionarLineaPasiva");
        logger.warning("Valor de evento: " + event.toString());
        if(event == null){
            return;
        }
        
        logger.warning("Invocar metodo para hacer el row seleccionado como CURRENT");
        //Row selectedRow =
            //(Row) invokeEL("#{bindings.FuentesExternasContratoDesembolsoVO.collectionModel.makeCurrent}", new Class[] {SelectionEvent.class},
                           //new Object[] { event });
        Row selectedRow = selectListenerTableUIC(event);
        
        if(null != selectedRow){
            logger.warning("ID de fuente externa seleccionada: " + selectedRow.getAttribute("Id"));
            try{
                logger.warning("Obteniendo id de fuente");
                logger.warning("Id: " + selectedRow.getAttribute("Id"));
                logger.warning("NumeroCuentaNostro: " + selectedRow.getAttribute("NumeroCuentaNostro"));
                logger.warning("NumeroCuentaClientePasivo: " + selectedRow.getAttribute("NumeroCuentaClientePasivo"));
                
                // Limpiamos el numero cuenta nostro y cliente por autosuggest
                limpiarCuentaNostroLOV();
                limpiarCuentaClienteLOV();
                
                Integer idFuente = (Integer) selectedRow.getAttribute("Id");
                ADFUtils.setBoundAttributeValue("Id", idFuente);
                ADFUtils.setBoundAttributeValue("NumeroCuentaNostro1", selectedRow.getAttribute("NumeroCuentaNostro"));
                ADFUtils.setBoundAttributeValue("NumeroCuentaClientePasivo", selectedRow.getAttribute("NumeroCuentaClientePasivo"));
            }catch(Exception e){
                logger.warning("ERROR al obtener el id de la fuente",e);
            }
        
            getCargarRegistroTransferenciaRecursosUIC();
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPglFuenteExternaUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPglTransferenciaRecursosUIC());
        logger.warning("Termina metodo seleccionarLineaPasiva");
    }
    
    /**
    * Programmatic invocation of a method that an EL evaluates to.
    *
    * @param el EL of the method to invoke
    * @param paramTypes Array of Class defining the types of the parameters
    * @param params Array of Object defining the values of the parametrs
    * @return Object that the method returns
    */
    public static Object invokeEL(String el, Class[] paramTypes, Object[] params) {
        logger.warning("Inicia metodo invokeEL");
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory =
        facesContext.getApplication().getExpressionFactory();
        MethodExpression exp =
        expressionFactory.createMethodExpression(elContext, el,
        Object.class, paramTypes);
        
        logger.warning("Termina metodo invokeEL");
        return exp.invoke(elContext, params);
    }
    
    public Row selectListenerTableUIC(SelectionEvent event){
        logger.warning("Inicia metodo selectListenerTableUIC");
        RichTable _table = (RichTable) event.getSource();
        DCIteratorBinding _tableIteratorBinding = null;
        Row row = null;
        if(_table != null){
            //the Collection Model is the object that provides the
            //structured data
            //for the table to render
            CollectionModel _tableModel = (CollectionModel) _table.getValue();
            
            //the ADF object that implements the CollectionModel is
            //JUCtrlHierBinding. It is wrapped by the CollectionModel API
            JUCtrlHierBinding _adfTableBinding = (JUCtrlHierBinding) _tableModel.getWrappedData();
            
            if(_adfTableBinding != null){
                //Acess the ADF iterator binding that is used with
                //ADF table binding
                _tableIteratorBinding = _adfTableBinding.getDCIteratorBinding();
                
                if(_tableIteratorBinding != null){
                    
                    //selection with the selection in the ADF model
                    Object _selectedRowData = _table.getSelectedRowData();
                
                    //cast to JUCtrlHierNodeBinding, which is the ADF object
                    //that represents a row
                    JUCtrlHierNodeBinding _nodeBinding = (JUCtrlHierNodeBinding) _selectedRowData;
                    
                    if(_nodeBinding != null){
                        //get the row key from the node binding and set it
                        //as the current row in the iterator
                        Key _rwKey = _nodeBinding.getRowKey();  
                        
                        logger.warning("Key de la fila: " + _rwKey);                        
                        if(_rwKey != null){
                            _tableIteratorBinding.setCurrentRowWithKey(_rwKey.toStringFormat(true));        
                        }
                        
                        if(null != _tableIteratorBinding){
                            row = _tableIteratorBinding.getCurrentRow();
                        }
                    }
                }
            }
        }        
        logger.warning("Termina metodo selectListenerTableUIC");
        return row;
    }

    public void setCargarRegistroTransferenciaRecursosUIC(RichOutputText cargarRegistroTransferenciaRecursosUIC) {
        this.cargarRegistroTransferenciaRecursosUIC = cargarRegistroTransferenciaRecursosUIC;
    }

    public RichOutputText getCargarRegistroTransferenciaRecursosUIC() {
        logger.warning("Inicia metodo getCargarRegistroTransferenciaRecursosUIC");
        Map<String, Object> mapaParametros = new HashMap<String, Object>();
        Integer idFuenteExterna = null;
        Long idFuente = null;
        String descripcionMoneda = null;
        String codigoBHQCliente = null;        
        Long pIdOperacion = null;
        Integer idTarea = null;
        String tareaBPMStr = null;
        
        try{
            idTarea = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.idTareaBPM}");
            //idTarea = 150;
        }catch(Exception e){
            logger.warning("ERROR al recuperar el idTareaBPM ", e);
        }
        
        logger.warning("IdTareaBPM: " + idTarea);
        
        if(null != idTarea){
            if(idTarea.compareTo(FenixConstants.CGD_GESTIONAR_DESEMBOLSO_DE_FUENTES_EXTERNAS)==0){
                logger.warning("Tarea GESTIONAR DESEMBOLSOS DE FUENTES EXTERNAS precargando datos de TRANSFERENCIA DE RECURSOS");
//                try{
//                    idFuenteExterna = (Integer) ADFUtils.getBoundAttributeValue("Id");
//                }catch(Exception e){
//                    logger.warning("ERROR al obtener el BoundAttribute de IdFuente ", e);
//                }
//                
//                if(null != idFuenteExterna){
//                    try{
//                        idFuente = idFuenteExterna.longValue();
//                    }catch(Exception e){
//                        logger.warning("ERROR al castear idFuente ", e);
//                    }
//                }
                
                if(null != JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}")){
                    pIdOperacion =
                        new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString());
                }else{
                    logger.warning("pIdOperacion es NULL");
                }
                logger.warning("pIdOperacion de TaskFlow: " + pIdOperacion);
                
//                mapaParametros.put("idFuenteExterna", idFuente);
//                try{
//                    logger.warning("Ejecutando OperationBinding de inicializarRegistroTransferenciaRecursos");
//                    super.execute(mapaParametros, "inicializarRegistroTransferenciaRecursos");
//                }catch(Exception e){
//                    logger.warning("Error al ejecutar operationBinding ", e);    
//                }
//                
                try{
                    logger.warning("Ejecutando OperationBinding de obtenerDescripcionMoneda");
                    descripcionMoneda = super.execute(null, "obtenerDescripcionMoneda");
                    logger.warning("Descripcion de moneda: " + descripcionMoneda);
                }catch(Exception e){
                    logger.warning("ERROR al ejecutar operationBinding ", e);
                }
                
                mapaParametros.clear();
                mapaParametros.put("idOperacion", pIdOperacion);
                try{
                    logger.warning("Ejecutando OperationBinding de obtenerCodigoBHQClientePorIdOperacion");
                    codigoBHQCliente = super.execute(mapaParametros, "obtenerCodigoBHQClientePorIdOperacion");
                    logger.warning("CodigoBHQ del cliente: " + codigoBHQCliente);
                }catch(Exception e){
                    logger.warning("ERROR al ejecutar operationBinding ", e);
                }
                
                mapaParametros.clear();
                mapaParametros.put("pTipoMoneda", descripcionMoneda);
                try{
                    logger.warning("Ejecutando OperationBinding de obtenerCuentasNostro");
                    super.execute(mapaParametros, "obtenerCuentasNostro");
                }catch(Exception e){
                    logger.warning("Error al ejecutar operationBinding ", e);    
                }
                
                //mapaParametros.clear();
                //mapaParametros.put("pMoneda", descripcionMoneda);
                //mapaParametros.put("pCodigoCliente", codigoBHQCliente);
                try{
                    logger.warning("Ejecutando OperationBinding de inicializarVista");
                    super.execute(null, "inicializarVista");
                }catch(Exception e){
                    logger.warning("Error al ejecutar operationBinding ", e);    
                }                
            }
        }else{
            logger.warning("ERROR el idTareaBPM es NULL");
        }
        
        logger.warning("Termina metodo getCargarRegistroTransferenciaRecursosUIC");
        return cargarRegistroTransferenciaRecursosUIC;
    }

    public void asignarValorNumeroCuenta(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo asignarValorNumeroCuenta");
        RowSetIterator iter = null;
        //String numeroCuenta = null; //
        String numeroCuenta = "200170100";
        String nombrecuenta = null;
        AttributeBinding nombreCuentaBinding;
        nombreCuentaBinding = ADFUtils.findControlBinding("NombreCuenta");
        
        try{
            iter = 
                ADFUtils.getDCBindingContainer().findIteratorBinding("VcaCuentaContablePasivaVOIterator").getRowSetIterator();
        }catch(Exception e){
            logger.warning("ERROR al obtener VcaCuentaContablePasivaVOIterator", e);
        }
        
        if(null != valueChangeEvent){
            try{
                //numeroCuenta = (String) valueChangeEvent.getNewValue();//
                logger.warning("Numero de cuenta: " + numeroCuenta);
            }catch(Exception e){
                logger.warning("ERROR al recuperar el numero de cuenta", e);
            }
            
            ADFUtils.setBoundAttributeValue("NumeroCuenta", numeroCuenta);
            
            logger.warning("Numero de cuenta asignado: " + ADFUtils.getBoundAttributeValue("NumeroCuenta"));
        }
        
        if(null != iter){
            logger.warning("Iterando VcaCuentaContablePasivaVOIterator");
            for(Row row : iter.getAllRowsInRange()){
                if(null != row.getAttribute("Cuenta")){
                    String rowCuentaNostro = null;
                    try{
                        rowCuentaNostro = (String) row.getAttribute("Cuenta");
                    }catch(Exception e){
                        logger.warning("ERROR al castear CuentaNostro del row");
                    }
                    logger.warning("numeroCuenta: " + numeroCuenta);
                    if(null != numeroCuenta){
                        if(numeroCuenta.equals("")){
                            logger.warning("numeroCuenta es vacío");
                            if(null != nombreCuentaBinding){
                                nombreCuentaBinding.setInputValue(null);
                            }
                        }else{
                            if(numeroCuenta.equalsIgnoreCase(rowCuentaNostro)){
                                
                                try{
                                    nombrecuenta = (String) row.getAttribute("Descripcion");
                                }catch(Exception e){
                                    logger.warning("ERROR al castear atributo NombreCuenta", e);
                                }
                                logger.warning("Nombre de la cuenta: " + nombrecuenta);
                                
                                if(null != nombreCuentaBinding){
                                    nombreCuentaBinding.setInputValue(nombrecuenta);
                                }
                                
                            }else{
                                logger.warning("Sin coincidencias de Cuenta");
                            }
                        }
                    }else{
                        logger.warning("Cuenta es NULL");
                    }
                }else{
                    logger.warning("Atributo Cuenta es NULL");
                }
            }
        }else{
            logger.warning("Iterador de VcaCuentaContablePasivaVOIterator es NULL");
        }
        
        logger.warning("Termina metodo asignarValorNumeroCuenta");
    }
    
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }

    public void seleccionarFuenteExterna(SelectionEvent selectionEvent) {
        logger.warning("Inicia metodo seleccionarFuenteExterna");
        
        
        
        logger.warning("Termina metodo seleccionarFuenteExterna");
    }

    public void setPglTransferenciaRecursosUIC(RichPanelGroupLayout pglTransferenciaRecursosUIC) {
        this.pglTransferenciaRecursosUIC = pglTransferenciaRecursosUIC;
    }

    public RichPanelGroupLayout getPglTransferenciaRecursosUIC() {
        return pglTransferenciaRecursosUIC;
    }

    public RichPanelGroupLayout getPglFuenteExternaUIC() {
        return pglFuenteExternaUIC;
    }

    public void setPglFuenteExternaUIC(RichPanelGroupLayout pglFuenteExternaUIC) {
        this.pglFuenteExternaUIC = pglFuenteExternaUIC;
    }

    public RichInputText getCuentaNostroUIC() {
        return cuentaNostroUIC;
    }

    public void setCuentaNostroUIC(RichInputText cuentaNostroUIC) {
        this.cuentaNostroUIC = cuentaNostroUIC;
    }

    public RichInputText getCuentaClienteUIC() {
        return cuentaClienteUIC;
    }

    public void setCuentaClienteUIC(RichInputText cuentaClienteUIC) {
        this.cuentaClienteUIC = cuentaClienteUIC;
    }
}
