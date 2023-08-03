xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarVotosEmitidosMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarVotosEmitidos/V1/Schema/ConsultarVotosEmitidosMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarVotosEmitidosBO";

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarAcumuladoVotacionResponse as element() (:: schema-element(ns1:ConsultarAcumuladoVotacionResponse) ::) external;
declare variable $ConsultarComentariosVotacionResponse as element() (:: schema-element(ns1:ConsultarComentariosVotacionResponse) ::) external;
declare variable $ConsultarVotosResponse as element() (:: schema-element(ns1:ConsultarVotosResponse) ::) external;
declare variable $ConsultarUsuarioReunionResponse as element() (:: schema-element(ns1:ConsultarUsuarioReunionResponse) ::) external;
declare variable $requestConsultarVotosEmitidosMessage as element() (:: schema-element(ns2:requestConsultarVotosEmitidosMessage) ::) external;

declare function local:func($ConsultarAcumuladoVotacionResponse as element() (:: schema-element(ns1:ConsultarAcumuladoVotacionResponse) ::), 
                            $ConsultarComentariosVotacionResponse as element() (:: schema-element(ns1:ConsultarComentariosVotacionResponse) ::), 
                            $ConsultarVotosResponse as element() (:: schema-element(ns1:ConsultarVotosResponse) ::),
                            $ConsultarUsuarioReunionResponse as element() (:: schema-element(ns1:ConsultarUsuarioReunionResponse) ::),
                            $requestConsultarVotosEmitidosMessage as element() (:: schema-element(ns2:requestConsultarVotosEmitidosMessage) ::)) 
                            as element() (:: schema-element(ns2:responseConsultarVotosEmitidosMessage) ::) {
    <ns2:responseConsultarVotosEmitidosMessage>
        <ns2:emiteVoto>{if(fn:data($ConsultarUsuarioReunionResponse/ns1:listadoUsuariosReunion[sol:usuario = fn:data($requestConsultarVotosEmitidosMessage/ns2:solicitudAprobacion/con:loginUsuario)]/sol:emiteVoto[1])= true()) then 1 else 0 }</ns2:emiteVoto>
        <ns2:emiteComentario>{if(fn:data($ConsultarUsuarioReunionResponse/ns1:listadoUsuariosReunion[sol:usuario = fn:data($requestConsultarVotosEmitidosMessage/ns2:solicitudAprobacion/con:loginUsuario)]/sol:idUsuarioReunion[1]/text()) != '') then 1 else 0}</ns2:emiteComentario>
        <ns2:detalleVotosEmitidos>
            {
                for $acumuladoVotacion in $ConsultarAcumuladoVotacionResponse/ns1:acumuladoVotacion
                return 
                <con:listaAcumuladoVotos>
                    <con:CantidadVotos>{fn:data($acumuladoVotacion/sol:cantidadVotos)}</con:CantidadVotos>
                    <con:descripcionDecision>{fn:data($acumuladoVotacion/sol:descripcionDecision)}</con:descripcionDecision>
                    <con:tipoDecision>{fn:data($acumuladoVotacion/sol:tipoDecision)}</con:tipoDecision></con:listaAcumuladoVotos>
            }
            {
                for $ListaComentariosVotacion in $ConsultarComentariosVotacionResponse/ns1:ListaComentariosVotacion
                return 
                <con:listaComentarios>
                    <con:idSolicitudAprobacion>{fn:data($ListaComentariosVotacion/sol:idSolicitudAprobacion)}</con:idSolicitudAprobacion>
                    <con:idOperacion>{fn:data($ListaComentariosVotacion/sol:idOperacion)}</con:idOperacion>
                    <con:fechaHora>{fn:data($ListaComentariosVotacion/sol:fechaHora)}</con:fechaHora>
                    <con:idComentario>{fn:data($ListaComentariosVotacion/sol:idComentario)}</con:idComentario>
                    <con:comentario>{fn:data($ListaComentariosVotacion/sol:comentario)}</con:comentario>
                    <con:nombreUsuario>{fn:data($ListaComentariosVotacion/sol:nombreUsuario)}</con:nombreUsuario></con:listaComentarios>
            }
            {
                for $ListaVotosEmitidos in $ConsultarVotosResponse/ns1:ListaVotosEmitidos
                return 
                <con:listaComentariosVotacion>
                    <con:idTipoDecision>{fn:data($ListaVotosEmitidos/sol:tipo_decision)}</con:idTipoDecision>
                    <con:descripcion>{fn:data($ListaVotosEmitidos/sol:descripcion)}</con:descripcion>
                    <con:fechaHora>{fn:data($ListaVotosEmitidos/sol:fecha_hora)}</con:fechaHora>
                    <con:comentarioVotacion>{fn:data($ListaVotosEmitidos/sol:comentario)}</con:comentarioVotacion>
                    <con:nombreUsuario>{fn:data($ListaVotosEmitidos/sol:nombreUsuario)}</con:nombreUsuario></con:listaComentariosVotacion>
            }
        </ns2:detalleVotosEmitidos>
        <ns2:Result>
            <res:result>OK</res:result>
            {if(count($ConsultarAcumuladoVotacionResponse/ns1:acumuladoVotacion)> 0)then
            <res:message>Operacion Exitosa</res:message>
            else
            <res:message>No existen registros</res:message>
            }
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Result>
    </ns2:responseConsultarVotosEmitidosMessage>
};

local:func($ConsultarAcumuladoVotacionResponse, $ConsultarComentariosVotacionResponse, $ConsultarVotosResponse, $ConsultarUsuarioReunionResponse, $requestConsultarVotosEmitidosMessage)
