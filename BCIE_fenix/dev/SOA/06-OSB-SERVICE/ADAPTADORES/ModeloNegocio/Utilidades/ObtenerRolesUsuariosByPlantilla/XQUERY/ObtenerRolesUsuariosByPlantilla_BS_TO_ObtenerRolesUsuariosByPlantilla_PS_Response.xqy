xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ObtenerRolesUsuarioByPlantilla";
(:: import schema at "../XSD/ObtenerRolesUsuariosByPlantilla_sp.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ObtenerRolesUsuariosByPlantillaResponse) ::) {
    <ns2:ObtenerRolesUsuariosByPlantillaResponse>
        <ns2:listaUsuariosTo>
            {
            for $rolUsuario in $OutputParameters/ns1:P_PARA_USUARIOS/ns1:Row/ns1:Column[@name= 'USUARIO_ROL']
            return 
            <cor:usuario>{fn:data($rolUsuario)}</cor:usuario>
            }
        </ns2:listaUsuariosTo>
        
        <ns2:listaUsuariosError>
            
            {
            for $rolUsuarioError in $OutputParameters/ns1:P_ERROR_USUARIOS/ns1:Row/ns1:Column[@name= 'USUARIO_ROL']
            return <cor:usuario>{fn:data($rolUsuarioError)}</cor:usuario>
            }

        </ns2:listaUsuariosError>
        
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta realizada exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ObtenerRolesUsuariosByPlantillaResponse>
};

local:func($OutputParameters)
