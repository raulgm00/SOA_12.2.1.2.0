xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearBitacoraCliente/V1/Schema/CrearBitacoraClienteMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $idBitacoraCliente as xs:string external;

declare function local:func($idBitacoraCliente as xs:string) as element() (:: schema-element(ns1:CrearBitacoraClienteResponse) ::) {
    <ns1:CrearBitacoraClienteResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:concat('La inserción se ha realizado correctamente, ID:', xs:string($idBitacoraCliente))}</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:CrearBitacoraClienteResponse>
};

local:func($idBitacoraCliente)
