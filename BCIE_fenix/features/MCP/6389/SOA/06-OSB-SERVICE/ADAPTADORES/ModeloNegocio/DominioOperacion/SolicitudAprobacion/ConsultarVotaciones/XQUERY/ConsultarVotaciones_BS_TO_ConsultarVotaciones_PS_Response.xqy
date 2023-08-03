xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarVotacion";
(:: import schema at "../XSD/ConsultarVotaciones_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarVotacionesResponse) ::) {
    <ns2:ConsultarVotacionesResponse>
          <ns2:Votaciones>
        {
        for $votacion in $OutputParameters/ns1:P_VOTACION/ns1:Row
        return
  <sol:Votacion>
                <sol:SolicitudAprobacion>
                    <sol:idSolicitudAprobacion>{fn:data($votacion/ns1:Column[@name ='ID_SOLICITUD_APROBACION'])}</sol:idSolicitudAprobacion>
                    <sol:idOperacion>{fn:data($votacion/ns1:Column[@name ='ID_OPERACION'])}</sol:idOperacion>
                    <sol:nombreOperacion>{fn:data($votacion/ns1:Column[@name ='NOMBRE'])}</sol:nombreOperacion>
                    <sol:tipoSolicitud>
                        <cat:Id>{fn:data($votacion/ns1:Column[@name ='ID_TCA_TIPO_SOLICITUD'])}</cat:Id>
                        <cat:Descripcion>{fn:data($votacion/ns1:Column[@name ='DESCRIPCION'])}</cat:Descripcion>
                    </sol:tipoSolicitud>
                    <sol:nivelAprobacion>
                        <cat:Id>{fn:data($votacion/ns1:Column[@name ='ID_TCA_NIVEL_APROBACION'])}</cat:Id>
                        <cat:Descripcion>{fn:data($votacion/ns1:Column[@name ='DESC_NIVEL_APROB'])}</cat:Descripcion>
                    </sol:nivelAprobacion>
                    <sol:esReunionVirtual>{if (fn:data($votacion/ns1:Column[@name ='ES_REUNION_VIRTUAL'])= '1') then true() else false()}</sol:esReunionVirtual>
                    <sol:fechaInicio>{fn:data($votacion/ns1:Column[@name ='FECHA_INICIO'])}</sol:fechaInicio>
                    <sol:fechaTermino>{fn:data($votacion/ns1:Column[@name ='FECHA_TERMINO'])}</sol:fechaTermino>
                    <sol:horaReunion>{fn:data($votacion/ns1:Column[@name ='HORA_REUNION'])}</sol:horaReunion>
                    <sol:lugar>{fn:data($votacion/ns1:Column[@name ='LUGAR_REUNION'])}</sol:lugar>
                    <sol:loginUsuario>{fn:data($votacion/ns1:Column[@name ='LOGIN_USUARIO_ASIGNA'])}</sol:loginUsuario>
                    <sol:fechaAsignacion>{fn:data($votacion/ns1:Column[@name ='FECHA_ASIGNACION'])}</sol:fechaAsignacion>
                    <sol:fechaCierre>{fn:data($votacion/ns1:Column[@name ='FECHA_CIERRE'])}</sol:fechaCierre>
                    <sol:loginUsuarioCierre>{fn:data($votacion/ns1:Column[@name ='LOGIN_USUARIO_CIERRE'])}</sol:loginUsuarioCierre>
                    <sol:fechaRegistro>{fn:data($votacion/ns1:Column[@name ='FECHA_REGISTRO'])}</sol:fechaRegistro>
                    <sol:estado>{if (fn:data($votacion/ns1:Column[@name ='BAN_ESTATUS_SOLI'])='1')then true() else false()}</sol:estado>
                    <sol:marcadoComoLeido>{if(fn:data($votacion/ns1:Column[@name ='MARCADO_COMO_LEIDO'])='1')then true() else false()}
                    </sol:marcadoComoLeido>
                    <sol:cliente>
                        <cli:idCliente>{fn:data($votacion/ns1:Column[@name ='ID_CLIENTE'])}</cli:idCliente>
                        <cli:razonSocial>{fn:data($votacion/ns1:Column[@name ='RAZON_SOCIAL'])}</cli:razonSocial>
                    </sol:cliente>
                </sol:SolicitudAprobacion>
                <sol:Usuario>
                    <sol:idUsuarioReunion>{fn:data($votacion/ns1:Column[@name ='ID_USUARIO_REUNION'])}</sol:idUsuarioReunion>
                    <sol:rol>
                        <cat:Id>{fn:data($votacion/ns1:Column[@name ='ID_TCA_ROL_BPM'])}</cat:Id>
                    </sol:rol>
                    <sol:usuario>{fn:data($votacion/ns1:Column[@name ='LOGIN_USUARIO'])}</sol:usuario>
                    <sol:emiteVoto>{if (fn:data($votacion/ns1:Column[@name ='EMITE_VOTO'])= '1') then true() else false()}</sol:emiteVoto>
                    <sol:fechaRegistro>{fn:data($votacion/ns1:Column[@name ='FECHA_REGISTRO_USUARIO_REU'])}</sol:fechaRegistro>
                    <sol:estado>{if (fn:data($votacion/ns1:Column[@name ='BAN_ESTATUS'])= '1') then true() else false()}</sol:estado>
                </sol:Usuario>
            </sol:Votacion>
            }
        </ns2:Votaciones>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns2:Resultado>
    </ns2:ConsultarVotacionesResponse>
};

local:func($OutputParameters)
