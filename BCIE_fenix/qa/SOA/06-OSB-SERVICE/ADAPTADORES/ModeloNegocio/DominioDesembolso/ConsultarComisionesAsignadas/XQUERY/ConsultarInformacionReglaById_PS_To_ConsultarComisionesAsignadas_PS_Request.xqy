xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarComisionesAsignadas";
(:: import schema at "../XSD/ConsultarComisionesAsignadas.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $ConsultarInformacionReglasByIdResponse as element() (:: schema-element(ns1:ConsultarInformacionReglasByIdResponse) ::) external;

declare function local:func($ConsultarInformacionReglasByIdResponse as element() (:: schema-element(ns1:ConsultarInformacionReglasByIdResponse) ::)) as element() (:: schema-element(ns2:ConsultarComisionesAsignadasInput) ::) {
    <ns2:ConsultarComisionesAsignadasInput>
        <ns2:ID_OPERACION>{fn:data($ConsultarInformacionReglasByIdResponse/ns1:Operacion/ope:idOperacion)}</ns2:ID_OPERACION>
    </ns2:ConsultarComisionesAsignadasInput>
};

local:func($ConsultarInformacionReglasByIdResponse)
