xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/CorreoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)

declare namespace cor = "http://www.bcie.org/CorreoBO";

declare namespace con = "http://www.bcie.org/CondicionBO";


declare variable $ConsultarCondicionValidacionByRolResponse as element() (:: schema-element(ns1:ConsultarCondicionValidacionByRolResponse) ::) external;
declare variable $NotificarEliminacionEvidenciaRequest as element() (:: schema-element(ns1:NotificarEliminacionEvidenciaRequest) ::) external;

declare function local:func($ConsultarCondicionValidacionByRolResponse as element() (:: schema-element(ns1:ConsultarCondicionValidacionByRolResponse) ::), 
                            $NotificarEliminacionEvidenciaRequest as element() (:: schema-element(ns1:NotificarEliminacionEvidenciaRequest) ::)) 
                            as element() (:: schema-element(ns2:enviarCorreoElectronicoRequest) ::) {
      let $usuarios:= $ConsultarCondicionValidacionByRolResponse/ns1:Condicion[ns1:Condicion/con:idCondicion=$NotificarEliminacionEvidenciaRequest/ns1:idCondicion]/ns1:ValidacionCondicion
    return
    <ns2:enviarCorreoElectronicoRequest>
        <ns2:CorreoElectronico>
            <cor:to>
            {
            for $usuario in $usuarios/con:loginUsuario
            return
                <cor:usuario>{fn:data($usuario)}</cor:usuario>
            }
            </cor:to>
            <cor:idOperacion>{fn:data($NotificarEliminacionEvidenciaRequest/ns1:idOperacion)}</cor:idOperacion>
            <cor:id_plantilla>89</cor:id_plantilla>
            <cor:parametros>
                <cor:tag>NOMBRE_DOCTO</cor:tag>
                <cor:valor>{fn:data($NotificarEliminacionEvidenciaRequest/ns1:idDocumento)}</cor:valor>
            </cor:parametros>
            <cor:parametros>
                <cor:tag>NOMBRE_CONDICION</cor:tag>
                <cor:valor>{fn:data($NotificarEliminacionEvidenciaRequest/ns1:idCondicion)}</cor:valor>
            </cor:parametros>
        </ns2:CorreoElectronico>
    </ns2:enviarCorreoElectronicoRequest>
};

local:func($ConsultarCondicionValidacionByRolResponse, $NotificarEliminacionEvidenciaRequest)
