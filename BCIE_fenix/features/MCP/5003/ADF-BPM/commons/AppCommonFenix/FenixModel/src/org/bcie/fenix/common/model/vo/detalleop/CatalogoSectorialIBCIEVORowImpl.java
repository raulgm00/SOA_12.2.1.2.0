package org.bcie.fenix.common.model.vo.detalleop;

import oracle.jbo.server.ViewRowImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Sep 20 10:23:13 CDT 2021
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CatalogoSectorialIBCIEVORowImpl extends ViewRowImpl {


    public static final int IDOPERACION = AttributesEnum.idOperacion.index();
    public static final int ESMULTISECTORIAL = AttributesEnum.esMultisectorial.index();
    public static final int IDSECTORIBCIE = AttributesEnum.idSectorIbcie.index();
    public static final int SECTORIBCIE = AttributesEnum.sectorIbcie.index();
    public static final int IDSUBSECTORIBCIE = AttributesEnum.idSubSectorIbcie.index();
    public static final int SUBSECTORIBCIE = AttributesEnum.subSectorIbcie.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CatalogoSectorialIBCIEVORowImpl() {
    }


    /**
     * Gets the attribute value for the calculated attribute idOperacion.
     * @return the idOperacion
     */
    public Integer getidOperacion() {
        return (Integer) getAttributeInternal(IDOPERACION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute idOperacion.
     * @param value value to set the  idOperacion
     */
    public void setidOperacion(Integer value) {
        setAttributeInternal(IDOPERACION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute esMultisectorial.
     * @return the esMultisectorial
     */
    public Integer getesMultisectorial() {
        return (Integer) getAttributeInternal(ESMULTISECTORIAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute esMultisectorial.
     * @param value value to set the  esMultisectorial
     */
    public void setesMultisectorial(Integer value) {
        setAttributeInternal(ESMULTISECTORIAL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute idSectorIbcie.
     * @return the idSectorIbcie
     */
    public Integer getidSectorIbcie() {
        return (Integer) getAttributeInternal(IDSECTORIBCIE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute idSectorIbcie.
     * @param value value to set the  idSectorIbcie
     */
    public void setidSectorIbcie(Integer value) {
        setAttributeInternal(IDSECTORIBCIE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute sectorIbcie.
     * @return the sectorIbcie
     */
    public String getsectorIbcie() {
        return (String) getAttributeInternal(SECTORIBCIE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute sectorIbcie.
     * @param value value to set the  sectorIbcie
     */
    public void setsectorIbcie(String value) {
        setAttributeInternal(SECTORIBCIE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute idSubSectorIbcie.
     * @return the idSubSectorIbcie
     */
    public Integer getidSubSectorIbcie() {
        return (Integer) getAttributeInternal(IDSUBSECTORIBCIE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute idSubSectorIbcie.
     * @param value value to set the  idSubSectorIbcie
     */
    public void setidSubSectorIbcie(Integer value) {
        setAttributeInternal(IDSUBSECTORIBCIE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute subSectorIbcie.
     * @return the subSectorIbcie
     */
    public String getsubSectorIbcie() {
        return (String) getAttributeInternal(SUBSECTORIBCIE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute subSectorIbcie.
     * @param value value to set the  subSectorIbcie
     */
    public void setsubSectorIbcie(String value) {
        setAttributeInternal(SUBSECTORIBCIE, value);
    }

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        idOperacion,
        esMultisectorial,
        idSectorIbcie,
        sectorIbcie,
        idSubSectorIbcie,
        subSectorIbcie;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public int index() {
            return org.bcie.fenix.common.model.vo.detalleop.CatalogoSectorialIBCIEVORowImpl.AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return org.bcie.fenix.common.model.vo.detalleop.CatalogoSectorialIBCIEVORowImpl.AttributesEnum.firstIndex() +
                   org.bcie.fenix.common.model.vo.detalleop.CatalogoSectorialIBCIEVORowImpl.AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = org.bcie.fenix.common.model.vo.detalleop.CatalogoSectorialIBCIEVORowImpl.AttributesEnum.values();
            }
            return vals;
        }
    }


}

