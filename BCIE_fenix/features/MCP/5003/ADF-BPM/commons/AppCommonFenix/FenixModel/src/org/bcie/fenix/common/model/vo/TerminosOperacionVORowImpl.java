package org.bcie.fenix.common.model.vo;

import java.sql.Timestamp;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Feb 11 12:18:02 COT 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TerminosOperacionVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdTipoTermino,
        TipoTermino,
        Fecha;
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
    public static final int IDTIPOTERMINO = AttributesEnum.IdTipoTermino.index();
    public static final int TIPOTERMINO = AttributesEnum.TipoTermino.index();
    public static final int FECHA = AttributesEnum.Fecha.index();

    /**
     * This is the default constructor (do not remove).
     */
    public TerminosOperacionVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute Id.
     * @return the Id
     */
    public Long getId() {
        return (Long) getAttributeInternal(ID);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTipoTermino.
     * @return the IdTipoTermino
     */
    public Integer getIdTipoTermino() {
        return (Integer) getAttributeInternal(IDTIPOTERMINO);
    }

    /**
     * Gets the attribute value for the calculated attribute TipoTermino.
     * @return the TipoTermino
     */
    public String getTipoTermino() {
        return (String) getAttributeInternal(TIPOTERMINO);
    }

    /**
     * Gets the attribute value for the calculated attribute Fecha.
     * @return the Fecha
     */
    public Timestamp getFecha() {
        return (Timestamp) getAttributeInternal(FECHA);
    }
}

