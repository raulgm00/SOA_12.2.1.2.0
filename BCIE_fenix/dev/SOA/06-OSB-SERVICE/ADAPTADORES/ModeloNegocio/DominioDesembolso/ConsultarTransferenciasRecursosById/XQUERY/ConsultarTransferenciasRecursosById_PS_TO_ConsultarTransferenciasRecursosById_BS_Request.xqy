xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTransferenciasRecursosById_DB";
(:: import schema at "../XSD/ConsultarTransferenciasRecursosById_DB.xsd" ::)

declare variable $ConsultarTransferenciasRecursosByIdRequest as element() (:: schema-element(ns1:ConsultarTransferenciasRecursosByIdRequest) ::) external;

declare function local:func($ConsultarTransferenciasRecursosByIdRequest as element() (:: schema-element(ns1:ConsultarTransferenciasRecursosByIdRequest) ::)) as element() (:: schema-element(ns2:ConsultarTransferenciasRecursosById_DBInput) ::) {
    <ns2:ConsultarTransferenciasRecursosById_DBInput>
        <ns2:idDesembolso>{fn:data($ConsultarTransferenciasRecursosByIdRequest/ns1:idDesembolso)}</ns2:idDesembolso>
        <ns2:idTransferencia>{fn:data($ConsultarTransferenciasRecursosByIdRequest/ns1:idTransferencia)}</ns2:idTransferencia>
    </ns2:ConsultarTransferenciasRecursosById_DBInput>
};

local:func($ConsultarTransferenciasRecursosByIdRequest)
