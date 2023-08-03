xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearSolicitudAprobacionDB";
(:: import schema at "../XSD/CrearSolicitudAprobacion_table.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $SolicitudAprobacionRequest as element() (:: schema-element(ns1:CrearSolicitudAprobacionRequest) ::) external;

declare function local:func($SolicitudAprobacionRequest as element() (:: schema-element(ns1:CrearSolicitudAprobacionRequest) ::)) as element() (:: schema-element(ns2:SolicitudAprobacionCollection) ::) {
    <ns2:SolicitudAprobacionCollection>
        {
            for $solicitudAprobacion in $SolicitudAprobacionRequest/ns1:solicitudAprobacion
            return 
            <ns2:SolicitudAprobacion>
                <ns2:id>{fn:data($solicitudAprobacion/sol:idSolicitudAprobacion)}</ns2:id>
                {
                    if ($solicitudAprobacion/sol:idOperacion)
                    then <ns2:idOperacion>{fn:data($solicitudAprobacion/sol:idOperacion)}</ns2:idOperacion>
                    else ()
                }
                {
                    if ($solicitudAprobacion/sol:tipoSolicitud/cat:Id)
                    then <ns2:idTcaTipoSolicitud>{fn:data($solicitudAprobacion/sol:tipoSolicitud/cat:Id)}</ns2:idTcaTipoSolicitud>
                    else ()
                }
                {
                    if ($solicitudAprobacion/sol:nivelAprobacion/cat:Id)
                    then <ns2:idTcaNivelAprobacion>{fn:data($solicitudAprobacion/sol:nivelAprobacion/cat:Id)}</ns2:idTcaNivelAprobacion>
                    else ()
                }
                <ns2:esReunionVirtual>{
                    if (fn:data($solicitudAprobacion/sol:esReunionVirtual) = true())
                      then(1)
                        else(0)
                      }
                </ns2:esReunionVirtual>
                <ns2:fechaInicio>{fn:data($solicitudAprobacion/sol:fechaInicio)}</ns2:fechaInicio>
                <ns2:fechaTermino>{fn:data($solicitudAprobacion/sol:fechaTermino)}</ns2:fechaTermino>
                <ns2:horaReunion>{fn:data($solicitudAprobacion/sol:horaReunion)}</ns2:horaReunion>
                <ns2:lugarReunion>{fn:data($solicitudAprobacion/sol:lugar)}</ns2:lugarReunion>
                <ns2:loginUsuarioAsigna>{fn:data($solicitudAprobacion/sol:loginUsuario)}</ns2:loginUsuarioAsigna>
                <ns2:fechaAsignacion>{fn:current-dateTime()}</ns2:fechaAsignacion>
                <ns2:fechaCierre>{fn:data($solicitudAprobacion/sol:fechaCierre)}</ns2:fechaCierre>
                <ns2:loginUsuarioCierre>{fn:data($solicitudAprobacion/sol:loginUsuarioCierre)}</ns2:loginUsuarioCierre>
                <ns2:fechaRegistro>{fn:current-dateTime()}</ns2:fechaRegistro>
                <ns2:banEstatus>1</ns2:banEstatus>
                <ns2:idCliente>{fn:data($solicitudAprobacion/sol:cliente/cli:idCliente)}</ns2:idCliente>
                <ns2:instanciaProcesoSeguimiento>{fn:data($solicitudAprobacion/sol:instanciaProcesoSeguimiento)}</ns2:instanciaProcesoSeguimiento>
            </ns2:SolicitudAprobacion>
        }
    </ns2:SolicitudAprobacionCollection>
};

local:func($SolicitudAprobacionRequest)
