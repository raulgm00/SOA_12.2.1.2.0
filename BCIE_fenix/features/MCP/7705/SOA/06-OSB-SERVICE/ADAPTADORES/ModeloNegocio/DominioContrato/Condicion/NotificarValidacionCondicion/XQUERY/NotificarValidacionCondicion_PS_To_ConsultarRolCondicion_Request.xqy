xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarRolesCondicion";
(:: import schema at "../../../../Utilidades/ConsultarRolesCondicion/XSD/ConsultarRolesCondicion.xsd" ::)

declare variable $NotificarValidacionCondicionRequest as element() (:: schema-element(ns1:NotificarValidacionCondicionRequest) ::) external;

declare function local:func($NotificarValidacionCondicionRequest as element() (:: schema-element(ns1:NotificarValidacionCondicionRequest) ::)) as element() (:: schema-element(ns2:ConsultarRolesCondicionInput) ::) {
    <ns2:ConsultarRolesCondicionInput>
        <ns2:OPERACION>{fn:data($NotificarValidacionCondicionRequest/ns1:idOperacion)}</ns2:OPERACION>
    </ns2:ConsultarRolesCondicionInput>
};

local:func($NotificarValidacionCondicionRequest)
