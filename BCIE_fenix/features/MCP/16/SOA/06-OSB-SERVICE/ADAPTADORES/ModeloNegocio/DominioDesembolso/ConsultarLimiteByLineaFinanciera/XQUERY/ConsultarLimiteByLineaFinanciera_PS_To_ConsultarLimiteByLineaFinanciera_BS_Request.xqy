xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLimiteByLineaFinanciera_DB";
(:: import schema at "../XSD/ConsultarLimiteByLineaFinanciera_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarLimiteByLineaFinancieraRequest as element() (:: schema-element(ns1:ConsultarLimiteByLineaFinancieraRequest) ::) external;

declare function local:func($ConsultarLimiteByLineaFinancieraRequest as element() (:: schema-element(ns1:ConsultarLimiteByLineaFinancieraRequest) ::)) as element() (:: schema-element(ns2:ConsultarLimiteByLineaFinanciera_DBInput) ::) {
    <ns2:ConsultarLimiteByLineaFinanciera_DBInput>
        <ns2:CODIGO_EXTERNO>{fn:data($ConsultarLimiteByLineaFinancieraRequest/ns1:Programa/her:LineaFinanciera/cat:codigoExterno)}</ns2:CODIGO_EXTERNO></ns2:ConsultarLimiteByLineaFinanciera_DBInput>
};

local:func($ConsultarLimiteByLineaFinancieraRequest)
