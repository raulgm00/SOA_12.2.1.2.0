package org.bcie.fenix.view.documentos;

import java.util.List;

public class TreeItem {
    
    private List<TreeItem> children = null; //esta variable guarda el árbol
    
    private String tipoElemento = null; //file o folder
    
    // Mapeo para datos de documento (provenientes del servicio)
    private oracle.jbo.domain.Number idDocumento = null;
    private oracle.jbo.domain.Number idTipoDoc = null;
    private String tipoDoc = null;
    private String etapaDoc = null;
    private String codigoDoc = null;
    private oracle.jbo.domain.Number idExterno = null;
    private oracle.jbo.domain.Number idOperacion = null;
    private String nombreDoc = null;
    private String fileName = null;
    private String mimeType = null;
    private oracle.jbo.domain.Number idAdjunto = null;
    private Boolean esJustificacion = null;
    private String resumenDoc = null;
    private String fechaDoc = null;
    private String fechaRegistro = null;
    private String tareaDoc = null;
    private String creadoPor = null;
    private String modificadoPor = null;
    private Boolean esModificable = null;
    private Boolean esBorrable = null;
    private oracle.jbo.domain.Number idTarea = null;


    public void setIdDocumento(oracle.jbo.domain.Number idDocumento) {
        this.idDocumento = idDocumento;
    }

    public oracle.jbo.domain.Number getIdDocumento() {
        return idDocumento;
    }
    
    public void setIdExterno(oracle.jbo.domain.Number idExterno) {
        this.idExterno = idExterno;
    }

    public oracle.jbo.domain.Number getIdExterno() {
        return idExterno;
    }
    
    public void setIdOperacion(oracle.jbo.domain.Number idOperacion) {
        this.idOperacion = idOperacion;
    }

    public oracle.jbo.domain.Number getIdOperacion() {
        return idOperacion;
    }
    
    public void setCreadoPor(String usuario) {
        this.creadoPor = usuario;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setChildren(List<TreeItem> children) {
        this.children = children;
    }

    public List<TreeItem> getChildren() {
        return children;
    }

    public void setCodigoDoc(String codigo) {
        this.codigoDoc = codigo;
    }

    public String getCodigoDoc() {
        return codigoDoc;
    }
    
    public void setTipoElemento(String tipoElemento) {
        this.tipoElemento = tipoElemento;
    }

    public String getTipoElemento() {
        return tipoElemento;
    }
    
    public String getNombreDoc() {
        return nombreDoc;
    }

    public void setNombreDoc(String nombre) {
        this.nombreDoc = nombre;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
    
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }
    
    public void setIdAdjunto(oracle.jbo.domain.Number idAdjunto) {
        this.idAdjunto = idAdjunto;
    }

    public oracle.jbo.domain.Number getIdAdjunto() {
        return idAdjunto;
    }
    
    public void setEsJustificacion(Boolean esJustificacion) {
        this.esJustificacion = esJustificacion;
    }

    public Boolean getEsJustificacion() {
        return esJustificacion;
    }
    
    public void setEtapaDoc(String etapaDoc) {
        this.etapaDoc = etapaDoc;
    }

    public String getEtapaDoc() {
        return etapaDoc;
    }
    
    public void setIdTipoDoc(oracle.jbo.domain.Number idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public oracle.jbo.domain.Number getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setTipoDoc(String tipo) {
        this.tipoDoc = tipo;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }
    
    public void setTareaDoc(String tareaDoc) {
        this.tareaDoc = tareaDoc;
    }

    public String getTareaDoc() {
        return tareaDoc;
    }
        
    public void setFechaDoc(String fechaDoc) {
        this.fechaDoc = fechaDoc;
    }

    public String getFechaDoc() {
        return fechaDoc;
    }
    
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }
    
    public void setEsModificable(Boolean esModificable) {
        this.esModificable = esModificable;
    }

    public Boolean getEsModificable() {
        return esModificable;
    }

    public void setEsBorrable(Boolean esBorrable) {
        this.esBorrable = esBorrable;
    }

    public Boolean getEsBorrable() {
        return esBorrable;
    }
    
    public void setIdTarea(oracle.jbo.domain.Number idTarea) {
        this.idTarea = idTarea;
    }

    public oracle.jbo.domain.Number getIdTarea() {
        return idTarea;
    }

    public void setResumenDoc(String resumenDoc) {
        this.resumenDoc = resumenDoc;
    }

    public String getResumenDoc() {
        return resumenDoc;
    }
    
    public TreeItem(String tipoElemento, String nombreDoc) {
        
        setTipoElemento(tipoElemento);
        setNombreDoc(nombreDoc);
    }
    
    public TreeItem(String tipoElemento, oracle.jbo.domain.Number idDocumento, String nombreDoc, String fileName,
                    String mimeType, oracle.jbo.domain.Number idAdjunto, Boolean esJustificacion, String etapaDoc, 
                    String codigoDoc, oracle.jbo.domain.Number idExterno, oracle.jbo.domain.Number idOperacion,
                    String creadoPor, oracle.jbo.domain.Number idTipoDoc, String tipoDoc, String tareaDoc, 
                    String fechaDoc, String fechaRegistro, String modificadoPor, String resumenDoc, 
                    Boolean esModificable, Boolean esBorrable, oracle.jbo.domain.Number idTarea) {
        
        setTipoElemento(tipoElemento);
        setIdDocumento(idDocumento);
        setNombreDoc(nombreDoc);
        setFileName(fileName);
        setMimeType(mimeType);
        setIdAdjunto(idAdjunto);
        setEsJustificacion(esJustificacion);
        setEtapaDoc(etapaDoc);
        setCodigoDoc(codigoDoc);
        setIdExterno(idExterno);
        setIdOperacion(idOperacion);
        setCreadoPor(creadoPor);
        setIdTipoDoc(idTipoDoc);
        setTipoDoc(tipoDoc);
        setTareaDoc(tareaDoc);
        setFechaDoc(fechaDoc);
        setFechaRegistro(fechaRegistro);
        setModificadoPor(modificadoPor);
        setResumenDoc(resumenDoc);
        setEsModificable(esModificable);
        setEsBorrable(esBorrable);
        setIdTarea(idTarea);
    }
}
