xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/PublicarJMSMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/PublicarJMS/V1/Schema/PublicarJMSMO.xsd" ::)

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $PublicarJMSRequest as element() (:: schema-element(ns1:PublicarJMSRequest) ::) external;

declare function local:func($PublicarJMSRequest as element() (:: schema-element(ns1:PublicarJMSRequest) ::)) as element() (:: schema-element(ns1:PublicarJMSRequest) ::) {
    <ns1:PublicarJMSRequest>
        <ns1:JMS>
            <com:nombreJMS>{fn:data($PublicarJMSRequest/ns1:JMS/com:nombreJMS)}</com:nombreJMS>
            <com:mensaje>{fn:data($PublicarJMSRequest/ns1:JMS/com:mensaje)}</com:mensaje>
        </ns1:JMS>
    </ns1:PublicarJMSRequest>
};

local:func($PublicarJMSRequest)