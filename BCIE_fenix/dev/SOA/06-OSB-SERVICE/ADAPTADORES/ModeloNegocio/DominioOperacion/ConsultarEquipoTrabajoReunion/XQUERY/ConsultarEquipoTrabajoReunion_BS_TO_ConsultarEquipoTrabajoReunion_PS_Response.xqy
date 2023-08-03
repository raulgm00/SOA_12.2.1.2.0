xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarEquipoTrabajoReunion";
(:: import schema at "../XSD/ConsultarEquipoTrabajoReunion.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $EquipoTrabajoResponse as element() (:: schema-element(ns1:ConsultarEquipoTrabajoReunionOutputCollection) ::) external;

declare function local:func($EquipoTrabajoResponse as element() (:: schema-element(ns1:ConsultarEquipoTrabajoReunionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarUsuarioReunionResponse) ::) {
    <ns2:ConsultarUsuarioReunionResponse>
        {
            for $ConsultarEquipoTrabajoReunionOutput in $EquipoTrabajoResponse/ns1:ConsultarEquipoTrabajoReunionOutput
            return 
            <ns2:listadoUsuariosReunion>
                <sol:idUsuarioReunion>{fn:data($ConsultarEquipoTrabajoReunionOutput/ns1:ID)}</sol:idUsuarioReunion>
                <sol:rol>
                    <cat:Id>{fn:data($ConsultarEquipoTrabajoReunionOutput/ns1:ROL_ID)}</cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarEquipoTrabajoReunionOutput/ns1:ROL_DESCRIPCION)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($ConsultarEquipoTrabajoReunionOutput/ns1:ROL_CORTA)}</cat:DescripcionCorta>
                    <cat:codigoExterno>{fn:data($ConsultarEquipoTrabajoReunionOutput/ns1:ROL_EXTERNO)}</cat:codigoExterno>
                </sol:rol>
                
                <sol:usuario>{fn:data($ConsultarEquipoTrabajoReunionOutput/ns1:USUARIO)}</sol:usuario>
            </ns2:listadoUsuariosReunion>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarUsuarioReunionResponse>
};

local:func($EquipoTrabajoResponse)
