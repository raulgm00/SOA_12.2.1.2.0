package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Mar 02 12:48:14 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ContrapartesTerminoVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        IdContraparte,
        NombreContraparte,
        esAgregado,
        esEliminado;
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


    public static final int IDCONTRAPARTE = AttributesEnum.IdContraparte.index();
    public static final int NOMBRECONTRAPARTE = AttributesEnum.NombreContraparte.index();
    public static final int ESAGREGADO = AttributesEnum.esAgregado.index();
    public static final int ESELIMINADO = AttributesEnum.esEliminado.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ContrapartesTerminoVORowImpl() {
    }
}

