
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultarMontoOperacionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarMontoOperacionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="IdTcaTipoMonto" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarMontoOperacionRequestType", propOrder = {
    "idOperacion",
    "idTcaTipoMonto"
})
public class ConsultarMontoOperacionRequestType {

    protected long idOperacion;
    @XmlElement(name = "IdTcaTipoMonto")
    protected long idTcaTipoMonto;

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
     * Obtiene el valor de la propiedad idTcaTipoMonto.
     * 
     */
    public long getIdTcaTipoMonto() {
        return idTcaTipoMonto;
    }

    /**
     * Define el valor de la propiedad idTcaTipoMonto.
     * 
     */
    public void setIdTcaTipoMonto(long value) {
        this.idTcaTipoMonto = value;
    }

}
