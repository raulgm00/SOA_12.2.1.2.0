package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Oct 31 14:11:31 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConsultarAsociadasVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        Idoperacion,
        Idoperacionasociada,
        Nombrepaisasociada,
        Razonsocialasociada,
        Nombreoperacionasociada,
        Seleccionaroperacion;
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
    public static final int IDOPERACION = AttributesEnum.Idoperacion.index();
    public static final int IDOPERACIONASOCIADA = AttributesEnum.Idoperacionasociada.index();
    public static final int NOMBREPAISASOCIADA = AttributesEnum.Nombrepaisasociada.index();
    public static final int RAZONSOCIALASOCIADA = AttributesEnum.Razonsocialasociada.index();
    public static final int NOMBREOPERACIONASOCIADA = AttributesEnum.Nombreoperacionasociada.index();
    public static final int SELECCIONAROPERACION = AttributesEnum.Seleccionaroperacion.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ConsultarAsociadasVORowImpl() {
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
     * Gets the attribute value for the calculated attribute Idoperacion.
     * @return the Idoperacion
     */
    public Long getIdoperacion() {
        return (Long) getAttributeInternal(IDOPERACION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Idoperacion.
     * @param value value to set the  Idoperacion
     */
    public void setIdoperacion(Long value) {
        setAttributeInternal(IDOPERACION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Idoperacionasociada.
     * @return the Idoperacionasociada
     */
    public Long getIdoperacionasociada() {
        return (Long) getAttributeInternal(IDOPERACIONASOCIADA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Idoperacionasociada.
     * @param value value to set the  Idoperacionasociada
     */
    public void setIdoperacionasociada(Long value) {
        setAttributeInternal(IDOPERACIONASOCIADA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Nombrepaisasociada.
     * @return the Nombrepaisasociada
     */
    public String getNombrepaisasociada() {
        return (String) getAttributeInternal(NOMBREPAISASOCIADA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Nombrepaisasociada.
     * @param value value to set the  Nombrepaisasociada
     */
    public void setNombrepaisasociada(String value) {
        setAttributeInternal(NOMBREPAISASOCIADA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Razonsocialasociada.
     * @return the Razonsocialasociada
     */
    public String getRazonsocialasociada() {
        return (String) getAttributeInternal(RAZONSOCIALASOCIADA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Razonsocialasociada.
     * @param value value to set the  Razonsocialasociada
     */
    public void setRazonsocialasociada(String value) {
        setAttributeInternal(RAZONSOCIALASOCIADA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Nombreoperacionasociada.
     * @return the Nombreoperacionasociada
     */
    public String getNombreoperacionasociada() {
        return (String) getAttributeInternal(NOMBREOPERACIONASOCIADA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Nombreoperacionasociada.
     * @param value value to set the  Nombreoperacionasociada
     */
    public void setNombreoperacionasociada(String value) {
        setAttributeInternal(NOMBREOPERACIONASOCIADA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Seleccionaroperacion.
     * @return the Seleccionaroperacion
     */
    public Boolean getSeleccionaroperacion() {
        return (Boolean) getAttributeInternal(SELECCIONAROPERACION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Seleccionaroperacion.
     * @param value value to set the  Seleccionaroperacion
     */
    public void setSeleccionaroperacion(Boolean value) {
        setAttributeInternal(SELECCIONAROPERACION, value);
    }
}

