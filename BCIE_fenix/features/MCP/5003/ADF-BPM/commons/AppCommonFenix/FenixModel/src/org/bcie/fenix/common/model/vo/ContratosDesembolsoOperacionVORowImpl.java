package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Feb 28 16:17:50 CST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ContratosDesembolsoOperacionVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdOperacion,
        IdTcaEstado,
        IdContratoDesembolso,
        MontoDesembolsar;
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
    public static final int IDOPERACION = AttributesEnum.IdOperacion.index();
    public static final int IDTCAESTADO = AttributesEnum.IdTcaEstado.index();
    public static final int IDCONTRATODESEMBOLSO = AttributesEnum.IdContratoDesembolso.index();
    public static final int MONTODESEMBOLSAR = AttributesEnum.MontoDesembolsar.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ContratosDesembolsoOperacionVORowImpl() {
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
     * Gets the attribute value for the calculated attribute IdOperacion.
     * @return the IdOperacion
     */
    public Long getIdOperacion() {
        return (Long) getAttributeInternal(IDOPERACION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdOperacion.
     * @param value value to set the  IdOperacion
     */
    public void setIdOperacion(Long value) {
        setAttributeInternal(IDOPERACION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTcaEstado.
     * @return the IdTcaEstado
     */
    public Integer getIdTcaEstado() {
        return (Integer) getAttributeInternal(IDTCAESTADO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTcaEstado.
     * @param value value to set the  IdTcaEstado
     */
    public void setIdTcaEstado(Integer value) {
        setAttributeInternal(IDTCAESTADO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdContratoDesembolso.
     * @return the IdContratoDesembolso
     */
    public Long getIdContratoDesembolso() {
        return (Long) getAttributeInternal(IDCONTRATODESEMBOLSO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdContratoDesembolso.
     * @param value value to set the  IdContratoDesembolso
     */
    public void setIdContratoDesembolso(Long value) {
        setAttributeInternal(IDCONTRATODESEMBOLSO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MontoDesembolsar.
     * @return the MontoDesembolsar
     */
    public BigDecimal getMontoDesembolsar() {
        return (BigDecimal) getAttributeInternal(MONTODESEMBOLSAR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MontoDesembolsar.
     * @param value value to set the  MontoDesembolsar
     */
    public void setMontoDesembolsar(BigDecimal value) {
        setAttributeInternal(MONTODESEMBOLSAR, value);
    }
}

