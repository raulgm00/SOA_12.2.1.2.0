
package org.bcie.atributobo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Accion.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="Accion">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EDITAR"/>
 *     &lt;enumeration value="ELIMINAR"/>
 *     &lt;enumeration value="AGREGAR"/>
 *     &lt;enumeration value="EXCEPTUAR"/>
 *     &lt;enumeration value="DISPENSAR"/>
 *     &lt;enumeration value="INACTIVAR"/>
 *     &lt;enumeration value="REACTIVAR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Accion")
@XmlEnum
public enum Accion {

    EDITAR,
    ELIMINAR,
    AGREGAR,
    EXCEPTUAR,
    DISPENSAR,
    INACTIVAR,
    REACTIVAR;

    public String value() {
        return name();
    }

    public static Accion fromValue(String v) {
        return valueOf(v);
    }

}
