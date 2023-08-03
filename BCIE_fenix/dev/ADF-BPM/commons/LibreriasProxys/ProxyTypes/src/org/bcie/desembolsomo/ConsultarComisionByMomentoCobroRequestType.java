
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para ConsultarComisionByMomentoCobroRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarComisionByMomentoCobroRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="idMomentoCobro" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarComisionByMomentoCobroRequestType", propOrder = {
    "idOperacion",
    "idMomentoCobro"
})
public class ConsultarComisionByMomentoCobroRequestType {

    protected long idOperacion;
    @XmlElement(required = true)
    protected Catalogo idMomentoCobro;

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     */
    public long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     */
    public void setIdOperacion(long value) {
        this.idOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idMomentoCobro.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getIdMomentoCobro() {
        return idMomentoCobro;
    }

    /**
     * Define el valor de la propiedad idMomentoCobro.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setIdMomentoCobro(Catalogo value) {
        this.idMomentoCobro = value;
    }

}
