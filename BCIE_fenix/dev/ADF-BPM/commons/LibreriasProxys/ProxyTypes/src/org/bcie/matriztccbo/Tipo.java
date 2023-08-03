
package org.bcie.matriztccbo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipo.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="tipo">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TERMINO"/>
 *     &lt;enumeration value="CONDICION"/>
 *     &lt;enumeration value="COMISION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipo")
@XmlEnum
public enum Tipo {

    TERMINO,
    CONDICION,
    COMISION;

    public String value() {
        return name();
    }

    public static Tipo fromValue(String v) {
        return valueOf(v);
    }

}
