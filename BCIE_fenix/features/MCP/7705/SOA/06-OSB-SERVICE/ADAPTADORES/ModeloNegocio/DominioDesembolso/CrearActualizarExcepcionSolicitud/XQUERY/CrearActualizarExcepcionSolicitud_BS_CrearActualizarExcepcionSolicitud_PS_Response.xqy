xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarExcepcionSolicitud_DB";
(:: import schema at "../XSD/CrearActualizarExcepcionSolicitud_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearActualizarExcepcionSolicitudResponse) ::) {
    <ns2:CrearActualizarExcepcionSolicitudResponse>
        <ns2:Resultado>
            <res:result>
            {
            if(fn:data($OutputParameters/ns1:P_CODIGO_RES)=1)then 'OK' else 'ERROR'}</res:result>
            <res:message>{fn:data($OutputParameters/ns1:P_MENSAJE)}</res:message>
        </ns2:Resultado>
    </ns2:CrearActualizarExcepcionSolicitudResponse>
};

local:func($OutputParameters)
