xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:ActualizarEstadoTCCResponse) ::) {
    <ns1:ActualizarEstadoTCCResponse>
        <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>Estados Invalidos</res:message>
        </ns1:Resultado>
    </ns1:ActualizarEstadoTCCResponse>
};

local:func()
