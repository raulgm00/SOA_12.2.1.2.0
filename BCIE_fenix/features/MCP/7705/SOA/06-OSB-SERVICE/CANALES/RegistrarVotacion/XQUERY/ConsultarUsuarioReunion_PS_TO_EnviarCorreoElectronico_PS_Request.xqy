  xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns3="http://www.bcie.org/CrearRegistroVotacionBO";
(:: import schema at "../XSD/CrearRegistroVotacion_JSON.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare namespace sol = "http://www.bcie.org/SolicitudAprobacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearRegistroVotacionRequest as element() (:: schema-element(ns3:CrearRegistroVotacionRequest) ::) external;
declare variable $ConsultarSolicitudAprobacionByIdResponse as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionByIdResponse) ::) external;
declare variable $ConsultarUsuarioReunionResponse as element() (:: schema-element(ns1:ConsultarUsuarioReunionResponse) ::) external;
declare variable $idRegistroVotacion as xs:long external;

declare function local:func($CrearRegistroVotacionRequest as element() (:: schema-element(ns3:CrearRegistroVotacionRequest) ::), 
                            $ConsultarSolicitudAprobacionByIdResponse as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionByIdResponse) ::), 
                            $ConsultarUsuarioReunionResponse as element() (:: schema-element(ns1:ConsultarUsuarioReunionResponse) ::),
                            $idRegistroVotacion as xs:long) 
                            as element() (:: schema-element(ns2:enviarCorreoElectronicoRequest) ::) {
                            
    let $nivel:= $ConsultarUsuarioReunionResponse/ns1:listadoUsuariosReunion[1]/sol:idNivelAprobacion
    let $usuarioCC := $ConsultarUsuarioReunionResponse/ns1:listadoUsuariosReunion[1][sol:rol/cat:Id = 17]
    let $usuarioPre := $ConsultarUsuarioReunionResponse/ns1:listadoUsuariosReunion[1][sol:rol/cat:Id = 18]
    return
    <ns2:enviarCorreoElectronicoRequest>
        <ns2:CorreoElectronico>
         {
        if ($nivel= 1) then 
          <cor:to>
                  <cor:usuario>{fn:data($usuarioCC/sol:usuario)}</cor:usuario>
          </cor:to>
       else 
           <cor:to>
              <cor:usuario>{fn:data($usuarioPre/sol:usuario)}</cor:usuario>
           </cor:to>
        }
            <cor:idOperacion>{fn:data($ConsultarSolicitudAprobacionByIdResponse/ns1:solicitudAprobacion/sol:idOperacion)}</cor:idOperacion>
            <cor:idClienteFenix>{fn:data($ConsultarSolicitudAprobacionByIdResponse/ns1:solicitudAprobacion/sol:cliente/cli:idCliente)}</cor:idClienteFenix>
            <cor:id_plantilla>{if ($nivel= 1) then 78 else 77}</cor:id_plantilla>
           <cor:parametros>
                <cor:tag>NOMBRE_USUARIO</cor:tag>
                <cor:valor>{fn:data($CrearRegistroVotacionRequest/ns3:registroVotacion/ns3:idUsuario)}</cor:valor>
            </cor:parametros>
            <cor:parametros>
                <cor:tag>TIPO_DECISION</cor:tag>
                <cor:valor>{fn:data($idRegistroVotacion)}</cor:valor>
            </cor:parametros>
            {
            if (fn:data($ConsultarSolicitudAprobacionByIdResponse/ns1:solicitudAprobacion/sol:idOperacion/text())!= '') then
            <cor:parametros>
                <cor:tag>TIPO_TRANSACCION</cor:tag>
                <cor:valor>1</cor:valor>
            </cor:parametros>
            else 
            <cor:parametros>
                <cor:tag>TIPO_TRANSACCION</cor:tag>
                <cor:valor>0</cor:valor>
            </cor:parametros>
            }
            
        </ns2:CorreoElectronico>
    </ns2:enviarCorreoElectronicoRequest>
};

local:func($CrearRegistroVotacionRequest, $ConsultarSolicitudAprobacionByIdResponse, $ConsultarUsuarioReunionResponse, $idRegistroVotacion)
