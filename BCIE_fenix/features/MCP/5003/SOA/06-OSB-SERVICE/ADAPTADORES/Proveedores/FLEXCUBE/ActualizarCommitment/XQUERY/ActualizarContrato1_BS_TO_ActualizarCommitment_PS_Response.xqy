xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ActualizarContrato1";
(:: import schema at "../../ActualizarPrestamoFLEXCUBE/XSD/ActualizarContrato1_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $actualizarContratoResponse as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($actualizarContratoResponse as element() (:: schema-element(ns2:OutputParameters) ::)) as element() (:: schema-element(ns1:ActualizarCommitmentResponse) ::) {
    <ns1:ActualizarCommitmentResponse>
        <ns1:Resultado>
            <res:result>
            {if (fn:data($actualizarContratoResponse/ns2:CODIGO_RESULTADO) = 0)
            then 'OK'
            else 'ERROR'}
            </res:result>
            <res:message>{fn:data($actualizarContratoResponse/ns2:MENSAJE)}</res:message>
        </ns1:Resultado>
    </ns1:ActualizarCommitmentResponse>
};

local:func($actualizarContratoResponse)