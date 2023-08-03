package org.bcie.fenix.view.elegibilidad;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.io.StringWriter;

import java.text.Normalizer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;

import javax.xml.transform.stream.StreamResult;

import oracle.adf.share.logging.ADFLogger;

import org.apache.myfaces.trinidad.model.UploadedFile;

import org.bcie.fenix.common.utils.ADFUtils;

import org.bcie.fenix.common.utils.JSFUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CuestionarioElegibilidadBean implements Serializable
{
    @SuppressWarnings("compatibility:3763926109164940977")
    private static final long serialVersionUID = 1L;   
    
    
    /**
     * el nombre del recurso
     */
    private static final String BUNDLE_NAME = "org.bcie.fenix.view.elegibilidad.CuestionarioElegibilidadVCBundle";
    /**
     * la etiqueta del rol
     */
    private static final String ROL_BUNDLE_PREFIX = "ROL_LABEL_";
    /**
     * el tipo de pregunta con respuesta alfanum&eacute;rica
     */
    private static final int TEXT_QUESTION_TYPE = 1;
    /**
     * el tipo de pregunta con respuesta num&eacute;rica
     */
    private static final int NUMERIC_QUESTION_TYPE = 2;
    /**
     * el tipo de pregunta con varias opciones como respuesta para seleccionar solo una
     */
    private static final int SELECT_ONE_OPTION_QUESTION_TYPE = 3;
    /**
     *  el tipo de pregunta con varias opciones como respuesta para seleccionar varias
     */
    private static final int SELECT_MULTIPLE_OPTIONS_QUESTION_TYPE = 4;
    /**
     * el nodo "article"
     */
    private static final String ELEMENT_NODE_ARTICLE = "article";
    /**
     * el nodo "div"
     */
    private static final String ELEMENT_NODE_DIV = "div";
    /**
     * el nodo "th"
     */
    private static final String ELEMENT_NODE_TH = "th";
    /**
     * el nodo "tr"
     */
    private static final String ELEMENT_NODE_TR = "tr";
    /**
     * el nodo "td"
     */
    private static final String ELEMENT_NODE_TD = "td";
    /**
     * el nodo "table"
     */
    private static final String ELEMENT_NODE_TABLE = "table";
    /**
     * el nodo "h2"
     */
    private static final String ELEMENT_NODE_H2 = "h2";
    /**
     * el nodo "h4"
     */
    private static final String ELEMENT_NODE_H4 = "h4";
    /**
     * el nodo "select"
     */
    private static final String ELEMENT_NODE_SELECT = "select";
    /**
     * el nodo "option"
     */
    private static final String ELEMENT_NODE_SELECT_OPTION = "option";
    /**
     * el nodo "selected"
     */
    private static final String ELEMENT_NODE_SELECT_SELECTED = "selected";
    /**
     * valor para el atributo multiple
     */
    private static final String ELEMENT_NODE_SELECT_MULTIPLE = "multiple";
    /**
     * el nodo "p"
     */
    private static final String ELEMENT_NODE_P = "p";
    /**
     * el nodo "input"
     */
    private static final String ELEMENT_NODE_INPUT = "input";
    /**
     * el nodo "textarea"
     */
    private static final String ELEMENT_NODE_TEXTAREA = "textarea";    
    /**
     * el nodo "text"
     */
    private static final String ELEMENT_NODE_INPUT_TYPE_TEXT = "text";
    /**
     * el nodo "button"
     */
    private static final String ELEMENT_NODE_INPUT_TYPE_BUTTOM = "button";
    /**
     * el nombre "button"
     */
    private static final String ELEMENT_NODE_INPUT_NAME_BUTTOM = "Agregar";
    /**
     * valor para el atributo type 
     */
    private static final String ELEMENT_NODE_INPUT_TYPE_NUMBER = "number";
    /**
     * valor para el atributo disabled 
     */
    private static final String ELEMENT_NODE_INPUT_TYPE_DISABLED = "disabled";
    /**
     * valor para el atributo maxlength 
     */
    private static final String ELEMENT_NODE_INPUT_TYPE_MAXLENGTH = "maxlength";
    /**
     * valor para el atributo onclick 
     */
    private static final String ELEMENT_NODE_INPUT_TYPE_ONCLICK = "onclick";
    /**
     * valor para el atributo onKeyPress 
     */
    private static final String ELEMENT_NODE_INPUT_TYPE_ONKEYPRESS = "onKeyPress";    
    /**
     * valor para el atributo class 
     */
    private static final String ELEMENT_NODE_INPUT_TYPE_CLASS = "class";
    /**
     * valor para el atributo style 
     */
    private static final String ELEMENT_NODE_INPUT_STYLE_TA = "margin: 0px; width: 290px; height: 50px; ";
    /**
     * valor para el atributo style 
     */
    private static final String ELEMENT_NODE_INPUT_STYLE= "style";
    /**
     * valor para el evento onkeyup 
     */
    private static final String EVENT_ONKEY_UP= "onkeyup";        
    /**
     * valor para el atributo placeholder 
     */
    private static final String PROPIEDAD_PLACEHOLDER= "placeholder"; 
    /**
     * mensaje para el campo de justificacion
     */
    private static final String MSG_DIMENSIONAR = "Presione Ctrl + flechas de direccion para dimensionar el campo";
    /**
     * valor para el atributo linea 
     */
    private static final String ELEMENT_NODE_CLASS_LINE = "linea";
    /**
     * valor para el atributo linea1 
     */
    private static final String ELEMENT_NODE_CLASS_LINE1 = "linea1";
    
    /**
     * el atributo "type"
     */
    private static final String ELEMENT_NODE_ATR_TYPE = "type";
    /**
     * el atributo "id"
     */
    private static final String ELEMENT_NODE_ATR_ID = "id";
    /**
     * el atributo "name"
     */
    private static final String ELEMENT_NODE_ATR_NAME = "name";
    /**
     * el atributo "value"
     */
    private static final String ELEMENT_NODE_ATR_VALUE = "value";
    /**
     * el atributo class
     */
    private static final String ELEMENT_NODE_ATR_CLASS = "class";
    /**
     * el atributo multiple
     */
    private static final String ELEMENT_NODE_ATR_MULTIPLE = "multiple";
    /**
     * el nombre de la clase del cuestionario
     */
    private static final String ELEMENT_CLASS_CUESTIONARIO= "ElegibilidadCuestionario";
    /**
     * el nombre de la clase del m&oacute;lo sin columnas
     */
    private static final String ELEMENT_CLASS_ARTICLE = "moduloSincolumnas";
    /**
     * el nombre de la clase de la columna 1
     */
    private static final String ELEMENT_CLASS_COLUMN1 = "Pregunta";
    /**
     * el nombre de la clase de la columna 2
     */
    private static final String ELEMENT_CLASS_COLUMN2 = "Opcion";
    /**
     * el nombre de la clase de la columna 3
     */
    private static final String ELEMENT_CLASS_COLUMN3 = "Justificacion";
    /**
     * el nombre de la clase de la columna 4
     */
    private static final String ELEMENT_CLASS_COLUMN4 = "Evidencia";
    
    /**
     * Ticket: 105279
     * Date: 21-10-21
     * Developer: RUGM
     * Consulting: Kruger
     * */
    
    /**
      * Eliminacion de bullet
     */
    private static final String ELEMENT_NODE_DETAILS = "details" ;
    
    /**
      * el nombre de details mas HTML
     */
    private static final String ELEMENT_BULLET_TOOLTIP = "cadena";
    /**
      * estilo  de details mas HTML
     */
    private static final String ELEMENT_NODE_INPUT_STYLE_DETAILS  = "cursor:default";
    
    /**
      * el nombre de summary mas HTML
     */
    private static final String ELEMENT_NODE_SUMARY = "summary" ;
    /**
      * estilo  de summary mas HTML
     */
    private static final String ELEMENT_NODE_INPUT_STYLE_SUMARY = "outline: none; margin: 25px; width: 1000px; height: 40px; text-align: justify;";
    
    /**
      * estilo  de div mas HTML
     */
    
    private static final String ELEMENT_NODE_INPUT_STYLE_DIV = "outline: none; margin: 45px; width: 1000px; height: 90px; text-align: justify;";
    /**
      * el nombre de oculto mas HTML
     */
    private static final String ELEMENT_NODE_I_DIV = "div" ;
    /**
      * estilo  de oculto mas HTML
     */
    private static final String ELEMENT_NODE_INPUT_STYLE_I_DIV = "color:#0000FF";
    /**
     * Leer mas (titulo)
     */
    private static final String ELEMENT_NODE_INPUT_LEER_MAS = "leer mas...";
    /**
      * Stilos CSS
     */
    private static final String ELEMENT_NODE_STYLE_CSS = "style" ;
    
    private static final String ELEMENT_NODE_ID_DIV= "id";
    private static final String ELEMENT_NODE_ID_DIV_TXT= "mydiv";
    
    private static final String ELEMENT_NODE_INPUT_STYLE_DIV_CLASS = "tooltip";
    
    private static final String ELEMENT_NODE_DIV_TIP = "tip";
    private static final String ELEMENT_NODE_DIV_TIP_TOOLTIP_TXT = "clic para expandir comentario";
    
    
    //--END
    
    /**
     * el cuestionario HTML
     */
    String cuestionarioHTML;
    /**
     * el cuestionario solo lectura HTML
     */
    String analizarHTML;
    /**
     * la lista de preguntas
     */
    List<Map> preguntasList;
    
    
    private static ADFLogger logger = null;
    
    
    private Long idPregunta;
    private UploadedFile evidenciaAdjunto;
    private InputStream evidenciaInputStream;
    private String nombreEvidencia;
    private Date fechaArchivo;
    private String codigoArchivo;
    private String resumenArchivo;
        
    public CuestionarioElegibilidadBean() {
        super();
        
        
        if (logger == null)
            {
                logger = ADFLogger.createADFLogger(this.getClass());
            }  
    }


    public void setCuestionarioHTML(String cuestionarioHTML) {
        this.cuestionarioHTML = cuestionarioHTML;
    }

    public String getCuestionarioHTML() {
        return cuestionarioHTML;
    }

    public void setAnalizarHTML(String analizarHTML) {
        this.analizarHTML = analizarHTML;
    }

    public String getAnalizarHTML() {
        return analizarHTML;
    }

    public void setPreguntasList(List<Map> preguntasList)
    {
        this.preguntasList = preguntasList;
    }

    public List<Map> getPreguntasList()
    {
        return preguntasList;
    }
    
    public Cuestionario getCuestionario()
    {
        Cuestionario cuestionario = (Cuestionario)JSFUtils.resolveExpression("#{preguntasElegibilidadBean}");
        
        if(cuestionario==null)
        {
            cuestionario = new Cuestionario();
        }
        
        return cuestionario;
    }
    
    /**
     * Ticket: 105279
     * Dare:24-10-2021
     * Developer: RUGM
     * Consulting: Kruger
     * Step: 1
     * */
    public void generarCuestionario() throws ParserConfigurationException, TransformerConfigurationException, TransformerException
    {
        
        logger.warning("preguntasList size: "+preguntasList.size());
        
        //La lista proviene del metodo getPreguntasCuestionario que esta definido en el TaskFlow retur ListaPreguntas
        
        if(preguntasList!=null && preguntasList.size()>0)
        {
            //Restablece el puntero a la primera fila
            getCuestionario().reset();
            List<Principio> listPrincipios = new ArrayList<Principio>();
            Integer pIdResponsable =  JSFUtils.resolveExpression("#{pageFlowScope.pIdResponsable}")==null?0:Integer.parseInt((String)JSFUtils.resolveExpression("#{pageFlowScope.pIdResponsable}"));
            Boolean pEsSoloLectura =  JSFUtils.resolveExpression("#{pageFlowScope.pEsSoloLectura}")==null?false:(Boolean)JSFUtils.resolveExpression("#{pageFlowScope.pEsSoloLectura}");
            Boolean pEsAnalizarElegibilidad =  JSFUtils.resolveExpression("#{pageFlowScope.pEsAnalizarElegibilidad}")==null?false:(Boolean)JSFUtils.resolveExpression("#{pageFlowScope.pEsAnalizarElegibilidad}");

            logger.warning("listPrincipios : "+ listPrincipios.size());
            logger.warning("pIdResponsable : "+ pIdResponsable );
            logger.warning("pEsAnalizarElegibilidad : "+ pEsAnalizarElegibilidad );
            logger.warning("¿pEsSoloLectura? : "+ pEsSoloLectura );
            
            //Instrucciones para obtener en memoria el contenido completo del fichero DOM XML java:
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder(); 
            Document doc = builder.newDocument(); 
            //A partir de ese momento, todas las acciones sobre el documento XML se deberán realizar sobre la variable documento de la clase Document de Java, que permite la gestión de documentos XML basándose en el modelo DOM.
            
            Element cuestionarioElement = doc.createElement(ELEMENT_NODE_DIV);  //Questionare Root
            cuestionarioElement.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_CUESTIONARIO);
            
            doc.appendChild(cuestionarioElement); 


            Principio currentPrincipio = null;
            Criterio currentCriterio = null;
            int contador = 1;
            //Recorre Lista e imprime lista
            for(Map pregunta : preguntasList){
                Iterator it = pregunta.entrySet().iterator();
                System.out.println("============== "+contador +" =======================");
                while (it.hasNext()) {
                    Map.Entry e = (Map.Entry)it.next();
                    
                    logger.warning(e.getKey() + " " + e.getValue());

                    if(e.getKey().toString().toUpperCase().equals("CRITERIO")){
                        logger.warning(e.getKey().toString());
                    }
                }
                contador++;
            }
            
            //Ordena la Lista para dejar en la cabeza a las preguntas relacionadas con Criterio Nota de Concepto
            // Es una colección ordenada de objetos que puede almacenar valores duplicados
            for(int i = 0; i <= preguntasList.size() -1; i++){
                if ( ((Map) preguntasList.get(i)).get("Criterio").equals("Nota de Concepto") ){
                    Map criterio = preguntasList.get(i);
                    preguntasList.remove(i);
                    preguntasList.add(0 ,criterio);
                }
            } 
            
            //Mapa de generacion de preguntas
            int contadorPregunta = 1 ;
            for(Map preguntaM : preguntasList)
            {
                contadorPregunta++;
                logger.warning("Tamaño de lista = " + preguntasList.size());
                logger.warning("Contador Pregunta = " + contadorPregunta);
                //Iteracion del mapa de preguntas
                
                Boolean crearPrincipio = Boolean.TRUE;
                Boolean crearCriterio = Boolean.TRUE;
                Boolean isRolAlterno = Boolean.FALSE;
              
                Pregunta newPregunta = new Pregunta(preguntaM, pIdResponsable, pEsSoloLectura, pEsAnalizarElegibilidad);
             
                Integer idPrincipio = newPregunta.getIdPrincipio();
                Integer idResponsable = newPregunta.getIdResponsable();
                Integer idCriterio = newPregunta.getIdCriterio();   
                String descPrincipio = newPregunta.getPrincipioTxt();
                
                System.out.println("======================= Recorrido de Lista de Preguntas =================================");
                System.out.println("idPrincipio = " + idPrincipio);
                System.out.println("idResponsable = " + idResponsable);
                System.out.println("idCriterio = " + idCriterio);
                System.out.println("descPrincipio = " + descPrincipio);
                System.out.println("pEsSoloLectura = " + pEsSoloLectura);
                System.out.println("newPregunta.getEsEditable() = " + newPregunta.getEsEditable());
                System.out.println("newPregunta.getEsVisible() = "+ newPregunta.getEsVisible());
                
                if(pEsSoloLectura || newPregunta.getEsEditable() || newPregunta.getEsVisible())
                {   
                    /**
                     * Ticket: 105279
                     * Dare:24-10-2021
                     * Developer: RUGM
                     * Consulting: Kruger
                     * Step: 2 Pregunta si DOM tienen cadenas de principios
                     * */
                    currentPrincipio = getPrincipio(idPrincipio, idResponsable, listPrincipios);
                    
                    if(null != currentPrincipio)
                    {
                        if(idResponsable.compareTo(currentPrincipio.getIdRol()) != 0)
                        {
                            currentPrincipio = null;
                            currentCriterio = null;
                            descPrincipio = descPrincipio + " ("+ ADFUtils.getStringFromBundle(ROL_BUNDLE_PREFIX+idResponsable, BUNDLE_NAME)+")";
                            System.out.println("descPrincipio = " + descPrincipio);
                            
                            if(pEsAnalizarElegibilidad)
                            {
                                isRolAlterno = Boolean.TRUE;
                            }
                        } 
                        else
                        {
                            crearPrincipio = Boolean.FALSE;
                        }
                    }
                   
                    /**
                     * Ticket: 105279
                     * Dare:24-10-2021
                     * Developer: RUGM
                     * Consulting: Kruger
                     * Step: 3 Pregunta si DOM tienen cadenas de principios  , de lo contrario las crea
                     * Note: Siempre entra
                     * */
                    if(crearPrincipio)
                    {
                        /**
                         * Ticket: 105279
                         * Dare:24-10-2021
                         * Developer: RUGM
                         * Consulting: Kruger
                         * Step: 4 Genera la seccion del DOM Principio, nivel 1
                         * */
                        currentPrincipio = generatePrincipio(doc, descPrincipio, idPrincipio, idResponsable, isRolAlterno);
                        listPrincipios.add(currentPrincipio);
                        cuestionarioElement.appendChild(currentPrincipio.getElementPrincipio());
                    }
                    
                    currentCriterio =  getCriterio(idCriterio,currentPrincipio.getListCriterios());
                    
                    crearCriterio = currentCriterio == null ? Boolean.TRUE: Boolean.FALSE;
                    
                    /**
                     * Ticket: 105279
                     * Dare:24-10-2021
                     * Developer: RUGM
                     * Consulting: Kruger
                     * Step: 5 Genera la seccion del DOM Titulo nivel 2
                     * */
                    if(crearCriterio)
                    {
                        /**
                         * Ticket: 105279
                         * Overview: el atributo pEsSoloLectura = indicara si elimina o no la columna de evidencia para todas las preguntas
                         * Developer: RUGM
                         * Consulting: kruger
                         * */
                        currentCriterio = generateCriterio(doc, newPregunta.getCriterioTxt(), idCriterio, pEsSoloLectura);
                        currentPrincipio.getListCriterios().add(currentCriterio);
                        currentPrincipio.getElementPrincipio().appendChild(currentCriterio.getElementCriterio());
                    }
                    
                    /**
                     * Ticket: 105279
                     * Dare:24-10-2021
                     * Developer: RUGM
                     * Consulting: Kruger
                     * Step: 6 Genera la el OBJ Cuestionario conteneodr de toda slas preguntas editables.
                     * */
                    if(newPregunta.getEsEditable())
                    {
                        getCuestionario().getPreguntasCuestionario().add(newPregunta);    
                    }
                    
                    /**
                     * Ticket: 105279
                     * Dare:24-10-2021
                     * Developer: RUGM
                     * Consulting: Kruger
                     * Step: 7 Genera la el OBJ pregunta DOM en todas las preguntas no editables.
                     * */
                    logger.warning("Contador Pregunta = " + contadorPregunta);
                    currentCriterio.getElementPreguntas().appendChild(generatePreguntaElement(doc,newPregunta,pEsSoloLectura,contadorPregunta));
                    
                    
                }
                
            } // End for(Map preguntaM : preguntasList)
            
            if(pEsAnalizarElegibilidad) 
            {
                NodeList principiosList = doc.getElementsByTagName(ELEMENT_NODE_ARTICLE);
                
                if(null != principiosList && principiosList.getLength()>0)
                {
                  Element readOnlyCuestionarioElement = doc.createElement(ELEMENT_NODE_DIV);  //Questionare Root
                  readOnlyCuestionarioElement.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_CUESTIONARIO);
                  Boolean hasElements = Boolean.FALSE;
                  
                  for (int i=0; i < principiosList.getLength(); i++) 
                  {
                      Element element=(Element)principiosList.item(i);

                      String attributeId = element.getAttribute(ELEMENT_NODE_ATR_ID);
                    
                      if(attributeId!=null && attributeId.equalsIgnoreCase("0"))
                      {
                        Node readOnlyElement = cuestionarioElement.removeChild(element);
              
                        readOnlyCuestionarioElement.appendChild(readOnlyElement);
                        
                        hasElements = Boolean.TRUE;
                      }
                  } 
                  
                  if(hasElements)
                  {
                    analizarHTML = getXMLStringCuestionario(readOnlyCuestionarioElement);
                      
                  }
                }
            }
            
            cuestionarioHTML = getXMLStringCuestionario(cuestionarioElement);
            logger.warning("Cadena HTML"+cuestionarioHTML);
            
            preguntasList=null; 
            
        }
    }
    
    private String getXMLStringCuestionario(Element cuestionarioElement) throws TransformerConfigurationException, TransformerException
   {
        DOMSource domSource = new DOMSource(cuestionarioElement);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty("omit-xml-declaration", "yes");
        transformer.transform(domSource, result);
        
        return  writer.toString();
    }
    
    /**
     * Ticket: 105279
     * Dare:24-10-2021
     * Developer: RUGM
     * Consulting: Kruger
     * Step: 4 Genera la seccion del DOM Principio, nivel 1
     * */
    private Principio generatePrincipio(Document poDoc, String psPrincipio, Integer piIdPrincipio, Integer piIdRol, Boolean pbEsAnalizarElegibilidad) 
    {
        logger.warning("Inside generatePrincipio");
        logger.warning("Generacion <ARTICLE>");
        logger.warning("psPrincipio = " + psPrincipio);
        logger.warning("piIdPrincipio = " + piIdPrincipio);
        logger.warning("piIdRol = " + piIdRol);
        logger.warning("pbEsAnalizarElegibilidad = " + pbEsAnalizarElegibilidad);
       
        
        
        Principio principio = new Principio();
        Element principioElement = poDoc.createElement(ELEMENT_NODE_ARTICLE);
        Element titleElemnt =  poDoc.createElement(ELEMENT_NODE_H2);
        titleElemnt.setTextContent(psPrincipio);
        
        if(pbEsAnalizarElegibilidad) 
        {
            principioElement.setAttribute(ELEMENT_NODE_ATR_ID, "0");
        }
        
        principioElement.appendChild(titleElemnt);
        
        principio.setIdPrincipio(piIdPrincipio);
        principio.setIdRol(piIdRol);
        principio.setElementPrincipio(principioElement);
        
        return principio;
    }
     /**
      * Ticket: 105279
      * Dare:24-10-2021
      * Developer: RUGM
      * Consulting: Kruger
      * Step: 2 Pregunta si el DOM tienen cadenas de principios
      * */
    private Principio getPrincipio(Integer piPrincipioId, Integer piRolId, List<Principio> listPrincipios)
    {
        logger.warning("Inside getPrincipio : ");
        logger.warning("piPrincipioId = " + piPrincipioId);
        logger.warning("piRolId = " + piRolId );
        /*for (Principio principio : listPrincipios) {
            logger.warning("===list Principios ====");
            logger.warning("Principio.getIdPrincipio() = " + principio.getIdPrincipio());
            logger.warning("Principio.getIdRol() = " + principio.getIdRol());
            logger.warning("Principio.getElementPrincipio().getTagName() = " + principio.getElementPrincipio().getTagName());
            logger.warning("Principio.getElementPrincipio().getTextContent() = " + principio.getElementPrincipio().getTextContent());
            for (Criterio criterio : principio.getListCriterios()) {
                logger.warning("===list Criterios====");
                logger.warning("Principio.Criterio.getIdCriterio() = " + criterio.getIdCriterio());
                logger.warning("Principio.Criterio.getElementCriterio().getTagName() = " + criterio.getElementCriterio().getTagName());
                logger.warning("Principio.Criterio.getElementCriterio().getTextContent() = " + criterio.getElementCriterio().getTextContent());
                logger.warning("Principio.Criterio.getElementPreguntas().getTagName() = " + criterio.getElementPreguntas().getTagName());
                logger.warning("Principio.Criterio.getElementPreguntas().getTextContent() = " + criterio.getElementPreguntas().getTextContent());
                
            }    
        }*/
        
        Principio principioB = null;
        if(null != listPrincipios)
        {
            for (Principio principio : listPrincipios)
            {
                if(principio.getIdPrincipio().compareTo(piPrincipioId) == 0)
                {
                    principioB = principio;
                    if(principio.getIdRol().compareTo(piRolId)==0)
                        break;
                }
            }
        }
        
        return principioB;
    }
    
    private Criterio getCriterio(Integer piCriterioId, List<Criterio> listCriterios)
    {
        logger.warning("Inside getCriterio : ");
        logger.warning("== Generacion de Subcabezas ==== ");
        logger.warning("piCriterioId = " + piCriterioId );
        /*for (Criterio criterio : listCriterios) {
                logger.warning("===list Criterios====");
                logger.warning("Principio.Criterio.getIdCriterio() = " + criterio.getIdCriterio());
                logger.warning("Principio.Criterio.getElementCriterio().getTagName() = " + criterio.getElementCriterio().getTagName());
                logger.warning("Principio.Criterio.getElementCriterio().getTextContent() = " + criterio.getElementCriterio().getTextContent());
                logger.warning("Principio.Criterio.getElementPreguntas().getTagName() = " + criterio.getElementPreguntas().getTagName());
                logger.warning("Principio.Criterio.getElementPreguntas().getTextContent() = " + criterio.getElementPreguntas().getTextContent());
        }*/
        
        if(null != listCriterios)
        {
            for (Criterio criterio : listCriterios)
            {
                if(criterio.getIdCriterio().compareTo(piCriterioId) == 0)
                {
                    return criterio;
                }
            }
        }
        
        return null;
    }
    
    /**
     * Ticket: 105279
     * Dare:24-10-2021
     * Developer: RUGM
     * Consulting: Kruger
     * Step: 5 Genera la seccion del DOM Titulo nivel 2
     * */
    private Criterio generateCriterio(Document poDoc, String psCriterio, Integer piIdCriterio, Boolean esSoloLectura)
    {
        Criterio criterio = new Criterio();
        Element criterioElement = poDoc.createElement(ELEMENT_NODE_ARTICLE);
        Element titleElemnt =  poDoc.createElement(ELEMENT_NODE_H4);
        /**
         * Ticket: 105279
         * Overview: el atributo pEsSoloLectura = indicara si elimina o no la columna de evidencia para todas las preguntas
         * Developer: RUGM
         * Consulting: kruger
         * */
        Element tablePreguntasElement = generateQuestionsTable(poDoc, psCriterio, esSoloLectura);
        
        titleElemnt.setTextContent(psCriterio);
        
        criterioElement.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_ARTICLE);
        criterioElement.appendChild(titleElemnt);
        criterioElement.appendChild(tablePreguntasElement);
        
        criterio.setIdCriterio(piIdCriterio);
        criterio.setElementPreguntas(tablePreguntasElement); 
        criterio.setElementCriterio(criterioElement);
        return criterio;
    }
    
    /**
     * Ticket: 105279
     * Overview: el atributo pEsSoloLectura = indicara si elimina o no la columna de evidencia para todas las preguntas
     * Developer: RUGM
     * Consulting: kruger
     * Step: Ultimo paso de creacion de cuestionario
     * */
    private Element generateQuestionsTable(Document poDoc, String criterio, Boolean esSoloLectura)
    {
        Element headersC4Element = null;
        
        Element tableElement = poDoc.createElement(ELEMENT_NODE_TABLE);
        Element tableHeadersElement = poDoc.createElement(ELEMENT_NODE_TR);
        
        Element headersC1Element = poDoc.createElement(ELEMENT_NODE_TH);
        headersC1Element.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_COLUMN1);
        
        Element headersC2Element = poDoc.createElement(ELEMENT_NODE_TH);
        headersC2Element.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_COLUMN2);
        
        Element headersC3Element = poDoc.createElement(ELEMENT_NODE_TH);
        headersC3Element.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_COLUMN3);
        
        /**
         * Ticket: 105279
         * Overview: el atributo pEsSoloLectura = indicara si elimina o no la columna de evidencia para todas las preguntas
         * Developer: RUGM
         * Consulting: kruger
         * */
        if(!esSoloLectura){
        headersC4Element = poDoc.createElement(ELEMENT_NODE_TH);
        headersC4Element.setAttribute(ELEMENT_NODE_ATR_CLASS, ELEMENT_CLASS_COLUMN4);
        }
        
        headersC1Element.setTextContent(ADFUtils.getStringFromBundle("HEADER_LABEL_C1", BUNDLE_NAME));
        headersC2Element.setTextContent(ADFUtils.getStringFromBundle("HEADER_LABEL_C2", BUNDLE_NAME));
        headersC3Element.setTextContent(ADFUtils.getStringFromBundle("HEADER_LABEL_C3", BUNDLE_NAME));
        
        /**
         * Ticket: 105279
         * Overview: el atributo pEsSoloLectura = indicara si elimina o no la columna de evidencia para todas las preguntas
         * Developer: RUGM
         * Consulting: kruger
         * */
        if(!esSoloLectura){
        headersC4Element.setTextContent(ADFUtils.getStringFromBundle("HEADER_LABEL_C4", BUNDLE_NAME));
        }
        
        tableHeadersElement.appendChild(headersC1Element);
                if (!criterio.equals("Nota de Concepto")){
                    tableHeadersElement.appendChild(headersC2Element);
                }
                
                tableHeadersElement.appendChild(headersC3Element);
                
                /**
                 * Ticket: 105279
                 * Overview: el atributo pEsSoloLectura = indicara si elimina o no la columna de evidencia para todas las preguntas
                 * Developer: RUGM
                 * Consulting: kruger
                 * */
                if(!esSoloLectura){
                    if (!criterio.equals("Nota de Concepto")){
                        tableHeadersElement.appendChild(headersC4Element);
                    }
                }
        
        tableElement.appendChild(tableHeadersElement);
        
        return tableElement;
        
    }
    
    /**
     * Ticket: 105279
     * Dare:24-10-2021
     * Developer: RUGM
     * Consulting: Kruger
     * Step: 7 FOR : Genera la el OBJ pregunta dentro del DOM en todas las preguntas no editables.
     * */
    private Element generatePreguntaElement(Document poDoc, Pregunta poPregunta, Boolean esSoloLectura, int id)
    {
        logger.warning("ID DIV = " + id);
        System.out.println("\nInside generatePreguntaElement.");
        
        System.out.println("¿Es pEsSoloLectura ? = " + esSoloLectura);
        
        
        //Creacion de un fila de la tabla
        Element preguntaElement = poDoc.createElement(ELEMENT_NODE_TR);
        Element preguntaTxtElement =  poDoc.createElement(ELEMENT_NODE_TD);
        
        //creacion de celda contendor <pregunta>
        Element preguntaContainerElement = poDoc.createElement(ELEMENT_NODE_TD);
        
        //creacion de Elemento Justificacion <justificacion>
        Element preguntaJustificacionElement = poDoc.createElement(ELEMENT_NODE_TD);
        
        //Creacion de Elemento BotonDoc
        Element botonDocElement = poDoc.createElement(ELEMENT_NODE_TD);
        
        //Contenedor Details, porque contiene la etiqueta <sumary> && <div>
        Element detailsContectElement = poDoc.createElement(ELEMENT_NODE_DETAILS);
        
        //Contenedor Sumary, porque tiene la etiqueta <i>
        Element sumaryContectElement = poDoc.createElement(ELEMENT_NODE_SUMARY);
        
        
        //Normalizar de alguna manera la cadena de texto antes de guardarlos en una base de datos
        preguntaTxtElement.setTextContent(Normalizer.normalize(poPregunta.getTextoPregunta(), Normalizer.Form.NFC) );
        
        Element preguntaJustificacionContentElement;
        Element btnDocElement = null;
        Element nombreDocElement = null;
        
        //Creacion de elemento <div>, definidos asi porque no contienen mas proipiedades
        Element leerMasElement = null;
        //Creacion de elemento <div>, definidos asi porque no contienen mas proipiedades
        Element divLeerMasMasElement = null;
        //Creacion de elemento <span>, definidos asi porque no contienen mas proipiedades
        //Element clicParaExpandirElement = null;
        
        //Informacion de la Pregunta a construir
        
        System.out.println("Pregunta.getEsEditable() = " + poPregunta.getEsEditable() + "\n");
        if(poPregunta.getEsEditable())
        {
            System.out.println("======================= Detalle Pregunta ======================================" );
            System.out.println(poPregunta.toString());
            
            // genera contenedor del textarea
            preguntaJustificacionContentElement = poDoc.createElement(ELEMENT_NODE_TEXTAREA);
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_ATR_ID, poPregunta.getJustificacionHTMLId());
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_ATR_NAME, poPregunta.getJustificacionHTMLId());
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_MAXLENGTH,"4000");
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_INPUT_STYLE,ELEMENT_NODE_INPUT_STYLE_TA);
            preguntaJustificacionContentElement.setAttribute(EVENT_ONKEY_UP, "dimensionar(this)");
            preguntaJustificacionContentElement.setAttribute(PROPIEDAD_PLACEHOLDER, MSG_DIMENSIONAR);
            
            
            if(poPregunta.getJustificacionPregunta() ==  null){
                preguntaJustificacionContentElement.setTextContent("\n");
            }else{
                preguntaJustificacionContentElement.setTextContent(poPregunta.getJustificacionPregunta());
            }
            
            
            
            //Se genera contenedor del Buttom
            btnDocElement = poDoc.createElement(ELEMENT_NODE_INPUT);
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_ID, poPregunta.getIdPregunta()+"");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_NAME, poPregunta.getIdPregunta()+"");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_TYPE, ELEMENT_NODE_INPUT_TYPE_BUTTOM);
            btnDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS,ELEMENT_NODE_CLASS_LINE1);
            btnDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_ONCLICK, "enviarArchivo("+poPregunta.getIdPregunta()+")");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_VALUE, ELEMENT_NODE_INPUT_NAME_BUTTOM);
            
            //Se genera nombre del Doc
            if(poPregunta.getNombreDoc() != null){
                // genera contenedor del nombreDocumento
                nombreDocElement = poDoc.createElement(ELEMENT_NODE_TEXTAREA);
                nombreDocElement.setAttribute(ELEMENT_NODE_ATR_ID, "nameTA-"+poPregunta.getIdPregunta());
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED,ELEMENT_NODE_INPUT_TYPE_DISABLED);                    
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS,ELEMENT_NODE_CLASS_LINE);
                nombreDocElement.setTextContent(poPregunta.getNombreDoc());
            }else{
                nombreDocElement = poDoc.createElement(ELEMENT_NODE_TEXTAREA);
                nombreDocElement.setAttribute(ELEMENT_NODE_ATR_ID, "nameTA-"+poPregunta.getIdPregunta());
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED,ELEMENT_NODE_INPUT_TYPE_DISABLED);                    
                nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS,ELEMENT_NODE_CLASS_LINE);
                nombreDocElement.setTextContent("\n");
            }
        }//Preguntas NO editables, eliminacion de textArea y generacion de Details>Sumary
        else
        {
            //Generacion textArea / cambiar por ** details>summary>i>div>
            
            //Generacion TextArea con atributos
            preguntaJustificacionContentElement = poDoc.createElement(ELEMENT_NODE_DIV);
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED,ELEMENT_NODE_INPUT_TYPE_DISABLED);
            preguntaJustificacionContentElement.setAttribute(ELEMENT_NODE_INPUT_STYLE,ELEMENT_NODE_INPUT_STYLE_TA);

            
            
            //2.-Crear el elemnto mas interno <div> con sus atributos
            leerMasElement  = poDoc.createElement(ELEMENT_NODE_I_DIV);
            
            //seteo de Id
            leerMasElement.setAttribute(ELEMENT_NODE_ID_DIV,ELEMENT_NODE_ID_DIV_TXT+'_'+id);
            
            //Seteo de evento
            //leerMasElement.setAttribute(ELEMENT_NODE_ID_DIV_EVENT,ELEMENT_NODE_ID_DIV_EVENT_TXT);
            leerMasElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_ONCLICK,"change(this)");
            
            //Seteo de clase css tooltip
            leerMasElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS,ELEMENT_NODE_INPUT_STYLE_DIV_CLASS);
            
            //Seteo de leyenda del tooltip
            leerMasElement.setAttribute(ELEMENT_NODE_DIV_TIP,ELEMENT_NODE_DIV_TIP_TOOLTIP_TXT);
            
            //Seteo de atributo=style, valor= color:#0000FF
            leerMasElement.setAttribute(ELEMENT_NODE_STYLE_CSS,ELEMENT_NODE_INPUT_STYLE_I_DIV);
            
            
            //2.- Generacion de Sumary con sus atributos
            //Seteo de atributo=style, valor= cursor:default
            sumaryContectElement.setAttribute(ELEMENT_NODE_INPUT_STYLE,ELEMENT_NODE_INPUT_STYLE_SUMARY);
            
            String cadena =null;
            int longitud = 0;
            int inicio = 0;
            //Numero de caracteres a 3 renglones
            int fin = 269;
            
            cadena = poPregunta.getJustificacionPregunta();
            System.out.println("ID Pregunta = " + poPregunta.getIdPregunta());
            System.out.println("Cadena justificacion = " + poPregunta.getJustificacionPregunta() == null ? "null" : poPregunta.getJustificacionPregunta() );
            
            if(poPregunta.getJustificacionPregunta() !=  null)
            {
                longitud = poPregunta.getJustificacionPregunta().length() ;
            }
            
            if(poPregunta.getJustificacionPregunta() ==  null)
            {
                preguntaJustificacionContentElement.setTextContent("\n");
                //Seteo de cadena leer mas...
                //leerMasElement.setTextContent("");
                //sumaryContectElement.setTextContent( "" );
                
            }
            else
            {
                //Seteo de cadena leer mas...
                leerMasElement.setTextContent(ELEMENT_NODE_INPUT_LEER_MAS);
                //Agregamos el <span> hijo de <div>
                //leerMasElement.appendChild(clicParaExpandirElement);
                //Si la cadena es candidata a ser cortada porque revada el amximo de caracteres en la cadena
                System.out.println("\nCadena : " +  cadena );
                System.out.println("\nLongitud : " + longitud );
                if( longitud > fin){
                    
                    System.out.println("Subcadena 1 = " + cadena.substring(inicio, fin).toString().length());
                    System.out.println("Subcadena 2 = " + cadena.substring(fin, longitud).toString().length());
                    sumaryContectElement.setTextContent(cadena.substring(inicio, fin));
                    
                    //-preguntaJustificacionContentElement.setTextContent(cadena.substring(inicio, fin));
                    //Dentro de Sumary agregare un hijo.
                    //3.-Embeber <i> dentro de <sumary>
                    //Agregamos al hijo leer mas.., si aplica la reglas de longuitudes
                    sumaryContectElement.appendChild(leerMasElement);
                }else{
                    preguntaJustificacionContentElement.setTextContent(poPregunta.getJustificacionPregunta());
                    //Si aplica la reglas de longuitudes no aplica solo aplciar el summary, sin hijos
                    //sumaryContectElement.setTextContent( cadena );
                    
                }
                
            }
            
            
            //4.- Generacion de Details con sus atributos
            //Seteo de atributo=style, valor= cursor:default
            detailsContectElement.setAttribute(ELEMENT_NODE_INPUT_STYLE,ELEMENT_NODE_INPUT_STYLE_DETAILS);
            
            //Solo crear el tag <div> si la cadena es candidata apartirse
            if(poPregunta.getJustificacionPregunta() !=  null && longitud > fin){
                //5.- Generacion de div con sus atributos
                divLeerMasMasElement = poDoc.createElement(ELEMENT_NODE_DIV);
                //Seteo de atributo=style, valor= cursor:default
                divLeerMasMasElement.setAttribute(ELEMENT_NODE_INPUT_STYLE,ELEMENT_NODE_INPUT_STYLE_DIV);
                divLeerMasMasElement.setTextContent(cadena.substring(fin, longitud));
            }
            
            //6.-Escribimos el contenido dle contenedot details <sumary> && <div>
            detailsContectElement.appendChild( sumaryContectElement );
            
            
            if(poPregunta.getJustificacionPregunta() !=  null && longitud > fin){
                detailsContectElement.appendChild( divLeerMasMasElement );
            }
            
            
            
            
            //Se genera Buttom
            btnDocElement = poDoc.createElement(ELEMENT_NODE_INPUT);
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_ID, poPregunta.getIdPregunta()+"");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_NAME, poPregunta.getIdPregunta()+"");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_TYPE, ELEMENT_NODE_INPUT_TYPE_BUTTOM);
            btnDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS,ELEMENT_NODE_CLASS_LINE1);
            btnDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_ONCLICK, "enviarArchivo("+poPregunta.getIdPregunta()+")");
            btnDocElement.setAttribute(ELEMENT_NODE_ATR_VALUE, ELEMENT_NODE_INPUT_NAME_BUTTOM);
            btnDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED, ELEMENT_NODE_INPUT_TYPE_DISABLED);
            
            //Se genera nombre del Doc
            //Apartado de Evidencias, si es editable muestralo de lo contrario  no 
            
            System.out.println("esSoloLectura " + esSoloLectura );
            if(!esSoloLectura){
                if(poPregunta.getNombreDoc() != null){
                    nombreDocElement = poDoc.createElement(ELEMENT_NODE_TEXTAREA);
                    nombreDocElement.setAttribute(ELEMENT_NODE_ATR_ID, "nameTA-"+poPregunta.getIdPregunta());
                    nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED,ELEMENT_NODE_INPUT_TYPE_DISABLED);                    
                    nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS,ELEMENT_NODE_CLASS_LINE);
                    nombreDocElement.setTextContent(poPregunta.getNombreDoc());
                }else{
                    nombreDocElement = poDoc.createElement(ELEMENT_NODE_TEXTAREA);
                    nombreDocElement.setAttribute(ELEMENT_NODE_ATR_ID, "nameTA-"+poPregunta.getIdPregunta());
                    nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_DISABLED,ELEMENT_NODE_INPUT_TYPE_DISABLED);                    
                    nombreDocElement.setAttribute(ELEMENT_NODE_INPUT_TYPE_CLASS,ELEMENT_NODE_CLASS_LINE);
                    nombreDocElement.setTextContent("\n");
                }
            }
        }
        
        logger.warning("filename: "+ poPregunta.getNombreDoc());
        //--preguntaJustificacionElement.appendChild(preguntaJustificacionContentElement);
        
        //Agregamos al componente Justificacion el detalle del tag <detail>, siempre y cuando la jsutificacion sea != null && no sea editable
        if(poPregunta.getJustificacionPregunta() != null && poPregunta.getEsEditable()==false )
        {
            //Error
            preguntaJustificacionElement.appendChild(detailsContectElement);
            
        }
        else
        {
                preguntaJustificacionElement.appendChild(preguntaJustificacionContentElement);    
        }
        
        //Sobre elemento superios, se agrega el contenedor de
        //preguntaJustificacionElement.appendChild(detailsContectElement);
        
        
        
        if(!esSoloLectura)
        {
            botonDocElement.appendChild(btnDocElement);
            botonDocElement.appendChild(nombreDocElement);
        }
        
        //Funcionalidas para generar el apartado de la pregunta
        Element preguntaComponentElement = generatePreguntaInputComponent(poDoc,poPregunta);
        
        if (null!=preguntaComponentElement)
        {
            preguntaContainerElement.appendChild(preguntaComponentElement);
        }
            
        preguntaElement.appendChild(preguntaTxtElement);
        
        if (!poPregunta.getCriterioTxt().equals("Nota de Concepto"))
        {
           preguntaElement.appendChild(preguntaContainerElement);
        }
        
        preguntaElement.appendChild(preguntaJustificacionElement);
        
        if (!poPregunta.getCriterioTxt().equals("Nota de Concepto"))
        {
           preguntaElement.appendChild(botonDocElement);
        }
        return preguntaElement;
    }
    
    /**
     * Construye el componente HTML para la pregunta con respuesta alfanum&eacute;rica:<br>
     * <input type="text" id="${id}" name="${name}" value="${value}"/>
     * @param poDocument el documento
     * @param poQuestion la pregunta
     * @return el componente HTML para la pregunta con respuesta alfanum&eacute;rica
     */
    private Element buildTextQuestionComponent(Document poDocument, Pregunta poQuestion) {
        // INPUT TEXT 
        // <input type="text" id="${id}" name="${name}" value="${value}"/>
        Element component = poDocument.createElement(ELEMENT_NODE_TEXTAREA);
        //component.setAttribute(ELEMENT_NODE_ATR_TYPE, ELEMENT_NODE_INPUT_TYPE_TEXT);
        component.setAttribute(ELEMENT_NODE_ATR_ID, poQuestion.getRepuestaHTMLId());
        component.setAttribute(ELEMENT_NODE_ATR_NAME, poQuestion.getRepuestaHTMLId());
        component.setAttribute(ELEMENT_NODE_INPUT_TYPE_MAXLENGTH,"4000");
        component.setAttribute(ELEMENT_NODE_INPUT_STYLE,ELEMENT_NODE_INPUT_STYLE_TA);
        
        if(poQuestion.getJustificacionPregunta() ==  null){
            component.setTextContent("\n");
        }else{
            component.setTextContent(poQuestion.getRespuestaPregunta());
        }
                
        //component.setAttribute(ELEMENT_NODE_ATR_VALUE, poQuestion.getRespuestaPregunta());
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
        component.setAttribute(ELEMENT_NODE_INPUT_TYPE_MAXLENGTH,"500");
        component.setAttribute(ELEMENT_NODE_INPUT_TYPE_ONKEYPRESS,"return soloNumeros(event)");
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
        Element component =  poDocument.createElement(ELEMENT_NODE_SELECT); 
        component.setAttribute(ELEMENT_NODE_ATR_ID, poQuestion.getRepuestaHTMLId());
        component.setAttribute(ELEMENT_NODE_ATR_NAME, poQuestion.getRepuestaHTMLId());
        StringTokenizer opcionesTkn = new StringTokenizer(poQuestion.getOpcionesPregunta(), ",");
        Element optionElement = poDocument.createElement(ELEMENT_NODE_SELECT_OPTION);
        optionElement.setTextContent("");
        component.appendChild(optionElement);
        while (opcionesTkn.hasMoreElements()) {
            String optionText = opcionesTkn.nextToken();
            optionElement =  poDocument.createElement(ELEMENT_NODE_SELECT_OPTION);   
            optionElement.setTextContent(optionText);
            if(null != poQuestion.getRespuestaPregunta() 
                    && optionText.equalsIgnoreCase(poQuestion.getRespuestaPregunta())) {
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
            poQuestion.getOpcionesPregunta() != null && 
            poQuestion.getOpcionesPregunta().trim().length() > 0 ? 
                poQuestion.getOpcionesPregunta().split(",") : null;
        String[] answers = 
            poQuestion.getRespuestaPregunta() != null && 
            poQuestion.getRespuestaPregunta().trim().length() > 0 ? 
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
    
    private Element generatePreguntaInputComponent(Document poDocument, Pregunta poQuestion) {
        if (poDocument == null) throw new IllegalArgumentException("El documento no puede ser null");
        if (poQuestion == null) throw new IllegalArgumentException("La pregunta no puede ser null");
        Element component = null;
        if (poQuestion.getEsEditable()) {
            switch(poQuestion.getTipoPregunta()) {
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
                    throw new IllegalArgumentException(
                        new StringBuilder()
                            .append("El tipo de pregunta ")
                            .append(poQuestion.getTipoPregunta()).append(" es incorrecto").toString());
            }
        } else {
            component =  poDocument.createElement(ELEMENT_NODE_DIV);
            component.setTextContent(poQuestion.getRespuestaPregunta());
        }
        return component;
    }
    
    public Boolean guardarRespuestasCuestionario ( HttpServletRequest poRequest) {
        return getCuestionario().guardarRespuestasCuestionario(poRequest);
    }


    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }



    public void setEvidenciaAdjunto(UploadedFile evidenciaAdjunto) {
        this.evidenciaAdjunto = evidenciaAdjunto;
        logger.warning("Dentro de setEvidenciaAdjunto");
        if(evidenciaAdjunto != null){
            logger.warning("fileName :"+getEvidenciaAdjunto().getFilename());
            this.setNombreEvidencia(getEvidenciaAdjunto().getFilename()); // Valor por default para el nombre del archivo
            logger.warning("valor nombreEvidencia :"+this.getNombreEvidencia());
            
            try {
                this.evidenciaInputStream = evidenciaAdjunto.getInputStream();
            } catch (IOException e) {
                logger.log(ADFLogger.ERROR, e.getMessage());
            }
        }else{
            logger.warning("evidenciaAdjunto es null");
        }
        
        logger.warning("Fuera de setEvidenciaAdjunto");
    }
    
    public UploadedFile getEvidenciaAdjunto() {
        return evidenciaAdjunto;
    }


    public String getNombreEvidencia() {
        return nombreEvidencia;
    }

    public void setNombreEvidencia(String nombreEvidencia) {
        this.nombreEvidencia = nombreEvidencia;
    }


    public InputStream getEvidenciaInputStream() {
        return evidenciaInputStream;
    }

    public void setEvidenciaInputStream(InputStream evidenciaInputStream) {
        this.evidenciaInputStream = evidenciaInputStream;
    }


    public Date getFechaArchivo() {
        return fechaArchivo;
    }

    public void setFechaArchivo(Date fechaArchivo) {
        this.fechaArchivo = fechaArchivo;
    }


    public String getResumenArchivo() {
        return resumenArchivo;
    }

    public void setResumenArchivo(String resumenArchivo) {
        this.resumenArchivo = resumenArchivo;
    }


    public String getCodigoArchivo() {
        return codigoArchivo;
    }

    public void setCodigoArchivo(String codigoArchivo) {
        this.codigoArchivo = codigoArchivo;
    }
}
