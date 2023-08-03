
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CrearCuestionario2RequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearCuestionario2RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="idProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="regenerar" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearCuestionario2RequestType", propOrder = {
    "idOperacion",
    "idProceso",
    "regenerar"
})
public class CrearCuestionario2RequestType {

    protected long idOperacion;
    protected long idProceso;
    protected boolean regenerar;

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
     * Obtiene el valor de la propiedad idProceso.
     * 
     */
    public long getIdProceso() {
        return idProceso;
    }

    /**
     * Define el valor de la propiedad idProceso.
     * 
     */
    public void setIdProceso(long value) {
        this.idProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad regenerar.
     * 
     */
    public boolean isRegenerar() {
        return regenerar;
    }

    /**
     * Define el valor de la propiedad regenerar.
     * 
     */
    public void setRegenerar(boolean value) {
        this.regenerar = value;
    }

}
