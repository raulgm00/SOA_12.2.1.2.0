package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Oct 21 19:08:00 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EvidenciasSolicitudVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdSolicitud,
        IdDocumento,
        Filename,
        IdOnbase;
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
    public static final int IDSOLICITUD = AttributesEnum.IdSolicitud.index();
    public static final int IDDOCUMENTO = AttributesEnum.IdDocumento.index();
    public static final int FILENAME = AttributesEnum.Filename.index();
    public static final int IDONBASE = AttributesEnum.IdOnbase.index();

    /**
     * This is the default constructor (do not remove).
     */
    public EvidenciasSolicitudVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute Id.
     * @return the Id
     */
    public Long getId() {
        return (Long) getAttributeInternal(ID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Id.
     * @param value value to set the  Id
     */
    public void setId(Long value) {
        setAttributeInternal(ID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdSolicitud.
     * @return the IdSolicitud
     */
    public Long getIdSolicitud() {
        return (Long) getAttributeInternal(IDSOLICITUD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdSolicitud.
     * @param value value to set the  IdSolicitud
     */
    public void setIdSolicitud(Long value) {
        setAttributeInternal(IDSOLICITUD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdDocumento.
     * @return the IdDocumento
     */
    public Long getIdDocumento() {
        return (Long) getAttributeInternal(IDDOCUMENTO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdDocumento.
     * @param value value to set the  IdDocumento
     */
    public void setIdDocumento(Long value) {
        setAttributeInternal(IDDOCUMENTO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Filename.
     * @return the Filename
     */
    public String getFilename() {
        return (String) getAttributeInternal(FILENAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Filename.
     * @param value value to set the  Filename
     */
    public void setFilename(String value) {
        setAttributeInternal(FILENAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdOnbase.
     * @return the IdOnbase
     */
    public String getIdOnbase() {
        return (String) getAttributeInternal(IDONBASE);
    }
}

