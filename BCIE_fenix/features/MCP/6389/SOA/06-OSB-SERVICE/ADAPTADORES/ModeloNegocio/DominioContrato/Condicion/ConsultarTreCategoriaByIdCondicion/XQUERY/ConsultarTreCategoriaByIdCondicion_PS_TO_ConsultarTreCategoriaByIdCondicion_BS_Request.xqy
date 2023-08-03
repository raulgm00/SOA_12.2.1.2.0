xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCategoriaByCondicion";
(:: import schema at "../XSD/ConsultarTreCategoriaByIdCondicion.xsd" ::)

declare variable $ConsultarTreCategoriaCondicionRequest as element() (:: schema-element(ns1:ConsultarTreCategoriaByIdCondicionRequest) ::) external;

declare function local:func($ConsultarTreCategoriaCondicionRequest as element() (:: schema-element(ns1:ConsultarTreCategoriaByIdCondicionRequest) ::)) as element() (:: schema-element(ns2:ConsultarCategoriaByCondicionInput) ::) {
    <ns2:ConsultarCategoriaByCondicionInput>
        <ns2:P_CONDICION>{fn:data($ConsultarTreCategoriaCondicionRequest/ns1:idCondicion)}</ns2:P_CONDICION>
    </ns2:ConsultarCategoriaByCondicionInput>
};

local:func($ConsultarTreCategoriaCondicionRequest)
