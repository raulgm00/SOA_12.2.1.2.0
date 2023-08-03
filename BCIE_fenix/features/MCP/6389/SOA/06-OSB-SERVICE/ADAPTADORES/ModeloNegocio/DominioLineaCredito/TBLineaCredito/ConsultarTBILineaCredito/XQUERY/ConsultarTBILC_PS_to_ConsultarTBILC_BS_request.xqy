xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTBLineaCredito";
(:: import schema at "../XSD/TBILineaCredito.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO"; 

declare variable $requestBS as element() (:: schema-element(ns1:TBILineaCreditoConsultarRequest) ::) external;

declare function local:func($requestBS as element() (:: schema-element(ns1:TBILineaCreditoConsultarRequest) ::)) as element() (:: schema-element(ns2:ConsultarTBLineaCreditoRequest) ::) {
    <ns2:ConsultarTBLineaCreditoRequest>
        <ns2:INSTANCIA_PROCESO>{fn:data($requestBS/ns1:TbiLineaCredito/lin:instanciaProceso)}</ns2:INSTANCIA_PROCESO>
        <ns2:ID_CONTRATO>{fn:data($requestBS/ns1:TbiLineaCredito/lin:idContrato)}</ns2:ID_CONTRATO>
        <ns2:NUMERO_LINEA_CREDITO>{fn:data($requestBS/ns1:TbiLineaCredito/lin:numeroLineaCredito)}</ns2:NUMERO_LINEA_CREDITO>
        <ns2:ID_LINEA_CREDITO>{fn:data($requestBS/ns1:TbiLineaCredito/lin:idLineaCredito)}</ns2:ID_LINEA_CREDITO> 
    </ns2:ConsultarTBLineaCreditoRequest>
};

local:func($requestBS)
