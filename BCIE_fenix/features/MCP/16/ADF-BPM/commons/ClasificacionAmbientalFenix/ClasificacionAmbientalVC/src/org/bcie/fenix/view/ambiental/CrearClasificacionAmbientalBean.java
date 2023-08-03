package org.bcie.fenix.view.ambiental;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import javax.el.ValueExpression;

import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.RowSetIterator;
import oracle.jbo.Row;

import javax.faces.event.ActionListener;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.MethodExpressionValueChangeListener;

import javax.el.MethodExpression;

import javax.faces.event.ValueChangeListener;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.faces.application.Application;

import javax.faces.component.UISelectItems;

import oracle.adf.view.rich.component.rich.RichPoll;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichSeparator;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.event.PollEvent;

public class CrearClasificacionAmbientalBean{
    /* ==============      VARIABLES       ============= */
    private StringBuilder mensajeErrores;
    private int indice;
    private ArrayList<ComponenteAmbiental> componentes;
    private ArrayList<String> erroresCA;
    private static ADFLogger logger = null;
    private RichSeparator dummySeparatorAC;


    /* ==============      CONSTANTES       ============= */
    private static final String ID_SECTOR = "socSCTR";
    private static final String ID_PG_SECTOR = "pglSCTR";
    private static final String ID_SI_SECTOR = "siSCTR";
    private static final String ID_APORTE = "socAPRT";
    private static final String ID_PG_APORTE = "pglAPRT";
    private static final String ID_SI_APORTE = "siAPRT";
    private static final String ID_CATEGORIA = "socCAT";
    private static final String ID_PG_CATEGORIA = "pglCAT";
    private static final String ID_SI_CATEGORIA = "siCAT";
    private static final String ID_SUBCATEGORIA = "socSUBC";
    private static final String ID_PG_SUBCATEGORIA = "pglSUBC";
    private static final String ID_SI_SUBCATEGORIA = "siSUBC";
    private static final String LABEL_STYLE = "text-align: left;";
    private static final String LABEL_STYLE_V1 = "width: 140px;padding-left: 20px;text-align: left;";
    private static final String CONTENT_STYLE_V1 = "width: 300px;";
    private static final String CONTENT_STYLE_V2 =
        "width: 300px;white-space: -moz-pre-wrap;white-space: pre;white-space: pre-wrap;word-wrap: break-word;";
    private static final String CONTENT_STYLE_V3 = "width: 100px;";
    private static final String CONTENT_STYLE_V4 = "width: 600px;";
    private static final String CONTENT_STYLE_V5 = "width: 900px;";
    private static final String BUNDLE = "org.bcie.fenix.view.ambiental.ClasificacionAmbientalVCBundle";
    private static final String ID_ELIMINAR = "rbCA";
    private static final String ID_PANELGRAL = "pglCAGral";
    private static final String ID_PANEL = "pglCmpCA";
    private static final String ID_PANELP = "pglCmpCAP";
    private static final String ID_PANELCMP = "pglComponentesAmbiental";
    private static final String ID_SEPARATOR = "rlsCA";
    private static final String LAYOUT_V = "vertical";
    private static final String LAYOUT_H = "horizontal";
    private static final String ID_PANELFORM = "pflCA";


    /* ==============      CONSTRUCTOR       ============= */

    public CrearClasificacionAmbientalBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }

    }
    /* ==============     CUSTOM METHODS     ============= */

    /**
     * Método que hace el set de valores por defecto
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 17/09/2019
     */

    public void valoresIniciales() {
        //Set de index en 1 para manejo de componentes dinámicos
        indice = 1;
        logger.warning("==== Set de valor de  indice en 1 =========");
        //Arreglo de tipo ComponenteAmbiental (Clasificación Ambiental)
        componentes = new ArrayList<ComponenteAmbiental>();
        // Arreglo para manejo de errores
        mensajeErrores = new StringBuilder();
        erroresCA = new ArrayList<String>();
        crearComponente(indice);
        indice++;
        AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANELGRAL));
    }

    /**
     * Método que controla el comportamiento del combo  Aporte ambiental.
     * @author : S&P Solutions
     * @param  : ValueChangeEvent valueChangeEvent
     * @version: v1.0
     * @Fecha  : 17/09/2019
     */

    public void vclAporteAmbiental(ValueChangeEvent valueChangeEvent) {
        logger.warning("\n=== Dentro de: vclAporteAmbiental: " + valueChangeEvent.getComponent().getId());
        Integer idAporte = null;
        // obtener componente originador de cambio
        RichSelectOneChoice soc = (RichSelectOneChoice) valueChangeEvent.getComponent();
        String idSoc = soc.getId();
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        String valor = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : "-NA-";
        if (!valor.matches("-NA-")) {
            //ObtenerID
            int index = obtenerIndice(idSoc);
            /* if (index == 1) {
                idAporte = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdAporte.inputValue}").toString());
            } else {
                String identificador = ID_APORTE + index;
                idAporte = getValorIDCmbComponenteAmbiental(obtenerComponente(ID_PANELGRAL), identificador);
            }*/
            String identificador = ID_APORTE + index;
            idAporte = getValorIDCmbComponenteAmbiental(obtenerComponente(ID_PANELCMP), identificador);
            logger.warning("\n===  vclAporteAmbiental | Valor: " + valor + " | idSoc: " + idSoc + " | idAporte: " +
                           idAporte);
            // Filtrar y actualizar Categoria
            logger.warning("\n=== filtrarCategoriaAmbiental con  idAporte:" + idAporte);
            filtrarCategoriaAmbiental(idAporte.intValue());
            logger.warning("\n=== actualizarCmbCategoriaAmbiental con index:" + index);
            actualizarCmbCategoriaAmbiental(index);
            // Limpiar y actualziar combo SubCategoria
            logger.warning("\n=== filtrarSubCategoriaAmbiental con  idCategoria:-1");
            filtrarSubCategoriaAmbiental(-1);
            logger.warning("\n=== actualizarCmbSubCategoriaAmbiental con index:" + index);
            actualizarCmbSubCategoriaAmbiental(index);

            AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANELGRAL));
        } else {
            logger.warning("\n=== Dentro de vclAporteAmbiental - valor nulo ===");
        }

    }


    /**
     * Método que controla el comportamiento del combo  Categoria ambiental.
     * @author : S&P Solutions
     * @param  : ValueChangeEvent valueChangeEvent
     * @version: v1.0
     * @Fecha  : 17/09/2019
     */

    public void vclCategoriaAmbiental(ValueChangeEvent valueChangeEvent) {
        logger.warning("\n=== Dentro de vclCategoriaAmbiental: " + valueChangeEvent.getComponent().getId());
        Integer idCategoria = null;
        // obtener componente originador de cambio
        RichSelectOneChoice soc = (RichSelectOneChoice) valueChangeEvent.getComponent();
        String idSoc = soc.getId();
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // Actualiza valor seleccionado en combo
        String valor = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : "-NA-";
        if (!valor.matches("-NA-")) {
            //ObtenerID
            int index = obtenerIndice(idSoc);
            /* if (index == 1) {
                idCategoria =
                    Integer.valueOf(JSFUtils.resolveExpression("#{bindings.IdCategoria.inputValue}").toString());
            } else {
                idCategoria = Integer.parseInt(getValorTxtCategoria(obtenerComponente(ID_PANELGRAL), index));
            }*/
            idCategoria = Integer.parseInt(getValorTxtCategoria(obtenerComponente(ID_PANELGRAL), index));
            logger.warning("\n===  vclCategoriaAmbiental | Valor: " + valor + " | idSoc: " + idSoc +
                           " | idCategoria: " + idCategoria);
            // Filtrar Categoria
            logger.warning("\n=== filtrarSubCategoriaAmbiental con  idCategoria:" + idCategoria);
            filtrarSubCategoriaAmbiental(idCategoria.intValue());
            //Actualizar componentes
            logger.warning("\n=== actualizarCmbSubCategoriaAmbiental con index:" + index);
            actualizarCmbSubCategoriaAmbiental(index);
        } else {
            logger.warning("\n=== Dentro de: vclCategoriaAmbiental - valor nulo ===");
        }

    }


    /**
     * Método auxiliar para la creacion de componentes dinámicos.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 21/09/2019
     */
    public void crearComponente(ActionEvent actionEvent) {
        logger.warning("=============== ActionListener: crearComponente | indice:" + indice);
        validarComponentes();
        if (mensajeErrores.length() > 0) {
            // mostar errores
            mostrarPopupErrorAgregarClasificacionAmbiental(true);
        } else {
            crearComponente(indice);
            indice++;
            AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANELCMP));

        }


    }


    /**
     * Método que valida los valores  del formulario dinámico.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */

    public void validarComponentes() {
        logger.warning("\n=== Inside validarComponentes");
        componentes = new ArrayList<ComponenteAmbiental>();
        componentes.clear();
        erroresCA.clear();
        for (int x = 1; x < indice; x++) {
            obtenerValoresComponentes(x);
        }

    }

    /**
     * Método que obtiene los valores de cada componente de los formularios dinámicos.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public void obtenerValoresComponentes(int index) {
        logger.warning("\n=======  obtenerValoresComponentes index:" + index);
        ComponenteAmbiental componente = new ComponenteAmbiental();
        mensajeErrores = new StringBuilder();
        erroresCA = new ArrayList<String>();
        //Sector
        String sector = getValorTxtSector(obtenerComponente(ID_PANELGRAL), index);
        if (sector.equals("-1")) {
            mensajeErrores.append("<p>El campo Sector es requerido.</p>");
            erroresCA.add("El campo Sector es requerido.");
        } else {
            componente.setSector(sector);
        }
        //Aporte
        String aporte = getValorTxtAporte(obtenerComponente(ID_PANELGRAL), index);
        if (aporte.equals("-1")) {
            mensajeErrores.append("<p>El campo Aporte es requerido.</p>");
            erroresCA.add("El campo Aporte es requerido.");
        } else {
            componente.setAporte(aporte);
        }
        //Categoria
        String categoria = getValorTxtCategoria(obtenerComponente(ID_PANELGRAL), index);
        if (categoria.equals("-1")) {
            mensajeErrores.append("<p>El campo Categoría es requerido.</p>");
            erroresCA.add("El campo Categoría es requerido.");
        } else {
            componente.setCategoria(categoria);

        }
        //SubCategoria
        String subcategoria = getValorTxtSubCategoria(obtenerComponente(ID_PANELGRAL), index);
        if (subcategoria.equals("-1")) {
            mensajeErrores.append("<p>El campo Subcategoría es requerido.</p>");
            erroresCA.add("El campo Subcategoría es requerido.");
        } else {
            componente.setSubCategoria(subcategoria);
        }
        if (!(mensajeErrores.length() > 0)) {
            componentes.add(componente);
        }
    }

    //==========  Metodos de creacion de componente ============= //

    /**
     * Método que crea el formulario dinámico.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 20/05/2019
     */
    public void crearComponente(int index) {
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_V);
        richPanelGroupLayout.setId(ID_PANEL + index);
        richPanelGroupLayout.setStyleClass(CONTENT_STYLE_V1);
        richPanelGroupLayout.getChildren().add(crearSector(index));
        richPanelGroupLayout.getChildren().add(crearAporte(index));
        richPanelGroupLayout.getChildren().add(crearCategoria(index));
        richPanelGroupLayout.getChildren().add(crearSubCategoria(index));

        //Add LineSeparator
        RichSeparator richSeparator = new RichSeparator();
        richSeparator.setId(ID_SEPARATOR + index);

        //Add PanelGroupLayout Padre
        RichPanelGroupLayout richPanelGroupLayoutPadre = new RichPanelGroupLayout();
        richPanelGroupLayoutPadre.setLayout(LAYOUT_H);
        richPanelGroupLayoutPadre.setId(ID_PANELP + index);
        richPanelGroupLayoutPadre.getChildren().add(richPanelGroupLayout);


        logger.warning("===============  CREATE FORM ===============");

        if (index == 1) {
            obtenerComponente(ID_PANELCMP).getChildren().add(richSeparator);
            //obtenerComponente(ID_PANELCMP).getChildren().add(botonEliminar(index));
            obtenerComponente(ID_PANELCMP).getChildren().add(richPanelGroupLayoutPadre);

        } else if (index == 2) {
            obtenerComponente(ID_PANELCMP).getChildren().add(richSeparator);
            obtenerComponente(ID_PANELCMP).getChildren().add(botonEliminar(index));
            obtenerComponente(ID_PANELCMP).getChildren().add(richPanelGroupLayoutPadre);
            // ocultarBotonEliminar(obtenerComponente(ID_PANELCMP), index - 1, false);
        } else if (index > 2) {
            obtenerComponente(ID_PANELCMP).getChildren().add(richSeparator);
            obtenerComponente(ID_PANELCMP).getChildren().add(botonEliminar(index));
            obtenerComponente(ID_PANELCMP).getChildren().add(richPanelGroupLayoutPadre);
            ocultarBotonEliminar(obtenerComponente(ID_PANELCMP), index - 1, false);
        }
    }


    /**
     * Método para la creacion dinámica de componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 20/09/2019
     */
    public UIComponent crearSector(int index) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoice = new RichSelectOneChoice();
        richSelectOneChoice.setId(ID_SECTOR + index);
        richSelectOneChoice.setAutoSubmit(true);
        richSelectOneChoice.setShowRequired(true);
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "SECTOR"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V1);
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_SECTOR + index);
        selectItems.setValue(cmbSectorAmbiental());
        richSelectOneChoice.getChildren().add(selectItems);
        richSelectOneChoice.setValue("0");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_H);
        richPanelGroupLayout.setId(ID_PG_SECTOR + index);
        richPanelGroupLayout.getChildren().add(richSelectOneChoice);

        return richPanelGroupLayout;
    }

    /**
     * Método para la creacion dinámica de componente Aporte Ambiental.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 20/09/2019
     */
    public UIComponent crearAporte(int index) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoice = new RichSelectOneChoice();
        richSelectOneChoice.setId(ID_APORTE + index);
        richSelectOneChoice.setAutoSubmit(true);
        richSelectOneChoice.setShowRequired(true);
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "APORTE"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V1);
        richSelectOneChoice.addValueChangeListener(resolveValueChangeListener("#{viewScope.crearClasificacionAmbiental.vclAporteAmbiental}"));
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_APORTE + index);
        selectItems.setValue(cmbAporteAmbiental());
        richSelectOneChoice.getChildren().add(selectItems);
        richSelectOneChoice.setValue("0");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_H);
        richPanelGroupLayout.setId(ID_PG_APORTE + index);
        richPanelGroupLayout.getChildren().add(richSelectOneChoice);

        return richPanelGroupLayout;
    }


    /**
     * Método para la creacion dinámica de componente Categoria Ambiental.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 20/09/2019
     */
    public UIComponent crearCategoria(int index) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoice = new RichSelectOneChoice();
        richSelectOneChoice.setId(ID_CATEGORIA + index);
        richSelectOneChoice.setAutoSubmit(true);
        richSelectOneChoice.setShowRequired(true);
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "CATEGORIA"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V1);
        String ptg[] = { ID_APORTE + index };
        richSelectOneChoice.setPartialTriggers(ptg);
        richSelectOneChoice.addValueChangeListener(resolveValueChangeListener("#{viewScope.crearClasificacionAmbiental.vclCategoriaAmbiental}"));
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_CATEGORIA + index);
        selectItems.setValue(cmbCategoriaAmbiental());
        richSelectOneChoice.getChildren().add(selectItems);
        richSelectOneChoice.setValue("0");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_H);
        richPanelGroupLayout.setId(ID_PG_CATEGORIA + index);
        richPanelGroupLayout.getChildren().add(richSelectOneChoice);

        return richPanelGroupLayout;
    }


    /**
     * Método para la creacion dinámica de componente SubCategoria Ambiental.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 20/09/2019
     */
    public UIComponent crearSubCategoria(int index) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoice = new RichSelectOneChoice();
        richSelectOneChoice.setId(ID_SUBCATEGORIA + index);
        richSelectOneChoice.setAutoSubmit(true);
        richSelectOneChoice.setShowRequired(true);
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "SUBCATEGORIA"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V1);
        String ptg[] = { ID_APORTE + index + " " + ID_CATEGORIA + index };
        richSelectOneChoice.setPartialTriggers(ptg);
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_SUBCATEGORIA + index);
        selectItems.setValue(cmbSubCategoriaAmbiental());
        richSelectOneChoice.getChildren().add(selectItems);
        richSelectOneChoice.setValue("0");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_H);
        richPanelGroupLayout.setId(ID_PG_SUBCATEGORIA + index);
        richPanelGroupLayout.getChildren().add(richSelectOneChoice);

        return richPanelGroupLayout;
    }

    /**
     * Método para la creacion dinámica de componente: botón Eliminar.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichButton
     * @version: v1.0
     * @Fecha  : 22/09/2019
     */
    public UIComponent botonEliminar(int index) {
        RichButton richButton = new RichButton();
        richButton.setId(ID_ELIMINAR + index);
        richButton.setText(getValueFromResourceBundle(BUNDLE, "ELIMINAR"));
        richButton.setVisible(true);
        richButton.setImmediate(true);
        richButton.addActionListener(getActionListener("#{viewScope.crearClasificacionAmbiental.eliminarComponenteCA}"));
        return richButton;
    }

    //==========  Metodos de obtencion de valor ============= //

    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtSector(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_SECTOR + index);
        String valor = "-1";
        /* if (index == 1) {
            valor = JSFUtils.resolveExpression("#{bindings.IdSector.inputValue}").toString();
        } else {*/
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        // }
        logger.warning("\n===  getValorTxtSector  | index:" + index + "| SECTOR value:" + valor);
        return valor;
    }

    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtAporte(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_APORTE + index);
        String valor = "-1";
        /* if (index == 1) {
            valor = JSFUtils.resolveExpression("#{bindings.IdAporte.inputValue}").toString();
        } else {*/
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        // }
        logger.warning("\n===  getValorTxtAporte  | index:" + index + "| APORTE value:" + valor);
        return valor;

    }

    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtCategoria(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_CATEGORIA + index);
        String valor = "-1";
        /* if (index == 1) {
            valor = JSFUtils.resolveExpression("#{bindings.IdCategoria.inputValue}").toString();
        } else {*/
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        // }
        logger.warning("\n===  getValorTxtCategoria  | index:" + index + "| CATEGORIA value:" + valor);
        return valor;
    }


    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtSubCategoria(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_SUBCATEGORIA + index);
        String valor = "-1";
        /*  if (index == 1) {
            valor = JSFUtils.resolveExpression("#{bindings.IdSubcategoria.inputValue}").toString();
        } else {*/
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //  }
        logger.warning("\n===  getValorTxtSubCategoria  | index:" + index + "| SUBCATEGORIA value:" + valor);
        return valor;
    }

    /**
     * Método que obtiene el valor de ID del componente dado el componente padre e indentificador.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, String identificador
     * @return : int
     * @version: v1.0
     * @Fecha  : 20/10/2019
     */
    public int getValorIDCmbComponenteAmbiental(UIComponent parentUIComponent, String identificador) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(identificador);
        //logger.warning("att  id"+ui.getAttributes().get("id"));
        logger.warning("============   attValue" + ui.getAttributes().get("value"));
        return Integer.parseInt(ui.getAttributes().get("value").toString());
    }


    //==========  Mapeo de valores ============= //

    /**
     * Método auxiliar para mapear los valores de los componentes al VO de Clasificación Ambiental.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public void mapearValoresClasificacionAmbiental() {
        logger.warning("=============== mapearValoresClasificacionAmbiental (): mapear los valores de los componentes al VO de CA ================");
        // int index = 0;
        DCIteratorBinding voComponentes =
            ADFUtils.getDCBindingContainer().findIteratorBinding("ClasificacionAmbientalVOIterator");
        ViewObject vo = voComponentes.getViewObject();
        vo.clearCache();
        vo.executeEmptyRowSet();
        logger.warning("=============== ComponenteAmbiental  size:" + componentes.size());
       // validarComponentes();
       // imprimirComponentes();
         for (ComponenteAmbiental componente : componentes) {
            Row row = vo.createRow();

            row.setAttribute("idSector", componente.getSector());
            row.setAttribute("idAporte", componente.getAporte());
            row.setAttribute("idCategoria", componente.getCategoria());
            row.setAttribute("idSubcategoria", componente.getSubCategoria());

            vo.insertRow(row);
        }
         //valoresVO();

    }
    
    /**
     * Método que valida los valores  del formulario dinámico antesde pasar a Crear Operacion.
     * @author : S&P Solutions
     * @param  : 
     * @version: v1.0
     * @Fecha  : 24/10/2019
     */

    public ArrayList<String> validarComponentesClasificacionAmbiental() {
        logger.warning("\n=== SE EJECUTA :  validarComponentesClasificacionAmbiental () =====");
        erroresCA = new ArrayList<String>();
        erroresCA.clear();
        logger.warning("\n===  Valor de indice:" + indice);
        validarComponentes();
        if (erroresCA.size() == 0) {
            //Pasar valores a VO
            mapearValoresClasificacionAmbiental();
        }

        return erroresCA;
    }


    public void imprimirComponentes() {
        for (ComponenteAmbiental componente : componentes) {

            logger.warning("=============== ComponenteAmbiental | Sector:" + componente.getSector());
            logger.warning("=============== ComponenteAmbiental | Aporte:" + componente.getAporte());
            logger.warning("=============== ComponenteAmbiental | Categoria:" + componente.getCategoria());
            logger.warning("=============== ComponenteAmbiental | SubCategoria:" + componente.getSubCategoria());

        }
    }


    //==========  Metodos de filtrado de iteradores ============= //

    /**
     * Método de filtrado del combo  Sector ambiental.
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 18/10/2019
     */
    private void filtrarSectorAmbiental() {
        DCIteratorBinding tcaSectorAmbiental = null;
        tcaSectorAmbiental = ADFUtils.getDCBindingContainer().findIteratorBinding("TcaSectorAmbientalVOIterator");
        tcaSectorAmbiental.executeQuery();
    }

    /**
     * Método de filtrado del combo  Aporte ambiental.
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 18/10/2019
     */
    private void filtrarAporteAmbiental() {
        DCIteratorBinding tcaAporteAmbiental = null;
        tcaAporteAmbiental = ADFUtils.getDCBindingContainer().findIteratorBinding("TcaAporteAmbientalVOIterator");
        tcaAporteAmbiental.executeQuery();
    }


    /**
     * Método de filtrado del combo  Categoria ambiental.
     * @author : S&P Solutions
     * @param  : int
     * @version: v1.0
     * @Fecha  : 18/09/2019
     */
    private void filtrarCategoriaAmbiental(int idAporte) {
        DCIteratorBinding tcaCategoriaAmbiental = null;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opFiltrarCategoria = bindings.getOperationBinding("setp_idAporte");
        opFiltrarCategoria.getParamsMap().put("value", idAporte);
        opFiltrarCategoria.execute();
        if (!opFiltrarCategoria.getErrors().isEmpty()) {
            // Manejo de errores
        }

        // Ejecutamos query en iterador (puesto que cambió el parámetro del Where))
        tcaCategoriaAmbiental = ADFUtils.getDCBindingContainer().findIteratorBinding("TcaCategoriaAmbientalVOIterator");
        tcaCategoriaAmbiental.executeQuery();
    }

    /**
     * Método de filtrado del combo  SubCategoria ambiental.
     * @author : S&P Solutions
     * @param  : int
     * @version: v1.0
     * @Fecha  : 18/09/2019
     */
    private void filtrarSubCategoriaAmbiental(int idCategoria) {
        DCIteratorBinding tcaSubCategoriaAmbiental = null;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opFiltrarSubCategoria = bindings.getOperationBinding("setp_idCategoria");
        if (idCategoria < 0) {
            opFiltrarSubCategoria.getParamsMap().put("value", null);
        } else {
            opFiltrarSubCategoria.getParamsMap().put("value", idCategoria);
        }
        opFiltrarSubCategoria.execute();
        if (!opFiltrarSubCategoria.getErrors().isEmpty()) {
            // Manejo de errores
        }

        // Ejecutamos query en iterador (puesto que cambió el parámetro del Where))
        tcaSubCategoriaAmbiental =
            ADFUtils.getDCBindingContainer().findIteratorBinding("TcaSubcategoriaAmbientalVOIterator");
        tcaSubCategoriaAmbiental.executeQuery();
    }

    //==========  Metodos de actualizacion de valores ============= //

    /**
     * Método auxiliar para actualizar(reset) combo Sector.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 18/09/2019
     */
    public void actualizarCmbSectorAmbiental(int index) {
        logger.warning("\n=== actualizarCmbSectorAmbiental con indice:" + index);
        //Actualizar seleccion
        RichSelectOneChoice rsoc = (RichSelectOneChoice) obtenerUicSectorAmbiental(index);
        rsoc.resetValue();
        rsoc.getChildren().clear();
        filtrarSectorAmbiental();
        setExpressionValue("#{bindings.TcaSectorAmbientalVO.inputValue}", "");
        AdfFacesContext.getCurrentInstance().addPartialTarget(rsoc);
    }

    /**
     * Método auxiliar para actualizar(reset) combo Aporte.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 18/09/2019
     */
    public void actualizarCmbAporteAmbiental(int index) {
        logger.warning("\n=== actualizarCmbAporteAmbiental con indice:" + index);
        //Actualizar seleccion
        RichSelectOneChoice rsoc = (RichSelectOneChoice) obtenerUicAporteAmbiental(index);
        rsoc.resetValue();
        rsoc.getChildren().clear();
        filtrarAporteAmbiental();
        setExpressionValue("#{bindings.TcaAporteAmbientalVO.inputValue}", "");
        AdfFacesContext.getCurrentInstance().addPartialTarget(rsoc);
    }


    /**
     * Método auxiliar para actualizar valores de combo Categoria.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 18/09/2019
     */
    public void actualizarCmbCategoriaAmbiental(int index) {
        logger.warning("\n=== actualizarCmbCategoriaAmbiental con indice:" + index);
        List<SelectItem> list = cmbCategoriaAmbiental();
        String id = getIdSelecItem(3, index);
        actualizarComponenteSelectItem(id, list);
        //Actualizar seleccion
        RichSelectOneChoice rsoc = (RichSelectOneChoice) obtenerUicCategoriaAmbiental(index);
        rsoc.setValue("0");
        logger.warning("\n=== actualizar rsoc:" + rsoc.getId());
        AdfFacesContext.getCurrentInstance().addPartialTarget(rsoc);
    }

    /**
     * Método auxiliar para actualizar valores de combo Categoria.
     * @author : S&P Solutions
     * @param  : int
     * @return :
     * @version: v1.0
     * @Fecha  : 18/09/2019
     */
    public void actualizarCmbSubCategoriaAmbiental(int indice) {
        logger.warning("\n=== actualizarCmbSubCategoriaAmbiental con indice:" + indice);
        List<SelectItem> list = cmbSubCategoriaAmbiental();
        String id = getIdSelecItem(4, indice);
        actualizarComponenteSelectItem(id, list);
        //Actualizar seleccion
        RichSelectOneChoice rsoc = (RichSelectOneChoice) obtenerUicSubCategoriaAmbiental(indice);
        rsoc.setValue("0");
        logger.warning("\n=== actualizar rsoc:" + rsoc.getId());
        AdfFacesContext.getCurrentInstance().addPartialTarget(rsoc);
    }

    /**
     * Método auxiliar para la obtencion del indice de un componente.
     * @author : S&P Solutions
     * @param  : String
     * @return : int
     * @version: v1.0
     * @Fecha  : 18/09/2019
     */
    public int obtenerIndice(String id) {
        String aux = "";
        if (id.startsWith(ID_SECTOR)) {
            aux = id.replaceAll(ID_SECTOR, "");
        } else if (id.startsWith(ID_APORTE)) {
            aux = id.replaceAll(ID_APORTE, "");
        } else if (id.startsWith(ID_CATEGORIA)) {
            aux = id.replaceAll(ID_CATEGORIA, "");
        } else if (id.startsWith(ID_SUBCATEGORIA)) {
            aux = id.replaceAll(ID_SUBCATEGORIA, "");
        }
        return Integer.valueOf(aux);
    }

    /**
     * Método auxiliar para generar el id del componente SelecItem dado el indice y clave de componente.
     * @author : S&P Solutions
     * @param  : int
     * @param  : int
     * @return : String
     * @version: v1.0
     * @Fecha  : 19/09/2019
     */
    public String getIdSelecItem(int clave, int index) {

        String id = "";
        switch (clave) {
        case 1: //Sector
            id = ID_SI_SECTOR + index;
            break;
        case 2: //Aporte
            id = ID_SI_APORTE + index;
            break;
        case 3: //Categoria
            id = ID_SI_CATEGORIA + index;
            break;
        case 4: //SubCategoria
            id = ID_SI_SUBCATEGORIA + index;
            break;
        }

        logger.warning("\n=== getIdSelecItem | id:" + id);
        return id;
    }

    /**
     * Método auxiliar para el manejo de la visibilidad del popup de mensaje de errores al agregar un componente.
     * @author : S&P Solutions
     * @param  : boolean
     * @return :
     * @version: v1.0
     * @Fecha  : 25/09/2019
     */
    public void mostrarPopupErrorAgregarClasificacionAmbiental(boolean valor) {
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        RichPopup ui = (RichPopup) findComponent(facesCtx.getViewRoot(), "ppMsgErrAgregarCA");

        if (valor) {
            ui.show(ph);
        } else {
            ui.hide();
        }
    }


    //==========  Metodos set de valores  de combos  ============= //


    /**
     * Método que restablece el valor del combo Sector.
     * @author : S&P Solutions
     * @param  :
     * @param  :
     * @version: v1.0
     * @Fecha  : 10/10/2019
     */
    public void resetSector() {
        String id = ID_SECTOR + "1";
        logger.warning("=== resetSector =====");
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        RichSelectOneChoice ui = (RichSelectOneChoice) findComponent(facesCtx.getViewRoot(), id);
        // int valor = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.TcaSectorAmbientalVO.inputValue}").toString());
        // logger.warning("=== resetSector | Valor en binding:"+valor);
        //filtrarSectorAmbiental();
        //ui.getAttributes().put("value","0");
        setExpressionValue("#{bindings.TcaSectorAmbientalVO.inputValue}", "");
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método que restablece el valor del combo Aporte.
     * @author : S&P Solutions
     * @param  :
     * @param  :
     * @version: v1.0
     * @Fecha  : 10/10/2019
     */
    public void resetAporte() {
        String id = ID_APORTE + "1";
        logger.warning("=== resetAporte ===");
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        RichSelectOneChoice ui = (RichSelectOneChoice) findComponent(facesCtx.getViewRoot(), id);
        setExpressionValue("#{bindings.TcaAporteAmbientalVO.inputValue}", "");
        //     int valor = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.TcaAporteAmbientalVO.inputValue}").toString());
        //   logger.warning("=== resetAporte | Valor en binding:"+valor);
        //  ui.getAttributes().put("value","0");
        //filtrarAporteAmbiental();
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente Sector creado dinámicamente.
     * @author : S&P Solutions
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 19/09/2019
     */

    private List<SelectItem> cmbSectorAmbiental() {
        logger.warning("\n===== Inside cmbSectorAmbiental =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TcaSectorAmbientalVOIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idCod = id + "";
            logger.warning("\n===== cmbSectorAmbiental | ID:" + idCod + " | Descripcion:" + descripcion);
            combo.add(new SelectItem(idCod, descripcion));
        }
        return combo;
    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente Sector creado dinámicamente.
     * @author : S&P Solutions
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 19/09/2019
     */

    private List<SelectItem> cmbAporteAmbiental() {
        logger.warning("\n===== Inside cmbAporteAmbiental =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TcaAporteAmbientalVOIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idCod = id + "";
            logger.warning("\n===== cmbAporteAmbiental | ID:" + idCod + " | Descripcion:" + descripcion);
            combo.add(new SelectItem(idCod, descripcion));
        }
        return combo;
    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente Categoria creado dinámicamente.
     * @author : S&P Solutions
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 19/09/2019
     */

    private List<SelectItem> cmbCategoriaAmbiental() {
        logger.warning("\n===== Inside cmbCategoriaAmbiental =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TcaCategoriaAmbientalVOIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("IdCategoria").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idCod = id + "";
            logger.warning("\n===== cmbCategoriaAmbiental | ID:" + idCod + " | Descripcion:" + descripcion);
            combo.add(new SelectItem(idCod, descripcion));
        }
        return combo;
    }

    /**
     * Método auxiliar para obtener los valores a ser seteados al componente SubCategoria creado dinámicamente.
     * @author : S&P Solutions
     * @param  :
     * @return : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 19/09/2019
     */

    private List<SelectItem> cmbSubCategoriaAmbiental() {
        logger.warning("\n===== Inside cmbSubCategoriaAmbiental =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TcaSubcategoriaAmbientalVOIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("IdSubcategoria").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idCod = id + "";
            logger.warning("\n===== cmbSubCategoriaAmbiental | ID:" + idCod + " | Descripcion:" + descripcion);
            combo.add(new SelectItem(idCod, descripcion));
        }
        return combo;
    }

    /**
     * Método que actualiza el valor de un componente SelectItem dado el id y valores nuevos.
     * @author : S&P Solutions
     * @param  : String
     * @param  : List<SelectItem>
     * @version: v1.0
     * @Fecha  : 20/09/2019
     */
    public void actualizarComponenteSelectItem(String id, List<SelectItem> selectItems) {
        logger.warning("\n=== actualizarComponenteSelectItem con id:" + id);
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        UIComponent ui = findComponent(facesCtx.getViewRoot(), id);
        ui.getAttributes().put("value", selectItems);

        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
    }


    /**
     * Método auxiliar para obtener el componente combo Sector.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent obtenerUicSectorAmbiental(int index) {
        UIComponent ui = getSocSectorComponent(obtenerComponente(ID_PANELGRAL), index);
        return ui;
    }

    /**
     * Método auxiliar para obtener el componente combo Aporte.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent obtenerUicAporteAmbiental(int index) {
        UIComponent ui = getSocAporteComponent(obtenerComponente(ID_PANELGRAL), index);
        return ui;
    }

    /**
     * Método auxiliar para obtener el componente combo Categoria.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent obtenerUicCategoriaAmbiental(int index) {
        UIComponent ui = getSocCategoriaComponent(obtenerComponente(ID_PANELGRAL), index);
        return ui;
    }

    /**
     * Método auxiliar para obtener el componente combo Categoria.
     * @author : S&P Solutions
     * @param  : int index
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 28/05/2019
     */
    public UIComponent obtenerUicSubCategoriaAmbiental(int index) {
        UIComponent ui = getSocSubCategoriaComponent(obtenerComponente(ID_PANELGRAL), index);
        return ui;
    }


    /**
     * Método auxiliar para obtener el valor: idSector.
     * @author : S&P Solutions
     * @param  : int index
     * @return : int
     * @version: v1.0
     * @Fecha  : 21/10/2019
     */

 /*   public int obtenerValorCmbSector(int index) {
        int id = 0;

        return id;
    }*/


    //==========  Metodos de utilerias  ============= //

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
     * Método que obtiene el ID de componente (Sector) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 25/09/2019
     */
    public UIComponent getSocSectorComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SECTOR + index);
        logger.warning("\n=== getSocSectorComponent | " + ui.getId());
        return ui;
    }

    /**
     * Método que obtiene el ID de componente (Aporte) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 25/09/2019
     */
    public UIComponent getSocAporteComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_APORTE + index);
        logger.warning("\n=== getSocAporteComponent | " + ui.getId());
        return ui;
    }

    /**
     * Método que obtiene el ID de componente (Categoria) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 25/09/2019
     */
    public UIComponent getSocCategoriaComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_CATEGORIA + index);
        logger.warning("\n=== getSocCategoriaComponent | " + ui.getId());
        return ui;
    }


    /**
     * Método que obtiene el ID de componente (SubCategoria) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 25/09/2019
     */
    public UIComponent getSocSubCategoriaComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SUBCATEGORIA + index);
        logger.warning("\n=== getSocSubCategoriaComponent | " + ui.getId());
        return ui;
    }


    /**
     * Método auxiliar para leer un valor del bundle.
     * @author :
     * @param  : String
     * @param  : String
     * @return : String
     * @version:
     * @Fecha  : 23/09/2019
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
     * Método auxiliar para la acción del botón eliminar de cada componente generado dinámicamente.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 20/10/2019
     */
    public void eliminarComponenteCA(ActionEvent actionEvent) {
        String id = actionEvent.getComponent().getId();
        id = id.replaceAll(ID_ELIMINAR, "");
        int index = Integer.parseInt(id);
        eliminarComponentesCA(obtenerComponente(ID_PANELCMP), index);
        indice--;
    }

    /**
     * Método que elimina un componente definido como hijo en un componente definido como padre.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, UIComponent childUIComponent
     * @version: v1.0
     * @Fecha  : 26/09/2019
     */
    public void eliminarComponente(UIComponent parentUIComponent, UIComponent childUIComponent) {
        parentUIComponent.getChildren().remove(childUIComponent);
        AdfFacesContext.getCurrentInstance().addPartialTarget(parentUIComponent);
    }


    /**
     * Método que elimina todos los componentes hijos de un componente padre.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent
     * @version: v1.0
     * @Fecha  : 20/10/2019
     */
    public void eliminarComponentesCA(UIComponent parentUIComponent, int index) {
        //Remover panelForm
        parentUIComponent.getChildren().remove(getPanelFormComponent(parentUIComponent, index));
        //Remover separator
        parentUIComponent.getChildren().remove(getSeparatorComponent(parentUIComponent, index));
        //Remover boton eliminar
        parentUIComponent.getChildren().remove(getBtnRemoveComponent(parentUIComponent, index));
        if (index > 2) {
            ocultarBotonEliminar(obtenerComponente(ID_PANELCMP), index - 1, true);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(parentUIComponent);
    }

    /**
     * Método que obtiene el componente (Panel padre) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 20/10/2019
     */
    public UIComponent getPanelFormComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_PANELP + index);
        return ui;
    }

    /**
     * Método que obtiene el componente (Separator) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 20/10/2019
     */
    public UIComponent getSeparatorComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_SEPARATOR + index);
        return ui;
    }

    /**
     * Método que obtiene el ID de componente (botón eliminar) dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : UIComponent ui
     * @version: v1.0
     * @Fecha  : 20/10/2019
     */
    public UIComponent getBtnRemoveComponent(UIComponent parentUIComponent, int index) {
        UIComponent ui = parentUIComponent.findComponent(ID_ELIMINAR + index);
        return ui;
    }

    /**
     * Método que restablece los valores por defecto del formulario para Clasificacion ambiental.
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 18/10/2019
     */
    public void resetComponentes() {
        logger.warning("\n=== DENTRO DE REST COMPONNETES EN CA ======");
        limpiarComponentes(obtenerComponente(ID_PANELCMP));
        // limpiarFormularioInicial();
        valoresIniciales();

    }

    /**
     * Método que elimina todos los componentes hijos de un componente padre.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent
     * @version: v1.0
     * @Fecha  : 28/09/2019
     */
    public void limpiarComponentes(UIComponent parentUIComponent) {
        parentUIComponent.getChildren().clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(parentUIComponent);
    }

    /**
     * Método que reinicia los iteradores para los combos
     * @author : S&P Solutions
     * @param  :
     * @return :
     * @version: v1.0
     * @Fecha  : 28/09/2019
     */
    public void limpiarFormularioInicial() {
        //filtrarSectorAmbiental();
        actualizarCmbSectorAmbiental(1);
        // filtrarAporteAmbiental();
        actualizarCmbAporteAmbiental(1);
        filtrarCategoriaAmbiental(-1);
        filtrarSubCategoriaAmbiental(-1);
        //AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANELFORM));
    }


    /**
     * Método de utileria para la generación dinámica de un ActionListener.
     * @author : S&P Solutions
     * @param  : String el
     * @return : MethodExpressionActionListener
     * @version: v1.0
     * @Fecha  : 23/09/2019
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
     * @Fecha  : 28/09/2019
     */
    private ValueChangeListener getValueChangeListener(String el) {
        MethodExpression methodExp = getMethodExpressionForAction(el);
        return new MethodExpressionValueChangeListener(methodExp);
    }


    /**
     * Método auxiliar para la generación de ValueChangeListener empleado en la generación dinámica de Listeners.
     * @author : S&P Solutions
     * @param  : String el
     * @return : MethodExpression
     * @version: v1.0
     * @Fecha  : 23/09/2019
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
     * Método que oculta | muestra el componente botón eliminar dado el index.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @version: v1.0
     * @Fecha  : 21/09/2019
     */
    public void ocultarBotonEliminar(UIComponent parentUIComponent, int index, boolean valor) {
        UIComponent ui = parentUIComponent.findComponent(ID_ELIMINAR + index);
        ui.getAttributes().put("visible", valor);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui);
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
     * Método que limpia mensaje de errores y oculta popup.
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 03/10/2019
     */
    public void accionPopUpMsgErrores() {
        mensajeErrores = new StringBuilder();
        //Cerrar popup
        mostrarPopupErrorAgregarClasificacionAmbiental(false);
    }

    /**
     * Get FacesContext.
     * @return FacesContext
     */
    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }


    /**
     * Método auxiliar para visualizar los valores del VO de Clasificación Estratégica (mantener comentado a menos que sea necesario por trazabilidad).
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public void valoresVO() {
        logger.warning("\n=============== valoresVO (): Validar valores mapeados ===============");

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("ClasificacionAmbientalVOIterator");
        ViewObject vo = iterator.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);
        //  RowSetIterator rsi = iterator.getRowSetIterator();
        logger.warning("\n=============== valoresVO size:" + rsi.getRowCount());
        while (rsi.hasNext()) {
            Row row = rsi.next();
            logger.warning("\n=============== COMPONENTE ===============");
            logger.warning("\n=== idSector: " + row.getAttribute("idSector") != null ?
                           row.getAttribute("idSector").toString() : "");
            logger.warning("\n=== idAporte: " + row.getAttribute("idAporte") != null ?
                           row.getAttribute("idAporte").toString() : "");
            logger.warning("\n=== idCategoria: " + row.getAttribute("idCategoria") != null ?
                           row.getAttribute("idCategoria").toString() : "");
            logger.warning("\n=== idSubcategoria: " + row.getAttribute("idSubcategoria") != null ?
                           row.getAttribute("idSubcategoria").toString() : "");

        }
        rsi.closeRowSetIterator();
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

    public void setComponentes(ArrayList<ComponenteAmbiental> componentes) {
        this.componentes = componentes;
    }

    public ArrayList<ComponenteAmbiental> getComponentes() {
        return componentes;
    }

    public void setErroresCA(ArrayList<String> errores) {
        this.erroresCA = errores;
    }

    public ArrayList<String> getErroresCA() {
        return erroresCA;
    }


    public void setDummySeparatorAC(RichSeparator dummySeparatorAC) {
        this.dummySeparatorAC = dummySeparatorAC;
    }

    public RichSeparator getDummySeparatorAC() {
        valoresIniciales();
        
        return dummySeparatorAC;
    }
}
