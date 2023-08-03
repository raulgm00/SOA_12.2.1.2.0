xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare variable $idOperacion as element() external;

declare function local:func($idOperacion as element()) as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionRequest) ::) {
    <ns1:ConsultarOperacionByIdOperacionRequest>
        <ns1:idOperacion>{fn:data($idOperacion)}</ns1:idOperacion>
    </ns1:ConsultarOperacionByIdOperacionRequest>
};

local:func($idOperacion)
