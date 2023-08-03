xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarObservacionCondicion";
(:: import schema at "../../ConsultarObservacionCondicion/XSD/ConsultarObservacionCondicion.xsd" ::)

declare variable $ConsultarCondicionByRolRequest as element() (:: schema-element(ns1:ConsultarCondicionByRolRequest) ::) external;

declare function local:func($ConsultarCondicionByRolRequest as element() (:: schema-element(ns1:ConsultarCondicionByRolRequest) ::)) as element() (:: schema-element(ns2:ConsultarObservacionCondicionInput) ::) {
    <ns2:ConsultarObservacionCondicionInput>
        <ns2:P_OPERACION>{fn:data($ConsultarCondicionByRolRequest/ns1:idOperacion)}</ns2:P_OPERACION>
    </ns2:ConsultarObservacionCondicionInput>
};

local:func($ConsultarCondicionByRolRequest)