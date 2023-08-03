xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarSolicitudDesembolso_DB";
(:: import schema at "../XSD/CrearActualizarSolicitudDesembolso_DB_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $SolicitudDesembolsoCollection as element() (:: schema-element(ns1:SolicitudDesembolsoCollection) ::) external;

declare function local:func($SolicitudDesembolsoCollection as element() (:: schema-element(ns1:SolicitudDesembolsoCollection) ::)) as element() (:: schema-element(ns2:CrearActualizarSolicitudDesembolsoResponse) ::) {
    <ns2:CrearActualizarSolicitudDesembolsoResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{concat("Id ", fn:data($SolicitudDesembolsoCollection/ns1:SolicitudDesembolso/ns1:id)," insertado o actualizado correctamente")}</res:message>
        </ns2:Resultado>
    </ns2:CrearActualizarSolicitudDesembolsoResponse>
};

local:func($SolicitudDesembolsoCollection)
