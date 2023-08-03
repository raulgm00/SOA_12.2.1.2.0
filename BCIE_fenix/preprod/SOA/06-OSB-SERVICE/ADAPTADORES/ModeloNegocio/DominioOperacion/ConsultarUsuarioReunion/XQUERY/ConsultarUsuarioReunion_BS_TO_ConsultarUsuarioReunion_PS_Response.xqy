xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarUsuarioReunionDB";
(:: import schema at "../XSD/ConsultarUsuarioReunionDB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarUsuarioReunionDBOutputCollection as element() (:: schema-element(ns1:ConsultarUsuarioReunionDBOutputCollection) ::) external;

declare function local:func($ConsultarUsuarioReunionDBOutputCollection as element() (:: schema-element(ns1:ConsultarUsuarioReunionDBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarUsuarioReunionResponse) ::) {
    <ns2:ConsultarUsuarioReunionResponse>
        {
            for $ConsultarUsuarioReunionDBOutput in $ConsultarUsuarioReunionDBOutputCollection/ns1:ConsultarUsuarioReunionDBOutput
            return 
            <ns2:listadoUsuariosReunion>
                <sol:idSolicitudAprobacion>{fn:data($ConsultarUsuarioReunionDBOutput/ns1:ID_SOLICITUD_APROBACION)}</sol:idSolicitudAprobacion>
                <sol:rol>
                    <cat:Id>{fn:data($ConsultarUsuarioReunionDBOutput/ns1:ID_ROL)}</cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarUsuarioReunionDBOutput/ns1:DESCRIPCION_ROL)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($ConsultarUsuarioReunionDBOutput/ns1:DESCRIPCION_CORTA_ROL)}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </sol:rol>
                <sol:usuario>{fn:data($ConsultarUsuarioReunionDBOutput/ns1:LOGIN_USUARIO)}</sol:usuario>
                <sol:emiteVoto>{if(fn:data($ConsultarUsuarioReunionDBOutput/ns1:EMITE_VOTO)=1) then true() else false()}</sol:emiteVoto>
                <sol:fechaRegistro>{fn:data($ConsultarUsuarioReunionDBOutput/ns1:FECHA_REGISTRO)}</sol:fechaRegistro>
                <sol:estado>{if(fn:data($ConsultarUsuarioReunionDBOutput/ns1:BAN_ESTATUS)=1) then true() else false()}</sol:estado></ns2:listadoUsuariosReunion>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarUsuarioReunionResponse>
};

local:func($ConsultarUsuarioReunionDBOutputCollection)
