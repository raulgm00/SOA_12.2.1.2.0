package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Aug 25 17:41:12 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class LineaCreditoDesembolsosVORowImpl extends ViewRowImpl {


    public static final int ENTITY_LINEACREDITOEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        BanEstatus,
        CodigoAsignacion,
        CodigoPlantillaLimite,
        CodigoSerialLimite,
        CondicionEspecial,
        DescripcionAsignacion,
        DescripcionCondEspecial,
        DescripcionLinea,
        EsRevolvente,
        FechaRegistro,
        FechaValor,
        FechaVencimiento,
        Fondo,
        DescripcionFondo,
        IdContrato,
        IdFlexcube,
        IdFlexcubePasivo,
        IdProductoFlexcube,
        MontoLinea,
        NumeroLineaCredito,
        TratamientoDiasFeriados,
        IdSolicitud,
        IdOperacion,
        ContratoDesembolsoVO,
        ContratoDesembolsoTreeVO,
        ContratoDesembolsoTreeVO1;
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


    public static final int CONTRATODESEMBOLSOVO1 = AttributesEnum.ContratoDesembolsoVO.index();

    public static final int ID = AttributesEnum.Id.index();
    public static final int BANESTATUS = AttributesEnum.BanEstatus.index();
    public static final int CODIGOASIGNACION = AttributesEnum.CodigoAsignacion.index();
    public static final int CODIGOPLANTILLALIMITE = AttributesEnum.CodigoPlantillaLimite.index();
    public static final int CODIGOSERIALLIMITE = AttributesEnum.CodigoSerialLimite.index();
    public static final int CONDICIONESPECIAL = AttributesEnum.CondicionEspecial.index();
    public static final int DESCRIPCIONASIGNACION = AttributesEnum.DescripcionAsignacion.index();
    public static final int DESCRIPCIONCONDESPECIAL = AttributesEnum.DescripcionCondEspecial.index();
    public static final int DESCRIPCIONLINEA = AttributesEnum.DescripcionLinea.index();
    public static final int ESREVOLVENTE = AttributesEnum.EsRevolvente.index();
    public static final int FECHAREGISTRO = AttributesEnum.FechaRegistro.index();
    public static final int FECHAVALOR = AttributesEnum.FechaValor.index();
    public static final int FECHAVENCIMIENTO = AttributesEnum.FechaVencimiento.index();
    public static final int FONDO = AttributesEnum.Fondo.index();
    public static final int DESCRIPCIONFONDO = AttributesEnum.DescripcionFondo.index();
    public static final int IDCONTRATO = AttributesEnum.IdContrato.index();
    public static final int IDFLEXCUBE = AttributesEnum.IdFlexcube.index();
    public static final int IDFLEXCUBEPASIVO = AttributesEnum.IdFlexcubePasivo.index();
    public static final int IDPRODUCTOFLEXCUBE = AttributesEnum.IdProductoFlexcube.index();
    public static final int MONTOLINEA = AttributesEnum.MontoLinea.index();
    public static final int NUMEROLINEACREDITO = AttributesEnum.NumeroLineaCredito.index();
    public static final int TRATAMIENTODIASFERIADOS = AttributesEnum.TratamientoDiasFeriados.index();
    public static final int IDSOLICITUD = AttributesEnum.IdSolicitud.index();
    public static final int IDOPERACION = AttributesEnum.IdOperacion.index();
    public static final int CONTRATODESEMBOLSOVO = AttributesEnum.ContratoDesembolsoVO.index();
    public static final int CONTRATODESEMBOLSOTREEVO = AttributesEnum.ContratoDesembolsoTreeVO.index();
    public static final int CONTRATODESEMBOLSOTREEVO1 = AttributesEnum.ContratoDesembolsoTreeVO1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public LineaCreditoDesembolsosVORowImpl() {
    }
}

