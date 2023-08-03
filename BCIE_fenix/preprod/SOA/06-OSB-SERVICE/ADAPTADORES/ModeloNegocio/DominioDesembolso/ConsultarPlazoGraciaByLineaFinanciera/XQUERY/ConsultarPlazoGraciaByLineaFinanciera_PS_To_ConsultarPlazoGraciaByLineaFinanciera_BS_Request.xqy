xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarPlazoGraciaByLineaFinanciera_DB";
(:: import schema at "../XSD/ConsultarPlazoGraciaByLineaFinanciera_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarPlazoGraciaByLineaFinancieraRequest as element() (:: schema-element(ns1:ConsultarPlazoGraciaByLineaFinancieraRequest) ::) external;

declare function local:func($ConsultarPlazoGraciaByLineaFinancieraRequest as element() (:: schema-element(ns1:ConsultarPlazoGraciaByLineaFinancieraRequest) ::)) as element() (:: schema-element(ns2:ConsultarPlazoGraciaByLineaFinanciera_DBInput) ::) {
    <ns2:ConsultarPlazoGraciaByLineaFinanciera_DBInput>
        <ns2:CODIGO_LINEA_FINANCIERA>{fn:data($ConsultarPlazoGraciaByLineaFinancieraRequest/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)}</ns2:CODIGO_LINEA_FINANCIERA>
    </ns2:ConsultarPlazoGraciaByLineaFinanciera_DBInput>
};

local:func($ConsultarPlazoGraciaByLineaFinancieraRequest)
