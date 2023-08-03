package org.bcie.fenix.view.operacion;

import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;

import oracle.adf.view.rich.component.rich.RichPoll;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichSeparator;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.PollEvent;

import org.bcie.fenix.common.utils.JSFUtils;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;

import oracle.javatools.resourcebundle.BundleFactory;

public class Componentes {
    /* ==============      VARIABLES       ============= */
    private RichPoll poll1;
    private RichPanelGroupLayout pglComponentes;
    private static ADFLogger logger = null;
    /* ==============      CONSTANTES       ============= */
    private static final String OutputText_INLINESTYLE_C1 = "white-space: -moz-pre-wrap;white-space: pre;white-space: pre-wrap;word-wrap: break-word; max-width: 300px;color:#4f4f4f;font-size:12px;";
    private static final String OutputText_INLINESTYLE_C2 = "white-space: -moz-pre-wrap;white-space: pre;white-space: pre-wrap;word-wrap: break-word; max-width: 500px;color:#4f4f4f;font-size:12px;";
    private static final String OutputLabel_INLINESTYLE = "color:#091e98";
    private static final String BUNDLE = "org.bcie.fenix.view.operacion.OperacionFenixVCBundle";
    private static final String CONTENT_STYLE_V1 = "width: 300px;";
    private static final String CONTENT_STYLE_V2 =
        "width: 300px;white-space: -moz-pre-wrap;white-space: pre;white-space: pre-wrap;word-wrap: break-word;";
    private static final String CONTENT_STYLE_V5 = "width: 900px;";
    /* ==============      CONSTRUCTOR       ============= */
    public Componentes() {
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
     * @Fecha  : 20/06/2019
     */
    public void pollListener(PollEvent pollEvent) {
        logger.warning("= Llamado a poll listener =");
        try {
            crearComponentes();
            poll1.setInterval(-1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(poll1);
        } catch (Exception ex) {
            poll1.setInterval(-1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(poll1);

        }
    }

    /**
     * Método que itera sobre el resultado de la consulta de servicio y crea los componentes
     * @author : S&P Solutions
     * @param  :
     * @return :
     * @version: v1.0
     * @Fecha  : 20/06/2019
     */
    public void crearComponentes() {
        logger.warning("= Creacion de componentes dinamicos =");
        limpiarComponentes(pglComponentes);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pglComponentes);
        //Recuperar el valor del binding multisectorial
        logger.warning("= Recuperar EsMultisectorial de bindings =");
        int multisectorial = (Integer) JSFUtils.resolveExpression("#{bindings.EsMultisectorial.inputValue}");
        logger.warning("= Recuperar DetalleClasificacionEstrategicaVOIterator de bindings =");
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("DetalleClasificacionEstrategicaVOIterator");
        ViewObject vo = iterator.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);
        logger.warning("= Rows recuperados:" + rsi.getRowCount());

        //Iterar sobre VO
        int index = 1;
        while (rsi.hasNext()) {
            Row row = rsi.next();
            ComponenteBean componente = new ComponenteBean();

            componente.setNombre(row.getAttribute("nombre") != null ? row.getAttribute("nombre").toString() : "");
            componente.setDescripcion(row.getAttribute("descripcion") != null ?
                                      row.getAttribute("descripcion").toString() : "");
            componente.setPorcentaje(row.getAttribute("porcentaje") != null ?
                                     row.getAttribute("porcentaje").toString() : "");
            componente.setActividadEconomica(row.getAttribute("actividadEconomica") != null ?
                                             row.getAttribute("actividadEconomica").toString() : "");
            componente.setIniciativaEstrategica(row.getAttribute("iniciativaEstrategica") != null ?
                                                row.getAttribute("iniciativaEstrategica").toString() : "");
            componente.setCantidadPaises(row.getAttribute("cantidadPaises") != null ?
                                         row.getAttribute("cantidadPaises").toString() : "");
            componente.setAreaFocalizacion(row.getAttribute("areaFocalizacion") != null ?
                                           row.getAttribute("areaFocalizacion").toString() : "");
            componente.setEjeEstrategico(row.getAttribute("ejeEstrategico") != null ?
                                         row.getAttribute("ejeEstrategico").toString() : "");
            componente.setSectorIbcie(row.getAttribute("sectorIbcie") != null ?
                                         row.getAttribute("sectorIbcie").toString() : "");
            componente.setSubSectorIbcie(row.getAttribute("subSectorIbcie") != null ?
                                         row.getAttribute("subSectorIbcie").toString() : "");

            if (multisectorial != 1) {
                crearComponenteNoMultisectorial(1, componente);
            } else {
                crearComponenteMultisectorial(index, componente);
            }
            index++;
        }
        /*   }else{
            //Obtener primer row de VO
            Row row = rsi.getCurrentRow();
            ComponenteBean componente =new ComponenteBean();
            componente.setActividadEconomica(row.getAttribute("actividadEconomica")!=null?row.getAttribute("actividadEconomica").toString():"");
            componente.setIniciativaEstrategica(row.getAttribute("iniciativaEstrategica")!=null?row.getAttribute("iniciativaEstrategica").toString():"");
            componente.setCantidadPaises(row.getAttribute("cantidadPaises")!=null?row.getAttribute("cantidadPaises").toString():"");
            componente.setAreaFocalizacion(row.getAttribute("areaFocalizacion")!=null?row.getAttribute("areaFocalizacion").toString():"");
            componente.setEjeEstrategico(row.getAttribute("ejeEstrategico")!=null?row.getAttribute("ejeEstrategico").toString():"");

            crearComponenteNoMultisectorial(1,componente);
        }*/
        rsi.closeRowSetIterator();
        AdfFacesContext.getCurrentInstance().addPartialTarget(pglComponentes);
    }

    /**
     * Método que elimina todos los componentes hijos de un componente padre.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public void limpiarComponentes(UIComponent parentUIComponent) {
        logger.warning("= Limpiar Panel de Componentes =");
        parentUIComponent.getChildren().clear();
        AdfFacesContext.getCurrentInstance().addPartialTarget(parentUIComponent);
    }

    /**
     * Método que crea el formulario dinámico.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public void crearComponenteMultisectorial(int index, ComponenteBean componente) {
        logger.warning("= Creacion de componente Multisectorial =");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutIzq = new RichPanelGroupLayout();
        richPanelGroupLayoutIzq.setLayout("vertical");
        richPanelGroupLayoutIzq.setId("rpglIzq" + index);

        richPanelGroupLayoutIzq.getChildren().add(createNombre(index, componente.getNombre()));
        richPanelGroupLayoutIzq.getChildren().add(createDescripcion(index, componente.getDescripcion()));
        richPanelGroupLayoutIzq.getChildren().add(createPorcentaje(index, componente.getPorcentaje()));
        richPanelGroupLayoutIzq.setStyleClass(CONTENT_STYLE_V1);

        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutDer = new RichPanelGroupLayout();
        richPanelGroupLayoutDer.setLayout("vertical");
        richPanelGroupLayoutDer.setId("rpglDer" + index);
        // richPanelGroupLayoutDer.getChildren().add(createActEconPrim(index));
        richPanelGroupLayoutDer.getChildren().add(createSectorIbcie(index, componente.getSectorIbcie()));
        richPanelGroupLayoutDer.getChildren().add(createSubSectorIbcie(index, componente.getSubSectorIbcie()));
        richPanelGroupLayoutDer.getChildren().add(createActEconomica(index, componente.getActividadEconomica()));
        richPanelGroupLayoutDer.getChildren().add(createIniEstrategica(index, componente.getIniciativaEstrategica()));
        richPanelGroupLayoutDer.getChildren().add(createCantidadPaises(index, componente.getCantidadPaises()));
        richPanelGroupLayoutDer.getChildren().add(createAreaFocalizacion(index, componente.getAreaFocalizacion()));
        richPanelGroupLayoutDer.getChildren().add(createEjeEstrategio(index, componente.getEjeEstrategico()));
        richPanelGroupLayoutDer.setStyleClass(CONTENT_STYLE_V5);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutCentral = new RichPanelGroupLayout();
        richPanelGroupLayoutCentral.setLayout("vertical");
        richPanelGroupLayoutCentral.setId("rpglCen" + index);
        RichSpacer spacer = new RichSpacer();
        spacer.setId("rspacer" + index);
        spacer.setWidth("60px;");
        richPanelGroupLayoutCentral.getChildren().add(spacer);

        //Add PanelFormLayout
       /* RichPanelFormLayout richPanelFormLayout = new RichPanelFormLayout();
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
        richPanelGroupPadre.setValign("top");

        //Add LineSeparator
        RichSeparator richSeparator = new RichSeparator();
        richSeparator.setId("rls" + index);

        pglComponentes.getChildren().add(richPanelGroupPadre);
        pglComponentes.getChildren().add(richSeparator);
    }


    /**
     * Método que crea el formulario dinámico.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public void crearComponenteNoMultisectorial(int index, ComponenteBean componente) {
        logger.warning("= Creacion de componente No Multisectorial =");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutIzq = new RichPanelGroupLayout();
        richPanelGroupLayoutIzq.setLayout("vertical");
        richPanelGroupLayoutIzq.setId("rpglIzq" + index);
        richPanelGroupLayoutIzq.getChildren().add(createSectorIbcie(index, componente.getSectorIbcie()));
        richPanelGroupLayoutIzq.getChildren().add(createSubSectorIbcie(index, componente.getSubSectorIbcie()));
        richPanelGroupLayoutIzq.getChildren().add(createActEconomica(index, componente.getActividadEconomica()));
        richPanelGroupLayoutIzq.getChildren().add(createIniEstrategica(index, componente.getIniciativaEstrategica()));
        richPanelGroupLayoutIzq.getChildren().add(createCantidadPaises(index, componente.getCantidadPaises()));
        richPanelGroupLayoutIzq.setStyleClass(CONTENT_STYLE_V5);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutDer = new RichPanelGroupLayout();
        richPanelGroupLayoutDer.setLayout("vertical");
        richPanelGroupLayoutDer.setId("rpglDer" + index);
        richPanelGroupLayoutDer.getChildren().add(createAreaFocalizacion(index, componente.getAreaFocalizacion()));
        richPanelGroupLayoutDer.getChildren().add(createEjeEstrategio(index, componente.getEjeEstrategico()));
        richPanelGroupLayoutDer.setStyleClass(CONTENT_STYLE_V5);
        //Add PanelFormLayout
        RichPanelFormLayout richPanelFormLayout = new RichPanelFormLayout();
        richPanelFormLayout.setRows(1);
        richPanelFormLayout.setId("rpfl" + index);
        richPanelFormLayout.getChildren().add(richPanelGroupLayoutIzq);
        richPanelFormLayout.getChildren().add(richPanelGroupLayoutDer);

        pglComponentes.getChildren().add(richPanelFormLayout);

    }

    /**
     * Método para la creacion dinámica de componente: Nombre.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createNombre(int index, String valor) {
        //Label
      /*  RichPanelLabelAndMessage rpam = new RichPanelLabelAndMessage();
        rpam.setId("lblNom" + index);
        rpam.setLabel("Nombre");*/
            
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblNom" + index);
        String lblValor= getValueFromResourceBundle(BUNDLE,"LBL_NOMBRE");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId("rotNom" + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C1);
        richOutputText.setNoWrap(false);
      //  rpam.getChildren().add(richOutputText);
        //Add PanelGroupLayout Nombre
        RichPanelGroupLayout richPanelGroupLayoutNombre = new RichPanelGroupLayout();
        richPanelGroupLayoutNombre.setLayout("vertical");
        richPanelGroupLayoutNombre.setId("rpglNom" + index);
        richPanelGroupLayoutNombre.getChildren().add(richOutputLabel);
        richPanelGroupLayoutNombre.getChildren().add(richOutputText);

        return richPanelGroupLayoutNombre;
    }

    /**
     * Método para la creacion dinámica de componente: Descripción.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createDescripcion(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblDsc" + index);
        String lblValor= getValueFromResourceBundle(BUNDLE,"LBL_DESCRIPCION");
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
     * Método para la creacion dinámica de componente: Porcentaje.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createPorcentaje(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblPsc" + index);
        String lblValor= getValueFromResourceBundle(BUNDLE,"LBL_PORCENTAJE");
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
     * Método para la creacion dinámica de componente combo: Actividad Económica Primaria .
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public UIComponent createActEconPrim(int index) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoiceAEP = new RichSelectOneChoice();
        richSelectOneChoiceAEP.setId("socAEP" + index);
        richSelectOneChoiceAEP.setReadOnly(true);
        int option = (int) (Math.random() * 3) + 1;
        richSelectOneChoiceAEP.setValue(option);
        richSelectOneChoiceAEP.setLabel("Actividad económica primaria");
        richSelectOneChoiceAEP.setLabelStyle("width: 230px;padding-left: 20px;text-align: left;");
        //Add items
        UISelectItems selectItemsUno = new UISelectItems();
        selectItemsUno.setId("siAEP" + index);
        selectItemsUno.setValue(1);
        richSelectOneChoiceAEP.getChildren().add(selectItemsUno);

        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutAEP = new RichPanelGroupLayout();
        richPanelGroupLayoutAEP.setLayout("horizontal");
        richPanelGroupLayoutAEP.setId("rpglAEP" + index);
        richPanelGroupLayoutAEP.getChildren().add(richSelectOneChoiceAEP);

        return richPanelGroupLayoutAEP;
    }

    /**
     * Método para la creacion dinámica de componente combo: Iniciativa Estratégica.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createIniEstrategica(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblIEs" + index);
        String lblValor= getValueFromResourceBundle(BUNDLE,"LBL_INICATIVA_ESTRATEGICA");
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
     * Método para la creacion dinámica de componente combo: Cantidad de países.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 22/05/2019
     */
    public UIComponent createCantidadPaises(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblCPB" + index);
        String lblValor= getValueFromResourceBundle(BUNDLE,"LBL_PAISES_BENEFICIADOS");
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
     * Método para la creacion dinámica de componente combo: Actividad Económica.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    public UIComponent createActEconomica(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblAEc" + index);
        String lblValor= getValueFromResourceBundle(BUNDLE,"LBL_ACTIVIDAD_ECONOMICA");
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
     * Método para la creacion dinámica de componente combo: Área de focalización.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 03/06/2019
     */
    public UIComponent createAreaFocalizacion(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblAF" + index);
        String lblValor= getValueFromResourceBundle(BUNDLE,"LBL_AREA_FOCALIZACION");
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
     * Método para la creacion dinámica de componente combo: Eje estratégico.
     * @author : S&P Solutions
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 23/05/2019
     */
    public UIComponent createEjeEstrategio(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblEEs" + index);
        String lblValor= getValueFromResourceBundle(BUNDLE,"LBL_EJE_ESTRATEGICO");
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
     * Método para la creacion dinámica de componente: Sector I-BCIE.
     * @author : Kruger Innova Latbc
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 31/12/2020
     */
    public UIComponent createSectorIbcie(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblSecIbcie" + index);
        String lblValor= getValueFromResourceBundle(BUNDLE,"LBL_SECTOR_IBCIE");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId("rotSecIbcie" + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutSecIbcie = new RichPanelGroupLayout();
        richPanelGroupLayoutSecIbcie.setLayout("vertical");
        richPanelGroupLayoutSecIbcie.setId("rpglSecIbcie" + index);
        richPanelGroupLayoutSecIbcie.getChildren().add(richOutputLabel);
        richPanelGroupLayoutSecIbcie.getChildren().add(richOutputText);
        
        // Bug 1755: ocultar Sector/Sub-sector I-BCIE si no tienen un valor.
        if ((valor == null) || (valor.trim().isEmpty()))
            richPanelGroupLayoutSecIbcie.setRendered(false);

        return richPanelGroupLayoutSecIbcie;
    }
    
    /**
     * Método para la creacion dinámica de componente: Sub-sector I-BCIE.
     * @author : Kruger Innova Latbc
     * @param  :
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 31/12/2020
     */
    public UIComponent createSubSectorIbcie(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId("lblSubSecIbcie" + index);
        String lblValor= getValueFromResourceBundle(BUNDLE,"LBL_SUBSECTOR_IBCIE");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId("rotSubSecIbcie" + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayoutSubSecIbcie = new RichPanelGroupLayout();
        richPanelGroupLayoutSubSecIbcie.setLayout("vertical");
        richPanelGroupLayoutSubSecIbcie.setId("rpglSubSecIbcie" + index);
        richPanelGroupLayoutSubSecIbcie.getChildren().add(richOutputLabel);
        richPanelGroupLayoutSubSecIbcie.getChildren().add(richOutputText);
        
        // Bug 1755: ocultar Sector/Sub-sector I-BCIE si no tienen un valor.
        if ((valor == null) || (valor.trim().isEmpty()))
            richPanelGroupLayoutSubSecIbcie.setRendered(false);

        return richPanelGroupLayoutSubSecIbcie;
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
    /* ==============     GETTERS & SETTERS   ============= */
    public void setPoll1(RichPoll poll1) {
        this.poll1 = poll1;
    }

    public RichPoll getPoll1() {
        return poll1;
    }

    public void setPglComponentes(RichPanelGroupLayout pglComponentes) {
        this.pglComponentes = pglComponentes;
    }

    public RichPanelGroupLayout getPglComponentes() {
        return pglComponentes;
    }


}