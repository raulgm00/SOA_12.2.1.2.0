xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearRegistroVotacion";
(:: import schema at "../XSD/CrearRegistroVotacion_table.xsd" ::)


declare variable $CrearRegistroVotacionRequest as element() (:: schema-element(ns1:CrearRegistroVotacionRequest) ::) external;

declare function local:func($CrearRegistroVotacionRequest as element() (:: schema-element(ns1:CrearRegistroVotacionRequest) ::)) as element() (:: schema-element(ns2:VotoAprobacionCollection) ::) {
    <ns2:CrearRegistroVotacionRequest>
        <ns2:VotoAprobacion>
            <ns2:id></ns2:id>
            {
                if ($CrearRegistroVotacionRequest/ns1:idTipoDecision)
                then <ns2:idTcaTipoDecision>{fn:data($CrearRegistroVotacionRequest/ns1:idTipoDecision)}</ns2:idTcaTipoDecision>
                else ()
            }
            {
                if ($CrearRegistroVotacionRequest/ns1:idUsuario)
                then <ns2:idUsuarioReunion>{fn:data($CrearRegistroVotacionRequest/ns1:idUsuario)}</ns2:idUsuarioReunion>
                else ()
            }
            <ns2:fechaHora>{fn:current-dateTime()}</ns2:fechaHora>
            {
                if ($CrearRegistroVotacionRequest/ns1:loginUsuario)
                then <ns2:loginUsuarioEmite>{fn:data($CrearRegistroVotacionRequest/ns1:loginUsuario)}</ns2:loginUsuarioEmite>
                else ()
            }
            <ns2:banEstatus>1</ns2:banEstatus>
            <ns2:esFueraDeSistema>0</ns2:esFueraDeSistema>
        </ns2:VotoAprobacion>
    </ns2:CrearRegistroVotacionRequest>
};

local:func($CrearRegistroVotacionRequest)
