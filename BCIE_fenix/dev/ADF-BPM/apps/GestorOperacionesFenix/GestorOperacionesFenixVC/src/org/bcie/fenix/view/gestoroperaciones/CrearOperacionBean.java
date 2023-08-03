package org.bcie.fenix.view.gestoroperaciones;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichQuery;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.model.QueryDescriptor;
import oracle.adf.view.rich.model.QueryModel;


import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.bcie.clientemo.CrearClienteResponseType;
import org.bcie.fenix.common.model.vo.ConfigVisibilidadVORowImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.operacionmo.CrearOperacionResponseType;
import org.bcie.resultbo.Resultado;

public class CrearOperacionBean extends FenixActionBean implements Serializable {
    @SuppressWarnings("compatibility:-4408316743482765878")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    private transient RichPopup popupCrearCliente;
    private transient RichPopup popUpConfirmarCancelarCrearCliente;
    private transient RichPopup popupCancelarOperacion;
    private transient RichPopup popupBuscarCliente;
    private transient RichPopup popUpAsociarOperacion;
    private transient RichTable tbAsoc;
    private transient RichQuery queryOpResultado;
    private transient RichQuery queryBuscarCliente;
    private transient RichSelectOneChoice socEjercicioPoa;
    private transient RichSelectOneChoice socSectorInstitucional;
    private transient RichSelectOneChoice soc6;

    private Long idAsociada;

    public static final Integer LINEA_GLOBAL_CREDITO_IFI = 26;
    public static final Integer ID_SECTOR_INST_PUBLICO = 1; // Id de Sector Institucional, "Sector Público"
    public static final Integer ID_SECTOR_INST_PRIVADO = 2; // Id de Sector Institucional, "Sector Privado"
    public static final Integer ID_SECTOR_INST_ORGANISMO_REGIONAL = 3; // Id de Sector Institucional, "Organismo Regional"
    public static final String SECTOR_PRIVADO = "Sector Privado";

    /**
     * Define ubicacion y nombre del archivo properties de resource bundle
     */
    public static final String BUNDLE = "org.bcie.fenix.view.GestorOperacionesFenixVCBundle";

    public CrearOperacionBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void productoValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE, "Inside productoValueChangeListener");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Integer productoTieneRiesgoCredito;

        // Mostramos / ocultamos campo dependiendo de la tabla ConfigVisibilidadVO
        obtenerConfiguracionByIdProducto((Integer) JSFUtils.resolveExpression("#{bindings.IdProducto.attributeValue}"));

        // Filtramos y habilitamos combo de Actividad Económica Padre (combos padres-hijos)
        filtrarActividadEconomicaPadre((Integer) JSFUtils.resolveExpression("#{bindings.IdProducto.attributeValue}"));
        
        // Limpiamos valor de Ubicación y Sector/Sub-sector I-BCIE si el Producto seleccionado NO tiene riesgo de crédito
        productoTieneRiesgoCredito = Integer.valueOf(ADFUtils.getBoundAttributeValue("ProductoTieneRiesgoCredito").toString());
        if (productoTieneRiesgoCredito.intValue() != 1) {
            ADFUtils.setBoundAttributeValue("UbicacionProyecto", null);
            ADFUtils.setBoundAttributeValue("IdSectorIbcie", null);
            ADFUtils.setBoundAttributeValue("IdSubSectorIbcie", null);
        }
    }
    
    public void sectorInstitucionalValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE, "Inside sectorInstitucionalValueChangeListener."); // — Kruger Innova, funcionalidad de Sector Institucional. — FCP, 21/sept/2020.
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        GestorOperacionesBean gestorOperacionesBean = (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        Integer idSectorInstitucional;
                
        // Campo de Sector Institucional es obligatorio en pantalla (por eso no hacemos null check).
        idSectorInstitucional = Integer.valueOf(ADFUtils.getBoundAttributeValue("IdSectorInstitucional").toString());
        logger.info("IdSectorInstitucional: " + idSectorInstitucional);
        
        // Asignamos variable que guarda si el Sector Institucional es "Sector Público".
        if (idSectorInstitucional == ID_SECTOR_INST_PUBLICO) {
            gestorOperacionesBean.setEsUnidadEjecutoraSecPublico(true);
            gestorOperacionesBean.setEsTipoDeGarantiaSecPublico(true);
            gestorOperacionesBean.setEsSecInstitucionalPublico(true);
        }
        else {
            // "Organismo Regional" (3) y "Sector Privado" (1) se deben comportar igual
            gestorOperacionesBean.setEsUnidadEjecutoraSecPublico(false);
            gestorOperacionesBean.setEsTipoDeGarantiaSecPublico(false);
            gestorOperacionesBean.setEsSecInstitucionalPublico(false);
        }
    }
    
    public void idCatPaisValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "Inside idCatPaisValueChangeListener."); // — Kruger Innova, funcionalidad de idCatPais. — FCP, 02/jun/2021.
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        GestorOperacionesBean gestorOperacionesBean = (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        BigDecimal idCatPais = null;
                
        // Campo de Pais es obligatorio en pantalla (por eso no hacemos null check).        
        logger.log(ADFLogger.WARNING, "idCatPais: " + ADFUtils.getBoundAttributeValue("IdCatPais").toString());
        
        idCatPais = new BigDecimal(ADFUtils.getBoundAttributeValue("IdCatPais").toString());
        logger.log(ADFLogger.WARNING,"Pais idCatPaisValueChangeListener: " + idCatPais);
        gestorOperacionesBean.setIdCatPais(idCatPais);     

    }
    
    public void tipoGarantiaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        // Actualiza bindings con el valor seleccionado en combo. — FCP, 11/dec/2020.
        logger.log(ADFLogger.TRACE, "Inside tipoGarantiaValueChangeListener.");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); 
    }

    private void filtrarActividadEconomicaPadre(Integer idProducto) {
        logger.log(ADFLogger.TRACE, "Inside filtrarActividadEconomicaPadre");
        DCIteratorBinding voTiposActividadEconomicaPadre = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();

        OperationBinding operationBinding = bindings.getOperationBinding("setidProducto");
        operationBinding.getParamsMap().put("value", idProducto);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
        }

        // Ejecutamos query en iterador (puesto que cambió el parámetro del Where))
        voTiposActividadEconomicaPadre =
            ADFUtils.getDCBindingContainer().findIteratorBinding("TiposActividadEconomicaPadreLOVIterator");
        voTiposActividadEconomicaPadre.executeQuery();

        // Limpiamos combos dependientes: Iniciativa estratégica, Cantidad de Países beneficiados, Actividad económica, Área de focalización y Eje estratégico
        filtrarIniciativaEstrategica(null);
        filtrarCantidadPaises(null);
        filtrarActividadEconomica(null);
        filtrarAreaFocalizacion(null, null);
        filtrarEjeEstrategico(null, null);

        // S&P | 05/07/2019 | Se anexa logica para Mulitesectorial  //
        // Recuperar valor multisectorial
        int multisectorial =
            (Integer) (JSFUtils.resolveExpression("#{bindings.v_multisectorial.inputValue}") != null ?
                       JSFUtils.resolveExpression("#{bindings.v_multisectorial.inputValue}") : 2);
        if (multisectorial ==
            1) { //Es multisectorial
            //Actualizar los valores de los componetes dinamicos
            ComponentesMultisectorialBean componentesMBean =
                              (ComponentesMultisectorialBean) JSFUtils.resolveExpression("#{viewScope.componenteMultisectorialBean}");
            componentesMBean.actualizarValoresComponentes();
        }
        //============================================================//
    }

    private void obtenerConfiguracionByIdProducto(Integer idProducto) {
        logger.log(ADFLogger.TRACE, "Inside obtenerConfiguracionByIdProducto");
        ConfigVisibilidadVORowImpl rowConfigVisibilidad = null;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("recuperarConfiguracionVisibilidad");
        operationBinding.getParamsMap().put("idProducto", idProducto);
        operationBinding.execute();

        rowConfigVisibilidad = (ConfigVisibilidadVORowImpl) operationBinding.getResult();

        logger.log(ADFLogger.TRACE, "ID TABLA CONFIGURACION: " + rowConfigVisibilidad.getId());
        logger.log(ADFLogger.TRACE, "ID PRODUCTO: " + rowConfigVisibilidad.getIdProducto());
        logger.log(ADFLogger.TRACE, "DatosGenerales: " + rowConfigVisibilidad.getDatosGenerales().toString());
        logger.log(ADFLogger.TRACE, "MontoTotal: " + rowConfigVisibilidad.getMontoTotal().toString());
        logger.log(ADFLogger.TRACE, "Actividad Economica: " + rowConfigVisibilidad.getActividadEconomica().toString());
        logger.log(ADFLogger.TRACE,
                   "Iniciativa estrategica: " + rowConfigVisibilidad.getIniciativaEstrategica().toString());
        logger.log(ADFLogger.TRACE,
                   "Objetivo estrategico: " + rowConfigVisibilidad.getObjetivoEstrategico().toString());
        logger.log(ADFLogger.TRACE, "Area focalizacion: " + rowConfigVisibilidad.getAreaFocalizacion().toString());
        logger.log(ADFLogger.TRACE, "Eje estrategico: " + rowConfigVisibilidad.getEjeEstrategico().toString());
        logger.log(ADFLogger.TRACE, "Tipo de garantia: " + rowConfigVisibilidad.getTipoGarantia().toString());
        logger.log(ADFLogger.TRACE,
                   "Componente de consecinalidad:" + rowConfigVisibilidad.getComponenteConcesionalidad().toString());
        logger.log(ADFLogger.TRACE, "Estructurador:" + rowConfigVisibilidad.getEstructurador().toString());
        logger.log(ADFLogger.TRACE, "Beneficiario:" + rowConfigVisibilidad.getBeneficiario().toString());
        logger.log(ADFLogger.TRACE, "Programa:" + rowConfigVisibilidad.getPrograma().toString());
        logger.log(ADFLogger.TRACE,
                   "Operaciones asociadas:" + rowConfigVisibilidad.getOperacionesAsociadas().toString());
        logger.log(ADFLogger.TRACE,
                   "Responde cuestionario:" + rowConfigVisibilidad.getRespondeCuestionario().toString());
        logger.log(ADFLogger.TRACE, "Analisis LAFT:" + rowConfigVisibilidad.getAnalisisLaft().toString());
        logger.log(ADFLogger.TRACE, "Sector mercado:" + rowConfigVisibilidad.getSectorMercado().toString());

        this.mapeoConfiguracionVisibilidad(rowConfigVisibilidad);
    }

    public void mapeoConfiguracionVisibilidad(ConfigVisibilidadVORowImpl rowConfigVisibilidad) {
        logger.log(ADFLogger.TRACE, "Inside mapeoConfiguracionVisibilidad");
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        if (Integer.parseInt(rowConfigVisibilidad.getMontoTotal().toString()) == 0) {
            gestorOperacionesBean.setEsMontoTotal(false);
        } else {
            gestorOperacionesBean.setEsMontoTotal(true);
        }
        if (Integer.parseInt(rowConfigVisibilidad.getTipoGarantia().toString()) == 0) {
            gestorOperacionesBean.setEsTipoDeGarantia(false);
        } else {
            gestorOperacionesBean.setEsTipoDeGarantia(true);
        }
        if (Integer.parseInt(rowConfigVisibilidad.getComponenteConcesionalidad().toString()) == 0) {
            gestorOperacionesBean.setEsComponenteDeConsecionalidad(false);
        } else {
            gestorOperacionesBean.setEsComponenteDeConsecionalidad(true);
        }
        if (Integer.parseInt(rowConfigVisibilidad.getEstructurador().toString()) == 0) {
            gestorOperacionesBean.setEsEstructurador(false);
        } else {
            gestorOperacionesBean.setEsEstructurador(true);
        }
        if (Integer.parseInt(rowConfigVisibilidad.getBeneficiario().toString()) == 0) {
            gestorOperacionesBean.setEsBeneficiario(false);
        } else {
            gestorOperacionesBean.setEsBeneficiario(true);
        }
        if (Integer.parseInt(rowConfigVisibilidad.getPrograma().toString()) == 0) {
            gestorOperacionesBean.setEsPrograma(false);
        } else {
            gestorOperacionesBean.setEsPrograma(true);
        }

    }

    public String crearOperacionAction() {
        logger.warning("Inside crearOperacion");
        String continuaFlujoEn = null;

        String codExterno = "";
        Integer idOficina = null;

        HashMap<String, CrearOperacionResponseType> respuestaServicio = null;
        CrearOperacionResponseType response = null;
        String errorCode = null;
        String message = null;
        DCIteratorBinding voCrearOperacion = null;
        GestorOperacionesBean gestorOperacionesBean = null;

        /*
          System.out.println(JSFUtils.resolveExpression("#{bindings.MontoTotal.inputValue}"));
          System.out.println(JSFUtils.resolveExpression("#{bindings.MontoSolicitado.inputValue}"));
        */

        if (validarCrearOperacion()) { // Validación de datos obligatorios y reglas de negocio
            logger.warning("ok paso validacion crearOperacion");
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding;


            //obtener idOficina apartir del IdCliente
            operationBinding = bindings.getOperationBinding("getIdOficinaByIdCliente");
            operationBinding.getParamsMap().put("idCliente",
                                                JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}"));
            operationBinding.execute();
            idOficina = (Integer) operationBinding.getResult();
            //obtener CodExterno apartir idOficina
            operationBinding = bindings.getOperationBinding("getCodExternoByIdOficina");
            operationBinding.getParamsMap().put("idOficina", idOficina);
            operationBinding.execute();
            codExterno = (String) operationBinding.getResult();

            // Asignamos el usuario en sesión en CrearOperacionVO
            voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
            voCrearOperacion.getRowAtRangeIndex(0).setAttribute("Usuario",
                                                                ADFContext.getCurrent().getSecurityContext().getUserName());

            // S&P | 05/07/2019 | Se anexa logica para Mulitesectorial  //
            //Pasar valor multisectorial
            Integer multisectorial =
                (Integer) (JSFUtils.resolveExpression("#{bindings.v_multisectorial.inputValue}") != null ?
                           JSFUtils.resolveExpression("#{bindings.v_multisectorial.inputValue}") : 2);
            logger.warning("==== Pasar valor v_multisectorial a voCrearOperacion: " + multisectorial);
            voCrearOperacion.getRowAtRangeIndex(0).setAttribute("EsMultisectorial", multisectorial);

            //Recuperamos la instancia del Bean GestorOperacionesBean
            gestorOperacionesBean =
                (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");

            // Invocamos Crear operación
            operationBinding = bindings.getOperationBinding("crearOperacion");
            operationBinding.getParamsMap().put("codExterno",
                                                codExterno); //enviar parametros codExterno e idOficina del
            operationBinding.getParamsMap().put("idOficina",
                                                idOficina.longValue()); // catálogo Oficina apartir del IdCliente
            //Se agregaron los siguiente parametros por problemas para limpiar sus valores cuando no son visibles

            operationBinding.getParamsMap().put("esEjercicioPoa",
                                                gestorOperacionesBean.getEsEjercicioPoa()); // Indicador de visibilidad para ejercicioPoa
            operationBinding.getParamsMap().put("esUnidadEjecutoraSecPublico",
                                                gestorOperacionesBean.getEsUnidadEjecutoraSecPublico()); // Indicador de visibilidad para
            // UnidadEjecutora
            operationBinding.getParamsMap().put("esTipoDeGarantiaSecPublico",
                                                gestorOperacionesBean.getEsTipoDeGarantiaSecPublico()); // Indicador de visibilidad para
            // TipoDeGarantia
            //Ejecuta el metodo crearOperacion del AM
            operationBinding.execute();

            if (operationBinding.getErrors().size() != 0) {
                // Manejo de errores












            } else if (operationBinding.getResult() != null) {
                respuestaServicio = (HashMap<String, CrearOperacionResponseType>) operationBinding.getResult();
                response = respuestaServicio.get("response");
                errorCode =
                    response.getResultado().getError().getErrorCode() == null ? "" :
                    response.getResultado().getError().getErrorCode();

                if ((response.getResultado().getResult() != null) &&
                    (response.getResultado().getResult().value().equalsIgnoreCase("OK")) &&
                    (errorCode.trim().length() == 0)) {

                    //JSFUtils.addFacesInformationMessage("Operación creada exitosamente.");
                    //Mensaje de la respuesta de SOPA
                    JSFUtils.addFacesInformationMessage(response.getResultado().getResult() + "\n" +
                                                        response.getResultado().getMessage());
                    continuaFlujoEn = "irBusquedaOperacion";
                } else {
                    // Error al ejecutar el servicio
                    message =
                        response.getResultado().getMessage() == null ? "" :
                        ("Mensaje: " + response.getResultado().getMessage()) + ". ";
                    JSFUtils.addFacesErrorMessage(message + "Código de error: " +
                                                  response.getResultado().getError().getErrorCode() +
                                                  ". Descripción: " +
                                                  response.getResultado().getError().getErrorDescription() + ".");
                    logger.log(ADFLogger.ERROR, response.getResultado().getError().getErrorDescription());
                }
            } else {
                logger.log(ADFLogger.WARNING, "operationBinding.getResult() es null.");
            }
        }
        logger.warning("termina metodo crearOperacion");
        return continuaFlujoEn;
    }

    public void cancelarOperacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarOperacionActionListener: " + actionEvent.getComponent().getId());

        // Mostramos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupCancelarOperacion().show(popupHints);
    }

    public String botonCancelarOperacion() {
        getPopupCancelarOperacion().hide();
        return null;
    }
    
    
  
    private Boolean validarCrearOperacion() {
        Boolean esDatosCorrectos = Boolean.TRUE;
        Boolean banderaMontoTotal = Boolean.FALSE;
        double montoSolicitado = 0;
        double montoTotal = 0;
        Long idCliente = null;
        Long estimatedRowCountAsociadas = null;
        Integer idProducto = null;


        // ***** Validación de sección Datos generales START
        idCliente = (Long) JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}");
        if ((idCliente == null) || (idCliente == 0)) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El campo Cliente es requerido.");
        }

        if (JSFUtils.resolveExpression("#{bindings.MontoSolicitado.inputValue}") != null)
            montoSolicitado =
                Double.valueOf(JSFUtils.resolveExpression("#{bindings.MontoSolicitado.inputValue}").toString());

        if (JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean.esMontoTotal}") != null)
            banderaMontoTotal =
                Boolean.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean.esMontoTotal}").toString());

        if (banderaMontoTotal.equals(Boolean.TRUE)) {

            if (JSFUtils.resolveExpression("#{bindings.MontoTotal.inputValue}") != null)
                montoTotal = Double.valueOf(JSFUtils.resolveExpression("#{bindings.MontoTotal.inputValue}").toString());
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
            if ((Boolean)JSFUtils.resolveExpression("#{(bindings.ProductoTieneRiesgoCredito.inputValue eq 1)}")) {
                
                // Cuando el Producto seleccionado tiene riesgo de crédito Sector/Sub-sector I-BCIE son obligatorios.
                if (((Boolean)JSFUtils.resolveExpression("#{empty bindings.IdSectorIbcie.inputValue}")) ||
                    (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdSectorIbcie.inputValue}").toString()) == 0)) {

                    esDatosCorrectos = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("El campo Sector I-BCIE Preliminar es requerido.");
                }
                
                if (((Boolean)JSFUtils.resolveExpression("#{empty bindings.IdSubSectorIbcie.inputValue}")) ||
                    (Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdSubSectorIbcie.inputValue}").toString()) == 0)) {

                    esDatosCorrectos = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("El campo Sub-sector I-BCIE Preliminar es requerido.");
                }
            }            
            
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

       // SPS | 25/10/2019 - Validacion de valores de Clasificacion Ambiental  
       logger.warning("\n=== SE EJECUTA : Validacion de valores de Clasificacion Ambiental ======");
       ClasificacionAmbiental componentesCAMBean =
                         (ClasificacionAmbiental) JSFUtils.resolveExpression("#{viewScope.clasificacionAmbiental}");
       logger.warning("\n=== SE OBTIENE MANAGED BEAN PARA CA :  crearClasificacionAmbiental  ======");
       ArrayList<String> resultadoValidacionCA =  componentesCAMBean.validarComponentesClasificacionAmbiental();
        if (resultadoValidacionCA.size() > 0) {
            esDatosCorrectos = Boolean.FALSE;
            for (String str : resultadoValidacionCA) {
                JSFUtils.addFacesErrorMessage(str);
            }
        }
        logger.warning("\n=== CONCLUYE: Validacion de valores de Clasificacion Ambiental ======");
        // =====  SPS | 25/10/2019 - FIN Validacion de valores de Clasificacion Ambiental  === 
        //Validacion si el producto es Ampliacion de Línea Global de Credito a IFI es obligatorio asociar una operacion
        idProducto = (Integer) JSFUtils.resolveExpression("#{bindings.IdProducto.attributeValue}");
        estimatedRowCountAsociadas =
            (Long) JSFUtils.resolveExpression("#{bindings.OperacionesAsociadasVO.estimatedRowCount}");

        if ((idProducto.compareTo(LINEA_GLOBAL_CREDITO_IFI) == 0) && (estimatedRowCountAsociadas == 0)) {
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("Es necesario asociar una operación para el tipo de producto Ampliación de Línea Global de Crédito a IFI.");
        }

        return esDatosCorrectos;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setPopupCrearCliente(RichPopup popupCrearCliente) {
        this.popupCrearCliente = popupCrearCliente;
    }

    public RichPopup getPopupCrearCliente() {
        return popupCrearCliente;
    }

    public void setPopupBuscarCliente(RichPopup popupBuscarCliente) {
        this.popupBuscarCliente = popupBuscarCliente;
    }

    public RichPopup getPopupBuscarCliente() {
        return popupBuscarCliente;
    }

    public void setPopUpAsociarOperacion(RichPopup popUpAsociarOperacion) {
        this.popUpAsociarOperacion = popUpAsociarOperacion;
    }

    public RichPopup getPopUpAsociarOperacion() {
        return popUpAsociarOperacion;
    }

    public void setTbAsoc(RichTable tbAsoc) {
        this.tbAsoc = tbAsoc;
    }

    public RichTable getTbAsoc() {
        return tbAsoc;
    }

    public void setQueryOpResultado(RichQuery queryOpResultado) {
        this.queryOpResultado = queryOpResultado;
    }

    public RichQuery getQueryOpResultado() {
        return queryOpResultado;
    }

    public void setQueryBuscarCliente(RichQuery queryBuscarCliente) {
        this.queryBuscarCliente = queryBuscarCliente;
    }

    public RichQuery getQueryBuscarCliente() {
        return queryBuscarCliente;
    }

    public void crearClienteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside crearClienteActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding crearRowCliente = bindings.getOperationBinding("crearRowCliente");

        // Creamos row para inicializar formulario vacío
        crearRowCliente.execute();
        if (!crearRowCliente.getErrors().isEmpty()) {
            // Manejo de errores
        }

        // Mostramos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupCrearCliente().show(popupHints);
    }

    public void aceptarCrearClienteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside aceptarCrearClienteActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        //DCIteratorBinding crearClienteVOIterator = null;
        GestorOperacionesBean gestorOperacionesBean = null;
        OperationBinding getCodExternoOp = null;
        OperationBinding crearClienteOp = null;
        String codExterno = null;
        String sector = null;
        BigDecimal idCatPais = null;

        if (validarCrearCliente()) {

            logger.warning("Obteniendo OperBinding getCodExternoByIdGrupoEconomico");
            // Obtener codExterno por ID grupo economico
            getCodExternoOp = bindings.getOperationBinding("getCodExternoByIdGrupoEconomico");
            getCodExternoOp.getParamsMap().put("idGrupoEconomico",
                                               JSFUtils.resolveExpression("#{bindings.GrupoEconomico.inputValue}"));
            logger.warning("Ejecutando OperBinding getCodExternoByIdGrupoEconomico");
            getCodExternoOp.execute();
            codExterno = (String) getCodExternoOp.getResult(); //este parametro se recupera y se envia a crearCliente
            logger.warning("COdExterno: " + codExterno);

            // Asignamos el usuario en sesión en CrearClienteVO
            //crearClienteVOIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearClienteVOIterator");
            //crearClienteVOIterator.getRowAtRangeIndex(0).setAttribute("Ejecutivo",
            //ADFContext.getCurrent().getSecurityContext().getUserName());
            ADFUtils.setBoundAttributeValue("Ejecutivo", ADFContext.getCurrent().getSecurityContext().getUserName());
            //Asignamos idTipoInstitucion
            logger.warning("Asignando valores a VOIterator");
            gestorOperacionesBean =
                (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
            if (null != gestorOperacionesBean) {


                logger.warning("Recuperando IdTipoInstitucion de Bean");
                if (null != gestorOperacionesBean.getRazonSocialCliente()) {
                    logger.warning("Razon social: " + gestorOperacionesBean.getRazonSocialCliente());
                    ADFUtils.setBoundAttributeValue("RazonSocial", gestorOperacionesBean.getRazonSocialCliente());
                }
                if (null != gestorOperacionesBean.getAbreviaturaCliente()) {
                    logger.warning("Abreviatura: " + gestorOperacionesBean.getAbreviaturaCliente());
                    ADFUtils.setBoundAttributeValue("Abreviatura", gestorOperacionesBean.getAbreviaturaCliente());
                }
                if (null != gestorOperacionesBean.getIdTipoPersonaCliente()) {
                    logger.warning("Tipo persona: " + gestorOperacionesBean.getIdTipoPersonaCliente());
                    ADFUtils.setBoundAttributeValue("TipoPersona", gestorOperacionesBean.getIdTipoPersonaCliente());
                }
                if (null != gestorOperacionesBean.getIdSectorCliente()) {
                    logger.warning("Sector: " + gestorOperacionesBean.getIdSectorCliente());
                    ADFUtils.setBoundAttributeValue("Sector", gestorOperacionesBean.getIdSectorCliente());
                }
                if (null != gestorOperacionesBean.getPais()) {
                    logger.warning("Pais: " + gestorOperacionesBean.getIdPaisCliente());
                    ADFUtils.setBoundAttributeValue("Pais", gestorOperacionesBean.getIdPaisCliente());
                }
                if (null != gestorOperacionesBean.getIdTipoInstitucion()) {
                    logger.warning("TipoInstitucion: " + gestorOperacionesBean.getIdTipoInstitucion());
                    ADFUtils.setBoundAttributeValue("TipoInstitucion", gestorOperacionesBean.getIdTipoInstitucion());
                }
                if (null != gestorOperacionesBean.getIdGrupoCliente()) {
                    logger.warning("Grupo economico: " + gestorOperacionesBean.getIdGrupoCliente());
                    ADFUtils.setBoundAttributeValue("GrupoEconomico", gestorOperacionesBean.getIdGrupoCliente());
                }
                if (null != gestorOperacionesBean.getNumeroIdentificacionCliente()) {
                    logger.warning("Numero identificacion: " + gestorOperacionesBean.getNumeroIdentificacionCliente());
                    ADFUtils.setBoundAttributeValue("NumeroIdentificacion",
                                                    gestorOperacionesBean.getNumeroIdentificacionCliente());
                }
                if (null != gestorOperacionesBean.getIdOficinaCliente()) {
                    logger.warning("Oficina: " + gestorOperacionesBean.getIdOficinaCliente());
                    ADFUtils.setBoundAttributeValue("Oficina1", gestorOperacionesBean.getIdOficinaCliente());
                }
            }

            // Invoca el metodo crearCliente dentro del CrearClienteVOImpl
            crearClienteOp = bindings.getOperationBinding("crearCliente");
            crearClienteOp.getParamsMap().put("codExterno", codExterno);
            crearClienteOp.execute();
            if (!crearClienteOp.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(crearClienteOp.getErrors().toString());
            } else {
                // Asignamos el cliente creado al combo en pantalla TODO
                HashMap<String, Object> resultado = (HashMap<String, Object>) crearClienteOp.getResult();
                DCIteratorBinding voCrearOperacion =
                    ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
                //Obtenemos el sector de cliente seleccionado
                try {
                    sector = ADFUtils.getBoundAttributeValue("Sector").toString();
                    logger.info("Sector de cliente creado: " + sector);
                    
                    // Fix para prevenir que el combo se quede "pegado", debido a que este método se llama desde un botón con immediate="true".
                    socSectorInstitucional.resetValue(); 

                    gestorOperacionesBean =
                        (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
                    //Si sector es publico -- ID_CAT_SECTOR_INSTITUCIONAL del cliente es = 1 habilitar
                    //UnidadEjecutora y TipoDeGarantia
                    if (sector == "1" || sector.equals("1") || sector.equals("Sector Público") ||
                        sector == "Sector Público") {

                        gestorOperacionesBean.setEsUnidadEjecutoraSecPublico(Boolean.TRUE);
                        gestorOperacionesBean.setEsTipoDeGarantiaSecPublico(Boolean.TRUE);
                        gestorOperacionesBean.setEsSecInstitucionalPublico(Boolean.TRUE);
                        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdSectorInstitucional", ID_SECTOR_INST_PUBLICO); // Asignamos valor en combo de Sector Institucional como "Sector Público" - 21/sept/2020
                    } else {
                        gestorOperacionesBean.setEsUnidadEjecutoraSecPublico(Boolean.FALSE);
                        gestorOperacionesBean.setEsTipoDeGarantiaSecPublico(Boolean.FALSE);
                        gestorOperacionesBean.setEsSecInstitucionalPublico(Boolean.FALSE);
                        voCrearOperacion =
                            ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
                        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("UnidadEjecutora", null);
                        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("TipoGarantia", null);
                        
                        if (sector.equals(SECTOR_PRIVADO)) 
                            voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdSectorInstitucional", ID_SECTOR_INST_PRIVADO); // Asignamos valor en combo de Sector Institucional como "Sector Privado" - 21/sept/2020
                        else 
                            voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdSectorInstitucional", ID_SECTOR_INST_ORGANISMO_REGIONAL); // Asignamos valor en combo de Sector Institucional como "Organismo Regional" - 21/sept/2020
                    }
                } catch (Exception e) {
                    logger.log(ADFLogger.ERROR, e.getMessage());
                }
                
                //Obtenemos el pais de cliente seleccionado
                try {
                    // Fix para prevenir que el combo se quede "pegado", debido a que este método se llama desde un botón con immediate="true".
                    soc6.resetValue(); 

                    gestorOperacionesBean =
                        (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
                    
                    idCatPais = new BigDecimal(ADFUtils.getBoundAttributeValue("PaisAttrValue").toString());
                    logger.warning("idCatPais de cliente creado: " + idCatPais);
                    
                    voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdCatPais", idCatPais); // Asignamos valor en combo de Pais - 03/jun/2021
                }catch (Exception e) {
                    logger.log(ADFLogger.ERROR, e.getMessage());
                }
                //invocar criteria getRazonSocialByIdCliente
                OperationBinding operationBindingCriteria = bindings.getOperationBinding("getRazonSocialByIdCliente");
                operationBindingCriteria.getParamsMap().put("idCliente", resultado.get("idCliente"));
                operationBindingCriteria.execute();
                String razonSocial = (String) operationBindingCriteria.getResult();
                //set the value razonSocial
                voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdCliente", resultado.get("idCliente"));
                gestorOperacionesBean =
                    (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
                gestorOperacionesBean.setNombreCliente(razonSocial);

                CrearClienteResponseType response = new CrearClienteResponseType();
                Resultado resultadoVO = new Resultado();
                resultadoVO = (Resultado) resultado.get("result");
                if (resultadoVO.getResult().toString().equals("OK")) {
                    JSFUtils.addFacesInformationMessage("Cliente creado exitosamente.");
                } else {
                    JSFUtils.addFacesErrorMessage("Error al crear el cliente. Vuelva a intentarlo mas tarde.");
                }
                getPopupCrearCliente().hide(); // Cerrramos popup
            }
        }

    }

    public void cancelarCrearClienteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarCrearClienteActionListener: " + actionEvent.getComponent().getId());
        getPopupCrearCliente().hide();
    }

    public void buscarClienteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside buscarClienteActionListener: " + actionEvent.getComponent().getId());

        // Mostramos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupBuscarCliente().show(popupHints);
    }

    public void seleccionarClienteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside seleccionarClienteActionListener: " + actionEvent.getComponent().getId());
        oracle.jbo.domain.Number idCliente = null;
        String nombreCliente = null;
        String sector = null;
        Integer pais = null;
        GestorOperacionesBean gestorOperacionesBean = null;
        DCIteratorBinding voCrearOperacion =
            ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");

        // Obtenemos id de cliente seleccionado
        // (este IdCliente es de BuscarClienteVOIterator, diferente al IdCliente de CrearOperacionVOIterator
        try {
            idCliente = new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{bindings.IdCliente1.inputValue}"));
        } catch (SQLException e) {
            logger.log(ADFLogger.ERROR, e.getMessage());
        }

        //Obtenemos el sector de cliente seleccionado
        try {
            sector = JSFUtils.resolveExpression("#{bindings.Sector1.inputValue}").toString();
            logger.info("Sector de cliente seleccionado: " + sector);
            
            // Fix para prevenir que el combo se quede "pegado", debido a que este método se llama desde un botón con immediate="true".
            socSectorInstitucional.resetValue(); 

            gestorOperacionesBean =
                (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
            //Si sector es publico -- ID_CAT_SECTOR_INSTITUCIONAL del cliente es = 1 habilitar
            //UnidadEjecutora y TipoDeGarantia
            if (sector == "Sector Público" || sector.equals("Sector Público")) {

                gestorOperacionesBean.setEsUnidadEjecutoraSecPublico(Boolean.TRUE);
                gestorOperacionesBean.setEsTipoDeGarantiaSecPublico(Boolean.TRUE);
                gestorOperacionesBean.setEsSecInstitucionalPublico(Boolean.TRUE);
                voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdSectorInstitucional", ID_SECTOR_INST_PUBLICO); // Asignamos valor en combo de Sector Institucional como "Sector Público" - 21/sept/2020
            } else {
                gestorOperacionesBean.setEsUnidadEjecutoraSecPublico(Boolean.FALSE);
                gestorOperacionesBean.setEsTipoDeGarantiaSecPublico(Boolean.FALSE);
                gestorOperacionesBean.setEsSecInstitucionalPublico(Boolean.FALSE);
                //                DCIteratorBinding voCrearOperacion =
                //                    ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
                //                voCrearOperacion.getRowAtRangeIndex(0).setAttribute("UnidadEjecutora", null);
                //                voCrearOperacion.getRowAtRangeIndex(0).setAttribute("TipoGarantia", null);
                
                if (sector.equals(SECTOR_PRIVADO)) 
                    voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdSectorInstitucional", ID_SECTOR_INST_PRIVADO); // Asignamos valor en combo de Sector Institucional como "Sector Privado" - 21/sept/2020
                else 
                    voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdSectorInstitucional", ID_SECTOR_INST_ORGANISMO_REGIONAL); // Asignamos valor en combo de Sector Institucional como "Organismo Regional" - 21/sept/2020
            }
            
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, e.getMessage());
        }
        
        //Obtenemos el pais de cliente seleccionado
        try {
            logger.info("Obtenemos el pais de cliente seleccionado:");
            pais = (Integer)JSFUtils.resolveExpression("#{bindings.Paisid.inputValue}");
            
            //pais = "600";
            logger.info("Pais de cliente seleccionado: " + pais);
            
            // Fix para prevenir que el combo se quede "pegado", debido a que este método se llama desde un botón con immediate="true".
            soc6.resetValue(); 

            gestorOperacionesBean =
                (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");

            //Pais
            voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdCatPais", pais); // Asignamos valor en combo de pais - 02/jun/2021                            
            logger.info("Pais asignado: " + voCrearOperacion.getCurrentRow().getAttribute("IdCatPais"));
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, e.getMessage());
        }

        // Asignamos el Id cliente seleccionado al VO CrearOperacion
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdCliente", idCliente);

        // Asignamos el cliente seleccionado al input en pantalla
        nombreCliente = (String) JSFUtils.resolveExpression("#{bindings.RazonSocial1.inputValue}");
        gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        gestorOperacionesBean.setNombreCliente(nombreCliente);

        // Limpiamos tabla y panel de búsqueda dentro del popup
        resetPopupBuscarCliente();

        // Cerramos el popup de Buscar cliente
        getPopupBuscarCliente().hide();
    }

    private void resetPopupBuscarCliente() {
        logger.log(ADFLogger.TRACE, "Inside resetPopupBuscarCliente.");
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

    /*
     * Tarjeta 13306
     */
    public void monedaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.NOTIFICATION, "Inside monedaValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer IdTcaTipoMoneda = null;
        //String idMoneda = null;
        DCIteratorBinding voCrearOperacion = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

        IdTcaTipoMoneda = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Moneda1.inputValue}").toString());
            
        IdTcaTipoMoneda = IdTcaTipoMoneda + 1;
            
        //idMoneda = JSFUtils.resolveExpression("#{bindings.Moneda1.inputValue}").toString();

        logger.log(ADFLogger.NOTIFICATION, "id Moneda: " + IdTcaTipoMoneda);

        // Asignamos en current row de CrearOperacionVO el valor de idMoneda
        voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdTcaTipoMoneda", IdTcaTipoMoneda);
    }

    public void actividadEconomicaPadreValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside actividadEconomicaPadreValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idActividadEconomica = null;
        String codigoExterno = null;
        DCIteratorBinding voCrearOperacion = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idActividadEconomica = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id.inputValue}").toString());
        codigoExterno = (String) JSFUtils.resolveExpression("#{bindings.CodExterno.inputValue}");

        // Asignamos en current row de CrearOperacionVO los valores de ActividadEconomica y CodigoExterno
        voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("ActividadEconomica", idActividadEconomica);
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("CodigoExterno", codigoExterno);

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
        DCIteratorBinding voCrearOperacion = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idIniciativaEstrategica = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id1.inputValue}").toString());

        // Asignamos en current row de CrearOperacionVO el valor de IniciativaEstrategica
        voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IniciativaEstrategica", idIniciativaEstrategica);

        
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
        DCIteratorBinding voCrearOperacion = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idActividad = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdActividad.inputValue}").toString());
        idIniciativaEstrategica = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id1.inputValue}").toString());
        idRangoPaises = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdRangoPaises.inputValue}").toString());

        // Asignamos en current row de CrearOperacionVO el valor de IdRangoPais
        voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdRangoPais", idRangoPaises);

        // Filtramos los combos Actividad económica, Área de focalización y Eje estratégico
        
        // SPS | 201/11/2019 | FIX T-14161 : LIMPIAR COMBOS AL RESET DE INICATIVA ESTRATEGICA 
        if(idRangoPaises>0){
            // por el idActividad, idIniciativaEstrategica e idRangoPaises
            filtrarActividadEconomica(new oracle.jbo.domain.Number(idActividad.intValue()));
            filtrarAreaFocalizacion(new oracle.jbo.domain.Number(idIniciativaEstrategica.intValue()),
                                    new oracle.jbo.domain.Number(idRangoPaises.intValue()));
            filtrarEjeEstrategico(new oracle.jbo.domain.Number(idIniciativaEstrategica.intValue()),
                                  new oracle.jbo.domain.Number(idRangoPaises.intValue()));

            asignarIdsActividadEconomica(); // Este método reemplaza a actividadEconomicaValueChangeListener puesto que el combo es readonly
            asignarIdAreaFocalizacion(); // Este método reemplaza a areaFocalizacionValueChangeListener puesto que el combo es readonly
            asignarIdEjeEstrategico(); // Este método reemplaza a ejeEstrategicoValueChangeListener puesto que el combo es readonly
        }else{
            filtrarActividadEconomica(null);
            filtrarAreaFocalizacion(null, null);
            filtrarEjeEstrategico(null, null);
        }
       
    }

    public void actividadEconomicaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside actividadEconomicaValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idActividadEconomica = null;
        String codigoExterno = null;
        DCIteratorBinding voCrearOperacion = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idActividadEconomica = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id3.inputValue}").toString());
        codigoExterno = (String) JSFUtils.resolveExpression("#{bindings.CodExterno1.inputValue}");

        // Asignamos en current row de CrearOperacionVO los valores de IdActividadEconomicaAsoc y CodigoExternoAsoc
        voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdActividadEconomicaAsoc", idActividadEconomica);
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("CodigoExternoAsoc", codigoExterno);
    }

    private void asignarIdsActividadEconomica() {
        logger.log(ADFLogger.TRACE, "Inside asignarIdsActividadEconomica.");
        Integer idActividadEconomica = null;
        String codigoExterno = null;
        DCIteratorBinding voCrearOperacion = null;

        if ((JSFUtils.resolveExpression("#{bindings.Id3.inputValue}") != null) &&
            (JSFUtils.resolveExpression("#{bindings.CodExterno1.inputValue}") != null)) {

            idActividadEconomica = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id3.inputValue}").toString());
            codigoExterno = (String) JSFUtils.resolveExpression("#{bindings.CodExterno1.inputValue}");
        }

        // Asignamos en current row de CrearOperacionVO los valores de IdActividadEconomicaAsoc y CodigoExternoAsoc
        voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("IdActividadEconomicaAsoc", idActividadEconomica);
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("CodigoExternoAsoc", codigoExterno);
    }

    public void areaFocalizacionValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside areaFocalizacionValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idAreaFocalizacion = null;
        DCIteratorBinding voCrearOperacion = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idAreaFocalizacion = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id4.inputValue}").toString());

        // Asignamos en current row de CrearOperacionVO el valor de AreaFocalizacion
        voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("AreaFocalizacion", idAreaFocalizacion);
    }

    private void asignarIdAreaFocalizacion() {
        logger.log(ADFLogger.TRACE, "Inside asignarIdAreaFocalizacion.");
        Integer idAreaFocalizacion = null;
        DCIteratorBinding voCrearOperacion = null;

        if (JSFUtils.resolveExpression("#{bindings.Id4.inputValue}") != null) {
            idAreaFocalizacion = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id4.inputValue}").toString());
        }

        // Asignamos en current row de CrearOperacionVO el valor de AreaFocalizacion
        voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("AreaFocalizacion", idAreaFocalizacion);
    }

    public void ejeEstrategicoValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside ejeEstrategicoValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idEjeEstrategico = null;
        DCIteratorBinding voCrearOperacion = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        idEjeEstrategico = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id5.inputValue}").toString());

        // Asignamos en current row de CrearOperacionVO el valor de EjeEstrategico
        voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("EjeEstrategico", idEjeEstrategico);
    }

    private void asignarIdEjeEstrategico() {
        logger.log(ADFLogger.TRACE, "Inside asignarIdEjeEstrategico.");
        Integer idEjeEstrategico = null;
        DCIteratorBinding voCrearOperacion = null;

        if (JSFUtils.resolveExpression("#{bindings.Id5.inputValue}") != null) {
            idEjeEstrategico = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Id5.inputValue}").toString());
        }

        // Asignamos en current row de CrearOperacionVO el valor de EjeEstrategico
        voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("EjeEstrategico", idEjeEstrategico);
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

    public void openDialogAsociarOperacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside openDialogAsociarOperacionActionListener: " + actionEvent.getComponent().getId());

        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpAsociarOperacion().show(popupHints);
    }

    public void asociarOperacion(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside asociarOperacion: " + actionEvent.getComponent().getId());
        BindingContainer bindings = getBindings();
        OperationBinding asociarOperacionesOp = bindings.getOperationBinding("asociarOperaciones");
        DCIteratorBinding operacionResultadoIterator = null;
        ViewObject operacionResultadoVO = null;

        operacionResultadoIterator =
            ADFUtils.getDCBindingContainer().findIteratorBinding("OperacionResultadoVOIterator");
        operacionResultadoVO = operacionResultadoIterator.getViewObject();
        Row[] operacionesSeleccionadasRows = operacionResultadoVO.getFilteredRows("SeleccionarOperacion", true);
        for (Row row : operacionesSeleccionadasRows) {
            asociarOperacionesOp.getParamsMap().put("row", row);
            asociarOperacionesOp.execute();

            if (asociarOperacionesOp.getErrors().size() != 0) {
                // Manejo de errores












            }
        }

        // Limpiamos tabla y panel de búsqueda dentro del popup
        resetPopupAsociarOperacion();

        // Cerramos popup
        getPopUpAsociarOperacion().hide();
    }

    public void asociarOperacionDetalle(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside asociarOperacionDetalle.");

        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");

        BindingContainer bindings = getBindings();
        OperationBinding asociarOperacionesOp = bindings.getOperationBinding("asociarOperacionesDetalle");
        DCIteratorBinding operacionResultadoIterator = null;
        ViewObject operacionResultadoVO = null;

        operacionResultadoIterator =
            ADFUtils.getDCBindingContainer().findIteratorBinding("OperacionResultadoVOIterator");
        operacionResultadoVO = operacionResultadoIterator.getViewObject();
        Row[] operacionesSeleccionadasRows = operacionResultadoVO.getFilteredRows("SeleccionarOperacion", true);
        if (operacionesSeleccionadasRows != null) {
            for (Row row : operacionesSeleccionadasRows) {
                Long idOperacionAsociada = (Long) row.getAttribute("IdOperacion");
                logger.warning("IdOperacion: " + gestorOperacionesBean.getIdOperacion());
                logger.warning("IdOperacionAsociada: " + idOperacionAsociada);
                asociarOperacionesOp.getParamsMap().put("idOperacion", gestorOperacionesBean.getIdOperacion());
                asociarOperacionesOp.getParamsMap().put("idOperacionAsociada", idOperacionAsociada);
                asociarOperacionesOp.execute();

                if (asociarOperacionesOp.getErrors().size() != 0) {
                    // Manejo de errores







                }
            }
            consultarAsociaciones();

            // Limpiamos tabla y panel de búsqueda dentro del popup
            resetPopupAsociarOperacion();

            //Se reejecuta query para Mostrar asociaciones actuales

            // Cerramos popup
            getPopUpAsociarOperacion().hide();
        }
    }

    public void cancelarAsociarOperacionesActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside cancelarAsociarOperacionesActionListener: " + actionEvent.getComponent().getId());

        // Limpiamos tabla y panel de búsqueda dentro del popup
        resetPopupAsociarOperacion();

        // Cerramos popup
        getPopUpAsociarOperacion().hide();
    }

    public void cancelarAsociarOperacionesDetalleActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,
                   "Inside cancelarAsociarOperacionesActionListener: " + actionEvent.getComponent().getId());

        // Limpiamos tabla y panel de búsqueda dentro del popup
        resetPopupAsociarOperacion();

        //Se reejecuta query para Mostrar asociaciones actuales
        consultarAsociaciones();

        // Cerramos popup
        getPopUpAsociarOperacion().hide();
    }


    private void resetPopupAsociarOperacion() {
        logger.log(ADFLogger.TRACE, "Inside resetPopupAsociarOperacion.");
        DCIteratorBinding operacionResultadoIterator = null;
        ViewObject operacionResultadoVO = null;

        // Hacemos un reset en panel de búsqueda antes de cerrar el popup
        QueryModel queryModel = queryOpResultado.getModel();
        QueryDescriptor queryDescriptor = queryOpResultado.getValue();
        queryModel.reset(queryDescriptor);
        queryOpResultado.refresh(FacesContext.getCurrentInstance());

        // Limpiamos tabla
        operacionResultadoIterator =
            ADFUtils.getDCBindingContainer().findIteratorBinding("OperacionResultadoVOIterator");
        operacionResultadoVO = operacionResultadoIterator.getViewObject();
        operacionResultadoVO.executeEmptyRowSet();
    }

    public void desasociarOperacion(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside desasociarOperacion: " + actionEvent.getComponent().getId());

        BindingContainer bindings = getBindings();
        DCIteratorBinding voAsociarOperacion =
            ADFUtils.getDCBindingContainer().findIteratorBinding("OperacionesAsociadasVOIterator");
        ViewObject vo = voAsociarOperacion.getViewObject();
        logger.log(ADFLogger.TRACE, "cantidad de rows:" + voAsociarOperacion.getEstimatedRowCount());

        Row[] operacionesSeleccionadasRows = vo.getFilteredRows("Seleccionaroperacion", false);
        logger.log(ADFLogger.TRACE, "cantidad de rows check en null" + operacionesSeleccionadasRows.length);

        OperationBinding operationBinding = bindings.getOperationBinding("limpiarAsociarOperaciones");
        operationBinding.execute();
        operationBinding = bindings.getOperationBinding("asociarOperaciones");
        for (Row row : operacionesSeleccionadasRows) {

            operationBinding.getParamsMap().put("row", row);
            operationBinding.execute();
        }
    }


    public void testClear(ActionEvent actionEvent) {
        // Add event code here...
        resetPopupBuscarCliente();
    }

    public RichPopup getPopupCancelarOperacion() {
        return popupCancelarOperacion;
    }

    public void setPopupCancelarOperacion(RichPopup popupCancelarOperacion) {
        this.popupCancelarOperacion = popupCancelarOperacion;
    }

    public void setPopUpConfirmarCancelarCrearCliente(RichPopup popUpConfirmarCancelarCrearCliente) {
        this.popUpConfirmarCancelarCrearCliente = popUpConfirmarCancelarCrearCliente;
    }

    public RichPopup getPopUpConfirmarCancelarCrearCliente() {
        return popUpConfirmarCancelarCrearCliente;
    }

    public void botonAceptarConfirmarCancelarCrearClienteActionListener(ActionEvent actionEvent) {
        logger.fine("Inside botonAceptarConfirmarCancelarCrearClienteActionListener");
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        if (null != gestorOperacionesBean) {
            gestorOperacionesBean.setRazonSocialCliente(null);
            gestorOperacionesBean.setAbreviaturaCliente(null);
            gestorOperacionesBean.setIdTipoPersonaCliente(null);
            gestorOperacionesBean.setIdSectorCliente(null);
            gestorOperacionesBean.setIdTipoInstitucion(null);
            gestorOperacionesBean.setIdPaisCliente(null);
            gestorOperacionesBean.setIdGrupoCliente(null);
            gestorOperacionesBean.setNumeroIdentificacionCliente(null);
            gestorOperacionesBean.setIdOficinaCliente(null);
        }
        getPopUpConfirmarCancelarCrearCliente().hide();
        getPopupCrearCliente().hide();
    }

    public String botonCancelarConfirmarCancelarRegistroCliente() {
        getPopUpConfirmarCancelarCrearCliente().hide();
        return null;
    }

    /**
     * Método para validar los datos requeridos del cliente
     * @since 26-oct-15 MJR
     * @return si es o no un registro válido
     */
    private Boolean validarCrearCliente() {
        GestorOperacionesBean gestorOperacionesBean = null;
        Boolean esDatosCorrectos = Boolean.TRUE;

        gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        if (null != gestorOperacionesBean) {
            if (esTextoInvalido(gestorOperacionesBean.getRazonSocialCliente())) {
                JSFUtils.addFacesErrorMessage("Razón social es un dato requerido.");
                esDatosCorrectos = Boolean.FALSE;
            }
            if (null == gestorOperacionesBean.getIdPaisCliente()) {
                JSFUtils.addFacesErrorMessage("País es un dato requerido.");
                esDatosCorrectos = Boolean.FALSE;
            }
            if (esTextoInvalido(gestorOperacionesBean.getAbreviaturaCliente())) {
                JSFUtils.addFacesErrorMessage("Abreviatura es un dato requerido.");
                esDatosCorrectos = Boolean.FALSE;
            }
            if (esTextoInvalido(gestorOperacionesBean.getNumeroIdentificacionCliente())) {
                JSFUtils.addFacesErrorMessage("Numero de identificación es un dato requerido.");
                esDatosCorrectos = Boolean.FALSE;
            }
            if (null == gestorOperacionesBean.getIdGrupoCliente()) {
                JSFUtils.addFacesErrorMessage("Grupo es un dato requerido.");
                esDatosCorrectos = Boolean.FALSE;
            }
            if (null == gestorOperacionesBean.getIdTipoPersonaCliente()) {
                JSFUtils.addFacesErrorMessage("Tipo persona es un dato requerido.");
                esDatosCorrectos = Boolean.FALSE;
            }
            if (null == gestorOperacionesBean.getIdSectorCliente()) {
                JSFUtils.addFacesErrorMessage("Sector es un dato requerido.");
                esDatosCorrectos = Boolean.FALSE;
            }
            if (null == gestorOperacionesBean.getIdOficinaCliente()) {
                JSFUtils.addFacesErrorMessage("Oficina es un dato requerido.");
                esDatosCorrectos = Boolean.FALSE;
            }
            if (null == gestorOperacionesBean.getIdTipoInstitucion()) {
                JSFUtils.addFacesErrorMessage("Tipo institución es un dato requerido.");
                esDatosCorrectos = Boolean.FALSE;
            }

            if (esDatosCorrectos) {

                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding oper = bindings.getOperationBinding("validarClienteDuplicado");
                if (oper != null) {

                    logger.warning("Ejecuta la validacion de cliente duplicado");
                    oper.execute();

                    if (oper.getErrors().isEmpty()) {
                        Boolean result = (Boolean) oper.getResult();
                        if (result != null) {
                            logger.warning("Resultado de la validacion: " + result);
                            if (!result) {
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CLIENTE_DUPLICADO"));
                                esDatosCorrectos = Boolean.FALSE;
                            }
                        } else {
                            logger.severe("Resultado obtenido es NULL");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_VALIDA_CLIENTE_DUPLICADO_ERROR"));
                            esDatosCorrectos = Boolean.FALSE;
                        }
                    } else {
                        logger.severe("El proceso de validacion contiene errores");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_VALIDA_CLIENTE_DUPLICADO_ERROR") + ". " +
                                                      oper.getErrors().toString());
                        esDatosCorrectos = Boolean.FALSE;
                    }
                } else {
                    logger.severe("No se pudo obtener instancia del Operator Bindings validarClienteDuplicado");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_VALIDA_CLIENTE_DUPLICADO_ERROR"));
                    esDatosCorrectos = Boolean.FALSE;
                }
            }
        } else {
            logger.severe("No se pudo obtener instancia del GestorOperacioneasBean");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_VALIDA_CLIENTE_DUPLICADO_ERROR"));
            esDatosCorrectos = Boolean.FALSE;
        }

        return esDatosCorrectos;
    }

    /**
     * Método para validar si capturaron texto
     * @since 26-oct-15 MJR
     * @return si es o no un registro válido
     */
    private boolean esTextoInvalido(String binding) {
        //        return (null == JSFUtils.resolveExpression(binding) ||
        //                JSFUtils.resolveExpression(binding).toString().isEmpty() ||
        //                JSFUtils.resolveExpression(binding).toString().replaceAll("\\s+", "").length() == 0);
        return (null == binding || binding.isEmpty() || binding.replaceAll("\\s+", "").length() == 0);
    }

    /**
     * Método para validar si seleccionaron un elemento de lista
     * @since 26-oct-15 MJR
     * @return si es o no un registro válido
     */
    private boolean esElementoInvalido(String binding) {
        //return (null == (JSFUtils.resolveExpression(binding)) ||
        //(JSFUtils.resolveExpression(binding).toString().isEmpty()));
        return (null == binding || binding.isEmpty());
    }

    public void programadoPoaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        logger.log(ADFLogger.TRACE, "Inside programadoPoaValueChangeListener");
        try {
            //Recuperar el Bean GestorOperacionesBean
            GestorOperacionesBean gestorOperacionesBean =
                (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
            //Recuperar el Iterador de CrearOperacionVO
            //        DCIteratorBinding voCrearOperacion = ADFUtils.getDCBindingContainer().
            //                                            findIteratorBinding("CrearOperacionVOIterator");
            //actualiza el valor seleccionado
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            //Validar que el nuevo valor sea diferente a null
            if (valueChangeEvent.getNewValue() != null) {
                logger.log(ADFLogger.TRACE,
                           "Into ejercicioPoaValueChangeListener " + valueChangeEvent.getNewValue().toString());
                // Si el valor es 1 = true
                if (valueChangeEvent.getNewValue().toString().equals("1") ||
                    valueChangeEvent.getNewValue().toString() == "1") {
                    //Visible el ejercicio POA
                    gestorOperacionesBean.setEsEjercicioPoa(true);
                    //Inicialisa con el id 6, año en curso
                    //voCrearOperacion.getRowAtRangeIndex(0).setAttribute("EjercicioPoa",6);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getSocEjercicioPoa());







                } else {
                    //Ocultar el ejercicio POA
                    gestorOperacionesBean.setEsEjercicioPoa(false);
                    //limpiar el valor de ejercicio POA
                    //voCrearOperacion.getRowAtRangeIndex(0).setAttribute("EjercicioPoa",null);
                    //AdfFacesContext.getCurrentInstance().addPartialTarget(getSocEjercicioPoa());
                }


            } else {
                //Valor programado POA es null , ocultar ejercicio Poa
                gestorOperacionesBean.setEsEjercicioPoa(false);
                //Limpiar el valor de ejercicio POA
                //voCrearOperacion.getRowAtRangeIndex(0).setAttribute("EjercicioPoa",null);
                //AdfFacesContext.getCurrentInstance().addPartialTarget(getSocEjercicioPoa());
            }

        } catch (Exception e) {
            //Mostrar la excepcion en el looger con alerta ERROR
            logger.log(ADFLogger.ERROR, e.getMessage());
        }
    }

    public void setSocEjercicioPoa(RichSelectOneChoice socEjercicioPoa) {
        this.socEjercicioPoa = socEjercicioPoa;
    }

    public RichSelectOneChoice getSocEjercicioPoa() {
        return socEjercicioPoa;
    }


    /**
     * Obtiene cadena valor del Resource Bundle
     * @param psKey contiene clave de bundle
     * @return devuelve cadena
     */
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }

    public void tipoInstitucionValueChangeListener(ValueChangeEvent valueChangeEvent) {
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        Integer tipoInstitucion = null;

        if (valueChangeEvent != null && gestorOperacionesBean != null) {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            if (null != ADFUtils.getBoundAttributeValue("TipoInstitucionAttrValue")) {

                try {
                    tipoInstitucion =
                        Integer.valueOf(ADFUtils.getBoundAttributeValue("TipoInstitucionAttrValue").toString());
                } catch (Exception e) {
                    logger.warning("valor no seleccionado");
                    tipoInstitucion = null;
                }

                logger.warning("TipoInstitucion: " + tipoInstitucion);
                gestorOperacionesBean.setIdTipoInstitucion(tipoInstitucion);
            } else {
                logger.warning("valor no seleccionado");
                tipoInstitucion = null;

                logger.warning("TipoInstitucion: " + tipoInstitucion);
                gestorOperacionesBean.setIdTipoInstitucion(tipoInstitucion);
            }
        }
    }

    public void asignarRazonSocial(ValueChangeEvent valueChangeEvent) {
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        String razonSocial = null;
        if (valueChangeEvent != null) {

            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            razonSocial = (String) ADFUtils.getBoundAttributeValue("RazonSocial");
            logger.warning("Razon social: " + razonSocial);
            if (null != gestorOperacionesBean) {
                logger.warning("Razon social: " + razonSocial);
                gestorOperacionesBean.setRazonSocialCliente(razonSocial);
            } else {
                gestorOperacionesBean.setRazonSocialCliente(null);
            }
        }
    }

    public void asignarAbreviatura(ValueChangeEvent valueChangeEvent) {
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        String abreviatura = null;
        if (valueChangeEvent != null) {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            abreviatura = (String) ADFUtils.getBoundAttributeValue("Abreviatura");
            if (null != gestorOperacionesBean) {
                logger.warning("Abreviatura: " + abreviatura);
                gestorOperacionesBean.setAbreviaturaCliente(abreviatura);
            } else {
                gestorOperacionesBean.setAbreviaturaCliente(null);
            }
        }
    }


    public void asignarTipoPersona(ValueChangeEvent valueChangeEvent) {
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        Integer tipoPersona = null;
        if (valueChangeEvent != null && gestorOperacionesBean != null) {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            if (null != ADFUtils.getBoundAttributeValue("TipoPersonaAttrValue")) {

                try {
                    tipoPersona = Integer.valueOf(ADFUtils.getBoundAttributeValue("TipoPersonaAttrValue").toString());
                } catch (Exception e) {
                    logger.warning("valor no seleccionado");
                    tipoPersona = null;
                }

                logger.warning("idTipoPersona: " + tipoPersona);
                gestorOperacionesBean.setIdTipoPersonaCliente(tipoPersona);
            } else {
                logger.warning("valor no seleccionado");
                tipoPersona = null;

                logger.warning("idTipoPersona: " + tipoPersona);
                gestorOperacionesBean.setIdTipoPersonaCliente(tipoPersona);
            }
        }
    }

    public void asignarSector(ValueChangeEvent valueChangeEvent) {
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        Integer sector = null;

        if (valueChangeEvent != null && gestorOperacionesBean != null) {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            if (null != ADFUtils.getBoundAttributeValue("SectorAttrValue")) {

                try {
                    sector = Integer.valueOf(ADFUtils.getBoundAttributeValue("SectorAttrValue").toString());
                } catch (Exception e) {
                    logger.warning("valor no seleccionado");
                    sector = null;
                }

                logger.warning("Sector: " + sector);
                gestorOperacionesBean.setIdSectorCliente(sector);

                ADFUtils.setBoundAttributeValue("TipoInstitucionAttrValue", null);
                gestorOperacionesBean.setIdTipoInstitucion(null);
            } else {
                logger.warning("valor no seleccionado");
                sector = null;

                logger.warning("Sector: " + sector);
                gestorOperacionesBean.setIdSectorCliente(sector);

                ADFUtils.setBoundAttributeValue("TipoInstitucionAttrValue", null);
                gestorOperacionesBean.setIdTipoInstitucion(null);
            }
        }
    }

    public void asignarPais(ValueChangeEvent valueChangeEvent) {
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        Integer pais = null;

        if (valueChangeEvent != null && gestorOperacionesBean != null) {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            if (null != ADFUtils.getBoundAttributeValue("PaisAttrValue")) {

                try {
                    pais = Integer.valueOf(ADFUtils.getBoundAttributeValue("PaisAttrValue").toString());
                } catch (Exception e) {
                    logger.warning("valor no seleccionado");
                    pais = null;
                }

                logger.warning("pais: " + pais);
                gestorOperacionesBean.setIdPaisCliente(pais);
            } else {
                logger.warning("valor no seleccionado");
                pais = null;

                logger.warning("pais: " + pais);
                gestorOperacionesBean.setIdPaisCliente(pais);
            }
        }
    }
    
    public void asignarIdCatPais(ValueChangeEvent valueChangeEvent) {
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");        
     
        BigDecimal idCatPais = null;

        if (valueChangeEvent != null && gestorOperacionesBean != null) {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            if (null != ADFUtils.getBoundAttributeValue("PaisAttrValue")) {

                try {
                    idCatPais = new BigDecimal(ADFUtils.getBoundAttributeValue("PaisAttrValue").toString());
                } catch (Exception e) {
                    logger.warning("valor no seleccionado");
                    idCatPais = null;
                }

                logger.warning("idCatPais: " + idCatPais);
                gestorOperacionesBean.setIdCatPais(idCatPais);
            } else {
                logger.warning("valor no seleccionado");
                idCatPais = null;

                logger.warning("idCatPais: " + idCatPais);
                gestorOperacionesBean.setIdCatPais(idCatPais);
            }
        }
    }
    
    public void asignarGrupoEconomico(ValueChangeEvent valueChangeEvent) {
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        Integer grupoEconomico = null;

        if (valueChangeEvent != null && gestorOperacionesBean != null) {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            if (null != ADFUtils.getBoundAttributeValue("GrupoEconomicoAttrValue")) {

                try {
                    grupoEconomico =
                        Integer.valueOf(ADFUtils.getBoundAttributeValue("GrupoEconomicoAttrValue").toString());
                } catch (Exception e) {
                    logger.warning("valor no seleccionado");
                    grupoEconomico = null;
                }

                logger.warning("GrupoEconomico: " + grupoEconomico);
                gestorOperacionesBean.setIdGrupoCliente(grupoEconomico);
            } else {
                logger.warning("valor no seleccionado");
                grupoEconomico = null;

                logger.warning("GrupoEconomico: " + grupoEconomico);
                gestorOperacionesBean.setIdGrupoCliente(grupoEconomico);
            }
        }
    }

    public void asignarNumeroIdentificacion(ValueChangeEvent valueChangeEvent) {
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        String numIdentificacion = null;

        if (valueChangeEvent != null) {

            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            numIdentificacion = (String) ADFUtils.getBoundAttributeValue("NumeroIdentificacion");
            logger.warning("NumeroIdentificacion: " + numIdentificacion);
            if (null != gestorOperacionesBean) {
                logger.warning("NumeroIdentificacion: " + numIdentificacion);
                gestorOperacionesBean.setNumeroIdentificacionCliente(numIdentificacion);
            } else {
                gestorOperacionesBean.setNumeroIdentificacionCliente(null);
            }
        }
    }

    public void asignarOficina(ValueChangeEvent valueChangeEvent) {
        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        Integer oficina = null;

        if (valueChangeEvent != null && gestorOperacionesBean != null) {
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            if (null != ADFUtils.getBoundAttributeValue("OficinaAttrValue")) {

                try {
                    oficina = Integer.valueOf(ADFUtils.getBoundAttributeValue("OficinaAttrValue").toString());
                } catch (Exception e) {
                    logger.warning("valor no seleccionado");
                    oficina = null;
                }

                logger.warning("Oficina: " + oficina);
                gestorOperacionesBean.setIdOficinaCliente(oficina);
            } else {
                logger.warning("valor no seleccionado");
                oficina = null;

                logger.warning("Oficina: " + oficina);
                gestorOperacionesBean.setIdOficinaCliente(oficina);
            }
        }
    }

    public void desasociarOperacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside desasociarOperacionActionListener.");
        Long id = 0L;
        BindingContainer bindings = getBindings();
        DCIteratorBinding voConsultarAsociadas =
            ADFUtils.getDCBindingContainer().findIteratorBinding("ConsultarAsociadasVOIterator");
        ViewObject vo = voConsultarAsociadas.getViewObject();
        logger.log(ADFLogger.TRACE, "cantidad de rows:" + voConsultarAsociadas.getEstimatedRowCount());

        Row[] operacionesSeleccionadasRows = vo.getFilteredRows("Seleccionaroperacion", true);
        logger.log(ADFLogger.TRACE, "cantidad de rows check en null" + operacionesSeleccionadasRows.length);

        if (operacionesSeleccionadasRows != null) {
            OperationBinding operationBinding = bindings.getOperationBinding("eliminarRowAsociada");
            for (Row row : operacionesSeleccionadasRows) {
                id = (Long) row.getAttribute("Id");
                operationBinding.getParamsMap().put("id", id);
                operationBinding.execute();
            }

            //Se reejecuta query para Mostrar asociaciones actuales
            consultarAsociaciones();
        }
    }

    public void consultarAsociaciones() {
        logger.log(ADFLogger.TRACE, "Inside consultarAsociaciones.");

        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        logger.log(ADFLogger.TRACE, "set idOperacion to ConsultarAsociadasVO");
        operationBinding = bindings.getOperationBinding("setvarIdOperacion");
        operationBinding.getParamsMap().put("value", gestorOperacionesBean.getIdOperacion());
        operationBinding.execute();

    }

    public Long getIdAsociada() {
        return idAsociada;
    }

    public void setIdAsociada(Long idAsociada) {
        this.idAsociada = idAsociada;
    }

    @SuppressWarnings("unchecked")
    public void establecerSeleccionar(ValueChangeEvent valueChangeEvent) {
        logger.warning("Entrando en establecerSeleccionar.");
        Boolean nuevoValor;
        if (valueChangeEvent.getNewValue() != null) {
            String newValue = valueChangeEvent.getNewValue().toString();

            logger.warning("newValue: " + newValue);

            if (null != newValue) {
                nuevoValor = Boolean.valueOf(newValue);
                logger.warning("nuevoValor: " + nuevoValor);

                BindingContainer bindings = ADFUtils.getBindingContainer();

                OperationBinding operationBinding = bindings.getOperationBinding("establecerSeleccionAsociada");
                operationBinding.getParamsMap().put("idAsociada", getIdAsociada());
                operationBinding.getParamsMap().put("nuevoValor", nuevoValor);
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                }
            }
        } else {
            logger.warning("Error valueChangeEvent es nulo.");
        }
    }
    
    public void setSocSectorInstitucional(RichSelectOneChoice socSectorInstitucional) {
        this.socSectorInstitucional = socSectorInstitucional;
    }

    public RichSelectOneChoice getSocSectorInstitucional() {
        return socSectorInstitucional;
    }
    
    public void setSoc6(RichSelectOneChoice soc6) {
        this.soc6 = soc6;
    }

    public RichSelectOneChoice getSoc6() {
        return soc6;
    }
}
