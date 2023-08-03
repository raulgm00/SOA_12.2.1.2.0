package org.bcie.fenix.view.gestoroperaciones;

import java.util.ArrayList;

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
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
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

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;


public class ComponentesMultisectorialBean {
    /* ==============      VARIABLES       ============= */
    private StringBuilder mensajeErrores;
    private int indice;
    private ArrayList<ComponenteBean> componentes;
    private ArrayList<String> errores;
    private int numPorcentaje;
    private static ADFLogger logger = null;
    private boolean banderaNombre;
    private boolean banderaDescripcion;
    private boolean banderaPorcentaje;
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
    private static final String ID_SOC_SECTOR_IBCIE = "socSecIbcie";
    private static final String ID_SI_SECTOR_IBCIE = "siSecIbcie";
    private static final String ID_SOC_SUBSECTOR_IBCIE = "socSubSecIbcie";
    private static final String ID_SI_SUBSECTOR_IBCIE = "siSubSecIbcie";
    private static final String LABEL_STYLE = "width: 140px;padding-left: 20px;text-align: left;";
    private static final String LABEL_STYLE_V1 = "width: 230px;padding-left: 20px;text-align: left;";
    private static final String CONTENT_STYLE_V1 = "width: 300px;";
    private static final String CONTENT_STYLE_V2 =
        "width: 300px;white-space: -moz-pre-wrap;white-space: pre;white-space: pre-wrap;word-wrap: break-word;";
    private static final String CONTENT_STYLE_V3 = "width: 100px;";
    private static final String CONTENT_STYLE_V4 = "width: 600px;";
    private static final String CONTENT_STYLE_V5 = "width: 900px;";
    private static final String BUNDLE = "org.bcie.fenix.view.GestorOperacionesFenixVCBundle";
    /* ==============      CONSTRUCTOR       ============= */
    public ComponentesMultisectorialBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
        valoresIniciales();
    }
    /* ==============     CUSTOM METHODS     ============= */

    /**
     * Método que hace el set de valores por defecto
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */

    public void valoresIniciales() {
        //Set de index en 1 para manejo de componentes dinámicos
        indice = 1;
        //Set de opción por defecto NO
        logger.warning("==== Set de valor de  v_multisectorial en 2 =========");
        setExpressionValue("#{bindings.v_multisectorial.inputValue}", 2);
        //Arreglo de tipo componenteBean (Clasificación Estratégica)
        componentes = new ArrayList<ComponenteBean>();
        // Arreglo para manejo de errores
        mensajeErrores = new StringBuilder();
        errores = new ArrayList<String>();
        //Suma de porcentajes
        numPorcentaje = 0;
    }

    /**
     * Método que controla el comportamiento del combo  Multisectorial.
     * @author : S&P Solutions
     * @param  : ValueChangeEvent
     * @version: v1.0
     * @Fecha  : 22/05/2019
     */
    public void vclMultisectorial(ValueChangeEvent valueChangeEvent) {
        logger.warning("\n=== Se ejecuta vclMultisectorial: " + valueChangeEvent.getComponent().getId());
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        if (valueChangeEvent.getNewValue().toString().equals("2")) { //Opción: No
            limpiarComponentes(obtenerComponente("pglComponentes"));
            limpiarIteradores();
            verComponente("btnAgregar", false);
            verComponente("pglOriginal", true);
        } else { //Opción: Si

            verComponente("btnAgregar", true);
            verComponente("pglOriginal", false);
            //Crear Componente nuevo
            indice = 1;
            crearComponente(indice);
            indice++;

        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente("pglComponentes"));
        asignarValorMultisectorialOperacionVO();
    }

    /**
     * Método que asigna el valor EsMultisectorial al iterador CrearOperacionVO.
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 07/07/2019
     */
    public void asignarValorMultisectorialOperacionVO() {
        logger.warning("\n==== Set de valor de  v_multisectorial en: 2 =========");
        DCIteratorBinding voCrearOperacion = null;
        // Asignamos en current row de CrearOperacionVO el valor de EsMultisectorial
        Integer multisectorial = (Integer) JSFUtils.resolveExpression("#{bindings.v_multisectorial.inputValue}");
        logger.warning("\n==== Pasar valor de v_multisectorial a voCrearOperacion: " + multisectorial);
        voCrearOperacion = ADFUtils.getDCBindingContainer().findIteratorBinding("CrearOperacionVOIterator");
        voCrearOperacion.getRowAtRangeIndex(0).setAttribute("EsMultisectorial", multisectorial);
    }

    /**
     * Método que controla el comportamiento del combo  Actividad Economica Primaria creado dinámicamente.
     * @author : S&P Solutions
     * @param  : ValueChangeEvent valueChangeEvent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public void vclActividadEconomicaPrimaria(ValueChangeEvent valueChangeEvent) {
        logger.warning("\n=== Se ejecuta actividadEconomicaPadreValueChangeListener: " + valueChangeEvent.getComponent().getId());
        Integer idActividadEconomica = null;
        String codigoExterno = null;
        // obtener componente originador de cambio
        RichSelectOneChoice soc = (RichSelectOneChoice) valueChangeEvent.getComponent();
        String idSoc = soc.getId();
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        String valor = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : "-NA-";
        logger.warning("\n=== actividadEconomicaPadreValueChangeListener | Valor: " + valor);
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
            logger.warning("\n=== Se ejecuta Limpiar combos dependientes  ==== ");
            filtrarCantidadPaises(null);
            filtrarActividadEconomica(null);
            filtrarAreaFocalizacion(null, null);
            filtrarEjeEstrategico(null, null);

            //Actualizar componentes
            logger.warning("\n=== Se ejecuta Actualizar componentes  ==== ");
            int index = obtenerIndice(idSoc);
            actualizarCmbIniciativaEstrategica(index);
            actualizarCmbCantidadPaises(index);
            actualizarCmbActividadEconomica(index);
            actualizarCmbAreaFocalizacion(index);
            actualizarCmbEjeEstrategico(index);
        } else {
            logger.warning("\n=== SE ejecuta actividadEconomicaPadreValueChangeListener valor: nulo ===");
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
        logger.warning("=== Inside iniciativaEstrategicaValueChangeListener: " +
                       valueChangeEvent.getComponent().getId());
        Integer idIniciativaEstrategica = null;
        // obtener componente originador de cambio
        RichSelectOneChoice soc = (RichSelectOneChoice) valueChangeEvent.getComponent();
        String idSoc = soc.getId();
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        String valor = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : "-NA-";
        logger.warning("=== Inside iniciativaEstrategicaValueChangeListener | Valor: " + valor);
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
                filtrarAreaFocalizacion(null, null);
                filtrarEjeEstrategico(null, null);
                //Actualizar componentes
                actualizarCmbCantidadPaises(index);
                actualizarCmbAreaFocalizacion(index);
                actualizarCmbEjeEstrategico(index);

            }
        } else {
            logger.warning("=== Inside iniciativaEstrategicaValueChangeListener valor nulo ===");
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

        logger.warning("===== Inside cantidadPaisesBeneficiadosValueChangeListener: " +
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

        logger.warning("=====  Obtener valores del combo - idRangoPaises: " + idRangoPaises);
        //Obtener valor Combo Iniciativa Estrategica
        int index = obtenerIndice(idSoc);
        valores = getValorTxtIniciativaEstrategica(obtenerComponente("pglComponentes"), index).split("-");
        idActividad = Integer.parseInt(valores[1]);
        idIniciativaEstrategica = Integer.parseInt(valores[0]);

        logger.warning("=====  Valores de IniEstrategica | idActividad: " + idActividad +
                       " | idIniciativaEstrategica:" + idIniciativaEstrategica);

        // Filtramos los combos Actividad económica, Área de focalización y Eje estratégico
        // por el idActividad, idIniciativaEstrategica e idRangoPaises
       
        if (idRangoPaises < 1) {
            filtrarActividadEconomica(null);
            filtrarAreaFocalizacion(null, null);
            filtrarEjeEstrategico(null, null);
            actualizarCmbActividadEconomica(index);
            actualizarCmbAreaFocalizacion(index);
            actualizarCmbEjeEstrategico(index);

        } else {
            filtrarActividadEconomica(new oracle.jbo.domain.Number(idActividad.intValue()));
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
        if(valueChangeEvent.getNewValue() != null){
            String entrada =   valueChangeEvent.getNewValue().toString();
            if((entrada.contains("."))){
                entrada=entrada.replaceAll(".", "");
                RichInputText rit = (RichInputText) valueChangeEvent.getComponent();
                String idRit = rit.getId();
                rit.setValue(entrada);
                actualizarComponente(idRit);
            }
        }
       
    }
  
    /**
     * Método auxiliar para la creacion de formularios dinámicos.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public void crearComponente(ActionEvent actionEvent) {
        logger.warning("\n=============== ActionListener: crearComponente | indice:" + indice);
        validarComponentes();
        if (mensajeErrores.length() > 0) {
            // mostar errores
            mostrarPopupErrorAgregarClasificacion(true);
        } else {
            crearComponente(indice);
            indice++;
            AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente("pglComponentes"));

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
            row.setAttribute("sectorIbcie", componente.getSectorIbcie());
            row.setAttribute("subSectorIbcie", componente.getSubSectorIbcie());
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

        if (errores.size() == 0) {
            //Pasar valores a VO
            mapearValoresComponentes();
        }

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
        logger.warning("\n===  Se ejecuta: validarComponentes   =====");
        componentes.clear();
        errores.clear();
        errores = new ArrayList<String>();
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
        logger.warning("\n===  Se ejecuta: obtenerValoresComponentes  para indice:"+index);
        ComponenteBean componente = new ComponenteBean();

        mensajeErrores = new StringBuilder();
       // errores = new ArrayList<String>();
        //Nombre
        if (getValorTxtNombre(obtenerComponente("pglComponentes"), index).trim().isEmpty()) {
            mensajeErrores.append("<p>El campo Nombre es requerido.</p>");
            errores.add("El campo Nombre es requerido.");
        } else {
            componente.setNombre(getValorTxtNombre(obtenerComponente("pglComponentes"), index));
        }
        //Descripción
        if (getValorTxtDescripcion(obtenerComponente("pglComponentes"), index).trim().isEmpty()) {
            mensajeErrores.append("<p>El campo Descripción es requerido.</p>");
            errores.add("El campo Descripción es requerido.");
        } else {
            componente.setDescripcion(getValorTxtDescripcion(obtenerComponente("pglComponentes"), index));
        }
        //Porcentaje
        if (getValorTxtPorcentaje(obtenerComponente("pglComponentes"), index).trim().isEmpty()) {
            mensajeErrores.append("<p>El campo Porcentaje es requerido.</p>");
            errores.add("El campo Porcentaje es requerido.");
        } else {
            String txtPorcentaje = getValorTxtPorcentaje(obtenerComponente("pglComponentes"), index);
            if(txtPorcentaje.contains(".")){
                mensajeErrores.append("<p>Formato no válido para el campo Porcentaje.</p>");
                errores.add("Formato no válido para el campo Porcentaje.");
            }else{
                componente.setPorcentaje(txtPorcentaje);
                numPorcentaje += Integer.parseInt(txtPorcentaje);
            }
        }
        // Sector I-BCIE
        Boolean isSocSectorVisible = ((RichSelectOneChoice) obtenerComponente("pglComponentes").findComponent(ID_SOC_SECTOR_IBCIE + index)).isVisible();
        if (isSocSectorVisible && (getValorTxtSectorIbcie(obtenerComponente("pglComponentes"), index).trim().isEmpty()) ){
            mensajeErrores.append("<p>El campo Sector I-BCIE Preliminar es requerido.</p>");
            errores.add("El campo Sector I-BCIE Preliminar es requerido.");
        } else {
            componente.setSectorIbcie(getValorTxtSectorIbcie(obtenerComponente("pglComponentes"), index));
        }
        // Sub-sector I-BCIE
        Boolean isSocSubSectorVisible = ((RichSelectOneChoice) obtenerComponente("pglComponentes").findComponent(ID_SOC_SUBSECTOR_IBCIE + index)).isVisible();
        if (isSocSubSectorVisible && (getValorTxtSubSectorIbcie(obtenerComponente("pglComponentes"), index).trim().isEmpty()) ){
            mensajeErrores.append("<p>El campo Sub-sector I-BCIE Preliminar es requerido.</p>");
            errores.add("El campo Sub-sector I-BCIE Preliminar es requerido.");
        } else {
            componente.setSubSectorIbcie(getValorTxtSubSectorIbcie(obtenerComponente("pglComponentes"), index));
        }
        
        //Actividad Economica Primaria
        if (getValorTxtActEconimicaPrimaria(obtenerComponente("pglComponentes"), index).trim().isEmpty()) {
            mensajeErrores.append("<p>El campo Actividad económica primaria es requerido.</p>");
            errores.add("El campo Actividad económica primaria es requerido.");
        } else {
            componente.setActividadEconomicaPrimaria(getValorTxtActEconimicaPrimaria(obtenerComponente("pglComponentes"),
                                                                                     index));
        }
        //Iniciativa Estratégica
        if (getValorTxtIniciativaEstrategica(obtenerComponente("pglComponentes"), index).trim().isEmpty()) {
            mensajeErrores.append("<p>El campo Iniciativa estratégica es requerido.</p>");
            errores.add("El campo Iniciativa estratégica es requerido.");
        } else {
            componente.setIniciativaEstrategica(getValorTxtIniciativaEstrategica(obtenerComponente("pglComponentes"),
                                                                                 index));
        }
        //Cantidad de Países Beneficiados
        if (getValorTxtCantidadPaises(obtenerComponente("pglComponentes"), index).trim().isEmpty()) {
            mensajeErrores.append("<p>El campo Cantidad de Países beneficiados es requerido.</p>");
            errores.add("El campo Cantidad de Países beneficiados es requerido.");
        } else {
            componente.setCantidadPaises(getValorTxtCantidadPaises(obtenerComponente("pglComponentes"), index));
        }
        //Actividad Económica
        if (getValorTxtActividadEconomica(obtenerComponente("pglComponentes"), index).trim().isEmpty()) {
            mensajeErrores.append("<p>No existe una Actividad económica para la combinación seleccionada.</p>");
            errores.add("No existe una Actividad económica para la combinación seleccionada.");
        } else {
            componente.setActividadEconomica(getValorTxtActividadEconomica(obtenerComponente("pglComponentes"), index));
        }
        //Área de Focalización
        if (getValorTxtAreaFocalizacion(obtenerComponente("pglComponentes"), index).trim().isEmpty()) {
            mensajeErrores.append("<p>No existe un Área de focalización para la combinación seleccionada.</p>");
            errores.add("No existe un Área de focalización para la combinación seleccionada.");
        } else {
            componente.setAreaFocalizacion(getValorTxtAreaFocalizacion(obtenerComponente("pglComponentes"), index));
        }
        //Área de Focalización
        if (getValorTxtEjeEstrategico(obtenerComponente("pglComponentes"), index).trim().isEmpty()) {
            mensajeErrores.append("<p>No existe un Eje estratégico para la combinación seleccionada.</p>");
            errores.add("No existe un Eje estratégico para la combinación seleccionada.");
        } else {
            componente.setEjeEstrategico(getValorTxtEjeEstrategico(obtenerComponente("pglComponentes"), index));
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
        logger.warning("\n=== Se ejecuta crearComponente dinamico: " + index );
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
        richPanelGroupLayoutDer.getChildren().add(createSectorIbcie(index));
        richPanelGroupLayoutDer.getChildren().add(createSubSectorIbcie(index));
	// Componente: *Actividad económica primaria:
        richPanelGroupLayoutDer.getChildren().add(createActEconPrim(index));
        // Componente: *Iniciativa estratégica::
        richPanelGroupLayoutDer.getChildren().add(createIniEstrategica(index));
        //Componente *Cantidad de países beneficiados:
        richPanelGroupLayoutDer.getChildren().add(createCantidadPaises(index));
        //Componente Actividad económica:
        richPanelGroupLayoutDer.getChildren().add(createActEconomica(index));
        //Componente Área de focalización:
        richPanelGroupLayoutDer.getChildren().add(createAreaFocalizacion(index));
        //Componente Eje estratégico:
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

            addComponent(obtenerComponente("pglComponentes"), richPanelGroupPadre);
            obtenerComponente("pglComponentes").getChildren().add(richSeparator);
        } else if (index == 2) {
            obtenerComponente("pglComponentes").getChildren().add(botonEliminar(index));
            obtenerComponente("pglComponentes").getChildren().add(richPanelGroupPadre);
            obtenerComponente("pglComponentes").getChildren().add(richSeparator);

        } else {
            obtenerComponente("pglComponentes").getChildren().add(botonEliminar(index));
            obtenerComponente("pglComponentes").getChildren().add(richPanelGroupPadre);
            obtenerComponente("pglComponentes").getChildren().add(richSeparator);
            ocultarBotonEliminar(obtenerComponente("pglComponentes"), index - 1, false);
        }
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
        richButton.addActionListener(getActionListener("#{viewScope.componenteMultisectorialBean.removeComponent}"));
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
        richButton.addActionListener(getActionListener("#{viewScope.componenteMultisectorialBean.crearComponente}"));
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
        richInputTextNombre.setMaximumLength(105);
        //Add PanelGroupLayout Nombre
        RichPanelGroupLayout richPanelGroupLayoutNombre = new RichPanelGroupLayout();
        richPanelGroupLayoutNombre.setLayout("horizontal");
        richPanelGroupLayoutNombre.setId("rpglNom" + index);
        richPanelGroupLayoutNombre.getChildren().add(richInputTextNombre);

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
        //Refrescar componente
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        String valor = ui.getAttributes().get("value").toString();
        logger.warning(" === getValorTxtNombre | index:"+index+" | valor:"+valor);

        return valor;
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
     * @param  :
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
        richInputTextDescripcion.setMaximumLength(250);
        //Add PanelGroupLayout Descripcion
        RichPanelGroupLayout richPanelGroupLayoutDesc = new RichPanelGroupLayout();
        richPanelGroupLayoutDesc.setLayout("horizontal");
        richPanelGroupLayoutDesc.setId("rpglDsc" + index);
        richPanelGroupLayoutDesc.getChildren().add(richInputTextDescripcion);

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
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        String valor = ui.getAttributes().get("value").toString();
        logger.warning(" === getValorTxtDescripcion | index:"+index+" | valor:"+valor);

        return valor;
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
        richInputTextPorcentaje.addValueChangeListener(resolveValueChangeListener("#{viewScope.componenteMultisectorialBean.vclPorcentaje}"));
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
     * Método que obtiene el valor del componente Porcentaje creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtPorcentaje(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_PORCENTAJE + index);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        String valor = ui.getAttributes().get("value").toString();
        logger.warning(" === getValorTxtPorcentaje | index:"+index+" | valor:"+valor);

        return valor;
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
        richSelectOneChoiceAEP.addValueChangeListener(resolveValueChangeListener("#{viewScope.componenteMultisectorialBean.vclActividadEconomicaPrimaria}"));
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
     * Método que obtiene el valor del componente Actividad Económica Primaria creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtActEconimicaPrimaria(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_AEP + index);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        String valor = (ui.getAttributes().get("value") != null ?
                (ui.getAttributes().get("value").toString().startsWith("0-") ? "" :
                 ui.getAttributes().get("value").toString()) : "");
        logger.warning(" === getValorTxtActEconimicaPrimaria | index:"+index+" | valor:"+valor);

        return valor;
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
        richSelectOneChoiceInEs.addValueChangeListener(resolveValueChangeListener("#{viewScope.componenteMultisectorialBean.vclIniciativaEstrategica}"));
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
     * Método que obtiene el valor del componente Iniciativa Estratégica creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtIniciativaEstrategica(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_IE + index);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        String valor = (ui.getAttributes().get("value") != null ?
                (ui.getAttributes().get("value").toString().startsWith("0-") ? "" :
                 ui.getAttributes().get("value").toString()) : "");
        
        logger.warning(" === getValorTxtIniciativaEstrategica | index:"+index+" | valor:"+valor);

        return valor;
        
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
        richSelectOneChoicePais.addValueChangeListener(resolveValueChangeListener("#{viewScope.componenteMultisectorialBean.vclCantidadPaises}"));
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
     * Método que obtiene el valor del componente Cantidad de países creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtCantidadPaises(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_CP + index);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        String valor = (ui.getAttributes().get("value") != null ?
                (ui.getAttributes().get("value").toString().startsWith("0-") ? "" :
                 ui.getAttributes().get("value").toString()) : "");
        
        logger.warning(" === getValorTxtCantidadPaises | index:"+index+" | valor:"+valor);

        return valor;
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
     * Método que obtiene el valor del componente Actividad Económica creado dinámicamente.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/05/2019
     */
    public String getValorTxtActividadEconomica(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_AE + index);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        String valor =  ui.getAttributes().get("value") != null ? ui.getAttributes().get("value").toString() : "";
        logger.warning(" === getValorTxtActividadEconomica | index:"+index+" | valor:"+valor);

        return valor;
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

        // richPanelGroupLayoutAF.getChildren().add(richPanelGroupLayoutLblAF);
        richPanelGroupLayoutAF.getChildren().add(richSelectOneChoiceAF);

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
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        String valor =  ui.getAttributes().get("value") != null ? ui.getAttributes().get("value").toString() : "";
        logger.warning(" === getValorTxtAreaFocalizacion | index:"+index+" | valor:"+valor);

        return valor;
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

        //  richPanelGroupLayout.getChildren().add(richPanelGroupLayoutLbl);
        richPanelGroupLayout.getChildren().add(richSelectOneChoice);

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
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        String valor =  ui.getAttributes().get("value") != null ? ui.getAttributes().get("value").toString() : "";
        logger.warning(" === getValorTxtEjeEstrategico | index:"+index+" | valor:"+valor);

        return valor;
    }

    /**
     * Método para la creacion dinámica de componente combo: Sector I-BCIE.
     * @author : Kruger Innova Latbc
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 31/12/2019
     */
    public UIComponent createSectorIbcie(int index) {
        RichSelectOneChoice socSectorIbcie = new RichSelectOneChoice();
        socSectorIbcie.setId(ID_SOC_SECTOR_IBCIE + index);
        socSectorIbcie.setShowRequired(true);
        socSectorIbcie.setVisible((Boolean)resolveExpression("#{(bindings.ProductoTieneRiesgoCredito.inputValue eq 1)}"));
        socSectorIbcie.setAutoSubmit(true);
        socSectorIbcie.setLabel(getValueFromResourceBundle(BUNDLE, "LBL_SECTOR_IBCIE"));
        socSectorIbcie.setLabelStyle(LABEL_STYLE_V1);
        String ptg[] = { "socIdProducto" };
        socSectorIbcie.setPartialTriggers(ptg);
        socSectorIbcie.setContentStyle(CONTENT_STYLE_V1);
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_SECTOR_IBCIE + index);
        selectItems.setValue(cmbSectorIbcie());
        socSectorIbcie.getChildren().add(selectItems);
        //Add PanelGroupLayout
        RichPanelGroupLayout rpglSec = new RichPanelGroupLayout();
        rpglSec.setLayout("horizontal");
        rpglSec.setId("rpglSec" + index);
        rpglSec.getChildren().add(socSectorIbcie);

        return rpglSec;
    }
    
    /**
     * Método que obtiene el valor del componente Sector I-BCIE creado dinámicamente.
     * @author : Kruger Innova Latbc
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 31/12/2020
     */
    public String getValorTxtSectorIbcie(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_SECTOR_IBCIE + index);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        String valor = ui.getAttributes().get("value") != null ? ui.getAttributes().get("value").toString() : "";
        logger.warning(" === getValorTxtSectorIbcie | index:"+index+" | valor:"+valor);

        return valor;
    }
    
    /**
     * Método para la creacion dinámica de componente combo: Sub-sector I-BCIE.
     * @author : Kruger Innova Latbc
     * @param  : int index
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 31/12/2019
     */
    public UIComponent createSubSectorIbcie(int index) {
        RichSelectOneChoice socSubSectorIbcie = new RichSelectOneChoice();
        socSubSectorIbcie.setId(ID_SOC_SUBSECTOR_IBCIE + index);
        socSubSectorIbcie.setShowRequired(true);
        socSubSectorIbcie.setVisible((Boolean)resolveExpression("#{(bindings.ProductoTieneRiesgoCredito.inputValue eq 1)}"));
        socSubSectorIbcie.setAutoSubmit(true);
        socSubSectorIbcie.setLabel(getValueFromResourceBundle(BUNDLE, "LBL_SUBSECTOR_IBCIE"));
        socSubSectorIbcie.setLabelStyle(LABEL_STYLE_V1);
        String ptg[] = { "socIdProducto" };
        socSubSectorIbcie.setPartialTriggers(ptg);
        socSubSectorIbcie.setContentStyle(CONTENT_STYLE_V1);
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_SUBSECTOR_IBCIE + index);
        selectItems.setValue(cmbSubSectorIbcie());
        socSubSectorIbcie.getChildren().add(selectItems);
        //Add PanelGroupLayout
        RichPanelGroupLayout rpglSubSec = new RichPanelGroupLayout();
        rpglSubSec.setLayout("horizontal");
        rpglSubSec.setId("rpglSubSec" + index);
        rpglSubSec.getChildren().add(socSubSectorIbcie);

        return rpglSubSec;
    }
    
    /**
     * Método que obtiene el valor del componente Sub-sector I-BCIE creado dinámicamente.
     * @author : Kruger Innova Latbc
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 31/12/2020
     */
    public String getValorTxtSubSectorIbcie(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_SUBSECTOR_IBCIE + index);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
        String valor = ui.getAttributes().get("value") != null ? ui.getAttributes().get("value").toString() : "";
        logger.warning(" === getValorTxtSubSectorIbcie | index:"+index+" | valor:"+valor);

        return valor;
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

        ocultarNombre(obtenerComponente("pglComponentes"), 1, valor);
        ocultarDescripcion(obtenerComponente("pglComponentes"), 1, valor);
        ocultarPorcentaje(obtenerComponente("pglComponentes"), 1, valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente("pglComponentes"));
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
                ocultarBotonEliminar(obtenerComponente("pglComponentes"), index - 1, true);
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
     * Método que obtiene el componente (Sector I-BCIE) dado el index.
     * @author : Kruger Innova Latbc
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 22/01/2020
     */
    public UIComponent getSocSectorIbcieComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_SECTOR_IBCIE + index);
        return ui;
    }
    
    /**
     * Método que obtiene el componente (Sub-sector I-BCIE) dado el index.
     * @author : Kruger Innova Latbc
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 22/01/2020
     */
    public UIComponent getSocSubSectorIbcieComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SOC_SUBSECTOR_IBCIE + index);
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
        removeComponents(obtenerComponente("pglComponentes"), index);
        indice--;
    }

    /**
     * Método auxiliar para obtener el componente combo de Sector I-BCIE creado dinámicamente.
     * @author : Kruger Innova Latbc
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 22/01/2020
     */
    public RichSelectOneChoice obtenerUicSectorIbcie(int index) {
        RichSelectOneChoice ui = (RichSelectOneChoice)getSocSectorIbcieComponent(obtenerComponente("pglComponentes"), index);
        return ui;
    }
    
    /**
     * Método auxiliar para obtener el componente combo de Sub-sector I-BCIE creado dinámicamente.
     * @author : Kruger Innova Latbc
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 22/01/2020
     */
    public RichSelectOneChoice obtenerUicSubSectorIbcie(int index) {
        RichSelectOneChoice ui = (RichSelectOneChoice)getSocSubSectorIbcieComponent(obtenerComponente("pglComponentes"), index);
        return ui;
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
        UIComponent ui = getSocActEcPrComponent(obtenerComponente("pglComponentes"), index);
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
        UIComponent ui = getSocIniEstrategicaComponent(obtenerComponente("pglComponentes"), index);
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
        UIComponent ui = getSocPaisesComponent(obtenerComponente("pglComponentes"), index);

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
        UIComponent ui = getSocActEconomicaComponent(obtenerComponente("pglComponentes"), index);

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
        UIComponent ui = getSocAreaFocalizacionComponent(obtenerComponente("pglComponentes"), index);

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
        UIComponent ui = getSocEjeEstrategicoComponent(obtenerComponente("pglComponentes"), index);

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
            combo.add(new SelectItem(idCod, descripcion));
            logger.warning("===== cmbActividadEconomica | ID:" + idCod + " | Descripcion:" + descripcion);
            
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
            combo.add(new SelectItem(id, descripcion));
            logger.warning("===== cmbAreaFocalizacion | ID:" + id + " | Descripcion:" + descripcion);
            
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
            combo.add(new SelectItem(id, descripcion));
            logger.warning("===== cmbEjeEstrategico | ID:" + id + " | Descripcion:" + descripcion);
            
        }
        return combo;
    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente Sector I-BCIE creado dinámicamente.
     * @author : Kruger Innova Latbc
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 31/12/2020
     */
    private List<SelectItem> cmbSectorIbcie() {
        logger.warning("===== Inside cmbSectorIbcie =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("CatSectorIbcieVOIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {

            String codSector = (String) row.getAttribute("CodigoSectorIbcie");
            String descripcion = (String) row.getAttribute("Descripcion");
            combo.add(new SelectItem(codSector, descripcion));
            logger.warning("===== cmbSectorIbcie | CodigoSectorIbcie:" + codSector + " | Descripcion:" + descripcion);
        }
        return combo;
    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente Sub-sector I-BCIE creado dinámicamente.
     * @author : Kruger Innova Latbc
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 31/12/2020
     */
    private List<SelectItem> cmbSubSectorIbcie() {
        logger.warning("===== Inside cmbSubSectorIbcie =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("CatSubSectorIbcieVOIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {

            String codSector = (String) row.getAttribute("CodigoSubsectorIbcie");
            String descripcion = (String) row.getAttribute("Descripcion");
            combo.add(new SelectItem(codSector, descripcion));
            logger.warning("===== cmbSubSectorIbcie | CodigoSubsectorIbcie:" + codSector + " | Descripcion:" + descripcion);
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
            actualizarCmbSectorIbcie(x);
            actualizarCmbSubSectorIbcie(x);
            actualizarCmbActivdadEconomicaPriaria(x);
            actualizarCmbIniciativaEstrategica(x);
            actualizarCmbCantidadPaises(x);
            actualizarCmbActividadEconomica(x);
            actualizarCmbAreaFocalizacion(x);
            actualizarCmbEjeEstrategico(x);
        }
    }

    /**
     * Método auxiliar para actualizar valores Sector I-BCIE.
     * @author : Kruger Innova Latbc
     * @param  : int
     * @return : void
     * @version: v1.0
     * @Fecha  : 22/01/2020
     */
    public void actualizarCmbSectorIbcie(int indice) {
        logger.info("Inside actualizarCmbSectorIbcie con indice:" + indice);
        RichSelectOneChoice ui = obtenerUicSectorIbcie(indice);
        
        // Para el combo Sector I-BCIE no es necesario invocar a "actualizarComponenteSelectItem", puesto que sus hijos (selectItems) no cambian.
        // Hay que resetear el valor seleccionado, reasignar propiedad visible (para que tome el cambio de flag ProductoTieneRiesgoCredito) y addPartialTarget.
        ui.resetValue();
        ui.setVisible((Boolean)resolveExpression("#{(bindings.ProductoTieneRiesgoCredito.inputValue eq 1)}"));
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }
    
    /**
     * Método auxiliar para actualizar valores Sub-sector I-BCIE.
     * @author : Kruger Innova Latbc
     * @param  : int
     * @return : void
     * @version: v1.0
     * @Fecha  : 22/01/2020
     */
    public void actualizarCmbSubSectorIbcie(int indice) {
        logger.info("Inside actualizarCmbSubSectorIbcie con indice:" + indice);
        RichSelectOneChoice ui = obtenerUicSubSectorIbcie(indice);

        // Para el combo Sub-sector I-BCIE no es necesario invocar a "actualizarComponenteSelectItem", puesto que sus hijos (selectItems) no cambian.
        // Hay que resetear el valor seleccionado, reasignar propiedad visible (para que tome el cambio de flag ProductoTieneRiesgoCredito) y addPartialTarget.
        ui.resetValue();
        ui.setVisible((Boolean)resolveExpression("#{(bindings.ProductoTieneRiesgoCredito.inputValue eq 1)}"));
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
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
        RichSelectOneChoice rsoc = (RichSelectOneChoice) ui;
        rsoc.resetValue();
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
        RichSelectOneChoice rsoc = (RichSelectOneChoice) ui;
        rsoc.resetValue();
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
        RichSelectOneChoice rsoc = (RichSelectOneChoice) ui;
        rsoc.resetValue();
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
        RichPopup ui = (RichPopup) findComponent(facesCtx.getViewRoot(), "ppMsgErrAgregarCM");

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

    //==========  Metodos de filtrado de iteradores ============= //
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

        DCIteratorBinding voTiposActividadEconomicaPadre = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Integer idProducto = (Integer) JSFUtils.resolveExpression("#{bindings.IdProducto.attributeValue}");
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
}
