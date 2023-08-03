xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare variable $ObtenerInformeCondicionesRequest as element() (:: schema-element(ns1:ObtenerInformeCondicionesRequest) ::) external;

declare function local:func($ObtenerInformeCondicionesRequest as element() (:: schema-element(ns1:ObtenerInformeCondicionesRequest) ::)) as element() (:: schema-element(ns1:ConsultarTransaccionCondicionRequest) ::) {
    <ns1:ConsultarTransaccionCondicionRequest>
        <ns1:idOperacion>{fn:data($ObtenerInformeCondicionesRequest/ns1:idOperacion)}</ns1:idOperacion>
    </ns1:ConsultarTransaccionCondicionRequest>
};

local:func($ObtenerInformeCondicionesRequest)
