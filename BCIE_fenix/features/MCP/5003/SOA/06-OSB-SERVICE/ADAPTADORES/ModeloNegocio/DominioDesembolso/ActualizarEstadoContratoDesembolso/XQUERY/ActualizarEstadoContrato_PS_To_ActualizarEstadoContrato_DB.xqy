xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ActualizarEstadoContratoDesembolso_DB";
(:: import schema at "../XSD/ActualizarEstadoContratoDesembolso_DB_sp.xsd" ::)

declare variable $ActualizarEstadoContratoDesembolsoRequest as element() (:: schema-element(ns1:ActualizarEstadoContratoDesembolsoRequest) ::) external;

declare function local:func($ActualizarEstadoContratoDesembolsoRequest as element() (:: schema-element(ns1:ActualizarEstadoContratoDesembolsoRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:P_ID_TCA_ESTADO>{fn:data($ActualizarEstadoContratoDesembolsoRequest/ns1:idTcaEstado)}</ns2:P_ID_TCA_ESTADO>
        <ns2:P_ID_CONTRATO>{fn:data($ActualizarEstadoContratoDesembolsoRequest/ns1:idContrato)}</ns2:P_ID_CONTRATO>
    </ns2:InputParameters>
};

local:func($ActualizarEstadoContratoDesembolsoRequest)
