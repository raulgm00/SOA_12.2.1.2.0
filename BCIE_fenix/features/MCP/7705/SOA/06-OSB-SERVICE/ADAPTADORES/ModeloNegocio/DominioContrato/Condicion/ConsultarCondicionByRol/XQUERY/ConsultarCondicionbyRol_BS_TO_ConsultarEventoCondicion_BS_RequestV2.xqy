xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../ConsultarValidacionCondicionByRol/XSD/CondicionMOV2.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEventoCondicion";
(:: import schema at "../XSD/ConsultarEventoCondicionV2.xsd" ::)

declare variable $ConsultarCondicionByRolRequest as element() (:: schema-element(ns1:ConsultarCondicionByRolRequest) ::) external;

declare function local:func($ConsultarCondicionByRolRequest as element() (:: schema-element(ns1:ConsultarCondicionByRolRequest) ::)) as element() (:: schema-element(ns2:ConsultarEventoCondicionInput) ::) {
    <ns2:ConsultarEventoCondicionInput>
        <ns2:P_EVENTO/>
        <ns2:P_ID_CONDICION>{fn:data($ConsultarCondicionByRolRequest/ns1:idCondicion)}</ns2:P_ID_CONDICION>
       
    </ns2:ConsultarEventoCondicionInput>
};

local:func($ConsultarCondicionByRolRequest)