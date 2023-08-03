xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare variable $request as element() (:: schema-element(ns1:VerificarValidacionAsignacionRequest) ::) external;

declare function local:func($request as element() (:: schema-element(ns1:VerificarValidacionAsignacionRequest) ::)) as element() (:: schema-element(ns1:ConsultarSolicitudDesembolsoRequest) ::) {
    <ns1:ConsultarSolicitudDesembolsoRequest>
        <ns1:idSolicitud>{fn:data($request/ns1:idSolicitud)}</ns1:idSolicitud>
    </ns1:ConsultarSolicitudDesembolsoRequest>
};

local:func($request)
