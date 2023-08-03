xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarUsuarioProcesoClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarUsuarioProcesoCliente/V1/Schema/ConsultarUsuarioProcesoClienteMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarUsuarioProcesoCliente_DB";
(:: import schema at "../XSD/ConsultarUsuarioProcesoCliente_DB.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarUsuarioTareasBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare variable $ConsultarUsuarioProcesoCliente_DBOutputCollection as element() (:: schema-element(ns1:ConsultarUsuarioProcesoCliente_DBOutputCollection) ::) external;

declare function local:func($ConsultarUsuarioProcesoCliente_DBOutputCollection as element() (:: schema-element(ns1:ConsultarUsuarioProcesoCliente_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarUsuarioProcesoClienteResponse) ::) {
    <ns2:ConsultarUsuarioProcesoClienteResponse>
        {
            for $ConsultarUsuarioProcesoCliente_DBOutput in $ConsultarUsuarioProcesoCliente_DBOutputCollection/ns1:ConsultarUsuarioProcesoCliente_DBOutput
            return 
            <ns2:UsuarioProcesoCliente>
                <con:idBitacoraClienteProceso>{fn:data($ConsultarUsuarioProcesoCliente_DBOutput/ns1:ID)}</con:idBitacoraClienteProceso>
                <con:usuario>{fn:data($ConsultarUsuarioProcesoCliente_DBOutput/ns1:USUARIO)}</con:usuario>
                <con:nombreUsuario>{fn:data($ConsultarUsuarioProcesoCliente_DBOutput/ns1:NOMBRE_USUARIO)}</con:nombreUsuario>
                <con:fechaRegistro>{fn:data($ConsultarUsuarioProcesoCliente_DBOutput/ns1:FECHA_REGISTRO)}</con:fechaRegistro>
                <con:idTarea>{fn:data($ConsultarUsuarioProcesoCliente_DBOutput/ns1:ID_TAREA)}</con:idTarea>
            </ns2:UsuarioProcesoCliente>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
        <res:message>Operación Exitosa</res:message>
        </ns2:Resultado>
    </ns2:ConsultarUsuarioProcesoClienteResponse>
};

local:func($ConsultarUsuarioProcesoCliente_DBOutputCollection)
