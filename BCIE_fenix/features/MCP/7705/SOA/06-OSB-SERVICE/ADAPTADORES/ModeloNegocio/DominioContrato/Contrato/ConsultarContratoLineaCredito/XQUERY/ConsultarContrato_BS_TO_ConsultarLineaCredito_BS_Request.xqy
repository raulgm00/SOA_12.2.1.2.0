xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContrato";
(:: import schema at "../XSD/ConsultarContrato.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/CosultarLineaCredito";
(:: import schema at "../../../../DominioLineaCredito/ConsultarLineaCreditoByContrato/XSD/CosultarLineaCredito.xsd" ::)

declare variable $requestContrato as element() (:: schema-element(ns1:ConsultarContratoOutputCollection) ::) external;
declare variable $index as xs:int external;

declare function local:func($requestContrato as element() (:: schema-element(ns1:ConsultarContratoOutputCollection) ::),
                            $index as xs:int) as element() (:: schema-element(ns2:CosultarLineaCreditoInput) ::) {
    <ns2:CosultarLineaCreditoInput>
        <ns2:P_ID_CONTRATO>{fn:data($requestContrato/ns1:ConsultarContratoOutput[$index]/ns1:ID)}</ns2:P_ID_CONTRATO>
    </ns2:CosultarLineaCreditoInput>
};

local:func($requestContrato, $index)