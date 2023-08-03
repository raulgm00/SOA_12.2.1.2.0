xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTreComision_DB";
(:: import schema at "../XSD/ConsultarTreComision_DB.xsd" ::)

declare variable $ConsultarTreComisionRequest as element() (:: schema-element(ns1:ConsultarTreComisionRequest) ::) external;

declare function local:func($ConsultarTreComisionRequest as element() (:: schema-element(ns1:ConsultarTreComisionRequest) ::)) as element() (:: schema-element(ns2:ConsultarTreComision_DBInput) ::) {
    <ns2:ConsultarTreComision_DBInput>
        <ns2:idOperacion>{fn:data($ConsultarTreComisionRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:descripcionCorta>{fn:data($ConsultarTreComisionRequest/ns1:descripcion)}</ns2:descripcionCorta>
    </ns2:ConsultarTreComision_DBInput>
};

local:func($ConsultarTreComisionRequest)
