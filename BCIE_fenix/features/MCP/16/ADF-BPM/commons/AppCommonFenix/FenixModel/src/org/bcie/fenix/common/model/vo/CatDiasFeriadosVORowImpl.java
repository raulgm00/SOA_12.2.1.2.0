package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Dec 14 22:41:20 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CatDiasFeriadosVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        CodigoExterno,
        Descripcion;
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
    public static final int CODIGOEXTERNO = AttributesEnum.CodigoExterno.index();
    public static final int DESCRIPCION = AttributesEnum.Descripcion.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CatDiasFeriadosVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute CodigoExterno.
     * @return the CodigoExterno
     */
    public String getCodigoExterno() {
        return (String) getAttributeInternal(CODIGOEXTERNO);
    }

    /**
     * Gets the attribute value for the calculated attribute Descripcion.
     * @return the Descripcion
     */
    public String getDescripcion() {
        return (String) getAttributeInternal(DESCRIPCION);
    }
}

