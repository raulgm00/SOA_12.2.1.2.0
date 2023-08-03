xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://xmlns.bcie.com/CuestionarioMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Cuestionario/V1/Schema/CuestionarioMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ObtenerRolesCuestionario";
(:: import schema at "../XSD/ObtenerRolesCuestionario.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $obtenerRolesCuestionarioBSResponse as element() (:: schema-element(ns1:ObtenerRolesCuestionarioOutputCollection) ::) external;

declare function local:func($obtenerRolesCuestionarioBSResponse as element() (:: schema-element(ns1:ObtenerRolesCuestionarioOutputCollection) ::)) as element() (:: schema-element(ns2:ObtenerRolesCuestionarioResponse) ::) {
    <ns2:ObtenerRolesCuestionarioResponse>
        <ns2:Roles>
            {
            for $ObtenerRolesCuestionarioOutput in $obtenerRolesCuestionarioBSResponse/ns1:ObtenerRolesCuestionarioOutput
            return <ns2:Rol>{fn:data($ObtenerRolesCuestionarioOutput/ns1:ROL)}</ns2:Rol>
            }
        </ns2:Roles>
        <ns2:Respuesta>
            <res:result>OK</res:result>
            <res:message>Consulta de roles del cuestionario exitosa</res:message>
        </ns2:Respuesta>
    </ns2:ObtenerRolesCuestionarioResponse>
};

local:func($obtenerRolesCuestionarioBSResponse)
