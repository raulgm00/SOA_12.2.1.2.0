package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Oct 20 19:26:56 CDT 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ClasificacionAmbientalVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        id,
        idSector,
        idAporte,
        idCategoria,
        idSubcategoria;
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
    public static final int ID = AttributesEnum.id.index();
    public static final int IDSECTOR = AttributesEnum.idSector.index();
    public static final int IDAPORTE = AttributesEnum.idAporte.index();
    public static final int IDCATEGORIA = AttributesEnum.idCategoria.index();
    public static final int IDSUBCATEGORIA = AttributesEnum.idSubcategoria.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ClasificacionAmbientalVORowImpl() {
    }
}

