xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarDesembolsosConfiguracionById";
(:: import schema at "../XSD/ConsultarDesembolsosConfiguracionById.xsd" ::)

declare variable $ConsultarDesembolsosConfiguracionById as element() (:: schema-element(ns1:ConsultarDesembolsosConfiguracionByIdRequest) ::) external;

declare function local:func($ConsultarDesembolsosConfiguracionById as element() (:: schema-element(ns1:ConsultarDesembolsosConfiguracionByIdRequest) ::)) as element() (:: schema-element(ns2:ConsultarDesembolsosConfiguracionByIdInput) ::) {
    <ns2:ConsultarDesembolsosConfiguracionByIdInput>
        <ns2:P_ID_DESEMBOLSO>{fn:data($ConsultarDesembolsosConfiguracionById/ns1:idContratoDesembolso)}</ns2:P_ID_DESEMBOLSO>
    </ns2:ConsultarDesembolsosConfiguracionByIdInput>
};

local:func($ConsultarDesembolsosConfiguracionById)