xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarLimiteLineaFinaciera_DB";
(:: import schema at "../XSD/ValidarLimiteLineaFinaciera_DB_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $ValidarLimiteLineaFinacieraRequest as element() (:: schema-element(ns1:ValidarLimiteLineaFinacieraRequest) ::) external;

declare function local:func($ValidarLimiteLineaFinacieraRequest as element() (:: schema-element(ns1:ValidarLimiteLineaFinacieraRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_NUMERO_LINEA_CREDITO>{fn:data($ValidarLimiteLineaFinacieraRequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</ns2:P_NUMERO_LINEA_CREDITO>
        <ns2:P_CODIGO_LINEA_FINANCIERA>{fn:data($ValidarLimiteLineaFinacieraRequest/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)}</ns2:P_CODIGO_LINEA_FINANCIERA>
        <ns2:P_MONTO_APROBADO_USD>{fn:data($ValidarLimiteLineaFinacieraRequest/ns1:LineaCredito/lin:MontoLinea)}</ns2:P_MONTO_APROBADO_USD>
        <ns2:P_SALDO_USD>{sum(fn:data($ValidarLimiteLineaFinacieraRequest/ns1:LineaCredito/lin:Monto[com:tipo/cat:DescripcionCorta='TRANSITO' or com:tipo/cat:DescripcionCorta='SALDO_CARTERA']/com:importe))}</ns2:P_SALDO_USD>
    </ns2:InputParameters>
};

local:func($ValidarLimiteLineaFinacieraRequest)
