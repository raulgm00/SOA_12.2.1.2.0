xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V2/Schema/DesembolsoMOV2.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:ActualizarComponenteResponse) ::) {
    <ns1:ActualizarComponenteResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Actualizacion Realizada Correctamente</res:message>
        </ns1:Resultado>
    </ns1:ActualizarComponenteResponse>
};

local:func()
