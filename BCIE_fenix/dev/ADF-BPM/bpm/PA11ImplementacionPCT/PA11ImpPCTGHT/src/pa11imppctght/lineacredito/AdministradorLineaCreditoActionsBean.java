package pa11imppctght.lineacredito;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
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

public class AdministradorLineaCreditoActionsBean {

    private static ADFLogger logger = null;
    private static final String BUNDLE = "pa11imppctght.PA11ImpPCTGHTBundle";
    private static final String FORMATO_MONTO_LINEA = "####.##";
    private static final char CARACTER_COMA = ',';
    private static final char CARACTER_PUNTO = '.';

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
    private RichInputText uiTasaMinima;
    private RichInputText uiTasaMaxima;
    private RichInputText uiMontoMnimo;
    private RichInputText uiMontoMaximo;
    private RichInputText uiMontoLinea;
    private RichInputText uiNumeroLineaCredito;
    private RichInputText uiDescripcionLinea;

    public AdministradorLineaCreditoActionsBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void lineaCreditoSelectionListener(SelectionEvent selectionEvent) {
        logger.log(ADFLogger.TRACE, "Inside lineaCreditoSelectionListener");

        // Actualizamos row seleccionado en tabla
        JSFUtils.resolveMethodExpression("#{bindings.DatosLineaCreditoVO.collectionModel.makeCurrent}", Object.class, new Class[] {
                                         SelectionEvent.class }, new Object[] { selectionEvent });

        // Llenamos datos de la línea de crédito seleccionada en la tabla DatosLineaCreditoVO
        consultarLineaCredito();

    }

    private Boolean consultarLineaCredito() {
        logger.log(ADFLogger.TRACE, "Inside consultarLineaCredito");
        Boolean esConsultarExitoso = Boolean.TRUE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String idOperacion = null;
        Integer idProducto = null;
        Long idLineaCredito = null;
        String idTarea = null;
        Integer idContratoLote = null;

        //Crear instancia de AdministradorLineaCreditoBean
        AdministradorLineaCreditoBean administradorLineaCreditoBean =
            (AdministradorLineaCreditoBean) JSFUtils.resolveExpression("#{pageFlowScope.AdministradorLineaCreditoMB}");

        //Limpiamos las listas de eliminados(vencimientos y comisiones)
        //Limpia lista de vencimientos
        administradorLineaCreditoBean.getIdsVencimientosEliminados().clear();

        //Limpia lista de comisiones
        administradorLineaCreditoBean.getIdsComisionesEliminadas().clear();

        // Se inicializan variables que se envian como parametros para el método consultarLineaCreditoByIdLineaCredito
        //Recupera el "ID" de la linea de credito seleccionada en la tabla DatosLineaCreditoVO
        if (null != JSFUtils.resolveExpression("#{bindings.Id.inputValue}")) {
            idLineaCredito = (Long) JSFUtils.resolveExpression("#{bindings.Id.inputValue}");
        }else{
            logger.warning("El id de la linea es nula.");
        }
        //Recupera el idOperacion de la entrada del taskflow
        if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")) {
            idOperacion = (String) JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");
        }else{
            logger.warning("El id de la operacion es nulo.");
        }
        if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoLote}")) {
            idContratoLote = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoLote}");
        }else{
            logger.warning("El id del contrato es nulo.");
        }
        //Recupera el idProducto de la entrada del taskflow
        if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}")) {
            logger.log(ADFLogger.WARNING, "pIdProducto " + JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}"));
            logger.log(ADFLogger.WARNING, "pIdProducto.class " + JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}").getClass());
            idProducto = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}");
        }
        //Recuperar el idTarea de la entrada del taskflow  por incidencia FNXII-3411
        if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}")) {
            idTarea = (String) JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}");
        }else{
            logger.warning("El id de la tarea es nulo.");
        }

        //Se invoca el metodo consultarLineaCreditoByIdLineaCredito
        operationBinding = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");

        //Se envian los parametros del metodo consultarLineaCreditoByIdLineaCredito
        operationBinding.getParamsMap().put("idOperacion", idOperacion); //InstanciaProceso(BPM)
        operationBinding.getParamsMap().put("idLineaCredito", idLineaCredito);
        operationBinding.getParamsMap().put("idProducto", idProducto);
        //se agrega parametro idTarea por incidencia FNXII-3411
        operationBinding.getParamsMap().put("idTarea", idTarea);
        operationBinding.getParamsMap().put("idContrato", idContratoLote);

        // Invocamos método
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            esConsultarExitoso = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        if (getFormDatosLineaCreditoUIC() != null) {
            logger.warning("Refresca panel de datos de la linea de credito");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getFormDatosLineaCreditoUIC());
        }

        return esConsultarExitoso;
    }

    public void esRevolventeValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE, "Dentro esRevolventeValueChangeListener");
        //Se recupera el contenedor de los bindings
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Integer idProducto = null;
        //actualiza el valor seleccionado
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        //Validar que el nuevo valor sea diferente a null
        if (valueChangeEvent.getNewValue() != null) {
            //Recupera el idProducto de la entrada del taskflow
            if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}")) {
                idProducto = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}");
                logger.warning("idProducto : "+idProducto);
            }else{
                logger.severe("idProducto es nulo");
            }
            OperationBinding operationBinding = bindings.getOperationBinding("filtrarProducto");
            //Se envian los parametros del metodo consultarLineaCreditoByIdLineaCredito
            //IdOperacion
            operationBinding.getParamsMap().put("idProducto", idProducto);

            if (valueChangeEvent.getNewValue().toString().equals("1") ||
                valueChangeEvent.getNewValue().toString() == "1") {
                logger.warning("isRevolvente es Y");
                operationBinding.getParamsMap().put("isRevolvente", "Y");
            } else {
                logger.warning("isRevolvente es N");
                operationBinding.getParamsMap().put("isRevolvente", "N");
            }
            operationBinding.execute();
        } //cierra if != null
        
        logger.log(ADFLogger.TRACE, "Fuera esRevolventeValueChangeListener");

    }

    public void tiposComisionValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE,
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
        logger.log(ADFLogger.TRACE,
                   "Inside agregarModificarVencimientoPlazoActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        DCIteratorBinding voAgregarVencimientoPlazo = null;
        DCIteratorBinding voVencimientoPlazo = null;
        Row rowAgregarVencimiento = null;
        Row rowVencimiento = null;
        String opAdjuntarModificar = null;
        RichPopup.PopupHints popupHints = null;

        // Asignación de variables
        opAdjuntarModificar = (String) JSFUtils.resolveExpression("#{viewScope.opAdjuntarModificar}");

        // Creamos row de captura
        operationBinding = bindings.getOperationBinding("crearRowVencimientoPlazo");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Si es modificar, asignamos datos de current row de tabla a row de captura
        if ((opAdjuntarModificar != null) && (opAdjuntarModificar.equalsIgnoreCase("modificar"))) {

            // Asignación de variables para modificar row
            voAgregarVencimientoPlazo =
                ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarVencimientoPlazoVOIterator");
            voVencimientoPlazo = ADFUtils.getDCBindingContainer().findIteratorBinding("VencimientoPlazoVOIterator");
            rowAgregarVencimiento = voAgregarVencimientoPlazo.getRowAtRangeIndex(0);
            rowVencimiento = voVencimientoPlazo.getCurrentRow();

            rowAgregarVencimiento.setAttribute("FechaInicio", rowVencimiento.getAttribute("FechaInicio"));
            rowAgregarVencimiento.setAttribute("FechaVencimiento", rowVencimiento.getAttribute("FechaVencimiento"));
            rowAgregarVencimiento.setAttribute("IdPlazo", rowVencimiento.getAttribute("IdPlazo"));
            rowAgregarVencimiento.setAttribute("IdTipoDeFecha", rowVencimiento.getAttribute("IdTipoDeFecha"));
            rowAgregarVencimiento.setAttribute("IdTipoDeVencimiento",
                                               rowVencimiento.getAttribute("IdTipoDeVencimiento"));
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
        logger.log(ADFLogger.TRACE,
                   "Inside agregarModificarComisionActionListener: " + actionEvent.getComponent().getId());
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
        opAdjuntarModificar = (String) JSFUtils.resolveExpression("#{viewScope.opAdjuntarModificar}");
        codProducto = (String) JSFUtils.resolveExpression("#{bindings.Id1.inputValue}");

        // Creamos row de captura
        operationBinding = bindings.getOperationBinding("crearRowComision");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
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
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Ejecutamos query en combo Tipos de Comisión (debido a que se filtró)
        voTiposComision = ADFUtils.getDCBindingContainer().findIteratorBinding("TiposComisionLOVIterator");
        voTiposComision.executeQuery();

        // Inicializamos el VO AgregarComisionVO con el valor seleccionado por defecto en Tipos de Comisión
        if (voTiposComision.getCurrentRow() != null) {
            rowAgregarComision.setAttribute("CodigoComision", voTiposComision.getCurrentRow().getAttribute("Codigo"));
            rowAgregarComision.setAttribute("TipoComision",
                                            voTiposComision.getCurrentRow().getAttribute("Descripcion"));
        }

        // Si es modificar, asignamos datos de current row de tabla a row de captura
        if ((opAdjuntarModificar != null) && (opAdjuntarModificar.equalsIgnoreCase("modificar"))) {

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
            rowTiposComision =
                voTiposComision.getViewObject().getFilteredRows("Codigo", rowComision.getAttribute("CodigoComision"));
            if (rowTiposComision.length > 0)
                voTiposComision.getViewObject().setCurrentRow(rowTiposComision[0]);
        }

        // Abrimos popup
        popupHints = new RichPopup.PopupHints();
        getPopupAgregarComision().show(popupHints);
    }

    public void aceptarAgregarModificarVencimientoPlazoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside aceptarAgregarModificarVencimientoPlazoActionListener: " +
                   actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String opAdjuntarModificar = null;
        DCIteratorBinding voAgregarVencimientoPlazo = null;
        DCIteratorBinding voVencimientoPlazo = null;
        Row rowAgregarVencimiento = null;

        // Asignación de variables
        opAdjuntarModificar = (String) JSFUtils.resolveExpression("#{viewScope.opAdjuntarModificar}");
        voAgregarVencimientoPlazo =
            ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarVencimientoPlazoVOIterator");
        voVencimientoPlazo = ADFUtils.getDCBindingContainer().findIteratorBinding("VencimientoPlazoVOIterator");
        rowAgregarVencimiento = voAgregarVencimientoPlazo.getRowAtRangeIndex(0);

        // Insertamos/modificamos en VO el Vencimiento
        if (opAdjuntarModificar.equalsIgnoreCase("agregar"))
            operationBinding = bindings.getOperationBinding("agregarVencimiento");
        else {
            operationBinding = bindings.getOperationBinding("modificarVencimiento");
            operationBinding.getParamsMap().put("id", voVencimientoPlazo.getCurrentRow().getAttribute("Id"));
            operationBinding.getParamsMap().put("idVencimiento",
                                                voVencimientoPlazo.getCurrentRow().getAttribute("IdVencimiento"));
        }

        // Mapeo de datos de popup
        operationBinding.getParamsMap().put("idTipoDeVencimiento",
                                            rowAgregarVencimiento.getAttribute("IdTipoDeVencimiento"));
        operationBinding.getParamsMap().put("idTipoDeFecha", rowAgregarVencimiento.getAttribute("IdTipoDeFecha"));
        operationBinding.getParamsMap().put("idPlazo", rowAgregarVencimiento.getAttribute("IdPlazo"));
        operationBinding.getParamsMap().put("tipoDeVencimiento",
                                            rowAgregarVencimiento.getAttribute("TipoDeVencimiento"));
        operationBinding.getParamsMap().put("tipoDeFecha", rowAgregarVencimiento.getAttribute("TipoDeFecha"));
        operationBinding.getParamsMap().put("tipoPlazo", rowAgregarVencimiento.getAttribute("TipoPlazo"));
        operationBinding.getParamsMap().put("plazo", rowAgregarVencimiento.getAttribute("Plazo"));
        operationBinding.getParamsMap().put("fechaInicio", rowAgregarVencimiento.getAttribute("FechaInicio"));
        operationBinding.getParamsMap().put("fechaVencimiento", rowAgregarVencimiento.getAttribute("FechaVencimiento"));

        // Invocamos método
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Cerramos popup
        getPopupAgregarVencimiento().hide();
    }

    public void aceptarAgregarModificarComisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside aceptarAgregarModificarComisionActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String opAdjuntarModificar = null;
        DCIteratorBinding voAgregarComision = null;
        DCIteratorBinding voComision = null;
        DCIteratorBinding voTiposComision = null;
        Row rowAgregarComision = null;

        // Asignación de variables
        opAdjuntarModificar = (String) JSFUtils.resolveExpression("#{viewScope.opAdjuntarModificar}");
        voAgregarComision = ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarComisionVOIterator");
        voComision = ADFUtils.getDCBindingContainer().findIteratorBinding("ComisionVOIterator");
        rowAgregarComision = voAgregarComision.getRowAtRangeIndex(0);

        voTiposComision = ADFUtils.getDCBindingContainer().findIteratorBinding("TiposComisionLOVIterator");

        // Insertamos/modificamos en VO la Comisión
        if (opAdjuntarModificar.equalsIgnoreCase("agregar"))
            operationBinding = bindings.getOperationBinding("agregarComision");
        else {
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

        if (voTiposComision.getCurrentRow() != null) {
            operationBinding.getParamsMap().put("idTipoComision", voTiposComision.getCurrentRow().getAttribute("Id"));
            operationBinding.getParamsMap().put("tipoComision",
                                                voTiposComision.getCurrentRow().getAttribute("Descripcion"));
            operationBinding.getParamsMap().put("codigoComision",
                                                voTiposComision.getCurrentRow().getAttribute("Codigo"));
        } else {
            operationBinding.getParamsMap().put("idTipoComision", rowAgregarComision.getAttribute("IdTipoComision"));
            operationBinding.getParamsMap().put("codigoComision", rowAgregarComision.getAttribute("CodigoComision"));
            operationBinding.getParamsMap().put("tipoComision", rowAgregarComision.getAttribute("TipoComision"));
        }

        // Invocamos método
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Cerramos popup
        getPopupAgregarComision().hide();
    }

    public void cancelarAgregarVencimientoPlazoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside cancelarAgregarVencimientoPlazoActionListener: " + actionEvent.getComponent().getId());

        // Cerramos popup
        getPopupAgregarVencimiento().hide();
    }

    public void cancelarAgregarComisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside cancelarAgregarVencimientoPlazoActionListener: " + actionEvent.getComponent().getId());

        // Cerramos popup
        getPopupAgregarComision().hide();
    }

    public void eliminarVencimientoComisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside eliminarVencimientoComisionActionListener: " + actionEvent.getComponent().getId());
        RichPopup.PopupHints popupHints = null;

        // Abrimos popup
        popupHints = new RichPopup.PopupHints();
        getPopupEliminar().show(popupHints);
    }

    public void aceptarEliminarVencimientoComisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside aceptarEliminarVencimientoComisionActionListener: " + actionEvent.getComponent().getId());
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
        AdministradorLineaCreditoBean administradorLineaCreditoBean =
            (AdministradorLineaCreditoBean) JSFUtils.resolveExpression("#{pageFlowScope.AdministradorLineaCreditoMB}");

        // Asignación de variables
        opEliminarOrigen = (String) JSFUtils.resolveExpression("#{viewScope.opEliminarOrigen}");

        if ((opEliminarOrigen != null) && (opEliminarOrigen.equalsIgnoreCase("vencimiento"))) {

            voVencimientoPlazo = ADFUtils.getDCBindingContainer().findIteratorBinding("VencimientoPlazoVOIterator");
            rowVencimiento = voVencimientoPlazo.getCurrentRow();
            idVencimiento = (Long) rowVencimiento.getAttribute("IdVencimiento");

            // Si el Id Vencimiento no es null, agregamos Id a lista de eliminados
            if (idVencimiento != null)
                administradorLineaCreditoBean.getIdsVencimientosEliminados().add(idVencimiento);

            // Eliminamos Vencimiento seleccionado de tabla
            operationBinding = bindings.getOperationBinding("eliminarVencimiento");
            operationBinding.getParamsMap().put("id", rowVencimiento.getAttribute("Id"));
        } else {
            voComision = ADFUtils.getDCBindingContainer().findIteratorBinding("ComisionVOIterator");
            rowComision = voComision.getCurrentRow();
            idComision = (Long) rowComision.getAttribute("IdComision");

            // Si el Id Comisión no es null, agregamos Id a lista de eliminados
            if (idComision != null)
                administradorLineaCreditoBean.getIdsComisionesEliminadas().add(idComision);

            // Eliminamos Comisión seleccionada de tabla
            operationBinding = bindings.getOperationBinding("eliminarComision");
            operationBinding.getParamsMap().put("id", rowComision.getAttribute("Id"));
        }

        // Invocamos método
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Cerramos popup
        getPopupEliminar().hide();
    }

    public void cancelarEliminarVencimientoComisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside cancelarEliminarVencimientoComisionActionListener: " + actionEvent.getComponent().getId());

        // Cerramos popup
        getPopupEliminar().hide();
    }

    public void guardarDatosLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside guardarDatosLineaCreditoActionListener");
        Boolean esGuardarExitoso = Boolean.TRUE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        AdministradorLineaCreditoBean administradorLineaCreditoBean = null;
        OperationBinding operationBinding = null;
        DCIteratorBinding voDatosLineaCreditoVO = null;
        String idOperacion = null;
        Integer idContratoLote = null;

        if (validarDatosLineaCredito()) {
            //Crear instancia de AdministradorLineaCreditoBean
            administradorLineaCreditoBean =
                (AdministradorLineaCreditoBean) JSFUtils.resolveExpression("#{pageFlowScope.AdministradorLineaCreditoMB}");

            // Insertamos al VO los Vencimientos eliminados
            if (administradorLineaCreditoBean.getIdsVencimientosEliminados().size() > 0) {

                operationBinding = bindings.getOperationBinding("agregarVencimientosEliminados");
                operationBinding.getParamsMap().put("idsVencimientosEliminados",
                                                    administradorLineaCreditoBean.getIdsVencimientosEliminados());
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    // Manejo de errores
                    esGuardarExitoso = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                }
            }

            // Insertamos al VO las Comisiones eliminadas
            if (administradorLineaCreditoBean.getIdsComisionesEliminadas().size() > 0) {

                operationBinding = bindings.getOperationBinding("agregarComisionesEliminadas");
                operationBinding.getParamsMap().put("idsComisionesEliminadas",
                                                    administradorLineaCreditoBean.getIdsComisionesEliminadas());

                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
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
            if (operationBinding.getErrors().size() < 1) {
                // Limpiamos las listas de vencimientos y comisiones eliminados
                administradorLineaCreditoBean.getIdsVencimientosEliminados().clear(); //Limpia lista de vencimientos
                administradorLineaCreditoBean.getIdsComisionesEliminadas().clear(); //Limpia lista de comisiones

                // Limpiamos RegistrarDatosLineaCreditoVO
                operationBinding = bindings.getOperationBinding("inicializarRegistrarDatosLineaCredito");
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    // Manejo de errores
                    esGuardarExitoso = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                }

                // Consultamos y llenamos RegistrarDatosLineaCreditoVO
                logger.log(ADFLogger.WARNING, "execute consultarLineaCredito()");
                consultarLineaCredito();

                // Limpiamos tabla de Líneas de crédito
                voDatosLineaCreditoVO =
                    ADFUtils.getDCBindingContainer().findIteratorBinding("DatosLineaCreditoVOIterator");
                voDatosLineaCreditoVO.executeQuery();


                // Actualizamos datos del encabezado y Líneas de crédito (se cargan todos los datos de nuevo)
                try {
                    if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")){
                        idOperacion = (String)JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");
                    }else{
                        logger.warning("La operacion ses nula.");
                    }
                    if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoLote}")){
                        idContratoLote = (Integer)JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoLote}");
                    }else{
                        logger.warning("La operacion ses nula.");
                    }
                    operationBinding = bindings.getOperationBinding("consultarLineaCredito");
                    operationBinding.getParamsMap().put("idOperacion", idOperacion);
                    operationBinding.getParamsMap().put("idContrato", idContratoLote);
                    operationBinding.execute();
                    if (!operationBinding.getErrors().isEmpty()) {
                        // Manejo de errores
                        esGuardarExitoso = Boolean.FALSE;
                        JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                    }

                    // Inicializamos pantalla con datos de la línea de crédito seleccionada en la tabla DatosLineaCreditoVO
                    if (esGuardarExitoso)
                        JSFUtils.addFacesInformationMessage("Actualizó correctamente.");
                } catch (Exception e) {
                    logger.warning("Error al consultar la linea de credito");
                }
            }
        }
    }

    private Boolean validarDatosLineaCredito() {
        logger.warning("Dentro de validarDatosLineaCredito");
        Boolean esDatosCorrectos = Boolean.TRUE;
        Integer diasDePago = null;
        Integer plazoFinanciamiento = null;
        BigDecimal MontoTotalLinea = null;
        BigDecimal MontoEscriturado = null;
        BigDecimal MontoLinea = null;
        AttributeBinding vceAux = null;

        // Número de línea es obligatorio
        if ((JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito.inputValue}") == null) ||
            (JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito.inputValue}").toString().trim().length() < 1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Número de línea es requerido.");
        }

        // Fondo contable es obligatorio
        if ((JSFUtils.resolveExpression("#{bindings.CatFondoContableVO.attributeValue}") == null) ||
            (JSFUtils.resolveExpression("#{bindings.CatFondoContableVO.attributeValue}").toString().trim().length() <
             1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Fondo contable es requerido.");
        }

        // Es revolvente es obligatorio
        if ((JSFUtils.resolveExpression("#{bindings.EsRevolvente.attributeValue}") == null) ||
            (JSFUtils.resolveExpression("#{bindings.EsRevolvente.attributeValue}").toString().trim().length() < 1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Es revolvente es requerido.");
        }

        // Aplican recursos ordinarios es obligatorio
        if ((JSFUtils.resolveExpression("#{bindings.EsRecursosExternos.attributeValue}") == null) ||
            (JSFUtils.resolveExpression("#{bindings.EsRecursosExternos.attributeValue}").toString().trim().length() <
             1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Aplican recursos ordinarios es requerido.");
        }

        // Producto es obligatorio
        if ((JSFUtils.resolveExpression("#{bindings.CatProductoFlexcubeLOV.attributeValue}") == null) ||
            (JSFUtils.resolveExpression("#{bindings.CatProductoFlexcubeLOV.attributeValue}").toString().trim().length() <
             1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Producto es requerido.");
        }

        // Fecha de apertura es obligatorio
        if ((JSFUtils.resolveExpression("#{bindings.FechaValor.inputValue}") == null) ||
            (JSFUtils.resolveExpression("#{bindings.FechaValor.inputValue}").toString().trim().length() < 1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Fecha de apertura es requerido.");
        }

        // Plazo de financiamiento es obligatorio
        if ((JSFUtils.resolveExpression("#{bindings.PlazoFinanciamiento.inputValue}") == null) ||
            (JSFUtils.resolveExpression("#{bindings.PlazoFinanciamiento.inputValue}").toString().trim().length() < 1) ||
            (JSFUtils.resolveExpression("#{bindings.CatPlazoFinanciamientoLOV.attributeValue}") == null) ||
            (JSFUtils.resolveExpression("#{bindings.CatPlazoFinanciamientoLOV.attributeValue}").toString().trim().length() <
             1)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Plazo de financiamiento es requerido.");
        } else {
            // Plazo de financiamiento debe ser mayor a cero
            plazoFinanciamiento =
                Integer.valueOf(JSFUtils.resolveExpression("#{bindings.PlazoFinanciamiento.inputValue}").toString());

            if (plazoFinanciamiento.intValue() < 1) {

                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("El Plazo de financiamiento debe ser mayor a cero.");
            }
        }
        
    
        // Monto de la línea es obligatorio
        if ((JSFUtils.resolveExpression("#{bindings.MontoLinea.inputValue}") == null) ||
            (JSFUtils.resolveExpression("#{bindings.MontoLinea.inputValue}").toString().trim().length() < 1)) {
            
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Monto de la línea es requerido.");
        } else {
            if (null != JSFUtils.resolveExpression("#{bindings.MontoLinea.inputValue}")) {
                MontoLinea = (BigDecimal) JSFUtils.resolveExpression("#{bindings.MontoLinea.inputValue}");
            } else {
                logger.warning("El valor del monto de la linea es nulo.");
            }
            if (null != MontoEscriturado && null != MontoLinea) {
                logger.warning("Valor MontoEscriturado :" + MontoEscriturado);
                logger.warning("Valor MontoLinea :" + MontoLinea);
                if (MontoLinea.compareTo(MontoEscriturado) == 1) {
                    logger.warning("Monto de la linea es mayor que Monto escriturado ");
                    esDatosCorrectos = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG03_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO",
                                                                               BUNDLE));
                    vceAux = ADFUtils.findControlBinding("MontoLinea");
                    vceAux.setInputValue(null);
                    resetValueTextComponent(uiMontoMaximo);
                }else{
                    logger.warning("Monto de la linea es menor que Monto escriturado ");
                }
            }
        }
        
        // Los vencimientos de plazos siempre deben contener Desembolso de la Totalidad de los Recursos e Inicio de Desembolso.
        DCIteratorBinding voVencimientoPlazo = null;
        voVencimientoPlazo = ADFUtils.getDCBindingContainer().findIteratorBinding("VencimientoPlazoVOIterator");
        ViewObject vo = voVencimientoPlazo.getViewObject();
        RowSetIterator iter = vo.createRowSetIterator(null);

        Boolean poseeDesembolsoTotalidadRecursos = false;
        Boolean poseeInicioDesembolso = false;
        Boolean poseeDesembolsoTotalidadRecursosVencimiento = false;
        Boolean poseeInicioDesembolsoVencimiento = false;
        
        // Variables para validaciones de user stories 1684 y 1958. — FCP, 10/mar/2021.
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

        if(!poseeDesembolsoTotalidadRecursos)
        {
            JSFUtils.addFacesErrorMessage("Debe agregar un vencimiento de plazo del tipo \"Desembolso de la totalidad de los recursos\".");
            esDatosCorrectos = Boolean.FALSE;
        }
        else
        {
            if(!poseeDesembolsoTotalidadRecursosVencimiento)
            {
                JSFUtils.addFacesErrorMessage("Debe agregar la fecha de vencimiento para el plazo del tipo \"Desembolso de la totalidad de los recursos\".");
                esDatosCorrectos = Boolean.FALSE;
            }
        }
        
        if(!poseeInicioDesembolso)
        {
            JSFUtils.addFacesErrorMessage("Debe agregar un vencimiento de plazo del tipo \"Inicio de Desembolso\".");
            esDatosCorrectos = Boolean.FALSE;
        }
        else
        {
            if(!poseeInicioDesembolsoVencimiento)
            {
                JSFUtils.addFacesErrorMessage("Debe agregar la fecha de vencimiento para el plazo del tipo \"Inicio de Desembolso\".");
                esDatosCorrectos = Boolean.FALSE;
            }
        }
        
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
        
        logger.warning("Fuera de validarDatosLineaCredito, retorna valor : "+esDatosCorrectos);

        return esDatosCorrectos;
    }
    
    public Boolean validaLineaCredito(){
        logger.warning("Entra en validaLineaCredito");
        Boolean existeLineaCredito = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        AdministradorLineaCreditoBean administradorLineaCreditoBean = null;
        OperationBinding operationBinding = null;
        
        try{
            operationBinding = bindings.getOperationBinding("buscarLineaCredito");
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                existeLineaCredito = (Boolean)operationBinding.getResult();
            }
        }catch(Exception e){
            logger.warning("Error en buscarLineaCredito.", e);
        }
        logger.warning("Existe registros :" + existeLineaCredito);
        return existeLineaCredito;
    }

    

    /* SCRUM_FenixII_BCIEFNXII-3145
     * Post-PROD-Formalización-KMBZ-3794-Campos vacíos en Registrar Línea de Crédito para Ayuda de Emergencia
     * Gabriel Niño Rosales
     * 18/03/2016
     */

    public void agregarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside agregarLineaCreditoActionListener");
        logger.warning("Inside agregarLineaCreditoActionListener");
        RichPopup.PopupHints popupHints = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        // Ejecutar metodo crear row
        operationBinding = bindings.getOperationBinding("crearUnicoRowAgregarLineaCredito");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        //al campo de idContrato1 se asigna el valor de idContratoLote Recibido JURBINA@17/10/2019
        
        Object idContratoPorLote = JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoLote}");
        
        if(idContratoPorLote != null)
        {
            Integer idContrato = Integer.parseInt(idContratoPorLote.toString());
            ADFUtils.setBoundAttributeValue("IdContrato1", idContrato);
        }
        
        // Abrimos popupAgregarLineaCredito
        popupHints = new RichPopup.PopupHints();
        getPopupAgregarLineaCredito().show(popupHints);
    }

    public void cancelarAgregarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarAgregarLineaCreditoActionListener");
        // Cerramos popupAgregarLineaCredito
        getPopupAgregarLineaCredito().hide();

        //Elimina el registro temporal de la VO que sirvio para trabajar el popup
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("DeleteAgregarLineaCredito");
        if (operationBinding != null) {
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.severe("Error al eliminar el Row temporal de Linea de Credito");
            }
        }

        //Refresca tabla de Lineas de credito.
        resetValueTextComponent(uiNumeroLineaCredito);
        resetValueTextComponent(uiDescripcionLinea);
        resetValueTextComponent(uiMontoLinea);
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
        String sMontoLinea = null;
        BigDecimal montoEscriturado = null;
        Boolean esDatosCorrectos = Boolean.TRUE;
        Integer idTipoMonedaMontoLinea = null;

        if (null != JSFUtils.resolveExpression("#{bindings.IdContrato1.inputValue}")) {
            Integer idContratoLong = (Integer) JSFUtils.resolveExpression("#{bindings.IdContrato1.inputValue}");
            idContrato = idContratoLong.longValue();
        }
        //Recuperar los valores de AgregarLineaCreditoVOIterator
        if (null != JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito1.inputValue}")) {
            numeroLineaCredito = (String) JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito1.inputValue}");
        }
        if (null != JSFUtils.resolveExpression("#{bindings.DescripcionLinea.inputValue}")) {
            descripcionLinea = (String) JSFUtils.resolveExpression("#{bindings.DescripcionLinea.inputValue}");
        }
        if(null != JSFUtils.resolveExpression("#{bindings.MontoLinea1.inputValue}")){
            sMontoLinea = String.valueOf(JSFUtils.resolveExpression("#{bindings.MontoLinea1.inputValue}"));
        }else{
            logger.warning("El valor del monto de la linea es nulo.");
        }
        
        if (null != sMontoLinea) {
            try {
                logger.info("+++++++++++++ sMontoLineaCredito: " + sMontoLinea);
                montoLinea = stringToBigDecimal(FORMATO_MONTO_LINEA, sMontoLinea);
                logger.warning("Valor del monto de la nueva linea :" + montoLinea);
            } catch (ParseException e) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage("No es posible convertir el monto de l\u00EDnea".concat(operationBinding.getErrors().toString()));
            }
        }

        String idOperacion = null;
        Long lnIdOperacion = null;
        try {
            idOperacion = (String) JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");
            if (idOperacion != null) {
                lnIdOperacion = Long.valueOf(idOperacion);
            } else {
                logger.severe("El Id de Operacion es NULL");
            }
        } catch (Exception e) {
            logger.severe("Error al obtener el Id de Operacion", e);
        }
        
        if(null != JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}")){
           montoEscriturado = (BigDecimal)JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}");
        }else{
           logger.warning("El valor del monto escriturado es nulo.");
        }
        if(null != montoEscriturado && null != montoLinea){
            logger.warning("Valor MontoEscriturado :" + montoEscriturado);
            logger.warning("Valor MontoTotalLinea :" + montoLinea);
            if(montoLinea.compareTo(montoEscriturado) == 1){
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG03_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO", BUNDLE));
            }
        }
        
        if (null != JSFUtils.resolveExpression("#{bindings.IdTipoMoneda1.attributeValue}")) {
            idTipoMonedaMontoLinea = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdTipoMoneda1.attributeValue}").toString());
        }
        
        logger.warning("Parametros llamado crearLineaCreditoWS");
        logger.warning("Valor numeroLineaCredito :" + numeroLineaCredito);
        logger.warning("Valor descripcionLinea :" + descripcionLinea);
        logger.warning("Valor MontoTotalLinea :" + montoLinea);
        logger.warning("Valor idContrato :" + idContrato);
        logger.warning("Valor lnIdOperacion :" + lnIdOperacion);
        logger.warning("Valor idTipoMonedaMontoLinea :" + idTipoMonedaMontoLinea);
        
        //Modificacion que atiende incidencia FNXII-4537
        //operationBinding = bindings.getOperationBinding("crearLineaCreditoSP");
        operationBinding = bindings.getOperationBinding("crearLineaCreditoWS");

        //Se envian los parametros del metodo crearTramoFormalizar
        operationBinding.getParamsMap().put("numeroLineaCredito", numeroLineaCredito);
        operationBinding.getParamsMap().put("descripcionLinea", descripcionLinea);
        operationBinding.getParamsMap().put("montoLinea", montoLinea);
        operationBinding.getParamsMap().put("idContrato", idContrato);
        operationBinding.getParamsMap().put("idOperacion", lnIdOperacion);
        operationBinding.getParamsMap().put("idTipoMonedaMontoLinea", idTipoMonedaMontoLinea);
        if (esDatosCorrectos) {
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                //JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                logger.severe("Muestra mensaje de error al crear la linea de credito");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_CREAR_LINEA_CREDITO"));
                logger.warning("Error de operationBinding");
                for(Object o : operationBinding.getErrors())
                {
                    logger.warning(o.toString());
                }
            } else {
                Boolean result = (Boolean) operationBinding.getResult();
                if (result != null && result) {
                    // Muestra mensaje de exito
                    logger.warning("Muestra mensaje de exito al crear la linea de credito");
                    JSFUtils.addFacesInformationMessage(getStringFromBundle("EXITO_CREAR_LINEA_CREDITO"));
                } else {
                    logger.severe("Muestra mensaje de error al crear la linea de credito");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_CREAR_LINEA_CREDITO"));
                }
                // Actualizar los valores de linea de credito
                this.recargarLineaCredito();
                // Cierra el popupAgregarTramo
                getPopupAgregarLineaCredito().hide();
            }

            //Elimina el registro temporal de la VO que sirvio para trabajar el popup
            operationBinding = null;
            operationBinding = bindings.getOperationBinding("DeleteAgregarLineaCredito");
            if (operationBinding != null) {
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.severe("Error al eliminar el Row temporal de Linea de Credito");
                }
            }

            //Refresca tabla de líneas de crédito e información de la misma.
            AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaDatosLineaCredUIC());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getFormDatosLineaCreditoUIC());
        }
        logger.warning("Finaliza aceptarAgregarLineaCreditoActionListener");
    }

    @SuppressWarnings("unchecked")
    public void modificarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside modificarLineaCreditoActionListener");
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

        if (getFormAgregarEditarLineaUIC() != null) {
            logger.warning("Refresca Formulario del Popup");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getFormAgregarEditarLineaUIC());
        }

        if (getPopupModificarLineaCredito() != null) {
            logger.warning("Refresca Popup");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupModificarLineaCredito());
        }
    }

    public void cancelarModificarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarModificarLineaCreditoActionListener");
        // Cerramos PopupModificarLineaCredito
        //getPopupModificarLineaCredito().hide();

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
        if (operationBinding != null) {

            Long idLinea = null;
            if (ADFUtils.getBoundAttributeValue("Id3") != null) {
                try {
                    idLinea = Long.valueOf(ADFUtils.getBoundAttributeValue("Id3").toString());
                } catch (Exception e) {
                    logger.severe("Error al obtener el Id de la Linea de Credito");
                }
            }

            String strNumLinea = null;
            if (ADFUtils.getBoundAttributeValue("NumeroLineaCredito2") != null) {
                strNumLinea = ADFUtils.getBoundAttributeValue("NumeroLineaCredito2").toString();
            }

            String strDesc = null;
            if (ADFUtils.getBoundAttributeValue("Descripcion1") != null) {
                strDesc = ADFUtils.getBoundAttributeValue("Descripcion1").toString();
            }

            BigDecimal bigMonto = null;
            if (ADFUtils.getBoundAttributeValue("MontoLinea2") != null) {
                String strNumber = ADFUtils.getBoundAttributeValue("MontoLinea2").toString();
                try {
                    bigMonto = BigDecimal.valueOf(NumberFormat.getInstance().parse(strNumber).doubleValue());
                } catch (Exception e) {
                    logger.severe("Error al obtener y convertir monto de la linea de credito");
                }
            }

            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else {

                ADFUtils.setBoundAttributeValue("NumeroLineaCredito1", strNumLinea);
                ADFUtils.setBoundAttributeValue("DescripcionLinea", strDesc);
                ADFUtils.setBoundAttributeValue("MontoLinea1", bigMonto);
                ADFUtils.setBoundAttributeValue("Id2", idLinea);

                logger.warning("Creacion y asignacion del Row para Editar la linea concluida con exito");
            }
        }
    }

    public void aceptarModificarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside aceptarModificarLineaCreditoActionListener");
        logger.warning("Inside aceptarModificarLineaCreditoActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idLineaCredito = null;
        String numeroLineaCredito = null;
        String descripcionLineaCredito = null;
        BigDecimal montoLineaCredito = null;
        BigDecimal montoEscriturado = null;
        Boolean esDatosCorrectos = Boolean.TRUE;
        //idLineaCredito, recuperar el valor dentro del popup
        if (null != JSFUtils.resolveExpression("#{bindings.Id2.inputValue}")) {
            idLineaCredito = (Long) JSFUtils.resolveExpression("#{bindings.Id2.inputValue}");
        }
        //numeroLineaCredito, recuperar el valor dentro del popup
        if (null != JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito1.inputValue}")) {
            numeroLineaCredito = (String) JSFUtils.resolveExpression("#{bindings.NumeroLineaCredito1.inputValue}");
        }
        //descripcionLineaCredito, recupera el valor dentro del popup
        if (null != JSFUtils.resolveExpression("#{bindings.DescripcionLinea.inputValue}")) {
            descripcionLineaCredito = (String) JSFUtils.resolveExpression("#{bindings.DescripcionLinea.inputValue}");
        }
        //montoLineaCredito, recuperar el valor dentro del popup
        if (null != JSFUtils.resolveExpression("#{bindings.MontoLinea1.inputValue}")) {
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
        if (ADFUtils.getBoundAttributeValue("IdContrato1") != null) {
            try {
                idContrato = new Long(ADFUtils.getBoundAttributeValue("IdContrato1").toString());
            } catch (Exception e) {
                logger.severe("Error al leer el atributo de Id Contrato");
            }
        }
        if(null != JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}")){
           montoEscriturado = (BigDecimal)JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}");
        }else{
           logger.warning("El valor del monto escriturado es nulo.");
        }
        if(null != montoEscriturado && null != montoLineaCredito){
            logger.warning("Valor MontoEscriturado :" + montoEscriturado);
            if(montoLineaCredito.compareTo(montoEscriturado) == 1){
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG03_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO", BUNDLE));
            }
        }

        operationBinding = bindings.getOperationBinding("modificarLineaCreditoPorContrato");
        //Se envian los parametros del metodo modificarLineaCredito
        operationBinding.getParamsMap().put("idContrato", idContrato);
        operationBinding.getParamsMap().put("idLineaCredito", idLineaCredito);
        operationBinding.getParamsMap().put("numeroLineaCredito", numeroLineaCredito);
        operationBinding.getParamsMap().put("descripcionLineaCredito", descripcionLineaCredito);
        operationBinding.getParamsMap().put("montoLineaCredito", montoLineaCredito);
        if (esDatosCorrectos) {
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {

            //Actualizar linea de credito
            this.recargarLineaCredito();

            // Cerramos PopupModificarLineaCredito
            getPopupModificarLineaCredito().hide();

            //Mensaje de exito
            JSFUtils.addFacesInformationMessage("Se modifico con \u00E9xito.");
        }

        //Elimina el registro temporal de la VO que sirvio para trabajar el popup
        operationBinding = null;
        operationBinding = bindings.getOperationBinding("DeleteAgregarLineaCredito");
        if (operationBinding != null) {
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.severe("Error al eliminar el Row temporal de Linea de Credito");
            }
        }

        //Refresca tabla de Lineas de credito.
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaDatosLineaCredUIC());
        }
    }

    public void eliminarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside eliminarLineaCreditoActionListener");
        RichPopup.PopupHints popupHints = null;
        // Abrimos popupAgregarLineaCredito
        popupHints = new RichPopup.PopupHints();
        getPopupEliminarLineaCredito().show(popupHints);
    }

    public void cancelarEliminarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarEliminarLineaCreditoActionListener");
        // Cerramos PopupEliminarLineaCredito
        getPopupEliminarLineaCredito().hide();
    }

    public void aceptarEliminarLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside aceptarEliminarLineaCreditoActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idLineaCredito = null;
        //Recuperar el id que se va eliminar
        if (JSFUtils.resolveExpression("#{bindings.Id3.inputValue}") != null) {
            idLineaCredito = (Long) JSFUtils.resolveExpression("#{bindings.Id3.inputValue}");
        } else {
            logger.warning("idLineaCredito es nulo.");
        }
        
        operationBinding = bindings.getOperationBinding("eliminarLineaCredito");
        //Se envian los parametros del metodo modificarLineaCredito
        operationBinding.getParamsMap().put("idLineaCredito", idLineaCredito); //InstanciaProceso(BPM)
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            // Cerramos PopupEliminarLineaCredito
            getPopupEliminarLineaCredito().hide();
            //Actualizar linea de credito
            this.recargarLineaCredito();
            //Mensaje de exito
            JSFUtils.addFacesInformationMessage("Se elimino con éxito.");
        }
    }

    public void recargarLineaCredito() {
        logger.log(ADFLogger.TRACE, "Inside recargarLineaCredito");
        logger.warning("Inside recargarLineaCredito");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String idOperacion = null;
        Integer idContratoLote = null;
        // Limpiar la tabla DatosLineaCreditoVO
        operationBinding = bindings.getOperationBinding("limpiarDatosLineaCredito");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Actualizamos datos del encabezado y Líneas de crédito (se cargan todos los datos de nuevo)
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")){
            idOperacion = (String)JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");
        }else{
            logger.warning("La operacion ses nula.");
        }
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoLote}")){
            idContratoLote = (Integer)JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoLote}");
        }else{
            logger.warning("La operacion ses nula.");
        }
        operationBinding = bindings.getOperationBinding("consultarLineaCredito");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idContrato", idContratoLote);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        this.consultarLineaCredito();
    }

    @SuppressWarnings("unchecked")
    public void obtenerFechaInicio(ValueChangeEvent valueChangeEvent) {

        if (null != valueChangeEvent) {

            logger.warning("valor de tipo fecha de inicio: " + valueChangeEvent.getNewValue());

            Integer tipofechaInicio = null;
            Long idOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());

            //Obliga actualizar el modelo
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

            if (null != valueChangeEvent.getNewValue()) {

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
                try {
                    lngIdTipoFechaInicio = (Long) ADFUtils.getBoundAttributeValue("IdTipoDeFecha");
                    if (lngIdTipoFechaInicio != null) {
                        tipofechaInicio = new Integer(lngIdTipoFechaInicio.intValue());
                    } else {
                        logger.severe("El Id de Fecha Inicio es NULL");
                    }
                } catch (Exception e) {
                    logger.severe("No se pudo obtener el Id de Fecha Inicio de Vencimiento Plazo");
                }

                if (null != tipofechaInicio && null != idOperacion) {
                    BindingContainer bindings = ADFUtils.getBindingContainer();
                    OperationBinding operationBinding = bindings.getOperationBinding("obtenerTipoFechaInicio");
                    operationBinding.getParamsMap().put("idFechaInicio", tipofechaInicio);
                    operationBinding.getParamsMap().put("idOperacion", idOperacion);
                    operationBinding.execute();

                    if (!operationBinding.getErrors().isEmpty()) {
                        logger.warning("Error al obtener la fecha de inicio");
                    } else {

                        AttributeBinding fechaInicioBinding;
                        fechaInicioBinding = ADFUtils.findControlBinding("FechaInicio");

                        if (null != operationBinding.getResult()) {
                            Timestamp fechaInicio = Timestamp.valueOf(operationBinding.getResult().toString());
                            logger.warning("Fecha obtenida del modelo: " + fechaInicio);
                            fechaInicioBinding.setInputValue(fechaInicio);
                        } else {
                            fechaInicioBinding.setInputValue(null);
                        }
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaInicioUIC());
                    }
                }
            }
        }
    }
    
    public void tipoFrecuenciaChangeListener(ValueChangeEvent valueChangeEvent) {

        if (valueChangeEvent == null) {
            return;
        }

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

        String strAlVenciValue = "Al Vencimiento";
        String value = null;
        try {
            value = (String) ADFUtils.getBoundAttributeValue("TipoFrecuencia");
        } catch (Exception e) {
            logger.warning("Error al obtener el valor de Tipo Frecuencia");
        }

        String strFrecuencia = null;
        try {
            strFrecuencia = (String) ADFUtils.getBoundAttributeValue("Frecuencia");
        } catch (Exception e) {
            logger.warning("Error al obtener el valor de Frecuencia");
        }

        if (value != null && strAlVenciValue.equals(value) && !"".equals(strFrecuencia)) {
            ADFUtils.setBoundAttributeValue("Frecuencia", "1");
            AdfFacesContext.getCurrentInstance().addPartialTarget(comisionFrecuenciaUIC);
        }
    }
    
    public void validaTasaMinimaValueChangeListener(ValueChangeEvent vce) {
        logger.warning("Entra en validaTasaMinimaValueChangeListener");
        Double tasaMinima = null;
        Double tasaMaxima = null;
        AttributeBinding vceAux = null;
        
        try {
            if (null != JSFUtils.resolveExpression("#{bindings.TasaMaxima.inputValue}")) {
                tasaMaxima = (Double) JSFUtils.resolveExpression("#{bindings.TasaMaxima.inputValue}");
            } else {
                logger.warning("La tasa maxima es nula.");
            }
            if (null != vce) {
                tasaMinima = (Double) vce.getNewValue();
                if (null != tasaMinima) {
                    if (tasaMinima.compareTo(new Double(0)) == 1 && tasaMinima.compareTo(new Double(100)) == -1) {
                        if (null != tasaMaxima) {
                            if (tasaMinima.compareTo(tasaMaxima) == 1) {
                                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_TASA_MINIMA_MENOR_TASA_MAXIMA_ROIDLC",
                                                                                           BUNDLE));
                                vceAux = ADFUtils.findControlBinding("TasaMinima");
                                vceAux.setInputValue(null);
                                resetValueTextComponent(uiTasaMinima);
                            } else {
                                logger.warning("La tasa minima cumple con las restricciones.");
                            }
                        } else {
                            logger.warning("No se realiza comparacion ya que la tasa maxima es nula.");
                        }
                    } else {
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_TASA_MINIMA_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO",
                                                                                   BUNDLE));
                        vceAux = ADFUtils.findControlBinding("TasaMinima");
                        vceAux.setInputValue(null);
                        resetValueTextComponent(uiTasaMinima);
                    }
                } else {
                    logger.warning("La tasa minima es nula.");
                }
            } else {
                logger.warning("El nuevo valor es nulo.");
            }
        } catch (Exception e) {
            logger.warning("Error al validar la tasa minima.", e);
        }
    }

    public void validaTasaMaximaValueChangeListener(ValueChangeEvent vce) {
        logger.warning("Entra en validaTasaMaximaValueChangeListener");
        Double tasaMinima = null;
        Double tasaMaxima = null;
        AttributeBinding vceAux = null;
        try {
            if (null != JSFUtils.resolveExpression("#{bindings.TasaMinima.inputValue}")) {
                tasaMinima = (Double) JSFUtils.resolveExpression("#{bindings.TasaMinima.inputValue}");
            } else {
                logger.warning("La tasa minima es nula.");
            }
            if (null != vce) {
                tasaMaxima = (Double) vce.getNewValue();
                if (null != tasaMaxima) {
                    if (tasaMaxima.compareTo(new Double(0)) == 1 && (tasaMaxima.compareTo(new Double(100)) == -1) ||
                        (tasaMaxima.compareTo(new Double(100)) == 0)) {
                        if (null != tasaMinima) {
                            if (tasaMinima.compareTo(tasaMaxima) == 1) {
                                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_TASA_MAXIMA_MENOR_TASA_MINIMA_ROIDLC",
                                                                                           BUNDLE));
                                vceAux = ADFUtils.findControlBinding("TasaMaxima");
                                vceAux.setInputValue(null);
                                resetValueTextComponent(uiTasaMaxima);
                            } else {
                                logger.warning("La tasa maxima cumple con las restricciones.");
                            }
                        } else {
                            logger.warning("No se realiza comparacion ya que la tasa minima es nula.");
                        }
                    } else {
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_TASA_MAXIMA_REGISTRAR_ORDEN_INICIO_DATOS_LINEA_CREDITO",
                                                                                   BUNDLE));
                        vceAux = ADFUtils.findControlBinding("TasaMaxima");
                        vceAux.setInputValue(null);
                        resetValueTextComponent(uiTasaMaxima);
                    }
                } else {
                    logger.warning("La tasa maxima es nula.");
                }
            } else {
                logger.warning("El nuevo valor es nulo.");
            }
        } catch (Exception e) {
            logger.warning("Error al validar la tasa maxima.", e);
        }
    }

    public void validaMontoMinimoValueChangeListener(ValueChangeEvent vce) {
        logger.warning("Entra en validaMontoMinimoValueChangeListener");
        Double montoMinimo = null;
        Double montoMaximo = null;
        AttributeBinding vceAux = null;
        
        try {
            if (null != JSFUtils.resolveExpression("#{bindings.MontoMaximo.inputValue}")) {
                montoMaximo = (Double) JSFUtils.resolveExpression("#{bindings.MontoMaximo.inputValue}");
            } else {
                logger.warning("El monto maximo es nulo.");
            }
            if (null != vce) {
                montoMinimo = (Double) vce.getNewValue();
                if (null != montoMinimo) {
                    if (montoMinimo.compareTo(new Double(0)) == 1) {
                        if (null != montoMaximo) {
                            if (montoMinimo.compareTo(montoMaximo) == 1 || montoMinimo.compareTo(montoMaximo) == 0) {
                                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_MONTO_MINIMO_MAYOR_IGUAL_MONTO_MAXIMO",
                                                                                           BUNDLE));
                                vceAux = ADFUtils.findControlBinding("MontoMinimo");
                                vceAux.setInputValue(null);
                                resetValueTextComponent(uiMontoMnimo);
                            } else {
                                logger.warning("El monto minimo cumple con las restricciones.");
                            }
                        } else {
                            logger.warning("No se realiza comparacion ya que el monto maximo es nulo.");
                        }
                    } else {
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_MONTO_MENOR_CERO", BUNDLE));
                        vceAux = ADFUtils.findControlBinding("MontoMinimo");
                        vceAux.setInputValue(null);
                        resetValueTextComponent(uiMontoMnimo);
                    }
                } else {
                    logger.warning("El monto minimo es nulo.");
                }
            } else {
                logger.warning("El nuevo valor es nulo.");
            }
        } catch (Exception e) {
            logger.warning("Error al validar el monto minimo.", e);
        }
    }

    public void validaMontoMaximoValueChangeListener(ValueChangeEvent vce) {
        logger.warning("Entra en validaMontoMaximoValueChangeListener");
        Double montoMinimo = null;
        Double montoMaximo = null;
        BigDecimal montoEscrituradoAux = null;
        Double montoEscriturado = null;
        AttributeBinding vceAux = null;
        
        try {
            if (null != JSFUtils.resolveExpression("#{bindings.MontoMinimo.inputValue}")) {
                montoMinimo = (Double) JSFUtils.resolveExpression("#{bindings.MontoMinimo.inputValue}");
            } else {
                logger.warning("El monto minimo es nulo.");
            }
            if (null != JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}")) {
                montoEscrituradoAux =
                    (BigDecimal) JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}");
                montoEscriturado = montoEscrituradoAux.doubleValue();
            } else {
                logger.warning("El monto escriturado es nulo.");
            }
            if (null != vce) {
                montoMaximo = (Double) vce.getNewValue();
                if (null != montoMaximo) {
                    if (montoMaximo.compareTo(new Double(0)) == 1) {
                        if (null != montoMinimo) {
                            if (montoMinimo.compareTo(montoMaximo) == 1 || montoMinimo.compareTo(montoMaximo) == 0) {
                                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_MONTO_MAXIMO_MENOR_IGUAL_MONTO_MINIMO",
                                                                                           BUNDLE));
                                vceAux = ADFUtils.findControlBinding("MontoMaximo");
                                vceAux.setInputValue(null);
                                resetValueTextComponent(uiMontoMaximo);
                            } else {
                                logger.warning("El monto minimo cumple con las restricciones.");
                            }
                        } else {
                            logger.warning("No se realiza comparacion ya que el monto minimo es nulo.");
                        }
                        if (null != montoEscriturado) {
                            if (montoMaximo.compareTo(montoEscriturado) == 1) {
                                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_MONTO_MAXIMO_MENOR_IGUAL_MONTO_ECRITURADO",
                                                                                           BUNDLE));
                                vceAux = ADFUtils.findControlBinding("MontoMaximo");
                                vceAux.setInputValue(null);
                                resetValueTextComponent(uiMontoMaximo);
                            } else {
                                logger.warning("El monto escriturado cumple con las restricciones.");
                            }
                        } else {
                            logger.warning("No se realiza comparacion ya que el monto escriturado es nulo.");
                        }
                    } else {
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_MONTO_MENOR_CERO", BUNDLE));
                        vceAux = ADFUtils.findControlBinding("MontoMaximo");
                        vceAux.setInputValue(null);
                        resetValueTextComponent(uiMontoMaximo);
                    }
                } else {
                    logger.warning("El monto maximo es nulo.");
                }
            } else {
                logger.warning("El nuevo valor es nulo.");
            }
        } catch (Exception e) {
            logger.warning("Error al validar el monto escriturado.", e);
        }
    }
    
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }

    /**
     *M&eacute;todo para convertir una cadena de texto a BigDecimal
     * @param patternFormat patron de formato
     * @param sNumber n&uacute;mero con formato
     * @return n&uacute;mero como BigDecimal
     * @throws ParseException
     */
    public static BigDecimal stringToBigDecimal(String patternFormat, String sNumber) throws ParseException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(CARACTER_COMA);
        symbols.setDecimalSeparator(CARACTER_PUNTO);
        DecimalFormat decimalFormat = new DecimalFormat(); //(patternFormat, symbols);
        decimalFormat.setParseBigDecimal(true);
        BigDecimal bdNumber = (BigDecimal) decimalFormat.parse(sNumber);
        return bdNumber;
    }
    
    public void resetValueTextComponent(RichInputText uiValue) {
        logger.warning("inside resetValueDateComponent.");
        
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        uiValue.resetValue();
        adfFacesContext.addPartialTarget(uiValue);
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
    
    public void setPopupEliminar(RichPopup popupEliminar) {
        this.popupEliminar = popupEliminar;
    }

    public RichPopup getPopupEliminar() {
        return popupEliminar;
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


    public void setUiTasaMinima(RichInputText uiTasaMinima) {
        this.uiTasaMinima = uiTasaMinima;
    }

    public RichInputText getUiTasaMinima() {
        return uiTasaMinima;
    }

    public void setUiTasaMaxima(RichInputText uiTasaMaxima) {
        this.uiTasaMaxima = uiTasaMaxima;
    }

    public RichInputText getUiTasaMaxima() {
        return uiTasaMaxima;
    }

    public void setUiMontoMnimo(RichInputText uiMontoMnimo) {
        this.uiMontoMnimo = uiMontoMnimo;
    }

    public RichInputText getUiMontoMnimo() {
        return uiMontoMnimo;
    }

    public void setUiMontoMaximo(RichInputText uiMontoMaximo) {
        this.uiMontoMaximo = uiMontoMaximo;
    }

    public RichInputText getUiMontoMaximo() {
        return uiMontoMaximo;
    }

    public void setUiMontoLinea(RichInputText uiMontoLinea) {
        this.uiMontoLinea = uiMontoLinea;
    }

    public RichInputText getUiMontoLinea() {
        return uiMontoLinea;
    }

    public void setUiNumeroLineaCredito(RichInputText uiNumeroLineaCredito) {
        this.uiNumeroLineaCredito = uiNumeroLineaCredito;
    }

    public RichInputText getUiNumeroLineaCredito() {
        return uiNumeroLineaCredito;
    }

    public void setUiDescripcionLinea(RichInputText uiDescripcionLinea) {
        this.uiDescripcionLinea = uiDescripcionLinea;
    }

    public RichInputText getUiDescripcionLinea() {
        return uiDescripcionLinea;
    }
}
