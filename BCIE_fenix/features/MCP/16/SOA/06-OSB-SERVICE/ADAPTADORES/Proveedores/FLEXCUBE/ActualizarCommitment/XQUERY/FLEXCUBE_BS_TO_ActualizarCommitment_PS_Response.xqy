xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace flex="http://org/bcie/ws/middle/FLEXCUBE.wsdl";

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $actualizarContratoResponse as element() (:: element(flex:actualizarContratoResponse) ::) external;

declare function local:func($actualizarContratoResponse as element() (:: element( flex:actualizarContratoResponse) ::)) as element() (:: schema-element(ns1:ActualizarCommitmentResponse) ::) {
    <ns1:ActualizarCommitmentResponse>
        <ns1:Resultado>
            <res:result>
            {if (fn:data($actualizarContratoResponse/codigoResultado_out) = 0)
            then 'OK'
            else 'ERROR'}
            </res:result>
            <res:message>{fn:data($actualizarContratoResponse/mensaje_out)}</res:message>
        </ns1:Resultado>
    </ns1:ActualizarCommitmentResponse>
};

local:func($actualizarContratoResponse)