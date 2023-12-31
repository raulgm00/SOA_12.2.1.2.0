package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Dec 13 11:37:30 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DatosLineaCreditoVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Id,
        NumeroLineaCredito,
        Fondo,
        MontoLinea,
        MontoTotal,
        IdFlexcube,
        Descripcion,
        FechaMaxima,
        MontoDesembolsar,
        FechaVencimiento,
        MontoDisponible,
        MontoProgramado,
        LineaGlobalIfi,
        DescripcionCorta,
        IdOperacion,
        IdContrato,
		IdTipoMonedaMontoLinea,
        IdTipoMonedaMontoTotal,
        DiasSpotTasa,
        IdTcaTipoRedondeo,
        CantidadDecimales,
        IdTcaTipoMora,
        CodigoTasaReferencia,
        ValorTasa,
        SpreadTasa,
        Frecuencia,
        IdTcaTipoFrecuencia,
        Moneda,        MontoTotalAplicado/*jenamorado monto total se debe aplicar*/,
        MontoTotalPendiente/*jenamorado monto total se debe aplicar*/;
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
    public static final int NUMEROLINEACREDITO = AttributesEnum.NumeroLineaCredito.index();
    public static final int FONDO = AttributesEnum.Fondo.index();
    public static final int MONTOLINEA = AttributesEnum.MontoLinea.index();
    public static final int MONTOTOTAL = AttributesEnum.MontoTotal.index();
    public static final int IDFLEXCUBE = AttributesEnum.IdFlexcube.index();
    public static final int DESCRIPCION = AttributesEnum.Descripcion.index();
    public static final int FECHAMAXIMA = AttributesEnum.FechaMaxima.index();
    public static final int MONTODESEMBOLSAR = AttributesEnum.MontoDesembolsar.index();
    public static final int FECHAVENCIMIENTO = AttributesEnum.FechaVencimiento.index();
    public static final int MONTODISPONIBLE = AttributesEnum.MontoDisponible.index();
    public static final int MONTOPROGRAMADO = AttributesEnum.MontoProgramado.index();
    public static final int LINEAGLOBALIFI = AttributesEnum.LineaGlobalIfi.index();
    public static final int DESCRIPCIONCORTA = AttributesEnum.DescripcionCorta.index();
    public static final int IDOPERACION = AttributesEnum.IdOperacion.index();
    public static final int IDCONTRATO = AttributesEnum.IdContrato.index();
	public static final int IDTIPOMONEDAMONTOLINEA = AttributesEnum.IdTipoMonedaMontoLinea.index();
    public static final int IDTIPOMONEDAMONTOTOTAL = AttributesEnum.IdTipoMonedaMontoTotal.index();
    public static final int DIASSPOTTASA = AttributesEnum.DiasSpotTasa.index();
    public static final int IDTCATIPOREDONDEO = AttributesEnum.IdTcaTipoRedondeo.index();
    public static final int CANTIDADDECIMALES = AttributesEnum.CantidadDecimales.index();
    public static final int IDTCATIPOMORA = AttributesEnum.IdTcaTipoMora.index();
    public static final int CODIGOTASAREFERENCIA = AttributesEnum.CodigoTasaReferencia.index();
    public static final int VALORTASA = AttributesEnum.ValorTasa.index();
    public static final int SPREADTASA = AttributesEnum.SpreadTasa.index();
    public static final int FRECUENCIA = AttributesEnum.Frecuencia.index();
    public static final int IDTCATIPOFRECUENCIA = AttributesEnum.IdTcaTipoFrecuencia.index();
    public static final int MONEDA = AttributesEnum.Moneda.index();

    public static final int MONTOTOTALAPLICADO = AttributesEnum.MontoTotalAplicado.index();/*jenamorado 30/08/2021*/
    public static final int MONTOTOTALPENDIENTE = AttributesEnum.MontoTotalPendiente.index();/*jenamorado 31/08/2021*/
    /**
     * This is the default constructor (do not remove).
     */
    public DatosLineaCreditoVORowImpl() {
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
     * Gets the attribute value for the calculated attribute NumeroLineaCredito.
     * @return the NumeroLineaCredito
     */
    public String getNumeroLineaCredito() {
        return (String) getAttributeInternal(NUMEROLINEACREDITO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute NumeroLineaCredito.
     * @param value value to set the  NumeroLineaCredito
     */
    public void setNumeroLineaCredito(String value) {
        setAttributeInternal(NUMEROLINEACREDITO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Fondo.
     * @return the Fondo
     */
    public String getFondo() {
        return (String) getAttributeInternal(FONDO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Fondo.
     * @param value value to set the  Fondo
     */
    public void setFondo(String value) {
        setAttributeInternal(FONDO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MontoLinea.
     * @return the MontoLinea
     */
    public BigDecimal getMontoLinea() {
        return (BigDecimal) getAttributeInternal(MONTOLINEA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MontoLinea.
     * @param value value to set the  MontoLinea
     */
    public void setMontoLinea(BigDecimal value) {
        setAttributeInternal(MONTOLINEA, value);
    }

    /**
     * Obtenemos la suma de los montos.
     * @return the MontoTotal
     */
    public BigDecimal getMontoTotal() {
        BigDecimal montoTotal = new BigDecimal(0);
        BigDecimal monto = new BigDecimal(0);
        
        // The recommended way to iterate a View Object is via a secondary RowSetIterator             
        RowSetIterator iterator = this.getViewObject().createRowSetIterator(null);
        iterator.reset();

        while (iterator.hasNext()) {
            Row row = iterator.next();
            monto = (BigDecimal)row.getAttribute("MontoLinea");
            
            if(monto != null)
                montoTotal = montoTotal.add(monto);
        }

        iterator.closeRowSetIterator();        
        return montoTotal;
    }

    /**
     * Gets the attribute value for the calculated attribute IdFlexcube.
     * @return the IdFlexcube
     */
    public String getIdFlexcube() {
        return (String) getAttributeInternal(IDFLEXCUBE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdFlexcube.
     * @param value value to set the  IdFlexcube
     */
    public void setIdFlexcube(String value) {
        setAttributeInternal(IDFLEXCUBE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Descripcion.
     * @return the Descripcion
     */
    public String getDescripcion() {
        return (String) getAttributeInternal(DESCRIPCION);
    }


    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Descripcion.
     * @param value value to set the  Descripcion
     */
    public void setDescripcion(String value) {
        setAttributeInternal(DESCRIPCION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute FechaMaxima.
     * @return the FechaMaxima
     */
    public Timestamp getFechaMaxima() {
        return (Timestamp) getAttributeInternal(FECHAMAXIMA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FechaMaxima.
     * @param value value to set the  FechaMaxima
     */
    public void setFechaMaxima(Timestamp value) {
        setAttributeInternal(FECHAMAXIMA, value);
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

    /**
     * Gets the attribute value for the calculated attribute FechaVencimiento.
     * @return the FechaVencimiento
     */
    public Timestamp getFechaVencimiento() {
        return (Timestamp) getAttributeInternal(FECHAVENCIMIENTO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute FechaVencimiento.
     * @param value value to set the  FechaVencimiento
     */
    public void setFechaVencimiento(Timestamp value) {
        setAttributeInternal(FECHAVENCIMIENTO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MontoDisponible.
     * @return the MontoDisponible
     */
    public BigDecimal getMontoDisponible() {
        return (BigDecimal) getAttributeInternal(MONTODISPONIBLE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MontoDisponible.
     * @param value value to set the  MontoDisponible
     */
    public void setMontoDisponible(BigDecimal value) {
        setAttributeInternal(MONTODISPONIBLE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MontoProgramado.
     * @return the MontoProgramado
     */
    public BigDecimal getMontoProgramado() {
        return (BigDecimal) getAttributeInternal(MONTOPROGRAMADO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MontoProgramado.
     * @param value value to set the  MontoProgramado
     */
    public void setMontoProgramado(BigDecimal value) {
        setAttributeInternal(MONTOPROGRAMADO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute LineaGlobalIfi.
     * @return the LineaGlobalIfi
     */
    public Boolean getLineaGlobalIfi() {
        return (Boolean) getAttributeInternal(LINEAGLOBALIFI);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute LineaGlobalIfi.
     * @param value value to set the  LineaGlobalIfi
     */
    public void setLineaGlobalIfi(Boolean value) {
        setAttributeInternal(LINEAGLOBALIFI, value);
    }

    /**
     * Gets the attribute value for the calculated attribute DescripcionCorta.
     * @return the DescripcionCorta
     */
    public String getDescripcionCorta() {
        return (String) getAttributeInternal(DESCRIPCIONCORTA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DescripcionCorta.
     * @param value value to set the  DescripcionCorta
     */
    public void setDescripcionCorta(String value) {
        setAttributeInternal(DESCRIPCIONCORTA, value);
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
     * Gets the attribute value for the calculated attribute IdContrato.
     * @return the IdContrato
     */
    public Integer getIdContrato() {
        return (Integer) getAttributeInternal(IDCONTRATO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdContrato.
     * @param value value to set the  IdContrato
     */
    public void setIdContrato(Integer value) {
        setAttributeInternal(IDCONTRATO, value);
    }

	/**
     * Gets the attribute value for the calculated attribute IdTipoMonedaMontoLinea.
     * @return the IdTipoMonedaMontoLinea
     */
    public Integer getIdTipoMonedaMontoLinea() {
        return (Integer) getAttributeInternal(IDTIPOMONEDAMONTOLINEA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTipoMonedaMontoLinea.
     * @param value value to set the  IdTipoMonedaMontoLinea
     */
    public void setIdTipoMonedaMontoLinea(Integer value) {
        setAttributeInternal(IDTIPOMONEDAMONTOLINEA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTipoMonedaMontoTotal.
     * @return the IdTipoMonedaMontoTotal
     */
    public Integer getIdTipoMonedaMontoTotal() {
        return (Integer) getAttributeInternal(IDTIPOMONEDAMONTOTOTAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTipoMonedaMontoTotal.
     * @param value value to set the  IdTipoMonedaMontoTotal
     */
    public void setIdTipoMonedaMontoTotal(Integer value) {
        setAttributeInternal(IDTIPOMONEDAMONTOTOTAL, value);
    }

	/**
     * Gets the attribute value for the calculated attribute DiasSpotTasa.
     * @return the DiasSpotTasa
     */
    public Number getDiasSpotTasa() {
        return (Number) getAttributeInternal(DIASSPOTTASA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute DiasSpotTasa.
     * @param value value to set the  DiasSpotTasa
     */
    public void setDiasSpotTasa(Number value) {
        setAttributeInternal(DIASSPOTTASA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTcaTipoRedondeo.
     * @return the IdTcaTipoRedondeo
     */
    public Number getIdTcaTipoRedondeo() {
        return (Number) getAttributeInternal(IDTCATIPOREDONDEO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTcaTipoRedondeo.
     * @param value value to set the  IdTcaTipoRedondeo
     */
    public void setIdTcaTipoRedondeo(Number value) {
        setAttributeInternal(IDTCATIPOREDONDEO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CantidadDecimales.
     * @return the CantidadDecimales
     */
    public Number getCantidadDecimales() {
        return (Number) getAttributeInternal(CANTIDADDECIMALES);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CantidadDecimales.
     * @param value value to set the  CantidadDecimales
     */
    public void setCantidadDecimales(Number value) {
        setAttributeInternal(CANTIDADDECIMALES, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTcaTipoMora.
     * @return the IdTcaTipoMora
     */
    public Number getIdTcaTipoMora() {
        return (Number) getAttributeInternal(IDTCATIPOMORA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTcaTipoMora.
     * @param value value to set the  IdTcaTipoMora
     */
    public void setIdTcaTipoMora(Number value) {
        setAttributeInternal(IDTCATIPOMORA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CodigoTasaReferencia.
     * @return the CodigoTasaReferencia
     */
    public String getCodigoTasaReferencia() {
        return (String) getAttributeInternal(CODIGOTASAREFERENCIA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CodigoTasaReferencia.
     * @param value value to set the  CodigoTasaReferencia
     */
    public void setCodigoTasaReferencia(String value) {
        setAttributeInternal(CODIGOTASAREFERENCIA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ValorTasa.
     * @return the ValorTasa
     */
    public Number getValorTasa() {
        return (Number) getAttributeInternal(VALORTASA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ValorTasa.
     * @param value value to set the  ValorTasa
     */
    public void setValorTasa(Number value) {
        setAttributeInternal(VALORTASA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SpreadTasa.
     * @return the SpreadTasa
     */
    public Number getSpreadTasa() {
        return (Number) getAttributeInternal(SPREADTASA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SpreadTasa.
     * @param value value to set the  SpreadTasa
     */
    public void setSpreadTasa(Number value) {
        setAttributeInternal(SPREADTASA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Frecuencia.
     * @return the Frecuencia
     */
    public Number getFrecuencia() {
        return (Number) getAttributeInternal(FRECUENCIA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Frecuencia.
     * @param value value to set the  Frecuencia
     */
    public void setFrecuencia(Number value) {
        setAttributeInternal(FRECUENCIA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IdTcaTipoFrecuencia.
     * @return the IdTcaTipoFrecuencia
     */
    public Number getIdTcaTipoFrecuencia() {
        return (Number) getAttributeInternal(IDTCATIPOFRECUENCIA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IdTcaTipoFrecuencia.
     * @param value value to set the  IdTcaTipoFrecuencia
     */
    public void setIdTcaTipoFrecuencia(Number value) {
        setAttributeInternal(IDTCATIPOFRECUENCIA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute moneda.
     * @return the IdTcaTipoFrecuencia
     */
    public String getMoneda() {
        return (String) getAttributeInternal(MONEDA);
    }
    /**
     * Obtenemos la suma de los montos.
     * @return the MontoTotalAplicado
     */
    public BigDecimal getMontoTotalAplicado() {
        BigDecimal MontoTotalAplicado = new BigDecimal(0);
        BigDecimal monto = new BigDecimal(0);
        String IdFlexcube ;
        // The recommended way to iterate a View Object is via a secondary RowSetIterator             
        RowSetIterator iterator = this.getViewObject().createRowSetIterator(null);
        iterator.reset();

        while (iterator.hasNext()) {
            Row row = iterator.next();
            monto = (BigDecimal)row.getAttribute("MontoLinea");
            IdFlexcube=(String )row.getAttribute("IdFlexcube");
            if(monto != null && IdFlexcube != null && IdFlexcube.contains("BHQ") )
                MontoTotalAplicado = MontoTotalAplicado.add(monto);
        }

        iterator.closeRowSetIterator();        
        return MontoTotalAplicado;
    }
    /**
     * Obtenemos la suma de los montos.
     * @return the MontoTotalPendiente
     */
    public BigDecimal getMontoTotalPendiente() {
        BigDecimal MontoTotalPendiente = new BigDecimal(0);
        BigDecimal monto = new BigDecimal(0);
        String IdFlexcube ;
        // The recommended way to iterate a View Object is via a secondary RowSetIterator             
        RowSetIterator iterator = this.getViewObject().createRowSetIterator(null);
        iterator.reset();

        while (iterator.hasNext()) {
            Row row = iterator.next();
          try{
            monto = (BigDecimal)row.getAttribute("MontoLinea");
            IdFlexcube=(String )row.getAttribute("IdFlexcube");
            
            if(monto != null  && !IdFlexcube.contains("BHQ") )
                MontoTotalPendiente = MontoTotalPendiente.add(monto);
            }catch (Exception e) {
                monto = new BigDecimal(0);
                MontoTotalPendiente = MontoTotalPendiente.add(monto);
            
            }
        }

        iterator.closeRowSetIterator();        
        return MontoTotalPendiente;
    }
    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Moneda.
     * @param value value to set the  Moneda
     */
    public void setMoneda(String value) {
        setAttributeInternal(MONEDA, value);
    }


}

