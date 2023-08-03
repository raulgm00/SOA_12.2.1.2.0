xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare variable $VerificarValidacionAsignacionRequest as element() (:: schema-element(ns1:VerificarValidacionAsignacionRequest) ::) external;

declare function local:func($VerificarValidacionAsignacionRequest as element() (:: schema-element(ns1:VerificarValidacionAsignacionRequest) ::)) as element() (:: schema-element(ns2:ConfiguracionValidacionAsignacionRequest) ::) {
    <ns2:ConfiguracionValidacionAsignacionRequest>
        <ns2:idSolicitud>{fn:data($VerificarValidacionAsignacionRequest/ns1:idSolicitud)}</ns2:idSolicitud>
        <ns2:idOperacion>{fn:data($VerificarValidacionAsignacionRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:instanciaProceso>0</ns2:instanciaProceso>
    </ns2:ConfiguracionValidacionAsignacionRequest>
};

local:func($VerificarValidacionAsignacionRequest)
