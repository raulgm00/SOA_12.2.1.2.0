package org.bcie.fenix.view.operacion;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.NumberConverter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.MethodExpressionValueChangeListener;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPoll;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSeparator;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.ClientListenerSet;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.event.PollEvent;

import org.bcie.catalogobo.Catalogo;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.operacionbo.ClasificacionEstrategicaMultisectorial;
import org.bcie.operacionbo.ComponenteClasificacionEstrategicaType;
import org.bcie.operacionbo.Operacion;
import org.bcie.operacionmo.ActualizarOperacionRequestType;
import org.bcie.operacionmo.ActualizarOperacionResponseType;

public class EditarClasificacionEstrategicaBean {
    /* ==============      VARIABLES       ============= */
    private StringBuilder mensajeErrores;
    private int indice;
    private ArrayList<ComponenteBean> componentes;
    private ArrayList<String> errores;
    private int numPorcentaje;
    private static ADFLogger logger = null;
    private RichSeparator dummySeparatorCEEd;
    private int multisectorial;
    private RichPoll pollEd;

    /* ==============      CONSTANTES       ============= */
    private static final String ID_NOMBRE = "ritNom";
    private static final String ID_DESCRIPCION = "ritDsc";
    private static final String ID_PORCENTAJE = "ritPsc";
    private static final String ID_SOC_AEP = "socAEP";
    private static final String ID_SI_AEP = "siAEP";
    private static final String ID_SOC_IE = "socInEs";
    private static final String ID_SI_IE = "siInEs";
    private static final String ID_SOC_CP = "socPais";
    private static final String ID_SI_CP = "siPais";
    private static final String ID_SOC_AE = "socAEc";
    private static final String ID_SI_AE = "siAEc";
    private static final String ID_SOC_AF = "socAF";
    private static final String ID_SI_AF = "siAF";
    private static final String ID_SOC_EE = "socEEs";
    private static final String ID_SI_EE = "siEEs";
    private static final String ID_LBL_NOMBRE = "lblNom";
    private static final String ID_ROT_NOMBRE = "rotNom";
    private static final String ID_RPGL_NOMBRE = "rpglNom";
    private static final String ID_PANEL_ORIGINAL = "pglOriginalCEEd";
    private static final String ID_PANEL_GRAL_EDITABLE = "pgGralCEEditable";
    private static final String ID_PANEL_COMPONENTES = "pgCEEditable";
    private static final String LABEL_STYLE = "width: 140px;padding-left: 20px;text-align: left;";
    private static final String LABEL_STYLE_V1 = "width: 230px;padding-left: 20px;text-align: left;";
    private static final String OutputLabel_INLINESTYLE = "color:#091e98";
    private static final String OutputText_INLINESTYLE_C1 =
        "white-space: -moz-pre-wrap;white-space: pre;white-space: pre-wrap;word-wrap: break-word; max-width: 300px;color:#4f4f4f;font-size:12px;";
    private static final String OutputText_INLINESTYLE_C2 =
        "white-space: -moz-pre-wrap;white-space: pre;white-space: pre-wrap;word-wrap: break-word; max-width: 500px;color:#4f4f4f;font-size:12px;";

    private static final String CONTENT_STYLE_V1 = "width: 300px;";
    private static final String CONTENT_STYLE_V2 =
        "width: 300px;white-space: -moz-pre-wrap;white-space: pre;white-space: pre-wrap;word-wrap: break-word;";
    private static final String CONTENT_STYLE_V3 = "width: 100px;";
    private static final String CONTENT_STYLE_V4 = "width: 600px;";
    private static final String CONTENT_STYLE_V5 = "width: 900px;";
    private static final String BUNDLE = "org.bcie.fenix.view.GestorOperacionesFenixVCBundle";
    /* ==============      CONSTRUCTOR       ============= */

    public EditarClasificacionEstrategicaBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }

    }
    /* ==============     CUSTOM METHODS     ============= */

    /**
     * Método auxiliar que refresca el panel de los componentes dinámicos
     * @author : S&P Solutions
     * @param  : PollEvent
     * @return :
     * @version: v1.0
     * @Fecha  : 27/11/2019
     */
    public void pollListenerEd(PollEvent pollEvent) {
        logger.warning("\n=== Se ejecuta | poll listener =");
        try {
            valoresIniciales();
            pollEd.setInterval(-1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(pollEd);
        } catch (Exception ex) {
            pollEd.setInterval(-1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(pollEd);

        }
    }

    /**
     * Método que hace el set de valores por defecto
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */

    public void valoresIniciales() {
        logger.warning("\n=== Se ejecuta valoresIniciales  ===");
        //Set de index en 1 para manejo de componentes dinámicos
        logger.warning("\n=== Set indice en 1  ===");
        indice = 1;
        //Arreglo de tipo componenteBean (Clasificación Estratégica)
        componentes = new ArrayList<ComponenteBean>();
        // Arreglo para manejo de errores
        mensajeErrores = new StringBuilder();
        errores = new ArrayList<String>();
        //Suma de porcentajes
        numPorcentaje = 0;
        //Recuperar valor EsMultisectorial
        int esMultisectorial =
            Integer.valueOf(JSFUtils.resolveExpression("#{bindings.EsMultisectorial.inputValue}").toString());
        logger.warning("\n=== Se recupera valor esMultisectoriral:" + esMultisectorial);
        int valor = esMultisectorial == 0 ? 2 : 1;
        logger.warning("\n=== valor:" + valor);
        setExpressionValue("#{bindings.v_multisectorial.inputValue}", valor);
        actualizarComponente("socMs");
        multisectorial = esMultisectorial;
        //valorMultisectorial();
        if (multisectorial == 1) {
            logger.warning("\n=== Se ejecuta: crearComponentes ===");
            limpiarComponentes(obtenerComponente(ID_PANEL_COMPONENTES));
            //Crear componentes en caso de ser multisectorial
            crearComponentes();
            //hacer visible el panel
            logger.warning("\n=== Se ejecuta: visibilidad de paneles | Multisectorial visible ===");
            verComponente(ID_PANEL_GRAL_EDITABLE, true);
            verComponente(ID_PANEL_ORIGINAL, false);
        } else {
            //Mostrar panel no multi
            logger.warning("\n=== Se ejecuta: visibilidad de paneles | No multisectorial visible ===");
            verComponente(ID_PANEL_GRAL_EDITABLE, false);
            verComponente(ID_PANEL_ORIGINAL, true);
        }


    }

    /**
     * Método que obtiene el valor de la variable EsMultisectorial
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */

    public void valorMultisectorial() {
        //Recuperar valor EsMultisectorial
        multisectorial =
            Integer.valueOf(JSFUtils.resolveExpression("#{bindings.EsMultisectorial.inputValue}").toString());
        //Set valor v_multisectorial
        //0 - No => 2
        //1 - Si => 1
        setExpressionValue("#{bindings.v_multisectorial.inputValue}", multisectorial == 0 ? 2 : 1);
    }


    /**
     * Método que controla el comportamiento del combo  Multisectorial.
     * @author : S&P Solutions
     * @param  : ValueChangeEvent
     * @version: v1.0
     * @Fecha  : 22/05/2019
     */
    public void vclMultisectorial(ValueChangeEvent valueChangeEvent) {

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        logger.warning("\n=== Detalle Editable | vclMultisectorial: " + valueChangeEvent.getNewValue().toString());
        if (valueChangeEvent.getNewValue().toString().equals("1")) { //Opción: Multisectorial
            logger.warning("\n=== vclMultisectorial | opcion Multisectorial");

            if (multisectorial == 0) { //Originalmente no multisectorial
                //Obtener valores y crear componente
                limpiarComponentes(obtenerComponente(ID_PANEL_COMPONENTES));
                indice = 1;
                crearComponenteNoMulti2Multi(indice);
                indice++;
                AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANEL_COMPONENTES));
            }
            verComponente(ID_PANEL_GRAL_EDITABLE, true);
            verComponente(ID_PANEL_ORIGINAL, false);
        } else {
            logger.warning("\n=== vclMultisectorial | opcion NO Multisectorial");
            if (multisectorial == 1) { //Originalmente  multisectorial
                //Pasar valores a bindigs
                componenteMulti2NoMulti();
                AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANEL_ORIGINAL));
            }
            verComponente(ID_PANEL_GRAL_EDITABLE, false);
            verComponente(ID_PANEL_ORIGINAL, true);
        }

        //asignarValorMultisectorial();
    }

    /**
     * Método que asigna el valor EsMultisectorial al iterador CrearOperacionVO.
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 07/07/2019
     */
    public void asignarValorMultisectorial() {
        Integer multisectorial = (Integer) JSFUtils.resolveExpression("#{bindings.v_multisectorial.inputValue}");
        logger.warning("==== Pasar valor de v_multisectorial a DetalleOperacionEditableVOIterator: " + multisectorial);
        setExpressionValue("#{bindings.EsMultisectorial.inputValue}", multisectorial);
    }

    /**
     * Método que controla el comportamiento del combo  Actividad Economica Primaria creado dinámicamente.
     * @author : S&P Solutions
     * @param  : ValueChangeEvent valueChangeEvent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public void vclActividadEconomicaPrimaria(ValueChangeEvent valueChangeEvent) {
        logger.warning("\n== actividadEconomicaPadreValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idActividadEconomica = null;
        String codigoExterno = null;
        // obtener componente originador de cambio
        RichSelectOneChoice soc = (RichSelectOneChoice) valueChangeEvent.getComponent();
        String idSoc = soc.getId();
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        String valor = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : "-NA-";
        logger.warning("\n== actividadEconomicaPadreValueChangeListener | Valor: " + valor);
        if (!valor.matches("-NA-")) {
            //ObtenerID y CodExterno
            String[] valores = valor.split("-");
            idActividadEconomica = Integer.parseInt(valores[0]);
            if (idActividadEconomica > 0) {
                codigoExterno = valores[1];
            }
            // Filtramos el combo de Iniciativa estratégica por Id
            logger.warning("\n=== Filtrar combo Iniciativa estratégica por Id: " + idActividadEconomica);
            filtrarIniciativaEstrategica(new oracle.jbo.domain.Number(idActividadEconomica.intValue()));

            // Limpiamos combos dependientes: Cantidad de Países beneficiados, Actividad económica, Área de focalización y Eje estratégico
            logger.warning("\n=== Limpiar combos dependientes  ==== ");
            filtrarCantidadPaises(null);
            filtrarActividadEconomica(null);
            filtrarAreaFocalizacion(null, null);
            filtrarEjeEstrategico(null, null);

            //Actualizar componentes
            logger.warning("\n=== Actualizar componentes  ==== ");
            int index = obtenerIndice(idSoc);
            actualizarCmbIniciativaEstrategica(index);
            actualizarCmbCantidadPaises(index);
            actualizarCmbActividadEconomica(index);
            actualizarCmbAreaFocalizacion(index);
            actualizarCmbEjeEstrategico(index);
        } else {
            logger.warning("\n=== actividadEconomicaPadreValueChangeListener valor nulo ===");
        }
    }

    /**
     * Método que controla el comportamiento del combo  Iniciativa Estratégica creado dinámicamente.
     * @author : S&P Solutions
     * @param  : ValueChangeEvent valueChangeEvent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public void vclIniciativaEstrategica(ValueChangeEvent valueChangeEvent) {
        logger.warning("\n=== iniciativaEstrategicaValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idIniciativaEstrategica = null;
        // obtener componente originador de cambio
        RichSelectOneChoice soc = (RichSelectOneChoice) valueChangeEvent.getComponent();
        String idSoc = soc.getId();
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        String valor = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : "-NA-";
        logger.warning("\n===  iniciativaEstrategicaValueChangeListener | Valor: " + valor);
        int index = obtenerIndice(idSoc);
        if (!valor.matches("-NA-")) {
            //ObtenerID
            String[] valores = valor.split("-");
            idIniciativaEstrategica = Integer.parseInt(valores[0]);
            if (idIniciativaEstrategica < 1) {
                filtrarCantidadPaises(null);
                filtrarActividadEconomica(null);
                filtrarAreaFocalizacion(null, null);
                filtrarEjeEstrategico(null, null);
                actualizarCmbCantidadPaises(index);
                actualizarCmbActividadEconomica(index);
                actualizarCmbAreaFocalizacion(index);
                actualizarCmbEjeEstrategico(index);

            } else {
                filtrarCantidadPaises(new oracle.jbo.domain.Number(idIniciativaEstrategica.intValue()));
                // Limpiamos combos dependientes: Área de focalización y Eje estratégico
                filtrarActividadEconomica(null);
                filtrarAreaFocalizacion(null, null);
                filtrarEjeEstrategico(null, null);
                //Actualizar componentes
                actualizarCmbCantidadPaises(index);
                actualizarCmbActividadEconomica(index);
                actualizarCmbAreaFocalizacion(index);
                actualizarCmbEjeEstrategico(index);
            }
        } else {
            logger.warning("\n=== iniciativaEstrategicaValueChangeListener valor nulo ===");
        }
    }


    /**
     * Método que controla el comportamiento del combo  Cantidad de Paises Benecificados creado dinámicamente.
     * @author : S&P Solutions
     * @param  : ValueChangeEvent valueChangeEvent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public void vclCantidadPaises(ValueChangeEvent valueChangeEvent) {

        logger.warning("\n=====  cantidadPaisesBeneficiadosValueChangeListener: " +
                       valueChangeEvent.getComponent().getId());
        Integer idActividad = null; //Obtener de Iniciativa Estrategica
        Integer idIniciativaEstrategica = null; //Obtener de Iniciativa Estrategica
        Integer idRangoPaises = null; //Obtener de Cantidad de Paises
        // obtener componente originador de cambio
        RichSelectOneChoice soc = (RichSelectOneChoice) valueChangeEvent.getComponent();
        String idSoc = soc.getId();
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        String valor = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : "-";
        //Obtener valores
        String[] valores = valor.split("-");
        idRangoPaises = Integer.parseInt(valores[1]);

        logger.warning("\n=====  Obtener valores del combo - idRangoPaises: " + idRangoPaises);
        //Obtener valor Combo Iniciativa Estrategica
        int index = obtenerIndice(idSoc);
        valores = getValorTxtIniciativaEstrategica(obtenerComponente(ID_PANEL_COMPONENTES), index).split("-");
        idActividad = Integer.parseInt(valores[1]);
        idIniciativaEstrategica = Integer.parseInt(valores[0]);

        logger.warning("\n=====  Valores de IniEstrategica | idActividad: " + idActividad +
                       " | idIniciativaEstrategica:" + idIniciativaEstrategica);

        // Filtramos los combos Actividad económica, Área de focalización y Eje estratégico
        // por el idActividad, idIniciativaEstrategica e idRangoPaises
        filtrarActividadEconomica(new oracle.jbo.domain.Number(idActividad.intValue()));
        if (idRangoPaises < 1) {
            filtrarActividadEconomica(null);
            filtrarAreaFocalizacion(null, null);
            filtrarEjeEstrategico(null, null);
            actualizarCmbActividadEconomica(index);
            actualizarCmbAreaFocalizacion(index);
            actualizarCmbEjeEstrategico(index);

        } else {
            filtrarAreaFocalizacion(new oracle.jbo.domain.Number(idIniciativaEstrategica.intValue()),
                                    new oracle.jbo.domain.Number(idRangoPaises.intValue()));
            filtrarEjeEstrategico(new oracle.jbo.domain.Number(idIniciativaEstrategica.intValue()),
                                  new oracle.jbo.domain.Number(idRangoPaises.intValue()));
            //Actualizar componentes
            actualizarCmbActividadEconomica(index);
            actualizarValorCmbActividadEconomica(index);
            actualizarCmbAreaFocalizacion(index);
            actualizarValorCmbAreaFocalizacion(index);
            actualizarCmbEjeEstrategico(index);
            actualizarValorCmbEjeEstrategico(index);
        }
    }

    /**
     * Método que valida le contenido ingresado en Porcentaje.
     * @author : S&P Solutions
     * @param  : ValueChangeEvent
     * @version: v1.0
     * @Fecha  : 22/11/2019
     */
    public void vclPorcentaje(ValueChangeEvent valueChangeEvent) {
        logger.warning("\n=== Se ejecuta vclPorcentaje para: " + valueChangeEvent.getComponent().getId());
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        if (valueChangeEvent.getNewValue() != null) {
            String entrada = valueChangeEvent.getNewValue().toString();
            if ((entrada.contains("."))) {
                entrada = entrada.replaceAll(".", "");
                RichInputText rit = (RichInputText) valueChangeEvent.getComponent();
                String idRit = rit.getId();
                rit.setValue(entrada);
                actualizarComponente(idRit);
            }
        }

    }

    /**
     * Método que itera sobre el resultado de la consulta de servicio y crea los componentes
     * @author : S&P Solutions
     * @param  :
     * @return :
     * @version: v1.0
     * @Fecha  : 25/05/2019
     */
    public void crearComponentes() {
        logger.warning("\n=== Creacion de componentes dinamicos =");
        boolean esEstadoCompletado =
            Boolean.parseBoolean(JSFUtils.resolveExpression("#{(pageFlowScope.DetalleOperacionBean.esEstadoCompletado)}").toString());
        logger.warning("\n= Recuperar esEstadoCompletado:" + esEstadoCompletado);
        logger.warning("\n= Recuperar ClasificacionEstrategicaEditableVOIterator de bindings =");
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("ClasificacionEstrategicaEditableVOIterator");
        ViewObject vo = iterator.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);
        logger.warning("\n= Rows recuperados:" + rsi.getRowCount());
        //Iterar sobre VO
        while (rsi.hasNext()) {
            Row row = rsi.next();
            ComponenteBean componente = new ComponenteBean();
            //Nombre
            componente.setNombre((row.getAttribute("NombreDelComponente") != null ?
                                  row.getAttribute("NombreDelComponente").toString() : ""));
            logger.warning("\n= NombreDelComponente:" + row.getAttribute("NombreDelComponente"));
            //Descripcion
            componente.setDescripcion((row.getAttribute("DescripcionDelComponente") != null ?
                                       row.getAttribute("DescripcionDelComponente").toString() : ""));
            logger.warning("\n= DescripcionDelComponente:" + row.getAttribute("DescripcionDelComponente"));
            //Porcentaje
            componente.setPorcentaje((row.getAttribute("Porcentaje") != null ?
                                      row.getAttribute("Porcentaje").toString() : ""));
            logger.warning("\n= Porcentaje:" + row.getAttribute("Porcentaje"));
            //================ Actividad Economica Primaria ==================
            //ID
            componente.setIdActividadEconomicaPrimaria((row.getAttribute("IdActividadEconomicaCe") != null ?
                                                        row.getAttribute("IdActividadEconomicaCe").toString() : ""));
            logger.warning("\n= IdActividadEconomicaCe:" + row.getAttribute("IdActividadEconomicaCe"));
            //CODEXT
            componente.setCodigoExterno((row.getAttribute("CodExtActividadEconomicaCe") != null ?
                                         row.getAttribute("CodExtActividadEconomicaCe").toString() : ""));
            logger.warning("\n= CodExtActividadEconomicaCe:" + row.getAttribute("CodExtActividadEconomicaCe"));
            //DESCRIPCION
            componente.setActividadEconomicaPrimaria((row.getAttribute("ActividadEconomicaCe") != null ?
                                                      row.getAttribute("ActividadEconomicaCe").toString() : ""));
            logger.warning("\n= ActividadEconomicaCe:" + row.getAttribute("ActividadEconomicaCe"));
            //================ Iniciativa Estrategica ==================//
            //Id Iniciativa Estrategia
            componente.setIdIniciativaEstrategica((row.getAttribute("IdIniciativaEstrategicaCe") != null ?
                                                   row.getAttribute("IdIniciativaEstrategicaCe").toString() : ""));
            logger.warning("\n= IdIniciativaEstrategicaCe:" + row.getAttribute("IdIniciativaEstrategicaCe"));
            //Descripcion Iniciativa Estrategia
            componente.setIniciativaEstrategica((row.getAttribute("IniciativaEstrategicaCe") != null ?
                                                 row.getAttribute("IniciativaEstrategicaCe").toString() : ""));
            logger.warning("\n= IniciativaEstrategicaCe:" + row.getAttribute("IniciativaEstrategicaCe"));
            //================ Cantidad de paises ==================//
            //Id Cantidad de paises
            componente.setIdCantidadPaises((row.getAttribute("IdCantidadPaisesCe") != null ?
                                            row.getAttribute("IdCantidadPaisesCe").toString() : ""));
            logger.warning("\n= IdCantidadPaisesCe:" + row.getAttribute("IdCantidadPaisesCe"));
            //Descripcion Cantidad de paises
            componente.setCantidadPaises((row.getAttribute("CantidadPaisesCe") != null ?
                                          row.getAttribute("CantidadPaisesCe").toString() : ""));
            logger.warning("\n= CantidadPaisesCe:" + row.getAttribute("CantidadPaisesCe"));
            //CodextPaisesCe
            componente.setCodExtPaises((row.getAttribute("CodextPaisesCe") != null ?
                                        row.getAttribute("CodextPaisesCe").toString() : ""));
            logger.warning("\n= CodextPaisesCe:" + row.getAttribute("CodextPaisesCe"));

            //================ Actividad Economica ==================//
            //Id Actividad Economica
            componente.setIdActividadEconomica((row.getAttribute("IdActividadEconomicaAsoCe") != null ?
                                                row.getAttribute("IdActividadEconomicaAsoCe").toString() : ""));
            logger.warning("\n= IdActividadEconomicaAsoCe:" + row.getAttribute("IdActividadEconomicaAsoCe"));
            //Descripcion Actividad Economica
            componente.setActividadEconomica((row.getAttribute("ActividadEconomicaAsoCe") != null ?
                                              row.getAttribute("ActividadEconomicaAsoCe").toString() : ""));
            logger.warning("\n= ActividadEconomicaAsoCe:" + row.getAttribute("ActividadEconomicaAsoCe"));
            //Codigo Externo Actividad Economica
            componente.setCodigoExterno1((row.getAttribute("CodextActEconomicAsoCe") != null ?
                                          row.getAttribute("CodextActEconomicAsoCe").toString() : ""));
            logger.warning("\n= CodextActEconomicAsoCe:" + row.getAttribute("CodextActEconomicAsoCe"));

            //================ Area de focalizacion ==================//
            //Id Area de focalizacion
            componente.setIdAreaFocalizacion((row.getAttribute("IdAreaFocalizacionCe") != null ?
                                              row.getAttribute("IdAreaFocalizacionCe").toString() : ""));
            logger.warning("\n= IdAreaFocalizacionCe:" + row.getAttribute("IdAreaFocalizacionCe"));
            //Descripcion Area de focalizacion
            componente.setAreaFocalizacion((row.getAttribute("AreaFocalizacionCe") != null ?
                                            row.getAttribute("AreaFocalizacionCe").toString() : ""));
            logger.warning("\n= AreaFocalizacionCe:" + row.getAttribute("AreaFocalizacionCe"));
            //================ Eje Estrategico ==================//
            //Id Eje Estrategico
            componente.setIdEjeEstrategico((row.getAttribute("IdEjeEstrategicoCe") != null ?
                                            row.getAttribute("IdEjeEstrategicoCe").toString() : ""));
            logger.warning("\n= IdEjeEstrategicoCe:" + row.getAttribute("IdEjeEstrategicoCe"));
            //Descripcion Eje Estrategico
            componente.setEjeEstrategico((row.getAttribute("EjeEstrategicoCe") != null ?
                                          row.getAttribute("EjeEstrategicoCe").toString() : ""));
            logger.warning("\n= EjeEstrategicoCe:" + row.getAttribute("EjeEstrategicoCe"));
            //Codigo Eje Estrategico
            componente.setCodigoEjeEstrategico((row.getAttribute("CodEjeEstrategicoCe") != null ?
                                                row.getAttribute("CodEjeEstrategicoCe").toString() : ""));
            logger.warning("\n= CodEjeEstrategicoCe:" + row.getAttribute("CodEjeEstrategicoCe"));

            if (esEstadoCompletado) {
                logger.warning("\n=== crearComponenteNoEditable: " + indice);
                crearComponenteNoEditable(indice, componente);
            } else {
                logger.warning("\n=== crearComponenteEditableInicial: " + indice);
                crearComponenteEditableInicial(indice, componente);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANEL_COMPONENTES));
            indice++;
        }
        rsi.closeRowSetIterator();

    }


    /**
     * Método auxiliar para la creacion de formularios dinámicos.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public void crearComponente(ActionEvent actionEvent) {
        logger.warning("=============== ActionListener: crearComponente | indice:" + indice);
        validarComponentes();
        if (mensajeErrores.length() > 0) {
            // mostar errores
            mostrarPopupErrorAgregarClasificacion(true);
        } else {
            crearComponente(indice);
            indice++;
            AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANEL_COMPONENTES));

        }


    }

    /**
     * Método auxiliar para mapear los valores de los componentes dinámicos al VO de Clasificación Estratégica.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public void mapearValoresComponentes() {
        logger.warning("=============== mapearValoresComponentes (): mapear los valores de los componentes dinámicos al VO de CE ================");
        // int index = 0;
        DCIteratorBinding voComponentes =
            ADFUtils.getDCBindingContainer().findIteratorBinding("ClasificacionEstrategicaVOIterator");
        ViewObject vo = voComponentes.getViewObject();
        vo.clearCache();
        vo.executeEmptyRowSet();
        for (ComponenteBean componente : componentes) {
            Row row = vo.createRow();

            row.setAttribute("nombre", componente.getNombre());
            row.setAttribute("descripcion", componente.getDescripcion());
            row.setAttribute("porcentaje", componente.getPorcentaje());
            row.setAttribute("actividadEconomica", componente.getActividadEconomicaPrimaria());
            row.setAttribute("iniciativaEstrategica", componente.getIniciativaEstrategica());
            row.setAttribute("cantidadPaises", componente.getCantidadPaises());
            row.setAttribute("actividadEconomicaAsociada", componente.getActividadEconomica());
            row.setAttribute("areaFocalizacion", componente.getAreaFocalizacion());
            row.setAttribute("ejeEstrategico", componente.getEjeEstrategico());
            vo.insertRow(row);
        }
        // valoresVO();

    }

    /**
     * Método auxiliar para visualizar los valores del VO de Clasificación Estratégica (mantenre comentado a menos que sea necesario por trazabilidad).
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public void valoresVO() {
        logger.warning("=============== valoresVO (): Validar valores mapeados ===============");

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("ClasificacionEstrategicaVOIterator");
        ViewObject vo = iterator.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);
        //  RowSetIterator rsi = iterator.getRowSetIterator();
        logger.warning("=============== valoresVO size:" + rsi.getRowCount());
        while (rsi.hasNext()) {
            Row row = rsi.next();
            logger.warning("=============== COMPONENTE ===============");
            logger.warning("=== Nombre: " + row.getAttribute("nombre") != null ? row.getAttribute("Nombre").toString() :
                           "");
            logger.warning("=== Descripcion: " + row.getAttribute("descripcion") != null ?
                           row.getAttribute("Descripcion").toString() : "");
            logger.warning("=== Porcentaje: " + row.getAttribute("porcentaje") != null ?
                           row.getAttribute("porcentaje").toString() : "");
            logger.warning("=== AEP: " + row.getAttribute("actividadEconomica") != null ?
                           row.getAttribute("actividadEconomica").toString() : "");
            logger.warning("=== IE: " + row.getAttribute("iniciativaEstrategica") != null ?
                           row.getAttribute("iniciativaEstrategica").toString() : "");
            logger.warning("=== CP: " + row.getAttribute("cantidadPaises") != null ?
                           row.getAttribute("cantidadPaises").toString() : "");
            logger.warning("=== AES: " + row.getAttribute("actividadEconomicaAsociada") != null ?
                           row.getAttribute("actividadEconomicaAsociada").toString() : "");
            logger.warning("=== AF: " + row.getAttribute("areaFocalizacion") != null ?
                           row.getAttribute("areaFocalizacion").toString() : "");
            logger.warning("=== EE: " + row.getAttribute("ejeEstrategico") != null ?
                           row.getAttribute("ejeEstrategico").toString() : "");
        }
        rsi.closeRowSetIterator();
    }


    /**
     * Método que valida los valores  del formulario dinámico.
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */

    public ArrayList<String> validarComponentesMultisectorial() {
        errores.clear();
        logger.warning("Inside validarComponentesMultisectorial");
        numPorcentaje = 0;
        logger.warning("Valor de indice:" + indice);
        validarComponentes();
        logger.warning("Suma de porcentajes: " + numPorcentaje);
        if (indice <= 2) {
            errores.add("Se requieren mínimo dos componentes para una Operacion multisectorial.");
        }

        if (numPorcentaje != 100) {
            errores.add("La suma de los porcentajes de los componentes debe ser 100");
        }

        /*
        * No necesario mapear en para caso editable
        * if (errores.size() == 0) {
            //Pasar valores a VO
            mapearValoresComponentes();
        }*/

        return errores;
    }

    /**
     * Método que limpia mensaje de errores y oculta popup.
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 31/05/2019
     */
    public void accionPopUpMsgErrores() {
        mensajeErrores = new StringBuilder();
        //Cerrar popup
        mostrarPopupErrorAgregarClasificacion(false);
    }

    /**
     * Método que elimina todos los componentes hijos de un componente padre.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    public void limpiarComponentes(UIComponent parentUIComponent) {
        parentUIComponent.getChildren().clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(parentUIComponent);
    }

    /**
     * Método que valida los valores  del formulario dinámico.
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */

    public void validarComponentes() {
        logger.warning("Inside validarComponentes");
        componentes.clear();
        errores.clear();
        for (int x = 1; x < indice; x++) {
            obtenerValoresComponentes(x);
        }

    }

    /**
     * Método que obtiene los valores de cada componente de los formularios dinámicos.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public void obtenerValoresComponentes(int index) {
        logger.warning("Inside obtenerValoresComponentes");
        ComponenteBean componente = new ComponenteBean();

        mensajeErrores = new StringBuilder();
        errores = new ArrayList<String>();
        //Nombre
        if (getValorTxtNombre(obtenerComponente(ID_PANEL_COMPONENTES), index).trim().isEmpty()) {
            mensajeErrores.append("<p>El campo Nombre es requerido.</p>");
            errores.add("El campo Nombre es requerido.");
        } else {
            componente.setNombre(getValorTxtNombre(obtenerComponente(ID_PANEL_COMPONENTES), index));
        }
        //Descripción
        if (getValorTxtDescripcion(obtenerComponente(ID_PANEL_COMPONENTES), index).trim().isEmpty()) {
            mensajeErrores.append("<p>El campo Descripción es requerido.</p>");
            errores.add("El campo Descripción es requerido.");
        } else {
            componente.setDescripcion(getValorTxtDescripcion(obtenerComponente(ID_PANEL_COMPONENTES), index));
        }
        //Porcentaje
        if (getValorTxtPorcentaje(obtenerComponente(ID_PANEL_COMPONENTES), index).trim().isEmpty()) {
            mensajeErrores.append("<p>El campo Porcentaje es requerido.</p>");
            errores.add("El campo Porcentaje es requerido.");
        } else {
            String txtPorcentaje = getValorTxtPorcentaje(obtenerComponente(ID_PANEL_COMPONENTES), index);
            componente.setPorcentaje(txtPorcentaje);
            numPorcentaje += Integer.parseInt(txtPorcentaje);
        }
        //Actividad Economica Primaria
        if (getValorTxtActEconimicaPrimaria(obtenerComponente(ID_PANEL_COMPONENTES), index).equals("-")) {
            mensajeErrores.append("<p>El campo Actividad económica primaria es requerido.</p>");
            errores.add("El campo Actividad económica primaria es requerido.");
        } else {
            componente.setActividadEconomicaPrimaria(getValorTxtActEconimicaPrimaria(obtenerComponente(ID_PANEL_COMPONENTES),
                                                                                     index));
        }
        //Iniciativa Estratégica
        if (getValorTxtIniciativaEstrategica(obtenerComponente(ID_PANEL_COMPONENTES), index).equals("-")) {
            mensajeErrores.append("<p>El campo Iniciativa estratégica es requerido.</p>");
            errores.add("El campo Iniciativa estratégica es requerido.");
        } else {
            componente.setIniciativaEstrategica(getValorTxtIniciativaEstrategica(obtenerComponente(ID_PANEL_COMPONENTES),
                                                                                 index));
        }
        //Cantidad de Países Beneficiados
        if (getValorTxtCantidadPaises(obtenerComponente(ID_PANEL_COMPONENTES), index).equals("-")) {
            mensajeErrores.append("<p>El campo Cantidad de Países beneficiados es requerido.</p>");
            errores.add("El campo Cantidad de Países beneficiados es requerido.");
        } else {
            componente.setCantidadPaises(getValorTxtCantidadPaises(obtenerComponente(ID_PANEL_COMPONENTES), index));
        }
        //Actividad Económica
        if (getValorTxtActividadEconomica(obtenerComponente(ID_PANEL_COMPONENTES), index).equals("-")) {
            mensajeErrores.append("<p>No existe una Actividad económica para la combinación seleccionada.</p>");
            errores.add("No existe una Actividad económica para la combinación seleccionada.");
        } else {
            componente.setActividadEconomica(getValorTxtActividadEconomica(obtenerComponente(ID_PANEL_COMPONENTES),
                                                                           index));
        }
        //Área de Focalización
        if (getValorTxtAreaFocalizacion(obtenerComponente(ID_PANEL_COMPONENTES), index).equals("-")) {
            mensajeErrores.append("<p>No existe un Área de focalización para la combinación seleccionada.</p>");
            errores.add("No existe un Área de focalización para la combinación seleccionada.");
        } else {
            componente.setAreaFocalizacion(getValorTxtAreaFocalizacion(obtenerComponente(ID_PANEL_COMPONENTES), index));
        }
        //Área de Focalización
        if (getValorTxtEjeEstrategico(obtenerComponente(ID_PANEL_COMPONENTES), index).equals("-")) {
            mensajeErrores.append("<p>No existe un Eje estratégico para la combinación seleccionada.</p>");
            errores.add("No existe un Eje estratégico para la combinación seleccionada.");
        } else {
            componente.setEjeEstrategico(getValorTxtEjeEstrategico(obtenerComponente(ID_PANEL_COMPONENTES), index));
        }

        if (!(mensajeErrores.length() > 0)) {
            componentes.add(componente);
        }
    }

    /**
     * Método que crea el formulario dinámico.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 20/05/2019
     */
    public void crearComponente(int index) {
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutIzq = new RichPanelGroupLayout();
        richPanelGroupLayoutIzq.setLayout("vertical");
        richPanelGroupLayoutIzq.setId("rpglIzq" + index);
        richPanelGroupLayoutIzq.setStyleClass(CONTENT_STYLE_V1);
        richPanelGroupLayoutIzq.getChildren().add(createNombre(index));
        richPanelGroupLayoutIzq.getChildren().add(createDescripcion(index));
        richPanelGroupLayoutIzq.getChildren().add(createPorcentaje(index));

        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutDer = new RichPanelGroupLayout();
        richPanelGroupLayoutDer.setLayout("vertical");
        richPanelGroupLayoutDer.setId("rpglDer" + index);
        richPanelGroupLayoutDer.setStyleClass(CONTENT_STYLE_V5);
        richPanelGroupLayoutDer.getChildren().add(createActEconPrim(index));
        richPanelGroupLayoutDer.getChildren().add(createIniEstrategica(index));
        richPanelGroupLayoutDer.getChildren().add(createCantidadPaises(index));
        richPanelGroupLayoutDer.getChildren().add(createActEconomica(index));
        richPanelGroupLayoutDer.getChildren().add(createAreaFocalizacion(index));
        richPanelGroupLayoutDer.getChildren().add(createEjeEstrategio(index));


        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutCentral = new RichPanelGroupLayout();
        richPanelGroupLayoutCentral.setLayout("vertical");
        richPanelGroupLayoutCentral.setId("rpglCen" + index);
        RichSpacer spacer = new RichSpacer();
        spacer.setId("rspacer" + index);
        spacer.setWidth("60px;");
        richPanelGroupLayoutCentral.getChildren().add(spacer);
        RichPanelGroupLayout richPanelGroupPadre = new RichPanelGroupLayout();
        richPanelGroupPadre.setId("rpfl" + index);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutIzq);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutCentral);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutDer);
        richPanelGroupPadre.setLayout("horizontal");
        // richPanelFormLayout.setInlineStyle(CONTENT_STYLE_V4);
        //Add LineSeparator
        RichSeparator richSeparator = new RichSeparator();
        richSeparator.setId("rls" + index);

        logger.warning("===============  CREATE FORM ===============");

        if (index == 1) {

            addComponent(obtenerComponente(ID_PANEL_COMPONENTES), richPanelGroupPadre);
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richSeparator);
        } else if (index == 2) {
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(botonEliminar(index));
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richPanelGroupPadre);
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richSeparator);

        } else {
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(botonEliminar(index));
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richPanelGroupPadre);
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richSeparator);
            ocultarBotonEliminar(obtenerComponente(ID_PANEL_COMPONENTES), index - 1, false);
        }
    }


    /**
     * Método que crea el formulario dinámico.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public void crearComponenteNoEditable(int index, ComponenteBean componente) {
        logger.warning("\n= Creacion de componente no editable =");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutIzq = new RichPanelGroupLayout();
        richPanelGroupLayoutIzq.setLayout("vertical");
        richPanelGroupLayoutIzq.setId("rpglIzq" + index);

        richPanelGroupLayoutIzq.getChildren().add(createNombreNoEditable(index, componente.getNombre()));
        richPanelGroupLayoutIzq.getChildren().add(createDescripcionNoEditable(index, componente.getDescripcion()));
        richPanelGroupLayoutIzq.getChildren().add(createPorcentajeNoEditable(index, componente.getPorcentaje()));
        richPanelGroupLayoutIzq.setStyleClass(CONTENT_STYLE_V1);

        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutDer = new RichPanelGroupLayout();
        richPanelGroupLayoutDer.setLayout("vertical");
        richPanelGroupLayoutDer.setId("rpglDer" + index);
        richPanelGroupLayoutDer.getChildren().add(createActEconPrimariaNoEditable(index,
                                                                                  componente.getActividadEconomica()));
        richPanelGroupLayoutDer.getChildren().add(createIniEstrategicaNoEditable(index,
                                                                                 componente.getIniciativaEstrategica()));
        richPanelGroupLayoutDer.getChildren().add(createCantidadPaisesNoEditable(index,
                                                                                 componente.getCantidadPaises()));
        richPanelGroupLayoutDer.getChildren().add(createActEconomicaNoEditable(index,
                                                                               componente.getActividadEconomica()));
        richPanelGroupLayoutDer.getChildren().add(createAreaFocalizacionNoEditable(index,
                                                                                   componente.getAreaFocalizacion()));
        richPanelGroupLayoutDer.getChildren().add(createEjeEstrategicoNoEditable(index,
                                                                                 componente.getEjeEstrategico()));
        richPanelGroupLayoutDer.setStyleClass(CONTENT_STYLE_V5);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutCentral = new RichPanelGroupLayout();
        richPanelGroupLayoutCentral.setLayout("vertical");
        richPanelGroupLayoutCentral.setId("rpglCen" + index);
        RichSpacer spacer = new RichSpacer();
        spacer.setId("rspacer" + index);
        spacer.setWidth("60px;");
        richPanelGroupLayoutCentral.getChildren().add(spacer);

        RichPanelGroupLayout richPanelGroupPadre = new RichPanelGroupLayout();
        richPanelGroupPadre.setId("rpfl" + index);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutIzq);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutCentral);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutDer);
        richPanelGroupPadre.setLayout("horizontal");
        richPanelGroupPadre.setValign("top");

        //Add LineSeparator
        RichSeparator richSeparator = new RichSeparator();
        richSeparator.setId("rls" + index);

        obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richPanelGroupPadre);
        obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richSeparator);
    }

    /**
     * Método que crea el formulario dinámico.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 20/05/2019
     */
    public void crearComponenteEditableInicial(int index, ComponenteBean componente) {
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutIzq = new RichPanelGroupLayout();
        richPanelGroupLayoutIzq.setLayout("vertical");
        richPanelGroupLayoutIzq.setId("rpglIzq" + index);
        richPanelGroupLayoutIzq.setStyleClass(CONTENT_STYLE_V1);
        richPanelGroupLayoutIzq.getChildren().add(createNombreEditable(index, componente.getNombre()));
        richPanelGroupLayoutIzq.getChildren().add(createDescripcionEditable(index, componente.getDescripcion()));
        richPanelGroupLayoutIzq.getChildren().add(createPorcentajeEditable(index, componente.getPorcentaje()));

        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutDer = new RichPanelGroupLayout();
        richPanelGroupLayoutDer.setLayout("vertical");
        richPanelGroupLayoutDer.setId("rpglDer" + index);
        richPanelGroupLayoutDer.setStyleClass(CONTENT_STYLE_V5);
        richPanelGroupLayoutDer.getChildren().add(createActEconPrimEditable(index,
                                                                            componente.getIdActividadEconomicaPrimaria(),
                                                                            componente.getCodigoExterno()));
        richPanelGroupLayoutDer.getChildren().add(createIniEstrategicaEditable(index,
                                                                               componente.getIdIniciativaEstrategica(),
                                                                               componente.getIdActividadEconomica()));
        //filtrar iterador para paises
        filtrarCantidadPaises(new oracle.jbo.domain.Number(Integer.valueOf(componente.getIdIniciativaEstrategica()).intValue()));
        richPanelGroupLayoutDer.getChildren().add(createCantidadPaisesEditable(index, componente.getIdCantidadPaises(),
                                                                               componente.getCodExtPaises()));
        //filtrar Actividad Economica
        filtrarActividadEconomica(new oracle.jbo.domain.Number(Integer.valueOf(componente.getIdActividadEconomica()).intValue()));
        richPanelGroupLayoutDer.getChildren().add(createActEconomicaEditable(index,
                                                                             componente.getIdActividadEconomica(),
                                                                             componente.getCodigoExterno1()));
        //filtrar Area de Focalizacion
        filtrarAreaFocalizacion(new oracle.jbo.domain.Number(Integer.valueOf(componente.getIdIniciativaEstrategica()).intValue()),
                                new oracle.jbo.domain.Number(Integer.valueOf(componente.getIdCantidadPaises()).intValue()));
        richPanelGroupLayoutDer.getChildren().add(createAreaFocalizacionEditable(index,
                                                                                 componente.getIdAreaFocalizacion()));
        //filtrar Area de Focalizacion
        filtrarEjeEstrategico(new oracle.jbo.domain.Number(Integer.valueOf(componente.getIdIniciativaEstrategica()).intValue()),
                              new oracle.jbo.domain.Number(Integer.valueOf(componente.getIdCantidadPaises()).intValue()));
        richPanelGroupLayoutDer.getChildren().add(createEjeEstrategicoEditable(index,
                                                                               componente.getIdEjeEstrategico()));


        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutCentral = new RichPanelGroupLayout();
        richPanelGroupLayoutCentral.setLayout("vertical");
        richPanelGroupLayoutCentral.setId("rpglCen" + index);
        RichSpacer spacer = new RichSpacer();
        spacer.setId("rspacer" + index);
        spacer.setWidth("60px;");
        richPanelGroupLayoutCentral.getChildren().add(spacer);
        RichPanelGroupLayout richPanelGroupPadre = new RichPanelGroupLayout();
        richPanelGroupPadre.setId("rpfl" + index);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutIzq);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutCentral);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutDer);
        richPanelGroupPadre.setLayout("horizontal");
        // richPanelFormLayout.setInlineStyle(CONTENT_STYLE_V4);
        //Add LineSeparator
        RichSeparator richSeparator = new RichSeparator();
        richSeparator.setId("rls" + index);

        logger.warning("===============  CREATE FORM ===============");

        if (index == 1) {

            addComponent(obtenerComponente(ID_PANEL_COMPONENTES), richPanelGroupPadre);
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richSeparator);
        } else if (index == 2) {
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(botonEliminar(index));
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richPanelGroupPadre);
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richSeparator);

        } else {
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(botonEliminar(index));
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richPanelGroupPadre);
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richSeparator);
            ocultarBotonEliminar(obtenerComponente(ID_PANEL_COMPONENTES), index - 1, false);
        }
    }

    /**
     * Método que crea el formulario dinámico.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 20/05/2019
     */
    public void crearComponenteNoMulti2Multi(int index) {
        String valor = null;
        String codigo = null;
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutIzq = new RichPanelGroupLayout();
        richPanelGroupLayoutIzq.setLayout("vertical");
        richPanelGroupLayoutIzq.setId("rpglIzq" + index);
        richPanelGroupLayoutIzq.setStyleClass(CONTENT_STYLE_V1);
        richPanelGroupLayoutIzq.getChildren().add(createNombre(index));
        richPanelGroupLayoutIzq.getChildren().add(createDescripcion(index));
        richPanelGroupLayoutIzq.getChildren().add(createPorcentaje(index));

        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutDer = new RichPanelGroupLayout();
        richPanelGroupLayoutDer.setLayout("vertical");
        richPanelGroupLayoutDer.setId("rpglDer" + index);
        richPanelGroupLayoutDer.setStyleClass(CONTENT_STYLE_V5);
        valor = obtenerValorBindigns(1);
        codigo = obtenerCodigoBindigns(1);
        richPanelGroupLayoutDer.getChildren().add(createActEconPrimEditable(index, valor, codigo));
        valor = obtenerValorBindigns(2);
        codigo = obtenerCodigoBindigns(2);
        richPanelGroupLayoutDer.getChildren().add(createIniEstrategicaEditable(index, valor, codigo));
        valor = obtenerValorBindigns(3);
        codigo = obtenerCodigoBindigns(3);
        richPanelGroupLayoutDer.getChildren().add(createCantidadPaisesEditable(index, valor, codigo));
        valor = obtenerValorBindigns(4);
        codigo = obtenerCodigoBindigns(4);
        richPanelGroupLayoutDer.getChildren().add(createActEconomicaEditable(index, valor, codigo));
        valor = obtenerValorBindigns(5);
        richPanelGroupLayoutDer.getChildren().add(createAreaFocalizacionEditable(index, valor));
        valor = obtenerValorBindigns(6);
        richPanelGroupLayoutDer.getChildren().add(createEjeEstrategicoEditable(index, valor));


        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutCentral = new RichPanelGroupLayout();
        richPanelGroupLayoutCentral.setLayout("vertical");
        richPanelGroupLayoutCentral.setId("rpglCen" + index);
        RichSpacer spacer = new RichSpacer();
        spacer.setId("rspacer" + index);
        spacer.setWidth("60px;");
        richPanelGroupLayoutCentral.getChildren().add(spacer);

        //Add PanelFormLayout
        /*  RichPanelFormLayout richPanelFormLayout = new RichPanelFormLayout();
        richPanelFormLayout.setRows(1);
        richPanelFormLayout.setId("rpfl" + index);
        richPanelFormLayout.getChildren().add(richPanelGroupLayoutIzq);
        richPanelFormLayout.getChildren().add(richPanelGroupLayoutDer);*/
        RichPanelGroupLayout richPanelGroupPadre = new RichPanelGroupLayout();
        richPanelGroupPadre.setId("rpfl" + index);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutIzq);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutCentral);
        richPanelGroupPadre.getChildren().add(richPanelGroupLayoutDer);
        richPanelGroupPadre.setLayout("horizontal");
        // richPanelFormLayout.setInlineStyle(CONTENT_STYLE_V4);
        //Add LineSeparator
        RichSeparator richSeparator = new RichSeparator();
        richSeparator.setId("rls" + index);

        logger.warning("===============  CREATE FORM ===============");

        if (index == 1) {

            addComponent(obtenerComponente(ID_PANEL_COMPONENTES), richPanelGroupPadre);
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richSeparator);
        } else if (index == 2) {
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(botonEliminar(index));
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richPanelGroupPadre);
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richSeparator);

        } else {
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(botonEliminar(index));
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richPanelGroupPadre);
            obtenerComponente(ID_PANEL_COMPONENTES).getChildren().add(richSeparator);
            ocultarBotonEliminar(obtenerComponente(ID_PANEL_COMPONENTES), index - 1, false);
        }
    }

    /**
     * Método que crea el formulario dinámico.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 28/11/2019
     */
    public void componenteMulti2NoMulti() {
        logger.warning("=== componenteMulti2NoMulti");
        //Actividad Economica Primaria
        String cadAEP[] = getValorTxtActEconimicaPrimaria(obtenerComponente(ID_PANEL_COMPONENTES), 1).split("-");
        logger.warning("=== cadAEP[0]:"+cadAEP[0]);
        logger.warning("=== cadAEP[1]:"+cadAEP[1]);
        JSFUtils.setExpressionValue("#{bindings.TiposActividadEconomicaPadreLOV.inputValue}",
                                    Integer.valueOf(cadAEP[0]).intValue());
      //  limpiarIteradores();
        //Iniciativa Estrategica
        //obtenerValorBindigns(2);
       // filtrarIniciativaEstrategica(new oracle.jbo.domain.Number(Integer.valueOf(cadAEP[0]).intValue()));
        obtenerValorBindigns(2);
        iterarTiposIniciativaEstrategicaLOV();
        //actualizarComponente("socIniciativaEstrategica");
       // obtenerValorBindigns(2);
        String cadIE[] = getValorTxtIniciativaEstrategica(obtenerComponente(ID_PANEL_COMPONENTES), 1).split("-");
        logger.warning("=== cadIE[0]:"+cadIE[0]);
        logger.warning("=== cadIE[1]:"+cadIE[1]);
        JSFUtils.setExpressionValue("#{bindings.TiposIniciativaEstrategicaLOV.inputValue}",Integer.valueOf(cadIE[0]).intValue());
        actualizarComponente("socIniciativaEstrategica");
        obtenerValorBindigns(2);
        
        JSFUtils.setExpressionValue("#{bindings.TiposIniciativaEstrategicaLOV.inputValue}",Integer.valueOf(cadIE[1]).intValue());
        actualizarComponente("socIniciativaEstrategica");
        obtenerValorBindigns(2);
   
        //Cantidad de paises
        String cadCP[] = getValorTxtCantidadPaises(obtenerComponente(ID_PANEL_COMPONENTES), 1).split("-");
        logger.warning("=== cadCP[0]:"+cadCP[0]);
        logger.warning("=== cadCP[1]:"+cadCP[1]);
        obtenerValorBindigns(3);
        filtrarCantidadPaises(new oracle.jbo.domain.Number(Integer.valueOf(cadIE[0]).intValue()));
        obtenerValorBindigns(3);
        TiposCantidadPaisesBeneficiadosLOV();
        JSFUtils.setExpressionValue("#{bindings.TiposCantidadPaisesBeneficiadosLOV.inputValue}",Integer.valueOf(cadCP[0]).intValue());
        actualizarComponente("socCantidadPaisesBeneficiados");
        obtenerValorBindigns(3);
        //Actividad Economica
        String cadAE[] = getValorTxtActividadEconomica(obtenerComponente(ID_PANEL_COMPONENTES), 1).split("-");
        logger.warning("=== cadAE[0]:"+cadAE[0]);
        logger.warning("=== cadAE[1]:"+cadAE[1]);
        filtrarActividadEconomica(new oracle.jbo.domain.Number(Integer.valueOf(cadIE[1]).intValue()));
        JSFUtils.setExpressionValue("#{bindings.TiposActividadEconomicaLOV.inputValue}",
                                    Integer.valueOf(cadAE[0]).intValue());

        //Area de Focalizacion
        int valor = Integer.valueOf(getValorTxtAreaFocalizacion(obtenerComponente(ID_PANEL_COMPONENTES), 1)).intValue();
        filtrarAreaFocalizacion(new oracle.jbo.domain.Number(Integer.valueOf(cadIE[0]).intValue()),new oracle.jbo.domain.Number(Integer.valueOf(cadCP[1]).intValue()) );
        JSFUtils.setExpressionValue("#{bindings.TiposAreaFocalizacionLOV.inputValue}", valor);
        //Eje Estrategico
        valor = Integer.valueOf(getValorTxtEjeEstrategico(obtenerComponente(ID_PANEL_COMPONENTES), 1)).intValue();
        filtrarEjeEstrategico(new oracle.jbo.domain.Number(Integer.valueOf(cadIE[0]).intValue()),new oracle.jbo.domain.Number(Integer.valueOf(cadCP[1]).intValue()) );
        JSFUtils.setExpressionValue("#{bindings.TiposEjeEstrategicoLOV.inputValue}", valor);

    }

    /**
     * Método para la creacion dinámica de componente: botón Eliminar.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichButton
     * @version: v1.0
     * @Fecha  : 22/05/2019
     */
    public UIComponent botonEliminar(int index) {
        RichButton richButton = new RichButton();
        richButton.setId("rb" + index);
        richButton.setText("Eliminar");
        richButton.setVisible(true);
        richButton.setImmediate(true);
        richButton.addActionListener(getActionListener("#{viewScope.clasificacionEstrategicaBean.removeComponent}"));
        return richButton;
    }

    /**
     * Método para la creacion dinámica de componente: botón Agregar Componente.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichButton
     * @version: v1.0
     * @Fecha  : 22/05/2019
     */
    public UIComponent botonAgregar() {
        RichButton richButton = new RichButton();
        richButton.setId("rbAgregar");
        richButton.setText("Agregar Clasificación");
        richButton.setVisible(true);
        richButton.setImmediate(true);
        richButton.addActionListener(getActionListener("#{viewScope.clasificacionEstrategicaBean.crearComponente}"));
        return richButton;
    }

    /**
     * Método para la creacion dinámica de componente: Nombre.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public UIComponent createNombre(int index) {
        RichInputText richInputTextNombre = new RichInputText();
        richInputTextNombre.setId(ID_NOMBRE + index);
        richInputTextNombre.setValue("");
        richInputTextNombre.setShowRequired(true);
        richInputTextNombre.setAutoSubmit(true);
        richInputTextNombre.setLabel(getValueFromResourceBundle(BUNDLE, "LBL_NOMBRE"));
        richInputTextNombre.setLabelStyle(LABEL_STYLE);
        //Add PanelGroupLayout Nombre
        RichPanelGroupLayout richPanelGroupLayoutNombre = new RichPanelGroupLayout();
        richPanelGroupLayoutNombre.setLayout("horizontal");
        richPanelGroupLayoutNombre.setId("rpglNom" + index);
        richPanelGroupLayoutNombre.getChildren().add(richInputTextNombre);

        return richPanelGroupLayoutNombre;
    }

    /**
     * Método para la creacion dinámica de componente editable: Nombre.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : Sting valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createNombreEditable(int index, String valor) {
        RichInputText richInputTextNombre = new RichInputText();
        richInputTextNombre.setId(ID_NOMBRE + index);
        richInputTextNombre.setValue(valor);
        richInputTextNombre.setShowRequired(true);
        richInputTextNombre.setAutoSubmit(true);
        richInputTextNombre.setLabel(getValueFromResourceBundle(BUNDLE, "LBL_NOMBRE"));
        richInputTextNombre.setLabelStyle(LABEL_STYLE);
        //Add PanelGroupLayout Nombre
        RichPanelGroupLayout richPanelGroupLayoutNombre = new RichPanelGroupLayout();
        richPanelGroupLayoutNombre.setLayout("horizontal");
        richPanelGroupLayoutNombre.setId("rpglNom" + index);
        richPanelGroupLayoutNombre.getChildren().add(richInputTextNombre);

        return richPanelGroupLayoutNombre;
    }

    /**
     * Método para la creacion dinámica de componente no editable: Nombre.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createNombreNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId(ID_LBL_NOMBRE + index);
        String lblValor = getValueFromResourceBundle(BUNDLE, "LBL_NOMBRE");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId(ID_ROT_NOMBRE + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C1);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout Nombre
        RichPanelGroupLayout richPanelGroupLayoutNombre = new RichPanelGroupLayout();
        richPanelGroupLayoutNombre.setLayout("vertical");
        richPanelGroupLayoutNombre.setId(ID_RPGL_NOMBRE + index);
        richPanelGroupLayoutNombre.getChildren().add(richOutputLabel);
        richPanelGroupLayoutNombre.getChildren().add(richOutputText);

        return richPanelGroupLayoutNombre;
    }


    /**
     * Método que obtiene el valor del componente Nombre creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtNombre(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_NOMBRE + index);
        return ui.getAttributes().get("value").toString();
    }

    /**
     * Método que oculta|muestra el componente Nombre dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public void ocultarNombre(UIComponent parentUIComponent, int index, boolean valor) {
        UIComponent ui = parentUIComponent.findComponent(ID_NOMBRE + index);
        ui.getAttributes().put("visible", valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método para la creacion dinámica de componente: Descripción.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public UIComponent createDescripcion(int index) {

        RichInputText richInputTextDescripcion = new RichInputText();
        richInputTextDescripcion.setId(ID_DESCRIPCION + index);
        richInputTextDescripcion.setLabel(getValueFromResourceBundle(BUNDLE, "LBL_DESCRIPCION"));
        richInputTextDescripcion.setLabelStyle(LABEL_STYLE);
        richInputTextDescripcion.setValue("");
        richInputTextDescripcion.setRows(4);
        richInputTextDescripcion.setShowRequired(true);
        richInputTextDescripcion.setAutoSubmit(true);
        richInputTextDescripcion.setMaximumLength(2500);
        //Add PanelGroupLayout Descripcion
        RichPanelGroupLayout richPanelGroupLayoutDesc = new RichPanelGroupLayout();
        richPanelGroupLayoutDesc.setLayout("horizontal");
        richPanelGroupLayoutDesc.setId("rpglDsc" + index);
        richPanelGroupLayoutDesc.getChildren().add(richInputTextDescripcion);

        return richPanelGroupLayoutDesc;
    }

    /**
     * Método para la creacion dinámica de componente editable: Descripción.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createDescripcionEditable(int index, String valor) {

        RichInputText richInputTextDescripcion = new RichInputText();
        richInputTextDescripcion.setId(ID_DESCRIPCION + index);
        richInputTextDescripcion.setLabel(getValueFromResourceBundle(BUNDLE, "LBL_DESCRIPCION"));
        richInputTextDescripcion.setLabelStyle(LABEL_STYLE);
        richInputTextDescripcion.setValue(valor);
        richInputTextDescripcion.setRows(4);
        richInputTextDescripcion.setShowRequired(true);
        richInputTextDescripcion.setAutoSubmit(true);
        richInputTextDescripcion.setMaximumLength(2500);
        //Add PanelGroupLayout Descripcion
        RichPanelGroupLayout richPanelGroupLayoutDesc = new RichPanelGroupLayout();
        richPanelGroupLayoutDesc.setLayout("horizontal");
        richPanelGroupLayoutDesc.setId("rpglDsc" + index);
        richPanelGroupLayoutDesc.getChildren().add(richInputTextDescripcion);

        return richPanelGroupLayoutDesc;
    }

    /**
     * Método para la creacion dinámica de componente no editable: Descripción.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createDescripcionNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblDsc" + index);
        String lblValor = getValueFromResourceBundle(BUNDLE, "LBL_DESCRIPCION");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId("rotDsc" + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C1);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout Descripcion
        RichPanelGroupLayout richPanelGroupLayoutDesc = new RichPanelGroupLayout();
        richPanelGroupLayoutDesc.setLayout("vertical");
        richPanelGroupLayoutDesc.setId("rpglDsc" + index);
        richPanelGroupLayoutDesc.getChildren().add(richOutputLabel);
        richPanelGroupLayoutDesc.getChildren().add(richOutputText);

        return richPanelGroupLayoutDesc;
    }


    /**
     * Método que obtiene el valor del componente Descripción creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtDescripcion(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_DESCRIPCION + index);
        return ui.getAttributes().get("value").toString();
    }

    /**
     * Método que oculta|muestra el componente Descripción dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index, boolean
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public void ocultarDescripcion(UIComponent parentUIComponent, int index, boolean valor) {
        UIComponent ui = parentUIComponent.findComponent(ID_DESCRIPCION + index);
        ui.getAttributes().put("visible", valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método para la creacion dinámica de componente: Porcentaje.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public UIComponent createPorcentaje(int index) {
        //Add Input text
        RichInputText richInputTextPorcentaje = new RichInputText();
        richInputTextPorcentaje.setId(ID_PORCENTAJE + index);
        richInputTextPorcentaje.setValue("");
        richInputTextPorcentaje.setLabel(getValueFromResourceBundle(BUNDLE, "LBL_PORCENTAJE"));
        richInputTextPorcentaje.setLabelStyle(LABEL_STYLE);
        richInputTextPorcentaje.setShowRequired(true);
        richInputTextPorcentaje.setAutoSubmit(true);
        richInputTextPorcentaje.setColumns(3);
        richInputTextPorcentaje.setMaximumLength(2);
        // richInputTextPorcentaje.setConverter(porcentajeConverter());
        richInputTextPorcentaje.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionEstrategicaBean.vclPorcentaje}"));
        richInputTextPorcentaje.setContentStyle("text-align: right;");
        //Listener
        ClientListenerSet CL = new ClientListenerSet();
        CL.addListener("keyPress", "filterForNumbers");
        richInputTextPorcentaje.setClientListeners(CL);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutPsc = new RichPanelGroupLayout();
        richPanelGroupLayoutPsc.setLayout("horizontal");
        richPanelGroupLayoutPsc.setId("rpglPsc" + index);
        richPanelGroupLayoutPsc.getChildren().add(richInputTextPorcentaje);

        return richPanelGroupLayoutPsc;
    }

    /**
     * Método para la creacion dinámica de componente editable: Porcentaje.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public UIComponent createPorcentajeEditable(int index, String valor) {
        //Add Input text
        RichInputText richInputTextPorcentaje = new RichInputText();
        richInputTextPorcentaje.setId(ID_PORCENTAJE + index);
        richInputTextPorcentaje.setValue(Integer.parseInt(valor));
        richInputTextPorcentaje.setLabel(getValueFromResourceBundle(BUNDLE, "LBL_PORCENTAJE"));
        richInputTextPorcentaje.setLabelStyle(LABEL_STYLE);
        richInputTextPorcentaje.setShowRequired(true);
        richInputTextPorcentaje.setAutoSubmit(true);
        richInputTextPorcentaje.setColumns(3);
        richInputTextPorcentaje.setMaximumLength(2);
        // richInputTextPorcentaje.setConverter(porcentajeConverter());
        richInputTextPorcentaje.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionEstrategicaBean.vclPorcentaje}"));
        richInputTextPorcentaje.setContentStyle("text-align: right;");
        //Listener
        ClientListenerSet CL = new ClientListenerSet();
        CL.addListener("keyPress", "filterForNumbers");
        richInputTextPorcentaje.setClientListeners(CL);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutPsc = new RichPanelGroupLayout();
        richPanelGroupLayoutPsc.setLayout("horizontal");
        richPanelGroupLayoutPsc.setId("rpglPsc" + index);
        richPanelGroupLayoutPsc.getChildren().add(richInputTextPorcentaje);

        return richPanelGroupLayoutPsc;
    }

    /**
     * Método para la creacion dinámica de componente no editable: Porcentaje.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createPorcentajeNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblPsc" + index);
        String lblValor = getValueFromResourceBundle(BUNDLE, "LBL_PORCENTAJE");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId("rotPsc" + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C1);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutPsc = new RichPanelGroupLayout();
        richPanelGroupLayoutPsc.setLayout("vertical");
        richPanelGroupLayoutPsc.setId("rpglPsc" + index);
        richPanelGroupLayoutPsc.getChildren().add(richOutputLabel);
        richPanelGroupLayoutPsc.getChildren().add(richOutputText);

        return richPanelGroupLayoutPsc;
    }


    /**
     * Método que obtiene el valor del componente Porcentaje creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtPorcentaje(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_PORCENTAJE + index);
        return ui.getAttributes().get("value").toString();
    }

    /**
     * Método que oculta|muestra el componente Porcentaje dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public void ocultarPorcentaje(UIComponent parentUIComponent, int index, boolean valor) {
        UIComponent ui = parentUIComponent.findComponent(ID_PORCENTAJE + index);
        ui.getAttributes().put("visible", valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método para la creacion dinámica de componente combo: Actividad Económica Primaria .
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public UIComponent createActEconPrim(int index) {
        RichSelectOneChoice richSelectOneChoiceAEP = new RichSelectOneChoice();
        richSelectOneChoiceAEP.setId(ID_SOC_AEP + index);
        richSelectOneChoiceAEP.setShowRequired(true);
        richSelectOneChoiceAEP.setAutoSubmit(true);
        richSelectOneChoiceAEP.setLabel(getValueFromResourceBundle(BUNDLE, "ACTIVIDAD_ECONOMICA_PRIMARIA"));
        richSelectOneChoiceAEP.setLabelStyle(LABEL_STYLE_V1);
        String ptg[] = { "socIdProducto" };
        richSelectOneChoiceAEP.setPartialTriggers(ptg);
        richSelectOneChoiceAEP.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionEstrategicaBean.vclActividadEconomicaPrimaria}"));
        richSelectOneChoiceAEP.setContentStyle(CONTENT_STYLE_V1);
        //Add items
        UISelectItems selectItemsUno = new UISelectItems();
        selectItemsUno.setId(ID_SI_AEP + index);
        selectItemsUno.setValue(cmbActividadEconomicaPrimaria());
        richSelectOneChoiceAEP.getChildren().add(selectItemsUno);
        richSelectOneChoiceAEP.setValue("0-");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutAEP = new RichPanelGroupLayout();
        richPanelGroupLayoutAEP.setLayout("horizontal");
        richPanelGroupLayoutAEP.setId("rpglAEP" + index);
        richPanelGroupLayoutAEP.getChildren().add(richSelectOneChoiceAEP);

        return richPanelGroupLayoutAEP;
    }

    /**
     * Método para la creacion dinámica de componente combo: Actividad Económica Primaria .
     * @author : S&P Solutions
     * @param  : int index
     * @param  : Strig valor
     * @param  : String codigo
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createActEconPrimEditable(int index, String valor, String codigo) {
        RichSelectOneChoice richSelectOneChoiceAEP = new RichSelectOneChoice();
        richSelectOneChoiceAEP.setId(ID_SOC_AEP + index);
        richSelectOneChoiceAEP.setShowRequired(true);
        richSelectOneChoiceAEP.setAutoSubmit(true);
        richSelectOneChoiceAEP.setLabel(getValueFromResourceBundle(BUNDLE, "ACTIVIDAD_ECONOMICA_PRIMARIA"));
        richSelectOneChoiceAEP.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoiceAEP.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionEstrategicaBean.vclActividadEconomicaPrimaria}"));
        richSelectOneChoiceAEP.setContentStyle(CONTENT_STYLE_V1);
        //Add items
        UISelectItems selectItemsUno = new UISelectItems();
        selectItemsUno.setId(ID_SI_AEP + index);
        selectItemsUno.setValue(cmbActividadEconomicaPrimaria());
        richSelectOneChoiceAEP.getChildren().add(selectItemsUno);
        richSelectOneChoiceAEP.setValue(valor + "-" + codigo);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutAEP = new RichPanelGroupLayout();
        richPanelGroupLayoutAEP.setLayout("horizontal");
        richPanelGroupLayoutAEP.setId("rpglAEP" + index);
        richPanelGroupLayoutAEP.getChildren().add(richSelectOneChoiceAEP);

        return richPanelGroupLayoutAEP;
    }

    /**
     * Método para la creacion dinámica de componente no editable: Actividad Económica Primaria.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createActEconPrimariaNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblAEcPrim" + index);
        String lblValor = getValueFromResourceBundle(BUNDLE, "ACTIVIDAD_ECONOMICA_PRIMARIA");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId("rotAEcPrim" + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutAEc = new RichPanelGroupLayout();
        richPanelGroupLayoutAEc.setLayout("vertical");
        richPanelGroupLayoutAEc.setId("rpglAEcPrim" + index);
        richPanelGroupLayoutAEc.getChildren().add(richOutputLabel);
        richPanelGroupLayoutAEc.getChildren().add(richOutputText);

        return richPanelGroupLayoutAEc;
    }


    /**
     * Método que obtiene el valor del componente Actividad Económica Primaria creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtActEconimicaPrimaria(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_AEP + index);
        return (ui.getAttributes().get("value") != null ?
                (ui.getAttributes().get("value").toString().startsWith("0-") ? "-" :
                 ui.getAttributes().get("value").toString()) : "-");

    }

    /**
     * Método para la creacion dinámica de componente combo: Iniciativa Estratégica.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 22/05/2019
     */
    public UIComponent createIniEstrategica(int index) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoiceInEs = new RichSelectOneChoice();
        richSelectOneChoiceInEs.setId(ID_SOC_IE + index);
        richSelectOneChoiceInEs.setContentStyle("200px");
        richSelectOneChoiceInEs.setAutoSubmit(true);
        richSelectOneChoiceInEs.setShowRequired(true);
        richSelectOneChoiceInEs.setLabel(getValueFromResourceBundle(BUNDLE, "INICIATIVA_ESTRATEGICA"));
        richSelectOneChoiceInEs.setLabelStyle(LABEL_STYLE_V1);
        String ptg[] = { ID_SOC_AEP + index };
        richSelectOneChoiceInEs.setPartialTriggers(ptg);
        richSelectOneChoiceInEs.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionEstrategicaBean.vclIniciativaEstrategica}"));
        richSelectOneChoiceInEs.setContentStyle(CONTENT_STYLE_V1);
        //Add items
        UISelectItems selectItemsInEs = new UISelectItems();
        selectItemsInEs.setId(ID_SI_IE + index);
        selectItemsInEs.setValue(cmbIniciativaEstrategica());
        richSelectOneChoiceInEs.getChildren().add(selectItemsInEs);
        //richSelectOneChoiceInEs.setValue("0-");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutInEs = new RichPanelGroupLayout();
        richPanelGroupLayoutInEs.setLayout("horizontal");
        richPanelGroupLayoutInEs.setId("rpglAEP" + index);
        richPanelGroupLayoutInEs.getChildren().add(richSelectOneChoiceInEs);

        return richPanelGroupLayoutInEs;
    }

    /**
     * Método para la creacion dinámica de componente combo: Iniciativa Estratégica.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 22/05/2019
     */
    public UIComponent createIniEstrategicaEditable(int index, String valor, String codigo) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoiceInEs = new RichSelectOneChoice();
        richSelectOneChoiceInEs.setId(ID_SOC_IE + index);
        richSelectOneChoiceInEs.setContentStyle("200px");
        richSelectOneChoiceInEs.setAutoSubmit(true);
        richSelectOneChoiceInEs.setShowRequired(true);
        richSelectOneChoiceInEs.setLabel(getValueFromResourceBundle(BUNDLE, "INICIATIVA_ESTRATEGICA"));
        richSelectOneChoiceInEs.setLabelStyle(LABEL_STYLE_V1);
        String ptg[] = { ID_SOC_AEP + index };
        richSelectOneChoiceInEs.setPartialTriggers(ptg);
        richSelectOneChoiceInEs.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionEstrategicaBean.vclIniciativaEstrategica}"));
        richSelectOneChoiceInEs.setContentStyle(CONTENT_STYLE_V1);
        //Add items
        UISelectItems selectItemsInEs = new UISelectItems();
        selectItemsInEs.setId(ID_SI_IE + index);
        selectItemsInEs.setValue(cmbIniciativaEstrategica());
        richSelectOneChoiceInEs.getChildren().add(selectItemsInEs);
        richSelectOneChoiceInEs.setValue(valor + "-" + codigo);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutInEs = new RichPanelGroupLayout();
        richPanelGroupLayoutInEs.setLayout("horizontal");
        richPanelGroupLayoutInEs.setId("rpglAEP" + index);
        richPanelGroupLayoutInEs.getChildren().add(richSelectOneChoiceInEs);

        return richPanelGroupLayoutInEs;
    }

    /**
     * Método para la creacion dinámica de componente combo: Iniciativa Estratégica.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createIniEstrategicaNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblIEs" + index);
        String lblValor = getValueFromResourceBundle(BUNDLE, "LBL_INICATIVA_ESTRATEGICA");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId("rotIEs" + index);

        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutInEs = new RichPanelGroupLayout();
        richPanelGroupLayoutInEs.setLayout("vertical");
        richPanelGroupLayoutInEs.setId("rpglAEP" + index);

        richPanelGroupLayoutInEs.getChildren().add(richOutputLabel);
        richPanelGroupLayoutInEs.getChildren().add(richOutputText);

        return richPanelGroupLayoutInEs;
    }

    /**
     * Método que obtiene el valor del componente Iniciativa Estratégica creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtIniciativaEstrategica(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_IE + index);
        return (ui.getAttributes().get("value") != null ?
                (ui.getAttributes().get("value").toString().startsWith("0-") ? "-" :
                 ui.getAttributes().get("value").toString()) : "-");
    }


    /**
     * Método para la creacion dinámica de componente combo: Cantidad de países.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 22/05/2019
     */
    public UIComponent createCantidadPaises(int index) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoicePais = new RichSelectOneChoice();
        richSelectOneChoicePais.setId(ID_SOC_CP + index);
        richSelectOneChoicePais.setAutoSubmit(true);
        richSelectOneChoicePais.setShowRequired(true);
        richSelectOneChoicePais.setLabel(getValueFromResourceBundle(BUNDLE, "CANTIDAD_DE_PAISES_BENEFICIADOS"));
        richSelectOneChoicePais.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoicePais.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionEstrategicaBean.vclCantidadPaises}"));
        String ptg[] = { ID_SOC_IE + index };
        richSelectOneChoicePais.setPartialTriggers(ptg);
        richSelectOneChoicePais.setContentStyle(CONTENT_STYLE_V3);
        //Add items
        UISelectItems selectItemsPais = new UISelectItems();
        selectItemsPais.setId(ID_SI_CP + index);
        selectItemsPais.setValue(cmbCantidadPaises());
        richSelectOneChoicePais.getChildren().add(selectItemsPais);
        //richSelectOneChoicePais.setValue("0-");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutPais = new RichPanelGroupLayout();
        richPanelGroupLayoutPais.setLayout("horizontal");
        richPanelGroupLayoutPais.setId("rpglPais" + index);
        richPanelGroupLayoutPais.getChildren().add(richSelectOneChoicePais);

        return richPanelGroupLayoutPais;
    }

    /**
     * Método para la creacion dinámica de componente editable: Cantidad de países.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 22/05/2019
     */
    public UIComponent createCantidadPaisesEditable(int index, String valor, String codigo) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoicePais = new RichSelectOneChoice();
        richSelectOneChoicePais.setId(ID_SOC_CP + index);
        richSelectOneChoicePais.setAutoSubmit(true);
        richSelectOneChoicePais.setShowRequired(true);
        richSelectOneChoicePais.setLabel(getValueFromResourceBundle(BUNDLE, "CANTIDAD_DE_PAISES_BENEFICIADOS"));
        richSelectOneChoicePais.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoicePais.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionEstrategicaBean.vclCantidadPaises}"));
        String ptg[] = { ID_SOC_IE + index };
        richSelectOneChoicePais.setPartialTriggers(ptg);
        richSelectOneChoicePais.setContentStyle(CONTENT_STYLE_V3);
        //Add items
        UISelectItems selectItemsPais = new UISelectItems();
        selectItemsPais.setId(ID_SI_CP + index);
        selectItemsPais.setValue(cmbCantidadPaises());
        richSelectOneChoicePais.getChildren().add(selectItemsPais);
        richSelectOneChoicePais.setValue(codigo + "-" + valor);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutPais = new RichPanelGroupLayout();
        richPanelGroupLayoutPais.setLayout("horizontal");
        richPanelGroupLayoutPais.setId("rpglPais" + index);
        richPanelGroupLayoutPais.getChildren().add(richSelectOneChoicePais);

        return richPanelGroupLayoutPais;
    }

    /**
     * Método para la creacion dinámica de componente No Editable: Cantidad de países.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 22/05/2019
     */
    public UIComponent createCantidadPaisesNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblCPB" + index);
        String lblValor = getValueFromResourceBundle(BUNDLE, "LBL_PAISES_BENEFICIADOS");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId("rotCPB" + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutPais = new RichPanelGroupLayout();
        richPanelGroupLayoutPais.setLayout("vertical");
        richPanelGroupLayoutPais.setId("rpglPais" + index);
        richPanelGroupLayoutPais.getChildren().add(richOutputLabel);
        richPanelGroupLayoutPais.getChildren().add(richOutputText);

        return richPanelGroupLayoutPais;
    }

    /**
     * Método que obtiene el valor del componente Cantidad de países creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtCantidadPaises(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_CP + index);
        return (ui.getAttributes().get("value") != null ?
                (ui.getAttributes().get("value").toString().startsWith("0-") ? "-" :
                 ui.getAttributes().get("value").toString()) : "-");
    }

    /**
     * Método para la creacion dinámica de componente combo: Actividad Económica.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    public UIComponent createActEconomica(int index) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoiceAEc = new RichSelectOneChoice();
        richSelectOneChoiceAEc.setId(ID_SOC_AE + index);
        richSelectOneChoiceAEc.setReadOnly(true);
        richSelectOneChoiceAEc.setLabel(getValueFromResourceBundle(BUNDLE, "ACTIVIDAD_ECONOMICA"));
        richSelectOneChoiceAEc.setLabelStyle(LABEL_STYLE_V1);
        String ptg[] = { ID_SOC_IE + index + " " + ID_SOC_CP + index };
        richSelectOneChoiceAEc.setPartialTriggers(ptg);
        richSelectOneChoiceAEc.setContentStyle(CONTENT_STYLE_V2);
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_AE + index);
        selectItems.setValue(cmbActividadEconomica());
        richSelectOneChoiceAEc.getChildren().add(selectItems);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutAEc = new RichPanelGroupLayout();
        richPanelGroupLayoutAEc.setLayout("horizontal");
        richPanelGroupLayoutAEc.setId("rpglAEc" + index);
        richPanelGroupLayoutAEc.getChildren().add(richSelectOneChoiceAEc);

        return richPanelGroupLayoutAEc;
    }

    /**
     * Método para la creacion dinámica de componente combo: Actividad Económica.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    public UIComponent createActEconomicaEditable(int index, String valor, String codigo) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoiceAEc = new RichSelectOneChoice();
        richSelectOneChoiceAEc.setId(ID_SOC_AE + index);
        richSelectOneChoiceAEc.setReadOnly(true);
        richSelectOneChoiceAEc.setLabel(getValueFromResourceBundle(BUNDLE, "ACTIVIDAD_ECONOMICA"));
        richSelectOneChoiceAEc.setLabelStyle(LABEL_STYLE_V1);
        String ptg[] = { ID_SOC_IE + index + " " + ID_SOC_CP + index };
        richSelectOneChoiceAEc.setPartialTriggers(ptg);
        richSelectOneChoiceAEc.setContentStyle(CONTENT_STYLE_V2);
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_AE + index);
        selectItems.setValue(cmbActividadEconomica());
        richSelectOneChoiceAEc.getChildren().add(selectItems);
        richSelectOneChoiceAEc.setValue(valor + "-" + codigo);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutAEc = new RichPanelGroupLayout();
        richPanelGroupLayoutAEc.setLayout("horizontal");
        richPanelGroupLayoutAEc.setId("rpglAEc" + index);
        richPanelGroupLayoutAEc.getChildren().add(richSelectOneChoiceAEc);

        return richPanelGroupLayoutAEc;
    }


    /**
     * Método para la creacion dinámica de componente no editable: Actividad Económica.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    public UIComponent createActEconomicaNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblAEc" + index);
        String lblValor = getValueFromResourceBundle(BUNDLE, "LBL_ACTIVIDAD_ECONOMICA");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId("rotAEc" + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutAEc = new RichPanelGroupLayout();
        richPanelGroupLayoutAEc.setLayout("vertical");
        richPanelGroupLayoutAEc.setId("rpglAEc" + index);
        richPanelGroupLayoutAEc.getChildren().add(richOutputLabel);
        richPanelGroupLayoutAEc.getChildren().add(richOutputText);

        return richPanelGroupLayoutAEc;
    }

    /**
     * Método que obtiene el valor del componente Actividad Económica creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtActividadEconomica(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_AE + index);
        return ui.getAttributes().get("value") != null ? ui.getAttributes().get("value").toString() : "-";
    }


    /**
     * Método para la creacion dinámica de componente combo: Área de focalización.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    public UIComponent createAreaFocalizacion(int index) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoiceAF = new RichSelectOneChoice();
        richSelectOneChoiceAF.setId(ID_SOC_AF + index);
        richSelectOneChoiceAF.setReadOnly(true);
        richSelectOneChoiceAF.setLabel(getValueFromResourceBundle(BUNDLE, "AREA_DE_FOCALIZACION"));
        richSelectOneChoiceAF.setLabelStyle(LABEL_STYLE_V1);
        String ptg[] = { ID_SOC_IE + index + " " + ID_SOC_CP + index };
        richSelectOneChoiceAF.setPartialTriggers(ptg);
        richSelectOneChoiceAF.setContentStyle(CONTENT_STYLE_V2);
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_AF + index);
        selectItems.setValue(cmbAreaFocalizacion());
        richSelectOneChoiceAF.getChildren().add(selectItems);

        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutAF = new RichPanelGroupLayout();
        richPanelGroupLayoutAF.setLayout("horizontal");
        richPanelGroupLayoutAF.setId("rpglAF" + index);

        richPanelGroupLayoutAF.getChildren().add(richSelectOneChoiceAF);

        return richPanelGroupLayoutAF;
    }

    /**
     * Método para la creacion dinámica de componente combo: Área de focalización.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createAreaFocalizacionEditable(int index, String valor) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoiceAF = new RichSelectOneChoice();
        richSelectOneChoiceAF.setId(ID_SOC_AF + index);
        richSelectOneChoiceAF.setReadOnly(true);
        richSelectOneChoiceAF.setLabel(getValueFromResourceBundle(BUNDLE, "AREA_DE_FOCALIZACION"));
        richSelectOneChoiceAF.setLabelStyle(LABEL_STYLE_V1);
        String ptg[] = { ID_SOC_IE + index + " " + ID_SOC_CP + index };
        richSelectOneChoiceAF.setPartialTriggers(ptg);
        richSelectOneChoiceAF.setContentStyle(CONTENT_STYLE_V2);
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_AF + index);
        selectItems.setValue(cmbAreaFocalizacion());
        richSelectOneChoiceAF.getChildren().add(selectItems);
        richSelectOneChoiceAF.setValue(Integer.parseInt(valor));
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutAF = new RichPanelGroupLayout();
        richPanelGroupLayoutAF.setLayout("horizontal");
        richPanelGroupLayoutAF.setId("rpglAF" + index);

        richPanelGroupLayoutAF.getChildren().add(richSelectOneChoiceAF);

        return richPanelGroupLayoutAF;
    }

    /**
     * Método para la creacion dinámica de componente no editable: Área de focalización.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createAreaFocalizacionNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblAF" + index);
        String lblValor = getValueFromResourceBundle(BUNDLE, "LBL_AREA_FOCALIZACION");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId("rotAF" + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutAF = new RichPanelGroupLayout();
        richPanelGroupLayoutAF.setLayout("vertical");
        richPanelGroupLayoutAF.setId("rpglAF" + index);

        richPanelGroupLayoutAF.getChildren().add(richOutputLabel);
        richPanelGroupLayoutAF.getChildren().add(richOutputText);
        return richPanelGroupLayoutAF;
    }

    /**
     * Método que obtiene el valor del componente Área de focalización creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtAreaFocalizacion(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_AF + index);
        return ui.getAttributes().get("value") != null ? ui.getAttributes().get("value").toString() : "-";
    }

    /**
     * Método para la creacion dinámica de componente combo: Eje estratégico.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    public UIComponent createEjeEstrategio(int index) {
        //Label
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoice = new RichSelectOneChoice();
        richSelectOneChoice.setId(ID_SOC_EE + index);
        richSelectOneChoice.setReadOnly(true);
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "EJE_ESTRATEGICO"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        String ptg[] = { ID_SOC_IE + index + " " + ID_SOC_CP + index };
        richSelectOneChoice.setPartialTriggers(ptg);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V2);
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_EE + index);
        selectItems.setValue(cmbEjeEstrategico());
        richSelectOneChoice.getChildren().add(selectItems);

        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout("horizontal");
        richPanelGroupLayout.setId("rpglEEs" + index);

        richPanelGroupLayout.getChildren().add(richSelectOneChoice);

        return richPanelGroupLayout;
    }


    /**
     * Método para la creacion dinámica de componente combo: Eje estratégico.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createEjeEstrategicoEditable(int index, String valor) {
        //Label
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoice = new RichSelectOneChoice();
        richSelectOneChoice.setId(ID_SOC_EE + index);
        richSelectOneChoice.setReadOnly(true);
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "EJE_ESTRATEGICO"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        String ptg[] = { ID_SOC_IE + index + " " + ID_SOC_CP + index };
        richSelectOneChoice.setPartialTriggers(ptg);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V2);
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_EE + index);
        selectItems.setValue(cmbEjeEstrategico());
        richSelectOneChoice.getChildren().add(selectItems);
        richSelectOneChoice.setValue(Integer.parseInt(valor));
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout("horizontal");
        richPanelGroupLayout.setId("rpglEEs" + index);

        richPanelGroupLayout.getChildren().add(richSelectOneChoice);

        return richPanelGroupLayout;
    }

    /**
     * Método para la creacion dinámica de componente no editable: Eje estratégico.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    public UIComponent createEjeEstrategicoNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblEEs" + index);
        String lblValor = getValueFromResourceBundle(BUNDLE, "LBL_EJE_ESTRATEGICO");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId("rotEEs" + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout("vertical");
        richPanelGroupLayout.setId("rpglEEs" + index);

        richPanelGroupLayout.getChildren().add(richOutputLabel);
        richPanelGroupLayout.getChildren().add(richOutputText);

        return richPanelGroupLayout;
    }

    /**
     * Método que obtiene el valor del componente Eje estratégico creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtEjeEstrategico(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_EE + index);
        return ui.getAttributes().get("value") != null ? ui.getAttributes().get("value").toString() : "-";
    }


    /**
     * Método que oculta o muestra el componente dado el id.
     * @author : S&P Solutions
     * @param  : String id, boolean valor
     * @param  : void
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public void verComponente(String id, boolean valor) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        UIComponent ui = findComponent(facesCtx.getViewRoot(), id);
        ui.getAttributes().put("visible", valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método que habilita o deshabilita componente dado el id.
     * @author : S&P Solutions
     * @param  : String id, boolean valor
     * @param  : void
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public void habilitarComponente(String id, boolean valor) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        UIComponent ui = findComponent(facesCtx.getViewRoot(), id);
        ui.getAttributes().put("disabled", valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método que refresca un componente dado el id.
     * @author : S&P Solutions
     * @param  : String
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public void actualizarComponente(String id) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        UIComponent ui = findComponent(facesCtx.getViewRoot(), id);
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

    /**
     * Método auxiliar que busca y retorna un componente dado su id y componente padre.
     * @author : S&P Solutions
     * @param  : UIComponent base, String id
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 29/06/2019
     */
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
     * Método que oculta o muestra los campos Nombre, Descripción y porcentaje.
     * @author : S&P Solutions
     * @param  : boolean
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public void ocultarCamposNuevos(boolean valor) {

        ocultarNombre(obtenerComponente(ID_PANEL_COMPONENTES), 1, valor);
        ocultarDescripcion(obtenerComponente(ID_PANEL_COMPONENTES), 1, valor);
        ocultarPorcentaje(obtenerComponente(ID_PANEL_COMPONENTES), 1, valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANEL_COMPONENTES));
    }

    /**
     * Método de utileria para la generación dinámica de un ActionListener.
     * @author : S&P Solutions
     * @param  : String el
     * @return : MethodExpressionActionListener
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    private ActionListener getActionListener(String el) {
        MethodExpression methodExp = getMethodExpressionForAction(el);
        return new MethodExpressionActionListener(methodExp);
    }

    /**
     * Método de utileria para la generación dinámica de un ValueChangeListener.
     * @author : S&P Solutions
     * @param  : String el
     * @return : MethodExpressionActionListener
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    private ValueChangeListener getValueChangeListener(String el) {
        MethodExpression methodExp = getMethodExpressionForAction(el);
        return new MethodExpressionValueChangeListener(methodExp);
    }


    /**
     * Método auxiliar para la generación de ValueChangeListener empleado en la generación dinámica de Listeners.
     * @author : S&P Solutions
     * @param  : String el
     * @return : ValueChangeListener
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    private ValueChangeListener resolveValueChangeListener(String validatorName) {
        //ValueChangeListener method takes 1 argument of following type , we have to define that
        Class[] argtypes = new Class[1];
        argtypes[0] = ValueChangeEvent.class;
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        Application app = facesCtx.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesCtx.getELContext();
        MethodExpression methodExp = elFactory.createMethodExpression(elContext, validatorName, null, argtypes);
        return new MethodExpressionValueChangeListener(methodExp);
    }


    /**
     * Método auxiliar para la generación de expresiones empleado en la generación dinámica de Listeners.
     * @author : S&P Solutions
     * @param  : String el
     * @return : MethodExpression
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    private MethodExpression getMethodExpressionForAction(String actionName) {
        Class[] argtypes = new Class[1];
        argtypes[0] = ActionEvent.class;

        FacesContext facesCtx = FacesContext.getCurrentInstance();
        Application app = facesCtx.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesCtx.getELContext();
        return elFactory.createMethodExpression(elContext, actionName, null, argtypes);
    }


    /**
     * Método que agrega un componente definido como hijo en un componente definido como padre.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, UIComponent childUIComponent
     * @version: v1.0
     * @Fecha  : 20/05/2019
     */
    public void addComponent(UIComponent parentUIComponent, UIComponent childUIComponent) {
        parentUIComponent.getChildren().add(childUIComponent);
        AdfFacesContext.getCurrentInstance().addPartialTarget(parentUIComponent);
    }


    /**
     * Método que elimina un componente definido como hijo en un componente definido como padre.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, UIComponent childUIComponent
     * @version: v1.0
     * @Fecha  : 20/05/2019
     */
    public void removeComponent(UIComponent parentUIComponent, UIComponent childUIComponent) {
        parentUIComponent.getChildren().remove(childUIComponent);
        AdfFacesContext.getCurrentInstance().addPartialTarget(parentUIComponent);
    }


    /**
     * Método que elimina todos los componentes hijos de un componente padre.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @version: v1.0
     * @Fecha  : 20/05/2019
     */
    public void removeComponents(UIComponent parentUIComponent, int index) {
        if (index > 1) {
            //Remover panelForm
            parentUIComponent.getChildren().remove(getPanelFormComponent(parentUIComponent, index));
            //Remover separator
            parentUIComponent.getChildren().remove(getSeparatorComponent(parentUIComponent, index));
            //Remover boton eliminar
            parentUIComponent.getChildren().remove(getBtnRemoveComponent(parentUIComponent, index));
            if (index > 2) {
                //Hacer visible botón eliminar de componente previo
                ocultarBotonEliminar(obtenerComponente(ID_PANEL_COMPONENTES), index - 1, true);
            }
        } else {
            //  Solo ocultar/limpiar
            //Remover panelForm
            //   parentUIComponent.getChildren().remove(o);
            //Remover separator
            //  parentUIComponent.getChildren().remove(o);
            ocultarCamposNuevos(true);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(parentUIComponent);
    }

    /**
     * Método que obtiene el componente (PanelFormLayOut) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent getPanelFormComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent("rpfl" + index);
        System.out.println(ui.getId());
        return ui;
    }

    /**
     * Método que obtiene el componente (Separator) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent getSeparatorComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent("rls" + index);
        System.out.println(ui.getId());
        return ui;
    }

    /**
     * Método que obtiene el componente (botón eliminar) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public UIComponent getBtnRemoveComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent("rb" + index);
        System.out.println(ui.getId());
        return ui;
    }

    /**
     * Método que obtiene el componente (Actividad Economica Primaria) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent getSocActEcPrComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_AEP + index);
        System.out.println(ui.getId());
        return ui;
    }

    /**
     * Método que obtiene el componente (Iniciativa Estratégica) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent getSocIniEstrategicaComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_IE + index);
        System.out.println(ui.getId());
        return ui;
    }

    /**
     * Método que obtiene el componente (Cantidad de Paises) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent getSocPaisesComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_CP + index);
        System.out.println(ui.getId());
        return ui;
    }


    /**
     * Método que obtiene el componente (Actividad Economica) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent getSocActEconomicaComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_AE + index);
        System.out.println(ui.getId());
        return ui;
    }

    /**
     * Método que obtiene el componente (Área de focalización) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent getSocAreaFocalizacionComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_AF + index);
        System.out.println(ui.getId());
        return ui;
    }

    /**
     * Método que obtiene el componente (Eje Estratégico) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent getSocEjeEstrategicoComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_EE + index);
        System.out.println(ui.getId());
        return ui;
    }

    /**
     * Método que oculta | muestra el componente botón eliminar dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index, boolean valor
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public void ocultarBotonEliminar(UIComponent parentUIComponent, int index, boolean valor) {
        UIComponent ui = parentUIComponent.findComponent("rb" + index);
        ui.getAttributes().put("visible", valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }


    /**
     * Método auxiliar para la acción del botón eliminar de cada componente generado dinámicamente.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    public void removeComponent(ActionEvent actionEvent) {
        String id = actionEvent.getComponent().getId();
        id = id.replaceAll("rb", "");
        int index = Integer.parseInt(id);
        removeComponents(obtenerComponente(ID_PANEL_COMPONENTES), index);
        indice--;
    }


    /**
     * Método auxiliar para obtener el componente combo de Actividad Economica Primaria creado dinámicamente.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent obtenerUicActividadEcoPrimaria(int index) {
        UIComponent ui = getSocActEcPrComponent(obtenerComponente(ID_PANEL_COMPONENTES), index);
        return ui;
    }


    /**
     * Método auxiliar para obtener el componente combo de Iniciativa Estratégica creado dinámicamente.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent obtenerUicIniciativaEstrategica(int index) {
        UIComponent ui = getSocIniEstrategicaComponent(obtenerComponente(ID_PANEL_COMPONENTES), index);
        return ui;
    }


    /**
     * Método auxiliar para obtener el componente combo de Cantidad de Países  creado dinámicamente.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent obtenerUicCantidadPaises(int index) {
        UIComponent ui = getSocPaisesComponent(obtenerComponente(ID_PANEL_COMPONENTES), index);

        return ui;
    }

    /**
     * Método auxiliar para obtener el componente combo Actividad Económica  creado dinámicamente.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent obtenerUicActiviadaEconomica(int index) {
        UIComponent ui = getSocActEconomicaComponent(obtenerComponente(ID_PANEL_COMPONENTES), index);

        return ui;
    }

    /**
     * Método auxiliar para obtener el componente combo Área de Focalización  creado dinámicamente.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent obtenerUicAreaFocalizacion(int index) {
        logger.warning("=== obtenerUicAreaFocalizacion con indice:" + index);
        UIComponent ui = getSocAreaFocalizacionComponent(obtenerComponente(ID_PANEL_COMPONENTES), index);

        return ui;
    }

    /**
     * Método auxiliar para obtener el componente combo Eje Estratégico  creado dinámicamente.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent obtenerUicEjeEstrategico(int index) {
        UIComponent ui = getSocEjeEstrategicoComponent(obtenerComponente(ID_PANEL_COMPONENTES), index);

        return ui;
    }

    /**
     * Método auxiliar para obtener el componente converter.
     * @author : S&P Solutions
     * @param  :
     * @return : NumberConverter
     * @version: v1.0
     * @Fecha  : 05/06/2019
     */

    public NumberConverter porcentajeConverter() {
        // DateTimeConverter dateTimeConverter= (DateTimeConverter) application.createConverter("javax.faces.DateTime");
        Application application = FacesContext.getCurrentInstance().getApplication();
        NumberConverter numberConverter = (NumberConverter) application.createConverter("javax.faces.Number");
        numberConverter.setType("percent");

        return numberConverter;
    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente Actividad Económica Primaria creado dinámicamente.
     * @author : S&P Solutions
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 22/06/2019
     */
    private List<SelectItem> cmbActividadEconomicaPrimaria() {
        logger.warning("===== Inside cmbActividadEconomicaPrimaria =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TiposActividadEconomicaPadreLOVIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        logger.warning("= Inside cmbActividadEconomicaPrimaria -rowsCat.length: " + rowsCat.length);
        for (Row row : rowsCat) {

            logger.warning("= TiposActividadEconomicaPadreLOVIterator -id: " +
                           Integer.parseInt(row.getAttribute("Id").toString()));
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String codExt = row.getAttribute("CodExterno") != null ? row.getAttribute("CodExterno").toString() : "";
            logger.warning("= TiposActividadEconomicaPadreLOVIterator -codExt: " + codExt);
            String descripcion = row.getAttribute("Descripcion").toString();
            String idCod = id + "-" + codExt;
            combo.add(new SelectItem(idCod, descripcion));
        }
        return combo;
    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente Iniciativa Estratégica creado dinámicamente.
     * @author : S&P Solutions
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 22/06/2019
     */

    private List<SelectItem> cmbIniciativaEstrategica() {
        logger.warning("===== Inside cmbIniciativaEstrategica =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TiposIniciativaEstrategicaLOVIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idActividad = row.getAttribute("IdActividad").toString();
            String idCod = id + "-" + idActividad;
            logger.warning("===== cmbIniciativaEstrategica | ID:" + idCod + " | Descripcion:" + descripcion);
            combo.add(new SelectItem(idCod, descripcion));
        }
        return combo;
    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente Cantidad de Países creado dinámicamente.
     * @author : S&P Solutions
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 22/06/2019
     */
    private List<SelectItem> cmbCantidadPaises() {
        logger.warning("===== Inside cmbCantidadPaises =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TiposCantidadPaisesBeneficiadosLOVIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idRango = row.getAttribute("IdRangoPaises").toString();
            String idCod = id + "-" + idRango;
            logger.warning("===== cmbCantidadPaises | ID:" + idCod + " | Descripcion:" + descripcion);
            combo.add(new SelectItem(idCod, descripcion));
        }
        return combo;
    }
    
    public void iterarTiposIniciativaEstrategicaLOV(){
        logger.warning("===== iterarTiposIniciativaEstrategicaLOV =====");
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TiposIniciativaEstrategicaLOVIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idActividad = row.getAttribute("IdActividad").toString();
            String idCod = id + "-" + idActividad;
            logger.warning("===== iterarTiposIniciativaEstrategicaLOV | ID:" + idCod + " | Descripcion:" + descripcion);
        }
    }
    
    public void TiposCantidadPaisesBeneficiadosLOV(){
        logger.warning("===== TiposCantidadPaisesBeneficiadosLOV =====");
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TiposCantidadPaisesBeneficiadosLOVIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idRango = row.getAttribute("IdRangoPaises").toString();
            String idCod = id + "-" + idRango;
            logger.warning("===== TiposCantidadPaisesBeneficiadosLOV | ID:" + idCod + " | Descripcion:" + descripcion);
        }
    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente Actividad Económica creado dinámicamente.
     * @author : S&P Solutions
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 22/06/2019
     */
    private List<SelectItem> cmbActividadEconomica() {
        logger.warning("===== Inside cmbActividadEconomica =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TiposActividadEconomicaLOVIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String codExt = row.getAttribute("CodExterno") != null ? row.getAttribute("CodExterno").toString() : "";
            String idCod = id + "-" + codExt;
            logger.warning("===== cmbCantidadPaises | ID:" + idCod + " | Descripcion:" + descripcion);
            combo.add(new SelectItem(idCod, descripcion));
        }
        return combo;
    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente Área de Focalización creado dinámicamente.
     * @author : S&P Solutions
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 22/06/2019
     */
    private List<SelectItem> cmbAreaFocalizacion() {
        logger.warning("===== Inside cmbAreaFocalizacion =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TiposAreaFocalizacionLOVIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            logger.warning("===== cmbAreaFocalizacion | ID:" + id + " | Descripcion:" + descripcion);
            combo.add(new SelectItem(id, descripcion));
        }
        return combo;

    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente Eje Estratégico  creado dinámicamente.
     * @author : S&P Solutions
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 22/06/2019
     */
    private List<SelectItem> cmbEjeEstrategico() {
        logger.warning("===== Inside cmbEjeEstrategico =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TiposEjeEstrategicoLOVIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            logger.warning("===== cmbEjeEstrategico | ID:" + id + " | Descripcion:" + descripcion);
            combo.add(new SelectItem(id, descripcion));
        }
        return combo;
    }

    /**
     * Método auxiliar para leer un valor del bundle.
     * @author :
     * @param  : String
     * @param  : String
     * @return : String
     * @version:
     * @Fecha  :
     */
    private String getValueFromResourceBundle(String resourceBundleName, String key) {
        if (resourceBundleName != null) {
            ResourceBundle resourceBundle = BundleFactory.getBundle(resourceBundleName);
            if (resourceBundle != null && key != null) {
                String value = resourceBundle.getString(key);
                return value;
            }
        }
        return null;
    }

    /**
     * Method for taking a reference to a JSF binding expression and returning
     * the matching object (or creating it).
     * @param expression EL expression
     * @return Managed object
     */
    public static Object resolveExpression(String expression) {
        FacesContext facesContext = getFacesContext();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
        return valueExp.getValue(elContext);
    }

    /**
     * Get FacesContext.
     * @return FacesContext
     */
    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Method for setting a new object into a JSF managed bean
     * Note: will fail silently if the supplied object does
     * not match the type of the managed bean.
     * @param expression EL expression
     * @param newValue new value to set
     */
    public static void setExpressionValue(String expression, Object newValue) {
        FacesContext facesContext = getFacesContext();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);

        //Check that the input newValue can be cast to the property type
        //expected by the managed bean.
        //If the managed Bean expects a primitive we rely on Auto-Unboxing
        //I could do a more comprehensive check and conversion from the object
        //to the equivilent primitive but life is too short
        Class bindClass = valueExp.getType(elContext);
        if (bindClass.isPrimitive() || bindClass.isInstance(newValue)) {
            valueExp.setValue(elContext, newValue);
        }
    }

    /**
     * Método auxiliar que actualiza los valores de todos los componentes creadoos dinámicamente.
     * @author : S&P Solutions
     * @param  :
     * @return :
     * @version: v1.0
     * @Fecha  : 05/07/2019
     */
    public void actualizarValoresComponentes() {
        //Al cambiar producto hacer reset de todos los componentes
        for (int x = 1; x < indice; x++) {
            actualizarCmbActivdadEconomicaPriaria(x);
            actualizarCmbIniciativaEstrategica(x);
            actualizarCmbCantidadPaises(x);
            actualizarCmbActividadEconomica(x);
            actualizarCmbAreaFocalizacion(x);
            actualizarCmbEjeEstrategico(x);
        }
    }


    /**
     * Método auxiliar para actualizar valores Actividad Economica Primaria.
     * @author : S&P Solutions
     * @param  : int
     * @return : void
     * @version: v1.0
     * @Fecha  : 05/07/2019
     */
    public void actualizarCmbActivdadEconomicaPriaria(int indice) {
        List<SelectItem> list = cmbActividadEconomicaPrimaria();
        UIComponent ui = obtenerUicActividadEcoPrimaria(indice);
        String id = getIdSelecItem(1, indice);
        actualizarComponenteSelectItem(id, list);

        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método auxiliar para actualizar valores Iniciativa Estrategica.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 05/07/2019
     */
    public void actualizarCmbIniciativaEstrategica(int indice) {
        logger.warning("=== actualizarCmbIniciativaEstrategica con indice:" + indice);
        List<SelectItem> list = cmbIniciativaEstrategica();
        String id = getIdSelecItem(2, indice);
        actualizarComponenteSelectItem(id, list);
        //Actualizar seleccion
        RichSelectOneChoice rsoc = (RichSelectOneChoice) obtenerUicIniciativaEstrategica(indice);
        rsoc.setValue("0-0");
        AdfFacesContext.getCurrentInstance().addPartialTarget(rsoc);
    }

    /**
     * Método auxiliar para actualizar valores Cantidad de Paises.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 05/07/2019
     */
    public void actualizarCmbCantidadPaises(int indice) {
        logger.warning("=== actualizarCmbCantidadPaises con indice:" + indice);
        List<SelectItem> list = cmbCantidadPaises();
        String id = getIdSelecItem(3, indice);
        actualizarComponenteSelectItem(id, list);
        //Actualizar seleccion
        RichSelectOneChoice rsoc = (RichSelectOneChoice) obtenerUicCantidadPaises(indice);
        rsoc.setValue("0-0");
        AdfFacesContext.getCurrentInstance().addPartialTarget(rsoc);
    }


    /**
     * Método auxiliar para actualizar valores Actividad Economica.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 05/07/2019
     */
    public void actualizarCmbActividadEconomica(int indice) {
        logger.warning("=== actualizarCmbActividadEconomica con indice:" + indice);
        List<SelectItem> list = cmbActividadEconomica();
        UIComponent ui = obtenerUicActiviadaEconomica(indice);
        String id = getIdSelecItem(4, indice);
        actualizarComponenteSelectItem(id, list);

        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método auxiliar para actualizar valores Area de Focalizacion.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 05/07/2019
     */
    public void actualizarCmbAreaFocalizacion(int indice) {
        logger.warning("=== actualizarCmbAreaFocalizacion con indice:" + indice);
        List<SelectItem> list = cmbAreaFocalizacion();
        UIComponent ui = obtenerUicAreaFocalizacion(indice);
        String id = getIdSelecItem(5, indice);
        actualizarComponenteSelectItem(id, list);

        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método auxiliar para actualizar valores Eje Estrategico.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 05/07/2019
     */
    public void actualizarCmbEjeEstrategico(int indice) {
        logger.warning("=== actualizarCmbEjeEstrategico con indice:" + indice);
        List<SelectItem> list = cmbEjeEstrategico();
        UIComponent ui = obtenerUicEjeEstrategico(indice);
        String id = getIdSelecItem(6, indice);
        actualizarComponenteSelectItem(id, list);

        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método que actualiza el valor de un componente SelectItem dado el id y valores nuevos.
     * @author : S&P Solutions
     * @param  : String
     * @param  : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 06/06/2019
     */
    public void actualizarComponenteSelectItem(String id, List<SelectItem> selectItems) {
        logger.warning("=== actualizarComponenteSelectItem con id:" + id);
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        UIComponent ui = findComponent(facesCtx.getViewRoot(), id);
        ui.getAttributes().put("value", selectItems);

        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método auxiliar para generar el id del componente SelecItem dado el indice y clave de componente.
     * @author : S&P Solutions
     * @param  : int
     * @param  : int
     * @return : String
     * @version: v1.0
     * @Fecha  : 06/06/2019
     */
    public String getIdSelecItem(int clave, int index) {

        String id = "";
        switch (clave) {
        case 1: //Actividad Economica Primaria
            id = ID_SI_AEP + index;
            break;
        case 2: //Iniciativa Estrategica
            id = ID_SI_IE + index;
            break;
        case 3: //Cantidad de paises
            id = ID_SI_CP + index;
            break;
        case 4: //Actividad Economica
            id = ID_SI_AE + index;
            break;
        case 5: //Area de Focalizacion
            id = ID_SI_AF + index;
            break;
        case 6: //Eje Estrategico
            id = ID_SI_EE + index;
            break;

        }

        logger.warning("=== getIdSelecItem | id:" + id);
        return id;
    }

    /**
     * Método auxiliar para establcer valor al componente Actividad Economica.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 05/07/2019
     */
    public void actualizarValorCmbActividadEconomica(int indice) {
        logger.warning("=== actualizarValorCmbActividadEconomica con indice:" + indice);
        UIComponent ui = obtenerUicActiviadaEconomica(indice);
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TiposActividadEconomicaLOVIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row row = rsiRowsCat.getRowAtRangeIndex(0);

        int id = Integer.parseInt(row.getAttribute("Id").toString());
        String codExt = row.getAttribute("CodExterno").toString();
        String idCod = id + "-" + codExt;

        ui.getAttributes().put("value", idCod);

        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }


    /**
     * Método auxiliar para establcer valor al componente Are de Focalizacion.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 05/07/2019
     */
    public void actualizarValorCmbAreaFocalizacion(int indice) {
        UIComponent ui = obtenerUicAreaFocalizacion(indice);
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TiposAreaFocalizacionLOVIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row row = rsiRowsCat.getRowAtRangeIndex(0);

        int id = Integer.parseInt(row.getAttribute("Id").toString());

        ui.getAttributes().put("value", id);

        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }


    /**
     * Método auxiliar para establecer valor al componente Eje estrategico.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 05/07/2019
     */
    public void actualizarValorCmbEjeEstrategico(int indice) {
        UIComponent ui = obtenerUicEjeEstrategico(indice);
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TiposEjeEstrategicoLOVIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row row = rsiRowsCat.getRowAtRangeIndex(0);

        int id = Integer.parseInt(row.getAttribute("Id").toString());
        ui.getAttributes().put("value", id);

        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método auxiliar para el manejo de la visibilidad del popup de mensaje de errores al agregar un componente.
     * @author : S&P Solutions
     * @param  : boolean
     * @return :
     * @version: v1.0
     * @Fecha  : 05/07/2019
     */
    public void mostrarPopupErrorAgregarClasificacion(boolean valor) {
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        RichPopup ui = (RichPopup) findComponent(facesCtx.getViewRoot(), "ppMsgErrAgregarCEEd");

        if (valor) {
            ui.show(ph);
        } else {
            ui.hide();
        }
    }

    /**
     * Método auxiliar para la obtencion del indice de un componente.
     * @author : S&P Solutions
     * @param  : String
     * @return : int
     * @version: v1.0
     * @Fecha  : 12/08/2019
     */
    public int obtenerIndice(String id) {
        String aux = "";
        if (id.startsWith(ID_SOC_AEP)) {
            aux = id.replaceAll(ID_SOC_AEP, "");
        } else if (id.startsWith(ID_SOC_IE)) {
            aux = id.replaceAll(ID_SOC_IE, "");
        } else if (id.startsWith(ID_SOC_CP)) {
            aux = id.replaceAll(ID_SOC_CP, "");
        }
        return Integer.valueOf(aux);
    }

    /**
     * Método auxiliar para la obtencion del valor de bindings componente no multisectorial.
     * @author : S&P Solutions
     * @param  : int
     * @return : String
     * @version: v1.0
     * @Fecha  : 12/09/2019
     */
    public String obtenerValorBindigns(int id) {
        String valor = "";
        switch (id) {
        case 1: //Actividad Economica Primaria
            valor =
                JSFUtils.resolveExpression("#{bindings.Id.inputValue}") != null ?
                JSFUtils.resolveExpression("#{bindings.Id.inputValue}").toString() : "0";
            logger.warning("=== obtenerValorBindigns | AEP | valor:" + valor);
            break;
        case 2: //Iniciativa Estrategica
            valor =
                JSFUtils.resolveExpression("#{bindings.Id1.inputValue}") != null ?
                JSFUtils.resolveExpression("#{bindings.Id1.inputValue}").toString() : "0";
            logger.warning("=== obtenerValorBindigns | IEs | valor:" + valor);
            break;
        case 3: //Cantidad de paises
            valor =
                JSFUtils.resolveExpression("#{bindings.IdRangoPaises.inputValue}") != null ?
                JSFUtils.resolveExpression("#{bindings.IdRangoPaises.inputValue}").toString() : "0";
            logger.warning("=== obtenerValorBindigns | CPB | valor:" + valor);
            break;
        case 4: //Actividad Economica
            valor =
                JSFUtils.resolveExpression("#{bindings.IdActEconomica.inputValue}") != null ?
                JSFUtils.resolveExpression("#{bindings.IdActEconomica.inputValue}").toString() : "0";
            logger.warning("=== obtenerValorBindigns | AEc | valor:" + valor);
            break;
        case 5: //Area de Focalizacion
            valor =
                JSFUtils.resolveExpression("#{bindings.IdAreaFocalizacion.inputValue}") != null ?
                JSFUtils.resolveExpression("#{bindings.IdAreaFocalizacion.inputValue}").toString() : "0";
            logger.warning("=== obtenerValorBindigns | AFo | valor:" + valor);
            break;
        case 6: //Eje Estrategico
            valor =
                JSFUtils.resolveExpression("#{bindings.IdEjeEstrategico.inputValue}") != null ?
                JSFUtils.resolveExpression("#{bindings.IdEjeEstrategico.inputValue}").toString() : "0";
            logger.warning("=== obtenerValorBindigns | EEs | valor:" + valor);
            break;

        }
        return valor;
    }

    /**
     * Método auxiliar para la obtencion del codigo de bindings componente no multisectorial.
     * @author : S&P Solutions
     * @param  : int
     * @return : String
     * @version: v1.0
     * @Fecha  : 12/09/2019
     */
    public String obtenerCodigoBindigns(int id) {
        String valor = "";
        switch (id) {
        case 1: //Actividad Economica Primaria
            valor =
                JSFUtils.resolveExpression("#{bindings.CodExterno.inputValue}") != null ?
                JSFUtils.resolveExpression("#{bindings.CodExterno.inputValue}").toString() : "";
            break;
        case 2: //Iniciativa Estrategica
            valor =
                JSFUtils.resolveExpression("#{bindings.IdActividad.inputValue}") != null ?
                JSFUtils.resolveExpression("#{bindings.IdActividad.inputValue}").toString() : "0";
            break;
        case 3: //Cantidad de paises
            valor =
                JSFUtils.resolveExpression("#{bindings.Id2.inputValue}") != null ?
                JSFUtils.resolveExpression("#{bindings.Id2.inputValue}").toString() : "0";
            break;
        case 4: //Actividad Economica
            valor =
                JSFUtils.resolveExpression("#{bindings.CodExterno1.inputValue}") != null ?
                JSFUtils.resolveExpression("#{bindings.CodExterno1.inputValue}").toString() : "0";
            break;
        }
        return valor;
    }


    /**
     * Método auxiliar para mapear los valores de los componentes hacia una lista de cadenas.
     * @author : S&P Solutions
     * @return : ArrayList
     * @version: v1.0
     * @Fecha  : 10/11/2019
     */
    public ActualizarOperacionRequestType obtenerValores() {
        Long idOperacion =
            Long.valueOf(AdfFacesContext.getCurrentInstance().getPageFlowScope().get("pIdOperacion").toString());
        ActualizarOperacionRequestType request = new ActualizarOperacionRequestType();
        StringBuilder valores = new StringBuilder();
        ClasificacionEstrategicaMultisectorial clasificaionEstrategica = new ClasificacionEstrategicaMultisectorial();
        Integer multisectorial = (Integer) JSFUtils.resolveExpression("#{bindings.v_multisectorial.inputValue}");
        Boolean banOperacionMulti = false;
        if (multisectorial == 1) { //Multisectorial
            for (ComponenteBean componente : componentes) {
                ComponenteClasificacionEstrategicaType componenteCE = new ComponenteClasificacionEstrategicaType();
                Catalogo actividadEconomicaMS = new Catalogo();
                Catalogo actividadEconomicaAsociadaMS = new Catalogo();
                Catalogo areaFocalizacionMS = new Catalogo();
                Catalogo ejeEstrategicoMS = new Catalogo();
                Catalogo iniciativaEstrategicaMS = new Catalogo();
                Catalogo rangoPaisesMS = new Catalogo();

                //Nombre
                componenteCE.setNombreComponente(componente.getNombre());
                //Descripcion
                componenteCE.setDescripcion(componente.getDescripcion());
                //Porcentaje
                componenteCE.setPorcentaje(new BigDecimal(componente.getPorcentaje()));
                //Actividad Economica Primaria
                String actividad = componente.getActividadEconomicaPrimaria();
                String[] aep = actividad.split("-");
                actividadEconomicaMS.setId(new Long(aep[0]).longValue());
                actividadEconomicaMS.setCodigoExterno(aep[1]);
                componenteCE.setActividadEconomica(actividadEconomicaMS);


                // Asignamos Actividad económica
                String actividadAs = componente.getActividadEconomica();
                String[] aepas = actividadAs.split("-");
                actividadEconomicaAsociadaMS.setId(new Long(aepas[0]).longValue());
                actividadEconomicaAsociadaMS.setCodigoExterno(aepas[1]);
                componenteCE.setActividadEconomicaAsociada(actividadEconomicaAsociadaMS);

                // Asignamos Iniciativa estratégica
                String iniEs = componente.getIniciativaEstrategica();
                String[] iniEst = iniEs.split("-");
                iniciativaEstrategicaMS.setId(new Long(iniEst[0]).longValue());
                componenteCE.setIniciativaEstrategica(iniciativaEstrategicaMS);

                // Asignamos Cantidad de Países beneficiados
                String rangopais = componente.getCantidadPaises();
                String[] paises = rangopais.split("-");
                rangoPaisesMS.setId(new Long(paises[1]).longValue());
                componenteCE.setCantidadPaises(rangoPaisesMS);

                // Asignamos Área de focalización
                areaFocalizacionMS.setId(new Long(componente.getAreaFocalizacion()).longValue());
                componenteCE.setAreaFocalizacion(areaFocalizacionMS);

                // Asignamos Eje estratégico
                ejeEstrategicoMS.setId(new Long(componente.getEjeEstrategico()).longValue());
                componenteCE.setEjeEstrategico(ejeEstrategicoMS);

                clasificaionEstrategica.getComponenteClasificacionEstrategica().add(componenteCE);

            }
            banOperacionMulti = true;
        } else {
            //No multi,  mapear los valores de los bindings para que actualice en CE
            ComponenteClasificacionEstrategicaType componenteCE = new ComponenteClasificacionEstrategicaType();
            Catalogo actividadEconomicaMS = new Catalogo();
            Catalogo actividadEconomicaAsociadaMS = new Catalogo();
            Catalogo areaFocalizacionMS = new Catalogo();
            Catalogo ejeEstrategicoMS = new Catalogo();
            Catalogo iniciativaEstrategicaMS = new Catalogo();
            Catalogo rangoPaisesMS = new Catalogo();

            //Nombre
            componenteCE.setNombreComponente("");
            //Descripcion
            componenteCE.setDescripcion("");
            //Porcentaje
            componenteCE.setPorcentaje(new BigDecimal("100"));
            //Actividad Economica Primaria
            actividadEconomicaMS.setId(new Long(JSFUtils.resolveExpression("#{bindings.Id.inputValue}").toString()).longValue());
            actividadEconomicaMS.setCodigoExterno(JSFUtils.resolveExpression("#{bindings.CodExterno.inputValue}").toString());
            componenteCE.setActividadEconomica(actividadEconomicaMS);

            // Asignamos Actividad económica
            actividadEconomicaAsociadaMS.setId(new Long(JSFUtils.resolveExpression("#{bindings.IdActEconomica.inputValue}").toString()).longValue());
            actividadEconomicaAsociadaMS.setCodigoExterno(JSFUtils.resolveExpression("#{bindings.CodExterno1.inputValue}").toString());
            componenteCE.setActividadEconomicaAsociada(actividadEconomicaAsociadaMS);

            // Asignamos Iniciativa estratégica
            iniciativaEstrategicaMS.setId(new Long(JSFUtils.resolveExpression("#{bindings.Id1.inputValue}").toString()).longValue());
            componenteCE.setIniciativaEstrategica(iniciativaEstrategicaMS);

            // Asignamos Cantidad de Países beneficiados
            rangoPaisesMS.setId(new Long(JSFUtils.resolveExpression("#{bindings.IdRangoPaises.inputValue}").toString()).longValue());
            componenteCE.setCantidadPaises(rangoPaisesMS);

            // Asignamos Área de focalización
            areaFocalizacionMS.setId(new Long(JSFUtils.resolveExpression("#{bindings.IdAreaFocalizacion.inputValue}").toString()).longValue());
            componenteCE.setAreaFocalizacion(areaFocalizacionMS);

            // Asignamos Eje estratégico IdEjeEstrategico
            ejeEstrategicoMS.setId(new Long(JSFUtils.resolveExpression("#{bindings.IdEjeEstrategico.inputValue}").toString()).longValue());
            componenteCE.setEjeEstrategico(ejeEstrategicoMS);

            clasificaionEstrategica.getComponenteClasificacionEstrategica().add(componenteCE);

        }
        //Paso de valores a Request
        Operacion operacion = new Operacion();
        logger.warning("\n=== idOperacion en operacion:" + idOperacion);
        operacion.setIdOperacion(idOperacion);
        logger.warning("\n=== clasificaionEstrategica en operacion ");
        // Se hace set de clasificacion multisectorial
        operacion.setClasificacionEstrategicaMultisectorial(clasificaionEstrategica);
        logger.warning("\n=== Valor multisectorial:" + banOperacionMulti);
        operacion.setEsMultisectorial(banOperacionMulti);
        logger.warning("\n=== setOperacion en request ");
        request.setOperacion(operacion);
        logger.warning("\n=== setStatus en request ");
        request.setStatus("editarCE");

        return request;
    }


    /**
     * Método de que realiza la consulta hacia la tabla de Clasificacion Estrategica dado el Id de operacion
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 26/11/2019
     */
    private void consultarValoresClasificacionEstrategica() {
        Long idOperacion =
            Long.valueOf(AdfFacesContext.getCurrentInstance().getPageFlowScope().get("pIdOperacion").toString());
        logger.warning("\n= Se ejecuta | Consultar Clasificacion Estrategica  | idOperacion:" + idOperacion);
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opCE = bindings.getOperationBinding("consultarClasificacionEstrategica");
        opCE.getParamsMap().put("value", idOperacion);
        opCE.execute();
        logger.warning("\n= Concluye | Consultar Clasificacion Estrategica  | idOperacion:" + idOperacion);
    }

    //==========  Metodos de filtrado de iteradores ============= //
    private void filtrarIniciativaEstrategica(oracle.jbo.domain.Number idPadre) {
        logger.warning("===== filtrarIniciativaEstrategica - idPadre: " + idPadre);
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
        logger.warning("===== filtrarCantidadPaises - idIniciativaEstrategica: " + idIniciativaEstrategica);
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
        logger.warning("===== filtrarActividadEconomica - idActividad: " + idActividad);
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
        logger.warning("===== filtrarAreaFocalizacion - idIniciativaEstrategica: " + idIniciativaEstrategica);
        logger.warning("===== filtrarAreaFocalizacion - idRangoPaises: " + idRangoPaises);
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
        logger.warning("===== filtrarEjeEstrategico - idIniciativaEstrategica: " + idIniciativaEstrategica);
        logger.warning("===== filtrarEjeEstrategico - idRangoPaises: " + idRangoPaises);
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

    public void limpiarIteradores() {
       // Limpiamos combos dependientes: Iniciativa estratégica, Cantidad de Países beneficiados, Actividad económica, Área de focalización y Eje estratégico
        filtrarIniciativaEstrategica(null);
        filtrarCantidadPaises(null);
        filtrarActividadEconomica(null);
        filtrarAreaFocalizacion(null, null);
        filtrarEjeEstrategico(null, null);
    }
    /* ==============     GETTERS & SETTERS   ============= */
    public void setMensajeErrores(StringBuilder mensajeErrores) {
        this.mensajeErrores = mensajeErrores;
    }

    public StringBuilder getMensajeErrores() {
        return mensajeErrores;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }

    public void setComponentes(ArrayList<ComponenteBean> componentes) {
        this.componentes = componentes;
    }

    public ArrayList<ComponenteBean> getComponentes() {
        return componentes;
    }

    public void setNumPorcentaje(int numPorcentaje) {
        this.numPorcentaje = numPorcentaje;
    }

    public int getNumPorcentaje() {
        return numPorcentaje;
    }

    public void setDummySeparatorCEEd(RichSeparator dummySeparatorCEEd) {
        this.dummySeparatorCEEd = dummySeparatorCEEd;
    }

    public RichSeparator getDummySeparatorCEEd() {
        logger.warning("\n=============== Detalle Editable | Ejecuta valoresIniciales()   ====");
        valoresIniciales();
        return dummySeparatorCEEd;
    }

    public void setMultisectorial(int multisectorial) {
        this.multisectorial = multisectorial;
    }

    public int getMultisectorial() {
        return multisectorial;
    }

    public void setPollEd(RichPoll pollEd) {
        this.pollEd = pollEd;
    }

    public RichPoll getPollEd() {
        return pollEd;
    }
}
