xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarRolesPorProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarRolesPorProceso/V1/Schema/ConsultarRolesPorProcesoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarRolesPorProceso";
(:: import schema at "../XSD/ConsultarRolesPorProceso.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarRolesPorProcesoBO";

declare variable $requestConsultarRolesPorProcesoMessage as element() (:: schema-element(ns1:requestConsultarRolesPorProcesoMessage) ::) external;

declare function local:requestConsultarRolesPorProcesoMessage($requestConsultarRolesPorProcesoMessage as element() (:: schema-element(ns1:requestConsultarRolesPorProcesoMessage) ::)) as element() (:: schema-element(ns2:ConsultarRolesPorProcesoInput) ::) {
    <ns2:ConsultarRolesPorProcesoInput>
        <ns2:idProceso>{fn:data($requestConsultarRolesPorProcesoMessage/ns1:Proceso/con:idProceso)}</ns2:idProceso>
           </ns2:ConsultarRolesPorProcesoInput>
};

local:requestConsultarRolesPorProcesoMessage($requestConsultarRolesPorProcesoMessage)
