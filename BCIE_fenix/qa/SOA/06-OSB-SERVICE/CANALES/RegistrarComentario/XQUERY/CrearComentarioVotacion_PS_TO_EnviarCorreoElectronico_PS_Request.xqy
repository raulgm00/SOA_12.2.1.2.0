xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns3="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://www.bice.org/CrearComentarioVotacion";
(:: import schema at "../XSD/CrearComentarioVotacion_JSON.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $usuariosReunionResponse as element() (:: schema-element(ns1:ConsultarUsuarioReunionResponse) ::) external;
declare variable $solicitudAprobacionResponse as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionByIdResponse) ::) external;
declare variable $request as element() (:: schema-element(ns2:CrearComentarioVotacionRequest) ::) external;

declare function local:func($usuariosReunionResponse as element() (:: schema-element(ns1:ConsultarUsuarioReunionResponse) ::), 
                            $solicitudAprobacionResponse as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionByIdResponse) ::), 
                            $request as element() (:: schema-element(ns2:CrearComentarioVotacionRequest) ::)) 
                            as element() (:: schema-element(ns3:enviarCorreoElectronicoRequest) ::) {
    <ns3:enviarCorreoElectronicoRequest>
        <ns3:CorreoElectronico>
            <cor:to>
                <cor:usuario>{fn:data($usuariosReunionResponse/ns1:listadoUsuariosReunion[1]/sol:rol/cat:Descripcion)}</cor:usuario>
            </cor:to>
            <cor:idOperacion>{fn:data($solicitudAprobacionResponse/ns1:solicitudAprobacion/sol:idOperacion)}</cor:idOperacion>
            <cor:idClienteFenix>{fn:data($solicitudAprobacionResponse/ns1:solicitudAprobacion/sol:cliente/cli:idCliente)}</cor:idClienteFenix>
            <cor:id_plantilla>90</cor:id_plantilla>
            <cor:parametros>
                <cor:tag>NOMBRE_USUARIO</cor:tag>
                <cor:valor>{fn:data($request/ns2:nombreUsuario)}</cor:valor>
            </cor:parametros>
            <cor:parametros>
                <cor:tag>ID_OPERACION</cor:tag>
                <cor:valor>{fn:data($solicitudAprobacionResponse/ns1:solicitudAprobacion/sol:idOperacion)}</cor:valor>
            </cor:parametros>
             <cor:parametros>
                <cor:tag>OPERACION</cor:tag>
                <cor:valor>{fn:data($solicitudAprobacionResponse/ns1:solicitudAprobacion/sol:nombreOperacion)}</cor:valor>
            </cor:parametros>
            <cor:parametros>
                <cor:tag>TIPO_TRANSACCION</cor:tag>
                <cor:valor>1</cor:valor>
            </cor:parametros>
            <cor:parametros>
                <cor:tag>IDCLIENTE</cor:tag>
                <cor:valor>{fn:data($solicitudAprobacionResponse/ns1:solicitudAprobacion/sol:cliente/cli:idCliente)}</cor:valor>
            </cor:parametros>
            <cor:parametros>
                <cor:tag>CLIENTE</cor:tag>
                <cor:valor>{fn:data($solicitudAprobacionResponse/ns1:solicitudAprobacion/sol:cliente/cli:abreviatura)}</cor:valor>
            </cor:parametros>
        </ns3:CorreoElectronico>
    </ns3:enviarCorreoElectronicoRequest>
};

local:func($usuariosReunionResponse, $solicitudAprobacionResponse, $request)
