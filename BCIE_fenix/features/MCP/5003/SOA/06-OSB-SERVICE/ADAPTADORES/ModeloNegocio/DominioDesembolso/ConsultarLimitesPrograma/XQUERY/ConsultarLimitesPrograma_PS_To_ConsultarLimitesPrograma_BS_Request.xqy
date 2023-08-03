xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLimitesPrograma_DB";
(:: import schema at "../XSD/ConsultarLimitesPrograma_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarLimitesProgramaRequest as element() (:: schema-element(ns1:ConsultarLimitesProgramaRequest) ::) external;

declare function local:func($ConsultarLimitesProgramaRequest as element() (:: schema-element(ns1:ConsultarLimitesProgramaRequest) ::)) as element() (:: schema-element(ns2:ConsultarLimitesPrograma_DBInput) ::) {
    <ns2:ConsultarLimitesPrograma_DBInput>
        <ns2:CODIGO_LINEA_FINANCIERA>{fn:data($ConsultarLimitesProgramaRequest/ns1:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)}</ns2:CODIGO_LINEA_FINANCIERA>
    </ns2:ConsultarLimitesPrograma_DBInput>
};

local:func($ConsultarLimitesProgramaRequest)
