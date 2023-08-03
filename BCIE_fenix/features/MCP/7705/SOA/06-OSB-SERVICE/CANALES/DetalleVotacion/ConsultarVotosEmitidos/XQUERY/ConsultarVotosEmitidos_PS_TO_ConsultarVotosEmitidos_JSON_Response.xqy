xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarVotosEmitidos";
(:: import schema at "../XSD/ConsultarVotosEmitidos_JSON.xsd" ::)
declare namespace ns1="http://www.bcie.org/ConsultarVotosEmitidosMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarVotosEmitidos/V1/Schema/ConsultarVotosEmitidosMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarVotosEmitidosBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare variable $responseConsultarVotosEmitidosMessage as element() (:: schema-element(ns1:responseConsultarVotosEmitidosMessage) ::) external;

declare function local:func($responseConsultarVotosEmitidosMessage as element() (:: schema-element(ns1:responseConsultarVotosEmitidosMessage) ::)) as element() (:: schema-element(ns2:ConsultarVotosEmitidos) ::) {
    <ns2:ConsultarVotosEmitidos>
        <ns2:emiteVoto>{fn:data($responseConsultarVotosEmitidosMessage/ns1:emiteVoto)}</ns2:emiteVoto>
        <ns2:emiteComentario>{fn:data($responseConsultarVotosEmitidosMessage/ns1:emiteComentario)}</ns2:emiteComentario>
        <ns2:detalleVotosEmitidos>
        {
        for $acumuladoVotacion in $responseConsultarVotosEmitidosMessage/ns1:detalleVotosEmitidos/con:listaAcumuladoVotos
        return
    <ns2:listaAcumuladoVotos>
       <ns2:CantidadVotos>{fn:data($acumuladoVotacion/con:CantidadVotos)}</ns2:CantidadVotos>
       <ns2:descripcionDecision>{fn:data($acumuladoVotacion/con:descripcionDecision)}</ns2:descripcionDecision>
       <ns2:tipoDecision>{fn:data($acumuladoVotacion/con:tipoDecision)}</ns2:tipoDecision>
    </ns2:listaAcumuladoVotos>
  }
{
for $i in $responseConsultarVotosEmitidosMessage/ns1:detalleVotosEmitidos/con:listaComentarios
return
            <ns2:listaComentarios>
                <ns2:idSolicitudAprobacion>{fn:data($i/con:idSolicitudAprobacion)}</ns2:idSolicitudAprobacion>
                <ns2:idOperacion>{fn:data($i/con:idOperacion)}</ns2:idOperacion>
                <ns2:fechaHora>{fn:data($i/con:fechaHora)}</ns2:fechaHora>
                <ns2:idComentario>{fn:data($i/con:idComentario)}</ns2:idComentario>
                <ns2:comentario>{fn:data($i/con:comentario)}</ns2:comentario>
                <ns2:nombreUsuario>{fn:data($i/con:nombreUsuario)}</ns2:nombreUsuario>
            </ns2:listaComentarios>
            }
 {
                for $j in $responseConsultarVotosEmitidosMessage/ns1:detalleVotosEmitidos/con:listaComentariosVotacion
                return
            <ns2:listaComentariosVotacion>
                <ns2:idTipoDecision>{fn:data($j/con:idTipoDecision)}</ns2:idTipoDecision>
                <ns2:descripcion>{fn:data($j/con:descripcion)}</ns2:descripcion>
                <ns2:fechaHora>{fn:data($j/con:fechaHora)}</ns2:fechaHora>
                <ns2:comentarioVotacion>{fn:data($j/con:comentarioVotacion)}</ns2:comentarioVotacion>
                <ns2:nombreUsuario>{fn:data($j/con:nombreUsuario)}</ns2:nombreUsuario>
             
            </ns2:listaComentariosVotacion>
            }

        </ns2:detalleVotosEmitidos>
       <ns2:Result>
           <ns2:result>{fn:data($responseConsultarVotosEmitidosMessage/ns1:Result/res:result)}</ns2:result>
           <ns2:message>{fn:data($responseConsultarVotosEmitidosMessage/ns1:Result/res:message)}</ns2:message>
            <ns2:error>
                <ns2:errorCode></ns2:errorCode>
                <ns2:errorDescription></ns2:errorDescription>
                <ns2:errorType></ns2:errorType>
            </ns2:error>

        </ns2:Result>
    </ns2:ConsultarVotosEmitidos>
};

local:func($responseConsultarVotosEmitidosMessage)
