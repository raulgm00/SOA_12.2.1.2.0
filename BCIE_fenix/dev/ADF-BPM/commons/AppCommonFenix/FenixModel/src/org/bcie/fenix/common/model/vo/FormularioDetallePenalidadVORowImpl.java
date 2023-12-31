package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.sql.Timestamp;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Oct 07 18:08:02 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FormularioDetallePenalidadVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdTrePrepagoContrato,
        FechaInicio,
        FechaFin,
        Plazo,
        Spread,
        FraccionLibor,
        MontoPenalidad,
        TasaPrepago,
        Intereses,
        MontoPrepago,
        ContratoDesembolso,
        Moneda,
        Resolucion,
        ProximoPago,
        TotalPrepago,
        TotalPenalidad,
        TotalInteres;
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
    public static final int IDTREPREPAGOCONTRATO = AttributesEnum.IdTrePrepagoContrato.index();
    public static final int FECHAINICIO = AttributesEnum.FechaInicio.index();
    public static final int FECHAFIN = AttributesEnum.FechaFin.index();
    public static final int PLAZO = AttributesEnum.Plazo.index();
    public static final int SPREAD = AttributesEnum.Spread.index();
    public static final int FRACCIONLIBOR = AttributesEnum.FraccionLibor.index();
    public static final int MONTOPENALIDAD = AttributesEnum.MontoPenalidad.index();
    public static final int TASAPREPAGO = AttributesEnum.TasaPrepago.index();
    public static final int INTERESES = AttributesEnum.Intereses.index();
    public static final int MONTOPREPAGO = AttributesEnum.MontoPrepago.index();
    public static final int CONTRATODESEMBOLSO = AttributesEnum.ContratoDesembolso.index();
    public static final int MONEDA = AttributesEnum.Moneda.index();
    public static final int RESOLUCION = AttributesEnum.Resolucion.index();
    public static final int PROXIMOPAGO = AttributesEnum.ProximoPago.index();
    public static final int TOTALPREPAGO = AttributesEnum.TotalPrepago.index();
    public static final int TOTALPENALIDAD = AttributesEnum.TotalPenalidad.index();
    public static final int TOTALINTERES = AttributesEnum.TotalInteres.index();

    /**
     * This is the default constructor (do not remove).
     */
    public FormularioDetallePenalidadVORowImpl() {
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
     * Gets the attribute value for the calculated attribute IdTrePrepagoContrato.
     * @return the IdTrePrepagoContrato
     */
    public Long getIdTrePrepagoContrato() {
        return (Long) getAttributeInternal(IDTREPREPAGOCONTRATO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTrePrepagoContrato.
     * @param value value to set the  IdTrePrepagoContrato
     */
    public void setIdTrePrepagoContrato(Long value) {
        setAttributeInternal(IDTREPREPAGOCONTRATO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FechaInicio.
     * @return the FechaInicio
     */
    public Timestamp getFechaInicio() {
        return (Timestamp) getAttributeInternal(FECHAINICIO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FechaInicio.
     * @param value value to set the  FechaInicio
     */
    public void setFechaInicio(Timestamp value) {
        setAttributeInternal(FECHAINICIO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FechaFin.
     * @return the FechaFin
     */
    public Timestamp getFechaFin() {
        return (Timestamp) getAttributeInternal(FECHAFIN);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FechaFin.
     * @param value value to set the  FechaFin
     */
    public void setFechaFin(Timestamp value) {
        setAttributeInternal(FECHAFIN, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Plazo.
     * @return the Plazo
     */
    public BigDecimal getPlazo() {
        return (BigDecimal) getAttributeInternal(PLAZO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Plazo.
     * @param value value to set the  Plazo
     */
    public void setPlazo(BigDecimal value) {
        setAttributeInternal(PLAZO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Spread.
     * @return the Spread
     */
    public BigDecimal getSpread() {
        return (BigDecimal) getAttributeInternal(SPREAD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Spread.
     * @param value value to set the  Spread
     */
    public void setSpread(BigDecimal value) {
        setAttributeInternal(SPREAD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FraccionLibor.
     * @return the FraccionLibor
     */
    public String getFraccionLibor() {
        return (String) getAttributeInternal(FRACCIONLIBOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FraccionLibor.
     * @param value value to set the  FraccionLibor
     */
    public void setFraccionLibor(String value) {
        setAttributeInternal(FRACCIONLIBOR, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MontoPenalidad.
     * @return the MontoPenalidad
     */
    public BigDecimal getMontoPenalidad() {
        return (BigDecimal) getAttributeInternal(MONTOPENALIDAD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MontoPenalidad.
     * @param value value to set the  MontoPenalidad
     */
    public void setMontoPenalidad(BigDecimal value) {
        setAttributeInternal(MONTOPENALIDAD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TasaPrepago.
     * @return the TasaPrepago
     */
    public BigDecimal getTasaPrepago() {
        return (BigDecimal) getAttributeInternal(TASAPREPAGO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TasaPrepago.
     * @param value value to set the  TasaPrepago
     */
    public void setTasaPrepago(BigDecimal value) {
        setAttributeInternal(TASAPREPAGO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Intereses.
     * @return the Intereses
     */
    public BigDecimal getIntereses() {
        return (BigDecimal) getAttributeInternal(INTERESES);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Intereses.
     * @param value value to set the  Intereses
     */
    public void setIntereses(BigDecimal value) {
        setAttributeInternal(INTERESES, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MontoPrepago.
     * @return the MontoPrepago
     */
    public BigDecimal getMontoPrepago() {
        return (BigDecimal) getAttributeInternal(MONTOPREPAGO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MontoPrepago.
     * @param value value to set the  MontoPrepago
     */
    public void setMontoPrepago(BigDecimal value) {
        setAttributeInternal(MONTOPREPAGO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ContratoDesembolso.
     * @return the ContratoDesembolso
     */
    public String getContratoDesembolso() {
        return (String) getAttributeInternal(CONTRATODESEMBOLSO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ContratoDesembolso.
     * @param value value to set the  ContratoDesembolso
     */
    public void setContratoDesembolso(String value) {
        setAttributeInternal(CONTRATODESEMBOLSO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Moneda.
     * @return the Moneda
     */
    public String getMoneda() {
        return (String) getAttributeInternal(MONEDA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Moneda.
     * @param value value to set the  Moneda
     */
    public void setMoneda(String value) {
        setAttributeInternal(MONEDA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Resolucion.
     * @return the Resolucion
     */
    public String getResolucion() {
        return (String) getAttributeInternal(RESOLUCION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Resolucion.
     * @param value value to set the  Resolucion
     */
    public void setResolucion(String value) {
        setAttributeInternal(RESOLUCION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ProximoPago.
     * @return the ProximoPago
     */
    public Timestamp getProximoPago() {
        return (Timestamp) getAttributeInternal(PROXIMOPAGO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ProximoPago.
     * @param value value to set the  ProximoPago
     */
    public void setProximoPago(Timestamp value) {
        setAttributeInternal(PROXIMOPAGO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TotalPrepago.
     * @return the TotalPrepago
     */
    public BigDecimal getTotalPrepago() {
        BigDecimal totalPrepago = new BigDecimal(0);
        BigDecimal montoPrepago = new BigDecimal(0);
        
        RowSetIterator iterator = this.getViewObject().createRowSetIterator(null);
               iterator.reset();
               
               while (iterator.hasNext()) {
                   Row row = iterator.next();
                   montoPrepago = (BigDecimal)row.getAttribute("MontoPrepago");
               if(montoPrepago != null)
                   totalPrepago = totalPrepago.add(montoPrepago);
               }
        
        iterator.closeRowSetIterator(); 
        return totalPrepago;
    }

    /**
     * Gets the attribute value for the calculated attribute TotalPenalidad.
     * @return the TotalPenalidad
     */
    public BigDecimal getTotalPenalidad() {
        BigDecimal totalPenalidad = new BigDecimal(0);
        BigDecimal montoPenalidad = new BigDecimal(0);
        
        RowSetIterator iterator = this.getViewObject().createRowSetIterator(null);
               iterator.reset();
               
               while (iterator.hasNext()) {
                   Row row = iterator.next();
                   montoPenalidad = (BigDecimal)row.getAttribute("MontoPenalidad");
               if(montoPenalidad != null)
                   totalPenalidad = totalPenalidad.add(montoPenalidad.setScale(2, RoundingMode.FLOOR));
               }
        
        iterator.closeRowSetIterator(); 
        return totalPenalidad;
    }

    /**
     * Gets the attribute value for the calculated attribute TotalInteres.
     * @return the TotalInteres
     */
    public BigDecimal getTotalInteres() {
        BigDecimal totalInteres = new BigDecimal(0);
        BigDecimal montoInteres = new BigDecimal(0);
        
        RowSetIterator iterator = this.getViewObject().createRowSetIterator(null);
               iterator.reset();
               
               while (iterator.hasNext()) {
                   Row row = iterator.next();
                   montoInteres = (BigDecimal)row.getAttribute("Intereses");
               if(montoInteres != null)
                   totalInteres = totalInteres.add(montoInteres.setScale(2, RoundingMode.CEILING));
               }
        
        iterator.closeRowSetIterator(); 
        return totalInteres;
    }
}

