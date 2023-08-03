xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarAprobacionByOperacion";
(:: import schema at "../../../../DominioOperacion/Aprobacion/ConsultarAprobacionByOperacion/XSD/ConsultarAprobacionByOperacion.xsd" ::)

declare variable $ConsultarLineaCreditoRequest as element() (:: schema-element(ns1:ConsultarLineaCreditoRequest) ::) external;

declare function local:func($ConsultarLineaCreditoRequest as element() (:: schema-element(ns1:ConsultarLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:ConsultarAprobacionByOperacionInput) ::) {
    <ns2:ConsultarAprobacionByOperacionInput>
        <ns2:P_INSTANCIA_PROCESO>{fn:data($ConsultarLineaCreditoRequest/ns1:instanciaProceso)}</ns2:P_INSTANCIA_PROCESO>
        <ns2:P_OPERACION>{fn:data($ConsultarLineaCreditoRequest/ns1:idOperacion)}</ns2:P_OPERACION>
    </ns2:ConsultarAprobacionByOperacionInput>
};

local:func($ConsultarLineaCreditoRequest)
