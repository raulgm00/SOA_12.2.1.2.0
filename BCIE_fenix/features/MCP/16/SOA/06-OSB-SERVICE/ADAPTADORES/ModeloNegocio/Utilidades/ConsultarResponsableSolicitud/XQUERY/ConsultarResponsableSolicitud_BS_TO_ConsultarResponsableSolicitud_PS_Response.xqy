xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ResponsableSolicitudMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarResponsableSolicitud/V1/Schema/ResponsableSolicitudMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarResponsableSolicitud_BD";
(:: import schema at "../XSD/ConsultarResponsableSolicitud_BD_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $EquipoTrabajoCollection as element() (:: schema-element(ns1:EquipoTrabajoCollection) ::) external;

declare function local:func($EquipoTrabajoCollection as element() (:: schema-element(ns1:EquipoTrabajoCollection) ::)) as element() (:: schema-element(ns2:ResponsableSolicitudResponse) ::) {
    <ns2:ResponsableSolicitudResponse>
    <ns2:Usuario>{fn:data($EquipoTrabajoCollection/ns1:EquipoTrabajo/ns1:usuario)}</ns2:Usuario>
    <ns2:Resultado>
            <res:result>OK</res:result>
    {
    if (fn:count($EquipoTrabajoCollection/ns1:EquipoTrabajo[1])>0)
        then
        <res:message>Consulta realizada exitosamente</res:message>
        else
            <res:message>No existen registros</res:message>
      
        } 
      </ns2:Resultado>
    </ns2:ResponsableSolicitudResponse>

};

local:func($EquipoTrabajoCollection)