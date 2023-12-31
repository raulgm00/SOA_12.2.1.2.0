package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Dec 15 12:06:38 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConsultaComisionNoPagadaVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdOperacion,
        Nombre,
        MontoComision,
        CodigoContrato,
        IdTcaEstadoTcc,
        IdTcaSubEstadoTcc;
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
    public static final int IDOPERACION = AttributesEnum.IdOperacion.index();
    public static final int NOMBRE = AttributesEnum.Nombre.index();
    public static final int MONTOCOMISION = AttributesEnum.MontoComision.index();
    public static final int CODIGOCONTRATO = AttributesEnum.CodigoContrato.index();
    public static final int IDTCAESTADOTCC = AttributesEnum.IdTcaEstadoTcc.index();
    public static final int IDTCASUBESTADOTCC = AttributesEnum.IdTcaSubEstadoTcc.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ConsultaComisionNoPagadaVORowImpl() {
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
     * Gets the attribute value for the calculated attribute Nombre.
     * @return the Nombre
     */
    public String getNombre() {
        return (String) getAttributeInternal(NOMBRE);
    }

    /**
     * Gets the attribute value for the calculated attribute MontoComision.
     * @return the MontoComision
     */
    public BigDecimal getMontoComision() {
        return (BigDecimal) getAttributeInternal(MONTOCOMISION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MontoComision.
     * @param value value to set the  MontoComision
     */
    public void setMontoComision(BigDecimal value) {
        setAttributeInternal(MONTOCOMISION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CodigoContrato.
     * @return the CodigoContrato
     */
    public String getCodigoContrato() {
        return (String) getAttributeInternal(CODIGOCONTRATO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CodigoContrato.
     * @param value value to set the  CodigoContrato
     */
    public void setCodigoContrato(String value) {
        setAttributeInternal(CODIGOCONTRATO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTcaEstadoTcc.
     * @return the IdTcaEstadoTcc
     */
    public Integer getIdTcaEstadoTcc() {
        return (Integer) getAttributeInternal(IDTCAESTADOTCC);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTcaSubEstadoTcc.
     * @return the IdTcaSubEstadoTcc
     */
    public Integer getIdTcaSubEstadoTcc() {
        return (Integer) getAttributeInternal(IDTCASUBESTADOTCC);
    }
}

