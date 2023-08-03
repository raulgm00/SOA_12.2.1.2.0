package org.bcie.fenix.view.matriztcc;

import java.io.Serializable;

import java.util.Map;

import oracle.adf.share.logging.ADFLogger;


public class AtributosTCC implements Serializable{
    @SuppressWarnings("compatibility:-7138059074865397170")
    private static final long serialVersionUID = -5617720119126595369L;
    
    /**
     * Log de la aplicacion
     */
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(AtributosTCC.class);
    
    private Integer id;
    private String columna;
    private Boolean esVisible;
    private Boolean esSoloLectura;
    private Boolean esMandatorio;
    private Integer ordenamiento;
    private String etiqueta;
    private Integer idTcaTermino;
    private Integer idTcaComision;
    private Integer idTcaCondicion;
    private String tipoValor;
    
    
    public AtributosTCC() {
        super();
        esVisible = false;
        esMandatorio = false;
        etiqueta = "";
        esSoloLectura = true;
    }

  public void setEsSoloLectura(Boolean esSoloLectura)
  {
    this.esSoloLectura = esSoloLectura;
  }

  public Boolean getEsSoloLectura()
  {
    return esSoloLectura;
  }

  public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public Boolean getEsVisible() {
        return esVisible;
    }

    public void setEsVisible(Boolean esVisible) {
        this.esVisible = esVisible;
    }

    public Boolean getEsMandatorio() {
        return esMandatorio;
    }

    public void setEsMandatorio(Boolean esMandatorio) {
        this.esMandatorio = esMandatorio;
    }

    public Integer getOrdenamiento() {
        return ordenamiento;
    }

    public void setOrdenamiento(Integer ordenamiento) {
        this.ordenamiento = ordenamiento;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Integer getIdTcaTermino() {
        return idTcaTermino;
    }

    public void setIdTcaTermino(Integer idTcaTermino) {
        this.idTcaTermino = idTcaTermino;
    }

    public Integer getIdTcaComision() {
        return idTcaComision;
    }

    public void setIdTcaComision(Integer idTcaComision) {
        this.idTcaComision = idTcaComision;
    }

    public Integer getIdTcaCondicion() {
        return idTcaCondicion;
    }

    public void setIdTcaCondicion(Integer idTcaCondicion) {
        this.idTcaCondicion = idTcaCondicion;
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    void setValues(Map valuesMap)
    {
      if(null != valuesMap)
      {
        Integer valor0=0;
        Integer esObligatorio=(Integer)valuesMap.get("EsObligatorio");
        Integer esLectura=(Integer) valuesMap.get("SoloLectura");
        
        if(null==esObligatorio || esObligatorio.compareTo(valor0)==0){
                esMandatorio=false;
            }
        else{
                esMandatorio=true;
            }
          if(null==esLectura || esLectura.compareTo(valor0)==0){
                  esSoloLectura=false;
              }
          else{
                  esSoloLectura=true;
              }
        esVisible = true;
        id = (Integer) valuesMap.get("Id");
        columna = (String) valuesMap.get("Columna");
      //  esSoloLectura = (Boolean) valuesMap.get("SoloLectura");
      //  esMandatorio = (Boolean) valuesMap.get("EsObligatorio");
        ordenamiento = (Integer) valuesMap.get("Ordenamiento");
        etiqueta = (String) valuesMap.get("Etiqueta");
        idTcaTermino = (Integer) valuesMap.get("IdTcaTermino");
        idTcaComision = (Integer) valuesMap.get("IdTcaComision");
        idTcaCondicion = (Integer) valuesMap.get("IdTcaCondicion");
        tipoValor = (String) valuesMap.get("TipoValor");
        
          /*
          LOGGER.warning("esVisible: "+esVisible);
          LOGGER.warning("id: "+id);
          LOGGER.warning("columna: "+columna);
          LOGGER.warning("esSoloLectura: "+esSoloLectura);
          LOGGER.warning("esMandatorio: "+esMandatorio);
          LOGGER.warning("etiqueta: "+etiqueta);
          LOGGER.warning("ordenamiento: "+ordenamiento);
          LOGGER.warning("idTcaComision: "+idTcaComision);
          LOGGER.warning("idTcaCondicion: "+idTcaCondicion);
          LOGGER.warning("IdTcaTermino: "+idTcaTermino);
          */
      }
    }
}
