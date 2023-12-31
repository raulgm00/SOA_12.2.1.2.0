package org.bcie.fenix.common.model.vo;

import java.sql.Timestamp;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Oct 04 15:51:46 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DatosHerramientaVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdIniciativaEstrategica,
        IdRangoPaises,
        IdAreaFocalizacion,
        IdEjeEstrategico,
        BanEstatus,
        FechaRegistro,
        CodExterno,
        IdTcaCicloEstrategico,
        IdTcaClasifEmpresarial,
        IdTcaProyectoMunicipal,
        IdTcaActividadEconomicaF1,
        CodigoPrograma;
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
    public static final int IDINICIATIVAESTRATEGICA = AttributesEnum.IdIniciativaEstrategica.index();
    public static final int IDRANGOPAISES = AttributesEnum.IdRangoPaises.index();
    public static final int IDAREAFOCALIZACION = AttributesEnum.IdAreaFocalizacion.index();
    public static final int IDEJEESTRATEGICO = AttributesEnum.IdEjeEstrategico.index();
    public static final int BANESTATUS = AttributesEnum.BanEstatus.index();
    public static final int FECHAREGISTRO = AttributesEnum.FechaRegistro.index();
    public static final int CODEXTERNO = AttributesEnum.CodExterno.index();
    public static final int IDTCACICLOESTRATEGICO = AttributesEnum.IdTcaCicloEstrategico.index();
    public static final int IDTCACLASIFEMPRESARIAL = AttributesEnum.IdTcaClasifEmpresarial.index();
    public static final int IDTCAPROYECTOMUNICIPAL = AttributesEnum.IdTcaProyectoMunicipal.index();
    public static final int IDTCAACTIVIDADECONOMICAF1 = AttributesEnum.IdTcaActividadEconomicaF1.index();
    public static final int CODIGOPROGRAMA = AttributesEnum.CodigoPrograma.index();

    /**
     * This is the default constructor (do not remove).
     */
    public DatosHerramientaVORowImpl() {
    }
}

