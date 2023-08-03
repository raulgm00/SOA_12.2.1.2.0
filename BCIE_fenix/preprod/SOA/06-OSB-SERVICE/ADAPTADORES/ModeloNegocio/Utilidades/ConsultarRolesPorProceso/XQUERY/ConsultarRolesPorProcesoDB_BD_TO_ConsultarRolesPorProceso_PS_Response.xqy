xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarRolesPorProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarRolesPorProceso/V1/Schema/ConsultarRolesPorProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarRolesPorProceso";
(:: import schema at "../XSD/ConsultarRolesPorProceso.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarRolesPorProcesoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarRolesPorProcesoOutputCollection as element() (:: schema-element(ns1:ConsultarRolesPorProcesoOutputCollection) ::) external;

declare function local:responseConsultarRolesPorProcesoMessage($ConsultarRolesPorProcesoOutputCollection as element() (:: schema-element(ns1:ConsultarRolesPorProcesoOutputCollection) ::)) as element() (:: schema-element(ns2:responseConsultarRolesPorProcesoMessage) ::) {
    <ns2:responseConsultarRolesPorProcesoMessage>
        <ns2:ListadoRoles>
        {
        for $i in $ConsultarRolesPorProcesoOutputCollection/ns1:ConsultarRolesPorProcesoOutput
        return
            <con:listadoRoles>
                <cat:Id>{fn:data($i/ns1:R_ID)}</cat:Id>
                <cat:Descripcion>{fn:data($i/ns1:R_DESCRIPCION)}</cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($i/ns1:R_DESCRIPCION_CORTA)}</cat:DescripcionCorta>
                <cat:estatus>{fn:data($i/ns1:R_BAN_ESTATUS)}</cat:estatus>
                <cat:codigoExterno>{fn:data($i/ns1:R_COD_EXTERNO)}</cat:codigoExterno>
            </con:listadoRoles>
            }
        </ns2:ListadoRoles>
        <ns2:Result>
            <res:result>OK</res:result>
            {if (count($ConsultarRolesPorProcesoOutputCollection/ns1:ConsultarRolesPorProcesoOutput)> 0)then
            <res:message>Operacion Exitosa</res:message>
            else
            <res:message>No existen registros</res:message>
            }
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Result>
    </ns2:responseConsultarRolesPorProcesoMessage>
};

local:responseConsultarRolesPorProcesoMessage($ConsultarRolesPorProcesoOutputCollection)
