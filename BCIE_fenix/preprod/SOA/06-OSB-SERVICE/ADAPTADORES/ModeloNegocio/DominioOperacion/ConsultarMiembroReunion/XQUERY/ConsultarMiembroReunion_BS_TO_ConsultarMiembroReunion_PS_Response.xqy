xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarMiembroReunionDB";
(:: import schema at "../XSD/ConsultarMiembroReunionDB.xsd" ::)

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarMiembroReunionDBOutputCollection as element() (:: schema-element(ns1:ConsultarMiembroReunionDBOutputCollection) ::) external;

declare function local:func($ConsultarMiembroReunionDBOutputCollection as element() (:: schema-element(ns1:ConsultarMiembroReunionDBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarMiembroReunionResponse) ::) {
    <ns2:ConsultarMiembroReunionResponse>
        {
            for $ConsultarMiembroReunionDBOutput in $ConsultarMiembroReunionDBOutputCollection/ns1:ConsultarMiembroReunionDBOutput
            return 
            <ns2:listadoUsuariosReunion>
                <sol:idUsuarioReunion>{fn:data($ConsultarMiembroReunionDBOutput/ns1:ID)}</sol:idUsuarioReunion>
                <sol:rol>
                    <cat:Id>{fn:data($ConsultarMiembroReunionDBOutput/ns1:ID_ROL)}</cat:Id>
                    <cat:Descripcion>{fn:data($ConsultarMiembroReunionDBOutput/ns1:DESCRIPCION_ROL)}</cat:Descripcion>
                    <cat:DescripcionCorta>{fn:data($ConsultarMiembroReunionDBOutput/ns1:DESCRIPCION_CORTA_ROL)}</cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno>{fn:data($ConsultarMiembroReunionDBOutput/ns1:ID_TCA_NIVEL_APROBACION)}</cat:codigoExterno>
                </sol:rol>
                <sol:listadoUsuarios>
            
                </sol:listadoUsuarios></ns2:listadoUsuariosReunion>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada correctamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarMiembroReunionResponse>
};

local:func($ConsultarMiembroReunionDBOutputCollection)
