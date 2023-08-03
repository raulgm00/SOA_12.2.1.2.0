xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)

declare variable $ConsultarPlazoCondicionRequest as element() (:: schema-element(ns1:ConsultarPlazoCondicionRequest) ::) external;

declare function local:func($ConsultarPlazoCondicionRequest as element() (:: schema-element(ns1:ConsultarPlazoCondicionRequest) ::)) as element() (:: schema-element(ns2:ConsultarCondicionByRolRequest) ::) {
    <ns2:ConsultarCondicionByRolRequest>
        <ns2:idOperacion>{fn:data($ConsultarPlazoCondicionRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:idRol></ns2:idRol>
        <ns2:idEventoCondicion></ns2:idEventoCondicion>
    </ns2:ConsultarCondicionByRolRequest>
};

local:func($ConsultarPlazoCondicionRequest)
