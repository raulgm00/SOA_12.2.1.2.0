package org.bcie.fenix.common.model.eo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.AttributeDef;
import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.Number;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Dec 13 17:26:45 COT 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ComponenteEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        IdCondicionFinanciera,
        IdTcaTipoComponente,
        IdTcaTipoTasaDesembolso,
        IdTcaBaseCalculo,
        EsDependiente,
        EsFactor,
        CodigoTasaReferencia,
        FechaEfectivaTasaReferencia,
        ValorTasaReferencia,
        Tasa,
        SpreadTasa,
        MontoDescuento,
        DiasSpotTasa,
        IdTcaTipoRedondeo,
        CantidadDecimales,
        LimiteTasaMinima,
        LimiteTasaMaxima,
        CondicionesFinancierasEO,
        tre_Condicion_Financiera_Cal_EO,
        Tre_Componente_Cal_EO;
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
    public static final int IDCONDICIONFINANCIERA = AttributesEnum.IdCondicionFinanciera.index();
    public static final int IDTCATIPOCOMPONENTE = AttributesEnum.IdTcaTipoComponente.index();
    public static final int IDTCATIPOTASADESEMBOLSO = AttributesEnum.IdTcaTipoTasaDesembolso.index();
    public static final int IDTCABASECALCULO = AttributesEnum.IdTcaBaseCalculo.index();
    public static final int ESDEPENDIENTE = AttributesEnum.EsDependiente.index();
    public static final int ESFACTOR = AttributesEnum.EsFactor.index();
    public static final int CODIGOTASAREFERENCIA = AttributesEnum.CodigoTasaReferencia.index();
    public static final int FECHAEFECTIVATASAREFERENCIA = AttributesEnum.FechaEfectivaTasaReferencia.index();
    public static final int VALORTASAREFERENCIA = AttributesEnum.ValorTasaReferencia.index();
    public static final int TASA = AttributesEnum.Tasa.index();
    public static final int SPREADTASA = AttributesEnum.SpreadTasa.index();
    public static final int MONTODESCUENTO = AttributesEnum.MontoDescuento.index();
    public static final int DIASSPOTTASA = AttributesEnum.DiasSpotTasa.index();
    public static final int IDTCATIPOREDONDEO = AttributesEnum.IdTcaTipoRedondeo.index();
    public static final int CANTIDADDECIMALES = AttributesEnum.CantidadDecimales.index();
    public static final int LIMITETASAMINIMA = AttributesEnum.LimiteTasaMinima.index();
    public static final int LIMITETASAMAXIMA = AttributesEnum.LimiteTasaMaxima.index();
    public static final int CONDICIONESFINANCIERASEO = AttributesEnum.CondicionesFinancierasEO.index();
    public static final int TRE_CONDICION_FINANCIERA_CAL_EO = AttributesEnum.tre_Condicion_Financiera_Cal_EO.index();
    public static final int TRE_COMPONENTE_CAL_EO = AttributesEnum.Tre_Componente_Cal_EO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ComponenteEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("org.bcie.fenix.common.model.eo.ComponenteEO");
    }


    /**
     * Gets the attribute value for Id, using the alias name Id.
     * @return the value of Id
     */
    public Number getId() {
        return (Number) getAttributeInternal(ID);
    }

    /**
     * Sets <code>value</code> as the attribute value for Id.
     * @param value value to set the Id
     */
    public void setId(Number value) {
        setAttributeInternal(ID, value);
    }

    /**
     * Gets the attribute value for IdCondicionFinanciera, using the alias name IdCondicionFinanciera.
     * @return the value of IdCondicionFinanciera
     */
    public Long getIdCondicionFinanciera() {
        return (Long) getAttributeInternal(IDCONDICIONFINANCIERA);
    }

    /**
     * Sets <code>value</code> as the attribute value for IdCondicionFinanciera.
     * @param value value to set the IdCondicionFinanciera
     */
    public void setIdCondicionFinanciera(Long value) {
        setAttributeInternal(IDCONDICIONFINANCIERA, value);
    }

    /**
     * Gets the attribute value for IdTcaTipoComponente, using the alias name IdTcaTipoComponente.
     * @return the value of IdTcaTipoComponente
     */
    public Long getIdTcaTipoComponente() {
        return (Long) getAttributeInternal(IDTCATIPOCOMPONENTE);
    }

    /**
     * Sets <code>value</code> as the attribute value for IdTcaTipoComponente.
     * @param value value to set the IdTcaTipoComponente
     */
    public void setIdTcaTipoComponente(Long value) {
        setAttributeInternal(IDTCATIPOCOMPONENTE, value);
    }

    /**
     * Gets the attribute value for IdTcaTipoTasaDesembolso, using the alias name IdTcaTipoTasaDesembolso.
     * @return the value of IdTcaTipoTasaDesembolso
     */
    public Integer getIdTcaTipoTasaDesembolso() {
        return (Integer) getAttributeInternal(IDTCATIPOTASADESEMBOLSO);
    }

    /**
     * Sets <code>value</code> as the attribute value for IdTcaTipoTasaDesembolso.
     * @param value value to set the IdTcaTipoTasaDesembolso
     */
    public void setIdTcaTipoTasaDesembolso(Integer value) {
        setAttributeInternal(IDTCATIPOTASADESEMBOLSO, value);
    }

    /**
     * Gets the attribute value for IdTcaBaseCalculo, using the alias name IdTcaBaseCalculo.
     * @return the value of IdTcaBaseCalculo
     */
    public Integer getIdTcaBaseCalculo() {
        return (Integer) getAttributeInternal(IDTCABASECALCULO);
    }

    /**
     * Sets <code>value</code> as the attribute value for IdTcaBaseCalculo.
     * @param value value to set the IdTcaBaseCalculo
     */
    public void setIdTcaBaseCalculo(Integer value) {
        setAttributeInternal(IDTCABASECALCULO, value);
    }

    /**
     * Gets the attribute value for EsDependiente, using the alias name EsDependiente.
     * @return the value of EsDependiente
     */
    public Integer getEsDependiente() {
        return (Integer) getAttributeInternal(ESDEPENDIENTE);
    }

    /**
     * Sets <code>value</code> as the attribute value for EsDependiente.
     * @param value value to set the EsDependiente
     */
    public void setEsDependiente(Integer value) {
        setAttributeInternal(ESDEPENDIENTE, value);
    }

    /**
     * Gets the attribute value for EsFactor, using the alias name EsFactor.
     * @return the value of EsFactor
     */
    public Integer getEsFactor() {
        return (Integer) getAttributeInternal(ESFACTOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for EsFactor.
     * @param value value to set the EsFactor
     */
    public void setEsFactor(Integer value) {
        setAttributeInternal(ESFACTOR, value);
    }

    /**
     * Gets the attribute value for CodigoTasaReferencia, using the alias name CodigoTasaReferencia.
     * @return the value of CodigoTasaReferencia
     */
    public String getCodigoTasaReferencia() {
        return (String) getAttributeInternal(CODIGOTASAREFERENCIA);
    }

    /**
     * Sets <code>value</code> as the attribute value for CodigoTasaReferencia.
     * @param value value to set the CodigoTasaReferencia
     */
    public void setCodigoTasaReferencia(String value) {
        setAttributeInternal(CODIGOTASAREFERENCIA, value);
    }

    /**
     * Gets the attribute value for FechaEfectivaTasaReferencia, using the alias name FechaEfectivaTasaReferencia.
     * @return the value of FechaEfectivaTasaReferencia
     */
    public Timestamp getFechaEfectivaTasaReferencia() {
        return (Timestamp) getAttributeInternal(FECHAEFECTIVATASAREFERENCIA);
    }

    /**
     * Sets <code>value</code> as the attribute value for FechaEfectivaTasaReferencia.
     * @param value value to set the FechaEfectivaTasaReferencia
     */
    public void setFechaEfectivaTasaReferencia(Timestamp value) {
        setAttributeInternal(FECHAEFECTIVATASAREFERENCIA, value);
    }

    /**
     * Gets the attribute value for ValorTasaReferencia, using the alias name ValorTasaReferencia.
     * @return the value of ValorTasaReferencia
     */
    public BigDecimal getValorTasaReferencia() {
        return (BigDecimal) getAttributeInternal(VALORTASAREFERENCIA);
    }

    /**
     * Sets <code>value</code> as the attribute value for ValorTasaReferencia.
     * @param value value to set the ValorTasaReferencia
     */
    public void setValorTasaReferencia(BigDecimal value) {
        setAttributeInternal(VALORTASAREFERENCIA, value);
    }

    /**
     * Gets the attribute value for Tasa, using the alias name Tasa.
     * @return the value of Tasa
     */
    public Number getTasa() {
        return (Number) getAttributeInternal(TASA);
    }

    /**
     * Sets <code>value</code> as the attribute value for Tasa.
     * @param value value to set the Tasa
     */
    public void setTasa(Number value) {
        setAttributeInternal(TASA, value);
    }

    /**
     * Gets the attribute value for SpreadTasa, using the alias name SpreadTasa.
     * @return the value of SpreadTasa
     */
    public BigDecimal getSpreadTasa() {
        return (BigDecimal) getAttributeInternal(SPREADTASA);
    }

    /**
     * Sets <code>value</code> as the attribute value for SpreadTasa.
     * @param value value to set the SpreadTasa
     */
    public void setSpreadTasa(BigDecimal value) {
        setAttributeInternal(SPREADTASA, value);
    }

    /**
     * Gets the attribute value for MontoDescuento, using the alias name MontoDescuento.
     * @return the value of MontoDescuento
     */
    public BigDecimal getMontoDescuento() {
        return (BigDecimal) getAttributeInternal(MONTODESCUENTO);
    }

    /**
     * Sets <code>value</code> as the attribute value for MontoDescuento.
     * @param value value to set the MontoDescuento
     */
    public void setMontoDescuento(BigDecimal value) {
        setAttributeInternal(MONTODESCUENTO, value);
    }

    /**
     * Gets the attribute value for DiasSpotTasa, using the alias name DiasSpotTasa.
     * @return the value of DiasSpotTasa
     */
    public Integer getDiasSpotTasa() {
        return (Integer) getAttributeInternal(DIASSPOTTASA);
    }

    /**
     * Sets <code>value</code> as the attribute value for DiasSpotTasa.
     * @param value value to set the DiasSpotTasa
     */
    public void setDiasSpotTasa(Integer value) {
        setAttributeInternal(DIASSPOTTASA, value);
    }

    /**
     * Gets the attribute value for IdTcaTipoRedondeo, using the alias name IdTcaTipoRedondeo.
     * @return the value of IdTcaTipoRedondeo
     */
    public Long getIdTcaTipoRedondeo() {
        return (Long) getAttributeInternal(IDTCATIPOREDONDEO);
    }

    /**
     * Sets <code>value</code> as the attribute value for IdTcaTipoRedondeo.
     * @param value value to set the IdTcaTipoRedondeo
     */
    public void setIdTcaTipoRedondeo(Long value) {
        setAttributeInternal(IDTCATIPOREDONDEO, value);
    }

    /**
     * Gets the attribute value for CantidadDecimales, using the alias name CantidadDecimales.
     * @return the value of CantidadDecimales
     */
    public Integer getCantidadDecimales() {
        return (Integer) getAttributeInternal(CANTIDADDECIMALES);
    }

    /**
     * Sets <code>value</code> as the attribute value for CantidadDecimales.
     * @param value value to set the CantidadDecimales
     */
    public void setCantidadDecimales(Integer value) {
        setAttributeInternal(CANTIDADDECIMALES, value);
    }

    /**
     * Gets the attribute value for LimiteTasaMinima, using the alias name LimiteTasaMinima.
     * @return the value of LimiteTasaMinima
     */
    public BigDecimal getLimiteTasaMinima() {
        return (BigDecimal) getAttributeInternal(LIMITETASAMINIMA);
    }

    /**
     * Sets <code>value</code> as the attribute value for LimiteTasaMinima.
     * @param value value to set the LimiteTasaMinima
     */
    public void setLimiteTasaMinima(BigDecimal value) {
        setAttributeInternal(LIMITETASAMINIMA, value);
    }

    /**
     * Gets the attribute value for LimiteTasaMaxima, using the alias name LimiteTasaMaxima.
     * @return the value of LimiteTasaMaxima
     */
    public BigDecimal getLimiteTasaMaxima() {
        return (BigDecimal) getAttributeInternal(LIMITETASAMAXIMA);
    }

    /**
     * Sets <code>value</code> as the attribute value for LimiteTasaMaxima.
     * @param value value to set the LimiteTasaMaxima
     */
    public void setLimiteTasaMaxima(BigDecimal value) {
        setAttributeInternal(LIMITETASAMAXIMA, value);
    }

    /**
     * @return the associated entity CondicionesFinancierasEOImpl.
     */
    public CondicionesFinancierasEOImpl getCondicionesFinancierasEO() {
        return (CondicionesFinancierasEOImpl) getAttributeInternal(CONDICIONESFINANCIERASEO);
    }

    /**
     * Sets <code>value</code> as the associated entity CondicionesFinancierasEOImpl.
     */
    public void setCondicionesFinancierasEO(CondicionesFinancierasEOImpl value) {
        setAttributeInternal(CONDICIONESFINANCIERASEO, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator gettre_Condicion_Financiera_Cal_EO() {
        return (RowIterator) getAttributeInternal(TRE_CONDICION_FINANCIERA_CAL_EO);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getTre_Componente_Cal_EO() {
        return (RowIterator) getAttributeInternal(TRE_COMPONENTE_CAL_EO);
    }


    /**
     * @param id key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number id) {
        return new Key(new Object[] { id });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        Number secuencia=null;
            for (AttributeDef def : getEntityDef().getAttributeDefs()) {
              String sequenceName = (String) def.getProperty("SequenceName");
              if (sequenceName != null) {
                SequenceImpl s = new SequenceImpl(sequenceName, getDBTransaction());
                //setAttribute(def.getIndex(), s.getSequenceNumber());
                secuencia=s.getSequenceNumber();
                
              }
            }
            setId(secuencia);
    }
}

