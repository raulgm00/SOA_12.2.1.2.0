xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarContratoInstaciaProceso";
(:: import schema at "../XSD/ActualizarContratoInstaciaProceso.xsd" ::)

declare variable $ActualizarRequestContrato as element() (:: schema-element(ns1:ActualizarContratoInstanciaProcesoRequest) ::) external;

declare function local:func($ActualizarRequestContrato as element() (:: schema-element(ns1:ActualizarContratoInstanciaProcesoRequest) ::)) as element() (:: schema-element(ns2:ActualizarContratoInstaciaProcesoInput) ::) {
    <ns2:ActualizarContratoInstaciaProcesoInput>
        <ns2:P_INSTANCIA_PROCESO>{fn:data($ActualizarRequestContrato/ns1:instanciaProceso)}</ns2:P_INSTANCIA_PROCESO>
        <ns2:P_ID_OPERACION>{fn:data($ActualizarRequestContrato/ns1:idOperacion)}</ns2:P_ID_OPERACION>
    </ns2:ActualizarContratoInstaciaProcesoInput>
};

local:func($ActualizarRequestContrato)
