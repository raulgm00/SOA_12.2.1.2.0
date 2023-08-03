xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare function local:func() {
    <ns1:enviarCorreoElectronicoResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>EL Correo se enviara</res:message>
        </ns1:Resultado>
    </ns1:enviarCorreoElectronicoResponse>
};

local:func()
