package org.bcie.fenix.common.model.vo;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Nov 24 13:57:24 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TreComiteRolVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdTcaNivelAprobacion,
        IdTcaRolBpm,
        EsSoloNotificacion;
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
    public static final int IDTCANIVELAPROBACION = AttributesEnum.IdTcaNivelAprobacion.index();
    public static final int IDTCAROLBPM = AttributesEnum.IdTcaRolBpm.index();
    public static final int ESSOLONOTIFICACION = AttributesEnum.EsSoloNotificacion.index();

    /**
     * This is the default constructor (do not remove).
     */
    public TreComiteRolVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute Id.
     * @return the Id
     */
    public Number getId() {
        return (Number) getAttributeInternal(ID);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTcaNivelAprobacion.
     * @return the IdTcaNivelAprobacion
     */
    public Integer getIdTcaNivelAprobacion() {
        return (Integer) getAttributeInternal(IDTCANIVELAPROBACION);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTcaRolBpm.
     * @return the IdTcaRolBpm
     */
    public Integer getIdTcaRolBpm() {
        return (Integer) getAttributeInternal(IDTCAROLBPM);
    }

    /**
     * Gets the attribute value for the calculated attribute EsSoloNotificacion.
     * @return the EsSoloNotificacion
     */
    public Integer getEsSoloNotificacion() {
        return (Integer) getAttributeInternal(ESSOLONOTIFICACION);
    }
}

