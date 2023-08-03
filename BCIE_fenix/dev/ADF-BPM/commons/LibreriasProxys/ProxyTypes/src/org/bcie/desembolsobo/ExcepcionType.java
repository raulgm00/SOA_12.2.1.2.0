
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.bcie.reglabo.ReglaBasicType;


/**
 * <p>Clase Java para ExcepcionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ExcepcionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/ReglaBO}ReglaBasicType">
 *       &lt;sequence>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="enProcesoExcepcion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExcepcionType", propOrder = {
    "instanciaProceso",
    "enProcesoExcepcion"
})
public class ExcepcionType
    extends ReglaBasicType
{

    protected long instanciaProceso;
    protected boolean enProcesoExcepcion;

    /**
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     */
    public long getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     */
    public void setInstanciaProceso(long value) {
        this.instanciaProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad enProcesoExcepcion.
     * 
     */
    public boolean isEnProcesoExcepcion() {
        return enProcesoExcepcion;
    }

    /**
     * Define el valor de la propiedad enProcesoExcepcion.
     * 
     */
    public void setEnProcesoExcepcion(boolean value) {
        this.enProcesoExcepcion = value;
    }

}
