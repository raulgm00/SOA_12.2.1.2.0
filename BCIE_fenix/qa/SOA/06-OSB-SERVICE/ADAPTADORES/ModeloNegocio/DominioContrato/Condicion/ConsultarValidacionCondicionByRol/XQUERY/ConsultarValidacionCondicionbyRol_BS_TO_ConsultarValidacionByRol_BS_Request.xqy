xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarValidacionCondicionByRol";
(:: import schema at "../XSD/ConsultarValidacionCondicionByRol.xsd" ::)

declare variable $ConsultarCondicionValidacionByRolRequest as element() (:: schema-element(ns1:ConsultarCondicionValidacionByRolRequest) ::) external;

declare function local:func($ConsultarCondicionValidacionByRolRequest as element() (:: schema-element(ns1:ConsultarCondicionValidacionByRolRequest) ::)) as element() (:: schema-element(ns2:ConsultarValidacionCondicionByRolInput) ::) {
    <ns2:ConsultarValidacionCondicionByRolInput>
        <ns2:P_ID_ROL_BPM>{ if (string($ConsultarCondicionValidacionByRolRequest/ns1:idRol)='0') then ()
        else
        fn:data($ConsultarCondicionValidacionByRolRequest/ns1:idRol)
        }</ns2:P_ID_ROL_BPM>
        <ns2:P_AGRUPADOR>{if($ConsultarCondicionValidacionByRolRequest/ns1:agrupador/text() = '') then () 
                        else data($ConsultarCondicionValidacionByRolRequest/ns1:agrupador)
        }</ns2:P_AGRUPADOR>
        <ns2:P_OPERACION>{fn:data($ConsultarCondicionValidacionByRolRequest/ns1:idOperacion)}</ns2:P_OPERACION>
        
    </ns2:ConsultarValidacionCondicionByRolInput>
};

local:func($ConsultarCondicionValidacionByRolRequest)