xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace m="http://org/bcie/ws/middle/FLEXCUBE.wsdl";
declare namespace typ="http://org/bcie/ws/middle/FLEXCUBE.wsdl/types/";

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $actualizarContrato1Response as element() (:: element(m:actualizarContrato1Response) ::) external;

declare function local:func($actualizarContrato1Response as element() (:: element(m:actualizarContrato1Response) ::)) as element() (:: schema-element(ns2:ActualizarPrestamoFLEXCUBEResponse) ::) {
    <ns2:ActualizarPrestamoFLEXCUBEResponse>
        <ns2:Resultado>
            <res:result>{
            if(fn:data(string($actualizarContrato1Response/codigoResultado_out)) = '0')then 'OK'
            else 'ERROR'}</res:result>
            <res:message>{
            if(fn:data(string($actualizarContrato1Response/codigoResultado_out)) = '0')then 'Actualización exitosa'
            else fn:data($actualizarContrato1Response/mensaje_out)
            }</res:message>
        </ns2:Resultado>
    </ns2:ActualizarPrestamoFLEXCUBEResponse>
};

local:func($actualizarContrato1Response)
