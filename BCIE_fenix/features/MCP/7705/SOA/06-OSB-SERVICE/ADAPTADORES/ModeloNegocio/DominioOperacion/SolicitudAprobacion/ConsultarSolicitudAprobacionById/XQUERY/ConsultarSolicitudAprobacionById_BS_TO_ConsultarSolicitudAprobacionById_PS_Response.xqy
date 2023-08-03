xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarSolicitudAprobacionById";
(:: import schema at "../XSD/ConsultarSolicitudAprobacionById.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarSolicitudAprobacionByIdOutputCollection as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionByIdOutputCollection) ::) external;

declare function local:func($ConsultarSolicitudAprobacionByIdOutputCollection as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionByIdOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarSolicitudAprobacionByIdResponse) ::) {
    <ns2:ConsultarSolicitudAprobacionByIdResponse>
        <ns2:solicitudAprobacion>
            <sol:idSolicitudAprobacion>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:ID)}</sol:idSolicitudAprobacion>
            <sol:idOperacion>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:ID_OPERACION)}</sol:idOperacion>
            <sol:nombreOperacion>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:NOMBRE_OPERACION)}</sol:nombreOperacion>
            <sol:tipoSolicitud>
                <cat:Id>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:ID_TCA_TIPO_SOLICITUD)}</cat:Id>
            </sol:tipoSolicitud>
            <sol:nivelAprobacion>
                <cat:Id>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:ID_TCA_NIVEL_APROBACION)}</cat:Id>
            </sol:nivelAprobacion>
            <sol:esReunionVirtual>{if (fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:ES_REUNION_VIRTUAL) = 1) then true() else false()}</sol:esReunionVirtual>
            <sol:fechaInicio>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:FECHA_INICIO)}</sol:fechaInicio>
            <sol:fechaTermino>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:FECHA_TERMINO)}</sol:fechaTermino>
            <sol:horaReunion>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:HORA_REUNION)}</sol:horaReunion>
            <sol:lugar>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:LUGAR_REUNION)}</sol:lugar>
            <sol:loginUsuario>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:LOGIN_USUARIO_ASIGNA)}</sol:loginUsuario>
            <sol:fechaAsignacion>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:FECHA_ASIGNACION)}</sol:fechaAsignacion>
            <sol:fechaCierre>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:FECHA_CIERRE)}</sol:fechaCierre>
            <sol:loginUsuarioCierre>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:LOGIN_USUARIO_CIERRE)}</sol:loginUsuarioCierre>
            <sol:fechaRegistro>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:FECHA_REGISTRO)}</sol:fechaRegistro>
            <sol:estado>{if (fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:BAN_ESTATUS)= 1) then true() else false()}</sol:estado>
            <sol:cliente>
                <cli:idCliente>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:ID_CLIENTE)}</cli:idCliente>
                <cli:razonSocial>{fn:data($ConsultarSolicitudAprobacionByIdOutputCollection/ns1:ConsultarSolicitudAprobacionByIdOutput/ns1:NOMBRE_CLIENTE)}</cli:razonSocial>
                <cli:abreviatura></cli:abreviatura>
            </sol:cliente>
        </ns2:solicitudAprobacion>
    </ns2:ConsultarSolicitudAprobacionByIdResponse>
};

local:func($ConsultarSolicitudAprobacionByIdOutputCollection)
