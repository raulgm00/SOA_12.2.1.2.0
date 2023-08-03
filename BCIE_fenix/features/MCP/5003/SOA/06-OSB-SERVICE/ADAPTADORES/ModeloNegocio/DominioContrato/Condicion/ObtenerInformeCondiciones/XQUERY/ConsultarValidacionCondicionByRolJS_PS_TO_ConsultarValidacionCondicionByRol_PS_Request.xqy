xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare variable $ConsultarValidacionCondicionByRolSJRequest as element() (:: schema-element(ns1:ConsultarValidacionCondicionByRolSJRequest) ::) external;
declare variable $counter as xs:int external;

declare function local:func($ConsultarValidacionCondicionByRolSJRequest as element() (:: schema-element(ns1:ConsultarValidacionCondicionByRolSJRequest) ::), 
                            $counter as xs:int) 
                            as element() (:: schema-element(ns1:ConsultarCondicionValidacionByRolRequest) ::) {
    <ns1:ConsultarCondicionValidacionByRolRequest>
        <ns1:idOperacion>{fn:data($ConsultarValidacionCondicionByRolSJRequest/ns1:idOperacion)}</ns1:idOperacion>
        <ns1:agrupador>{fn:data($ConsultarValidacionCondicionByRolSJRequest/ns1:agrupador[$counter])}</ns1:agrupador>
    </ns1:ConsultarCondicionValidacionByRolRequest>
};

local:func($ConsultarValidacionCondicionByRolSJRequest, $counter)
