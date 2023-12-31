package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Aug 01 15:49:45 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TccTreeTcaComisionVORowImpl extends ViewRowImpl {
    public static final int ENTITY_TCACOMISIONEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        Descripcion,
        DescripcionCorta,
        IdTcaTipoComision,
        CodExterno,
        Tipo,
        IdComision,
        IdTcaEstadoTcc,
        IdTcaSubEstadoTcc,
        DescripcionEstado,
        DescripcionSubEstado,
        Nombre,
        Cantidad,
        TccTreeComisionVO;
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
    public static final int DESCRIPCION = AttributesEnum.Descripcion.index();
    public static final int DESCRIPCIONCORTA = AttributesEnum.DescripcionCorta.index();
    public static final int IDTCATIPOCOMISION = AttributesEnum.IdTcaTipoComision.index();
    public static final int CODEXTERNO = AttributesEnum.CodExterno.index();
    public static final int TIPO = AttributesEnum.Tipo.index();
    public static final int IDCOMISION = AttributesEnum.IdComision.index();
    public static final int IDTCAESTADOTCC = AttributesEnum.IdTcaEstadoTcc.index();
    public static final int IDTCASUBESTADOTCC = AttributesEnum.IdTcaSubEstadoTcc.index();
    public static final int DESCRIPCIONESTADO = AttributesEnum.DescripcionEstado.index();
    public static final int DESCRIPCIONSUBESTADO = AttributesEnum.DescripcionSubEstado.index();
    public static final int NOMBRE = AttributesEnum.Nombre.index();
    public static final int CANTIDAD = AttributesEnum.Cantidad.index();
    public static final int TCCTREECOMISIONVO = AttributesEnum.TccTreeComisionVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public TccTreeTcaComisionVORowImpl() {
    }
}

