package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.RowIterator;
import oracle.jbo.RowSet;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Oct 31 18:37:57 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConsultarReglasNoCumplidasDesembolsoExcepcionVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        ConsultarLineasReglasNoCumplidasDesembolsoExcepcionVO;
        static AttributesEnum[] vals = null;
        ;
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
    public static final int CONSULTARLINEASREGLASNOCUMPLIDASDESEMBOLSOEXCEPCIONVO =
        AttributesEnum.ConsultarLineasReglasNoCumplidasDesembolsoExcepcionVO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ConsultarReglasNoCumplidasDesembolsoExcepcionVORowImpl() {
    }


    /**
     * Gets the attribute value for the calculated attribute Id.
     * @return the Id
     */
    public Long getId() {
        return (Long) getAttributeInternal(ID);
    }


    /**
     * Gets the associated <code>RowIterator</code> using master-detail link ConsultarLineasReglasNoCumplidasDesembolsoExcepcionVO.
     */
    public RowIterator getConsultarLineasReglasNoCumplidasDesembolsoExcepcionVO() {
        return (RowIterator) getAttributeInternal(CONSULTARLINEASREGLASNOCUMPLIDASDESEMBOLSOEXCEPCIONVO);
    }


}

