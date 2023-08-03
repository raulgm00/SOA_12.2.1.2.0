package org.bcie.fenix.view.matriztcc;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Map;

import org.bcie.fenix.common.FenixConstants;


public class TerminoManagedBean implements Serializable{
    @SuppressWarnings("compatibility:-2902276018847057483")
    private static final long serialVersionUID = -3938953456848786974L;

    AtributosTCC nombreTermino;
    AtributosTCC descipcionTermino;
    AtributosTCC idTipoFechaInicio;
    AtributosTCC fechaInicio;
    AtributosTCC idFrecuenciaPlazo;
    AtributosTCC plazo;
    AtributosTCC fechaVencimiento;
    AtributosTCC idTipoMoneda;
    AtributosTCC monto;
    AtributosTCC tasa;
    AtributosTCC idTipoTasa;
    AtributosTCC fechaTermino;
    AtributosTCC frecuenciaRevision;
    AtributosTCC idFrecuenciaRevision;
    AtributosTCC fechaInicioRevision;
    AtributosTCC frecuenciaPagoInteres;
    AtributosTCC idFrecuecniaPagoInteres;
    AtributosTCC fechaInicioPagoInteres;
    AtributosTCC frecuenciaAmortizacion;
    AtributosTCC idFrecuenciaAmortizacion;
    AtributosTCC mora;
    AtributosTCC porcentajeCobertura;
    AtributosTCC aplicaRecursosConsecion;
    AtributosTCC aplicaRecursosExternos;
    AtributosTCC requiereFondoPresupuestario;
    AtributosTCC tasaMinimaDesembolso;
    AtributosTCC tasaMaximaDesembolso;
    AtributosTCC montoMinimoDesembolso;
    AtributosTCC montoMaximoDesembolso;
    AtributosTCC idTipoContrapartes;
    AtributosTCC treeContrapartes;
    AtributosTCC idTermino;
    AtributosTCC ordenInicio;
    AtributosTCC reqFormalizacionAutomatica;
    AtributosTCC porcentajeModificacion;
    AtributosTCC clienteGestionaContratacion;
    
    AtributosTCC idTcaTipoAvance;
    AtributosTCC idTcaTipoPorcentaje;
    AtributosTCC porcentaje;
    AtributosTCC porcentajeInicial;
    AtributosTCC porcentajeFinal;
    
    private Boolean mostrarPorcentajeUnico;

    public TerminoManagedBean() {
        super();
        
        nombreTermino = new AtributosTCC();
        descipcionTermino = new AtributosTCC();
        idTipoFechaInicio = new AtributosTCC();
        fechaInicio = new AtributosTCC();
        idFrecuenciaPlazo = new AtributosTCC();
        plazo = new AtributosTCC();
        fechaVencimiento = new AtributosTCC();
        idTipoMoneda = new AtributosTCC();
        monto = new AtributosTCC();
        tasa = new AtributosTCC();
        idTipoTasa = new AtributosTCC();
        fechaTermino = new AtributosTCC();
        frecuenciaRevision = new AtributosTCC();
        idFrecuenciaRevision = new AtributosTCC();
        fechaInicioRevision = new AtributosTCC();
        frecuenciaPagoInteres = new AtributosTCC();
        idFrecuecniaPagoInteres = new AtributosTCC();
        fechaInicioPagoInteres = new AtributosTCC();
        frecuenciaAmortizacion = new AtributosTCC();
        idFrecuenciaAmortizacion = new AtributosTCC();
        mora = new AtributosTCC();
        porcentajeCobertura = new AtributosTCC();
        aplicaRecursosConsecion = new AtributosTCC();
        aplicaRecursosExternos = new AtributosTCC();
        requiereFondoPresupuestario = new AtributosTCC();
        tasaMinimaDesembolso = new AtributosTCC();
        tasaMaximaDesembolso = new AtributosTCC();
        montoMinimoDesembolso = new AtributosTCC();
        montoMaximoDesembolso = new AtributosTCC();
        idTipoContrapartes = new AtributosTCC();
        treeContrapartes = new AtributosTCC();
        idTermino = new AtributosTCC();
        ordenInicio = new AtributosTCC();
        reqFormalizacionAutomatica = new AtributosTCC();
        porcentajeModificacion = new AtributosTCC();
        clienteGestionaContratacion = new AtributosTCC();
        
        idTcaTipoAvance = new AtributosTCC();
        idTcaTipoPorcentaje = new AtributosTCC();
        porcentaje = new AtributosTCC();
        porcentajeInicial = new AtributosTCC();
        porcentajeFinal = new AtributosTCC();
    }
    
    public String getDescipcionTerminoEtiqueta() {
        return descipcionTermino.getEtiqueta();
    }
    public Boolean getDescipcionTerminoMandatorio() {
        return descipcionTermino.getEsMandatorio();
    }
    public Boolean getDescipcionTerminoVisible() {
        return descipcionTermino.getEsVisible();
    }
    public Boolean getDescipcionTerminoLectura() {
        return descipcionTermino.getEsSoloLectura();
    }
    public Integer getDescipcionTerminoOrdenamiento() {
        return descipcionTermino.getOrdenamiento();
    }
    public String getDescipcionTerminoColumna() {
        return descipcionTermino.getColumna();
    }
    public String getDescipcionTerminoTipoValor() {
        return descipcionTermino.getTipoValor();
    }

    public String getNombreTerminoEtiqueta() {
        return nombreTermino.getEtiqueta();
    }
    public Boolean getNombreTerminoMandatorio() {
        return nombreTermino.getEsMandatorio();
    }
    public Boolean getNombreTerminoVisible() {
        return nombreTermino.getEsVisible();
    }
    public Boolean getNombreTerminoLectura() {
        return nombreTermino.getEsSoloLectura();
    }
    public Integer getNombreTerminoOrdenamiento() {
        return nombreTermino.getOrdenamiento();
    }
    public String getNombreTerminoColumna() {
        return nombreTermino.getColumna();
    }
    public String getNombreTerminoTipoValor() {
        return nombreTermino.getTipoValor();
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
    
    public String getIdFrecuenciaPlazoEtiqueta() {
        return idFrecuenciaPlazo.getEtiqueta();
    }
    public Boolean getIdFrecuenciaPlazoMandatorio() {
        return idFrecuenciaPlazo.getEsMandatorio();
    }
    public Boolean getIdFrecuenciaPlazoVisible() {
        return idFrecuenciaPlazo.getEsVisible();
    }
    public Boolean getIdFrecuenciaPlazoLectura() {
        return idFrecuenciaPlazo.getEsSoloLectura();
    }
    public Integer getIdFrecuenciaPlazoOrdenamiento() {
        return idFrecuenciaPlazo.getOrdenamiento();
    }
    public String getIdFrecuenciaPlazoColumna() {
        return idFrecuenciaPlazo.getColumna();
    }
    public String getIdFrecuenciaPlazoTipoValor() {
        return idFrecuenciaPlazo.getTipoValor();
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
    
    public String getMontoEtiqueta() {
        return monto.getEtiqueta();
    }
    public Boolean getMontoMandatorio() {
        return monto.getEsMandatorio();
    }
    public Boolean getMontoVisible() {
        return monto.getEsVisible();
    }
    public Boolean getMontoLectura() {
        return monto.getEsSoloLectura();
    }
    public Integer getMontoOrdenamiento() {
        return monto.getOrdenamiento();
    }
    public String getMontoColumna() {
        return monto.getColumna();
    }
    public String getMontoTipoValor() {
        return monto.getTipoValor();
    }
    
    public String getTasaEtiqueta() {
        return tasa.getEtiqueta();
    }
    public Boolean getTasaMandatorio() {
        return tasa.getEsMandatorio();
    }
    public Boolean getTasaVisible() {
        return tasa.getEsVisible();
    }
    public Boolean getTasaLectura() {
        return tasa.getEsSoloLectura();
    }
    public Integer getTasaOrdenamiento() {
        return tasa.getOrdenamiento();
    }
    public String getTasaColumna() {
        return tasa.getColumna();
    }
    public String getTasaTipoValor() {
        return tasa.getTipoValor();
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
    
    public String getFechaTerminoEtiqueta() {
        return fechaTermino.getEtiqueta();
    }
    public Boolean getFechaTerminoMandatorio() {
        return fechaTermino.getEsMandatorio();
    }
    public Boolean getFechaTerminoVisible() {
        return fechaTermino.getEsVisible();
    }
    public Boolean getFechaTerminoLectura() {
        return fechaTermino.getEsSoloLectura();
    }
    public Integer getFechaTerminoOrdenamiento() {
        return fechaTermino.getOrdenamiento();
    }
    public String getFechaTerminoColumna() {
        return fechaTermino.getColumna();
    }
    public String getFechaTerminoTipoValor() {
        return fechaTermino.getTipoValor();
    }
        
    public String getFrecuenciaRevisionEtiqueta() {
        return frecuenciaRevision.getEtiqueta();
    }
    public Boolean getFrecuenciaRevisionMandatorio() {
        return frecuenciaRevision.getEsMandatorio();
    }
    public Boolean getFrecuenciaRevisionVisible() {
        return frecuenciaRevision.getEsVisible();
    }
    public Boolean getFrecuenciaRevisionLectura() {
        return frecuenciaRevision.getEsSoloLectura();
    }
    public Integer getFrecuenciaRevisionOrdenamiento() {
        return frecuenciaRevision.getOrdenamiento();
    }
    public String getFrecuenciaRevisionColumna() {
        return frecuenciaRevision.getColumna();
    }
    public String getFrecuenciaRevisionTipoValor() {
        return frecuenciaRevision.getTipoValor();
    }
    
    public String getIdFrecuenciaRevisionEtiqueta() {
        return idFrecuenciaRevision.getEtiqueta();
    }
    public Boolean getIdFrecuenciaRevisionMandatorio() {
        return idFrecuenciaRevision.getEsMandatorio();
    }
    public Boolean getIdFrecuenciaRevisionVisible() {
        return idFrecuenciaRevision.getEsVisible();
    }
    public Boolean getIdFrecuenciaRevisionLectura() {
        return idFrecuenciaRevision.getEsSoloLectura();
    }
    public Integer getIdFrecuenciaRevisionOrdenamiento() {
        return idFrecuenciaRevision.getOrdenamiento();
    }
    public String getIdFrecuenciaRevisionColumna() {
        return idFrecuenciaRevision.getColumna();
    }
    public String getIdFrecuenciaRevisionTipoValor() {
        return idFrecuenciaRevision.getTipoValor();
    }
    
    public String getFechaInicioRevisionEtiqueta() {
        return fechaInicioRevision.getEtiqueta();
    }
    public Boolean getFechaInicioRevisionMandatorio() {
        return fechaInicioRevision.getEsMandatorio();
    }
    public Boolean getFechaInicioRevisionVisible() {
        return fechaInicioRevision.getEsVisible();
    }
    public Boolean getFechaInicioRevisionLectura() {
        return fechaInicioRevision.getEsSoloLectura();
    }
    public Integer getFechaInicioRevisionOrdenamiento() {
        return fechaInicioRevision.getOrdenamiento();
    }
    public String getFechaInicioRevisionColumna() {
        return fechaInicioRevision.getColumna();
    }
    public String getFechaInicioRevisionTipoValor() {
        return fechaInicioRevision.getTipoValor();
    }
    
    
    public String getFrecuenciaPagoInteresEtiqueta() {
        return frecuenciaPagoInteres.getEtiqueta();
    }
    public Boolean getFrecuenciaPagoInteresMandatorio() {
        return frecuenciaPagoInteres.getEsMandatorio();
    }
    public Boolean getFrecuenciaPagoInteresVisible() {
        return frecuenciaPagoInteres.getEsVisible();
    }
    public Boolean getFrecuenciaPagoInteresLectura() {
        return frecuenciaPagoInteres.getEsSoloLectura();
    }
    public Integer getFrecuenciaPagoInteresOrdenamiento() {
        return frecuenciaPagoInteres.getOrdenamiento();
    }
    public String getFrecuenciaPagoInteresColumna() {
        return frecuenciaPagoInteres.getColumna();
    }
    public String getFrecuenciaPagoInteresTipoValor() {
        return frecuenciaPagoInteres.getTipoValor();
    }
    
    public String getIdFrecuecniaPagoInteresEtiqueta() {
        return idFrecuecniaPagoInteres.getEtiqueta();
    }
    public Boolean getIdFrecuecniaPagoInteresMandatorio() {
        return idFrecuecniaPagoInteres.getEsMandatorio();
    }
    public Boolean getIdFrecuecniaPagoInteresVisible() {
        return idFrecuecniaPagoInteres.getEsVisible();
    }
    public Boolean getIdFrecuecniaPagoInteresLectura() {
        return idFrecuecniaPagoInteres.getEsSoloLectura();
    }
    public Integer getIdFrecuecniaPagoInteresOrdenamiento() {
        return idFrecuecniaPagoInteres.getOrdenamiento();
    }
    public String getIdFrecuecniaPagoInteresColumna() {
        return idFrecuecniaPagoInteres.getColumna();
    }
    public String getIdFrecuecniaPagoInteresTipoValor() {
        return idFrecuecniaPagoInteres.getTipoValor();
    }
    
    public String getFechaInicioPagoInteresEtiqueta() {
        return fechaInicioPagoInteres.getEtiqueta();
    }
    public Boolean getFechaInicioPagoInteresMandatorio() {
        return fechaInicioPagoInteres.getEsMandatorio();
    }
    public Boolean getFechaInicioPagoInteresVisible() {
        return fechaInicioPagoInteres.getEsVisible();
    }
    public Boolean getFechaInicioPagoInteresLectura() {
        return fechaInicioPagoInteres.getEsSoloLectura();
    }
    public Integer getFechaInicioPagoInteresOrdenamiento() {
        return fechaInicioPagoInteres.getOrdenamiento();
    }
    public String getFechaInicioPagoInteresColumna() {
        return fechaInicioPagoInteres.getColumna();
    }
    public String getFechaInicioPagoInteresTipoValor() {
        return fechaInicioPagoInteres.getTipoValor();
    }
    
    public String getFrecuenciaAmortizacionEtiqueta() {
        return frecuenciaAmortizacion.getEtiqueta();
    }
    public Boolean getFrecuenciaAmortizacionMandatorio() {
        return frecuenciaAmortizacion.getEsMandatorio();
    }
    public Boolean getFrecuenciaAmortizacionVisible() {
        return frecuenciaAmortizacion.getEsVisible();
    }
    public Boolean getFrecuenciaAmortizacionLectura() {
        return frecuenciaAmortizacion.getEsSoloLectura();
    }
    public Integer getFrecuenciaAmortizacionOrdenamiento() {
        return frecuenciaAmortizacion.getOrdenamiento();
    }
    public String getFrecuenciaAmortizacionColumna() {
        return frecuenciaAmortizacion.getColumna();
    }
    public String getFrecuenciaAmortizacionTipoValor() {
        return frecuenciaAmortizacion.getTipoValor();
    }
    
    public String getIdFrecuenciaAmortizacionEtiqueta() {
        return idFrecuenciaAmortizacion.getEtiqueta();
    }
    public Boolean getIdFrecuenciaAmortizacionMandatorio() {
        return idFrecuenciaAmortizacion.getEsMandatorio();
    }
    public Boolean getIdFrecuenciaAmortizacionVisible() {
        return idFrecuenciaAmortizacion.getEsVisible();
    }
    public Boolean getIdFrecuenciaAmortizacionLectura() {
        return idFrecuenciaAmortizacion.getEsSoloLectura();
    }

    public Integer getIdFrecuenciaAmortizacionOrdenamiento() {
        return idFrecuenciaAmortizacion.getOrdenamiento();
    }
    public String getIdFrecuenciaAmortizacionColumna() {
        return idFrecuenciaAmortizacion.getColumna();
    }
    public String getIdFrecuenciaAmortizacionTipoValor() {
        return idFrecuenciaAmortizacion.getTipoValor();
    }
    
    public String getMoraEtiqueta() {
        return mora.getEtiqueta();
    }
    public Boolean getMoraMandatorio() {
        return mora.getEsMandatorio();
    }
    public Boolean getMoraVisible() {
        return mora.getEsVisible();
    }
    public Boolean getMoraLectura() {
        return mora.getEsSoloLectura();
    }
    public Integer getMoraOrdenamiento() {
        return mora.getOrdenamiento();
    }
    public String getMoraColumna() {
        return mora.getColumna();
    }
    public String getMoraTipoValor() {
        return mora.getTipoValor();
    }
    

    public String getPorcentajeCoberturaEtiqueta() {
        return porcentajeCobertura.getEtiqueta();
    }
    public Boolean getPorcentajeCoberturaMandatorio() {
        return porcentajeCobertura.getEsMandatorio();
    }
    public Boolean getPorcentajeCoberturaVisible() {
        return porcentajeCobertura.getEsVisible();
    }
    public Boolean getPorcentajeCoberturaLectura() {
        return porcentajeCobertura.getEsSoloLectura();
    }
    public Integer getPorcentajeCoberturaOrdenamiento() {
        return porcentajeCobertura.getOrdenamiento();
    }
    public String getPorcentajeCoberturaColumna() {
        return porcentajeCobertura.getColumna();
    }
    public String getPorcentajeCoberturaTipoValor() {
        return porcentajeCobertura.getTipoValor();
    }
    
    
    
    
    public String getAplicaRecursosConsecionEtiqueta() {
        return aplicaRecursosConsecion.getEtiqueta();
    }
    public Boolean getAplicaRecursosConsecionMandatorio() {
        return aplicaRecursosConsecion.getEsMandatorio();
    }
    public Boolean getAplicaRecursosConsecionVisible() {
        return aplicaRecursosConsecion.getEsVisible();
    }
    public Boolean getAplicaRecursosConsecionLectura() {
        return aplicaRecursosConsecion.getEsSoloLectura();
    }
    public Integer getAplicaRecursosConsecionOrdenamiento() {
        return aplicaRecursosConsecion.getOrdenamiento();
    }
    public String getAplicaRecursosConsecionColumna() {
        return aplicaRecursosConsecion.getColumna();
    }
    public String getAplicaRecursosConsecionTipoValor() {
        return aplicaRecursosConsecion.getTipoValor();
    }
    
    
    public String getAplicaRecursosExternosEtiqueta() {
        return aplicaRecursosExternos.getEtiqueta();
    }
    public Boolean getAplicaRecursosExternosMandatorio() {
        return aplicaRecursosExternos.getEsMandatorio();
    }
    public Boolean getAplicaRecursosExternosVisible() {
        return aplicaRecursosExternos.getEsVisible();
    }
    public Boolean getAplicaRecursosExternosLectura() {
        return aplicaRecursosExternos.getEsSoloLectura();
    }
    public Integer getAplicaRecursosExternosOrdenamiento() {
        return aplicaRecursosExternos.getOrdenamiento();
    }
    public String getAplicaRecursosExternosColumna() {
        return aplicaRecursosExternos.getColumna();
    }
    public String getAplicaRecursosExternosTipoValor() {
        return aplicaRecursosExternos.getTipoValor();
    }
    
    public String getRequiereFondoPresupuestarioEtiqueta() {
        return requiereFondoPresupuestario.getEtiqueta();
    }
    public Boolean getRequiereFondoPresupuestarioMandatorio() {
        return requiereFondoPresupuestario.getEsMandatorio();
    }
    public Boolean getRequiereFondoPresupuestarioVisible() {
        return requiereFondoPresupuestario.getEsVisible();
    }
    public Boolean getRequiereFondoPresupuestarioLectura() {
        return requiereFondoPresupuestario.getEsSoloLectura();
    }
    public Integer getRequiereFondoPresupuestarioOrdenamiento() {
        return requiereFondoPresupuestario.getOrdenamiento();
    }
    public String getRequiereFondoPresupuestarioColumna() {
        return requiereFondoPresupuestario.getColumna();
    }
    public String getRequiereFondoPresupuestarioTipoValor() {
        return requiereFondoPresupuestario.getTipoValor();
    }

    public String getTreeContrapartesEtiqueta() {
        return treeContrapartes.getEtiqueta();
    }
    public Boolean getTreeContrapartesMandatorio() {
        return treeContrapartes.getEsMandatorio();
    }
    public Boolean getTreeContrapartesVisible() {
        return treeContrapartes.getEsVisible();
    }
    public Boolean getTreeContrapartesLectura() {
        return treeContrapartes.getEsSoloLectura();
    }
    public Integer getTreeContrapartesOrdenamiento() {
        return treeContrapartes.getOrdenamiento();
    }
    public String getTreeContrapartesColumna() {
        return treeContrapartes.getColumna();
    }
    public String getTreeContrapartesTipoValor() {
        return treeContrapartes.getTipoValor();
    }
    
    public String getIdTipoContrapartesEtiqueta() {
        return idTipoContrapartes.getEtiqueta();
    }
    public Boolean getIdTipoContrapartesMandatorio() {
        return idTipoContrapartes.getEsMandatorio();
    }
    public Boolean getIdTipoContrapartesVisible() {
        return idTipoContrapartes.getEsVisible();
    }
    public Boolean getIdTipoContrapartesLectura() {
        return idTipoContrapartes.getEsSoloLectura();
    }
    public Integer getIdTipoContrapartesOrdenamiento() {
        return idTipoContrapartes.getOrdenamiento();
    }
    public String getIdTipoContrapartesColumna() {
        return idTipoContrapartes.getColumna();
    }
    public String getIdTipoContrapartesTipoValor() {
        return idTipoContrapartes.getTipoValor();
    }
    
    public String getTasaMinimaDesembolsoEtiqueta() {
        return tasaMinimaDesembolso.getEtiqueta();
    }
    public Boolean getTasaMinimaDesembolsoMandatorio() {
        return tasaMinimaDesembolso.getEsMandatorio();
    }
    public Boolean getTasaMinimaDesembolsoVisible() {
        return tasaMinimaDesembolso.getEsVisible();
    }
    public Boolean getTasaMinimaDesembolsoLectura() {
        return tasaMinimaDesembolso.getEsSoloLectura();
    }
    public Integer getTasaMinimaDesembolsoOrdenamiento() {
        return tasaMinimaDesembolso.getOrdenamiento();
    }
    public String getTasaMinimaDesembolsoColumna() {
        return tasaMinimaDesembolso.getColumna();
    }
    public String getTasaMinimaDesembolsoTipoValor() {
        return tasaMinimaDesembolso.getTipoValor();
    }
    
    public String getTasaMaximaDesembolsoEtiqueta() {
        return tasaMaximaDesembolso.getEtiqueta();
    }
    public Boolean getTasaMaximaDesembolsoMandatorio() {
        return tasaMaximaDesembolso.getEsMandatorio();
    }
    public Boolean getTasaMaximaDesembolsoVisible() {
        return tasaMaximaDesembolso.getEsVisible();
    }
    public Boolean getTasaMaximaDesembolsoLectura() {
        return tasaMaximaDesembolso.getEsSoloLectura();
    }
    public Integer getTasaMaximaDesembolsoOrdenamiento() {
        return tasaMaximaDesembolso.getOrdenamiento();
    }
    public String getTasaMaximaDesembolsoColumna() {
        return tasaMaximaDesembolso.getColumna();
    }
    public String getTasaMaximaDesembolsoTipoValor() {
        return tasaMaximaDesembolso.getTipoValor();
    }
    
    
    public String getMontoMinimoDesembolsoEtiqueta() {
        return montoMinimoDesembolso.getEtiqueta();
    }
    public Boolean getMontoMinimoDesembolsoMandatorio() {
        return montoMinimoDesembolso.getEsMandatorio();
    }
    public Boolean getMontoMinimoDesembolsoVisible() {
        return montoMinimoDesembolso.getEsVisible();
    }
    public Boolean getMontoMinimoDesembolsoLectura() {
        return montoMinimoDesembolso.getEsSoloLectura();
    }
    public Integer getMontoMinimoDesembolsoOrdenamiento() {
        return montoMinimoDesembolso.getOrdenamiento();
    }
    public String getMontoMinimoDesembolsoColumna() {
        return montoMinimoDesembolso.getColumna();
    }
    public String getMontoMinimaDesembolsoTipoValor() {
        return montoMinimoDesembolso.getTipoValor();
    }
    
    public String getMontoMaximaDesembolsoEtiqueta() {
        return montoMaximoDesembolso.getEtiqueta();
    }
    public Boolean getMontoMaximoDesembolsoMandatorio() {
        return montoMaximoDesembolso.getEsMandatorio();
    }
    public Boolean getMontoMaximoDesembolsoVisible() {
        return montoMaximoDesembolso.getEsVisible();
    }
    public Boolean getMontoMaximoDesembolsoLectura() {
        return montoMaximoDesembolso.getEsSoloLectura();
    }
    public Integer getMontoMaximoDesembolsoOrdenamiento() {
        return montoMaximoDesembolso.getOrdenamiento();
    }
    public String getMontoMaximoDesembolsoColumna() {
        return montoMaximoDesembolso.getColumna();
    }
    public String getMontoMaximoDesembolsoTipoValor() {
        return montoMaximoDesembolso.getTipoValor();
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
    
    
    public String getRequiereOrdenInicioEtiqueta() {
        return ordenInicio.getEtiqueta();
    }
    public Boolean getRequiereOrdenInicioMandatorio() {
        return ordenInicio.getEsMandatorio();
    }
    public Boolean getRequiereOrdenInicioVisible() {
        return ordenInicio.getEsVisible();
    }
    public Boolean getRequiereOrdenInicioLectura() {
        return ordenInicio.getEsSoloLectura();
    }
    public Integer getRequiereOrdenInicioOrdenamiento() {
        return ordenInicio.getOrdenamiento();
    }
    public String getRequiereOrdenInicioColumna() {
        return ordenInicio.getColumna();
    }
    public String getRequiereOrdenInicioTipoValor() {
        return ordenInicio.getTipoValor();
    }
    
    //Metodos get para Atributo PorcentajeModificacion T701, T705, T706, T707
    public String getClienteGestionaContratacionEtiqueta() {
        return clienteGestionaContratacion.getEtiqueta();
    }
    public Boolean getClienteGestionaContratacionMandatorio() {
        return clienteGestionaContratacion.getEsMandatorio();
    }
    public Boolean getClienteGestionaContratacionLectura() {
        return clienteGestionaContratacion.getEsSoloLectura();
    }
    public Boolean getClienteGestionaContratacionVisible() {
        return clienteGestionaContratacion.getEsVisible();
    }
    public String getPorcentajeModificacionEtiqueta() {
        return porcentajeModificacion.getEtiqueta();
    }
    public Boolean getPorcentajeModificacionMandatorio() {
        return porcentajeModificacion.getEsMandatorio();
    }
    public Boolean getPorcentajeModificacionVisible() {
        return porcentajeModificacion.getEsVisible();
    }
    public Boolean getPorcentajeModificacionLectura() {
        return porcentajeModificacion.getEsSoloLectura();
    }
    public Integer getPorcentajeModificacionOrdenamiento() {
        return porcentajeModificacion.getOrdenamiento();
    }
    public String getPorcentajeModificacionColumna() {
        return porcentajeModificacion.getColumna();
    }
    public String getPorcentajeModificacionTipoValor() {
        return porcentajeModificacion.getTipoValor();
    }
    
    //Accessors T800...
    public String getIdTcaTipoAvanceEtiqueta() {
        return idTcaTipoAvance.getEtiqueta();
    }
    public Boolean getIdTcaTipoAvanceMandatorio() {
        return idTcaTipoAvance.getEsMandatorio();
    }
    public Boolean getIdTcaTipoAvanceVisible() {
        return idTcaTipoAvance.getEsVisible();
    }
    public Boolean getIdTcaTipoAvanceLectura() {
        return idTcaTipoAvance.getEsSoloLectura();
    }
    public Integer getIdTcaTipoAvanceOrdenamiento() {
        return idTcaTipoAvance.getOrdenamiento();
    }
    public String getIdTcaTipoAvanceColumna() {
        return idTcaTipoAvance.getColumna();
    }
    public String getIdTcaTipoAvanceTipoValor() {
        return idTcaTipoAvance.getTipoValor();
    }
    
    public String getIdTcaTipoPorcentajeEtiqueta() {
        return idTcaTipoPorcentaje.getEtiqueta();
    }
    public Boolean getIdTcaTipoPorcentajeMandatorio() {
        return idTcaTipoPorcentaje.getEsMandatorio();
    }
    public Boolean getIdTcaTipoPorcentajeVisible() {
        return idTcaTipoPorcentaje.getEsVisible();
    }
    public Boolean getIdTcaTipoPorcentajeLectura() {
        return idTcaTipoPorcentaje.getEsSoloLectura();
    }
    public Integer getIdTcaTipoPorcentajeOrdenamiento() {
        return idTcaTipoPorcentaje.getOrdenamiento();
    }
    public String getIdTcaTipoPorcentajeColumna() {
        return idTcaTipoPorcentaje.getColumna();
    }
    public String getIdTcaTipoPorcentajeTipoValor() {
        return idTcaTipoPorcentaje.getTipoValor();
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
    
    public String getPorcentajeInicialEtiqueta() {
        return porcentajeInicial.getEtiqueta();
    }
    public Boolean getPorcentajeInicialMandatorio() {
        return porcentajeInicial.getEsMandatorio();
    }
    public Boolean getPorcentajeInicialVisible() {
        return porcentajeInicial.getEsVisible();
    }
    public Boolean getPorcentajeInicialLectura() {
        return porcentajeInicial.getEsSoloLectura();
    }
    public Integer getPorcentajeInicialOrdenamiento() {
        return porcentajeInicial.getOrdenamiento();
    }
    public String getPorcentajeInicialColumna() {
        return porcentajeInicial.getColumna();
    }
    public String getPorcentajeInicialTipoValor() {
        return porcentajeInicial.getTipoValor();
    }
    
    public String getPorcentajeFinalEtiqueta() {
        return porcentajeFinal.getEtiqueta();
    }
    public Boolean getPorcentajeFinalMandatorio() {
        return porcentajeFinal.getEsMandatorio();
    }
    public Boolean getPorcentajeFinalVisible() {
        return porcentajeFinal.getEsVisible();
    }
    public Boolean getPorcentajeFinalLectura() {
        return porcentajeFinal.getEsSoloLectura();
    }
    public Integer getPorcentajeFinalOrdenamiento() {
        return porcentajeFinal.getOrdenamiento();
    }
    public String getPorcentajeFinalColumna() {
        return porcentajeFinal.getColumna();
    }
    public String getPorcentajeFinalTipoValor() {
        return porcentajeFinal.getTipoValor();
    }


    public void setMostrarPorcentajeUnico(Boolean mostrarPorcentajeUnico) {
        this.mostrarPorcentajeUnico = mostrarPorcentajeUnico;
    }

    public Boolean getMostrarPorcentajeUnico() {
        return mostrarPorcentajeUnico;
    }

    public String getReqFormalizacionAutomaticaEtiqueta() {
        return reqFormalizacionAutomatica.getEtiqueta();
    }
    public Boolean getReqFormalizacionAutomaticaMandatorio() {
        return reqFormalizacionAutomatica.getEsMandatorio();
    }
    public Boolean getReqFormalizacionAutomaticaVisible() {
        return reqFormalizacionAutomatica.getEsVisible();
    }
    public Boolean getReqFormalizacionAutomaticaLectura() {
        return reqFormalizacionAutomatica.getEsSoloLectura();
    }
    public Integer getReqFormalizacionAutomaticaOrdenamiento() {
        return reqFormalizacionAutomatica.getOrdenamiento();
    }
    public String getReqFormalizacionAutomaticaColumna() {
        return reqFormalizacionAutomatica.getColumna();
    }
    public String getReqFormalizacionAutomaticaTipoValor() {
        return reqFormalizacionAutomatica.getTipoValor();
    }
        

    void setAtributeValues(Map<String, Map> configuracionAtributosTCCMap)
    {

      descipcionTermino.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_DESCRIPCION));
      nombreTermino.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_NOMBRE));
      idTipoMoneda.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_ID_TCA_MONEDA));
      monto.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_MONTO));
      tasa.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_TASA));
      fechaTermino.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_FECHA));
      fechaInicioPagoInteres.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_FECHA_INICIO_PAGO_INTERES));
      frecuenciaAmortizacion.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_FRECUENCIA_AMORTIZACION));
      idTipoTasa.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_ID_TCA_TIPO_TASA));
      idFrecuenciaAmortizacion.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_ID_TCA_FRECUENCIA_AMORTIZACION));
      frecuenciaRevision.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_FRECUENCIA_REVISION));
      idFrecuenciaRevision.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_ID_TCA_FRECUENCIA_REVISION));
      fechaVencimiento.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_FECHA_VENCIMIENTO));
      mora.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_MORA));
      aplicaRecursosExternos.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_SE_APLICAN_RECURSOS_EXTERNOS));
      requiereFondoPresupuestario.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_REQUIERE_FONDO_PRESUPUESTARIO));
      tasaMinimaDesembolso.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_TASA_MINIMA_DESEMBOLSO));
      tasaMaximaDesembolso.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_TASA_MAXIMA_DESEMBOLSO));
      montoMinimoDesembolso.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_MONTO_MINIMO_DESEMBOLSO));
      montoMaximoDesembolso.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_MONTO_MAXIMO_DESEMBOLSO));
      idTipoContrapartes.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_TIPO_CONTRAPARTE));
      frecuenciaPagoInteres.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_FRECUENCIA_PAGO_INTERES));
      fechaInicioRevision.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_FECHA_INICIO_REVISION));
      idTipoFechaInicio.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_ID_TCA_TIPO_FECHA_INICIO));
      fechaInicio.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_FECHA_INICIO));
      idFrecuenciaPlazo.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CN_ID_TCA_FRECUENCIA_PLAZO));
      plazo.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_PLAZO));
      aplicaRecursosConsecion.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_SE_APLICAN_RECURSOS_CONCESION));
      treeContrapartes.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_TRE_CONTRAPARTES_TERMINO));
      idTermino.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CM_ID));
      idFrecuecniaPagoInteres.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_ID_TCA_FRECUENCIA_PAGO_INTERES));
      porcentajeCobertura.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_PORCENTAJE_COBERTURA));
      ordenInicio.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_REQUIERE_ORDEN_INICIO));
      reqFormalizacionAutomatica.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_REQ_FORMALIZACION_AUTOMATICA));
      porcentajeModificacion.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_PORCENTAJE_MODIFICACION));
      clienteGestionaContratacion.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_CLIENTE_GESTIONA_CONTRATACION));
      idTcaTipoAvance.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_ID_TCA_TIPO_AVANCE));
      idTcaTipoPorcentaje.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_ID_TCA_TIPO_PORCENTAJE));
      porcentaje.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_PORCENTAJE));
      porcentajeInicial.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_PORCENTAJE_INICIAL));
      porcentajeFinal.setValues(configuracionAtributosTCCMap.get(FenixConstants.TCC_TR_PORCENTAJE_FINAL));
     }
     
}
