package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jan 14 13:33:44 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AgregarTramoFormalizarVORowImpl extends ViewRowImpl {
    public static final int ENTITY_TRAMOFORMALIZAREO = 0;


    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdContrato,
        NombreTramo,
        Monto,
        Porcentaje,
        FechaRegistro,
        BanEstatus;
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
    public static final int IDCONTRATO = AttributesEnum.IdContrato.index();
    public static final int NOMBRETRAMO = AttributesEnum.NombreTramo.index();
    public static final int MONTO = AttributesEnum.Monto.index();
    public static final int PORCENTAJE = AttributesEnum.Porcentaje.index();
    public static final int FECHAREGISTRO = AttributesEnum.FechaRegistro.index();
    public static final int BANESTATUS = AttributesEnum.BanEstatus.index();

    /**
     * This is the default constructor (do not remove).
     */
    public AgregarTramoFormalizarVORowImpl() {
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
     * Gets the attribute value for the calculated attribute IdContrato.
     * @return the IdContrato
     */
    public Long getIdContrato() {
        return (Long) getAttributeInternal(IDCONTRATO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdContrato.
     * @param value value to set the  IdContrato
     */
    public void setIdContrato(Long value) {
        setAttributeInternal(IDCONTRATO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NombreTramo.
     * @return the NombreTramo
     */
    public String getNombreTramo() {
        return (String) getAttributeInternal(NOMBRETRAMO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NombreTramo.
     * @param value value to set the  NombreTramo
     */
    public void setNombreTramo(String value) {
        setAttributeInternal(NOMBRETRAMO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Monto.
     * @return the Monto
     */
    public BigDecimal getMonto() {
        return (BigDecimal) getAttributeInternal(MONTO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Monto.
     * @param value value to set the  Monto
     */
    public void setMonto(BigDecimal value) {
        setAttributeInternal(MONTO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Porcentaje.
     * @return the Porcentaje
     */
    public BigDecimal getPorcentaje() {
        return (BigDecimal) getAttributeInternal(PORCENTAJE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Porcentaje.
     * @param value value to set the  Porcentaje
     */
    public void setPorcentaje(BigDecimal value) {
        setAttributeInternal(PORCENTAJE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FechaRegistro.
     * @return the FechaRegistro
     */
    public Timestamp getFechaRegistro() {
        return (Timestamp) getAttributeInternal(FECHAREGISTRO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FechaRegistro.
     * @param value value to set the  FechaRegistro
     */
    public void setFechaRegistro(Timestamp value) {
        setAttributeInternal(FECHAREGISTRO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute BanEstatus.
     * @return the BanEstatus
     */
    public Integer getBanEstatus() {
        return (Integer) getAttributeInternal(BANESTATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute BanEstatus.
     * @param value value to set the  BanEstatus
     */
    public void setBanEstatus(Integer value) {
        setAttributeInternal(BANESTATUS, value);
    }

    /**
     * Gets TramoFormalizarEO entity object.
     * @return the TramoFormalizarEO
     */
    public EntityImpl getTramoFormalizarEO() {
        return (EntityImpl) getEntity(ENTITY_TRAMOFORMALIZAREO);
    }


}

