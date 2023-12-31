package org.bcie.fenix.common.model.vo;

import oracle.jbo.domain.Date;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed May 10 13:09:05 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FT05VORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        idContratoDesembolso,
        BHQTransferencia,
        FechaEfectiva,
        CuentaPuente,
        CuentaCustomer;
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


    public static final int IDCONTRATODESEMBOLSO = AttributesEnum.idContratoDesembolso.index();
    public static final int BHQTRANSFERENCIA = AttributesEnum.BHQTransferencia.index();
    public static final int FECHAEFECTIVA = AttributesEnum.FechaEfectiva.index();
    public static final int CUENTAPUENTE = AttributesEnum.CuentaPuente.index();
    public static final int CUENTACUSTOMER = AttributesEnum.CuentaCustomer.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FT05VORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute idContratoDesembolso.
     * @return the idContratoDesembolso
     */
    public Long getidContratoDesembolso() {
        return (Long) getAttributeInternal(IDCONTRATODESEMBOLSO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute idContratoDesembolso.
     * @param value value to set the  idContratoDesembolso
     */
    public void setidContratoDesembolso(Long value) {
        setAttributeInternal(IDCONTRATODESEMBOLSO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BHQTransferencia.
     * @return the BHQTransferencia
     */
    public String getBHQTransferencia() {
        return (String) getAttributeInternal(BHQTRANSFERENCIA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BHQTransferencia.
     * @param value value to set the  BHQTransferencia
     */
    public void setBHQTransferencia(String value) {
        setAttributeInternal(BHQTRANSFERENCIA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FechaEfectiva.
     * @return the FechaEfectiva
     */
    public Date getFechaEfectiva() {
        return (Date) getAttributeInternal(FECHAEFECTIVA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FechaEfectiva.
     * @param value value to set the  FechaEfectiva
     */
    public void setFechaEfectiva(Date value) {
        setAttributeInternal(FECHAEFECTIVA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CuentaPuente.
     * @return the CuentaPuente
     */
    public String getCuentaPuente() {
        return (String) getAttributeInternal(CUENTAPUENTE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CuentaPuente.
     * @param value value to set the  CuentaPuente
     */
    public void setCuentaPuente(String value) {
        setAttributeInternal(CUENTAPUENTE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CuentaCustomer.
     * @return the CuentaCustomer
     */
    public String getCuentaCustomer() {
        return (String) getAttributeInternal(CUENTACUSTOMER);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CuentaCustomer.
     * @param value value to set the  CuentaCustomer
     */
    public void setCuentaCustomer(String value) {
        setAttributeInternal(CUENTACUSTOMER, value);
    }
}

