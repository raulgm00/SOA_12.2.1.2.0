xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/FENIX/PKG_ENVIAR_CORREO/P_CONSTRUIR_CORREO/";
(:: import schema at "../XSD/ConstruirCorreoElectronico_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConstruirCorreoElectronicoResponse) ::) {
    <ns2:ConstruirCorreoElectronicoResponse>
        <ns2:CorreoElectronico>
            <cor:to>
                <cor:usuario>{fn:data($OutputParameters/ns1:P_DESTINATARIOS)}</cor:usuario>
            </cor:to>
            <cor:cc>
                <cor:usuario>{fn:data($OutputParameters/ns1:P_CC)}</cor:usuario>
            </cor:cc>
            <cor:subject>{fn:data($OutputParameters/ns1:P_ASUNTO)}</cor:subject>
            <cor:mensaje>{fn:data($OutputParameters/ns1:P_MENSAJE)}</cor:mensaje>
        </ns2:CorreoElectronico>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>El correo se construyo exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ConstruirCorreoElectronicoResponse>
};

local:func($OutputParameters)
