xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearComentarioVotacion";
(:: import schema at "../XSD/CrearComentarioVotacion_table.xsd" ::)

declare variable $CrearComentarioVotacionRequest as element() (:: schema-element(ns1:CrearComentarioVotacionRequest) ::) external;

declare function local:func($CrearComentarioVotacionRequest as element() (:: schema-element(ns1:CrearComentarioVotacionRequest) ::)) as element() (:: schema-element(ns2:ComentarioReunionCollection) ::) {
    <ns2:ComentarioReunionCollection>
        <ns2:ComentarioReunion>
            <ns2:id></ns2:id>
            {
                if ($CrearComentarioVotacionRequest/ns1:idUsuario)
                then <ns2:idUsuarioReunion>{fn:data($CrearComentarioVotacionRequest/ns1:idUsuario)}</ns2:idUsuarioReunion>
                else ()
            }
            {
                if ($CrearComentarioVotacionRequest/ns1:idVotacion)
                then <ns2:idVotoAprobacion>{fn:data($CrearComentarioVotacionRequest/ns1:idVotacion)}</ns2:idVotoAprobacion>
                else ()
            }            
            <ns2:fechaHora>{fn:current-dateTime()}</ns2:fechaHora>
            {
                if ($CrearComentarioVotacionRequest/ns1:comentarios)
                then <ns2:comentario>{fn:data($CrearComentarioVotacionRequest/ns1:comentarios)}</ns2:comentario>
                else ()
            }
            {
                if ($CrearComentarioVotacionRequest/ns1:nombreUsuario)
                then <ns2:nombreUsuario>{fn:data($CrearComentarioVotacionRequest/ns1:nombreUsuario)}</ns2:nombreUsuario>
                else ()
            }
            <ns2:banEstatus>1</ns2:banEstatus>
        </ns2:ComentarioReunion>
    </ns2:ComentarioReunionCollection>
};

local:func($CrearComentarioVotacionRequest)
