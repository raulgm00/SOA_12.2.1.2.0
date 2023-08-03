xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare variable $ConsultarCondicionValidacionByRolRequest as element() (:: schema-element(ns1:ConsultarCondicionValidacionByRolRequest) ::) external;

declare function local:func($ConsultarCondicionValidacionByRolRequest as element() (:: schema-element(ns1:ConsultarCondicionValidacionByRolRequest) ::)) as element() (:: schema-element(ns1:ConsultarCondicionByRolRequest) ::) {
    <ns1:ConsultarCondicionByRolRequest>
        <ns1:idOperacion>{fn:data($ConsultarCondicionValidacionByRolRequest/ns1:idOperacion)}</ns1:idOperacion>
        <ns1:idRol>{if (
        string($ConsultarCondicionValidacionByRolRequest/ns1:idRol)='1') then () else       fn:data($ConsultarCondicionValidacionByRolRequest/ns1:idRol)
        }</ns1:idRol>
        <ns1:Agrupador>{fn:data($ConsultarCondicionValidacionByRolRequest/ns1:agrupador)}</ns1:Agrupador>
    </ns1:ConsultarCondicionByRolRequest>
};

local:func($ConsultarCondicionValidacionByRolRequest)