xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ActivarOperacionProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ActivarOperacionProceso/V1/Schema/ActivarOperacionProcesoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ActivarOperacionProcesoRequest as element() (:: schema-element(ns1:ActivarOperacionProcesoRequest) ::) external;

declare function local:func($ActivarOperacionProcesoRequest as element() (:: schema-element(ns1:ActivarOperacionProcesoRequest) ::)) as element() (:: schema-element(ns1:ActivarOperacionProcesoResponse) ::) {
    <ns1:ActivarOperacionProcesoResponse>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Los registros se actualizaron correctamente</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:ActivarOperacionProcesoResponse>
};

local:func($ActivarOperacionProcesoRequest)
