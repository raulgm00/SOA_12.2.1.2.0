xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $CrearOperacionAsociadaRequest as element() (:: schema-element(ns1:CrearOperacionAsociadaRequest) ::) external;

declare function local:func($CrearOperacionAsociadaRequest as element() (:: schema-element(ns1:CrearOperacionAsociadaRequest) ::)) as element() (:: schema-element(ns1:CrearOperacionAsociadaResponse) ::) {
    <ns1:CrearOperacionAsociadaResponse>
        <ns1:idOperacionPadre>{fn:data($CrearOperacionAsociadaRequest/ns1:idOperacionPadre)}</ns1:idOperacionPadre>
        <ns1:idOperacionAsociada>{fn:data($CrearOperacionAsociadaRequest/ns1:idOperacionAsociada)}</ns1:idOperacionAsociada>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Operacion creada satisfactoriamente</res:message>
        </ns1:Resultado>
    </ns1:CrearOperacionAsociadaResponse>
};

local:func($CrearOperacionAsociadaRequest)
