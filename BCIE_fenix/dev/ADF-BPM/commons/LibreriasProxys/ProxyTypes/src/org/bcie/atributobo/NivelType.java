
package org.bcie.atributobo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para NivelType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="NivelType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OPERACION"/>
 *     &lt;enumeration value="CONTRATO"/>
 *     &lt;enumeration value="LINEA"/>
 *     &lt;enumeration value="SOLICITUDESDESEMBOLSO"/>
 *     &lt;enumeration value="DESEMBOLSO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NivelType")
@XmlEnum
public enum NivelType {

    OPERACION,
    CONTRATO,
    LINEA,
    SOLICITUDESDESEMBOLSO,
    DESEMBOLSO;

    public String value() {
        return name();
    }

    public static NivelType fromValue(String v) {
        return valueOf(v);
    }

}
