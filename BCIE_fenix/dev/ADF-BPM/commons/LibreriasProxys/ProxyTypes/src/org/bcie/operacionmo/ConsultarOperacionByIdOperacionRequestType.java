
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.bcie.atributobo.NivelType;


/**
 * Capacidad para consultar una Operacion por un Id de Operacion
 *           Usar el Id del Operacion para la consulta
 * 
 * <p>Clase Java para ConsultarOperacionByIdOperacionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarOperacionByIdOperacionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="nivelDetalle" type="{http://www.bcie.org/AtributoBO}NivelType" minOccurs="0"/>
 *         &lt;element name="infoDetalleCliente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarOperacionByIdOperacionRequestType", propOrder = {
    "idOperacion",
    "nivelDetalle",
    "infoDetalleCliente"
})
public class ConsultarOperacionByIdOperacionRequestType {

    protected long idOperacion;
    @XmlElement(defaultValue = "OPERACION")
    @XmlSchemaType(name = "string")
    protected NivelType nivelDetalle;
    protected Boolean infoDetalleCliente;

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
     * Obtiene el valor de la propiedad nivelDetalle.
     * 
     * @return
     *     possible object is
     *     {@link NivelType }
     *     
     */
    public NivelType getNivelDetalle() {
        return nivelDetalle;
    }

    /**
     * Define el valor de la propiedad nivelDetalle.
     * 
     * @param value
     *     allowed object is
     *     {@link NivelType }
     *     
     */
    public void setNivelDetalle(NivelType value) {
        this.nivelDetalle = value;
    }

    /**
     * Obtiene el valor de la propiedad infoDetalleCliente.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInfoDetalleCliente() {
        return infoDetalleCliente;
    }

    /**
     * Define el valor de la propiedad infoDetalleCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInfoDetalleCliente(Boolean value) {
        this.infoDetalleCliente = value;
    }

}
