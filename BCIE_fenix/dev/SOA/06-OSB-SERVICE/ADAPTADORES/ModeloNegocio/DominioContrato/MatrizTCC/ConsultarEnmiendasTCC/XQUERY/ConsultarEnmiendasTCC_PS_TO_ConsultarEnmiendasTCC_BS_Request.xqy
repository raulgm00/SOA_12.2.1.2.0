xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEnmiendas";
(:: import schema at "../XSD/ConsultarEnmiendas.xsd" ::)

declare variable $ConsultarEnmiendasTCCRequest as element() (:: schema-element(ns1:ConsultarEnmiendasTCCRequest) ::) external;

declare function local:func($ConsultarEnmiendasTCCRequest as element() (:: schema-element(ns1:ConsultarEnmiendasTCCRequest) ::)) as element() (:: schema-element(ns2:ConsultarEnmiendasInput) ::) {
    <ns2:ConsultarEnmiendasInput>
        <ns2:P_ENMIENDA>{fn:data($ConsultarEnmiendasTCCRequest/ns1:idEnmienda)}</ns2:P_ENMIENDA>
    </ns2:ConsultarEnmiendasInput>
};

local:func($ConsultarEnmiendasTCCRequest)
