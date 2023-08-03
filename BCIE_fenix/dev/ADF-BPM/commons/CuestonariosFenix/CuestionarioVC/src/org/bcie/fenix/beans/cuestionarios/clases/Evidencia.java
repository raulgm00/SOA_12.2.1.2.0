package org.bcie.fenix.beans.cuestionarios.clases;

import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;

import java.util.Date;

import oracle.adf.share.logging.ADFLogger;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class Evidencia implements Serializable {
    @SuppressWarnings("compatibility:7885385265704466684")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;

    private Long idPregunta;
    private transient UploadedFile evidenciaAdjunto;
    private transient InputStream evidenciaInputStream;
    private String nombreEvidencia;
    private Date fechaArchivo;
    private String codigoArchivo;
    private String resumenArchivo;

    public Evidencia() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setEvidenciaAdjunto(UploadedFile evidenciaAdjunto) {
        this.evidenciaAdjunto = evidenciaAdjunto;
        logger.warning("Dentro de setEvidenciaAdjunto");
        if (evidenciaAdjunto != null) {
            logger.warning("fileName :" + getEvidenciaAdjunto().getFilename());
            this.setNombreEvidencia(getEvidenciaAdjunto().getFilename()); // Valor por default para el nombre del archivo
            logger.warning("valor nombreEvidencia :" + this.getNombreEvidencia());

            try {
                this.evidenciaInputStream = evidenciaAdjunto.getInputStream();
            } catch (IOException e) {
                logger.log(ADFLogger.ERROR, e.getMessage());
            }
        } else {
            logger.warning("evidenciaAdjunto es null");
        }

        logger.warning("Fuera de setEvidenciaAdjunto");
    }

    public UploadedFile getEvidenciaAdjunto() {
        return evidenciaAdjunto;
    }

    public void setEvidenciaInputStream(InputStream evidenciaInputStream) {
        this.evidenciaInputStream = evidenciaInputStream;
    }

    public InputStream getEvidenciaInputStream() {
        return evidenciaInputStream;
    }

    public void setNombreEvidencia(String nombreEvidencia) {
        this.nombreEvidencia = nombreEvidencia;
    }

    public String getNombreEvidencia() {
        return nombreEvidencia;
    }

    public void setFechaArchivo(Date fechaArchivo) {
        this.fechaArchivo = fechaArchivo;
    }

    public Date getFechaArchivo() {
        return fechaArchivo;
    }

    public void setCodigoArchivo(String codigoArchivo) {
        this.codigoArchivo = codigoArchivo;
    }

    public String getCodigoArchivo() {
        return codigoArchivo;
    }

    public void setResumenArchivo(String resumenArchivo) {
        this.resumenArchivo = resumenArchivo;
    }

    public String getResumenArchivo() {
        return resumenArchivo;
    }

    public void inicialisarFormularioEvidencia() {

        setEvidenciaAdjunto(null);
        setNombreEvidencia(null);
        setFechaArchivo(null);
        setCodigoArchivo(null);
        setResumenArchivo(null);

        oracle.jbo.domain.Date fechaActual =
            new oracle.jbo.domain.Date(new java.sql.Timestamp(System.currentTimeMillis()));
        setFechaArchivo(fechaActual.getValue());
    }
}
