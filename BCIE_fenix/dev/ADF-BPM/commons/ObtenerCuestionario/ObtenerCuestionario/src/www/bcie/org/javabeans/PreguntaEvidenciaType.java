/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www.bcie.org.javabeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author latbc
 */
public class PreguntaEvidenciaType implements Serializable{
    private Long idPregunta;
    private String pregunta;
    private String respuesta;
    private String justificacion;
    private Long evidencia;
    private String usuario;
    private Date fechaRegistro;
    private Long idDocumento;
    private String filename;
    private String mimetype;
    private String seccion;
    private String secDescripcion;
    private String subSeccion;
    private String subSecDescripcion;
    private String tipoPregunta;
    private List <String> opciones;
    private String idProducto;
    private String numOrdenCriterio;
    private String numOrdenPrincipio;
    
    

    public PreguntaEvidenciaType() {
    }

    public PreguntaEvidenciaType( String pregunta, String respuesta, String justificacion) {        
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.justificacion = justificacion;
    }
    
    public PreguntaEvidenciaType( Long idPregunta,String pregunta, String respuesta, String justificacion, Long evidencia, String filename,List <String> opciones) {        
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.justificacion = justificacion;
        this.evidencia = evidencia;
        this.filename = filename;
        this.opciones=opciones;
    }

    public PreguntaEvidenciaType(String pregunta, String respuesta, String justificacion, String seccion, String subSeccion) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.justificacion = justificacion;
        this.seccion = seccion;
        this.subSeccion = subSeccion;
    }
    
      public PreguntaEvidenciaType(Long idPregunta, String pregunta, String respuesta, String justificacion, String seccion, String subSeccion, List <String> opciones) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.justificacion = justificacion;
        this.seccion = seccion;
        this.subSeccion = subSeccion;
        this.opciones= opciones;
    }

 
    public PreguntaEvidenciaType(Long idPregunta, String pregunta, String respuesta, String justificacion, Long evidencia, String usuario, Date fechaRegistro, Long idDocumento, String filename, String mimetype, String seccion, String subSeccion, String tipoPregunta) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.justificacion = justificacion;
        this.evidencia = evidencia;
        this.usuario = usuario;
        this.fechaRegistro = fechaRegistro;
        this.idDocumento = idDocumento;
        this.filename = filename;
        this.mimetype = mimetype;
        this.seccion = seccion;
        this.subSeccion = subSeccion;
        this.tipoPregunta = tipoPregunta;
    }
      

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Long getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(Long evidencia) {
        this.evidencia = evidencia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    public String getSecDescripcion() {
        return secDescripcion;
    }

    public void setSecDescripcion(String secDescripcion) {
        this.secDescripcion = secDescripcion;
    }
    

    public String getSubSeccion() {
        return subSeccion;
    }
    
    public void setSubSeccion(String subSeccion) {
        this.subSeccion = subSeccion;
    }

    public String getSubSecDescripcion() {
        return subSecDescripcion;
    }
    
    public void setSubSecDescripcion(String subSecDescripcion) {
        this.subSecDescripcion = subSecDescripcion;
    }   

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }
    
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    
    public String getNumOrdenCriterio() {
        return numOrdenCriterio;
    }

    public void setNumOrdenCriterio(String numOrdenCriterio) {
        this.numOrdenCriterio = numOrdenCriterio;
    }
    ;
        
    public String getNumOrdenPrincipio() {
        return numOrdenPrincipio;
    }

    public void setNumOrdenPrincipio(String numOrdenPrincipio) {
        this.numOrdenPrincipio = numOrdenPrincipio;
    }
}
