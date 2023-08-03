package pa02admoncomisionesght.beans;

import java.io.Serializable;

import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

public class AtributosTCC implements Serializable{
    @SuppressWarnings("compatibility:8115586352842257052")
    private static final long serialVersionUID = 1L;
    
    private static ADFLogger logger = null;
    private Integer id;
    private String columna;
    private Boolean esVisible;
    private Boolean esSoloLectura;
    private Boolean esMandatorio;
    private Integer ordenamiento;
    private String etiqueta;
    private Integer idTcaComision;
    private String tipoValor;
    
    public AtributosTCC() {
        super();
        if(null == logger){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
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

      public Integer getIdTcaComision() {
          return idTcaComision;
      }

      public void setIdTcaComision(Integer idTcaComision) {
          this.idTcaComision = idTcaComision;
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
          idTcaComision = (Integer) valuesMap.get("IdTcaComision");
          tipoValor = (String) valuesMap.get("TipoValor");            
        }
      }
}
