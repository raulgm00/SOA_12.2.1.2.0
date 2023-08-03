package org.bcie.fenix.view.elegibilidad;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

public class Pregunta implements Serializable
{
    @SuppressWarnings("compatibility:153387229853031996")
    private static final long serialVersionUID = 1L;
    private static final String PREGUNTA_RES_PREF = "pc01_r_";
    private static final String PREGUNTA_JUS_PREF = "pc01_j_";
    
    private Long idPregunta;
    private Integer tipoPregunta;
    private Integer idPrincipio;
    private Integer idResponsable;
    private Integer idCriterio;   
    private String textoPregunta;
    private String opcionesPregunta;
    private String respuestaPregunta;
    private String justificacionPregunta;
    private String principioTxt;
    private String criterioTxt;
    private String nombreDoc;
    private Long idDocumento;
    private Boolean esEditable;
    private Boolean esVisible;
    //private Boolean esConsulta; //TODO

    private String repuestaHTMLId;
    private String justificacionHTMLId;

    private static ADFLogger logger = null;
    public Pregunta()
    {
        super();
       
    }
    
    public Pregunta(Map poPregunta,Integer piIdResponsable, Boolean esSoloLectura, Boolean esAnalizarElegibilidad)
    {
        
        super();
        if (logger == null)
            {
                logger = ADFLogger.createADFLogger(this.getClass());
            } 
        idPregunta = (Long)poPregunta.get("IdPregunta");
        tipoPregunta = (Integer)poPregunta.get("IdTipoPregunta");
        idPrincipio = (Integer)poPregunta.get("IdPrincipio");
        idResponsable = (Integer)poPregunta.get("IdResponsable");
        idCriterio = (Integer)poPregunta.get("IdCriterio");
        textoPregunta = (String)poPregunta.get("Pregunta");
        opcionesPregunta = (String)poPregunta.get("Opciones");
        respuestaPregunta = (String)poPregunta.get("Respuesta");
        justificacionPregunta = (String)poPregunta.get("Justificacion");
        principioTxt = (String)poPregunta.get("Principio");
        criterioTxt = (String)poPregunta.get("Criterio");
        if(null != poPregunta.get("IdDocumento")){
            logger.warning("IdDocumento :"+poPregunta.get("IdDocumento"));
            idDocumento = (Long)poPregunta.get("IdDocumento");
        }else{
            logger.warning("IdDocumento es null");
        }
        
        if(null != poPregunta.get("NombreDocumento")){
            logger.warning("NombreDocumento :"+poPregunta.get("NombreDocumento"));
            nombreDoc = (String)poPregunta.get("NombreDocumento");
        }else{
            logger.warning("NombreDocumento es null");
        }
        
       
        if(!esSoloLectura)
        {
            if(piIdResponsable.compareTo(idResponsable)==0)
            {
                esEditable = Boolean.TRUE; 
                esVisible = Boolean.TRUE; 
            }
            else
            {
                esEditable = Boolean.FALSE; 
                if(esAnalizarElegibilidad)
                {
                    esVisible = Boolean.TRUE; 
                }
                else
                {
                    esVisible = Boolean.FALSE; 
                }
            }
        }
        else
        {               
            esEditable = Boolean.FALSE; 
            esVisible = Boolean.TRUE;
            //esConsulta = Boolean.FALSE; 
        }
            
        repuestaHTMLId = PREGUNTA_RES_PREF + idPregunta;
        justificacionHTMLId =PREGUNTA_JUS_PREF + idPregunta;
    }

    public Map getValoresPregunta()
    {
        Map<String, Object> valoresPregunta = new HashMap<String,Object>();
        
        valoresPregunta.put("IdPregunta", idPregunta);
        valoresPregunta.put("Respuesta", respuestaPregunta);
        valoresPregunta.put("Justificacion", justificacionPregunta);
        valoresPregunta.put("NombreDocumento", nombreDoc);
                
        return valoresPregunta;
    }

    public void setIdPregunta(Long idPregunta)
    {
        this.idPregunta = idPregunta;
    }

    public Long getIdPregunta()
    {
        return idPregunta;
    }

    public void setTipoPregunta(Integer tipoPregunta)
    {
        this.tipoPregunta = tipoPregunta;
    }

    public Integer getTipoPregunta()
    {
        return tipoPregunta;
    }

    public void setTextoPregunta(String textoPregunta)
    {
        this.textoPregunta = textoPregunta;
    }

    public String getTextoPregunta()
    {
        return textoPregunta;
    }

    public void setOpcionesPregunta(String opcionesPregunta)
    {
        this.opcionesPregunta = opcionesPregunta;
    }

    public String getOpcionesPregunta()
    {
        return opcionesPregunta;
    }

    public void setRespuestaPregunta(String respuestaPregunta)
    {
        this.respuestaPregunta = respuestaPregunta;
    }

    public String getRespuestaPregunta()
    {
        return respuestaPregunta;
    }

    public void setJustificacionPregunta(String justificacionPregunta)
    {
        this.justificacionPregunta = justificacionPregunta;
    }

    public String getJustificacionPregunta()
    {
        return justificacionPregunta;
    }

    public void setIdDocumento(Long idDocumento)
    {
        this.idDocumento = idDocumento;
    }

    public Long getIdDocumento()
    {
        return idDocumento;
    }    
    
    public void setNombreDoc(String nombreDoc)
    {
        this.nombreDoc = nombreDoc;
    }
    public String getNombreDoc()
    {
        return nombreDoc;
    }
    
    public void setRepuestaHTMLId(String repuestaHTMLId)
    {
        this.repuestaHTMLId = repuestaHTMLId;
    }

    public String getRepuestaHTMLId()
    {
        return repuestaHTMLId;
    }

    public void setJustificacionHTMLId(String justificacionHTMLId)
    {
        this.justificacionHTMLId = justificacionHTMLId;
    }

    public String getJustificacionHTMLId()
    {
        return justificacionHTMLId;
    }

    public void setEsEditable(Boolean esEditable)
    {
        this.esEditable = esEditable;
    }

    public Boolean getEsEditable()
    {
        return esEditable;
    }

    public void setEsVisible(Boolean esVisible)
    {
        this.esVisible = esVisible;
    }

    public Boolean getEsVisible()
    {
        return esVisible;
    }


    public void setPrincipioTxt(String PrincipioTxt)
    {
        this.principioTxt = PrincipioTxt;
    }

    public String getPrincipioTxt()
    {
        return principioTxt;
    }

    public void setIdPrincipio(Integer idPrincipio)
    {
        this.idPrincipio = idPrincipio;
    }

    public Integer getIdPrincipio()
    {
        return idPrincipio;
    }

    public void setIdResponsable(Integer idResponsable)
    {
        this.idResponsable = idResponsable;
    }

    public Integer getIdResponsable()
    {
        return idResponsable;
    }

    public void setIdCriterio(Integer idCriterio)
    {
        this.idCriterio = idCriterio;
    }

    public Integer getIdCriterio()
    {
        return idCriterio;
    }

    public void setCriterioTxt(String criterioTxt)
    {
        this.criterioTxt = criterioTxt;
    }

    public String getCriterioTxt()
    {
        return criterioTxt;
    }


   /* public void setEsConsulta(Boolean esConsulta) {
        this.esConsulta = esConsulta;
    }

    public Boolean getEsConsulta() {
        return esConsulta;
    }*/
    
   @Override
     public String toString() {
       
       StringBuilder obj = new StringBuilder();
        obj.append("ID Pregunta = ").append(this.getIdPregunta()).append("\n");
        obj.append("Tipo Pregunta= ").append(this.getTipoPregunta() ).append("\n");
         obj.append("ID Principio= ").append(this.getPrincipioTxt() ).append("\n");
         obj.append("ID Responsable = ").append(this.getIdResponsable() ).append("\n");
         obj.append("ID Criterio= ").append(this.getIdCriterio() ).append("\n");
         obj.append("Texto Pregunta = ").append(this.getPrincipioTxt() ).append("\n");
         obj.append("Opciones Pregunta = ").append(this.getOpcionesPregunta() ).append("\n");
         obj.append("Respuesta Pregunta = ").append(this.getRespuestaPregunta() ).append("\n");
         obj.append("Justificacion Pregunta = ").append(this.getJustificacionPregunta() ).append("\n");
         obj.append("Principio TXT = ").append(this.getPrincipioTxt() ).append("\n");
         obj.append("Criterio TXT = ").append(this.getCriterioTxt() ).append("\n");
         obj.append("Nombre Doc = ").append(this.getNombreDoc() ).append("\n");
         obj.append("ID Documento = ").append(this.getIdDocumento() ).append("\n");
         obj.append("Es Editable = ").append(this.getEsEditable() ).append("\n");
         obj.append("Es Visible = ").append(this.getEsVisible() ).append("\n");
         obj.append("ID Repuesta HTML = ").append(this.getRepuestaHTMLId() ).append("\n");
         obj.append("ID Justificacion HTML= ").append(this.getJustificacionHTMLId() ).append("\n");
       return obj.toString(); 
     }
}
