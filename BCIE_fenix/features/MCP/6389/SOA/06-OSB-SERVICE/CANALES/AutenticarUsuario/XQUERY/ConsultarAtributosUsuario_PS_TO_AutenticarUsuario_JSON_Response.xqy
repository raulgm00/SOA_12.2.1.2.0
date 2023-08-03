xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/AutenticarUsuario";
(:: import schema at "../XSD/AutenticarUsuarioResponse_JSON.xsd" ::)
declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/UsuarioMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/Utilidades/Usuario/V1/Schema/UsuarioMO.xsd" ::)

declare namespace usu = "http://www.bcie.org/UsuarioBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare variable $ConsultarMiembroReunionResponse as element() (:: schema-element(ns1:ConsultarMiembroReunionResponse) ::) external;
declare variable $ConsultarAtributosUsuarioResponse as element() (:: schema-element(ns2:ConsultarAtributosUsuarioResponse) ::) external;
declare variable $responseJava as xs:boolean external;

declare function local:func($ConsultarMiembroReunionResponse as element() (:: schema-element(ns1:ConsultarMiembroReunionResponse) ::), 
                            $ConsultarAtributosUsuarioResponse as element() (:: schema-element(ns2:ConsultarAtributosUsuarioResponse) ::),
                            $responseJava as xs:boolean) as element() (:: schema-element(ns3:AutenticarUsuario) ::) {
    <ns3:AutenticarUsuario>
        <ns3:respuesta>{fn:data($responseJava)}</ns3:respuesta>
        <ns3:NombreCompleto>{fn:data($ConsultarAtributosUsuarioResponse/ns2:listaUsuarios/usu:usuario/usu:nombreCompleto)}</ns3:NombreCompleto>
        <ns3:dependencia>{fn:data($ConsultarAtributosUsuarioResponse/ns2:listaUsuarios/usu:usuario/usu:dependencia)}</ns3:dependencia>
        {
        let $rol := $ConsultarMiembroReunionResponse/ns1:listadoUsuariosReunion/sol:rol[cat:DescripcionCorta = $ConsultarAtributosUsuarioResponse/ns2:listaUsuarios/usu:usuario/usu:listaGrupos/usu:grupo]
        return 
        if ($rol[cat:codigoExterno = '2']) then
         <ns2:nivelAprobacion>2</ns2:nivelAprobacion>
        else 
         if ($rol[cat:codigoExterno = '1']) then
        <ns2:nivelAprobacion>1</ns2:nivelAprobacion>
        else
         <ns2:nivelAprobacion>0</ns2:nivelAprobacion>
        }         
        <ns3:Resultado>
            <ns3:result>OK</ns3:result>
            <ns3:message></ns3:message>
        </ns3:Resultado>
    </ns3:AutenticarUsuario>
};

local:func($ConsultarMiembroReunionResponse, $ConsultarAtributosUsuarioResponse, $responseJava)
