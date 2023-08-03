xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace com="http://www.bcie.org/ComisionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace mat="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd", 
                     "oramds:/apps/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace mat1 = "http://www.bcie.org/MatrizTCCBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $inputVariable.request as element() (:: schema-element(mat:ActualizarTCCRequest) ::) external;
declare variable $outConsultarComisionActualizada.response as element() (:: schema-element(com:ConsultarComisionResponse) ::) external;

declare function local:funcDetalleenmiendatransformation($inputVariable.request as element() (:: schema-element(mat:ActualizarTCCRequest) ::), 
                                                         $outConsultarComisionActualizada.response as element() (:: schema-element(com:ConsultarComisionResponse) ::)) 
                                                         as element() (:: schema-element(mat:ActualizarDetalleEnmiendaRequest) ::) {
    <mat:ActualizarDetalleEnmiendaRequest>
        <mat:detalleEnmienda>            
            <mat1:tipoTCC>{fn:data($inputVariable.request/mat:Tipo)}</mat1:tipoTCC>
            <mat1:idTCC>{fn:data($inputVariable.request/mat:Comision/com1:idComision)}</mat1:idTCC>
            <mat1:accion>{
                if(fn:data($outConsultarComisionActualizada.response/com:Comision/com1:subEstadoTCC/atr:id) = 16)
                    then('AGREGAR')
                        else if (fn:data($outConsultarComisionActualizada.response/com:Comision/com1:subEstadoTCC/atr:id) = 17)
                            then('DISPENSAR')
                                else if (fn:data($outConsultarComisionActualizada.response/com:Comision/com1:subEstadoTCC/atr:id) = 18)
                                    then('MODIFICAR')
                                        else if (fn:data($outConsultarComisionActualizada.response/com:Comision/com1:subEstadoTCC/atr:id) = 19)
                                            then('ELIMINAR')
                                            else()
            }
            </mat1:accion>
            
        </mat:detalleEnmienda>
    </mat:ActualizarDetalleEnmiendaRequest>
};

local:funcDetalleenmiendatransformation($inputVariable.request, $outConsultarComisionActualizada.response)
