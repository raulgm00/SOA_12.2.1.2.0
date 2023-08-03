
package org.bcie.productobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Entidad Producto - (Modelo canónico)
 *           Se usa para la administración de los productos del banco
 * 			  
 * 
 * <p>Clase Java para Producto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Producto">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/ProductoBO}ProductoBasicType">
 *       &lt;sequence>
 *         &lt;element name="idInstrumentoFinanciero" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idTipoOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="reglas" type="{http://www.bcie.org/ProductoBO}ReglasOperacion"/>
 *         &lt;element name="instrumentoFinanciero" type="{http://www.bcie.org/ProductoBO}InstrumentoFinanciero"/>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Requiere_Cierre" type="{http://www.bcie.org/ProductoBO}RequiereCierre"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Producto", propOrder = {
    "idInstrumentoFinanciero",
    "idTipoOperacion",
    "reglas",
    "instrumentoFinanciero",
    "estatus",
    "requiereCierre"
})
public class Producto
    extends ProductoBasicType
{

    protected int idInstrumentoFinanciero;
    protected long idTipoOperacion;
    @XmlElement(required = true)
    protected ReglasOperacion reglas;
    @XmlElement(required = true)
    protected InstrumentoFinanciero instrumentoFinanciero;
    protected boolean estatus;
    @XmlElement(name = "Requiere_Cierre", required = true)
    protected RequiereCierre requiereCierre;

    /**
     * Obtiene el valor de la propiedad idInstrumentoFinanciero.
     * 
     */
    public int getIdInstrumentoFinanciero() {
        return idInstrumentoFinanciero;
    }

    /**
     * Define el valor de la propiedad idInstrumentoFinanciero.
     * 
     */
    public void setIdInstrumentoFinanciero(int value) {
        this.idInstrumentoFinanciero = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoOperacion.
     * 
     */
    public long getIdTipoOperacion() {
        return idTipoOperacion;
    }

    /**
     * Define el valor de la propiedad idTipoOperacion.
     * 
     */
    public void setIdTipoOperacion(long value) {
        this.idTipoOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad reglas.
     * 
     * @return
     *     possible object is
     *     {@link ReglasOperacion }
     *     
     */
    public ReglasOperacion getReglas() {
        return reglas;
    }

    /**
     * Define el valor de la propiedad reglas.
     * 
     * @param value
     *     allowed object is
     *     {@link ReglasOperacion }
     *     
     */
    public void setReglas(ReglasOperacion value) {
        this.reglas = value;
    }

    /**
     * Obtiene el valor de la propiedad instrumentoFinanciero.
     * 
     * @return
     *     possible object is
     *     {@link InstrumentoFinanciero }
     *     
     */
    public InstrumentoFinanciero getInstrumentoFinanciero() {
        return instrumentoFinanciero;
    }

    /**
     * Define el valor de la propiedad instrumentoFinanciero.
     * 
     * @param value
     *     allowed object is
     *     {@link InstrumentoFinanciero }
     *     
     */
    public void setInstrumentoFinanciero(InstrumentoFinanciero value) {
        this.instrumentoFinanciero = value;
    }

    /**
     * Obtiene el valor de la propiedad estatus.
     * 
     */
    public boolean isEstatus() {
        return estatus;
    }

    /**
     * Define el valor de la propiedad estatus.
     * 
     */
    public void setEstatus(boolean value) {
        this.estatus = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereCierre.
     * 
     * @return
     *     possible object is
     *     {@link RequiereCierre }
     *     
     */
    public RequiereCierre getRequiereCierre() {
        return requiereCierre;
    }

    /**
     * Define el valor de la propiedad requiereCierre.
     * 
     * @param value
     *     allowed object is
     *     {@link RequiereCierre }
     *     
     */
    public void setRequiereCierre(RequiereCierre value) {
        this.requiereCierre = value;
    }

}
