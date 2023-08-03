xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace cor="http://www.bcie.org/CorreoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/EnviarCorreoElectronico/V1/Schema/CorreoElectronicoMO.xsd" ::)

declare namespace cor1 = "http://www.bcie.org/CorreoBO";

declare variable $inputVariable.EnviarCorreoElectronicoRequest as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::) external;
declare variable $outInvoke_ConsultarPlantilla.ConsultarPlantillaByProcesoResponse as element() (:: schema-element(cor:ConsultarPlantillaByProcesoResponse) ::) external;

declare function local:funcBpelrq_to_obtenerrolesusuariosbyplantillarq($inputVariable.EnviarCorreoElectronicoRequest as element() (:: schema-element(cor:EnviarCorreoElectronicoBPELRequest) ::), 
                                                                       $outInvoke_ConsultarPlantilla.ConsultarPlantillaByProcesoResponse as element() (:: schema-element(cor:ConsultarPlantillaByProcesoResponse) ::)) 
                                                                       as element() (:: schema-element(cor:ObtenerRolesUsuariosByPlantillaRequest) ::) {
    <cor:ObtenerRolesUsuariosByPlantillaRequest>
        <cor:descripcionPlantilla>{fn:data($outInvoke_ConsultarPlantilla.ConsultarPlantillaByProcesoResponse/cor:ProcesoEventoPlantilla/cor1:descripcion)}</cor:descripcionPlantilla>
        {
            if ($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:idOperacion)
            then <cor:idOperacion>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:idOperacion)}</cor:idOperacion>
            else ()
        }
        {
            if ($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:idClienteFenix)
            then <cor:idCliente>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:idClienteFenix)}</cor:idCliente>
            else ()
        }
        {
            if ($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:proceso)
            then <cor:idProceso>{fn:data($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:proceso)}</cor:idProceso>
            else ()
        }
        <cor:banderas>
            <cor1:tag>BAN_VALIDA_ET</cor1:tag>
            <cor1:valor>{fn:data($outInvoke_ConsultarPlantilla.ConsultarPlantillaByProcesoResponse/cor:ProcesoEventoPlantilla/cor1:banValidaET)}</cor1:valor>
        </cor:banderas>
         <cor:banderas>
            <cor1:tag>BAN_VALIDA_MCC</cor1:tag>
            <cor1:valor>{fn:data($outInvoke_ConsultarPlantilla.ConsultarPlantillaByProcesoResponse/cor:ProcesoEventoPlantilla/cor1:banValidaMCC)}</cor1:valor>
        </cor:banderas>
         <cor:banderas>
            <cor1:tag>BAN_VALIDA_VA</cor1:tag>
            <cor1:valor>{fn:data($outInvoke_ConsultarPlantilla.ConsultarPlantillaByProcesoResponse/cor:ProcesoEventoPlantilla/cor1:banValidaVA)}</cor1:valor>
        </cor:banderas>
        <cor:banderas>
            <cor1:tag>BAN_VALIDA_RA</cor1:tag>
            <cor1:valor>{fn:data($outInvoke_ConsultarPlantilla.ConsultarPlantillaByProcesoResponse/cor:ProcesoEventoPlantilla/cor1:banValidaRA)}</cor1:valor>
        </cor:banderas>
        <cor:banderas>
            <cor1:tag>BAN_VALIDA_RO</cor1:tag>
            {
            if($outInvoke_ConsultarPlantilla.ConsultarPlantillaByProcesoResponse/cor:ProcesoEventoPlantilla/cor1:banValidaRO = 1)
              then <cor1:valor>{fn:data($outInvoke_ConsultarPlantilla.ConsultarPlantillaByProcesoResponse/cor:ProcesoEventoPlantilla/cor1:banValidaRO)}</cor1:valor>
            else (
            
            if($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:parametros[cor1:tag='ROL']/cor1:valor = 8)
              then<cor1:valor>1</cor1:valor>
            else
              <cor1:valor>{fn:data($outInvoke_ConsultarPlantilla.ConsultarPlantillaByProcesoResponse/cor:ProcesoEventoPlantilla/cor1:banValidaRO)}</cor1:valor>
            )}
        </cor:banderas>
        {   
        for $parametros in ($inputVariable.EnviarCorreoElectronicoRequest/cor:correoElectronico/cor1:parametros)
          return
          
           if(fn:data($parametros/cor1:tag) ='ID_ACCION') then
           <cor:accciones>
           
            <cor1:tag>{fn:data($parametros/cor1:tag)}</cor1:tag>
            <cor1:valor>{fn:data($parametros/cor1:valor)}</cor1:valor>
            </cor:accciones>
          else()
        }
       
    </cor:ObtenerRolesUsuariosByPlantillaRequest>
};

local:funcBpelrq_to_obtenerrolesusuariosbyplantillarq($inputVariable.EnviarCorreoElectronicoRequest, $outInvoke_ConsultarPlantilla.ConsultarPlantillaByProcesoResponse)
