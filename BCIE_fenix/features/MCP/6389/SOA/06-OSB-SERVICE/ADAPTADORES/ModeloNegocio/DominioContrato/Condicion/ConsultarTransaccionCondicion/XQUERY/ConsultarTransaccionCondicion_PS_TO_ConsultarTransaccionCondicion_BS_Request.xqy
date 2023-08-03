xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTransaccionCondicion";
(:: import schema at "../XSD/ConsultarTransaccionCondicion.xsd" ::)

declare variable $ConsultarTransaccionCondicionRequest as element() (:: schema-element(ns1:ConsultarTransaccionCondicionRequest) ::) external;

declare function local:func($ConsultarTransaccionCondicionRequest as element() (:: schema-element(ns1:ConsultarTransaccionCondicionRequest) ::)) as element() (:: schema-element(ns2:ConsultarTransaccionCondicionInput) ::) {
    <ns2:ConsultarTransaccionCondicionInput>
        <ns2:agrupador>{fn:data($ConsultarTransaccionCondicionRequest/ns1:Agrupador)}</ns2:agrupador>
        <ns2:idOperacion>{fn:data($ConsultarTransaccionCondicionRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarTransaccionCondicionInput>
};

local:func($ConsultarTransaccionCondicionRequest)
