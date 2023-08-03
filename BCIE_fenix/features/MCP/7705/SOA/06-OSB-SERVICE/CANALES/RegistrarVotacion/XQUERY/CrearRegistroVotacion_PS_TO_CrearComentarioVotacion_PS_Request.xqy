xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearRegistroVotacionBO";
(:: import schema at "../XSD/CrearRegistroVotacion_JSON.xsd" ::)
declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace functx = "http://www.functx.com";

declare variable $CrearRegistroVotacionRequest as element() (:: schema-element(ns1:CrearRegistroVotacionRequest) ::) external;
declare variable $idRegistroVotacion as element() external;
declare variable $comentarioDecoder as xs:string external;

declare function functx:substring-after-if-contains
  ( $arg as xs:string? ,
    $delim as xs:string )  as xs:string? {

   if (contains($arg,$delim))
   then substring-after($arg,$delim)
   else $arg
 };

declare function local:func($CrearRegistroVotacionRequest as element() (:: schema-element(ns1:CrearRegistroVotacionRequest) ::), 
                            $idRegistroVotacion as element(),$comentarioDecoder as xs:string) 
                            as element() (:: schema-element(ns2:CrearComentarioVotacionRequest) ::) {
                            
    <ns2:CrearComentarioVotacionRequest>
        <ns2:idUsuario>{fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:idUsuario)}</ns2:idUsuario>
        <ns2:idVotacion>{fn:data($idRegistroVotacion)}</ns2:idVotacion>
        <ns2:nombreUsuario>{fn:data($CrearRegistroVotacionRequest/ns1:registroVotacion/ns1:nombreUsuario)}</ns2:nombreUsuario>
        <ns2:comentarios>{fn:data($comentarioDecoder)}</ns2:comentarios>
    </ns2:CrearComentarioVotacionRequest>
};

local:func($CrearRegistroVotacionRequest, $idRegistroVotacion,$comentarioDecoder)
