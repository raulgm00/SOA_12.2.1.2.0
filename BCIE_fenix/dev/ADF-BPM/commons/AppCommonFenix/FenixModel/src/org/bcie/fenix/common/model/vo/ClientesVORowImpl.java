package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jan 30 10:15:02 CST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ClientesVORowImpl extends ViewRowImpl {
    public static final int ENTITY_CLIENTESEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        IdCliente,
        Abreviatura,
        Autorizo,
        BanEstatus,
        ComentarioAprobacion,
        DiaPago,
        Ejecutivo,
        EstaDeteriorado,
        FechaAprobacion,
        FechaBaja,
        FechaRegistro,
        GrupoEconomico,
        IdFlexcube,
        IdTcaPerspectiva,
        IdTcaScr,
        NumeroIdentificacion,
        Oficina,
        Pais,
        RazonSocial,
        RequiereEnvioAutomatico,
        Sector,
        TipoCliente,
        TipoIdentificacion,
        TipoInstitucion,
        TipoPersona;
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
    public static final int IDCLIENTE = AttributesEnum.IdCliente.index();
    public static final int ABREVIATURA = AttributesEnum.Abreviatura.index();
    public static final int AUTORIZO = AttributesEnum.Autorizo.index();
    public static final int BANESTATUS = AttributesEnum.BanEstatus.index();
    public static final int COMENTARIOAPROBACION = AttributesEnum.ComentarioAprobacion.index();
    public static final int DIAPAGO = AttributesEnum.DiaPago.index();
    public static final int EJECUTIVO = AttributesEnum.Ejecutivo.index();
    public static final int ESTADETERIORADO = AttributesEnum.EstaDeteriorado.index();
    public static final int FECHAAPROBACION = AttributesEnum.FechaAprobacion.index();
    public static final int FECHABAJA = AttributesEnum.FechaBaja.index();
    public static final int FECHAREGISTRO = AttributesEnum.FechaRegistro.index();
    public static final int GRUPOECONOMICO = AttributesEnum.GrupoEconomico.index();
    public static final int IDFLEXCUBE = AttributesEnum.IdFlexcube.index();
    public static final int IDTCAPERSPECTIVA = AttributesEnum.IdTcaPerspectiva.index();
    public static final int IDTCASCR = AttributesEnum.IdTcaScr.index();
    public static final int NUMEROIDENTIFICACION = AttributesEnum.NumeroIdentificacion.index();
    public static final int OFICINA = AttributesEnum.Oficina.index();
    public static final int PAIS = AttributesEnum.Pais.index();
    public static final int RAZONSOCIAL = AttributesEnum.RazonSocial.index();
    public static final int REQUIEREENVIOAUTOMATICO = AttributesEnum.RequiereEnvioAutomatico.index();
    public static final int SECTOR = AttributesEnum.Sector.index();
    public static final int TIPOCLIENTE = AttributesEnum.TipoCliente.index();
    public static final int TIPOIDENTIFICACION = AttributesEnum.TipoIdentificacion.index();
    public static final int TIPOINSTITUCION = AttributesEnum.TipoInstitucion.index();
    public static final int TIPOPERSONA = AttributesEnum.TipoPersona.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ClientesVORowImpl() {
    }
}

