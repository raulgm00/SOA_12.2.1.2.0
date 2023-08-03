xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarVotaciones";
(:: import schema at "../XSD/ConsultarVotacionesBO_JSON.xsd" ::)
declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ResponseConsultarVotacion as element() (:: schema-element(ns1:ConsultarVotacionesResponse) ::) external;

declare function local:func($ResponseConsultarVotacion as element() (:: schema-element(ns1:ConsultarVotacionesResponse) ::)) as element() (:: schema-element(ns2:ConsultarVotaciones) ::) {
    <ns2:ConsultarVotaciones>
        <ns2:Votaciones>
         {
                for $consultarVotacionesOutput in $ResponseConsultarVotacion/ns1:Votaciones/sol:Votacion
                return 
            <ns2:Votacion>
                <ns2:SolicitudAprobacion>
                    <ns2:idSolicitudAprobacion>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:idSolicitudAprobacion)}</ns2:idSolicitudAprobacion>
                    <ns2:idOperacion>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:idOperacion)}</ns2:idOperacion>
                    <ns2:nombreOperacion>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:nombreOperacion)}</ns2:nombreOperacion>
                    <ns2:tipoSolicitud>
                        <ns2:Id>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:tipoSolicitud/cat:Id)}</ns2:Id>
                        <ns2:Descripcion>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:tipoSolicitud/cat:Descripcion)}</ns2:Descripcion>
                          </ns2:tipoSolicitud>
                    <ns2:nivelAprobacion>
                        <ns2:Id>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:nivelAprobacion/cat:Id)}</ns2:Id>
                        <ns2:Descripcion>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:nivelAprobacion/cat:Descripcion)}</ns2:Descripcion>
                       
                    </ns2:nivelAprobacion>
                    <ns2:esReunionVirtual>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:esReunionVirtual)}</ns2:esReunionVirtual>
                    <ns2:fechaInicio>{fn:substring(fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:fechaInicio/text()),0,11)}</ns2:fechaInicio>
                    <ns2:fechaTermino>{fn:substring(fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:fechaTermino/text()),0,11)}</ns2:fechaTermino>
                    <ns2:horaReunion>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:horaReunion)}</ns2:horaReunion>
                    <ns2:lugar>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:lugar)}</ns2:lugar>
                    <ns2:loginUsuario>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:loginUsuario)}</ns2:loginUsuario>
                    <ns2:fechaAsignacion>{fn:substring(fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:fechaAsignacion/text()),0,11)}</ns2:fechaAsignacion>
                    <ns2:fechaCierre>{fn:substring(fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:fechaCierre/text()),0,11)}</ns2:fechaCierre>
                    <ns2:loginUsuarioCierre>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:loginUsuarioCierre)}</ns2:loginUsuarioCierre>
                    <ns2:fechaRegistro>{fn:substring(fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:fechaRegistro/text()),0,11)}</ns2:fechaRegistro>
                    <ns2:estado>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:estado)}</ns2:estado>
                    <ns2:marcadoComoLeido>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:marcadoComoLeido)}</ns2:marcadoComoLeido>
                    <ns2:cliente>
                        <ns2:idCliente>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:cliente/cli:idCliente)}</ns2:idCliente>
                        <ns2:razonSocial>{fn:data($consultarVotacionesOutput/sol:SolicitudAprobacion/sol:cliente/cli:razonSocial)}</ns2:razonSocial>
                    </ns2:cliente>
                    </ns2:SolicitudAprobacion>
                <ns2:Usuario>
                    <ns2:idUsuarioReunion>{fn:data($consultarVotacionesOutput/sol:Usuario/sol:idUsuarioReunion)}</ns2:idUsuarioReunion>
                    <ns2:rol>
                        <ns2:Id>{fn:data($consultarVotacionesOutput/sol:Usuario/sol:rol/cat:Id)}</ns2:Id>
                        <ns2:Descripcion></ns2:Descripcion>
                       
                    </ns2:rol>
                    <ns2:usuario>{fn:data($consultarVotacionesOutput/sol:Usuario/sol:usuario)}</ns2:usuario>
                    <ns2:dependecia>{fn:data($consultarVotacionesOutput/sol:Usuario/sol:dependecia)}</ns2:dependecia>
                    <ns2:emiteVoto>{fn:data($consultarVotacionesOutput/sol:Usuario/sol:emiteVoto)}</ns2:emiteVoto>
                    <ns2:notificar>{fn:data($consultarVotacionesOutput/sol:Usuario/sol:notificar)}</ns2:notificar>
                    <ns2:fechaRegistro>{fn:substring($consultarVotacionesOutput/sol:Usuario/sol:fechaRegistro/text(),0,11)}</ns2:fechaRegistro>
                    <ns2:otro>{fn:data($consultarVotacionesOutput/sol:Usuario/sol:otro)}</ns2:otro>
                    <ns2:excusado>{fn:data($consultarVotacionesOutput/sol:Usuario/sol:excusado)}</ns2:excusado>
                    <ns2:estado>{fn:data($consultarVotacionesOutput/sol:Usuario/sol:estado)}</ns2:estado>
                </ns2:Usuario>
                    </ns2:Votacion>
           } </ns2:Votaciones>

    </ns2:ConsultarVotaciones>
};

local:func($ResponseConsultarVotacion)
