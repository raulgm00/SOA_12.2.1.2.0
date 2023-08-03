
package org.bcie.fenix.view.ambiental;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

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

import oracle.adf.model.binding.DCDataControl;
import oracle.adf.view.rich.component.rich.RichPoll;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSeparator;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.event.PollEvent;

import org.bcie.catalogobo.Catalogo;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.am.common.FenixAM;
import org.bcie.operacionbo.ComponenteClasificacionAmbientalType;
import org.bcie.operacionbo.InsertClasificacionAmbiental;
import org.bcie.operacionbo.Operacion;
import org.bcie.operacionmo.ActualizarOperacionRequestType;
import org.bcie.operacionmo.ActualizarOperacionResponseType;

public class EditarClasificacionAmbientalBean {
    /* ==============      VARIABLES       ============= */
    private StringBuilder mensajeErroresCAEd;
    private int indice;
    private ArrayList<ComponenteAmbiental> componentesCAEd;
    private ArrayList<ComponenteAmbiental> componentesCAConsultaVO;
    private ArrayList<String> erroresCAEd;
    private static ADFLogger logger = null;
    private RichSeparator dummySeparatorACEd;
    private Boolean editable;
    
    /**
     * Creacion de variables globales para determianr si los valores deben crearse o no en a vista (dinamicamente)
     * @author : Kruger
     * @Feature: 5003 
     * @version: v2.0
     * @Fecha  : 20/10/21
     * @Step   : 0
     **/
    private Boolean categoriaAmbiental = false;
    private Boolean subcategoriaAmbiental = false;

    /* ==============      CONSTANTES       ============= */
    private static final String ID_SECTOR = "socSCTREd";
    private static final String ID_PG_SECTOR = "pglSCTREd";
    private static final String ID_SI_SECTOR = "siSCTREd";
    private static final String ID_APORTE = "socAPRTEd";
    private static final String ID_PG_APORTE = "pglAPRTEd";
    private static final String ID_SI_APORTE = "siAPRTEd";
    private static final String ID_CATEGORIA = "socCATEd";
    private static final String ID_PG_CATEGORIA = "pglCATEd";
    private static final String ID_SI_CATEGORIA = "siCATEd";
    private static final String ID_SUBCATEGORIA = "socSUBCEd";
    private static final String ID_PG_SUBCATEGORIA = "pglSUBCEd";
    private static final String ID_SI_SUBCATEGORIA = "siSUBCEd";
    private static final String OutputLabel_INLINESTYLE = "padding-left: 20px;color:#091e98";
    private static final String OutputText_INLINESTYLE_C2 =
        "white-space: -moz-pre-wrap;white-space: pre;white-space: pre-wrap;word-wrap: break-word; max-width: 500px;color:#4f4f4f;font-size:12px;padding-left: 20px;";
    private static final String LABEL_STYLE = "text-align: left;";
    private static final String LABEL_STYLE_V1 = "width: 300px;padding-left: 20px;text-align: left;";
    private static final String CONTENT_STYLE_V1 = "width: 300px;";
    private static final String CONTENT_STYLE_V2 =
        "width: 300px;white-space: -moz-pre-wrap;white-space: pre;white-space: pre-wrap;word-wrap: break-word;";
    private static final String CONTENT_STYLE_V3 = "width: 100px;";
    private static final String CONTENT_STYLE_V4 = "width: 600px;";
    private static final String CONTENT_STYLE_V5 = "width: 900px;";
    private static final String BUNDLE = "org.bcie.fenix.view.ambiental.ClasificacionAmbientalVCBundle";
    private static final String ID_ELIMINAR = "rbCAEd";
    private static final String ID_PANELGRAL = "pgGralCAEditable";
    private static final String ID_PANEL = "pglCmpCAEd";
    private static final String ID_PANELP = "pglCmpCAPEd";
    private static final String ID_PANELCMP = "pgCAEditable";
    // private static final String ID_PANELCMPNE = "pgCANoEditable";
    private static final String ID_SEPARATOR = "rlsCAEd";
    private static final String LAYOUT_V = "vertical";
    private static final String LAYOUT_H = "horizontal";
    private static final String ID_LBL_SECTOR = "lblSector";
    private static final String ID_TXT_SECTOR = "rotSector";
    private static final String ID_LBL_APORTE = "lblAporte";
    private static final String ID_TXT_APORTE = "rotAporte";
    private static final String ID_LBL_CATEGORIA = "lblCategoria";
    private static final String ID_TXT_CATEGORIA = "rotCategoria";
    private static final String ID_LBL_SUBCATEGORIA = "lblSubcategoria";
    private static final String ID_TXT_SUBCATEGORIA = "rotSubcategoria";
    private static final String ID_POPUP_MSG_ERROR = "ppMsgErrAgregarCAEd";
    private static final String ID_POPUP_MSG_ACTUALIZAR = "ppActualizarCAEd";


    /* ==============      CONSTRUCTOR       ============= */

    public EditarClasificacionAmbientalBean() {
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
    /**
     * 
     * @author : Kruger
     * @Feature: 5003 
     * @version: v2.0
     * @Fecha  : 20/10/21
     * @Step   : 1.1
     **/
    public void valoresIniciales() {
        //Set de index en 1 para manejo de componentes dinámicos
        indice = 1;
        logger.warning("==== Set de valor de  indice en 1 =========");
        //Arreglo de tipo ComponenteAmbiental (Clasificación Ambiental)
        componentesCAEd = new ArrayList<ComponenteAmbiental>();
        // Arreglo para manejo de errores
        mensajeErroresCAEd = new StringBuilder();
        erroresCAEd = new ArrayList<String>();
        editable = (Boolean) AdfFacesContext.getCurrentInstance().getPageFlowScope().get("pEditable");
        logger.warning("= Recuperar pEditable: " + editable);
        
        /**
         * CrearComponentes
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : 2
         **/
        crearComponentes();
    }

    /**
     * Método que controla el comportamiento del combo  Aporte ambiental.
     * @author : S&P Solutions
     * @param  : ValueChangeEvent valueChangeEvent
     * @version: v1.0
     * @Fecha  : 17/09/2019
     */

    public void vclAporteAmbiental(ValueChangeEvent valueChangeEvent) {
        logger.warning("=== Dentro de: vclAporteAmbiental: " + valueChangeEvent.getComponent().getId());
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
            logger.warning("===  vclAporteAmbiental | Valor: " + valor + " | idSoc: " + idSoc + " | idAporte: " + idAporte);
            // Filtrar y actualizar Categoria
            logger.warning("=== filtrarCategoriaAmbiental con  idAporte:" + idAporte);
            filtrarCategoriaAmbiental(idAporte.intValue());
            logger.warning("=== actualizarCmbCategoriaAmbiental con index:" + index);
            actualizarCmbCategoriaAmbiental(index);
            // Limpiar y actualziar combo SubCategoria
            logger.warning("=== filtrarSubCategoriaAmbiental con  idCategoria:-1");
            filtrarSubCategoriaAmbiental(-1);
            logger.warning("=== actualizarCmbSubCategoriaAmbiental con index:" + index);
            actualizarCmbSubCategoriaAmbiental(index);

            AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANELGRAL));
        } else {
            logger.warning("=== Dentro de vclAporteAmbiental - valor nulo ===");
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
        logger.warning("=== Dentro de vclCategoriaAmbiental: " + valueChangeEvent.getComponent().getId());
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
            logger.warning("===  vclCategoriaAmbiental | Valor: " + valor + " | idSoc: " + idSoc +
                           " | idCategoria: " + idCategoria);
            // Filtrar Categoria
            logger.warning("=== filtrarSubCategoriaAmbiental con  idCategoria:" + idCategoria);
            filtrarSubCategoriaAmbiental(idCategoria.intValue());
            //Actualizar componentes
            logger.warning("=== actualizarCmbSubCategoriaAmbiental con index:" + index);
            actualizarCmbSubCategoriaAmbiental(index);
        } else {
            logger.warning("=== Dentro de: vclCategoriaAmbiental - valor nulo ===");
        }

    }


    /**
     * Método que itera sobre el resultado de la consulta de servicio y crea los componentes
     * @author : S&P Solutions
     * @param  :
     * @return :
     * @version: v1.0
     * @Fecha  : 05/11/2019
     */
    /**
     * CrearComponentes
     * @author : Kruger
     * @Feature: 5003 
     * @version: v2.0
     * @Fecha  : 20/10/21
     * @Step   : 2.1
     **/
    public void crearComponentes() {
        logger.warning("=== Creacion de componentes dinamicos =");
        // limpiarComponentes(pglComponentes);

        logger.warning("= Recuperar ClasificacionAmbientalEditableVOIterator de bindings = ");
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("ClasificacionAmbientalEditableVOIterator");
        ViewObject vo = iterator.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);
        int rowCont = rsi.getRowCount();
        logger.warning("= Rows recuperados: " + rowCont);

        //Generacion de Arreglo contenedor de los registros Clasificacion Ambiental asociados a la operacion
        
        componentesCAConsultaVO = new ArrayList<ComponenteAmbiental>();
            
        //Iterar sobre VO
        while (rsi.hasNext()) {
            Row row = rsi.next();
            
            ComponenteAmbiental componente = new ComponenteAmbiental();
            
            if(row.getAttribute("IdClasificacion") != null){//Si recupera valores
                logger.warning("=== IdClasificacion: " + row.getAttribute("IdClasificacion").toString());
                
                componente.setIdSector(row.getAttribute("IdSector") != null ? row.getAttribute("IdSector").toString() :"0");
                componente.setSector(row.getAttribute("DescSector") != null ? row.getAttribute("DescSector").toString() :"0");
                componente.setIdAporte(row.getAttribute("IdAporte") != null ? row.getAttribute("IdAporte").toString() : "0");
                componente.setAporte(row.getAttribute("DescAporte") != null ? row.getAttribute("DescAporte").toString() :"0");
                componente.setIdCategoria(row.getAttribute("IdCategoria") != null ? row.getAttribute("IdCategoria").toString() : "0");
                componente.setCategoria(row.getAttribute("DescCategoria") != null ? row.getAttribute("DescCategoria").toString() : "0");
                componente.setIdSubCategoria(row.getAttribute("IdSubcategoria") != null ? row.getAttribute("IdSubcategoria").toString() : "0");
                componente.setSubCategoria(row.getAttribute("DescSubcategoria") != null ? row.getAttribute("DescSubcategoria").toString() : "0");
                
                
                logger.warning("=== IdSector: " + componente.getIdSector());
                logger.warning("=== DescSector: " + componente.getSector());
                logger.warning("=== IdAporte: " + componente.getIdAporte());
                logger.warning("=== DescAporte: " + componente.getAporte());
                logger.warning("=== IdCategoria: " + componente.getIdCategoria());
                logger.warning("=== DescCategoria: " + componente.getCategoria());
                logger.warning("=== IdSubcategoria: " + componente.getIdSubCategoria());
                logger.warning("=== DescSubcategoria: " + componente.getSubCategoria());
                
                if(componente.getCategoria().equals("NA")){
                    this.setCategoriaAmbiental(Boolean.FALSE);
                }else{
                    this.setCategoriaAmbiental(Boolean.TRUE);
                }
                              
                if( componente.getSubCategoria().equals("NA")){
                    this.setSubcategoriaAmbiental(Boolean.FALSE);
                }else{
                    this.setSubcategoriaAmbiental(Boolean.TRUE);
                }
                
                this.componentesCAConsultaVO.add(componente);
                
                logger.warning("=== Es Editable ?: " + editable);
                if (editable) {
                    if(componente.getIdSector().matches("0")){
                        logger.warning("=== La operacion no tiene CA. Crear formulario incial ===" + indice);
                        crearComponente(indice);
                    }
                    logger.warning("=== crearComponenteEditableInicial: " + indice);
                    crearComponenteEditableInicial(componente, indice);
                    
                } else {
                    logger.warning("=== crearComponenteNoEditable: " + indice);
                    /**
                     * CrearComponentes
                     * @author : Kruger
                     * @Feature: 5003 
                     * @version: v2.0
                     * @Fecha  : 20/10/21
                     * @Step   : 3.0
                     **/
                    crearComponenteNoEditable(componente, indice, this.getCategoriaAmbiental(), this.getSubcategoriaAmbiental());

                }
                AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANELCMP));
                indice++;
            
            }else{
                logger.warning("=== Sin Clasificacion Ambiental ==== "); 
                if (editable) {
                    logger.warning("===  crearComponenteInicial: " + indice);
                    crearComponente(indice);

                } else {
                    logger.warning("=== crearComponenteNoEditable vacio: ");
                    componente = new ComponenteAmbiental();

                    componente.setIdSector("");
                    componente.setSector("");
                    componente.setIdAporte("");
                    componente.setAporte("");
                    componente.setIdCategoria("");
                    componente.setCategoria("");
                    componente.setIdSubCategoria("");
                    componente.setSubCategoria("");
                    /**Validar**/
                    crearComponenteNoEditable(componente, indice, Boolean.FALSE, Boolean.FALSE);

                }
                AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANELCMP));
                indice++;
            }
        } // End de interacion de VO
        
        
        if(rsi.getRowCount()==0){
            logger.warning("=== Sin Clasificacion Ambiental ==== "); 
            if (editable) {
                logger.warning("===  crearComponenteInicial: " + indice);
                crearComponente(indice);

            } else {
                logger.warning("=== crearComponenteNoEditable vacio: ");
                ComponenteAmbiental componente = new ComponenteAmbiental();

                componente.setIdSector("");
                componente.setSector("");
                componente.setIdAporte("");
                componente.setAporte("");
                componente.setIdCategoria("");
                componente.setCategoria("");
                componente.setIdSubCategoria("");
                componente.setSubCategoria("");
                /**Valida**/
                crearComponenteNoEditable(componente, indice, Boolean.FALSE, Boolean.FALSE);

            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(obtenerComponente(ID_PANELCMP));
            indice++;
        }
        rsi.closeRowSetIterator();
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
        if (mensajeErroresCAEd.length() > 0) {
            // mostar errores
            mostrarPopup(true, ID_POPUP_MSG_ERROR);
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
        logger.warning("=== Inside validarComponentes");
        componentesCAEd = new ArrayList<ComponenteAmbiental>();
        componentesCAEd.clear();
        erroresCAEd.clear();
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
        logger.warning("=======  obtenerValoresComponentes index :" + index);
        
        ComponenteAmbiental componente = new ComponenteAmbiental();
        mensajeErroresCAEd = new StringBuilder();
        erroresCAEd = new ArrayList<String>();
        
        //Sector
        String sector = getValorTxtSector(obtenerComponente(ID_PANELGRAL), index);
        logger.warning("=======  obtenerValoresComponentes SECTOR:" + sector);
        if (sector.equals("-1")) {
            mensajeErroresCAEd.append("<p>El campo Sector es requerido.</p>");
            erroresCAEd.add("El campo Sector es requerido.");
        } else {
            componente.setSector(sector);
        }
        //Aporte
        String aporte = getValorTxtAporte(obtenerComponente(ID_PANELGRAL), index);
        logger.warning("=======  obtenerValoresComponentes APORTE:" + aporte);
        if (aporte.equals("-1")) {
            mensajeErroresCAEd.append("<p>El campo Aporte es requerido.</p>");
            erroresCAEd.add("El campo Aporte es requerido.");
        } else {
            componente.setAporte(aporte);
        }
        
        //Categoria
        /**
         * Eliminacion de Categoria Ambiental
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        String categoria;
        
        if( this.getComponentesCAConsultaVO().size() > index-1){
            categoria = this.getComponentesCAConsultaVO().get(index-1).getIdCategoria(); 
            componente.setCategoria(categoria);
            logger.warning("=======  obtenerValoresComponentes ERROR-CATEGORIA :" + categoria);
        }else{
            //Asignar valor asociado a valores null 
            //Busqeuda del Valor NA en CatalogoVO
            categoria = busquedaCategoria("NA");
            logger.warning("=======  busquedaCategoria NA-CATEGORIA :" + categoria);
            componente.setCategoria(categoria);
            
        }
        
        
        
        /**String categoria = getValorTxtCategoria(obtenerComponente(ID_PANELGRAL), index);
        if (categoria.equals("-1")) {
            logger.warning("=======  obtenerValoresComponentes ERROR-CATEGORIA:" + categoria);
            mensajeErroresCAEd.append("<p>El campo Categoría es requerido.</p>");
            erroresCAEd.add("El campo Categoría es requerido.");
        } else {
            componente.setCategoria(categoria);

        }**/
        //SubCategoria
        /**
         * Eliminacion de Subcategoria Ambiental
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        String subcategoria;
        
        if( this.getComponentesCAConsultaVO().size() > index-1){        
            subcategoria = this.getComponentesCAConsultaVO().get(index-1).getIdSubCategoria(); 
            logger.warning("=======  obtenerValoresComponentes ERROR-SUBCATEGORIA :" + subcategoria);
            componente.setSubCategoria(subcategoria);
        }else{
            subcategoria = busquedaSubCategoria("NA");
            logger.warning("=======  obtenerValoresComponentes NA-SUBCATEGORIA :" + subcategoria);
            componente.setSubCategoria(subcategoria);
            
        }
        
        
        /**String subcategoria = getValorTxtSubCategoria(obtenerComponente(ID_PANELGRAL), index);
        if (subcategoria.equals("-1")) {
            logger.warning("=======  obtenerValoresComponentes ERROR-CATEGORIA:" + categoria);
            mensajeErroresCAEd.append("<p>El campo Subcategoría es requerido.</p>");
            erroresCAEd.add("El campo Subcategoría es requerido.");
        } else {
            componente.setSubCategoria(subcategoria);
        }**/
        
        if (!(mensajeErroresCAEd.length() > 0)) {
            logger.warning("=======  obtenerValoresComponentes Componente  :" + index);    
            componentesCAEd.add(componente);
            //this.imprimirComponentes();
        }
        
    }
    
    
    public String busquedaCategoria(String value){
        String idCategoria = "";
        logger.warning("===== Inside busquedaCategoria =====");
            
            //Obtener el Iterador de Binding para poder ejecutar su Query intenro:
            DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iterator = bindings.findIteratorBinding("TcaCategoriaAmbientalAllVOIterator");
            ViewObject vo = iterator.getViewObject();
            RowSetIterator rsi = vo.createRowSetIterator(null);
            logger.warning("=============== valoresVO size:" + rsi.getRowCount());
            
            if( rsi.getRowCount() > 0){
                while (rsi.hasNext()) {
                    Row row = rsi.next();
                    String idCod = row.getAttribute("Id").toString() != null ? row.getAttribute("Id").toString() : "";
                    String descripcion = row.getAttribute("Descripcion").toString() != null ? row.getAttribute("Descripcion").toString() : "";
                    
                    logger.warning("===== busquedaCategoria | ID:" + idCod + " | Descripcion:" + descripcion);
                        
                        if(descripcion.equals(value)){
                            idCategoria = idCod;
                        }
                    }
                
                    rsi.closeRowSetIterator();
                    
            }else{
                        logger.warning("==== TcaCategoriaAmbientalAllVOIterator  vacio ====");
                        idCategoria = "6";  
            }
        return idCategoria;
    }
    
    public String busquedaSubCategoria( String value){
        String idSubCategoria = "";
        logger.warning("===== Inside busquedaSubCategoria =====");
        //Obtener el Iterador de Binding para poder ejecutar su Query intenro:
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TcaSubCategoriaAmbientalAllVOIterator");
        ViewObject vo = iterator.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);
        logger.warning("=============== valoresVO size:" + rsi.getRowCount());
        
        if( rsi.getRowCount() > 0){
            while (rsi.hasNext()) {
                Row row = rsi.next();
                String idCod = row.getAttribute("Id").toString() != null ? row.getAttribute("Id").toString() : "";
                String descripcion = row.getAttribute("Descripcion").toString() != null ? row.getAttribute("Descripcion").toString() : "";
                
                logger.warning("===== busquedaSubCategoria | ID:" + idCod + " | Descripcion:" + descripcion);
                    
                    if(descripcion.equals(value)){
                        idSubCategoria = idCod;
                    }
                }
            
                rsi.closeRowSetIterator();
                
        }else{
                    logger.warning("==== TcaSubcategoriaAmbientalVOIterator  vacio ====");
                    idSubCategoria = "33";  
        }
        return idSubCategoria;
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
        /**
        * Eliminacion de catalogo requerimiento: 5003
        * @author : Kruger
        * @Feature: 5003 
        * @version: v2.0
        * @Fecha  : 20/10/21
        * @Step   : 1
        **/
        //richPanelGroupLayout.getChildren().add(crearCategoria(index));
        //richPanelGroupLayout.getChildren().add(crearSubCategoria(index));

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
     * Método que crea el formulario dinámico.
     * @author : S&P Solutions
     * @param  : ComponenteAmbiental component
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 20/05/2019
     */
    public void crearComponenteEditableInicial(ComponenteAmbiental componente, int index) {
        //Add PanelGroupLayout
        logger.warning("=== Dentro de crearComponenteEditableInicial, indice: " + indice);
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_V);
        richPanelGroupLayout.setId(ID_PANEL + index);
        richPanelGroupLayout.setStyleClass(CONTENT_STYLE_V1);
        richPanelGroupLayout.getChildren().add(crearSectorEditable(index, componente.getIdSector()));
        richPanelGroupLayout.getChildren().add(crearAporteEditable(index, componente.getIdAporte()));
        
        /**
        * Eliminacion de catalogo requerimiento: 5003
        * @author : Kruger
        * @Feature: 5003 
        * @version: v2.0
        * @Fecha  : 20/10/21
        * @Step   : 1
        **/
        //richPanelGroupLayout.getChildren().add(crearCategoriaEditable(index, componente.getIdCategoria(), componente.getIdAporte()));
        //richPanelGroupLayout.getChildren().add(crearSubCategoriaEditable(index, componente.getIdSubCategoria(),componente.getIdCategoria()));

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
     * Método que crea el formulario dinámico.
     * @author : S&P Solutions
     * @param  : ComponenteAmbiental component
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 20/05/2019
     */
    /**
     * CrearComponentes
     * @author : Kruger
     * @Feature: 5003 
     * @version: v2.0
     * @Fecha  : 20/10/21
     * @Step   : 3.1
     **/
    public void crearComponenteNoEditable(ComponenteAmbiental componente, int index, Boolean CATEGORIA_AMBIENTAL, Boolean SUBCATEGORIA_AMBIENTAL) {
        //Add PanelGroupLayout
        logger.warning("=== Inside : crearComponenteNoEditable " );
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_V);
        richPanelGroupLayout.setId(ID_PANEL + index);
        richPanelGroupLayout.setStyleClass(CONTENT_STYLE_V1);
        richPanelGroupLayout.getChildren().add(crearSectorNoEditable(index, componente.getSector()));
        richPanelGroupLayout.getChildren().add(crearAporteNoEditable(index, componente.getAporte()));
        /**
        * Eliminacion de catalogo requerimiento: 5003
        * @author : Kruger
        * @Feature: 5003 
        * @version: v2.0
        * @Fecha  : 20/10/21
        * @Step   : 1
        **/
        //Mandar bandera para saber si debe o no renderizar los catalogos de:
        //Categoria & SUbcategoria
        if(this.getCategoriaAmbiental()){
        richPanelGroupLayout.getChildren().add(crearCategoriaNoEditable(index, componente.getCategoria()));
        }
        if(this.getSubcategoriaAmbiental()){
        richPanelGroupLayout.getChildren().add(crearSubcategoriaNoEditable(index, componente.getSubCategoria()));
        }
        //Add LineSeparator
        RichSeparator richSeparator = new RichSeparator();
        richSeparator.setId(ID_SEPARATOR + index);

        //Add PanelGroupLayout Padre
        RichPanelGroupLayout richPanelGroupLayoutPadre = new RichPanelGroupLayout();
        richPanelGroupLayoutPadre.setLayout(LAYOUT_H);
        richPanelGroupLayoutPadre.setId(ID_PANELP + index);
        richPanelGroupLayoutPadre.getChildren().add(richPanelGroupLayout);

        logger.warning("===============  CREATE FORM  NO EDITABLE ===============");
        if (index > 1) {
            obtenerComponente(ID_PANELCMP).getChildren().add(richSeparator);
        }

        obtenerComponente(ID_PANELCMP).getChildren().add(richPanelGroupLayoutPadre);


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
        /**
         * Cambio de nombre de Bundle de SECTOR a  SECTOR_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "SECTOR_AMBIENTAL"));
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
     * Método para la creacion dinámica de componente Sector Ambiental no editable.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 06/11/2019
     */
    public UIComponent crearSectorNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId(ID_LBL_SECTOR + index);
        /**
         * Cambio de nombre de Bundle de SECTOR a  SECTOR_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        String lblValor = getValueFromResourceBundle(BUNDLE, "SECTOR_AMBIENTAL");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId(ID_TXT_SECTOR + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_V);
        richPanelGroupLayout.setId(ID_PG_SECTOR + index);
        richPanelGroupLayout.getChildren().add(richOutputLabel);
        richPanelGroupLayout.getChildren().add(richOutputText);

        return richPanelGroupLayout;
    }

    /**
     * Método para la creacion dinámica de componente Sector Ambiental editable.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 20/09/2019
     */
    public UIComponent crearSectorEditable(int index, String valor) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoice = new RichSelectOneChoice();
        richSelectOneChoice.setId(ID_SECTOR + index);
        richSelectOneChoice.setAutoSubmit(true);
        richSelectOneChoice.setShowRequired(true);
        /**
         * Cambio de nombre de Bundle de SECTOR a  SECTOR_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "SECTOR_AMBIENTAL"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V1);
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_SECTOR + index);
        selectItems.setValue(cmbSectorAmbiental());
        richSelectOneChoice.getChildren().add(selectItems);
        richSelectOneChoice.setValue(valor);
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
        /**
         * Cambio de nombre de Bundle de APORTE a  APORTE_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "APORTE_AMBIENTAL"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V1);
        /**
         * Se deshailita el valuechangeListener por que ya no se usara
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        //richSelectOneChoice.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionAmbientalBean.vclAporteAmbiental}"));
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
     * Método para la creacion dinámica de componente Aporte Ambiental no editable.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 06/11/2019
     */
    public UIComponent crearAporteNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId(ID_LBL_APORTE + index);
        /**
         * Cambio de nombre de Bundle de APORTE a  APORTE_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        String lblValor = getValueFromResourceBundle(BUNDLE, "APORTE_AMBIENTAL");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId(ID_TXT_APORTE + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_V);
        richPanelGroupLayout.setId(ID_PG_APORTE + index);
        richPanelGroupLayout.getChildren().add(richOutputLabel);
        richPanelGroupLayout.getChildren().add(richOutputText);

        return richPanelGroupLayout;
    }

    /**
     * Método para la creacion dinámica de componente Aporte Ambiental.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 20/09/2019
     */
    public UIComponent crearAporteEditable(int index, String valor) {
        //Add RichSelectOneChoice
        RichSelectOneChoice richSelectOneChoice = new RichSelectOneChoice();
        richSelectOneChoice.setId(ID_APORTE + index);
        richSelectOneChoice.setAutoSubmit(true);
        richSelectOneChoice.setShowRequired(true);
        /**
         * Cambio de nombre de Bundle de APORTE a  APORTE_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "APORTE_AMBIENTAL"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V1);
        /**
         * Se deshailita el valuechangeListener por que ya no se usara
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        //richSelectOneChoice.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionAmbientalBean.vclAporteAmbiental}"));
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_APORTE + index);
        selectItems.setValue(cmbAporteAmbiental());
        richSelectOneChoice.getChildren().add(selectItems);
        richSelectOneChoice.setValue(valor);
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
     * @param  : int index
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
        //Vsisible
        //richSelectOneChoice.setVisible(true);
        /**
         * Cambio de nombre de Bundle de CATEGORIA a  CATEGORIA_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "CATEGORIA_AMBIENTAL"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V1);
        String ptg[] = { ID_APORTE + index };
        richSelectOneChoice.setPartialTriggers(ptg);
        richSelectOneChoice.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionAmbientalBean.vclCategoriaAmbiental}"));
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_CATEGORIA + index);
        selectItems.setValue(cmbCategoriaAmbiental());
        richSelectOneChoice.getChildren().add(selectItems);
        /**
         * Creacion de componente con valores NA ID = 6 (Catalogo  FENIX.TCA_CATEGORIA_AMBIENTAL)
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        richSelectOneChoice.setValue("6");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_H);
        richPanelGroupLayout.setId(ID_PG_CATEGORIA + index);
        richPanelGroupLayout.getChildren().add(richSelectOneChoice);

        return richPanelGroupLayout;
    }


    /**
     * Método para la creacion dinámica de componente Categoria Ambiental no editable.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 06/11/2019
     */
    public UIComponent crearCategoriaNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId(ID_LBL_CATEGORIA + index);
        /**
         * Cambio de nombre de Bundle de CATEGORIA a  CATEGORIA_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        String lblValor = getValueFromResourceBundle(BUNDLE, "CATEGORIA_AMBIENTAL");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId(ID_TXT_CATEGORIA + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_V);
        richPanelGroupLayout.setId(ID_PG_CATEGORIA + index);
        richPanelGroupLayout.getChildren().add(richOutputLabel);
        richPanelGroupLayout.getChildren().add(richOutputText);

        return richPanelGroupLayout;
    }

    /**
     * Método para la creacion dinámica de componente Categoria Ambiental.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 20/09/2019
     */
    public UIComponent crearCategoriaEditable(int index, String valor, String idAporte) {
        //Add RichSelectOneChoice
        logger.warning("=== valor = " + valor +", idAporte =" + idAporte + " =======================");
        RichSelectOneChoice richSelectOneChoice = new RichSelectOneChoice();
        richSelectOneChoice.setId(ID_CATEGORIA + index);
        richSelectOneChoice.setAutoSubmit(true);
        richSelectOneChoice.setShowRequired(true);
        /**
         * Cambio de nombre de Bundle de CATEGORIA a  CATEGORIA_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "CATEGORIA_AMBIENTAL"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V1);
        String ptg[] = { ID_APORTE + index };
        richSelectOneChoice.setPartialTriggers(ptg);
        richSelectOneChoice.addValueChangeListener(resolveValueChangeListener("#{viewScope.clasificacionAmbientalBean.vclCategoriaAmbiental}"));
        //Filtrar Categoria
        filtrarCategoriaAmbiental(Integer.valueOf(idAporte).intValue());
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_CATEGORIA + index);
        selectItems.setValue(cmbCategoriaAmbiental());
        richSelectOneChoice.getChildren().add(selectItems);
        richSelectOneChoice.setValue(valor);
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
     * @param  : int index
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
        //Vsisible
        //richSelectOneChoice.setVisible(true);
        
        /**
         * Cambio de nombre de Bundle de SUBCATEGORIA a  SUBCATEGORIA_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
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
        /**
         * Creacion de componente con valores NA ID = 33 (Catalogo  FENIX.TCA_SUBCATEGORIA_AMBIENTAL)
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        richSelectOneChoice.setValue("33");
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_H);
        richPanelGroupLayout.setId(ID_PG_SUBCATEGORIA + index);
        richPanelGroupLayout.getChildren().add(richSelectOneChoice);

        return richPanelGroupLayout;
    }


    /**
     * Método para la creacion dinámica de componente Subcategoria Ambiental no editable.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 06/11/2019
     */
    public UIComponent crearSubcategoriaNoEditable(int index, String valor) {
        //Label
        RichOutputLabel richOutputLabel = new RichOutputLabel();
        richOutputLabel.setId(ID_LBL_SUBCATEGORIA + index);
        /**
         * Cambio de nombre de Bundle de SUBCATEGORIA a  SUBCATEGORIA_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        String lblValor = getValueFromResourceBundle(BUNDLE, "SUBCATEGORIA_AMBIENTAL");
        richOutputLabel.setValue(lblValor);
        richOutputLabel.setInlineStyle(OutputLabel_INLINESTYLE);
        //OutputText
        RichOutputText richOutputText = new RichOutputText();
        richOutputText.setId(ID_TXT_SUBCATEGORIA + index);
        richOutputText.setValue(valor);
        richOutputText.setInlineStyle(OutputText_INLINESTYLE_C2);
        richOutputText.setNoWrap(false);
        //Add PanelGroupLayout
        RichPanelGroupLayout richPanelGroupLayout = new RichPanelGroupLayout();
        richPanelGroupLayout.setLayout(LAYOUT_V);
        richPanelGroupLayout.setId(ID_PG_SUBCATEGORIA + index);
        richPanelGroupLayout.getChildren().add(richOutputLabel);
        richPanelGroupLayout.getChildren().add(richOutputText);

        return richPanelGroupLayout;
    }


    /**
     * Método para la creacion dinámica de componente SubCategoria Ambiental editable.
     * @author : S&P Solutions
     * @param  : int index
     * @param  : String valor
     * @return : UIComponent RichPanelGroupLayout
     * @version: v1.0
     * @Fecha  : 20/09/2019
     */
    public UIComponent crearSubCategoriaEditable(int index, String valor, String idCategoria) {
        //Add RichSelectOneChoice
        logger.warning("=== valor = " + valor +", idAporte =" + idCategoria + " =======================");
        RichSelectOneChoice richSelectOneChoice = new RichSelectOneChoice();
        richSelectOneChoice.setId(ID_SUBCATEGORIA + index);
        richSelectOneChoice.setAutoSubmit(true);
        richSelectOneChoice.setShowRequired(true);
        /**
         * Cambio de nombre de Bundle de SUBCATEGORIA a  SUBCATEGORIA_AMBIENTAL
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        richSelectOneChoice.setLabel(getValueFromResourceBundle(BUNDLE, "SUBCATEGORIA_AMBIENTAL"));
        richSelectOneChoice.setLabelStyle(LABEL_STYLE_V1);
        richSelectOneChoice.setContentStyle(CONTENT_STYLE_V1);
        String ptg[] = { ID_APORTE + index + " " + ID_CATEGORIA + index };
        richSelectOneChoice.setPartialTriggers(ptg);
        //Filtrar Subcategoria
        filtrarSubCategoriaAmbiental(Integer.valueOf(idCategoria).intValue());
        //Add items
        UISelectItems selectItems = new UISelectItems();
        selectItems.setId(ID_SI_SUBCATEGORIA + index);
        selectItems.setValue(cmbSubCategoriaAmbiental());
        richSelectOneChoice.getChildren().add(selectItems);
        richSelectOneChoice.setValue(valor);
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
        richButton.addActionListener(getActionListener("#{viewScope.clasificacionAmbientalBean.eliminarComponenteCA}"));
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
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        logger.warning("===  getValorTxtSector  | index:" + index + "| SECTOR value:" + valor);
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
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        logger.warning("===  getValorTxtAporte  | index:" + index + "| APORTE value:" + valor);
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
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        logger.warning("===  getValorTxtCategoria  | index:" + index + "| CATEGORIA value:" + valor);
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
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        logger.warning("===  getValorTxtSubCategoria  | index:" + index + "| SUBCATEGORIA value:" + valor);
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
     * Método auxiliar para mapear los valores de los componentes hacia una lista de cadenas.
     * @author : S&P Solutions
     * @return : ArrayList
     * @version: v1.0
     * @Fecha  : 10/11/2019
     */
    public ActualizarOperacionRequestType obtenerValores() {
        
        logger.warning("===  Inside ActualizarOperacionRequestType ");
        Long idOperacion = Long.valueOf(AdfFacesContext.getCurrentInstance().getPageFlowScope().get("pIdOperacion").toString());
        ActualizarOperacionRequestType request = new ActualizarOperacionRequestType();
        StringBuilder valores = new StringBuilder();
        InsertClasificacionAmbiental insertClasificacionAmbiental = new InsertClasificacionAmbiental();

        for (ComponenteAmbiental componente : componentesCAEd) {
            
            
            ComponenteClasificacionAmbientalType componenteCA = new ComponenteClasificacionAmbientalType();

            componenteCA.setIdOperacion(idOperacion);
            String idSector = componente.getSector();
            String idAporte = componente.getAporte();
            String idCategoria = componente.getCategoria();
            String idSubcategoria = componente.getSubCategoria();
            /**
             * Eliminacion de Subcategoria Ambiental
             * @author : Kruger
             * @Feature: 5003 
             * @version: v2.0
             * @Fecha  : 20/10/21
             * @Step   : *
             **/
            
            logger.warning("===  Req>> idSector | " + idSector);
            logger.warning("===  Req>> idAporte | " + idAporte);
            logger.warning("===  Req>> idCategoria | " + idCategoria);
            logger.warning("===  Req>> idSubcategoria | " + idSubcategoria);
            
            //Sector
            Catalogo sectorAmbiental = new Catalogo();
            logger.warning("===  obtenerValores |idSector:" + idSector);
            sectorAmbiental.setId(new Long(idSector));
            componenteCA.setSectorAmbiental(sectorAmbiental);

            //Aporte
            Catalogo aporteAmbiental = new Catalogo();
            logger.warning("===  obtenerValores |idAporte:" + idAporte);
            aporteAmbiental.setId(new Long(idAporte));
            componenteCA.setAporteAmbiental(aporteAmbiental);
            
            
            //Categoria
            /**
             * Eliminacion de Subcategoria Ambiental
             * @author : Kruger
             * @Feature: 5003 
             * @version: v2.0
             * @Fecha  : 20/10/21
             * @Step   : *
             **/
            Catalogo categoriaAmbiental = new Catalogo();
            logger.warning("===  obtenerValores |idCategoria:" + idCategoria);
            categoriaAmbiental.setId(new Long(idCategoria));
            componenteCA.setCategoriaAmbiental(categoriaAmbiental);

            //SubCategoria
            /**
             * Eliminacion de Subcategoria Ambiental
             * @author : Kruger
             * @Feature: 5003 
             * @version: v2.0
             * @Fecha  : 20/10/21
             * @Step   : *
             **/
            Catalogo subCategoriaAmbiental = new Catalogo();
            logger.warning("===  obtenerValores |idSubcategoria:" + idSubcategoria);
            subCategoriaAmbiental.setId(new Long(idSubcategoria));
            componenteCA.setSubCategoriaAmbiental(subCategoriaAmbiental);

            insertClasificacionAmbiental.getComponenteClasificacionAmbiental().add(componenteCA);
        }

        //Paso de velores a Request
        Operacion operacion = new Operacion();
        logger.warning("=== idOperacion en operacion ");
        operacion.setIdOperacion(idOperacion);
        logger.warning("=== setInsertClasificacionAmbiental en operacion ");
        operacion.setInsertClasificacionAmbiental(insertClasificacionAmbiental);
        logger.warning("=== setOperacion en request ");
        request.setOperacion(operacion);
        logger.warning("=== setStatus en request ");
        request.setStatus("editarCA");

        return request;
    }

    /**
     * Método auxiliar para mapear los valores de los componentes al VO de Clasificación Ambiental.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 21/05/2019
     */
    public void mapearValoresClasificacionAmbiental() {
        logger.warning("=============== mapearValoresClasificacionAmbiental (): mapear los valores de los componentes a ActualizarClasificacionAmbientalVO de CA ================");
        validarComponentes();
        if (mensajeErroresCAEd.length() > 0) {
            // mostar errores
            mostrarPopup(true, ID_POPUP_MSG_ERROR);
        } else {

            DCIteratorBinding voComponentes =
                ADFUtils.getDCBindingContainer().findIteratorBinding("ActualizarClasificacionAmbientalVO1Iterator");


            ViewObject vo = voComponentes.getViewObject();
            if (vo != null) {
                vo.clearCache();
                vo.executeEmptyRowSet();
                logger.warning("=============== Componente Ambiental Editable  size:" + componentesCAEd.size());
                // validarComponentes();
                // imprimirComponentes();
                for (ComponenteAmbiental componente : componentesCAEd) {
                    Row row = vo.createRow();

                    row.setAttribute("idSector", componente.getSector());
                    row.setAttribute("idAporte", componente.getAporte());
                    /**Validar**/
                    row.setAttribute("idCategoria", componente.getCategoria());
                    row.setAttribute("idSubcategoria", componente.getSubCategoria());

                    vo.insertRow(row);
                }
            }
            valoresVO();

        }


    }

    /**
     * Método que valida los valores  del formulario dinámico antesde pasar a Crear Operacion.
     * @author : S&P Solutions
     * @param  :
     * @version: v1.0
     * @Fecha  : 24/10/2019
     */

    public ArrayList<String> validarComponentesClasificacionAmbiental() {
        logger.warning("=== SE EJECUTA :  validarComponentesClasificacionAmbiental () =====");
        erroresCAEd = new ArrayList<String>();
        erroresCAEd.clear();
        logger.warning("===  Valor de indice:" + indice);
        validarComponentes();
        if (erroresCAEd.size() == 0) {
            //Pasar valores a VO
            mapearValoresClasificacionAmbiental();
        }

        return erroresCAEd;
    }


    public void imprimirComponentes() {
        
        for (ComponenteAmbiental componente : componentesCAEd) {

            logger.warning("=============== ComponenteAmbiental | Sector:" + componente.getSector());
            logger.warning("=============== ComponenteAmbiental | Aporte:" + componente.getAporte());
            logger.warning("=============== ComponenteAmbiental | Categoria:" + componente.getCategoria());
            logger.warning("=============== ComponenteAmbiental | SubCategoria:" + componente.getSubCategoria());

        }
    }


    //==========  Metodos de actualizacion de valores de clasificacion ============= //

    /**
     * Método que valida los valores  del formulario dinámico antes de ejecutar la actualizacion de clasificacion ambiental.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 24/10/2019
     */
    public void actualizarClasificacion(ActionEvent actionEvent) {
        validarComponentes();
        
        if (mensajeErroresCAEd.length() > 0) {
            // mostar errores
            mostrarPopup(true, ID_POPUP_MSG_ERROR);
        } else {
            mostrarPopup(true, ID_POPUP_MSG_ACTUALIZAR);
        }
    }

    //

    /**
     * Método que actualiza los valores de clasificacion ambiental.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 24/10/2019
     */
    public void aceptarActualizarClasificacionAmbiental(ActionEvent actionEvent) {
        //Mapear valores
        
        try{
        logger.warning("== Inicia metodo aceptarActualizarClasificacionAmbiental ==");
        ActualizarOperacionRequestType request = null;
        ActualizarOperacionResponseType response = new ActualizarOperacionResponseType();
        HashMap<String, ActualizarOperacionResponseType> respuestaServicio = null;
        request = obtenerValores();
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding opbActualizar = bindings.getOperationBinding("actualizarClasificacionAmbiental");
        opbActualizar.getParamsMap().put("request", request);
        opbActualizar.execute();
        if (!opbActualizar.getErrors().isEmpty()) {
            // Manejo de errores
        } else if (opbActualizar.getResult() != null) {
            respuestaServicio = (HashMap<String, ActualizarOperacionResponseType>) opbActualizar.getResult();
            response = respuestaServicio.get("response");
            if (response.getResultado() != null) {
                JSFUtils.addFacesInformationMessage(response.getResultado().getMessage().toString());
                mostrarPopup(false, ID_POPUP_MSG_ACTUALIZAR);
            } else {
                mostrarPopup(false, ID_POPUP_MSG_ACTUALIZAR);
                JSFUtils.addFacesErrorMessage("Error al actualizar la informaci\u00F3n de Clasificaci\u00F3n Ambiental");
            }
        } else {
            mostrarPopup(false, ID_POPUP_MSG_ACTUALIZAR);
            //MENSAJE_ERROR_ACTUALIZAR_OPERACION
            JSFUtils.addFacesErrorMessage("Error al actualizar la informaci\u00F3n de Clasificaci\u00F3n Ambiental");
        }
        }catch(Exception e){
            System.out.println("===================================================================");
            e.printStackTrace();
            System.out.println("====================================================================");
            mostrarPopup(false, ID_POPUP_MSG_ACTUALIZAR);
            //MENSAJE_ERROR_ACTUALIZAR_OPERACION
            JSFUtils.addFacesErrorMessage("Error al actualizar la informaci\u00F3n de Clasificaci\u00F3n Ambiental, el servicio fallo, favor de contactar al administrador.");
        }
        
    }

    /**
     * Método que valida los valores  del formulario dinámico antesde pasar a Crear Operacion.
     * @author : S&P Solutions
     * @param  : ActionEvent actionEvent
     * @version: v1.0
     * @Fecha  : 24/10/2019
     */
    public void cancelarActualizarClasificacionAmbiental(ActionEvent actionEvent) {
        //Cerrar popup
        mostrarPopup(false, ID_POPUP_MSG_ACTUALIZAR);
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
        logger.warning("=== actualizarCmbSectorAmbiental con indice:" + index);
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
        logger.warning("=== actualizarCmbAporteAmbiental con indice:" + index);
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
        logger.warning("=== actualizarCmbCategoriaAmbiental con indice:" + index);
        List<SelectItem> list = cmbCategoriaAmbiental();
        String id = getIdSelecItem(3, index);
        actualizarComponenteSelectItem(id, list);
        //Actualizar seleccion
        RichSelectOneChoice rsoc = (RichSelectOneChoice) obtenerUicCategoriaAmbiental(index);
        rsoc.setValue("0");
        logger.warning("=== actualizar rsoc:" + rsoc.getId());
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
        logger.warning("=== actualizarCmbSubCategoriaAmbiental con indice:" + indice);
        List<SelectItem> list = cmbSubCategoriaAmbiental();
        String id = getIdSelecItem(4, indice);
        actualizarComponenteSelectItem(id, list);
        //Actualizar seleccion
        RichSelectOneChoice rsoc = (RichSelectOneChoice) obtenerUicSubCategoriaAmbiental(indice);
        rsoc.setValue("0");
        logger.warning("=== actualizar rsoc:" + rsoc.getId());
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

        logger.warning("=== getIdSelecItem | id:" + id);
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
    public void mostrarPopup(boolean valor, String idPopup) {
        RichPopup.PopupHints ph = new RichPopup.PopupHints();
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        RichPopup ui = (RichPopup) findComponent(facesCtx.getViewRoot(), idPopup);

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
        logger.warning("===== Inside cmbSectorAmbiental =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TcaSectorAmbientalVOIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idCod = id + "";
            logger.warning("===== cmbSectorAmbiental | ID:" + idCod + " | Descripcion:" + descripcion);
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
        logger.warning("===== Inside cmbAporteAmbiental =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TcaAporteAmbientalVOIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("Id").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idCod = id + "";
            logger.warning("===== cmbAporteAmbiental | ID:" + idCod + " | Descripcion:" + descripcion);
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
        logger.warning("===== Inside cmbCategoriaAmbiental =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TcaCategoriaAmbientalVOIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("IdCategoria").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idCod = id + "";
            logger.warning("===== cmbCategoriaAmbiental | ID:" + idCod + " | Descripcion:" + descripcion);
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
        logger.warning("===== Inside cmbSubCategoriaAmbiental =====");
        List<SelectItem> combo = new ArrayList<SelectItem>();

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("TcaSubcategoriaAmbientalVOIterator");
        RowSetIterator rsiRowsCat = iterator.getRowSetIterator();
        Row[] rowsCat = rsiRowsCat.getAllRowsInRange();
        for (Row row : rowsCat) {
            int id = Integer.parseInt(row.getAttribute("IdSubcategoria").toString());
            String descripcion = row.getAttribute("Descripcion").toString();
            String idCod = id + "";
            logger.warning("===== cmbSubCategoriaAmbiental | ID:" + idCod + " | Descripcion:" + descripcion);
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
        logger.warning("=== actualizarComponenteSelectItem con id:" + id);
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
        logger.warning("=== getSocSectorComponent | " + ui.getId());
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
        logger.warning("=== getSocAporteComponent | " + ui.getId());
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
        logger.warning("=== getSocCategoriaComponent | " + ui.getId());
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
        logger.warning("=== getSocSubCategoriaComponent | " + ui.getId());
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
        logger.warning("=== Inside getValueFromResourceBundle: ");
        logger.warning("=== resourceBundleName =  " + resourceBundleName );
        logger.warning("=== key = " + key );
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
        logger.warning("=== DENTRO DE REST COMPONNETES EN CA ======");
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
        mensajeErroresCAEd = new StringBuilder();
        //Cerrar popup
        mostrarPopup(false, ID_POPUP_MSG_ERROR);
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
        logger.warning("=============== valoresVO (): Validar valores mapeados ===============");

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = bindings.findIteratorBinding("ActualizarClasificacionAmbientalVO1Iterator");
        ViewObject vo = iterator.getViewObject();
        RowSetIterator rsi = vo.createRowSetIterator(null);
        //  RowSetIterator rsi = iterator.getRowSetIterator();
        logger.warning("=============== valoresVO size:" + rsi.getRowCount());
        while (rsi.hasNext()) {
            Row row = rsi.next();
            logger.warning("=============== COMPONENTE ===============");
            logger.warning("=== idSector: " + row.getAttribute("idSector") != null ?
                           row.getAttribute("idSector").toString() : "");
            logger.warning("=== idAporte: " + row.getAttribute("idAporte") != null ?
                           row.getAttribute("idAporte").toString() : "");
            logger.warning("=== idCategoria: " + row.getAttribute("idCategoria") != null ?
                           row.getAttribute("idCategoria").toString() : "");
            logger.warning("=== idSubcategoria: " + row.getAttribute("idSubcategoria") != null ?
                           row.getAttribute("idSubcategoria").toString() : "");

        }
        rsi.closeRowSetIterator();
    }


    /* ==============     GETTERS & SETTERS   ============= */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }

    public void setDummySeparatorACEd(RichSeparator dummySeparatorACEd) {
        this.dummySeparatorACEd = dummySeparatorACEd;
    }
    
    /**
     * Carga de parametros dento del render de la pagina *.jspx
     * Catalogo: Categoria / Subcategoria
     * @author : Kruger
     * @Feature: 5003 
     * @version: v2.0
     * @Fecha  : 20/10/21
     * @Step   : 1
     **/
    public RichSeparator getDummySeparatorACEd() {
        //Boolean banEstado = Boolean.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pEstadoBpm}").toString());
        // if (banEstado) {
        logger.warning("=============== Ejecuta valoresIniciales()   ====");
        valoresIniciales();
        // }
        return dummySeparatorACEd;
    }

    public void setMensajeErroresCAEd(StringBuilder mensajeErroresCAEd) {
        this.mensajeErroresCAEd = mensajeErroresCAEd;
    }

    public StringBuilder getMensajeErroresCAEd() {
        return mensajeErroresCAEd;
    }

    public void setComponentesCAEd(ArrayList<ComponenteAmbiental> componentesCAEd) {
        this.componentesCAEd = componentesCAEd;
    }

    public ArrayList<ComponenteAmbiental> getComponentesCAEd() {
        return componentesCAEd;
    }

    public void setErroresCAEd(ArrayList<String> erroresCAEd) {
        this.erroresCAEd = erroresCAEd;
    }

    public ArrayList<String> getErroresCAEd() {
        return erroresCAEd;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getEditable() {
        return editable;
    }
    
    /**
     * Creacion de variables globales para determianr si los valores deben crearse o no en a vista (dinamicamente)
     * @author : Kruger
     * @Feature: 5003 
     * @version: v2.0
     * @Fecha  : 20/10/21
     * @Step   : 0
     **/
    public void setCategoriaAmbiental(Boolean categoriaAmbiental) {
        this.categoriaAmbiental = categoriaAmbiental;
    }

    public Boolean getCategoriaAmbiental() {
        return categoriaAmbiental;
    }

    public void setSubcategoriaAmbiental(Boolean subcategoriaAmbiental) {
        this.subcategoriaAmbiental = subcategoriaAmbiental;
    }

    public Boolean getSubcategoriaAmbiental() {
        return subcategoriaAmbiental;
    }


    public void setComponentesCAConsultaVO(ArrayList<ComponenteAmbiental> componentesCAConsultaVO) {
        this.componentesCAConsultaVO = componentesCAConsultaVO;
    }

    public ArrayList<ComponenteAmbiental> getComponentesCAConsultaVO() {
        return componentesCAConsultaVO;
    }
}
