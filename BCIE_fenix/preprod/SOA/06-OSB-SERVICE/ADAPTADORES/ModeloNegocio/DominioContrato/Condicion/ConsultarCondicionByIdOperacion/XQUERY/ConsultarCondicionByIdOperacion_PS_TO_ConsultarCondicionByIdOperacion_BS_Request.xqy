xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCondicionByIdOperacion";
(:: import schema at "../XSD/ConsultarCondicionByIdOperacion.xsd" ::)

declare variable $ConsultarCondicionByIdOperacionRequest as element() (:: schema-element(ns2:ConsultarCondicionByIdOperacionRequest) ::) external;

declare function local:func($ConsultarCondicionByIdOperacionRequest as element() (:: schema-element(ns2:ConsultarCondicionByIdOperacionRequest) ::)) as element() (:: schema-element(ns1:ConsultarCondicionByIdOperacionInput) ::) {
    <ns1:ConsultarCondicionByIdOperacionInput>
        <ns1:idOperacion>{fn:data($ConsultarCondicionByIdOperacionRequest/ns2:idOperacion)}</ns1:idOperacion>
        <ns1:idCondicion>{fn:data($ConsultarCondicionByIdOperacionRequest/ns2:idCondicion)}</ns1:idCondicion>
    </ns1:ConsultarCondicionByIdOperacionInput>
};

local:func($ConsultarCondicionByIdOperacionRequest)
