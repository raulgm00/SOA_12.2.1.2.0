xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarRolUsuarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarRolUsuario/V1/Schema/ConsultarRolUsuarioMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarRolUsuario_DB";
(:: import schema at "../XSD/ConsultarRolUsuario_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarRolUsuario_Output as element() (:: schema-element(ns1:ConsultarRolUsuario_DBOutputCollection) ::) external;

declare function local:func($ConsultarRolUsuario_Output as element() (:: schema-element(ns1:ConsultarRolUsuario_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarRolUsuarioResponse) ::) {
    <ns2:ConsultarRolUsuarioResponse>
        <ns2:Descripcion>
            <cat:Id>{fn:data($ConsultarRolUsuario_Output/ns1:ConsultarRolUsuario_DBOutput/ns1:ID)}</cat:Id>
            <cat:Descripcion>{fn:data($ConsultarRolUsuario_Output/ns1:ConsultarRolUsuario_DBOutput/ns1:DESCRIPCION)}</cat:Descripcion>
            <cat:DescripcionCorta>{fn:data($ConsultarRolUsuario_Output/ns1:ConsultarRolUsuario_DBOutput/ns1:DESCRIPCION_CORTA)}</cat:DescripcionCorta>
            <cat:estatus>{fn:data($ConsultarRolUsuario_Output/ns1:ConsultarRolUsuario_DBOutput/ns1:BAN_ESTATUS)}</cat:estatus>
            <cat:codigoExterno>{fn:data($ConsultarRolUsuario_Output/ns1:ConsultarRolUsuario_DBOutput/ns1:COD_EXTERNO)}</cat:codigoExterno>
        </ns2:Descripcion>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada correctamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarRolUsuarioResponse>
};

local:func($ConsultarRolUsuario_Output)
