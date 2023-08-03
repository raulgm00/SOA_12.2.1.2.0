xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarCategoriaCondicionByIdCondicion_BS";
(:: import schema at "../XSD/EliminarCategoriaCondicionByIdCondicion_BS.xsd" ::)

declare variable $EliminarCategoriaCondicionByIdCondicionRequest as element() (:: schema-element(ns1:EliminarCategoriaCondicionByIdCondicionRequest) ::) external;

declare function local:func($EliminarCategoriaCondicionByIdCondicionRequest as element() (:: schema-element(ns1:EliminarCategoriaCondicionByIdCondicionRequest) ::)) as element() (:: schema-element(ns2:EliminarCategoriaCondicionByIdCondicion_BSInput) ::) {
    <ns2:EliminarCategoriaCondicionByIdCondicion_BSInput>
        <ns2:idCondicion>{fn:data($EliminarCategoriaCondicionByIdCondicionRequest/ns1:idCondicion)}</ns2:idCondicion>
    </ns2:EliminarCategoriaCondicionByIdCondicion_BSInput>
};

local:func($EliminarCategoriaCondicionByIdCondicionRequest)
