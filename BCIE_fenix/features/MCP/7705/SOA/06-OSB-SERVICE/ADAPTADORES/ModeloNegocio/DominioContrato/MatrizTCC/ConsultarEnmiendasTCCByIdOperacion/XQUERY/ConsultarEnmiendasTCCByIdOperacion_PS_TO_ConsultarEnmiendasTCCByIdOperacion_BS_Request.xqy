xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarEnmiendasTCCByIdOperacion";
(:: import schema at "../XSD/ConsultarEnmiendasTCCByIdOperacion_table.xsd" ::)

declare variable $ConsultarEnmiendasTCCByIdOperacionRequest as element() (:: schema-element(ns1:ConsultarEnmiendasTCCByIdOperacionRequest) ::) external;

declare function local:func($ConsultarEnmiendasTCCByIdOperacionRequest as element() (:: schema-element(ns1:ConsultarEnmiendasTCCByIdOperacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarEnmiendasTCCByIdOperacionSelect_idOperacionInputParameters) ::) {
    <ns2:ConsultarEnmiendasTCCByIdOperacionSelect_idOperacionInputParameters>
        <ns2:idOperacion>{fn:data($ConsultarEnmiendasTCCByIdOperacionRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarEnmiendasTCCByIdOperacionSelect_idOperacionInputParameters>
};

local:func($ConsultarEnmiendasTCCByIdOperacionRequest)