
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CrearOperacionAsociadaRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearOperacionAsociadaRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacionPadre" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="idOperacionAsociada" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearOperacionAsociadaRequestType", propOrder = {
    "idOperacionPadre",
    "idOperacionAsociada"
})
public class CrearOperacionAsociadaRequestType {

    protected long idOperacionPadre;
    protected long idOperacionAsociada;

    /**
     * Obtiene el valor de la propiedad idOperacionPadre.
     * 
     */
    public long getIdOperacionPadre() {
        return idOperacionPadre;
    }

    /**
     * Define el valor de la propiedad idOperacionPadre.
     * 
     */
    public void setIdOperacionPadre(long value) {
        this.idOperacionPadre = value;
    }

    /**
     * Obtiene el valor de la propiedad idOperacionAsociada.
     * 
     */
    public long getIdOperacionAsociada() {
        return idOperacionAsociada;
    }

    /**
     * Define el valor de la propiedad idOperacionAsociada.
     * 
     */
    public void setIdOperacionAsociada(long value) {
        this.idOperacionAsociada = value;
    }

}
