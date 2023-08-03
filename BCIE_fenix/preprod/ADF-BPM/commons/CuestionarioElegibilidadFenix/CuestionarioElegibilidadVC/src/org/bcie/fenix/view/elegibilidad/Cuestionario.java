package org.bcie.fenix.view.elegibilidad;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.share.logging.ADFLogger;

public class Cuestionario implements Serializable
{
    @SuppressWarnings("compatibility:6002151897354408453")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;
    
    
    List<Pregunta> preguntasCuestionario;
    
    public Cuestionario()
    {
        super();
        preguntasCuestionario = new ArrayList<Pregunta>();
        if (logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void setPreguntasCuestionario(List<Pregunta> preguntasCuestionario)
    {
        this.preguntasCuestionario = preguntasCuestionario;
    }

    public List<Pregunta> getPreguntasCuestionario()
    {
        return preguntasCuestionario;
    }
    
    public Boolean cuestionarioCompletado()
    {
        Boolean isCompleted = Boolean.TRUE;
        
        for(Pregunta pregunta :preguntasCuestionario)
        {
            
            if(null == pregunta.getRespuestaPregunta() ||
                pregunta.getRespuestaPregunta().trim().length()==0)
            {
                isCompleted = Boolean.FALSE;
                break;
            }
        }
        
        return isCompleted;
    }
    
    public List<Map> getRespuestasList()
    {
        List<Map> respuestasMap = new ArrayList<Map> ();
        
        if(preguntasCuestionario!=null)
        {
            for(Pregunta pregunta : preguntasCuestionario)
            {
                respuestasMap.add(pregunta.getValoresPregunta());
            }
        }
        
        
        return respuestasMap;
    }
    
    
    public Boolean guardarRespuestasCuestionario ( HttpServletRequest poRequest)
    {
        logger.warning("Entramos a guardarRespuestasCuestionario");
        
        Boolean esCuestionarioCompletado = Boolean.TRUE;
        
        for(Pregunta pregunta : preguntasCuestionario)
        {
            String pRespuesta = poRequest.getParameter(pregunta.getRepuestaHTMLId());
            String[] answers = poRequest.getParameterValues(pregunta.getRepuestaHTMLId());
            String noAplicaTxt = "No Aplica";
            
            if (answers != null && answers.length > 1) {
                // seleccion multiple
                pRespuesta = concat(answers);
            }
            if (pregunta.getCriterioTxt().toUpperCase().equals("NOTA DE CONCEPTO")){
                pRespuesta = noAplicaTxt;
            }

            String pJustificacion = poRequest.getParameter(pregunta.getJustificacionHTMLId());
            
            pregunta.setRespuestaPregunta(pRespuesta);
            pregunta.setJustificacionPregunta(pJustificacion);
            
            if(pRespuesta==null || pRespuesta.trim().length()==0){
                logger.warning("Criterio: " + pregunta.getCriterioTxt());
                logger.warning("Principio: " + pregunta.getPrincipioTxt());
                logger.warning("pRespuesta: " + pregunta.getRepuestaHTMLId());
                
                esCuestionarioCompletado = Boolean.FALSE;
            }
            
            
        }
        logger.warning("El cuestionario esta completado: " + esCuestionarioCompletado);
        return esCuestionarioCompletado;
    }

    private String concat(String[] values) {
        StringBuilder str = new StringBuilder();
        for (int idx = 0; values != null && idx < values.length; idx++) {
            if (idx > 0) str.append(",");
            str.append(values[idx]);
        }
        return str.toString();
    }

    void reset()
    {
        preguntasCuestionario = null;
        preguntasCuestionario = new ArrayList<Pregunta>();
    }
}
