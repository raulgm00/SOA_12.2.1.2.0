xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarContratoInstaciaProceso";
(:: import schema at "../XSD/ActualizarContratoInstaciaProceso.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $responseActualizarContrato as element() (:: schema-element(ns1:ActualizarContratoInstaciaProcesoInput) ::) external;

declare function local:func($responseActualizarContrato as element() (:: schema-element(ns1:ActualizarContratoInstaciaProcesoInput) ::)) as element() (:: schema-element(ns2:ActualizarContratoInstanciaProcesoResponse) ::) {
    <ns2:ActualizarContratoInstanciaProcesoResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Actualización Realizada Exitosamente</res:message>
           
        </ns2:Resultado>
    </ns2:ActualizarContratoInstanciaProcesoResponse>
};

local:func($responseActualizarContrato)
