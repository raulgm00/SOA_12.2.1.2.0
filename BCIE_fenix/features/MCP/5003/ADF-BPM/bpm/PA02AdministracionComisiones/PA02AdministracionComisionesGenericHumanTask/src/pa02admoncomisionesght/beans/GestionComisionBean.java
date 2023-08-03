package pa02admoncomisionesght.beans;

import java.io.Serializable;

import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.domain.Timestamp;

import org.bcie.fenix.common.FenixConstants;

public class GestionComisionBean implements Serializable{
    @SuppressWarnings("compatibility:6209874956623166660")
    private static final long serialVersionUID = 1L;
    
    private static ADFLogger logger = null;
    
    AtributosTCC descipcionComision;
    AtributosTCC nombreComision;
    AtributosTCC idTipoMoneda;
    AtributosTCC idTipoMomentoCobro;
    AtributosTCC idTcaComision;
    AtributosTCC idTipoFrecuencia;
    AtributosTCC idTipoFondo;
    AtributosTCC idTipoMontoBase;
    AtributosTCC idTipoTasa;
    AtributosTCC frecuenciaPago;
    AtributosTCC montoBase;
    AtributosTCC montoComision;
    AtributosTCC fechaVencimiento;
    AtributosTCC idEstadoTcc;
    AtributosTCC idSubestadoTcc;
    AtributosTCC comisioncompartida;
    AtributosTCC idBaseCalculo;
    AtributosTCC fechaInicioComision;
    AtributosTCC idComision;
    AtributosTCC porcentaje;
    AtributosTCC fechaValor;
    AtributosTCC fechaInicioCapital;
    
    private Timestamp fechaFlexcubeActual;
    
    public GestionComisionBean() {
        super();
        if(null == logger){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
        descipcionComision = new AtributosTCC();
        nombreComision = new AtributosTCC();
        idTipoMoneda = new AtributosTCC();
        idTipoMomentoCobro = new AtributosTCC();
        idTcaComision = new AtributosTCC();
        idTipoFrecuencia = new AtributosTCC();
        idTipoFondo = new AtributosTCC();
        idTipoMontoBase = new AtributosTCC();
        idTipoTasa = new AtributosTCC();
        frecuenciaPago = new AtributosTCC();
        montoBase = new AtributosTCC();
        montoComision = new AtributosTCC();
        fechaVencimiento = new AtributosTCC();
        idEstadoTcc = new AtributosTCC();
        idSubestadoTcc = new AtributosTCC();
        comisioncompartida = new AtributosTCC();
        idBaseCalculo = new AtributosTCC();
        fechaInicioComision = new AtributosTCC();
        idComision = new AtributosTCC();
        porcentaje = new AtributosTCC();
        fechaValor = new AtributosTCC();
        fechaInicioCapital = new AtributosTCC();
    }
    
    public String getDescipcionComisionEtiqueta() {
        
        return descipcionComision.getEtiqueta();
    }
    public Boolean getDescipcionComisionMandatorio() {
        return descipcionComision.getEsMandatorio();
    }
    public Boolean getDescipcionComisionVisible() {
        return descipcionComision.getEsVisible();
    }
    public Boolean getDescipcionComisionLectura() {
        return descipcionComision.getEsSoloLectura();
    }
    public Integer getDescipcionComisionOrdenamiento() {
        return descipcionComision.getOrdenamiento();
    }
    public String getDescipcionComisionColumna() {
        return descipcionComision.getColumna();
    }
    public String getDescipcionComisionTipoValor() {
        return descipcionComision.getTipoValor();
    }

    public String getNombreComisionEtiqueta() {
        return nombreComision.getEtiqueta();
    }
    public Boolean getNombreComisionMandatorio() {
        return nombreComision.getEsMandatorio();
    }
    public Boolean getNombreComisionVisible() {
        return nombreComision.getEsVisible();
    }
    public Boolean getNombreComisionLectura() {
        return nombreComision.getEsSoloLectura();
    }
    public Integer getNombreComisionOrdenamiento() {
        return nombreComision.getOrdenamiento();
    }
    public String getNombreComisionColumna() {
        return nombreComision.getColumna();
    }
    public String getNombreComisionTipoValor() {
        return nombreComision.getTipoValor();
    }
    
    public String getIdTipoMonedaEtiqueta() {
        return idTipoMoneda.getEtiqueta();
    }
    public Boolean getIdTipoMonedaMandatorio() {
        return idTipoMoneda.getEsMandatorio();
    }
    public Boolean getIdTipoMonedaVisible() {
        return idTipoMoneda.getEsVisible();
    }
    public Boolean getIdTipoMonedaLectura() {
        return idTipoMoneda.getEsSoloLectura();
    }
    public Integer getIdTipoMonedaOrdenamiento() {
        return idTipoMoneda.getOrdenamiento();
    }
    public String getIdTipoMonedaColumna() {
        return idTipoMoneda.getColumna();
    }
    public String getIdTipoMonedaTipoValor() {
        return idTipoMoneda.getTipoValor();
    }
    
    public String getIdTipoMomentoCobroEtiqueta() {
        return idTipoMomentoCobro.getEtiqueta();
    }
    public Boolean getIdTipoMomentoCobroMandatorio() {
        return idTipoMomentoCobro.getEsMandatorio();
    }
    public Boolean getIdTipoMomentoCobroVisible() {
        return idTipoMomentoCobro.getEsVisible();
    }
    public Boolean getIdTipoMomentoCobroLectura() {
        return idTipoMomentoCobro.getEsSoloLectura();
    }
    public Integer getIdTipoMomentoCobroOrdenamiento() {
        return idTipoMomentoCobro.getOrdenamiento();
    }
    public String getIdTipoMomentoCobroColumna() {
        return idTipoMomentoCobro.getColumna();
    }
    public String getIdTipoMomentoCobroTipoValor() {
        return idTipoMomentoCobro.getTipoValor();
    }
    
    public String getIdTcaComisionEtiqueta() {
        return idTcaComision.getEtiqueta();
    }
    public Boolean getIdTcaComisionMandatorio() {
        return idTcaComision.getEsMandatorio();
    }
    public Boolean getIdTcaComisionVisible() {
        return idTcaComision.getEsVisible();
    }
    public Boolean getIdTcaComisionLectura() {
        return idTcaComision.getEsSoloLectura();
    }
    public Integer getIdTcaComisionOrdenamiento() {
        return idTcaComision.getOrdenamiento();
    }
    public String getIdTcaComisionColumna() {
        return idTcaComision.getColumna();
    }
    public String getIdTcaComisionTipoValor() {
        return idTcaComision.getTipoValor();
    }
    
    public String getIdTipoFrecuenciaEtiqueta() {
        return idTipoFrecuencia.getEtiqueta();
    }
    public Boolean getIdTipoFrecuenciaMandatorio() {
        return idTipoFrecuencia.getEsMandatorio();
    }
    public Boolean getIdTipoFrecuenciaVisible() {
        return idTipoFrecuencia.getEsVisible();
    }
    public Boolean getIdTipoFrecuenciaLectura() {
        return idTipoFrecuencia.getEsSoloLectura();
    }
    public Integer getIdTipoFrecuenciaOrdenamiento() {
        return idTipoFrecuencia.getOrdenamiento();
    }
    public String getIdTipoFrecuenciaColumna() {
        return idTipoFrecuencia.getColumna();
    }
    public String getIdTipoFrecuenciaTipoValor() {
        return idTipoFrecuencia.getTipoValor();
    }
    
    public String getIdTipoFondoEtiqueta() {
        return idTipoFondo.getEtiqueta();
    }
    public Boolean getIdTipoFondoMandatorio() {
        return idTipoFondo.getEsMandatorio();
    }
    public Boolean getIdTipoFondoVisible() {
        return idTipoFondo.getEsVisible();
    }
    public Boolean getIdTipoFondoLectura() {
        return idTipoFondo.getEsSoloLectura();
    }
    
    public Integer getIdTipoFondoOrdenamiento() {
        return idTipoFondo.getOrdenamiento();
    }
    public String getIdTipoFondoColumna() {
        return idTipoFondo.getColumna();
    }
    public String getIdTipoFondoTipoValor() {
        return idTipoFondo.getTipoValor();
    }
    
    public String getIdTipoMontoBaseEtiqueta() {
        return idTipoMontoBase.getEtiqueta();
    }
    public Boolean getIdTipoMontoBaseMandatorio() {
        return idTipoMontoBase.getEsMandatorio();
    }
    public Boolean getIdTipoMontoBaseVisible() {
        return idTipoMontoBase.getEsVisible();
    }
    public Boolean getIdTipoMontoBaseLectura() {
        return idTipoMontoBase.getEsSoloLectura();
    }
    public Integer getIdTipoMontoBaseOrdenamiento() {
        return idTipoMontoBase.getOrdenamiento();
    }
    public String getIdTipoMontoBaseColumna() {
        return idTipoMontoBase.getColumna();
    }
    public String getIdTipoMontoBaseTipoValor() {
        return idTipoMontoBase.getTipoValor();
    }
    
    public String getIdTipoTasaEtiqueta() {
        return idTipoTasa.getEtiqueta();
    }
    public Boolean getIdTipoTasaMandatorio() {
        return idTipoTasa.getEsMandatorio();
    }
    public Boolean getIdTipoTasaVisible() {
        return idTipoTasa.getEsVisible();
    }
    public Boolean getIdTipoTasaLectura() {
        return idTipoTasa.getEsSoloLectura();
    }
    public Integer getIdTipoTasaOrdenamiento() {
        return idTipoTasa.getOrdenamiento();
    }
    public String getIdTipoTasaColumna() {
        return idTipoTasa.getColumna();
    }
    public String getIdTipoTasaTipoValor() {
        return idTipoTasa.getTipoValor();
    }
    
    public String getFrecuenciaPagoEtiqueta() {
        return frecuenciaPago.getEtiqueta();
    }
    public Boolean getFrecuenciaPagoMandatorio() {
        return frecuenciaPago.getEsMandatorio();
    }
    public Boolean getFrecuenciaPagoVisible() {
        return frecuenciaPago.getEsVisible();
    }
    public Boolean getFrecuenciaPagoLectura() {
        return frecuenciaPago.getEsSoloLectura();
    }
    public Integer getFrecuenciaPagoOrdenamiento() {
        return frecuenciaPago.getOrdenamiento();
    }
    public String getFrecuenciaPagoColumna() {
        return frecuenciaPago.getColumna();
    }
    public String getFrecuenciaPagoTipoValor() {
        return frecuenciaPago.getTipoValor();
    }

    public String getMontoBaseEtiqueta() {
        return montoBase.getEtiqueta();
    }
    public Boolean getMontoBaseMandatorio() {
        return montoBase.getEsMandatorio();
    }
    public Boolean getMontoBaseVisible() {
        return montoBase.getEsVisible();
    }
    public Boolean getMontoBaseLectura() {
        return montoBase.getEsSoloLectura();
    }
    public Integer getMontoBaseOrdenamiento() {
        return montoBase.getOrdenamiento();
    }
    public String getMontoBaseColumna() {
        return montoBase.getColumna();
    }
    public String getMontoBaseTipoValor() {
        return montoBase.getTipoValor();
    }
    
    public String getMontoComisionEtiqueta() {
        return montoComision.getEtiqueta();
    }
    public Boolean getMontoComisionMandatorio() {
        return montoComision.getEsMandatorio();
    }
    public Boolean getMontoComisionVisible() {
        return montoComision.getEsVisible();
    }
    public Boolean getMontoComisionLectura() {
        return montoComision.getEsSoloLectura();
    }
    public Integer getMontoComisionOrdenamiento() {
        return montoComision.getOrdenamiento();
    }
    public String getMontoComisionColumna() {
        return montoComision.getColumna();
    }
    public String getMontoComisionTipoValor() {
        return montoComision.getTipoValor();
    }
    
    public String getFechaVencimientoEtiqueta() {
        return fechaVencimiento.getEtiqueta();
    }
    public Boolean getFechaVencimientoMandatorio() {
        return fechaVencimiento.getEsMandatorio();
    }
    public Boolean getFechaVencimientoVisible() {
        return fechaVencimiento.getEsVisible();
    }
    public Boolean getFechaVencimientoLectura() {
        return fechaVencimiento.getEsSoloLectura();
    }

    public Integer getFechaVencimientoOrdenamiento() {
        return fechaVencimiento.getOrdenamiento();
    }
    public String getFechaVencimientoColumna() {
        return fechaVencimiento.getColumna();
    }
    public String getFechaVencimientoTipoValor() {
        return fechaVencimiento.getTipoValor();
    }
    
    public String getIdEstadoTccEtiqueta() {
        return idEstadoTcc.getEtiqueta();
    }
    public Boolean getIdEstadoTccMandatorio() {
        return idEstadoTcc.getEsMandatorio();
    }
    public Boolean getIdEstadoTccVisible() {
        return idEstadoTcc.getEsVisible();
    }
    public Boolean getIdEstadoTccLectura() {
        return idEstadoTcc.getEsSoloLectura();
    }
    public Integer getIdEstadoTccOrdenamiento() {
        return idEstadoTcc.getOrdenamiento();
    }
    public String getIdEstadoTccColumna() {
        return idEstadoTcc.getColumna();
    }
    public String getIdEstadoTccTipoValor() {
        return idEstadoTcc.getTipoValor();
    }
    
    public String getComisioncompartidaEtiqueta() {
        return comisioncompartida.getEtiqueta();
    }
    public Boolean getComisioncompartidaMandatorio() {
        return comisioncompartida.getEsMandatorio();
    }
    public Boolean getComisioncompartidaVisible() {
        return comisioncompartida.getEsVisible();
    }
    public Boolean getComisioncompartidaLectura() {
        return comisioncompartida.getEsSoloLectura();
    }

    public Integer getComisioncompartidaOrdenamiento() {
        return comisioncompartida.getOrdenamiento();
    }
    public String getComisioncompartidaColumna() {
        return comisioncompartida.getColumna();
    }
    public String getComisioncompartidaTipoValor() {
        return comisioncompartida.getTipoValor();
    }
    
    public String getIdBaseCalculoEtiqueta() {
        return idBaseCalculo.getEtiqueta();
    }
    public Boolean getIdBaseCalculoMandatorio() {
        return idBaseCalculo.getEsMandatorio();
    }
    public Boolean getIdBaseCalculoVisible() {
        return idBaseCalculo.getEsVisible();
    }
    public Boolean getIdBaseCalculoLectura() {
        return idBaseCalculo.getEsSoloLectura();
    }
    public Integer getIdBaseCalculoOrdenamiento() {
        return idBaseCalculo.getOrdenamiento();
    }
    public String getIdBaseCalculoColumna() {
        return idBaseCalculo.getColumna();
    }
    public String getIdBaseCalculoTipoValor() {
        return idBaseCalculo.getTipoValor();
    }
    
    public String getPorcentajeEtiqueta() {
        return porcentaje.getEtiqueta();
    }
    public Boolean getPorcentajeMandatorio() {
        return porcentaje.getEsMandatorio();
    }
    public Boolean getPorcentajeVisible() {
        return porcentaje.getEsVisible();
    }
    public Boolean getPorcentajeLectura() {
        return porcentaje.getEsSoloLectura();
    }
    public Integer getPorcentajeOrdenamiento() {
        return porcentaje.getOrdenamiento();
    }
    public String getPorcentajeColumna() {
        return porcentaje.getColumna();
    }
    public String getPorcentajeTipoValor() {
        return porcentaje.getTipoValor();
    }
    
    public String getFechaInicioComisionEtiqueta() {
        return fechaInicioComision.getEtiqueta();
    }
    public Boolean getFechaInicioComisionMandatorio() {
        return fechaInicioComision.getEsMandatorio();
    }
    public Boolean getFechaInicioComisionVisible() {
        return fechaInicioComision.getEsVisible();
    }
    public Boolean getFechaInicioComisionLectura() {
        return fechaInicioComision.getEsSoloLectura();
    }
    public Integer getFechaInicioComisionOrdenamiento() {
        return fechaInicioComision.getOrdenamiento();
    }
    public String getFechaInicioComisionColumna() {
        return fechaInicioComision.getColumna();
    }
    public String getFechaInicioComisionTipoValor() {
        return fechaInicioComision.getTipoValor();
    }
    
    public String getFechaValorEtiqueta() {
        return fechaValor.getEtiqueta();
    }
    public Boolean getFechaValorMandatorio() {
        return fechaValor.getEsMandatorio();
    }
    public Boolean getFechaValorVisible() {
        return fechaValor.getEsVisible();
    }
    public Boolean getFechaValorLectura() {
        return fechaValor.getEsSoloLectura();
    }
    public Integer getFechaValorOrdenamiento() {
        return fechaValor.getOrdenamiento();
    }
    public String getFechaValorColumna() {
        return fechaValor.getColumna();
    }
    public String getFechaValorTipoValor() {
        return fechaValor.getTipoValor();
    }
    
    public String getFechaInicioCapitalEtiqueta() {
        return fechaInicioCapital.getEtiqueta();
    }
    public Boolean getFechaInicioCapitalMandatorio() {
        return fechaInicioCapital.getEsMandatorio();
    }
    public Boolean getFechaInicioCapitalVisible() {
        return fechaInicioCapital.getEsVisible();
    }
    public Boolean getFechaInicioCapitalLectura() {
        return fechaInicioCapital.getEsSoloLectura();
    }
    public Integer getFechaInicioCapitalOrdenamiento() {
        return fechaInicioCapital.getOrdenamiento();
    }
    public String getFechaInicioCapitalColumna() {
        return fechaInicioCapital.getColumna();
    }
    public String getFechaInicioCapitalTipoValor() {
        return fechaInicioCapital.getTipoValor();
    }
    
    void setAtributeValues(Map<String, Map> configuracionAtributosTCCMap){
        logger.warning("Entra en setAtributeValues.");
        try{
            descipcionComision.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_DESCRIPCION));
            nombreComision.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_NOMBRE));
            idTipoMoneda.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID_TCA_MONEDA));
            idTipoMomentoCobro.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID_TCA_MOMENTO_COBRO));
            idTcaComision.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID_TCA_TIPO_COMISION));
            idTipoFrecuencia.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID_TCA_TIPO_FRECUENCIA));
            idTipoFondo.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID_TCA_FONDO));
            idTipoMontoBase.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID_TCA_MONTO_BASE));
            idTipoTasa.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID_TCA_TIPO_TASA));
            frecuenciaPago.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_FRECUENCIA_PAGO));
            montoBase.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_MONTO_BASE));
            montoComision.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_MONTO_COMISION));
            fechaVencimiento.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_FECHA_VENCIMIENTO));
            idEstadoTcc.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID_TCA_ESTADO_TCC));
            idSubestadoTcc.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID_TCA_SUB_ESTADO_TCC));
            comisioncompartida.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_COMISION_COMPARTIDA));
            idBaseCalculo.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID_TCA_BASE_CALCULO));
            fechaInicioComision.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_FECHA_INICIO_COMISION));
            idComision.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID));
            porcentaje.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_PORCENTAJE_SOBRE_MONTO_BASE));
            fechaValor.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_FECHA_VALOR));
            fechaInicioCapital.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_FECHA_INICIO_CAPITAL));
        }catch(Exception e){
            logger.warning("Error al obtener la configuracion de atributos.", e);
        }      
    }
    
    public Timestamp getFechaFlexcubeActual() {
        return fechaFlexcubeActual;
    }

    public void setFechaFlexcubeActual(Timestamp fechaFlexcubeActual) {
        this.fechaFlexcubeActual = fechaFlexcubeActual;
    }
    
}
