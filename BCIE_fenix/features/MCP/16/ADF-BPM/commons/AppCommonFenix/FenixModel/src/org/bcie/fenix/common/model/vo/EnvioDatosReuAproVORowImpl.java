package org.bcie.fenix.common.model.vo;

import java.sql.Timestamp;

import oracle.jbo.RowSet;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Nov 25 19:35:04 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EnvioDatosReuAproVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        IdTipoReunion,
        TipoReunion,
        FechaInicio,
        FechaCierre,
        HoraReunion,
        LugarReunion,
        IdOperacion,
        TipoReunionLOV1;
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


    public static final int IDTIPOREUNION = AttributesEnum.IdTipoReunion.index();
    public static final int TIPOREUNION = AttributesEnum.TipoReunion.index();
    public static final int FECHAINICIO = AttributesEnum.FechaInicio.index();
    public static final int FECHACIERRE = AttributesEnum.FechaCierre.index();
    public static final int HORAREUNION = AttributesEnum.HoraReunion.index();
    public static final int LUGARREUNION = AttributesEnum.LugarReunion.index();
    public static final int IDOPERACION = AttributesEnum.IdOperacion.index();
    public static final int TIPOREUNIONLOV1 = AttributesEnum.TipoReunionLOV1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public EnvioDatosReuAproVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute IdTipoReunion.
     * @return the IdTipoReunion
     */
    public Number getIdTipoReunion() {
        return (Number) getAttributeInternal(IDTIPOREUNION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTipoReunion.
     * @param value value to set the  IdTipoReunion
     */
    public void setIdTipoReunion(Number value) {
        setAttributeInternal(IDTIPOREUNION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TipoReunion.
     * @return the TipoReunion
     */
    public String getTipoReunion() {
        return (String) getAttributeInternal(TIPOREUNION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TipoReunion.
     * @param value value to set the  TipoReunion
     */
    public void setTipoReunion(String value) {
        setAttributeInternal(TIPOREUNION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FechaInicio.
     * @return the FechaInicio
     */
    public Date getFechaInicio() {
        return (Date) getAttributeInternal(FECHAINICIO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FechaInicio.
     * @param value value to set the  FechaInicio
     */
    public void setFechaInicio(Date value) {
        setAttributeInternal(FECHAINICIO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FechaCierre.
     * @return the FechaCierre
     */
    public Date getFechaCierre() {
        return (Date) getAttributeInternal(FECHACIERRE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FechaCierre.
     * @param value value to set the  FechaCierre
     */
    public void setFechaCierre(Date value) {
        setAttributeInternal(FECHACIERRE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute HoraReunion.
     * @return the HoraReunion
     */
    public Timestamp getHoraReunion() {
        return (Timestamp) getAttributeInternal(HORAREUNION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute HoraReunion.
     * @param value value to set the  HoraReunion
     */
    public void setHoraReunion(Timestamp value) {
        setAttributeInternal(HORAREUNION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute LugarReunion.
     * @return the LugarReunion
     */
    public String getLugarReunion() {
        return (String) getAttributeInternal(LUGARREUNION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LugarReunion.
     * @param value value to set the  LugarReunion
     */
    public void setLugarReunion(String value) {
        setAttributeInternal(LUGARREUNION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdOperacion.
     * @return the IdOperacion
     */
    public Number getIdOperacion() {
        return (Number) getAttributeInternal(IDOPERACION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdOperacion.
     * @param value value to set the  IdOperacion
     */
    public void setIdOperacion(Number value) {
        setAttributeInternal(IDOPERACION, value);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TipoReunionLOV1.
     */
    public RowSet getTipoReunionLOV1() {
        return (RowSet) getAttributeInternal(TIPOREUNIONLOV1);
    }
}

