package org.bcie.fenix.view.matriztcc;

import java.io.Serializable;

import java.util.Map;

import org.bcie.fenix.common.FenixConstants;


public class CondicionMangedBean implements Serializable{
    @SuppressWarnings("compatibility:-7583727580301304656")
    private static final long serialVersionUID = -6647063380773689097L;

    AtributosTCC descipcionCondicion;
    AtributosTCC nombreCondicion;
    AtributosTCC categoria;
    AtributosTCC idTipocontrol;
    AtributosTCC eventos;
    AtributosTCC idTipoFechaInicio;
    AtributosTCC fechaInicio;
    AtributosTCC idTipoPlazo;
    AtributosTCC plazo;
    AtributosTCC fechaFinal;
    AtributosTCC lineaCredito;
    AtributosTCC observacionesCondicion;
    AtributosTCC idCondicion;
    AtributosTCC fuenteCondicion;
    AtributosTCC idTcaTipoDesembolso;
    
    private Boolean mostrarIdTcaTipoDesembolso;
    
    public CondicionMangedBean() {
        super();
        
        descipcionCondicion= new AtributosTCC();
        nombreCondicion= new AtributosTCC();
        categoria= new AtributosTCC();
        idTipocontrol= new AtributosTCC();
        eventos= new AtributosTCC();
        idTipoFechaInicio= new AtributosTCC();
        fechaInicio= new AtributosTCC();
        idTipoPlazo= new AtributosTCC();
        plazo= new AtributosTCC();
        fechaFinal= new AtributosTCC();
        lineaCredito= new AtributosTCC();
        observacionesCondicion= new AtributosTCC();
        idCondicion= new AtributosTCC();
        fuenteCondicion= new AtributosTCC();
        idTcaTipoDesembolso = new AtributosTCC();

        
    }
    
    public String getDescipcionCondicionEtiqueta() {
        return descipcionCondicion.getEtiqueta();
    }
    public Boolean getDescipcionCondicionMandatorio() {
        return descipcionCondicion.getEsMandatorio();
    }
    public Boolean getDescipcionCondicionVisible() {
        return descipcionCondicion.getEsVisible();
    }
    public Boolean getDescipcionCondicionLectura() {
        return descipcionCondicion.getEsSoloLectura();
    }
    public Integer getDescipcionCondicionOrdenamiento() {
        return descipcionCondicion.getOrdenamiento();
    }
    public String getDescipcionCondicionColumna() {
        return descipcionCondicion.getColumna();
    }
    public String getDescipcionCondicionTipoValor() {
        return descipcionCondicion.getTipoValor();
    }

    public String getNombreCondicionEtiqueta() {
        return nombreCondicion.getEtiqueta();
    }
    public Boolean getNombreCondicionMandatorio() {
        return nombreCondicion.getEsMandatorio();
    }
    public Boolean getNombreCondicionVisible() {
        return nombreCondicion.getEsVisible();
    }
    public Boolean getNombreCondicionLectura() {
        return nombreCondicion.getEsSoloLectura();
    }
    public Integer getNombreCondicionOrdenamiento() {
        return nombreCondicion.getOrdenamiento();
    }
    public String getNombreCondicionColumna() {
        return "descipcionComision";
    }
    public String getNombreCondicionTipoValor() {
        return nombreCondicion.getTipoValor();
    }
    
    public String getCategoriaEtiqueta() {
        return categoria.getEtiqueta();
    }
    public Boolean getCategoriaMandatorio() {
        return categoria.getEsMandatorio();
    }
    public Boolean getCategoriaVisible() {
        return categoria.getEsVisible();
    }
    public Boolean getCategoriaLectura() {
        return categoria.getEsSoloLectura();
    }
    public Integer getCategoriaOrdenamiento() {
        return categoria.getOrdenamiento();
    }
    public String getCategoriaColumna() {
        return categoria.getColumna();
    }
    public String getCategoriaTipoValor() {
        return categoria.getTipoValor();
    }
    
    public String getIdTipocontrolEtiqueta() {
        return idTipocontrol.getEtiqueta();
    }
    public Boolean getIdTipocontrolMandatorio() {
        return idTipocontrol.getEsMandatorio();
    }
    public Boolean getIdTipocontrolVisible() {
        return idTipocontrol.getEsVisible();
    }
    public Boolean getIdTipocontrolLectura() {
        return idTipocontrol.getEsSoloLectura();
    }
    public Integer getIdTipocontrolOrdenamiento() {
        return idTipocontrol.getOrdenamiento();
    }
    public String getIdTipocontrolColumna() {
        return idTipocontrol.getColumna();
    }
    public String getIdTipocontrolTipoValor() {
        return idTipocontrol.getTipoValor();
    }
    
    public String getEventosEtiqueta() {
        return eventos.getEtiqueta();
    }
    public Boolean getEventosMandatorio() {
        return eventos.getEsMandatorio();
    }
    public Boolean getEventosVisible() {
        return eventos.getEsVisible();
    }
    public Boolean getEventosLectura() {
        return eventos.getEsSoloLectura();
    }
    public Integer getEventosOrdenamiento() {
        return eventos.getOrdenamiento();
    }
    public String getEventosColumna() {
        return eventos.getColumna();
    }
    public String getEventosTipoValor() {
        return eventos.getTipoValor();
    }
    
    public String getIdTipoFechaInicioEtiqueta() {
        return idTipoFechaInicio.getEtiqueta();
    }
    public Boolean getIdTipoFechaInicioMandatorio() {
        return idTipoFechaInicio.getEsMandatorio();
    }
    public Boolean getIdTipoFechaInicioVisible() {
        return idTipoFechaInicio.getEsVisible();
    }
    public Boolean getIdTipoFechaInicioLectura() {
        return idTipoFechaInicio.getEsSoloLectura();
    }
    public Integer getIdTipoFechaInicioOrdenamiento() {
        return idTipoFechaInicio.getOrdenamiento();
    }
    public String getIdTipoFechaInicioColumna() {
        return idTipoFechaInicio.getColumna();
    }
    public String getIdTipoFechaInicioTipoValor() {
        return idTipoFechaInicio.getTipoValor();
    }
    
    public String getFechaInicioEtiqueta() {
        return fechaInicio.getEtiqueta();
    }
    public Boolean getFechaInicioMandatorio() {
        return fechaInicio.getEsMandatorio();
    }
    public Boolean getFechaInicioVisible() {
        return fechaInicio.getEsVisible();
    }
    public Boolean getFechaInicioLectura() {
        return fechaInicio.getEsSoloLectura();
    }
    public Integer getFechaInicioOrdenamiento() {
        return fechaInicio.getOrdenamiento();
    }
    public String getFechaInicioColumna() {
        return fechaInicio.getColumna();
    }
    public String getFechaInicioTipoValor() {
        return fechaInicio.getTipoValor();
    }
    
    public String getIdTipoPlazoEtiqueta() {
        return idTipoPlazo.getEtiqueta();
    }
    public Boolean getIdTipoPlazoMandatorio() {
        return idTipoPlazo.getEsMandatorio();
    }
    public Boolean getIdTipoPlazoVisible() {
        return idTipoPlazo.getEsVisible();
    }
    public Boolean getIdTipoPlazoLectura() {
        return idTipoPlazo.getEsSoloLectura();
    }
    public Integer getIdTipoPlazoOrdenamiento() {
        return idTipoPlazo.getOrdenamiento();
    }
    public String getIdTipoPlazoColumna() {
        return idTipoPlazo.getColumna();
    }
    public String getIdTipoPlazoTipoValor() {
        return idTipoPlazo.getTipoValor();
    }
    
    public String getPlazoEtiqueta() {
        return plazo.getEtiqueta();
    }
    public Boolean getPlazoMandatorio() {
        return plazo.getEsMandatorio();
    }
    public Boolean getPlazoVisible() {
        return plazo.getEsVisible();
    }
    public Boolean getPlazoLectura() {
        return plazo.getEsSoloLectura();
    }
    public Integer getPlazoOrdenamiento() {
        return plazo.getOrdenamiento();
    }
    public String getPlazoColumna() {
        return plazo.getColumna();
    }
    public String getPlazoTipoValor() {
        return plazo.getTipoValor();
    }
    
    public String getFechaFinalEtiqueta() {
        return fechaFinal.getEtiqueta();
    }
    public Boolean getFechaFinalMandatorio() {
        return fechaFinal.getEsMandatorio();
    }
    public Boolean getFechaFinalVisible() {
        return fechaFinal.getEsVisible();
    }
    public Boolean getFechaFinalLectura() {
        return fechaFinal.getEsSoloLectura();
    }
    public Integer getFechaFinalOrdenamiento() {
        return fechaFinal.getOrdenamiento();
    }
    public String getFechaFinalColumna() {
        return fechaFinal.getColumna();
    }
    public String getFechaFinalTipoValor() {
        return fechaFinal.getTipoValor();
    }

    public String getLineaCreditoEtiqueta() {
        return lineaCredito.getEtiqueta();
    }
    public Boolean getLineaCreditoMandatorio() {
        return lineaCredito.getEsMandatorio();
    }
    public Boolean getLineaCreditoVisible() {
        return lineaCredito.getEsVisible();
    }
    public Boolean getLineaCreditoLectura() {
        return lineaCredito.getEsSoloLectura();
    }
    public Integer getLineaCreditoOrdenamiento() {
        return lineaCredito.getOrdenamiento();
    }
    public String getLineaCreditoColumna() {
        return lineaCredito.getColumna();
    }
    public String getLineaCreditoTipoValor() {
        return lineaCredito.getTipoValor();
    }
    
    public String getObservacionesCondicionEtiqueta() {
        return observacionesCondicion.getEtiqueta();
    }
    public Boolean getObservacionesCondicionMandatorio() {
        return observacionesCondicion.getEsMandatorio();
    }
    public Boolean getObservacionesCondicionVisible() {
        return observacionesCondicion.getEsVisible();
    }
    public Boolean getObservacionesCondicionLectura() {
        return observacionesCondicion.getEsSoloLectura();
    }
    public Integer getObservacionesCondicionOrdenamiento() {
        return observacionesCondicion.getOrdenamiento();
    }
    public String getObservacionesCondicionColumna() {
        return observacionesCondicion.getColumna();
    }
    public String getObservacionesCondicionTipoValor() {
        return observacionesCondicion.getTipoValor();
    }
    
    public String getFuenteCondicionEtiqueta() {
        return fuenteCondicion.getEtiqueta();
    }
    public Boolean getFuenteCondicioMandatorio() {
        return fuenteCondicion.getEsMandatorio();
    }
    public Boolean getFuenteCondicioVisible() {
        return fuenteCondicion.getEsVisible();
    }
    public Boolean getFuenteCondicioLectura() {
        return fuenteCondicion.getEsSoloLectura();
    }
    public Integer getFuenteCondicioOrdenamiento() {
        return fuenteCondicion.getOrdenamiento();
    }
    public String getFuenteCondicioColumna() {
        return fuenteCondicion.getColumna();
    }
    public String getFuenteCondicioTipoValor() {
        return fuenteCondicion.getTipoValor();
    }
    public String getIdTcaTipoDesembolsoEtiqueta() {
        return idTcaTipoDesembolso.getEtiqueta();
    }
    public Boolean getIdTcaTipoDesembolsoMandatorio() {
        return idTcaTipoDesembolso.getEsMandatorio();
    }
    public Boolean getIdTcaTipoDesembolsoVisible() {
        return idTcaTipoDesembolso.getEsVisible();
    }
    public Boolean getIdTcaTipoDesembolsoLectura() {
        return idTcaTipoDesembolso.getEsSoloLectura();
    }
    public Integer getIdTcaTipoDesembolsoOrdenamiento() {
        return idTcaTipoDesembolso.getOrdenamiento();
    }
    public String getIdTcaTipoDesembolsoColumna() {
        return idTcaTipoDesembolso.getColumna();
    }
    public String getIdTcaTipoDesembolsoTipoValor() {
        return idTcaTipoDesembolso.getTipoValor();
    }

    public void setMostrarIdTcaTipoDesembolso(Boolean mostrarIdTcaTipoDesembolso) {
        this.mostrarIdTcaTipoDesembolso = mostrarIdTcaTipoDesembolso;
    }

    public Boolean getMostrarIdTcaTipoDesembolso() {
        return mostrarIdTcaTipoDesembolso;
    }

    void setAtributeValues(Map<String, Map> configuracionAtributosTCCMap) {
        descipcionCondicion.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_DESCRIPCION));
        nombreCondicion.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_NOMBRE));
        categoria.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_TRE_CATEGORIA_CONDICION));
        eventos.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_TRE_TCA_EVENTO_CONDICION));
        idTipoFechaInicio.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_ID_TCA_TIPO_FECHA_INICIO));
        fechaInicio.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_FECHA_INICIO));
        idTipoPlazo.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_ID_TCA_FRECUENCIA_PLAZO));
        plazo.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_PLAZO));
        fechaFinal.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_FECHA_FINAL));
        lineaCredito.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_TRE_LINEA_CREDITO_TCC));
        observacionesCondicion.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_OBSERVACION_CONDICION));
        fuenteCondicion.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_TRE_FUENTE_CONDICION));
        idTipocontrol.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_ID_TCA_CONTROL_CONDICION));
        idCondicion.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID));
        idTcaTipoDesembolso.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_ID_TCA_TIPO_DESEMBOLSO));
    }
    
}
