xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarSolicitudAproByIdSolicitud";
(:: import schema at "../XSD/ConsultarSolicitudAproByIdSolicitud.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarSolicitudAproByIdSolicitudOutputCollection as element() (:: schema-element(ns1:ConsultarSolicitudAproByIdSolicitudOutputCollection) ::) external;

declare function local:func($ConsultarSolicitudAproByIdSolicitudOutputCollection as element() (:: schema-element(ns1:ConsultarSolicitudAproByIdSolicitudOutputCollection) ::)) as element() (:: element(*, ns2:ConsultarVotacionUsuarioResponseType) ::) {
    <ns2:ConsultarVotacionUsuarioResponseType>
    {
    for $i in $ConsultarSolicitudAproByIdSolicitudOutputCollection/ns1:ConsultarSolicitudAproByIdSolicitudOutput
    return
        <ns2:ListaVotacionUsuario>
            <sol:idSolicitudAprobacion>{fn:data($i/ns1:ID_SOLICITUD_APROBACION)}</sol:idSolicitudAprobacion>
            <sol:idOperacion>{fn:data($i/ns1:ID_OPERACION)}</sol:idOperacion>
            <sol:idTcaTipoSolicitud>{fn:data($i/ns1:ID_TCA_TIPO_SOLICITUD)}</sol:idTcaTipoSolicitud>
            <sol:idTcaNivelAprobacion>{fn:data($i/ns1:ID_TCA_NIVEL_APROBACION)}</sol:idTcaNivelAprobacion>
            <sol:esReunionVirtual>{fn:data($i/ns1:ES_REUNION_VIRTUAL)}</sol:esReunionVirtual>
            <sol:fechaInicio>{fn:data($i/ns1:FECHA_INICIO)}</sol:fechaInicio>
            <sol:fechaTermino>{fn:data($i/ns1:FECHA_TERMINO)}</sol:fechaTermino>
            <sol:horaReunion>{fn:data($i/ns1:HORA_REUNION)}</sol:horaReunion>
            <sol:lugarReunion>{fn:data($i/ns1:LUGAR_REUNION)}</sol:lugarReunion>
            <sol:fechaAsignacion>{fn:data($i/ns1:FECHA_ASIGNACION)}</sol:fechaAsignacion>
            <sol:fechaCierre>{fn:data($i/ns1:FECHA_CIERRE)}</sol:fechaCierre>
            <sol:idCliente>{fn:data($i/ns1:ID_CLIENTE)}</sol:idCliente>
            <sol:instanciaProcesoSeguimiento>{fn:data($i/ns1:INSTANCIA_PROCESO_SEGUIMIENTO)}</sol:instanciaProcesoSeguimiento>
        </ns2:ListaVotacionUsuario>
        }
        <ns2:Result>
            <res:result>OK</res:result>
            {if (count($ConsultarSolicitudAproByIdSolicitudOutputCollection/ns1:ConsultarSolicitudAproByIdSolicitudOutput)> 0)then
            <res:message>Validación Exitosa</res:message>
            else
            <res:message>No existen registros</res:message>
            }
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Result>
    </ns2:ConsultarVotacionUsuarioResponseType>
};

local:func($ConsultarSolicitudAproByIdSolicitudOutputCollection)
