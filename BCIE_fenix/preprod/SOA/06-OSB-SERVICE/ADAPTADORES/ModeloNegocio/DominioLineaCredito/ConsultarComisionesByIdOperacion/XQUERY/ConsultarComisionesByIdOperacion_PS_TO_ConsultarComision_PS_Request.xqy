xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)

declare variable $ConsultarComisionesByIdOperacion as element() (:: schema-element(ns1:ConsultarComisionesByIdOperacionRequest) ::) external;

declare function local:func($ConsultarComisionesByIdOperacion as element() (:: schema-element(ns1:ConsultarComisionesByIdOperacionRequest) ::)) as element() (:: schema-element(ns1:ConsultarComisionRequest) ::) {
    <ns1:ConsultarComisionRequest>
        <ns1:idOperacion>{fn:data($ConsultarComisionesByIdOperacion/ns1:idOperacion)}</ns1:idOperacion>
    </ns1:ConsultarComisionRequest>
};

local:func($ConsultarComisionesByIdOperacion)
