package org.bcie.fenix.beans.cuestionarios.clases;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.share.logging.ADFLogger;

public class Cuestionario implements Serializable {
    @SuppressWarnings("compatibility:-2576333546210674390")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;


    List<Pregunta> preguntasCuestionario;
    List<Pregunta> preguntasMostrar;
    private Boolean presionarGuardar;
    private Boolean existeResultado = Boolean.FALSE;

    public Cuestionario() {
        super();
        preguntasCuestionario = new ArrayList<Pregunta>();
        preguntasMostrar = new ArrayList<Pregunta>();
        this.presionarGuardar=Boolean.FALSE;
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void setPreguntasCuestionario(List<Pregunta> preguntasCuestionario) {
        this.preguntasCuestionario = preguntasCuestionario;
    }

    public List<Pregunta> getPreguntasCuestionario() {
        return preguntasCuestionario;
    }

    public void setPreguntasMostrar(List<Pregunta> preguntasMostrar) {
        this.preguntasMostrar = preguntasMostrar;
    }

    public List<Pregunta> getPreguntasMostrar() {
        return preguntasMostrar;
    }

    public Boolean cuestionarioCompletado() {
        Boolean isCompleted = Boolean.TRUE;

        for (Pregunta pregunta : preguntasCuestionario) {

            if (null == pregunta.getRespuestaPregunta() || pregunta.getRespuestaPregunta().trim().length() == 0) {
                isCompleted = Boolean.FALSE;
                break;
            }
        }
        return isCompleted;
    }
     /**
     * @Método asociadoo a Feature20
     * @funtion: Guardar respuestas del Cuestionario de Derivados Implicitos
     * @since 16/06/2021
     * @by Krugers
     * @throws: 3 paso de ejecución Recupera la lista de respuesta
     */
    public List<Map> getRespuestasList() {
        logger.warning("Entramos a  getRespuestasList");
        List<Map> respuestasMap = new ArrayList<Map>();

        if (preguntasCuestionario != null) {
            for (Pregunta pregunta : preguntasCuestionario) {
                respuestasMap.add(pregunta.getValoresPregunta());
            }
        }
        logger.warning("El cuestionario tiene lista de respuestas: " + respuestasMap.toString());
        return respuestasMap;
    }
    /**
    * @Método asociadoo a Feature20
    * @funtion: Guardar respuestas del Cuestionario de Derivados Implicitos
    * @since 16/06/2021
    * @by Krugers
    * @throws: 2 paso de ejecución de guardado/update de datos
    */

    public Boolean guardarRespuestasCuestionario(HttpServletRequest poRequest) {
        logger.warning("Entramos a guardarRespuestasCuestionario");

        Boolean esCuestionarioCompletado = Boolean.TRUE;

        for (Pregunta pregunta : preguntasCuestionario) {
            String pRespuesta = poRequest.getParameter(pregunta.getRepuestaHTMLId());
            String[] answers = poRequest.getParameterValues(pregunta.getRepuestaHTMLId());
            String noAplicaTxt = "No Aplica";

            if (answers != null && answers.length > 1) {
                // seleccion multiple
                pRespuesta = concat(answers);
            }
            if (pregunta.getCriterioTxt().toUpperCase().equals("NOTA DE CONCEPTO")) {
                pRespuesta = noAplicaTxt;
            }

            String pJustificacion = poRequest.getParameter(pregunta.getJustificacionHTMLId());

            pregunta.setRespuestaPregunta(pRespuesta);
            pregunta.setJustificacionPregunta(pJustificacion);

            if (pRespuesta == null || pRespuesta.trim().length() == 0) {
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
            if (idx > 0)
                str.append(",");
            str.append(values[idx]);
        }
        return str.toString();
    }

    void reset() {
        preguntasCuestionario = null;
        preguntasCuestionario = new ArrayList<Pregunta>();
    }

    public static void setLogger(ADFLogger logger) {
        Cuestionario.logger = logger;
    }

    public static ADFLogger getLogger() {
        return logger;
    }

    public void setPresionarGuardar(Boolean presionarGuardar) {
        this.presionarGuardar = presionarGuardar;
    }

    public Boolean getPresionarGuardar() {
        return presionarGuardar;
    }


    public void setExisteResultado(Boolean existeResultado) {
        this.existeResultado = existeResultado;
    }

    public Boolean getExisteResultado() {
        return existeResultado;
    }
}
