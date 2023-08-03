xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:responseCrearBitacoraProcesoMessage) ::) {
    <ns1:responseCrearBitacoraProcesoMessage>
        <ns1:Result>
            <res:result>OK</res:result>
            <res:message>Inserción realizada</res:message>
        </ns1:Result>
    </ns1:responseCrearBitacoraProcesoMessage>
};

local:func()