package org.bcie.fenix.common.model.vo;

import java.sql.Timestamp;

import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jun 02 13:31:03 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AplicacionExternaVORowImpl extends ViewRowImpl {
    public static final int ENTITY_APLICACIONEXTERNAEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        BanEstatus,
        FechaRegistro,
        Llave,
        Valor,
        BanEstatusAdapter;
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
    public static final int BANESTATUS = AttributesEnum.BanEstatus.index();
    public static final int FECHAREGISTRO = AttributesEnum.FechaRegistro.index();
    public static final int LLAVE = AttributesEnum.Llave.index();
    public static final int VALOR = AttributesEnum.Valor.index();
    public static final int BANESTATUSADAPTER = AttributesEnum.BanEstatusAdapter.index();

    /**
     * This is the default constructor (do not remove).
     */
    public AplicacionExternaVORowImpl() {
    }

    /**
     * Gets AplicacionExternaEO entity object.
     * @return the AplicacionExternaEO
     */
    public EntityImpl getAplicacionExternaEO() {
        return (EntityImpl) getEntity(ENTITY_APLICACIONEXTERNAEO);
    }

    /**
     * Gets the attribute value for BAN_ESTATUS using the alias name BanEstatus.
     * @return the BAN_ESTATUS
     */
    public Integer getBanEstatus() {
        return (Integer) getAttributeInternal(BANESTATUS);
    }

    /**
     * Sets <code>value</code> as attribute value for BAN_ESTATUS using the alias name BanEstatus.
     * @param value value to set the BAN_ESTATUS
     */
    public void setBanEstatus(Integer value) {
        setAttributeInternal(BANESTATUS, value);
    }

    /**
     * Gets the attribute value for FECHA_REGISTRO using the alias name FechaRegistro.
     * @return the FECHA_REGISTRO
     */
    public Timestamp getFechaRegistro() {
        return (Timestamp) getAttributeInternal(FECHAREGISTRO);
    }

    /**
     * Sets <code>value</code> as attribute value for FECHA_REGISTRO using the alias name FechaRegistro.
     * @param value value to set the FECHA_REGISTRO
     */
    public void setFechaRegistro(Timestamp value) {
        setAttributeInternal(FECHAREGISTRO, value);
    }

    /**
     * Gets the attribute value for LLAVE using the alias name Llave.
     * @return the LLAVE
     */
    public String getLlave() {
        return (String) getAttributeInternal(LLAVE);
    }

    /**
     * Sets <code>value</code> as attribute value for LLAVE using the alias name Llave.
     * @param value value to set the LLAVE
     */
    public void setLlave(String value) {
        setAttributeInternal(LLAVE, value);
    }

    /**
     * Gets the attribute value for VALOR using the alias name Valor.
     * @return the VALOR
     */
    public String getValor() {
        return (String) getAttributeInternal(VALOR);
    }

    /**
     * Sets <code>value</code> as attribute value for VALOR using the alias name Valor.
     * @param value value to set the VALOR
     */
    public void setValor(String value) {
        setAttributeInternal(VALOR, value);
    }
    
     /**
      * Gets the attribute value for the calculated attribute BanEstatusAdapter.
      * @return the BanEstatusAdapter
      */
     public Boolean getBanEstatusAdapter() {
         Boolean banEstatusBool = null;
         if (null != getBanEstatus()) {
             if (getBanEstatus().compareTo(1) == 0) {
                 banEstatusBool = Boolean.TRUE;
             } else {
                 banEstatusBool = Boolean.FALSE;
             }
         }
         return banEstatusBool;
     }

     /**
      * Sets <code>value</code> as the attribute value for the calculated attribute BanEstatusAdapter.
      * @param value value to set the  BanEstatusAdapter
      */
     public void setBanEstatusAdapter(Boolean value) {
         Integer banEstatusInt = null;
         
         if (null != value) {
             if (value.compareTo(Boolean.TRUE) == 0) {
                 banEstatusInt = 1;
             } else {
                 banEstatusInt = 0;
             }
         }
         
         setAttributeInternal(BANESTATUS, banEstatusInt);
     }
}

