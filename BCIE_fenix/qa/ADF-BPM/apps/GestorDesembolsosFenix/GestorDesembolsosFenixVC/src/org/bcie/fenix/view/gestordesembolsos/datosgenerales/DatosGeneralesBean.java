package org.bcie.fenix.view.gestordesembolsos.datosgenerales;

import java.io.Serializable;

import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class DatosGeneralesBean implements Serializable {
    @SuppressWarnings("compatibility:-34166753875220157")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    private Integer estadoSolicitud;
    private Integer codigoTarea;
    private Boolean esEscritura;
    private Long claveOperacion;
    private Long claveContrato;
    private Integer modalidad;
    private Long idSolicitud;
    private Long idLineaCredito;
    private Boolean cargarInformacion;
    private String estatusTareaBpm = "";
    public static final Integer MODO_EJECUCION_LECTURA = 1;
    private transient RichPopup ppComponentes;


    public void cargarEstado() {
        try {
            modalidad = (Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pModalidad}").toString()));
            logger.warning("modalidad " + modalidad);
        } catch (Exception ex) {
            logger.warning("modalidad no obtenido");
        }
        try {
            claveContrato = (Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.pIcContratoDe}").toString()));
            logger.warning("claveContrato " + claveContrato);
        } catch (Exception ex) {
            logger.warning("claveContrato no obtenido");
        }
        try {
            claveOperacion = (Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString()));
            logger.warning("claveOperacion " + claveOperacion);
        } catch (Exception ex) {
            logger.warning("claveOperacion no obtenido");
        }
        try {
            estadoSolicitud =
                (Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pEstadoSolicitud}").toString()));
            logger.warning("estadoSolicitud " + estadoSolicitud);
        } catch (Exception ex) {
            logger.warning("estado no obtenido");
        }
        try {
            codigoTarea = (Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pTarea}").toString()));
            logger.warning("codigoTarea " + codigoTarea);
        } catch (Exception ex) {
            logger.warning("tarea no obtenido");
        }
        try {
            idSolicitud = (Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.pSolicitud}").toString()));
            logger.warning("idSolicitud " + idSolicitud);
        } catch (Exception ex) {
            logger.warning("idSolicitud no obtenido");
        }
        try {
            idLineaCredito =
                (Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.pIdLineaCredito}").toString()));
            logger.warning("idLineaCredito " + idLineaCredito);
        } catch (Exception ex) {
            logger.warning("idLineaCredito no obtenido");
        }
        try {
            cargarInformacion =
                new Boolean(JSFUtils.resolveExpression("#{pageFlowScope.pCargarInformacion}").toString());
        } catch (Exception e) {
            logger.warning("CargarInformacion no obtenido.");
        }
        if (null != estadoSolicitud) {
            switch (estadoSolicitud) {
            default:
                esEscritura = Boolean.FALSE;
                break;
            }
        } else {
            esEscritura = Boolean.FALSE;
        }
    }

    public DatosGeneralesBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }


    public void precargaDatosGenerales() {
        logger.warning("Inicia precarga de datos generales");
        cargarTipoTasa();
        cargaDatosDesembolso();
        validarEstatusTareaBpm();
        mostrarConsulta();
        consultarDatosContratoDesembolso();
        JSFUtils.setExpressionValue("#{pageFlowScope.esResponsableOperacion}", esUsuarioResponsableDeOperacion());

        if (modalidad != null && modalidad.compareTo(MODO_EJECUCION_LECTURA) == 0) {
            establecerTabEnModoLectura();
        }

        logger.warning("termina precarga de datos generales");
    }


    public Boolean esUsuarioResponsableDeOperacion() {
        logger.warning("*Inf, inicia metodo esUsuarioResponsableDeOperacion para el nivel de SolicitudDesemboso");

        Long idOperacion = null;
        String usuarioSession = "";
        BindingContainer bindings = null;
        OperationBinding operBinding = null;
        Boolean esResponsableOperacion = Boolean.FALSE;

        //recuperamos el id de la operacionn del taskflow
        if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")) {
            idOperacion = (Long) JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");
        }

        //recuperamos la sesión de usuario
        if (null != JSFUtils.resolveExpression("#{securityContext.userName}")) {
            usuarioSession = (String) JSFUtils.resolveExpression("#{securityContext.userName}");
        }

        logger.warning("Usuario en Session: " + usuarioSession);

        try {
            bindings = ADFUtils.getBindingContainer();
            operBinding = bindings.getOperationBinding("verificarUsuarioComoResponsable");
            operBinding.getParamsMap().put("usuario", usuarioSession);
            operBinding.getParamsMap().put("idOperacion", idOperacion);
            operBinding.execute();
        } catch (Exception e) {
            logger.warning("OperationBinding verificarUsuarioComoResponsable devuelve errores ->", e);
            JSFUtils.addFacesErrorMessage("Error al verificar usuario como responsable->" + e.getMessage());
        }

        if (!operBinding.getErrors().isEmpty()) {
            logger.warning("Error operBinding obtenerDiasInhabilesMoneda ->" + operBinding.getErrors().toString());
            JSFUtils.addFacesErrorMessage("Error operBinding obtenerDiasInhabilesMoneda ->" +
                                          operBinding.getErrors().toString());
        } else {
            esResponsableOperacion = (Boolean) operBinding.getResult();
        }


        logger.warning("*Inf, termina metodo esUsuarioResponsableDeOperacion esResponsableOperacion: " +
                       esResponsableOperacion);
        return esResponsableOperacion;
    }


    public void cargaDatosDesembolso() {
        logger.warning("Inicia metodo cargaDatosDesembolso");

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("cargaDatosDesembolso");
            operBinding.execute();

        } catch (Exception e) {
            logger.warning("!Ha ocurrido un error al ejecutar el operBinding cargarDatosDesembolso ->", e);
        }

        logger.warning("Termina metodo cargaDatosDesembolso");
    }


    public void validarEstatusTareaBpm() {
        logger.warning("Inicia metodo validacionPrecargaDTGenBean");

        try {

            estatusTareaBpm =
                (null == JSFUtils.resolveExpression("#{pageFlowScope.estatusTareaBpm}")) ? "" :
                JSFUtils.resolveExpression("#{pageFlowScope.estatusTareaBpm}").toString();

        } catch (Exception e) {
            logger.warning("ha ocurrido un error al recuperar el estatus de la tarea ->", e);
        }

        logger.warning("estatusTareaBpm: " + estatusTareaBpm);

        //Esta bandera nos indica si la tarea BPM ya finalizo o sige activa
        if (estatusTareaBpm.equalsIgnoreCase("COMPLETED")) { //  COMPLETED - ASSIGNED
            logger.warning("set imputs a lectura: " + estatusTareaBpm);
            establecerTabEnModoLectura();
        }

        logger.warning("Termina metodo validacionPrecargaDTGenBean");
    }


    public void establecerTabEnModoLectura() {
        logger.warning("Inicia metodo establecerTabEnModoLectura");

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("setAtributosPregarcarga");
            operBinding.execute();

        } catch (Exception e) {
            logger.warning("Ha ocurrido un error al ejecutar el operbinding validacionPrecargaDTGen ->" + e);
        }

        logger.warning("termina metodo establecerTabEnModoLectura");
    }


    public void cargarTipoTasa() {
        logger.warning("*Inf, Inicia metodo cargarTipoTasa ");

        Long idOperacion =
            (null == JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")) ? null :
            (Long) JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");

        Long idDesembolso =
            (null == JSFUtils.resolveExpression("#{pageFlowScope.pIcContratoDe}")) ? null :
            (Long) JSFUtils.resolveExpression("#{pageFlowScope.pIcContratoDe}");


        if (idOperacion == null || idDesembolso == null) {
            logger.warning("*Inf, Importante parametros requeridos resuletos a null");
            logger.warning("idOperacion: " + idOperacion);
            logger.warning("idDesembolso: " + idDesembolso);
        } else {


            try {

                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("crearRow");
                operBinding.getParamsMap().put("idContratoDesembolso", idDesembolso);
                operBinding.getParamsMap().put("idOperacion", idOperacion);
                operBinding.execute();

                if (!operBinding.getErrors().isEmpty()) {
                    logger.warning("***Error, al ejecutar operation Binding crearRow  en condiciones financieras ->" +
                                   operBinding.getErrors());
                }

            } catch (Exception e) {
                logger.log(ADFLogger.WARNING, "Error en setCurrentRow", e);
            }
        }


        logger.warning("*Inf, Termina metodo cargarTipoTasa ");
    }


    // ----------------------- S&P: INICIA  CUSTOM METHODS PARA MULTISECTORIAL ----------------------------------------------------------------- //

    /**
     * Método auxiliar para la mostrar los componentes.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 13/06/2019
     */
    public void mostrarComponentes(ActionEvent actionEvent) {
        //Mostrar Popup
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        ppComponentes.show(ph);
    }


    /**
     * Método auxiliar de seleccion de componente y actualizacion en DB.
     * @author : S&P Solutions
     * @param  : ActionEvent
     * @version: v1.0
     * @Fecha  : 13/06/2019
     */
    public void seleccionarComponente(ActionEvent actionEvent) {
        boolean banRow = false;
        Long IdDesembolso = null;
        Long IdEstado = null;
        Long IdClasificacion = null;
        Long IdActividadEconomica = null;
        Long IdAreaFocalizacion = null;
        Long IdEjeEstrategico = null;
        Long IdIniciativaEstrategica = null;
        Long IdCantidadPaises = null;
        String nombre = null;
        String descripcion = null;
        String porcentaje = null;
        String actividadEconomica = null;
        String iniciativaEstrategica = null;
        String cantidadPaises = null;
        String areaFocalizacion = null;
        String ejeEstrategico = null;

        logger.warning(" == Dentro de seleccionarComponente ==");
        ppComponentes.hide();

        if (JSFUtils.resolveExpression("#{bindings.IdDesembolso.inputValue}") != null) {
            IdDesembolso = Long.parseLong(JSFUtils.resolveExpression("#{bindings.IdDesembolso.inputValue}").toString());
            //banIdDesembolso = true;

            if (JSFUtils.resolveExpression("#{bindings.IdEstadoContrato.inputValue}") != null) {
                IdEstado =
                    Long.parseLong(JSFUtils.resolveExpression("#{bindings.IdEstadoContrato.inputValue}").toString());
                //banIdEstado=true;

                //Recuperar info de Row Seleccionado
                Row selectedRow =
                    (Row) JSFUtils.resolveExpression("#{bindings.DatosDesembolsoClasificacionEstrategicaVO1Iterator.currentRow}");
                if (selectedRow != null) {
                    //Obtener valores
                    nombre = selectedRow.getAttribute("NombreDelComponente").toString();
                    descripcion = selectedRow.getAttribute("DescripcionDelComponente").toString();
                    porcentaje = selectedRow.getAttribute("Porcentaje").toString();
                    actividadEconomica = selectedRow.getAttribute("ActividadEconomica").toString();
                    iniciativaEstrategica = selectedRow.getAttribute("IniciativaEstrategica").toString();
                    cantidadPaises = selectedRow.getAttribute("CantidadPaises").toString();
                    areaFocalizacion = selectedRow.getAttribute("AreaFocalizacion").toString();
                    ejeEstrategico = selectedRow.getAttribute("EjeEstrategico").toString();

                    if (selectedRow.getAttribute("IdActividadEconomica") != null) {
                        IdActividadEconomica =
                            Long.parseLong(selectedRow.getAttribute("IdActividadEconomica").toString());
                        banRow = true;
                    } else {
                        banRow = false;
                    }

                    if (selectedRow.getAttribute("IdIniciativaEstrategica") != null) {
                        IdIniciativaEstrategica =
                            Long.parseLong(selectedRow.getAttribute("IdIniciativaEstrategica").toString());
                        banRow = true;
                    } else {
                        banRow = false;
                    }
                    if (selectedRow.getAttribute("IdCantidadPaises") != null) {
                        IdCantidadPaises = Long.parseLong(selectedRow.getAttribute("IdCantidadPaises").toString());
                        banRow = true;
                    } else {
                        banRow = false;
                    }
                    if (selectedRow.getAttribute("IdAreaFocalizacion") != null) {
                        IdAreaFocalizacion = Long.parseLong(selectedRow.getAttribute("IdAreaFocalizacion").toString());
                        banRow = true;
                    } else {
                        banRow = false;
                    }
                    if (selectedRow.getAttribute("IdEjeEstrategico") != null) {
                        IdEjeEstrategico = Long.parseLong(selectedRow.getAttribute("IdEjeEstrategico").toString());
                        banRow = true;
                    } else {
                        banRow = false;
                    }

                    if (selectedRow.getAttribute("IdClasificacion") != null) {
                        IdClasificacion = Long.parseLong(selectedRow.getAttribute("IdClasificacion").toString());
                        banRow = true;
                    } else {
                        banRow = false;
                    }

                    if (banRow) {
                        //Actualizar valores en DB
                        logger.warning(" ==  seleccionarComponente | actualizarValores ==");
                        logger.warning(" ==  seleccionarComponente | Valores enviados a metodo ==");
                        logger.warning(" ==  seleccionarComponente | (1) IdDesembolso:" + IdDesembolso);
                        logger.warning(" ==  seleccionarComponente | (2) IdEstado:" + IdEstado);
                        logger.warning(" ==  seleccionarComponente | (3) IdClasificacion:" + IdClasificacion);
                        logger.warning(" ==  seleccionarComponente | (4) IdActividadEconomica:" + IdActividadEconomica);
                        logger.warning(" ==  seleccionarComponente | (5) IdAreaFocalizacion:" + IdAreaFocalizacion);
                        logger.warning(" ==  seleccionarComponente | (6) IdEjeEstrategico:" + IdEjeEstrategico);

                        try {
                            BindingContainer binding = ADFUtils.getBindingContainer();
                            OperationBinding operBinding = binding.getOperationBinding("actualizarDatosDesembolso");

                            operBinding.getParamsMap().put("IdDesembolso", IdDesembolso);
                            operBinding.getParamsMap().put("IdEstado", IdEstado);
                            operBinding.getParamsMap().put("IdClasificacion", IdClasificacion);
                            operBinding.getParamsMap().put("IdActividadEconomica", IdActividadEconomica);
                            operBinding.getParamsMap().put("IdAreaFocalizacion", IdAreaFocalizacion);
                            operBinding.getParamsMap().put("IdEjeEstrategico", IdEjeEstrategico);

                            operBinding.execute();

                        } catch (Exception e) {
                            logger.warning("=== Ha ocurrido un error al ejecutar el operBinding actualizarDatosDesembolso ->",
                                           e);
                        }
                        logger.warning(" == Concluye metodo actualizarValores ==");


                        JSFUtils.setExpressionValue("#{pageFlowScope.nombreCE}", nombre);
                        JSFUtils.setExpressionValue("#{pageFlowScope.descripcionCE}", descripcion);
                        JSFUtils.setExpressionValue("#{pageFlowScope.porcentajeCE}", porcentaje);
                        JSFUtils.setExpressionValue("#{pageFlowScope.actividadEconomicaCE}", actividadEconomica);
                        JSFUtils.setExpressionValue("#{pageFlowScope.areaFocalizacionCE}", areaFocalizacion);
                        JSFUtils.setExpressionValue("#{pageFlowScope.ejeEstrategicoCE}", ejeEstrategico);
                        
                        
                        //Mostrar valores en interfaz
                        JSFUtils.setExpressionValue("#{bindings.ce_Nombre.inputValue}", nombre);
                        JSFUtils.setExpressionValue("#{bindings.ce_Descripcion.inputValue}", descripcion);
                        JSFUtils.setExpressionValue("#{bindings.ce_Porcentaje.inputValue}", porcentaje);
                        JSFUtils.setExpressionValue("#{bindings.ce_ActividadEconomica.inputValue}", actividadEconomica);
                        JSFUtils.setExpressionValue("#{bindings.ce_AreaFocalizacion.inputValue}", areaFocalizacion);
                        JSFUtils.setExpressionValue("#{bindings.ce_EjeEstrategico.inputValue}", ejeEstrategico);
                        
                        refrescarComponente("pflValCE");

                    } else {
                        logger.warning(" == Dentro de seleccionarComponente | Alguno de los valores requeridos de Row en NULL ==");
                        //Levantar mensaje
                        JSFUtils.addFacesErrorMessage("Error al actualizar: Valor seleccionado resuelto a nulo");
                    }
                }

            } else {
                logger.warning(" == Dentro de seleccionarComponente | IdEstado NULL ==");
                //Levantar mensaje
                JSFUtils.addFacesErrorMessage("Error al actualizar: IdEstado resuelto a nulo");
            }


        } else {
            logger.warning(" == Dentro de seleccionarComponente | IdDesembolso NULL ==");
            //Levantar mensaje
            JSFUtils.addFacesErrorMessage("Error al actualizar: IdDesembolso resuelto a nulo");
        }


    }

    /**
     * Método que refrescar en interfaz un componente dado su id.
     * @author : S&P Solutions
     * @param  : String
     * @return :
     * @version: v1.0
     * @Fecha  : 30/07/2019
     */
    public void refrescarComponente(String id) {
        UIComponent ui = obtenerComponente(id);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }


    /**
     * Método que busca y retorna un componente dado su id.
     * @author : S&P Solutions
     * @param  : String
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 29/06/2019
     */
    public UIComponent obtenerComponente(String id) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        UIComponent ui = findComponent(facesCtx.getViewRoot(), id);
        return ui;
    }

    private UIComponent findComponent(UIComponent base, String id) {

        if (id.equals(base.getId())) {
            return base;
        }

        UIComponent children = null;
        UIComponent result = null;
        Iterator childrens = base.getFacetsAndChildren();
        while (childrens.hasNext() && (result == null)) {
            children = (UIComponent) childrens.next();
            if (id.equals(children.getId())) {
                result = children;
                break;
            }
            result = findComponent(children, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }


    /**
     * Método auxiliar para ejecutar la consulta de los datos de Clasificacion Estrategica dado el id de operacion.
     * @author : S&P Solutions
     * @param  :
     * @return :
     * @version: v1.0
     * @Fecha  : 26/11/2019
     */
    public void consultarDatosDesembolsoClasificacionEstrategica() {
        logger.warning("\n=== Se ejecuta consultarDatosDesembolsoClasificacionEstrategica ===");

        Integer idOperacion = Integer.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("consultarDatosDesembolsoCE");
            operBinding.getParamsMap().put("idOperacion", idOperacion);
            operBinding.execute();

        } catch (Exception e) {
            logger.warning("\n===Ha ocurrido un error al ejecutar el operBinding consultarDatosDesembolsoCE ->", e);
        }

        logger.warning("Termina metodo consultarDatosDesembolsoClasificacionEstrategica");
    }

    /**
     * Método auxiliar para ejecutar la consulta de los datos de Contrato Desembolso dado el id de operacion.
     * @author : S&P Solutions
     * @param  :
     * @return :
     * @version: v1.0
     * @Fecha  : 26/11/2019
     */
    public void consultarDatosContratoDesembolso() {
        logger.warning("\n=== Se ejecuta consultarDatosContratoDesembolso ===");
        Long idClasificacion = null;
        boolean banConsulta = false;
        Integer idOperacion = Integer.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());

        DCBindingContainer bindingsCid = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iteratorCid = bindingsCid.findIteratorBinding("ConsultarIDContratoDesembolsoVO1Iterator");
        ViewObject voCid = iteratorCid.getViewObject();
        RowSetIterator rsiCid = voCid.createRowSetIterator(null);
        logger.warning("=============== voCid size:" + rsiCid.getRowCount());
        if (rsiCid.getRowCount() > 0) {
            while (rsiCid.hasNext()) {
                Row row = rsiCid.next();
                idClasificacion = Long.valueOf(row.getAttribute("IdClasificacionEstrategica").toString());
                logger.warning("=============== IdClasificacionEstrategica:" + idClasificacion);
            }

            DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iterator =
                bindings.findIteratorBinding("DatosDesembolsoClasificacionEstrategicaVO1Iterator");
            ViewObject vo = iterator.getViewObject();
            RowSetIterator rsi = vo.createRowSetIterator(null);
            //  RowSetIterator rsi = iterator.getRowSetIterator();
            logger.warning("=============== valoresVO size:" + rsi.getRowCount());
            while (rsi.hasNext()) {
                Row row = rsi.next();
                Long idClasificacionRow =
                    row.getAttribute("IdClasificacion") != null ?
                    Long.valueOf(row.getAttribute("IdClasificacion").toString()) : 0;
                logger.warning("=== id Clasificacion: " + idClasificacionRow + "|  IdClasificacionEstrategica:" +
                               idClasificacion);
                if (idClasificacion.equals(idClasificacionRow)) {
                    logger.warning("=============== ENTRA IF ===>> ID's IGUALES");
                    String nombre = row.getAttribute("NombreDelComponente").toString();
                    String decripcion = row.getAttribute("DescripcionDelComponente").toString();
                    String porcentaje = row.getAttribute("Porcentaje").toString();
                    String actividadEconomica = row.getAttribute("ActividadEconomica").toString();
                    String areaFocalizacion = row.getAttribute("AreaFocalizacion").toString();
                    String ejeEstrategico = row.getAttribute("EjeEstrategico").toString();

                    JSFUtils.setExpressionValue("#{pageFlowScope.nombreCE}", nombre);
                    JSFUtils.setExpressionValue("#{pageFlowScope.descripcionCE}", decripcion);
                    JSFUtils.setExpressionValue("#{pageFlowScope.porcentajeCE}", porcentaje);
                    JSFUtils.setExpressionValue("#{pageFlowScope.actividadEconomicaCE}", actividadEconomica);
                    JSFUtils.setExpressionValue("#{pageFlowScope.areaFocalizacionCE}", areaFocalizacion);
                    JSFUtils.setExpressionValue("#{pageFlowScope.ejeEstrategicoCE}", ejeEstrategico);

                }
            }
            rsi.closeRowSetIterator();
        } else {
            // JSFUtils.addFacesErrorMessage("Error al consultar información de Contrato Desembolso");
            logger.warning("Termina metodo consultarDatosDesembolsoClasificacionEstrategica el valor de ID CLASIFICACION es nulo.");
        }
        
        rsiCid.closeRowSetIterator();
        logger.warning("Termina metodo consultarDatosDesembolsoClasificacionEstrategica");
    }

    public void mostrarConsulta() {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("DatosDesembolsoClasificacionEstrategicaVO1Iterator");
        ViewObject vo = iterator.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);
        //  RowSetIterator rsi = iterator.getRowSetIterator();
        logger.warning("=============== DatosDesembolsoClasificacionEstrategicaVO1Iterator size:" + rsi.getRowCount());
        while (rsi.hasNext()) {
            Row row = rsi.next();
            Long idClasificacionRow =
                row.getAttribute("IdClasificacion") != null ?
                Long.valueOf(row.getAttribute("IdClasificacion").toString()) : 0;
            logger.warning("=== id Clasificacion: " + idClasificacionRow);
            logger.warning("=== Nombre: " + row.getAttribute("NombreDelComponente"));
            logger.warning("=== Descripcion: " + row.getAttribute("DescripcionDelComponente"));

        }
        rsi.closeRowSetIterator();

        DCBindingContainer bindingsDesembolso =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iteratorDesembolso =
            bindingsDesembolso.findIteratorBinding("ConsultarIDContratoDesembolsoVO1Iterator");
        ViewObject voDesembolso = iteratorDesembolso.getViewObject();
        RowSetIterator rsiDesembolso = voDesembolso.createRowSetIterator(null);
        logger.warning("=============== ConsultarIDContratoDesembolsoVO1Iterator size:" + rsiDesembolso.getRowCount());
        while (rsiDesembolso.hasNext()) {
            Row row = rsiDesembolso.next();
            Long idClasificacionRow =
                row.getAttribute("IdClasificacionEstrategica") != null ?
                Long.valueOf(row.getAttribute("IdClasificacionEstrategica").toString()) : 0;
            logger.warning("=== IdClasificacionEstrategica: " + idClasificacionRow);

        }
        rsiDesembolso.closeRowSetIterator();


        logger.warning("Termina metodo mostrarConsulta");
    }
    // -----------------------  FIN CUSTOM METHODS PARA MULTISECTORIAL----------------------------------------------------------------- //

    public Integer getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(Integer estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public Boolean getEsEscritura() {
        return esEscritura;
    }

    public void setEsEscritura(Boolean esEscritura) {
        this.esEscritura = esEscritura;
    }

    public Integer getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoTarea(Integer codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public Long getClaveOperacion() {
        return claveOperacion;
    }

    public void setClaveOperacion(Long claveOperacion) {
        this.claveOperacion = claveOperacion;
    }

    public Long getClaveContrato() {
        return claveContrato;
    }

    public void setClaveContrato(Long claveContrato) {
        this.claveContrato = claveContrato;
    }

    public Integer getModalidad() {
        return modalidad;
    }

    public void setModalidad(Integer modalidad) {
        this.modalidad = modalidad;
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Long getIdLineaCredito() {
        return idLineaCredito;
    }

    public void setIdLineaCredito(Long idLineaCredito) {
        this.idLineaCredito = idLineaCredito;
    }

    public void setCargarInformacion(Boolean cargarInformacion) {
        this.cargarInformacion = cargarInformacion;
    }

    public Boolean getCargarInformacion() {
        return cargarInformacion;
    }

    public void setPpComponentes(RichPopup ppComponentes) {
        this.ppComponentes = ppComponentes;
    }

    public RichPopup getPpComponentes() {
        return ppComponentes;
    }
}
