xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/FinalizarOperacionProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/FinalizarOperacionProceso/V1/Schema/FinalizarOperacionProcesoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";


declare function local:func() as element() (:: schema-element(ns1:responseFinalizarOperacionProcesoMessage) ::) {
    <ns1:responseFinalizarOperacionProcesoMessage>
        <ns1:Result>
            <res:result>OK</res:result>
            <res:message>Finalización del proceso exitosa</res:message>
        </ns1:Result>
    </ns1:responseFinalizarOperacionProcesoMessage>
};

local:func()
