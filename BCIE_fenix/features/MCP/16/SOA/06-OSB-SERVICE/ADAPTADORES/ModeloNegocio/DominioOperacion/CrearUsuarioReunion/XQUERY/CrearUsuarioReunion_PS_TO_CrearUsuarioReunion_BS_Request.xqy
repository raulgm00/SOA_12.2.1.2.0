xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearUsuarioReunion";
(:: import schema at "../XSD/CrearUsuarioReunion_table.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearUsuarioReunionRequest as element() (:: schema-element(ns1:CrearUsuarioReunionRequest) ::) external;

declare function local:func($CrearUsuarioReunionRequest as element() (:: schema-element(ns1:CrearUsuarioReunionRequest) ::)) as element() (:: schema-element(ns2:UsuarioReunionAprobacionCollection) ::) {
    <ns2:UsuarioReunionAprobacionCollection>
        <ns2:UsuarioReunionAprobacion>
            {
                if ($CrearUsuarioReunionRequest/ns1:listadoUsuariosReunion/sol:idSolicitudAprobacion)
                then <ns2:idSolicitudAprobacion>{fn:data($CrearUsuarioReunionRequest/ns1:listadoUsuariosReunion/sol:idSolicitudAprobacion)}</ns2:idSolicitudAprobacion>
                else ()
            }
            {
                if ($CrearUsuarioReunionRequest/ns1:listadoUsuariosReunion/sol:rol/cat:Id)
                then <ns2:idTcaRolBpm>{fn:data($CrearUsuarioReunionRequest/ns1:listadoUsuariosReunion/sol:rol/cat:Id)}</ns2:idTcaRolBpm>
                else ()
            }
            <ns2:loginUsuario>{fn:data($CrearUsuarioReunionRequest/ns1:listadoUsuariosReunion/sol:listadoUsuarios/sol:usuario/sol:usuario)}</ns2:loginUsuario>
            <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
            <ns2:banEstatus>1</ns2:banEstatus>
            <ns2:emiteVoto>{
                if (fn:data($CrearUsuarioReunionRequest/ns1:listadoUsuariosReunion/sol:emiteVoto = true()))
                  then(1)
                    else(0)
            }
            </ns2:emiteVoto>
            <ns2:marcadoComoLeido>0</ns2:marcadoComoLeido>
            <ns2:nombreUsuario>{fn:data($CrearUsuarioReunionRequest/ns1:listadoUsuariosReunion/sol:listadoUsuarios/sol:usuario/sol:nombreUsuario)}</ns2:nombreUsuario>
            <ns2:idTcaTipoUsuario>{fn:data($CrearUsuarioReunionRequest/ns1:listadoUsuariosReunion/sol:tipoUsuario)}</ns2:idTcaTipoUsuario>
            <ns2:conNotificacion>{if(fn:data($CrearUsuarioReunionRequest/ns1:listadoUsuariosReunion/sol:conNotificacion)=true()) then 1 else 0}</ns2:conNotificacion>
        </ns2:UsuarioReunionAprobacion>
    </ns2:UsuarioReunionAprobacionCollection>
};

local:func($CrearUsuarioReunionRequest)
