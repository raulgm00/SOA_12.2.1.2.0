xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContrato";
(:: import schema at "../XSD/ConsultarContrato.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCuentas";
(:: import schema at "../../ConsultarCuentas/XSD/ConsultarCuentas.xsd" ::)

declare variable $requestCuentas as element() (:: schema-element(ns1:ConsultarContratoOutputCollection) ::) external;
declare variable $index as xs:int external;
declare variable $tipoMoneda as xs:string external;

declare function local:func($requestCuentas as element() (:: schema-element(ns1:ConsultarContratoOutputCollection) ::), $index as xs:int) as element() (:: schema-element(ns2:ConsultarCuentasInput) ::) {
    <ns2:ConsultarCuentasInput>
        <ns2:P_CLIENTE>{fn:data($requestCuentas/ns1:ConsultarContratoOutput[$index]/ns1:ID_FLEXCUBE)}</ns2:P_CLIENTE>
        <ns2:tipoMoneda>{fn:data($tipoMoneda)}</ns2:tipoMoneda>
    </ns2:ConsultarCuentasInput>
};

local:func($requestCuentas,  $index)
