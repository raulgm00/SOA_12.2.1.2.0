xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://www.bice.org/CrearComentarioVotacion";
(:: import schema at "../XSD/CrearComentarioVotacion_JSON.xsd" ::)

declare variable $CrearComentarioVotacionRequest as element() (:: schema-element(ns1:CrearComentarioVotacionRequest) ::) external;
declare variable $comentarioDecoder as xs:string external;

declare function local:func($CrearComentarioVotacionRequest as element()(:: schema-element(ns1:CrearComentarioVotacionRequest) ::),
                            $comentarioDecoder as xs:string)     
                            as element() (:: schema-element(ns2:CrearComentarioVotacionRequest) ::) {
    <ns2:CrearComentarioVotacionRequest>
        <ns2:idUsuario>{fn:data($CrearComentarioVotacionRequest/ns1:idUsuario)}</ns2:idUsuario>
        <ns2:idVotacion>{fn:data($CrearComentarioVotacionRequest/ns1:idVotacion)}</ns2:idVotacion>
        <ns2:nombreUsuario>{fn:data($CrearComentarioVotacionRequest/ns1:nombreUsuario)}</ns2:nombreUsuario>
        <ns2:comentarios>{fn:data($comentarioDecoder)}</ns2:comentarios>
    </ns2:CrearComentarioVotacionRequest>
};

local:func($CrearComentarioVotacionRequest,$comentarioDecoder)
