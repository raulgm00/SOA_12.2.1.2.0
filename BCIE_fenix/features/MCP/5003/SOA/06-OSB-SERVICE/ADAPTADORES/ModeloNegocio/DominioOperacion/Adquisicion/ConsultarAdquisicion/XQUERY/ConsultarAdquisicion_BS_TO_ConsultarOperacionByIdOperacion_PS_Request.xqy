xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAdquisicion";
(:: import schema at "../XSD/ConsultarAdquisicion.xsd" ::)

declare variable $ConsultarAdquisicionOutputCollection as element() (:: schema-element(ns1:ConsultarAdquisicionOutputCollection) ::) external;

declare function local:func($ConsultarAdquisicionOutputCollection as element() (:: schema-element(ns1:ConsultarAdquisicionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarOperacionByIdOperacionRequest) ::) {
    <ns2:ConsultarOperacionByIdOperacionRequest>
        <ns2:idOperacion>{fn:data($ConsultarAdquisicionOutputCollection/ns1:ConsultarAdquisicionOutput/ns1:ID_OPERACION)}</ns2:idOperacion>
        <ns2:nivelDetalle>OPERACION</ns2:nivelDetalle>
    </ns2:ConsultarOperacionByIdOperacionRequest>
};

local:func($ConsultarAdquisicionOutputCollection)