xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarPlazoComponente_DB";
(:: import schema at "../XSD/ConsultarPlazoComponente_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarPlazoComponenteRequest as element() (:: schema-element(ns1:ConsultarPlazoComponenteRequest) ::) external;

declare function local:func($ConsultarPlazoComponenteRequest as element() (:: schema-element(ns1:ConsultarPlazoComponenteRequest) ::)) as element() (:: schema-element(ns2:ConsultarPlazoComponente_DBInput) ::) {
    <ns2:ConsultarPlazoComponente_DBInput>
        <ns2:CODIGO_LINEA_FINANCIERA>{fn:data($ConsultarPlazoComponenteRequest/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)}</ns2:CODIGO_LINEA_FINANCIERA>
    </ns2:ConsultarPlazoComponente_DBInput>
};

local:func($ConsultarPlazoComponenteRequest)
