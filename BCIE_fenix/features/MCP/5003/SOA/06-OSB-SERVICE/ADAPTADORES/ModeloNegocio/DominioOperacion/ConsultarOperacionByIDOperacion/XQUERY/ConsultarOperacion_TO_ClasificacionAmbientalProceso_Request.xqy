xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarClasificacionAmbiental_BS";
(:: import schema at "../../ConsultarClasificacionAmbiental/XSD/ConsultarClasificacionAmbiental_BS.xsd" ::)

declare variable $RequestConsultarClasificacionAmbiental as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionRequest) ::) external;

declare function local:func($RequestConsultarClasificacionAmbiental as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarClasificacionAmbiental_BSInput) ::) {
    <ns2:ConsultarClasificacionAmbiental_BSInput>
        <ns2:idOperacion>{fn:data($RequestConsultarClasificacionAmbiental/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarClasificacionAmbiental_BSInput>
};

local:func($RequestConsultarClasificacionAmbiental)