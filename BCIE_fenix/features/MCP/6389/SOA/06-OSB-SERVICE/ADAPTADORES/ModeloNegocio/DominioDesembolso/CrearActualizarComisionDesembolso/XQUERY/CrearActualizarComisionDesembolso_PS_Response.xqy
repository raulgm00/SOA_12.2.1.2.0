xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:CrearActualizarComisionDesembolsoResponse) ::) {
    <ns1:CrearActualizarComisionDesembolsoResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Operación existosa</res:message>
        </ns1:Resultado>
    </ns1:CrearActualizarComisionDesembolsoResponse>
};

local:func()
