xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarEventoCondicionByidCondicion_BS";
(:: import schema at "../XSD/EliminarEventoCondicionByidCondicion_BS.xsd" ::)

declare variable $EliminarEventoCondicionByIdCondicionRequest as element() (:: schema-element(ns1:EliminarEventoCondicionByIdCondicionRequest) ::) external;

declare function local:func($EliminarEventoCondicionByIdCondicionRequest as element() (:: schema-element(ns1:EliminarEventoCondicionByIdCondicionRequest) ::)) as element() (:: schema-element(ns2:EliminarEventoCondicionByidCondicion_BSInput) ::) {
    <ns2:EliminarEventoCondicionByidCondicion_BSInput>
        <ns2:idCondicion>{fn:data($EliminarEventoCondicionByIdCondicionRequest/ns1:idCondicion)}</ns2:idCondicion>
    </ns2:EliminarEventoCondicionByidCondicion_BSInput>
};

local:func($EliminarEventoCondicionByIdCondicionRequest)
