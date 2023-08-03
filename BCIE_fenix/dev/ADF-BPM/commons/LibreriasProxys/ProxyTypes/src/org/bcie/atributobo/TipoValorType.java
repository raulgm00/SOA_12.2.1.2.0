
package org.bcie.atributobo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TipoValorType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoValorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="VARCHAR"/>
 *     &lt;enumeration value="NUMBER"/>
 *     &lt;enumeration value="DECIMAL"/>
 *     &lt;enumeration value="DATE"/>
 *     &lt;enumeration value="TIMESTAMP"/>
 *     &lt;enumeration value="ONECHOICE"/>
 *     &lt;enumeration value="MULTICHOICE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoValorType")
@XmlEnum
public enum TipoValorType {

    VARCHAR,
    NUMBER,
    DECIMAL,
    DATE,
    TIMESTAMP,
    ONECHOICE,
    MULTICHOICE;

    public String value() {
        return name();
    }

    public static TipoValorType fromValue(String v) {
        return valueOf(v);
    }

}
