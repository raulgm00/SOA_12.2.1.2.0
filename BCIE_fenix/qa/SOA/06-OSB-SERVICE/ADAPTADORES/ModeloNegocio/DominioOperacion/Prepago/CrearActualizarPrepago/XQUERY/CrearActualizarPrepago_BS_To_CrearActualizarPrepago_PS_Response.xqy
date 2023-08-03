xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarPrepago";
(:: import schema at "../XSD/CrearActualizarPrepago_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $response as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($response as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearActualizarPrepagoResponse) ::) {
    <ns2:CrearActualizarPrepagoResponse>
        <ns2:P_ID_PREPAGO>{fn:data($response/ns1:P_ID_PREPAGO)}</ns2:P_ID_PREPAGO>
        <ns2:P_ESTADO>{fn:data($response/ns1:P_ESTADO)}</ns2:P_ESTADO>
        <ns2:P_CODIGO>{fn:data($response/ns1:P_CODIGO)}</ns2:P_CODIGO>
        <ns2:P_MENSAJE>{fn:data($response/ns1:P_MENSAJE)}</ns2:P_MENSAJE>
       
    </ns2:CrearActualizarPrepagoResponse>
};

local:func($response)