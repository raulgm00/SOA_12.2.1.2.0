
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;
import org.bcie.commonbo.MontoType;
import org.bcie.matriztccbo.TCC;


/**
 * <p>Clase Java para CargoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CargoType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/CatalogoBO}Catalogo">
 *       &lt;sequence>
 *         &lt;element name="Monto" type="{http://www.bcie.org/CommonBO}MontoType"/>
 *         &lt;element name="FechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SoloLectura" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TCC" type="{http://www.bcie.org/MatrizTCCBO}TCC" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CargoType", propOrder = {
    "monto",
    "fechaRegistro",
    "status",
    "soloLectura",
    "tcc"
})
public class CargoType
    extends Catalogo
{

    @XmlElement(name = "Monto", required = true)
    protected MontoType monto;
    @XmlElement(name = "FechaRegistro", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    @XmlElement(name = "Status")
    protected Boolean status;
    @XmlElement(name = "SoloLectura")
    protected Boolean soloLectura;
    @XmlElement(name = "TCC")
    protected TCC tcc;

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     * @return
     *     possible object is
     *     {@link MontoType }
     *     
     */
    public MontoType getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     * @param value
     *     allowed object is
     *     {@link MontoType }
     *     
     */
    public void setMonto(MontoType value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaRegistro.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Define el valor de la propiedad fechaRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRegistro(XMLGregorianCalendar value) {
        this.fechaRegistro = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStatus(Boolean value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad soloLectura.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSoloLectura() {
        return soloLectura;
    }

    /**
     * Define el valor de la propiedad soloLectura.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSoloLectura(Boolean value) {
        this.soloLectura = value;
    }

    /**
     * Obtiene el valor de la propiedad tcc.
     * 
     * @return
     *     possible object is
     *     {@link TCC }
     *     
     */
    public TCC getTCC() {
        return tcc;
    }

    /**
     * Define el valor de la propiedad tcc.
     * 
     * @param value
     *     allowed object is
     *     {@link TCC }
     *     
     */
    public void setTCC(TCC value) {
        this.tcc = value;
    }

}
