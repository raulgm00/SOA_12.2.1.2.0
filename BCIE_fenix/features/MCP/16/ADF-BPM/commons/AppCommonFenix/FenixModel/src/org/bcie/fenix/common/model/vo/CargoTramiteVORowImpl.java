package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jan 07 11:16:28 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CargoTramiteVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        TipoComision,
        TipoMoneda,
        TipoMontoBase,
        TipoFrecuencia,
        TipoFondo,
        TipoEstadoTCC,
        TipoSubEstadoTCC,
        Nombre,
        Observaciones,
        Validar,
        BanEstatus,
        FechaVencimiento,
        FechaInicioComision,
        FechaInicioCapital,
        FechaRegistro,
        FechaValor,
        EsTasa,
        EsRequerido,
        MontoComision,
        MontoBase,
        Porcentaje,
        TipoMomentoCobro,
        FrecuenciaPago,
        IdOperacion,
        Id,
        IdTipoMoneda,
        IdEstadoTCC,
        IdSubEstadoTCC,
        IdMomentoCobro,
        IdFondo,
        IdTipoFrecuencia,
        IdTipoComision,
        IdMontoBase,
        IdFlexcube,
        CodigoContrato,
        EsVencimiento,
        ComisionValor,
        EsMonto,
        EsCobro,
        BtnSalida,
        EsColor,
        TcaTipoMonedaVO,
        TcaEstadoTccVO,
        TcaMomentoCobroVO,
        FondoAplicaVO,
        TcaTipoFrecuenciaVO,
        TcaTipoComisionVO,
        TcaMontoBaseComisionVO,
        TcaCatComisionVO;
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


    public static final int TIPOCOMISION = AttributesEnum.TipoComision.index();
    public static final int TIPOMONEDA = AttributesEnum.TipoMoneda.index();
    public static final int TIPOMONTOBASE = AttributesEnum.TipoMontoBase.index();
    public static final int TIPOFRECUENCIA = AttributesEnum.TipoFrecuencia.index();
    public static final int TIPOFONDO = AttributesEnum.TipoFondo.index();
    public static final int TIPOESTADOTCC = AttributesEnum.TipoEstadoTCC.index();
    public static final int TIPOSUBESTADOTCC = AttributesEnum.TipoSubEstadoTCC.index();
    public static final int NOMBRE = AttributesEnum.Nombre.index();
    public static final int OBSERVACIONES = AttributesEnum.Observaciones.index();
    public static final int VALIDAR = AttributesEnum.Validar.index();
    public static final int BANESTATUS = AttributesEnum.BanEstatus.index();
    public static final int FECHAVENCIMIENTO = AttributesEnum.FechaVencimiento.index();
    public static final int FECHAINICIOCOMISION = AttributesEnum.FechaInicioComision.index();
    public static final int FECHAINICIOCAPITAL = AttributesEnum.FechaInicioCapital.index();
    public static final int FECHAREGISTRO = AttributesEnum.FechaRegistro.index();
    public static final int FECHAVALOR = AttributesEnum.FechaValor.index();
    public static final int ESTASA = AttributesEnum.EsTasa.index();
    public static final int ESREQUERIDO = AttributesEnum.EsRequerido.index();
    public static final int MONTOCOMISION = AttributesEnum.MontoComision.index();
    public static final int MONTOBASE = AttributesEnum.MontoBase.index();
    public static final int PORCENTAJE = AttributesEnum.Porcentaje.index();
    public static final int TIPOMOMENTOCOBRO = AttributesEnum.TipoMomentoCobro.index();
    public static final int FRECUENCIAPAGO = AttributesEnum.FrecuenciaPago.index();
    public static final int IDOPERACION = AttributesEnum.IdOperacion.index();
    public static final int ID = AttributesEnum.Id.index();
    public static final int IDTIPOMONEDA = AttributesEnum.IdTipoMoneda.index();
    public static final int IDESTADOTCC = AttributesEnum.IdEstadoTCC.index();
    public static final int IDSUBESTADOTCC = AttributesEnum.IdSubEstadoTCC.index();
    public static final int IDMOMENTOCOBRO = AttributesEnum.IdMomentoCobro.index();
    public static final int IDFONDO = AttributesEnum.IdFondo.index();
    public static final int IDTIPOFRECUENCIA = AttributesEnum.IdTipoFrecuencia.index();
    public static final int IDTIPOCOMISION = AttributesEnum.IdTipoComision.index();
    public static final int IDMONTOBASE = AttributesEnum.IdMontoBase.index();
    public static final int IDFLEXCUBE = AttributesEnum.IdFlexcube.index();
    public static final int CODIGOCONTRATO = AttributesEnum.CodigoContrato.index();
    public static final int ESVENCIMIENTO = AttributesEnum.EsVencimiento.index();
    public static final int COMISIONVALOR = AttributesEnum.ComisionValor.index();
    public static final int ESMONTO = AttributesEnum.EsMonto.index();
    public static final int ESCOBRO = AttributesEnum.EsCobro.index();
    public static final int BTNSALIDA = AttributesEnum.BtnSalida.index();
    public static final int ESCOLOR = AttributesEnum.EsColor.index();
    public static final int TCATIPOMONEDAVO = AttributesEnum.TcaTipoMonedaVO.index();
    public static final int TCAESTADOTCCVO = AttributesEnum.TcaEstadoTccVO.index();
    public static final int TCAMOMENTOCOBROVO = AttributesEnum.TcaMomentoCobroVO.index();
    public static final int FONDOAPLICAVO = AttributesEnum.FondoAplicaVO.index();
    public static final int TCATIPOFRECUENCIAVO = AttributesEnum.TcaTipoFrecuenciaVO.index();
    public static final int TCATIPOCOMISIONVO = AttributesEnum.TcaTipoComisionVO.index();
    public static final int TCAMONTOBASECOMISIONVO = AttributesEnum.TcaMontoBaseComisionVO.index();
    public static final int TCACATCOMISIONVO = AttributesEnum.TcaCatComisionVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CargoTramiteVORowImpl() {
    }
}

