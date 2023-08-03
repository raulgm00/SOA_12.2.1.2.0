xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerPlanAmortizacion";
(:: import schema at "../XSD/ObtenerPlanAmortizacion_sp.xsd" ::)

declare variable $GeneraReportePlanAmortizacionRequest as element() (:: schema-element(ns1:GeneraReportePlanAmortizacionRequest) ::) external;

declare function local:func($GeneraReportePlanAmortizacionRequest as element() (:: schema-element(ns1:GeneraReportePlanAmortizacionRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_DESEMBOLSO>{fn:data($GeneraReportePlanAmortizacionRequest/ns1:idDesembolso)}</ns2:P_ID_DESEMBOLSO>
        {
            if ($GeneraReportePlanAmortizacionRequest/ns1:idFacturador)
            then <ns2:P_CONTRACT_REF_NO>{fn:data($GeneraReportePlanAmortizacionRequest/ns1:idFacturador)}</ns2:P_CONTRACT_REF_NO>
            else ()
        }
    </ns2:InputParameters>
};

local:func($GeneraReportePlanAmortizacionRequest)
