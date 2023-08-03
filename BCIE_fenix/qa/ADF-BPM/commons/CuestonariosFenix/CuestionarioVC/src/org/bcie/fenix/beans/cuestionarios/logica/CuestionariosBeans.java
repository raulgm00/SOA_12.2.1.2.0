package org.bcie.fenix.beans.cuestionarios.logica;

import java.io.Serializable;
import java.io.StringWriter;

import java.text.Normalizer;

import java.util.StringTokenizer;

import javax.faces.context.FacesContext;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import oracle.adf.share.logging.ADFLogger;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import org.bcie.fenix.beans.cuestionarios.clases.Criterio;
import org.bcie.fenix.beans.cuestionarios.clases.Pregunta;
import org.bcie.fenix.beans.cuestionarios.clases.Principio;
import org.bcie.fenix.common.utils.ADFUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CuestionariosBeans implements Serializable {
    @SuppressWarnings("compatibility:-5910705516608622612")
    private static final long serialVersionUID = 1L;

    public CuestionariosBeans() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    protected static ADFLogger logger = null;

    protected final String QUESTIONARY_FILE_NAME = "Cuestionario.xls";
    protected final String MSG_ERROR_GUARDAR2 = "MSG_ERROR_SAVING2";

    /**
     * el nombre del recurso
     */
    protected static final String BUNDLE_NAME = "org.bcie.fenix.beans.cuestionarios.bundle.Bundle";

    /**
     * la etiqueta del rol
     */
    protected static final String ROL_BUNDLE_PREFIX = "ROL_LABEL_";

    /**
     * el tipo de pregunta con respuesta alfanum&eacute;rica
     */
    protected static final int TEXT_QUESTION_TYPE = 1;

    /**
     * el tipo de pregunta con respuesta num&eacute;rica
     */
    protected static final int NUMERIC_QUESTION_TYPE = 2;

    /**
     * el tipo de pregunta con varias opciones como respuesta para seleccionar solo una
     */
    protected static final int SELECT_ONE_OPTION_QUESTION_TYPE = 3;

    /**
     *  el tipo de pregunta con varias opciones como respuesta para seleccionar varias
     */
    protected static final int SELECT_MULTIPLE_OPTIONS_QUESTION_TYPE = 4;

    /**
     * el nodo "article"
     */
    protected static final String ELEMENT_NODE_ARTICLE = "article";

    /**
     * el nodo "div"
     */
    protected static final String ELEMENT_NODE_DIV = "div";

    /**
     * el nodo "th"
     */
    protected static final String ELEMENT_NODE_TH = "th";

    /**
     * el nodo "tr"
     */
    protected static final String ELEMENT_NODE_TR = "tr";

    /**
     * el nodo "td"
     */
    protected static final String ELEMENT_NODE_TD = "td";

    /**
     * el nodo "table"
     */
    protected static final String ELEMENT_NODE_TABLE = "table";

    /**
     * el nodo "h2"
     */
    protected static final String ELEMENT_NODE_H2 = "h2";

    /**
     * el nodo "h4"
     */
    protected static final String ELEMENT_NODE_H4 = "h4";

    /**
     * el nodo "select"
     */
    protected static final String ELEMENT_NODE_SELECT = "select";

    /**
     * el nodo "option"
     */
    protected static final String ELEMENT_NODE_SELECT_OPTION = "option";

    /**
     * el nodo "selected"
     */
    protected static final String ELEMENT_NODE_SELECT_SELECTED = "selected";

    /**
     * valor para el atributo multiple
     */
    protected static final String ELEMENT_NODE_SELECT_MULTIPLE = "multiple";

    /**
     * el nodo "p"
     */
    protected static final String ELEMENT_NODE_P = "p";

    /**
     * el nodo "input"
     */
    protected static final String ELEMENT_NODE_INPUT = "input";

    /**
     * el nodo "textarea"
     */
    protected static final String ELEMENT_NODE_TEXTAREA = "textarea";

    /**
     * el nodo "text"
     */
    protected static final String ELEMENT_NODE_INPUT_TYPE_TEXT = "text";

    /**
     * el nodo "button"
     */
    protected static final String ELEMENT_NODE_INPUT_TYPE_BUTTOM = "button";

    /**
     * el nombre "button"
     */
    protected static final String ELEMENT_NODE_INPUT_NAME_BUTTOM = "Agregar";

    /**
     * valor para el atributo type
     */
    protected static final String ELEMENT_NODE_INPUT_TYPE_NUMBER = "number";

    /**
     * valor para el atributo disabled
     */
    protected static final String ELEMENT_NODE_INPUT_TYPE_DISABLED = "disabled";

    /**
     * valor para el atributo maxlength
     */
    protected static final String ELEMENT_NODE_INPUT_TYPE_MAXLENGTH = "maxlength";

    /**
     * valor para el atributo onclick
     */
    protected static final String ELEMENT_NODE_INPUT_TYPE_ONCLICK = "onclick";

    /**
     * valor para el atributo onKeyPress
     */
    protected static final String ELEMENT_NODE_INPUT_TYPE_ONKEYPRESS = "onKeyPress";

    /**
     * valor para el atributo class
     */
    protected static final String ELEMENT_NODE_INPUT_TYPE_CLASS = "class";

    /**
     * valor para el atributo style
     */
    protected static final String ELEMENT_NODE_INPUT_STYLE_TA = "margin: 0px; width: 290px; height: 50px; ";

    /**
     * valor para el atributo style
     */
    protected static final String ELEMENT_NODE_INPUT_STYLE = "style";

    /**
     * valor para el evento onkeyup
     */
    protected static final String EVENT_ONKEY_UP = "onkeyup";

    /**
     * valor para el atributo placeholder
     */
    protected static final String PROPIEDAD_PLACEHOLDER = "placeholder";

    /**
     * mensaje para el campo de justificacion
     */
    protected static final String MSG_DIMENSIONAR = "Presione Ctrl + flechas de direccion para dimensionar el campo";

    /**
     * valor para el atributo linea
     */
    protected static final String ELEMENT_NODE_CLASS_LINE = "linea";

    /**
     * valor para el atributo linea1
     */
    protected static final String ELEMENT_NODE_CLASS_LINE1 = "linea1";

    /**
     * el atributo "type"
     */
    protected static final String ELEMENT_NODE_ATR_TYPE = "type";

    /**
     * el atributo "id"
     */
    protected static final String ELEMENT_NODE_ATR_ID = "id";

    /**
     * el atributo "name"
     */
    protected static final String ELEMENT_NODE_ATR_NAME = "name";

    /**
     * el atributo "value"
     */
    protected static final String ELEMENT_NODE_ATR_VALUE = "value";

    /**
     * el atributo class
     */
    protected static final String ELEMENT_NODE_ATR_CLASS = "class";

    /**
     * el atributo multiple
     */
    protected static final String ELEMENT_NODE_ATR_MULTIPLE = "multiple";

    /**
     * el nombre de la clase del cuestionario
     */
    protected static final String ELEMENT_CLASS_CUESTIONARIO = "Cuestionario";

    /**
     * el nombre de la clase del m&oacute;lo sin columnas
     */
    protected static final String ELEMENT_CLASS_ARTICLE = "moduloSincolumnas";

    /**
     * el nombre de la clase de la columna 1
     */
    protected static final String ELEMENT_CLASS_COLUMN1 = "Pregunta";

    /**
     * el nombre de la clase de la columna 2
     */
    protected static final String ELEMENT_CLASS_COLUMN2 = "Opcion";

    /**
     * el nombre de la clase de la columna 3
     */
    protected static final String ELEMENT_CLASS_COLUMN3 = "Justificacion";

    /**
     * el nombre de la clase de la columna 4
     */
    protected static final String ELEMENT_CLASS_COLUMN4 = "Evidencia";


    public void writeJavaScriptToClient(String script) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        erks.addScript(fctx, script);
    }

    protected String getXMLStringCuestionario(Element cuestionarioElement) throws TransformerConfigurationException,
                                                                                  TransformerException {
        DOMSource domSource = new DOMSource(cuestionarioElement);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty("omit-xml-declaration", "yes");
        transformer.transform(domSource, result);

        return writer.toString();
    }

    protected Criterio generateCriterio(Document poDoc, String psCriterio, Integer piIdCriterio) {

        Criterio criterio = new Criterio();
        Element criterioElement = poDoc.createElement(ELEMENT_NODE_ARTICLE);
        Element titleElemnt = poDoc.createElement(ELEMENT_NODE_H4);
        Element tablePreguntasElement = generateQuestionsTable(poDoc, psCriterio);

        titleElemnt.setTextContent(psCriterio);

        criterioElement.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_ARTICLE);
        criterioElement.appendChild(titleElemnt);
        criterioElement.appendChild(tablePreguntasElement);

        criterio.setIdCriterio(piIdCriterio);
        criterio.setElementPreguntas(tablePreguntasElement);
        criterio.setElementCriterio(criterioElement);

        return criterio;
    }

    protected Element generateQuestionsTable(Document poDoc, String criterio) {
        criterio.getClass();

        Element tableElement = poDoc.createElement(ELEMENT_NODE_TABLE);
        Element tableHeadersElement = poDoc.createElement(ELEMENT_NODE_TR);

        Element headersC1Element = poDoc.createElement(ELEMENT_NODE_TH);
        headersC1Element.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_COLUMN1);

        Element headersC2Element = poDoc.createElement(ELEMENT_NODE_TH);
        headersC2Element.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_COLUMN2);

        Element headersC3Element = poDoc.createElement(ELEMENT_NODE_TH);
        headersC3Element.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_COLUMN3);

        Element headersC4Element = poDoc.createElement(ELEMENT_NODE_TH);
        headersC4Element.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_COLUMN4);


        headersC1Element.setTextContent(ADFUtils.getStringFromBundle("HEADER_LABEL_C1", BUNDLE_NAME));
        headersC2Element.setTextContent(ADFUtils.getStringFromBundle("HEADER_LABEL_C2", BUNDLE_NAME));
        headersC3Element.setTextContent(ADFUtils.getStringFromBundle("HEADER_LABEL_C3", BUNDLE_NAME));
        headersC4Element.setTextContent(ADFUtils.getStringFromBundle("HEADER_LABEL_C4", BUNDLE_NAME));

        tableHeadersElement.appendChild(headersC1Element);
        tableHeadersElement.appendChild(headersC2Element);
        tableHeadersElement.appendChild(headersC3Element);
        //tableHeadersElement.appendChild(headersC4Element);

        tableElement.appendChild(tableHeadersElement);

        return tableElement;

    }

    protected Principio generatePrincipio(Document poDoc, String psPrincipio, Integer piIdPrincipio, Integer piIdRol,
                                          Boolean pbEsAnalizarCuestionario) {
        Principio principio = new Principio();
        Element principioElement = poDoc.createElement(ELEMENT_NODE_ARTICLE);
        Element titleElemnt = poDoc.createElement(ELEMENT_NODE_H2);
        titleElemnt.setTextContent(psPrincipio);

        if (pbEsAnalizarCuestionario) {
            principioElement.setAttribute(ELEMENT_NODE_ATR_ID, "0");
        }

        principioElement.appendChild(titleElemnt);

        principio.setIdPrincipio(piIdPrincipio);
        principio.setIdRol(piIdRol);
        principio.setElementPrincipio(principioElement);

        return principio;
    }


    protected Element generatePreguntaElement(Document poDoc, Pregunta poPregunta) {
        Element preguntaElement = poDoc.createElement(ELEMENT_NODE_TR);
        Element preguntaTxtElement = poDoc.createElement(ELEMENT_NODE_TD);
        Element preguntaContainerElement = poDoc.createElement(ELEMENT_NODE_TD);
        Element preguntaJustificacionElement = poDoc.createElement(ELEMENT_NODE_TD);
        Element botonDocElement = poDoc.createElement(ELEMENT_NODE_TD);

        preguntaTxtElement.setTextContent(Normalizer.normalize(poPregunta.getTextoPregunta(), Normalizer.Form.NFC));

        Element preguntaJustificacionContentElement;
        Element btnDocElement = null;
        Element nombreDocElement = null;

        if (poPregunta.getEsEditable()) {
            preguntaJustificacionContentElement = poDoc.createElement(ELEMENT_NODE_TEXTAREA);
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_ATR_ID, poPregunta.getJustificacionHTMLId());
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_ATR_NAME,
                                                             poPregunta.getJustificacionHTMLId());
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_MAXLENGTH, "4000");
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_INPUT_STYLE, ELEMENT_NODE_INPUT_STYLE_TA);
            preguntaJustificacionContentElement.setAttribute(EVENT_ONKEY_UP, "dimensionar(this)");
            preguntaJustificacionContentElement.setAttribute(PROPIEDAD_PLACEHOLDER, MSG_DIMENSIONAR);


            if (poPregunta.getJustificacionPregunta() == null) {
                preguntaJustificacionContentElement.setTextContent("\n");
            } else {
                preguntaJustificacionContentElement.setTextContent(poPregunta.getJustificacionPregunta());
            }

            //Se genera Buttom
            btnDocElement = poDoc.createElement(ELEMENT_NODE_INPUT);
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_ID, poPregunta.getIdPregunta() + "");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_NAME, poPregunta.getIdPregunta() + "");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_TYPE, ELEMENT_NODE_INPUT_TYPE_BUTTOM);
            btnDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS, ELEMENT_NODE_CLASS_LINE1);
            btnDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_ONCLICK,
                                       "enviarArchivoCuestionario(" + poPregunta.getIdPregunta() + ")");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_VALUE, ELEMENT_NODE_INPUT_NAME_BUTTOM);

            //Se genera nombre del Doc
            if (poPregunta.getNombreDoc() != null) {
                nombreDocElement = poDoc.createElement(ELEMENT_NODE_TEXTAREA);
                nombreDocElement.setAttribute(ELEMENT_NODE_ATR_ID, "nameTA-" + poPregunta.getIdPregunta());
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED, ELEMENT_NODE_INPUT_TYPE_DISABLED);
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS, ELEMENT_NODE_CLASS_LINE);
                nombreDocElement.setTextContent(poPregunta.getNombreDoc());
            } else {
                nombreDocElement = poDoc.createElement(ELEMENT_NODE_TEXTAREA);
                nombreDocElement.setAttribute(ELEMENT_NODE_ATR_ID, "nameTA-" + poPregunta.getIdPregunta());
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED, ELEMENT_NODE_INPUT_TYPE_DISABLED);
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS, ELEMENT_NODE_CLASS_LINE);
                nombreDocElement.setTextContent("\n");
            }
        } else {

            preguntaJustificacionContentElement = poDoc.createElement(ELEMENT_NODE_TEXTAREA);
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED,
                                                             ELEMENT_NODE_INPUT_TYPE_DISABLED);
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_INPUT_STYLE, ELEMENT_NODE_INPUT_STYLE_TA);


            if (poPregunta.getJustificacionPregunta() == null) {
                preguntaJustificacionContentElement.setTextContent("\n");
            } else {
                preguntaJustificacionContentElement.setTextContent(poPregunta.getJustificacionPregunta());
            }

            //Se genera Buttom
            btnDocElement = poDoc.createElement(ELEMENT_NODE_INPUT);
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_ID, poPregunta.getIdPregunta() + "");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_NAME, poPregunta.getIdPregunta() + "");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_TYPE, ELEMENT_NODE_INPUT_TYPE_BUTTOM);
            btnDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS, ELEMENT_NODE_CLASS_LINE1);
            btnDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_ONCLICK,
                                       "enviarArchivoCuestionario(" + poPregunta.getIdPregunta() + ")");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_VALUE, ELEMENT_NODE_INPUT_NAME_BUTTOM);
            btnDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED, ELEMENT_NODE_INPUT_TYPE_DISABLED);

            //Se genera nombre del Doc
            if (poPregunta.getNombreDoc() != null) {
                nombreDocElement = poDoc.createElement(ELEMENT_NODE_TEXTAREA);
                nombreDocElement.setAttribute(ELEMENT_NODE_ATR_ID, "nameTA-" + poPregunta.getIdPregunta());
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED, ELEMENT_NODE_INPUT_TYPE_DISABLED);
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS, ELEMENT_NODE_CLASS_LINE);
                nombreDocElement.setTextContent(poPregunta.getNombreDoc());
            } else {
                nombreDocElement = poDoc.createElement(ELEMENT_NODE_TEXTAREA);
                nombreDocElement.setAttribute(ELEMENT_NODE_ATR_ID, "nameTA-" + poPregunta.getIdPregunta());
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED, ELEMENT_NODE_INPUT_TYPE_DISABLED);
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS, ELEMENT_NODE_CLASS_LINE);
                nombreDocElement.setTextContent("\n");
            }
        }

        preguntaJustificacionElement.appendChild(preguntaJustificacionContentElement);
        botonDocElement.appendChild(btnDocElement);
        botonDocElement.appendChild(nombreDocElement);

        Element preguntaComponentElement = generatePreguntaInputComponent(poDoc, poPregunta);

        if (null != preguntaComponentElement)
            preguntaContainerElement.appendChild(preguntaComponentElement);

        preguntaElement.appendChild(preguntaTxtElement);
        preguntaElement.appendChild(preguntaContainerElement);
        preguntaElement.appendChild(preguntaJustificacionElement);
        //preguntaElement.appendChild(botonDocElement);

        return preguntaElement;
    }


    private Element generatePreguntaInputComponent(Document poDocument, Pregunta poQuestion) {
        if (poDocument == null)
            throw new IllegalArgumentException("El documento no puede ser null");
        if (poQuestion == null)
            throw new IllegalArgumentException("La pregunta no puede ser null");
        Element component = null;
        if (poQuestion.getEsEditable()) {
            switch (poQuestion.getTipoPregunta()) {
            case TEXT_QUESTION_TYPE:
                component = buildTextQuestionComponent(poDocument, poQuestion);
                break;
            case NUMERIC_QUESTION_TYPE:
                component = buildNumericQuestionComponent(poDocument, poQuestion);
                break;
            case SELECT_ONE_OPTION_QUESTION_TYPE:
                component = buildSelectOneOnlyQuestionComponent(poDocument, poQuestion);
                break;
            case SELECT_MULTIPLE_OPTIONS_QUESTION_TYPE:
                component = buildSelectMultipleQuestionComponent(poDocument, poQuestion);
                break;
            default:
                throw new IllegalArgumentException(new StringBuilder().append("El tipo de pregunta ").append(poQuestion.getTipoPregunta()).append(" es incorrecto").toString());
            }
        } else {
            component = poDocument.createElement(ELEMENT_NODE_DIV);
            component.setTextContent(poQuestion.getRespuestaPregunta());
        }
        return component;
    }

    /**
     * Construye el componente HTML para la pregunta con respuesta num&eacute;rica:<br>
     * <input type="number" id="${id}" name="${name}" value="${value}"/>
     * @param poDocument el documento
     * @param poQuestion la pregunta
     * @return el componente HTML para la pregunta con respuesta num&eacute;rica
     */
    private Element buildNumericQuestionComponent(Document poDocument, Pregunta poQuestion) {
        // INPUT NUMBER
        // <input type="number" id="${id}" name="${name}" value="${value}"/>
        Element component = poDocument.createElement(ELEMENT_NODE_INPUT);
        component.setAttribute(ELEMENT_NODE_ATR_TYPE, ELEMENT_NODE_INPUT_TYPE_TEXT);
        component.setAttribute(ELEMENT_NODE_ATR_ID, poQuestion.getRepuestaHTMLId());
        component.setAttribute(ELEMENT_NODE_ATR_NAME, poQuestion.getRepuestaHTMLId());
        component.setAttribute(ELEMENT_NODE_INPUT_TYPE_MAXLENGTH, "500");
        component.setAttribute(ELEMENT_NODE_INPUT_TYPE_ONKEYPRESS, "return soloNumeros(event)");
        component.setAttribute(ELEMENT_NODE_ATR_VALUE, poQuestion.getRespuestaPregunta());
        return component;
    }

    /**
     * Construye el componente HTML para la pregunta con m&uacute;ltiples opciones y una sola respuesta
     * <select id="${id} name="${name}">
     *   <option value="volvo">Volvo</option>
     *   <option value="saab">Saab</option>
     *   <option value="mercedes">Mercedes</option>
     * <option value="audi">Audi</option>
     * </select>
     * @param poDocument el documento
     * @param poQuestion la pregunta
     * @return el componente HTML para la pregunta con m&uacute;ltiples opciones y una sola respuesta
     */
    private Element buildSelectOneOnlyQuestionComponent(Document poDocument, Pregunta poQuestion) {
        // SELECT ONLY ONE
        // <select id="${id} name="${name}">
        //   <option value="volvo">Volvo</option>
        //   <option value="saab">Saab</option>
        //   <option value="mercedes">Mercedes</option>
        // <option value="audi">Audi</option>
        // </select>
        Element component = poDocument.createElement(ELEMENT_NODE_SELECT);
        component.setAttribute(ELEMENT_NODE_ATR_ID, poQuestion.getRepuestaHTMLId());
        component.setAttribute(ELEMENT_NODE_ATR_NAME, poQuestion.getRepuestaHTMLId());
        StringTokenizer opcionesTkn = new StringTokenizer(poQuestion.getOpcionesPregunta(), ",");
        Element optionElement = poDocument.createElement(ELEMENT_NODE_SELECT_OPTION);
        optionElement.setTextContent("");
        component.appendChild(optionElement);
        while (opcionesTkn.hasMoreElements()) {
            String optionText = opcionesTkn.nextToken();
            optionElement = poDocument.createElement(ELEMENT_NODE_SELECT_OPTION);
            optionElement.setTextContent(optionText);
            if (null != poQuestion.getRespuestaPregunta() &&
                optionText.equalsIgnoreCase(poQuestion.getRespuestaPregunta())) {
                optionElement.setAttribute(ELEMENT_NODE_SELECT_SELECTED, ELEMENT_NODE_SELECT_SELECTED);
            }
            component.appendChild(optionElement);
        }
        return component;
    }

    /**
     * Construye el componente HTML para la pregunta con m&uacute;ltiples opciones y varias respuestas:<br>
     * <select id="${id} name="${name}" multiple="multiple">
     *   <option value="volvo">Volvo</option>
     *   <option value="saab">Saab</option>
     *   <option value="mercedes">Mercedes</option>
     * <option value="audi">Audi</option>
     * </select>
     * @param poDocument el documento
     * @param poQuestion la pregunta
     * @return el componente HTML para la pregunta con m&uacute;ltiples opciones y varias respuestas
     */
    private Element buildSelectMultipleQuestionComponent(Document poDocument, Pregunta poQuestion) {
        // SELECT MULTIPLE
        // <select id="${id} name="${name}" multiple="multiple">
        //   <option value="volvo">Volvo</option>
        //   <option value="saab">Saab</option>
        //   <option value="mercedes">Mercedes</option>
        // <option value="audi">Audi</option>
        // </select>
        // asumiendo que poPregunta.getOpcionesPegunta() son las opciones del select separadas por ","
        // y poPregunta.getRespuestaPregunta() son las opciones seleccionadas separadas por ","
        Element component = poDocument.createElement(ELEMENT_NODE_SELECT);
        component.setAttribute(ELEMENT_NODE_ATR_MULTIPLE, ELEMENT_NODE_SELECT_MULTIPLE);
        component.setAttribute(ELEMENT_NODE_ATR_ID, poQuestion.getRepuestaHTMLId());
        component.setAttribute(ELEMENT_NODE_ATR_NAME, poQuestion.getRepuestaHTMLId());
        String[] options =
            poQuestion.getOpcionesPregunta() != null && poQuestion.getOpcionesPregunta().trim().length() > 0 ?
            poQuestion.getOpcionesPregunta().split(",") : null;
        String[] answers =
            poQuestion.getRespuestaPregunta() != null && poQuestion.getRespuestaPregunta().trim().length() > 0 ?
            poQuestion.getRespuestaPregunta().split(",") : null;
        // crea la opcion del select
        // barre las opciones y respuestas para identificar identificar las ya seleccionadas
        for (int oidx = 0; options != null && oidx < options.length; oidx++) {
            Element option = poDocument.createElement(ELEMENT_NODE_SELECT_OPTION);
            option.setTextContent(options[oidx]);
            for (int aidx = 0; answers != null && aidx < answers.length; aidx++) {
                if (options[oidx] != null && answers[aidx] != null && options[oidx].equalsIgnoreCase(answers[aidx])) {
                    option.setAttribute(ELEMENT_NODE_SELECT_SELECTED, ELEMENT_NODE_SELECT_SELECTED);
                    break;
                }
            }
            component.appendChild(option);
        }
        return component;
    }

    private Element buildTextQuestionComponent(Document poDocument, Pregunta poQuestion) {
        // INPUT TEXT
        // <input type="text" id="${id}" name="${name}" value="${value}"/>
        Element component = poDocument.createElement(ELEMENT_NODE_TEXTAREA);
        //component.setAttribute(ELEMENT_NODE_ATR_TYPE, ELEMENT_NODE_INPUT_TYPE_TEXT);
        component.setAttribute(ELEMENT_NODE_ATR_ID, poQuestion.getRepuestaHTMLId());
        component.setAttribute(ELEMENT_NODE_ATR_NAME, poQuestion.getRepuestaHTMLId());
        component.setAttribute(ELEMENT_NODE_INPUT_TYPE_MAXLENGTH, "4000");
        component.setAttribute(ELEMENT_NODE_INPUT_STYLE, ELEMENT_NODE_INPUT_STYLE_TA);

        if (poQuestion.getJustificacionPregunta() == null) {
            component.setTextContent("\n");
        } else {
            component.setTextContent(poQuestion.getRespuestaPregunta());
        }

        //component.setAttribute(ELEMENT_NODE_ATR_VALUE, poQuestion.getRespuestaPregunta());
        return component;
    }
}
