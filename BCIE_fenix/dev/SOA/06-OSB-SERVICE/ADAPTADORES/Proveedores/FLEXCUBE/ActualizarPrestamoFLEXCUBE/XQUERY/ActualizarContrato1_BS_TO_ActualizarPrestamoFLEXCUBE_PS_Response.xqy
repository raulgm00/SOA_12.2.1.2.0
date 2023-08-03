xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ActualizarContrato1";
(:: import schema at "../XSD/ActualizarContrato1_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $actualizarContrato1Response as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($actualizarContrato1Response as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ActualizarPrestamoFLEXCUBEResponse) ::) {
    <ns2:ActualizarPrestamoFLEXCUBEResponse>
        <ns2:Resultado>
            <res:result>{
            if(fn:data(string($actualizarContrato1Response/ns1:CODIGO_RESULTADO)) = '0')then 'OK'
            else 'ERROR'}</res:result>
            <res:message>{
            if(fn:data(string($actualizarContrato1Response/ns1:CODIGO_RESULTADO)) = '0')then 'Actualización exitosa'
            else fn:data($actualizarContrato1Response/ns1:MENSAJE)
            }</res:message>
        </ns2:Resultado>
    </ns2:ActualizarPrestamoFLEXCUBEResponse>
};

local:func($actualizarContrato1Response)