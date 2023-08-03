xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare variable $request as element() (:: schema-element(ns1:NotificarValidacionCondicionRequest) ::) external;

declare function local:func($request as element() (:: schema-element(ns1:NotificarValidacionCondicionRequest) ::)) as element() (:: schema-element(ns1:ConsultarTransaccionCondicionRequest) ::) {
    <ns1:ConsultarTransaccionCondicionRequest>
        <ns1:Agrupador>{fn:data($request/ns1:idAgrupador)}</ns1:Agrupador>
    </ns1:ConsultarTransaccionCondicionRequest>
};

local:func($request)