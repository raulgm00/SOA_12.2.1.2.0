xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContratoByIdDesembolso";
(:: import schema at "../XSD/ConsultarContratoByIdDesembolso.xsd" ::)

declare variable $ConsultarContratoByIdDesembolsoRequest as element() (:: schema-element(ns1:ConsultarContratoByIdDesembolsoRequest) ::) external;

declare function local:func($ConsultarContratoByIdDesembolsoRequest as element() (:: schema-element(ns1:ConsultarContratoByIdDesembolsoRequest) ::)) as element() (:: schema-element(ns2:ConsultarContratoByIdDesembolsoInput) ::) {
    <ns2:ConsultarContratoByIdDesembolsoInput>
        <ns2:P_ID_DESEMBOLSO>{fn:data($ConsultarContratoByIdDesembolsoRequest/ns1:idDesembolso)}</ns2:P_ID_DESEMBOLSO>
    </ns2:ConsultarContratoByIdDesembolsoInput>
};

local:func($ConsultarContratoByIdDesembolsoRequest)
