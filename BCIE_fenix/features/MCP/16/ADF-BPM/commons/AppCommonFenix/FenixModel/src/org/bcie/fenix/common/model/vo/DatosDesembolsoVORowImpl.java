package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.server.ViewRowImpl;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Sep 13 19:12:53 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DatosDesembolsoVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        MontoDesembolsar,
        IdTcaTipoMoneda,
        IdTcaEstado,
        Programado,
        FechaEstimadaDesembolsar,
        FechaEfectiva,
        FechaRegistro,
        BanEstatus,
        ContratoFlexcube,
        CuentaConRecursosExternos,
        MontoDesembolsarUsd,
        Fondo,
        ProgramaOperacion,
        DestinoFinanciamiento,
        IdTcaModalidadFinan,
        IdCatActividadEconomica,
        IdCatAreaFocalizacion,
        IdCatEjeEstrategico,
        IdTcaActividadEconomicaF1,
        IdTcaProyMunicipal,
        IniciativaEstrategica,
        IdTcaProductoPrograma;
        private static AttributesEnum[] vals = null;
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


    public static final int ID = AttributesEnum.Id.index();
    public static final int MONTODESEMBOLSAR = AttributesEnum.MontoDesembolsar.index();
    public static final int IDTCATIPOMONEDA = AttributesEnum.IdTcaTipoMoneda.index();
    public static final int IDTCAESTADO = AttributesEnum.IdTcaEstado.index();
    public static final int PROGRAMADO = AttributesEnum.Programado.index();
    public static final int FECHAESTIMADADESEMBOLSAR = AttributesEnum.FechaEstimadaDesembolsar.index();
    public static final int FECHAEFECTIVA = AttributesEnum.FechaEfectiva.index();
    public static final int FECHAREGISTRO = AttributesEnum.FechaRegistro.index();
    public static final int BANESTATUS = AttributesEnum.BanEstatus.index();
    public static final int CONTRATOFLEXCUBE = AttributesEnum.ContratoFlexcube.index();
    public static final int CUENTACONRECURSOSEXTERNOS = AttributesEnum.CuentaConRecursosExternos.index();
    public static final int MONTODESEMBOLSARUSD = AttributesEnum.MontoDesembolsarUsd.index();
    public static final int FONDO = AttributesEnum.Fondo.index();
    public static final int PROGRAMAOPERACION = AttributesEnum.ProgramaOperacion.index();
    public static final int DESTINOFINANCIAMIENTO = AttributesEnum.DestinoFinanciamiento.index();
    public static final int IDTCAMODALIDADFINAN = AttributesEnum.IdTcaModalidadFinan.index();
    public static final int IDCATACTIVIDADECONOMICA = AttributesEnum.IdCatActividadEconomica.index();
    public static final int IDCATAREAFOCALIZACION = AttributesEnum.IdCatAreaFocalizacion.index();
    public static final int IDCATEJEESTRATEGICO = AttributesEnum.IdCatEjeEstrategico.index();
    public static final int IDTCAACTIVIDADECONOMICAF1 = AttributesEnum.IdTcaActividadEconomicaF1.index();
    public static final int IDTCAPROYMUNICIPAL = AttributesEnum.IdTcaProyMunicipal.index();
    public static final int INICIATIVAESTRATEGICA = AttributesEnum.IniciativaEstrategica.index();
    public static final int IDTCAPRODUCTOPROGRAMA = AttributesEnum.IdTcaProductoPrograma.index();

    /**
     * This is the default constructor (do not remove).
     */
    public DatosDesembolsoVORowImpl() {
    }
}

