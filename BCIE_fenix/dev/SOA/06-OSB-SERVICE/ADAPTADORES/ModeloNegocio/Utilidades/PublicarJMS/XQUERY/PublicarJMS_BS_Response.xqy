xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/PublicarJMSMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/PublicarJMS/V1/Schema/PublicarJMSMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:PublicarJMSResponse) ::) {
    <ns1:PublicarJMSResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Mensaje enviado exitosamente</res:message>
        </ns1:Resultado>
    </ns1:PublicarJMSResponse>
};

local:func()
