xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContrato";
(:: import schema at "../XSD/ConsultarContrato.xsd" ::)

declare variable $ConsultarContratroRequet as element() (:: schema-element(ns1:ConsultarLineaCreditoRequest) ::) external;

declare function local:func($ConsultarContratroRequet as element() (:: schema-element(ns1:ConsultarLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:ConsultarContratoInput) ::) {
    <ns2:ConsultarContratoInput>
        <ns2:P_ID_OPERACION>{fn:data($ConsultarContratroRequet/ns1:idOperacion)}</ns2:P_ID_OPERACION>
        <ns2:P_INSTANCIA_PROCESO>{fn:data($ConsultarContratroRequet/ns1:instanciaProceso)}</ns2:P_INSTANCIA_PROCESO>
    </ns2:ConsultarContratoInput>
};

local:func($ConsultarContratroRequet)
