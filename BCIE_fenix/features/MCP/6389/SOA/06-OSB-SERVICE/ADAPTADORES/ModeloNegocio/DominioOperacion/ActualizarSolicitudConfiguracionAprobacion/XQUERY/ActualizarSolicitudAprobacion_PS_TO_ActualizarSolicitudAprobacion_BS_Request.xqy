xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarSolicitudAprobacion";
(:: import schema at "../XSD/ActualizarSolicitudAprobacion_table.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $SolicitudAprobacion as element() (:: schema-element(ns1:ActualizarConfiguracionSolicitudAprobacionRequest) ::) external;

declare function local:func($SolicitudAprobacion as element() (:: schema-element(ns1:ActualizarConfiguracionSolicitudAprobacionRequest) ::)) as element() (:: schema-element(ns2:SolicitudAprobacionCollection) ::) {
    <ns2:SolicitudAprobacionCollection>
        <ns2:SolicitudAprobacion>
            <ns2:id>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:idSolicitudAprobacion)}</ns2:id>
            {
                if ($SolicitudAprobacion/ns1:solicitudAprobacion/sol:idOperacion)
                then <ns2:idOperacion>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:idOperacion)}</ns2:idOperacion>
                else ()
            }
            {
                if ($SolicitudAprobacion/ns1:solicitudAprobacion/sol:tipoSolicitud/cat:Id)
                then <ns2:idTcaTipoSolicitud>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:tipoSolicitud/cat:Id)}</ns2:idTcaTipoSolicitud>
                else ()
            }
            {
                if ($SolicitudAprobacion/ns1:solicitudAprobacion/sol:nivelAprobacion/cat:Id)
                then <ns2:idTcaNivelAprobacion>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:nivelAprobacion/cat:Id)}</ns2:idTcaNivelAprobacion>
                else ()
            }
            <ns2:esReunionVirtual>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:esReunionVirtual)}</ns2:esReunionVirtual>
            <ns2:fechaInicio>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:fechaInicio)}</ns2:fechaInicio>
            <ns2:fechaTermino>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:fechaTermino)}</ns2:fechaTermino>
            <ns2:horaReunion>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:horaReunion)}</ns2:horaReunion>
            <ns2:lugarReunion>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:lugar)}</ns2:lugarReunion>
            <ns2:loginUsuarioAsigna>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:loginUsuario)}</ns2:loginUsuarioAsigna>
            <ns2:fechaAsignacion>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:fechaAsignacion)}</ns2:fechaAsignacion>
            <ns2:fechaCierre>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:fechaCierre)}</ns2:fechaCierre>
            <ns2:loginUsuarioCierre>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:loginUsuarioCierre)}</ns2:loginUsuarioCierre>
            <ns2:fechaRegistro>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:fechaRegistro)}</ns2:fechaRegistro>
            <ns2:banEstatus>{fn:data($SolicitudAprobacion/ns1:solicitudAprobacion/sol:estado)}</ns2:banEstatus>
        </ns2:SolicitudAprobacion>
    </ns2:SolicitudAprobacionCollection>
};

local:func($SolicitudAprobacion)
