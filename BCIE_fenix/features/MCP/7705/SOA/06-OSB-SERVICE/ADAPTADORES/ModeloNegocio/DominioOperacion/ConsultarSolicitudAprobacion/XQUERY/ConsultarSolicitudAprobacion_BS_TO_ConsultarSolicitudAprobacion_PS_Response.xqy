xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarSolicitudAprobacion";
(:: import schema at "../XSD/ConsultarSolicitudAprobacion.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare variable $consultarSolicitudAprobacionOutput as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionOutputCollection) ::) external;

declare function local:func($consultarSolicitudAprobacionOutput as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarSolicitudAprobacionResponse) ::) {
    <ns2:ConsultarSolicitudAprobacionResponse>
       {
               for $solicitudes in $consultarSolicitudAprobacionOutput/ns1:ConsultarSolicitudAprobacionOutput
               return 
        <ns2:solicitudAprobacion>
            <sol:idSolicitudAprobacion>{fn:data($solicitudes/ns1:ID)}</sol:idSolicitudAprobacion>
            <sol:idOperacion>{fn:data($solicitudes/ns1:ID_OPERACION)}</sol:idOperacion>
            <sol:tipoSolicitud>
                <cat:Id>{fn:data($solicitudes/ns1:ID_TCA_TIPO_SOLICITUD)}</cat:Id>
             
            </sol:tipoSolicitud>
            <sol:nivelAprobacion>
                <cat:Id>{fn:data($solicitudes/ns1:ID_TCA_NIVEL_APROBACION)}</cat:Id>
               
            </sol:nivelAprobacion>
            <sol:esReunionVirtual>{fn:data($solicitudes/ns1:ES_REUNION_VIRTUAL)}</sol:esReunionVirtual>
            <sol:fechaInicio>{fn:data($solicitudes/ns1:FECHA_INICIO)}</sol:fechaInicio>
            <sol:fechaTermino>{fn:data($solicitudes/ns1:FECHA_TERMINO)}</sol:fechaTermino>
            <sol:horaReunion>{fn:data($solicitudes/ns1:HORA_REUNION)}</sol:horaReunion>
            <sol:lugar>{fn:data($solicitudes/ns1:LUGAR_REUNION)}</sol:lugar>
            <sol:loginUsuario>{fn:data($solicitudes/ns1:LOGIN_USUARIO_ASIGNA)}</sol:loginUsuario>
            <sol:fechaAsignacion>{fn:data($solicitudes/ns1:FECHA_ASIGNACION)}</sol:fechaAsignacion>
            <sol:fechaCierre>{fn:data($solicitudes/ns1:FECHA_CIERRE)}</sol:fechaCierre>
            <sol:loginUsuarioCierre>{fn:data($solicitudes/ns1:LOGIN_USUARIO_CIERRE)}</sol:loginUsuarioCierre>
            <sol:fechaRegistro>{fn:data($solicitudes/ns1:FECHA_REGISTRO)}</sol:fechaRegistro>
            <sol:estado>{fn:data($solicitudes/ns1:BAN_ESTATUS)}</sol:estado>
        </ns2:solicitudAprobacion>
        }
    </ns2:ConsultarSolicitudAprobacionResponse>
};

local:func($consultarSolicitudAprobacionOutput)
