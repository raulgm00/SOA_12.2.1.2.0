xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTerminoByIdOperacion";
(:: import schema at "../XSD/ConsultarTerminoByIdOperacion.xsd" ::)

declare variable $requestConsultarPlazoCondicionMessage as element() (:: schema-element(ns1:ConsultarPlazoCondicionRequest) ::) external;

declare function local:func($requestConsultarPlazoCondicionMessage as element() (:: schema-element(ns1:ConsultarPlazoCondicionRequest) ::)) as element() (:: schema-element(ns2:ConsultarTerminoByIdOperacionInput) ::) {
    <ns2:ConsultarTerminoByIdOperacionInput>
        <ns2:idOperacion>{fn:data($requestConsultarPlazoCondicionMessage/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarTerminoByIdOperacionInput>
};

local:func($requestConsultarPlazoCondicionMessage)
