xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/CondicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace mat="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd", 
                     "oramds:/apps/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)

declare namespace mat1 = "http://www.bcie.org/MatrizTCCBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $inputVariable.request as element() (:: schema-element(mat:ActualizarTCCRequest) ::) external;
declare variable $outConsultarCondicionActualizada.response as element() (:: schema-element(con:ConsultarCondicionResponse) ::) external;

declare function local:funcDetalleenmiendatransformation($inputVariable.request as element() (:: schema-element(mat:ActualizarTCCRequest) ::), 
                                                         $outConsultarCondicionActualizada.response as element() (:: schema-element(con:ConsultarCondicionResponse) ::)) 
                                                         as element() (:: schema-element(mat:ActualizarDetalleEnmiendaRequest) ::) {
    <mat:ActualizarDetalleEnmiendaRequest>
        <mat:detalleEnmienda>
            <mat1:tipoTCC>{fn:data($inputVariable.request/mat:Tipo)}</mat1:tipoTCC>
            <mat1:idTCC>{fn:data($inputVariable.request/mat:Condicion/con1:idCondicion)}</mat1:idTCC>
            <mat1:accion>{
               if (fn:data($outConsultarCondicionActualizada.response/con:Condicion/con1:subEstadoTCC/atr:id) = 16)
                then('AGREGAR')
                    else if (fn:data($outConsultarCondicionActualizada.response/con:Condicion/con1:subEstadoTCC/atr:id) = 17)
                        then ('DISPENSAR')
                            else if (fn:data($outConsultarCondicionActualizada.response/con:Condicion/con1:subEstadoTCC/atr:id) = 18)
                                then ('MODIFICAR')
                                    else if (fn:data($outConsultarCondicionActualizada.response/con:Condicion/con1:subEstadoTCC/atr:id) = 19)
                                        then ('ELIMINAR')
                                            else()
            }
            </mat1:accion>
            <mat1:estado>{fn:data($outConsultarCondicionActualizada.response/con:Condicion/con1:subEstadoTCC/atr:id)}</mat1:estado>
        </mat:detalleEnmienda>
    </mat:ActualizarDetalleEnmiendaRequest>
};

local:funcDetalleenmiendatransformation($inputVariable.request, $outConsultarCondicionActualizada.response)
