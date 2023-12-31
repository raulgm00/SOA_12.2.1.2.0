package org.bcie.fenix.common.model.vo;

import oracle.jbo.RowSet;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Feb 29 16:21:30 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TccSeleccionarVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdTermino,
        NombreTermino,
        IdCondicion,
        NombreCondicion,
        IdComision,
        NombreComision,
        IdOperacion,
        TccSeleccionarTerminoLOV,
        TccSeleccionarCondicionLOV,
        TccSeleccionarComisionLOV;
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
    public static final int IDTERMINO = AttributesEnum.IdTermino.index();
    public static final int NOMBRETERMINO = AttributesEnum.NombreTermino.index();
    public static final int IDCONDICION = AttributesEnum.IdCondicion.index();
    public static final int NOMBRECONDICION = AttributesEnum.NombreCondicion.index();
    public static final int IDCOMISION = AttributesEnum.IdComision.index();
    public static final int NOMBRECOMISION = AttributesEnum.NombreComision.index();
    public static final int IDOPERACION = AttributesEnum.IdOperacion.index();
    public static final int TCCSELECCIONARTERMINOLOV = AttributesEnum.TccSeleccionarTerminoLOV.index();
    public static final int TCCSELECCIONARCONDICIONLOV = AttributesEnum.TccSeleccionarCondicionLOV.index();
    public static final int TCCSELECCIONARCOMISIONLOV = AttributesEnum.TccSeleccionarComisionLOV.index();

    /**
     * This is the default constructor (do not remove).
     */
    public TccSeleccionarVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute Id.
     * @return the Id
     */
    public Integer getId() {
        return (Integer) getAttributeInternal(ID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Id.
     * @param value value to set the  Id
     */
    public void setId(Integer value) {
        setAttributeInternal(ID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTermino.
     * @return the IdTermino
     */
    public Long getIdTermino() {
        return (Long) getAttributeInternal(IDTERMINO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTermino.
     * @param value value to set the  IdTermino
     */
    public void setIdTermino(Long value) {
        setAttributeInternal(IDTERMINO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NombreTermino.
     * @return the NombreTermino
     */
    public String getNombreTermino() {
        return (String) getAttributeInternal(NOMBRETERMINO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NombreTermino.
     * @param value value to set the  NombreTermino
     */
    public void setNombreTermino(String value) {
        setAttributeInternal(NOMBRETERMINO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdCondicion.
     * @return the IdCondicion
     */
    public Long getIdCondicion() {
        return (Long) getAttributeInternal(IDCONDICION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdCondicion.
     * @param value value to set the  IdCondicion
     */
    public void setIdCondicion(Long value) {
        setAttributeInternal(IDCONDICION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NombreCondicion.
     * @return the NombreCondicion
     */
    public String getNombreCondicion() {
        return (String) getAttributeInternal(NOMBRECONDICION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NombreCondicion.
     * @param value value to set the  NombreCondicion
     */
    public void setNombreCondicion(String value) {
        setAttributeInternal(NOMBRECONDICION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdComision.
     * @return the IdComision
     */
    public Long getIdComision() {
        return (Long) getAttributeInternal(IDCOMISION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdComision.
     * @param value value to set the  IdComision
     */
    public void setIdComision(Long value) {
        setAttributeInternal(IDCOMISION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute NombreComision.
     * @return the NombreComision
     */
    public String getNombreComision() {
        return (String) getAttributeInternal(NOMBRECOMISION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NombreComision.
     * @param value value to set the  NombreComision
     */
    public void setNombreComision(String value) {
        setAttributeInternal(NOMBRECOMISION, value);
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
     * Gets the view accessor <code>RowSet</code> TccSeleccionarTerminoLOV.
     */
    public RowSet getTccSeleccionarTerminoLOV() {
        return (RowSet) getAttributeInternal(TCCSELECCIONARTERMINOLOV);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TccSeleccionarCondicionLOV.
     */
    public RowSet getTccSeleccionarCondicionLOV() {
        return (RowSet) getAttributeInternal(TCCSELECCIONARCONDICIONLOV);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TccSeleccionarComisionLOV.
     */
    public RowSet getTccSeleccionarComisionLOV() {
        return (RowSet) getAttributeInternal(TCCSELECCIONARCOMISIONLOV);
    }
}

