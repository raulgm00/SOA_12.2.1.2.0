package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.RowSet;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Feb 10 11:34:40 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ActualizarOperacionVORowImpl extends ViewRowImpl {


    public static final int ENTITY_OPERACIONEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        ActividadEconomica,
        AplicaLaft,
        AreaFocalizacion,
        BanEstatus,
        Beneficiario,
        ComponenteConcecionalidad,
        Descripcion,
        EjeEstrategico,
        EjercicioPoa,
        Estado,
        Estructurador,
        Etapa,
        FechaBaja,
        FechaInicio,
        IdActividadEconomicaAsoc,
        IdCliente,
        IdOperacion,
        IdPerspectiva,
        IdProducto,
        IdRangoPais,
        IniciativaEstrategica,
        JustificacionCancela,
        Nombre,
        ObjetivosEstrategicos,
        ObservacionCancela,
        Oficina,
        Programa,
        ProgramadoPoa,
        Raroc,
        RequiereRecursosExt,
        Scr,
        ScrEstatus,
        SectorMercado,
        TipoGarantia,
        Tir,
        TirEstatus,
        UnidadEjecutora,
        Usuario,
        MontoSolicitado,
        MontoTotal,
        EsFactible,
        IdTcaTipoTasa,
        CodigoTasaReferencia,
        FechaFinalizacionEstudios,
        FechaCalculoInteres,
        ValorSpread,
        ValorTasa,
        JustificacionEnvio,
        FechaInteresesCalculados,
        TotalEnvioCobro,
        TasaTotal,
        CodigoMoneda,
        CodTasRef,
        TcaCompartidaValorVo,
        TcaTipoTasaOperacionVO,
        TasaReferenciaVO;
        static AttributesEnum[] vals = null;
        ;
        private static final int firstIndex = 0;

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int ACTIVIDADECONOMICA = AttributesEnum.ActividadEconomica.index();
    public static final int APLICALAFT = AttributesEnum.AplicaLaft.index();
    public static final int AREAFOCALIZACION = AttributesEnum.AreaFocalizacion.index();
    public static final int BANESTATUS = AttributesEnum.BanEstatus.index();
    public static final int BENEFICIARIO = AttributesEnum.Beneficiario.index();
    public static final int COMPONENTECONCECIONALIDAD = AttributesEnum.ComponenteConcecionalidad.index();
    public static final int DESCRIPCION = AttributesEnum.Descripcion.index();
    public static final int EJEESTRATEGICO = AttributesEnum.EjeEstrategico.index();
    public static final int EJERCICIOPOA = AttributesEnum.EjercicioPoa.index();
    public static final int ESTADO = AttributesEnum.Estado.index();
    public static final int ESTRUCTURADOR = AttributesEnum.Estructurador.index();
    public static final int ETAPA = AttributesEnum.Etapa.index();
    public static final int FECHABAJA = AttributesEnum.FechaBaja.index();
    public static final int FECHAINICIO = AttributesEnum.FechaInicio.index();
    public static final int IDACTIVIDADECONOMICAASOC = AttributesEnum.IdActividadEconomicaAsoc.index();
    public static final int IDCLIENTE = AttributesEnum.IdCliente.index();
    public static final int IDOPERACION = AttributesEnum.IdOperacion.index();
    public static final int IDPERSPECTIVA = AttributesEnum.IdPerspectiva.index();
    public static final int IDPRODUCTO = AttributesEnum.IdProducto.index();
    public static final int IDRANGOPAIS = AttributesEnum.IdRangoPais.index();
    public static final int INICIATIVAESTRATEGICA = AttributesEnum.IniciativaEstrategica.index();
    public static final int JUSTIFICACIONCANCELA = AttributesEnum.JustificacionCancela.index();
    public static final int NOMBRE = AttributesEnum.Nombre.index();
    public static final int OBJETIVOSESTRATEGICOS = AttributesEnum.ObjetivosEstrategicos.index();
    public static final int OBSERVACIONCANCELA = AttributesEnum.ObservacionCancela.index();
    public static final int OFICINA = AttributesEnum.Oficina.index();
    public static final int PROGRAMA = AttributesEnum.Programa.index();
    public static final int PROGRAMADOPOA = AttributesEnum.ProgramadoPoa.index();
    public static final int RAROC = AttributesEnum.Raroc.index();
    public static final int REQUIERERECURSOSEXT = AttributesEnum.RequiereRecursosExt.index();
    public static final int SCR = AttributesEnum.Scr.index();
    public static final int SCRESTATUS = AttributesEnum.ScrEstatus.index();
    public static final int SECTORMERCADO = AttributesEnum.SectorMercado.index();
    public static final int TIPOGARANTIA = AttributesEnum.TipoGarantia.index();
    public static final int TIR = AttributesEnum.Tir.index();
    public static final int TIRESTATUS = AttributesEnum.TirEstatus.index();
    public static final int UNIDADEJECUTORA = AttributesEnum.UnidadEjecutora.index();
    public static final int USUARIO = AttributesEnum.Usuario.index();
    public static final int MONTOSOLICITADO = AttributesEnum.MontoSolicitado.index();
    public static final int MONTOTOTAL = AttributesEnum.MontoTotal.index();
    public static final int ESFACTIBLE = AttributesEnum.EsFactible.index();
    public static final int IDTCATIPOTASA = AttributesEnum.IdTcaTipoTasa.index();
    public static final int CODIGOTASAREFERENCIA = AttributesEnum.CodigoTasaReferencia.index();
    public static final int FECHAFINALIZACIONESTUDIOS = AttributesEnum.FechaFinalizacionEstudios.index();
    public static final int FECHACALCULOINTERES = AttributesEnum.FechaCalculoInteres.index();
    public static final int VALORSPREAD = AttributesEnum.ValorSpread.index();
    public static final int VALORTASA = AttributesEnum.ValorTasa.index();
    public static final int JUSTIFICACIONENVIO = AttributesEnum.JustificacionEnvio.index();
    public static final int FECHAINTERESESCALCULADOS = AttributesEnum.FechaInteresesCalculados.index();
    public static final int TOTALENVIOCOBRO = AttributesEnum.TotalEnvioCobro.index();
    public static final int TASATOTAL = AttributesEnum.TasaTotal.index();
    public static final int CODIGOMONEDA = AttributesEnum.CodigoMoneda.index();
    public static final int CODTASREF = AttributesEnum.CodTasRef.index();
    public static final int TCACOMPARTIDAVALORVO = AttributesEnum.TcaCompartidaValorVo.index();
    public static final int TCATIPOTASAOPERACIONVO = AttributesEnum.TcaTipoTasaOperacionVO.index();
    public static final int TASAREFERENCIAVO = AttributesEnum.TasaReferenciaVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ActualizarOperacionVORowImpl() {
    }

    /**
     * Gets OperacionEO entity object.
     * @return the OperacionEO
     */
    public EntityImpl getOperacionEO() {
        return (EntityImpl) getEntity(ENTITY_OPERACIONEO);
    }

    /**
     * Gets the attribute value for ACTIVIDAD_ECONOMICA using the alias name ActividadEconomica.
     * @return the ACTIVIDAD_ECONOMICA
     */
    public Number getActividadEconomica() {
        return (Number) getAttributeInternal(ACTIVIDADECONOMICA);
    }

    /**
     * Sets <code>value</code> as attribute value for ACTIVIDAD_ECONOMICA using the alias name ActividadEconomica.
     * @param value value to set the ACTIVIDAD_ECONOMICA
     */
    public void setActividadEconomica(Number value) {
        setAttributeInternal(ACTIVIDADECONOMICA, value);
    }

    /**
     * Gets the attribute value for APLICA_LAFT using the alias name AplicaLaft.
     * @return the APLICA_LAFT
     */
    public Number getAplicaLaft() {
        return (Number) getAttributeInternal(APLICALAFT);
    }

    /**
     * Sets <code>value</code> as attribute value for APLICA_LAFT using the alias name AplicaLaft.
     * @param value value to set the APLICA_LAFT
     */
    public void setAplicaLaft(Number value) {
        setAttributeInternal(APLICALAFT, value);
    }

    /**
     * Gets the attribute value for AREA_FOCALIZACION using the alias name AreaFocalizacion.
     * @return the AREA_FOCALIZACION
     */
    public Number getAreaFocalizacion() {
        return (Number) getAttributeInternal(AREAFOCALIZACION);
    }

    /**
     * Sets <code>value</code> as attribute value for AREA_FOCALIZACION using the alias name AreaFocalizacion.
     * @param value value to set the AREA_FOCALIZACION
     */
    public void setAreaFocalizacion(Number value) {
        setAttributeInternal(AREAFOCALIZACION, value);
    }

    /**
     * Gets the attribute value for BAN_ESTATUS using the alias name BanEstatus.
     * @return the BAN_ESTATUS
     */
    public String getBanEstatus() {
        return (String) getAttributeInternal(BANESTATUS);
    }

    /**
     * Sets <code>value</code> as attribute value for BAN_ESTATUS using the alias name BanEstatus.
     * @param value value to set the BAN_ESTATUS
     */
    public void setBanEstatus(String value) {
        setAttributeInternal(BANESTATUS, value);
    }

    /**
     * Gets the attribute value for BENEFICIARIO using the alias name Beneficiario.
     * @return the BENEFICIARIO
     */
    public String getBeneficiario() {
        return (String) getAttributeInternal(BENEFICIARIO);
    }

    /**
     * Sets <code>value</code> as attribute value for BENEFICIARIO using the alias name Beneficiario.
     * @param value value to set the BENEFICIARIO
     */
    public void setBeneficiario(String value) {
        setAttributeInternal(BENEFICIARIO, value);
    }

    /**
     * Gets the attribute value for COMPONENTE_CONCECIONALIDAD using the alias name ComponenteConcecionalidad.
     * @return the COMPONENTE_CONCECIONALIDAD
     */
    public Number getComponenteConcecionalidad() {
        return (Number) getAttributeInternal(COMPONENTECONCECIONALIDAD);
    }

    /**
     * Sets <code>value</code> as attribute value for COMPONENTE_CONCECIONALIDAD using the alias name ComponenteConcecionalidad.
     * @param value value to set the COMPONENTE_CONCECIONALIDAD
     */
    public void setComponenteConcecionalidad(Number value) {
        setAttributeInternal(COMPONENTECONCECIONALIDAD, value);
    }

    /**
     * Gets the attribute value for DESCRIPCION using the alias name Descripcion.
     * @return the DESCRIPCION
     */
    public String getDescripcion() {
        return (String) getAttributeInternal(DESCRIPCION);
    }

    /**
     * Sets <code>value</code> as attribute value for DESCRIPCION using the alias name Descripcion.
     * @param value value to set the DESCRIPCION
     */
    public void setDescripcion(String value) {
        setAttributeInternal(DESCRIPCION, value);
    }

    /**
     * Gets the attribute value for EJE_ESTRATEGICO using the alias name EjeEstrategico.
     * @return the EJE_ESTRATEGICO
     */
    public Number getEjeEstrategico() {
        return (Number) getAttributeInternal(EJEESTRATEGICO);
    }

    /**
     * Sets <code>value</code> as attribute value for EJE_ESTRATEGICO using the alias name EjeEstrategico.
     * @param value value to set the EJE_ESTRATEGICO
     */
    public void setEjeEstrategico(Number value) {
        setAttributeInternal(EJEESTRATEGICO, value);
    }

    /**
     * Gets the attribute value for EJERCICIO_POA using the alias name EjercicioPoa.
     * @return the EJERCICIO_POA
     */
    public Number getEjercicioPoa() {
        return (Number) getAttributeInternal(EJERCICIOPOA);
    }

    /**
     * Sets <code>value</code> as attribute value for EJERCICIO_POA using the alias name EjercicioPoa.
     * @param value value to set the EJERCICIO_POA
     */
    public void setEjercicioPoa(Number value) {
        setAttributeInternal(EJERCICIOPOA, value);
    }

    /**
     * Gets the attribute value for ESTADO using the alias name Estado.
     * @return the ESTADO
     */
    public Number getEstado() {
        return (Number) getAttributeInternal(ESTADO);
    }

    /**
     * Sets <code>value</code> as attribute value for ESTADO using the alias name Estado.
     * @param value value to set the ESTADO
     */
    public void setEstado(Number value) {
        setAttributeInternal(ESTADO, value);
    }

    /**
     * Gets the attribute value for ESTRUCTURADOR using the alias name Estructurador.
     * @return the ESTRUCTURADOR
     */
    public String getEstructurador() {
        return (String) getAttributeInternal(ESTRUCTURADOR);
    }

    /**
     * Sets <code>value</code> as attribute value for ESTRUCTURADOR using the alias name Estructurador.
     * @param value value to set the ESTRUCTURADOR
     */
    public void setEstructurador(String value) {
        setAttributeInternal(ESTRUCTURADOR, value);
    }

    /**
     * Gets the attribute value for ETAPA using the alias name Etapa.
     * @return the ETAPA
     */
    public Number getEtapa() {
        return (Number) getAttributeInternal(ETAPA);
    }

    /**
     * Sets <code>value</code> as attribute value for ETAPA using the alias name Etapa.
     * @param value value to set the ETAPA
     */
    public void setEtapa(Number value) {
        setAttributeInternal(ETAPA, value);
    }

    /**
     * Gets the attribute value for FECHA_BAJA using the alias name FechaBaja.
     * @return the FECHA_BAJA
     */
    public Timestamp getFechaBaja() {
        return (Timestamp) getAttributeInternal(FECHABAJA);
    }

    /**
     * Sets <code>value</code> as attribute value for FECHA_BAJA using the alias name FechaBaja.
     * @param value value to set the FECHA_BAJA
     */
    public void setFechaBaja(Timestamp value) {
        setAttributeInternal(FECHABAJA, value);
    }

    /**
     * Gets the attribute value for FECHA_INICIO using the alias name FechaInicio.
     * @return the FECHA_INICIO
     */
    public Timestamp getFechaInicio() {
        return (Timestamp) getAttributeInternal(FECHAINICIO);
    }

    /**
     * Sets <code>value</code> as attribute value for FECHA_INICIO using the alias name FechaInicio.
     * @param value value to set the FECHA_INICIO
     */
    public void setFechaInicio(Timestamp value) {
        setAttributeInternal(FECHAINICIO, value);
    }

    /**
     * Gets the attribute value for ID_ACTIVIDAD_ECONOMICA_ASOC using the alias name IdActividadEconomicaAsoc.
     * @return the ID_ACTIVIDAD_ECONOMICA_ASOC
     */
    public Number getIdActividadEconomicaAsoc() {
        return (Number) getAttributeInternal(IDACTIVIDADECONOMICAASOC);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_ACTIVIDAD_ECONOMICA_ASOC using the alias name IdActividadEconomicaAsoc.
     * @param value value to set the ID_ACTIVIDAD_ECONOMICA_ASOC
     */
    public void setIdActividadEconomicaAsoc(Number value) {
        setAttributeInternal(IDACTIVIDADECONOMICAASOC, value);
    }

    /**
     * Gets the attribute value for ID_CLIENTE using the alias name IdCliente.
     * @return the ID_CLIENTE
     */
    public Number getIdCliente() {
        return (Number) getAttributeInternal(IDCLIENTE);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_CLIENTE using the alias name IdCliente.
     * @param value value to set the ID_CLIENTE
     */
    public void setIdCliente(Number value) {
        setAttributeInternal(IDCLIENTE, value);
    }

    /**
     * Gets the attribute value for ID_OPERACION using the alias name IdOperacion.
     * @return the ID_OPERACION
     */
    public Number getIdOperacion() {
        return (Number) getAttributeInternal(IDOPERACION);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_OPERACION using the alias name IdOperacion.
     * @param value value to set the ID_OPERACION
     */
    public void setIdOperacion(Number value) {
        setAttributeInternal(IDOPERACION, value);
    }

    /**
     * Gets the attribute value for ID_PERSPECTIVA using the alias name IdPerspectiva.
     * @return the ID_PERSPECTIVA
     */
    public Number getIdPerspectiva() {
        return (Number) getAttributeInternal(IDPERSPECTIVA);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_PERSPECTIVA using the alias name IdPerspectiva.
     * @param value value to set the ID_PERSPECTIVA
     */
    public void setIdPerspectiva(Number value) {
        setAttributeInternal(IDPERSPECTIVA, value);
    }

    /**
     * Gets the attribute value for ID_PRODUCTO using the alias name IdProducto.
     * @return the ID_PRODUCTO
     */
    public Number getIdProducto() {
        return (Number) getAttributeInternal(IDPRODUCTO);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_PRODUCTO using the alias name IdProducto.
     * @param value value to set the ID_PRODUCTO
     */
    public void setIdProducto(Number value) {
        setAttributeInternal(IDPRODUCTO, value);
    }

    /**
     * Gets the attribute value for ID_RANGO_PAIS using the alias name IdRangoPais.
     * @return the ID_RANGO_PAIS
     */
    public Number getIdRangoPais() {
        return (Number) getAttributeInternal(IDRANGOPAIS);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_RANGO_PAIS using the alias name IdRangoPais.
     * @param value value to set the ID_RANGO_PAIS
     */
    public void setIdRangoPais(Number value) {
        setAttributeInternal(IDRANGOPAIS, value);
    }

    /**
     * Gets the attribute value for INICIATIVA_ESTRATEGICA using the alias name IniciativaEstrategica.
     * @return the INICIATIVA_ESTRATEGICA
     */
    public Number getIniciativaEstrategica() {
        return (Number) getAttributeInternal(INICIATIVAESTRATEGICA);
    }

    /**
     * Sets <code>value</code> as attribute value for INICIATIVA_ESTRATEGICA using the alias name IniciativaEstrategica.
     * @param value value to set the INICIATIVA_ESTRATEGICA
     */
    public void setIniciativaEstrategica(Number value) {
        setAttributeInternal(INICIATIVAESTRATEGICA, value);
    }

    /**
     * Gets the attribute value for JUSTIFICACION_CANCELA using the alias name JustificacionCancela.
     * @return the JUSTIFICACION_CANCELA
     */
    public String getJustificacionCancela() {
        return (String) getAttributeInternal(JUSTIFICACIONCANCELA);
    }

    /**
     * Sets <code>value</code> as attribute value for JUSTIFICACION_CANCELA using the alias name JustificacionCancela.
     * @param value value to set the JUSTIFICACION_CANCELA
     */
    public void setJustificacionCancela(String value) {
        setAttributeInternal(JUSTIFICACIONCANCELA, value);
    }

    /**
     * Gets the attribute value for NOMBRE using the alias name Nombre.
     * @return the NOMBRE
     */
    public String getNombre() {
        return (String) getAttributeInternal(NOMBRE);
    }

    /**
     * Sets <code>value</code> as attribute value for NOMBRE using the alias name Nombre.
     * @param value value to set the NOMBRE
     */
    public void setNombre(String value) {
        setAttributeInternal(NOMBRE, value);
    }

    /**
     * Gets the attribute value for OBJETIVOS_ESTRATEGICOS using the alias name ObjetivosEstrategicos.
     * @return the OBJETIVOS_ESTRATEGICOS
     */
    public Number getObjetivosEstrategicos() {
        return (Number) getAttributeInternal(OBJETIVOSESTRATEGICOS);
    }

    /**
     * Sets <code>value</code> as attribute value for OBJETIVOS_ESTRATEGICOS using the alias name ObjetivosEstrategicos.
     * @param value value to set the OBJETIVOS_ESTRATEGICOS
     */
    public void setObjetivosEstrategicos(Number value) {
        setAttributeInternal(OBJETIVOSESTRATEGICOS, value);
    }

    /**
     * Gets the attribute value for OBSERVACION_CANCELA using the alias name ObservacionCancela.
     * @return the OBSERVACION_CANCELA
     */
    public String getObservacionCancela() {
        return (String) getAttributeInternal(OBSERVACIONCANCELA);
    }

    /**
     * Sets <code>value</code> as attribute value for OBSERVACION_CANCELA using the alias name ObservacionCancela.
     * @param value value to set the OBSERVACION_CANCELA
     */
    public void setObservacionCancela(String value) {
        setAttributeInternal(OBSERVACIONCANCELA, value);
    }

    /**
     * Gets the attribute value for OFICINA using the alias name Oficina.
     * @return the OFICINA
     */
    public String getOficina() {
        return (String) getAttributeInternal(OFICINA);
    }

    /**
     * Sets <code>value</code> as attribute value for OFICINA using the alias name Oficina.
     * @param value value to set the OFICINA
     */
    public void setOficina(String value) {
        setAttributeInternal(OFICINA, value);
    }

    /**
     * Gets the attribute value for PROGRAMA using the alias name Programa.
     * @return the PROGRAMA
     */
    public String getPrograma() {
        return (String) getAttributeInternal(PROGRAMA);
    }

    /**
     * Sets <code>value</code> as attribute value for PROGRAMA using the alias name Programa.
     * @param value value to set the PROGRAMA
     */
    public void setPrograma(String value) {
        setAttributeInternal(PROGRAMA, value);
    }

    /**
     * Gets the attribute value for PROGRAMADO_POA using the alias name ProgramadoPoa.
     * @return the PROGRAMADO_POA
     */
    public Number getProgramadoPoa() {
        return (Number) getAttributeInternal(PROGRAMADOPOA);
    }

    /**
     * Sets <code>value</code> as attribute value for PROGRAMADO_POA using the alias name ProgramadoPoa.
     * @param value value to set the PROGRAMADO_POA
     */
    public void setProgramadoPoa(Number value) {
        setAttributeInternal(PROGRAMADOPOA, value);
    }

    /**
     * Gets the attribute value for RAROC using the alias name Raroc.
     * @return the RAROC
     */
    public Number getRaroc() {
        return (Number) getAttributeInternal(RAROC);
    }

    /**
     * Sets <code>value</code> as attribute value for RAROC using the alias name Raroc.
     * @param value value to set the RAROC
     */
    public void setRaroc(Number value) {
        setAttributeInternal(RAROC, value);
    }

    /**
     * Gets the attribute value for REQUIERE_RECURSOS_EXT using the alias name RequiereRecursosExt.
     * @return the REQUIERE_RECURSOS_EXT
     */
    public Number getRequiereRecursosExt() {
        return (Number) getAttributeInternal(REQUIERERECURSOSEXT);
    }

    /**
     * Sets <code>value</code> as attribute value for REQUIERE_RECURSOS_EXT using the alias name RequiereRecursosExt.
     * @param value value to set the REQUIERE_RECURSOS_EXT
     */
    public void setRequiereRecursosExt(Number value) {
        setAttributeInternal(REQUIERERECURSOSEXT, value);
    }

    /**
     * Gets the attribute value for SCR using the alias name Scr.
     * @return the SCR
     */
    public Number getScr() {
        return (Number) getAttributeInternal(SCR);
    }

    /**
     * Sets <code>value</code> as attribute value for SCR using the alias name Scr.
     * @param value value to set the SCR
     */
    public void setScr(Number value) {
        setAttributeInternal(SCR, value);
    }

    /**
     * Gets the attribute value for SCR_ESTATUS using the alias name ScrEstatus.
     * @return the SCR_ESTATUS
     */
    public String getScrEstatus() {
        return (String) getAttributeInternal(SCRESTATUS);
    }

    /**
     * Sets <code>value</code> as attribute value for SCR_ESTATUS using the alias name ScrEstatus.
     * @param value value to set the SCR_ESTATUS
     */
    public void setScrEstatus(String value) {
        setAttributeInternal(SCRESTATUS, value);
    }

    /**
     * Gets the attribute value for SECTOR_MERCADO using the alias name SectorMercado.
     * @return the SECTOR_MERCADO
     */
    public Number getSectorMercado() {
        return (Number) getAttributeInternal(SECTORMERCADO);
    }

    /**
     * Sets <code>value</code> as attribute value for SECTOR_MERCADO using the alias name SectorMercado.
     * @param value value to set the SECTOR_MERCADO
     */
    public void setSectorMercado(Number value) {
        setAttributeInternal(SECTORMERCADO, value);
    }

    /**
     * Gets the attribute value for TIPO_GARANTIA using the alias name TipoGarantia.
     * @return the TIPO_GARANTIA
     */
    public Number getTipoGarantia() {
        return (Number) getAttributeInternal(TIPOGARANTIA);
    }

    /**
     * Sets <code>value</code> as attribute value for TIPO_GARANTIA using the alias name TipoGarantia.
     * @param value value to set the TIPO_GARANTIA
     */
    public void setTipoGarantia(Number value) {
        setAttributeInternal(TIPOGARANTIA, value);
    }

    /**
     * Gets the attribute value for TIR using the alias name Tir.
     * @return the TIR
     */
    public Number getTir() {
        return (Number) getAttributeInternal(TIR);
    }

    /**
     * Sets <code>value</code> as attribute value for TIR using the alias name Tir.
     * @param value value to set the TIR
     */
    public void setTir(Number value) {
        setAttributeInternal(TIR, value);
    }

    /**
     * Gets the attribute value for TIR_ESTATUS using the alias name TirEstatus.
     * @return the TIR_ESTATUS
     */
    public String getTirEstatus() {
        return (String) getAttributeInternal(TIRESTATUS);
    }

    /**
     * Sets <code>value</code> as attribute value for TIR_ESTATUS using the alias name TirEstatus.
     * @param value value to set the TIR_ESTATUS
     */
    public void setTirEstatus(String value) {
        setAttributeInternal(TIRESTATUS, value);
    }

    /**
     * Gets the attribute value for UNIDAD_EJECUTORA using the alias name UnidadEjecutora.
     * @return the UNIDAD_EJECUTORA
     */
    public String getUnidadEjecutora() {
        return (String) getAttributeInternal(UNIDADEJECUTORA);
    }

    /**
     * Sets <code>value</code> as attribute value for UNIDAD_EJECUTORA using the alias name UnidadEjecutora.
     * @param value value to set the UNIDAD_EJECUTORA
     */
    public void setUnidadEjecutora(String value) {
        setAttributeInternal(UNIDADEJECUTORA, value);
    }

    /**
     * Gets the attribute value for USUARIO using the alias name Usuario.
     * @return the USUARIO
     */
    public String getUsuario() {
        return (String) getAttributeInternal(USUARIO);
    }

    /**
     * Sets <code>value</code> as attribute value for USUARIO using the alias name Usuario.
     * @param value value to set the USUARIO
     */
    public void setUsuario(String value) {
        setAttributeInternal(USUARIO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MontoSolicitado.
     * @return the MontoSolicitado
     */
    public BigDecimal getMontoSolicitado() {
        return (BigDecimal) getAttributeInternal(MONTOSOLICITADO);
    }

    /**
     * Gets the attribute value for the calculated attribute MontoTotal.
     * @return the MontoTotal
     */
    public BigDecimal getMontoTotal() {
        return (BigDecimal) getAttributeInternal(MONTOTOTAL);
    }


    /**
     * Gets the attribute value for ES_FACTIBLE using the alias name EsFactible.
     * @return the ES_FACTIBLE
     */
    public Integer getEsFactible() {
        return (Integer) getAttributeInternal(ESFACTIBLE);
    }

    /**
     * Sets <code>value</code> as attribute value for ES_FACTIBLE using the alias name EsFactible.
     * @param value value to set the ES_FACTIBLE
     */
    public void setEsFactible(Integer value) {
        setAttributeInternal(ESFACTIBLE, value);
    }

    /**
     * Gets the attribute value for ID_TCA_TIPO_TASA using the alias name IdTcaTipoTasa.
     * @return the ID_TCA_TIPO_TASA
     */
    public Integer getIdTcaTipoTasa() {
        return (Integer) getAttributeInternal(IDTCATIPOTASA);
    }

    /**
     * Sets <code>value</code> as attribute value for ID_TCA_TIPO_TASA using the alias name IdTcaTipoTasa.
     * @param value value to set the ID_TCA_TIPO_TASA
     */
    public void setIdTcaTipoTasa(Integer value) {
        setAttributeInternal(IDTCATIPOTASA, value);
    }

    /**
     * Gets the attribute value for CODIGO_TASA_REFERENCIA using the alias name CodigoTasaReferencia.
     * @return the CODIGO_TASA_REFERENCIA
     */
    public String getCodigoTasaReferencia() {
        return (String) getAttributeInternal(CODIGOTASAREFERENCIA);
    }

    /**
     * Sets <code>value</code> as attribute value for CODIGO_TASA_REFERENCIA using the alias name CodigoTasaReferencia.
     * @param value value to set the CODIGO_TASA_REFERENCIA
     */
    public void setCodigoTasaReferencia(String value) {
        setAttributeInternal(CODIGOTASAREFERENCIA, value);
    }


    /**
     * Gets the attribute value for FECHA_FINALIZACION_ESTUDIOS using the alias name FechaFinalizacionEstudios.
     * @return the FECHA_FINALIZACION_ESTUDIOS
     */
    public Date getFechaFinalizacionEstudios() {
        return (Date) getAttributeInternal(FECHAFINALIZACIONESTUDIOS);
    }

    /**
     * Sets <code>value</code> as attribute value for FECHA_FINALIZACION_ESTUDIOS using the alias name FechaFinalizacionEstudios.
     * @param value value to set the FECHA_FINALIZACION_ESTUDIOS
     */
    public void setFechaFinalizacionEstudios(Date value) {
        setAttributeInternal(FECHAFINALIZACIONESTUDIOS, value);
    }

    /**
     * Gets the attribute value for FECHA_CALCULO_INTERES using the alias name FechaCalculoInteres.
     * @return the FECHA_CALCULO_INTERES
     */
    public Date getFechaCalculoInteres() {
        return (Date) getAttributeInternal(FECHACALCULOINTERES);
    }

    /**
     * Sets <code>value</code> as attribute value for FECHA_CALCULO_INTERES using the alias name FechaCalculoInteres.
     * @param value value to set the FECHA_CALCULO_INTERES
     */
    public void setFechaCalculoInteres(Date value) {
        setAttributeInternal(FECHACALCULOINTERES, value);
    }

    /**
     * Gets the attribute value for VALOR_SPREAD using the alias name ValorSpread.
     * @return the VALOR_SPREAD
     */
    public Number getValorSpread() {
        return (Number) getAttributeInternal(VALORSPREAD);
    }

    /**
     * Sets <code>value</code> as attribute value for VALOR_SPREAD using the alias name ValorSpread.
     * @param value value to set the VALOR_SPREAD
     */
    public void setValorSpread(Number value) {
        setAttributeInternal(VALORSPREAD, value);
    }

    /**
     * Gets the attribute value for VALOR_TASA using the alias name ValorTasa.
     * @return the VALOR_TASA
     */
    public Number getValorTasa() {
        return (Number) getAttributeInternal(VALORTASA);
    }

    /**
     * Sets <code>value</code> as attribute value for VALOR_TASA using the alias name ValorTasa.
     * @param value value to set the VALOR_TASA
     */
    public void setValorTasa(Number value) {
        setAttributeInternal(VALORTASA, value);
    }

    /**
     * Gets the attribute value for JUSTIFICACION_ENVIO using the alias name JustificacionEnvio.
     * @return the JUSTIFICACION_ENVIO
     */
    public String getJustificacionEnvio() {
        return (String) getAttributeInternal(JUSTIFICACIONENVIO);
    }

    /**
     * Sets <code>value</code> as attribute value for JUSTIFICACION_ENVIO using the alias name JustificacionEnvio.
     * @param value value to set the JUSTIFICACION_ENVIO
     */
    public void setJustificacionEnvio(String value) {
        setAttributeInternal(JUSTIFICACIONENVIO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FechaInteresesCalculados.
     * @return the FechaInteresesCalculados
     */
    public Date getFechaInteresesCalculados() {
        return (Date) getAttributeInternal(FECHAINTERESESCALCULADOS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FechaInteresesCalculados.
     * @param value value to set the  FechaInteresesCalculados
     */
    public void setFechaInteresesCalculados(Date value) {
        setAttributeInternal(FECHAINTERESESCALCULADOS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TotalEnvioCobro.
     * @return the TotalEnvioCobro
     */
    public BigDecimal getTotalEnvioCobro() {
        return (BigDecimal) getAttributeInternal(TOTALENVIOCOBRO);
    }

    /**
     * Gets the attribute value for the calculated attribute TasaTotal.
     * @return the TasaTotal
     */
    public Number getTasaTotal() {
        return (Number) getAttributeInternal(TASATOTAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TasaTotal.
     * @param value value to set the  TasaTotal
     */
    public void setTasaTotal(Number value) {
        setAttributeInternal(TASATOTAL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CodigoMoneda.
     * @return the CodigoMoneda
     */
    public String getCodigoMoneda() {
        return (String) getAttributeInternal(CODIGOMONEDA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CodigoMoneda.
     * @param value value to set the  CodigoMoneda
     */
    public void setCodigoMoneda(String value) {
        setAttributeInternal(CODIGOMONEDA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CodTasRef.
     * @return the CodTasRef
     */
    public String getCodTasRef() {
        return (String) getAttributeInternal(CODTASREF);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CodTasRef.
     * @param value value to set the  CodTasRef
     */
    public void setCodTasRef(String value) {
        setAttributeInternal(CODTASREF, value);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TcaCompartidaValorVo.
     */
    public RowSet getTcaCompartidaValorVo() {
        return (RowSet) getAttributeInternal(TCACOMPARTIDAVALORVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TcaTipoTasaOperacionVO.
     */
    public RowSet getTcaTipoTasaOperacionVO() {
        return (RowSet) getAttributeInternal(TCATIPOTASAOPERACIONVO);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TasaReferenciaVO.
     */
    public RowSet getTasaReferenciaVO() {
        return (RowSet) getAttributeInternal(TASAREFERENCIAVO);
    }
}

