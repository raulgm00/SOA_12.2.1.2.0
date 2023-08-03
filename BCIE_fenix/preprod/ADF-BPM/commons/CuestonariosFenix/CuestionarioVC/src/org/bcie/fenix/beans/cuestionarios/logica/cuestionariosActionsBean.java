package org.bcie.fenix.beans.cuestionarios.logica;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import oracle.adf.model.BindingContext;
import oracle.adf.model.OperationParameter;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCInvokeMethod;
import oracle.adf.model.binding.DCInvokeMethodDef;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.bcie.fenix.beans.cuestionarios.clases.Criterio;
import org.bcie.fenix.beans.cuestionarios.clases.Cuestionario;
import org.bcie.fenix.beans.cuestionarios.clases.Pregunta;
import org.bcie.fenix.beans.cuestionarios.clases.Principio;
import org.bcie.fenix.common.model.vo.gestioncuestionarios.GestionCuestionariosVORowImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class cuestionariosActionsBean extends CuestionariosBeans implements Serializable{
    @SuppressWarnings("compatibility:8584478568713319431")
    private static final long serialVersionUID = 1L;

    private Long idProceso;
    private Long idTareaBpm;
    private Long idEstatus;
    private Long idOperacion;
    private Integer idResponsable;
    private String instanciaProceso;

    private Boolean esSoloLectura = Boolean.FALSE;
    private String cuestionarioHTML;
    private Cuestionario cuestionario;
    
    private transient  RichOutputText htmlQuestionary;
    private transient  ViewObject vo;
    
    
    /**
    * Método asociadoo a Feature20
    * @funtion: Obtiene la cadena html asociada al componente RichOutputText (Output text) de Cuestionario de Derivados Implicitos
    * @since 16/06/2021
    * @by Krugers
    * @throws: 1 paso de ejecución, carga de constructor
    */
    public cuestionariosActionsBean() {
        super();

        try {

            logger.warning("cuestionariosActionsBean");

            esSoloLectura = (Boolean) JSFUtils.resolveExpression("#{pageFlowScope.pEsSoloLectura}");

            idOperacion =
                JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") == null ? 0L :
                Long.parseLong((JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString()));

            idTareaBpm =
                JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") == null ? 0L :
                Long.parseLong((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString()));

            idProceso =
                JSFUtils.resolveExpression("#{pageFlowScope.pIdProceso}") == null ? 0L :
                Long.parseLong((JSFUtils.resolveExpression("#{pageFlowScope.pIdProceso}").toString()));

            idEstatus =
                JSFUtils.resolveExpression("#{pageFlowScope.pIdEstatus}") == null ? 0L :
                Long.parseLong((JSFUtils.resolveExpression("#{pageFlowScope.pIdEstatus}").toString()));

            idResponsable =
                JSFUtils.resolveExpression("#{pageFlowScope.pIdResponsable}") == null ? 0 :
                Integer.parseInt((String) JSFUtils.resolveExpression("#{pageFlowScope.pIdResponsable}"));
            
            this.cuestionario = (Cuestionario) JSFUtils.resolveExpression("#{pageFlowScope.pObjetoCuestionario}");
            logger.warning(" Pre this.cuestionario " +  this.cuestionario);
            if(this.cuestionario == null) {
                this.cuestionario = new Cuestionario();  
            }
            logger.warning(" Post this.cuestionario " +  this.cuestionario);
            if (JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}") != null) {
                instanciaProceso = JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}").toString();
            }

            logger.warning("idResponsable " + idResponsable);
            logger.warning("idOperacion " + idOperacion);
            logger.warning("idProceso " + idProceso);
            logger.warning("idTareaBpm " + idTareaBpm);
            logger.warning("idEstatus " + idEstatus);
            logger.warning("instanciaProceso " + instanciaProceso);
            logger.warning("esSoloLectura " + esSoloLectura);
            
            /**
            * Método asociadoo a Feature20
            * @funtion: Obtiene la cadena html asociada al componente RichOutputText (Output text) de Cuestionario de Derivados Implicitos
            * @since 16/06/2021
            * @by Krugers
            * @throws: 2 paso de ejecución, carga de constructor
            */
            vo = getQuestionary();

            logger.warning("Size VO: " + vo.getRowCount());

            if (vo.getRowCount() > 0) {
                this.cuestionario.setExisteResultado(Boolean.TRUE);
                /**
                * Método asociadoo a Feature20
                * @funtion: Obtiene la cadena html asociada al componente RichOutputText (Output text) de Cuestionario de Derivados Implicitos
                * @since 16/06/2021
                * @by Krugers
                * @throws: 4 paso de ejecución, generación del cuestionario html
                */
                generateQuestionary(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
    * @Metodo asociadoo a Feature20
    * @funtion: Busqueda de Cuestionario de Derivados Implicitos dentro de AM (*Proyecto global conectado al modelo de datos)
    * @since 16/06/2021
    * @by Krugers
    * @throws: 3 paso de ejecución, busueda de las preguntas conforme a los parametros de entrada del TaskFlow
    */
    private ViewObject getQuestionary() {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlHierBinding obj = (JUCtrlHierBinding) bindings.findCtrlBinding("GestionCuestionariosVO1");

        ViewObject vo = obj.getViewObject();
        logger.warning("VO: " + vo);
        vo.setNamedWhereClauseParam("varIdProceso", idProceso);
        vo.setNamedWhereClauseParam("varIdEstatus", idEstatus);
        vo.setNamedWhereClauseParam("varIdOperacion", idOperacion);
        vo.executeQuery();
        return vo;
    }
    
    /**
    * Método asociadoo a Feature20
    * @funtion: Obtiene la cadena html asociada al componente RichOutputText (Output text) de Cuestionario de Derivados Implicitos
    * @since 16/06/2021
    * @by Krugers
    * @throws: 5 paso de ejecución, generación del cuestionario html
    */

    private void generateQuestionary(ViewObject vo) throws ParserConfigurationException,
                                                           TransformerConfigurationException, TransformerException {

        Document doc = generateDoc();

        Element cuestionarioElement = doc.createElement(ELEMENT_NODE_DIV); //Questionare Root
        cuestionarioElement.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_CUESTIONARIO);
        doc.appendChild(cuestionarioElement);

        Principio currentPrincipio = null;
        Criterio currentCriterio = null;
        while (vo.hasNext()) {

            GestionCuestionariosVORowImpl gestionCuestionario = (GestionCuestionariosVORowImpl) vo.next();
            Pregunta newPregunta = new Pregunta(gestionCuestionario, idResponsable, esSoloLectura);

            //newPregunta.printPregunta();
            if (esSoloLectura || newPregunta.getEsEditable() || newPregunta.getEsVisible()) {
                getCuestionario().getPreguntasMostrar().add(newPregunta);
            }

            if (newPregunta.getEsEditable()) {
                getCuestionario().getPreguntasCuestionario().add(newPregunta);
            }
        }


        if (getCuestionario().getPreguntasMostrar().size() > 0) {
            currentPrincipio =
                generatePrincipio(doc, ADFUtils.getStringFromBundle("PRINCIPIO_LABEL", BUNDLE_NAME), 0, idResponsable,
                                  Boolean.FALSE);
            cuestionarioElement.appendChild(currentPrincipio.getElementPrincipio());

            currentCriterio = generateCriterio(doc, " ", 0);
            currentPrincipio.getElementPrincipio().appendChild(currentCriterio.getElementCriterio());

            for (int i = 0; i < getCuestionario().getPreguntasMostrar().size(); i++) {
                currentCriterio.getElementPreguntas().appendChild(generatePreguntaElement(doc,
                                                                                          getCuestionario().getPreguntasMostrar().get(i)));
            }
        }

        cuestionarioHTML = getXMLStringCuestionario(cuestionarioElement);
        logger.warning("Cadena HTML" + cuestionarioHTML);
    }
    
    /**
    * @Método asociadoo a Feature20
    * @funtion: Guardar respuestas del Cuestionario de Derivados Implicitos
    * @since 16/06/2021
    * @by Krugers
    * @throws: 1 paso de ejecución de guardado/update de datos
    */
    @SuppressWarnings("unchecked")
    public String guardarRespuestasCuestionario() {
        logger.warning("Entra a  guardarRespuestasCuestionario ");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext ectx = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ectx.getRequest();

        @SuppressWarnings("unused")
        /**
        * @Método asociadoo a Feature20
        * @funtion: Guardar respuestas del Cuestionario de Derivados Implicitos
        * @since 16/06/2021
        * @by Krugers
        * @throws: 2 paso de ejecución de guardado/update de datos
        */
        Boolean cuestionarioCompletado = cuestionario.guardarRespuestasCuestionario(request);
        /**
        * @Método asociadoo a Feature20
        * @funtion: Guardar respuestas del Cuestionario de Derivados Implicitos
        * @since 16/06/2021
        * @by Krugers
        * @throws: 3 paso de ejecución Recupera la lista de respuesta
        */
        List<Map> respuestasCuestionario = cuestionario.getRespuestasList();
        
        /**
        * @Método asociadoo a Feature20
        * @funtion: Guardar respuestas del Cuestionario de Derivados Implicitos
        * @since 16/06/2021
        * @by Krugers
        * @throws: 4 paso de ejecución, se invoca el metodo del Aplication Module para ir a actualziar los datos de interes: Respuesa y Justificación con respecto al ID de la pregunta
        */
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("guardarCuestionario");
        Map operationParamsMap = operationBinding.getParamsMap();

        DCInvokeMethod method = (DCInvokeMethod) operationBinding.getOperationInfo();
        logger.warning("guardarRespuestasCuestionario: method= " + method ); 
        if (method != null) {
            DCInvokeMethodDef methodDef = method.getDef();
            logger.warning("guardarRespuestasCuestionario: operationParameters= " + methodDef ); 
            if (methodDef != null) {
                OperationParameter[] operationParameters = null;
                operationParameters = methodDef.getParameters();
                logger.warning("guardarRespuestasCuestionario: operationParameters= " + operationParameters ); 
                if (operationParameters != null) {
                    for (OperationParameter operationParameter : operationParameters) {
                        String argumentName = operationParameter.getName();
                        logger.warning("guardarRespuestasCuestionario: argumentName= " + argumentName ); 
                        Object argumentType = operationParameter.getTypeName();
                        logger.warning("guardarRespuestasCuestionario: argumentType= " + argumentType ); 
                        Object defaultValue = operationParameter.getValue();
                        if (argumentName != null) {
                            Object value = respuestasCuestionario;
                            logger.warning("guardarRespuestasCuestionario: respuestasCuestionario= " + value.toString() ); 
                            operationParamsMap.put(argumentName, value != null ? value : defaultValue);
                        }
                    }
                }
            }
        }

        @SuppressWarnings("unused")
        Boolean result = (Boolean) operationBinding.execute();
        logger.warning("guardarRespuestasCuestionario: result= " + result ); 
        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(MSG_ERROR_GUARDAR2, BUNDLE_NAME));
        }
        this.cuestionario.setPresionarGuardar(Boolean.TRUE);
        return null;
    }

    private Document generateDoc() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.newDocument();
        return doc;
    }

    public void setEsSoloLectura(Boolean esSoloLectura) {
        this.esSoloLectura = esSoloLectura;
    }

    public Boolean getEsSoloLectura() {
        return esSoloLectura;
    }
    
    public void setHtmlQuestionary(RichOutputText htmlQuestionary) {
        this.htmlQuestionary = htmlQuestionary;
    }
    
    /**
    * Método asociadoo a Feature20
    * @funtion: Obtiene la cadena html asociada al componente RichOutputText (Output text) de Cuestionario de Derivados Implicitos
    * @since 16/06/2021
    * @by Krugers
    * @throws: Ultimo paso XXX
    */

    public RichOutputText getHtmlQuestionary() {
        return htmlQuestionary;
    }

    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }

    public String getQuestionaryFileName() {
        return QUESTIONARY_FILE_NAME;
    }

    public void setCuestionarioHTML(String cuestionarioHTML) {
        this.cuestionarioHTML = cuestionarioHTML;
    }

    public String getCuestionarioHTML() {
        return cuestionarioHTML;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

}
