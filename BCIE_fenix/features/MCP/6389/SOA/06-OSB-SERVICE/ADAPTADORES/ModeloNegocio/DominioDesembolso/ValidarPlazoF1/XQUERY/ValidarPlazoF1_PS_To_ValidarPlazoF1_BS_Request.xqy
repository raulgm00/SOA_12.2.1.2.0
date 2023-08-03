xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarPlazoF1_DB";
(:: import schema at "../XSD/ValidarPlazoF1_DB_sp.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ValidarPlazoF1Request as element() (:: schema-element(ns1:ValidarPlazoF1Request) ::) external;

declare function local:func($ValidarPlazoF1Request as element() (:: schema-element(ns1:ValidarPlazoF1Request) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_NUMERO_LINEA_CREDITO>{fn:data($ValidarPlazoF1Request/ns1:LineaCredito/lin:NumeroLineaCredito)}</ns2:P_NUMERO_LINEA_CREDITO>
        <ns2:P_CODIGO_LINEA_FINANCIERA>{fn:data($ValidarPlazoF1Request/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)}</ns2:P_CODIGO_LINEA_FINANCIERA>
    </ns2:InputParameters>
};

local:func($ValidarPlazoF1Request)
